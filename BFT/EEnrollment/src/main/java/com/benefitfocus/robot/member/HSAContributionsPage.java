package com.benefitfocus.robot.member;

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
public class HSAContributionsPage {
	
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

	// Locators
	By initialContributionButton = By
			.xpath("//input[@value='EE_INITIAL']");
	By initialContributionCheckBox = By
			.xpath("//input[@value='This is an initial contribution']");
	By hsaContributionAmount = By
			.xpath("//span[contains(text(),'HSA abstract')]");
	By scheduleContributionAmount = By
			.xpath("//span[contains(text(),'Schedule contribution')]");
	By contributionAmount = By.id("contributionAmount");
	By addContributionButton = By
			.xpath("//a[@href='/form/submit']");
	
	By addContributionButtonNew= By.xpath("//*[@id='contribFormView']/section/div[5]/a[contains(text(),' Add contribution ')]");
			
	By selectStartDate = By.id("startDate");
	By selectEndDate = By.id("endDate");
	By selectScheduleDate = By.id("scheduledStartDate");
	By nextButton = By.xpath("//button[contains(text(),'Next')]");
	By hsacustodialAgreement = By
			.xpath("//input[@id='customAnswer0-nativeHtmlElement']");
	By hsaelectronicDiscloure = By
			.xpath("//input[@id='customAnswer1-nativeHtmlElement']");
	By hsaRepresentiveAgreement = By
			.xpath("//input[@id='customAnswer0-nativeHtmlElement']");
	By addContribution=By.linkText("Add Contribution");
    By ongoingContribution=By.xpath("//input[@value='EE_ONGOING']");
    By editContribution = By.linkText("Edit contribution");
    By actionsButton = By.xpath("//button[contains(text(),'Actions ')]");
    By editButton = By.xpath("//a[contains(text(),'Edit')]");
    By updateButton = By.id("updateRecord");
    By changeContibutionStartAndEndDates = By.id("start-and-end-dates-header");
    By repeatingRadioButton = By.xpath("//input[@name='contributionType'][@value='EE_ONGOING']");
    By oneTimeRadioButton = By.xpath("//input[@name='contributionType'][@value='EE_SCHEDULED']");
    By scheduleContributionchkbox=By.xpath("//input[@value='EE_SCHEDULED']");
    By initialContributionchkbox=By.xpath("//input[@type='checkbox' and @value='This is an initial contribution']");

	// select Initial Contribution
	private void selectInitialContribution() {
		performAction.click(initialContributionButton,
				"Initial Contribution Radio button");
	}

	// select HSA abstract
	private void selectHsaAbstract() {
		performAction.click(hsaContributionAmount,
				"HSA Contribution Radio Button");
	}

	// Enter Contribution Amount
	private void enterContributionAmount(String StrAmount) { 
		performAction.waitUntilElementPresent(contributionAmount);
		performAction.click(contributionAmount, "contributionAmount");
		performAction.clearEnter(contributionAmount, StrAmount, "Clear and Enter  change amount");
	}

	// Add Contribution Amount
	private void addContributionAmount() throws InterruptedException {
		performAction.waitUntilElementPresent(addContributionButtonNew);
		performAction.jsclick(addContributionButtonNew, "Add Contibution Button ");
		performAction.jsclick(addContributionButtonNew, "Add Contibution Button ");
	}

	
	// select Initial Contribution checkbox for scheduled contribution
	private void selectInitialContributioncheckbox() {
		performAction.click(initialContributionchkbox,
				"Initial Contribution check box");
	}
	
	// select Start date
	private void selectStartDate(String strStartDate) {
		if(strStartDate.startsWith("d:")){
			strStartDate=strStartDate.substring(2);
		}
		performAction.enter(selectStartDate,utils.getDate(strStartDate) , "Select Start Date");
	}

	// select End Date
	private void selectEndDate(String strEndDate) {
		if(strEndDate.startsWith("d:")){
			strEndDate=strEndDate.substring(2);
		}
		performAction.enter(selectEndDate, utils.getDate(strEndDate), "Select End Date");
	}

	// select Schedule Contribution Dates

