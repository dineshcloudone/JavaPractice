package com.benefitfocus.robot.hradmin;

import java.util.List;

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
public class BenefitContributionAmountPage {

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
	
	//Selenium Locators
	By flexContributionAmount = By.id("contributionAmount");
	By nextButton = By.linkText("Next");

    //Payroll
    By contributionType = By.id("contributionType");
    By payPerContributionAmount = By.id("annualAmount");
    By addButton = By.linkText("Add");
    By updateButton = By.linkText("Update");

	// Enter Coverage Amount for Flex Amount
	private void enterFlexContributionAmount(String strcontributionAmount) {
		performAction.waitUntilElementPresent(flexContributionAmount);
		performAction.clearEnter(flexContributionAmount, strcontributionAmount,
				"Coverage Amount");
	}

	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 *  
	 * Description   : 'Edit Coverage Amount For Flex Benefit' keyword or method is used to set the Contribution Amount Plan for the FSA benefit
	 * 
	 * PreCondition  : Coverage Amount page should be opened of FSA/DCFSA benefit
	 * 
	 * PostCondition : Coverage Amount will be entered into available text field and click on Next button, it navigates to next page as per the configuration
	 * 
	 * <b>Parameters & Example </b> 
	 * 
	 * | strFlexContributionAmount - String argument | 
	 * | You can contribute between $1.00 and $2,550.00 per plan year (Range of Amount depends on the Group)|
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "Flex Coverage Amount" })
	public void setContributionAmountForFlexBenefit(
			String strFlexContributionAmount) {
		try {
			this.enterFlexContributionAmount(strFlexContributionAmount);
			performAction.click(nextButton, "Next button.");
		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out
					.println("Exception occured while entering contribution amount of FSA plan"
							+ e.getMessage());
			throw new CustomException(
					"Exception occured while entering contribution amount of FSA plan"
							+ e.getMessage());
		}

	}

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description   : keyword or method is used to select the contribution type
     *
     * PreCondition  : Coverage Amount page should be opened of FSA/DCFSA benefit
     *
     * PostCondition : Coverage Amount type will be selected from the available combo list and click on Next button, it navigates to next page as per the configuration
     *
     * <b>Parameters & Example </b>
     *
     * |strContributionType - String argument |strContributionAmount - String argument |
     * | Ongoing Contribution | 100 |
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({ "strContributionType", " strContributionAmount" })
    public void setContributionAmountForHSAPlan(
            String strContributionType, String strContributionAmount) {
        try {
            performAction.select(contributionType, strContributionType, "Contribution Amount Type");
            Thread.sleep(2000);
            performAction.clearEnter(payPerContributionAmount, strContributionAmount, "Ongoing Contribution Amount");

            performAction.click(addButton, "Add button");
            Thread.sleep(3000);

        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while selecting contribution amount type"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while selecting contribution amount type"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description   : keyword or method is used to edit the amount for contribution type
     *
     * PreCondition  : Coverage Amount page should be opened
     *
     * PostCondition : Coverage Amount type will be edited and click on Update button, it navigates to next page as per the configuration
     *
     * <b>Parameters & Example </b>
     *
     * | strFlexContributionType - String argument |
     * |strFlexContributionAmount - String argument |
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({ "Coverage Amount Type", "Ongoing Contribution Amount" })
    public void editContributionAmountForHSAPlan(
            String strContributionType, String strOngoingContributionAmount) {
        try {

            performAction
                    .click(By.xpath("//div[contains(text(),'" + strContributionType
                            + "')]/preceding::td[1]/following::td[4]//a[text()='Edit']"), "Click on Edit Contribution Amount");
            utils.sleep(2000);
            performAction.clearEnter(payPerContributionAmount, strOngoingContributionAmount, "Ongoing Contribution Amount");

            performAction.click(updateButton, "Update button");

        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while selecting contribution amount type"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while selecting contribution amount type"
                            + e.getMessage());
        }

    }

}
