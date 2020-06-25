package webdriver;

import java.sql.Driver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class dropDownList {

	WebDriver driver;
	Select select;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	
	
@BeforeClass
public void beforeClass() {
	driver = new FirefoxDriver();
//	System.setProperty("webdriver.chrome.driver", "D:\\BoiMai\\Class16\\01-Software\\chromedriver.exe");
//	driver = new ChromeDriver();
	expliciWait = new WebDriverWait(driver, 30);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	jsExecutor = (JavascriptExecutor) driver;
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
//check github conflict

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


public void TC_04_jQuery() {
	driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	
	selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
	Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text'] and text()=5")).isDisplayed());
		
}


public void TC_05_Angular() {
	driver.get("https://bit.ly/2UV2vYi");
	
	selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-search-icon')]", ".//ul[@id='games_options']/li", "Football");
	Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Football");
	
	selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-search-icon')]", ".//ul[@id='games_options']/li", "Golf");
	Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Golf");
}


public void TC_07_VueJS(){
	
	driver.get("https://mikerodham.github.io/vue-dropdowns/");
	selectItemInDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a","Second Option");
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	
	
}
//TC_07_VueJS

public void TC_06_ReactJS() {
	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
	
	selectItemInDropdown("//div[@role='listbox']", "//div[contains(@class, 'item')]","Christian" );
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='text']")).isDisplayed());
	
}

@Test
public void TC_08_editDropdownJQ(){
	
	driver.get("http://indrimuska.github.io/jquery-editable-select/");


	sendKeyToEditDropdown("//div[@id='default-place']/input", "Audi");
	Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Audi");
	
	sendKeyToEditDropdown("///div[@id='default-place']/input", "BMW");
	Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "BMW");
	
}


@AfterClass
public void closeBrowser() {
	driver.quit();
	
}

public void selectItemInDropdown(String parentLocator, String itemLocators, String expectedItem) {
	//find element to click
	expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
	driver.findElement(By.xpath(parentLocator)).click();
	sleepInSecond(1);
	//get all item in the list
	expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocators)));
	List <WebElement> allItems = driver.findElements(By.xpath(itemLocators));
	System.out.println("the number of the array is " + allItems.size());
	for (WebElement item : allItems) {
		String actualItem = item.getText();
//		System.out.println(actualItem);
		if (actualItem.equals(expectedItem)) {
			//Berfore select item, user scroll
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
			sleepInSecond(2);
			expliciWait.until(ExpectedConditions.elementToBeClickable(item));
			item.click();
			sleepInSecond(2);
			break;
		}
	}
	
}

public String getHiddenText(String cssLocator) {
	return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
}

public void sendKeyToEditDropdown(String locator, String value) {
	driver.findElement(By.xpath(locator)).clear();
	driver.findElement(By.xpath(locator)).sendKeys(value);
	sleepInSecond(1);
	driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
	sleepInSecond(1);
	
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
