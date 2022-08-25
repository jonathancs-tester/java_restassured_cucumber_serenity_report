package ecm.standalone.cucumber.BUC7502;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import ecm.standalone.cucumber.utils.UtilsSteps;
import ecm.standalone.testData.BUC7502testData;
import ecm.standalone.userPreference.UserPreference;
import net.thucydides.core.annotations.Steps;

public class US7915 {
  @Steps
  UtilsSteps utilsSteps;

  @When("^I delete user preference in applicationID for \"([^\"]*)\" and userRequest = \"([^\"]*)\"$")
  public void deleteUserPreference(String userAplicationId, String userRequest) throws Exception {
    BUC7502.setUsername(userAplicationId);

    utilsSteps.authorizationRequest(userRequest, userRequest);
    utilsSteps.sendDELETERequestTo(userAplicationId + "/applicationPreference", BUC7502.getApplicationId(0));
  }

  @When("^I delete user preference in applicationID for \"([^\"]*)\" and userRequest = \"([^\"]*)\" with payload$")
  public void deleteDefaultUserPreferenceWithPayload(String userAplicationId, String userRequest, DataTable arg2) throws Exception {
    BUC7502.setUsername(userAplicationId);

    List<Integer> indexAlter = arg2.asList(Integer.class);
    UserPreference user1 = BUC7502testData.updateUP(BUC7502.getApplicationId(0), BUC7502.getDefId(), indexAlter);

    utilsSteps.authorizationRequest(userRequest, userRequest);
    utilsSteps.createPOSTRequestBodyObject(user1);
    utilsSteps.sendDELETERequestTo(userAplicationId + "/applicationPreference", BUC7502.getApplicationId(0));
  }
}
