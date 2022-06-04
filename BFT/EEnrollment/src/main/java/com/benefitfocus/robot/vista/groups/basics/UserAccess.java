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
public class UserAccess {

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
	By lockoutMessage=By.xpath("//input[@id='lockoutMessage-ENGLISH']");
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'updateUserAccess' keyword used to update the User Access information 
	 * in vista role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in User Access page 
	 * 
	 * PostCondition : user  will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters & Example </b> 
	 * | labelName - Label Name |selectValue - Value in Drop down or Enter a value into Text Box |
	 * 
	 * Java file Name : UserAccess.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"labelName ","selectValue" })
	public void updateUserAccess(String strlabelName,String strValue){
		try	{
			common.updateInfo(strlabelName,strValue);
			scr.capturePageScreenshot();			
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			throw new CustomException("Exception in performing update the information in User Access page"
					+ e.getMessage());
		}
	}	
}
