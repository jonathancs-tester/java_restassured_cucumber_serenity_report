package ecm.standalone.cucumber.BUC7502;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import ecm.standalone.cucumber.utils.UtilsSteps;
import ecm.standalone.testData.BUC7502testData;
import ecm.standalone.userPreference.UserPreference;
import net.thucydides.core.annotations.Steps;

public class US7914 {
  @Steps
  UtilsSteps utilsSteps;

  @When("^I update user preference in applicationID for \"([^\"]*)\" and userRequest = \"([^\"]*)\" with put$")
  public void updateDefaultUserPreferenceWithPut(String userAplicationId, String userRequest, DataTable arg2) throws Exception {
    BUC7502.setUsername(userAplicationId);

    List<Integer> indexAlter = arg2.asList(Integer.class);
    UserPreference user1 = BUC7502testData.updateUP(BUC7502.getApplicationId(0), BUC7502.getDefId(), indexAlter);

    utilsSteps.authorizationRequest(userRequest, userRequest);
    utilsSteps.createPOSTRequestBodyObject(user1);
    utilsSteps.sendPUTRequestTo(userAplicationId + "/applicationPreference", BUC7502.getApplicationId(0));
  }
}
