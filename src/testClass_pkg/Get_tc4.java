package testClass_pkg;

import static io.restassured.RestAssured.given;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class Get_tc4 {

	public static void main(String[] args) {
		//step1: configure BaseURI 
		String BaseURI="https://reqres.in/";
		RestAssured.baseURI=BaseURI;
				
		//Step2: Fetch status code and response body
		int statusCode = given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract().statusCode();
		System.out.println(statusCode);
				
		String res_body = given().header("Content-Type","application/json").when().get("api/users?page=2").then().extract().response().asString();
			 
		System.out.println(res_body);
		
		//Step3: set expected results
		int id[] = {7, 8, 9, 10, 11, 12};
			String[] email= {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", 
					           "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
			String[] first_name= {"Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"};
			String[] last_name= {"Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"};
			
			//Step4: Validate Response body
			
			//step4.1: parse the response body
				
			JsonPath res_jsp= new JsonPath (res_body);
			int count=res_jsp.getList("data").size();
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				//Step4.2: Fetch expected result
				int exp_id=id[i];
				String exp_email=email[i];
				String exp_first_name=first_name[i];
				String exp_last_name=last_name[i];
					
			   //step4.3 fetch response body parameters
				String res_id=res_jsp.getString("data["+i+"].id");
				int res_int_id=Integer.parseInt(res_id);
				System.out.println(res_int_id);
				String res_email=res_jsp.getString("data["+i+"].email");
				System.out.println(res_email);
				String res_first_name=res_jsp.getString("data["+i+"].first_name");
				System.out.println(res_first_name);
				String res_last_name=res_jsp.getString("data["+i+"].last_name");
				System.out.println(res_last_name);
					
				//Step4.4 Validate response body
					
				Assert.assertEquals(exp_id,res_int_id,"ID at index " +i);
				Assert.assertEquals(exp_email,res_email,"email at index " +i);
				Assert.assertEquals(exp_first_name,res_first_name,"first_name at index " +i);
				Assert.assertEquals(exp_last_name,res_last_name,"last_name at index " +i);
					
		
	         }

          }
     }
