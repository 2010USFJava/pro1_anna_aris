/*Unit test made as part of tutorial from
 * https://www.youtube.com/watch?v=Hv_a3ZBSO_g
 * 12/10/2020
 * 
 */

package com.revature.meta;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.revature.pojo.AuthorPOJO;
import com.revature.pojo.BookPOJO;
import com.revature.pojo.DayPOJO;
import com.revature.pojo.SimpleTestCaseJsonPOJO;
import com.revature.util.JsonHelper;
import com.revature.util.LogThis;
import com.revature.util.LogThis.LevelEnum;

public class jsonParserTest {

	private String simpleTestCase= "{\r\n"
			+ "	\"title\": \"Coder From Scratch\",\r\n"
			+ "	\"author\": \"Rui\"\r\n"
			+ "}";
	private String dayScenario1 ="{\r\n"
			+ "	\"date\": \"2019-12-15\",\r\n"
			+ "	\"name\":\"Christmas Day\"\r\n"
			+ "}";
	private String authorBookScenario1="{\r\n"
			+ "	\"authorName\": \"Rui\",\r\n"
			+ "	\"books\": [\r\n"
			+ "		{\r\n"
			+ "			\"title\": \"title1\",\r\n"
			+ "			\"inPrint\": true,\r\n"
			+ "			\"publishDate\": \"2019-12-25\"\r\n"
			+ "		},\r\n"
			+ "		{\r\n"
			+ "			\"title\": \"title2\",\r\n"
			+ "			\"inPrint\": false,\r\n"
			+ "			\"publishDate\": \"2019-01-01\"\r\n"
			+ "		}\r\n"
			+ "	]\r\n"
			+ "}";
	
	
	@Test
	public void parseSimple() throws IOException {
		JsonNode node = JsonHelper.parse(simpleTestCase);
		assertEquals(node.get("title").asText(),"Coder From Scratch");
	}

	@Test
	public void fromJson() throws IOException {
		JsonNode node = JsonHelper.parse(simpleTestCase);
		SimpleTestCaseJsonPOJO pojo=JsonHelper.fromJson(node, SimpleTestCaseJsonPOJO.class);
		String output="Pojo Title: "+pojo.getTitle();
		LogThis.logIt(LevelEnum.DEBUG, output);

		assertEquals(pojo.getTitle(),"Coder From Scratch");
	}
	
	@Test
	public void toJson() {
		SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
		pojo.setTitle("Testing 123");
		
		JsonNode node = JsonHelper.toJson(pojo);
		
		assertEquals(node.get("title").asText(),"Testing 123");

	}
	
	@Test
	public void stringify() throws JsonProcessingException {
		SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
		pojo.setTitle("Testing 123");
		
		JsonNode node = JsonHelper.toJson(pojo);
		
		String output_normal = JsonHelper.stringify(node);
		LogThis.logIt(LevelEnum.DEBUG, output_normal);
		 String output_pretty = JsonHelper.pettyPrint(node);
			LogThis.logIt(LevelEnum.DEBUG, output_pretty);
		

			assertEquals("{\"title\":\"Testing 123\"}",output_normal);
	}
	
	@Test
	public void dayTestScenario1() throws IOException{
		JsonNode node = JsonHelper.parse(dayScenario1);
		DayPOJO pojo = JsonHelper.fromJson(node, DayPOJO.class);
//		LogThis.logIt(LevelEnum.DEBUG,"Date:"+ pojo.getDate());
		
		assertEquals("2019-12-15",pojo.getDate().toString());
		
	}
	
	@Test
	public void authorBookScenario1() throws IOException{
		JsonNode node = JsonHelper.parse(authorBookScenario1);
		AuthorPOJO pojo = JsonHelper.fromJson(node, AuthorPOJO.class);
		LogThis.logIt(LevelEnum.DEBUG,"Author Scenario 1:"+ pojo.getAuthorName());
		
		for(BookPOJO bp : pojo.getBooks()) {
			LogThis.logIt(LevelEnum.DEBUG, "Book: "+bp.getTitle());
			LogThis.logIt(LevelEnum.DEBUG, "In Print: "+bp.isInPrint());
			LogThis.logIt(LevelEnum.DEBUG, "Date: "+bp.getPublishDate());
		}

		
	}

}
