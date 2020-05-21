package webdriver;

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
		driver.manage().window().maximize();
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
	public void TC_05_LoginPass() throws InterruptedException {
		// Login Page Url matching
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("send2")).click();
		Thread.sleep(3000);
		String Title= driver.findElement(By.xpath(".//*[@class='dashboard']/div/h1")).getText();
		String User_Name = driver.findElement(By.xpath(".//*[@class='hello']/strong")).getText();
		//String Account	= driver.findElement(By.xpath(".//*[@class='box-content']/p[contains(text(),'Automation Testing')]")).getText();
		Assert.assertEquals(Title, "MY DASHBOARD");
		Assert.assertEquals(User_Name, "Hello, Automation Testing!");
		//Assert.assertEquals(Account, "Automation Testing");
		Thread.sleep(3000);
		//logout 
		driver.findElement(By.xpath(".//*[@id='header']//div[@class='account-cart-wrapper']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='header-account']//li[last()]")).click();
		//System.out.println("Pass TC_02");
	}
	
	@Test
	public void TC_06_SignUP() throws InterruptedException {
		// Login Page Url matching
		
		driver.findElement(By.xpath(".//*[@id='login-form']//div[@class='col-1 new-users']//div[@class='buttons-set']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("firstname")).sendKeys("Nguyen");
		driver.findElement(By.name("middlename")).sendKeys("Van");
		driver.findElement(By.name("lastname")).sendKeys("An");
		driver.findElement(By.id("email_address")).sendKeys("annguyen@yopmail.com");
		driver.findElement(By.cssSelector("#password")).sendKeys("123456");
		driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
		driver.findElement(By.xpath(".//*[@class='buttons-set']//button[@class='button']")).click();
		Thread.sleep(3000);
		String Title= driver.findElement(By.xpath(".//*[@class='dashboard']/div/h1")).getText();
		String User_Name = driver.findElement(By.xpath(".//*[@class='hello']/strong")).getText();
		//String Account	= driver.findElement(By.xpath(".//*[@class='box-content']/p[contains(text(),'Automation Testing')]")).getText();
		Assert.assertEquals(Title, "MY DASHBOARD");
		Assert.assertEquals(User_Name, "Hello, Automation Testing!");
		//Assert.assertEquals(Account, "Automation Testing");
		Thread.sleep(3000);
		//logout 
		driver.findElement(By.xpath(".//*[@id='header']//div[@class='account-cart-wrapper']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='header-account']//li[last()]")).click();
		//System.out.println("Pass TC_02");
	}

	@AfterClass
	public void Close_Browser()
	{
		driver.quit();
	}
}
