package com.benefitfocus.robot.vista.groups.users;

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
public class AddAdministratorAccount {

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

	// Locators on the Page
	// /////Create New Administrator Account Fields////////

	// General Information Fields
	By nameFirstName = By.xpath("//input[@id='firstName']");
	By nameLastName = By.xpath("//input[@id='lastName']");
	By workEmail = By.id("email");

	// Login Information
	By loginId = By.id("loginID");
	By password = By.id("password");
	By passwordConfirmation = By.id("passwordConfirmation");
	By save = By.xpath("//strong[text()='Save']");
	By Logout = By.xpath("//b[text()='Logout']");
	By mouseOverOnExistingAccount = By
	.xpath("//*[@id='innerInnerInnerInnerPageCore']/form/div/table/tbody/tr[5]/td[1]");
	By clickonEditEnablements = By
	.xpath("//a[contains(text(),'Edit Enablements')]");
	By clickonEditExistingAccount = By
	.xpath("//a[contains(text(),'Edit Account')]");
	By clickonDelete = By.xpath("//a[text()='Delete']");
	By clickonSelectAllLink = By.xpath("//a[text()='Select All']");
	By clickonAddEnablement = By
	.xpath("//a[contains(text(),'Add Enablement')]");
	By clickonLogoutlink = By.xpath("//b[text()='Logout']");
	By popup = By.xpath("//a[@class='buttonInnerLink' and text()='Proceed']");
	By TaskChangeNotificationsMessage = By
	.xpath("//h2[contains(text(),'Task Change Notification Settings')]");

	// permission
	// Allow Administrator to Login
	By AllowAdministratortoLoginDropDown = By
	.xpath("//select[@id='loginAllowed']");
	By DoesthisuseraccountrequirepermanentaccessrestrictionDropDown = By
	.xpath("//select[@id='permanentAccessRestrictionCause']");

	// set the FirstName Text Box
	private void setFirstName(String strFirstname) {
		performAction
		.clearEnter(nameFirstName, strFirstname, "Firstname Field");
	}

	// set the LastName Text Box
	private void setLastName(String strLastname) {
		performAction.clearEnter(nameLastName, strLastname, "Lastname Field");
	}

	// set the Work Email
	private void setWorkEmail(String strWorkEmail) {
		performAction.clearEnter(workEmail, strWorkEmail, "WorkEmail Field");
	}

	// set the loginId Text Box
	private void setLoginId(String strLoginId) {
		performAction.clearEnter(loginId, strLoginId, "LoginId Field");
	}

	// set the Password Text Box
	private void setPassword(String strPassword) {
		performAction.clearEnter(password, strPassword, "Password Field");

	}

	// set the PasswordConfirmation Text Box
	private void setPasswordConfirmation(String strPasswordConfirmation)
	throws Exception {

		performAction.clearEnter(passwordConfirmation, strPasswordConfirmation,
		"PasswordConfirmation Field");

	}

	// Save Button
	private void clickSaveButton() throws Exception {

		performAction.click(save, "save Field");

	}

	// verify the Task Change Notifications Message
	private void veirfyTaskChangeNotification() {

		try {
			String ExpectedText = "Task Change Notification Settings";

			String ActualText = browser
			.getCurrentWebDriver()
			.findElement(
					By.xpath("//h2[contains(text(),'Task Change Notification Settings')]"))
					.getText();
			if (ExpectedText.contains(ExpectedText.trim())) {

				logger.info("Your message has been verfied successfully :"
						+ ActualText);

			} else {
				logger.info("Your expected message is not avilabel in the webpage : "
						+ ExpectedText);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 * 
	 * Description  : 'createAdministratorAccountInVistaRole' Keyword or method is used to perform creating VistaAdministratorAccount in Vista Admin Role.
	 * 
	 * Role : Vista Role
	 * 
	 * Precondition : Create New Administrator Account Page in Vista Admin  Role.
	 * 
	 * PostConditions :  Administrator Account is created successfully.
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | classification | 
	 * 
	 * | vistacreatenewadministratoraccountdetails - is used to get the data set from the Json File "eEnrollmentCommon.json". |
	 * 
	 * Java File Name : AddAdministratorAccount.java
	 * 
	 * </pre>
	 * 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strAddAdminAcountDetails", "OutLoginID",
	"OutLoginPassword" })
	public void createAdministratorAccountInVistaRole(
			String strAddAdminAcountDetails, String OutLoginID,
			String OutLoginPassword) {

		try {
			if (strAddAdminAcountDetails.startsWith("td:"))
				strAddAdminAcountDetails = strAddAdminAcountDetails
				.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData
			.getTestData(strAddAdminAcountDetails.toLowerCase());
			logger.info("fields = " + fields.toJSONString());

			object = fields.get("firstname");
			if (object != null) {
				this.setFirstName(object.toString());
			}

			object = fields.get("lastname");
			if (object != null) {
				this.setLastName(object.toString().toString());
			}
			object = fields.get("workemail");
			if (object != null) {
				this.setWorkEmail(object.toString().toString());
			}

			object = fields.get("loginid");
			if (object != null) {
				// store the loginid
				String loginid = utils.generateRandomNumber(object.toString());
				this.setLoginId(loginid);
				browser.hMap.put(OutLoginID, loginid);
			}

			object = fields.get("password");
			if (object != null) {

				// store the loginid
				String loginPassword = utils.generateRandomNumber(object
						.toString());
				this.setPassword(loginPassword);
				browser.hMap.put(OutLoginPassword, loginPassword);
				// this.setPassword(object.toString().toString());
			}

			object = fields.get("passwordconfirmation");
			if (object != null) {
				this.setPasswordConfirmation(object.toString().toString());
			}

			//Allow Access to the Correct Effective Date Wizard DropDown "Yes"
			performAction.select(By.id("allowEffectiveDateCorrections"), "Yes","Allow Access to the Correct Effective Date Wizard DropDown" );

			// click on savebutton
			this.clickSaveButton();
			// verify the message
			this.veirfyTaskChangeNotification();
			// click on savebutton
			this.clickSaveButton();

		} catch (Exception e) {
			logger.info("Exception occured while Entering Vista Add Admin Account Details "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Entering Vista Add Admin Account Details "
					+ e.getMessage());
		}
	}

}
