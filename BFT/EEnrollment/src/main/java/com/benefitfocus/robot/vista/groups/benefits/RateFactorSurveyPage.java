package com.benefitfocus.robot.vista.groups.benefits;

import java.util.Arrays;

import com.benefitfocus.robot.vista.groups.basics.BasicCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;

@RobotKeywords
public class RateFactorSurveyPage {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Logging logger;

	@Autowired
	protected Screenshot captureScreen;

	@Autowired
	protected BasicCommon common;

	// Locators
	By rateFactorSurveyType = By.id("surveyConfigStrategyType");
	By participationPeriod = By.id("selectedParticipationPeriodKeys");
	By globalRateFactorSurvey = By.id("selectedGlobalRateFactorSurveyKey");
	By selectRoles = By.id("selectedRoles");
	By addButton = By.xpath("//strong[text()='Add']");
	By existedRateFactorSurveyimage = By
			.xpath("//img[@src='/static/images/icons/editcontextualmenu.gif']");
	By deleteButton = By.xpath("//a[text()='Delete']");
	By existedRFSTable = By
			.className("table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate");
	By saveButton = By.xpath("//strong[contains(text(),'Save')]");
	By situsSurveyStrategy = By.id("rateFactorStrategyType");
	By enableRateFactorSurvey = By.id("rateFactorSurveyEnabled");
	By saveRFSButton = By.xpath("//strong[text()='Save']");
	By editButton = By.xpath("//a[text()='Edit']");
	String RFSTable = "//table[contains(@class,'table table-striped table-bordered')]/tbody/tr";
	By updateButton = By.xpath("//strong[text()='Update']");
	By existingSurvey = By.id("globalRateFactorSurveyList");
	By categoryTypeForm = By.xpath("//label[@for='categoryTypeForm']");

	// Select Global Ratefactor survey
	private void selectGlobalRateFactorSurveyType() {
		performAction.select(rateFactorSurveyType,
				"Global Survey Strategy By Participation Period",
				"Ratefactorsurvey Type");
	}

	// Select Global Ratefactor survey
	private void selectCarrierRateFactorSurveyType() {
		performAction.select(rateFactorSurveyType, "Carrier Survey Strategy",
				"Ratefactorsurvey Type");
	}

	// Select Participation Period
	private void selectParticipationPeriod(String[] StrPeriod) {
		try {

			String[] period = StrPeriod;
			for (int i = 0; i < period.length; i++) {
				performAction.select(participationPeriod, period[i],
						"Participation Period");
			}
		} catch (Exception e) {
			throw new CustomException(
					"exception occured in selecting participation periods");
		}
	}

	// Select Rate Factor Survey Question
	private void selectRateFactorSurvey(String StrQuestion) {
		performAction.select(globalRateFactorSurvey, StrQuestion,
				"Rate Facteor Survey Question");
	}

	// Select Carrier Situs Strategy
	private void selectSitusStrategy(String StrSitus) {
		performAction.select(situsSurveyStrategy, StrSitus, "Situs Strategy");
	}

	// Select Roles for the Rate factor Survey
	private void selectRoles(String[] StrRoles) {

		String[] SelectRoles = StrRoles;

		for (int i = 0; i < SelectRoles.length; i++) {

			performAction
					.select(selectRoles, SelectRoles[i], "Selecting Roles");
		}
		System.out.println("SelectRoles are ====="
				+ Arrays.toString(SelectRoles));
	}

	// Click on Add Survey Button
	private void addSurvey() {
		performAction.click(addButton, "Add Button ");
	}

	// Click on Save Button to save the Survey
	private void clickSaveSurvey() {
		performAction.click(saveButton, "Save Button");
	}

	// Select Rate factor Survey Enable/Disable
	private void enableRateFactorSurvey(String StrOption) {
		performAction.select(enableRateFactorSurvey, StrOption,
				"Enable or Disable Surevy");
	}

	// Click to save the Enabled/Disabled Rate Factor Survey
	private void clicksaveRFS() {
		performAction.click(saveRFSButton, "Save enabled RFS");
	}

