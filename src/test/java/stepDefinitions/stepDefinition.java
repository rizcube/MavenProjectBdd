package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	String place_id;
	JsonPath js;
	/*
	@Given("Add Place Payload")
	public void add_Place_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions	
		//resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
		
	}*/
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name, language, address));
	}

	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		System.out.println("URL is not printing");
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
			//.then().spec(resspec).extract().response();
	}


	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals(response.getStatusCode(),200);
		
	   
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	    String resp = response.asString();
	    js = new JsonPath(resp);
	   
	    assertEquals(js.get(keyValue).toString(),Expectedvalue);
	    System.out.println(resp);
	 }


	
	
	
}
