package it.aruba.sp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.aruba.sp.dto.PraticaDto;
import it.aruba.sp.dto.UpsertVersionePraticaDto;
import it.aruba.sp.service.PraticaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/pratica")
@RequiredArgsConstructor
public class PraticaController {
	
	private final PraticaService praticaService;
	
	@GetMapping("/{codicePratica}")
	public ResponseEntity<PraticaDto> getPraticaByCodicePratica (
			@PathVariable String codicePratica) {
		
		return new ResponseEntity<PraticaDto>(
				praticaService.getPraticaByCodicePratica(codicePratica), 
				HttpStatus.OK);

	}
	
	@PostMapping("/crea-nuova")
	public ResponseEntity<PraticaDto> createPratica (
			@RequestBody @Valid UpsertVersionePraticaDto createPraticaDto) {
		
		return new ResponseEntity<PraticaDto>(
				praticaService.createPratica(createPraticaDto), 
				HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<PraticaDto> updateVersionePratica (
			@RequestParam String codicePratica,
			@RequestBody @Valid UpsertVersionePraticaDto updatePraticaDto) {
		
		return new ResponseEntity<PraticaDto>(
				praticaService.updateVersionePratica(codicePratica, updatePraticaDto), 
				HttpStatus.OK);

	}
	
	

}
