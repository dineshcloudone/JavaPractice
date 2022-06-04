package testngTutorials;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
 * 1.Groups
 * 2.Groups of Groups
 * 3.Exclusion Groups
 * 4.Partial Groups 
 */


public class GroupsPractice {
	
	WebDriver driver;
		
	@BeforeClass(groups= {"functest","checkintest"})
	public void LaunchApplication()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Dinesh\\Java\\HRM_Automation\\HRM_Automation\\JarFiles\\BrowserServers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.testingmasters.com/hrm/symfony/web/index.php/auth/login");
	}

	@Test(groups= {"functest","checkin test"},priority=1,description="This test case will verify login functionlaity")
	public void loginApplication()
	{
		driver.findElement(By.id("txtUsername")).sendKeys("user01");
		driver.findElement(By.id("txtPassword")).sendKeys("pass1234");
		driver.findElement(By.id("btnLogin")).click();
		String DashboardText=driver.findElement(By.xpath("//div[@class='head']/h1[text()='Dashboard']")).getText();
		Assert.assertEquals(DashboardText, "Dashboard");
		System.out.println("Assertion Passed");
	}
	
	@Test(groups= {"functest"},priority=2,description="This test case will select item")
	public void selectItems()
	{
		System.out.println("Item Selected");
	}
	
	@Test(groups= {"checkin test"},priority=3,description="This test case will checkout")
	public void checkOut()
	{
		System.out.println("Checkout Completed");
	}
	
	
	@AfterClass(groups= {"functest","checkin test"})
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

		
		
		
		