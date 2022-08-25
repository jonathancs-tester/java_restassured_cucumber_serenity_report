@release:Release-19.1
@ET
@ET10119
Feature: ET10119 - This ET complements the ET9896. 
				   In addition: 
				   	* errorCorrection = 1: Only project effectiveDate is shifted to current date, all its contests are kept on their original dates;
					* errorCorrection = 0: Project effectiveDate, along with all its contents, are shifted to current date.

@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as TRUE when effectiveDate is in the future PROJECT_ACTIVATION_IGNORE_DATE variable on System Configuration as TRUE
		  Test Result: FAILED (HX50278) by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 15:00
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0040) R1A/L  Date: 2019-02-21 11:49
	Given I have a project in the future date
	And I create a PO with PS
	And I set on System Configurations the PROJECT_ACTIVATION_IGNORE_DATE variable as TRUE
	And I restart the JBOSS server
	When I activate this project with SHIFTDATES as TRUE as queryParam in this endpoint
	Then immediately the project must be updated to ACTIVE	 
	And the effective date must be updated to current date 
	
@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as TRUE when effectiveDate is in the future and PROJECT_ACTIVATION_IGNORE_DATE variable on System Configuration as FALSE
		  Test Result: FAILED (HX50278) by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 15:00
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0040) R1A/L  Date: 2019-02-21 12:13
	Given I have a project in the future date
	And I create a PO with PS
	And I set on System Configurations the PROJECT_ACTIVATION_IGNORE_DATE variable as FALSE 
	When I activate this project with SHIFTDATES as TRUE as queryParam in this endpoint
	Then immediately the project must be updated to ACTIVE
	And the project must be activated 
	And the effective date must not be updated to current date 		

@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as TRUE to the PO that have one version when effectiveDate is in the future and PROJECT_ACTIVATION_IGNORE_DATE variable on System Configuration as TRUE
		  Test Result: FAILED (HX50278) by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 15:00
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0040) R1A/L  Date: 2019-02-21 11:55
	Given I have a project in the future date
	And I create a PO with one version
	And I set on System Configurations the PROJECT_ACTIVATION_IGNORE_DATE variable as TRUE
	And I restart the JBOSS server
	When I activate the project of the definition PO with SHIFTDATES as TRUE as queryParam in this endpoint
	Then immediately the project must be updated to ACTIVE
	And the effective date of the definition PO must be updated to current date 
	And the effective date of the active PO must not be updated to current date

@manual
Scenario: On POST to activate a PO, set the variable configuration as SHIFTDATES as FALSE to the PO that have one version when effectiveDate is in the future and PROJECT_ACTIVATION_IGNORE_DATE variable on System Configuration as FALSE
		  Test Result: FAILED (HX50278) by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 15:00
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0040) R1A/L  Date: 2019-02-21 12:30
	Given I have a project in the future date
	And I create a PO with one version
	And I set on System Configurations the PROJECT_ACTIVATION_IGNORE_DATE variable as FALSE
	When I activate the project of the definition PO with SHIFTDATES as FALSE as queryParam in this endpoint
	Then immediately the project must be updated to ACTIVE
	And the project must be activated 
	And the effective date of the definition PO must not be updated to current date 
	And the effective date of the active PO	must not be updated to current date

@manual
Scenario: Set ERROR_CORRECTION as TRUE(1) on PO and see project effective date with the current date when the project is active
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 16:00
	Given I have a project in future date
	And I create a PO
	And I set on System Configurations the PROJECT_ACTIVATION_IGNORE_DATE variable as TRUE
	And I restart the JBOSS server
	When I set the error correction as true in this project
	And I activate the project of the definition PO with SHIFTDATES as TRUE as queryParam in this endpoint
	Then just project effectiveDate must be updated to current date

@manual
Scenario: Set ERROR_CORRECTION as FALSE(0) on PO and see project effective date with the current date when the project is active
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 16:25
	Given I have a project in future date
	And I create a PO
	And I set on System Configurations the PROJECT_ACTIVATION_IGNORE_DATE variable as TRUE
	And I restart the JBOSS server
	When I set the error correction as false in this project
	And I activate the project of the definition PO with SHIFTDATES as TRUE as queryParam in this endpoint
	Then the project effectiveDate and the PO effectiveDate must be updated to current date

@manual
Scenario: Set ERROR_CORRECTION as TRUE(1) on PO and error correction not possible change after project activated
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 16:30
	Given I have a project in future date
	And I create a PO
	And I set the error correction as true in this project
	When I activate the project
	Then the ERROR_CORRECTION field must not be available
	
@manual
Scenario: Set ERROR_CORRECTION as FALSE(0) on PO and error correction not possible change after project activated
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 16:37
	Given I have a project in future date
	And I create a PO
	And I set the error correction as false in this project
	When I activate the project
	Then the ERROR_CORRECTION field must not be available

@manual
Scenario: Set ERROR_CORRECTION as FALSE(0), no PO can be added
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0039) R1A/K  Date: 2019-02-15 16:40
	Given I have a project in past date
	And I create a PO
	When I set the ERROR_CORRECTION to TRUE
	Then no PO can be added