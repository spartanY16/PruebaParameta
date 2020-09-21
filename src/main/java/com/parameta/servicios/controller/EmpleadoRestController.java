package com.parameta.servicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parameta.servicios.dto.EmpleadoDTO;
import com.parameta.servicios.dto.RespuestaServicioDTO;
import com.parameta.servicios.service.EmpleadoService;

@RestController
@RequestMapping("/servicioParameta")
public class EmpleadoRestController {
	
	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping(value = "/orquestar")
	public ResponseEntity<Object> transformarServicio(
			@RequestHeader(value = "nombres", required = false) String nombres,
			@RequestHeader(value = "apellidos", required = false) String apellidos,
			@RequestHeader(value = "tipoDeDocumento", required = false) String tipoDeDocumento,
			@RequestHeader(value = "numeroDeDocumento", required = false) String numeroDeDocumento,
			@RequestHeader(value = "fechaDeNacimiento", required = false) String fechaDeNacimiento,
			@RequestHeader(value = "fechaDeVinculacion", required = false) String fechaDeVinculacion,
			@RequestHeader(value = "cargo", required = false) String cargo,
			@RequestHeader(value = "salario", required = false) Double salario) {

		ResponseEntity<Object> respuestaServicio = null;
		EmpleadoDTO empleado = new EmpleadoDTO(nombres, apellidos, tipoDeDocumento, numeroDeDocumento, cargo, salario);

		RespuestaServicioDTO respuestaServicioDTO = new RespuestaServicioDTO();

		empleado.setFechaNacimiento(
				empleadoService.validadorDeFecha(fechaDeNacimiento, empleado, respuestaServicioDTO) != null
						? empleadoService.validadorDeFecha(fechaDeNacimiento, empleado, respuestaServicioDTO)
						: null);

		if (respuestaServicioDTO.isServicioExitoso()) {

			empleado.setFechaVinculacionCompania(
					empleadoService.validadorDeFecha(fechaDeVinculacion, empleado, respuestaServicioDTO) != null
							? empleadoService.validadorDeFecha(fechaDeVinculacion, empleado, respuestaServicioDTO)
							: null);

			if (respuestaServicioDTO.isServicioExitoso()) {

				empleadoService.validadorDeParametros(empleado, respuestaServicioDTO);

				if (respuestaServicioDTO.isServicioExitoso()) {

					empleadoService.edadDelEmpleado(empleado, respuestaServicioDTO);
					empleadoService.tiempoEnLaCompania(empleado, respuestaServicioDTO);
					empleadoService.guardarEmpleadoSOAP(empleado, respuestaServicioDTO);

				}

			}

		}

		respuestaServicio = new ResponseEntity<>(respuestaServicioDTO, HttpStatus.OK);
		return respuestaServicio;

	}

}
