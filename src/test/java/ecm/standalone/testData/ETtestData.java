package ecm.standalone.testData;

import java.util.ArrayList;
import java.util.List;

import ecm.standalone.models.Version;
import ecm.standalone.models.VersionExpanded;
import ecm.standalone.utils.Utils;
import ecm.standalone.models.AssociationType;
import ecm.standalone.models.Characteristic;
import ecm.standalone.models.CharacteristicValue;
import ecm.standalone.models.CharacteristicVersion;
import ecm.standalone.models.ConditionRule;
import ecm.standalone.models.ConditionRuleAssignment;
import ecm.standalone.models.ContextSpecification;
import ecm.standalone.models.Dimension;
import ecm.standalone.models.DimensionSpecification;
import ecm.standalone.models.DimensionVersion;
import ecm.standalone.models.Group;
import ecm.standalone.models.Item;
import ecm.standalone.models.ItemVersion;
import ecm.standalone.models.Media;
import ecm.standalone.models.POAssociation;
import ecm.standalone.models.ParameterName;
import ecm.standalone.models.Parent;
import ecm.standalone.models.Price;
import ecm.standalone.models.PriceSpecification;
import ecm.standalone.models.ProductCategory;
import ecm.standalone.models.ProductSpecification;
import ecm.standalone.models.Project;
import ecm.standalone.models.PropertiesRule;
import ecm.standalone.models.Request;
import ecm.standalone.models.Rule;
import ecm.standalone.models.RuleAssignment;
import ecm.standalone.models.RuleAssociation;
import ecm.standalone.models.RuleSpaceSpec;
import ecm.standalone.models.RuleVersion;
import ecm.standalone.models.Selection;
import ecm.standalone.models.SelectionValue;
import ecm.standalone.models.ValidFor;
import ecm.standalone.models.Value;
import ecm.standalone.models.ValueAssociation;
import ecm.standalone.models.ValueTypeSpecification;

public class ETtestData {
	private static String projectId;	
	private static String resourceId;
	private static String apiSpecId;
	private static String resourceName;
	private static String catalogRuleId;
	private static String catalogRuleName;
	private static String catalogRuleIdTrue;
	private static String catalogRuleNameTrue;
	private static String productSpecificationID;
		
	public static void setup() {
		long randomID = System.currentTimeMillis();
		projectId = "ET_project_" + randomID;
		resourceId = "ET_res_" + randomID;
		apiSpecId = "ET_api_" + randomID;
		resourceName = "ET_res_" + randomID;
		catalogRuleId ="MDR_CatalogRule_" + randomID;
		catalogRuleName = "MDR CatalogRule Name " + randomID;
		catalogRuleIdTrue ="MDR_True_" + randomID;
		catalogRuleNameTrue = "MDR_True_" + randomID;
		productSpecificationID = "ET_PS_" + randomID;
	}
	
	public static String getProjectId() {
		return projectId;
	}

	public static void setProjectId(String projectId) {
		ETtestData.projectId = projectId;
	}
	
	public static String getProductSpecificationID() {
		return productSpecificationID;
	}

	public static void setProductSpecificationID(String productSpecificationID) {
		ETtestData.productSpecificationID = productSpecificationID;
	}

	public static String getResourceId() {
		return resourceId;
	}

	public static void setResourceId(String resourceId) {
		ETtestData.resourceId = resourceId;
	}

	public static String getResourceName() {
		return resourceName;
	}

	public static void setResourceName(String resourceName) {
		ETtestData.resourceName = resourceName;
	}

	public static String getApiSpecId() {
		return apiSpecId;
	}

	public static void setApiSpecId(String apiSpecId) {
		ETtestData.apiSpecId = apiSpecId;
	}
	
	public static String getCatalogRuleId() {
		return catalogRuleId;
	}

	public static void setCatalogRuleId(String catalogRuleId) {
		ETtestData.catalogRuleId = catalogRuleId;
	}

