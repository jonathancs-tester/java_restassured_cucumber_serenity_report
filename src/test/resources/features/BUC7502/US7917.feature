@release:Release-18.1 
Feature: US7917 - As a Client Application User, I want to be able to read my personalized User Preference via REST API.
			      This user story is about implementing a REST API which allows client applications users to read their personalized application User Preferences. 
			      It is possible to use GET method from the new User Preference REST API specifying the Client Application id and the user id.

@BUC7502
@smoke
Scenario Outline: GET for a Client Application with one User Preference personalized
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
Scenario Outline: GET for a Client Application with one User Preference personalized and expand equal true
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	When I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And the "expand" = "true" parameter is used
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
@smoke
Scenario Outline: GET for a Client Application with one User Preference personalized not authorized
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	When I create an user preference in applicationID for "user1" and userRequest = "<user>"
	And the user is logged as "<user>"
	And the preference definition is created for "user1"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	403				|
		|	lisa		   |	should not	|	403				|
		|	anne		   |	should not	|	403				|
		|	alex	   	   |	should not	|	403				|
		|	mario		   |	should not	|	403				|
		|	catmainrestapi |	should not	|	403				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502
@smoke
Scenario Outline: GET for a Client Application with one User Preference personalized and user inexistent
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	When I create an user preference in applicationID for "badUser" and userRequest = "<user>"
	And the user is logged as "badUser"
	And the preference definition is created for "badUser"
	And a GET operation is made on "User Preference" API
	Then I verify if response "should not" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		  	|	responseCode	|
		|	upadmin		   	|	401				|
		|	lisa		   	|	401				|
		|	anne		   	|	401				|
		|	alex	   	   	|	401				|
		|	mario		   	|	401				|
		|	catmainrestapi 	|	401				|
		|	user1		   	|	401				|
		|	user2		   	|	401				|
		|	nouser		   	|	401				|
				
@BUC7502
@RegressionTest
Scenario Outline: GET for a Client Application with one User Preference personalized and user inexistent
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	When I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And I change applicationId to "appError"
	And the preference definition is created for "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "should not" be valid for user preference
	|	1	|
	| 	2 	|
	|	3	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		  	|	responseCode	|
		|	upadmin		   	|	404				|
		|	lisa		   	|	404				|
		|	anne		   	|	404				|
		|	alex	   	   	|	404				|
		|	mario		   	|	404				|
		|	catmainrestapi 	|	404				|
		|	user1		   	|	404				|
		|	user2		   	|	403				|
		|	nouser		   	|	401				|