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
public class ToolsandReports {
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
	 * Description : 'updateToolsAndReports' keyword used to update Tools and Reports for a group in vista role
	 * 
	 * Role : Vista Role
     * 
	 * PreCondition : user should be in Basics Page 
	 * 
	 * PostCondition : user  will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters & Example </b> >
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 *  
	 * Java file Name :  ToolsandReports.java
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateToolsAndReports(String labelName,String strValue){
		try	{
			
			logger.info("Object created");
			common.updateInfo(labelName,strValue);			
			}catch (Exception e) {
				logger.info("Exception occured " + e.getMessage());
				scr.capturePageScreenshot();
				throw new CustomException("Exception in performing action in Tools and Reports page "
						+ e.getMessage());
			}
		}	

}
