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
public class DeductionPeriodPage {

	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Screenshot scr;

	By deductionPeriodsRemaining = By.id("newPeriodsRemaining");
	By nextButton = By.xpath("//strong[text()='Next']");
	By flexibleSpendingDedutionPeriods = By.id("newPeriodsRemaining");

	private void enterRemainingDeductionPeriodsForTheYear(
			String strDeductionPeriodsRemaining) {
		performAction.clearEnter(deductionPeriodsRemaining,
				strDeductionPeriodsRemaining,
				"Remaining Periods In the year For Flex benefit");
		performAction.click(nextButton, "Next bUtton in contribution");
	}

	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 *  
	 * Description   : 'Enter Deduction Periods Remaining In The Year' keyword or method is used to enter the number of deduction periods remaining for the Flex benefit
	 * 
	 * PreCondition  : Coverage Amount page should be opened for FSA/DCFSA benefit
	 * 
	 * PostCondition : Coverage AMount will be entered and click on Next button, it navigates to next page as per the configuration
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void enterDeductionPeriodsRemainingInTheYear(
			String strDeductionPeriodsRemaining) {
		this.enterRemainingDeductionPeriodsForTheYear(strDeductionPeriodsRemaining);
	}
	
	/**
	 * <pre>
	 * Author  : Teja P
	 * source : DeductionPeriodPage
	 *  
	 * Description : This Keyword is used to enter Flexible spending deduction periods in HR-Admin Role
	 * and clicks Next button
	 * 
	 * PreCondition : Deduction period textbox should be displayed and enabled
	 * 
	 * PostCondition : After entering deduction period and clicking Next button corresponding page should be displayed as per the 
	 * configurations
	 *     
	 * <b>Parameters & Example </b> 
	 * 
	 * <b>strDeductionPeriods </b>
	 * |3 |4 etc..|
	 * 
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strDeductionPeriods" })
	public void enterFlexibleSpendingDeductionPeriods(String strDeductionPeriods) {

		try {

			performAction.enter(flexibleSpendingDedutionPeriods,
					strDeductionPeriods,
					"Flexible Spending Deduciton period textbox");

			performAction.click(nextButton, "Next Button in Medicare page");

		} catch (Exception e) {

			throw new CustomException(
					"Exception occured while entering Flexible Spending Deduction Periods"
							+ " " + e.getMessage());
		}

	}

}