	private void selectScheduleDate() {
		performAction.waitUntilElementPresent(selectScheduleDate);
		performAction.select(selectScheduleDate, "index=1", "Schedule Date");
	}

	// Click on Next Button
	private void nextButton() {
		performAction.click(nextButton, "Next Button");
	}

	// click on agreements check box
	private void clickHsaCustodial() {
		performAction.waitUntilElementPresent(hsacustodialAgreement);
		performAction.click(hsacustodialAgreement, "Agreement Check Box");
	}

	// click on agreements check box
	private void clickHsaElectronicDisclosure() {
		performAction.click(hsaelectronicDiscloure, "Agreement Check Box");
	}

	// click on agreements check box
	private void clickHsaRepresentivAgreement() {
		performAction.waitUntilElementPresent(hsaRepresentiveAgreement);
		performAction.click(hsaRepresentiveAgreement, "Agreement Check Box");
	}
	// click on Add Contribution Button
	private void clickAddContributionButton() {
		performAction.click(addContribution, "click on Add Contribution Button");
	}
	// click on Add Contribution Button
		private void clickAddContributionAmountButton() {
			performAction.waitUntilElementPresent(addContributionButtonNew);
			performAction.click(addContributionButtonNew, "click on Add Contribution Button");
	}
		private void checkContributionBoxCheckbox(){
			performAction.click(scheduleContributionchkbox, "schedule contribution checkbox");
		}
	// click on Edit Contribution Button
		private void clickEditContributionButton() {
			performAction.jsclick(editContribution, "click on Edit Contribution Button");
		}
	// click on Edit  Button
		private void clickEditButton() {
			performAction.jsclick(editButton, "click on Edit Button in contributions");
		}
	// click on update Button
		private void clickUpdateButton() {
			performAction.click(updateButton, "click on Update Button in contributions");
		}
	// click on Edit Contribution Button
		private void clickShowOrHideDetails(String strOffer, String strButton) {
			String offer = "//h3[contains(text(),'"+strOffer+"')]";
			 By showOrHideDetails = By
			    		.xpath(offer+"/following-sibling::div//*[contains(text(),'"+strButton+"')]");
			
				 
				 logger.info(offer+"/following-sibling::div//span[contains(text(),'"+strButton+"')]");
				
				  performAction.click(showOrHideDetails, "click on Show Details Button");
		}

	// click on Edit Contribution Button
		private void clickActionsButton() {
			performAction.waitUntilElementPresent(actionsButton);
			performAction.jsclick(actionsButton, "click on Actions Button");
		}

	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : HSAInitialContribution keyword or method is used   to select Intial Contribution option and add amount
     * 
     * *
	 * Role: Member Role
	 *
      * PreCondition : Member should be in HSA Contributions page. 
     * 
      * PostCondition : member will complete the HSA Contributions page.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrAmount |
      * | 100/200/150   |  
      * 
      *  Java file Name :  HSAContributionsPage.java
      *  
      * </pre> 
      **/
	
