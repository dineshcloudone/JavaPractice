package com.benefitfocus.robot.hradmin;

import org.json.simple.JSONObject;
import org.openqa.selenium.Alert;
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
public class MemberBenefitDetailsPage {

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

	By benefitDetailsButton = By
			.xpath("//span[contains(text(),'Benefit details')]");
	By editCobraBenefitDetailsButton = By
			.xpath("//strong[contains(text(),'Edit')]");
	By editEffectiveDateButton = By.xpath("(//a[contains(text(),'Edit')])[4]");
	By enterEffectiveDate = By.id("overallEffectiveDate");

	String currentBenefitsList = "//div[@id='preFurlBenefitsDisplay']";
	By verifycancelbenefitsbtn = By
			.xpath("//strong[contains(text(),'Cancel Benefits for All')]");
	By clickCancelButton = By.linkText("Cancel Benefits for All");
	By cancelBenefitRadioButton = By.id("other");

	By clickNextButton = By.xpath("//strong[contains(text(),'Next')]");
	By clickSaveAndGotoBenefitsButton = By.linkText("Save and Go to Benefits");
	By memberOverview = By.xpath("//span[contains(text(),'Overview')]");
	By manageCobra = By.xpath("//strong[contains(text(),'Manage COBRA')]");
	// Locators for "Select edit due to change reason on the benefit tab to add a life event"
	By newEventType=By.name("newEventType");
	By otherResonRadio=By.id("other");
	By refuseAllRadio=By.id("cancelOnly");
	By resonForChanageMsg=By.xpath("//div[@class='regionHeader']/h3[contains(text(),'Reason for Change')]");
	By continueWithChangeYes=By.id("yesNo[true]");
	By continueWithChangeNo=By.id("yesNo[false]");
	By newEventDate=By.id("newEventDate");
	By memberInitiatedDate=By.id("memberInitiatedDate");
	By refusebenefitsbutton = By.partialLinkText("Refuse");

	/*
	 * Click Benefit Details to navigate to Benefit Details page from member
	 * home page
	 */
	private void clickBenefitDetails() {

		performAction.click(benefitDetailsButton, "Benefit Details");
		if(performAction.isAlertPresent()){
			performAction.acceptAlert();
		}
	}

	/* Click Edit Button in Benefit details page for the selected Benefit */
	private void clickEditButtonForBenefitDetails(String strBenefitName) {

		boolean benefitFound = false;
		try {
			Thread.sleep(1000);
			if (browser.getCurrentWebDriver()
					.findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

				By list = By.xpath(currentBenefitsList + "/table");

				System.out.println("list" + list.toString());

				int benefitsCount = browser.getCurrentWebDriver()
						.findElements(list).size();

				System.out.println("benefitsCount" + benefitsCount);

				if (benefitsCount > 0) {

					for (int i = 1; i <= benefitsCount; i++) {

						By BenefitHeader = By.xpath(currentBenefitsList
								+ "/table[" + i + "]//h1");
						System.out.println("BenefitName" + BenefitHeader);

						if (browser.getCurrentWebDriver()
								.findElement(BenefitHeader).getText().trim()
								.equalsIgnoreCase(strBenefitName)) {
							Thread.sleep(1000);
							By editButton = By.xpath(currentBenefitsList
									+ "/table[" + i + "]//a");
							performAction.jsclick(editButton, strBenefitName
									+ " edit button");

							benefitFound = true;
							break;
						}
					}
				} else {

					throw new RuntimeException("No Benefits available");
				}

				if (!benefitFound) {
					throw new RuntimeException(strBenefitName
							+ "benefit NOT available.");
				}
			}

		}

		catch (Exception e) {

			throw new CustomException(
					"Exception occured while Editing Benefit details to the Employee in HR Mode "
							+ " " + e.getMessage());
		}

	}

	/* Click on Refuse Button in Benefit details tab for the selected Benefit */
	private void clickRefuseButtonForBenefitDetails(String strBenefitName) {

		boolean benefitFound = false;
		try {
			Thread.sleep(1000);
			if (browser.getCurrentWebDriver()
					.findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

				By list = By.xpath(currentBenefitsList + "/table");

				System.out.println("list" + list.toString());

				int benefitsCount = browser.getCurrentWebDriver()
						.findElements(list).size();

				System.out.println("benefitsCount" + benefitsCount);

				if (benefitsCount > 0) {

					for (int i = 1; i <= benefitsCount; i++) {

						By BenefitHeader = By.xpath(currentBenefitsList
								+ "/table[" + i + "]//h1");
						System.out.println("BenefitName" + BenefitHeader);

						if (browser.getCurrentWebDriver()
								.findElement(BenefitHeader).getText().trim().toLowerCase()
								/*.equalsIgnoreCase(strBenefitName.toLowerCase().trim())) {*/
							.contains(strBenefitName.toLowerCase().trim())) {
							Thread.sleep(1000);
							By refuseButton = By.xpath("("
									+ currentBenefitsList + "/table[" + i
									+ "]//a)[last()]");
							performAction.jsclick(refuseButton, strBenefitName
									+ " edit button");

							benefitFound = true;
							break;
						}
					}
				} else {

					throw new RuntimeException("No Benefits available");
				}

				if (!benefitFound) {
					throw new RuntimeException(strBenefitName
							+ "benefit NOT available.");
				}
			}

		}

		catch (Exception e) {

			throw new CustomException(
					"Exception occured while Editing Benefit details to the Employee in HR Mode "
							+ " " + e.getMessage());
		}

	}

