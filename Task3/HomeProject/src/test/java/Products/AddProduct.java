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
public class AddProduct {

	Properties properties = new Properties();

	@Test()
	public void AddProductWithActualPayLoad() {

		given().contentType(Resources.getContentType()).
				body(payloads.getAddProductPayload()).
				when().log().all().post(Resources.getProductResource()).
				then().assertThat().statusCode(HttpStatus.SC_CREATED);
	}

	
	@Test()
	public void AddProductWithMissingMandatoryKeyInJson() {

		given().contentType(Resources.getContentType()).
				body(payloads.getAddProductPayload_Missing()).
				when().log().all().post(Resources.getProductResource()).
				then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
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
