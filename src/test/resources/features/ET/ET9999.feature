@release:Release-19.1
@BUC9903
@ET9999
Feature: ET9999 - New Privilege for Approve state.

@smoke
Scenario Outline: Verify the project status as APR or LPI with user does have privilege
	Given I have a project in definition state
	When I change the state to "<state>" through "PATCH" request as "<user>"
	Then the state "<state>" must be updated
	
	Examples: 
		|	user		   |	state    		|
		|	lisa		   |	APR   			|	
		|	anne		   |	APR			    |	
		|	upadmin	   	   |	APR			    |		
		|	mario		   |	APR 		    |		
		|	lisa		   |	LPI   			|	
		|	anne		   |	LPI			    |	
		|	upadmin	   	   |	LPI			    |		
		|	mario		   |	LPI 		    |		
		
@smoke
Scenario Outline: Verify the possibility to activate a project with a user with different privileges
	Given I have a project in definition state
	And I change the state to "APR" through "PATCH" request as "upadmin"
	When I change the state to "LPI" through "PATCH" request as "<user>"
	Then I activate the project as "<user>"
	And I verify the "<responseCode>"
		
    Examples: 
		|	user		   |	responseCode    |
		|	alex		   |	403   			|	
		|	tom		       |	403   			|	
		|	anne		   |	403   			|	
		|	mario		   |	403 			|
		|	upadmin		   |	200   			|	
		|	lisa 		   |	200 			|
		

@RegressionTest
Scenario Outline: Verify the project status as APR or LPI with user with different privileges
	Given I have a project in definition state
	And I change the state to "APR" through "PATCH" request as "upadmin"
	When I change the state to "LPI" through "PATCH" request as "<user>"
	Then I verify the "<responseCode>"
		
    Examples: 
		|	user		   |	responseCode    |
		|	alex		   |	403   			|	
		|	tom		       |	403   			|	
		|	anne		   |	403   			|	
		|	mario		   |	403 			|
		|	upadmin		   |	200   			|	
		|	lisa 		   |	200 			|		

