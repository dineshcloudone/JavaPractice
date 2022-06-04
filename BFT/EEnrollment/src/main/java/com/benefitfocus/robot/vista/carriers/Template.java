package com.benefitfocus.robot.vista.carriers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class Template {
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
	
	//Locators in Template search
	By templateGroupName=By.id("searchCriteria");
	By searchButton=By.xpath("//a[contains(@href,'templateGroupSearchForm.submit()')]");
	
	// Enter Template Group Name
	private void enterTemplateGroupName(String strTemplateGroupName){
		if(strTemplateGroupName.startsWith("td:")){
			strTemplateGroupName=utils.getValue(strTemplateGroupName);
		}
		performAction.enter(templateGroupName, strTemplateGroupName, "Enter Template Group Name");
	}
	// Click on Search Button
	private void clickSearchButton(){
		performAction.click(searchButton, "Click on Search Button");
	}
	
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : VISTA Role 
	 *
	 * Description   : "Perform Template Search In Vista Role" keyword is used to search for a template From Carriers >> Template Search
	 *
	 * PreCondition  : Navigate to Template Search page
	 *
	 * PostCondition : Able to search template
	 * <pre>
	 * <b>Parameters :</b>
	 * | strTemplateGroupName |
	 * | Aasonn Group Template |
	 * 
	 * Java File Name : com.benefitfocus.robot.vista.carriers >> Template.java
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strTemplateGroupName"})
	public void performTemplateSearchInVistaRole(String strTemplateGroupName) {
		try {
			this.enterTemplateGroupName(strTemplateGroupName);
			this.clickSearchButton();
			Thread.sleep(2000);
			performAction.verifyMessage(strTemplateGroupName);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception in searching Template");
			scr.capturePageScreenshot();
			throw new CustomException("Exception in searching Template"
					+ e.getMessage());
		}
	}

	
}
