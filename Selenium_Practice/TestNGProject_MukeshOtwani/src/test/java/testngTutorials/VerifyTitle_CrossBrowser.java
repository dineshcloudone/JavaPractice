package testngTutorials;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

public class VerifyTitle_CrossBrowser {
	
	WebDriver driver;
	
	@Test
	@Parameters("browser")
	public void verifyTitle(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Dinesh\\Java\\HRM_Automation\\HRM_Automation\\JarFiles\\BrowserServers\\chromedriver.exe");
			driver=new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Dinesh\\Java\\HRM_Automation\\HRM_Automation\\JarFiles\\BrowserServers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Dinesh\\Java\\HRM_Automation\\HRM_Automation\\JarFiles\\BrowserServers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.testingmasters.com/hrm/symfony/web/index.php/auth/login");
		
		System.out.println(driver.getTitle());		
		
		driver.quit();
	}

}
