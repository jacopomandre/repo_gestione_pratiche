package it.aruba.sp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.aruba.sp.dto.PraticaDto;
import it.aruba.sp.entity.Pratica;
import it.aruba.sp.repository.PraticaRepository;
import it.aruba.sp.utils.Converter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PraticaService {
	
	private final PraticaRepository praticaRepository;

	public PraticaDto getPraticaByCodicePratica(String codicePratica) {		
		Optional <Pratica> optPratica = praticaRepository.findByCodicePratica(codicePratica);
		return Converter.convertPraticaToPraticaDto(optPratica.get());
	}

}
