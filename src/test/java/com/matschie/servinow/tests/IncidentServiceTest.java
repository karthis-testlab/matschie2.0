package com.matschie.servinow.tests;

import org.testng.annotations.Test;

import com.matschie.general.utils.PropertiesHandler;
import com.matschie.pojo.serialization.CreateIncident;
import com.matschie.servicenow.services.IncidentService;
import com.matschie.testng.api.TestNGHooks;

public class IncidentServiceTest extends TestNGHooks {
	
	String sys_id;
	
	@Test(priority = 0)
	public  void userShouldAbleToCreateIncidentAndFetchSysId() {
		
		CreateIncident payload = new CreateIncident();
		payload.setDescription("Added the Description here");
		payload.setShort_description("RAS");
		
		sys_id = new IncidentService()
		    .create(payload)
		    .validateStatusCode(201)
		    .validateStatusLine("Created")
		    .validateContentType("application/json")
		    .extractValue("result.sys_id");
		
	}
	
	@Test(priority = 0)
	public  void userShouldAbleToCreateIncidentWithoutCallerId() {
		
		CreateIncident payload = new CreateIncident();
		payload.setDescription("Added the Description here");
		payload.setShort_description("RAS");
		
		new IncidentService()
		    .create(payload)
		    .validateStatusCode(201)
		    .validateStatusLine("Created")
		    .validateContentType("application/json")
		    .validateShortDescriptionValue("RAS")
		    .validateDescriptionValue("Added the Description here")
		    .validateCallerIdIsEmpty();
		
	}
	
	@Test(priority = 0)
	public  void userShouldAbleToCreateIncidentCallerId() {
		
		CreateIncident payload = new CreateIncident();
		payload.setDescription("Added the Description here");
		payload.setShort_description("RAS");
		payload.setCaller_id("413a4d35eb32010045e1a5115206fe6b");
		
		new IncidentService()
		    .createWithCallerId(payload)
		    .validateStatusCode(201)
		    .validateStatusLine("Created")
		    .validateContentType("application/json")
		    .validateShortDescriptionValue("RAS")
		    .validateDescriptionValue("Added the Description here")
		    .validateCallerIdValue("413a4d35eb32010045e1a5115206fe6b");
		
	}
	
	@Test(priority = 1)
	public void userShouldAbleToGetASingleIncident() {
		new IncidentService()
		.get(sys_id)
		.validateStatusCode(200)
		.validateStatusLine("OK")
		.validateContentType("application/json");
	}
	
	@Test
	public void userShouldAbleToGetASingleIncidentWithQueryParam() {
		
		
		System.out.println(new IncidentService().get("7adeb7ba83ec9210695bc7b6feaad30b",
				PropertiesHandler.queryParamMap("incident-service", "sysparm_fields")).extractResponse());
		
		
	}
	
	@Test
	public void userShouldAbleToGetAllIncidentsWithQueryParamForSoftware() {
		System.out.println(new IncidentService()
				.get(PropertiesHandler.queryParamsMap("incident-service-software", new String[] {"sysparm_query", "sysparm_limit"}))
				.extractResponse());
	}
	
	@Test
	public void userShouldAbleToGetAllIncidentsWithQueryParamForHardware() {
		System.out.println(new IncidentService()
				.get(PropertiesHandler.queryParamsMap("incident-service", new String[] {"sysparm_query", "sysparm_limit"}))
				.extractResponse());
	}
	
	@Test
	public void userShouldAbleToGetAllIncidentForHardwareCategory() {
		new IncidentService()
		    .get(PropertiesHandler.queryParamMap("incident-service", "sysparm_query"))
		    .validateStatusCode(200)
		    .validateStatusLine("OK")
		    .validateContentType("application/json")
		    .validateIncidentCategory("result.findAll{it.category == 'hardware'}.category", "hardware")
		    .validateIncidentCategoryCount("result.findAll{it.category == 'hardware'}.category", 10);
	}
	

}