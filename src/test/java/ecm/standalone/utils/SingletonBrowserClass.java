package ecm.standalone.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingletonBrowserClass {
	// instance of singleton class
	private static SingletonBrowserClass instanceOfSingletonBrowserClass=null;
    private WebDriver driver;
    private WebDriverWait wait;
    //private ChromeOptions options;

    // Constructor
    private SingletonBrowserClass(){	
    	//options = new ChromeOptions();
    	// options.addArguments("headless");
		driver= new ChromeDriver();
		//wait = new WebDriverWait(driver, WebDriverWait.DEFAULT_SLEEP_TIMEOUT);
		driver.manage().window().maximize();
    }

    // TO create instance of class
    public static SingletonBrowserClass getInstanceOfSingletonBrowserClass(){
        if(instanceOfSingletonBrowserClass==null){
        	instanceOfSingletonBrowserClass = new SingletonBrowserClass();
        }
        return instanceOfSingletonBrowserClass;
    }
    
    // To get driver
    public WebDriver getDriver()
    {
    	return driver;
    }
    
    public WebDriverWait getWait()
    {
    	return wait;
    }
}
