package stepDefinitions;

import static io.restassured.RestAssured.given;

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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class stepDefinition {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	
	@Given("Add Place Payload")
	public void add_Place_Payload() {
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName("Princess Castle 3");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("Spiritual Landing place");
		p.setWebsite("https://www.google.com");
		p.setLanguage("French-IN");
		
		
	// types is a list so we have to create a list object first
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l= new Location();
		l.setLng(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		
		// set is used for setting parameters
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(req)
		.body(p);
		
	}
	
	@When("user calls {string} with Post http request")
	public void user_calls_with_Post_http_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		response = res.when().post("/maps/api/place/add/json")
				.then().spec(resspec).extract().response();
	}


	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals(response.getStatusCode(),200);
		
	   
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println(string2);
	}

	
	
}
