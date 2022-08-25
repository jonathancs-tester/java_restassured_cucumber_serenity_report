package ecm.standalone.cucumber.BUC7502;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import com.standalone.testbase.TestBase;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ecm.standalone.cucumber.utils.UtilsSteps;
import ecm.standalone.testData.BUC7502testData;
import ecm.standalone.userPreference.UserPreference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;

public class BUC7502 {
  @Steps
  UtilsSteps utilsSteps;

  private static List<String> applicationId = new ArrayList<String>();
  private static List<String> defId = new ArrayList<String>();
  private static String username = "app_pref_default_user";

  public static String getUsername() {
    return username;
  }

  public BUC7502() {
    RestAssured.baseURI = TestBase.USER_PROFILE_URL;
  }

  @Given("^I create (\\d+) application(?:s)? with \"([^\"]*)\"$")
  public void createApplications(int quantity, String user) throws Exception {
    BUC7502testData.DeleteApp();
    applicationId.clear();

    for (int sequence = 0; sequence < quantity; sequence++) {
      applicationId.add(BUC7502testData.CreateApplication(sequence + 1, user));
      BUC7502testData.CreateTranslation(getApplicationId(sequence));
      BUC7502testData.VerifyCreate("CWDBCODETABLES", "CODE", getApplicationId(sequence));
      Thread.sleep(1);
    }
  }

  @Given("^I have \"(\\d+)\" user preferences created for \"([^\"]*)\"$")
  public void createUserPreferences(int quantity, String application) throws Exception {
	  //Create application and users
	    BUC7502testData.DeleteApp(application);
	    BUC7502testData.CreateApplication(application, "upadmin");
        BUC7502testData.CreateTranslation(application);
        BUC7502testData.VerifyCreate("CWDBCODETABLES", "CODE", application);
	  
	  //Create preference definitions 1-3
	  	createPreferenceDefinition(3, "56");
	  
	  //Delete default user preferences
		utilsSteps.authorizationRequest("upadmin", "upadmin");
	  	utilsSteps.sendDELETERequestTo("app_pref_default_user" + "/applicationPreference", application);
	  	
	  //Create multiple default user preferences 
		UserPreference userPreferencePayload = BUC7502testData.createUP(application, defId);
		utilsSteps.authorizationRequest("upadmin", "upadmin");
		utilsSteps.createPOSTRequestBodyObject(userPreferencePayload);
		utilsSteps.sendPOSTRequestTo("app_pref_default_user" + "/applicationPreference/" + application);
		utilsSteps.verifyStatusCode(201);
		UtilsSteps.getResponse().then().log().all();
  }

  @Then("^I verify if response \"([^\"]*)\" be valid for user preference(?:s)?$")
  public void verifyUserPreference(String assertion, DataTable arg2) throws Exception {
    JsonPath response = UtilsSteps.getResponse().then().extract().jsonPath();
    List<Integer> values = arg2.asList(Integer.class);

    if (assertion.equals("should")) {
      Assert.assertEquals(response.getString("id"), getApplicationId(0));
      if (values.get(0) == 0) {
        Assert.assertTrue(response.getString("preferences[0].preferences") == null);
      } else {
        int amountOfPreferences = response.getList("preferences[0].preferences").size();
        for (int preferenceNumber = 0; preferenceNumber < values.size(); preferenceNumber++) {
          Assert.assertEquals(response.getString("preferences[0].preferences[" + preferenceNumber + "].id"), getDefId().get(values.get(preferenceNumber) - 1));
          Assert.assertNotNull(response.getString("preferences[0].preferences[" + preferenceNumber + "].value"));
        }
        Assert.assertEquals(values.size(), amountOfPreferences);
      }
    }
  }

