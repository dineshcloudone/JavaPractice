
package com.benefitfocus.robot.vista.groups.basics;

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
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;
import com.benefitfocus.robot.utils.ReadJsonTestData;

@RobotKeywords
public class PersonalCustomization {
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
	protected BasicCommon common;

	//Locators on the Page
	By categoryTypeForm = By.xpath("//label[@for='categoryTypeForm']");

	By allowUnknownGender = By.id("unknownGenderOption");
	By allowHiddenDependentsOptions = By.id("allowHiddenDependentsOptions");
	By save = By.linkText("Save");
	By clickSave = By.xpath("//a[@class='buttonInnerLink']//strong[contains(text(),'Save')]");
	
	By changeDependentVerification = By.id("dependentVerificationEnabled");
	By turnOnDependent = By.id("enableDependentVerificationByBenefitType-nativeHtmlElement");
	By carrierCommunication = By.id("communicatingCarrier");
	By userRoleForCommunication = By.id("rolesForCommunicationPreferences");
	By selectMedical = By.id("dependentVerificationRequiredBenefitTypes");

	//Private Methods

	private void allowDependents() {
		performAction.select(allowUnknownGender, "Allow Subscriber and Dependents", "Allow Subscriber and Dependents");
	}

	private void hideDependents() {
		performAction.select(allowHiddenDependentsOptions, "Allow HR Admin and Member", "Select Allow HR Admin and Member");
	}

	private void save() {
		performAction.click(save, "Click Save Button");
	}

	private void saveConfiguration() {
		performAction.click(clickSave, "Click Save Button");
	}
	
	private void changeDependentVerificationToNo() {
		performAction.select(changeDependentVerification, "No", "Select Required Option");
		logger.info("Changed to NO");
	}
	
	private void changeDependentVerificationToYes() {
		performAction.select(changeDependentVerification, "Yes", "Select Required Option");
		logger.info("Changed to YES");
	}
	
	private void turnDependentOn() {
		performAction.click(turnOnDependent, "Turn On Dependent Verification");
	}
	
	private void selectPlan(String strMedical) {
		performAction.select(selectMedical, strMedical, "Select Required Option");
	}
	
	private void selectCarrier(String strCommunication) {
		performAction.select(carrierCommunication, strCommunication, "Select Required Option");
	}
	
	private void selectUserRole(String strUserRole) {
		performAction.select(userRoleForCommunication, strUserRole, "Select Required Option");
	}

	//select or enter a value based on the Label we have selected in the page
	private void actionPerform(String tagName,String locator,String type,String value)
	{
		if(tagName.equalsIgnoreCase("select"))
		{		
			By loctor=By.xpath(locator);				
			performAction.select(loctor, value, "Select drop down element");
		}
		if((tagName.equalsIgnoreCase("input"))&&(type.equalsIgnoreCase("text")))
		{
			By loctor=By.xpath(locator);
			performAction.clearEnter(loctor, value,"Text Box");
		}
		if((tagName.equalsIgnoreCase("input"))&&(type.equalsIgnoreCase("checkbox")))
		{

		}
		if(tagName.equalsIgnoreCase("textarea"))
		{
			By loctor=By.xpath(locator);				
			performAction.clearEnter(loctor, value,"Text Box");
		}
	}

	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'updatePersonalCustomization' keyword used to update Personal Customization information for a group  in vista role
	 *
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in Basics Page 
	 * 
	 * PostCondition : user will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * </pre> 
	 * 
	 * Java file Name : PersonalCustomization.java
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updatePersonalCustomization(String labelName,String strValue){
		try	{
			if(performAction.isElementPresent(categoryTypeForm))
			{
				logger.info("Add Category Type Page is reloaded");	
			}
			logger.info("Object created");
			common.updateInfo(labelName,strValue);
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in Personal Customization page in Vista Role"
					+ e.getMessage());
		}
	}	

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Change Dependent Configuration for Member in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Personal Customization Page Opened in Vista Role
	 * 
	 * PostCondition : Updated Dependent Configuration Settings for Member in Vista Role
	 * 
	 * Java File Name : PersonalCustomozation.java
	 * 
	 * | Configuration Type |
	 * | ex: Allow Unknown Gender/Allow Users to Hide Dependents |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strConfigurationType" })
	public void changeDependentConfigurationForMemberInVistaRole(String strConfigurationType) {
		try {

			if (strConfigurationType.equalsIgnoreCase("Allow Unknown Gender")) {
				this.allowDependents();
				this.save();
			}else if (strConfigurationType.equalsIgnoreCase("Allow Users to Hide Dependents")) {
				this.hideDependents();
				this.saveConfiguration();
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Save Button Found"
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Change Dependent Verification Settings for Group in Vista Role for HR Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Basics Tab and Personal Customization Page Opened
	 * 
	 * PostCondition : Verification Setting Changed for Dependent for Medical Benefit Successfully
	 * 
	 * Java File: PersonalCustomozation.java
	 * 
	 * | dependentverificationchanges |
	 * | td:dependentverificationchanges (Medical, Blue Cross and Blue Shield of Kansas City, HR Admin) |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "dependentverificationchanges" })
	public void changeDependentVerificationForMedicalBenefitOnly(String dependentverificationchanges) {
		try {
			this.changeDependentVerificationToNo();
			this.changeDependentVerificationToYes();
			this.turnDependentOn();
			Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(dependentverificationchanges.toLowerCase());
            object = fields.get("medical");
            String medical=utils.getValue(object.toString());
			this.selectPlan(medical);
			object = fields.get("communication");
            String communication=utils.getValue(object.toString());
			this.selectCarrier(communication);
			object = fields.get("userrole");
            String userrole=utils.getValue(object.toString());
			this.selectUserRole(userrole);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Not Found Save Button"
					+ e.getMessage());
		}
	}
}
