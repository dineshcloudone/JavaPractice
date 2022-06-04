package com.benefitfocus.robot.hradmin.contentmanager;

import org.openqa.selenium.By;
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
public class WidgetManager 
{
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
	
	//Locators on the Page
	By widgetManager=By.linkText("Widget Manager");
	
	//Locators in Widget Manager Page
	By createNewWidget=By.linkText("Create New Widget");

	private void clickWidgetManager()
	{
		performAction.click(widgetManager, "Widget Manager");
	}

	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'createNewWidget' keyword used to Create New Widget hr admin role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : user should be in Content Manager Page
	 *  
	 * PostCondition : user  will be redirected to Links Widget Manager page.
	 * 
     * Java file Name :  WidgetManager.java
	 *   
	 * </pre> 
	 **/
	
	@RobotKeyword	
	public void createNewWidget(){
		try	{
			this.clickWidgetManager();
			performAction.click(createNewWidget, "Create New Widget");
			scr.capturePageScreenshot();
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while creating new Widget"
					+ e.getMessage());
		}
	}
}
