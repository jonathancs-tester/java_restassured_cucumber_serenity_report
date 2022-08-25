@release:Release-19.1
@BUC9903
@ET9828
Feature: ET9828 - Enhance project GET API to include more info in projectDeliverables.
				  Include resourceType, resourceSubtype, updatedBy, updatedDate and href.

@smoke
Scenario Outline: Verify the resourceType, resourceSubtype, updatedBy, updatedDate and href information in DEF project
	Given I create the Price Specification with discountDetails as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I change the state to "LPI" through "PATCH" request as "<user>"
	Then I verify if include all news properties

	Examples: 
		|	user		   |
		|	lisa		   |		
		|	anne		   |		
		|	upadmin	   	   |			
		|	mario		   |			
		
@RegressionTest
Scenario Outline: Verify the resourceType, resourceSubtype, updatedBy, updatedDate and href information in ACT project
	Given I create the Price Specification with discountDetails as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I change the state to "LPI" through "PATCH" request as "<user>"
	And activated the project
	Then I verify if include all news properties
		
    Examples: 
		|	user		   |
		|	lisa		   |		
		|	anne		   |		
		|	upadmin	   	   |			
		|	mario		   |
		
@RegressionTest
Scenario Outline: Verify the resourceType, resourceSubtype, updatedBy, updatedDate and href information in Others state in project
	Given I create the Price Specification with discountDetails as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then I verify if include all news properties
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	APR   			|	
		|	lisa		   |	LPI 			|
		