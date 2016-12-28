package attotest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.AssertJUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;


import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class testCnodeTest extends variable{
	
	public RemoteWebDriver driver;
	public static String appURL = "http://10.61.129.81:8009";
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private StringBuffer verificationErrors = new StringBuffer();
	private static final String CAPTURE_PATH = "test-output/";


	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL("http://10.51.11.12:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
		
	    String loginWindow = driver.getWindowHandle();
	    driver.switchTo().window(loginWindow);  
	    
		System.out.println("***** Starting Cnode Test Case *****");
	    
	}
	
	@Test(enabled = true, priority = 1)
	public void testValidLoginTest() throws InterruptedException {
		System.out.println("*** Not Input Cnode ***");
		driver.navigate().to(appURL);
		String strPageTitle = driver.getTitle();

		AssertJUnit.assertTrue(strPageTitle.equalsIgnoreCase("Athene Login"));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		try {
			// find element user name & password & submit			
			WebElement username = driver.findElement(By.name("userid"));
			WebElement passwd = driver.findElement(By.name("pw"));
			WebElement login = driver.findElement(By.id("submit"));
			
			username.sendKeys("athene");
			Thread.sleep(1000);
			passwd.sendKeys("athene");
			Thread.sleep(1000);
			
			login.submit();
			Thread.sleep(10000);
			
			// Cnode
            WebElement m_menu_cnode = driver.findElement(By.xpath("//*[contains(text(), 'Cnode')]"));
            m_menu_cnode.click();
			Thread.sleep(5000);
			
            //Check Cnode List Page
            WebElement s_menu_cnodeList = driver.findElement(By.xpath("//*[contains(text(), 'Cnode List')]"));
            s_menu_cnodeList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Cnode List"))
				driver.close();
			
            WebElement btn_cnodelist_add = driver.findElement(By.xpath("//i[@class='fa fa-plus fa-fw']"));
            btn_cnodelist_add.click();
			
			/*
            //Check Add New Cnode Page
            WebElement s_menu_addcnode = driver.findElement(By.xpath("//*[contains(text(), 'Add Cnode')]"));
            s_menu_addcnode.click();
			*/			
			Thread.sleep(5000);
						
			if (!driver.getPageSource().contains("Add New Cnode"))
				driver.close();		
			
		    WebElement btn_addcnode = driver.findElement(By.xpath("//input[@class='isul-edit-button']"));
		    btn_addcnode.click();
		    
		    // Name Error Message
		    WebElement nonamecnode = driver.findElement(By.xpath("//form//div//label[1]//span[@class='error']"));
		    String Errmsg_nonamecnode = nonamecnode.getText();
			System.out.println("No Input + Add Cnode - Name Error Message is : " + Errmsg_nonamecnode);
		    
			//ip Error Message    
		    WebElement noipcnode = driver.findElement(By.xpath("//form//div//label[2]//span[@class='error']"));
		    String Errmsg_noipcnode = noipcnode.getText();
			System.out.println("No Input + Add Cnode - ip Error Message is : " + Errmsg_noipcnode);
			
			// Hostname Error Message
			WebElement nohostnamecnode = driver.findElement(By.xpath("//form//div//label[4]//span[@class='error']"));
		    String Errmsg_nohostnamecnode = nohostnamecnode.getText();
			System.out.println("No Input + Add Cnode - Hostname Error Message is : " + Errmsg_nohostnamecnode);
					    
		    assertEquals(Errmsg_nonamecnode, CnodeNullNameErrMsg);
		    assertEquals(Errmsg_noipcnode, CnodeNullipErrMsg);
		    assertEquals(Errmsg_nohostnamecnode, CnodeNullHostnameErrMsg);
		    
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Cnode-No Input Cnode.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	
			Thread.sleep(5000);	
			
			
			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		System.out.println("***** Finished Cnode Test Case *****");
		driver.quit();   
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
	


