package SDET_QA_AUTO_TECHIE;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Demo4_DELTE_Request {
	
	
	String empid="2";
	
	@Test
	public void deleteRequest() {
		
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="/api/users"+empid;
		
		Response response= 		given()
								.when()
									.delete()
								.then()
									.statusCode(200)
									.statusLine("HTTP/1.1 200 OK")
									.log()
									.all()
									.extract().response();
		
		String jsonString=response.asString();
		//Assert.assertEquals(jsonString.contains("successfully! deleted records"), true);
		Assert.assertEquals(jsonString.contains("204"), true);
		
	}	

}
