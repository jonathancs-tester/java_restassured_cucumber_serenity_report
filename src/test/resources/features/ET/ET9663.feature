@release:Release-19
@ET
@ET9663
Feature: ET9663 - POST for second version should not require HID in payload.
				  If a new product offering is created by management REST API (/ecm/CatalogManagement/v2/productOffering) and humanReadableId (hId) is passed, when creating second version for that product offering, that attribute (humanReadableId) should not be required in the payload again, hId from first version shall be considered instead.

				  Acceptance Criteria:
				  	* Consider that humanReadableId is imutable, which means that:
						* if in first version, hId is not set, second version must always receive null;
						* if in first version, hId is set, second version must receive the same value or null, it cannot be changed.

@smoke
Scenario: Create second version should not require HID in payload and use the HID of the first version
	Given I create the PS 
	And I create of the PO and Project with humanReadableId and "upadmin"
	And I activate the project that is used to create the PO
	And I GET the PO with humanReadableId in First Version with "upadmin"
	When I create of the second version PO and project without humanReadableId and "upadmin"
	And I GET the PO with humanReadableId in Second Version with "upadmin"
	Then I verify if response exists humanReadableId on First and Second Version

@RegressionTest
Scenario Outline:: Create second version should not require HID in payload and use the HID of the first version
	Given I create the PS
	And I create of the PO and Project with humanReadableId and "<user>"
	And I activate the project that is used to create the PO
	And I GET the PO with humanReadableId in First Version with "<user>"
	When I create of the second version PO and project without humanReadableId and "<user>"
	And I GET the PO with humanReadableId in Second Version with "<user>"
	Then I verify if response exists humanReadableId on First and Second Version
	
	Examples: 
		| user		|   
		| lisa		|		
		| anne		|      
