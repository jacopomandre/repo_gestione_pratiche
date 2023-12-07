package it.aruba.sp;

import static org.assertj.core.api.Assertions.assertThat;

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
import it.aruba.sp.dto.UpsertPraticaDto;
import it.aruba.sp.entity.Utente;
import it.aruba.sp.service.JwtService;
import it.aruba.sp.utils.JsonUtils;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	
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
		UpsertPraticaDto createBody = UpsertPraticaDto.builder()
		.codicePratica(codicePratica)
		.descrizioneVersione("versione iniziale")
		.noteVersione("bozza")
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
	
	@Test
	public void salvataggioAndRecuperoPraticaNuova () throws Exception {
		String codicePratica = "NUOVA-PRATICA";
		salvataggioPratica(codicePratica);
		PraticaDto praticaDto = getPraticaDtoByCodicePratica(codicePratica);
		assertThat(praticaDto.getVersioni().size()).isEqualTo(1);
		assertThat(praticaDto.getVersioni().getFirst().getNumeroVersione()).isEqualTo(1);
	}
	
}
