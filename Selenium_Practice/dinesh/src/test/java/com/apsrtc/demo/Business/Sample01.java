
package com.apsrtc.demo.Business;

import org.openqa.selenium.*;
import com.apsrtc.demo.Login.*;


public class Sample01 {
	
	private WebDriver driver=null;
	
	LoginPage_ lp;
	
	public Sample01(WebDriver driver)
	{
		this.driver=driver;
		lp=new LoginPage_(driver);
	}
	
	public void APSRTC_FailLogin(String username,String Password)
	{
		lp.getUsername().sendKeys(username);
		
		lp.getPassword().sendKeys(Password);
		lp.getLoginButton().click();
		
	}
	

}
