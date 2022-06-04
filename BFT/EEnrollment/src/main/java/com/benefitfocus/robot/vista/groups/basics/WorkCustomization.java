package com.benefitfocus.robot.vista.groups.basics;

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
public class WorkCustomization {
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
	
	
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'updateWorkCustomization' keyword used to update work Customization information for a group  in vista role
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
	 * Java file Name :  WorkCustomization.java
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateWorkCustomization(String strlabelName,String strValue){								
		try	{
			common.updateInfo(strlabelName,strValue);
			scr.capturePageScreenshot();			
		 }catch (Exception e) {
				logger.info("Exception occured " + e.getMessage());
				scr.capturePageScreenshot();
				throw new CustomException("Exception in performing action in Work Customization page "
						+ e.getMessage());
			}
		}	

}
