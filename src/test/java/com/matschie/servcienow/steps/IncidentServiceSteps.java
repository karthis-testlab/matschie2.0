package com.matschie.servcienow.steps;

import static com.matschie.general.utils.PropertiesHandler.*;
import com.matschie.pojo.serialization.CreateIncident;
import com.matschie.servicenow.services.IncidentService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

public class IncidentServiceSteps {

	private IncidentService service = new IncidentService();

	@Given("Set a base URI and the base path")
	public void set_a_base_uri_and_the_base_path() {
		baseURI = config("service.now.instance.uri");
		basePath = config("service.now.instance.basePath");
	}

	@Given("Set authentication for the create opration")
	public void set_authentication_for_the_create_opration() {
		authentication = basic(config("service.now.instance.username"), secret("service.now.instance.password"));
	}

	@When("user hit the post method with the request payload")
	public void user_hit_the_post_method_with_the_request_payload() {
		
		CreateIncident payload = new CreateIncident();
		payload.setDescription("Create the record via cucumber");
		payload.setShort_description("cucumber");

		service.create(payload);

	}

	@When("^user hit the post method with the following request (.*), (.*), (.*) payload data$")
	public void user_hit_the_post_method_with_the_following_data_provider(String shotDescription, String description, String callerId) {
		
		CreateIncident payload = new CreateIncident();
		payload.setDescription(description);
		payload.setShort_description(shotDescription);
		payload.setCaller_id(callerId);

		service.createWithCallerId(payload);
	}

	@Then("^new incident record should have the following (.*), (.*), (.*) data got created$")
	public void new_incident_record_should_have_the_following_data_got_created(String shotDescription, String description, String callerId) {
		service.validateStatusCode(201)
		       .validateStatusLine("Created")
		       .validateContentType("application/json")
		       .validateDescriptionValue(description)
		       .validateShortDescriptionValue(shotDescription)
		       .validateCallerIdValue(callerId);
	}

	@Then("new incident record should be created")
	public void new_incident_record_should_be_created() {
		service.validateStatusCode(201)
		       .validateStatusLine("Created")
		       .validateContentType("application/json")
			   .validateDescriptionValue("Create the record via cucumber")
			   .validateShortDescriptionValue("cucumber")
			   .validateCallerIdIsEmpty();
	}

}