	public static String getCatalogRuleName() {
		return catalogRuleName;
	}

	public static void setCatalogRuleName(String catalogRuleName) {
		ETtestData.catalogRuleName = catalogRuleName;
	}
	
	public static String getCatalogRuleIdTrue() {
		return catalogRuleIdTrue;
	}

	public static void setCatalogRuleIdTrue(String catalogRuleIdTrue) {
		ETtestData.catalogRuleIdTrue = catalogRuleIdTrue;
	}

	public static String getCatalogRuleNameTrue() {
		return catalogRuleNameTrue;
	}

	public static void setCatalogRuleNameTrue(String catalogRuleNameTrue) {
		ETtestData.catalogRuleNameTrue = catalogRuleNameTrue;
	}

	public static void createProject(String username, String password, String effectiveDate) {
		setup();
		Utils.createProject(getProjectId(), username, password, effectiveDate);
	}

	public static Object getPayload(String resourceType, String effectiveDate, String type, String state, String endDate, String retireDate, String POId) {
		switch(resourceType) {
		    case "Catalog Rules CR":
		    	return ETtestData.ruleCR(effectiveDate);
		    case "Catalog Rules MDR - Composite False":
		    	return ETtestData.ruleMDRCompositeFalse(effectiveDate);
		    case "Catalog Rules MDR - Composite True":
		    	return ETtestData.ruleMDRCompositeTrue(effectiveDate);
		    case "Catalog Rules MDR Wrapper":
		    	return ETtestData.ruleMDRWrapper(effectiveDate);
		    case "Association Type":
		    	return ETtestData.associationType(effectiveDate);
		    case "Product Category":
		    	return ETtestData.productCategory(effectiveDate);
		    case "Context":
		    	return ETtestData.contextSpecification(effectiveDate);
		    case "Group":
		    	return ETtestData.group(effectiveDate);
		    case "Project state":
		    	return ETtestData.changeProjectState(state, effectiveDate);
		    case "Price Specification":
			case "Charge Type":
			case "Charge Type CHAG":
				return ETtestData.chargeType(effectiveDate, "CHAG");
			case "Charge Type ALWC":
				return ETtestData.chargeType(effectiveDate, "ALWC");
			case "Charge Type COST":
				return ETtestData.chargeType(effectiveDate, "COST");
			case "Charge Type FORMULA":
				return ETtestData.chargeType(effectiveDate, "FORMULA");
			case "Product Offering":
				return ETtestData.productOffering(effectiveDate, endDate);
			case "Product Offering with PS":
				return ETtestData.productOfferingWithPS(effectiveDate, getProductSpecificationID());
			case "Product Offering with Conditional Rule":
				return ETtestData.productOfferingConditionalRule(effectiveDate, type);
			case "Product Offering with Rule":
				return ETtestData.productOfferingRule(effectiveDate, type);
			case "Product Offering with Rule modified":
				return ETtestData.productOfferingRuleModify(effectiveDate, type);
			case "Product Offering with Characteristic":
				return ETtestData.createPOWithPOPandChar(effectiveDate);
			case "Product Offering Price":
				return ETtestData.createPOP(effectiveDate);
			case "API Specification":
				return ETtestData.apiSpecification(effectiveDate);
		    case "Composite Product Specification":
		        return ETtestData.compositeProductSpecification(effectiveDate);
		    case "Product Specification":
		      return ETtestData.productSpecification(effectiveDate);
		    case "Product Specification With ID":
			      return ETtestData.productSpecificationWithID(effectiveDate);
		    case "Dimension":
		    	return ETtestData.dimension(effectiveDate);
		    case "Dimension Specification":
		    	return ETtestData.dimensionSpecification(effectiveDate);
		    case "Catalog Rules":
		    	return ETtestData.rule(effectiveDate);
		    case "Catalog Rules Segment":
		    	return ETtestData.ruleSegment(effectiveDate);
		    case "Hierarchy":
		    	return ETtestData.productCategory(effectiveDate);
		    case "Request":
		    	return ETtestData.request(effectiveDate);
		    case "Retire Offer":
		    	return ETtestData.productOfferingSetRetire(effectiveDate, endDate, retireDate, POId);
		    case "Characteristic Specification":
		    case "Fulfillment Configuration Specification":
		    case "Customer Facing Service Specification":
		    case "Service Candidate":
		    case "Resource Specification":
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
		    	return ETtestData.item(effectiveDate);
			case "Business Dates Model":
				return ETtestData.businessDatesModel(effectiveDate);
		    default:
		    	return resourceType;
		}
    }
	
