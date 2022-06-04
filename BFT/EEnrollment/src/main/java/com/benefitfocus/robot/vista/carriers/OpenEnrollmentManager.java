package com.benefitfocus.robot.vista.carriers;

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
import com.benefitfocus.robot.vista.groups.basics.BasicCommon;

@RobotKeywords
public class OpenEnrollmentManager {
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
	
	
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'clickPrivateLabe' keyword used to click on Private label and open the carrier information
	 * 
	 * PreCondition : user should be in Group search page
	 * 
	 * PostCondition : user  will be redirected to Carrier information page
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value of Carrier name for the group |
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"strValue" })
	public void clickPrivateLabel(String strValue)
	{
		
		String prvateLabel="//tr/td/label[text()='Private Label']";
		
		if(browser.getCurrentWebDriver().findElement(By.xpath(prvateLabel)).getText().equalsIgnoreCase("Privat Label"))
		{
			prvateLabel=prvateLabel+"/../../td//a";
			browser.getCurrentWebDriver().findElement(By.xpath(prvateLabel)).click();
		}		
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'updateOpenEnrollmentManager' keyword used to update OpenEnrollment Manager information for a carrier  in vista role
	 * 
	 * PreCondition : user should be in Basics Page 
	 * 
	 * PostCondition : user  will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateOpenEnrollmentManager(String labelName,String strValue){
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
}
