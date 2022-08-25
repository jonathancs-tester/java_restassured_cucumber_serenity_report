@release:Release-19
@ET
Feature: ET9565 - Association Type GET REST API improvement to use filter on finder.
				  An improvement in the management api is required.
				  Need to create a parsing input in associationType Get bind, more specificaly inside 'cws_ecm.handleReadAssociationType', in order to map query parameter into the request and then from the request into the searchDS.
				  The finder responsible to read association types is 'cwt_pc.cwt_relationTypeFinder', this finder already support the parameters.
				  Example of a get for association types:   /ecm/CatalogManagement/v2/associationType/?category=Image.
				  
				  Acceptance Criteria:
				  	* GET request should be return in less than 1 second.

@ET9565
@RegressionTest
Scenario Outline: I want to validate that GET operations Association Type with category filters
	When I GET a Association Type with "<user>" using the filter "<category>"
	Then I am able to validate "<category>" filter 
	And the verify status code is "<statusCode>"
	
	Examples:
		| user 	  | category					| statusCode |
		| upadmin | Attachment					| 200		 |
		| upadmin | Action						| 200		 |
		| upadmin | Description					| 200		 |
		| upadmin | Dimensions					| 200		 |
		| upadmin | Group						| 200		 |
		| upadmin | Image						| 200		 |
		| upadmin | Item						| 200		 |
		| upadmin | Rule						| 200		 |
		| upadmin | ItemAttribute				| 200		 |
		| upadmin | Price						| 200		 |
		| upadmin | ChargeTypeDesc				| 200		 |
		| upadmin | AttrRelationType			| 200		 |
		| upadmin | InfoModelAttrib				| 200		 |
		| upadmin | ValueUseRestric				| 200		 |
		| upadmin | PropertyPermiss				| 200		 |
		| upadmin | CatalogRule					| 200		 |
		| upadmin | Restriction					| 200		 |
		| anne	  | Price						| 200		 |
		| anne    | AttrRelationType			| 404		 |
		| mario	  | Price						| 200		 |
		| mario   | AttrRelationType			| 404		 |
		