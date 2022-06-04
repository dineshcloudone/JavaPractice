package com.benefitfocus.robot.member;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;

@RobotKeywords
public class Navigations {
	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Logging logger;
	
	@Autowired
	protected ManageBrowser browser;
	
	public enum MainMenus {
		HOME, DEPENDENTS, BENEFITS, PROFILE, RESOURCECENTER, LOGININFORMATION, MEDICARE,
		VIEWHSACONTRIBUTION, LIFECHANGE, LANGUAGEPREFERENCES, EMPLOYEESUMMARYREPORT, LEARNINGCENTER, EESUMMARYREPORT, EMPLOYEEDETAILREPORT, MYDOCUMENTS
	};

	By home = By.xpath("//span[text()='Home']");
	By dependents = By.xpath("//span[text()='Dependents']");
	By benefitDetails = By.xpath("//span[text()='Benefits']");
	By profile = By.xpath("//span[text()='Profile']");
	By loginInformaiton = By.xpath("//a[text()='Login Information']");
	By HSAContribution = By.xpath("//a[text()='View HSA Contribution']");
	By LifeChange = By.xpath("//a[text()='Life Change']");
	By resourceCenter = By.xpath("//a[text()='Resource Center']");
	By languagePreferences = By.xpath("//span[text()='Language Preferences']");
	By profileLink = By.cssSelector("a[href*='method=startSection']");
	By newProfileLink =  By.linkText("Profile");
	By employeeSummaryReport=By.linkText("Employee Summary Report");
	By learningCenter = By.xpath("//a[text()='Learning Center']");
	By medicare = By.linkText("Medicare");
	By myDocuments = By.linkText("My Documents");
	By employeeDetailReport = By.linkText("Employee Detail Report");
	By employeeSummaryReportLink=By.linkText("Employee Summary Report");

	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : navigateToNewMemberMenu keyword or method is used to perform navigation in New Member Role Home Page
     * 
     * Role: New Member role
     * 
      * PreCondition : Member should be in New Member role Home Page . 
     * 
      * PostCondition : member will reloaded to the required page.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | strSideMenu |
      * | HOME, Benefits , Login Information, Resource Center  |   
      * 
      * JavaFileName : Navigations.java
      * </pre> 
      **/
	
	@RobotKeyword
	@ArgumentNames({ "strSideMenu" })
	public void navigateToNewMemberMenu(String strSideMenu) {

		try {

			MainMenus sdf = MainMenus.valueOf(strSideMenu.toUpperCase().trim());

			switch (sdf) {

			case HOME:
				performAction.click(home, "HOME link");
				break;
			case RESOURCECENTER:
				performAction.click(resourceCenter, "RESOURCE CENTER link");
				break;
		
			case DEPENDENTS:
				performAction.waitUntilElementPresent(dependents);
				performAction.jsclick(dependents, "Dependents link");
				break;
				
			case LANGUAGEPREFERENCES:
				performAction.waitUntilElementPresent(languagePreferences);
				performAction.jsclick(languagePreferences, "Language Preferences link");
				break;	

			case BENEFITS:
				WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 60);
				wait.until(ExpectedConditions.visibilityOfElementLocated(benefitDetails));
				wait.until(ExpectedConditions.elementToBeClickable(benefitDetails));
				performAction.waitUntilElementPresent(benefitDetails);
				performAction.click(benefitDetails, "Benefits link");		
				break;
			case PROFILE:
				if(performAction.isElementPresent(profile)){
				performAction.click(profile,"Profile link");
				break;
				}
				if(performAction.isElementPresent(newProfileLink)){
					performAction.click(newProfileLink, "Profile tab link");
					break;
				}
			
			case LOGININFORMATION:
				performAction.click(loginInformaiton, "Login Information link");
				break;
			
			case VIEWHSACONTRIBUTION:
				performAction.click(HSAContribution, "View HSA COntribution link");
				break;
				
			case LIFECHANGE:
				performAction.click(LifeChange, "Life Change link");
				break;
				
			case MEDICARE:
				performAction.waitUntilElementPresent(medicare);
				performAction.click(medicare, "Medicare Link");
				break;
			case EMPLOYEEDETAILREPORT:
				performAction.waitUntilElementPresent(employeeDetailReport);
				performAction.click(employeeDetailReport, "Employee Detail Report Link");
				break;
				
			case EMPLOYEESUMMARYREPORT:
				performAction.click(employeeSummaryReport, "Employee Summary Report");
				break;
			case LEARNINGCENTER:
				performAction.click(learningCenter, "learning Center");
				break;
			case EESUMMARYREPORT:
				performAction.click(employeeSummaryReportLink, "Employee Summary Report link");
				break;
			case MYDOCUMENTS:
				performAction.click(myDocuments, "My Documents link");
				break;
				
				
			}

		} catch (Exception e) {
			logger.info("Exception in performing HR admin menu navigations "
					+ e.getMessage());
			throw new CustomException(
					"Exception in performing HR admin menu navigations "
							+ e.getMessage());
		}
	}
}
