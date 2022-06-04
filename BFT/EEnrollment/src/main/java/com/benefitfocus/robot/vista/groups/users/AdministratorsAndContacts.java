package com.benefitfocus.robot.vista.groups.users;

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
public class AdministratorsAndContacts {
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;

	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected Logging logger;
	@Autowired
	protected BasicCommon common;
	
	//Locators on the Page
	By adminMenu=By.cssSelector("[id^='contextualMenuAnchornavMenuAdmin']");
	By editAdminAccount=By.linkText("Edit Account");
	By save = By.xpath("//strong[text()='Save']");
	private void editAdminAccount()
	{
		performAction.click(editAdminAccount, "Edit Admin Acccont");
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'editAdministratorAccount' keyword is used to update the Administrator Account information when Editing
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in Edit Administrator Account Page 
	 * 
	 * PostCondition : user  will be redirected to the Administrator Account  page.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * </pre> 
	 * Java file Name : AdministratorsAndContacts.java
	 **/
	
	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void editAdministratorAccount(String labelName,String strValue){
	try	{	
		performAction.mouseOver(adminMenu, "contextualMenuAnchornavMenuAdmin");
		this.editAdminAccount();
		 common.updateInfo(labelName,strValue);
		//click on Save Button in Edit Administrator Account page
		performAction.click(save, "SaveButton in Edit Administrator Account Page");
		//click on Save Button in Task Change Notification Settings page
		//performAction.click(save, "SaveButton in Task Change Notification Settings page");
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing Edit in Administrator Account page "
					+ e.getMessage());
		}
	}	


}
