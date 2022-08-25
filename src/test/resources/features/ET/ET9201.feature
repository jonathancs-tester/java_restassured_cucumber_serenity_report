@release:Release-19
@ET
Feature: ET9201 - New Privileges to edit Market Segment Rules for BSS Marketing Managers.
				  The requirements are split in the following items:
					* It must be possible for Lisa and Anne to edit market segment rules and also to assign them to a PO;
					* It must be possible for Lisa and Anne to assign availability, eligibility and validation rules, but not to change the values.
@ET9201
@smoke
Scenario Outline: It should be possible for Lisa and Anne to edit market segment rules and also to assign them to a PO
	Given there is a product offering with Market Segment
	Then I can Edit and Assign the Market Segment Rules with "<user>" and "<responseCode>"
	
	Examples: 
		|	user		   |	responseCode	|
		|	lisa		   |	201				|
		|	anne	   	   |	201				|
		
@ET9201		
@RegressionTest
Scenario Outline: It should be possible for Lisa and Anne to assign rules and to change the values
				  #According the BE team, should be change the value in BE but in FE not is possible.
	Given there is a product offering with "<type>" rule
	Then I change name the rules to "<type>" with "<user>" and "<responseCode>"
	
	Examples: 
		|	user		   |	responseCode	| 		type 		|
		|	lisa		   |	200				|	eligibility		|
		|	anne		   |	200				|	eligibility		|
		|	lisa	   	   |	200				|	availability	|
		|	anne		   |	200				|	availability	|
		|	lisa	   	   |	200				|	validation		|
		|	anne		   |	200				|	validation		|
		
@ET9201
@RegressionTest
Scenario Outline: It should be possible for Lisa and Anne to assign rules
	Given there is a product offering with "<type>" rule and "<user>"
	Then I verify if "<user>" status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	responseCode	| 		type 		|
		|	lisa		   |	200				|	eligibility		|
		|	anne		   |	200				|	eligibility		|
		|	lisa	   	   |	200				|	availability	|
		|	anne		   |	200				|	availability	|
		|	lisa	   	   |	200				|	validation		|
		|	anne		   |	200				|	validation		|
		
