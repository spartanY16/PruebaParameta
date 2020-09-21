package com.parameta.servicios.enums;

public enum CodigosError {

	NOMBRES_OBLIGATORIOS(1, "El parámetro 'nombres' es requerido"),
	APELLIDOS_OBLIGATORIOS(2, "El parámetro 'apellidos' es requerido"),
	TIPO_DOCUMENTO_OBLIGATORIO(3, "El parámetro 'numeroDeDocumento' es requerido"),
	NUMERO_DOCUMENTO_OBLIGATORIO(4, "El parámetro 'tipoDeDocumento' es requerido"),
	FECHA_NACIMIENTO_OBLIGATORIA(5, "El parámetro 'fechaDeNacimiento' es requerido"),
	FECHA_VINCULACION_OBLIGATORIA(6, "El parámetro 'fechaDeVinculación' es requerido"),
	CARGO_OBLIGATORIO(7, "El parámetro 'cargo' es requerido"),
	SALARIO_OBLIGATORIO(8, "El parámetro 'salario' es requerido"),
	MENOR_DE_EDAD(9, "El empleado a registrar no puede ser menor de edad"),
	FORMATO_FECHA_INCORRECTO(10, "El formato de las fechas debe tener la siguiente estructura: DD/MM/AAAA"),
	FALLO_SERVICIO_SOAP(11, "Se presentó un problema al momento de guardar el empleado por medio del servicio SOAP");

	private Integer codigoError;
	private String descripcionError;

	CodigosError(Integer codigo, String descripcion) {
		codigoError = codigo;
		descripcionError = descripcion;
	}

	public Integer getCodigoError() {
		return codigoError;
	}

	public String getDescripcion() {
		return descripcionError;
	}

}
