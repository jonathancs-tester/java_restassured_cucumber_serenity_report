@release:Release-18.1 
Feature: US7911 - As a Client Application, I want to be able to create default User Preferences via REST API.
   				  This user story is about implementing a REST API which allows a Client Application to create its default values for User Preferences.

@BUC7502 
@smoke
Scenario Outline: Create one preference definition in User Preference
	Given I create 3 applications with "<user>"
	And I create 2 users 
	When the user is logged as "<user>"
	And I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" 
	Then I verify if response "<assertion>" be valid for user preference
	|	1	| 
	And the return http status code is "<responseCode>"
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	201				|
		|	lisa		   |	should not	|	403				|
		|	anne		   |	should not	|	403				|
		|	alex	   	   |	should not	|	403				|
		|	mario		   |	should not	|	403				|
		|	user1		   |	should not	|	403				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
	
@BUC7502  
@RegressionTest
Scenario Outline: Create two preferences definitions in User Preference
	Given I create 3 applications with "<user>"
	And I create 2 users 
	When the user is logged as "<user>"
	When I create 2 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" 
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	|	2	|
	And the return http status code is "<responseCode>"
		
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	201				|
		|	lisa		   |	should not	|	403				|
		|	anne		   |	should not	|	403				|
		|	alex	   	   |	should not	|	403				|
		|	mario		   |	should not	|	403				|
		|	catmainrestapi |	should not	|	403				|
		|	user1		   |	should not	|	403				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502  
@smoke
Scenario Outline: The attempt to retrieve a user preference with id incorrect must have an error response code 
	Given I create 3 applications with "<user>"
	And I create 2 users 
	When I create 1 preference definition with default_value = "5.0" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I change defId to "defError" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" 
	And the user is logged as "<user>"
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	400				|
		|	lisa		   |	should not	|	403				|
		|	anne		   |	should not	|	403				|
		|	alex	   	   |	should not	|	403				|
		|	mario		   |	should not	|	403				|
		|	catmainrestapi |	should not	|	403				|
		|	user1		   |	should not	|	403				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502  
@RegressionTest
Scenario Outline: The attempt to retrieve a user preference with id equal to null must have an error response code
	Given I create 3 applications with "<user>"
	And I create 2 users 
	When I create 1 preference definition with default_value = "5.0" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I remove defId 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" 
	And the user is logged as "<user>"
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	400				|
		|	lisa		   |	should not	|	403				|
		|	anne		   |	should not	|	403				|
		|	alex	   	   |	should not	|	403				|
		|	mario		   |	should not	|	403				|
		|	catmainrestapi |	should not	|	403				|
		|	user1		   |	should not	|	403				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502
@smoke  
Scenario Outline: Attempting to retrieve a non-existent user must have an error response code
	Given I create 3 applications with "<user>"
	And I create 2 users 
	When I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" 
	And the user is logged as "<user>"
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	And the return http status code is "<responseCode>"
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	201				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
			
@BUC7502  
@RegressionTest
Scenario Outline: Attempting to retrieve a nonexistent application must have an error response code
	Given I create 3 applications with "<user>"
	And I create 2 users 
	When I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I change applicationId to "appError" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" 
	And the user is logged as "<user>"
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	404				|
		|	lisa		   |	should not	|	403				|
		|	anne		   |	should not	|	403				|
		|	alex	   	   |	should not	|	403				|
		|	mario		   |	should not	|	403				|
		|	catmainrestapi |	should not	|	403				|
		|	user1		   |	should not	|	403				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502  
@smoke
Scenario Outline: Create duplicate default user preference in application
	Given I create 3 applications with "<user>"
	And I create 2 users 
	When I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>"
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" 
	And the user is logged as "<user>"
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	500				|
		|	lisa		   |	should not	|	403				|
		|	anne		   |	should not	|	403				|
		|	alex	   	   |	should not	|	403				|
		|	mario		   |	should not	|	403				|
		|	catmainrestapi |	should not	|	403				|
		|	user1		   |	should not	|	403				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|