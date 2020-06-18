package resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws FileNotFoundException
	{
	RestAssured.baseURI = "https://rahulshettyacademy.com";
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));	
		// set is used for setting parameters
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
				.setContentType(ContentType.JSON).build();
		
		return req;
	}
	
	
}
