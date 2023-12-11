package it.aruba.sp.utils;

import java.util.Optional;

import it.aruba.sp.entity.RisultatoPratica;
import it.aruba.sp.entity.StatoPratica;
import it.aruba.sp.entity.VersionePratica;
import it.aruba.sp.enumeration.StatoPraticaEnum;

public class ValidationUtils {

	public static void checkStatoVersione(VersionePratica versionePratica, StatoPraticaEnum statoPraticaEnum) {
		if(!statoPraticaEnum.name().equals(versionePratica.getStato().getDescrizione())) {
			throw new RuntimeException("Stato diverso da "+ statoPraticaEnum.name() +": operazione non consentita");	
		}
	}

	public static void checkOptStatoStep(Optional<StatoPratica> optStato, Integer nextStep) {
		if (!optStato.isPresent()) {
			throw new RuntimeException("Stato con step "+nextStep+" non presente a database");	
		}
	}

	public static void checkOptRisulatato(Optional<RisultatoPratica> optRisultato, String risultatoDescrizione) {
		if (!optRisultato.isPresent()) {
			throw new RuntimeException("Risultato con descrizione "+risultatoDescrizione+" non presente a database");	
		}
		
	}
}
