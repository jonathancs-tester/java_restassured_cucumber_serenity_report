package ecm.standalone.utils;

import java.util.Calendar;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;
import com.standalone.testbase.TestBase;

import ecm.standalone.cucumber.BUC7502.BUC7502;
import ecm.standalone.models.Project;
import ecm.standalone.testData.BUC7503testData;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

public class Utils extends TestBase {

  public static String getEffectiveDate(int dayOffset, int hourOffset) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.DATE, dayOffset);
    cal.add(Calendar.HOUR, hourOffset);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    return dateFormat.format(cal.getTime());
  }
  
  public static String getEffectiveDateMin(int dayOffset, int hourOffset, int minute) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	cal.add(Calendar.DATE, dayOffset);
	cal.add(Calendar.HOUR, hourOffset);
	cal.add(Calendar.MINUTE, minute);

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	 
	return dateFormat.format(cal.getTime());
  }

  public static void deleteProjectasUpadmin(String projectId) {
    SerenityRest.given().auth().preemptive().basic("upadmin", "upadmin").contentType("application/json").log().all().pathParam("projectid", projectId).when().delete("/project/{projectid}");
  }
 
  @Title("Create a project")
  public static  void createProject(String projectId, String username, String password, String effectiveDate) {
    Project project = new Project();
    project.setId(projectId);
    project.setName(projectId);
    project.setState("DEF");
    project.setEffectiveDate(effectiveDate);

    SerenityRest
	    .given()
	    	.auth()
	    	.preemptive()
	    	.basic(username, password)
	    	.contentType("application/json")
	    	.body(project)
	    	.log()
	    	.all()
	    .when()
	      	.post("/project/")
	    .then()
	      	.log()
	      	.all()
	      	.statusCode(201);
  }

  public static String getEndpoint(String entityName) throws Exception {
    switch (entityName) {
    case "Composite Product Specification":
    case "Product Specification":
      return "/productSpecification";
    case "Product Offering":
    case "Group":
      return "/productOffering";
    case "Characteristic Specification":
      return "/characteristicSpecification";
    case "Service Candidate":
    	return "/serviceCandidate";
    case "Resource Facing Service Specification":
    	return "/resourceFacingServiceSpecification";
    case "Fulfillment Configuration Specification":
    	return "/fulfillmentConfigSpecification";
    case "Customer Facing Service Specification":
    	return "/customerFacingServiceSpecification";
    case "Product Category":
        return "/productCategory";
    case "Charging Configuration Specification":
        return "/chargingConfigurationSpec";
    case "Service Level Spec Consequence":
    	return "/serviceLevelSpecConsequence";
    case "Service Level Spec Applicability":
    	return "/serviceLevelSpecApplicability";
    case "Service Level Specification":
    	return "/serviceLevelSpecification";
    case "Service Level Objective":
    	return "/serviceLevelObjective";
    case "Key Quality Indicator":
    	return "/keyQualityIndicator";
    case "Key Performance Indicator":
    	return "/keyPerformanceIndicator";
    case "Resource Candidate":
    	return "/resourceCandidate";
    case "Physical Resource Spec":
    	return "/physicalResourceSpecification";
    case "Logical Resource Specification":
    	return "/logicalResourceSpecification";
    case "API Specification":
    	return "/apiSpecification";
    case "Price Specification":
    case "Charge Type":
	case "Charge Type CHAG":
	case "Charge Type ALWC":
	case "Charge Type COST":
	case "Charge Type DISC":
    	return "/priceSpecification";
    case "Association Type":
    	return "/associationType";
    case "Business Dates Model":
    	return "/businessDatesModel";
    case "Dimension":
    	return "/dimension";
    case "Dimension Specification":
    	return "/dimensionSpecification";
    case "Catalog Rules CR":
    case "Catalog Rules MDR":
    case "Catalog Rules":
    case "Catalog Rules Segment":
    	return "/rule";
    case "Hierarchy":
    	return "/productCategory";
    case "Context":
    	return "/contextSpecification";
    case "Project":
    	return "/project";
    case "Request":
    	return "/request";
    case "Resource Specification":
    	return "/resourceSpecification";
    case "User Preference":
      return "/"+BUC7502.getUsername() + "/applicationPreference";
    case "All User Preference":
      return "/"+BUC7502.getUsername();
    default:
      throw new Exception("Endpoint Not Found");
    }
  }

  public static Object getPayload(String entityName, String effectiveDate) throws Exception {
    switch (entityName) {
    case "Composite Product Specification":
        return BUC7503testData.compositeProductSpecification(effectiveDate);
    case "Product Specification":
      return BUC7503testData.productSpecification(effectiveDate);
    case "Price Specification":
    	return BUC7503testData.priceSpecification(effectiveDate);
    case "Association Type":
    	return BUC7503testData.associationType(effectiveDate);
    case "Dimension":
    	return BUC7503testData.dimension(effectiveDate);
    case "Dimension Specification":
    	return BUC7503testData.dimensionSpecification(effectiveDate);
    case "Catalog Rules":
    	return BUC7503testData.rule(effectiveDate);
    case "Catalog Rules Segment":
    	return BUC7503testData.ruleSegment(effectiveDate);
    case "Hierarchy":
    	return BUC7503testData.productCategory(effectiveDate);
    case "Context":
    	return BUC7503testData.contextSpecification(effectiveDate);
    case "Project":
    	return BUC7503testData.project(effectiveDate);
    case "Request":
    	return BUC7503testData.request(effectiveDate);
    case "Product Offering":
    case "Characteristic Specification":
    case "Fulfillment Configuration Specification":
    case "Customer Facing Service Specification":
    case "Product Category":
    case "Service Candidate":
    case "Resource Facing Service Specification":
    case "Charging Configuration Specification":
    case "Service Level Spec Consequence":
    case "Service Level Spec Applicability":
    case "Service Level Specification":
    case "Service Level Objective":
    case "Key Quality Indicator":
    case "Key Performance Indicator":
    case "Resource Candidate":
    case "Physical Resource Spec":
    case "Logical Resource Specification":
    case "API Specification":
    case "Business Dates Model":
    	return BUC7503testData.item(effectiveDate);
    default:
      throw new Exception("Payload Not Found");
    }
  }
}
