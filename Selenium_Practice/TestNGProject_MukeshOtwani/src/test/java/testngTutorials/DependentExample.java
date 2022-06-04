package testngTutorials;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DependentExample {
	
	@BeforeClass
	public void startBrowser()
	{
		//driver =new ChromeDriver();
		System.out.println("Browser Started");		
	}
	
	@Test
	public void startApp()
	{
		 System.out.println("start App");
		 Assert.assertEquals("a","b");
	}
	
	@Test(dependsOnMethods="startApp")
	public void loginApp()
	{
		System.out.println("login App");
	}
	
	@Test(dependsOnMethods= {"loginApp","startApp"},alwaysRun=true)
	public void logoutApp()
	{
		System.out.println("logout App");
	}
	
	@AfterClass
	public void closeApp()
	{
		//driver.quit();
		System.out.println("Browser closed");
	}
}
