package seleniumeasysite;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.sun.jna.platform.WindowUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

//import com.sun.jna.platform.FileUtils;

public class SmallConcepts {

	WebDriver driver;

	private int invalidLinksCount;

	@BeforeClass
	public void setUp() throws InterruptedException {
		
		/*System.setProperty("webdriver.chrome.driver",
				"C:\\Dinesh\\Java\\Softwares\\Selenium\\chromedriver.exe");		
		driver = new ChromeDriver();*/
		
		
		  System.setProperty("webdriver.gecko.driver",
		  "C:\\Dinesh\\Dinesh_dg185171\\Dinesh\\Java\\Softwares\\Selenium\\geckodriver.exe"
		  );
		
		//WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		/*System.setProperty("webdriver.ie.driver",
				"C:\\Dinesh\\Java\\Softwares\\Selenium\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();*/
				
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//WebElement element=driver.findElement(By.name("Mobdevices"));
		
		driver.manage().window().maximize();
		//driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.navigate().to("http://demo.automationtesting.in/Register.html");
		
		Thread.sleep(3000);
		
		
		/*
		 * boolean b=driver.findElement(By.
		 * xpath("//span[text()='( Username : Admin | Password : admin123 )']")).
		 * isDisplayed(); System.out.println("boolean value:"+b); boolean
		 * b2=driver.findElement(By.
		 * xpath("//span[text()='( Username : Admin | Password : admin123 )']ab")).
		 * isEnabled(); System.out.println("boolean b2 value:"+b2);
		 */
		
		
		/*
		 * By userid=By.id("txtUsername"); By password=By.id("txtPassword"); By
		 * login=By.id("btnLogin");
		 * 
		 * driver.findElement(userid).sendKeys("user01");
		 */
		//WebDriverWait wait=new WebDriverWait(driver,10);
		//wait.until(ExpectedConditions.visibilityOf(element))
		
		/*
		 * WebDriverManager.chromedriver().setup();
		 * WebDriverManager.firefoxdriver().setup();
		 * WebDriverManager.iedriver().setup();
		 * 
		 * driver.findElement(password).sendKeys("pass1234");
		 * driver.findElement(login).click();
		 */
		//DesiredCapabilities ds=new DesiredCapabilities().internetExplorer();
		
		//driver.close();
		
	}

	@Test
	public void validateInvalidLinks() throws InterruptedException {
		
		System.out.println("validateInvalidLinks method");
		
		By lang=By.xpath("//multi-select//div[@id='msdd']");
		
		WebElement lang_element=driver.findElement(lang);
		
		lang_element.click();
		
		WebElement sele_arabic=driver.findElement(By.xpath("//div[@id='msdd']//following::div//a[text()='Arabic']"));
		
		WebElement sele_bulg=driver.findElement(By.xpath("//div[@id='msdd']//following::div//a[text()='Bulgarian']"));
		
		sele_arabic.click();
		sele_bulg.click();
		
		driver.navigate().refresh();
		Thread.sleep(5000);
		
		
		/*
		 * Actions action=new Actions(driver);
		 * 
		 * action.moveToElement(lang_element).click().perform();;
		 * //div[@id='msdd']//following::div//a[text()='Arabic']
		 * 
		 * action.moveToElement(sele_arabic).click();
		 * action.moveToElement(sele_bulg).click();
		 */
		
		//driver.close();
		
		
		/*try {
			
			
			
			driver.get("https://www.google.co.in");
			
			invalidLinksCount = 0;
			List<WebElement> anchorTagList = driver.findElements(By.tagName("a"));
									
			System.out.println("Total number of links :" + anchorTagList.size());

			for (WebElement anchorTag : anchorTagList) {
				if (anchorTag != null) {
					String url = anchorTag.getAttribute("href");
					if (url != null && !url.contains("javascript")) {
						verifyURLStatus(url);
					} else {
						invalidLinksCount++;
					}
				}
			}
			System.out.println("Invalid Links Count :" + invalidLinksCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}*/
	}

	//@Test
	public void scrollToElement() throws InterruptedException
	{
		driver.get("http://www.seleniumeasy.com/selenium-webdriver-tutorials");
		
		Thread.sleep(3000);
		
		driver.manage().window().maximize();
		
		

		Thread.sleep(5000);
		
		WebElement element=driver.findElement(By.xpath("//li/a[text()='Synchronization in Selenium Webdriver ']"));
		//element.findElement(By.xpath("")).click();;
			
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		Thread.sleep(3000);
		
		js.executeScript("window.scrollTo("+element.getLocation().getX()+","+element.getLocation().getY()+")");
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		Thread.sleep(5000);
		
		System.out.println("Scrolling down the page");
		//js.executeScript("scrollTo(0, 250);");

		//js.executeScript("arguments[0].click();",element);
						
	}
		
//	@Test
	public void contextClick() throws InterruptedException
	{
		driver.get("http://www.seleniumeasy.com/selenium-webdriver-tutorials");
		
		Thread.sleep(3000);		
		driver.manage().window().maximize();		
		WebElement element=driver.findElement(By.xpath("//li/a[text()='Synchronization in Selenium Webdriver ']"));
		
		//select s=new select(element);
		
		
		//element.sendKeys("fjlska");
		
		Actions action=new Actions(driver);
		
		
		
		//action.contextClick(element).perform();		
	}

