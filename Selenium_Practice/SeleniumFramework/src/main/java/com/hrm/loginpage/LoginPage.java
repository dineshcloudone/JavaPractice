package com.hrm.loginpage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import test.common.ManageBrowser;
import test.common.WebActions;

public class LoginPage extends WebActions{
	
	ManageBrowser browser;
	
	//WebActions performAction=PageFactory.initElements(browser.getCurrentWebDriver(),WebActions.class);
	//WebActions performAction;
	
	private String URL="http://www.testingmasters.com/hrm/symfony/web/index.php/auth/login";
	By txtUsername=By.id("txtUsername");
	By txtPassword=By.id("txtPassword");
	By btnLogin=By.id("btnLogin");
	By spnInvalidCredentials=By.id("spanMessage");
	
	private void enterUsername(String strUsername) {
		clearEnter(txtUsername, strUsername, "Username Text Field");
	}
	
	private void enterPassword(String strPassword) {
		clearEnter(txtUsername, strPassword, "Password Text Field");
	}
	
	private void clickLoginButton() {
		click(btnLogin, "Login Button");
	}
	
	private void verifyInvalidCredMessage(String strMessage) {
		verifyMessage(strMessage);
	}	
		
	public void verifyLoginWithInvalidCredentials(String spnMessage) {
		try {			
		this.enterUsername("user01");
		this.enterPassword("pass1234");
		this.clickLoginButton();
		this.verifyInvalidCredMessage("Invalid credentials");
		}
		catch(Exception e) {
			System.out.println("Exception occured while doing verifyLoginWithInvalidCredentials");
		}
	}
	
}
