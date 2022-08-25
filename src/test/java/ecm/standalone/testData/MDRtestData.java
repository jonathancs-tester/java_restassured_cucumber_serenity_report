package ecm.standalone.testData;

import java.util.ArrayList;
import java.util.List;
import ecm.standalone.models.Version;
import ecm.standalone.utils.Utils;
import ecm.standalone.models.AssociationType;
import ecm.standalone.models.CharacteristicDimension;
import ecm.standalone.models.ConditionRuleAssignment;
import ecm.standalone.models.ContextSpecification;
import ecm.standalone.models.Dimension;
import ecm.standalone.models.DimensionElement;
import ecm.standalone.models.DimensionSpecification;
import ecm.standalone.models.DimensionVersion;
import ecm.standalone.models.Item;
import ecm.standalone.models.ItemVersion;
import ecm.standalone.models.ParameterName;
import ecm.standalone.models.Parent;
import ecm.standalone.models.PriceSpecification;
import ecm.standalone.models.ProductCategory;
import ecm.standalone.models.ProductSpecification;
import ecm.standalone.models.Project;
import ecm.standalone.models.PropertiesRule;
import ecm.standalone.models.Request;
import ecm.standalone.models.Rule;
import ecm.standalone.models.RuleAssignment;
import ecm.standalone.models.RuleSpaceSpec;
import ecm.standalone.models.RuleVersion;
import ecm.standalone.models.Selection;
import ecm.standalone.models.SelectionValue;
import ecm.standalone.models.ValidFor;
import ecm.standalone.models.Value;
import ecm.standalone.models.ValueAssociation;
import ecm.standalone.models.ValueDimensionElement;
import ecm.standalone.models.ValueTypeSpecification;

public class MDRtestData {
	private static String projectId;	
	private static String dimensionSpecId;
	private static String dimensionSpecName;
	private static String dimensionId;
	private static String dimensionName;
	private static String ruleSpaceSpecId;
	private static String ruleSpaceSpecName;
	private static String catalogRuleId;
	private static String catalogRuleName;
	private static String ruleAssignmentId;
	private static String ruleAssignmentName;
	private static String ruleSelectionId;

	public static void setup() {
		long randomID = System.currentTimeMillis();
		projectId = "MDR_project_" + randomID;
		dimensionId = "MDR_Dimension_" + randomID;
		dimensionName = "MDR Dimension Name " + randomID;
		dimensionSpecId ="MDR_DimensionSpec_" + randomID;
		dimensionSpecName = "MDR DimensionSpec Name " + randomID;
		ruleSpaceSpecId ="MDR_RuleSpaceSpec_" + randomID;
		ruleSpaceSpecName = "MDR RuleSpaceSpec Name " + randomID;
		catalogRuleId ="MDR_CatalogRule_" + randomID;
		catalogRuleName = "MDR CatalogRule Name " + randomID;
		ruleAssignmentId ="MDR_RuleAssignment_" + randomID;
		ruleAssignmentName = "MDR RuleAssignment Name " + randomID;
		ruleSelectionId ="MDR_RuleSelection_" + randomID;
	}