	/*
	 * Click Next button on Effective Details page
	 */
	private void clickEditEffectiveDateButton() {

		performAction.click(editEffectiveDateButton, "Edit");

	}

	/*
	 * Click on Next button the current benefit
	 */
	private void selectEffectiveDateAndClickNextButton() {
		String currentDate = utils.getDate("currentdate:");
		performAction.clearEnter(enterEffectiveDate, currentDate,
				"New Effective Date");
		utils.sleep(1000);
		performAction.click(clickNextButton, "Next");
	}

	/* Click on Cancel Benefit Button to cancel the selected benefit */
	private void clickCancelBenefitForAll() {

		if (performAction.isElementPresent(verifycancelbenefitsbtn)) {
			performAction.click(verifycancelbenefitsbtn,
					"Cancel Benefits for All");
			utils.sleep(3000);
		} else {
			performAction.click(clickCancelButton, "Cancel Benefits for All");
			utils.sleep(3000);
		}
		Alert alert = browser.getCurrentWebDriver().switchTo().alert();
		alert.accept();
	}

	/* Select Radio button to select reason for cancel the benefit */
	private void selectCancelRadioButton() {
		performAction.click(cancelBenefitRadioButton, "Other");
	}

	/* Click on Next Button after selecting radio button */
	private void clickReasonNextButton() {
		performAction.click(clickNextButton, "Next");
	}

	/* Click on Next button after Effective date changed */
	private void clickEffectiveDateNextButton() {
		performAction.click(clickNextButton, "Next");
	}

	/* Click on Save And Goto Benefits Button */
	private void clickSaveGotoBenefitsButton() {
		performAction.click(clickSaveAndGotoBenefitsButton,
				"Save And Go to Benefits");
	}

	/* Click on Member Overview tab */
	private void clickMemberOverviewButton() {
		performAction.click(memberOverview, "Save And Go to Benefits");
	}

	/* Click on Manage COBRA */
	private void clickManageCobraButton() {
		performAction.click(manageCobra, "Manage COBRA");
	}

	/*
	 * Click on Edit COBRA Benefits
	 */
	private void clickEditCobraButton() {
		performAction.click(editCobraBenefitDetailsButton, "Manage COBRA");
	}

	/* 
	 * Select a new Change Resaon
	 * Reason Eg: Birth,Adoption,COBRO,or Court order etc..
	 */

	private void selectChangeReason(String reason)
	{
		performAction.select(newEventType, reason, "Select Reason");
	}
	/* 
	 * Select other option 
	 */
	private void selectOtherOption()
	{
		performAction.click(otherResonRadio, "Other oprtion for Select Reason");
	}
	/* 
	 * Select Refuse All for Select Reason
	 */
	private void selectRefuseAll()
	{
		performAction.click(refuseAllRadio, "Refuse All option for Select Reason");

	}
	/*
	 * Do you wish to continue with this change? Yes
	 */
	private void chooseContinueYes()
	{
		performAction.click(continueWithChangeYes, "Yes Option");
	}
	/* 
	 * Do you wish to continue with this change? No
	 */

	private void chooseContinueNo()
	{
		performAction.click(continueWithChangeNo, "No Option");
	}
	/*
	 * Enter Event Birth date
	 */
	private void setBirthDate(String eventDate)
	{
		performAction.enter(newEventDate, eventDate, "BirthDate");
	}
	/*
	 * Enter notified life event date
	 */
	private void setNotifiedDate(String notifiedDate)
	{
		performAction.enter(memberInitiatedDate, notifiedDate, "Notified Event Date");
	}
	
