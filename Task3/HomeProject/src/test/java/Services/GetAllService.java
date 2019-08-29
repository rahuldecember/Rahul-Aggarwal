package Services;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Resources;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Test(groups= "Services")
public class GetAllService {

	Properties properties = new Properties();
	static int limit = 23;
	static int skip = 0;

	@Test()
	public void GetAllServiceWithQueryParameters() {

		Response res = given().
						contentType(Resources.getContentType()).
						queryParam("$limit", limit).
						queryParam("$skip", skip).
				
						when().get(Resources.getServicesResource()).
				
						then().assertThat().statusCode(HttpStatus.SC_OK).
						
						extract().response();
		
		System.out.println(res.asString());
		
		JsonPath parser = new JsonPath(res.asString());
		System.out.println(parser);
		
		System.out.println(parser.get("limit"));
		
		//assertEquals(23, parser.get("total"));
		assertEquals(limit, parser.get("limit"));
		assertEquals(skip, parser.get("skip"));
		
		assertEquals(skip+1, parser.get("data.id[0]"));
		System.out.println(parser.get("data.id.size()"));
		assertEquals(limit, parser.get("data.id.size()"));
		
		
	}
	
	@Test()
	public void GetAllServiceWithOutQueryParameters() {

		Response res = given().
						contentType(Resources.getContentType()).
				
						when().log().all().get(Resources.getServicesResource()).
				
						then().assertThat().statusCode(HttpStatus.SC_OK).
						
						extract().response();
		
		System.out.println(res.asString());
		
		JsonPath parser = new JsonPath(res.asString());
		System.out.println(parser);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class");
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		System.out.println("Before Test");

	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}

	@BeforeSuite
	public void beforeSuite() throws IOException {
		System.out.println("Before Suite");
		FileInputStream input = new FileInputStream(System.getProperty("user.dir")+
				Resources.getPropertiesPath());
		properties.load(input);
		
		RestAssured.baseURI = properties.getProperty("HOST");
		System.out.println(RestAssured.baseURI);
		System.out.println(System.getProperty("user.dir"));
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}


}
