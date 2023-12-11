package it.aruba.sp.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
	
	public static String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = JsonMapper.builder()
			    .addModule(new JavaTimeModule())
			    .build();
		return objectMapper.writeValueAsString(obj);
	}
	
	public static <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = JsonMapper.builder()
			    .addModule(new JavaTimeModule())
			    .build();
		return objectMapper.readValue(json, clazz);
	}

}
