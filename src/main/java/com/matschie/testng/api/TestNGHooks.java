package com.matschie.testng.api;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.matschie.data.utils.DataEngine;
import com.matschie.data.utils.DataFactory;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.basic;
import static com.matschie.general.utils.PropertiesHandler.*;

public class TestNGHooks {
	
	@BeforeMethod
	public void setUp() {
		baseURI = config("service.now.instance.uri");
		basePath = config("service.now.instance.basePath");
		authentication = basic(config("service.now.instance.username"), secret("service.now.instance.password"));
	}
	
	@DataProvider
	public String[][] excel() {
		return DataFactory.engine(DataEngine.EXCEL).getData("create-incidents");
	}
	
	@DataProvider
	public String[][] csv() {
		return DataFactory.engine(DataEngine.CSV).getData("incidents");
	}

}