	public static Dimension  dimension(String effectiveDate) {	
		Dimension dimension = new Dimension();
		DimensionVersion version = new DimensionVersion();
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		dimensionSpecification.setId("simpleDimensionSpec");
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		
		version.setValidFor(validFor);
		version.setName(resourceName);
		version.setId(getResourceId());
		version.setState("DEF");
		version.setDimensionSpecification(dimensionSpecification);
		
		List<DimensionVersion> versions = new ArrayList<>();
		versions.add(version);
		
		dimension.setVersions(versions);
		dimension.setId(getResourceId());
		
		return dimension;
	}
	
	public static DimensionSpecification  dimensionSpecification(String effectiveDate) {	
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		dimensionSpecification.setId(resourceId);
		dimensionSpecification.setName(resourceName);
		dimensionSpecification.setValidFor(validFor);
		dimensionSpecification.setState("DEF");
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType("hierarchyDimension");
		
		return dimensionSpecification;
	}
	
	public static Rule  rule(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(validFor);
		version.setImplementationType("CR");
		version.setRuleType("O");
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		rule.setVersions(versions);
		rule.setId(resourceId);
		
		return rule;
	}
	
	public static Rule  ruleSegment(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(validFor);
		version.setImplementationType("CR");
		version.setRuleType("S");
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		rule.setVersions(versions);
		rule.setId(resourceId);
		
		return rule;
	}

	public static Request request(String effectiveDate) {
        Request request = new Request();
        if(getResourceId().contains("PLD")) {
        	request.setId(getResourceId());
        }
        request.setName(getResourceName());
        request.setType("RULE_PLM");
        request.setEffectiveDate(effectiveDate);
    	return request;
	}
	
	public static Item item(String effectiveDate) {
		Item productOffering = new Item();
		ItemVersion version = new ItemVersion();
		ValidFor validFor =  new ValidFor();
		
		validFor.setStartDateTime(effectiveDate);
		
		version.setId(getResourceId());
		version.setName(resourceName);
		version.setValidFor(validFor);
		
		List<Version> versions =  new ArrayList<>();
		versions.add(version);
		
		productOffering.setId(getResourceId());
		productOffering.setVersions(versions);
		
		return productOffering;
	}


	public static ProductSpecification  productSpecification (String effectiveDate) {	
		ProductSpecification ps = new ProductSpecification();
		ValidFor validFor = new ValidFor();
		Version version = new Version();	
		
		validFor.setStartDateTime(effectiveDate);
		version.setValidFor(validFor);
		version.setName(getResourceId());
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		
		ps.setProductNumber(getResourceId());
		ps.setVersions(versions);
		
		return ps;
	}
	
	public static ProductSpecification  productSpecificationWithID (String effectiveDate) {	
		ProductSpecification ps = new ProductSpecification();
		ValidFor validFor = new ValidFor();
		Version version = new Version();	
		
		validFor.setStartDateTime(effectiveDate);
		version.setValidFor(validFor);
		version.setName(getProductSpecificationID());
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		
		ps.setProductNumber(getProductSpecificationID());
		ps.setVersions(versions);
		
		return ps;
	}
	
