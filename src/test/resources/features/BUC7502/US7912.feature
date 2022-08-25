@release:Release-18.1 
Feature: US7912 - As a Client Application, I want to be able to read default User Preference via REST API.
				  This user story is about implementing a REST API which allows client applications to read default application User Preferences. 

@BUC7502
@smoke 
Scenario Outline: GET operation valid user
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "5.0" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When the user is logged as "<user>"
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
		|	mario		   |	should 		|	200				|
		|	catmainrestapi |	should		|	200				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
	
@BUC7502 
@RegressionTest 
Scenario Outline: GET operation valid user with expand equal true
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "5.0" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When the user is logged as "<user>"
	And the "expand" = "true" parameter is used 
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
		|	mario		   |	should 		|	200				|
		|	catmainrestapi |	should		|	200				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502 
@smoke
Scenario Outline: GET operation valid user with multiple default user preference
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "5.0"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When the user is logged as "<user>"
	And the "ids" = definition id parameter is used
	|	1	|
	|	2	|
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	|	2	|
	And the return http status code is "<responseCode>"  
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should		|	200				|
		|	anne		   |	should		|	200				|
		|	alex	   	   |	should		|	200				|
		|	mario		   |	should 		|	200				|
		|	catmainrestapi |	should		|	200				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502
@RegressionTest  
Scenario Outline: GET operation valid user with multiple default user preference and expand equal true
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "5.0"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When the user is logged as "<user>"
	And the "ids" = definition id parameter is used
	|	1	|
	|	2	|
	And the "expand" = "true" parameter is used 
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	1	|
	|	2	|
	And the return http status code is "<responseCode>"  
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should		|	200				|
		|	anne		   |	should		|	200				|
		|	alex	   	   |	should		|	200				|
		|	mario		   |	should 		|	200				|
		|	catmainrestapi |	should		|	200				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502
@RegressionTest 
Scenario Outline: GET operation valid user with multiple default user preference and expand equal true return one specific
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "5.0"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When the user is logged as "<user>"
	And the "ids" = definition id parameter is used
	|	3	|
	And the "expand" = "true" parameter is used 
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	3	|
	And the return http status code is "<responseCode>"  
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should		|	200				|
		|	anne		   |	should		|	200				|
		|	alex	   	   |	should		|	200				|
		|	mario		   |	should 		|	200				|
		|	catmainrestapi |	should		|	200				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502
@RegressionTest  
Scenario Outline: GET operation valid user with multiple default user preference return one specific
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "5.0"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When the user is logged as "<user>"
	And the "ids" = definition id parameter is used
	|	3	|
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	3	|
	And the return http status code is "<responseCode>"  
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should		|	200				|
		|	lisa		   |	should		|	200				|
		|	anne		   |	should		|	200				|
		|	alex	   	   |	should		|	200				|
		|	mario		   |	should 		|	200				|
		|	catmainrestapi |	should		|	200				|
		|	user1		   |	should		|	200				|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502 
@RegressionTest 
Scenario Outline: GET operation valid user with multiple default user preference return inexistent
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "5.0"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	When the user is logged as "<user>"
	And the "ids" = definition id parameter is used
	|	4	|
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	0 |
	And the return http status code is "<responseCode>"  
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	200				|
		|	lisa		   |	should not	|	200				|
		|	anne		   |	should not	|	200				|
		|	alex	   	   |	should not	|	200				|
		|	mario		   |	should not 	|	200				|
		|	catmainrestapi |	should not	|	200				|
		|	user1		   |	should not	|	200 			|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502  
@RegressionTest 
Scenario Outline: The attempt to retrieve a application with id incorrect must have an error response code 
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "5.0" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I change applicationId to "appError" 
	When the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	0 |
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	404				|
		|	lisa		   |	should not	|	404				|
		|	anne		   |	should not	|	404				|
		|	alex	   	   |	should not	|	404				|
		|	mario		   |	should not	|	404				|
		|	catmainrestapi |	should not	|	404				|
		|	user1		   |	should not	|	404 			|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		
@BUC7502  
@RegressionTest
Scenario Outline: The attempt to retrieve a username with id incorrect must have an error response code 
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 1 preference definition with default_value = "5.0" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And the preference definition is created for "userError" 
	When the user is logged as "<user>"
	And a GET operation is made on "User Preference" API
	Then I verify if response "<assertion>" be valid for user preference
	|	0 |
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	assertion	|	responseCode	|
		|	upadmin		   |	should not	|	404				|
		|	lisa		   |	should not	|	404				|
		|	anne		   |	should not	|	404				|
		|	alex	   	   |	should not	|	404				|
		|	mario		   |	should not	|	404				|
		|	catmainrestapi |	should not	|	404				|
		|	user1		   |	should not	|	404 			|
		|	user2		   |	should not	|	403				|
		|	nouser		   |	should not	|	401				|
		