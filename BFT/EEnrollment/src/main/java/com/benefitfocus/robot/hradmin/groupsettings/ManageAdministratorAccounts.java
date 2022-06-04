package com.benefitfocus.robot.hradmin.groupsettings;

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
public class ManageAdministratorAccounts {
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
	@Autowired
	protected Screenshot captureScreen;

	// Locators on the Page
	By manageAdminAcnts=By.linkText("Manage administrator accounts");
	By createNewAdminAcnt=By.linkText("Create New Administrator Account");
	By textMessage=By.xpath("//h1[text()='Create New Administrator Account']");
	By save = By.xpath("//strong[text()='Save']");
	By groupInformationReport=By.linkText("Group information report");
	By downloadReport=By.linkText("Download Report");

	//Create New Administrator Account Locators
	By firstName=By.id("firstName");
	By lastName=By.id("lastName");
	By workEmail=By.id("email");
	By loginID=By.id("loginID");
	By passwordField=By.id("password");
	By confirmPasswordField=By.id("passwordConfirmation");
	By tablecount = By.xpath("//table[contains(@class,'table tabl')]/tbody/tr");
	By   taskChangeNotificationSettingText =By.xpath("//h2[contains(text(),'Task Change Notification Settings')]");


	//Global  Variables
	String administratortable ="//table[contains(@class,'table tabl')]/tbody/tr";
	String value ="";
	String msg = "";
	int count ;



	private void clickmanageAdministratorAccounts() {
		performAction.click(manageAdminAcnts, "Manage Administrator Accounts");
	}

	private void clickGroupInformationReport()
	{
		performAction.click(groupInformationReport, "Group Information Report");
	}
	private void clickDownloadReport()
	{
		performAction.click(downloadReport, "Download Report");
	}



	//FirstName FieldLabel
	private void setFirstName(String strFirstName){
		performAction.clearEnter(firstName, strFirstName, "FirstName TextBox");
	}
	//LastName Field
	private void setLastName(String strlastName){
		performAction.clearEnter(lastName, strlastName, "LastName TextBox");
	}
	//workEmail Filed
	private void setworkEmail(String strWorkEmail){
		performAction.clearEnter(workEmail, strWorkEmail, "WorkEmail TextBox");
	}
	//LoginId Field
	private void setLoginID(String strLoginID){
		performAction.clearEnter(loginID, strLoginID, "LoginID TextBox");
	}
	//Password Field
	private void setPassword(String strPassword){
		performAction.clearEnter(passwordField, strPassword, "Password TextBox");
	}
	//Confirm Field
	private void setConfirmPassword(String strConfirmPassword){
		performAction.clearEnter(confirmPasswordField, strConfirmPassword, "ConfirmPassword TextBox");
	}

	// Save Button
	private void clickSaveButton() throws Exception {

		performAction.click(save, "save Field");

	}

