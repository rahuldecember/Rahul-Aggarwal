package Categories;

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

@Test(groups= "Category")
public class AddCategory {

	Properties properties = new Properties();
	
	int categoryId_random = (int) (Math.random() * 999999 + 100000);

	@Test()
	public void AddCategoryValidPayload() {

		given().contentType(Resources.getContentType()).
				body(payloads.getAddCategoryPayload(categoryId_random)).
				when().log().all().post(Resources.getCategoriesResource()).
				then().assertThat().statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test()
	public void AddCategoryInValidpayload() {

		given().contentType(Resources.getContentType()).
				body(payloads.getAddCategoryPayloadInvalid()).
				when().log().all().post(Resources.getCategoriesResource()).
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
