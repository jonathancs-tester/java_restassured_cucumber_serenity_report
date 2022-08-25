@release:Release-18.1 
Feature: US7916 - As a Client Application User, I want to be able to create my personalized User Preferences via REST API.
				  This user story is about implementing a REST API which allows client applications users to create personalized User Preferences. 

@BUC7502
@smoke 
Scenario Outline: Create for a Client Application one User Preference personalized
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	When the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should		|	200				|
		|	anne		   |	should		|	200				|
		|	alex	   	   |	should		|	200				|
		|	mario		   |	should		|	200				|
		|	catmainrestapi |	should		|	200				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|

@BUC7502 
@smoke
Scenario Outline: Create for a Client Application multiples User Preference personalized
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	When I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should		|	200				|
		|	anne		   |	should		|	200				|
		|	alex	   	   |	should		|	200				|
		|	mario		   |	should		|	200				|
		|	catmainrestapi |	should		|	200				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502 
@RegressionTest
Scenario Outline: Create for a Client Application wrong User Preference personalized
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I change defId to "defError"
	When I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		    |	responseCode	|
		|	upadmin		   	|	400				|
		|	lisa		   	|	400				|
		|	anne		   	|	400				|
		|	alex	   	   	|	400				|
		|	mario		   	|	400				|
		|	catmainrestapi 	|	400				|
		|	user1		   	|	400				|
		|	user2		   	|	403				|
		|	nouser		   	|	401				|
			
@BUC7502 
@RegressionTest
Scenario Outline: Create for a Client Application with applicationId User Preference personalized incorrect
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I change applicationId to "appError"
	When I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		    |	responseCode	|
		|	upadmin		   	|	404				|
		|	lisa		   	|	404				|
		|	anne		   	|	404				|
		|	alex	   	   	|	404				|
		|	mario		   	|	404				|
		|	catmainrestapi 	|	404				|
		|	user1		   	|	404				|
		|	user2		   	|	403				|
		|	nouser		   	|	401				|
		
@BUC7502 
@RegressionTest
Scenario Outline: Create for a Client Application with userId User Preference personalized incorrect
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	When I create an user preference in applicationID for "badUser" and userRequest = "<user>"
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		    |	responseCode	|
		|	upadmin		   	|	404				|
		|	lisa		   	|	404				|
		|	anne		   	|	404				|
		|	alex	   	   	|	404				|
		|	mario		   	|	404				|
		|	catmainrestapi 	|	404				|
		|	user1		   	|	404				|
		|	user2		   	|	403				|
		|	nouser		   	|	401				|
		
@BUC7502
@RegressionTest
Scenario Outline: Create for a Client Application duplicated User Preference personalized
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	When I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And a GET operation is made on "User Preference" API
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	Then the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   		|	responseCode	|
		|	upadmin		   		|	500				|
		|	lisa		   		|	500				|
		|	anne		   		|	500				|
		|	alex	   	   		|	500				|
		|	mario		   		|	500				|
		|	catmainrestapi 		|	500				|
		|	user1		   		|	500				|
		|	user2		   		|	403				|
		|	nouser		   		|	401				|
		