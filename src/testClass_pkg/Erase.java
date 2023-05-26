package testClass_pkg;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class Delete_tc5 {

	public static void main(String[] args) {

		//step 1:Declare Base url
				String BaseURI="https://reqres.in/";
				
				RestAssured.baseURI=BaseURI;
					
			//step 2: configure request body
			    int statusCode=given().header("Content-Type","application/json").when().delete("/api/users/2").then().extract().statusCode();
		        System.out.println(statusCode);
		        Assert.assertEquals(statusCode, 204);

	}

}
