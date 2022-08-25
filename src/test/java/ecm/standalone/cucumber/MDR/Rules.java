package ecm.standalone.cucumber.MDR;

import static org.junit.Assert.assertEquals;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ecm.standalone.testData.MDRtestData;
import ecm.standalone.utils.Utils;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;

public class Rules {
	JsonPath response;
	String projectDate = Utils.getEffectiveDate(2, 0);
	String itemDate = Utils.getEffectiveDate(2, 0);
	String projectID,ruleID,ruleSpaceSpecId;
	String json;
	
	public Rules() {
		projectID=MDRtestData.getProjectId();
		ruleID = MDRtestData.getRuleSpaceSpecId();
		ruleSpaceSpecId = MDRtestData.getRuleSpaceSpecId();
	}
	
	@Given("^I create the RuleSpaceSpecification with \"([^\"]*)\"$")
	public void createDimensionSpec(String user) throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.body(MDRtestData.getPayload("Rule Space Specification", itemDate, null, null, null))
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		      	.post("/ruleSpaceSpecification");
	}
	
	@Given("^I create the Catalog Rule with \"([^\"]*)\"$")
	public void createCR(String user) throws Exception {	
		SerenityRest
	    	.given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.body(MDRtestData.getPayload("Catalog Rules MDR", itemDate, null, null, null))
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		    	.post("/rule");
	}
	
	@When("^I delete the RuleSpaceSpecification with \"([^\"]*)\"$")
	public void deleteRuleSpaceSpecification(String user) throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.pathParam("ruleSpaceSpecificationId", MDRtestData.getRuleSpaceSpecId())
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		      	.delete("/ruleSpaceSpecification/{ruleSpaceSpecificationId}");
	}
	
	@When("^I delete the Catalog Rule with \"([^\"]*)\"$")
	public void deleteCR(String user) throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		      	.delete("/rule/{catalogRuleId}");
	}
	
	@When("^I delete the Rule Assignment with \"([^\"]*)\"$")
	public void deleteRuleAssignment(String user) throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
		      	.pathParam("ruleAssignmentId", MDRtestData.getRuleAssignmentId())
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		      	.delete("/rule/{catalogRuleId}/ruleAssignment/{ruleAssignmentId}");
	}
	
	
	@Given("^Request \"([^\"]*)\" to the Rule Assignment with \"([^\"]*)\"$")
	public void createRuleAssignment(String request, String user) throws Exception {	
		if(request.equals("post")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Rule Assignment", itemDate, null, null, null))
			      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/rule/{catalogRuleId}/ruleAssignment");
			}
		else if (request.equals("put")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Rule Assignment", itemDate, null, null, null))
			      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/rule/{catalogRuleId}/ruleAssignment");
				
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Rule Assignment updated", itemDate, null, null, null))
			      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
			      	.pathParam("ruleAssignmentId", MDRtestData.getRuleAssignmentId())
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.put("/rule/{catalogRuleId}/ruleAssignment/{ruleAssignmentId}");
		}
	}
	@Given("^Request \"([^\"]*)\" to the Catalog Rule with \"([^\"]*)\"$")
	public void createCatalogRule(String request, String user) throws Exception {	
		if(request.equals("post")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Catalog Rules MDR", itemDate, null, null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/rule");
		}
		else if (request.equals("put")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Catalog Rules MDR", itemDate, null, null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/rule");
			
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Catalog Rules MDR updated", itemDate, null, null, null))
			      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.put("/rule/{catalogRuleId}");
		}
	}
	
	@Given("^Request \"([^\"]*)\" to the Catalog Rule with Rule Assignment and \"([^\"]*)\"$")
	public void createCatalogRuleRuleAssignment(String request, String user) throws Exception {	
		if(request.equals("post")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Catalog Rules MDR with Rule Assignment", itemDate, null, null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/rule");
		}
		else if (request.equals("put")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Catalog Rules MDR with Rule Assignment", itemDate, null, null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/rule");
			
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Catalog Rules MDR with Rule Assignment updated", itemDate, null, null, null))
			      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.put("/rule/{catalogRuleId}");
		}
	}
	
	@Given("^Request \"([^\"]*)\" to the RuleSpaceSpecification with \"([^\"]*)\"$")
	public void createRuleSpaceSpecification(String request, String user) throws Exception {	
		if(request.equals("post")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Rule Space Specification", itemDate, null, null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/ruleSpaceSpecification");
		}
		else if (request.equals("put")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Rule Space Specification", itemDate, null, null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/ruleSpaceSpecification");
			
			SerenityRest
			    .given()
			      .auth()
			      .preemptive()
			      .basic(user, user)
			      .contentType("application/json")
			      .body(MDRtestData.getPayload("Rule Space Specification updated", itemDate, null, null, null))
			      .pathParam("ruleSpaceSpecificationId", MDRtestData.getRuleSpaceSpecId())
			      .queryParam("project", MDRtestData.getProjectId())
			    .when()
			      .put("/ruleSpaceSpecification/{ruleSpaceSpecificationId}");
		}
	}
	
	@Then("^GET request to return the RuleSpaceSpecification with \"([^\"]*)\"$")
	public void getRuleSpaceSpecification(String user) throws Exception {	
		 response =
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.pathParam("ruleSpaceSpecificationId", MDRtestData.getRuleSpaceSpecId())
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					      	.get("/ruleSpaceSpecification/{ruleSpaceSpecificationId}")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(200)
					      	.extract()
					      	.jsonPath();
	}
	
	@Then("^GET request to return with empty RuleSpaceSpecification$")
	public void getEmptyRuleSpaceSpecification() throws Exception {	
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic("upadmin", "upadmin")
					      	.contentType("application/json")
					      	.pathParam("ruleSpaceSpecificationId", MDRtestData.getRuleSpaceSpecId())
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					      	.get("/ruleSpaceSpecification/{ruleSpaceSpecificationId}")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(204);
	}
	
	@Then("^GET request to return the Catalog Rule with \"([^\"]*)\"$")
	public void getCatalogRule(String user) throws Exception {	
		 response =
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					      	.get("/rule/{catalogRuleId}")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(200)
					      	.extract()
					      	.jsonPath();
	}
	
	@Then("^GET request to return the Rule Assignment with \"([^\"]*)\"$")
	public void getRuleAssignment(String user) throws Exception {	
		 response =
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.pathParam("catalogRuleId", MDRtestData.getCatalogRuleId())
					      	.pathParam("ruleAssignmentId", MDRtestData.getRuleAssignmentId())
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					      	.get("/rule/{catalogRuleId}/ruleAssignment/{ruleAssignmentId}")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(200)
					      	.extract()
					      	.jsonPath();
	}
	
	@Then("^I validate the \"([^\"]*)\" state$")
	public void validateIsCmposite(String isComposite) throws Exception {	
		assertEquals("isComposite", response.getString("[0].versions[0].properties[0].id"));
		 assertEquals(isComposite, response.getString("[0].versions[0].properties[0].value"));
	}
}