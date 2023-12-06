package it.aruba.sp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.aruba.sp.dto.InsertVersionePraticaDto;
import it.aruba.sp.dto.PraticaDto;
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
	
	public void createVersione() throws Exception {
	   String uri = "/versione";
	   InsertVersionePraticaDto ivpDto = InsertVersionePraticaDto.builder()
			   .idPratica(1L)
			   .idStato(10001L)
			   .idRisultato(10001L)
			   .descrizione("modifica")
			   .build();
	   
	   String inputJson = JsonUtils.mapToJson(ivpDto);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(Utente.builder().username("1234567890").password("").id(1L).build()))
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertThat(status).isEqualTo(200);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertThat(content).isNotNull();
	}
	
//	@Test
	public PraticaDto getPraticaDtoByCodicePratica() throws Exception {
	
		String codicePratica = "PRIMA";
		
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
	
	@Test 
	public void salvataggioRecupero() throws Exception  {
		createVersione();
		PraticaDto output = getPraticaDtoByCodicePratica();
		assertThat(output.getVersioni().size()).isEqualTo(4);
		
	}
	
}
