package testngTutorials;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderExample {

	WebDriver driver;
	
	@Test(dataProvider="logincredentials")
	public void login(String username, String password) throws InterruptedException
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Dinesh\\Java\\HRM_Automation\\HRM_Automation\\JarFiles\\BrowserServers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.testingmasters.com/hrm/symfony/web/index.php/auth/login");
		
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();	
		/*Thread.sleep(3000);
		String DashboardText=driver.findElement(By.xpath("//div[@class='head']/h1[text()='Dashboard']")).getText();
		Thread.sleep(3000);
		Assert.assertEquals(DashboardText, "Dashboard");*/
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='welcome']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='welcome-menu']/ul/li[3]/a[text()='Logout']")).click();
		driver.quit();
	}
	
	@DataProvider(name="logincredentials")
	public Object[][] passData()
	{
		Object[][] obj={ {"user01","pass1234"}};
			
		return obj;
	}
	
}
