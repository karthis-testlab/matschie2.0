package com.matschie.servicenow.test.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"@src/test/resources/failedtest.txt"},
		          glue = {"com.matschie.servcienow.steps"},
		          dryRun = false,		          
		          plugin = {
		        		  "pretty", 
		        		  "html:reports/result.html",
		        		  "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
		        		  "rerun:src/test/resources/failedtest.txt"
		        		  }
		         )

public class ReRunFaliedTest extends AbstractTestNGCucumberTests {

}