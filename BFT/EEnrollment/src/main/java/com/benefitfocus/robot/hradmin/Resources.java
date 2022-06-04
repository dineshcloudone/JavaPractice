package com.benefitfocus.robot.hradmin;

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
public class Resources {
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected ManageBrowser browser;  
	@Autowired
	protected Logging logger;
	
	// Locators in the page
	By resource=By.xpath("//h1[text()='Resources']");
	By training=By.linkText("Training");
	By featuredVideos=By.linkText("Featured Videos");
	
	private void clickTrainingItem(String strItem)
	{
		By locator=By.linkText(strItem);
		performAction.click(locator, "Training Item");
		performAction.selectLatestWindow(browser.getCurrentWebDriver().getTitle());
		browser.getCurrentWebDriver().close();
	}
	private void clickstrFeaturedVideoItem(String strVideoItem)
	{
		By locator=By.linkText(strVideoItem);
		performAction.click(locator, "Training Item");	
	
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description   : 'clickTrainingProgramInHRAdmin' keyword used to click on Training Item in HR Admin Resource page
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition  : User should be Navigate to Resource page HR Admin
	 * 
	 * PostCondition : Able to open Training Item in new Window
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strTrainingProgram | 
	 * | Benefit Administrator-User Guide-Winter 2015 | 
	 * 
	 * Java file Name : Resources.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strTrainingProgram"})
	public void clickTrainingProgramInHRAdmin(String strTrainingProgram)
	{
		try{
			String pageTitle=browser.getCurrentWebDriver().getTitle();
			this.clickTrainingItem(strTrainingProgram);
			scr.capturePageScreenshot();
			performAction.selectLatestWindow(pageTitle);
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in click on Training Item HR Admin Resource page"
					+ e.getMessage());
			throw new CustomException(
					"Exception in click on Training Item HR Admin Resource page"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description   : 'clickFeaturedVideosInHRAdmin' keyword used to click on Featured Videos Item in HR Admin Resource page
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition  : User should be Navigate to Resource page HR Admin
	 * 
	 * PostCondition : Able to open Featured Video Item in new Window
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strFeaturedVideo | 
	 * | Defined Contribution or Generic Brands | 
	 * 
	 * Java file Name : Resources.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strFeaturedVideo"})
	public void clickFeaturedVideosInHRAdmin(String strFeaturedVideo)
	{
		try{
			performAction.click(featuredVideos, "FeaturedVideos");
			String pageTitle=browser.getCurrentWebDriver().getTitle();
			this.clickstrFeaturedVideoItem(strFeaturedVideo);
			scr.capturePageScreenshot();
			performAction.selectLatestWindow(pageTitle);
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in clicking Featured Videos Item in HR Admin Resource page"
					+ e.getMessage());
			throw new CustomException(
					"Exception in clicking Featured Videos Item in HR Admin Resource page"
							+ e.getMessage());
		}
	}
}