	public static String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projId) {
		projectId = projId;
	}
	
	public static String getDimensionSpecId() {
		return dimensionSpecId;
	}

	public static void setDimensionSpecId(String dimensionSpecId) {
		MDRtestData.dimensionSpecId = dimensionSpecId;
	}

	public static String getDimensionSpecName() {
		return dimensionSpecName;
	}

	public static void setDimensionSpecName(String dimensionSpecName) {
		MDRtestData.dimensionSpecName = dimensionSpecName;
	}

	public static String getDimensionId() {
		return dimensionId;
	}

	public static void setDimensionId(String dimensionId) {
		MDRtestData.dimensionId = dimensionId;
	}

	public static String getDimensionName() {
		return dimensionName;
	}

	public static void setDimensionName(String dimensionName) {
		MDRtestData.dimensionName = dimensionName;
	}
	
	public static String getRuleSpaceSpecId() {
		return ruleSpaceSpecId;
	}

	public static void setRuleSpaceSpecId(String ruleSpaceSpecId) {
		MDRtestData.ruleSpaceSpecId = ruleSpaceSpecId;
	}

	public static String getRuleSpaceSpecName() {
		return ruleSpaceSpecName;
	}

	public static void setRuleSpaceSpecName(String ruleSpaceSpecName) {
		MDRtestData.ruleSpaceSpecName = ruleSpaceSpecName;
	}

	public static String getCatalogRuleId() {
		return catalogRuleId;
	}

	public static void setCatalogRuleId(String catalogRuleId) {
		MDRtestData.catalogRuleId = catalogRuleId;
	}

	public static String getCatalogRuleName() {
		return catalogRuleName;
	}

	public static void setCatalogRuleName(String catalogRuleName) {
		MDRtestData.catalogRuleName = catalogRuleName;
	}
	
	public static String getRuleAssignmentId() {
		return ruleAssignmentId;
	}

	public static void setRuleAssignmentId(String ruleAssignmentId) {
		MDRtestData.ruleAssignmentId = ruleAssignmentId;
	}

	public static String getRuleAssignmentName() {
		return ruleAssignmentName;
	}

	public static void setRuleAssignmentName(String ruleAssignmentName) {
		MDRtestData.ruleAssignmentName = ruleAssignmentName;
	}
	
	public static String getRuleSelectionId() {
		return ruleSelectionId;
	}

	public static void setRuleSelectionId(String ruleSelectionId) {
		MDRtestData.ruleSelectionId = ruleSelectionId;
	}

	public static void createProject(String username, String password, String effectiveDate) {
		setup();
		Utils.createProject(getProjectId(), username, password, effectiveDate);
	}
	
	 public static Object getPayload(String entityName, String effectiveDate, String typeDimensionSpec, String dimensionId, String dimensionSpecId) throws Exception {
		    switch (entityName) {
		    case "Product Specification":
		      return MDRtestData.productSpecification(effectiveDate);
		    case "Price Specification":
		    	return MDRtestData.priceSpecification(effectiveDate);
		    case "Association Type":
		    	return MDRtestData.associationType(effectiveDate);
		    case "Dimension":
		    	return MDRtestData.dimension(effectiveDate, typeDimensionSpec);
		    case "Dimension Version":
		    	return MDRtestData.dimensionVersion(effectiveDate, typeDimensionSpec, dimensionId, dimensionSpecId);
		    case "Dimension without Element":
		    	return MDRtestData.dimensionWithoutElement(effectiveDate, typeDimensionSpec);
		    case "Dimension wrong Dimension Spec element":
		    	return MDRtestData.dimensionWrongElement(effectiveDate, typeDimensionSpec);
		    case "Dimension wrong Dimension Spec dimSpec":
		    	return MDRtestData.dimensionWrongDimSpec(effectiveDate, typeDimensionSpec);
		    case "Dimension updated":
		    	return MDRtestData.dimensionUpdated(effectiveDate, typeDimensionSpec);
		    case "Dimension Element":
		    	return MDRtestData.dimensionElement(effectiveDate);
		    case "Dimension Element updated":
		    	return MDRtestData.dimensionElementUpdate(effectiveDate);
		    case "Dimension Specification":
		    	return MDRtestData.dimensionSpecification(effectiveDate, typeDimensionSpec);
		    case "Dimension Specification Version":
		    	return MDRtestData.dimensionSpecificationVersion(effectiveDate, typeDimensionSpec, dimensionSpecId);
		    case "Catalog Rules MDR":
		    	return MDRtestData.ruleMDR(effectiveDate);
		    case "Catalog Rules MDR updated":
		    	return MDRtestData.ruleMDRUpdate(effectiveDate);
		    case "Catalog Rules MDR with Rule Assignment":
		    	return MDRtestData.ruleMDRAssignment(effectiveDate);
		    case "Catalog Rules MDR with Rule Assignment updated":
		    	return MDRtestData.ruleMDRAssignmentUpdate(effectiveDate);
		    case "Rule Space Specification":
		    	return MDRtestData.ruleSpaceSpec(effectiveDate);
		    case "Rule Space Specification updated":
		    	return MDRtestData.ruleSpaceSpecUpdate(effectiveDate);
		    case "Catalog Rules Segment":
		    	return MDRtestData.ruleSegment(effectiveDate);
		    case "Rule Assignment":
		    	return MDRtestData.ruleAssignment(effectiveDate);
		    case "Rule Assignment updated":
		    	return MDRtestData.ruleAssignmentUpdate(effectiveDate);
		    case "Hierarchy":
		    	return MDRtestData.productCategory(effectiveDate);
		    case "Context":
		    	return MDRtestData.contextSpecification(effectiveDate);
		    case "Project":
		    	return MDRtestData.project(effectiveDate);
		    case "Request":
		    	return MDRtestData.request(effectiveDate);
		    default:
		      throw new Exception("Payload Not Found");
		    }
		  }
	
	public static ProductSpecification  productSpecification (String effectiveDate) {	
		ProductSpecification ps = new ProductSpecification();
		ValidFor validFor = new ValidFor();
		Version version = new Version();
		
		String psDate = effectiveDate;
		
		validFor.setStartDateTime(psDate);
		version.setValidFor(validFor);
		version.setName(getDimensionName());
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		
		ps.setProductNumber(getDimensionId());
		ps.setVersions(versions);
		
		return ps;
	}
	
	public static PriceSpecification  priceSpecification (String effectiveDate) {	
		PriceSpecification chargeType = new PriceSpecification();
		ValidFor validFor = new ValidFor();
		Version version = new Version();
		
		String chargeTypeDate = effectiveDate;
		
		validFor.setStartDateTime(chargeTypeDate);
		version.setValidFor(validFor);
		version.setName(getDimensionName());
		version.setFrequency("M");
		version.setPopsType("CHAG");
		version.setState("DEF");
		version.setId(getDimensionId());
		
		List<Version> versions = new ArrayList<>();
		versions.add(version);
		
		chargeType.setId(getDimensionId());
		chargeType.setVersions(versions);
		
		return chargeType;
	}
	
	public static RuleSpaceSpec ruleSpaceSpec(String effectiveDate) {	
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		Dimension dimension = new Dimension();
		ValueTypeSpecification valueTypeSpecification = new ValueTypeSpecification();
		
		valueTypeSpecification.setId("AUTO_attr_simple");
		valueTypeSpecification.setHref("/ecm/ecm/CatalogManagement/v2/characteristicValueSpecification/AUTO_attr_simple");
		
		dimension.setId("AUTO_ruleAttr_simple");
		dimension.setName("AUTO_ruleAttr_simple");
		dimension.setValueTypeSpec(valueTypeSpecification);
		dimension.setSequence(2);
		dimension.setType("infoModelSystemAttribute");
		
		List<Dimension> dimensions = new ArrayList<>();
		dimensions.add(dimension);
		
		ValidFor validFor = new ValidFor();
		validFor.setStartDateTime(effectiveDate);
		
		ruleSpaceSpec.setId(ruleSpaceSpecId);
		ruleSpaceSpec.setName(ruleSpaceSpecName);
		ruleSpaceSpec.setState("DEF");
		ruleSpaceSpec.setValidFor(validFor);
		ruleSpaceSpec.setDimensions(dimensions);
			
		return ruleSpaceSpec;
	}
	
	public static RuleSpaceSpec ruleSpaceSpecUpdate(String effectiveDate) {	
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		Dimension dimension = new Dimension();
		Dimension dimension2 = new Dimension();
		ValueTypeSpecification valueTypeSpecification = new ValueTypeSpecification();
		
		valueTypeSpecification.setId("AUTO_attr_simple");
		valueTypeSpecification.setHref("/ecm/ecm/CatalogManagement/v2/characteristicValueSpecification/AUTO_attr_simple");
		
		dimension.setId("AUTO_ruleAttr_simple");
		dimension.setName("AUTO_ruleAttr_simple");
		dimension.setValueTypeSpec(valueTypeSpecification);
		dimension.setSequence(2);
		dimension.setType("infoModelSystemAttribute");
		
		dimension2.setId("description");
		dimension2.setName("Description");
		dimension2.setValueTypeSpec(valueTypeSpecification);
		dimension2.setSequence(3);
		dimension2.setType("infoModelSystemAttribute");
		
		List<Dimension> dimensions = new ArrayList<>();
		dimensions.add(dimension);
		dimensions.add(dimension2);
		
		ValidFor validFor = new ValidFor();
		validFor.setStartDateTime(effectiveDate);
		
		ruleSpaceSpec.setId(ruleSpaceSpecId);
		ruleSpaceSpec.setName(ruleSpaceSpecName);
		ruleSpaceSpec.setState("DEF");
		ruleSpaceSpec.setValidFor(validFor);
			
		return ruleSpaceSpec;
	}
	
	public static Rule ruleMDR(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		PropertiesRule properties = new PropertiesRule();
		
		properties.setId("isComposite");
		properties.setValue("false");
		
		ValidFor validFor = new ValidFor();
		validFor.setStartDateTime(effectiveDate);
		
		version.setId(catalogRuleId);
		version.setName(catalogRuleName);
		version.setValidFor(validFor);
		version.setImplementationType("MDR");
		version.setRuleType("S");
		version.setProperties(properties);
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		ruleSpaceSpec.setId(MDRtestData.getRuleSpaceSpecId());
		ruleSpaceSpec.setHref("/ecm/ecm/CatalogManagement/v2/ruleSpaceSpecification/"+MDRtestData.getRuleSpaceSpecId());
		
		rule.setVersions(versions);
		rule.setId(catalogRuleId);
		rule.setRuleSpaceSpec(ruleSpaceSpec);
		
		return rule;
	}
	
	public static Rule ruleMDRUpdate(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		PropertiesRule properties = new PropertiesRule();
		
		properties.setId("isComposite");
		properties.setValue("true");
		
		ValidFor validFor = new ValidFor();
		validFor.setStartDateTime(effectiveDate);
		
		version.setId(catalogRuleId);
		version.setName(catalogRuleName);
		version.setValidFor(validFor);
		version.setImplementationType("MDR");
		version.setRuleType("S");
		version.setProperties(properties);
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		ruleSpaceSpec.setId(MDRtestData.getRuleSpaceSpecId());
		
		rule.setVersions(versions);
		rule.setId(catalogRuleId);
		rule.setRuleSpaceSpec(ruleSpaceSpec);
		
		return rule;
	}
	
	public static Rule ruleMDRAssignment(String effectiveDate) {	
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
		
		selection.setId(ruleSelectionId);
		selection.setSelectionValues(selectionValues);
		
		condition.setConditionType("selection");
		condition.setSelection(selection);
		
		ruleAssignment.setId(ruleAssignmentId);
		ruleAssignment.setName(ruleAssignmentName);
		ruleAssignment.setState("DEF");
		ruleAssignment.setValidFor(validFor);
		ruleAssignment.setParent(parent);
		ruleAssignment.setCondition(condition);
		ruleAssignment.setValue(value);
		
		List<RuleAssignment> ruleAssignments = new ArrayList<>();
		ruleAssignments.add(ruleAssignment);
 		
		properties.setId("isComposite");
		properties.setValue("false");
		
		version.setId(catalogRuleId);
		version.setName(catalogRuleName);
		version.setValidFor(validFor);
		version.setImplementationType("MDR");
		version.setRuleType("S");
		version.setRuleAssignments(ruleAssignments);
		version.setProperties(properties);
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		ruleSpaceSpec.setId(MDRtestData.getRuleSpaceSpecId());
		
		rule.setVersions(versions);
		rule.setId(catalogRuleId);
		rule.setRuleSpaceSpec(ruleSpaceSpec);
		
		return rule;
	}
	
	public static RuleAssignment ruleAssignment(String effectiveDate) {	
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
		value.setExclude(false);
		
		parent.setId(catalogRuleId);
		parent.setType("rule");
		
		parameterName.setId("AUTO_ruleAttr_simple");
		
		selectionValue.setParameterName(parameterName);
		selectionValue.setValue("KD1");
		selectionValue.setDisplayValue("Nokia resources");
		selectionValue.setType("Simple");	
	
		List<SelectionValue> selectionValues = new ArrayList<>();
		selectionValues.add(selectionValue);
		
		selection.setId(ruleSelectionId);
		selection.setSelectionValues(selectionValues);
		
		condition.setConditionType("selection");
		condition.setSelection(selection);
		
		ruleAssignment.setId(ruleAssignmentId);
		ruleAssignment.setName(ruleAssignmentName);
		ruleAssignment.setState("DEF");
		ruleAssignment.setValidFor(validFor);
		ruleAssignment.setParent(parent);
		ruleAssignment.setCondition(condition);
		ruleAssignment.setValue(value);
		
		return ruleAssignment;
	}
	
	public static Rule ruleMDRAssignmentUpdate(String effectiveDate) {	
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
		
		parent.setId(ruleSelectionId);
		
		parameterName.setId("AUTO_ruleAttr_simple");
		
		selectionValue.setParameterName(parameterName);
		selectionValue.setValue("KD2|KD6");
		selectionValue.setDisplayValue("Nokia resources|Motorola Resources");
		selectionValue.setType("Simple");
		
		List<SelectionValue> selectionValues = new ArrayList<>();
		selectionValues.add(selectionValue);
		
		selection.setId(ruleSelectionId);
		selection.setSelectionValues(selectionValues);
		
		condition.setConditionType("selection");
		condition.setSelection(selection);
		
		ruleAssignment.setId(ruleAssignmentId);
		ruleAssignment.setName(ruleAssignmentName);
		ruleAssignment.setState("DEF");
		ruleAssignment.setValidFor(validFor);
		ruleAssignment.setParent(parent);
		ruleAssignment.setCondition(condition);
		ruleAssignment.setValue(value);
		
		List<RuleAssignment> ruleAssignments = new ArrayList<>();
		ruleAssignments.add(ruleAssignment);
 		
		properties.setId("isComposite");
		properties.setValue("true");
		
		version.setId(catalogRuleId);
		version.setName(catalogRuleName);
		version.setValidFor(validFor);
		version.setImplementationType("MDR");
		version.setRuleType("S");
		version.setRuleAssignments(ruleAssignments);
		version.setProperties(properties);
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		RuleSpaceSpec ruleSpaceSpec = new RuleSpaceSpec();
		ruleSpaceSpec.setId(MDRtestData.getRuleSpaceSpecId());
		
		rule.setVersions(versions);
		rule.setId(catalogRuleId);
		rule.setRuleSpaceSpec(ruleSpaceSpec);
		
		return rule;
	}
	
	public static RuleAssignment ruleAssignmentUpdate(String effectiveDate) {	
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
		
		selection.setId(ruleSelectionId);
		selection.setSelectionValues(selectionValues);
		
		condition.setConditionType("selection");
		condition.setSelection(selection);
		
		ruleAssignment.setId(ruleAssignmentId);
		ruleAssignment.setName(ruleAssignmentName+"_update");
		ruleAssignment.setState("DEF");
		ruleAssignment.setValidFor(validFor);
		ruleAssignment.setParent(parent);
		ruleAssignment.setCondition(condition);
		ruleAssignment.setValue(value);
		
		return ruleAssignment;
	}
	
	public static AssociationType  associationType (String effectiveDate) {	
		AssociationType associationType = new AssociationType();
		ValidFor validFor = new ValidFor();
		
		String associationTypeDate = effectiveDate;

		associationType.setId(getDimensionId());
		associationType.setName(getDimensionName());
		validFor.setStartDateTime(associationTypeDate);
		associationType.setValidFor(validFor);
		associationType.setState("DEF");
		associationType.setCategory("Price");
		
		return associationType;
	}
	
	public static DimensionElement  dimensionElement(String effectiveDate) {	
		DimensionElement dimensionElement = new DimensionElement();
		
		ValidFor validFor = new ValidFor();
		validFor.setStartDateTime(effectiveDate);
		
		ValueDimensionElement value = new ValueDimensionElement();
		value.setCharacteristicId("code");
		value.setCharacteristicName("Code");
		value.setValue("KD1");
		
		ValueDimensionElement value2 = new ValueDimensionElement();
		value2.setCharacteristicId("description");
		value2.setCharacteristicName("Description");
		value2.setValue("Nokia Resources");
		
		ValueDimensionElement value3 = new ValueDimensionElement();
		value3.setCharacteristicId("ModelType");
		value3.setCharacteristicName("ModelType");
		value3.setValue("Cell Phone");
		
		ValueDimensionElement value4 = new ValueDimensionElement();
		value4.setCharacteristicId("EquipmentType");
		value4.setCharacteristicName("EquipmentType");
		value4.setValue("Lumia 510");
		
		ValueDimensionElement value5 = new ValueDimensionElement();
		value5.setCharacteristicId("Obsolete");
		value5.setCharacteristicName("Obsolete");
		value5.setValue("0");
		
		List<ValueDimensionElement> values = new ArrayList<>();
		values.add(value);
		values.add(value2);
		values.add(value3);
		values.add(value4);
		values.add(value5);
		
		dimensionElement.setId("Element_01");
		dimensionElement.setDescription("Element01");
		dimensionElement.setState("DEF");
		dimensionElement.setValidFor(validFor);
		dimensionElement.setValues(values);
		
		return dimensionElement;
	}
	
	public static DimensionElement  dimensionElementUpdate(String effectiveDate) {	
		DimensionElement dimensionElement = new DimensionElement();
		
		ValidFor validFor = new ValidFor();
		validFor.setStartDateTime(effectiveDate);
		
		ValueDimensionElement value = new ValueDimensionElement();
		value.setCharacteristicId("code");
		value.setCharacteristicName("Code");
		value.setValue("KD1_Update");
		
		ValueDimensionElement value2 = new ValueDimensionElement();
		value2.setCharacteristicId("description");
		value2.setCharacteristicName("Description");
		value2.setValue("Nokia Resources");
		
		ValueDimensionElement value3 = new ValueDimensionElement();
		value3.setCharacteristicId("ModelType");
		value3.setCharacteristicName("ModelType");
		value3.setValue("Cell Phone");
		
		ValueDimensionElement value4 = new ValueDimensionElement();
		value4.setCharacteristicId("EquipmentType");
		value4.setCharacteristicName("EquipmentType");
		value4.setValue("Lumia 510");
		
		ValueDimensionElement value5 = new ValueDimensionElement();
		value5.setCharacteristicId("Obsolete");
		value5.setCharacteristicName("Obsolete");
		value5.setValue("1");
		
		List<ValueDimensionElement> values = new ArrayList<>();
		values.add(value);
		values.add(value2);
		values.add(value3);
		values.add(value4);
		values.add(value5);
		
		dimensionElement.setId("Element_01_Update");
		dimensionElement.setDescription("Element01_Update");
		dimensionElement.setState("DEF");
		dimensionElement.setValidFor(validFor);
		dimensionElement.setValues(values);	

		return dimensionElement;
	}
	
	public static Dimension  dimension(String effectiveDate, String typeDimensionSpec) {	
		Dimension dimension = new Dimension();
		DimensionVersion version = new DimensionVersion();
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		DimensionElement dimensionElement1 = new DimensionElement();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		ValueDimensionElement value = new ValueDimensionElement();
		value.setCharacteristicId("code");
		value.setCharacteristicName("Code");
		value.setValue("KD1");
		
		ValueDimensionElement value2 = new ValueDimensionElement();
		value2.setCharacteristicId("description");
		value2.setCharacteristicName("Description");
		value2.setValue("Nokia Resources");
		
		ValueDimensionElement value3 = new ValueDimensionElement();
		value3.setCharacteristicId("ModelType");
		value3.setCharacteristicName("ModelType");
		value3.setValue("Cell Phone");
		
		ValueDimensionElement value4 = new ValueDimensionElement();
		value4.setCharacteristicId("EquipmentType");
		value4.setCharacteristicName("EquipmentType");
		value4.setValue("Lumia 510");
		
		ValueDimensionElement value5 = new ValueDimensionElement();
		value5.setCharacteristicId("Obsolete");
		value5.setCharacteristicName("Obsolete");
		value5.setValue("0");
		
		List<ValueDimensionElement> values = new ArrayList<>();
		values.add(value);
		values.add(value2);
		values.add(value3);
		values.add(value4);
		values.add(value5);
		
		dimensionElement1.setId("Element_01");
		dimensionElement1.setDescription("Element01");
		dimensionElement1.setState("DEF");
		dimensionElement1.setValidFor(validFor);
		dimensionElement1.setValues(values);
		
		List<DimensionElement> dimensionElements = new ArrayList<>();
		dimensionElements.add(dimensionElement1);
		
		dimensionSpecification.setId(getDimensionSpecId());
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType(typeDimensionSpec);
		
		version.setValidFor(validFor);
		version.setName(getDimensionName());
		version.setId(getDimensionId());
		version.setState("DEF");
		version.setDimensionSpecification(dimensionSpecification);
		version.setDimensionElements(dimensionElements);
		
		List<DimensionVersion> versions = new ArrayList<>();
		versions.add(version);
		
		dimension.setVersions(versions);
		dimension.setId(getDimensionId());
		
		return dimension;
	}
	
	public static Dimension  dimensionVersion(String effectiveDate, String typeDimensionSpec, String dimensionId, String dimensionSpecId) {	
		Dimension dimension = new Dimension();
		DimensionVersion version = new DimensionVersion();
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		DimensionElement dimensionElement1 = new DimensionElement();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		ValueDimensionElement value = new ValueDimensionElement();
		value.setCharacteristicId("code");
		value.setCharacteristicName("Code");
		value.setValue("KD1");
		
		ValueDimensionElement value2 = new ValueDimensionElement();
		value2.setCharacteristicId("description");
		value2.setCharacteristicName("Description");
		value2.setValue("Nokia Resources");
		
		ValueDimensionElement value3 = new ValueDimensionElement();
		value3.setCharacteristicId("ModelType");
		value3.setCharacteristicName("ModelType");
		value3.setValue("Cell Phone");
		
		ValueDimensionElement value4 = new ValueDimensionElement();
		value4.setCharacteristicId("EquipmentType");
		value4.setCharacteristicName("EquipmentType");
		value4.setValue("Lumia 510");
		
		ValueDimensionElement value5 = new ValueDimensionElement();
		value5.setCharacteristicId("Obsolete");
		value5.setCharacteristicName("Obsolete");
		value5.setValue("0");
		
		List<ValueDimensionElement> values = new ArrayList<>();
		values.add(value);
		values.add(value2);
		values.add(value3);
		values.add(value4);
		values.add(value5);
		
		dimensionElement1.setId("Element_01");
		dimensionElement1.setDescription("Element01");
		dimensionElement1.setState("DEF");
		dimensionElement1.setValidFor(validFor);
		dimensionElement1.setValues(values);
		
		List<DimensionElement> dimensionElements = new ArrayList<>();
		dimensionElements.add(dimensionElement1);
		
		dimensionSpecification.setId(dimensionSpecId);
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType(typeDimensionSpec);
		
		version.setValidFor(validFor);
		version.setName(getDimensionName());
		version.setId(dimensionId);
		version.setState("DEF");
		version.setDimensionSpecification(dimensionSpecification);
		version.setDimensionElements(dimensionElements);
		
		List<DimensionVersion> versions = new ArrayList<>();
		versions.add(version);
		
		dimension.setVersions(versions);
		dimension.setId(dimensionId);
		
		return dimension;
	}
	
	public static Dimension  dimensionWithoutElement(String effectiveDate, String typeDimensionSpec) {	
		Dimension dimension = new Dimension();
		DimensionVersion version = new DimensionVersion();
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		dimensionSpecification.setId(getDimensionSpecId());
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType(typeDimensionSpec);
		
		version.setValidFor(validFor);
		version.setName(getDimensionName());
		version.setId(getDimensionId());
		version.setState("DEF");
		version.setDimensionSpecification(dimensionSpecification);
		
		List<DimensionVersion> versions = new ArrayList<>();
		versions.add(version);
		
		dimension.setVersions(versions);
		dimension.setId(getDimensionId());
		
		return dimension;
	}
	
	public static Dimension  dimensionUpdated(String effectiveDate, String typeDimensionSpec) {	
		Dimension dimension = new Dimension();
		DimensionVersion version = new DimensionVersion();
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		DimensionElement dimensionElement1 = new DimensionElement();
		DimensionElement dimensionElement2 = new DimensionElement();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		ValueDimensionElement value = new ValueDimensionElement();
		value.setCharacteristicId("code");
		value.setCharacteristicName("Code");
		value.setValue("KD1");
		
		ValueDimensionElement value2 = new ValueDimensionElement();
		value2.setCharacteristicId("description");
		value2.setCharacteristicName("Description");
		value2.setValue("Nokia Resources");
		
		ValueDimensionElement value3 = new ValueDimensionElement();
		value3.setCharacteristicId("ModelType");
		value3.setCharacteristicName("ModelType");
		value3.setValue("Cell Phone");
		
		ValueDimensionElement value4 = new ValueDimensionElement();
		value4.setCharacteristicId("EquipmentType");
		value4.setCharacteristicName("EquipmentType");
		value4.setValue("Lumia 510");
		
		ValueDimensionElement value5 = new ValueDimensionElement();
		value5.setCharacteristicId("Obsolete");
		value5.setCharacteristicName("Obsolete");
		value5.setValue("0");	
	
		List<ValueDimensionElement> values = new ArrayList<>();
		values.add(value);
		values.add(value2);
		values.add(value3);
		values.add(value4);
		values.add(value5);
		
		dimensionElement1.setId("Element_01");
		dimensionElement1.setDescription("Element01");
		dimensionElement1.setState("DEF");
		dimensionElement1.setValidFor(validFor);
		dimensionElement1.setValues(values);
		
		ValueDimensionElement value6 = new ValueDimensionElement();
		value6.setCharacteristicId("code");
		value6.setCharacteristicName("Code");
		value6.setValue("KD2");
		
		ValueDimensionElement value7 = new ValueDimensionElement();
		value7.setCharacteristicId("description");
		value7.setCharacteristicName("Description");
		value7.setValue("Asus Resources");
		
		ValueDimensionElement value8 = new ValueDimensionElement();
		value8.setCharacteristicId("ModelType");
		value8.setCharacteristicName("ModelType");
		value8.setValue("Smartphone");
		
		ValueDimensionElement value9 = new ValueDimensionElement();
		value9.setCharacteristicId("EquipmentType");
		value9.setCharacteristicName("EquipmentType");
		value9.setValue("Zenfone Selfie");
		
		ValueDimensionElement value10 = new ValueDimensionElement();
		value10.setCharacteristicId("Obsolete");
		value10.setCharacteristicName("Obsolete");
		value10.setValue("1");
		
		List<ValueDimensionElement> values2 = new ArrayList<>();
		values2.add(value6);
		values2.add(value7);
		values2.add(value8);
		values2.add(value9);
		values2.add(value10);
		
		dimensionElement2.setId("Element_02");
		dimensionElement2.setDescription("Element02");
		dimensionElement2.setState("DEF");
		dimensionElement2.setValidFor(validFor);
		dimensionElement2.setValues(values2);
	
		List<DimensionElement> dimensionElements = new ArrayList<>();
		dimensionElements.add(dimensionElement1);
		dimensionElements.add(dimensionElement2);

		
		dimensionSpecification.setId(getDimensionSpecId());
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType(typeDimensionSpec);
		
		version.setValidFor(validFor);
		version.setName(getDimensionName());
		version.setId(getDimensionId());
		version.setState("DEF");
		version.setDimensionSpecification(dimensionSpecification);
		version.setDimensionElements(dimensionElements);
		
		List<DimensionVersion> versions = new ArrayList<>();
		versions.add(version);
		
		dimension.setVersions(versions);
		dimension.setId(getDimensionId());
		
		return dimension;
	}
	
	public static Dimension  dimensionWrongElement(String effectiveDate, String typeDimensionSpec) {	
		Dimension dimension = new Dimension();
		DimensionVersion version = new DimensionVersion();
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		DimensionElement dimensionElement1 = new DimensionElement();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		ValueDimensionElement value = new ValueDimensionElement();
		value.setCharacteristicId("code");
		value.setCharacteristicName("Code");
		value.setValue("KD1");
		
		ValueDimensionElement value2 = new ValueDimensionElement();
		value2.setCharacteristicId("description");
		value2.setCharacteristicName("Description");
		value2.setValue("Nokia Resources");
		
		ValueDimensionElement value3 = new ValueDimensionElement();
		value3.setCharacteristicId("ModelType");
		value3.setCharacteristicName("ModelType");
		value3.setValue("Cell Phone");
		
		ValueDimensionElement value4 = new ValueDimensionElement();
		value4.setCharacteristicId("EquipmentType");
		value4.setCharacteristicName("EquipmentType");
		value4.setValue("Lumia 510");
		
		ValueDimensionElement value5 = new ValueDimensionElement();
		value5.setCharacteristicId("Obsolete");
		value5.setCharacteristicName("Obsolete");
		value5.setValue("25");
		
		List<ValueDimensionElement> values = new ArrayList<>();
		values.add(value);
		values.add(value2);
		values.add(value3);
		values.add(value4);
		values.add(value5);
		
		dimensionElement1.setId("Element_01");
		dimensionElement1.setDescription("Element01");
		dimensionElement1.setState("DEF");
		dimensionElement1.setValidFor(validFor);
		dimensionElement1.setValues(values);
		
		List<DimensionElement> dimensionElements = new ArrayList<>();
		dimensionElements.add(dimensionElement1);
		
		dimensionSpecification.setId(getDimensionSpecId());
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType(typeDimensionSpec);
		
		version.setValidFor(validFor);
		version.setName(getDimensionName());
		version.setId(getDimensionId());
		version.setState("DEF");
		version.setDimensionSpecification(dimensionSpecification);
		version.setDimensionElements(dimensionElements);
		
		List<DimensionVersion> versions = new ArrayList<>();
		versions.add(version);
		
		dimension.setVersions(versions);
		dimension.setId(getDimensionId());
		
		return dimension;
	}
	
	public static Dimension  dimensionWrongDimSpec(String effectiveDate, String typeDimensionSpec) {	
		Dimension dimension = new Dimension();
		DimensionVersion version = new DimensionVersion();
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		DimensionElement dimensionElement1 = new DimensionElement();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		ValueDimensionElement value = new ValueDimensionElement();
		value.setCharacteristicId("code");
		value.setCharacteristicName("Code");
		value.setValue("KD1");
		
		ValueDimensionElement value2 = new ValueDimensionElement();
		value2.setCharacteristicId("description");
		value2.setCharacteristicName("Description");
		value2.setValue("Nokia Resources");
		
		ValueDimensionElement value3 = new ValueDimensionElement();
		value3.setCharacteristicId("ModelType");
		value3.setCharacteristicName("ModelType");
		value3.setValue("Cell Phone");
		
		ValueDimensionElement value4 = new ValueDimensionElement();
		value4.setCharacteristicId("EquipmentType");
		value4.setCharacteristicName("EquipmentType");
		value4.setValue("Lumia 510");
		
		ValueDimensionElement value5 = new ValueDimensionElement();
		value5.setCharacteristicId("Obsolete");
		value5.setCharacteristicName("Obsolete");
		value5.setValue("0");
		
		List<ValueDimensionElement> values = new ArrayList<>();
		values.add(value);
		values.add(value2);
		values.add(value3);
		values.add(value4);
		values.add(value5);
		
		dimensionElement1.setId("Element_01");
		dimensionElement1.setDescription("Element01");
		dimensionElement1.setState("DEF");
		dimensionElement1.setValidFor(validFor);
		dimensionElement1.setValues(values);
		
		List<DimensionElement> dimensionElements = new ArrayList<>();
		dimensionElements.add(dimensionElement1);
		
		dimensionSpecification.setId(getDimensionSpecId()+"_Wrong");
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType(typeDimensionSpec);
		
		version.setValidFor(validFor);
		version.setName(getDimensionName());
		version.setId(getDimensionId());
		version.setState("DEF");
		version.setDimensionSpecification(dimensionSpecification);
		version.setDimensionElements(dimensionElements);
		
		List<DimensionVersion> versions = new ArrayList<>();
		versions.add(version);
		
		dimension.setVersions(versions);
		dimension.setId(getDimensionId());
		
		return dimension;
	}
	
	public static DimensionSpecification  dimensionSpecification(String effectiveDate, String typeDimensionSpec) {	
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		dimensionSpecification.setId(getDimensionSpecId());
		dimensionSpecification.setName(getDimensionSpecName());
		dimensionSpecification.setValidFor(validFor);
		dimensionSpecification.setState("DEF");
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType(typeDimensionSpec);
		
		CharacteristicDimension characteristic = new CharacteristicDimension();
		characteristic.setId("ModelType");
		characteristic.setName("ModelType");
		characteristic.setValueType("String");
		characteristic.setValueTypeSpecification("stringAttribute");
		characteristic.setIsLevel(false);
		characteristic.setSequence("2");
		
		CharacteristicDimension characteristic2 = new CharacteristicDimension();
		characteristic2.setId("EquipmentType");
		characteristic2.setName("EquipmentType");
		characteristic2.setValueType("String");
		characteristic2.setValueTypeSpecification("stringAttribute");
		characteristic2.setIsLevel(false);
		characteristic2.setSequence("3");
		
		CharacteristicDimension characteristic3 = new CharacteristicDimension();
		characteristic3.setId("Obsolete");
		characteristic3.setName("Obsolete");
		characteristic3.setValueType("String");
		characteristic3.setValueTypeSpecification("booleanAttribute");
		characteristic3.setIsLevel(false);
		characteristic3.setSequence("4");

		List<CharacteristicDimension> CharacteristicDimensions = new ArrayList<>();
		
		CharacteristicDimensions.add(characteristic);
		CharacteristicDimensions.add(characteristic2);
		CharacteristicDimensions.add(characteristic3);

		dimensionSpecification.setCharacteristics(CharacteristicDimensions);
		
		return dimensionSpecification;
	}
	
	public static DimensionSpecification  dimensionSpecificationVersion(String effectiveDate, String typeDimensionSpec, String dimensionSpecId) {	
		DimensionSpecification dimensionSpecification = new DimensionSpecification();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		dimensionSpecification.setId(dimensionSpecId);
		dimensionSpecification.setName(getDimensionSpecName());
		dimensionSpecification.setValidFor(validFor);
		dimensionSpecification.setState("DEF");
		dimensionSpecification.setSpecificationType("DimensionSpecification");
		dimensionSpecification.setType(typeDimensionSpec);
		
		CharacteristicDimension characteristic = new CharacteristicDimension();
		characteristic.setId("ModelType");
		characteristic.setName("ModelType");
		characteristic.setValueType("String");
		characteristic.setValueTypeSpecification("stringAttribute");
		characteristic.setIsLevel(false);
		characteristic.setSequence("2");
		
		CharacteristicDimension characteristic2 = new CharacteristicDimension();
		characteristic2.setId("EquipmentType");
		characteristic2.setName("EquipmentType");
		characteristic2.setValueType("String");
		characteristic2.setValueTypeSpecification("stringAttribute");
		characteristic2.setIsLevel(false);
		characteristic2.setSequence("3");
		
		CharacteristicDimension characteristic3 = new CharacteristicDimension();
		characteristic3.setId("Obsolete");
		characteristic3.setName("Obsolete");
		characteristic3.setValueType("String");
		characteristic3.setValueTypeSpecification("booleanAttribute");
		characteristic3.setIsLevel(false);
		characteristic3.setSequence("4");

		List<CharacteristicDimension> CharacteristicDimensions = new ArrayList<>();
		
		CharacteristicDimensions.add(characteristic);
		CharacteristicDimensions.add(characteristic2);
		CharacteristicDimensions.add(characteristic3);

		dimensionSpecification.setCharacteristics(CharacteristicDimensions);
		
		return dimensionSpecification;
	}
	
	public static Rule  rule(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		version.setId(getDimensionId());
		version.setName(getDimensionName());
		version.setValidFor(validFor);
		version.setImplementationType("CR");
		version.setRuleType("O");
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		rule.setVersions(versions);
		rule.setId(getDimensionId());
		
		return rule;
	}
	
	public static Rule  ruleSegment(String effectiveDate) {	
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		
		ValidFor validFor = new ValidFor();
		String dimensionTypeDate = effectiveDate;
		validFor.setStartDateTime(dimensionTypeDate);
		
		version.setId(getDimensionId());
		version.setName(getDimensionName());
		version.setValidFor(validFor);
		version.setImplementationType("CR");
		version.setRuleType("S");
		
		List<RuleVersion> versions = new ArrayList<>();
		versions.add(version);
		
		rule.setVersions(versions);
		rule.setId(getDimensionId());
		
		return rule;
	}
	
	public static ProductCategory  productCategory(String effectiveDate) {	
		ProductCategory productCategory = new ProductCategory();
		
		ValidFor validFor = new ValidFor();
		String productCategoryDate = effectiveDate;
		validFor.setStartDateTime(productCategoryDate);

		productCategory.setId(getDimensionId());
		productCategory.setValidFor(validFor);
		productCategory.setParentCategoryId("");
		productCategory.setName(getDimensionName());
		productCategory.setDescription("Testing Description");
		productCategory.setState("DEF");
		
		return productCategory;
	}
	
	public static ContextSpecification  contextSpecification(String effectiveDate) {	
		ContextSpecification contextSpecification = new ContextSpecification();

		contextSpecification.setId(getDimensionId());
		contextSpecification.setName(getDimensionName());
		
		return contextSpecification;
	}

	public static Project project(String effectiveDate) {
        Project project = new Project();
        project.setId(MDRtestData.getDimensionId());
        project.setName(MDRtestData.getDimensionName());
        project.setState("DEF");
        project.setEffectiveDate(effectiveDate);
    	return project;
	}

	public static Request request(String effectiveDate) {
        Request request = new Request();
        if(MDRtestData.getDimensionId().contains("PLD")) {
        	request.setId(MDRtestData.getDimensionId());
        }
        request.setName(MDRtestData.getDimensionName());
        request.setType("RULE_PLM");
        request.setEffectiveDate(effectiveDate);
    	return request;
	}
	
	public static Item item(String effectiveDate) {
		Item productOffering = new Item();
		ItemVersion version = new ItemVersion();
		ValidFor validFor =  new ValidFor();
		
		validFor.setStartDateTime(effectiveDate);
		
		version.setId(getDimensionId());
		version.setName(getDimensionName());
		version.setValidFor(validFor);
		
		List<Version> versions =  new ArrayList<>();
		versions.add(version);
		
		productOffering.setId(getDimensionId());
		productOffering.setVersions(versions);
		
		return productOffering;
	}
}
