package com.benefitfocus.robot.member;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class InitialLoginPage {

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected Screenshot scr;

	By language = By.id("spokenLanguage");
	By newPassword = By.id("newPassword");
	By newConfirmPassword = By.id("newConfirmPassword");
	By secretQuestion1 = By.id("secretQuestion-1");
	By secretAnswer1 = By.id("secretAnswer-1");
	By secretQuestion2 = By.id("secretQuestion-2");
	By secretAnswer2 = By.id("secretAnswer-2");
	By secretQuestion3 = By.id("secretQuestion-3");
	By secretAnswer3 = By.id("secretAnswer-3");
	By city = By.id("city");
	By state = By.id("state");
	By errors = By.id("errors");
	By verifyHomePage = By.linkText("Login Information");
	By editSecretQuestion = By.id("SecretQuestionsEditor");

	By saveButtonOnLanguagePage = By.id("submitForm");
	By saveButtonOnPwdChange = By.xpath("//button[contains(text(),'Save')]");
	By saveButtonOnSecretQuestion = By
			.xpath("//button[contains(text(),'Save')]");
	By saveButtonOnPasswordChanged = By
			.xpath("//button[contains(text(),'Next')]");
	By saveButtonOnAnnoucements = By.xpath("//button[contains(text(),'Next')]");
	By agreementCheckbox = By.id("agreement-nativeHtmlElement");
	
	By userName = By.id("j_username");
	By password = By.id("j_password");	
	By loginIdBtn = By.id("log-in-command");
    By errorMsg = By.xpath("//div[@class='error']");
	
 // Set user name in textbox
 	private void setUserName(String strUserName) {
 		performAction.enter(userName, strUserName, "username textbox");		
 	}

 	// Set password in password textbox
 	private void setPassword(String strPassword) {
 		performAction.enter(password, strPassword, "Password textbox");
 	}

 	// Click on login button
 	private void clickLogin() {
 		performAction.click(loginIdBtn, "login button");
 	}

	// Click Save Button
	private void clickSaveButton() {
		performAction.click(saveButtonOnLanguagePage, "Save Button");
	}

	// set the newPassword text box
	private void setNewPassword(String strnewPassword) {
		performAction.enter(newPassword, strnewPassword,
				"New Password Text box");
	}

	// set the newConfirmPassword text box
	private void setConfirmPassword(String strnewPassword) {
		performAction.enter(newConfirmPassword, strnewPassword,
				"New Confirm Password Text box");
	}

	// click the PasswordSave Button
	private void clickPasswordSavebutton() {
		performAction.click(saveButtonOnPwdChange, "Save Password Button");
	}

	

	// click the saveButtonOnSecretQuestion
	private void clickSecretQuestionsSavebutton() {
		performAction.click(saveButtonOnSecretQuestion,
				"Save Secret Question Button");
	}

	// click the saveButtonOnPasswordChanged
	private void clickPasswordSavedNextbutton() {
		if (performAction.isElementPresent(saveButtonOnPasswordChanged)) {
			performAction.click(saveButtonOnPasswordChanged,
					"Save Password Changed Button");
		}
	}

	// click the saveButtonOnAnnoucements
	private void clickNextbuttonOnAnnouncements() {
		performAction.click(saveButtonOnAnnoucements,
				"Save Button on Announcements Page");
	}

	// Click Agreements check box
	private void clickAgreementTersmofUse() {
		performAction.click(agreementCheckbox, "Agreement Check box");
	}

	private void verifyLockoutMessages(String loginId, String password)
	{
		String errMsg;
		for (int i=1;i<6;i++)
		{
			this.setUserName(loginId);
			this.setPassword(password);
			this.clickLogin();
			if(i==4){
				if (performAction.isElementPresent(errorMsg)){

					errMsg = browser.getCurrentWebDriver().findElement(errorMsg).getText();
					
					scr.capturePageScreenshot();
					Assert.assertTrue("Lock out message is incorrect for 4 attempts", 
								errMsg.contains("Your username and password don't match our records. To keep your account from being disabled and to recover your login information, please click on \"Can't access your account?\""));
												
				}
			}	
			if(i==5){
				if (performAction.isElementPresent(errorMsg)){

					errMsg = browser.getCurrentWebDriver().findElement(errorMsg).getText();
					
					scr.capturePageScreenshot();
					Assert.assertTrue("Lock out message is incorrect for 5 attempts", 
								errMsg.contains("Your account is disabled due to too many failed attempts. Please click on \"Can't access your account?\" to recover your login information."));
					}
			}	
			
		
		}
		
	}
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword or method used to choose the preferred language during initial login page
	 * 
	 * PreCondition : New Member is created and member logging into the system for the first time. 
	 * 
	 * PostCondition : Selects the specified language option and clicks Save button
	 *  
	 * <b>Parameters & Example </b> 
	 * | strLanguage | 
	 * | English / Spanish | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strLanguage" })
	public void choosePreferredLanguage(String strLanguage) {
		try {
			if (strLanguage != "") {
				By preferredLanguage = By.xpath("//*[@name='languageSelection'][@value='"+strLanguage.toUpperCase()+"']");
				if (performAction.isElementPresent(preferredLanguage)) {
					//this.setPreferredLanguage(strLanguage);  
					performAction.click(preferredLanguage, "Preferred Language Radio Button");
					this.clickSaveButton();
				}
			}
		} catch (Exception e) {
			throw new CustomException(
					"Unable to choose preferred language. Error : "
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword or method used to change the member login password. if NOT specified, sets the password to bfEnrol1.
	 * 
	 * PreCondition : New Member is created and member logging into the system for the first time. 
	 * 
	 * PostCondition : Sets the new password as 'bfEnrol1' as given by the user.
	 *  
	 * <b>Parameters & Example </b> 
	 * | pwd | 
	 * | bfEnrol1 | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "pwd" })
	public void changeInitialMemberPassword(String pwd) {
		try {
			if(pwd.startsWith("td:")){
				pwd = utils.getValue(pwd);
			}
			if (pwd == null)
				pwd = "bfEnrol1";
			this.setNewPassword(pwd);
			this.setConfirmPassword(pwd);
			this.clickPasswordSavebutton();
		} catch (Exception e) {
			throw new CustomException(
					"Unable to change member login password. Error : "
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword or method used to continue on group and
	 * global announcements page based on the isShown parameter value passed
	 * from JSON test data file.
	 * 
	 * PreCondition : New Member is created and member logging into the system for the first time. 
	 * 
	 * PostCondition : Announcement page is verified and click Next if configured for the group
	 *  
	 * <b>Parameters & Example </b> 
	 * | isShown | 
	 * | yes / no | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "isShown" })
	public void announcementsPage(String isShown) {
		try {
			if(isShown.startsWith("td:")){
				isShown = utils.getValue(isShown);
			}
			if (isShown.equalsIgnoreCase("yes"))
				this.clickNextbuttonOnAnnouncements();
		} catch (Exception e) {
			throw new CustomException(
					"Excepioon occured verifying announcement. Error : "
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword or method used to continue on Agreements page if
	 * shown based on the isShown parameter value passed from JSON test data
	 * 
	 * PreCondition : New Member is created and member logging into the system for the first time. 
	 * 
	 * PostCondition : Agreements page is verified and checked and click Next if configured for the group
	 *  
	 * <b>Parameters & Example </b> 
	 * | isShown | 
	 * | yes / no | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "isShown" })
	public void agreementsPage(String isShown) {
		try {
			if(isShown.startsWith("td:")){
				isShown = utils.getValue(isShown);
			}
			if (performAction.isElementPresent(agreementCheckbox)
					&& (isShown.equalsIgnoreCase("yes"))) {
				this.clickAgreementTersmofUse();
				this.clickNextbuttonOnAnnouncements();
			}
		} catch (Exception e) {
			throw new CustomException(
					"Exception occured verifying announcement. Error : "
							+ e.getMessage());
		}
	}

		
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword or method used to choose the secret questions and continue by saving
	 * 
	 * PreCondition : New Member is created and member logging into the system for the first time. 
	 * 
	 * PostCondition : Secret Questions and answers are selected with "Test" as answer for each question 
	 *  
	 * <b>Parameters & Example </b> 
	 * | noofQuestions | 
	 * | 3 / 5  - depending on the group configuration | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "noofQuestions" })
	public void selectSecretQuestionsAndSave(String noofQuestions) {
		try {
			if(noofQuestions.startsWith("td:")){
				noofQuestions = utils.getValue(noofQuestions);
			}
			int noofQs = Integer.parseInt(noofQuestions);

			if (performAction.isElementPresent(editSecretQuestion)
					&& performAction.isDisplayed(editSecretQuestion,
							"Edit button")) {
				performAction.click(editSecretQuestion,
						"Secret questions edit button");
			}
			noofQs = browser
					.getCurrentWebDriver()
					.findElements(
							By.xpath("//fieldset[@id='secretQuestions']/fieldset/div"))
					.size() / 2;
			do {
				for (int i = 1; i <= noofQs; i++) {
					By question = By.id("secretQuestion-" + i);
					By answer = By.id("secretAnswer-" + i);
					performAction.select(question, "RND", "Secrte Question "
							+ i);
					performAction.enter(answer, "Test", "Secrte Question " + i);
				}
				this.clickSecretQuestionsSavebutton();
			} while (performAction.isElementPresent(errors));

			this.clickPasswordSavedNextbutton();

		} catch (Exception e) {
			logger.warn("Exception occured in selecting secret questions "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	  * Author  : Nagarjuna Thallam
	 *  
	  * Description : runExistedMember keyword or method used to click on the button which comes after the existed member login
	  * and it has to combine with login with valid credentials keyword.
	 * 
	  * PreCondition :  Member must login into the application with existed credentials
	 * 
	  * PostCondition : member successfully redirected to member Home page.
	 *  
	  * </pre> 
	  **/

	@RobotKeyword
	public void runExistedMember() {
		try {
			this.clickNextbuttonOnAnnouncements();
		} catch (Exception e) {
			logger.warn("Exception occured in login "
					+ e.getMessage());
		}
	}
	
	
	/**
	 * <pre> 
	 * Author  : Sunitha Yerra
	 * 
	 * Role  :  Member role
	 *  
	 * Description : Keyword or method used to verify the password lockout messages for a member login.
	 * 
	 * PreCondition : Member logging into the system using member role. 
	 * 
	 * PostCondition : The member account gets locked on 5th invalid attempt.
	 * 
	 * Java File Name: InitialLoginPage
	 *  
	 * <b>Parameters & Example </b> 
	 * | loginId |  password | 
	 * | hoso5440 | bfEnrol1 |
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "loginId", "password" })
	public void verifyPasswordLockoutMessage(String loginId, String password) {
		try {
			loginId = utils.getValue(loginId);
			
			verifyLockoutMessages(loginId,password);
			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while verifying password lockout messages : ");
			throw new CustomException(
					"Exception occured while verifying password lockout messages : "
							+ e.getMessage());
		}
	}
	
}
