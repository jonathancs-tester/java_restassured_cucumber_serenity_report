package ecm.standalone.utils;

//STEP 1. Import required packages

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.standalone.testbase.TestBase;

public class ConnectionDB {
	public static String USER = "ECM";
	public static String PASS = "ECM";   
	public static String DB_PORT = TestBase.DB_PORT;
	public static String DB_SID = TestBase.DB_SID;
	public static String DB_SERVER = TestBase.SERVER_NAME; 
	public static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver"; 
	public static String SERVER_NAME = TestBase.SERVER_NAME;
	public static String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=" + DB_SERVER + ")(PORT=" + DB_PORT + ")))(CONNECT_DATA=(SERVICE_NAME=" + DB_SID + ")))";

   public static Connection getConnection(){
	   Connection conn = null;
	   try{
	       try {
	           //STEP 2: Register JDBC driver
	    	   Class.forName(JDBC_DRIVER);  
	       } catch (ClassNotFoundException ex) {
	           throw new RuntimeException("Connection Error: ", ex);
	       }
	
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS); 
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }//end try
	        return conn;
	   }
   
   public static void closeConnection(Connection conn) {
       try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   
   public static void closeConnection(Connection conn,PreparedStatement stmt) {
       closeConnection(conn);
        try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se){
          se.printStackTrace();
      }
   }
   
   public static void closeConnection(Connection conn,PreparedStatement stmt, ResultSet rs){
       closeConnection(conn, stmt);
        try{
         if(rs!=null)
            rs.close();
      }catch(SQLException se){
         se.printStackTrace();
      }      
   }
}//end ConnectionDb