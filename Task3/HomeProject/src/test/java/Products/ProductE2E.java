package Products;

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

public class ProductE2E {

	Properties properties = new Properties();
	
	int productId =0;

	@Test()
	public void GetAllProduct() {

		given().contentType(Resources.getContentType()).
				when().get(Resources.getProductResource()).
				then().assertThat().statusCode(HttpStatus.SC_OK);
	}
	
	@Test(dependsOnMethods ="GetAllProduct")
	public void AddProduct() {

		productId = given().contentType(Resources.getContentType()).
				body(payloads.getAddProductPayload()).
				when().log().all().post(Resources.getProductResource()).
				then().assertThat().statusCode(HttpStatus.SC_CREATED).and().
				extract().path("id");
		System.out.println(productId);
	}

	@Test(dependsOnMethods = "AddProduct")
	  public void GetProductById() {
		System.out.println(productId);
		
		given().
		
		when().log().all().get(Resources.getProductResource()+ "/" + productId).
		
		then().assertThat()
		.statusCode(HttpStatus.SC_OK).and()
		.contentType(ContentType.JSON).and()
		.body("id", equalTo(productId));
    		  
	}
	
	@Test(dependsOnMethods = "GetProductById")
	public void DeleteProduct() {
		System.out.println(productId);
		
		given().pathParam("id",productId).
				when().log().all().delete(Resources.getProductResource()+"/{id}").
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
