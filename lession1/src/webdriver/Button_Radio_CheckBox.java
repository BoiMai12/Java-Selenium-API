package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Button_Radio_CheckBox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor)driver;
		
	}


	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		boolean status = loginButton.isEnabled();
		System.out.println("it is a enable login button: "+status);
		driver.findElement(By.id("login_username")).sendKeys("boi@yopmail.com");
		driver.findElement(By.id("login_password")).sendKeys("eeeee3333333333333");
		sleepInSecond(3);
		status = loginButton.isEnabled();
		System.out.println("it is a enable login button: "+status);
		Assert.assertTrue(status);
//		driver.get("https://www.fahasa.com/customer/account/create");
//		sleepInSecond(2);
		
		
	}

	
	public void TC_02_defaultCheckbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		driver.findElement(By.id("eq5")).click();
		Assert.assertTrue(driver.findElement(By.id("eq5")).isSelected());
		sleepInSecond(2);
		driver.findElement(By.id("eq5")).click();
		Assert.assertFalse(driver.findElement(By.id("eq5")).isSelected());
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		driver.findElement(By.id("engine3")).click();
		
		if (driver.findElement(By.id("engine3")).isSelected())
		{
			System.out.println("Radio is checked");
		}
		else
		{
			System.out.println("Radio has not checked yet. please checked it");
			driver.findElement(By.id("engine3")).click();
			
		}
		
	}

	@Test
	public void TC_03_customCheckboxOrRadioButton() {
		
		driver.get("https://material.angular.io/components/radio/examples");
		
		By radio = By.xpath("//div[contains(text(),'Summer')]/preceding-sibling::div/input");
		clickByJavascript(driver.findElement(radio));
		sleepInSecond(3);
		if (driver.findElement(radio).isSelected()) {
		Assert.assertTrue(driver.findElement(radio).isSelected());
		}
		else {
			clickByJavascript(driver.findElement(radio));
			sleepInSecond(3);
			Assert.assertTrue(driver.findElement(radio).isSelected());
		}
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkbox_1 = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input");
		By checkbox_2 = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::div/input");
		clickByJavascript(driver.findElement(checkbox_1));
		clickByJavascript(driver.findElement(checkbox_2));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(checkbox_1).isSelected());
		Assert.assertTrue(driver.findElement(checkbox_2).isSelected());
		
		clickByJavascript(driver.findElement(checkbox_1));
		clickByJavascript(driver.findElement(checkbox_2));
		Assert.assertFalse(driver.findElement(checkbox_1).isSelected());
		Assert.assertFalse(driver.findElement(checkbox_2).isSelected());
		
	}

	public void clickByJavascript(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	@AfterClass
	public void afterClass() {
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
}
