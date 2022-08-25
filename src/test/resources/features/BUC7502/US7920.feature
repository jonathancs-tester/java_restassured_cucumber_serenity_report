@release:Release-18.1 
Feature: US7920 - As a Client Application User, I want to be able to delete all my personalized User Preferences via REST API.
				  This user story is about implementing a REST API which allows client applications users to delete their application User Preferences.
				  It is possible to use DELETE method from the new User Preference REST API specifying only Client Application id and the user id.

@BUC7502
@smoke
Scenario Outline: Delete for a Client Application with one default User Preference personalized and multiples preferences definitions
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "<user>" and userRequest = "<user>"
	And the return http status code is "<responseCodeDelete>" 
	And the preference definition is created for "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |	responseCodeDelete	|		responseCodeGet	    |
		|	upadmin		   	|	200					|		204	   			 	|
		|	lisa		   	|	200					|		204	   			 	|
		|	anne		   	|	200					|		204	   			 	|
		|	alex	   	   	|	200					|		204	   			 	|
		|	mario		   	|	200					|		204	   			 	|
		|	catmainrestapi 	|	200					|		204	   			 	|
		|	user1		   	|	200					|		204	   			 	|
		|	user2		 	|	403					|		403	   			 	|
		|	nouser		  	|	401					|		401	   			 	|
		
@BUC7502
@RegressionTest
Scenario Outline: Delete for a Client Application with one default User Preference personalized and not authorized
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "user1" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "user1" and userRequest = "<user>"
	And the return http status code is "<responseCodeDelete>" 
	And the preference definition is created for "user1"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "should not" be valid for user preference
	|	1	|
	|	2	|
	|	3	|
	And the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |	responseCodeDelete	|		responseCodeGet	    |	
		|	upadmin		 	|	403					|		403	   			 	|	
		|	lisa		 	|	403					|		403	   			 	|	
		|	anne		 	|	403					|		403	   			 	|
		|	alex		 	|	403					|		403	   			 	|
		|	mario		 	|	403					|		403	   			 	|
		|	catmainrestapi	|	403					|		403	   			 	|
		|	user2		 	|	403					|		403	   			 	|		
		|	nouser		  	|	401					|		401	   			 	|	
		
@BUC7502
@RegressionTest
Scenario Outline: Delete for a Client Application with one default User Preference personalized duplicated
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "<user>" and userRequest = "<user>"
	And the return http status code is "<responseCodeDelete>" 
	And the preference definition is created for "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	And the return http status code is "<responseCodeGet>" 
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "<user>" and userRequest = "<user>"
	And the return http status code is "<responseCodeDeleteAfter>" 

	Examples: 
		|	user		    |	responseCodeDelete	|		responseCodeGet	    |	responseCodeDeleteAfter		|
		|	upadmin		   	|	200					|		204	   			 	|	404	   			 			|
		|	lisa		   	|	200					|		204	   			 	|	404	   				 		|
		|	anne		   	|	200					|		204	   			 	|	404	   				 		|
		|	alex	   	   	|	200					|		204	   			 	|	404	   				 		|
		|	mario		   	|	200					|		204	   			 	|	404	   				 		|
		|	catmainrestapi 	|	200					|		204	   			 	|	404	   				 		|
		|	user1		   	|	200					|		204	   			 	|	404	   				 		|
		|	user2		 	|	403					|		403	   			 	|	403							|
		|	nouser		  	|	401					|		401	   			 	|	401							|
		
@BUC7502
@RegressionTest
Scenario Outline: Delete for a Client Application with one default User Preference personalized and application incorrect
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And I change applicationId to "appError"
	When I delete user preference in applicationID for "<user>" and userRequest = "<user>"
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
Scenario Outline: Delete for a Client Application with one default User Preference personalized and username incorrect
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "badUser" and userRequest = "<user>"
	And the return http status code is "<responseCodeDelete>" 
	And the preference definition is created for "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |   responseCodeDelete	|		responseCodeGet	    |	assertion		|
		|	upadmin		   	|	404					|		200	   			 	|	should			|
		|	lisa		   	|	404					|		200	   			 	|	should			|
		|	anne		   	|	404					|		200	   			 	|	should			|
		|	alex	   	   	|	404					|		200	   			 	|	should			|
		|	mario		   	|	404					|		200	   			 	|	should			|
		|	catmainrestapi 	|	404					|		200	   			 	|	should			|
		|	user1		   	|	404					|		200	   			 	|	should			|
		|	user2		 	|	403					|		403	   			 	|	should not		|
		|	nouser		  	|	401					|		401	   			 	|	should not		|
		
@BUC7502
@RegressionTest
Scenario Outline: Delete for a Client Application with one default User Preference personalized and multiples preferences definitions
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "<user>" and userRequest = "<user>" with payload
	|	1	|
	And the return http status code is "<responseCodeDelete>" 
	And the preference definition is created for "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then the return http status code is "<responseCodeGet>" 

	Examples: 
		|	user		    |	responseCodeDelete	|		responseCodeGet	    |
		|	upadmin		   	|	400					|		200	   			 	|
		|	lisa		   	|	400					|		200	   			 	|
		|	anne		   	|	400					|		200	   			 	|
		|	alex	   	   	|	400					|		200	   			 	|
		|	mario		   	|	400					|		200	   			 	|
		|	catmainrestapi 	|	400					|		200	   			 	|
		|	user1		   	|	400					|		200	   			 	|
		|	user2		 	|	403					|		403	   			 	|
		|	nouser		  	|	401					|		401	   			 	|
