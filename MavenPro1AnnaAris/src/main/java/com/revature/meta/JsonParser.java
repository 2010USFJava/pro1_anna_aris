/* Made from following tutorial by CoderFromScratch@
 * https://www.youtube.com/watch?v=Hv_a3ZBSO_g
 * 
 * Minor Modifications: Anna Carlson
 */


package com.revature.meta;

import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonParser {

	private static  ObjectMapper objectMapper = getDefaultObjectMapper();
	
	private static ObjectMapper getDefaultObjectMapper() {
		ObjectMapper defaultObjectMapper = new ObjectMapper();
		return defaultObjectMapper;
	}
	
}
