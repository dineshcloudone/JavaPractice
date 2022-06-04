package testngTutorials;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ParametersPractice {
	
	@Test
	@Parameters("param")
	public void param1(String param)
	{
		System.out.println("Parameter Value : "+param);
	}
}

		
		
		
		