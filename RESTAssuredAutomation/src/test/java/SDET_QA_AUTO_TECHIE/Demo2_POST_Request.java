package SDET_QA_AUTO_TECHIE;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

public class Demo2_POST_Request {
	
	public static HashMap map=new HashMap();

	@BeforeClass
	public void postdata() {
		map.put("name", RestUtils.getName());
		map.put("job",RestUtils.getJob());
		
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="/api/users";
	}
	
	@Test
	public void testPost() {
		
		given()
			.contentType("application/json") // for both POST and PUT requests we should mention the content type
			.body(map)
		.when()
			.post()
		.then()
			.statusCode(201);
			
			//.and()
			//.body("SuccessCode",equalTo("OPERATION_SUCCESS"))
			//.and()
			//.body("Message",equalTo("Operation completed successfully"));
			
	}
}
