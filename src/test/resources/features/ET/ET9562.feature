@release:Release-19
@ET
Feature: ET9562 - PATCH on item characteristics should not be erasing existing values.
				  It was noticed during development of Offer Manager UI that PATCH on item characteristics is not reusing existing created values for numerous optional fields.

					* maxCardinallity;
					* isArray;
					* values (boolean - array);
					* valueType
					* value Range cannot be mandatory
					* properties is being set to default
 
	 			 HX32003 - pscmSystemAttribute (future development)
				 These characteristics properties should be reused from existing ones in case of PATCH and not set to default values when they are not passed in REST request.

@ET9562
@smoke
Scenario Outline: It should be  possible to modify the characteristics with PATCH and upadmin user
	Given I create the PS
	And I create of the PO contains PS with characteristics and "upadmin"
	When I modify characteristics "<characteristics>" with "<value>" of the PO with "upadmin"
	Then I verify the "<responseCode>" and the "<characteristics>" equal "<value>"
	
	Examples:
		|characteristics|value						|responseCode|
		|name			|test_updated				|200		 |
		|valueType		|Number						|200		 |
		|type			|pscmCharacteristicAttribute|200		 |
		|maxCardinality |2							|200		 |

@ET9562
@RegressionTest
Scenario Outline: It should be not possible to modify the characteristics with PATCH other values and upadmin user 		
				  HX31814 - D5 Closed 
	Given I create the PS 
	And I create of the PO contains PS with characteristics and "<user>"
	When I modify characteristics "<characteristics>" with "<value>" of the PO with "<user>"
	Then I verify the "<responseCode>" and the "<characteristics>" equal "<value>"
	
	Examples: 
		|user	|characteristics|value						|responseCode|
		|upadmin|valueType		|Boolean					|200		 |
		|upadmin|valueType		|Date						|200		 |
		|lisa	|valueType		|Boolean					|200		 |
		|lisa	|valueType		|Date						|200		 |
		|anne	|valueType		|Boolean					|200		 |
		|anne   |valueType		|Date						|200		 |
		#|upadmin|type			|pscmInternalAttribute		|200		 |
		#|upadmin|type			|pscmSystemAttribute		|200		 |
		|upadmin|type			|pscmTechnicalAttribute		|200		 |
		|upadmin|type			|lifeCycle					|200		 |
		#|lisa	|type			|pscmInternalAttribute		|200		 |
		#|lisa	|type			|pscmSystemAttribute		|200		 |
		|lisa	|type			|pscmTechnicalAttribute		|200		 |
		|lisa	|type			|lifeCycle					|200		 |
		#|anne	|type			|pscmInternalAttribute		|200		 |
		#|anne	|type			|pscmSystemAttribute		|200		 |
		|anne	|type			|pscmTechnicalAttribute		|200		 |
		|anne	|type			|lifeCycle					|200		 |

@ET9562
@RegressionTest
#Keep the old value or not exists use String as default - Felyphe Oliveira
Scenario Outline: It should be not possible to modify the characteristics with PATCH invalid valueType values and upadmin user 
	Given I create the PS 
	And I create of the PO contains PS with characteristics and "<user>"
	When I modify characteristics "<characteristics>" with "<value>" of the PO with "<user>"
	Then I verify the "<responseCode>" and the "<characteristics>" equal "<value>"
	
	Examples: 
		|user	|characteristics|value						|responseCode|
		|upadmin|valueType		|Invalid					|200		 |
	    |lisa	|valueType		|Invalid					|200		 |
		|anne	|valueType		|Invalid					|200		 |

@ET9562		
@RegressionTest
#Keep the old value or not exists use String as default - Felyphe Oliveira
Scenario Outline: It should be not possible to modify the characteristics with PATCH invalid type values and upadmin user 
				  HX58933 - Closed
	Given I create the PS 
	And I create of the PO contains PS with characteristics and "<user>"
	When I modify characteristics "<characteristics>" with "<value>" of the PO with "<user>"
	Then I verify the "<responseCode>" and UPDATE_FAILED error
	
	Examples: 
		|user	|characteristics|value						|responseCode|
		|upadmin|type			|Invalid					|400		 |
		|lisa	|type			|Invalid					|400		 |
		|anne	|type			|Invalid					|400		 |