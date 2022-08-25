package ecm.standalone.utils;

import java.util.ArrayList;
import java.util.List;

import ecm.standalone.models.Action;
import ecm.standalone.models.AssociationTypeCharacteristics;
import ecm.standalone.models.BusinessDate;
import ecm.standalone.models.BusinessDatesModel;
import ecm.standalone.models.BusinessDatesModelAssociation;
import ecm.standalone.models.Characteristic;
import ecm.standalone.models.CharacteristicValue;
import ecm.standalone.models.CharacteristicValueSpecification;
import ecm.standalone.models.CharacteristicVersion;
import ecm.standalone.models.Commentary;
import ecm.standalone.models.Document;
import ecm.standalone.models.ExternalIdentifier;
import ecm.standalone.models.Group;
import ecm.standalone.models.Item;
import ecm.standalone.models.Media;
import ecm.standalone.models.POAssociation;
import ecm.standalone.models.Parent;
import ecm.standalone.models.ProductOfferingVersionExpanded;
import ecm.standalone.models.PropertiesPermission;
import ecm.standalone.models.Property;
import ecm.standalone.models.ReferenceList;
import ecm.standalone.models.Rule;
import ecm.standalone.models.RuleAssociation;
import ecm.standalone.models.RuleVersion;
import ecm.standalone.models.Translation;
import ecm.standalone.models.TranslationAssociation;
import ecm.standalone.models.ValidFor;
import ecm.standalone.models.Value;
import ecm.standalone.models.ValueAssociation;
import ecm.standalone.models.ValueTypeSpecification;
import ecm.standalone.models.Version;

public class TestData {
	
	public Translation setTranslation(String fieldName, String text, String language){
		Translation translation = new Translation();
		translation.setFieldname(fieldName);
		List<TranslationAssociation> translationAssociations = new ArrayList<TranslationAssociation>();
		TranslationAssociation translationAssociation = new TranslationAssociation();
		translationAssociation.setText(text);
		translationAssociation.setLanguage(language);
		translationAssociations.add(translationAssociation);			
		translation.setTranslation(translationAssociations);
		return translation;
	}
	
	public ExternalIdentifier setExternalIdentifier(){
		//Handle External Identifier
		ExternalIdentifier externalIdentifier = new ExternalIdentifier();
		externalIdentifier.setExternalId("abc123");
		externalIdentifier.setExternalSource("CS");
		externalIdentifier.setExternalidvalue("abc");
		List<ExternalIdentifier> externalIdentifiers = new ArrayList<ExternalIdentifier>();
		externalIdentifiers.add(externalIdentifier);
		
		return externalIdentifier;
	}

	public ValidFor setValidFor(){
		//Handle ValidFor
		ValidFor validFor = new ValidFor();
		validFor.setStartDateTime(Utils.getEffectiveDate(0, 3));
		
		return validFor;
	}
	
	public CharacteristicValue setCharacteriticValue(String id){
		return setCharacteriticValue(id, id);
	}
	
	public CharacteristicValue setCharacteriticValue(String id, String displayValue){
		CharacteristicValue cv = new CharacteristicValue();
		cv.setValidFor(setValidFor());
		cv.setValue(id);
		cv.setValueType("CodeTable");
		cv.setIsDefault(false);
		cv.setDisplayValue(displayValue);
		
		return cv;
	}
	
	public CharacteristicValueSpecification setCharacteristicValueSpecification(String id) {

		CharacteristicValueSpecification characteristicValueSpecification = new CharacteristicValueSpecification();
		characteristicValueSpecification.setId(id);
		characteristicValueSpecification.setIsReference(true);
			
		ReferenceList referenceList = new ReferenceList();
		referenceList.setId(id);
		referenceList.setHref("/ecm/ecm/CatalogManagement/v2/referenceList/" + id +"/listElement");
		
		characteristicValueSpecification.setReferenceList(referenceList);
		return characteristicValueSpecification;
	}

