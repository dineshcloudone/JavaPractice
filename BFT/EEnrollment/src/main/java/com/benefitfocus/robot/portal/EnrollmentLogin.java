package com.benefitfocus.robot.portal;

import junit.framework.Assert;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class EnrollmentLogin {

	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ReadJsonTestData readJson;	
	@Autowired
	protected Screenshot scr;

	// Selenium object locators
	By eeUsername = By.name("j_username");
	By eePassword = By.name("j_password");
	By titleText = By.tagName("Title");
	By resetSession = By.linkText("Reset Session");
	By login = By.id("log-in-command");
	By logoutVista = By.linkText("Logout");
	By logoutMember = By.linkText("Logout");
	
	By logoutMemberConfirmation = By.linkText("Log Out");	
	By hrAdminAccount = By.xpath("//a[contains(@href, '#')]");
	By logoutHRadmin = By.linkText("Log out");
	By closeHRRole = By.linkText("Close HR role");
	By popup = By.xpath("//button[contains(text(),'Not right now')]");
	By loginPageError = By.className("error");
	By searchCriteriainHRRole = By.id("searchCriteria");
	By logoutNewMember = By.xpath("//a[@class='btn btn-link dropdown-toggle']/span");
	By logoutLink	= By.xpath("//a[contains(text(),'Log Out')]");
	By skipShopping = By.xpath("//span[contains(text(),'Skip')]");
	By returnHome = By.id("returnHome");
	By closeButton = By.xpath("//button[@class='close']");
	By newPassword = By.id("newPassword");
	By confirmCarrierPassword = By.id("confirmPassword");
	By save = By.linkText("Save");
	By newConfirmPassword = By.id("newConfirmPassword");
	By savePassword = By.xpath("//a[@class='buttonInnerLink']//strong");

	// Set user name in textbox
	private void setUserName(String strUserName) {
		performAction.enter(eeUsername, strUserName, "username textbox");		
	}

	// Set password in password textbox
	private void setPassword(String strPassword) {
		performAction.enter(eePassword, strPassword, "Password textbox");
	}

	// Click on login button
	private void clickResetSession() {
		performAction.click(resetSession, "reset session link");
	}

	// Click on login button
	private void clickLogin() {
		performAction.click(login, "login button");
	}

	// Click on logout vista button
	private void clickLogoutVista() {
		performAction.jsclick(logoutVista, "logout link");
	}

	// Click on logout Member button
	private void clickLogoutMember() {
		performAction.click(logoutMember, "logout member link");
	}

	// Click on logout HRAdmin
	private void clickLogoutHRAdmin() throws InterruptedException {
		Thread.sleep(5000);		
		performAction.click(hrAdminAccount, "HR admin account");

		performAction.click(logoutHRadmin, "logout hr admin");
	}
	private void verfiyMessage(String strMessage) throws InterruptedException {
		// Browser.utils.sleep(1000);
					strMessage = utils.getValue(strMessage);
					boolean res = browser.getCurrentWebDriver().getPageSource()
							.toLowerCase().contains(strMessage.trim().toLowerCase());
					if (!res) {
						 logger.info(" Verifying that Message " +
						strMessage + " is not present ");
						
						scr.capturePageScreenshot();
					} else {
						scr.capturePageScreenshot();
						throw new RuntimeException("Message " + strMessage + " found");				
						
					}
	}

	private void save() {
		performAction.click(save, "Save Button");
	}

	private void savePassword() {
		performAction.click(savePassword, "Save Password Button");
	}

	// Carrier Representative Password Update
	private void updateCarrierPassword(String strNewPassword, String strConfirmPassword) throws Exception {
		performAction.clearEnter(newPassword, strNewPassword, "New Password");
		performAction.clearEnter(confirmCarrierPassword, strConfirmPassword, "Confirm Password");
		//save button
		this.save();
		//page verification
		performAction.verifyMessage("Your information has been saved.");
	}

	// Agent Password Update
	private void updateAgentPassword(String strNewPassword, String strConfirmPassword) throws Exception {
		performAction.clearEnter(newPassword, strNewPassword, "New Password");
		performAction.clearEnter(newConfirmPassword, strConfirmPassword, "Confirm Password");
		//save button
		this.savePassword();
		//page verification
		performAction.verifyMessage("Your password has been changed.");
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 * 
	 * Description : Keyword or method used to login to Enrollment application with valid login credentials
	 * 
	 * PreCondition : Vista Home page
	 * 
	 * PostCondition : Respective menu item page
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | username | password | 
	 * | xyz | abc | 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "username", "password" })
	public void loginToEnrollmentWithValidCredentials(String username,
			String password) {
		try {
			String loginStatus = "successful";
			username = utils.getValue(username);
			// Fill user name
			this.setUserName(username);
			// Fill password
			this.setPassword(password);
			// Click Login button
			this.clickLogin();

			boolean pageerrormessage = performAction.isElementPresent(By.xpath("//div[@class='error']"));


			if (pageerrormessage){

				loginStatus = "notsuccessful";
			}
			browser.hMap.put("errorMsg", loginStatus);


			//			Assert.assertFalse("Unable to login with given credentials. ",
			//					performAction.isElementPresent(loginPageError));
			// return flag;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(
					"Unable to login with given credentials. \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 * 
	 * Description : Keyword or method used to login to Enrollment application with valid vista login credentials
	 * 
	 * PreCondition : Test data is configured 'eEnrollmentCommon' testdata file.  
	 * 
	 * PostCondition : Logs in as the vista admin
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | jsonTagName | 
	 * | validvistacredentials | 
	 * </pre>	
	 **/
	@RobotKeyword
	@ArgumentNames({ "jsonTagName"})
	public void loginToEnrollmentAsVistaAdmin(String jsonTagName) {
		try {
			JSONObject credentials = ReadJsonTestData.getTestData(jsonTagName);
			System.out.println("credentials = " + credentials.toJSONString());
			// Fill user name
			this.setUserName(credentials.get("username").toString());
			// Fill password
			this.setPassword(credentials.get("password").toString());
			// Click Login button
			this.clickLogin();

			Assert.assertTrue("Vista admin login failed. ",
					performAction.isElementPresent(resetSession));
		} catch (Exception e) {
			// logger.error("Application Logging Error \n");
			scr.capturePageScreenshot();
			throw new CustomException("Unable to login as vista admin. \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 * 
	 * Description : Keyword or method used to login to Enrollment application with valid vista login credentials
	 * 
	 * PreCondition : Test data is configured 'eEnrollmentCommon' testdata file.  
	 * 
	 * PostCondition : Logs in as the vista admin
	 *  
	 * <b>Parameters & Example </b>
	 * NA - No arguments to the keyword as the username and password picked from the json test data file
	 * Key from JsonFile - 'validvistacredentials'
	 * </pre> 
	 **/
	@RobotKeyword
	private void loginToEnrollmentAsVistaAdmin() {
		try {
			JSONObject credentials = ReadJsonTestData
			.getTestData("validvistacredentials");
			System.out.println("credentials = " + credentials.toJSONString());
			// Fill user name
			this.setUserName(credentials.get("username").toString());
			// Fill password
			this.setPassword(credentials.get("password").toString());
			// Click Login button
			this.clickLogin();

			Assert.assertTrue("Vista admin login failed. ",
					performAction.isElementPresent(resetSession));
		} catch (Exception e) {
			// logger.error("Application Logging Error \n");
			throw new CustomException("Unable to login as vista admin. \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword or method 'loginToEnrollmentAsHRAdmin' used to login to Enrollment application with valid HR admin login credentials
	 * 
	 * PreCondition : Test data is configured 'eEnrollmentCommon' testdata file and eEnrollment login page
	 * 
	 * PostCondition : Logs in as the HR admin admin
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | jsonTagName | 
	 * | validhrcredentials | 
	 * </pre>	 
	 **/	
	@RobotKeyword
	@ArgumentNames({ "jsonTagName" })
	public void loginToEnrollmentAsHRAdmin(String jsonTagName) {
		try {
			JSONObject credentials = ReadJsonTestData.getTestData(jsonTagName);
			// Fill user name
			this.setUserName(credentials.get("username").toString());
			// Fill password
			this.setPassword(credentials.get("password").toString());
			// Click Login button
			this.clickLogin();

			Thread.sleep(1000);
			//boolean flag=browser.getCurrentWebDriver().findElement(eeUsername).isDisplayed();

			//performAction.isDisplayed(searchCriteriainHRRole, "SearchCriteriainHRRole");
			if (browser.getCurrentWebDriver().getPageSource().trim().contains("Username")) {
				System.out.println("HR Admin login Failed");

			} else {
				System.out.println("HR Admin login Passed");
			}
			//Assert.assertTrue("HR Admin login failed. ",
			//performAction.isElementPresent(searchCriteriainHRRole));
			/*
			 * if (performAction.isElementPresent(popup)) {
			 * webdriver.findElement(popup).click(); }
			 */
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Unable to login as HR admin. \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 * 
	 * Description : Keyword or method used to login to Enrollment application with valid HR admin login credentials
	 * 
	 * PreCondition : Test data is configured 'eEnrollmentCommon' testdata file and enrollment login page
	 * 
	 * PostCondition : Logs in as the hradmin admin
	 *  
	 * <b>Parameters & Example </b>
	 * NA - No arguments to the keyword as the username and password picked from the json test data file
	 * Key from JsonFile - 'validhrcredentials'
	 * </pre> 
	 **/
	@RobotKeyword
	private void loginToEnrollmentAsHRAdmin() {
		try {

			JSONObject credentials = ReadJsonTestData
			.getTestData("validhrcredentials");
			// Fill user name
			this.setUserName(credentials.get("username").toString());
			// Fill password
			this.setPassword(credentials.get("password").toString());
			// Click Login button
			this.clickLogin();

			Thread.sleep(1000);
			/*
			 * Assert.assertTrue("HR Admin login failed. ",
			 * performAction.isElementPresent(searchCriteriainHRRole)); if
			 * (webdriver.findElement(popup).isDisplayed()) {
			 * webdriver.findElement(popup).click(); }
			 */
		} catch (Exception e) {
			throw new CustomException("Unable to login as HR admin. \n"
					+ e.getMessage());
		}
	}

	/**
	 * Keyword or method 'getToVistaHomePage' used navigate to vista home page
	 */
	@RobotKeyword
	private void getToVistaHomePage() {
		try {
			// Reset the current session
			this.clickResetSession();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CustomException(e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Logout of eEnrollment application as vista administrator
	 * 
	 * PreCondition : Logged in eErnollment application as vista admin
	 * 
	 * PostCondition : Logout
	 *  
	 * <b>Parameters & Example </b> NA 
	 * </pre> 
	 **/
	@RobotKeyword
	public void logoutVistaAdmin() {
		try {
			// Fill user name
			this.clickLogoutVista();
		} catch (Exception e) {
			throw new CustomException("Unable to logout as admin. \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Logout of eEnrollment application as HR administrator
	 * 
	 * PreCondition : Logged in eErnollment application as HR admin
	 * 
	 * PostCondition : Logout
	 *  
	 * <b>Parameters & Example </b> NA 
	 * </pre> 
	 **/
	@RobotKeyword
	public void logoutHRAdmin() {
		try {
			this.clickLogoutHRAdmin();
		} catch (Exception e) {
			throw new CustomException("Unable to logout as HR admin. \n"
					+ e.getMessage());
		}

	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Close HR administrator window 
	 * 
	 * PreCondition : When Group is opened from vista login using 'Open Group in HR Role'
	 * 
	 * PostCondition :  HR admin window close
	 *  
	 * <b>Parameters & Example </b> NA 
	 * </pre> 
	 **/
	@RobotKeyword
	public void closeHRAdmin() {
		try {

			performAction.waitUntilElementPresent(hrAdminAccount);			
			performAction.highlightElement(hrAdminAccount);
			Thread.sleep(2000);
			performAction.click(hrAdminAccount, "HR admin account");
			Thread.sleep(1000);
			performAction.click(closeHRRole, "Close HR Role");
			// performAction.closeLatestWindow("Group Settings");
			performAction.selectLatestWindow("Reset Session");
		} catch (Exception e) {
			throw new CustomException("Unable to close HR admin. \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Logout of eEnrollment application as Member (Old member UI)
	 * 
	 * PreCondition : Logged in eErnollment application as Member
	 * 
	 * PostCondition : Logout
	 *  
	 * <b>Parameters & Example </b> NA 
	 * </pre> 
	 **/
	@RobotKeyword
	public void logoutMember() {
		try {
			// Fill user name
			this.clickLogoutMember();
			utils.sleep(1000);
			if (performAction.isElementPresent(logoutMemberConfirmation))
				performAction.click(logoutMemberConfirmation,
				"Logout confirmation popup");

		} catch (Exception e) {
			throw new CustomException("Unable to logout member. \n"
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : logoutNewMemberRole keyword or method is used to Logout of eEnrollment application as Member in New Member Role..
	 * 
	 * PreCondition : Member should be in New member role. 
	 * 
	 * PostCondition : member will logout from the member role.
	 * 
	 * JavaFileName : EnrollmentLogin.java
	 *   
	 * </pre> 
	 **/

	@RobotKeyword
	public void logoutNewMemberRole() {
		try {
			if(performAction.isElementPresent(logoutNewMember)){
				if(performAction.isElementPresent(closeButton)){
					performAction.click(closeButton, "Close button on alert");
				}
				performAction.click(logoutNewMember, "Logout In New Member role");
				performAction.waitUntilElementPresent(logoutLink);
				performAction.click(logoutLink, "Logout link");
			}
			else {
				if(performAction.isElementPresent(skipShopping)){
					performAction.click(skipShopping, "Skip Shopping");
					performAction.click(returnHome, "Return to home");
					performAction.waitUntilElementPresent(logoutNewMember);
					performAction.click(logoutNewMember, "Logout In New Member role");
					performAction.waitUntilElementPresent(logoutLink);
					performAction.click(logoutLink, "Logout link");
				}
			}
		} catch (Exception e) {
			throw new CustomException("Unable to logout member. \n"
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 * 
	 * Description : Keyword or method used to login to Enrollment application with valid Carrier rep login credentials
	 * 
	 * PreCondition : Enrollment Login Page Opened
	 * 
	 * PostCondition : Logs in as the Carrier Representative
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | jsonTagName | 
	 * | validcarrierrepcredentials |
	 * </pre>	
	 **/
	@RobotKeyword
	@ArgumentNames({ "jsonTagName"})
	public void loginToEnrollmentAsCarrierRepresentive(String jsonTagName) {
		try {
			JSONObject credentials = ReadJsonTestData.getTestData(jsonTagName);
			System.out.println("credentials = " + credentials.toJSONString());
			// Fill user name
			this.setUserName(credentials.get("username").toString());
			// Fill password
			this.setPassword(credentials.get("password").toString());
			// Click Login button
			this.clickLogin();
		} catch (Exception e) {
			// logger.error("Application Logging Error \n");
			scr.capturePageScreenshot();
			throw new CustomException("Unable to login as Carrier Representative Admin. \n"
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Rajeswari Nimmala
	 * 
	 * Description : 'verifyMessageNotExist' keyword to verify text message is  not available on the page  
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : Page in which verification need to be performed
	 * 
	 * PostCondition : Verification performed
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strMessage - any string which is not uniquely available on the page
	 * 
	 * Java File Name: EnrollmentLogin.java
	 * </pre>	
	 **/
	@RobotKeyword
	@ArgumentNames({ "strMessage" })
	public void verifyMessageNotExist(String strMessage) {
		try {
			this.verfiyMessage(strMessage);
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.error(" Text :: + " + strMessage + "   :: not found");
			
			throw new RuntimeException("Message " + strMessage + " not found"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *
	 * Description : updateCarrierRepresentativesPassword Keyword or method is used to perform the "UpdatePassword".
	 *
	 * Role : Vista Role
	 *
	 * PreCondition : Carrier Representative Log-in Page Opened
	 *
	 * PostCondition : Carrier Representative Password is Updated successfully..
	 *
	 * Java File Name : EnrollmentLogin.java
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | updatepassword |
	 * |ex: td:carrierrepupdatepassword |
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"updatepassword"})
	public void updateCarrierRepresentativePassword(String updatepassword) {
		try {
			if (updatepassword.startsWith("td:"))
				updatepassword = updatepassword.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(updatepassword.toLowerCase());
			object = fields.get("newpassword");
			String newPassword = utils.getValue(object.toString());
			object = fields.get("confirmpassword");
			String confirmPassword = utils.getValue(object.toString());
			//Carrier Representative Update Password
			this.updateCarrierPassword(newPassword, confirmPassword);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Updating the Carrier Representative Password"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *
	 * Description : Update Agent Password Keyword or method is used to perform the "UpdatePassword".
	 *
	 * Role : Vista Role
	 *
	 * PreCondition : Agent Log-in Page Opened
	 *
	 * PostCondition : Agent Password is Updated successfully..
	 *
	 * Java File Name : EnrollmentLogin.java
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | updatepassword |
	 * |ex: td:agentupdatepassword |
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"updatepassword"})
	public void updateAgentLoginPassword(String updatepassword) {
		try {
			if (updatepassword.startsWith("td:"))
				updatepassword = updatepassword.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(updatepassword.toLowerCase());
			object = fields.get("newpassword");
			String newPassword = utils.getValue(object.toString());
			object = fields.get("confirmpassword");
			String confirmPassword = utils.getValue(object.toString());
			//Agent Update Password
			this.updateAgentPassword(newPassword, confirmPassword);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Updating the Agent Password"
							+ e.getMessage());
		}
	}
}
