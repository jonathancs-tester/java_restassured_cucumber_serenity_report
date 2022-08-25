@release:Release-19
@ET
@ET9702
Feature: ET9702 - Support depth parameter for GET PO with expand=ps or expand=true.
		   	      Need to add support for depth parameter on GET PO management REST API when expanding product specifications. 
				  Use following URL as an example: {{location}}/ecm/catalogManagement/v2/productOffering/{{itemCode}}?expand=productSpecification&depth=1.
				  Depth parameter shall folow same behavior from product category API.
 
				  If depth parameter is set as:
				  	-1: All product specifications must be returned;
					0: Only root level of product specifications must be returned;
					1: Only root and first level of product specifications must be returned;
					and so on;
 				  This behavior must be applied for expand=productSpecification and for expand=true.

@smoke
Scenario Outline: The GET request in a PO should be response with PS or PO relation of the level in depth with upadmin
	Given one PO canvas with 3 levels 
	When GET request with "upadmin" of the PO with "<expand>" and "<depth>"
	Then I verify if response exists PS or PO of the level in "<expand>" and "<depth>"
	
	Examples: 
		| expand					|  depth  	|   
		| ProductSpecification		|  -1		|
		| ProductSpecification		|   0		|	
		| ProductSpecification		|   1 		|
		| ProductSpecification		|   2		|
		| true						|  -1		|
		| true						|   0		|	
		| true						|   1 		|
		| true						|   2		|
	
@RegressionTest
Scenario Outline: The GET request in a PO should be response with PS or PO relation of the level in depth with lisa and anne user
	Given one PO canvas with 3 levels 
	When GET request with "upadmin" of the PO with "<expand>" and "<depth>"
	Then I verify if response exists PS or PO of the level in "<depth>" and "upadmin"
	
	Examples: 
		| user	| expand					|  depth  	|   
		| lisa	| ProductSpecification		|  -1		|
		| lisa	| ProductSpecification		|   0		|	
		| lisa	| ProductSpecification		|   1 		|
		| lisa	| ProductSpecification		|   2		|
		| lisa	| true						|  -1		|
		| lisa	| true						|   0		|	
		| lisa	| true						|   1 		|
		| lisa	| true						|   2		|
		| anne	| ProductSpecification		|  -1		|
		| anne	| ProductSpecification		|   0		|	
		| anne	| ProductSpecification		|   1 		|
		| anne	| ProductSpecification		|   2		|
		| anne	| true						|  -1		|
		| anne	| true						|   0		|	
		| anne	| true						|   1 		|
		| anne	| true						|   2		|

@RegressionTest
Scenario: It should not possible to create the PO with more than 32 characters
		  HX58169 - Closed
	Given one PO canvas with more than 32 characters in Id
	Then I verify if response not have more than 32 characters error