@release:Release-18.1
@BUC7502 
@US7921
Feature: US7921 - As a Catalog Application User, I want to be able to read all default User Preferences from all applications via REST API
			      This user story is about implementing a REST API which allows client applications to read all default User Preferences from all applications. 
			      It is possible to use GET method from the new User Preference REST API specifying the application default user id. 

@smoke
Scenario Outline: GET default User Preference from all applications
	Given I have "3" user preferences created for "app1"
	And I have "3" user preferences created for "app2"
	When the user is logged as "<user>"
	And the preference definition is created for "app_pref_default_user"
	And a GET operation is made on "All User Preference" API
	Then the return http status code is "<responseCode>"  
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2,def3|
	| 	app2 |	def1,def2,def3|
	
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
Scenario Outline: GET default User Preference from all applications with expand equal true
	Given I have "3" user preferences created for "app1"
	And I have "3" user preferences created for "app2"
	When the user is logged as "<user>"
	And the preference definition is created for "app_pref_default_user"
	And the "expand" = "true" parameter is used
	And a GET operation is made on "All User Preference" API
	Then the return http status code is "<responseCode>"  
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2,def3|
	| 	app2 |	def1,def2,def3|
	
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
Scenario Outline: GET default User Preference from all applications with double filter 
	Given I have "3" user preferences created for "app1"
	And I have "3" user preferences created for "app2"
	When the user is logged as "<user>"
	And the preference definition is created for "app_pref_default_user"
	And the "ids" = definition id parameter is used
	|	1	|
	|	2	|
	And a GET operation is made on "All User Preference" API
	Then the return http status code is "<responseCode>"  
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2|
	| 	app2 |	def1,def2|
	
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
Scenario Outline: GET default User Preference from all applications with double filter and expand equal true
	Given I have "3" user preferences created for "app1"
	And I have "3" user preferences created for "app2"
	When the user is logged as "<user>"
	And the preference definition is created for "app_pref_default_user"
	And the "ids" = definition id parameter is used
	|	1	|
	|	2	|
	And the "expand" = "true" parameter is used
	And a GET operation is made on "All User Preference" API
	Then the return http status code is "<responseCode>"  
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1,def2|
	| 	app2 |	def1,def2|
	
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
Scenario Outline: GET default User Preference from all applications with single filter 
	Given I have "3" user preferences created for "app1"
	And I have "3" user preferences created for "app2"
	When the user is logged as "<user>"
	And the preference definition is created for "app_pref_default_user"
	And the "ids" = definition id parameter is used
	|	1	|
	And a GET operation is made on "All User Preference" API
	Then the return http status code is "<responseCode>"  
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1|
	| 	app2 |	def1|
	
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
Scenario Outline: GET default User Preference from all applications with single filter and expand equal true
	Given I have "3" user preferences created for "app1"
	And I have "3" user preferences created for "app2"
	When the user is logged as "<user>"
	And the preference definition is created for "app_pref_default_user"
	And the "ids" = definition id parameter is used
	|	1	|
	And the "expand" = "true" parameter is used
	And a GET operation is made on "All User Preference" API
	Then the return http status code is "<responseCode>"  
	And I verify if response "<assertion>" be valid for user preference with multiples Applications 
	|	app1 |	def1|
	| 	app2 |	def1|
	
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
Scenario Outline: GET default User Preference from all applications with single wrong filter
	Given I have "3" user preferences created for "app1"
	And I have "3" user preferences created for "app2"
	When the user is logged as "<user>"
	And the preference definition is created for "app_pref_default_user"
	And the "ids" = definition id parameter is used
	|	4	|
	And a GET operation is made on "All User Preference" API
	Then the return http status code is "<responseCode>"  
	And I verify if response "<assertion>" be valid but has no preferences in each application
	|	app1 |
	| 	app2 |
	
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
		