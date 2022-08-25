@release:Release-18.1 
Feature: US7512 - As a Catalog Admin User, I want to be able to register new ECM Client Applications. 
				  This user story is about defining a new code table to hold supported Client Applications, which would be used by User Preferences APIs, and to define a default application user to hold all default User Preferences for all Client Applications.

@BUC7502
@smoke
Scenario Outline: As a Catalog User, I want to be able to register new ECM Client Applications
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "5.0"
	And I delete any existing default user preference for "app_pref_default_user"
	When the user is logged as "<user>"
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>"
	Then the return http status code is "<responseCode>"  
	And I verify if response "<assertion>" be valid for user preference 
	|	1	|
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	201				|
		|	lisa		   |	should not	|	403				|
		|	anne		   |	should not	|	403				|
		|	alex	   	   |	should not	|	403				|
		|	mario		   |	should not	|	403				|
		|	catmainrestapi |	should not	|	403				|
		|	user1		   |	should not 	|	403				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
