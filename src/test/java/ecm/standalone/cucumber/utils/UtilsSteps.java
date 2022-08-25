package ecm.standalone.cucumber.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ecm.standalone.cucumber.BUC7502.BUC7502;
import ecm.standalone.utils.Utils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UtilsSteps {

  private static RequestSpecification request;
  private static Response response;
  private static String projectId;
  private static String resourceId;
  private static String cat1Id;
  private static String cat2Id;

  @Step
  @When("^a GET operation is made on \"([^\"]*)\" API$")
  public void getAPI(String endpoint) throws Exception {
    String resource = null;
    switch (endpoint) {
    	case "Product Category":
    		resource = cat1Id;
    	break;
    case "User Preference":
      resource = BUC7502.getApplicationId(0);
      break;
    default:
      resource = "";
      break;
    }
    sendGETRequestTo(Utils.getEndpoint(endpoint), resource);
  }
  
  @Step
  @When("^the user is logged as \"([^\"]*)\"$")
  public void theUserIsLoggedAs(String arg1) throws Exception {
        setRequest(
        SerenityRest
	        .given()
	         	.auth()
	         	.preemptive()
	         	.basic(arg1, arg1)
	         	.log()
	         	.all()
       );  
  }
 
  @Step
  @Then("^I verify if response \"([^\"]*)\" for minimal parameter:$")
  public void verifyIfResponseForMinimalParameter(String assertion, DataTable arg2) throws Exception {
    List<String> values = arg2.asList(String.class);
    JsonPath jsonResponse = getResponse()
    	.then()
          	.extract()
          	.jsonPath();

    if (assertion.equals("should not")) {
      assertEquals("403", jsonResponse.getString("[0].status"));
      return;
    }
	 
    for (String value : values) {
      boolean hasThatcharacteristic = false;
      int productsSize  = jsonResponse.getList("").size();
      for (int i = 0; i < productsSize; i++) {
    	  if(jsonResponse.getString("["+i+"].id").contains("BUC7905")) {
		      assertTrue(jsonResponse.getString("["+i+"].versions[0].media[0].URL") != null);
		      int characteristicSize = jsonResponse.getList("["+i+"].versions[0].characteristics").size();
		      	for (int j = 0; j < characteristicSize; j++) {
		          if (jsonResponse.getString("["+i+"].versions[0].characteristics[" + j + "].id").equals(value)) {
		            hasThatcharacteristic = true;
		          }
		        }
		      	assertTrue(hasThatcharacteristic);   
    	  	}
      	}
	 }
  }
  
  @Step
  @Then("^I verify if response \"([^\"]*)\" for expand parameter:$")
  public void verifyIfResponse(String assertion, DataTable arg2) throws Exception {
    List<String> values = arg2.asList(String.class); 
    JsonPath response = getResponse()
        	.then()
    			.extract()
    			.jsonPath();

    if (assertion.equals("has not")) {
      assertEquals("403", response.getString("[0].status"));
      return;
    }

    for (String value : values) {
      boolean hasThatcharacteristic = false;
      if (response.getString("[0].productCategoryList") != null) {
        Assert.assertTrue(response.getString("[0].productCategoryList[0].products." + value) != null);
      } else {
        int productsSize = response.getList("[0].products").size();
        for (int i = 0; i < productsSize; i++) {
        	assertTrue(response.getString("[0].products[" + i + "].product.versions[0].media[0].URL") != null);
        	int characteristicSize = response.getList("[0].products[" + i + "].product.versions[0].characteristics").size();
        	for (int j = 0; j < characteristicSize; j++) {
        		if (response.getString("[0].products[" + i + "].product.versions[0].characteristics[" + j + "].id").equals(value)) {
        			hasThatcharacteristic = true;
            }
          }
          assertTrue(hasThatcharacteristic);
        }
      }
    }
  }

  @Step
  @When("^the \"([^\"]*)\" = \"([^\"]*)\" parameter is used$")
  public void parameterIsUsed(String param, String value) {
    if(value.equals("projectid")) {
      value = projectId;
    }
    addQueryParam(param, value);
  }
  
  @Step
  @Then("^the return http status code is \"([^\"]*)\"$")
  public void assertStatusCode(String statusCode) {
    verifyStatusCode(Integer.parseInt(statusCode));
  }

  @Step("POST Request")
  public void sendPOSTRequestTo(String endpoint) {
    setResponse(
     getRequest()
     	.log()
     	.all()
     .when()
       .post(endpoint)
    );
  }
    
  @Step("GET Request")
  public void sendGETRequestTo(String endpoint, String resourceId) throws Exception{
    getRequest().body("");
    setResponse(
         getRequest()
         	.log()
         	.all()
         	.pathParam("resourceId", resourceId)
          .when()
            .get(endpoint+"/{resourceId}")
        );
  }
  
  @Step("DELETE Request")
  public void sendDELETERequestTo(String endpoint, String resourceId) {
    setResponse(
     getRequest()
     	.log()
     	.all()
     	.pathParam("resourceId", resourceId)
      .when()
        .delete(endpoint+"/{resourceId}")
    );
    
  }
  
  @Step("PATCH Request")
  public void sendPATCHRequestTo(String endpoint, String resourceId) {
    setResponse(
     getRequest()
     	.log()
     	.all()
     	.pathParam("resourceId", resourceId)
      .when()
        .patch(endpoint+"/{resourceId}")
    );
  }
  
  @Step("PUT Request")
  public void sendPUTRequestTo(String endpoint, String resourceId) {
    setResponse(
     getRequest()
     	.log()
     	.all()
     	.pathParam("resourceId", resourceId)
      .when()
        .put(endpoint+"/{resourceId}")
    );
  }
  
  @Step
  public static void setup(String buc) throws InterruptedException {
    Thread.sleep(1);
    long randomID = System.currentTimeMillis();
    setProjectId(buc + "_project_" + randomID);
    setResourceId(buc + "_res_");
  }
  
  @Step
  public static void createProject(String username, String password, String effectiveDate, String buc) throws InterruptedException {
    setup(buc);
    Utils.createProject(getProjectId(), username, password, effectiveDate);
  }

  @Step("Verify http status code")
  public void verifyStatusCode(int statusCode) {
	  assertEquals(statusCode, response.statusCode());
  }
  
  @Step("Authorization Request")
  public void authorizationRequest(String username, String password) {
    setRequest(
     SerenityRest
	     .given()
	      	.auth()
	      	.preemptive()
	      	.basic(username, password)
	      	.log()
	      	.all()
    );   
  }
  
  @Step("Prepare Path to add new parameter")
  public void addPathParam(String paramName, String value) {
    setRequest(
        getRequest()
        	.log()
        	.all()
        	.pathParam(paramName, value)
    ); 
  }
  
  @Step("Add new parameter Request")
  public void addQueryParam(String paramName, String value) {
    setRequest(
        getRequest()
        	.log()
        	.all()
        	.queryParam(paramName, value)
    ); 
  }
  
  @Step("Prepare request body")
  public void createPOSTRequestBodyObject(Object bodyObject) throws Exception {
    setRequest(
        getRequest()
          	.contentType("application/json")
          	.body(bodyObject)
          	.log()
          	.all()
        );
  }

  public RequestSpecification getRequest() {
    return request;
  }

  public void setRequest(RequestSpecification request) {
    UtilsSteps.request = request;
  }

  public static Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    UtilsSteps.response = response;
  }

  public static String getProjectId() {
    return projectId;
  }

  public static void setProjectId(String projectId) {
    UtilsSteps.projectId = projectId;
  }

  public static String getResourceId() {
    return resourceId;
  }

  public static void setResourceId(String resourceId) {
    UtilsSteps.resourceId = resourceId;
  }

  public String getCat1Id() {
    return cat1Id;
  }

  public void setCat1Id(String cat1Id) {
    UtilsSteps.cat1Id = cat1Id;
  }

  public String getCat2Id() {
    return cat2Id;
  }

  public void setCat2Id(String cat2Id) {
    UtilsSteps.cat2Id = cat2Id;
  }
}
