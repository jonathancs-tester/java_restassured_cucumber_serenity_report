@release:Release-19.1
@BUC9903
@ET9908
Feature: ET9908 - New project life cycle "Approval" and info table with state transitions - For Life Cycle (BUC# 9665)
		          It is required as part of Offer Manager project life cycle feature to introduce a new state called "approval" in existing life cycle diagram "objectLifeCycleModified". [needs to confirm with Craig state transition for Approval]
				  In addition, to manage the states in Offer Manager, a new info table shall be created with a reduced state transition so that FE can consult the information.

@smoke
Scenario Outline: Verify the project status as TSI or APR when the project is definition
	Given I have a project in definition state
	When I change the state to "<state>" through "PUT" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	TSI      		|
		|	upadmin		   |	APR     		|
		
@smoke
Scenario Outline: Verify the project status as DEF or APR when the project went to TSI
	Given I have a project in definition state
	When I change the state to "TSI" through "PUT" request as "upadmin"
	When I change the state to "<state>" through "PUT" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	DEF      		|
		|	upadmin		   |	APR     		|

@smoke
Scenario Outline: Verify the project status as LPI, ACT or DEF when the project went to APR
	Given I have a project in definition state
	When I change the state to "TSI" through "PUT" request as "upadmin"
	And I change the state to "APR" through "PATCH" request as "upadmin"
	And I change the state to "<state>" through "PUT" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	LPI      		|
		|	upadmin		   |	ACT     		|
		|	upadmin		   |	DEF     		|
		
@smoke
Scenario Outline: Verify the project status as ACT or DEF when the project went to LPI
	Given I have a project in definition state
	When I change the state to "TSI" through "PUT" request as "upadmin"
	And I change the state to "APR" through "PUT" request as "upadmin"
	And I change the state to "LPI" through "PUT" request as "upadmin"
	And I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	upadmin		   |	ACT     		|
		|	upadmin		   |	DEF     		|
		
@smoke
Scenario Outline: Verify the project status as DEF or TSI when the project is ACT
	Given I have a project in definition state
	When I change the state to "TSI" through "PATCH" request as "upadmin"
	And I change the state to "APR" through "PATCH" request as "upadmin"
	And I change the state to "LPI" through "PATCH" request as "upadmin"
	And I change the state to "ACT" through "PATCH" request as "upadmin"
	And I change the state to "<state>" through "PATCH" request as "<user>"
	Then I verify the "<responseCode>"
	
	Examples: 
		|	user		   |	state    		|	responseCode   |			
		|	lisa		   |	DEF     		|      403         |
		|	lisa		   |	TSI     		|      403         |	

@smoke
Scenario Outline: Verify the project status as APR or LPI when the project is LOC 
	Given I have a project in definition state
	When I change the state to "TSI" through "PATCH" request as "upadmin"
	And I change the state to "APR" through "PATCH" request as "upadmin"
	And I change the state to "LPI" through "PATCH" request as "upadmin"
	And I change the state to "LOC" through "PATCH" request as "upadmin"
	And I change the state to "<state>" through "PATCH" request as "<user>"
	Then I verify the "<responseCode>"
	
	Examples: 
		|	user		   |	state    		|	responseCode   |			
		|	lisa		   |	APR    		    |      403         |
		|	lisa		   |	LPI    		    |      403         |
		

