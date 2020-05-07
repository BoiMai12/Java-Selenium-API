package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.If;
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
public void LeadtoAccountPage ()
{
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("http://live.demoguru99.com/");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//Load to account page
	driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
	driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	String CurrentSignUpURL = driver.getCurrentUrl();
	
	System.out.println(CurrentSignUpURL);
	//jjhjhfdf3333

}

@Test
public void TC_01_LoginEmptyUsernameAndPass() {
	// Login Page Url matching
	driver.findElement(By.xpath(".//*[@id='send2']")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	String mess1= driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText();
	//System.out.println(mess1);
	Assert.assertEquals(mess1, "This is a required field.");
	//System.out.println("Pass TC_01");
	
}
@Test
public void TC_02_LoginInvalidEmail() {
	// Login Page Url matching
	driver.navigate().refresh();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("123423234@123455.123123");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.xpath(".//*[@id='send2']")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	String mess2= driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText();
	Assert.assertEquals(mess2, "Please enter a valid email address. For example johndoe@domain.com.");
	//System.out.println("Pass TC_02");
}
	
}
