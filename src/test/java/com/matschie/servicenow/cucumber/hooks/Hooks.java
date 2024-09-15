package com.matschie.servicenow.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class Hooks {
	
	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("BEFORE --> Running the scenario: "+ scenario.getName());
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		System.out.println("AFTER --> Ran the scenario: "+ scenario.getName());
	}
	
	@BeforeStep
	public void beforeStep() {
		System.out.println("BEFORE STEP Run");
	}
	
	@AfterStep
	public void afterStep() {
		System.out.println("AFTER STEP Run");
	}
}