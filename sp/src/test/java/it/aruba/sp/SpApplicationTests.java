package it.aruba.sp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.aruba.sp.controller.PraticaController;
import it.aruba.sp.controller.VersioneController;
import it.aruba.sp.dto.PraticaDto;

@SpringBootTest
class SpApplicationTests {
	
	private VersioneController versioneController;
	private PraticaController praticaController;
	
	@Autowired
	public SpApplicationTests(
			VersioneController versioneController, 
			PraticaController praticaController) {
		super();
		this.versioneController = versioneController;
		this.praticaController = praticaController;

	}

	@Test
	void contextLoads() {
		assertThat(versioneController).isNotNull();
		assertThat(praticaController).isNotNull();
	}
	
//	@Test
//	public void getProductsList() throws Exception {
//	   String uri = "/pratica/PRIMA";
//	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//	   
//	   int status = mvcResult.getResponse().getStatus();
//	   assertThat(status).isEqualTo(200);
//	   String content = mvcResult.getResponse().getContentAsString();
//	   PraticaDto productlist = mapFromJson(content, PraticaDto.class);
//	   assertThat(productlist).isNotNull();
//	}
//	
//	protected <T> T mapFromJson(String json, Class<T> clazz)
//			throws JsonParseException, JsonMappingException, IOException {
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		return objectMapper.readValue(json, clazz);
//	}

}
