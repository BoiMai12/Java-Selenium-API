package webdriver;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Textbox_TextArea {
	WebDriver driver;
	String userID ="mngr265488";
	String passWord ="vUnYnAj";
//	String customerID ="8748";
//	String userID, passWord;
	String customerID;
	String loginURL, name, dateOfBirth,address, city, state, pin, mobilenumber,email, pass, genderM, genderF;
	By cusNameTextBox = By.name("name");
	By maleRadio = By.xpath("//input[@value='m']");
	By FemaleRadio = By.xpath("//input[@value='f']");
	By dateOfBirthTextBox = By.name("dob");
	By addressTextBox = By.name("addr");
	By cityTextBox = By.name("city");
	By stateeTextBox = By.name("state");
	By pinTextBox = By.name("pinno");
	By mobileNumberTextBox = By.name("telephoneno");
	By eMailTextBox = By.name("emailid");
	By passTextBox = By.name("password");
	// element view customer page
	
	By cusNameLocator = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By genderLocator = By.xpath("//td[text()='Gender']/following-sibling::td");
	By dateOfBirthLocator = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By addressLocator = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityLocator= By.xpath("//td[text()='City']/following-sibling::td");
	By stateLocator = By.xpath("//td[text()='State']/following-sibling::td");
	By pinLocator= By.xpath("//td[text()='Pin']/following-sibling::td");
	By mobileNumberLocator = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By eMailLocator = By.xpath("//td[text()='Email']/following-sibling::td");
	By customerIDLocator = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	
	
@BeforeClass
 void beforeClass() {
	  driver = new FirefoxDriver();
//	  System.setProperty("webdriver.chrome.driver", "D:\\BoiMai\\Class16\\01-Software\\chromedriver.exe");
//	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	  driver.manage().window().maximize();
	  driver.get("http://demo.guru99.com/v4/");
	  loginURL = driver.getCurrentUrl();
	  name ="Nguyen Van A";
	  genderM="male";
	  dateOfBirth="1990-10-04";
	  address = "Tran hung dao\nQuan Binh Thanh\nTphcm";
	  city = "TPHCM";
	  state= "Binh thanh District";
	  pin = "700000";
	  mobilenumber="1411122222222";
	  email="boi"+ randomNum() +"@yopmail.com";
	  pass="1234556";
	  
	  //data to edit customer
	  

}

public void TC01_Register() {
	    String email = "boi"+ randomNum() +"@yopmail.com";
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
}
@Test
public void TC02_login() {
	driver.get(loginURL);
	driver.findElement(By.name("uid")).sendKeys(userID);
	driver.findElement(By.name("password")).sendKeys(passWord);
	driver.findElement(By.name("btnLogin")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : "+userID);
}

@Test
public void TC03_createNewCustomer() {

	
	driver.findElement(By.xpath("//a[text()='New Customer']")).click();	
	driver.findElement(cusNameTextBox).sendKeys(name);
	driver.findElement(dateOfBirthTextBox).sendKeys(dateOfBirth);
	driver.findElement(addressTextBox).sendKeys(address);
	driver.findElement(cityTextBox).sendKeys(city);
	driver.findElement(stateeTextBox).sendKeys(state);
	driver.findElement(pinTextBox).sendKeys(pin);
	driver.findElement(mobileNumberTextBox).sendKeys(mobilenumber);
	driver.findElement(eMailTextBox).sendKeys(email);
	driver.findElement(passTextBox).sendKeys(pass);
	
	driver.findElement(By.name("sub")).click();
//Assert.assertEquals(driver.findElement(By.xpath("//td/marquee")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
// check data after creating
	customerID = driver.findElement(customerIDLocator).getText();
	System.out.println(customerID);
	Assert.assertEquals(driver.findElement(cusNameLocator).getText(), name);
	Assert.assertEquals(driver.findElement(genderLocator).getText(), genderM);
	Assert.assertEquals(driver.findElement(dateOfBirthLocator).getText(), dateOfBirth);
	Assert.assertEquals(driver.findElement(addressLocator).getText(), address.replace("\n", " "));
	Assert.assertEquals(driver.findElement(cityLocator).getText(), city);
	Assert.assertEquals(driver.findElement(stateLocator).getText(), state);
	Assert.assertEquals(driver.findElement(pinLocator).getText(), pin);
	Assert.assertEquals(driver.findElement(mobileNumberLocator).getText(), mobilenumber);
	Assert.assertEquals(driver.findElement(eMailLocator).getText(), email);
	

<<<<<<< HEAD
}

@Test
public void TC04_editNewCustomer() {
	
	
	
	driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	driver.findElement(By.name("cusid")).sendKeys(customerID);
	driver.findElement(By.name("AccSubmit")).click();
	Assert.assertFalse(driver.findElement(cusNameTextBox).isEnabled());
	Assert.assertFalse(driver.findElement(By.name("gender")).isEnabled());
	Assert.assertFalse(driver.findElement(dateOfBirthTextBox).isEnabled());
	driver.findElement(addressTextBox).clear();
	driver.findElement(addressTextBox).sendKeys("1200 Tran hung dao\nQuan Binh Thanh\nTphcm");
	driver.findElement(cityTextBox).clear();
	driver.findElement(cityTextBox).sendKeys("Hanoi");
	driver.findElement(stateeTextBox).clear();
	driver.findElement(stateeTextBox).sendKeys("Binh ChanhDistrict");
	driver.findElement(pinTextBox).clear();
	driver.findElement(pinTextBox).sendKeys("700011");
	driver.findElement(mobileNumberTextBox).clear();
	driver.findElement(mobileNumberTextBox).sendKeys("12345679999");
	driver.findElement(eMailTextBox).clear();
	driver.findElement(eMailTextBox).sendKeys(email);
	sleepInSecond(10);
	driver.findElement(By.name("sub")).click();
//Assert.assertEquals(driver.findElement(By.xpath("//td/marquee")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer details updated Successfully!!!");
// check data after editing
	Assert.assertEquals(driver.findElement(customerIDLocator).getText(), customerID);
	Assert.assertEquals(driver.findElement(cusNameLocator).getText(), name);
	Assert.assertEquals(driver.findElement(genderLocator).getText(), genderM);
	Assert.assertEquals(driver.findElement(dateOfBirthLocator).getText(), dateOfBirth);
	Assert.assertEquals(driver.findElement(addressLocator).getText(), "1200 Tran hung dao\nQuan Binh Thanh\nTphcm".replace("\n", " "));
	Assert.assertEquals(driver.findElement(cityLocator).getText(), "Hanoi");
	Assert.assertEquals(driver.findElement(stateLocator).getText(), "Binh ChanhDistrict");
	Assert.assertEquals(driver.findElement(pinLocator).getText(), "700011");
	Assert.assertEquals(driver.findElement(mobileNumberLocator).getText(), "12345679999");
	Assert.assertEquals(driver.findElement(eMailLocator).getText(), email);
	
=======
>>>>>>> branch 'master' of https://github.com/BoiMai12/Java-Selenium-API.git
}
	
	
public int randomNum() {
	Random rand = new Random();
	return rand.nextInt(9999);
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
public void closeBrowser() {
	 driver.quit();
	  
}
}