	public static ProductSpecification  compositeProductSpecification (String effectiveDate) {	
		ProductSpecification ps = new ProductSpecification();
		ValidFor validFor = new ValidFor();
		VersionExpanded version = new VersionExpanded();
				
		validFor.setStartDateTime(effectiveDate);
		version.setValidFor(validFor);
		version.setName(getProductSpecificationID());
		version.setIsComposite(true);
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		
		ps.setProductNumber(getProductSpecificationID());
		ps.setVersions(versions);
		
		return ps;
	}
	
	private static Object businessDatesModel(String effectiveDate) {
		Item businessDatesModel = new Item();
		VersionExpanded version = new VersionExpanded();
		
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(new ValidFor(effectiveDate));
		version.setState("DEF");
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		businessDatesModel.setId(resourceId);
		businessDatesModel.setVersions(versions);
		
		return businessDatesModel;
	}

	private static Object apiSpecification(String effectiveDate) {
		Item apiSpecification = new Item();
		VersionExpanded version = new VersionExpanded();
		
		version.setId(apiSpecId);
		version.setName(resourceName);
		version.setValidFor(new ValidFor(effectiveDate));
		version.setState("DEF");
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		apiSpecification.setId(apiSpecId);
		apiSpecification.setVersions(versions);
		
		return apiSpecification;
	}

	private static Object productOffering(String effectiveDate, String endDate) {
		Item productOffering = new Item();
		VersionExpanded version = new VersionExpanded();
		ValidFor validFor = new ValidFor();
		
		version.setId(resourceId);
		version.setName(resourceName);
		validFor.setStartDateTime(effectiveDate);
		validFor.setEndDateTime(endDate);
		
		version.setValidFor(validFor);
		version.setState("DEF");
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		productOffering.setId(resourceId);
		productOffering.setVersions(versions);
		
		return productOffering;
	}
	
	private static Object productOfferingWithPS(String effectiveDate, String PSId) {
		Item bodyFirst = new Item();
		VersionExpanded versionFirst = new VersionExpanded();
		POAssociation associationFirst = new POAssociation();
		
		associationFirst.setId(PSId);
		associationFirst.setTargetSpecificationId(PSId);
		associationFirst.setTargetSpecificationType("ProductSpecification");
		associationFirst.setAssociationType("contains");
		associationFirst.setMaxQuantity(1);
		
		List<POAssociation> associations = new ArrayList<>();
		associations.add(associationFirst);
		
		versionFirst.setAssociations(associations);				
		versionFirst.setId(ETtestData.getResourceId());
		versionFirst.setName(ETtestData.getResourceName());
		versionFirst.setValidFor(new ValidFor(effectiveDate));
			
		List<Version> versions = new ArrayList<>();
		versions.add(versionFirst);
		bodyFirst.setId(ETtestData.getResourceId());
		bodyFirst.setVersions(versions);
		
		return bodyFirst;
	}
	
	private static Object productOfferingConditionalRule(String effectiveDate, String type) {
		Item productOffering = new Item();
		VersionExpanded version = new VersionExpanded();
		RuleAssociation rule = new RuleAssociation();
		ConditionRule conditionRule = new ConditionRule();
		
		rule.setid(catalogRuleId);
		
		conditionRule.setId(catalogRuleId);
		conditionRule.setName(catalogRuleName);
		conditionRule.setRuleType(type);
		conditionRule.setRuleLanguage("CR");
		conditionRule.setRuleAssociation(rule);
		
		List<ConditionRule> rules = new ArrayList<>();
		rules.add(conditionRule);
		
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(new ValidFor(effectiveDate));
		version.setState("DEF");
		version.setConditions(rules);
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		productOffering.setId(resourceId);
		productOffering.setVersions(versions);
		
		return productOffering;
	}
	
