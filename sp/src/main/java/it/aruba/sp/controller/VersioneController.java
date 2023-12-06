package it.aruba.sp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aruba.sp.dto.InsertVersionePraticaDto;
import it.aruba.sp.dto.VersionePraticaDto;
import it.aruba.sp.service.VersioneService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/versione")
@RequiredArgsConstructor
public class VersioneController {
	
	private final VersioneService versioneService;
	
	@PostMapping
	public ResponseEntity<VersionePraticaDto> insert (
			@RequestBody @Validated InsertVersionePraticaDto insertVersionePraticaDto) {
		
		return new ResponseEntity<VersionePraticaDto>(
				versioneService.insertVersione(insertVersionePraticaDto), 
				HttpStatus.OK);

	}

}
