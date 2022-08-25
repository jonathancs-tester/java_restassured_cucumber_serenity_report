package ecm.standalone.utils;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {
	protected Properties prop = null;
	protected FileInputStream input;
	public ReadPropertyFile() throws Exception{
		input = new FileInputStream("configs/config.properties");
		prop = new Properties();
	    prop.load(input);		
	}
	
	public String getServerName(){
		return prop.getProperty("SERVER_NAME");	
	}
	public String getDatabasePort(){
		return prop.getProperty("DB_PORT");	
	}
	public String getSID(){
		return prop.getProperty("DB_SID");	
	}
}
