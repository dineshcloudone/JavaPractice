package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//import com.pages.AccountsPage;

public class LoginPage {

	private WebDriver driver;
	
	//1. By Locators:
	private By txtemailId=By.id("sso_username");
	private By txtpasswd=By.id("ssopassword");
	private By btnSignIn=By.id("signin_button");
	private By lnkForgotPwdLnk=By.linkText(" Need help?");
	private By lnkStartCoding=By.linkText("Start Coding Now");
	
	// 2. Constructor of the page class:
		public LoginPage(WebDriver driver) {
			this.driver = driver;
		}

		// 3. page actions: features(behavior) of the page the form of methods:

		public String getLoginPageTitle() {
			
			driver.findElement(lnkStartCoding).click();
			
			return driver.getTitle();
		}

		public boolean isForgotPwdLinkExist() {
			return driver.findElement(lnkForgotPwdLnk).isDisplayed();
		}

		public void enterUserName(String username) {
			driver.findElement(txtemailId).sendKeys(username);
		}

		public void enterPassword(String pwd) {
			driver.findElement(txtpasswd).sendKeys(pwd);
		}

		public void clickOnLogin() {
			driver.findElement(btnSignIn).click();
		}

		
		  public AccountsPage doLogin(String un, String pwd) {
		  System.out.println("login with: " + un + " and " + pwd);
		  driver.findElement(txtemailId).sendKeys(un);
		  driver.findElement(txtpasswd).sendKeys(pwd);
		  driver.findElement(btnSignIn).click(); 
		  
		  return new AccountsPage(driver); }
		 

}