	//@Test
	public void mouseHoverJScript() throws InterruptedException
	{
		driver.get("http://www.seleniumeasy.com/selenium-webdriver-tutorials");
		
		Thread.sleep(3000);
			
		driver.manage().window().maximize();
		WebElement HoverElement=driver.findElement(By.xpath("//li/a[text()='Synchronization in Selenium Webdriver ']"));
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		
		//if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}
		
		((JavascriptExecutor) driver).executeScript(mouseOverScript,
				HoverElement);
	}
	
	//@Test
	public void takeScreenshot() throws IOException, InterruptedException	
	{
		driver.get("http://www.seleniumeasy.com/selenium-webdriver-tutorials");
		
		Thread.sleep(3000);		
		driver.manage().window().maximize();		
		
		TakesScreenshot ts=((TakesScreenshot)driver);
		File f=ts.getScreenshotAs(OutputType.FILE);		
		FileUtils.copyFile(f, new File("C:\\Users\\dg185171\\Desktop\\testScreenshot1.png"));		
	}
	
//	@Test
	public void usingRobot() throws AWTException
	{
		Robot r=new Robot();
		
	}
	
	//@Test
	public void verifyText_DisplayShortTime() throws Exception
	{
		By leaveMenu=By.id("menu_leave_viewLeaveModule");
		By applySubMenu=By.id("menu_leave_applyLeave");
		By leaveTypeCombobox=By.id("applyleave_txtLeaveType");
		By fromDate_Leave=By.id("applyleave_txtFromDate");
		By fromDate_Calender=By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[3]/a");
		By toDate_Leave=By.id("applyleave_txtToDate");
		By toDate_Calender=By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[5]/a");
		
		By commentText=By.id("applyleave_txtComment");
		By applyButton=By.id("applyBtn");
		
		By failedToSubmit_ErrorMessage=By.xpath("//div[contains(.,'Failed to Submit')]");
		
		
		Thread.sleep(5000);
		
		driver.findElement(leaveMenu).click();
		driver.findElement(applySubMenu).click();
		
		Select s=new Select(driver.findElement(leaveTypeCombobox));
		s.selectByValue("1");
				
		driver.findElement(fromDate_Leave).click();
		driver.findElement(fromDate_Calender).click();
		
		driver.findElement(toDate_Leave).click();
		driver.findElement(toDate_Calender).click();
		
		driver.findElement(applyButton).click();
		
		WebDriverWait wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(failedToSubmit_ErrorMessage));
		
		System.out.println("Error Message displayed: "+driver.findElement(failedToSubmit_ErrorMessage).isDisplayed());
		
	}
		
	@AfterClass
	public void tearDown() {
		
		
		driver.quit();
		
		/*
		 * By welcomeArun=By.id("welcome"); By
		 * logout=By.xpath("//div[@id=\"welcome-menu\"]//a[contains(text(),'Logout')]");
		 * 
		 * 
		 * driver.findElement(welcomeArun).click(); driver.findElement(logout).click();
		 */
		
		/*
		 * if (driver != null) driver.quit();
		 */
	}

	public void verifyURLStatus(String URL) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(request);
			// verifying response code and The HttpStatus should be 200 if not,
			// increment invalid link count
			//// We can also check for 404 status code like
			// response.getStatusLine().getStatusCode() == 404

			System.out.println("status Line : " + response.getStatusLine());
			if (response.getStatusLine().getStatusCode() != 200)
				invalidLinksCount++;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void readDynamicElements_ClickNthElement()
	{
		By MyLeave=By.xpath("//span[text()='My Leave']");
		By resultTable=By.xpath("//table[@id='resultTable']//tbody/tr[1]/td");
		
		
		
		System.out.println("x location of webelement :"+ driver.findElement(MyLeave).getLocation().x);
		System.out.println("getx location of webelement :"+ driver.findElement(MyLeave).getLocation().getX());
		System.out.println("gety location of webelement :"+ driver.findElement(MyLeave).getLocation().getY());
		
		driver.findElement(MyLeave).click();
		List<WebElement> element=driver.findElements(resultTable);
		
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element.get(1));
		
		element.forEach(x->System.out.println("\n "+x.getText()));
	
	
	}
	
	
}
