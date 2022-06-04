package SDET_QA_AUTO_TECHIE;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

/*given()
set cookies, add auth, add param, set headers info etc
when()
get,post,put,delete….
then()
validate status code, extra response, extra headers cookies & response body….
*/

public class Demo1_GET_Request {
	
	@Test
	public void get_RequestEx1() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.assertThat().body("page",equalTo(2))
			.header("Content-Type","application/json; charset=utf-8");
	}

}