	private static Object productOfferingRule(String effectiveDate, String type) {
		Item productOffering = new Item();
		VersionExpanded version = new VersionExpanded();
		RuleAssociation rule = new RuleAssociation();
		Rule conditionRule = new Rule();	
		rule.setid("DefaultTrue");
		conditionRule.setId("DefaultTrue");
		conditionRule.setName("DefaultTrue");
		conditionRule.setRuleType(type);
		conditionRule.setType(type);
		conditionRule.setSequence("-");
		conditionRule.setRuleLanguage("CR");
		conditionRule.setRule(rule);	
		
		List<Rule> rules = new ArrayList<>();
		rules.add(conditionRule);
		
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(new ValidFor(effectiveDate));
		version.setState("DEF");
		version.setRules(rules);
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		productOffering.setId(resourceId);
		productOffering.setVersions(versions);
		
		return productOffering;
	}
	
	private static Object productOfferingRuleModify(String effectiveDate, String type) {
		Item productOffering = new Item();
		VersionExpanded version = new VersionExpanded();
		RuleAssociation rule = new RuleAssociation();
		Rule conditionRule = new Rule();
		
		rule.setid("DefaultTrue");
		conditionRule.setId("DefaultTrue");
		conditionRule.setName("name modified");
		conditionRule.setRuleType(type);
		conditionRule.setType(type);
		conditionRule.setSequence("-");
		conditionRule.setRuleLanguage("CR");
		conditionRule.setRule(rule);
		
		List<Rule> rules = new ArrayList<>();
		rules.add(conditionRule);
		
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(new ValidFor(effectiveDate));
		version.setState("DEF");
		version.setRules(rules);
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		productOffering.setId(resourceId);
		productOffering.setVersions(versions);
		
		return productOffering;
	}
	
	private static PriceSpecification chargeType(String effectiveDate, String chargeTypeType) {
		PriceSpecification chargeType = new PriceSpecification();
		Price price = new Price();
		
		VersionExpanded version = new VersionExpanded();
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(new ValidFor(effectiveDate));
		version.setState("DEF");
		version.setPopsType("CHAG");
		version.setFrequency("M");
		
		if (chargeTypeType == "FORMULA") {
			Characteristic characteristic = new Characteristic();
			CharacteristicVersion versionCharacter = new CharacteristicVersion();
			CharacteristicValue characteristicValue = new CharacteristicValue();
			
			characteristicValue.setValue("band_method");
			characteristicValue.setDisplayValue("band_method");
			characteristicValue.setIsDefault(true);
			characteristicValue.setValueType("band_method");
			characteristicValue.setValidFor(new ValidFor(effectiveDate));
			
			List<CharacteristicValue> characteristicValues = new ArrayList<>();
			characteristicValues.add(characteristicValue);
			
			versionCharacter.setCharacteristicValue(characteristicValues);
			versionCharacter.setId("ecmPriceAmout");
			versionCharacter.setValueType("Attribute Type");
			versionCharacter.setValidFor(new ValidFor(effectiveDate));
			versionCharacter.setDerivationFormula("band_method");
			List<CharacteristicVersion> characteristicVersions = new ArrayList<>();
			characteristicVersions.add(versionCharacter);
			
			characteristic.setId("ecmPriceAmout");
			characteristic.setVersions(characteristicVersions);
			
			List<Characteristic> characteristics = new ArrayList<>();
			characteristics.add(characteristic);
			
			version.setCharacteristics(characteristics);
		
		}else{
			price.setAmount("50");
			version.setPrice(price);
		}
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		chargeType.setId(resourceId);
		chargeType.setVersions(versions);
		
		return chargeType;
	}

	private static Project changeProjectState(String state, String effectiveDate) {
		Project project = new Project();
		project.setId(projectId);
		project.setName(projectId);
		project.setState(state);
		project.setEffectiveDate(effectiveDate);
		return project;
	}
	
	private static Object group(String effectiveDate) {
		Item productOffering = new Item();
		VersionExpanded version = new VersionExpanded();
		Group group = new Group();
		group.setId("group1");
		
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(new ValidFor(effectiveDate));
		version.setState("DEF");
		List<Group> groups = new ArrayList<>();
		groups.add(group);
		version.setGroups(groups);

		List<Version> versions = new ArrayList<>();
		versions.add(version);
		productOffering.setId(resourceId);
		productOffering.setVersions(versions);
		
		return productOffering;
	}

