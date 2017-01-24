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

public class testUser extends variable{
	
	public RemoteWebDriver driver;
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
	    
		System.out.println("***** Starting User Test Case *****");
	    
	}
	
	@Test(enabled = true, priority = 1)
	public void testValidLoginTest() throws InterruptedException {
		System.out.println("*** No Input User ***");
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
			
			// User
			WebElement m_menu_user = driver.findElement(By.xpath("//li/a/i[@class='fa fa fa-user-secret']"));
            m_menu_user.click();
			Thread.sleep(5000);
			
            //Check User Page
			WebElement s_menu_yourprofile = driver.findElement(By.xpath("//a[@href='#/user/edit']"));
            s_menu_yourprofile.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Edit Your Profile"))
				driver.close();
			
			
            WebElement btn_user_done = driver.findElement(By.xpath("//input[@class='isul-save-button']"));
            btn_user_done.click();
            
		    
		    // New Password Error Message
		    WebElement noNewPasswordUser = driver.findElement(By.xpath("//form//div//label[5]//span[@class='error']"));
		    String Errmsg_noNewPasswordUser = noNewPasswordUser.getText();
			System.out.println("No Input + Add Subnet - Network Address Error Message is : " + Errmsg_noNewPasswordUser);
		    
			//Repeat Password Error Message    
		    WebElement noRepeatPasswordUser = driver.findElement(By.xpath("//form//div//label[6]//span[@class='error']"));
		    String Errmsg_noRepeatPasswordUser = noRepeatPasswordUser.getText();
			System.out.println("No Input + Add Subnet - Mask Error Message is : " + Errmsg_noRepeatPasswordUser);
					    
		    assertEquals(Errmsg_noNewPasswordUser, nullNewPasswordErrMsg);
		    assertEquals(Errmsg_noRepeatPasswordUser, nullRepeatPasswordErrMsg);
		    
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" User-No Input User.png"));
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
		System.out.println("***** Finished User Test Case *****");
		driver.quit();   
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
	


