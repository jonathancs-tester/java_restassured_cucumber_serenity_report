@release:Release-19.1
@BUC9903
@ET9831
Feature: ET9831 - Include display of discount details from price spec to POP level.
				  Map discount details from CT to POP. Display only, cannot be changed.
				  CT information is already available at POP level.

@smoke
Scenario Outline: Return discountDetails structure when GET in POP with upadmin and lisa users
	Given I create the Price Specification with discountDetails as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I create the POP with Price Specification with "<user>"
	Then I verify if return the discountDetails
	
	Examples: 
		|	user		|
		|	upadmin		|
		|	lisa		|

@RegressionTest
Scenario Outline: Return discountDetails structure when GET in POP with upadmin and lisa users and activate project
	Given I create the Price Specification with discountDetails as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I create the POP with Price Specification with "<user>"
	And activated the project
	Then I verify if return the discountDetails
	
	Examples: 
		|	user		|
		|	upadmin		|
		|	lisa		|

@RegressionTest
Scenario Outline: Return discountDetails structure when GET in POP with others users
	Given I create the Price Specification with discountDetails as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I create the POP with Price Specification with "<user>"
	Then I verify if return the discountDetails
	
	Examples: 
		|	user		|
		|	anne		|
		|	alex		|
	
@RegressionTest
Scenario Outline: Create the POP with users without privilegies
	Given I create the Price Specification with discountDetails as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I can not create the POP with Price Specification with "<user>"
	Then I verify if return the error message as "<user>"
	
	Examples: 
		|	user		|
		|	mario		|
		|	tom			|