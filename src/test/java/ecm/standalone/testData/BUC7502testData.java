package ecm.standalone.testData;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ecm.standalone.userPreference.Preference;
import ecm.standalone.userPreference.UserPreference;
import ecm.standalone.utils.ConnectionDB;

public class BUC7502testData {
	
  public static UserPreference createUP(String applicationId, List<String> defId) {
	  List<Preference> preferenceSub = new ArrayList<Preference>();

	  for (int sequence = 0; sequence < defId.size(); sequence++) {
		  Preference preferencedefId = new Preference();
      if (defId.get(sequence) != null) {
        preferencedefId.setId(defId.get(sequence));
      }
      preferencedefId.setValue((5 * sequence + 5.1) + "");
      preferenceSub.add(preferencedefId);
	  }

	  Preference preference1 = new Preference();
	  preference1.setGroup("GENERAL");
	  preference1.setPreferences(preferenceSub);

	  List<Preference> preferences = new ArrayList<Preference>();
	  preferences.add(preference1);

	  UserPreference user = new UserPreference();
	  user.setId(applicationId);
	  user.setPreferences(preferences);

	  return user;
  }

  public static String CreateUser(int sequence) {
	  Connection conn = ConnectionDB.getConnection();
	  String userId = "user" + sequence;
	  PreparedStatement stmt = null;
	  try {
		  stmt = conn.prepareStatement("CREATE USER " + userId + " IDENTIFIED BY " + userId);
		  stmt.executeQuery();
      return userId;
	  } catch (SQLException e) {
    	e.printStackTrace();
      return null;
	  } finally {
    	ConnectionDB.closeConnection(conn, stmt);
    }
  }

  public static String CreateApplication(int sequence, String user) {
	  Connection conn = ConnectionDB.getConnection();
	  String appID = "app" + sequence; //+ ;
	  PreparedStatement stmt = null;
	  try {
		  stmt = conn.prepareStatement("Insert into CWDBCODETABLES (CTTYPE,CODE,ACTIVE,DESCRIPTION,UPDATEDBY,LASTUPDATEDDATE,CODETABLEID,APP_NAME) values ('clientApplicationList','" + appID
          + "',1,'zvSAa','" + user + "',to_date('03-APR-18','DD-MON-RR'),'" + System.currentTimeMillis() + "','ecm')");
		  stmt.executeQuery();
      return appID;
	  } catch (SQLException e) {
    	e.printStackTrace();
      return null;
	  } finally {
    	ConnectionDB.closeConnection(conn, stmt);
    }
  }

  public static String CreateApplication(String application, String user) {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  try {
		  stmt = conn.prepareStatement("Insert into CWDBCODETABLES (CTTYPE,CODE,ACTIVE,DESCRIPTION,UPDATEDBY,LASTUPDATEDDATE,CODETABLEID,APP_NAME) values ('clientApplicationList','" + application
          + "',1,'zvSAa','" + user + "',to_date('03-APR-18','DD-MON-RR'),'" + System.currentTimeMillis() + "','ecm')");
		  stmt.executeQuery();
      return application;
	  } catch (SQLException e) {
    	e.printStackTrace();
      return null;
	  } finally {
    	ConnectionDB.closeConnection(conn, stmt);
    }
  }

  public static void GrantConnect(String userId) {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  ResultSet rs = null;
	  try {
		  stmt = conn.prepareStatement("GRANT CONNECT TO " + userId);
		  stmt.executeQuery();
	  } catch (SQLException e) {
    	e.printStackTrace();
	  } finally {
    	ConnectionDB.closeConnection(conn, stmt, rs);
    }
  }

  public static void createPreference(String value, String name) throws Exception {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  try {
		  stmt = conn.prepareStatement(
          "insert into CWUSER_PREFERENCE_DEFINITION (leaf_name, data_type, label, default_value, code_table, group_code) values ('" + name + "', 3 , null, " + value + ", null, 'GENERAL')");
		  stmt.executeQuery();
	  } catch (SQLException e) {
    	e.printStackTrace();
	  } finally {
    	ConnectionDB.closeConnection(conn, stmt);
    }
  }

  public static void CreateUserInCWUser(String userId, String username) {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  ResultSet rs = null;
	  try {
		  stmt = conn.prepareStatement("Insert into CWUSER (USERID,USER_NAME,ACTIVE,PROFILEID,MINTASKS,MAXTASKS) values ('" + userId + "','" + username + "',1,'" + userId + "',3,10)");
		  stmt.executeQuery();
	  } catch (SQLException e) {
    	e.printStackTrace();
	  } finally {
    	ConnectionDB.closeConnection(conn, stmt, rs);
    }
  }

  public static void VerifyCreate(String table, String column, String value) {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  ResultSet rs = null;
	  try {
		  stmt = conn.prepareStatement("SELECT * from " + table + " WHERE " + column + " like '" + value + "'");
		  rs = stmt.executeQuery();
		  assertTrue(rs.next());
	  } catch (SQLException e) {
    	e.printStackTrace();
	  } finally {
    	ConnectionDB.closeConnection(conn, stmt, rs);
    }
  }

