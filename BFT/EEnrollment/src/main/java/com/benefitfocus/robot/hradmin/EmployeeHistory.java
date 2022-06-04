package com.benefitfocus.robot.hradmin;

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
public class EmployeeHistory {
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
	

	By employeeHistoryMsg=By.xpath("//h2[text()='Employee History']");
	By employeeTimelineMsg=By.xpath("//h2[text()='Employee Timeline']");
	By employeeHistoryQuestion=By.xpath("//h3[contains(text(),'How do you want to view this employee')]");
	By pointInTime=By.xpath("//label[@for='employeeHistoryViewMode-0']");
	By timelineSummaryOfEvent=By.xpath("//label[contains(text(),'Timeline -- summary of events')]");
	By timelineStartDate=By.id("timelineStartDate");
	By pointinTimeDate=By.id("pit1Date");	
	By selectDateForTimeline=By.id("pit1Date");
	By nextButton=By.xpath("//*[text()='Next']");
	By lastName=By.xpath("//h1[@class='mrs media-heading inline-block']");
	
	String date="";
	private void selectPointInTime()
	{
		performAction.waitUntilElementPresent(pointInTime);		
		performAction.click(pointInTime, "Point in Time option");
	}
	private void selectTimelineStartDate()
	{
		performAction.waitUntilElementPresent(timelineSummaryOfEvent);
		performAction.jsclick(timelineSummaryOfEvent, "Timelime Summary Of Event option");
	}
	private void setDateOfPointInTime(String date)
	{
		performAction.clearEnter(pointinTimeDate, date, "Point in Time Date");
	}
	private void setDateOfTimeline(String date)
	{
		performAction.clearEnter(timelineStartDate, date, "Timeline Start Date");
	}
	private void verifyEmployeeHistory(String strlastName)
	{
		if(performAction.isElementPresent(employeeHistoryMsg))
		{
		performAction.verify(employeeHistoryMsg, "Employee History", "Employee History Message");
		performAction.verify(lastName, strlastName, "Employee Last Name");
		}
		if(performAction.isElementPresent(employeeTimelineMsg))
		{
		 performAction.verify(employeeTimelineMsg, "Employee Timeline", "Employee Timeline Message");
		 performAction.verify(lastName, strlastName, "Employee Last Name");
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : This keyword used to select an option from Employee History in HR Role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : user should be in Employee page in Hr Role 
	 * 
	 * PostCondition : user  will be redirected to Changed Categories Page
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | empHistoryType - Point in Time or Timeline Summary |strEmpLastName - Employee Last Name |
	 * 
	 * Java file Name : EmployeeHistory.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"empHistoryType" , "strEmpLastName" })
	public void selectEmployeeHistoryOption(String empHistoryType, String strEmpLastName )
	{		
		if (strEmpLastName.startsWith("HMV")) {
			strEmpLastName = utils.getValue(strEmpLastName);
		} 		
		if (empHistoryType.startsWith("td:"))
			empHistoryType = empHistoryType.substring(3);
		Object object = null;
		JSONObject fields = ReadJsonTestData.getTestData(empHistoryType.toLowerCase());
				
		try{
			if(browser.getCurrentWebDriver().getPageSource().contains("How do you want to view this employee's history?"))
			{					
				object = fields.get("whatdoyouwant");
				if (object != null) {			
					if(object.toString().equalsIgnoreCase("Point in time"))
					{
						Thread.sleep(2000);
						this.selectPointInTime();
						object = fields.get("effectivedate");						
						if (object != null) {
							this.setDateOfPointInTime(object.toString());							
							browser.hMap.put("effectivedateofchange", object.toString());
							Thread.sleep(2000);	
							performAction.click(nextButton, "Next Button");
							Thread.sleep(2000);	
							date=browser.getCurrentWebDriver().findElement(By.id("pit1Date")).getAttribute("value");
							performAction.verify(pointinTimeDate, date, "Date in Point in Time");
						}
					}
					if(object.toString().equalsIgnoreCase("Timeline Summary"))
					{
						this.selectTimelineStartDate();
						object = fields.get("effectivedate");						
						if (object != null) {
							this.setDateOfTimeline(object.toString());
							browser.hMap.put("effectivedateofchange", object.toString());							
							performAction.click(nextButton, "Next Button");
							Thread.sleep(2000);	
							date=browser.getCurrentWebDriver().findElement(By.id("pit1Date")).getAttribute("value");
							performAction.verify(pointinTimeDate, date, "Date in Time line Summery");
						}
					}
				}								
				verifyEmployeeHistory(strEmpLastName);								
			}
		} catch (Exception e) {
			logger.info("Exception in selecting option"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in selecting option"
							+ e.getMessage());				
		}
	}	
	
}
