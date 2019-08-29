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
import io.restassured.response.Response;
import payLoad.payloads;

@Test(groups= "Services")
public class AddService {

	Properties properties = new Properties();

	@Test()
	public void AddServices1() {

		Response id = given().contentType(Resources.getContentType()).
				body(payloads.getAddServicePayload()).
				when().log().all().post(Resources.getServicesResource()).
				then().assertThat().statusCode(HttpStatus.SC_CREATED).and().
				extract().response();
		System.out.println(id.asString());
	}

	@Test(enabled = false)
	public void AddServices2() {

		int id = given().contentType(Resources.getContentType()).
				body(payloads.getAddServicePayload()).
				when().post(Resources.getServicesResource()).
				then().assertThat().statusCode(HttpStatus.SC_CREATED).and().
				extract().path("id");
		System.out.println(id);
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
		
		FileInputStream input = new FileInputStream(System.getProperty("user.dir")+
				Resources.getPropertiesPath());
		properties.load(input);
		
		RestAssured.baseURI = properties.getProperty("HOST");
		System.out.println("Before Test - " + RestAssured.baseURI);
		System.out.println(System.getProperty("user.dir"));


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
