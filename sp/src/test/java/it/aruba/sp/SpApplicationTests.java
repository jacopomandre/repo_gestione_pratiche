package it.aruba.sp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.aruba.sp.controller.PraticaController;

@SpringBootTest
class SpApplicationTests {
	
	private PraticaController praticaController;
	
	@Autowired
	public SpApplicationTests(
			PraticaController praticaController) {
		super();
		this.praticaController = praticaController;

	}

	@Test
	void contextLoads() {
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
