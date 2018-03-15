package dd_core;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import dd_util.TestUtil;
import dd_util.Xls_Reader;

public class Page  {
	
	
	
	 public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Xls_Reader excel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\dd_properties\\testdata.xlsx");
	public static Logger logs = Logger.getLogger("devpinoyLogger");
	
	
	/*    ----Loading Files and executing them before executing the suite */
	@BeforeSuite
	public void init() throws IOException, AddressException, SQLException, ClassNotFoundException, MessagingException{
		
		if(driver==null){
			/*    in this loop it will check if the driver is null then it gonna assign it the value
			 */
			/*    Below we load Config file onto fis */
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/dd_properties/Config.properties");
		Config.load(fis);
		logs.debug("Loaded the Config property file");
		/*    ----Below we are loading the Or file which holds all the xpaths */
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\OR.properties");
		OR.load(fis);
		logs.debug("loaded the OR property file");
		/* the test will open the browser whichever is written in the config file */
		if(Config.getProperty("browser").equals("firefox")){
			
			driver = new FirefoxDriver(); 
			logs.debug("Loaded Firefox");
			
		}else if(Config.getProperty("browser").equals("chrome")){
			
			driver = new ChromeDriver();
			logs.debug("Loaded Chrome");
		}
		
		driver.get(Config.getProperty("testsiteurl"));
		/* above code will check for the website */
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		
		}
		
		
	}
	
	/* below function is used in LoginTest to perfrom operation*/
	
	public static WebElement findElement(String key) throws IOException{
		
		try{
		
			
			return driver.findElement(By.xpath(OR.getProperty(key)));
		
		
		}catch(Throwable t){
			
			/* will capture a screenshot if the file isnt loaded */
			TestUtil.CaptureScreenshot();
			return null;
			
		}
		
		
	}
	

	
	@AfterSuite
	public void QuitDriver(){
		
		
		//driver.quit();
		
	}

}
