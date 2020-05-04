package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	

}

@Test
public void TC_01_LoginEmptyUsernameAndPass() {
	// Login Page Url matching
	
}
	
}
