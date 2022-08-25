@release:Release-19
@ET
Feature: ET9504 - Activity Log should be enhanced to save open project information.

				  Acceptance Criteria:
				  	* Every time an action is being made in an entity that requires pcmaintapi script openProject to be called, a log should be saved in activity log with operationType Open for specificationType="project".

@ET9504
@RegressionTest
Scenario Outline: Validate that Activity Log is incremented after Add/Upd/Del operation of resources
	Given the "<user>" tries to "<action>" a "<resource>"
	When I get TrasactionId in Activity Log API
	And I get Activity Log API using transactionId filters
	Then I validate content OPEN has the expected information
	
	Examples:
		| user   | action   | resource                             			 |
		| upadmin| post  	| API Specification         					 |
		| upadmin| put  	| API Specification         					 |
		| upadmin| patch  	| API Specification         					 |
		#| upadmin| delete  	| API Specification        						 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Customer Facing Service Specification 		 |
		| upadmin| put  	| Customer Facing Service Specification 		 |
		| upadmin| patch  	| Customer Facing Service Specification 		 |
		#| upadmin| delete  	| Customer Facing Service Specification 		 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Characteristic Specification 					 |
		| upadmin| put  	| Characteristic Specification 					 |
		| upadmin| patch  	| Characteristic Specification  				 |
		#| upadmin| delete  	| Characteristic Specification  				 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Charging Configuration Specification 			 |
		| upadmin| put  	| Charging Configuration Specification 			 |
		| upadmin| patch  	| Charging Configuration Specification  		 |
		#| upadmin| delete  	| Charging Configuration Specification  		 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Composite Product Specification 				 |
		| upadmin| put  	| Composite Product Specification 				 |
		| upadmin| patch  	| Composite Product Specification  				 |
		#| upadmin| delete  	| Composite Product Specification  				 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Fulfillment Configuration Specification 		 |
		| upadmin| put  	| Fulfillment Configuration Specification 		 |
		| upadmin| patch  	| Fulfillment Configuration Specification  		 |
		#| upadmin| delete  	| Fulfillment Configuration Specification  		 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Key Performance Indicator 					 |
		| upadmin| put  	| Key Performance Indicator 					 |
		| upadmin| patch  	| Key Performance Indicator  					 |
		#| upadmin| delete  	| Key Performance Indicator  					 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Key Quality Indicator 						 |
		| upadmin| put  	| Key Quality Indicator 						 |
		| upadmin| patch  	| Key Quality Indicator  						 |
		#| upadmin| delete  	| Key Quality Indicator  						 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Logical Resource Specification 				 |
		| upadmin| put  	| Logical Resource Specification 				 |
		| upadmin| patch  	| Logical Resource Specification  				 |
		#| upadmin| delete  	| Logical Resource Specification  				 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Physical Resource Spec 						 |
		| upadmin| put  	| Physical Resource Spec 						 |
		| upadmin| patch  	| Physical Resource Spec  						 |
		#| upadmin| delete  	| Physical Resource Spec  						 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Product Offering 								 |
		| upadmin| put  	| Product Offering 								 |
		| upadmin| patch  	| Product Offering  							 |
		#| upadmin| delete  	| Product Offering  							 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Product Specification 						 |
		| upadmin| put  	| Product Specification 						 |
		| upadmin| patch  	| Product Specification  						 |
		#| upadmin| delete  	| Product Specification  						 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Resource Candidate 							 |
		| upadmin| put  	| Resource Candidate 							 |
		| upadmin| patch  	| Resource Candidate  							 |
		#| upadmin| delete  	| Resource Candidate 							 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Resource Facing Service Specification 		 |
		| upadmin| put  	| Resource Facing Service Specification 		 |
		| upadmin| patch  	| Resource Facing Service Specification  		 |
		#| upadmin| delete  	| Resource Facing Service Specification			 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Service Candidate 		 					 |
		| upadmin| put  	| Service Candidate 		 					 |
		| upadmin| patch  	| Service Candidate  		 					 |
		#| upadmin| delete  	| Service Candidate			 					 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Service Level Objective						 |
		| upadmin| put  	| Service Level Objective						 |
		| upadmin| patch  	| Service Level Objective  		 				 |
		#| upadmin| delete  	| Service Level Objective						 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Service Level Specification					 |
		| upadmin| put  	| Service Level Specification 					 |
		| upadmin| patch  	| Service Level Specification  					 |
		#| upadmin| delete  	| Service Level Specification					 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Service Level Spec Applicability				 |
		| upadmin| put  	| Service Level Spec Applicability 		 		 |
		| upadmin| patch  	| Service Level Spec Applicability  			 |
		#| upadmin| delete  	| Service Level Spec Applicability				 |Delete not open project (Felyphe oliveira)
		| upadmin| post  	| Service Level Spec Consequence       			 |
		| upadmin| put  	| Service Level Spec Consequence        		 |
		| upadmin| patch  	| Service Level Spec Consequence        		 |
		#| upadmin| delete  	| Service Level Spec Consequence        		 | Delete not open project (Felyphe oliveira)

@RegressionTest
Scenario Outline: I validate the patch request in PO associate with PS (HX62357)
	Given I create a PO associate with PS as "<user>"
	When I updated the PO using "patch" request as "<user>"
	Then I validate the statuscode as "200"
	
	Examples:
		| user   |
		| lisa   |
		| anne   |
		| alex   |