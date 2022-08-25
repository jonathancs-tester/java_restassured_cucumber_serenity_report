@release:Release-19.1
@BUC9903
@ET9888
Feature: ET9888 - Add support at product offfering API to expand price and characteristics using minimal=short..
				  Expand parameter should control the minimal content.
				  GET {{baseUrl}}/CatalogManagement/v2/productOffering/?versions.specificationSubtype=device&expand=ProductOfferingPrice,Characteristics&minimal=short.
					* Minimal short will say that only one version has to be returned/controls which version will be returned;
					* expand=ProductOfferingPrice will expand the POP inside that version;
					* expand=characteristic will expand all characteristics of that version.
				  Remembering that minimal=short has it's own logic to return a version:
					* with project - returns last DEF in that project if any or last ACT one;
					* without project - returns last DEF if any or last ACT.

@smoke
Scenario Outline: It should be possible expand filter using the ProductOfferingPrice and Characteristics with minimal=short and project DEF
	Given I create the Price Specification without formula as "<user>"
	And I create the PO with Characteristics and POP with "<user>"
	When I GET request using "<expand>" with "<project>" and "<user>"
	Then I verify if return "<expand>" information
	
	Examples: 
		|	user		   |	expand									|	project		|
		|	lisa		   |	ProductOfferingPrice					|	true		|
		|	lisa		   |	ProductOfferingPrice					|	false		|
		|	lisa		   |	Characteristic							|	true		|
		|	lisa		   |	Characteristic							|	false		|
		|	lisa		   |	ProductOfferingPrice,Characteristic		|	true		|
		|	lisa		   |	ProductOfferingPrice,Characteristic		|	false		|
	
@ET9888
@RegressionTest
Scenario Outline: It should be possible expand filter using the ProductOfferingPrice and Characteristics with minimal=short and project ACT
	Given I create the Price Specification without formula as "<user>"
	And I create the PO with Characteristics and POP with "<user>"
	And I activate the project with id
	When I GET request using "<expand>" with "<project>" and "<user>"
	Then I verify if return "<expand>" information
	
	Examples: 
		|	user		   |	expand									|	project		|
		|	lisa		   |	ProductOfferingPrice					|	true		|
		|	lisa		   |	Characteristic							|	true		|
		|	lisa		   |	ProductOfferingPrice,Characteristic		|	true		|
		
@ET9888
@RegressionTest
Scenario Outline: It should be possible expand filter using the ProductOfferingPrice and Characteristics with minimal=short and others users
	Given I create the Price Specification without formula as "<user>"
	And I create the PO with Characteristics and POP with "<user>"
	And I activate the project with id
	When I GET request using "<expand>" with "<project>" and "<user>"
	Then I verify if return "<expand>" information
	
	Examples: 
		|	user		   |	expand									|	project		|
		|	anne		   |	ProductOfferingPrice					|	true		|
		|	anne		   |	Characteristic							|	true		|
		|	anne		   |	ProductOfferingPrice,Characteristic		|	true		|
		|	tom			   |	ProductOfferingPrice					|	true		|
		|	tom			   |	Characteristic							|	true		|
		|	tom			   |	ProductOfferingPrice,Characteristic		|	true		|