  @Then("^I verify if response \"([^\"]*)\" be valid for user preference with multiples Applications$")
  public void verifyUserPreferencewithMultiplesApplications(String assertion, DataTable expectedResults) throws Exception {
	  Map<String, String> expectedResultsMapped = new HashMap<>();
	  expectedResultsMapped = expectedResults.asMap(String.class, String.class);
	  Map<String, List<String>> expectedMapOfIDsAndApplications = new HashMap<>();
	  
	  Map<String, List<String>> actualMapOfIDsAndApplications = new HashMap<>();
	  JsonPath response = UtilsSteps.getResponse().then().extract().jsonPath();
	  
	  if (assertion.equals("should")) {
		  for(String key : expectedResultsMapped.keySet()) {
			  String[] def = expectedResultsMapped.get(key).split(",");
			  List<String> expectedListOfApplications = new ArrayList<>();
			  for(int amountOfApplications = 0; amountOfApplications < def.length; amountOfApplications++) {
				  expectedListOfApplications.add(def[amountOfApplications]);
				  System.out.println("ADDED TO KEYSET: [KEY : " + key + " , VALUE : " + def[amountOfApplications] + " ]");
			  }
			  expectedListOfApplications.sort(String.CASE_INSENSITIVE_ORDER);
			  expectedMapOfIDsAndApplications.put(key, expectedListOfApplications);
		  }
		  
		  for(int sizeOfResponseArray = 0; sizeOfResponseArray < response.getList("").size();  sizeOfResponseArray++) {
			  String key = response.getString("[" + sizeOfResponseArray + "].id");
			  List<String> actualListOfApplications = new ArrayList<>();
			  for(int amountOfApplications = 0; amountOfApplications < response.getList("[" + sizeOfResponseArray + "].preferences[0].preferences").size(); amountOfApplications++) {
				  actualListOfApplications.add(response.getString("[" + sizeOfResponseArray + "].preferences[0].preferences[" + amountOfApplications + "].id"));
				  System.out.println("ADDED TO KEYSET: [KEY : " + key + " , VALUE : " + response.getString("[" + sizeOfResponseArray + "].preferences[0].preferences[" + amountOfApplications + "].id") + " ]");
			  }
			  actualListOfApplications.sort(String.CASE_INSENSITIVE_ORDER);
			  actualMapOfIDsAndApplications.put(key, actualListOfApplications);
		  }
		  
		  boolean compareMaps = true;
		  for(String expectedKey : expectedMapOfIDsAndApplications.keySet()) {
			  List<String> expectedList = expectedMapOfIDsAndApplications.get(expectedKey);
			  List<String> actualList = actualMapOfIDsAndApplications.get(expectedKey);
			  actualMapOfIDsAndApplications.remove(expectedKey);
			  if(!expectedList.equals(actualList)) {
				  compareMaps = false;
			  } 
		  } 
		  assertTrue(compareMaps);
	  } else {
		  Assert.assertNull(response.getString("[0].preferences[0].preferences"));
	  }
  }
  
  @Then("^I verify if response \"([^\"]*)\" be valid but has no preferences in each application$")
  public void verifyUserPreferencewithMultiplesApplicationsAndNoPreference(String assertion, DataTable arg2) throws Exception {
    JsonPath response = UtilsSteps.getResponse().then().extract().jsonPath();
    List<String> responseApplicationList = new ArrayList<String>();
    
    List<String> expectedApplicationList = new ArrayList<String>();
    expectedApplicationList = arg2.asList(String.class);

    if (assertion.equals("should")) { 	
    	int responseListSize = response.getList("").size();
    	for (int responseListCursor = 0; responseListCursor < responseListSize; responseListCursor++) {
    		responseApplicationList.add(response.getString("[" + responseListCursor + "]" + ".id"));
    	}
        
    	List<String> innerJoin = responseApplicationList;
    	innerJoin.retainAll(expectedApplicationList);
        
    	List<String> outerJoin = new ArrayList<String>();
    	outerJoin.addAll(responseApplicationList);
    	outerJoin.addAll(expectedApplicationList);
    	outerJoin.removeAll(innerJoin);
        
    	Assert.assertTrue(outerJoin.isEmpty());
    }
  }

