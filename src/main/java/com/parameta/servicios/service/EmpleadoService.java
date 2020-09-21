package com.parameta.servicios.service;

import java.time.LocalDate;

import com.parameta.servicios.dto.EmpleadoDTO;
import com.parameta.servicios.dto.RespuestaServicioDTO;

public interface EmpleadoService {

	/**
	 * @param fecha
	 * @param empleado
	 * @param respuestaServicio
	 * @apiNote Valida que el formato de la fecha ingresada sea la correcta, en caso
	 *          tal devuevle la fecha con el formato correcto (LocalDate)
	 */
	public LocalDate validadorDeFecha(String fecha, EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio);

	/**
	 * @param empleado
	 * @param respuestaServicio
	 * @apiNote Valida que ningún atributo del empleado encuentre vacío
	 */
	public void validadorDeParametros(EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio);

	/**
	 * @param empleado
	 * @param respuestaServicio
	 * @return
	 * @apiNote Agrega la edad del empleado en el objeto de respuesta del servicio
	 */
	public void edadDelEmpleado(EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio);

	/**
	 * @param empleado
	 * @param respuestaServicio
	 * @return
	 * @apiNote Agrega el tiempo del empleado en la compañia en el objeto de
	 *          respuesta del servicio
	 */
	public void tiempoEnLaCompania(EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio);

	/**
	 * @param empleado
	 * @param respuestaServicio
	 * @return
	 * @apiNote Guarda el empleado por medio de un servicio SOAP
	 */
	public void guardarEmpleadoSOAP(EmpleadoDTO empleado, RespuestaServicioDTO respuestaServicio);

}
