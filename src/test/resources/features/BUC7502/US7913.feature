@release:Release-18.1 
Feature: US7913 -  As a Client Application, I want to be able to partially update default User Preference via REST API.
				   This user story is about implementing a REST API which allows client applications to update some default application User Preferences. 

@BUC7502
@smoke
Scenario Outline: Update for client application with one default User Preference using patch
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I update user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with patch
	|	1	|
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should      |	200				|
		|	anne		   |	should     	|	200				|
		|	alex	   	   |	should     	|	200				|
		|	mario		   |	should     	|	200				|
		|	catmainrestapi |	should     	|	200				|
		|	user1		   |	should     	|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502
@RegressionTest
Scenario Outline: Update for client application with multiples default User Preferences using patch
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I update user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with patch
	|	1	|
	|	2	|
	|	3	|
	And the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	|	2	|
	|	3	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should     	|	200				|
		|	anne		   |	should     	|	200				|
		|	alex	   	   |	should     	|	200				|
		|	mario		   |	should     	|	200				|
		|	catmainrestapi |	should     	|	200				|
		|	user1		   |	should     	|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502
@smoke 
Scenario Outline: Update for client application with multiples default User Preferences alter specific using patch
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I update user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with patch
	|	1	|
	|	3	|
	And the user is logged as "<user>"
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	|	3	|
	|	2	|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should not 	|	403				|
		|	anne		   |	should not 	|	403				|
		|	alex	   	   |	should not 	|	403				|
		|	mario		   |	should not  |	403				|
		|	catmainrestapi |	should not 	|	403				|
		|	user1		   |	should not	|	403				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
					
@BUC7502 
@smoke
Scenario Outline: Update for client application with multiples default User Preferences alter incorrect preference definition using patch
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I update user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with patch
	|	4	|
	And the user is logged as "<user>"
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode	|
		|	upadmin		   |	400				|
		|	lisa		   |	403				|
		|	anne		   |	403				|
		|	alex	   	   |	403				|
		|	mario		   |	403				|
		|	catmainrestapi |	403				|
		|	user1		   |	403				|
		|	user2		   |	403				|
		|	nouser		   |	401				|
		
@BUC7502 
@RegressionTest
Scenario Outline: Update for client application with multiples default User Preferences alter null preference definition using patch
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I update user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with patch
	|	-1	|
	And the user is logged as "<user>"
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode	|
		|	upadmin		   |	400				|
		|	lisa		   |	403				|
		|	anne		   |	403				|
		|	alex	   	   |	403				|
		|	mario		   |	403				|
		|	catmainrestapi |	403				|
		|	user1		   |	403				|
		|	user2		   |	403				|
		|	nouser		   |	401				|
		
@BUC7502 
@RegressionTest
Scenario Outline: Update for client application with multiples default User Preferences alter specific and username incorrect using patch
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When I update user preference in applicationID for "wrongUser" and userRequest = "<user>" with patch
	|	1	|
	|	3	|
	And the user is logged as "<user>"
	And the return http status code is "<responseCode>" 

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
Scenario Outline: Update for client application with multiples default User Preferences alter specific and application incorrect using patch
	Given I create 1 application with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I change applicationId to "appError" 
	When I update user preference in applicationID for "app_pref_default_user" and userRequest = "<user>" with patch
	|	1	|
	|	3	|
	And the user is logged as "<user>"
	And the return http status code is "<responseCode>" 

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
		