  @Given("^I create (\\d+) user(?:s)?$")
  public void createUsers(int quantity) throws Exception {
    BUC7502testData.DeleteUser();
    BUC7502testData.DeleteUserRole();

    for (int sequence = 1; sequence <= quantity; sequence++) {
      BUC7502testData.DeleteUserDB(sequence);
    }

    String userId1 = BUC7502testData.CreateUser(1);
    BUC7502testData.GrantConnect(userId1);
    BUC7502testData.CreateUserInCWUser(userId1, getUsername());
    BUC7502testData.CreateUserRole(userId1, "BSS_DEFAULT_GROUP");
    BUC7502testData.CreateUserRole(userId1, "BSS_EDIT_DEFAULT_GROUP");
    BUC7502testData.CreateUserRole(userId1, "BSS_VIEW_DEFAULT_GROUP");
    BUC7502testData.VerifyCreate("CWUSER", "USERID", userId1);

    for (int sequence = 2; sequence <= quantity; sequence++) {
      Thread.sleep(1);
      String userId = BUC7502testData.CreateUser(sequence);
      BUC7502testData.GrantConnect(userId);
      BUC7502testData.CreateUserInCWUser(userId, getUsername());
      BUC7502testData.VerifyCreate("CWUSER", "USERID", userId);
    }
  }

  @When("^I create (\\d+) preference(?:s)? definition(?:s)? with default_value = \"([^\"]*)\"$")
  public void createPreferenceDefinition(int quantity, String value) throws Exception {
    BUC7502testData.DeleteDefUser();
    defId.clear();
    
    for (int sequence = 0; sequence < quantity; sequence++) {
      String concat = "def" + (sequence + 1);
      defId.add(concat);
      BUC7502testData.createPreference(value, concat);
      Thread.sleep(2);
      value = (sequence + 1) * 15.0 + "";
    }
  }

  @When("^the \"([^\"]*)\" = definition id parameter is used$")
  public void addParameters(String param, DataTable arg2) {
    List<Integer> values = arg2.asList(Integer.class);

    String concat = null;
    if (values.get(0) <= defId.size()) {
      concat = getDefId().get(values.get(0) - 1);
    } else {
      concat = "def" + values.get(0);
    }

    for (int sequence = 1; sequence < values.size(); sequence++) {
      if (values.get(sequence) <= defId.size()) {
        concat = concat + "," + getDefId().get(values.get(sequence) - 1);
      }
    }

    utilsSteps.addQueryParam(param, concat);
  }

  @When("^I delete any existing default user preference for \"([^\"]*)\"$")
  public void deleteAnyExistingDefaultUserPreferenceForUser(String userdefault) {
    utilsSteps.authorizationRequest("upadmin", "upadmin");
    utilsSteps.sendDELETERequestTo(userdefault + "/applicationPreference", getApplicationId(0));
  }

  @Then("^I create an user preference in applicationID for \"([^\"]*)\" and userRequest = \"([^\"]*)\"$")
  public void createDefaultUserPreferenceInApplicationIDForUser(String userAplicationId, String userRequest) throws Exception {
    UserPreference user1 = BUC7502testData.createUP(getApplicationId(0), getDefId());
    utilsSteps.authorizationRequest(userRequest, userRequest);
    utilsSteps.createPOSTRequestBodyObject(user1);
    utilsSteps.sendPOSTRequestTo(userAplicationId + "/applicationPreference/" + getApplicationId(0));
  }
  
  @Then("^I \"([^\"]*)\" be able to validate information provided about \"([^\"]*)\"$")
  public void validateInformationInResponseForApplication(String ability, String application) {
	  if(ability == "should") {
		  utilsSteps
		  	.getResponse()
		  .then()
		  	.extract().jsonPath();
	  }
  }

  public static List<String> getDefId() {
    return defId;
  }

  public static void setDefId(int index, String value) {
    BUC7502.defId.set(index, value);
  }

  public static String getApplicationId(int index) {
    return applicationId.get(index);
  }

  public static void setApplicationId(int index, String value) {
    BUC7502.applicationId.set(index, value);
  }

  public static void setUsername(String username) {
    BUC7502.username = username;
  }
}