	// verify the Task Change Notifications Message
	private void veirfyTaskChangeNotification() {

		try {
			if ("Task Change Notification Settings".contains(browser.getCurrentWebDriver().findElement(taskChangeNotificationSettingText).getText().trim())) {
				logger.info("Your message has been verfied successfully :"
						+ browser.getCurrentWebDriver().findElement(taskChangeNotificationSettingText).getText().trim());
			} else {
				logger.info("Your expected message is not avilabel in the webpage : "
						+ "Task Change Notification Settings");
			}
		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in Verifying the Task Notification Settings Message in  Task Change Notifications page "
					+ e.getMessage());
		}
	}
	//delete Administrator Account 
	private void deleteAdministratorAccountInHrRole(String strLoginid){

		count =browser.getCurrentWebDriver().findElements(tablecount).size();
		for (int i = 4; i <=count; i++) {
			String tableLoginId =browser.getCurrentWebDriver().findElement(By.xpath(administratortable+"["+i+"]/td[3]")).getText();
			if (tableLoginId.trim().equalsIgnoreCase(strLoginid)) {
				performAction.mouseOver(By.xpath("//table[contains(@class,'table tabl')]/tbody/tr[5]/td["+i+"]/../td[1]"), "Mouse Over on Administrator Name");
				performAction.click(By.linkText("Permanently Restrict"), "click on Perminently Restrict Link");
				performAction.click(By.linkText("Continue"), "click on Continue Button");
				if (strLoginid.trim().contains(browser.getCurrentWebDriver().getPageSource().trim())) {
					logger.info("Administrator account is not deleted : "+strLoginid);
				} else {
					logger.info("Administrator account has been deleted succesffully : "+strLoginid);
				}
				scr.capturePageScreenshot();
				break;
			}
		}
	}


	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *  
	 * Description : 'createNewAdministratorAccountInHrRole' keyword used to Create New Administrator Account in hr admin role
	 * 
	 * Role : HR Role
	 * 
	 * PreCondition : user should be in HR Admin Group Settings Page
	 *  
	 * PostCondition : Administrator Account is created successfully
	 * <b>Parameters & Example </b>
	 * 
	 * | classification | 
	 * 
	 * | createnewadministratoraccountInHrRole - is used to get the data set from the Json File "ManageAdministratorAccounts.json.json". |
	 * 
	 * Java  File Name :ManageAdministratorAccounts
	 * 
	 * </pre>
	 * 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strAddAdminAcountDetails", "OutLoginID",
	"OutLoginPassword" })
	public void createNewAdministratorAccountInHrRole(String strAddAdminAcountDetails, String OutLoginID,
			String OutLoginPassword) {

		try {
			//this.clickmanageAdministratorAccounts();
			if(performAction.isElementPresent(createNewAdminAcnt))
			performAction.click(createNewAdminAcnt, "Create New Administrator Account");
			msg = browser.getCurrentWebDriver().findElement(textMessage).getText().trim();
			logger.info("Message:::::" + msg);
			try {
				if (msg.equalsIgnoreCase("Create New Administrator Account")) {
					logger.info(msg + " is verified in the page");
					scr.capturePageScreenshot();
				}
				//Entering Create an account page fields
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
					this.setworkEmail(object.toString().toString());
				}

				object = fields.get("loginid");
				if (object != null) {
					// store the login id
					String loginid = utils.generateRandomNumber(object.toString());
					this.setLoginID(loginid);
					browser.hMap.put(OutLoginID, loginid);
				}

				object = fields.get("password");
				if (object != null) {
					// store the login id
					String loginPassword = utils.generateRandomNumber(object
							.toString());
					this.setPassword(loginPassword);
					browser.hMap.put(OutLoginPassword, loginPassword);
				}

				object = fields.get("passwordconfirmation");
				if (object != null) {
					this.setConfirmPassword(object.toString().toString());
				}
				//Allow Access to the Correct Effective Date Wizard DropDown "Yes"
				performAction.select(By.id("allowEffectiveDateCorrections"), "Yes","Allow Access to the Correct Effective Date Wizard DropDown" );
				// click on save button
				this.clickSaveButton();
				// verify the message
				this.veirfyTaskChangeNotification();
				// click on save button
				this.clickSaveButton();
				// click on save button
				this.clickSaveButton();
			} catch (Exception e) {
				logger.info(msg + "  is not find in the page");
				throw new CustomException(
						"Exception in performing action in Create New Administrator Account "
						+ e.getMessage());
			}
		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing action in Create New Administrator Account page "
					+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * 
	 * Author  : Sekhar Tirumala
	 *  
	 * Description : 'deleteHrAdministratorAccountInHrRole' keyword used to delete the Administrator Account hr admin role
	 * 
	 * Role : HR Role
	 * 
	 * PreCondition : user should be in HR admin Group Settings , Manage administrator accounts page.
	 *  
	 * PostCondition : user  will be redirected to HR admin Group Settings , Manage administrator accounts page.
	 * 
	 *  * <b>Parameters & Example </b> 
	 *
	 * | strLoginid | 
	 * | Automation87474 | 
	 * 
	 * Java FileName : ManageAdministratorAccounts.java
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({"strLoginid"})
	public void deleteHrAdministratorAccountInHrRole(String strLoginid){
		try	{
			value =utils.getValue(strLoginid);
			scr.capturePageScreenshot();
			this.deleteAdministratorAccountInHrRole(value);
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in deleting  update administrator account in the table "
					+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'verifyAdministratorAccount' keyword used to verify the Administrator Account hr admin role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : user should be in HR admin Group Settings page
	 *  
	 * PostCondition : user will be redirected to Administrator Account page.
	 * 
	 *  * <b>Parameters & Example </b> 
	 * 
	 * | strAdminUserName | 
	 * | hradminautomation | 
	 * </pre>
	 * 
	 *  Java file Name :  ManageAdministratorAccounts.java
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAdminUserName" })
	public void verifyAdministratorAccount(String strAdminUserName) {
		try {
			this.clickmanageAdministratorAccounts();
			strAdminUserName=strAdminUserName.toUpperCase();
			boolean value=browser.getCurrentWebDriver().getPageSource().contains(strAdminUserName);
			if(value)
			{	
				logger.info(strAdminUserName+" is Verified in Administrator Account page ");
				scr.capturePageScreenshot();
			}

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in verifying Administrator Account page "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'downloadGroupInformationReport' keyword used to download the GroupInformationReport 
	 * 
	 * PreCondition : user should be in HR admin Group Settings page
	 *  
	 * PostCondition : user  will down load the Report as pdf file
	 * 
	 *   <b>Parameters & Example </b> 
	 *  	
	 * </pre>
	 **/
	@RobotKeyword

	public void downloadGroupInformationReport() {
		try {
			this.clickGroupInformationReport();
			this.clickDownloadReport();
			scr.capturePageScreenshot();

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in download the Group Information Report "
					+ e.getMessage());
		}
	}

}
