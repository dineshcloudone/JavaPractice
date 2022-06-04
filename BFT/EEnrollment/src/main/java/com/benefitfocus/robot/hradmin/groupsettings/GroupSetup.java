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
public class GroupSetup {
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
	By groupSetup=By.linkText("Group setup");
	
	private void clickGroupSetup()
	{	if(performAction.isElementPresent(groupSetup))
		{
		 performAction.click(groupSetup, "Group Setup");
		 logger.info("Group Setup present in the page");
		}
	}

	private void verifyGroupInformation(String strHeading)
	{
		String strLocator="//div[@class='regionHeader']/h2[text()='"+strHeading+"']";
		performAction.verify(strLocator, strHeading, strHeading);
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'verifyGroupSetup' keyword used to verify the group information in HR Admin role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : user should be in HR Admin Group Setu page
	 *  
	 * PostCondition : user  will verify the group information in HR Admin role
	 * 
	 * <b>Parameters & Example </b> 
	 * | groupsetupinfo | 
	 * 
	 * | verifyGroupSetup - is used to get the data set from the Json File "GroupSetup.json". |
	 * 
	 * Java file Name :  GroupSetup.java
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "strGroupInformation" })
	public void verifyGroupSetup(String strGroupInformation)
	{
		try
		{
		 this.clickGroupSetup();
		 scr.capturePageScreenshot();
		 if (strGroupInformation.startsWith("td:"))
			 strGroupInformation = strGroupInformation
				.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData
			.getTestData(strGroupInformation.toLowerCase());
			logger.info("fields = " + fields.toJSONString());

			object = fields.get("identification");
			if (object != null) {				
				this.verifyGroupInformation(object.toString());
			}
			object = fields.get("groupcustomization");
			if (object != null) {				
				this.verifyGroupInformation(object.toString());
			}
			object = fields.get("categories");
			if (object != null) {				
				this.verifyGroupInformation(object.toString());
			}
			object = fields.get("cobrasetup");
			if (object != null) {				
				this.verifyGroupInformation(object.toString());
			}
			object = fields.get("personalcustomization");
			if (object != null) {				
				this.verifyGroupInformation(object.toString());
			}
			object = fields.get("workcustomization");
			if (object != null) {				
				this.verifyGroupInformation(object.toString());
			}
			

		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing action in Group Setup page "
							+ e.getMessage());
		}		

	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'downloadFile' keyword used to download a File
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : Download link should be displayed in the page
	 *  
	 * PostCondition : Download the File
	 * 
	 * <b>Parameters & Example </b> 
	 * NA
	 * 
	 * Java file Name : GroupSetup.java
	 * </pre>
	 **/
	@RobotKeyword
	public void downloadFile() 
	{	
		try {					
			String[] commands = new String[]{};						
			commands = new String[]{"Q:\\KB\\HrInTouch\\Data\\DownloadFile.exe"}; //location of the autoit executable					
			Thread.sleep(5000);				
			logger.info("Command :: "+commands);
			Runtime.getRuntime().exec(commands);
			Thread.sleep(10000);			
			scr.capturePageScreenshot();
			Thread.sleep(10000);						
			return;
		} catch (Throwable e) {			
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();			
			return;
		}				
	}
	

}
