@release:Release-19
@MDR
Feature: MDR test - As a Catalog User, I want to create the Dimension and Dimension Spec via management REST API.

				    Acceptance Criteria:
						* Create the Simple Dimension;
						* Create the Range Dimension;
						* Create the Dimension Element;
						* Create the Dimension Versioning
@Dimension
@smoke
Scenario Outline: Create and Update the Simple/Range Dimension via management REST API
	Given I create the project with "<user>"
	And I create the Dimension Spec with "<user>"
	And request "<request>" to the Dimension "<type>" with "<user>"
	When I validate the project
	Then GET request to return the Dimension with "<user>"
	
	Examples:
		| user   	| request 	| 	type			|
		| upadmin	| post		|	simpleDimension	|
		| upadmin	| put		|	simpleDimension	|
		| upadmin	| post		|	rangeDimension	|
		| upadmin	| put		|	rangeDimension	|

@Dimension
@RegressionTest
Scenario Outline: Create the Simple Dimension with wrong Dimension Spec via management REST API
	Given I create the project with "<user>"
	And I create the Dimension Spec with "<user>"
	And I create the simple dimension with wrong "<error>" with "<user>"
	When I validate the project
	Then GET request to return the Simple Dimension with wrong Dimension Specification and "<user>"
	
	Examples:
		| user   | error	|
		| upadmin| dimSpec	|
		| upadmin| element	|

@DimensionElement
@smoke
Scenario Outline: Create and Update the Dimension Element via management REST API
	Given I create the project with "<user>"
	And I create the Dimension Spec with "<user>"
	And I create the simple dimension without dimension element and "<user>"
	When Request "<request>" to the Dimension Element with "<user>"
	And I validate the project 
	Then GET request to return the Dimension Element with "<user>"
	And GET request to return the Dimension Element by ID with "<user>"
	
	Examples:
		| user   	| request 	| 	
		| upadmin	| post		|	
		| upadmin	| put		|	
		| upadmin	| patch		|

@DimensionVersion
@smoke
Scenario: Create versions of the Dimension via management REST API
	Given I create "3" Dimension version and activate each Dimension with "upadmin"
	When GET request to return all Dimension with "upadmin"
	Then I verify Dimension quantity version "3" in project

@DimensionVersion
@RegressionTest
Scenario: Duplicate the Dimension via management REST API
	Given I create the project with "upadmin"
	And I create the Dimension Spec with "upadmin"
	And I create the simple dimension without dimension element and "upadmin"
	When I create the same simple dimension with "upadmin"
	Then I verify error in dimension duplicated
	
@DimensionDelete	
@RegressionTest
Scenario: Delete the Dimension Specification, Dimension, and Dimension Element via management REST API
	Given I create the project with "upadmin"
	And I create the Dimension Spec with "upadmin"
	And I create the simple dimension without dimension element and "upadmin"
	And Request "post" to the Dimension Element with "upadmin"
	When I delete the dimension element with "upadmin"
	And I delete the Dimension with "upadmin"
	Then GET request to return the Simple Dimension with wrong Dimension Specification and "upadmin"
