package Stores;

import Resources.Resources;
import payLoad.payloads;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class StoreE2E {

	Properties properties = new Properties();
	
	int serviceId =0;

	@Test()
	public void GetAllStore() {

		given().contentType(Resources.getContentType()).
				when().log().all().get(Resources.getStoresResource()).
				then().assertThat().statusCode(HttpStatus.SC_OK);
	}
	
	@Test(dependsOnMethods ="GetAllStore")
	public void AddStore() {

		serviceId = given().contentType(Resources.getContentType()).
				body(payloads.getAddStorePayload()).
				when().log().all().post(Resources.getStoresResource()).
				then().assertThat().statusCode(HttpStatus.SC_CREATED).and().
				extract().path("id");
		System.out.println(serviceId);
	}

	@Test(dependsOnMethods = "AddStore")
	  public void GetStoreById() {
		System.out.println(serviceId);
		
		given().
		
		when().log().all().get(Resources.getStoresResource()+ "/" + serviceId).
		
		then().assertThat()
		.statusCode(HttpStatus.SC_OK).and()
		.contentType(ContentType.JSON).and()
		.body("id", equalTo(serviceId));
    		  
	}
	
	@Test(dependsOnMethods = "GetStoreById")
	public void DeleteStore() {
		System.out.println(serviceId);
		
		given().pathParam("id",serviceId).
				when().delete(Resources.getStoresResource()+"/{id}").
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


}
