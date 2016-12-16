package attotest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.AssertJUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

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
			Thread.sleep(1000);
			
            WebElement m_menu_service = driver.findElement(By.xpath("//*[contains(text(), 'Service')]"));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(m_menu_service));
			
            m_menu_service.click();
			Thread.sleep(1000);
			
            //Check Service List Page
            WebElement s_menu_serviceList = driver.findElement(By.xpath("//*[contains(text(), 'Service List')]"));
            s_menu_serviceList.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene Chaining Services"))
				driver.close();
            
            //Check Add New Page
            WebElement s_menu_addnew = driver.findElement(By.xpath("//*[contains(text(), 'Add New')]"));
            s_menu_addnew.click();
			Thread.sleep(1000);     
			
			if (!driver.getPageSource().contains("New Network Service"))
				driver.close();
			
			// Object
            WebElement m_menu_object = driver.findElement(By.xpath("//*[contains(text(), 'Object')]"));
            m_menu_object.click();
			Thread.sleep(1000);
			
            //Check Node Page
            WebElement s_menu_node = driver.findElement(By.xpath("//*[contains(text(), 'Node')]"));
            s_menu_node.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene NODE Setting"))
				driver.close();
			
            //Check Network Page
            WebElement s_menu_network = driver.findElement(By.xpath("//*[contains(text(), 'Network')]"));
            s_menu_network.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene NETWORK Setting"))
				driver.close();
			
            //Check Range Page
            WebElement s_menu_range = driver.findElement(By.xpath("//*[contains(text(), 'Range')]"));
            s_menu_range.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene RANGE Setting"))
				driver.close();
			
	        //Check Group Page
            WebElement s_menu_group = driver.findElement(By.xpath("//*[contains(text(), 'Group')]"));
            s_menu_group.click();
			Thread.sleep(1000);
			
			if (!driver.getPageSource().contains("Athene GROUP Setting"))
				driver.close();
			
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
	


