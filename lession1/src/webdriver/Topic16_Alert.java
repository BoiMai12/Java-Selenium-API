package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic16_Alert {
	WebDriver driver;
	Alert alert;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	
	public void TC_01_acceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = driver.switchTo().alert();
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
	}

	
	public void TC_02_confirmAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
		
	}

	
	public void TC_03_promptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("boi mai");
		sleepInSecond(2);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: boi mai");	
		
	}
	

	public void TC_03_authenticationAlert() {
		String username = "admin";
		String password = "admin";
		
		driver.get("http://"+username+ ":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	@Test
	public void TC_04_advanceAuthenticationAlert() {
//		String username = "admin";
//		String password = "admin";
//		driver.get("http://"+username+ ":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
		handleAuthenticationAlert("http://the-internet.herokuapp.com/basic_auth", "admin", "admin");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public void handleAuthenticationAlert(String link, String username, String password) {
		String splitLink[]=link.split("//");
		link= splitLink[0]+"//"+username+":"+password+"@"+splitLink[1];
		driver.get(link);
	}

	
	public void TC_03_LoginFormDisplayed() {
		
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
