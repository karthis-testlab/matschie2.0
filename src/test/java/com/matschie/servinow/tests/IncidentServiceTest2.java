package com.matschie.servinow.tests;

import org.testng.annotations.Test;

import com.matschie.general.utils.PropertiesHandler;
import com.matschie.servicenow.services.IncidentService;
import com.matschie.testng.api.TestNGHooks;

public class IncidentServiceTest2 extends TestNGHooks {	
	
	@Test
	public void userShouldAbleToGetAllIncidentForHardwareCategory() {
		new IncidentService()
		    .get(PropertiesHandler.queryParamMap("incident-service", "sysparm_query"))
		    .validateStatusCode(200)
		    .validateStatusLine("OK")
		    .validateContentType("application/json")
		    .validateIncidentCategory("result.findAll{it.category == 'hardware'}.category", "hardware")
		    .validateIncidentCategoryCount("result.findAll{it.category == 'hardware'}.category", 11);
	}
	

}