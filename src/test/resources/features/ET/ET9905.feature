@release:Release-19.1
@BUC9903
@ET9905
Feature: ET9905 - Assign an price spec to a POP should populate formula field.
				  Assigning a price spec to a POP and the price spec amount is of type formula, the ruleName should be assigned to the formula field. 
				  The current behavior of changing ecmPrice characteristic formula is already setting the formula fields inside POP. This should be maintained.
				  Also, in catalog designer, if user explicitly defined a formula, this formula should be used.

@smoke
Scenario Outline: Return formula value with Reuse ON or OFF when GET in POP with upadmin and lisa users
	Given I create the Price Specification with formula as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I create the POP with Price Specification type formula with "<reuse>" and "<user>"
	Then I verify the formula value was reused or not
	
	Examples: 
		|	user		|	reuse		|
		|	upadmin		|	Reuse_ON	|
		|	upadmin		|	Reuse_OFF	|
		|	lisa		|	Reuse_ON	|

@RegressionTest
Scenario Outline: Return formula value with Reuse ON or OFF when GET in POP with upadmin and lisa users when project activate
	Given I create the Price Specification with formula as "upadmin"
	And I create the PO with Characteristics and POP with "<user>"
	When I create the POP with Price Specification type formula with "<reuse>" and "<user>"
	And I activate the project
	Then I verify the formula value was reused or not
	
	Examples: 
		|	user		|	reuse		|
		|	upadmin		|	Reuse_ON	|
		|	upadmin		|	Reuse_OFF	|
		|	lisa		|	Reuse_ON	|
