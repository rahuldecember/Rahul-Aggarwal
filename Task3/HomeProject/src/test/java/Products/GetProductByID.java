package Products;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@Test(groups= "Products")
public class GetProductByID {

	
	@Test()
	  public void GetProductById1() {
		
		given().
		
		when().log().all().get("http://localhost:3030/products/9999713").
		
		then().assertThat()
		.statusCode(HttpStatus.SC_OK).and()
		.contentType(ContentType.JSON).and()
		.body("id", equalTo(9999713)).and()
		.header("X-Powered-By", "Express");		  
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
  public void beforeTest() {
	  RestAssured.baseURI = "http://localhost";
	  RestAssured.port = 3030;
	  System.out.println("Before Test - " + RestAssured.baseURI + " "+ RestAssured.port);
  }

  

}
