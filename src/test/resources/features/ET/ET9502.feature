@release:Release-19
@ET
Feature: ET9502 - ProjectCreatedDate information shall be exposed by GET project management API projectCreatedDate information shall be exposed by GET project management API.
			      
			      Acceptance Criteria:		
				 	* Project should be exposed the projectCreatedDate information in GET request.

@ET9502
@smoke
Scenario Outline: It should be possible to GET all product with projectCreatedDate paramenter
	Given there is a product offering 
	And a GET operation is made on Project Management API with "<user>"
	Then I verify if response "projectCreatedDate" parameter
	And the return status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	responseCode	|
		|	lisa		   |	200				|
		|	lisa		   |	200				|
		|	upadmin	   	   |	200				|
		|	upadmin		   |	200				|
