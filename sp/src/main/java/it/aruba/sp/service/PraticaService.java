package it.aruba.sp.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.aruba.sp.dto.PraticaDto;
import it.aruba.sp.dto.UpsertVersionePraticaDto;
import it.aruba.sp.dto.VersionePraticaDto;
import it.aruba.sp.entity.Pratica;
import it.aruba.sp.entity.RisultatoPratica;
import it.aruba.sp.entity.StatoPratica;
import it.aruba.sp.entity.VersionePratica;
import it.aruba.sp.enumeration.RisultatoPraticaEnum;
import it.aruba.sp.enumeration.StatoPraticaEnum;
import it.aruba.sp.repository.PraticaRepository;
import it.aruba.sp.repository.RisultatoPraticaRepository;
import it.aruba.sp.repository.StatoPraticaRepository;
import it.aruba.sp.repository.VersionePraticaRepository;
import it.aruba.sp.utils.CalcoloVersioneUtils;
import it.aruba.sp.utils.Converter;
import it.aruba.sp.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PraticaService {
	
	private final PraticaRepository praticaRepository;
	private final StatoPraticaRepository statoPraticaRepository;
	private final VersionePraticaRepository versionePraticaRepository;
	private final RisultatoPraticaRepository risultatoPraticaRepository;


	public PraticaDto getPraticaByCodicePratica(String codicePratica) {		
		Optional <Pratica> optPratica = praticaRepository.findByCodicePratica(codicePratica);
		return Converter.convertPraticaToPraticaDto(optPratica.get());
	}

	public PraticaDto createPratica(UpsertVersionePraticaDto upsertVersionePraticaDto) {

		Pratica pratica = Pratica.builder()
				.codicePratica(upsertVersionePraticaDto.getCodicePratica())
				.versioni(new ArrayList<VersionePratica>())
				.build();
		
		pratica = praticaRepository.save(pratica);
		
		VersionePratica versionePratica = preparazioneVersionePratica(
				upsertVersionePraticaDto, 
				pratica, 
				CalcoloVersioneUtils.UNO,
				statoPraticaRepository.findByDescrizione(StatoPraticaEnum.CREATA.name()));
		
		versionePratica = versionePraticaRepository.save(versionePratica);
		
		pratica.getVersioni().add(versionePratica);
		return Converter.convertPraticaToPraticaDto(pratica);
	}
	
	public PraticaDto updateVersionePratica(String codicePratica, UpsertVersionePraticaDto upsertVersionePraticaDto) {

		VersionePratica versionePraticaDb = versionePraticaRepository
				.findTopByPratica_CodicePraticaOrderByNumeroVersioneDesc(codicePratica);

		ValidationUtils.checkStatoVersione(versionePraticaDb, StatoPraticaEnum.CREATA);
		
		VersionePratica versionePraticaInsert = preparazioneVersionePratica(
				upsertVersionePraticaDto, 
				versionePraticaDb.getPratica(), 
				Integer.sum(versionePraticaDb.getNumeroVersione(), CalcoloVersioneUtils.UNO),
				versionePraticaDb.getStato());

		versionePraticaInsert = versionePraticaRepository.save(versionePraticaInsert);
		
		Pratica pratica = versionePraticaDb.getPratica();
		pratica.setCodicePratica(upsertVersionePraticaDto.getCodicePratica());
		
		pratica = praticaRepository.save(pratica);		
		pratica.getVersioni().add(versionePraticaInsert);

		return Converter.convertPraticaToPraticaDto(pratica);
	}
	
	private VersionePratica preparazioneVersionePratica(UpsertVersionePraticaDto upsertVersionePraticaDto,
			Pratica pratica, Integer numeroVersione, StatoPratica stato) {
		
		VersionePratica versionePratica= Converter.convertUpsertVersionePraticaDtoToVersionePratica(upsertVersionePraticaDto);
		versionePratica.setNumeroVersione(numeroVersione);
		versionePratica.setPratica(pratica);
		versionePratica.setStato(stato);
		return versionePratica;
		
	}

	public VersionePraticaDto avanzamentoPratica(String codicePratica, String risultatoDescrizione) throws CloneNotSupportedException {
		
		VersionePratica versionePraticaDb = versionePraticaRepository
				.findTopByPratica_CodicePraticaOrderByNumeroVersioneDesc(codicePratica);
		
		Integer nextStep = Integer.sum(versionePraticaDb.getStato().getStep(), CalcoloVersioneUtils.UNO);
		Optional<StatoPratica> optStato = statoPraticaRepository.findByStep(nextStep);
		
		ValidationUtils.checkOptStatoStep(optStato, nextStep);
		
		StatoPratica nextStato = optStato.get();
		
		VersionePratica versionePraticaInsert = versionePraticaDb.clone();
		versionePraticaInsert.setId(null);
		versionePraticaInsert.setNumeroVersione(Integer.sum(versionePraticaDb.getNumeroVersione(), CalcoloVersioneUtils.UNO));
		versionePraticaInsert.setStato(nextStato);
		
		if (StatoPraticaEnum.COMPLETATA.name().equals(nextStato.getDescrizione())) {
			Optional<RisultatoPratica> optRisultato = risultatoPraticaRepository.findByDescrizione(risultatoDescrizione);
			
			ValidationUtils.checkOptRisulatato(optRisultato, risultatoDescrizione);
			
			versionePraticaInsert.setRisultato(optRisultato.get());
		}
		
		VersionePratica versionePraticaSaved = versionePraticaRepository.save(versionePraticaInsert);
		
		return Converter.convertVersionePraticaToVersionePraticaDto(versionePraticaSaved);
	}

}