	public Characteristic setSpecificationSubType(){
		Characteristic specificationSubType = new Characteristic();
		specificationSubType.setId("specificationSubType");
		
		List<CharacteristicVersion> specificationSubType_versions = new ArrayList<CharacteristicVersion>();
		CharacteristicVersion specificationSubType_version = new CharacteristicVersion();
		specificationSubType_version.setId("specificationSubType");
		specificationSubType_version.setName("Product Offering Type Test");
		specificationSubType_version.setValidFor(setValidFor());
		specificationSubType_version.setType("pscmSystemAttribute");
		specificationSubType_version.setValueType("CodeTable");
		
		List<CharacteristicValue> subtype_characteristicValues = new ArrayList<CharacteristicValue>();
		subtype_characteristicValues.add(setCharacteriticValue("Add-on"));
		subtype_characteristicValues.add(setCharacteriticValue("Add-on Service"));
		subtype_characteristicValues.add(setCharacteriticValue("Discount"));
		subtype_characteristicValues.add(setCharacteriticValue("Equipment"));
		subtype_characteristicValues.add(setCharacteriticValue("Feature"));	
		subtype_characteristicValues.add(setCharacteriticValue("Free Units"));
		subtype_characteristicValues.add(setCharacteriticValue("Gifts"));	
		subtype_characteristicValues.add(setCharacteriticValue("Optional Service"));
		subtype_characteristicValues.add(setCharacteriticValue("Plan"));
		subtype_characteristicValues.add(setCharacteriticValue("Product"));	
		subtype_characteristicValues.add(setCharacteriticValue("Promotion"));
		subtype_characteristicValues.add(setCharacteriticValue("Retention"));	
		subtype_characteristicValues.add(setCharacteriticValue("Testing"));
		subtype_characteristicValues.add(setCharacteriticValue("ratingAndCharging", "Rating & Charging"));
								
		specificationSubType_version.setCharacteristicValue(subtype_characteristicValues);
		specificationSubType_version.setState("ACT");
		
		List<Translation> translations = new ArrayList<Translation>();
		translations.add(setTranslation("name", "Offer Type", "en-xx"));
		
		specificationSubType_version.setTranslations(translations);
		specificationSubType_version.setSequence(1);
		
		ValueTypeSpecification valueTypeSpecification = new ValueTypeSpecification();
		valueTypeSpecification.setId("pscm_offerType");
		specificationSubType_version.setValueTypeSpecification(valueTypeSpecification);
		
		List<PropertiesPermission> propertiesPermissions = new ArrayList<PropertiesPermission>();
		PropertiesPermission propertiesPermission = new PropertiesPermission(); 
		propertiesPermission.setId("perm_readonly");
		propertiesPermission.setType("readonly");
		propertiesPermission.setIsSelected(true);
		propertiesPermissions.add(propertiesPermission);
		specificationSubType_version.setPropertiesPermissions(propertiesPermissions);
			
		specificationSubType_version.setChangeState("inherited");
		specificationSubType_version.setCharacteristicValueSpecification(setCharacteristicValueSpecification("pscm_offerType"));	
		specificationSubType_versions.add(specificationSubType_version);
		specificationSubType.setVersions(specificationSubType_versions);
		return specificationSubType;
	}
	
	public Characteristic setFamily(){
		Characteristic family = new Characteristic();
		family.setId("family");
		
		List<CharacteristicVersion> family_versions = new ArrayList<CharacteristicVersion>();
		CharacteristicVersion family_version = new CharacteristicVersion();
		family_version.setId("family");
		family_version.setName("Test Family");
		family_version.setValidFor(setValidFor());
		family_version.setType("pscmSystemAttribute");
		family_version.setValueType("CodeTable");
			
		List<CharacteristicValue> family_characteristicValues = new ArrayList<CharacteristicValue>();
		family_characteristicValues.add(setCharacteriticValue("Digital"));
		family_characteristicValues.add(setCharacteriticValue("Internet"));
		family_characteristicValues.add(setCharacteriticValue("Mobile"));
		family_characteristicValues.add(setCharacteriticValue("Security"));
		family_characteristicValues.add(setCharacteriticValue("Telephony"));	
		family_characteristicValues.add(setCharacteriticValue("Television"));
								
		family_version.setCharacteristicValue(family_characteristicValues);
		family_version.setState("ACT");
		
		List<Translation> translations = new ArrayList<Translation>();
		translations.add(setTranslation("name", "Family", "en-xx"));
		
		family_version.setTranslations(translations);
		family_version.setSequence(3);
		
		ValueTypeSpecification valueTypeSpecification = new ValueTypeSpecification();
		valueTypeSpecification.setId("pscm_offerType");
		family_version.setValueTypeSpecification(valueTypeSpecification);
		
		List<PropertiesPermission> propertiesPermissions = new ArrayList<PropertiesPermission>();
		PropertiesPermission propertiesPermission = new PropertiesPermission(); 
		propertiesPermission.setId("perm_readonly");
		propertiesPermission.setType("readonly");
		propertiesPermission.setIsSelected(true);
		propertiesPermissions.add(propertiesPermission);
		family_version.setPropertiesPermissions(propertiesPermissions);
		family_version.setChangeState("inherited");
		family_version.setCharacteristicValueSpecification(setCharacteristicValueSpecification("pscm_offerFamily"));	
		family_versions.add(family_version);
		family.setVersions(family_versions);
		return family;
	}
	
