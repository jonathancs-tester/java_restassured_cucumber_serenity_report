package ecm.standalone.cucumber.ET;

import cucumber.api.java.en.*;
import ecm.standalone.testData.ETtestData;
import ecm.standalone.utils.Utils;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;

public class ET9201 {
	
	JsonPath response;
	String projectDate = Utils.getEffectiveDate(2, 0);
	String itemDate = Utils.getEffectiveDate(2, 0);
	String projectID,ruleID,ruleSpaceSpecId;
	String json;
	
	public ET9201() {
		projectID=ETtestData.getProjectId();
	}
	
	@Given("^there is a product offering with Market Segment$")
	public void createPOwithMarketSegment() throws Exception {
		ETtestData.createProject("upadmin", "upadmin", projectDate);
		projectID=ETtestData.getProjectId();
		
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic("upadmin", "upadmin")
		      	.contentType("application/json")
		      	.body(ETtestData.getPayload("Catalog Rules MDR - Composite True", itemDate, null, null, null, null, null))
		      	.queryParam("project", ETtestData.getProjectId())
		    .when()
		      .post("/rule");
		
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic("upadmin", "upadmin")
		      	.contentType("application/json")
		      	.body(ETtestData.getPayload("Catalog Rules MDR - Composite False", itemDate, null, null, null, null, null))
		      	.queryParam("project", projectID)
		    .when()
		      .post("/rule");
	
	    SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic("upadmin", "upadmin")
		      	.contentType("application/json")
		      	.pathParam("projectID", projectID)
		    .when()
		      	.post("/project/{projectID}/activate");  
	}
	
	@Then("^I can Edit and Assign the Market Segment Rules with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void edit_assign_market_segment(String user, int responseCode) throws Exception {
		ETtestData.createProject(user, user, projectDate);
		
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.body(ETtestData.getPayload("Product Offering with Rule", itemDate, "marketSegment", null, null, null, null))
		      	.queryParam("project", ETtestData.getProjectId())
		    .when()
		      	.post("/productOffering")
		    .then()
		    	.statusCode(responseCode);
	}
	
	@Given("^there is a product offering with \"([^\"]*)\" rule$")
	public void createRules(String type) throws Exception {
		ETtestData.createProject("upadmin", "upadmin", projectDate);
		
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic("upadmin", "upadmin")
		      	.contentType("application/json")
		      	.body(ETtestData.getPayload("Product Offering with Rule", itemDate, type, null, null, null, null))
		      	.queryParam("project", ETtestData.getProjectId())
		    .when()
		      	.post("/productOffering")
		    .then()
		    	.statusCode(201);
	}
	
	@Given("^there is a product offering with \"([^\"]*)\" rule and \"([^\"]*)\"$")
	public void createRulesWithUser(String type, String user) throws Exception {
		ETtestData.createProject(user, user, projectDate);
		
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.body(ETtestData.getPayload("Product Offering with Rule", itemDate, type, null, null, null, null))
		      	.queryParam("project", ETtestData.getProjectId())
		    .when()
		      	.post("/productOffering")
		    .then()
		    	.statusCode(201);
	}
	
	@Then("^I change name the rules to \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void modifyRuleTypeMarketSegment(String type, String user, int responseCode) throws Exception {
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.body(ETtestData.getPayload("Product Offering with Rule modified", itemDate, type, null, null, null, null))
		      	.queryParam("project", ETtestData.getProjectId())
		      	.pathParam("poId", ETtestData.getResourceId())
		    .when()
		      	.patch("/productOffering/{poId}")
		    .then()
		    	.statusCode(responseCode);
	}

	@Then("^I verify if \"([^\"]*)\" status code is \"([^\"]*)\"$")
	public void the_system_allows_to_mark_the_config_variable_as(String user, int responseCode) throws Exception {
		response =
					SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.queryParam("project", ETtestData.getProjectId())
					      	.pathParam("poId", ETtestData.getResourceId())
					    .when()
					      	.get("/productOffering/{poId}")
						 .then()
						   	.log()
						   	.all()
						   	.statusCode(responseCode)
						   	.extract()
						   	.jsonPath();
	}
}