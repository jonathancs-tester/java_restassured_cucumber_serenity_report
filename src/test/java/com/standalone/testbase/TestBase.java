package com.standalone.testbase;

import org.junit.BeforeClass;

import ecm.standalone.utils.ReadPropertyFile;
import io.restassured.RestAssured;


public class TestBase {
	
	public static String SERVER_NAME;
	public static String DB_PORT;
	public static String DB_SID;
	public static String BASE_URL;
	public static String ECM_MANAGEMENT_URL;
	public static String USER_PROFILE_URL;
	public static String ECM_SYSTEM_CONFIGURATION_URL;
	public static String ECM_METRICS_URL;
	public static String ECM_RUNTIME_URL;
	public static String ECM_COUNTER_DEFINITION_URL;
	
	@BeforeClass
	public static void init() throws Exception{
		ReadPropertyFile config = new ReadPropertyFile();
		SERVER_NAME=config.getServerName();
		DB_PORT=config.getDatabasePort();
		DB_SID=config.getSID();
		
	    BASE_URL =  "http://" + SERVER_NAME + ":8080/ecm";
		ECM_MANAGEMENT_URL = BASE_URL + "/ecm/CatalogManagement/v2";
		USER_PROFILE_URL = BASE_URL + "/userProfile/v1/userPreference/";
		ECM_SYSTEM_CONFIGURATION_URL = BASE_URL + "/System/v1/";
		ECM_METRICS_URL = BASE_URL + "/ecm/metrics/v1";
		ECM_RUNTIME_URL = BASE_URL + "/ecmRT/v2";
		ECM_COUNTER_DEFINITION_URL = BASE_URL;
		
		RestAssured.baseURI = ECM_MANAGEMENT_URL;
	}
}
