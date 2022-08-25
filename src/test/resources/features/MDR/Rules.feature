@release:Release-19
@MDR
Feature: MDR test: As a Catalog User, I want to create the Rules via management REST API.
				   As a Catalog User, I want to create the Rules (Rule Space Specification, Catalog Rule, and Rule Assignment) via management REST API.

				   Acceptance Criteria:
					* Create the Rule Space Specification;
					* Create the Catalog Rule;
					* Create the Rule Assignment

@RuleSpaceSpecification
@smoke
Scenario Outline: Create and Update the RuleSpaceSpecification via management REST API
	Given I create the project with "upadmin"
	And Request "<request>" to the RuleSpaceSpecification with "upadmin"
	When I validate the project
	Then GET request to return the RuleSpaceSpecification with "upadmin"
	
	Examples:
		| request 	| 
		| post		|	
		| put		|	

@CatalogRule
@smoke
Scenario Outline: Create and Update the Catalog Rule via management REST API - HX49478
	Given I create the project with "upadmin"
	And I create the RuleSpaceSpecification with "upadmin"
	And Request "<request>" to the Catalog Rule with "upadmin"
	When I validate the project
	Then GET request to return the Catalog Rule with "upadmin"
	And I validate the "<isComposite>" state
	
	Examples:
		| request 	| isComposite |
		| post		|	false	  |
		| put		|	true	  |

@CatalogRule
@RegressionTest
Scenario Outline: Create and Update the Catalog Rule with Rule Assignment via management REST API - HX49478
	Given I create the project with "upadmin"
	And I create the RuleSpaceSpecification with "upadmin"
	And Request "<request>" to the Catalog Rule with Rule Assignment and "upadmin"
	When I validate the project
	Then GET request to return the Catalog Rule with "upadmin"
	And I validate the "<isComposite>" state
	
	Examples:
		| request 	| isComposite |
		| post		|	false	  |
		| put		|	true	  |
	

@RuleAssignment
@smoke
Scenario Outline: Create and Update the Rule Assignment via management REST API
	Given I create the project with "upadmin"
	And I create the RuleSpaceSpecification with "upadmin"
	And I create the Catalog Rule with "upadmin"
	And Request "<request>" to the Rule Assignment with "upadmin"
	When I validate the project
	Then GET request to return the Rule Assignment with "upadmin"
	
	Examples:
		| request 	| 
		| post		|	
		| put		|	

@DeleteRules
@RegressionTest
Scenario: Delete the Rule Assignment, Catalog Rule, and RuleSpaceSpecification via management REST API
	Given I create the project with "upadmin"
	And I create the RuleSpaceSpecification with "upadmin"
	And I create the Catalog Rule with "upadmin"
	And Request "post" to the Rule Assignment with "upadmin"
	When I delete the Rule Assignment with "upadmin"
	And I delete the Catalog Rule with "upadmin"
	And I delete the RuleSpaceSpecification with "upadmin"
	Then GET request to return with empty RuleSpaceSpecification
	