	/*
	 * Select Refuse Benefits link
	 */
	private void clickRefuseButton(){
		performAction.click(refusebenefitsbutton, "Refuse benefits button");
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 * 
	 * Keyword to Change Effective Date for the current started benefit started
	 * either from Member Role or HR Role
	 * 
	 * 
	 * <precondition>
	 * <b> HR should be Logged In and member home page should open and note that the selected benefit should be already started </b>
	 * <b>Parameter: </b>
	 * | strBenefitName - String type argument takes Benefit Name as input value, to which benefit want to change Effective date |
	 * <b>Example :</b>
	 * | MEDICAL 2015 / DENTAL Offer 2015 / Vision 2015 / FSA 2015 / Life Plan 2015 / LTD2015 / STD 2015 |
	 * <postcondition>
	 * <b>Effective Date For selected Benefit will be changed to today's date</b>
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "benefitPlan" })
	public void changeEffectiveDateForTheStartedBenefitInHRRole(
			String strBenefitName) {

		try {
			this.clickBenefitDetails();

			this.clickEditButtonForBenefitDetails(strBenefitName);

			this.clickEditEffectiveDateButton();

			this.selectEffectiveDateAndClickNextButton();

			this.clickSaveGotoBenefitsButton();
			performAction.verifyMessage("Pending Approval");
			this.clickMemberOverviewButton();
		}

		catch (Exception e) {

			throw new CustomException(
					"Exception occured while changing Effective Date for the selected benefit in HR Mode "
							+ " " + e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 * 
	 * Keyword to Cancel selected benefit, started either in Member Role or HR
	 * Role
	 * 
	 *  <precondition>
	 *  <b> HR should be logged in and member home page should open and the selected benefit should already be started either in ember Role or HR Role </b>
	 * | strBenefitName - String type argument takes Benefit Name as input value |
	 *  <b>Example :</b>
	 *  | MEDICAL 2015 / DENTAL Offer 2015 / Vision 2015 / FSA 2015 / Life Plan 2015 / LTD2015 / STD 2015 |
	 *  <postcondition>
	 *  <b>Selected Benefit will be cancelled </b>
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "benefitPlan" })
	public void cancelSelectedBenefitForMemberInHRRole(
			String strCancelBenefitName) {
		try {

			if (performAction.isElementPresent(verifycancelbenefitsbtn)) {
				this.clickBenefitDetails();

				this.clickEditButtonForBenefitDetails(strCancelBenefitName);

				this.clickCancelBenefitForAll();
				utils.sleep(2000);
				this.selectCancelRadioButton();

				this.clickReasonNextButton();

				this.clickEffectiveDateNextButton();

				this.clickSaveGotoBenefitsButton();

				performAction.verifyMessage("Status:Cancelled");

				this.clickMemberOverviewButton();
			} else {
				this.clickBenefitDetails();

				this.clickEditButtonForBenefitDetails(strCancelBenefitName);

				this.clickCancelBenefitForAll();
				utils.sleep(2000);

				this.clickEffectiveDateNextButton();
				this.clickSaveGotoBenefitsButton();

				performAction.verifyMessage("Status:Cancelled");

				this.clickMemberOverviewButton();
			}

		} catch (Exception e) {

			throw new CustomException(
					"Exception occured while  Cancelling benefits to the Employee in HR Mode "
							+ " " + e.getMessage());
		}
	}

	/**
	 * 
	 * <pre>
	 * Author  : CH Phani Srikar
	 * 
	 * Keyword to Refuse Benefit which is not yet started in HR Role
	 * 
	 * <precondition>
	 *  <b> Selected Benefit should not be started either from Member or HR Role</b>
	 * | strBenefitName - String type argument takes Benefit Name as input value |
	 *  <b>Example :</b>
	 *  | MEDICAL 2015 / DENTAL Offer 2015 / Vision 2015 / FSA 2015 / Life Plan 2015 / LTD 2015 / STD 2015 |
	 *  <postcondition>
	 *  <b>Selected Benefit will be refused</b>
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "benefitPlan" })
	public void refuseCurrentSelectedBenefitInHRRole(String strRefuseBenefitName) {
		try {
			this.clickBenefitDetails();
			this.clickRefuseButtonForBenefitDetails(strRefuseBenefitName);
			//performAction.verifyMessage("Status: Coverage Declined");
			scr.capturePageScreenshot();
		} catch (Exception e) {

			throw new CustomException(
					"Exception occured while Refusing benefits to the Employee in HR Mode "
							+ " " + e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 * 
	 * Keyword to Cancel COBRA Benefit in HR Role for the terminated
	 * member/employee
	 * <precondition>
	 *  <b>Member Home page should open</b>
	 *  <postcondition>
	 *  <b>COBRA  Benefit will be cancelled</b>
	 * </pre>
	 */
	@RobotKeyword
	public void cancelCOBRABenefitsForMemberInHRROle() {
		try {
			this.clickBenefitDetails();
			performAction.verifyMessage("Terminated");
			this.clickManageCobraButton();

			this.clickEditCobraButton();

			this.clickCancelBenefitForAll();
			this.selectCancelRadioButton();

			this.clickReasonNextButton();

			this.clickEffectiveDateNextButton();

			this.clickSaveGotoBenefitsButton();

			performAction.verifyMessage("Canceled");

			this.clickMemberOverviewButton();

		} catch (Exception e) {

			throw new CustomException(
					"Exception occured while Cancelling COBRA benefits to the Member in HR Mode "
							+ " " + e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : "selectLifeEvent" is used to verify the Reason for Change for the employee in eEnrollment HR admin role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : Add an Employee with Hiredate (Must be less than the Current date)
	 * 
	 * PostCondition : NA
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | lifeevent - input takes from JSON file  | 
	 * | birthdate and notified date |
	 * 
	 * Java file Name :  MemberBenefitDetailsPage.java
	 * </pre> 
	 * */
	@RobotKeyword
	@ArgumentNames({ "lifeevent" })
	public void selectLifeEvent(String lifeevent) {
		if (lifeevent.startsWith("td:"))
			lifeevent = lifeevent.substring(3);	
		try {

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(lifeevent
					.toLowerCase());
			object = fields.get("reason");
			if (object != null) {
				this.selectChangeReason(object.toString());	
				performAction.click(clickNextButton, "Next");
				browser.hMap.put("reason", object.toString());
				performAction.verifyMessage(browser.getCurrentWebDriver().findElement(resonForChanageMsg).getText());
				if(browser.getCurrentWebDriver().findElement(newEventDate).isDisplayed())
				{
					this.chooseContinueYes();
					object = fields.get("birthdate");
					if (object != null) {
						this.setBirthDate(utils.getDate(object.toString()));					
					}
					object = fields.get("notifieddate");
					if (object != null) {
						this.setNotifiedDate(utils.getDate(object.toString()));					
					}
					performAction.click(clickNextButton, "Next");
				}else
				{
					this.chooseContinueYes();
					performAction.click(clickNextButton, "Next");
				}
			}			

		} catch (Exception e) {

			throw new CustomException(
					"Exception occured while selecting change reason in life event"
							+ " " + e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : Keyword to verify the Reason for Change for the employee in eEnrollment HR admin role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : Add an Employee with Hiredate (Must be less than the Current date)
	 * 
	 * PostCondition : NA
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | lifeevent - input takes from JSON file  | 
	 * | birthdate and notified date |
	 * </pre> 
	 * 
	 *  Java file Name :  MemberBenefitDetailsPage.java
	 * */
	@RobotKeyword
	@ArgumentNames({ "lifeevent" })
	public void reasonForChange(String lifeevent) {
			
		try {
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(lifeevent
					.toLowerCase());
			object = fields.get("reason");			
			if (object != null) {			
				performAction.verifyMessage(browser.getCurrentWebDriver().findElement(resonForChanageMsg).getText());
				if(browser.getCurrentWebDriver().findElement(newEventDate).isDisplayed())
				{
					this.chooseContinueYes();
					object = fields.get("birthdate");
					if (object != null) {
						this.setBirthDate(utils.getDate(object.toString()));					
					}
					object = fields.get("notifieddate");
					if (object != null) {
						this.setNotifiedDate(utils.getDate(object.toString()));					
					}
					performAction.click(clickNextButton, "Next");
				}else
				{
					this.chooseContinueYes();
					performAction.click(clickNextButton, "Next");
				}
			}
		} catch (Exception e) {
			throw new CustomException(
					"Exception occured while Cancelling COBRA benefits to the Member in HR Mode "
							+ " " + e.getMessage());
		}
	}
	
	/**
	 * Keyword to Refuse all Benefits which are not yet started in HR Role
	 * 
	 * <pre>
	 * Author  :Abhijith Desai
	 * 
	 * Role: HR Role
	 * 
	 * Description  : 'refuseAllBenefitsForEmployeeInHRRole' keyword used to click on Refuse All Button in HR Admin Role
	 * 
	 * PreCondition : User should be Refuse benefits page  
	 * 
	 * PostCondition: Will click on refuse all benefits button on the page
	 * 
	 * Java File Name: MemberBenefitDetailsPage.java 
	 *
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({})
	public void refuseAllBenefitsForEmployeeInHRRole() {
		try {
			this.clickBenefitDetails();
			this.clickRefuseButton();
			scr.capturePageScreenshot();
		} catch (Exception e) {

			throw new CustomException(
					"Exception occurred while Refusing all benefits to the Employee in HR Role "
							+ " " + e.getMessage());
		}
	}
	
}
