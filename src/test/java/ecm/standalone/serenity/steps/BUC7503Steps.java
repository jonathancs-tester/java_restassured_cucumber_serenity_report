package ecm.standalone.serenity.steps;

import ecm.standalone.cucumber.utils.UtilsSteps;
import ecm.standalone.testData.BUC7503testData;
import ecm.standalone.utils.Utils;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;

public class BUC7503Steps {

	@Steps
	UtilsSteps utilsSteps;
	
	/***
	 * Function that creates a generic resource (POST request) using a payload provided as input.
	 * Upadmin is used for this operation.
	 * It expects the project to be already created
	 * 
	 * @param projectID The ID of the project that will be used
	 * @param payload The payload that will be submitted
	 * @param endpoint The endpoint to which this request will be submitted (such as /productOffering/ )
	 * @return the response of the request submitted
	 * @throws Exception Exception
	 */
	public Response createResource(String projectID, Object payload, String endpoint) throws Exception {
		UtilsSteps setup = new UtilsSteps();
		UtilsSteps.setProjectId(projectID);
		setup.authorizationRequest("upadmin", "upadmin");
		setup.addQueryParam("project", projectID);
		setup.createPOSTRequestBodyObject(payload);
		setup.sendPOSTRequestTo(endpoint);
		setup.verifyStatusCode(201);
		return setup.getResponse();
	}

	/***
	 * POST operation to create a new resource using the user provided.
	 * @param user The user that will be provided as authorization
	 * @param projectID The ID of the existing Project
	 * @param resource The type of resource that will be created
	 * @param itemDate The startDateTime that will be used
	 * @throws Exception Exception
	 */
	public void submitPOST(String user, String projectID, String resource, String itemDate) throws Exception {
		utilsSteps.authorizationRequest(user, user);
		utilsSteps.addQueryParam("project", projectID);
		utilsSteps.createPOSTRequestBodyObject(Utils.getPayload(resource, itemDate));
		utilsSteps.sendPOSTRequestTo(Utils.getEndpoint(resource));
	}

	/***
	 * PUT operation to update a resource using the user provided.
	 * This function creates a resource before updating.
	 * @param user The user that will be provided as authorization
	 * @param projectID The ID of the existing Project
	 * @param resource The type of resource that will be created
	 * @param itemDate The startDateTime that will be used
	 * @throws Exception Exception
	 * @return Response of the submitted request
	 */
	public Response submitPUT(String user, String projectID, String resource, String itemDate) throws Exception {
  		Response response = createResource(projectID, Utils.getPayload(resource,itemDate), Utils.getEndpoint(resource));	
  		//create and submit put payload
  		utilsSteps.authorizationRequest(user, user);
  		utilsSteps.addQueryParam("project", projectID);
  		if(resource.equals("Request") ){
  			BUC7503testData.setResourceId(response.jsonPath().getString("id"));
  		}
  		BUC7503testData.setResourceName(BUC7503testData.getResourceName() + " updated");
  		utilsSteps.createPOSTRequestBodyObject(Utils.getPayload(resource,itemDate));
  		utilsSteps.sendPUTRequestTo(Utils.getEndpoint(resource),BUC7503testData.getResourceId());	
		return UtilsSteps.getResponse();
	}

	/***
	 * PATCH operation to update a resource using the user provided.
	 * This function creates a resource before updating.
	 * @param user The user that will be provided as authorization
	 * @param projectID The ID of the existing Project
	 * @param resource The type of resource that will be created
	 * @param itemDate The startDateTime that will be used
	 * @throws Exception Exception
	 * @return Response of the submitted request
	 */
	public Response submitPATCH(String user, String projectID, String resource, String itemDate) throws Exception {
  		Response response = createResource(projectID, Utils.getPayload(resource,itemDate), Utils.getEndpoint(resource));	
  		//create and submit patch payload
  		utilsSteps.authorizationRequest(user, user);
  		utilsSteps.addQueryParam("project", projectID);
  		if(resource.equals("Request") ){
  			BUC7503testData.setResourceId(response.jsonPath().getString("id"));
  		}
  		BUC7503testData.setResourceName(BUC7503testData.getResourceName() + " updated");
  		utilsSteps.createPOSTRequestBodyObject(Utils.getPayload(resource,itemDate));
  		utilsSteps.sendPATCHRequestTo(Utils.getEndpoint(resource),BUC7503testData.getResourceId());
  		return UtilsSteps.getResponse();
	}

