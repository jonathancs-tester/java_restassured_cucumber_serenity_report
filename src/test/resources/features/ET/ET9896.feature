@release:Release-19.1
@BUC9903
@ET9896
Feature: ET9896 - Can we local this system configuration and expose it as a system variable Catalog UI and API will use the  variable “project launch lead time” (or whatever it is named today). 
				  If value  = 0 (hours), or “correction” project  and project launch date (effective date) is today or yesterday then set the effective date to current date-time and activation project, on successful active reload the cache, else keep existing dates and reload cache. 
				  If != 0 then keep existing behavior with the project effective date validation and don’t reload cache.  
				  I’d think most customer will have the project launch (effective date) lead time set to 0,  so catalog  it is real-time.  
				  New installs should set the flag to 0, existing customers should keep the value they have.
				  
				  OBS: The varibale IMMEDIATE_RT_ACT_PROP_TO_SERVER include other nodes, so this variable will not tested.

@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as TRUE when effectiveDate is in the past and PAST_PROJECT_ACTIVATION and IMMEDIATE_RT_RELOAD_CACHE variables on System Configuration as TRUE
		  Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-18 10:33
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with PS
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as TRUE and IMMEDIATE_RT_RELOAD_CACHE variable as TRUE
	And I restart the JBOSS server
	When I activate this project with SHIFTDATES as TRUE as queryParam in this endpoint
	Then immediately the project must be updated to ACTIVE	
	And the effective date must be updated to current date 
	
@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as TRUE when effectiveDate is in the past and PAST_PROJECT_ACTIVATION and IMMEDIATE_RT_RELOAD_CACHE variables on System Configuration as FALSE
		  Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-18 14:40
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with PS
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as FALSE and IMMEDIATE_RT_RELOAD_CACHE variable as FALSE 
	When I activate this project with SHIFTDATES as TRUE as queryParam in this endpoint
	Then the project must not be activated 		
	And the effective date must not be updated to current date 
	
@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as TRUE when effectiveDate is in the past and PAST_PROJECT_ACTIVATION as TRUE IMMEDIATE_RT_RELOAD_CACHE variable on System Configuration as FALSE
	      Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-18 15:00
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with PS
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as TRUE and IMMEDIATE_RT_RELOAD_CACHE variable as FALSE
	And I restart the JBOSS server
	When I activate this project with SHIFTDATES as TRUE as queryParam in this endpoint
	Then the project will not activate immediately
	And the project must be activated 
	And the effective date must be updated to current date 
	 	
@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as TRUE when effectiveDate is in the past and PAST_PROJECT_ACTIVATION as FALSE IMMEDIATE_RT_RELOAD_CACHE variable on System Configuration as TRUE
	      Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-18 15:00
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with PS
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as FALSE and IMMEDIATE_RT_RELOAD_CACHE variable as TRUE 
	And I restart the JBOSS server
	When I activate this project with SHIFTDATES as TRUE as queryParam in this endpoint
	Then the project must not be activated 
	And the effective date must not be updated to current date

@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as FALSE when effectiveDate is in the past and PAST_PROJECT_ACTIVATION and IMMEDIATE_RT_RELOAD_CACHE variables on System Configuration as TRUE
		  Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-18 15:15
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with PS
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as TRUE and IMMEDIATE_RT_RELOAD_CACHE variable as TRUE
	And I restart the JBOSS server
	When I activate this project with SHIFTDATES as FALSE as queryParam in this endpoint
	Then immediately the project must be updated to ACTIVE
	And the effective date must not be updated to current date 
	
@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as FALSE when effectiveDate is in the past and PAST_PROJECT_ACTIVATION and IMMEDIATE_RT_RELOAD_CACHE variables on System Configuration as FALSE
		  Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-18 15:32
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with PS
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as FALSE and IMMEDIATE_RT_RELOAD_CACHE variable as FALSE 
	When I activate this project with SHIFTDATES as FALSE as queryParam in this endpoint
	Then the project must not be activated
	Then the effective date must not be updated to current date 
	
@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as FALSE when effectiveDate is in the past and PAST_PROJECT_ACTIVATION as TRUE IMMEDIATE_RT_RELOAD_CACHE variable on System Configuration as FALSE
	      Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-18 15:50
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with PS
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as TRUE and IMMEDIATE_RT_RELOAD_CACHE variable as FALSE
	And I restart the JBOSS server
	When I activate this project with SHIFTDATES as FALSE as queryParam in this endpoint
	Then the project will not activate immediately
	And the project must be activated
	And the effective date must not be updated to current date 
	
@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as FALSE when effectiveDate is in the past and PAST_PROJECT_ACTIVATION as FALSE IMMEDIATE_RT_RELOAD_CACHE variable on System Configuration as TRUE
	      Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-18 16:24
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with PS
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as FALSE and IMMEDIATE_RT_RELOAD_CACHE variable as TRUE 
	And I restart the JBOSS server
	When I activate this project with SHIFTDATES as FALSE as queryParam in this endpoint
	Then the project must not be activated
	And the effective date must not be updated to current date

@manual
Scenario: On POST to activate the project, set the variable configuration as SHIFTDATES as TRUE to the PO that have one version when effectiveDate is in the past and PAST_PROJECT_ACTIVATION and IMMEDIATE_RT_RELOAD_CACHE variables on System Configuration as TRUE
		  Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-19 09:52
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with one version
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as TRUE and IMMEDIATE_RT_RELOAD_CACHE variable as TRUE
	And I restart the JBOSS server
	When I activate the project of the definition PO with SHIFTDATES as TRUE as queryParam in this endpoint
	Then immediately the project must be updated to ACTIVE
	And the project must be activated
	And the effective date of the definition PO must not be updated to current date 
	And the effective date of the active PO must not be updated to current date

@manual
Scenario: On POST to activate a PO, set the variable configuration as SHIFTDATES as FALSE to the PO that have one version when effectiveDate is in the past and PAST_PROJECT_ACTIVATION and IMMEDIATE_RT_RELOAD_CACHE variables on System Configuration as TRUE
	      Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0039) R1A/K  Date: 2019-02-19 10:28
	Given I have a project in the past date
	And I set the ERROR_CORRECTION as TRUE
	And I create a PO with one version
	And I set on System Configurations the PAST_PROJECT_ACTIVATION variable as TRUE and IMMEDIATE_RT_RELOAD_CACHE variable as TRUE
	When I activate the project of the definition PO with SHIFTDATES as FALSE as queryParam in this endpoint
	Then the project will not activate immediately
	And the project must be activated
	And the effective date of the definition PO must not be updated to current date 
	And the effective date of the active PO	must not be updated to current date

@manual
Scenario: Create PO with PS when ERROR_CORRECTION is FALSE
		  Test Result: SUCCESS by ZPIVJES - Build: 19.1 (0042) R1A/L  Date: 2019-02-26 09:51
	Given I have a project in the future
	And I update the project with a date in the past
	And I set the ERROR_CORRECTION as FALSE
	When I create a PO with PS
	Then the PO can't be created on project that is with ERROR_CORRECTION as false 
	
@manual
Scenario: Activate project when ERROR_CORRECTION is FALSE
		  Test Result: SUCCESS by XSILJON - Build: 19.1 (0042) R1A/L  Date: 2019-02-26 09:30
	Given I have a project in the future
	And I create a PO with PS
	And I set the ERROR_CORRECTION as FALSE
	When I update the project with a date in the past using Project Utility
	And I activate this project with SHIFTDATES as TRUE as queryParam in this endpoint
	Then the project can't be activated because ERROR_CORRECTION is FALSE 
		