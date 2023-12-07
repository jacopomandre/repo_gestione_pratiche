package it.aruba.sp.utils;

import java.util.List;

import it.aruba.sp.entity.VersionePratica;

public class VersioneUtils {

	private static final Integer ZERO = 0;
	public static final Integer UNO = 1;

	public static Integer calcolaMaxNumeroVersione(List <VersionePratica>versioni) {

		return versioni.stream()
				.map(VersionePratica::getNumeroVersione)
				.max(Integer::compareTo).
				orElse(ZERO);
	}
	
	public static Integer calcolaNextNumeroVersione(List <VersionePratica>versioni) {

		return Integer.sum(calcolaMaxNumeroVersione(versioni), UNO);
	}
	
}