	// click to update the survey
	private void clickUpdate() {
		performAction.click(updateButton, "Update the survey button	");
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : createGlobalRateFactorSurvey keyword or method is used to Create Global Rate Factor 
	 * Survey in Vista role for the member role.   
	 * 
	 *  Role: Vista Role
	 *
	 * PreCondition : Member should be in Beneficiary Information page
	 * 
	 * PostCondition : New Beneficiary is saved successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Participation Period | Survey Name | Role1,Role2,.. |
	 * | 01/01/2015 - 12/31/2015  |  Tobacco Survey / 2015 health survey | Member / Vista,Member |
	 * 
	 * Java file Name :  RateFactorSurveyPage.java
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "StrPeriod", "StrQuestion", "StrRoles" })
	public void createGlobalRateFactorSurveyInVista(String StrPeriod,
			String StrQuestion, String StrRoles) {
		try {
			this.selectGlobalRateFactorSurveyType();
			this.selectParticipationPeriod(StrPeriod.split(","));
			this.selectRateFactorSurvey(StrQuestion);
			this.selectRoles(StrRoles.split(","));
			this.addSurvey();
			this.clickSaveSurvey();
		} catch (Exception e) {
			captureScreen.capturePageScreenshot();
			throw new CustomException(
					"Error occured while running Global Survey Creation");
		}

	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : createCarrierRateFactorSurvey keyword or method is used to Create Carrier 
	 * Rate Factor Survey in Vista role for the member role.   
	 * 
	 *  Role: Vista Role
	 *
	 * PreCondition : Member should be in Rate factor survey page in vista role
	 * 
	 * PostCondition : New survey will be created.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | StrSitusType | Role1,Role2,.. |
	 * |   Use Member situs | Member / Vista,Member |
	 * 
	 * Java file Name :  RateFactorSurveyPage.java
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "StrSitusType", "StrRoles" })
	public void createCarrierRateFactorSurveyInVista(String StrSitusType,
			String StrRoles) {
		try {

			this.selectCarrierRateFactorSurveyType();
			this.selectSitusStrategy(StrSitusType);
			this.selectRoles(StrRoles.split(","));
			this.clickSaveSurvey();
		} catch (Exception e) {
			e.printStackTrace();
			captureScreen.capturePageScreenshot();
			throw new CustomException(
					"Error occured while running Global Survey Creation");
		}

	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : selectRateFactorSurveySetup keyword or method is used to 
	 * enable or disable the Rate factor survey in vista role.   
	 * 
	 *  Role: Vista Role
	 *
	 * PreCondition : Member should be System page in vista role.
	 * 
	 * PostCondition : New survey will be enabled.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | StrOption | 
	 * | Enabled / Disabled|
	 * 
	 * Java file Name :  RateFactorSurveyPage.java
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "StrOption" })
	public void selectRateFactorSurveySetup(String StrOption) {
		try {
			this.enableRateFactorSurvey(StrOption);
			this.clicksaveRFS();
		} catch (Exception e) {
			captureScreen.capturePageScreenshot();
			throw new CustomException(
					"Error occured while Enabling or Disabling RFS");
		}

	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : deleteExistingRateFactorSurveyInVista keyword or method is used to 
	 * delete the Existing Rate factor survey in vista role.   
	 * 
	 *  Role: Vista Role
	 *
	 * PreCondition : Member should be Rate Factor Survey page in vista role.
	 * 
	 * PostCondition :  survey will be deleted.
	 * 
	 * Java file Name :  RateFactorSurveyPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "SurveyName" })
	public void deleteExistingRateFactorSurveyInVista(String SurveyName) {
		try{
			if (performAction.isElementPresent(existingSurvey)){
				int surveyCount = browser.getCurrentWebDriver()
						.findElements(By.xpath(RFSTable)).size();
				logger.info("survey count is..."+surveyCount);

				for (int i = 2; i <= surveyCount; i++) {
					String survey = browser
							.getCurrentWebDriver()
							.findElement(
									By.xpath(RFSTable + "[2]/td[3]/div"))
							.getText();
					logger.info("survey is..."+survey);
					if (SurveyName.toLowerCase().equalsIgnoreCase(
							survey.toLowerCase())||SurveyName.toLowerCase().equalsIgnoreCase("none")) {
						performAction.mouseOver(existedRateFactorSurveyimage,
								"Existed Survey");
						performAction.click(deleteButton, "Delete Button");
					}
				}
			} else {
				logger.info("There is no Existing Rate factor survey");
			}

		} catch (Exception e) {
			captureScreen.capturePageScreenshot();
			e.printStackTrace();
			throw new CustomException("Error occured while deleting RFS");
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : editRateFactorSurveyInVista keyword or method is used to edit the existing Rate factor survey in vista role. 
	 * 
	 * Role: Vista Role
	 *
	 * PreCondition : Member should be System page in vista role.
	 * 
	 * PostCondition : Updated survey will be enabled.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | StrParams[] | 
	 * |SurveyName |participation period|survey Name| Roles |
	 * 
	 * Java file Name :  RateFactorSurveyPage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "StrSurvey", "StrPeriod", "SurveyName", "Roles" })
	public void editRateFactorSurveyInVista(String StrSurvey, String StrPeriod,
			String SurveyName, String Roles) {
		try {
			if (performAction.isElementPresent(existedRateFactorSurveyimage)) {
				int surveyCount = browser.getCurrentWebDriver()
						.findElements(By.xpath(RFSTable)).size();

				for (int i = 2; i <= surveyCount; i++) {
					String survey = browser
							.getCurrentWebDriver()
							.findElement(
									By.xpath(RFSTable + "[" + "]/td[3]/div"))
							.getText();
					if (StrSurvey.toLowerCase().equalsIgnoreCase(
							survey.toLowerCase())) {
						performAction.mouseOver(existedRateFactorSurveyimage,
								"Existed Survey");
						performAction.click(editButton, "Edit Button");
						this.selectParticipationPeriod(StrPeriod.split(","));
						this.selectRateFactorSurvey(SurveyName);
						this.selectRoles(Roles.split(","));
						this.clickUpdate();
						this.clickSaveSurvey();
					}
				}
				/*
				 * Select opt = new Select(browser.getCurrentWebDriver()
				 * .findElement(rateFactorSurveyType)); String sel =
				 * opt.getFirstSelectedOption().getText(); if
				 * ((sel.equalsIgnoreCase("Carrier Survey Strategy")) &&
				 * (StrParams.length < 4)) {
				 * this.selectSitusStrategy(StrParams[1]);
				 * this.selectRoles(StrParams[2].split(","));
				 * this.clickUpdate(); this.clickSaveSurvey();
				 * 
				 * } else if ((sel .equalsIgnoreCase(
				 * "Global Survey Strategy By Participation Period")) &&
				 * (StrParams.length >= 4)) {
				 */
			}
		} catch (Exception e) {
			captureScreen.capturePageScreenshot();
			throw new CustomException("Error occured while deleting RFS");
		}

	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *
	 * Description : 'updateRateFactorSurvey' keyword used to Update the information in Rate FactorSurvey page
	 *
	 * Role : Vista Role
	 *
	 * PreCondition : user should be in Rate Factor Survey Page
	 *
	 * PostCondition : user will be redirected to the Basics page.
	 *
	 * <pre>
	 * <b>Parameters :</b>
	 * |labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 *
	 * Java file Name : RateFactorSurvey.java
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateRateFactorSurvey(String labelName,String strValue){
		try	{
			if(performAction.isElementPresent(categoryTypeForm))
				logger.info("Add Category Type Page is reloaded");

			logger.info("Object created");
			common.updateInfo(labelName,strValue);
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			captureScreen.capturePageScreenshot();
			throw new CustomException("Exception in updating Rate Factor Survey Page"
					+ e.getMessage());
		}
	}

}
