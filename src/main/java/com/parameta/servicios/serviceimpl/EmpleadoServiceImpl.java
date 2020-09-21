package com.parameta.servicios.serviceimpl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parameta.servicios.dto.EmpleadoDTO;
import com.parameta.servicios.dto.RespuestaServicioDTO;
import com.parameta.servicios.enums.CodigosError;
import com.parameta.servicios.enums.Constantes;
import com.parameta.servicios.service.EmpleadoService;
import com.parameta.servicios.soap.client.SoapClient;
import com.parameta.servicios.soap.manager.CustomerRequest;
import com.parameta.servicios.soap.manager.Response;
import com.parameta.servicios.util.Utilidades;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private SoapClient clienteSOAP;

	@Override
	public LocalDate validadorDeFecha(String fecha, EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(Constantes.FORMATO_FECHA.getDescripcion());

		try {

			LocalDate fechaFormateada = LocalDate.parse(fecha, formato);
			return fechaFormateada;

		} catch (Exception e) {

			respuestaServicio.setCodigoError(CodigosError.FORMATO_FECHA_INCORRECTO.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.FORMATO_FECHA_INCORRECTO.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		}

		return null;

	}

	@Override
	public void validadorDeParametros(EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio) {

		if (Utilidades.validadorDeVacio(empleado.getTipoDocumento())) {

			respuestaServicio.setCodigoError(CodigosError.TIPO_DOCUMENTO_OBLIGATORIO.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.TIPO_DOCUMENTO_OBLIGATORIO.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		} else if (Utilidades.validadorDeVacio(empleado.getNumeroDocumento())) {

			respuestaServicio.setCodigoError(CodigosError.NUMERO_DOCUMENTO_OBLIGATORIO.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.NUMERO_DOCUMENTO_OBLIGATORIO.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		} else if (Utilidades.validadorDeVacio(empleado.getNombres())) {

			respuestaServicio.setCodigoError(CodigosError.NOMBRES_OBLIGATORIOS.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.NOMBRES_OBLIGATORIOS.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		} else if (Utilidades.validadorDeVacio(empleado.getApellidos())) {

			respuestaServicio.setCodigoError(CodigosError.APELLIDOS_OBLIGATORIOS.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.APELLIDOS_OBLIGATORIOS.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		} else if (Utilidades.validadorDeVacio(empleado.getFechaNacimiento().toString())) {

			respuestaServicio.setCodigoError(CodigosError.FECHA_NACIMIENTO_OBLIGATORIA.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.FECHA_NACIMIENTO_OBLIGATORIA.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		} else if (Utilidades.validadorDeVacio(empleado.getFechaVinculacionCompania().toString())) {

			respuestaServicio.setCodigoError(CodigosError.FECHA_VINCULACION_OBLIGATORIA.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.FECHA_VINCULACION_OBLIGATORIA.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		} else if (Utilidades.validadorDeVacio(empleado.getCargo())) {

			respuestaServicio.setCodigoError(CodigosError.CARGO_OBLIGATORIO.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.CARGO_OBLIGATORIO.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		} else if (Utilidades.validadorDeVacio(empleado.getSalario().toString())) {

			respuestaServicio.setCodigoError(CodigosError.SALARIO_OBLIGATORIO.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.SALARIO_OBLIGATORIO.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		} else if (!Utilidades.esMayorDeEdad(empleado.getFechaNacimiento())) {

			respuestaServicio.setCodigoError(CodigosError.MENOR_DE_EDAD.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.MENOR_DE_EDAD.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		}

	}

	@Override
	public void edadDelEmpleado(EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio) {

		LocalDate fechaActual = LocalDate.now();

		Period periodo = Period.between(empleado.getFechaNacimiento(), fechaActual);
		respuestaServicio.setEdadActualEmpleado(
				Constantes.EDAD_EMPLEADO.getEdadEmpleado(periodo.getYears(), periodo.getMonths(), periodo.getDays()));

	}

	@Override
	public void tiempoEnLaCompania(EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio) {

		LocalDate fechaActual = LocalDate.now();

		Period periodo = Period.between(empleado.getFechaVinculacionCompania(), fechaActual);
		respuestaServicio.setTiempoVinculacion(Constantes.TIEMPO_VINCUALCION.getTiempoDeVinculacion(periodo.getYears(),
				periodo.getMonths(), periodo.getDays()));

	}

	@Override
	public void guardarEmpleadoSOAP(EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio) {

		CustomerRequest peticion = new CustomerRequest(empleado.getNombres(), empleado.getApellidos(),
				empleado.getTipoDocumento(), empleado.getNumeroDocumento(), empleado.getFechaNacimiento().toString(),
				empleado.getFechaVinculacionCompania().toString(), empleado.getCargo(), empleado.getSalario());

		try {

			Response respuestaSOAP = clienteSOAP.guardarEmpleadoSOAP(peticion);

			if (!respuestaSOAP.isConsumoExitoso()) {

				respuestaServicio.setCodigoError(CodigosError.FALLO_SERVICIO_SOAP.getCodigoError());
				respuestaServicio.setMensajeError(CodigosError.FALLO_SERVICIO_SOAP.getDescripcion());
				respuestaServicio.setServicioExitoso(false);

			}

		} catch (Exception e) {

			respuestaServicio.setCodigoError(CodigosError.FALLO_SERVICIO_SOAP.getCodigoError());
			respuestaServicio.setMensajeError(CodigosError.FALLO_SERVICIO_SOAP.getDescripcion());
			respuestaServicio.setServicioExitoso(false);

		}

	}

}
