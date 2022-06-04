package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import com.pages.AccountsPage;

public class LoginPage {

	private WebDriver driver;
	
	//1. By Locators:
	private By txtemailId=By.id("txtUsername");
	private By txtpasswd=By.id("txtPassword");
	private By btnLogIn=By.id("btnLogin");
	private By lnkForgotPwdLnk=By.linkText("Forgot your password?");
	private By msgInvalidCredentials=By.xpath("//span[@id='spanMessage']");
	private By lnkStartCoding=By.linkText("Start Coding Now");
	private By lnkLogOut_Username=By.id("welcome");
	private By lnkLogOut=By.xpath("//div[@id='welcome-menu']//a[text()='LogoutLogout']");
	
	// 2. Constructor of the page class:
		public LoginPage(WebDriver driver) {
			this.driver = driver;
		}

		// 3. page actions: features(behavior) of the page the form of methods:

		public String getLoginPageTitle() {
			
			//driver.findElement(lnkStartCoding).click();			
			return driver.getTitle();
		}

		public boolean isForgotPwdLinkExist() {
			return driver.findElement(lnkForgotPwdLnk).isDisplayed();
		}

		public boolean msgInvalidCredentials() {
			return driver.findElement(msgInvalidCredentials).isDisplayed();
		}
		
		public void enterUserName(String username) {
			driver.findElement(txtemailId).sendKeys(username);
		}

		public void enterPassword(String pwd) {
			driver.findElement(txtpasswd).sendKeys(pwd);
		}

		public void clickOnLogin() {
			driver.findElement(btnLogIn).click();
		}

		public void logOutApplication() throws InterruptedException {
			Thread.sleep(3000);
			WebElement element=driver.findElement(lnkLogOut_Username);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor. executeScript("arguments[0]. setAttribute('style', 'border:2px solid red; background:yellow')", element);
			element.click();
			Thread.sleep(3000);
			driver.findElement(lnkLogOut).click();
		}
		
		
		  public DashboardPage doLogin(String un, String pwd) {
		  System.out.println("login with: " + un + " and " + pwd);
		  driver.findElement(txtemailId).sendKeys(un);
		  driver.findElement(txtpasswd).sendKeys(pwd);
		  driver.findElement(btnLogIn).click(); 
		  
		  return new DashboardPage(driver); }
		 

}