	@RobotKeyword
	@ArgumentNames({ "StrAmount" })
	public void HSAInitialContribution(String StrAmount) {
		try {
			if(performAction.isElementPresent(oneTimeRadioButton)){
				performAction.jsclick(oneTimeRadioButton, "Click on OneTime radio button");
			}
			if(performAction.isElementPresent(initialContributionButton)){
				this.selectInitialContribution();
			}else{
				performAction.jsclick(initialContributionCheckBox, "Check itialContributionCheckBox");
			}
			this.enterContributionAmount(StrAmount);
			performAction.waitUntilElementPresent(addContributionButtonNew);
			this.clickAddContributionAmountButton();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in Adding Initial Contribution");
		}
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : HSAAbstractContribution keyword or method is used to select Abstract Contribution option and add amount
      * 
	 * Role: Member Role
	 *
      * PreCondition : Member should be in HSA Contributions page. 
     * 
      * PostCondition : member will complete the HSA Contributions page.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrAmount |
      * | 100/200/150   |  
      * 
      *  Java file Name :  HSAContributionsPage.java
      * </pre> 
      **/
	
	@RobotKeyword
	@ArgumentNames({ "StrAmount" })
	public void HSAAbstractContribution(String StrAmount) {
		try {
			this.selectHsaAbstract();
			Thread.sleep(2000);
			this.enterContributionAmount(StrAmount);
			//this.selectStartDate();
			//this.selectEndDate();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("HSA abstract Contribution not found");
		}
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : HSAScheduleContribution keyword or method is used to select Schedule Contribution option and add amount
     *
	 * * Role: Member Role
	 *
      * PreCondition : Member should be in HSA Contributions page. 
     * 
      * PostCondition : member will complete the HSA Contributions page.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrAmount |
      * | 100/200/150   |  
      * 
      *  Java file Name :  HSAContributionsPage.java
      *  
      * </pre> 
      **/
	
	@RobotKeyword
	@ArgumentNames({ "StrAmount" })
	public void HSAScheduleContribution(String StrAmount) {
		try {
			if(performAction.isElementPresent(repeatingRadioButton)){
				performAction.click(repeatingRadioButton, "Check repeating Radio Button");
			}
			this.enterContributionAmount(StrAmount);
			this.selectScheduleDate();
			this.addContributionAmount();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Schedule Contribution not found ");
		}
	}
	/**
	 * <pre> 
	  * Author  : Nagarjuna Thallam
	 *  
	  * Description : HSAScheduleContribution keyword or method is used to select Ongoing Contribution option and add amount
	 * 
	 *  Role: Member Role
	 *
	 *
	  * PreCondition : Member should be in HSA Contributions page. 
	 * 
	  * PostCondition : member will complete the HSA Contributions page.
	 *  
	  * <b>Parameters & Example </b> 
	  * 
	  * | StrAmount |
	  * | 100/200/150   |   
	  * 
	  * Java file Name :  HSAContributionsPage.java
	  * 
	  * </pre> 
	  **/
	
	@RobotKeyword
	@ArgumentNames({ "StrAmount","strStartDate","strEndDate" })
	public void HSAOngoingContribution(String StrAmount,String strStartDate,String strEndDate) {
		try {
			if(performAction.isElementPresent(repeatingRadioButton)){
				performAction.click(repeatingRadioButton, "Check repeating Radio Button");
			}
		
			performAction.click(changeContibutionStartAndEndDates, "Click on Change Contibution Start and End dates");
			this.selectStartDate(strStartDate);
			this.selectEndDate(strEndDate);
			this.enterContributionAmount(StrAmount);
			performAction.waitUntilElementPresent(addContributionButtonNew);
			this.clickAddContributionAmountButton();
			scr.capturePageScreenshot();
		}
		catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Ongoing Contribution not Added");
		}
	}

	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : HSACompleteContribution keyword or method is used to complete the Contribution in new member role.
     * 
     *  Role: Member Role
	 *
      * PreCondition : Member should be in HSA Contributions page. 
     * 
      * PostCondition : member will complete the HSA Contributions page and redirects to the agreements page.
     *   
     *   Java file Name :  HSAContributionsPage.java
     *   
      * </pre> 
      **/
	
	@RobotKeyword
	public void HSACompleteContribution() {
		try {
			this.nextButton();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Next Button is not able to found");
		}
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : HSACompleteContribution keyword or method is used to complete all HSA Agreements and
	 * redirects to offer summary page 
	 * 
	 *  Role: Member Role
	 *
      * PreCondition : Member should be in HSA Agreements page. 
     * 
      * PostCondition : member redirects to the offer summary page.
     *    
     *  Java file Name :  HSAContributionsPage.java
     *    
      * </pre> 
      **/
	
	@RobotKeyword
	public void HSACompleteAgreements() {
		try {
			this.clickHsaCustodial();
			if(performAction.isElementPresent(hsaelectronicDiscloure)){
			this.clickHsaElectronicDisclosure();
			this.nextButton();
			}
//			this.clickHsaElectronicDisclosure();
			this.nextButton();
			this.clickHsaRepresentivAgreement();
			this.nextButton();
			this.clickHsaCustodial();
			this.nextButton();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Error occured while agreements check box");

		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 *  
	 * Description   : 'Add HSA Contribution' keyword used to click on 'Add Contribution' button in Member Role.
	 * 
	 * PreCondition  : Navigate to 'Contribute to your Health Savings Account (HSA)' page while enrolling a member in HSA plan. 
	 * 
	 * PostCondition : Able to view Intial,Ongoing,Scheduled Contributions.
	 *	 
	 * </pre>
	 **/
	@RobotKeyword
	public void addHSAContributionInMemberRole() {
		try {
			this.clickAddContributionButton();
			if(performAction.isElementPresent(addContributionButton)){
				System.out.println("Contributions Available");
			}else{
				this.nextButton();
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting Add Contribution while enrolling a member in HSA plan"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting Add Contribution while enrolling a member in HSA plan"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Rajeswari Nimmala
	 *  
	 * Description   : keyword used to click on 'Edit Contribution' button in Member Role.
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition  : Navigate to ' Health Savings Account (HSA)' page while enrolling a member in HSA plan. 
	 * 
	 * PostCondition : Able to view Add Contributions.
	 * 
	 *	Java File Name: HSAContributionsPage.java 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void editHSAContributionInMemberRole() {
		try {
			this.clickEditContributionButton();
			
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in click on edit Contribution of a member in HSA plan"
					+ e.getMessage());
			throw new CustomException(
					"Exception in click on edit Contribution of a member in HSA plan"
							+ e.getMessage());
		}
	}

	/**
     * <pre> 
     * Author  : Rajeswari Nimmala
     *  
     * Description :  keyword is used  to update Intial Contribution amount.
     * 
     * Role: MemberRole
     * 
     * PreCondition : Member should be in HSA Edit Contributions page. 
     * 
     * PostCondition : Member will come to the HSA Contributions page.
     *  
     * <b>Parameters & Example </b> 
     * 
     * | StrAmount |
     * | 100/200/150   | 
     * 
     * Java File Name: HSAContibutionsPage.java
     * </pre> 
     **/
	@RobotKeyword
	@ArgumentNames({ "StrAmount" })
	public void updateHSAInitialContributionAmountInMemberRole(String StrAmount) {
		try {
			this.clickEditContributionButton();
			
			this.clickActionsButton();
			this.clickEditButton();
			
			this.enterContributionAmount(StrAmount);
			this.clickUpdateButton();
			
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in updating HSAcontribution amount of a member"
					+ e.getMessage());
			throw new CustomException(
					"Exception in updating HSAcontribution amount of a member"
							+ e.getMessage());
		}
	}

	/**
     * <pre> 
     * Author  : Rajeswari Nimmala
     *  
     * Description :  keyword  is used to click on Show / Hide details of Additional Information in new member role.
     *
     * Role: Member Role
     * 
     * PreCondition : Member should be in Summary page. 
     * 
     * PostCondition : member additional information will be displayed in Summary page.
     * 
     * <b>Parameters & Example </b> 
     * 
     * | strOffer - Offer name  || strButton - Click to perform on Button given  |
     * Java File Name: HSAContributionsPage.java  
     * </pre> 
     **/
	
	@RobotKeyword
	@ArgumentNames({ "strOffer", "strButton" })
	public void clickShowOrHideDetailsInMemberRole(String strOffer, String strButton) {
		try {
			this.clickShowOrHideDetails(strOffer , strButton );
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Show or Hide Button is not able to found");
		}
	}
	
	/**
     * <pre> 
      * Author  : Srikanth G
     *  
      * Description : HSAScheduleContribution keyword or method is used to select Schedule Contribution option and add amount
     * 
      * PreCondition : Member should be in HSA Contributions page. 
     * 
      * PostCondition : member will complete the HSA Contributions page.
      * 
      * Role: Member
      * 
      * Java file name: HSAContributionsPage.java
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrAmount |
      * | 100/200/150   |   
      * </pre> 
      **/
	
	@RobotKeyword
	@ArgumentNames({ "StrAmount" })
	public void HSAScheduleContributionInNewMemberRole(String StrAmount) {
		try {
			if(performAction.isElementPresent(scheduleContributionchkbox)){
				this.checkContributionBoxCheckbox();
			}
			this.enterContributionAmount(StrAmount);
			this.selectInitialContributioncheckbox();
			scr.capturePageScreenshot();
			this.nextButton();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Schedule Contribution not found ");
		}
	}
}
