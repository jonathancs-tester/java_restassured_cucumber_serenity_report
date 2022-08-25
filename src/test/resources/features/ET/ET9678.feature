@release:Release-19
@ET
@ET9678
Feature: ET9678 - Should be possible to add same rule to two different prices on same PO.
				  It should be possible to return in the response for PO with expand=true and POP GET operation rules with same code in two different prices on same PO.

				  Acceptance Criteria:
				  	* See that only rule_1 inside POP_1 is returned.

@smoke
Scenario: Create two different POP in PO and verify if response exists only DefaultTrue Rule each POP
	Given I create a PO and project with "upadmin"
	When I create the "2" POP with same Rule with "upadmin"
	And I GET POP with expand=true
	Then I verify if response exists only "DefaultTrue" Rule

@RegressionTest
Scenario Outline: Create two different POP in PO and verify if response exists only DefaultTrue Rule each POP with lisa/anne user
	Given I create a PO and project with "<user>"
	When I create the "2" POP with same Rule with "<user>"
	And I GET POP with expand=true
	Then I verify if response exists only "DefaultTrue" Rule
	
	Examples: 
		| user		|   
		| lisa		|		
		| anne		|      
