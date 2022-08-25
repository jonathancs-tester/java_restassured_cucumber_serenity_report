@release:Release-18.1 
Feature: US7915 - As a Client Application, I want to be able to delete all default User Preferences via REST API.
				  This user story is about implementing a REST API which allows client applications to delete default application User Preferences.  

@BUC7502
@smoke 
Scenario Outline: Delete for client application with one default User Preference
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I delete user preference in applicationID for "app_pref_default_user" and userRequest = "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	204				|
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
Scenario Outline: Delete for client application with multiples default User Preferences
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I delete user preference in applicationID for "app_pref_default_user" and userRequest = "<user>"
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	|	2	|
	|	3	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	204				|
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
Scenario Outline: Delete for client application with multiples default User Preferences alter specific and username incorrect
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I delete user preference in applicationID for "wrongUser" and userRequest = "<user>"
	And the user is logged as "<user>"
	Then the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode|
		|	upadmin		   |	404			|
		|	lisa		   |	404			|
		|	anne		   |	404			|
		|	alex	   	   |	404			|
		|	mario		   |	404			|
		|	catmainrestapi |	404			|
		|	user1		   |	404			|
		|	user2		   |	403			|
		|	nouser		   |	401			|
		
@BUC7502
@RegressionTest 
Scenario Outline: Delete for client application with multiples default User Preferences alter specific and application incorrect
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I change applicationId to "appError" 
	When I delete user preference in applicationID for "app_pref_default_user" and userRequest = "<user>"
	And the user is logged as "<user>"
	Then the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode|
		|	upadmin		   |	404			|
		|	lisa		   |	403			|
		|	anne		   |	403			|
		|	alex	   	   |	403			|
		|	mario		   |	403			|
		|	catmainrestapi |	403			|
		|	user1		   |	403			|
		|	user2		   |	403			|
		|	nouser		   |	401			|
	
@BUC7502 
@RegressionTest
Scenario Outline: Delete for client application with multiples default User Preferences and payload  with multiples preferences definitions for deleted
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with payload
	|	1	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode|
		|	upadmin		   |	400			|
		|	lisa		   |	400			|
		|	anne		   |	400			|
		|	alex	   	   |	400			|
		|	mario		   |	400			|
		|	catmainrestapi |	400			|
		|	user1		   |	400			|
		|	user2		   |	403			|
		|	nouser		   |	401			|
		
@BUC7502 
@RegressionTest	
Scenario Outline: Delete for client application with one default User Preferences and payload with one preference definition for deleted
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with payload
	|	1	|
	|	2	|
	|	3	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode|
		|	upadmin		   |	400			|
		|	lisa		   |	400			|
		|	anne		   |	400			|
		|	alex	   	   |	400			|
		|	mario		   |	400			|
		|	catmainrestapi |	400			|
		|	user1		   |	400			|
		|	user2		   |	403			|
		|	nouser		   |	401			|

@BUC7502
@RegressionTest
Scenario Outline: Delete for client application with one default User Preferences and payload 
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 1 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And the user is logged as "<user>"
	When I delete user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with payload
	|	1	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode|
		|	upadmin		   |	400			|
		|	lisa		   |	400			|
		|	anne		   |	400			|
		|	alex	   	   |	400			|
		|	mario		   |	400			|
		|	catmainrestapi |	400			|
		|	user1		   |	400			|
		|	user2		   |	403			|
		|	nouser		   |	401			|