package attotest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.AssertJUnit;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class testLoginTest {
	
	public RemoteWebDriver driver;
	public static String appURL = "http://10.61.129.81:8009";
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL("http://10.51.11.12:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testValidLoginTest() throws InterruptedException {
		System.out.println("*** Valid UserID & Password ***");
		driver.navigate().to(appURL);
		String strPageTitle = driver.getTitle();

		AssertJUnit.assertTrue(strPageTitle.equalsIgnoreCase("Athene Login"));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
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
		
		// Capture
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(timestamp+".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
	


