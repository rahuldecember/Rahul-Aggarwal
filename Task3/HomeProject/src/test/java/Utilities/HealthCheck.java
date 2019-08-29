package Utilities;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Resources.Resources;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class HealthCheck {
	
	Properties properties = new Properties();
	
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
	
	
  @Test
  public void HealthCheckTest() {
	  given().
	  
	  when().get(Resources.getUtilityHCResource()).
	  
	  then().assertThat().statusCode(HttpStatus.SC_OK).and().
	  body("$", hasKey("uptime")).
	  body("$", hasKey("readonly")).
	  body("documents", hasKey("products")).
	  body("documents", hasKey("stores")).
	  body("documents", hasKey("categories"));	 

  }
  
  @Test
  public void HealthCheckErrorTest() {
	  given().
	  
	  when().get(Resources.getUtilityHCResource()+"/.").
	  
	  then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);	 

  }
}
