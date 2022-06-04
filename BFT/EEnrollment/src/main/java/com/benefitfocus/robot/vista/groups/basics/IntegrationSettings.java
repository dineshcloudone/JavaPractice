package com.benefitfocus.robot.vista.groups.basics;

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
public class IntegrationSettings {

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

	By documentCentreEnabled = By.id("hrInTouchDocumentCenterEnabled");
	By documentCentreAccessPage = By.id("documentCenterAccessPageEnabled");
	By dependentVerificationDocument = By.xpath("//option[@value='ALL']");
	By saveButton = By.linkText("Save");

	//Private Methods

	private void enableDocumentCenter() {
		performAction.select(documentCentreEnabled, "Yes", "Yes");
		performAction.select(documentCentreAccessPage, "Yes", "Yes");
		performAction.click(dependentVerificationDocument, "Click All Option");
	}

	private void saveConfiguration() {
		performAction.click(saveButton, "Click Save Button");
	}

	//Robot Keywords

	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'updateIngrationSettings' keyword used to update Group Customization information for a group  in vista role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in Basics Page 
	 * 
	 * PostCondition : user  will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters & Example </b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateIntegrationSettings(String labelName,String strValue){
		try	{		
			logger.info("Object created");
			common.updateInfo(labelName,strValue);
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in Integration Settings page in Vista role"
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Update Document Center Access for Member in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Integration Settings Page Opened in Vista Role
	 * 
	 * PostCondition : Document Center Enabled for Member in Vista Role
	 * 
	 * Java File Name : IntegrationSettings.java
	 * 
	 * | |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void updateDocumentCentreEnabled() {
		try {

			this.enableDocumentCenter();
			this.saveConfiguration();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Save Button Found"
					+ e.getMessage());
		}
	}

}
