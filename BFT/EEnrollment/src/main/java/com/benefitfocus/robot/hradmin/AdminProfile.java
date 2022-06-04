package com.benefitfocus.robot.hradmin;

import java.util.concurrent.TimeUnit;

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
public class AdminProfile {
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Screenshot scr;

	//Locators on the Page
	By accountMenu = By.xpath("//div[ contains(@class,'account-menu')]/div/a/span");
	By myProflielink = By.xpath("//div[contains(@class,'panel')]/ul[contains(@class,'nav nav')]/descendant::span");
	By logOut = By.xpath("//a[text()='Log out']");
	By currentPassword = By.id("currentPassword");
	By workEmail = By.id("email");
	By newPassword = By.id("newPassword");
	By confirmNewPassword = By.id("newConfirmPassword");
	By saveButton = By.xpath("//strong[text()='Save']");
	By nextButton = By.xpath("//strong[text()='Next']");
	By confirmationMessage = By.xpath("//div[@id='r1670643956-1']");
	By changeSecurityMessage = By.xpath("//div[@class='footer-left col-md-6 col-sm-6']/div");
	By workEmailField = By.id("email");
	
	//Global Varibales
	String pagesource ="";


	//click on my account Meanu and click
	private void clickAccountMenu(String strNewPassword, String strConfirmPassword) throws Exception {

		//String ExpectedText="Benefitfocus ® is a registered mark of Benefitfocus.com, Inc.";
		boolean flag = performAction.isElementPresent(changeSecurityMessage);
		if (flag == false) {

			performAction.clearEnter(newPassword, strNewPassword, "NewPassword");
			performAction.clearEnter(confirmNewPassword, strConfirmPassword, "ConfirmPassword");
			this.clicksaveButton();
			Thread.sleep(2500);
			//page verification
			String pagesource = browser.getCurrentWebDriver().getPageSource();
			if (pagesource.contains("Your administrator password has been changed.")) {
				logger.info("Your First time administrator password has been changed.");
			} else {
				logger.info("Your First time  administrator password has not  been changed.");
			}

		}

		//click on MyProfileLink
		performAction.click(accountMenu, "accountMenu");
		Thread.sleep(2000);
	}

	//click on AccountMenu
	private void clickMenu() throws Exception {
		//click on MyProfileLink
		performAction.click(accountMenu, "accountMenu");
		Thread.sleep(2000);
	}


	//click on MyProfileLink
	private void clickMyProfileLink() {
		//click on MyProfileLink
		performAction.click(myProflielink, "MyProflieLink");
	}

	//click on LogoutLink
	private void clickLogoutLink() {
		performAction.click(logOut, "LogoutLink");
	}

	//hradmin update password
	private void updatePassword(String strcurrentPassword, String strNewPassword, String strConfirmPassword) throws Exception {
		performAction.clearEnter(currentPassword, strcurrentPassword, "CurrentPassword");
		performAction.clearEnter(newPassword, strNewPassword, "NewPassword");
		performAction.clearEnter(confirmNewPassword, strConfirmPassword, "ConfirmPassword");
		//save button
		this.clicksaveButton();
		Thread.sleep(2500);
		//page verification
		String pagesource = browser.getCurrentWebDriver().getPageSource();
		if (pagesource.contains("Your administrator password has been changed.")) {
			logger.info("Your administrator password has been changed.");
		} else {
			logger.info("Your administrator password has not  been changed.");
		}
		Thread.sleep(2500);


	}

	//for click
	private void clicksaveButton() throws Exception {
		performAction.click(saveButton, "SaveButton");

	}

	//for Nextbutton
	private void clickNextButton() throws Exception {
		performAction.click(nextButton, "NextButton");

	}

