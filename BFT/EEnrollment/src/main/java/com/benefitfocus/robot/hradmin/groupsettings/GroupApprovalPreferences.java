package com.benefitfocus.robot.hradmin.groupsettings;

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
public class GroupApprovalPreferences {
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
	
	By saveLink = By.linkText("Save");

	/**
	 * <pre>
	 * Author  : Sunitha Yerra
	 * 
	 * Role:  HR Role
	 *  
	 * Description : 'updateGroupApprovalPreferences' keyword used to update configurations in Group Approval Preferences for hr admin role
	 * 
	 * PreCondition : user should be in Group Approval Preferences page under HR Admin -> Group Settings
	 *  
	 * PostCondition : user  will be redirected to HR Admin Home page.
	 * 
	 * <b>Parameters & Example </b> 
	 * 
	 * | labelName | strValue | 
	 * | labelName - is the field name to which changes need to be made |strValue - Value in Drop down that has to be updated
	 * 
	 * Java file Name :  GroupApprovalPreferences.java
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "labelName", "strValue" })
	public void updateGroupApprovalPreferences(String labelName, String strValue)
	{
		String location = "//tr[@class='fieldListRow']//td[2]//label";
		String currentlabelname = "";
		String labelLocator = location + "[text()='" + labelName + "']";
		String currentLocator = "";
		String objectLocator = "";
		
		try {
			
			currentLocator = "";
			logger.info("Label : "+ browser.getCurrentWebDriver().findElement(By.xpath(labelLocator)).getText());
			currentlabelname = browser.getCurrentWebDriver().findElement(By.xpath(labelLocator)).getText();
			objectLocator = labelLocator + "/../..//td[1]/*";
			logger.info("object Tag : "	+ browser.getCurrentWebDriver().findElement(By.xpath(objectLocator)).getTagName());
			
			if (currentlabelname.equalsIgnoreCase(labelName)) {
				
					currentLocator = labelLocator + "/../..//td[1]/input";
					String checked =browser.getCurrentWebDriver().findElement(By.xpath(currentLocator)).getAttribute("checked");
				if(strValue.equalsIgnoreCase("true")){	
					if(checked==null || !strValue.equalsIgnoreCase("true"))
					{
						performAction.click(currentLocator, "Check box");
					}
					else if(checked!=null && strValue.equalsIgnoreCase("false"))
					{
						performAction.click(currentLocator, "Check box");
					}
				}else{
					if(checked!=null && strValue.equalsIgnoreCase("true"))
					{
						performAction.click(currentLocator, "Check box");
					}
					
				}
					
			}
			scr.capturePageScreenshot();
			performAction.click(saveLink, "Save Button");
		} catch (Exception e) {
			logger.info("Exception in performing action in Group Approval Preferences page " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing action in Group Approval Preferences page "
							+ e.getMessage());
		}
				

	}
	

}
