package attotest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.AssertJUnit;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTest {
	
	public RemoteWebDriver driver;
	public static String appURL = "http://www.google.com";
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL("http://10.51.11.12:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testGooglePageTitleInIEBrowser() {
		System.out.println("*** Navigation to Application ***");
		driver.navigate().to(appURL);
		String strPageTitle = driver.getTitle();
		System.out.println("*** Verifying page title ***");
		AssertJUnit.assertTrue(strPageTitle.equalsIgnoreCase("Google"));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		// Capture
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("scrFile.png"));
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
	


