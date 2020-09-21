package com.parameta.servicios.enums;

public enum Constantes {

	FORMATO_FECHA("dd/MM/yyyy"), EDAD_EMPLEADO("La edad actual del empleado de: "),
	TIEMPO_VINCUALCION("El tiempo de vinculación del empleado en la compañia es de: "), URL_SERVICIO_SOAP("http://localhost:8050/ws");

	private String descripcion;

	Constantes(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;

	}

	public String getEdadEmpleado(Integer anio, Integer mes, Integer dia) {

		return descripcion + anio + " años, " + mes + " mes(es) y " + dia + " dia(s).";

	}

	public String getTiempoDeVinculacion(Integer anio, Integer mes, Integer dia) {

		return descripcion + anio + " años, " + mes + " mes(es) y " + dia + " dia(s).";

	}

}
