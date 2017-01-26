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

public class testSubnetTest extends variable{
	
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
	    
		System.out.println("***** Starting Subnet Test Case *****");
	    
	}
	
	@Test(enabled = true, priority = 1)
	public void testNullSubnetTest() throws InterruptedException {
		System.out.println("*** No Input Subnet ***");
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
			
			// Subnet
			WebElement m_menu_subnet = driver.findElement(By.xpath("//i[@class='fa fa fa-retweet']"));
            m_menu_subnet.click();
			Thread.sleep(5000);
			
            //Check Subnet List Page
			WebElement s_menu_subnetList = driver.findElement(By.xpath("//a[@href='#/subnet/list']"));
            s_menu_subnetList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Subnet List"))
				driver.close();
			
			
            WebElement btn_subnetlist_add = driver.findElement(By.xpath("//i[@class='fa fa-plus fa-fw']"));
            btn_subnetlist_add.click();
            
            /*
            //Check Add New Subnet Page
            WebElement s_menu_addsubnet = driver.findElement(By.xpath("//*[contains(text(), 'Add Subnet')]"));
            s_menu_addsubnet.click();
			*/
            
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Add New Subnet"))
				driver.close();
			
			
		    WebElement btn_addsubnet = driver.findElement(By.xpath("//input[@class='isul-edit-button']"));
		    btn_addsubnet.click();
		    
		    // Name Error Message
		    WebElement noNetworkaddresssubnet = driver.findElement(By.xpath("//form//div//label[3]//span[@class='form-error']"));
		    String Errmsg_noNetworkaddresssubnet = noNetworkaddresssubnet.getText();
			System.out.println("No Input + Add Subnet - Network Address Error Message is : " + Errmsg_noNetworkaddresssubnet);
		    
			//ip Error Message    
		    WebElement noMasksubnet = driver.findElement(By.xpath("//form//div//label[4]//span[@class='form-error']"));
		    String Errmsg_noMasksubnet = noMasksubnet.getText();
			System.out.println("No Input + Add Subnet - Mask Error Message is : " + Errmsg_noMasksubnet);
			
			// Hostname Error Message
			WebElement novLansubnet = driver.findElement(By.xpath("//form//div//label[6]//span[@class='form-error']"));
		    String Errmsg_novLansubnet = novLansubnet.getText();
			System.out.println("No Input + Add Subnet - vLan Error Message is : " + Errmsg_novLansubnet);
					    
		    assertEquals(Errmsg_noNetworkaddresssubnet, SubnetNullNetworkAddressErrMsg);
		    assertEquals(Errmsg_noMasksubnet, SubnetNullMaskErrMsg);
		    assertEquals(Errmsg_novLansubnet, SubnetNullvLanErrMsg);
		    
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Subnet-No Input Subnet.png"));
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
		System.out.println("***** Finished Subnet Test Case *****");
		driver.quit();   
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
	


