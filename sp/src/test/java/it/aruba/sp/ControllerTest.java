package it.aruba.sp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.aruba.sp.dto.PraticaDto;
import it.aruba.sp.dto.UpsertVersionePraticaDto;
import it.aruba.sp.dto.VersionePraticaDto;
import it.aruba.sp.entity.Utente;
import it.aruba.sp.service.JwtService;
import it.aruba.sp.utils.JsonUtils;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest {
	
	private static final String MODIFICA = "-suffissoModifica";

	private static final String NON_ESISTENTE = "CODICE_NON_ESISTENTE";

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JwtService jwtService;
	
	@Test
	public void salvataggioAndRecuperoPraticaNuova () throws Exception {
		String codicePratica = "NUOVA-PRATICA";
		salvataggioPratica(codicePratica);
		updateVersionePratica(codicePratica);
		avanzamentoPratica(codicePratica + MODIFICA, null);
		avanzamentoPratica(codicePratica + MODIFICA, "APPROVATA");
		PraticaDto praticaDto = getPraticaDtoByCodicePratica(codicePratica + MODIFICA);
		assertThat(praticaDto.getVersioni().size()).isEqualTo(4);
		
	}
	
	public PraticaDto getPraticaDtoByCodicePratica(String codicePratica) throws Exception {
		
		String uri = "/pratica/"+ codicePratica;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(Utente.builder().username("1234567890").password("").id(1L).build()))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(200);
		String content = mvcResult.getResponse().getContentAsString();
		PraticaDto praticaDto = JsonUtils.mapFromJson(content, PraticaDto.class);
		assertThat(praticaDto).isNotNull();
		return praticaDto;
	}
	
	public PraticaDto salvataggioPratica(String codicePratica) throws Exception  {
		String uri = "/pratica/crea-nuova";
		UpsertVersionePraticaDto createBody = UpsertVersionePraticaDto.builder()
		.codicePratica(codicePratica)
		.descrizione("versione iniziale")
		.note("bozza")
		.nome("Mario")
		.cognome("Rossi")
		.codiceFiscale("RSSMRA80A01H501U")
		.dataDiNascita(LocalDate.of(1980, 1, 1))
		.fileName("nomeFile")
		.filePath("pathFile")
		.build();

		String inputJson = JsonUtils.mapToJson(createBody);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(Utente.builder().username("1234567890").password("").id(1L).build()))
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(200);
		String content = mvcResult.getResponse().getContentAsString();
		PraticaDto praticaDto = JsonUtils.mapFromJson(content, PraticaDto.class);
		return praticaDto;
		
	}
	
	public PraticaDto updateVersionePratica(String codicePratica) throws Exception  {
		String uri = "/pratica/update";
		UpsertVersionePraticaDto createBody = UpsertVersionePraticaDto.builder()
		.codicePratica(codicePratica + MODIFICA)
		.descrizione("versione modificata")
		.note("revisione")
		.nome("Giuseppe")
		.cognome("Verdi")
		.codiceFiscale("VRDGPP62A01H501S")
		.dataDiNascita(LocalDate.of(1962, 1, 1))
		.fileName("nomeFile"+MODIFICA)
		.filePath("pathFile"+MODIFICA)
		.build();

		String inputJson = JsonUtils.mapToJson(createBody);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders
				.put(uri)
				.queryParam("idPratica", codicePratica)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(Utente.builder().username("1234567890").password("").id(1L).build()))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(200);
		String content = mvcResult.getResponse().getContentAsString();
		PraticaDto praticaDto = JsonUtils.mapFromJson(content, PraticaDto.class);
		return praticaDto;
		
	}
	
	public VersionePraticaDto avanzamentoPratica(String codicePratica, String risultato) throws Exception  {
		String uri = "/supporto/avanzamento-pratica";
		
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders
				.put(uri)
				.queryParam("idPratica", codicePratica)
				.queryParam("risultato", risultato)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(Utente.builder().username("1234567890").password("").id(1L).build()))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(200);
		String content = mvcResult.getResponse().getContentAsString();
		VersionePraticaDto versionePraticaDto = JsonUtils.mapFromJson(content, VersionePraticaDto.class);
		return versionePraticaDto;
		
	}
	
}
