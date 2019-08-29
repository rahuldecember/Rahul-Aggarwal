package Services;

import static io.restassured.RestAssured.given;

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
import payLoad.payloads;

@Test(groups= "Services")
public class DeleteService {

	Properties properties = new Properties();

	@Test()
	public void DeleteService1() {

		given().pathParam("id","99996").
				when().delete(Resources.getServicesResource()+"/{id}").
				then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
	}

	@Test()
	public void DeleteService2() {

		int id = given().contentType(Resources.getContentType()).
				body(payloads.getAddServicePayload()).
				when().log().all().post(Resources.getServicesResource()).
				then().assertThat().statusCode(HttpStatus.SC_CREATED).and().
				extract().path("id");
		
		
		given().pathParam("id",id).
				when().delete(Resources.getServicesResource()+"/{id}").
				then().assertThat().statusCode(HttpStatus.SC_OK);
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
	public void beforeTest() {
		
		System.out.println("Before Test - ");
		
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
		System.out.println("Before Test - " + RestAssured.baseURI);
		System.out.println(System.getProperty("user.dir"));
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}

}
