@release:Release-19
@ET
Feature: ET9503 - Activity Log should be enhanced to use expand=user.
				  As part of Offer Manager UI it is required that management API for activity log accepts new value for expand=user (that shall work when expand=true is called).
				  Expand=user option shall retrieve additional information (Name) about the user who did a particular change. Today, activity log only retrieves id information for a user.
				  It's important to keep performance in mind so only one call to get all user names are made and mapped to the activity log, avoiding more hits to database.

				  Acceptance Criteria:
				  	* Expand=user option shall retrieve additional information (Name) about the user who did a particular change. Today, activity log only retrieves id information for a user.

@ET9503
@smoke
Scenario Outline: It should be possible to GET all product with projectCreatedDate paramenter
	Given I submit "<Action>" a "Product Offering" as "<user>"
	And a GET operation is made on expand = "user" with "<user>"
	Then I verify if response "<username>" username parameter
	
	Examples: 
		| Action |	user		   |	username					|
		| post   |	lisa		   |	lisa						|
		| put    |	lisa		   |	lisa						|
		| delete |	lisa		   |	lisa						|
		| post   |	upadmin	   	   |	user profile administrator	|
		| put    |	upadmin		   |	user profile administrator	|
		| delete |	upadmin		   |	user profile administrator	|
		| post   |	anne	   	   |	anne						|
		| put    |	anne		   |	anne						|
		| delete |	anne		   |	anne						|