	/***
	 * DELETE operation to destroy a resource using the user provided.
	 * This function creates a resource before deleting.
	 * @param user The user that will be provided as authorization
	 * @param projectID The ID of the existing Project
	 * @param resource The type of resource that will be created
	 * @param itemDate The startDateTime that will be used
	 * @throws Exception Exception
	 * @return Response of the submitted request
	 */
	public Response submitDELETE(String user, String projectID, String resource, String itemDate) throws Exception {
  		Response response = createResource(projectID, Utils.getPayload(resource,itemDate), Utils.getEndpoint(resource));	
  		//create and submit delete
  		utilsSteps.authorizationRequest(user, user);
  		utilsSteps.addQueryParam("project", projectID);
  		if(resource.equals("Request") ){
  			BUC7503testData.setResourceId(response.jsonPath().getString("id"));
  		}
  		utilsSteps.sendDELETERequestTo(Utils.getEndpoint(resource),BUC7503testData.getResourceId());
  		return UtilsSteps.getResponse();
	}

	/***
	 * GET operation to retrieve a resource using the user provided.
	 * This function creates a resource before retrieving it.
	 * @param user The user that will be provided as authorization
	 * @param projectID The ID of the existing Project
	 * @param resource The type of resource that will be created
	 * @param itemDate The startDateTime that will be used
	 * @throws Exception Exception
	 * @return Response of the submitted request
	 */
	public Response submitGET(String user, String projectID, String resource, String itemDate) throws Exception {
		Response response = createResource(projectID, Utils.getPayload(resource,itemDate), Utils.getEndpoint(resource));	
  		//create and submit get
  		utilsSteps.authorizationRequest(user, user);
  		utilsSteps.addQueryParam("project", projectID);
  		if(resource.equals("Request") ){
  			BUC7503testData.setResourceId(response.jsonPath().getString("id"));
  		}
  		utilsSteps.sendGETRequestTo(Utils.getEndpoint(resource),BUC7503testData.getResourceId());
  		return UtilsSteps.getResponse();
	}

	/***
	 * POST operation to activate a resource using the user provided.
	 * This function does not create a resource before updating.
	 * @param user The user that will be provided as authorization
	 * @param projectID The ID of the existing Project
	 * @param resource The type of resource that will be created
	 * @param itemDate The startDateTime that will be used
	 * @throws Exception Exception
	 * @return Response of the submitted request
	 */
	public Response submitACTIVATE(String user, String projectID, String resource, String itemDate) throws Exception {
  		utilsSteps.authorizationRequest(user, user);
  		utilsSteps.sendPOSTRequestTo(Utils.getEndpoint(resource)+"/"+BUC7503testData.getProjectId()+"/activate");
  		return UtilsSteps.getResponse();
	}

	/***
	 * POST operation to publish a resource using the user provided.
	 * This function does not create a resource before updating.
	 * @param user The user that will be provided as authorization
	 * @param projectID The ID of the existing Project
	 * @param resource The type of resource that will be created
	 * @param itemDate The startDateTime that will be used
	 * @throws Exception Exception
	 * @return Response of the submitted request
	 */
	public Response submitPUBLISH(String user, String projectID, String resource, String itemDate) throws Exception {
  		utilsSteps.authorizationRequest(user, user);
  		utilsSteps.sendPOSTRequestTo(Utils.getEndpoint(resource)+"/"+BUC7503testData.getProjectId()+"/publish");
  		return UtilsSteps.getResponse();
	}

	/***
	 * POST operation to submit a resource using the user provided.
	 * This function creates a resource before updating.
	 * @param user The user that will be provided as authorization
	 * @param projectID The ID of the existing Project
	 * @param resource The type of resource that will be created
	 * @param itemDate The startDateTime that will be used
	 * @throws Exception Exception
	 * @return Response of the submitted request
	 */
	public Response submitSUBMIT(String user, String projectID, String resource, String itemDate) throws Exception {
  		Response response = createResource(projectID, Utils.getPayload(resource,itemDate), Utils.getEndpoint(resource));	
  		utilsSteps.authorizationRequest(user, user);
  		if(resource.equals("Request") ){
  			BUC7503testData.setResourceId(response.jsonPath().getString("id"));
  		}
  		utilsSteps.sendPOSTRequestTo(Utils.getEndpoint(resource)+"/"+BUC7503testData.getResourceId()+"/submit");
  		return UtilsSteps.getResponse();
	}

}
