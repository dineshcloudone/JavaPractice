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
public class BenefitSnapshotPage {

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

	//  Selenium locators
	By saveAndGoToBenefitsButton = By.linkText("Save and Go to Benefits");
	By contributionAmountEditButton = By
			.xpath("//span[text()='Contribution Amount']/../..//a[text()='Edit']");
	By effectiveDateEditButton = By
			.xpath("//span[text()='Effective Date']/../..//a[text()='Edit']");

	// Click on SaveandGoToBenefits button
	private void clickSaveAndGoToBenefits() {
		performAction.waitUntilElementPresent(saveAndGoToBenefitsButton);
		performAction.click(saveAndGoToBenefitsButton,
				"Save and Go To Benefits Button");
	}

	// Click Edit button for the selected plan
	private void clickSelectedPlanEditButton(String strPlanName) {
		if(performAction.isElementPresent(By.xpath("//div[contains(text(),'"+strPlanName+"')]/..//a[text()='Edit']"))){
			performAction.click(By.xpath("//div[contains(text(),'"+strPlanName+"')]/..//a[text()='Edit']"), "Click on Edit Plan");
		}else{
			String strEditButton = "//span[text()='" + strPlanName
					+ "']/../..//a[text()='Edit']";
			System.out.println("Plan Edit Button Path is" + strEditButton);
			By planEditButton = By.xpath(strEditButton);
			performAction.waitUntilElementPresent(planEditButton);
			performAction.click(planEditButton, "Edit Button For Selected Plan");
		}
		
	}

	// Click on Edit Button Of CoverageAmount Of Flex benefit
	private void clickContributionAmountEditButton() {

		performAction.waitUntilElementPresent(contributionAmountEditButton);
		performAction.click(contributionAmountEditButton,
				"Edit Button For Coverage Amount");
	}

	// Click on EffectiveDate Edit Button
	private void clickEffectiveDateEditButton() {
		performAction.waitUntilElementPresent(effectiveDateEditButton);
		performAction.click(effectiveDateEditButton,
				"Edit Button For Effective Date");
	}

	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 *  
	 * Description   : 'Save And Goto Benefits Page' keyword or method is used to click on Save and Goto benefits button at the benefit snapshot page in HR Admin Role
	 * 
	 * PreCondition  : Benefits Snapshot Page should be opened
	 * 
	 * PostCondition : It will Click on 'Save And Goto benefits Page' button and navigates to Benefit Details tab
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	public void saveAndGoToBenefitEnrollmentPage() {
		try {

			this.clickSaveAndGoToBenefits();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out
					.println("Exception occured while clicking Save And Goto Benefits button in HR Admin Role"
							+ e.getMessage());
			throw new CustomException(
					"Exception occured while clicking Save And Goto Benefits button in HR Admin Role "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 *  
	 * Description   : 'Edit Current Plan' keyword or method is used to Edit the Current Plan for the selected benefit
	 * 
	 * PreCondition  : Required Benefit/Offer Edit Page should be opened
	 * 
	 * PostCondition : Navigates to plan selection page
	 * 
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPlanName - String argument | 
	 * | Medical / HSA / Dental / Vision (Plans availability depends on Group)|
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "Plan Name" })
	public void editCurrentPlan(String strPlanName) {
		try {
			this.clickSelectedPlanEditButton(strPlanName);
		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out
					.println("Exception occured while clicking on Edit for the current plan"
							+ e.getMessage());
			throw new CustomException(
					"Exception occured while clicking on Edit for the current plan"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 *  
	 * Description   : 'Edit Contribution Amount For Flex Benefit' keyword or method is used to click on Edit button for the Flex benefit
	 * 
	 * PreCondition  : Benefit Details page should be opened
	 * 
	 * PostCondition : Page will navigate to contribution amount page
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void editContributionAmountForFSA() {
		try {
			this.clickContributionAmountEditButton();
			performAction.verifyMessage("Contribution Amount");
		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out
					.println("Exception occured while editing contribution amount of FSA Benefit"
							+ e.getMessage());
			throw new CustomException(
					"Exception occured while editing contribution amount of FSA Benefit"
							+ e.getMessage());
		}

	}

	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 *  
	 * Description   : 'Edit Effective Date' keyword or method is used to click on Edit button Of EffectiveDate for the enrolled benefit
	 * 
	 * PreCondition  : Benefit Edit details page should be opened for the selected benefit
	 * 
	 * PostCondition : It will click on Edit button of Effective date
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void editEffectiveDate() {
		try {
			this.clickEffectiveDateEditButton();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out
					.println("Exception occured while clicking Effective Date edit button"
							+ e.getMessage());
			throw new CustomException(
					"Exception occured while clicking Effective Date edit button"
							+ e.getMessage());
		}

	}
}
