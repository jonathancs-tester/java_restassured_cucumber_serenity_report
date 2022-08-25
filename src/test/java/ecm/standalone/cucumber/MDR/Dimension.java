package ecm.standalone.cucumber.MDR;

import static org.junit.Assert.assertEquals;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ecm.standalone.testData.MDRtestData;
import ecm.standalone.utils.Utils;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;

public class Dimension {
	JsonPath response;
	String projectDate = Utils.getEffectiveDate(-2, 4);
	String itemDate = Utils.getEffectiveDate(-1, 4);
	String itemDate2 = Utils.getEffectiveDate(-2, 4);
	String projectID,dimensionID,dimensionSpecId;
	String json;
	
	public Dimension() {
		MDRtestData.setup();
		dimensionID = MDRtestData.getDimensionId();
		dimensionSpecId = MDRtestData.getDimensionSpecId();
	}
	
	@Given("^I create the project with \"([^\"]*)\"$")
	public void createProject(String user) {	
		MDRtestData.createProject(user, user, projectDate);
		projectID=MDRtestData.getProjectId();
	}
	
	@Given("^I create the Dimension Spec with \"([^\"]*)\"$")
	public void createDimensionSpec(String user) throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.body(MDRtestData.getPayload("Dimension Specification", itemDate, "simpleDimension", null, null))
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		      	.post("/dimensionSpecification");
	}
	
	@When("^I delete the Dimension with \"([^\"]*)\"$")
	public void deleteDimension(String user) throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.pathParam("dimensionId", MDRtestData.getDimensionId())
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		      	.delete("/dimension/{dimensionId}");
	}
	
	@When("^I delete the dimension element with \"([^\"]*)\"$")
	public void deleteDimensionElement(String user) throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.pathParam("dimensionId", MDRtestData.getDimensionId())
		      	.pathParam("dimensionElementId", "Element_01")
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		      	.delete("/dimension/{dimensionId}/dimensionElement/{dimensionElementId}");
	}
	
	@Given("^I create the simple dimension without dimension element and \"([^\"]*)\"$")
	public void createDimensionSimple(String user) throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic(user, user)
		      	.contentType("application/json")
		      	.body(MDRtestData.getPayload("Dimension without Element", itemDate2, "simpleDimension", null, null))
		      	.queryParam("project", MDRtestData.getProjectId())
		    .when()
		    	.post("/dimension");
	}
	
	@Given("^I create the same simple dimension with \"([^\"]*)\"$")
	public void createSameDimensionSimple(String user) throws Exception {	
		response = SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.body(MDRtestData.getPayload("Dimension without Element", itemDate2, "simpleDimension", null, null))
					      	.queryParam("project", MDRtestData.getProjectId())
					    .when()
					    	.post("/dimension")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(500)
					      	.extract()
					      	.jsonPath();
	}
	
	@Given("^request \"([^\"]*)\" to the Dimension \"([^\"]*)\" with \"([^\"]*)\"$")
	public void createSimpleDimension(String request, String type, String user) throws Exception {
		if(request.equals("post")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension", itemDate, type, null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/dimension");
		}
		else if (request.equals("put")) {
			SerenityRest
		    	.given()
		    		.auth()
		    		.preemptive()
		    		.basic(user, user)
		    		.contentType("application/json")
		    		.body(MDRtestData.getPayload("Dimension", itemDate, type, null, null))
		    		.queryParam("project", MDRtestData.getProjectId())
		    	.when()
		    		.post("/dimension");
			
			SerenityRest
		    	.given()
		    		.auth()
		    		.preemptive()
		    		.basic(user, user)
		    		.contentType("application/json")
		    		.body(MDRtestData.getPayload("Dimension updated", itemDate, type, null, null))
		    		.pathParam("dimensionId", MDRtestData.getDimensionId())
		    		.queryParam("project", MDRtestData.getProjectId())
		    	.when()
		    		.put("/dimension/{dimensionId}");
		}
	}
	
	@Given("^Request \"([^\"]*)\" to the Dimension Element with \"([^\"]*)\"$")
	public void createUpdateDimensionElement(String request, String user) throws Exception {	
		if(request.equals("post")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension Element", itemDate2, "simpleDimension", null, null))
			      	.pathParam("dimensionId", MDRtestData.getDimensionId())
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/dimension/{dimensionId}/dimensionElement");
		}
		else if (request.equals("put")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension Element", itemDate2, "simpleDimension", null, null))
			      	.pathParam("dimensionId", MDRtestData.getDimensionId())
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/dimension/{dimensionId}/dimensionElement");
				
				SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension Element updated", itemDate2, "simpleDimension", null, null))
			      	.pathParam("dimensionId", MDRtestData.getDimensionId())
			      	.pathParam("dimensionElementId", "Element_01")
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.put("/dimension/{dimensionId}/dimensionElement/{dimensionElementId}");
			
		}else if (request.equals("patch")) {
			SerenityRest
		    	.given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension Element", itemDate2, "simpleDimension", null, null))
			      	.pathParam("dimensionId", MDRtestData.getDimensionId())
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/dimension/{dimensionId}/dimensionElement");
				
				SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension Element updated", itemDate2, "simpleDimension", null, null))
			      	.pathParam("dimensionId", MDRtestData.getDimensionId())
			      	.pathParam("dimensionElementId", "Element_01")
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.patch("/dimension/{dimensionId}/dimensionElement/{dimensionElementId}");
		}
	}
	
	@Given("^I create the simple dimension with wrong \"([^\"]*)\" with \"([^\"]*)\"$")
	public void createWrongSimpleDimension(String error, String user) throws Exception {	
		if(error.equals("element")) {	
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension wrong Dimension Spec element", itemDate, "simpleDimension", null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/dimension");
		} else if (error.equals("dimSpec")) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension wrong Dimension Spec dimSpec", itemDate, "simpleDimension", null, null))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/dimension");
		}
	}
	
	@Given("^I create \"([^\"]*)\" Dimension version and activate each Dimension with \"([^\"]*)\"$")
	public void createPOVersionandActivate(int number, String user) throws Exception {
		for (int i = 1; i <= number; i++) {
		itemDate = Utils.getEffectiveDate(i, i);
		MDRtestData.setup();
		Utils.createProject(MDRtestData.getProjectId(), user, user, Utils.getEffectiveDate(i, i));
		
		if(i==1) {
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension Specification Version", itemDate, "simpleDimension", null, dimensionSpecId))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/dimensionSpecification");
		}
			SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.body(MDRtestData.getPayload("Dimension Version", itemDate, "simpleDimension", dimensionID, dimensionSpecId))
			      	.queryParam("project", MDRtestData.getProjectId())
			    .when()
			      	.post("/dimension");
		    
		    SerenityRest
			    .given()
			      	.auth()
			      	.preemptive()
			      	.basic(user, user)
			      	.contentType("application/json")
			      	.pathParam("projectID", MDRtestData.getProjectId())
			    .when()
			      	.post("/project/{projectID}/activate");
		}
	}
	
	@When("^I validate the project$")
	public void validateProject() throws Exception {	
		SerenityRest
		    .given()
		      	.auth()
		      	.preemptive()
		      	.basic("upadmin", "upadmin")
		      	.contentType("application/json")
		      	.pathParam("projectId", MDRtestData.getProjectId())
		    .when()
		      	.post("/project/{projectId}/validate")
		    .then()
		      	.statusCode(200);
	}
	
	@Then("^GET request to return the Dimension with \"([^\"]*)\"$")
	public void getSimpleDimension(String user) throws Exception {	
		 response =
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.pathParam("dimensionId", MDRtestData.getDimensionId())
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					    	.get("/dimension/{dimensionId}/")
					    .then()
					    	.log()
					    	.all()
					      	.statusCode(200)
					      	.extract()
					      	.jsonPath();
	}
	
	@Then("^GET request to return the Dimension Element by ID with \"([^\"]*)\"$")
	public void getSimpleDimensionElementID(String user) throws Exception {	
		 response =
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.pathParam("dimensionId", MDRtestData.getDimensionId())
					      	.pathParam("dimensionElementId", "Element_01")
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					    	.get("/dimension/{dimensionId}/dimensionElement/{dimensionElementId}")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(200)
					      	.extract()
					      	.jsonPath();
	}
	
	@Then("^GET request to return the Dimension Element with \"([^\"]*)\"$")
	public void getSimpleDimensionElement(String user) throws Exception {	
		 response =
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.pathParam("dimensionId", MDRtestData.getDimensionId())
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					      	.get("/dimension/{dimensionId}/dimensionElement")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(200)
					      	.extract()
					      	.jsonPath();
	}
	
	@When("^GET request to return all Dimension with \"([^\"]*)\"$")
	public void getSimpleDimensionVersion(String user) throws Exception {	
		 response =
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.pathParam("dimensionId", dimensionID)
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					      	.get("/dimension/{dimensionId}/version")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(200)
					      	.extract()
					      	.jsonPath();
	}
	
	@Then("^GET request to return the Simple Dimension with wrong Dimension Specification and \"([^\"]*)\"$")
	public void getWrongSimpleDimension(String user) throws Exception {	
		 response =
				    SerenityRest
					    .given()
					      	.auth()
					      	.preemptive()
					      	.basic(user, user)
					      	.contentType("application/json")
					      	.pathParam("dimensionId", MDRtestData.getDimensionId())
					      	.queryParam("project", MDRtestData.getProjectId())
					      	.log()
					      	.all()
					    .when()
					      	.get("/dimension/{dimensionId}/")
					    .then()
					      	.log()
					      	.all()
					      	.statusCode(404)
					      	.extract()
					      	.jsonPath();
		 
		 assertEquals(response.getString("[0].message"),"Dimension not found");
	}

	@Then("^I verify Dimension quantity version \"([^\"]*)\" in project$")
	public void verify_if_quantity_dimension(int number) throws Exception {
		for (int i = 1; i <= number; i++) {
			assertEquals(response.getString("[0].versions["+(i-1)+"].state"),"ACT");
		}
	}
	
	@Then("^I verify error in dimension duplicated$")
	public void verify_if_error_dimension() throws Exception {
		assertEquals(response.getString("[0].status"),"500");
		assertEquals(response.getString("[0].message"),"Entity with same ID already exists.");		
	}
}