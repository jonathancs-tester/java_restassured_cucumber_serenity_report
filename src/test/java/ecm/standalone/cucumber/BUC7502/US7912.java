package ecm.standalone.cucumber.BUC7502;

import cucumber.api.java.en.When;

public class US7912 {

  @When("the preference definition is created for \"([^\"]*)\"$")
  public void changeUsername(String id) throws Exception {
    BUC7502.setUsername(id);
  }
}
