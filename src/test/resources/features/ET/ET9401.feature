@release:Release-19
@ET
Feature: ET9401 - Overlapping needs to be enhanced to prevent same SD and SD before others versions.
		          Validation for overlapping versions needs to be enhanced to prevent two scenarios to happen. Scripts that needs to be changed is "validateVersionEffectiveDate".
				  Two versions with same startdate should not be allowed. Today, it's possible to create, via REST API, two versions with exactly same SD. This is not support today, as what happens in this case is not the creation of a new version, but instead the whole version is swapped from one project to another.
				  Create a new version that starts before an existing DEF version, overlap and end before the existing version should not be allowed.

@ET9401
@smoke
Scenario Outline: It should be not possible to create time slice with same startDateTime
	Given I create the PS
	And I create of the PO contains PS with "upadmin"
	And I activate the project
	When I modify and create new version PO with "<date>" and "<user>"
	Then I verify the responseCode 400 in time slice version
	
	Examples: 
		|	user		   	| date 		 |
		|	upadmin		   	| now  		 |
		|	lisa		   	| now	 	 |
		|	anne		   	| now	 	 |
		
@ET9401
@RegressionTest
Scenario Outline: It should be not possible to create time slice with old startDate
	Given I create the PS 
	And I create of the PO contains PS with "upadmin"
	And I activate the project
	When I modify and create new version PO with "<date>" and "<user>"
	Then I verify the responseCode 400 in time slice version
	
	Examples: 
		|	user		   	| date 				 |
		|	upadmin		   	| yesterday  		 |
		|	lisa		   	| yesterday	 	 	 |
		|	anne		   	| yesterday	 	 	 |
		
@ET9401
@RegressionTest
Scenario Outline: It should be not possible to create time slice with differents endDateTime
	Given I create the PS 
	And I create of the PO contains PS with "upadmin"
	And I activate the project
	When I modify and create new version PO with "tomorrow" and "<user>"
	When I modify and create new version PO with EndDate "<date>" and "<user>"
	Then I verify the responseCode 400 in time slice version
	
	Examples: 
		|	user		   	| date 				     |
		|	upadmin		   	| after tomorrow  		 |
		|	lisa		   	| after tomorrow 	 	 |
		|	anne		   	| after tomorrow	 	 |
		|	upadmin		   	| tomorrow  		 	 |
		|	lisa		   	| tomorrow 	 	 		 |
		|	anne		   	| tomorrow	 	 		 |	

