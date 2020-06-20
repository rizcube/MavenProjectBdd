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
	static String place_id;

	
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
		
		System.out.println("resource is >"+resource +  " Method is > " + method);
		
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
	    
	    assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	    
	 }

	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // prepare requestSpec (endpoint/baseurl/queryparameter/key etc)
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_Post_http_request(resource,"GET");
	    System.out.println(resource + place_id);
	    String actualName = getJsonPath(response, "name");
	    System.out.println(actualName);
	    assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(data.deletePlacePayload("coming fro place Payload class" + place_id));
	    res = given().spec(requestSpecification().body(data.deletePlacePayload(place_id)));
	}

	
}
