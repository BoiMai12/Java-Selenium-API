package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic18_Popup_Iframe_Windown {
	WebDriver driver;
	boolean status;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", "D:\\BoiMai\\Class16\\01-Software\\chromedriver.exe");
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver, 30);
		jsExecutor =(JavascriptExecutor) driver;
		
		
	}

	
	public void TC_01_fixedPopup1() {
		driver.get("https://www.zingpoll.com/");
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.id("Loginform")));
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(3);
		
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Display popup is " + status);
		Assert.assertTrue(status);

		sleepInSecond(3);
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		sleepInSecond(2);
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Display popup is " + status);
		Assert.assertFalse(status);
		
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(2);
		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");
		driver.findElement(By.id("button-login")).click();
		sleepInSecond(3);
		driver.navigate().refresh();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='username' and contains(text(),'Automation Testing')]")).isDisplayed());
	}

	
	public void TC_02_fixedPopup2() {
		
			driver.get("https://bni.vn/");
			sleepInSecond(3);
			status = driver.findElement(By.id("sgpb-popup-dialog-main-div")).isDisplayed();
			System.out.println("Display popup is " + status);
			Assert.assertTrue(status);
			
			driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
			sleepInSecond(3);
			status = driver.findElement(By.id("sgpb-popup-dialog-main-div")).isDisplayed();
			System.out.println("Display popup is " + status);
			Assert.assertFalse(status);
			
	}


	public void TC_03_randomPopupDisplayed() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(15);
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			System.out.println("Go to close popup");
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='close-mailch']")).isDisplayed());
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(5);
		}
		
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("selenium");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#search-2 .glass")).click();
		List<WebElement> allArticleTitle = driver.findElements(By.xpath("//h3[@class='post-title']"));
		System.out.println("size: " + allArticleTitle.size());
		for (WebElement articleTitle : allArticleTitle) {
			String titleText = articleTitle.getText().trim();
			Assert.assertTrue(titleText.contains("Selenium"));
		}
	}
	
	public void TC_04_randomPopup_Undisplayed() {
		driver.get("https://blog.testproject.io/");
		
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			System.out.println("Go to close popup");
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='close-mailch']")).isDisplayed());
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(5);
		}
		
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("selenium");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#search-2 .glass")).click();
		List<WebElement> allArticleTitle = driver.findElements(By.xpath("//h3[@class='post-title']"));
		System.out.println("size: " + allArticleTitle.size());
		for (WebElement articleTitle : allArticleTitle) {
			String titleText = articleTitle.getText().trim();
			Assert.assertTrue(titleText.contains("Selenium"));
		}
	}

	
	public void TC_05_frameWork() {
		driver.get("https://kyna.vn/");
		sleepInSecond(5);
		if(driver.findElement(By.cssSelector(".fancybox-image")).isDisplayed())
		{
			System.out.println("Go to close image popup");
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']//iframe")).isDisplayed());
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
		
		String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertTrue(facebookLike.contains("169K"));
		//Switch
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".chat-area")).isDisplayed());
		driver.findElement(By.cssSelector(".textarea")).sendKeys("Basic Java");
		driver.findElement(By.cssSelector(".textarea")).sendKeys(Keys.ENTER);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector(".infomation-section")).isDisplayed());
		//Swich
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Java");
		driver.findElement(By.cssSelector(".search-button")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "'Java'");
		
	}
	

	public void TC_06_windown() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();
		clickToElementByJS("//a[text()='GOOGLE']");
//		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");
		sleepInSecond(3);
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(3);
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		closeAllWindowsWithoutParent(parentID);
		sleepInSecond(5);
		System.out.println(driver.getCurrentUrl());
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
	}
	

	public void TC_07_Windows2() {
		driver.get("https://kyna.vn/");
		sleepInSecond(5);
		if(driver.findElement(By.cssSelector(".fancybox-image")).isDisplayed())
		{
			System.out.println("Go to close image popup");
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		String parentId = driver.getWindowHandle();
		String parentTitle = driver.getTitle();
		clickToElementByJS("//img[@alt='facebook']");
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		sleepInSecond(2);
		switchToWindowByTitle(parentTitle);
		clickToElementByJS("//img[@alt='android-app-icon']");
		switchToWindowByTitle("Kyna 2 - Apps on Google Play");
		Assert.assertEquals(driver.getTitle(), "Kyna 2 - Apps on Google Play");
		sleepInSecond(2);
		closeAllWindowsWithoutParent(parentId);
		Assert.assertEquals(driver.getTitle(), parentTitle);
		
	}
	
	@Test
	public void TC_08_Windows3() {
		driver.get("http://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		String parentId = driver.getWindowHandle();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		clickToElementByJS("//span[text()='Compare']");
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeAllWindowsWithoutParent(parentId);
		clickToElementByJS("//a[text()='Clear All']");
		sleepInSecond(3);
		driver.switchTo().alert().accept();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	}
	public void switchToWindowByTitle(String title) {
		Set <String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(title)) {
				break;
			}
		}
		
	}
	
	public void switchToWindowID(String parentID) {
		Set <String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				break;
			}
		}
		
	}
	
	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set <String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
			driver.switchTo().window(parentID);
			if(driver.getWindowHandles().size()==1)
				return true;
			else
				return false;
			
		}
		
	
	
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
