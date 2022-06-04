package testngTutorials;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class FirstTestNGScript2 {
	
	WebDriver driver;
		
	@BeforeClass
	public void LaunchApplication()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Dinesh\\Dinesh_dg185171\\Dinesh\\Java\\Selenium_Browser_Drivers\\geckodriver.exe");
		//driver=new ChromeDriver();
		
		//WebDriverManager.firefoxdriver().setup();
		
		
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.google.com");
		
		//driver.get("http://www.testingmasters.com/hrm/symfony/web/index.php/auth/login");
		//driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-automate-radio-button-in.html");
		
	}

	/*@DataProvider
	public HashMap<String,String> createData()
	{
		HashMap<String,String> hm=new HashMap<String,String>();
		
		hm.put("Username", "user01");
		hm.put("Password", "pass1234");
		
		return hm;
	}*/
	
	@Test(priority=1,description="This test case will verify login functionlaity")
	//@DataProvider(name="createData")
	public void loginApplication() throws IOException
	{
		
		By b1=By.linkText("");
		Actions action=new Actions(driver);	
		WebElement search_txtbox=driver.findElement(By.xpath("//*[@class='gLFyf gsfi']"));
		search_txtbox.sendKeys("selenium website");
		
		
		
		WebElement Search_button=driver.findElement(By.xpath("//*[@class='gNO89b']"));
		
		
		action.moveToElement(search_txtbox).moveToElement(search_txtbox).build().perform();
		
		//WebElement element=driver.findElement(By.xpath("//*[@class='gLFyf gsfi']"));
		  
		//Select s=new Select(element);
		  
		//Runtime.getRuntime().exec("");
		 
		driver.manage().window().maximize();
		
			
		action.sendKeys(Keys.ESCAPE); //11 methods can be used, https://www.seleniumeasy.com/selenium-tutorials/handling-keyboard-events-and-mouse-hover-events-using-webdriver
				
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'",Search_button);
		  
		//action.moveToElement(Search_button).build().perform();
		  
		 
				
		
		driver.findElement(By.xpath("//*[@class='gNO89b']")).click();	
		
		System.out.println("Text identified" +driver.findElement(By.xpath("//h3[text()='Selenium']")).getText());
		
		
		
		action.moveToElement(driver.findElement(By.xpath("//h3[text()='Selenium']")));
		
		//Alert alert=driver.switchTo().alert();
		
		/*
		 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) //Wait for the
		 * condition .withTimeout(30, TimeUnit.SECONDS) // which to check for the
		 * condition with interval of 5 seconds. .pollingEvery(5, TimeUnit.SECONDS)
		 * //Which will ignore the NoSuchElementException
		 * .ignoring(NoSuchElementException.class);
		 */
		
		
		//WebDriverWait wait123 = new WebDriverWait(driver, 5);
		//wait.until(ExpectedConditions.presenceOfElementLocated(b1));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)); 
		
		//wait.until(ExpectedConditions.elementToBeClickable(locator));		
		
		//wait..until(ExpectedConditions.visibilityOf(element));		
		//wait..until(ExpectedConditions.visibilityOfAllElements(linkElements));		
		
		//wait.until(ExpectedConditions.invisibilityOfElementWithText(by));		
		//wait.until(ExpectedConditions.invisibilityOfElementWithText(by, strText));
		
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));
		
		
		
		
		/*
		 * FirefoxProfile profile = new FirefoxProfile();
		 * profile.setPreference("browser.startup.homepage", "http://www.google.com");
		 * driver = new FirefoxDriver(profile);
		 */
		
		/*
		 * driver.findElement(By.id("txtPassword")).sendKeys("pass1234");
		 * driver.findElement(By.id("btnLogin")).click(); String
		 * DashboardText=driver.findElement(By.xpath(
		 * "//div[@class='head']/h1[text()='Dashboard']")).getText();
		 */
		//Assert.assertEquals(DashboardText, "Dashboard");
		
		
	}
	
	@Test(priority=2,description="This test case will select item")
	public void selectItems()
	{
		System.out.println("Item Selected");
	}
	
	@Test(priority=3,description="This test case will checkout")
	public void checkOut()
	{
		System.out.println("Checkout Completed");
	}
	
	
	
	@AfterClass
	public void closeApplication() {
		//driver.findElement(By.id("welcome")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.findElement(By.xpath("//*[@id='welcome-menu']/ul/li[3]/a[text()='Logout']")).click();

		if (driver != null)
			driver.quit();
	}
	 
}

		
		
		
		