	public Characteristic setCategory(){
		Characteristic category = new Characteristic();
		category.setId("category");
		List<CharacteristicVersion> category_versions = new ArrayList<CharacteristicVersion>();
		CharacteristicVersion category_version = new CharacteristicVersion();
		category_version.setId("category");
		category_version.setName("Test Category");
		category_version.setValidFor(setValidFor());
		category_version.setType("pscmSystemAttribute");
		category_version.setValueType("CodeTable");
			
		List<CharacteristicValue> category_characteristicValues = new ArrayList<CharacteristicValue>();
		category_characteristicValues.add(setCharacteriticValue("Rental"));
		category_characteristicValues.add(setCharacteriticValue("Sale"));
		category_characteristicValues.add(setCharacteriticValue("Service"));
		category_characteristicValues.add(setCharacteriticValue("Lease"));
								
		category_version.setCharacteristicValue(category_characteristicValues);
		category_version.setState("ACT");
		
		List<Translation> translations = new ArrayList<Translation>();
		translations.add(setTranslation("name", "Category", "en-xx"));
		
		category_version.setTranslations(translations);
		category_version.setSequence(4);
		
		ValueTypeSpecification valueTypeSpecification = new ValueTypeSpecification();
		valueTypeSpecification.setId("pscm_offerCategory");
		category_version.setValueTypeSpecification(valueTypeSpecification);
		
		List<PropertiesPermission> propertiesPermissions = new ArrayList<PropertiesPermission>();
		PropertiesPermission propertiesPermission = new PropertiesPermission(); 
		propertiesPermission.setId("perm_readonly");
		propertiesPermission.setType("readonly");
		propertiesPermission.setIsSelected(true);
		propertiesPermissions.add(propertiesPermission);
		
		category_version.setPropertiesPermissions(propertiesPermissions);	
		category_version.setChangeState("inherited");	
		category_version.setCharacteristicValueSpecification(setCharacteristicValueSpecification("pscm_offerCategory"));	
		category_versions.add(category_version);
		category.setVersions(category_versions);
		
		return category;
	}
	
	public Characteristic setInstanceState(){
		Characteristic instanceState = new Characteristic();
		instanceState.setId("instance_state");
		
		List<CharacteristicVersion> instanceState_versions = new ArrayList<CharacteristicVersion>();
		CharacteristicVersion instanceState_version = new CharacteristicVersion();
		instanceState_version.setId("instance_state");
		instanceState_version.setName("Instance State Test");
		instanceState_version.setValidFor(setValidFor());
		instanceState_version.setType("lifeCycle");
		instanceState_version.setValueType("CodeTable");
			
		List<CharacteristicValue> category_characteristicValues = new ArrayList<CharacteristicValue>();
		category_characteristicValues.add(setCharacteriticValue("ACTIVE"));
		category_characteristicValues.add(setCharacteriticValue("MIGRATED"));
		category_characteristicValues.add(setCharacteriticValue("NEW"));
		category_characteristicValues.add(setCharacteriticValue("SUSPENDED"));
		category_characteristicValues.add(setCharacteriticValue("TERMINATED"));
								
		instanceState_version.setCharacteristicValue(category_characteristicValues);
		instanceState_version.setState("ACT");
		
		List<Translation> translations = new ArrayList<Translation>();
		translations.add(setTranslation("name", "Instance State", "en-xx"));
		
		instanceState_version.setTranslations(translations);
		
		ValueTypeSpecification valueTypeSpecification = new ValueTypeSpecification();
		valueTypeSpecification.setId("state_model");
		instanceState_version.setValueTypeSpecification(valueTypeSpecification);
		
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("CONF", true));
		properties.add(new Property("isMandatory", false));
		properties.add(new Property("canBeOverriden", false));
		properties.add(new Property("Tree", false));
		properties.add(new Property("Translatable", false));
		properties.add(new Property("STORFRT", false));
		properties.add(new Property("ReadOnlyExtension", false));
		properties.add(new Property("ReadOnlyNewVersions", false));
		properties.add(new Property("READONLY", false));
		properties.add(new Property("PROV", false));
		properties.add(new Property("POPrice", false));
		properties.add(new Property("MandatoryExtension", false));
		properties.add(new Property("HIDDEN", false));
		properties.add(new Property("Flex", false));
		properties.add(new Property("FEATURE", false));
		properties.add(new Property("EXTBASE", false));
		properties.add(new Property("DisplayOnInvoice", false));
		properties.add(new Property("DEF", false));
		properties.add(new Property("Context", false));
		
