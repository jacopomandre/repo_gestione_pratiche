package it.aruba.sp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.aruba.sp.dto.PraticaDto;
import it.aruba.sp.dto.UpsertPraticaDto;
import it.aruba.sp.entity.Pratica;
import it.aruba.sp.entity.VersionePratica;
import it.aruba.sp.enumeration.StatoPraticaEnum;
import it.aruba.sp.repository.PraticaRepository;
import it.aruba.sp.repository.StatoPraticaRepository;
import it.aruba.sp.repository.VersionePraticaRepository;
import it.aruba.sp.utils.Converter;
import it.aruba.sp.utils.VersioneUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PraticaService {
	
	private final PraticaRepository praticaRepository;
	private final StatoPraticaRepository statoPraticaRepository;
	private final VersionePraticaRepository versionePraticaRepository;


	public PraticaDto getPraticaByCodicePratica(String codicePratica) {		
		Optional <Pratica> optPratica = praticaRepository.findByCodicePratica(codicePratica);
		return Converter.convertPraticaToPraticaDto(optPratica.get());
	}

	public PraticaDto createPratica(UpsertPraticaDto createPraticaDto) {

		Pratica pratica = Pratica.builder()
				.codicePratica(createPraticaDto.getCodicePratica())
				.build();
		
		pratica = praticaRepository.save(pratica);
		
		VersionePratica versionePratica = VersionePratica.builder()
				.stato(statoPraticaRepository.findByDescrizione(StatoPraticaEnum.CREATA.name()))
				.numeroVersione(VersioneUtils.UNO)
				.descrizione(createPraticaDto.getDescrizioneVersione())
				.note(createPraticaDto.getNoteVersione())
				.pratica(pratica)
				.build();
		
		versionePratica = versionePraticaRepository.save(versionePratica);
		
		return Converter.convertPraticaToPraticaDto(versionePratica.getPratica());
	}

	public PraticaDto updatePratica(String codicePratica, UpsertPraticaDto updatePraticaDto) {
		
		VersionePratica versionePratica = versionePraticaRepository
				.findTopByPratica_CodicePraticaOrderByNumeroVersioneDesc(codicePratica);
		
		if (checkStato(versionePratica, StatoPraticaEnum.CREATA)) {
			
			
		} else {
			throw new RuntimeException("Stato diverso da Creata: operazione di update non consentita");
		}
		
		return null;
	}

	private boolean checkStato(VersionePratica versionePratica, StatoPraticaEnum statoPraticaEnum) {
		return statoPraticaEnum.name().equals(versionePratica.getStato().getDescrizione());	
	}

}
