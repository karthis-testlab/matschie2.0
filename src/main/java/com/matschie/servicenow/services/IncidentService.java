package com.matschie.servicenow.services;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.matschie.pojo.deserialization.Root;
import com.matschie.pojo.deserialization.RootCallerId;
import com.matschie.pojo.serialization.CreateIncident;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class IncidentService {	
	
	private Response response;
	private Root root;
	private RootCallerId rootCallerId;
	
	private Response createIncident(File file) {
		return given()    
		.header("Content-Type", "application/json")			
        .log()
        .all()
        .when()
        .body(file)
        .post("/incident");
	}
	
	private Response createIncident(String payload) {
		return given()    
		.header("Content-Type", "application/json")			
        .log()
        .all()
        .when()
        .body(payload)
        .post("/incident");
	}
	
	private Response createIncident(CreateIncident payload) {
		return given()    
		.header("Content-Type", "application/json")			
        .log()
        .all()
        .when()
        .body(payload)
        .post("/incident");
	}	
	
	private Response getASingleIncident(String sysId) {
		return given()						
		        .log()
		        .all()
		        .when()		        
		        .get("/incident/"+sysId);
	}
	
	private Response getAllIncidents(Map<String, String> map) {
		return given()	
				.queryParams(map)
		        .log()
		        .all()
		        .when()
		        .get("/incident");
	}
	
	private Response getASingleIncidentWithQueryParam(String sysId, Map<String, String> map) {		
		return given()	
				.queryParams(map)
		        .log()
		        .all()
		        .when()		        
		        .get("/incident/"+sysId);
	}
	
	public IncidentService create(CreateIncident payload) {
		response = createIncident(payload);
		root = response.as(Root.class);
		return this;
	}
	
	public IncidentService createWithCallerId(CreateIncident payload) {
		response = createIncident(payload);
		rootCallerId = response.as(RootCallerId.class);
		return this;
	}
	
	public IncidentService create(String payload) {
		response = createIncident(payload);
		return this;
	}
	
	public IncidentService create(File payload) {
		response = createIncident(payload);
		return this;
	}
	
	public IncidentService get(String sysId) {
		response = getASingleIncident(sysId);
		return this;
	}
	
	public IncidentService get(String sysId, Map<String, String> map) {
		response = getASingleIncidentWithQueryParam(sysId, map);
		return this;
	}
	
	public IncidentService get(Map<String, String> map) {
		response = getAllIncidents(map);
		return this;
	}
	
	public IncidentService validateStatusCode(int statusCode) {
		assertThat(response.getStatusCode(), equalTo(statusCode));
		return this;
	}
	
	public IncidentService validateStatusLine(String statusLine) {
		assertThat(response.getStatusLine(), containsString(statusLine));
		return this;
	}
	
	public IncidentService validateContentType(String contentType) {
		assertThat(response.getContentType(), containsString(contentType));
		return this;
	}
	
	public IncidentService validateShortDescriptionValue(String expected) {
		if(root != null) {
			assertThat(root.getResult().getShort_description(), equalTo(expected));
		} else {
			assertThat(rootCallerId.getResult().getShort_description(), equalTo(expected));
		}
		return this;
	}
	
	public IncidentService validateDescriptionValue(String expected) {
		if(root != null) {
			assertThat(root.getResult().getDescription(), equalTo(expected));
		} else {
			assertThat(rootCallerId.getResult().getDescription(), equalTo(expected));
		}
		return this;
	}
	
	public IncidentService validateCallerIdIsEmpty() {
		assertThat(root.getResult().getCaller_id(), is(emptyString()));
		return this;
	}
	
	public IncidentService validateCallerIdValue(String expected) {
		assertThat(rootCallerId.getResult().getCaller_id().getValue(), equalTo(expected));
		return this;
	}
	
	public IncidentService validateIncidentCategory(String jsonPath, String expected) {
		List<String> list = response.body().jsonPath().getList(jsonPath);
		for (String string : list) {
			assertThat(string, equalTo(expected));
		}
		return this;
	}
	
	public IncidentService validateIncidentCategoryCount(String jsonPath, int expected) {
		List<String> list = response.body().jsonPath().getList(jsonPath);
		assertThat(list.size(), equalTo(expected));
		return null;
	}
	
	public String extractValue(String jsonPath) {
		return response.body().jsonPath().getString(jsonPath);
	}
	
	public String extractResponse() {
		return response.body().asPrettyString();
	}

}