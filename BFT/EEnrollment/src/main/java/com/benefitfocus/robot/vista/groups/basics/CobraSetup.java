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
public class CobraSetup {

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
	By cobrasetup=By.linkText("COBRA Setup");
	By primaryRegion = By.xpath("//table[@class='primaryRegion']//h1]");
	
	// Variables for the Public methods
	String loc="//tr[@class='fieldListRow']//td[1]//label";
	String tagName="";
	String attributeType="";
	String currentlabelname="";
	String labelLoc ="";
	String currentLocator="";
	String objectLoc ="";
	String Msg="";
	//select or enter a value based on the Label we have selected in the page
	
	
	private void actionPerform(String tagName,String locator,String type,String value)
	{
		if(tagName.equalsIgnoreCase("select"))
		{
			By loctor=By.xpath(locator);
			System.out.println(loctor);
			performAction.select(loctor, value, "Select drop down element");
		}
		if((tagName.equalsIgnoreCase("input"))&&(type.equalsIgnoreCase("text")))
		{
			By loctor=By.xpath(locator);
			performAction.clearEnter(loctor, value,"Text Box");
		}
		if((tagName.equalsIgnoreCase("input"))&&(type.equalsIgnoreCase("checkbox")))
		{

		}
		if(tagName.equalsIgnoreCase("textarea"))
		{
			By loctor=By.xpath(locator);
			System.out.println(loctor);
			performAction.clearEnter(loctor, value,"Text Box");
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'updateCobraSetup' keyword used to update the Configurations in vista role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in Categories Page 
	 * 
	 * PostCondition : user  will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * 
     * Java file Name : CobraSetup.java
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateCobraSetup(String labelName,String strValue){
		try	{

			if(performAction.isElementPresent(primaryRegion))
			{
				Msg=browser.getCurrentWebDriver().findElement(primaryRegion).getText();
				logger.info(Msg+" Page is loaded");	
			}													
			labelLoc = loc+"[text()='"+labelName+"']";
			currentlabelname=browser.getCurrentWebDriver().findElement(By.xpath(labelLoc)).getText();
			objectLoc = labelLoc+"/../..//..//td[2]/*";			
			tagName=browser.getCurrentWebDriver().findElement(By.xpath(objectLoc)).getTagName();
			if(currentlabelname.equalsIgnoreCase(labelName))
			{
				if(tagName.equalsIgnoreCase("input")){	
					currentLocator=	labelLoc+"/..//..//td[2]/input";		
					attributeType=browser.getCurrentWebDriver().findElement(By.xpath(objectLoc)).getAttribute("type");
					logger.info("Attribute Name :"+attributeType);
					actionPerform(tagName,currentLocator,attributeType,strValue);
				}
				if(tagName.equalsIgnoreCase("select")){
					currentLocator=	labelLoc+"/..//..//td[2]/select";
					actionPerform(tagName,currentLocator,attributeType,strValue);
				}
			}
			scr.capturePageScreenshot();
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in COBRA Setup Page "
					+ e.getMessage());
		}
	}		

}
