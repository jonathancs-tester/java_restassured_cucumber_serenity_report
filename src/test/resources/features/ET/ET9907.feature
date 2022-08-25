@release:Release-19.1
@BUC9903
@ET9907
Feature: ET9907 - Management REST API for project should support PATCH operation. The PATCH operation shall enable user to at least:
			    	* Change state of the project following metadata life cycle diagram "objectLifeCycleModified". When an invalid state is passed, a proper error saying the next valid state shall be returned;
					* Name and description
				  This new operation shall follow existing pattern for management v2/project/{projectId}. All existing PATCH validations shall be applied here.

@smoke
Scenario Outline: Verify the project DEF status updated to others lifecycle status
	Given I have a project in "DEF" state
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	EVI      		|
		|	upadmin		   |	DVI	    		|
		|	lisa		   |	TSI     		|
		|	upadmin		   |	CNI	    		|
		|	lisa		   |	LPI     		|
		|	upadmin		   |	APR	    		|
		|	lisa		   |	ACT     		|
		
@smoke
Scenario Outline: Verify the project created with all lifecycle status
				  HX53083 - Closed
	Given I have a project in "<state>" state
	Then the project can not be created using "<state>"
	
	Examples: 
		|	state    		|
		|	EVI      		|
		|	DVI	    		|
		|	TSI     		|
		|	CNI	    		|
		|	LPI     		|
		|	APR	    		|
		|	ACT     		|
			
@smoke
Scenario Outline: Verify the project EVI status updated to others lifecycle status
	Given I have a project in "DEF" state
	And I change the state to "EVI" through "PATCH" request as "<user>"
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	DEF      		|
		|	upadmin		   |	DVI	    		|
		|	lisa		   |	TSI     		|
		|	upadmin		   |	CNI	    		|
		|	lisa		   |	LPI     		|
		|	upadmin		   |	ACT     		|
			
@smoke
Scenario Outline: Verify the project DVI status updated to others lifecycle status
	Given I have a project in "DEF" state
	And I change the state to "DVI" through "PATCH" request as "<user>"
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	EVI      		|
		|	upadmin		   |	DEF	    		|
		|	lisa		   |	TSI     		|
		|	upadmin		   |	CNI	    		|
		|	lisa		   |	LPI     		|
		|	upadmin		   |	APR     		|
		|	upadmin		   |	ACT     		|

@smoke
Scenario Outline: Verify the project TSI status updated to others lifecycle status
	Given I have a project in "DEF" state
	And I change the state to "TSI" through "PATCH" request as "<user>"
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	EVI      		|
		|	upadmin		   |	DEF	    		|
		|	lisa		   |	DVI     		|
		|	upadmin		   |	CNI	    		|
		|	lisa		   |	LPI     		|
		|	lisa		   |	ACT     		|
		
@smoke
Scenario Outline: Verify the project CNI status updated to others lifecycle status
	Given I have a project in "DEF" state
	And I change the state to "CNI" through "PATCH" request as "<user>"
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	EVI      		|
		|	upadmin		   |	DEF	    		|
		|	lisa		   |	DVI     		|
		|	upadmin		   |	TSI	    		|
		|	lisa		   |	LPI     		|
		|	lisa		   |	LPI     		|
		|	upadmin		   |	ACT     		|
		
@smoke
Scenario Outline: Verify the project LPI status updated to others lifecycle status
	Given I have a project in "DEF" state
	And I change the state to "LPI" through "PATCH" request as "<user>"
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	EVI      		|
		|	upadmin		   |	DEF	    		|
		|	lisa		   |	DVI     		|
		|	upadmin		   |	TSI	    		|
		|	lisa		   |	CNI     		|
		|	upadmin		   |	ACT     		|
		
@smoke
Scenario Outline: Verify the project APR status updated to others lifecycle status
	Given I have a project in "DEF" state
	And I change the state to "APR" through "PATCH" request as "<user>"
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	LPI      		|
		|	lisa		   |	ACT     		|

@RegressionTest
Scenario: Verify the project flow exists with lifecycle status
	Given I have a project in "DEF" state
	When I change the state to "CNI" through "PATCH" request as "lisa"
	And I change the state to "DVI" through "PATCH" request as "lisa"
	And I change the state to "TSI" through "PATCH" request as "lisa"
	And I change the state to "EVI" through "PATCH" request as "lisa"
	And I change the state to "LPI" through "PATCH" request as "lisa"
	And I change the state to "APR" through "PATCH" request as "lisa"
	And I change the state to "ACT" through "PATCH" request as "lisa"
	Then the state "ACT" must be updated
	
@RegressionTest
Scenario Outline: Verify the project DEF status updated to others lifecycle status with PO version
	Given I create a project and PO with PS as "upadmin"
	And I activate this project 
	And I create a new PO version
	When I change the state to "<state>" through "PATCH" request as "<user>" to version
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	EVI      		|
		|	upadmin		   |	DVI	    		|
		|	lisa		   |	TSI     		|
		|	upadmin		   |	CNI	    		|
		|	lisa		   |	LPI     		|
		|	upadmin		   |	APR	    		|
		|	lisa		   |	ACT     		|
		
@RegressionTest
Scenario Outline:  Verify the project EVI status updated to others lifecycle status with PO version
	Given I create a project and PO with PS as "upadmin"
	And I activate this project 
	And I create a new PO version
	When I change the state to "EVI" through "PATCH" request as "<user>" to version
	And I change the state to "<state>" through "PATCH" request as "<user>" to version
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	DEF     		|
		|	upadmin		   |	DVI	    		|
		|	lisa		   |	TSI     		|
		|	upadmin		   |	CNI	    		|
		|	lisa		   |	LPI     		|
		|	upadmin		   |	ACT     		|

@RegressionTest
Scenario:  Verify the project flow exists with lifecycle status with PO version
	Given I create a project and PO with PS as "upadmin"
	And I activate this project 
	And I create a new PO version
	When I change the state to "CNI" through "PATCH" request as "lisa" to version
	And I change the state to "DVI" through "PATCH" request as "lisa" to version
	And I change the state to "TSI" through "PATCH" request as "lisa" to version
	And I change the state to "EVI" through "PATCH" request as "lisa" to version
	And I change the state to "LPI" through "PATCH" request as "lisa" to version
	And I change the state to "APR" through "PATCH" request as "lisa" to version
	And I change the state to "ACT" through "PATCH" request as "lisa" to version
	Then the state "ACT" must be updated
	
@RegressionTest
Scenario Outline: Verify the project ACT status updated by users that does not have permission to update
	Given I create a project and PO with PS as "upadmin"
	And I activate this project 
	And I create a new PO version
	When I change the state to "APR" through "PUT" request as "lisa" to version
	And I change the state to "ACT" through "PATCH" request as "<user>" to version
	Then the state "<state>" must not be updated by "<user>"
	
	Examples: 
		|	user		   |	state    		|
		|	mario		   |	ACT     		|
		|	tom 		   |	ACT     		| 
		|	alex 		   |	ACT     		|
	