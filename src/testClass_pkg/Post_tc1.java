package testClass_pkg;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;

public class Post_tc1 {

	public static void main(String[] args) {
		// Declare base URI and request body variables
		String BaseURI="https://reqres.in/";
		String req_body="{\r\n"
				+ "    \"name\": \"Riya\",\r\n"
				+ "    \"job\": \"manager\"\r\n"
				+ "}";
		// Configure baseURI
		RestAssured.baseURI=BaseURI;
				
		//Parse the request body and its parameters
		JsonPath jspreq=new JsonPath(req_body);
		String req_name=jspreq.getString("name");
		String req_job=jspreq.getString("job");
		
		//Fetch response status code and response body
	
		int statusCode=given().header("Content-type","application/json").body(req_body).when().post("api/users").then().extract().response().statusCode();
		System.out.println(statusCode);
		
		String res_body=given().header("Content-type","application/json").body(req_body).when().post("api/users").then().extract().response().asString();
		System.out.println(res_body);
		
		//Parse response body and its parameters
		JsonPath jspres=new JsonPath(res_body);
		String res_name=jspres.getString("name");
		String res_job=jspres.getString("job");
		String res_id=jspres.getString("id");
		String res_createdAt=jspres.getString("createdAt");
		
		String trim_date=res_createdAt.substring(0,10);
		
		//generate date
	   LocalDateTime date=LocalDateTime.now();
	   String exp_date=date.toString().substring(0,10);
	
    	//Validate the response
	   
	   Assert.assertEquals(statusCode,201);
	   Assert.assertEquals(req_name, res_name);
	   Assert.assertEquals(req_job, res_job);
	   Assert.assertNotNull(res_id);
	   Assert.assertEquals(trim_date, exp_date);
	   
	
	}

}
