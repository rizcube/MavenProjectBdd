package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition {

	@Given("Add Place Payload")
	public void add_Place_Payload() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("addPlacepayload");
	}
	
	@When("user calls {string} with Post http request")
	public void user_calls_with_Post_http_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println(string);
	}


	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println(int1);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println(string2);
	}

	
	
}
