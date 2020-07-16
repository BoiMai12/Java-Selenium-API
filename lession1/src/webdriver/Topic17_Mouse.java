package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic17_Mouse {
	
	WebDriver driver;
	Actions action;
	WebElement element;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action = new Actions(driver);
	}

	
	public void TC_01_hoverMouse() {
		driver.get("http://www.myntra.com/");
		element = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"));
		action.moveToElement(element).perform();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
		
		
	}
	

	public void TC_02_clickAndHoldMultipleItem() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> listItems = driver.findElements(By.cssSelector("#selectable>li"));
		action.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();
		sleepInSecond(3);
		List<WebElement> selectedItems = driver.findElements(By.cssSelector(".ui-state-default.ui-selectee.ui-selected"));
		Assert.assertEquals(selectedItems.size(), 4);
		ArrayList<String> allSelectedItemsText = new ArrayList<String>();
		for (WebElement item: selectedItems) {
			allSelectedItemsText.add(item.getText());
		}
		System.out.println(allSelectedItemsText);
		Assert.assertTrue(allSelectedItemsText.contains("1"));
		Assert.assertTrue(allSelectedItemsText.contains("2"));
		Assert.assertTrue(allSelectedItemsText.contains("3"));
		Assert.assertTrue(allSelectedItemsText.contains("4"));
		
	}
	
	
	public void TC_03_clickAndHoldMultipleItemRandom() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> listItems = driver.findElements(By.cssSelector("#selectable>li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(listItems.get(0)).click(listItems.get(2)).click(listItems.get(5)).click(listItems.get(10)).perform();
		action.keyUp(Keys.CONTROL).perform();
		
		List<WebElement> selectedItems = driver.findElements(By.cssSelector(".ui-state-default.ui-selectee.ui-selected"));
		Assert.assertEquals(selectedItems.size(), 4);
		ArrayList<String> allSelectedItemsText = new ArrayList<String>();
		for (WebElement item: selectedItems) {
			allSelectedItemsText.add(item.getText());
		}
		System.out.println(allSelectedItemsText);
		Assert.assertTrue(allSelectedItemsText.contains("1"));
		Assert.assertTrue(allSelectedItemsText.contains("3"));
		Assert.assertTrue(allSelectedItemsText.contains("6"));
		Assert.assertTrue(allSelectedItemsText.contains("11"));
		
	}
	
	
	public void TC_04_doubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		element = driver.findElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(element).perform();
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		
	}
	
	
	public void TC_05_rightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		element = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(element).perform();
		sleepInSecond(3);
		element = driver.findElement(By.cssSelector(".context-menu-icon-quit"));
		action.moveToElement(element).perform();
		sleepInSecond(2);
		String quitAttribute = element.getAttribute("class");
		System.out.println(quitAttribute);
		Assert.assertTrue(quitAttribute.contains("context-menu-hover"));
		Assert.assertTrue(quitAttribute.contains("context-menu-visible"));
		element.click();
		
	}
	
	@Test
	public void TC_06_dragAndDrop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement dragElement = driver.findElement(By.id("draggable"));
		WebElement dropElement = driver.findElement(By.id("droptarget"));
		
		action.dragAndDrop(dragElement, dropElement).build().perform();
		sleepInSecond(3);
		Assert.assertEquals(dropElement.getText(), "You did great!");
		
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		WebElement ColumnElementA = driver.findElement(By.id("column-a"));
		WebElement ColumnElementB = driver.findElement(By.id("column-b"));
		
		action.dragAndDrop(ColumnElementA, ColumnElementB).build().perform();
		sleepInSecond(3);
		
	}
	
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	

}
