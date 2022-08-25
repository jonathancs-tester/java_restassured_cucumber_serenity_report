package ecm.standalone.serenity.steps;

import org.junit.Assert;

import ecm.standalone.cucumber.utils.UtilsSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class BUC7504Steps {

  @Step("Verify expand=productOffering response")
  public void verifyExpandParamResponse(Response rResponse) {
    JsonPath response = rResponse.then().log().all().extract().jsonPath();

    int productSize = response.getList("[0].products").size();

    for (int i = 0; i < productSize; i++) {
      assert (response.getString("[0].products[" + i + "].product.versions") != null);
    }

  }

  @Step("POST Request")
  public void sendPOSTRequestToMakeTheAssociation(String catID, UtilsSteps object) {
    object.setResponse(
     object.getRequest()
      .when()
        .post("/productCategory/"+catID+"/product")
    );
  }
  
  
  @Step("Verify aggregated response")
  public void verifyAggregatedResponse(Response rResponse, int quantity) {
    if(rResponse.getStatusCode() == 403) 
      return;
    
    JsonPath response = rResponse.then().extract().jsonPath();
    
    int productSize = response.getList("[0].products").size();

    if (response.getList("[0].productCategoryList") != null) {
      int productCategoryListSize = response.getList("[0].productCategoryList").size();
      Assert.assertEquals(productCategoryListSize, 1);
      Assert.assertTrue(response.getString("[0].productCategoryList[0].products") == null);
    }

    Assert.assertEquals(productSize, quantity);
  }

  @Step("Verify Product list size")
  public void verifyProductListSize(int size, Response rResponse) {
    JsonPath response = rResponse.then().log().all().extract().jsonPath();

    int productSize = response.getList("[0].products").size();
    Assert.assertEquals(size, productSize);
  }

  @Step("Verify productCategoryId")
  public boolean verifyproductCategoryId(String categoryId, int poIndex, Response rResponse) throws NullPointerException {

    JsonPath response = rResponse.then().log().all().extract().jsonPath();
    if (response.getString("[0].products[" + poIndex + "].productCategoryAssociation") != null) {
      
      return (response.getString("[0].products[" + poIndex + "].productCategoryAssociation[0].productCategoryId").equals(categoryId));
    } else {
      return false;
    }

  }

  @Step("Verify productCategoryPath")
  public boolean verifyproductCategoryPath(String categoryPath, int poIndex, Response rResponse) throws NullPointerException {

    JsonPath response = rResponse.then().log().all().extract().jsonPath();

    if (response.getString("[0].products[" + poIndex + "].productCategoryAssociation") != null) {
      return (response.getString("[0].products[" + poIndex + "].productCategoryAssociation[0].productCategoryPath").equals(categoryPath));
    } else {
      return false;
    }

  }
}
