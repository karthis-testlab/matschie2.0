package com.matschie.servicenow.test.runners;

//import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/com/matchi/servicenow/features/IncidentService.feature"},
		          glue = {"com.matschie.servcienow.steps", "com.matschie.servicenow.cucumber.hooks"},
		          dryRun = false,		          
		          plugin = {
		        		  "pretty", 
		        		  "html:reports/result.html",
		        		  "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
		        		  "rerun:src/test/resources/failedtest.txt"
		        		  }
		         )

public class IncidentServiceTest extends AbstractTestNGCucumberTests {
	
	/*@DataProvider(parallel = true)
    public Object[][] scenarios() {
		return super.scenarios();		
	}*/

}