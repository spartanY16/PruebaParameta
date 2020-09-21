package com.parameta.servicios.util;

import java.time.LocalDate;
import java.time.Period;

public class Utilidades {

	public static boolean validadorDeVacio(String variableAValidar) {

		if (variableAValidar == null || variableAValidar.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean esMayorDeEdad(LocalDate fechaAValidar) {

		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(fechaAValidar, fechaActual);

		if (periodo.getYears() < 18) {

			return false;
		} else {

			return true;
		}

	}

}
