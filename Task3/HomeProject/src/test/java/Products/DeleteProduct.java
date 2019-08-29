package Products;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Resources.Resources;
import io.restassured.RestAssured;
import payLoad.payloads;

@Test(groups= "Products")
public class DeleteProduct {

	Properties properties = new Properties();

	@Test()
	public void DeleteProduct1() {

		given().pathParam("id","99996").
				when().log().all().delete(Resources.getProductResource()+"/{id}").
				then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
	}

	@Test()
	public void DeleteProduct2() {
		
		//Add Product
	int productId =	
		given().contentType(Resources.getContentType()).
		body(payloads.getAddProductPayload()).
		when().log().all().post(Resources.getProductResource()).
		then().assertThat().statusCode(HttpStatus.SC_CREATED).
		extract().path("id");
		
		// Delete Product Created in last request
		given().pathParam("id",productId).
				when().log().all().delete(Resources.getProductResource()+"/{id}").
				then().assertThat().statusCode(HttpStatus.SC_OK);
	}

	@BeforeSuite
	public void beforeSuite() throws IOException {
		System.out.println("Before Suite");
		FileInputStream input = new FileInputStream(System.getProperty("user.dir")+
				Resources.getPropertiesPath());
		properties.load(input);
		
		RestAssured.baseURI = properties.getProperty("HOST");
		System.out.println("Before Test - " + RestAssured.baseURI);
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}

}
