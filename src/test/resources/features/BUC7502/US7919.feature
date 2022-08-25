@release:Release-18.1 
Feature: US7919 - As a Client Application User, I want to be able to fully update my personalized User Preference via REST API.
				  This user story is about implementing a REST API which allows client applications users to update all their application User Preferences. 
				  It is possible to use PUT method from the new User Preference REST API specifying the Client Application id and the user id.

@BUC7502
@smoke
Scenario Outline: Put for a Client Application with one User Preference personalized and one preference definition
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I update user preference in applicationID for "<user>" and userRequest = "<user>" with put
	|	2	|
	And the preference definition is created for "<user>"
	Then I verify if response "<assertion>" be valid for user preference
	|	2	|
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
Scenario Outline: Put for a Client Application with one User Preference personalized and multiples preferences definitions
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I update user preference in applicationID for "<user>" and userRequest = "<user>" with put
	|	1	|
	|	3	|
	|	2	|
	And the preference definition is created for "<user>"
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	|	3	|
	|	2	|
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
Scenario Outline: Put for a Client Application with one User Preference personalized, multiples preferences definitions and one incorrect
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I update user preference in applicationID for "<user>" and userRequest = "<user>" with put
	|	1	|
	|	3	|
	|	4	|
	|	2	|
	And the return http status code is "<responseCodePut>" 
	And the preference definition is created for "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |	responseCodePut	|		responseCodeGet	    |	assertion		|
		|	upadmin		   	|	400				|		200	   			 	|	should			|
		|	lisa		   	|	400				|		200	   			 	|	should			|
		|	anne		   	|	400				|		200	   			 	|	should			|
		|	alex	   	   	|	400				|		200	   			 	|	should			|
		|	mario		   	|	400				|		200	   			 	|	should			|
		|	catmainrestapi 	|	400				|		200	   			 	|	should			|
		|	user1		   	|	400				|		200	   			 	|	should			|
		|	user2		 	|	403				|		403	   			 	|	should not		|
		|	nouser		  	|	401				|		401	   			 	|	should not		|
		
@BUC7502
@RegressionTest
Scenario Outline: Put for a Client Application with one User Preference personalized and preference definition null
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I update user preference in applicationID for "<user>" and userRequest = "<user>" with put
	|	2	|
	|	-1	|
	And the return http status code is "<responseCodePut>" 
	And the preference definition is created for "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |	responseCodePut	|		responseCodeGet	    |	assertion		|
		|	upadmin		   	|	400				|		200	   			 	|	should			|
		|	lisa		   	|	400				|		200	   			 	|	should			|
		|	anne		   	|	400				|		200	   			 	|	should			|
		|	alex	   	   	|	400				|		200	   			 	|	should			|
		|	mario		   	|	400				|		200	   			 	|	should			|
		|	catmainrestapi 	|	400				|		200	   			 	|	should			|
		|	user1		   	|	400				|		200	   			 	|	should			|
		|	user2		 	|	403				|		403	   			 	|	should not		|
		|	nouser		  	|	401				|		401	   			 	|	should not		|
		
@BUC7502
@RegressionTest
Scenario Outline: Put for a Client Application with one User Preference personalized and preference definition duplicated
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I update user preference in applicationID for "<user>" and userRequest = "<user>" with put
	|	3	|
	|	-2	|
	And the return http status code is "<responseCodePut>" 
	And the preference definition is created for "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |	responseCodePut	|		responseCodeGet	    |	assertion		|
		|	upadmin		   	|	400				|		200	   			 	|	should			|
		|	lisa		   	|	400				|		200	   			 	|	should			|
		|	anne		   	|	400				|		200	   			 	|	should			|
		|	alex	   	   	|	400				|		200	   			 	|	should			|
		|	mario		   	|	400				|		200	   			 	|	should			|
		|	catmainrestapi 	|	400				|		200	   			 	|	should			|
		|	user1		   	|	400				|		200	   			 	|	should			|
		|	user2		 	|	403				|		403	   			 	|	should not		|
		|	nouser		  	|	401				|		401	   			 	|	should not		|
		
@BUC7502
@RegressionTest
Scenario Outline: Put for a Client Application with one User Preference personalized and not authorized
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "user1" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I update user preference in applicationID for "user1" and userRequest = "<user>" with put
	|	3	|
	And the return http status code is "<responseCodePut>" 
	And the preference definition is created for "user1"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "should not" be valid for user preference
	|	3	|
	And the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |	responseCodePut	|		responseCodeGet	    |	
		|	upadmin		 	|	403				|		403	   			 	|	
		|	lisa		 	|	403				|		403	   			 	|	
		|	anne		 	|	403				|		403	   			 	|
		|	alex		 	|	403				|		403	   			 	|
		|	mario		 	|	403				|		403	   			 	|
		|	catmainrestapi	|	403				|		403	   			 	|
		|	user2		 	|	403				|		403	   			 	|		
		|	nouser		  	|	401				|		401	   			 	|	
		
@BUC7502
@RegressionTest
Scenario Outline: Put for a Client Application with one User Preference personalized and application incorrect
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And I change applicationId to "appError"
	When I update user preference in applicationID for "<user>" and userRequest = "<user>" with put
	|	1	|
	| 	3	|
	And the preference definition is created for "<user>"
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode	|
		|	upadmin		   |	404				|
		|	lisa		   |	404				|
		|	anne		   |	404				|
		|	alex	   	   |	404				|
		|	mario		   |	404				|
		|	catmainrestapi |	404				|
		|	user1		   |	404				|
		|	user2		   |	403				|
		|	nouser		   |	401				|
		
@BUC7502
@RegressionTest
Scenario Outline: Put for a Client Application with incorrect User Preference personalized 
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I update user preference in applicationID for "badUser" and userRequest = "<user>" with put
	|	1	|
	| 	3	|
	And the return http status code is "<responseCodePut>" 
	And the preference definition is created for "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |	responseCodePut	|		responseCodeGet	    |	assertion		|
		|	upadmin		   	|	404				|		200	   			 	|	should			|
		|	lisa		   	|	404				|		200	   			 	|	should			|
		|	anne		   	|	404				|		200	   			 	|	should			|
		|	alex	   	   	|	404				|		200	   			 	|	should			|
		|	mario		   	|	404				|		200	   			 	|	should			|
		|	catmainrestapi 	|	404				|		200	   			 	|	should			|
		|	user1		   	|	404				|		200	   			 	|	should			|
		|	user2		 	|	403				|		403	   			 	|	should not		|
		|	nouser		  	|	401				|		401	   			 	|	should not		|