package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Top23_uploadFile {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {

	}

	@Test
	public void TC_02_ValidatePageTitle() {

	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		
	}

	@AfterClass
	public void afterClass() {

	}
}
