package webdriver;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Check_Element {

WebDriver driver;

@BeforeClass
public void LeadtoAccountPage () throws InterruptedException
{
	//System.setProperty("webdriver.chrome.driver", "D:\\BoiMai\\Class16\\01-Software\\chromedriver.exe");
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	driver.manage().window().maximize();

	Thread.sleep(2000);
	
}




public void TC_01_VerifyURLs() throws InterruptedException {
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	
}


public void TC_02_Title() throws InterruptedException {
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
	Assert.assertEquals(driver.getTitle(), "Customer Login");
	driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	
}


public void TC_03_RegisterPage() throws InterruptedException {
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
	driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	driver.navigate().back();
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	driver.navigate().forward();
	Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
}

public void TC_04_getPagesource() throws InterruptedException {
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath(".//*[@class='footer']//a[@title='My Account']")).click();
	String getSourceLogin = driver.getPageSource();
	Assert.assertTrue(getSourceLogin.contains("Login or Create an Account"));
	driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	String getSourceAccount = driver.getPageSource();
	Assert.assertTrue(getSourceAccount.contains("Create an Account"));

}

public void TC_01_ElenmentIsDisplay() throws InterruptedException {
	
	driver.get("https://automationfc.github.io/basic-form/index.html");
	

	if (elementIsDisplay("//input[@name='user_email']"))
	{
		driver.findElement(By.xpath("//input[@name='user_email']")).sendKeys("Automation Testing");
		Thread.sleep(2000);
	}
	if (elementIsDisplay("//textarea[@id='edu']"))
	{
		driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
		Thread.sleep(2000);
	}	
	
	if (elementIsDisplay("//input[@id='under_18']"))
	{
		driver.findElement(By.id("under_18")).click();
		Thread.sleep(2000);
	}
}


public void TC_02_ElenmentIsEnable() throws InterruptedException {
	
	driver.get("https://automationfc.github.io/basic-form/index.html");
	elementIsEnable("//input[@name='user_email']");
	elementIsEnable("//input[@id='under_18']");
	elementIsEnable("//textarea[@id='edu']");
	elementIsEnable("//select[@id='job1']");
	elementIsEnable("//select[@id='job2']");
	elementIsEnable("//input[@id='development']");
	elementIsEnable("//input[@id='slider-1']");
	elementIsEnable("//input[@name='user_pass']");
	elementIsEnable("//input[@id='radio-disabled']");
	elementIsEnable("//textarea[@id='bio']");
	elementIsEnable("//select[@id='job3']");
	elementIsEnable("//input[@id='check-disbaled']");
	
	
}

@Test
public void TC_03_ElenmentIsSelect() throws InterruptedException {
	
	driver.get("https://automationfc.github.io/basic-form/index.html");
	if (elementIsDisplay("//input[@id='under_18']"))
	{
		driver.findElement(By.id("under_18")).click();
		Thread.sleep(2000);
	}
	if (elementIsDisplay("//input[@id='development']"))
	{
		driver.findElement(By.id("development")).click();
		Thread.sleep(2000);
	}
	//check checked checkbox
	elementIsSelected("//input[@id='under_18']");
	elementIsSelected("//input[@id='development']");
	driver.findElement(By.id("development")).click();
	//check unchecked
	elementIsSelected("//input[@id='development']");
}


public boolean elementIsDisplay(String xpathValue) {
	WebElement element = driver.findElement(By.xpath(xpathValue));
	if (element.isDisplayed()) {
		System.out.println("Element with xpath"+ xpathValue + "is displayed");
		return true;
	}
	else {
		System.out.println("Element with xpath"+ xpathValue + "is not displayed");
		return false;
	}
}

public boolean elementIsEnable(String xpathValue) {
	WebElement element = driver.findElement(By.xpath(xpathValue));
	if (element.isEnabled()) {
		System.out.println("Element with xpath"+ xpathValue + "is enable");
		return true;
	}
	else {
		System.out.println("Element with xpath"+ xpathValue + "is disable");
		return false;
	}
}

public boolean elementIsSelected(String xpathValue) {
	WebElement element = driver.findElement(By.xpath(xpathValue));
	if (element.isSelected()) {
		System.out.println("Element with xpath"+ xpathValue + "is selected");
		return true;
	}
	else {
		System.out.println("Element with xpath"+ xpathValue + "is deselected");
		return false;
	}
}


@AfterClass
public void Close_Browser()
{
	driver.quit();
}
}