		instanceState_version.setProperties(properties);	
		instanceState_version.setChangeState("inherited");
		instanceState_version.setCharacteristicValueSpecification(setCharacteristicValueSpecification("state_model"));
		instanceState_versions.add(instanceState_version);
		instanceState.setVersions(instanceState_versions);
		
		return instanceState;
	}
	
	public RuleAssociation setRuleAssociation(){
		RuleAssociation ruleAssociation = new RuleAssociation();
		ruleAssociation.setid("DefaultTrue");
		ruleAssociation.sethref("/ecm/ecm/CatalogManagement/v2/rule/DefaultTrue");
		return ruleAssociation;
	}
	
	public ValueTypeSpecification setValueTypeSpecification(String id){
		ValueTypeSpecification valueTypeSpecification = new ValueTypeSpecification();
		valueTypeSpecification.setId(id);
		return valueTypeSpecification;
	}
	
	public CharacteristicValue setNumberCharacteristicValue(String id){
		CharacteristicValue cv = new CharacteristicValue();
		cv.setValue(id);
		cv.setValueType("Number");
		cv.setIsDefault(true);
		cv.setDisplayValue(id);
		return cv;
	}
	
	public CharacteristicValue setCodeTableCharacteristicValue(String id, Boolean isDefault){
		return setCodeTableCharacteristicValue(id, isDefault, id);
	}
	
	public CharacteristicValue setCodeTableCharacteristicValue(String id, Boolean isDefault, String displayValue){
		CharacteristicValue cv = new CharacteristicValue();
		cv.setValue(id);
		cv.setValueType("CodeTable");
		cv.setIsDefault(isDefault);
		cv.setDisplayValue(displayValue);
		return cv;
	}
	
	public CharacteristicValue setBooleanCharacteristicValue(String id){
		CharacteristicValue cv = new CharacteristicValue();
		cv.setValue(id);
		cv.setValueType("Boolean");
		cv.setIsDefault(true);
		cv.setDisplayValue(id);
		return cv;
	}
	
	public AssociationTypeCharacteristics setAssociationTypeCharacteristics(String id, int sequence, String valueType, String valueTypeSpecification){
		return setAssociationTypeCharacteristics(id, sequence, valueType, valueTypeSpecification, null, null);
	}	
	
	public AssociationTypeCharacteristics setAssociationTypeCharacteristics(String id, int sequence, String valueType, String valueTypeSpecification, List<CharacteristicValue> characteristicValues){
		return setAssociationTypeCharacteristics(id, sequence, valueType, valueTypeSpecification, characteristicValues, null);
	}	
	
	public AssociationTypeCharacteristics setAssociationTypeCharacteristics(String id, int sequence, String valueType, String valueTypeSpecification, List<CharacteristicValue> characteristicValues, List<Property> properties){
		AssociationTypeCharacteristics associationTypeCharacteristics = new AssociationTypeCharacteristics();
		associationTypeCharacteristics.setId(id);
		associationTypeCharacteristics.setName(id);
		associationTypeCharacteristics.setSequence(sequence);
		associationTypeCharacteristics.setValueType(valueType);
		associationTypeCharacteristics.setValueTypeSpecification(setValueTypeSpecification(valueTypeSpecification));
		associationTypeCharacteristics.setCharacteristicValues(characteristicValues);
		associationTypeCharacteristics.setProperties(properties);
		
		return associationTypeCharacteristics;
	}

	private POAssociation setPOAssociation(String psID) {
		List<CharacteristicValue> number = new ArrayList<CharacteristicValue>();
		number.add(setNumberCharacteristicValue("1"));
		
		List<CharacteristicValue> codeTableAssocPattern = new ArrayList<CharacteristicValue>();
		codeTableAssocPattern.add(setCodeTableCharacteristicValue("realization", false));
		codeTableAssocPattern.add(setCodeTableCharacteristicValue("information", false));
		codeTableAssocPattern.add(setCodeTableCharacteristicValue("dependency", false));
		codeTableAssocPattern.add(setCodeTableCharacteristicValue("composition", true));
		codeTableAssocPattern.add(setCodeTableCharacteristicValue("association", false));
		codeTableAssocPattern.add(setCodeTableCharacteristicValue("aggregation", false));
		
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("READONLY", true));

		List<CharacteristicValue> associationTypeAttributeValue = new ArrayList<CharacteristicValue>();
		associationTypeAttributeValue.add(setNumberCharacteristicValue("associationTypeAttributeValue"));
		
		List<CharacteristicValue> bool = new ArrayList<CharacteristicValue>();
		bool.add(setBooleanCharacteristicValue("0"));

		List<CharacteristicValue> codeTableAssocHandler = new ArrayList<CharacteristicValue>();
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Propagate.ServiceInactive", false, "PROPAGATE.SERVICEINACTIVE"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("PropagateIfExclusive", false, "PROPAGATEIFEXCLUSIVE"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("AddExisting", false, "ADDEXISTING"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Propagate", true, "PROPAGATE"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Reclaim", false, "RECLAIM"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Release", false, "RELEASE"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Delink", false, "DELINK"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Swap", false, "SWAP"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Reuse", false, "REUSE"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Delete", false, "DELETE"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("New", false, "NEW"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("Block", false, "BLOCK"));
		codeTableAssocHandler.add(setCodeTableCharacteristicValue("None", false, "NONE"));

		List<CharacteristicValue> codeTableSrcHandler = new ArrayList<CharacteristicValue>();
		codeTableSrcHandler.add(setCodeTableCharacteristicValue("NA", false));
		codeTableSrcHandler.add(setCodeTableCharacteristicValue("ANY", false));
		codeTableSrcHandler.add(setCodeTableCharacteristicValue("ALL", false));

		List<CharacteristicValue> codeTableRollBackHandler = new ArrayList<CharacteristicValue>();
		codeTableRollBackHandler.add(setCodeTableCharacteristicValue("Yes", false, "YES"));
		codeTableRollBackHandler.add(setCodeTableCharacteristicValue("No", false, "NO"));

		List<CharacteristicValue> codeTableSearchHandler = new ArrayList<CharacteristicValue>();
		codeTableSearchHandler.add(setCodeTableCharacteristicValue("OriginalRelatedItem", false, "ORIGINAL RELATED ITEM"));
		codeTableSearchHandler.add(setCodeTableCharacteristicValue("OriginalInstance", false, "ORIGINAL INSTANCE"));
		codeTableSearchHandler.add(setCodeTableCharacteristicValue("RelatedItem", false, "RELATED ITEM"));
		codeTableSearchHandler.add(setCodeTableCharacteristicValue("Inventory", false, "INVENTORY"));
		codeTableSearchHandler.add(setCodeTableCharacteristicValue("Undefined", false, "UNDEFINED"));
		
		List<AssociationTypeCharacteristics> associationTypeCharacteristics = new ArrayList<AssociationTypeCharacteristics>();
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_minSourceCardinality", 1, "Number", "numberAttribute", number));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_maxSourceCardinality", 2, "Number", "numberAttribute", number));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_minTargetCardinality", 3, "Number", "numberAttribute", number));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_maxTargetCardinality", 4, "Number", "numberAttribute", number));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_associationPattern", 5, "CodeTable", "associationPattern", codeTableAssocPattern, properties));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_associationPriority", 6, "Number", "numberAttribute", associationTypeAttributeValue));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_informationOnly", 7, "Boolean", "booleanAttribute"));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_biDirectional", 8, "Boolean", "booleanAttribute",bool));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_AssociationTargetHandler", 9, "CodeTable", "AssociationActionHandlers",codeTableAssocHandler));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_AssociationSourceHandler", 10, "CodeTable", "AssociationActionHandlers",codeTableAssocHandler));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_AssociationSrcHandlerTrigger", 11, "CodeTable", "AssociationSourceHandlerTigger",codeTableSrcHandler));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_RollbackSourceActionPattern", 12, "CodeTable", "RollbackSourceActionPattern",codeTableRollBackHandler));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_TargetSearchScope", 13, "CodeTable", "searchScopeAttribute",codeTableSearchHandler));
		associationTypeCharacteristics.add(setAssociationTypeCharacteristics("ecm_SourceSearchScope", 14, "CodeTable", "searchScopeAttribute",codeTableSearchHandler));
		
		POAssociation association = new POAssociation();
		association.setValidFor(setValidFor());
		association.setTargetSpecificationId(psID);
		association.setHref("/ecm/ecm/CatalogManagement/v2/productspecification/" + psID);
		association.setTargetSpecificationType("ProductSpecification");
		association.setAssociationType("contains");
		association.setId("reserved_0_PO_to_PS");
		association.setMaxQuantity(1);
		association.setAssociationTypeCharacteristics(associationTypeCharacteristics);
		return association;
	}
	
	public Document setDocument(){
		Document doc = new Document();
		doc.setId("testDocument");
		doc.setName("testDocument");
		doc.setType("contract");
		doc.setMimeType("image/png");
		doc.setAttachmentName("testDocument.PNG");
		doc.setAttachmentCode("testDocument");

		List<Translation> translations = new ArrayList<Translation>();
		translations.add(setTranslation("name", "testDocument", "en-xx"));
		doc.setTranslations(translations);
		
		doc.setURL("/ecm/t/catalogAttachment?id=78bbf1be162b4ba8bf57dc8b87d20ae0");
		
		return doc;
	}
	
	public Media setMedia(){
		Media media = new Media();
		media.setId("testMedia");
		media.setName("testMedia");
		media.setImageType("PNG");
		media.setImageSize("L");
		media.setCategory("P");
		media.setImageCode("testMedia");
		media.setURL("/ecm/t/image?id=6a66a1aebae145b6aa8fa9088ce05552");
		
		return media;
	}

	public Rule setRule(){
		Rule rule = new Rule();
		RuleVersion version = new RuleVersion();
		List<RuleVersion> versions = new ArrayList<>();

		versions.add(version);
		rule.setId("DefaultTrue");
		rule.setVersions(versions);
		
		return rule;
	}
	
	public Group setGroup(){
		Group group = new Group();
		group.setId("newGroup");
		return group;
	}
	
	public Commentary setCommentary(){
		Commentary commentary = new Commentary();
		commentary.setId("test_new_description");
		commentary.setItemDescriptionCode("test_new_description");
		List<Translation> translations = new ArrayList<Translation>();
		translations.add(setTranslation("description", "new description", "en-xx"));
		commentary.setTranslations(translations);
		commentary.setStartDate(Utils.getEffectiveDate(0, 3));
		commentary.setStatus("DEF");
		
		return commentary;
	}
	
	public Action setAction() {
		Action action = new Action();
		action.setId("testAction");
		action.setName("testAction");
		action.setOrderItemAction("RESUME");
		action.setSubAction("Basket.Add");
		action.setImplementationType("Eligibility");
		action.setImplementationValue("DefaultTrue");
		action.setValidFor(setValidFor());
		
		return action;
	}

	private BusinessDate setBusinessDate(String poID) {
		BusinessDate bd = new BusinessDate();

		Parent parent = new Parent();
		parent.setId(poID);
		parent.setHref("/ecm/ecm/CatalogManagement/v2/productOffering/" + poID);
		
		ValueAssociation testSD = new ValueAssociation();
		testSD.setName("testSD");
		testSD.setDisplayValue("test Start Date");
		ValueAssociation testED = new ValueAssociation();
		testED.setName("testED");
		testED.setDisplayValue("test End Date");
	
		List<ValueAssociation> values = new ArrayList<ValueAssociation>();
		values.add(testSD);
		values.add(testED);
		
		Value value = new Value();
		value.setValues(values);
		
		BusinessDatesModelAssociation businessDatesModel = new BusinessDatesModelAssociation();
		businessDatesModel.setId("test");
		businessDatesModel.setHref("/ecm/ecm/CatalogManagement/v2/businessDatesModel/test");
		
		bd.setState("DEF");
		bd.setValidFor(setValidFor());
		bd.setParent(parent);
		bd.setValue(value);
		bd.setBusinessDatesModelAssociation(businessDatesModel);
		
		return bd;
	}

	private BusinessDatesModel setBusinessDatesModel(String poID) {
		BusinessDatesModel bdm = new BusinessDatesModel();

		BusinessDatesModelAssociation businessDatesModel = new BusinessDatesModelAssociation();
		businessDatesModel.setId("test");
		businessDatesModel.setHref("/ecm/ecm/CatalogManagement/v2/businessDatesModel/test");

		Parent parent = new Parent();
		parent.setId(poID);
		parent.setHref("/ecm/ecm/CatalogManagement/v2/productOffering/" + poID);
		
		bdm.setName("test");
		bdm.setBusinessDatesModel(businessDatesModel);
		bdm.setValidFor(setValidFor());
		bdm.setState("DEF");
		bdm.setParent(parent);
		return bdm;
	}
	
	public Version setPOVersion(){
		String poID = new String("poID");
		String poName = new String("po Name");
		String poDesc = new String("po description");
		
		List<ExternalIdentifier> listExternalIdentifier = new ArrayList<ExternalIdentifier>();
		listExternalIdentifier.add(setExternalIdentifier());
		
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		characteristics.add(setSpecificationSubType());
		characteristics.add(setFamily());
		characteristics.add(setCategory());
		characteristics.add(setInstanceState());
		
		List<POAssociation> associations = new ArrayList<POAssociation>();
		associations.add(setPOAssociation("psID"));
		
		List<Document> documents = new ArrayList<Document>();
		documents.add(setDocument());
		
		List<Media> media = new ArrayList<Media>();
		media.add(setMedia());
		
		List<Rule> rules = new ArrayList<Rule>();
		rules.add(setRule());
		
		List<Group> groups = new ArrayList<Group>();
		groups.add(setGroup());
		
		List<Commentary> commentaries = new ArrayList<Commentary>();
		commentaries.add(setCommentary());
		
		List<Action> actions = new ArrayList<Action>();
		actions.add(setAction());
		
		List<String> domains = new ArrayList<String>();
		domains.add("test2");
		
		List<Translation> translations = new ArrayList<Translation>();
		translations.add(setTranslation("testing name", "Product_Spec_Optional_name test", "en-xx"));
		translations.add(setTranslation("description", "testing description", "en-xx"));
		
		List<BusinessDate> businessDates = new ArrayList<BusinessDate>();
		businessDates.add(setBusinessDate(poID));
		
		List<BusinessDatesModel> businessDatesModels = new ArrayList<BusinessDatesModel>();
		businessDatesModels.add(setBusinessDatesModel(poID));
		
		//Handle PO Version (adds all objects to PO Version)
		ProductOfferingVersionExpanded poVersion = new ProductOfferingVersionExpanded();
		poVersion.setIdItemVersion(poID);
		poVersion.setExternalIdentifiers(listExternalIdentifier);
		poVersion.setName(poName);
		poVersion.setDescription(poDesc);
		poVersion.setState("DEF");
		poVersion.setValidFor(setValidFor());
		poVersion.setBaseEntityId("base_ProductOffering");
		poVersion.setIsComposite(true);
		poVersion.setSpecificationType("ProductOffering");
		poVersion.setSharedIndicator(true);
		poVersion.setSellIndicator(true);
		poVersion.setCharacteristics(characteristics);
		poVersion.setAssociations(associations);
		poVersion.setDocuments(documents);
		poVersion.setMedia(media);
		poVersion.setRules(rules);
		poVersion.setGroups(groups);
		poVersion.setCommentaries(commentaries);
		poVersion.setActions(actions);
		poVersion.setDomains(domains);
		poVersion.setTranslations(translations);
		poVersion.setBusinessDates(businessDates);
		poVersion.setBusinessDatesModel(businessDatesModels);
		
		return poVersion;
	}

	public Item createProductOfferingJSON(){
		String poID = new String("poID");

		List<Version> versionList = new ArrayList<Version>();
		versionList.add(setPOVersion());
		
		Item po = new Item();
		po.setId(poID);
		po.setVersions(versionList);
		
		return po;
	}
}