	public static Rule ruleCR(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		version.setId(resourceId);
		version.setName(resourceName);
		version.setValidFor(validFor);
		version.setImplementationType("CR");
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		rule.setVersions(versions);
		rule.setId(resourceId);
		
		return rule;
	}
	
	public static Rule ruleMDRCompositeFalse(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		PropertiesRule properties = new PropertiesRule();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		properties.setId("isComposite");
		properties.setValue("false");
		
		version.setId(catalogRuleId);
		version.setName(catalogRuleName);
		version.setValidFor(validFor);
		version.setRuleType("S");
		version.setProperties(properties);
		version.setImplementationType("MDR");
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		ruleSpaceSpec.setId("cwt_orderItemActionRuleSpace");
		
		rule.setVersions(versions);
		rule.setId(catalogRuleId);
		rule.setRuleSpaceSpec(ruleSpaceSpec);
		
		return rule;
	}
	
	public static Rule ruleMDRWrapper(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		PropertiesRule properties = new PropertiesRule();
		ParameterName parameterName = new ParameterName();
		SelectionValue selectionValue = new SelectionValue();
		Selection selection = new Selection();
		Parent parent = new Parent();
		ValueAssociation valueassociation = new ValueAssociation();
		Value value = new Value();
		ConditionRuleAssignment condition = new ConditionRuleAssignment();
		RuleAssignment ruleAssignment = new RuleAssignment();
		
		ValidFor validFor = new ValidFor();
		validFor.setStartDateTime(effectiveDate);
		
		valueassociation.setName("price");
		valueassociation.setDisplayValue("1");
		
		List<ValueAssociation> values = new ArrayList<>();
		values.add(valueassociation);
		
		value.setValues(values);
		value.setExclude(true);
		
		parent.setId(catalogRuleId);
		parent.setType("rule");
		
		parameterName.setId("AUTO_ruleAttr_simple");
		
		selectionValue.setParameterName(parameterName);
		selectionValue.setValue("KD2|KD6");
		selectionValue.setDisplayValue("Nokia resources|Motorola Resources");
		selectionValue.setType("Simple");
		
		List<SelectionValue> selectionValues = new ArrayList<>();
		selectionValues.add(selectionValue);
		
		selection.setId(catalogRuleId);
		selection.setSelectionValues(selectionValues);
		
		condition.setConditionType("selection");
		condition.setSelection(selection);
		
		ruleAssignment.setId(catalogRuleId);
		ruleAssignment.setName(catalogRuleName);
		ruleAssignment.setState("DEF");
		ruleAssignment.setValidFor(validFor);
		ruleAssignment.setParent(parent);
		ruleAssignment.setCondition(condition);
		ruleAssignment.setValue(value);
		
		List<RuleAssignment> ruleAssignments = new ArrayList<>();
		ruleAssignments.add(ruleAssignment);
 		
		properties.setId("isComposite");
		properties.setValue("false");
	
		version.setId("wrapper_MS");
		version.setName("wrapper_MS");
		version.setValidFor(validFor);
		version.setImplementationType("MDR");
		version.setRuleType("S");
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		ruleSpaceSpec.setId("rmMarketSegment");
		
		rule.setVersions(versions);
		rule.setId("wrapper_MS");
		rule.setRuleSpaceSpec(ruleSpaceSpec);
		
		return rule;
	}
	
	public static Rule ruleMDRCompositeTrue(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		PropertiesRule properties = new PropertiesRule();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		properties.setId("isComposite");
		properties.setValue("true");
		
		version.setId(catalogRuleIdTrue);
		version.setName(catalogRuleNameTrue);
		version.setValidFor(validFor);
		version.setRuleType("S");
		version.setProperties(properties);
		version.setImplementationType("MDR");
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		ruleSpaceSpec.setId("cwt_orderItemActionRuleSpace");
		
		rule.setVersions(versions);
		rule.setId(catalogRuleIdTrue);
		rule.setRuleSpaceSpec(ruleSpaceSpec);
			
		return rule;
	}
	
