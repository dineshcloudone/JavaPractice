package SDET_QA_AUTO_TECHIE;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

/*
 * 1. Test Status Code
 * 2. Log Response
 * 3. verifying single content in response body
 * 4. verifying multiple contents in response body
 * 5. setting parameters & headers
 * 
 * */

public class Demo5_BasicValidations_JsonResponse {

	// 1. status code
	// 2. Log Response

	@Test(priority = 1)
	public void testStatuCode() {

		when().get("http://jsonplaceholder.typicode.com/posts/3").then().statusCode(200).log().all();

		/*
		 * when() .get("http://jsonplaceholder.typicode.com/posts/3") .then()
		 * .statusCode(200);
		 */
	}

	// 3. Verifying single content in respone body
	// 4. Verifying multiple contents in response body
	@Test(priority=2)
	public void testSingleContent() {
		given()
		.when()
			.get("http://jsonplaceholder.typicode.com/posts/3")
		.then()
			.statusCode(200)
			.body("x.data[1].first_name", equalTo("Lindsay"));
			//.body("RestResonse.data[1].first_name", hasItems("Michael","Lindsay","Tobias");
	}
	
	//5. setting parameters & headers
	@Test(priority=3)
	public void testParamsAndHeaders() {
		given()
			.param("MyName","abc")
			.headers("MyHeader", "Indian")
		.when()
			.get("http://jsonplaceholder.typicode.com/posts/3")
		.then()
			.statusCode(200)
			.body("x.data[1].first_name", equalTo("Lindsay"));
			//.body("RestResonse.data[1].first_name", hasItems("Michael","Lindsay","Tobias");
	}
}