	//For WorkEmailTextBox
	private void workEmailTextBox(String strEmailId) throws Exception {
		performAction.clearEnter(workEmailField, strEmailId, "Work EmailID");
		//save button
		this.clicksaveButton();
		Thread.sleep(2500);
		//page verification
		 pagesource = browser.getCurrentWebDriver().getPageSource();
		if (pagesource.contains("Your work email has been changed.")) {
			logger.info("Your work email has been changed.");
		} else {
			logger.info("Your work email has not been changed.");
		}
		Thread.sleep(2500);

		//click on MyProfileLink
		this.clickMenu();
		//click on LogoutLink
		this.clickLogoutLink();
	}


	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description : updateHrAdminPassword Keyword or method is used to perform the "UpdateHRAdminPassword".
	 * 
	 * Role : HR Role
	 *
	 * PreCondition : Create New Administrator Account Page in Vista Admin  Role.
	 *
	 * PostCondition : HrAdmin password is Edited successfully..
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification | strcurrentPassword | strNewPassword | strConfirmPassword |
	 *
	 * | changeaccountsecuritypasswords - is used to get the data set from the Json File "eEnrollmentCommon.json". |
	 *
	 * | strcurrentPassword - strNewPassword - strConfirmPassword|
	 *
	 * | bfEnrol1 , bfEnrol0 , bfEnrol0 |
	 * 
	 *  Java File Name : AdminProfile.java
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"changeAccountNewandconfirmpassword", "strcurrentPassword", "strNewPassword", "strConfirmPassword"})
	public void updateHrAdminPassword(String changeAccountNewandconfirmpassword, String strcurrentPassword, String strNewPassword, String strConfirmPassword) {

		try {

			if (changeAccountNewandconfirmpassword.startsWith("td:"))
				changeAccountNewandconfirmpassword = changeAccountNewandconfirmpassword.substring(3);

			Object object;
			Object object1;

			JSONObject fields = ReadJsonTestData.getTestData(changeAccountNewandconfirmpassword
					.toLowerCase());
			logger.info("fields = " + fields.toJSONString());
			object = fields.get("securityNewPassword");
			object1 = fields.get("securityConfirmPassword");

			if (object != null) {
				//click on AccountMenu
				this.clickAccountMenu(object.toString(), object1.toString());

			}
			//click on MyProfileLink
			this.clickMyProfileLink();

			//hradmin update password
			this.updatePassword(strcurrentPassword, strNewPassword, strConfirmPassword);

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while Updating the HRAdminPassword value "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while Updating the HRAdminPassword value  "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Ch Phani Srikar
	 *
	 * Description : updateHrAdminEmail Keyword or method is used to perform the "UpdateHRAdminEmail".
	 * 
	 * Role : Hr Role
	 *
	 * PreCondition : Create New Administrator Account Page in Vista Admin  Role.
	 *
	 * PostCondition : HrAdmin WorkEmail is Edited successfully.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification | strEmailId |
	 *
	 * | changeaccountsecuritypasswords - is used to get the data set from the Json File "eEnrollmentCommon.json". |
	 *
	 * | strEmailId |
	 *
	 * | Test78@bf.com |
	 * 
	 * Java File Name : AdminProfile.java
	 *
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({"changeAccountNewandconfirmpassword", "strEmailId"})
	public void updateHrAdminEmail(String changeAccountNewandconfirmpassword, String strEmailId) {

		try {

			browser.getCurrentWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			if (changeAccountNewandconfirmpassword.startsWith("td:"))
				changeAccountNewandconfirmpassword = changeAccountNewandconfirmpassword.substring(3);

			Object object;
			Object object1;

			JSONObject fields = ReadJsonTestData.getTestData(changeAccountNewandconfirmpassword
					.toLowerCase());
			logger.info("fields = " + fields.toJSONString());
			object = fields.get("securityNewPassword");
			object1 = fields.get("securityConfirmPassword");

			if (object != null) {
				//click on AccountMenu
				this.clickAccountMenu(object.toString(), object1.toString());
			}
			//click on MyProfileLink
			this.clickMyProfileLink();

			//hradmin update password
			this.workEmailTextBox(strEmailId);

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while Updating the HRAdmin Email value "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while Updating the HRAdmin Email value "
					+ e.getMessage());
		}
	}


}