	public static AssociationType associationType(String effectiveDate) {
		AssociationType associationType = new AssociationType();
		ValidFor validFor = new ValidFor(effectiveDate); 
		associationType.setId(resourceId);
		associationType.setName(resourceName);
		associationType.setState("DEF");
		associationType.setValidFor(validFor);
		associationType.setCategory("Attachment");
		
		return associationType;
	}

	public static ProductCategory productCategory(String effectiveDate) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(resourceId);
		productCategory.setName(resourceName);
		productCategory.setValidFor(new ValidFor(effectiveDate));
		
		return productCategory;
	}
	
	public static ContextSpecification contextSpecification(String effectiveDate) {
		ContextSpecification contextSpecification = new ContextSpecification();
		contextSpecification.setId(resourceId);
		contextSpecification.setName(resourceName);
		return contextSpecification;
	}
	
	public static Item createPOWithPOPandChar(String effectiveDate){
	    Item productOffering = new Item();
	    VersionExpanded version = new VersionExpanded();
	    ValidFor validFor = new ValidFor();
	    Media media = new Media();
	    validFor.setStartDateTime(effectiveDate);

	    media.setId("PH");
	    media.setName("PH");
	    media.setImageType("png");
	    media.setImageSize("N");
	    media.setImageCode("PH");

	    List<Media> medias = new ArrayList<>();
	    medias.add(media);

	    CharacteristicVersion versionCharacteristics = new CharacteristicVersion();
		versionCharacteristics.setId("test");
		versionCharacteristics.setName("test");
		versionCharacteristics.setType("pscmUserAttribute");
		versionCharacteristics.setValueType("String");
		versionCharacteristics.setMaxCardinality("1");
		versionCharacteristics.setSequence(7);
		versionCharacteristics.setValidFor(new ValidFor(effectiveDate));
		List<CharacteristicVersion> associationsCharacteristics = new ArrayList<>();
		associationsCharacteristics.add(versionCharacteristics);
		
		Characteristic characteristics = new Characteristic();
		characteristics.setId("test");
		characteristics.setVersions(associationsCharacteristics);

		List<Characteristic> listCharacteristics = new ArrayList<>();
		listCharacteristics.add(characteristics);

	    POAssociation association = new POAssociation();
		association.setId(getProductSpecificationID());
		association.setTargetSpecificationId(getProductSpecificationID());
		association.setTargetSpecificationType("ProductSpecification");
		association.setTargetType("Product");
		association.setAssociationType("contains");
		
		List<POAssociation> associations = new ArrayList<>();
		associations.add(association);
		
		version.setAssociations(associations);
		
	    version.setId(resourceId);
	    version.setName(resourceName);
	    version.setValidFor(validFor);
	    version.setMedia(medias);
	    version.setCharacteristics(listCharacteristics);

	    List<Version> versions = new ArrayList<>();
	    versions.add(version);

	    productOffering.setId(resourceId);
	    productOffering.setVersions(versions);

	    return productOffering;
	  }
	
	public static Item createPOP(String effectiveDate){
	    Item productOfferingPrice = new Item();
	    VersionExpanded version = new VersionExpanded();
	    ValidFor validFor = new ValidFor();
	    Characteristic characteristType = new Characteristic();
	    ValueTypeSpecification valueTypeSpecification = new ValueTypeSpecification();
	    validFor.setStartDateTime(effectiveDate);
	    
	    valueTypeSpecification.setId("booleanAttribute");

	    CharacteristicVersion characteristicTypeVersion = new CharacteristicVersion();
	    characteristicTypeVersion.setId("reusePricingRule");
	    characteristicTypeVersion.setName("reusePricingRule");
	    characteristicTypeVersion.setValue("0");
	    characteristicTypeVersion.setValueTypeSpecification(valueTypeSpecification);
	    characteristicTypeVersion.setValueType("Boolean");

	    List<CharacteristicVersion> characteristicTypeVersions = new ArrayList<>();
	    characteristicTypeVersions.add(characteristicTypeVersion);

	    characteristType.setId("reusePricingRule");
	    characteristType.setVersions(characteristicTypeVersions);

	    List<Characteristic> characteristics = new ArrayList<>();
	    characteristics.add(characteristType);

	    version.setId("POPid");
	    version.setName("POPid");
	    version.setValidFor(validFor);
	    version.setPlaId("base_PriceSpecification");
	    version.setCharacteristics(characteristics);

	    List<Version> versions = new ArrayList<>();
	    versions.add(version);

	    productOfferingPrice.setId("POPid");
	    productOfferingPrice.setVersions(versions);

	    return productOfferingPrice;
	  }
	
	private static Object productOfferingSetRetire(String effectiveDate, String endDate, String retireDate, String POId) {
		Item productOffering = new Item();
		VersionExpanded version = new VersionExpanded();
		ValidFor validfor = new ValidFor();

		validfor.setEndDateTime(endDate);
		validfor.setRetireDateTime(retireDate);
		
		version.setId(POId);
		version.setName(resourceName);
		version.setDescription("PO with retire date");
		version.setValidFor(validfor);
		version.setState("DEF");
		version.setSellIndicator(true);
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		productOffering.setId(POId);
		productOffering.setVersions(versions);
		
		return productOffering;
	}
	
	public static String convertOperationType(String operationType) {
		switch(operationType) {
		case "post":
			return "Add";
		case "put":
			return "Upd";
		case "delete":
			return "Del";
		case "get":
			return "Read";
		default:
			return operationType;
		}
	}

	public static String convertResourceType(String resourceType) {
		switch(resourceType) {
			case "Catalog Rules CR":
			case "Catalog Rules MDR":
				return "Rule";
			case "Product Category":
				return "Hierarchy";
			case "Context":
				return "Context";
			case "Group":
				return "ProductOffering";
			case "Project":
				return "Project";
			case "Charge Type":
			case "Charge Type CHAG":
			case "Charge Type ALWC":
			case "Charge Type COST":
			case "Charge Type DISC":
				return "Charge Type";
			case "API Specification":
				return "APISpecification";
			case "Customer Facing Service Specification":
				return "CustomerFacingServiceSpec";
			case "Characteristic Specification":
				return "CharacteristicSpecification";
			case "Charging Configuration Specification":
				return "ChargingConfigurationSpec";
			case "Composite Product Specification":
				return "ProductSpecification";
			case "Fulfillment Configuration Specification":
				return "FulfillmentConfigSpec";
			case "Key Performance Indicator":
				return "KeyPerformanceIndicator";
			case "Key Quality Indicator":
				return "KeyQualityIndicator";
			case "Logical Resource Specification":
				return "LogicalResourceSpec";
			case "Physical Resource Spec":                  
				return "PhysicalResourceSpec";
			case "Product Offering":
				return "ProductOffering";
			case "Product Specification":
				return "ProductSpecification";
			case "Resource Candidate":
				return "ResourceCandidate";
			case "Resource Specification":
				return "ResourceSpecification";
			case "Resource Facing Service Specification":
				return "ResourceFacingServiceSpec";
			case "Service Candidate":
				return "ServiceCandidate";
			case "Service Level Objective":
				return "ServiceLevelObjective";
			case "Service Level Specification":
				return "ServiceLevelSpecification";
			case "Service Level Spec Applicability":
				return "ServiceLevelSpecApplicability";
			case "Service Level Spec Consequence":
				return "ServiceLevelSpecConsequence";
			default:
				return resourceType;
		}
	}
}
