package com.apsrtc.demo.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.apsrtc.demo.*;

public class LoginPage_ {
	
	public WebDriver driver;
	
	public LoginPage_(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By username=By.id("userName");
	private By password=By.id("password");
	private By loginButton=By.id("submitBtn");
			
	public WebElement getUsername()
	{
		return driver.findElement(username);		
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);		
	}
	
	public WebElement getLoginButton()
	{
		return driver.findElement(loginButton);		
	}
}
