package Stores;

import org.testng.annotations.Test;

import Resources.Resources;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;


@Test(groups= "Stores")
public class GetAllStore {

	Properties properties = new Properties();

	@Test()
	public void GetAllProduct1() {

		given().contentType(Resources.getContentType()).
				when().log().all().get(Resources.getStoresResource()).
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
