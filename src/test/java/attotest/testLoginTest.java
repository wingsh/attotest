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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testLoginTest {
	
	public RemoteWebDriver driver;
	public static String appURL = "http://10.61.129.81:8009";
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private StringBuffer verificationErrors = new StringBuffer();

	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL("http://10.51.11.12:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
		
	    String loginWindow = driver.getWindowHandle();
	    driver.switchTo().window(loginWindow);  
	    
	}
	
	@Test(enabled = true)
	public void testInvaildUserTest() throws InterruptedException {
		System.out.println("*** Invalid UserID ***");
		driver.navigate().to(appURL);
		String strPageTitle = driver.getTitle();

		AssertJUnit.assertTrue(strPageTitle.equalsIgnoreCase("Athene Login"));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		try {
			// find element user name & password & submit			
			WebElement username = driver.findElement(By.name("userid"));
			WebElement passwd = driver.findElement(By.name("pw"));
			WebElement login = driver.findElement(By.id("submit"));
			
			username.sendKeys("invaild");
			Thread.sleep(1000);
			passwd.sendKeys("invaild");
			Thread.sleep(1000);
			
			login.submit();
			Thread.sleep(3000);
			
			WebElement loginError = driver.findElement(By.className("ng-binding"));
			String loginErrormsg = loginError.getAttribute("innerHTML");
			System.out.println(loginErrormsg);
			
			assertEquals(loginErrormsg, "invalid password or id");
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("Login Test - invaild user"+timestamp+".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			username.clear();
			passwd.clear();
		} catch (Error e) {
            verificationErrors.append(e.toString());
		}
	}
	@Test(enabled = true)
	public void testInvaildPasswordTest() throws InterruptedException {
		System.out.println("*** Invalid Password ***");
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
			passwd.sendKeys("invaild");
			Thread.sleep(1000);
						
			login.submit();
			Thread.sleep(3000);
			
			WebElement loginError = driver.findElement(By.className("ng-binding"));
			String loginErrormsg = loginError.getAttribute("innerHTML");
			System.out.println(loginErrormsg);
						
			assertEquals(loginErrormsg, "invalid password or id");
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("Login Test - invaild password"+timestamp+".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			username.clear();
			passwd.clear();

		} catch (Error e) {
            verificationErrors.append(e.toString());
		}
	}
	
	@Test(enabled = true)
	public void testValidLoginTest() throws InterruptedException {
		System.out.println("*** Valid UserID & Password ***");
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
			
            WebElement m_menu_service = driver.findElement(By.xpath("//*[contains(text(), 'Service')]"));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(m_menu_service));
			
            m_menu_service.click();
			Thread.sleep(5000);
			
            //Check Service List Page
            WebElement s_menu_serviceList = driver.findElement(By.xpath("//*[contains(text(), 'Service List')]"));
            s_menu_serviceList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Athene Chaining Services"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Service List"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
            
            //Check Add New Page
            WebElement s_menu_addnew = driver.findElement(By.xpath("//*[contains(text(), 'Add New')]"));
            s_menu_addnew.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("New Network Service"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Add New"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
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
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Cluster List"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
            //Check Add New Cluster Page
            WebElement s_menu_addcluster = driver.findElement(By.xpath("//*[contains(text(), 'Add Cluster')]"));
            s_menu_addcluster.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Add New Cluster"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Add Cluster"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
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
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Cnode List"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
            //Check Add New Cnode Page
            WebElement s_menu_addcnode = driver.findElement(By.xpath("//*[contains(text(), 'Add Cnode')]"));
            s_menu_addcnode.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Add New Cnode"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Add Cnode"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// Subnet
            WebElement m_menu_subnet = driver.findElement(By.xpath("//*[contains(text(), 'Subnet')]"));
            m_menu_subnet.click();
			Thread.sleep(5000);
			
            //Check Subnet List Page
            WebElement s_menu_subnetList = driver.findElement(By.xpath("//*[contains(text(), 'Subnet List')]"));
            s_menu_subnetList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Subnet List"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Subnet List"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
            //Check Add New Subnet Page
            WebElement s_menu_addsubnet = driver.findElement(By.xpath("//*[contains(text(), 'Add Subnet')]"));
            s_menu_addsubnet.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Add New Subnet"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Add Subnet"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// User
            WebElement m_menu_user = driver.findElement(By.xpath("//*[contains(text(), 'User')]"));
            m_menu_user.click();
			Thread.sleep(5000);
			
            //Check Your Profile Page
            WebElement s_menu_yourprofile = driver.findElement(By.xpath("//*[contains(text(), 'Your Profile')]"));
            s_menu_yourprofile.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Edit Yout Profile"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("Login Test - vaild - Your Profile"+timestamp+".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		 driver.quit();       
		 String verificationErrorString = verificationErrors.toString();
		 if (!"".equals(verificationErrorString)) {
			 fail(verificationErrorString);
		 }
	 }
}
	


