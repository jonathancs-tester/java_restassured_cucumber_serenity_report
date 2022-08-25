@release:Release-19
@ET
Feature: ET9505 - Item GET collection API and minimal=short should be enhanced to consider project directive.
				  It's requires that item GET by collection API to have the same behavior as GET by id in the scenario where a project directive is used.

				  Acceptance Criteria:
					* Product Offering GET by ID passing ?project=projId returns all active versions of that PO and any definition version that belongs to the specified project;
					* Product Offering GET by collection passing ?project=projId should return all POs that are in ACT state as well as any DEF that belongs to the project passed;
					* GET using minimal=short and project=projId should follow same idea of returning only ACT specification or DEF that belongs to the project passed.

@ET9505
@smoke
Scenario Outline: It should be possible to GET all product offerings using parameter minimal=short without characteristics 
	Given there is a product offering 
	When the user is logged as "<user>" 
	And the "minimal" = "short" parameter is used "<minimal>"
	And a GET operation is made on "Product Offering" API 
	Then the return http status code is "<responseCode>" 
	
	Examples: 
		|	user		   |	minimal			|	responseCode	|
		|	lisa		   |	true			|	200				|
		|	lisa		   |	false			|	200				|
		|	upadmin	   	   |	true			|	200				|
		|	upadmin		   |	false			|	200				|
