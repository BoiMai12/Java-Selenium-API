package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC05 {
	WebDriver driver;

	@BeforeClass
	public void LeadtoAccountPage () throws InterruptedException
	{
		driver = new FirefoxDriver();
		Thread.sleep(2000);
		//driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);
		//Load to account page
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='header-account']//a[@title='My Account']")).click();;
		Thread.sleep(2000);
		String CurrentSignUpURL = driver.getCurrentUrl();
		
		System.out.println(CurrentSignUpURL);
		//jjhjhfdf333356546574546

	}

	
	@Test
	public void TC_06_SignUP() throws InterruptedException {
		// Login Page Url matching
		
		driver.findElement(By.xpath(".//*[@class='button' and @title='Create an Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("firstname")).sendKeys("Nguyen");
		driver.findElement(By.id("middlename")).sendKeys("Van");
		driver.findElement(By.id("lastname")).sendKeys("An");
		String email = "annguyen" + RandomNumber()+ "@yopmail.com";
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.cssSelector("#password")).sendKeys("123456");
		driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
		driver.findElement(By.xpath(".//*[@class='buttons-set']//button[@class='button']")).click();
		Thread.sleep(3000);
		String Success_Message= driver.findElement(By.xpath(".//*[@class='success-msg']//span")).getText();
		//String User_Name = driver.findElement(By.xpath(".//*[@class='hello']/strong")).getText();
		Assert.assertEquals(Success_Message, "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='hello']/strong")).getText(), "Hello, Nguyen Van An!");
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@class='box-content']/p[contains(text(),'Nguyen Van An')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@class='box-content']/p[contains(.,'"+email+"')]")).isDisplayed());
		
	
	}

	@AfterClass
	public void Close_Browser()
	{
		driver.quit();
	}
	public int RandomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
