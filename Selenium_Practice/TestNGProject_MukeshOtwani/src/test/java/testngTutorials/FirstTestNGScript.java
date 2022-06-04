package testngTutorials;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class FirstTestNGScript {
	
	WebDriver driver;
		
	@BeforeClass
	public void LaunchApplication()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Dinesh\\Dinesh_dg185171\\Dinesh\\Java\\HRM_Automation\\HRM_Automation\\JarFiles\\BrowserServers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.get("http://www.testingmasters.com/hrm/symfony/web/index.php/auth/login");
		driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-automate-radio-button-in.html");
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
	public void loginApplication()
	{
		driver.findElement(By.id("txtUsername")).sendKeys("user01");
		driver.findElement(By.id("txtPassword")).sendKeys("pass1234");
		driver.findElement(By.id("btnLogin")).click();		
		String DashboardText=driver.findElement(By.xpath("//div[@class='head']/h1[text()='Dashboard']")).getText();
		Assert.assertEquals(DashboardText, "Dashboard");
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
	public void closeApplication()
	{
		driver.findElement(By.id("welcome")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id='welcome-menu']/ul/li[3]/a[text()='Logout']")).click();
		
		if(driver!=null)
		driver.quit();
	}
	
}

		
		
		
		