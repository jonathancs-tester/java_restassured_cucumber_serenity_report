@release:Release-19.1
@BUC9903
@ET9906
Feature: ET9906 - GET Price Specification enhancement to support minimal=short.
				  GET Price Specification API should be enhanced to support new value short for minimal parameter. The following behavior shall be implemented:

				  Also, about the minimal=short, should it follow same behavior as for item:
					* with project - returns last DEF in that project if any or last ACT one;
					* without project - returns last DEF if any or last ACT;
					* characteristics, condition rules, external identifiers should not be returned.

				  Performance tests (jmeter) should be executed to compare minimal=true and minimal=short with a priceSpec contains at least 5 versions, rules, 10 attributes, external identifier (if possible, try to populate every "tab").
				  Performance tests (jmeter) with at least 50 price specification, loaded should be performed for analysis.

@smoke
Scenario Outline: Return price specification GET with minimal=short and project
	Given I create the Price Specification without formula as "<user>"
	When a GET operation is made on minimal = "short" and with project with "<user>"
	Then I verify if return the price specification "DEF"
	
	Examples: 
		|	user						|
		|	upadmin						|
		|	anne						|
		|	tom							|

@RegressionTest
Scenario Outline: Return price specification GET with minimal=short and without project
	Given I create the Price Specification without formula as "<user>"
	When a GET operation is made on minimal = "short" and without project with "<user>"
	Then I verify if return the price specification "DEF"
	
	Examples: 
		|	user						|
		|	upadmin						|
		|	anne						|
		|	tom							|

@RegressionTest
Scenario Outline: Return price specification GET with minimal=short and project
	Given I create the Price Specification without formula as "<user>"
	And activated the project
	When a GET operation is made on minimal = "short" and with project with "<user>"
	Then I verify if return the price specification "ACT"
	
	Examples: 
		|	user						|
		|	upadmin						|
		|	anne						|
		|	tom							|

