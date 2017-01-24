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

public class testLoginTest extends variable{
	
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
	    
		System.out.println("***** Starting Login Test Case *****");
	    
	}
	@Test(enabled = true, priority = 1)
	public void testNullInputTest() throws InterruptedException {
		System.out.println("*** Null Input ***");
		driver.navigate().to(appURL);
		String strPageTitle = driver.getTitle();

		AssertJUnit.assertTrue(strPageTitle.equalsIgnoreCase("Athene Login"));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		try {
			// find element user name & password & submit			
			WebElement username = driver.findElement(By.name("userid"));
			WebElement passwd = driver.findElement(By.name("pw"));
			WebElement login = driver.findElement(By.id("submit"));
			
			login.submit();
			Thread.sleep(3000);
			
			WebElement loginError = driver.findElement(By.className("ng-binding"));
			String loginErrormsg = loginError.getAttribute("innerHTML");
			System.out.println(loginErrormsg);
			
			assertEquals(loginErrormsg, LoginNoInputErrMsg);
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-null input.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			username.clear();
			passwd.clear();
		} catch (Error e) {
            verificationErrors.append(e.toString());
		}
	}
	
	
	@Test(enabled = true, priority = 2)
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
			
			assertEquals(loginErrormsg, LoginInvalidUserErrMsg);
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-invaild user.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			username.clear();
			passwd.clear();
		} catch (Error e) {
            verificationErrors.append(e.toString());
		}
	}
	@Test(enabled = true, priority = 3)
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
						
			assertEquals(loginErrormsg, LoginInvalidPasswordErrMsg);
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-invaild password.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			username.clear();
			passwd.clear();

		} catch (Error e) {
            verificationErrors.append(e.toString());
		}
	}
	
	@Test(enabled = true, priority = 4)
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
			
			// Dashbaord
            WebElement s_menu_dashboard = driver.findElement(By.xpath("//i[@class='fa fa-tachometer']"));
            s_menu_dashboard.click();
			Thread.sleep(5000);

			if (!driver.getPageSource().contains("Athene Dashboard Main"))
				driver.close();
			
			// Check Dashboard Main Page
            WebElement s_menu_dashboardmain = driver.findElement(By.xpath("//a[@href='#/dashboard/box']"));
            s_menu_dashboardmain.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Athene Dashboard Main"))
				driver.close();
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Dashboard Main.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// Service
            WebElement m_menu_service = driver.findElement(By.xpath("//i[@class='fa fa-cubes']"));
            m_menu_service.click();
			Thread.sleep(5000);
			
            // Check Service List Page
            WebElement s_menu_serviceList = driver.findElement(By.xpath("//a[@href='#/chaining/list']"));
            s_menu_serviceList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Athene Chaining Services"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Service List.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
            
            // Check Add New Page
            WebElement s_menu_addnew = driver.findElement(By.xpath("//a[@href='#/chaining/new']"));
            s_menu_addnew.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("New Network Service"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Add New.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// Cluster
            WebElement m_menu_cluster = driver.findElement(By.xpath("//i[@class='fa fa-cog']"));
            m_menu_cluster.click();
			Thread.sleep(5000);
			
            // Check Cluster List Page
            WebElement s_menu_clusterList = driver.findElement(By.xpath("//a[@href='#/cluster/list']"));
            s_menu_clusterList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Cluster List"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Cluster List.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
            // Check Add New Cluster Page
            WebElement s_menu_addcluster = driver.findElement(By.xpath("//a[@href='#/cluster/add']"));
            s_menu_addcluster.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Add New Cluster"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Add Cluster.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// Cnode
            WebElement m_menu_cnode = driver.findElement(By.xpath("//i[@class='fa fa-desktop']"));
            m_menu_cnode.click();
			Thread.sleep(5000);
			
            // Check Cnode List Page
            WebElement s_menu_cnodeList = driver.findElement(By.xpath("//a[@href='#/cnode/list']"));
            s_menu_cnodeList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Cnode List"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Cnode List.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
            // Check Add New Cnode Page
            WebElement s_menu_addcnode = driver.findElement(By.xpath("//a[@href='#/cnode/add']"));
            s_menu_addcnode.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Add New Cnode"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Add Cnode.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// Subnet
            WebElement m_menu_subnet = driver.findElement(By.xpath("//i[@class='fa fa fa-retweet']"));
            m_menu_subnet.click();
			Thread.sleep(5000);
			
            // Check Subnet List Page
            WebElement s_menu_subnetList = driver.findElement(By.xpath("//a[@href='#/subnet/list']"));
            s_menu_subnetList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Subnet List"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Subnet List.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
            // Check Add New Subnet Page
            WebElement s_menu_addsubnet = driver.findElement(By.xpath("//a[@href='#/subnet/add']"));
            s_menu_addsubnet.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Add New Subnet"))
				driver.close();
			
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Add Subnet.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// User
            WebElement m_menu_user = driver.findElement(By.xpath("//li/a/i[@class='fa fa fa-user-secret']"));
            m_menu_user.click();
			Thread.sleep(5000);
			
            // Check Your Profile Page
            WebElement s_menu_yourprofile = driver.findElement(By.xpath("//a[@href='#/user/edit']"));
            s_menu_yourprofile.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Edit Your Profile"))
				driver.close();
	
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Your Profile.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}	
			
			// Notification
            WebElement m_menu_noficiation = driver.findElement(By.xpath("//i[@class='fa fa-flask']"));
            m_menu_noficiation.click();
			Thread.sleep(5000);
			
            // Check Log Page
            WebElement s_menu_log = driver.findElement(By.xpath("//a[@href='#/notification/log']"));
            s_menu_log.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Notification Log"))
				driver.close();
	
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_Log.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}			
			
			Thread.sleep(5000);	
			
			// License
            WebElement m_menu_license = driver.findElement(By.xpath("//i[@class='fa fa-clipboard']"));
            m_menu_license.click();
			Thread.sleep(5000);
			
            // Check Log Page
            WebElement s_menu_information = driver.findElement(By.xpath("//a[@href='#/license/info']"));
            s_menu_information.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("License Information"))
				driver.close();
	
			// Capture
			try {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Login Test-vaild_License.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}			
			
			Thread.sleep(5000);	
			
            // Collapsed
            WebElement icon_collapsed = driver.findElement(By.xpath("//i[@class='fa fa-chevron-circle-left']"));
            icon_collapsed.click();
			Thread.sleep(1000);
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Collpased.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}		
			
			
			System.out.println("*** Logout ***");
		    WebElement profile = driver.findElement(By.xpath("//i[@class='fa fa fa-user-secret']"));
		    profile.click();
			Thread.sleep(1000);	

			
		    WebElement logout = driver.findElement(By.xpath("//div/div/a/i[@class='fa fa-sign-out']"));
		    logout.click();	    
		    
		    // Capture
		 	try {
		 		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 		FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Logout.png"));
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
		System.out.println("***** Finished Login Test Case *****");
		driver.quit();   
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
	


