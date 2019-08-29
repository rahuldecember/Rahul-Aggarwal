package Utilities;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Resources.Resources;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Version {
	
	Properties properties = new Properties();
	
	static final String VERSION = "1.1.0";
	
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
  public void VersionTest() {
	  given().
	  
	  when().get(Resources.getUtilityVersionResource()).
	  
	  then().assertThat().statusCode(HttpStatus.SC_OK).and().
	  body("version", equalTo(properties.getProperty("VERSION"))).and().
	  header("X-Powered-By", "Express");

  }
}
