@release:Release-19.1
@BUC9903
@ET9820
Feature: ET9820 - Return isFormula in price specification GET with minimal=true.

@smoke
Scenario Outline: Return isFormula=true in price specification GET with minimal=true
		Given I create the Price Specification with formula as "<user>"
		When a GET operation is made on minimal = "true" and with formula with "<user>"
		Then I verify if response isFormula = "true" parameter
		
		Examples: 
			|	user						|
			|	upadmin						|
			|	anne						|
			|	tom							|

@ET9820
@RegressionTest
Scenario Outline: Return isFormula=false in price specification GET with minimal=true
	Given I create the Price Specification without formula as "<user>"
	When a GET operation is made on minimal = "true" and without formula with "<user>"
	Then I verify if response isFormula = "false" parameter
	
	Examples: 
		|	user						|
		|	upadmin						|
		|	anne						|
		|	tom							|

@ET9820
@RegressionTest
Scenario Outline: Return isFormula=true in price specification GET with minimal=false
	Given I create the Price Specification without formula as "<user>"
	When a GET operation is made on minimal = "false" and with formula with "<user>"
	Then I verify if response isFormula = "true" parameter
	
	Examples: 
		|	user						|
		|	upadmin						|
		|	anne						|
		|	tom							|

@ET9820
@RegressionTest
Scenario Outline: Return isFormula=false in price specification GET with minimal=true and project activated
	Given I create the Price Specification without formula as "<user>"
    And I activate the project
	When a GET operation is made on minimal = "true" and without formula with "<user>"
	Then I verify if response isFormula = "false" parameter
	
	Examples: 
		|	user						|
		|	upadmin						|
		|	anne						|
		|	tom							|

		