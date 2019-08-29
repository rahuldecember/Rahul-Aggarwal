package Categories;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import payLoad.payloads;

@Test(groups= "Category")
public class GetCategoryByID {
	
	int categoryId_random = (int) (Math.random() * 999999 + 100000);
	
	@Test
	  public void GetCategoryByID1() {
		String categoryId=
				given().contentType(Resources.getContentType()).
				body(payloads.getAddCategoryPayload(categoryId_random)).
				when().log().all().post(Resources.getCategoriesResource()).
				then().assertThat().statusCode(HttpStatus.SC_CREATED).
				extract().path("id");
		
		
		given().
		
		when().log().all().get(Resources.getCategoriesResource() +"/" +categoryId).
		
		then().assertThat()
		.statusCode(HttpStatus.SC_OK).and()
		.contentType(ContentType.JSON).and()
		.body("id", equalTo(categoryId));
		
		given().pathParam("id",categoryId).
		when().log().all().delete(Resources.getCategoriesResource()+"/{id}").
		then().assertThat().statusCode(HttpStatus.SC_OK);
      		  
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

}
