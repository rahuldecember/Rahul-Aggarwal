package Stores;

import org.testng.annotations.Test;

import Resources.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

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
public class GetStoreByID {	
		
	@Test
	  public void GetStoreByID1() {
		
		given().
		
		when().log().all().get(Resources.getStoresResource() +"/8926").
		
		then().assertThat()
		.statusCode(HttpStatus.SC_OK).and()
		.contentType(ContentType.JSON).and()
		.body("id", equalTo(8926));
      		  
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
	  RestAssured.baseURI = "http://localhost";
	  RestAssured.port = 3030;
	  System.out.println("Before Test - " + RestAssured.baseURI + " "+ RestAssured.port);
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After Test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Before Suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("After Suite");
  }

}
