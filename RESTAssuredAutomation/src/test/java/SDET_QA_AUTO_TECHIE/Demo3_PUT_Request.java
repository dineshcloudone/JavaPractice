package SDET_QA_AUTO_TECHIE;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.*;

public class Demo3_PUT_Request {

	public static HashMap map = new HashMap();
	String name = RestUtils.getName();
	String job = RestUtils.getJob();

	@BeforeClass
	public void putdata() {
		
		map.put("name", name);
		map.put("job",job);
		
		
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="/api/users/2";
	}
	
	@Test
	public void testPut() {
		
		given()
			.contentType("application/json") //// for both POST and PUT requests we should mention the content type
			.body(map)
		.when()
			.put()
		.then()
			.statusCode(200)
			.log()
			.all();
		
			//.and()
			//.body("SuccessCode",equalTo("OPERATION_SUCCESS"));
			
	}

}
