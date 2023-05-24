package testClass_pkg;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.xml.*;

public class Soap_tc6 {

	public static void main(String[] args) {
		// step1; declare base URI and request body variables
				String BaseURI="https://www.dataaccess.com";
				String req_body="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
								+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
								+ "  <soap:Body>\r\n"
								+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
								+ "      <ubiNum>325</ubiNum>\r\n"
								+ "    </NumberToWords>\r\n"
								+ "  </soap:Body>\r\n"
								+ "</soap:Envelope>\r\n"
								+ "";
				
				//step2: Fetch request parameters
				XmlPath xml_req= new XmlPath(req_body);
				String req_input= xml_req.getString("ubiNum");
				System.out.println(req_input);
								
				//step3: configure the API and fetch response body
				RestAssured.baseURI=BaseURI;
				String responseBody=given().header("Content-Type","text/xml; charset=utf-8").body(req_body).when().post("/webservicesserver/NumberConversion.wso")
				.then().extract().response().getBody().asString();
				System.out.println(responseBody);
				
				//step 4: Parse the response body and fetch the response parameters
				XmlPath xml_res= new XmlPath(responseBody);
				String result= xml_res.getString("NumberToWordsResult");
				System.out.println(result);
			
				//step5: Validate response body parameters
				Assert.assertEquals(result,"three hundred and twenty five ");

	}

}
