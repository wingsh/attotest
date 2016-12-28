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

public class testClusterTest extends variable{
	
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
	    
		System.out.println("***** Starting Cluster Test Case *****");
	    
	}
	
	@Test(enabled = true, priority = 1)
	public void testNullInputTest() throws InterruptedException {
		System.out.println("*** No Input Cluster ***");
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
			
			// Cluster
            WebElement m_menu_cluster = driver.findElement(By.xpath("//*[contains(text(), 'Cluster')]"));
            m_menu_cluster.click();
			Thread.sleep(5000);
			
            //Check Cluster List Page
            WebElement s_menu_clusterList = driver.findElement(By.xpath("//*[contains(text(), 'Cluster List')]"));
            s_menu_clusterList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Cluster List"))
				driver.close();
			
            //Check Add New Cluster Page
            WebElement s_menu_addcluster = driver.findElement(By.xpath("//*[contains(text(), 'Add Cluster')]"));
            s_menu_addcluster.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Add New Cluster"))
				driver.close();
			
		    WebElement btn_publish = driver.findElement(By.xpath("//input[@class='isul-save-button']"));
		    btn_publish.click();
			
		    WebElement noinputcluster = driver.findElement(By.xpath("//span[@class='error ng-scope']"));
		    String Errmsg_noinputcluster = noinputcluster.getText();
		    System.out.println("No Input + Publish Error Message is : "+Errmsg_noinputcluster);
			assertEquals(Errmsg_noinputcluster, ClusterNoInputErrMsg);
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Cluster-No Input Cluster.png"));
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
		System.out.println("***** Finished Cluster Test Case *****");
		driver.quit();   
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
	


