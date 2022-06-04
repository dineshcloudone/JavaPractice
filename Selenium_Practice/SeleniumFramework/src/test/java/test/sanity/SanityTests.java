package test.sanity;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.support.PageFactory;


import test.common.WebActions;
import com.hrm.loginpage.*;
//import com.tanmaysarkar.demo.business.Sample_01;

import java.util.*;
import com.hrm.loginpage.*;

public class SanityTests {
	
	//ManageBrowser browser=new ManageBrowser();
	
	//LoginPage lg=new LoginPage();
	//LoginPage lg2=PageFactory.initElements(browser.getCurrentWebDriver(),LoginPage.class);
	
	WebActions wa=new WebActions();
	
	HashMap<String,String> hm1=new HashMap<String,String>();
	
	@BeforeSuite
	public void OpenBrowser() {
		
			
	}
		
	@Test
	public void VerifyLogin_InvalidCredentials_TC1() {
				
		
	}

	@AfterSuite
	public void CloseBrowser() {
		
	}

}
