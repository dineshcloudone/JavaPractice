package com.benefitfocus.robot.vista.groups.datesandrules;

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
public class EligibilityDateRules {
	
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
	
	By existingTestOffer = By.linkText("Test Offer");
	By editEligibilityDates = By.xpath("//tr[@class='dtr dtrEvenPassive']//a[@class='buttonInnerLink']");
	By deleteEligibilityDates = By.linkText("Delete Eligibility Date Rules");
	By createEligibilityDates = By.linkText("Create Eligibility Date Rules");
	By eligibilityRulesDefined = By.id("selectedTemplateKey");
	By next = By.linkText("Next");
	By yesButton = By.linkText("Yes");
	
	By waitPeriod = By.xpath("//input[@name='waitPeriodDuration']");
	By setEligibilityRule = By.xpath("(//input[@name='initialEligibilityRuleType'])[8]");
	
	By contributionWaitPeriodRule = By.xpath("//input[@name='contributionWaitPeriodRule']");
	
	By initialGracePeriod = By.id("initialEnrollmentGracePeriod[START_END_X_DAYS]");
	By gracePeriodEligibilityStartPeriod = By.id("startEligibilityPeriod");
	By gracePeriodEligibilityEndPeriod = By.id("gracePeriodDays");
	By save = By.linkText("Save");
	
	//Private Methods
	
	private void createEligibilityDates() {
		performAction.click(createEligibilityDates, "Click Create Eligibility Dates");
	}
	
	private void selectEligibilityRules() {
		performAction.select(eligibilityRulesDefined, "One value for all employees", "One value for all employees");
	}
	
	private void navigatePagesByNext() {
		performAction.click(next, "Click Next");
		if (performAction.isElementPresent(next)) {
			performAction.click(next, "Click Next");
		}
		if (performAction.isElementPresent(next)) {
			performAction.click(next, "Click Next");
		}
	}
	
	@ArgumentNames({ "strWaitPeriod", "strGraceStartPeriod", "strGraceEndPeriod"})
	private void setEligibilityRules(String strWaitPeriod, String strGraceStartPeriod, String strGraceEndPeriod) {
		performAction.enter(waitPeriod, strWaitPeriod, "Enter Wait Period");
		performAction.click(setEligibilityRule, "Set Rule On the 1st Pay period Check Date after the wait period is satisfied");
		performAction.click(contributionWaitPeriodRule, "Set Contribution Wait Period Rule");
		performAction.click(gracePeriodEligibilityStartPeriod, "Click Grace period Eligibility Start Period");
		performAction.enter(gracePeriodEligibilityStartPeriod, strGraceStartPeriod, "Enter Grace Period Eligibility Start");
		performAction.click(gracePeriodEligibilityEndPeriod, "Click Grace period Eligibility End Period");
		performAction.enter(gracePeriodEligibilityEndPeriod, strGraceEndPeriod, "Enter Grace Period Eligibility End");
		performAction.click(next, "Click Next");
	}
	
	//Robot Methods
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Change Eligibility Rules For Benefit Effective Date In Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Change Eligibility Rules Page Opened in Vista Role
	 * 
	 * PostCondition : Created Rule for Benefit Effective Date in Vista Role
	 * 
	 * Java File Name : EligibilityDateRules.java
	 * 
	 * | strWaitPeriod | strGraceStartPeriod | strGraceEndPeriod |
	 * | ex: 7 | ex: 7 | ex: 2 |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strWaitPeriod", "strGraceStartPeriod", "strGraceEndPeriod"})
	public void changeEligibilityDateRulesForMemberInVistaRole(String strWaitPeriod, String strGraceStartPeriod, String strGraceEndPeriod) {
		try {
			performAction.mouseOver(editEligibilityDates, "Click Edit Eligibility Dates");
			if (!performAction.isElementPresent(deleteEligibilityDates)) {
				this.createEligibilityDates();
				this.selectEligibilityRules();
				this.navigatePagesByNext();
				this.setEligibilityRules(strWaitPeriod, strGraceStartPeriod, strGraceEndPeriod);
				performAction.click(save,"Save Button");
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while creating Eligibility Rules"
					+ e.getMessage());
		}
	}
}
