package com.apsrtc.demo;

import java.util.concurrent.TimeUnit;
import java.io.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.TakesScreenshot;;

public class Base {
	
	public WebDriver driver=null;
	public WebDriver getDriver()
	{
		//C:\Dinesh\Java\Selenium_Jars
		//System.setProperty("webdriver.ie.driver", "C:\\Dinesh\\Java\\Selenium_Jars\\IEDriverServer.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Dinesh\\Java\\Softwares\\Selenium\\geckodriver.exe");
		//System.setProperty("webdriver.firefox.marionette", "C:\\Dinesh\\Java\\Softwares\\Selenium\\geckodriver.exe");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Dinesh\\Java\\Softwares\\Selenium\\chromedriver.exe");
		
	    //driver=new FirefoxDriver();
		//driver=new InternetExplorerDriver();
		driver=new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        
		return driver;
	}

}
