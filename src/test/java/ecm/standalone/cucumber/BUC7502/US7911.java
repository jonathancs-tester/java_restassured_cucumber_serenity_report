package ecm.standalone.cucumber.BUC7502;

import cucumber.api.java.en.When;

public class US7911 {

  @When("I remove defId")
  public void removeId() throws Exception {
    for (int sequence = 0; sequence < BUC7502.getDefId().size(); sequence++) {
      BUC7502.setDefId(sequence, null);
    }
  }

  @When("I change defId to \"([^\"]*)\"$")
  public void changeDefId(String id) throws Exception {
    BUC7502.setDefId(0, id);
  }

  @When("I change applicationId to \"([^\"]*)\"$")
  public void changeAppId(String id) throws Exception {
    BUC7502.setApplicationId(0, id);
  }
}
