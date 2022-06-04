package com.benefitfocus.robot.vista.groups.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeywords;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class BasicCommon {

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
	String loc = "//tr[@class='fieldListRow']//td[1]/..//..//span[text()='*']//..//..//label";
	String loc1 = "//tr[@class='fieldListRow']//td[1]//label";
	String otherLocation = "";
	String tagName = "";
	String attributeType = "";
	String currentlabelname = "";
	String labelLoc="";
	// select or enter a value based on the Label we have selected in the page
	private void actionPerform(String tagName, String locator, String type,
			String value) {
		if (tagName.equalsIgnoreCase("select")) {
			By loctor = By.xpath(locator);			
			performAction.select(loctor, value, "Select drop down element");
		}
		if ((tagName.equalsIgnoreCase("input"))
				&& (type.equalsIgnoreCase("text"))) {
			By loctor = By.xpath(locator);
			performAction.clearEnter(loctor, value, "Text Box");
		}
		if ((tagName.equalsIgnoreCase("input"))
				&& (type.equalsIgnoreCase("checkbox"))) {
			By loctor = By.xpath(locator);
			Boolean status = browser.getCurrentWebDriver().findElement(loctor).isSelected();
			if(value.toLowerCase().equalsIgnoreCase("check")){
				if(status == true){
					logger.info("check box is selected");
				}
				else{
					performAction.click(loctor, "check box");
				}
			}
			else if(value.toLowerCase().equalsIgnoreCase("uncheck")){
				if(status == true)
					performAction.click(loctor, "check box");
			}
			else{
				logger.info("check box is un selected");
			}
		}
		if ((tagName.equalsIgnoreCase("input"))
				&& (type.equalsIgnoreCase("hidden"))) {
			By loctor = By.xpath(locator+"/../div/textarea");
			performAction.clearEnter(loctor, value, "Text Box");
		}
		if (tagName.equalsIgnoreCase("textarea")) {
			By loctor = By.xpath(locator);			
			performAction.clearEnter(loctor, value, "Text Box");
		}		
	}
	public void updateInfo(String labelName, String strValue) {

		otherLocation="//div[contains(text(),'"+labelName+"')]/input";
		if(labelName.contains("'")){
			labelLoc = loc + "//label[text()=\"" + labelName + "\"]";
			System.out.println("Locator is... "+labelLoc);
		}
		else{
			labelLoc = loc + "[text()='" + labelName + "']";
		}
		String currentLocator = "";
		String objectLoc = "";
		try {
			logger.info("Label : "+ browser.getCurrentWebDriver()
					.findElement(By.xpath(labelLoc)).getText());
			currentlabelname = browser.getCurrentWebDriver()
					.findElement(By.xpath(labelLoc)).getText();
			objectLoc = labelLoc + "/../..//..//td[2]/*";
			logger.info("object Tag : "
					+ browser.getCurrentWebDriver()
					.findElement(By.xpath(objectLoc)).getTagName());
			tagName = browser.getCurrentWebDriver()
					.findElement(By.xpath(objectLoc)).getTagName();
			if (currentlabelname.equalsIgnoreCase(labelName)) {
				if (tagName.equalsIgnoreCase("input")) {
					currentLocator = labelLoc + "/../..//..//td[2]/input";
					attributeType = browser.getCurrentWebDriver()
							.findElement(By.xpath(objectLoc))
							.getAttribute("type");
					logger.info("Attribute Name :" + attributeType);
					actionPerform(tagName, currentLocator, attributeType,
							strValue);
				}
				if (tagName.equalsIgnoreCase("select")) {
					currentLocator = labelLoc + "/../..//..//td[2]/select";
					actionPerform(tagName, currentLocator, attributeType,
							strValue);
				}
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("No Such element in the page " + e.getMessage());
		}
		try {
			if(labelName.contains("'")){
				labelLoc = loc1+"[text()=\"" + labelName + "\"]";
				System.out.println("Locator is... "+labelLoc);
			}
			else{
				labelLoc = loc1 + "[text()='" + labelName + "']";
			}
			/*labelLoc = loc1 + "[text()='" + labelName + "']";*/
			currentLocator = "";
			logger.info("Label : "
					+ browser.getCurrentWebDriver()
					.findElement(By.xpath(labelLoc)).getText());
			currentlabelname = browser.getCurrentWebDriver()
					.findElement(By.xpath(labelLoc)).getText();
			objectLoc = labelLoc + "/../..//td[2]/*";
			logger.info("object Tag : "
					+ browser.getCurrentWebDriver()
					.findElement(By.xpath(objectLoc)).getTagName());
			tagName = browser.getCurrentWebDriver()
					.findElement(By.xpath(objectLoc)).getTagName();
			if (currentlabelname.equalsIgnoreCase(labelName)) {
				if (tagName.equalsIgnoreCase("input")) {
					currentLocator = labelLoc + "/../..//td[2]/input";
					attributeType = browser.getCurrentWebDriver()
							.findElement(By.xpath(objectLoc))
							.getAttribute("type");
					logger.info("Attribute Name :" + attributeType);
					actionPerform(tagName, currentLocator, attributeType,
							strValue);
				}
				if (tagName.equalsIgnoreCase("select")) {
					currentLocator = labelLoc + "/../..//td[2]/select";
					actionPerform(tagName, currentLocator, attributeType,
							strValue);
				}
				if (tagName.equalsIgnoreCase("textarea")) {
					currentLocator = labelLoc + "/../..//td[2]//textarea";
					actionPerform(tagName, currentLocator, attributeType,
							strValue);
				}
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("No Such element in the page " + e.getMessage());
		}
		try{  //added this block to support edit of FSA contribution amounts
			if(performAction.isElementPresent(By.xpath(otherLocation))){
				WebElement element = browser.getCurrentWebDriver().findElement(By.xpath(otherLocation));
				if(element.isDisplayed())
				{
					element.clear();
					element.sendKeys(strValue);
				}
			}
		} catch (Exception e) {
			throw new CustomException("No Such element in the page " + e.getMessage());

		}
	}
}
