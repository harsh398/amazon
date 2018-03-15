package dd_testcases;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import dd_core.Page;
import dd_util.TestUtil;
public class LoginTest extends Page {
	/*Delcaring a global Username to assigne value of the username*/
	public static String GlobalUsername;
	/* Below  function is used when the runmode is no in exxcel file */
	@BeforeTest
	public void isSkip(){		
		if(!TestUtil.isExecutable("LoginTest")){		
			throw new SkipException("Skipping the test as the Run mode is No");
		}}	
/* below Testcase is fetching Data from getData Method */
	@Test(dataProvider="getData")
	public void B(String username,String password) throws IOException{
		System.out.println("########################");
		System.out.println("1. BrowSer Opened");
		/*below code is used for hovering over the Signin and clicking on it */
		WebElement mouse = driver.findElement(By.xpath(".//*[@id='nav-link-yourAccount']"));	
		Actions builder = new Actions(driver);
		builder.moveToElement(mouse).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='nav-flyout-ya-signin']/a/span")));
		driver.findElement(By.xpath(".//*[@id='nav-flyout-ya-signin']/a/span")).click();	
		System.out.println("2. User Clicked on Sign in");
		System.out.println("3.Login Page will open Up");
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		System.out.println("4.Entered the Email");
		findElement("username").sendKeys(username);
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		findElement("continue").click();
		System.out.println("5.Entered the Password");
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		findElement("password").sendKeys(password);	
		findElement("login").click();
		System.out.println("6. Clicked Login");
		GlobalUsername = username;
		System.out.println("User Got Logged In");
	}		
	@Test
		public void C() throws IOException{
			System.out.println("I am Logged in");
			/*Calling Screenshot mehtod*/
		TestUtil.CaptureScreenshot();	
		}	
	/* Data provider for the Login Page */
		@DataProvider
		public static Object[][] getData(){			
			return TestUtil.getData("LoginTest");									
		}
}

