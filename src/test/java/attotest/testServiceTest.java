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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testServiceTest extends variable{
	
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
	    
		System.out.println("***** Starting Service Test Case *****");
	    
	}
	
	@Test(enabled = true, priority = 1)
	public void testInvalidServiceTest() throws InterruptedException {
		System.out.println("*** No Input Service ***");
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
			
			WebElement m_menu_service = driver.findElement(By.xpath("//i[@class='fa fa-cubes']"));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(m_menu_service));
			
            m_menu_service.click();
			Thread.sleep(5000);
			
            //Check Service List Page
			WebElement s_menu_serviceList = driver.findElement(By.xpath("//a[@href='#/chaining/list']"));
            s_menu_serviceList.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("Athene Chaining Services"))
				driver.close();
            
            //Check Add New Page
			WebElement s_menu_addnew = driver.findElement(By.xpath("//a[@href='#/chaining/new']"));
            s_menu_addnew.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("New Network Service"))
				driver.close();

		    WebElement btn_save = driver.findElement(By.xpath("//i[@class='fa fa-save']"));
			btn_save.click();
			
			Thread.sleep(1000);	
			
		    WebElement nochoosecluster = driver.findElement(By.xpath("//div[@class='isul-notify-panel ng-binding']"));
		    String Errmsg_nochoosecluster = nochoosecluster.getText();
			System.out.println("No Input + Save - Error Message is : " + Errmsg_nochoosecluster);
			assertEquals(Errmsg_nochoosecluster, ServiceNoInputErrMsg);
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Service-No Choose Cluster.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	
			Thread.sleep(1000);	
			
			Select select_service_cluster = new Select(driver.findElementByXPath("//select[@class='ng-pristine ng-untouched ng-valid']"));
		    //text_cluster_type.click();
			Thread.sleep(3000);	
			select_service_cluster.selectByVisibleText("Cluster1");
			
			WebElement text_service_title = driver.findElement(By.xpath("//input[@class='isul-border-line ng-pristine ng-untouched ng-valid']"));
			text_service_title.sendKeys(letter129);
			
			btn_save.click();
			
			Thread.sleep(1000);	
			
		    WebElement maxtitle = driver.findElement(By.xpath("//div[@class='isul-notify-panel ng-binding']"));
		    String ErrormsgMaxTitle = maxtitle.getText();
			System.out.println("Max Titlet + Save - Error Message is : " + ErrormsgMaxTitle);
			assertEquals(ErrormsgMaxTitle, ServicemaxTitleErrMsg);
			
			Thread.sleep(3000);	
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Service-Max String Title.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			} catch (Error e) {
	            verificationErrors.append(e.toString());
		}
		
	}
	
	
	@Test(enabled = false, priority = 2)
	public void testNOTServiceTest() throws InterruptedException {
		System.out.println("*** Service Max Title Test ***");
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
			
            //Check Add New Page
            WebElement s_menu_addnew = driver.findElement(By.xpath("//*[contains(text(), 'Add New')]"));
            s_menu_addnew.click();
			Thread.sleep(5000);
			
			if (!driver.getPageSource().contains("New Network Service"))
				driver.close();
			
		    Select select_service_cluster = new Select(driver.findElementByXPath("//select[@class='ng-pristine ng-untouched ng-valid']"));
		    //text_cluster_type.click();
			Thread.sleep(5000);	
			select_service_cluster.selectByVisibleText("Cluster1");
			
			WebElement text_service_title = driver.findElement(By.xpath("//input[@class='isul-border-line ng-pristine ng-untouched ng-valid']"));
			text_service_title.sendKeys(letter129);
			
			

		    WebElement btn_save = driver.findElement(By.xpath("//i[@class='fa fa-save']"));
			btn_save.click();
			
			
		    WebElement maxtitle = driver.findElement(By.xpath("//div[@class='isul-notify-panel ng-binding']"));
		    String ErrormsgMaxTitle = maxtitle.getText();
			assertEquals(ErrormsgMaxTitle, ServicemaxTitleErrMsg);
			
			// Capture
			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CAPTURE_PATH+timestamp+" Service-Max String Title.png"));
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
		System.out.println("***** Finished Service Test Case *****");
		driver.quit();   
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
	


