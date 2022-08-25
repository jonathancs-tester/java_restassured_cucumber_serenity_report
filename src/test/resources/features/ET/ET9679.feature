@release:Release-19
@ET
@ET9679
Feature: ET9679 - Condition rules from price specification should be shown under product offering price.
				  Doing a GET in a POP, all condition rules from its price specification should be shown in the response.

				  Steps:
					* Create a price spec;
					* Create a condition rule in the price spec;
					* Create a PO;
					* Create a POP in that PO;
					* Do a GET in that POP;
					* The condition rule should be seen in the response
					 
				  Script cwt_pcmaintapi.itemRead should fetch from DB all rules used in the given PO and then map it to cwt_pcmaintapi.item.
				  Event Handler cwl_ecm.mapSpecificationtoSIDSpecification should correctly map rules from cwt_pcmaintapi.item to ecm_sid.ProductDomain.ProductOfferingABE.ProductOffering.

@smoke
Scenario Outline: Doing a GET in a POP, all condition rules from its price specification should be shown in the response
	Given I create the Price Specification with Conditional Rule and "<user>"
	And I create the PO that contains POP with "<user>"
	When I GET request of the POP
	Then I verify if response exists Conditional Rule created
	
	Examples: 
		| user		|   
	  	| upadmin	|		

@RegressionTest
Scenario Outline: Doing a GET in a POP, all condition rules from its price specification should be shown in the response
	Given I create the Price Specification with Conditional Rule and "<user>"
	Then I verify if not possible create Conditional Rule
	
	Examples: 
		| user		| 
		| lisa		|			
		| anne		| 