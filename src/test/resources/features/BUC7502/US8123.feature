@release:Release-18.1
@BUC7502 
@US8123
Feature: US8123 - As a Client Application User, I want to be able to read my personalized User Preferences from all applications via REST API.
				  This user story is about implementing a REST API which allows client applications to read all User Preferences from all applications.
				  It is possible to use GET method from the new User Preference REST API specifying the user id.


@smoke
Scenario Outline: GET personalized User Preference from all applications
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And a GET operation is made on "All User Preference" API
	Then I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2,def3|
	|	app2 |	def1,def2,def3|
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
		
@smoke
Scenario Outline: GET personalized User Preference from all applications with expand equal true
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And the "expand" = "true" parameter is used
	And a GET operation is made on "All User Preference" API
	Then I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2,def3|
	|	app2 |	def1,def2,def3|
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
		
@smoke 
Scenario Outline: GET personalized User Preference from all applications with double filter 
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	Then the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And the "ids" = definition id parameter is used
	|	1	|
	|	2	|
	And a GET operation is made on "All User Preference" API
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2|
	|	app2 |	def1,def2|
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
		
@smoke 
Scenario Outline: GET personalized User Preference from all applications with double filter and expand equal true
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	Then the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And the "expand" = "true" parameter is used
	And the "ids" = definition id parameter is used
	|	1	|
	|	2	|
	And a GET operation is made on "All User Preference" API
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2|
	|	app2 |	def1,def2|
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
		
@RegressionTest 
Scenario Outline: GET personalized User Preference from all applications with single filter and expand equal true
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	Then the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And the "expand" = "true" parameter is used
	And the "ids" = definition id parameter is used
	|	3	|
	And a GET operation is made on "All User Preference" API
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def3|
	|	app2 |	def3|
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
		
@RegressionTest 
Scenario Outline: GET personalized User Preference from all applications with single filter
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	Then the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And the "ids" = definition id parameter is used
	|	3	|
	And a GET operation is made on "All User Preference" API
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def3|
	|	app2 |	def3|
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
		
@RegressionTest
Scenario Outline: GET personalized User Preference from all applications with single wrong filter
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	Then the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And the "ids" = definition id parameter is used
	|	4	|
	And a GET operation is made on "All User Preference" API
	And I verify if response "should not" be valid for user preference with multiples Applications 
	|	app1 |	def0|
	|	app2 |	def0|
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	responseCode	|
		|	upadmin		   |	200				|
		|	lisa		   |	200				|
		|	anne		   |	200				|
		|	alex	   	   |	200				|
		|	mario		   |	200				|
		|	catmainrestapi |	200				|
		|	user1		   |	200				|
		|	user2		   |	403				|
		|	nouser		   |	401				|
		
@RegressionTest
Scenario Outline: GET personalized User Preference from all applications with single wrong filter and expand equal true
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	Then the user is logged as "<user>"
	And the preference definition is created for "<user>"
	And the "expand" = "true" parameter is used
	And the "ids" = definition id parameter is used
	|	4	|
	And a GET operation is made on "All User Preference" API
	And I verify if response "should not" be valid for user preference with multiples Applications 
	|	app1 |	def0|
	|	app2 |	def0|
	And the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	responseCode	|
		|	upadmin		   |	200				|
		|	lisa		   |	200				|
		|	anne		   |	200				|
		|	alex	   	   |	200				|
		|	mario		   |	200				|
		|	catmainrestapi |	200				|
		|	user1		   |	200				|
		|	user2		   |	403				|
		|	nouser		   |	401				|
		
@RegressionTest 
Scenario Outline: GET personalized User Preference from all applications with wrong user 
	Given I create 3 applications with "<user>"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "<user>"
	And I create an user preference in applicationID for "<user>" and userRequest = "<user>"
	And the user is logged as "<user>"
	And the preference definition is created for "badUser"
	And a GET operation is made on "All User Preference" API
	And I verify if response "should not" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2,def3|
	|	app2 |	def1,def2,def3|
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
		
@RegressionTest
Scenario Outline: GET personalized User Preference from all applications with incorrect username
	Given I create 3 applications with "user1"
	And I create 2 users 
	And I create 3 preferences definitions with default_value = "10.1" 
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "user1"
	And I create an user preference in applicationID for "user1" and userRequest = "user1"
	And I change applicationId to "app2"
	And I delete any existing default user preference for "app_pref_default_user" 
	And I create an user preference in applicationID for "app_pref_default_user" and userRequest = "upadmin"
	And I delete any existing default user preference for "user1"
	And I create an user preference in applicationID for "user1" and userRequest = "user1"
	And the user is logged as "<user>"
	And the preference definition is created for "user1"
	And the "expand" = "true" parameter is used
	And a GET operation is made on "All User Preference" API
	Then I verify if response "should not" be valid for user preference with multiples Applications 
	|	app1 |	def0|
	|	app2 |	def0|
	And the return http status code is "<responseCode>" 

	Examples: 
		|	user		   |	responseCode	|
		|	upadmin		   |	403				|
		|	lisa		   |	403				|
		|	anne		   |	403				|
		|	alex	   	   |	403				|
		|	mario		   |	403				|
		|	catmainrestapi |	403				|
		|	user2		   |	403				|
		|	nouser		   |	401				|