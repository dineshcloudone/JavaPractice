package testngTutorials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


// Listeners can be implemented at two levels one is class leve and other one is Suite level

@Listeners(testngTutorials.ListenersClass.class)
public class UsingListener {
	
	WebDriver driver;
	
	@Test	
	public void GoogleTitleVerify()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Dinesh\\Java\\HRM_Automation\\HRM_Automation\\JarFiles\\BrowserServers\\geckodriver.exe");
		driver=new FirefoxDriver();
		
		driver.get("https://www.google.com");
		System.out.println("Title : "+driver.getTitle());
		driver.quit();
	}
	
	@Test
	public void checkTitle()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Dinesh\\Java\\HRM_Automation\\HRM_Automation\\JarFiles\\BrowserServers\\geckodriver.exe");
		driver=new FirefoxDriver();
		
		driver.get("https://www.google.com");
		System.out.println("Title : "+driver.getTitle());
		Assert.assertEquals("a", driver.getTitle());
		
		driver.quit();
	}
	
}
