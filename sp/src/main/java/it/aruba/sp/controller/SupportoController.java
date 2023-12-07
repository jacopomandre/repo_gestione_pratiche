package it.aruba.sp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.aruba.sp.dto.VersionePraticaDto;
import it.aruba.sp.service.PraticaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/supporto")
@RequiredArgsConstructor
public class SupportoController {
	
	private final PraticaService praticaService;
	
	@PutMapping("/avanzamento-pratica")
	public ResponseEntity<VersionePraticaDto> avanzamentoPratica (
			@RequestParam String codicePratica,
			@RequestParam (required = false) String risultato) throws CloneNotSupportedException {
		
		return new ResponseEntity<VersionePraticaDto>(
				praticaService.avanzamentoPratica(codicePratica, risultato), 
				HttpStatus.OK);

	}

}
