package dd_testcases;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import dd_core.Page;
import dd_util.TestUtil;
public class MyProfileTest extends Page {
	@BeforeTest
	public void isSkip(){	
		if(!TestUtil.isExecutable("MyProfileTest")){		
			throw new SkipException("Skipping the test as the Run mode is No");
			
		}		}	
	@Test
	public void E() throws IOException {
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	System.out.println("7.Started second test");
	findElement("Search").sendKeys("playstation");
	System.out.println("8.Searched for item");
	findElement("searchbutton").click();	
	System.out.println("9.clicked on search button");
	driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	findElement("itemselect").click();
	System.out.println("10. item Selected");	
	findElement("addtocart").click();
	
	}
	@Test
	public void F() throws IOException{
		System.out.println("Item Added to Cart");		
	TestUtil.CaptureScreenshot();
	driver.close();
	
	}
	
	
	
}
