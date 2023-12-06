package it.aruba.sp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aruba.sp.dto.PraticaDto;
import it.aruba.sp.service.PraticaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/pratica")
@RequiredArgsConstructor
public class PraticaController {
	
	private final PraticaService praticaService;
	
	@GetMapping("/{codicePratica}")
	public ResponseEntity<PraticaDto> getPraticaByCodicePratica (@PathVariable String codicePratica) {
		
		return new ResponseEntity<PraticaDto>(
				praticaService.getPraticaByCodicePratica(codicePratica), 
				HttpStatus.OK);

	}

}
