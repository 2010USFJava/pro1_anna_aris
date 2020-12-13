/* Made from following tutorial by CoderFromScratch@
 * https://www.youtube.com/watch?v=Hv_a3ZBSO_g
 * 
 */


package com.revature.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonHelper {
	
private static  ObjectMapper objectMapper = getDefaultObjectMapper();
	
	private static ObjectMapper getDefaultObjectMapper() {
		ObjectMapper defaultObjectMapper = new ObjectMapper();
		defaultObjectMapper.registerModule(new JavaTimeModule());
		defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return defaultObjectMapper;
	}
	
	public static JsonNode parse(String src) throws IOException {
		return objectMapper.readTree(src);
	}
	
	public static <A> A fromJson(JsonNode node, Class<A> myClass) throws JsonProcessingException {
		return objectMapper.treeToValue(node, myClass);
		
	}
	
	public static JsonNode toJson(Object a) {
		return objectMapper.valueToTree(a);
	}
	
	public static String stringify(JsonNode node) throws JsonProcessingException {
		return generateString(node,false);
	}
	
	public static String pettyPrint(JsonNode node) throws JsonProcessingException {
		return generateString(node,true);
		 
	}
	
	private static String generateString(JsonNode node, boolean pretty)throws JsonProcessingException {
		ObjectWriter objectWriter = objectMapper.writer();
		if(pretty) {
			objectWriter=objectWriter.with(SerializationFeature.INDENT_OUTPUT);
		}
		return objectWriter.writeValueAsString(node);
		 
	}

}
