package com.matschie.testng.api;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.matschie.general.utils.PropertiesHandler.config;

public class Retry implements IRetryAnalyzer {

	int counter = 0;

	public boolean retry(ITestResult result) {
		if (counter < Integer.parseInt(config("matchie.test.retry.max.limit"))) {
			counter++;
			return true;
		}
		return false;
	}

}