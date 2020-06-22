package webdriver;

import java.sql.Driver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class dropDownList {

	WebDriver driver;
	Select select;
	
@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}

public void TC_01_selectOneValue() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	select = new Select(driver.findElement(By.id("job1")));
	Assert.assertFalse(select.isMultiple());
	driver.findElement(By.id("job1")).click();
	//select by visible Text
	select.selectByVisibleText("Adhoc Testing");
	Assert.assertEquals(select.getFirstSelectedOption().getText(), "Adhoc Testing");
	//select by Value
	sleepInSecond(2);
	select.selectByValue("mobile");
	Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
	sleepInSecond(2);
	//select by Index
	select.selectByIndex(9);
	Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
	sleepInSecond(2);
	List<WebElement> alljob1 = select.getOptions();
	int alljobsize = alljob1.size();
	if(alljobsize == 10) {
		System.out.println("Job 1 has "+ alljobsize +"options");
	}else {
		System.out.println("job 1 has not enough"+alljobsize +"options");
	}
		
}


public void TC_02_selectMultipleValue() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	select = new Select(driver.findElement(By.id("job2")));
	Assert.assertTrue(select.isMultiple());
	driver.findElement(By.id("job2")).click();
	//select by visible Text
	select.selectByVisibleText("Automation");
	select.selectByVisibleText("Mobile");
	select.selectByVisibleText("Desktop");

	List<WebElement> job2Selected = select.getAllSelectedOptions();
	int optionSelected = job2Selected.size();
	for (WebElement option: job2Selected) {
		System.out.println(option.getText());
	}
	System.out.println("Display"+optionSelected+ "in the dropdown list");
	select.deselectAll();
	optionSelected=select.getAllSelectedOptions().size();
	Assert.assertEquals(optionSelected, 0);
	
}
//check
@Test
public void TC_03_HTMLdropdownList() {
	driver.get("https://demo.nopcommerce.com/register");
	
	String email = "emily"+randomNum()+"@yopmail.com";
	driver.findElement(By.xpath("//a[text()='Register']")).click();
	//input data into the form
	driver.findElement(By.id("gender-female")).click();
	driver.findElement(By.id("FirstName")).sendKeys("Emily");
	driver.findElement(By.id("LastName")).sendKeys("Nguyen");
	select = new Select(driver.findElement(By.name("DateOfBirthDay")));
	driver.findElement(By.name("DateOfBirthDay")).click();
	select.selectByVisibleText("1");
	int dayItems = select.getOptions().size();
	System.out.println("Day dropdown has " + dayItems+ " items");
	
	select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
	driver.findElement(By.name("DateOfBirthMonth")).click();
	select.selectByVisibleText("May");
	int monthItems = select.getOptions().size();
	System.out.println("Day dropdown has " + monthItems+ " items");

	select = new Select(driver.findElement(By.name("DateOfBirthYear")));
	driver.findElement(By.name("DateOfBirthYear")).click();
	select.selectByVisibleText("1982");
	int yearItems = select.getOptions().size();
	System.out.println("Day dropdown has " + yearItems+ " items");
	
	driver.findElement(By.id("Email")).sendKeys(email);
	driver.findElement(By.id("Password")).sendKeys("12345678@");
	driver.findElement(By.id("ConfirmPassword")).sendKeys("12345678@");
	driver.findElement(By.id("register-button")).click();
	
	sleepInSecond(2);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
	
}


@AfterClass
public void closeBrowser() {
	driver.quit();
	
}
	
public void sleepInSecond(long time) {
	try {
		Thread.sleep(time*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public int randomNum() {
	Random rand = new Random();
	return rand.nextInt(9999);
}
}
