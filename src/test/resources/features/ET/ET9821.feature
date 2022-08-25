@release:Release-19.1
@BUC9821
@ET9821
Feature: ET9821 - Change offer start and end date (BUC#9802 Bulk Price Change)

@smoke
Scenario Outline: I have a simple definition PO and update the start date using versionIdentifier and dateVersion
	Given I have a project
	And I create a definition PO
	When I update the PO start date using "<type>" with "<value>" to "<toUpdate>"
	And I GET the PO version
	Then the PO start date must be updated to future
	
	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   1.0	       |    future      |
		|   dateVersion          |   future	       |	future      |

@smoke
Scenario Outline: I have a definition PO with PS and update the start date using versionIdentifier and dateVersion
	Given I have a project
	And I create a PO with PS
	When I update the PO start date using "<type>" with "<value>" to "<toUpdate>"
	And I GET the PO version
	Then the definition PO item and subitems must be updated with the new date
	
	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   1.0	       |    future      |
		|   dateVersion          |   future	       |	future      |
		
@smoke
Scenario Outline: I have a definition PO with PS and verify definition base items and active base items after the start date change
	Given I have a project
	And I create a PO with PS
	When I update the PO start date using "<type>" with "<value>" to "<toUpdate>"
	And I GET the PO version
	Then the definition PO item and subitems must be updated with the new date
	And the definition base items must be updated and the active base items must not be updated
	
	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   1.0	       |    future      |
		|   dateVersion          |   future	       |	future      |	
	 				 
@smoke
Scenario Outline: I create a PO with base characteristic and verify if the base characteristics items can be updated with the new date and the tag changeState=overridden
	Given I have a project
	And I create a PO with PS
	And I create a PO with base characteristic and associate them
	When I update the PO start date using "<type>" with "<value>" to "<toUpdate>"
	And I GET the PO version 
	Then the PO start date must be updated to future
	And the base characteristic item must be updated with the tag changeState=overridden
	
	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   1.0	       |    future      |
		|   dateVersion          |   future	       |	future      |

@smoke
Scenario Outline: I have a PO version with category and update the start date of the second version using versionIdentifier and dateVersion
	Given I have a project
	And I create a PO with PS
	And I activate the project
	And I create a new PO version
	And I associate this PO to category
	When I update the PO start date using "<type>" with "<value>" to version to "<toUpdate>"
	And I GET the PO version without project
	Then the PO start date of the version 2.0 must be updated
	
	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   2.0	       |    future      |
		|   dateVersion          |   future	       |	future      |

@smoke
Scenario Outline: I have one PO version and update the start date to second version
	Given I have a project
	And I create a PO with PS
	And I activate the project
	And I create a new PO version
	When I update the PO start date using "<type>" with "<value>" to version to "<toUpdate>"
	And I GET the PO version without project
	Then the PO start date of the version 2.0 must be updated

	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   2.0	       |    future      |
		|   dateVersion          |   future	       |	future      |

@smoke 
Scenario Outline: I have a retired PO with retireDate and update the start date of this PO version
	Given I have a project
	And I create a PO with PS
	And I activate the project 
	And I create a new version of the PO with the retire date with "upadmin"
	When I update the PO start date using "<type>" with "<value>" to version to "<toUpdate>"
	And I GET the PO version without project 
	Then the PO start date of the version 2.0 must be updated

	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   2.0	       |    future      |
		|   dateVersion          |   future	       |	future      |	

@smoke
Scenario Outline: I have an active PO and verify if start date can be updated with the future date
	Given I have a project
	And I create a PO with PS
	And I activate the project 
	When I update the PO start date using "<type>" with "<value>" to "<toUpdate>"
	Then the active PO start date must not be updated
	
	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   1.0	       |    future      |
		|   dateVersion          |   future	       |	future      |
	
@smoke
Scenario Outline: I have a definition PO and verify if start date can be updated with the past date 
			      HX55859 - Closed
			      HX60480 - Closed
	Given I have a project with error correction
	And I create a definition PO 
	When I update the PO start date using "<type>" with "<value>" to "<toUpdate>"
	And I GET the PO version
	Then the PO start date must be updated to past

	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   1.0	       |    past        |
		|   dateVersion          |   past 	       |	past        |

@smoke
Scenario Outline: I have a PO version with category and update the start date of the second version with a past date 
	Given I have a project
	And I create a PO with PS
	And I activate the project
	And I create a new PO version
	And I associate this PO to category
	When I update the PO start date using "<type>" with "<value>" to version to "<toUpdate>"
	And I GET the PO version without project
	Then the PO start date of the version 2.0 must not be updated
	
	Examples:
		|       type             |   value         |   toUpdate     |
		|   versionIdentifier    |   2.0	       |    past        |
		|   dateVersion          |   past 	       |	past        |
				
@RegressionTest
Scenario Outline: I have two PO versions and change the startDateTime of thrid PO version
	Given I have a project
	And I create a PO with PS
	And I activate the project
	And I create a new PO version
	And I activate the project
	And I create a second PO version
	When I update the PO start date using "<type>" with "<value>" to third version to "<toUpdate>"
	And I GET the PO version without project
	Then the PO start date of the version 3.0 must be updated

	Examples:
		|       type             |   value         |   toUpdate     |
		|   dateVersion          |   future        |	future      |		

@RegressionTest
Scenario Outline: I have an active PO and verify if start date can be updated with the past date 
			      HX60480 - Closed
	Given I have a project with error correction
	And I create a PO with PS
	And I activate the project
	When I update the PO start date using "<type>" with "<value>" to "<toUpdate>"
	Then the active PO start date must not be updated

	Examples:
		|       type             |   value         |   toUpdate     |
		|   dateVersion          |   past          |	past        |	

		 
		 