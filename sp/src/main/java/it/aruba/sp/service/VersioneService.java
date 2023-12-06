package it.aruba.sp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.aruba.sp.dto.InsertVersionePraticaDto;
import it.aruba.sp.dto.VersionePraticaDto;
import it.aruba.sp.entity.Pratica;
import it.aruba.sp.entity.RisultatoPratica;
import it.aruba.sp.entity.StatoPratica;
import it.aruba.sp.entity.VersionePratica;
import it.aruba.sp.repository.PraticaRepository;
import it.aruba.sp.repository.VersionePraticaRepository;
import it.aruba.sp.utils.Converter;
import it.aruba.sp.utils.VersioneUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VersioneService {
	
	private final PraticaRepository praticaRepository;
	private final VersionePraticaRepository versionePraticaRepository;
	
	public VersionePraticaDto insertVersione(InsertVersionePraticaDto insertVersionePraticaDto) {
		
		VersionePratica versionePratica = null;
		Optional<Pratica> optPratica = praticaRepository.findById(insertVersionePraticaDto.getIdPratica());
		
		
		if (optPratica.isPresent()) {
			
			Pratica pratica = optPratica.get();
			
			versionePratica = 
					VersionePratica.builder()
					.pratica(pratica)
					.stato(StatoPratica.builder()
							.id(insertVersionePraticaDto.getIdStato())
							.build())
					.descrizione(insertVersionePraticaDto.getDescrizione())
					.risultato(RisultatoPratica.builder()
							.id(insertVersionePraticaDto.getIdRisultato())
							.build())
					.note(insertVersionePraticaDto.getNote())
					.numeroVersione(VersioneUtils.calcolaNextNumeroVersione(pratica.getVersioni()))
					.build();
			
			versionePratica = versionePraticaRepository.save(versionePratica);
			
			
		} else {
			
			new RuntimeException ("L'identificativo pratica non presente, versione non salvata");
		}

		
		return Converter.convertVersionePraticaToVersionePraticaDto(versionePratica);
	}


}
