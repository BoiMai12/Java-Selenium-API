package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUp {

	WebDriver driver;

	@BeforeClass
	public void LeadtoAccountPage() throws InterruptedException {
		driver = new FirefoxDriver();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);
		// Load to account page
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='header-account']//a[@title='My Account']")).click();
		;
		Thread.sleep(2000);
		String CurrentSignUpURL = driver.getCurrentUrl();

		System.out.println(CurrentSignUpURL);
		// jjhjhfdf333356546574546

	}

	@Test
	public void TC_01_LoginEmptyUsernameAndPass() {
		// Login Page Url matching
		driver.findElement(By.id("send2")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String mess1 = driver.findElement(By.id("advice-required-entry-email")).getText();
		// System.out.println(mess1);
		Assert.assertEquals(mess1, "This is a required field.");
		// System.out.println("Pass TC_01");

	}

	@Test
	public void TC_02_LoginInvalidEmail() throws InterruptedException {
		// Login Page Url matching
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#email")).sendKeys("123423234@123455.123123");
		driver.findElement(By.cssSelector("#send2")).click();
		Thread.sleep(2000);
		String mess2 = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(mess2, "Please enter a valid email address. For example johndoe@domain.com.");
		// System.out.println("Pass TC_02");
	}

	@Test
	public void TC_03_LoginInvalidEmail() throws InterruptedException {
		// Login Page Url matching
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys("123423234@123455.123123");
		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);
		// String mess2=
		// driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
		// System.out.println("Pass TC_02");
	}

	@Test
	public void TC_04_LoginInvalidPass() throws InterruptedException {
		// Login Page Url matching
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		Thread.sleep(1000);
		driver.findElement(By.name("send")).click();
		Thread.sleep(3000);

		Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
		// System.out.println("Pass TC_02");
	}

	@Test
	public void TC_05_LoginPass() throws InterruptedException {
		// Login Page Url matching

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();
		Thread.sleep(3000);
		String Title = driver.findElement(By.xpath(".//*[@class='dashboard']/div/h1")).getText();
		String User_Name = driver.findElement(By.xpath(".//*[@class='hello']/strong")).getText();
		Assert.assertEquals(Title, "Thank you for registering with Main Website Store.");
		Assert.assertEquals(User_Name, "Hello, Automation Testing!");
		Assert.assertTrue(
				driver.findElement(By.xpath(".//*[@class='box-content']/p[contains(text(),'Automation Testing')]"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath(".//*[@class='box-content']/p[contains(.,'automation@gmail.com')]"))
						.isDisplayed());
		// Assert.assertEquals(Account, "Automation Testing");
		Thread.sleep(3000);
		// logout
		driver.findElement(By.xpath(".//*[@id='header']//div[@class='account-cart-wrapper']/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='header-account']//li[last()]")).click();
		// System.out.println("Pass TC_02");
	}

	@Test
	public void TC_06_SignUP() throws InterruptedException {
		// Login Page Url matching

		driver.findElement(By.xpath(".//*[@class='button' and @title='Create an Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("firstname")).sendKeys("Nguyen");
		driver.findElement(By.id("middlename")).sendKeys("Van");
		driver.findElement(By.id("lastname")).sendKeys("An");
		String email = "annguyen" + RandomNumber() + "@yopmail.com";
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.cssSelector("#password")).sendKeys("123456");
		driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
		driver.findElement(By.xpath(".//*[@class='buttons-set']//button[@class='button']")).click();
		Thread.sleep(3000);
		String Success_Message = driver.findElement(By.xpath(".//*[@class='success-msg']//span")).getText();
		String User_Name = driver.findElement(By.xpath(".//*[@class='hello']/strong")).getText();
		Assert.assertEquals(Success_Message, "Thank you for registering with Main Website Store.");
		Assert.assertEquals(User_Name, "Hello, Nguyen Van An!");
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@class='box-content']/p[contains(text(),'Nguyen Van An')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@class='box-content']/p[contains(.,'" + email + "')]"))
				.isDisplayed());

	}

	@AfterClass
	public void Close_Browser() {
		driver.quit();
	}

	public int RandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
