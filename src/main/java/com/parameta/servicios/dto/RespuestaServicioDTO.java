package com.parameta.servicios.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaServicioDTO {

	private String tiempoVinculacion;
	private String edadActualEmpleado;
	private String mensajeError = null;
	private Integer codigoError = null;
	private boolean servicioExitoso = true;

	public String getTiempoVinculacion() {
		return tiempoVinculacion;
	}

	public void setTiempoVinculacion(String tiempoVinculacion) {
		this.tiempoVinculacion = tiempoVinculacion;
	}

	public String getEdadActualEmpleado() {
		return edadActualEmpleado;
	}

	public void setEdadActualEmpleado(String edadActualEmpleado) {
		this.edadActualEmpleado = edadActualEmpleado;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Integer getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}

	public boolean isServicioExitoso() {
		return servicioExitoso;
	}

	public void setServicioExitoso(boolean servicioExitoso) {
		this.servicioExitoso = servicioExitoso;
	}

}