  public static void CreateTranslation(String appId) {
	  Connection conn = ConnectionDB.getConnection();
	  String randomID = "tl" + System.currentTimeMillis();
	  PreparedStatement stmt = null;
	  try {
		  stmt = conn.prepareStatement("insert into cwtranslations (id, language, translation, lastupdateddate) values ('" + randomID + "', 'en-xx', '" + appId + "', to_date('03-APR-18','DD-MON-RR'))");
		  stmt.executeQuery();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  } finally {
		  ConnectionDB.closeConnection(conn, stmt);
    }
  }

  public static void CreateUserRole(String userId, String group) {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  try {
		  stmt = conn.prepareStatement("Insert into CWUSERROLE (USERID,ROLEID,ACTIVE,MANAGER) values ('" + userId + "','" + group + "',1,0)");
		  stmt.executeQuery();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  } finally {
		  ConnectionDB.closeConnection(conn, stmt);
    }
  }

  public static void DeleteApp() {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	   try {
	     stmt = conn.prepareStatement("select * FROM  CWDBCODETABLES WHERE CODE  like 'app%'");
	     ResultSet rs = stmt.executeQuery();

	     if (rs.next()) {
	       PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM  CWDBCODETABLES WHERE CODE  like 'app%'");
	       stmt2.executeQuery();
	      }

	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	ConnectionDB.closeConnection(conn, stmt);
	    }
	  }
  
  public static void DeleteApp(String application) {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  try {
	    stmt = conn.prepareStatement("select * FROM  CWDBCODETABLES WHERE CODE  like '"+application+"'");
	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
	      PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM  CWDBCODETABLES WHERE CODE  like '"+application+"'");
	      stmt2.executeQuery();
	     }

	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	ConnectionDB.closeConnection(conn, stmt);
	    }
	  }

  public static void DeleteUser() {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  try {
		  stmt = conn.prepareStatement("select * from CWUSER where USERID like 'user%'");
		  ResultSet rs = stmt.executeQuery();
		  if (rs.next()) {
			  PreparedStatement stmt2 = conn.prepareStatement("delete from CWUSER where USERID like 'user%'");
			  stmt2.executeQuery();
		  }
	  } catch (SQLException e) {
		  e.printStackTrace();
	  } finally {
		  ConnectionDB.closeConnection(conn, stmt);
    }
  }

  public static void DeleteUserRole() {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  try {
		  stmt = conn.prepareStatement("select * from CWUSERROLE where USERID like 'user%'");
		  ResultSet rs = stmt.executeQuery();
		  if (rs.next()) {
			  PreparedStatement stmt2 = conn.prepareStatement("delete from CWUSERROLE where USERID like 'user%'");
			  stmt2.executeQuery();
		  }
	  } catch (SQLException e) {
		  e.printStackTrace();
	  } finally {
		  ConnectionDB.closeConnection(conn, stmt);
    }
  }

  public static void DeleteDefUser() {
	  Connection conn = ConnectionDB.getConnection();
	  PreparedStatement stmt = null;
	  ResultSet rs = null;
	  try {
		  stmt = conn.prepareStatement("select * from CWUSER_PREFERENCE_DEFINITION where LEAF_NAME like 'def%'");
		  rs = stmt.executeQuery();
		  if (rs.next()) {
			  PreparedStatement stmt2 = conn.prepareStatement("delete from CWUSER_PREFERENCE_DEFINITION where LEAF_NAME like 'def%'");
			  stmt2.executeQuery();
		  }
	  } catch (SQLException e) {
		  e.printStackTrace();
	  } finally {
		  ConnectionDB.closeConnection(conn, stmt, rs);
    }
  }

  public static void DeleteUserDB(int value) {
	  Connection conn = ConnectionDB.getConnection();
	  try {
		  PreparedStatement stmt2 = conn.prepareStatement("DROP USER USER" + value + " CASCADE");
		  stmt2.executeQuery();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  } finally {
		  ConnectionDB.closeConnection(conn);
    }
  }

  public static UserPreference updateUP(String applicationId, List<String> defId, List<Integer> indexAlter) {
	  List<Preference> preferenceSub = new ArrayList<Preference>();
	  for (int index : indexAlter) {
		  Preference preferencedefId = new Preference();
		  if (index >= 0) {
			  if (index <= defId.size()) {
				  preferencedefId.setId(defId.get(index - 1));
			  } else {
				  preferencedefId.setId("def" + index + "Error");
			  }
        preferencedefId.setValue((5 * index + 100.1) + "");  
		  }else if(index== -1){
			  preferencedefId.setId(null);
		  }else {
			  preferencedefId.setId2("def"+(index*-1+1));
			  preferencedefId.setId("def"+index*-1);
			  preferencedefId.setId3("def"+index*-1);
		  }
      preferenceSub.add(preferencedefId);
    }

	  Preference preference1 = new Preference();
	  preference1.setGroup("GENERAL");
	  preference1.setPreferences(preferenceSub);

	  List<Preference> preferences = new ArrayList<Preference>();
	  preferences.add(preference1);

	  UserPreference user = new UserPreference();
	  user.setId(applicationId);
	  user.setPreferences(preferences);

	  return user;
  }

}
