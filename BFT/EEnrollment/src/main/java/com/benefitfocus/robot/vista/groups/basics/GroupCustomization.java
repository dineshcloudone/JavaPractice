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
public class GroupCustomization {

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
	//Locators on the Group Customization Page
	By lookBackProgram=By.id("lookBackProgram");
	By lookBackProgramUrl=By.id("lookBackProgramUrl");
	By saveButton = By.xpath("//strong[contains(text(),'Save')]");
	// Variables for the public methods
	
	
	// Select Look Back Program option
	private void selectLookBackProgram(String strOption){
		performAction.select(lookBackProgram, strOption, "Select Look Back Program option");
	}
	// Enter look Back Program Url
	private void enterLookBackProgramURL(String strURL){
		performAction.clearEnter(lookBackProgramUrl, strURL, "Enter look Back Program Url");
	}
	// Click Save Button
	private void saveButton() {
		performAction.click(saveButton, "Click on Save Button");
	}
	
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'updateGroupCustomization' keyword used to update Group Customization information for a group  in vista role
	 * 
	 * Role : Vista Role
     * 
	 * PreCondition : user should be in Basics Page 
	 * 
	 * PostCondition : user  will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * 	
     * Java file Name :  GroupCustomization.java
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateGroupCustomization(String labelName,String strValue){
	try	{
		
		logger.info("Object created");
		common.updateInfo(labelName,strValue);
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in Group Customization page "
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Dilip K
	 *  
	 * Description  : 'Enable Look Back Program' keyword used to enable Look Back Program from Group Customization in Vista Role.
	 * 
	 * PreCondition : Navigate to Basics >> Group Customization.  
	 * 
	 * PostCondition: Able to enable Look Back Program.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strURL |
	 * | https://www.benefitfocus.com  |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strOption","strURL" })
	public void enableLookBackProgram(String strOption,String strURL) {

		try {
			this.selectLookBackProgram(strOption);
			this.enterLookBackProgramURL(strURL);
			this.saveButton();
            scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();			
			throw new CustomException(
					"Exception in Enable Look Back Program "
					+ e.getMessage());
		}
	}

}
