package com.benefitfocus.robot.member;

import org.json.simple.JSONObject;
import org.junit.Assert;
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
import com.benefitfocus.robot.member.Navigations;

@RobotKeywords
public class MedicarePolicyPage {

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
	protected Navigations navigations;
	
	By enableMedicareManager = By.id("enableMedicareManager");
	By medicareAutoPopulate = By.id("medicareAutoPopulate");
	By medicareAutoEnrollment = By.id("medicareAutoEnrollment");
	By clickSave = By.linkText("Save");
	
	By medicare = By.linkText("Medicare");
	By medicareInformation = By.id("hasMedicare[YES]");
	By noMedicareInformation = By.id("hasMedicare[NO]");
	By coveredMedicare = By.name("policyHolderCacheKey-nativeHtmlElement");
	By allRequiredDetails = By.id("sectionIncomplete[false]");
	By medicareNumber = By.id("hicNumber");
	By eligibilityReason = By.id("reasonAge-nativeHtmlElement");
	By hospitalInsuranceForPartA = By.id("isPartA[true]");
	By enterEffectiveDate = By.id("partAEffDate");
	By clickSaveMemberRole = By.id("submitForm");
	By ageCheckBox = By.id("reasonAge");
	By actionsDropDown = By.id("editDelete");
	By edit = By.linkText("Edit");
	By remove = By.linkText("Remove");

	By nextButton = By.xpath("//button[contains(text(),'Next')]");
	By cancelMedicare = By.xpath("//button[contains(text(),'Cancel')]");
	By removeMedicareInfo = By.xpath("//div[@id='deleteMedicareModal']//button[contains(text(),'Remove Medicare Information')]");
	By removeMedicareButton = By.xpath("//button[@id='removePolicyButton']");
	By deleteConfirmation = By.xpath("//span[conatins(text(),'Delete Confirmation')]");
	By medicareNumberColumn = By.xpath("//table//th[contains(text(),'Medicare Number')]");
	By errorMsg = By.id ("errors");
	
	//Private Mothods
	
	private void remove() {
		performAction.click(actionsDropDown, "Actions");
		performAction.click(remove, "Remove");
	}
	
	private void removeMedicare() {
		performAction.click(removeMedicareInfo, "Click remove medicare info");
	}

	private void cancelMedicare() {
		performAction.click(cancelMedicare, "Click cancel medicare ");
	}
	private void verifyDeleteConfrimationDialog() {
		performAction.isElementPresent(deleteConfirmation, "Delete Confrimation text");
		performAction.verifyMessage("Are you sure you want to delete this medicare information?");
	}
	private void verifyCancelMedicare() {
		Assert.assertTrue(performAction.isElementPresent(medicareNumberColumn));

	}
	private void verifyRemoveMedicare() {
		Assert.assertFalse(performAction.isElementPresent(medicareNumberColumn));

	}
	private void addMedicareInformation() {
		performAction.click(medicareInformation, "Click Yes For Medicare Information");
		performAction.click(coveredMedicare, "Click Covered Medicare");
		performAction.click(allRequiredDetails, "Click Yes For All Required Fields");
	}
	
	private void enterAdditionalMedicareInformation(String strMedicareNumber, String strenterEffectiveDate) {
		performAction.waitUntilElementPresent(medicareNumber);
		logger.info("strMedicareNumber"+strMedicareNumber);
		performAction.enter(medicareNumber, strMedicareNumber, "Medicare Number");
		performAction.click(eligibilityReason, "Select Age Eligibility Reason");
		performAction.click(hospitalInsuranceForPartA, "Click Hospital Insurance For Part A");
		performAction.enter(enterEffectiveDate, strenterEffectiveDate, "Effective Date");

	}

	private void updateAdditionalMedicareInformation(String strMedicareNumber) {
		performAction.waitUntilElementPresent(medicareNumber);
		logger.info("strMedicareNumber"+strMedicareNumber);
		browser.getCurrentWebDriver().findElement(medicareNumber).clear();
		performAction.clearEnter(medicareNumber, strMedicareNumber, "Medicare Number");
	}
	
	private void saveMedicareinformation() {
		performAction.waitUntilElementPresent(clickSaveMemberRole);
		performAction.click(clickSaveMemberRole, "Click Save Member UI");
		scr.capturePageScreenshot();
		performAction.waitForPageLoad();
		Assert.assertFalse(performAction.isElementPresent(errorMsg, "Error displayed"));

	}
	

	private void editMedicareInformation() {
		performAction.waitUntilElementPresent(actionsDropDown);
		performAction.click(actionsDropDown, "Actions");
		performAction.click(edit, "Edit");
	}
	
    // Click on Next Button
    private void clickNextButton() {
        performAction.click(nextButton, "Click on Next Button");
    }


    private void verifyMedicareInformation() {
    	String isChecked=browser.getCurrentWebDriver().findElement(eligibilityReason).getAttribute("checked");
		scr.capturePageScreenshot();
		Assert.assertTrue("Age Checkbox is checked already", isChecked.equals("true"));
	}
	
	//Robot Methods
	
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Medicare Policy Steps for Member in Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Medicare Policy Page Opened in Member Role
	 * 
	 * PostCondition : Updated Medicare Policy for Member in Member Role
	 * 
	 * Java File Name : MedicarePolicyPage.java
	 * 
	 * | strMedicareNumber | strenterEffectiveDate |
	 * | ex: A123456789 | ex: currentdate |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strMedicareNumber", "strenterEffectiveDate" })
	public void addmedicarePolicyInMemberRole(String strMedicareNumber, String strenterEffectiveDate) {
		try {
			this.addMedicareInformation();
			this.clickNextButton();
			this.enterAdditionalMedicareInformation(strMedicareNumber, strenterEffectiveDate);
			this.clickNextButton();
			this.saveMedicareinformation();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Error while adding medicare Policy details In Member Role"
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Edit Medicare Policy details for Member in Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Medicare Policy Page Opened in Member Role
	 * 
	 * PostCondition : Updated Medicare Policy details for Member in Member Role
	 * 
	 * Java File Name : MedicarePolicyPage.java
	 * 
	 *  <b>Parameters & Example </b>
	 *
	 * | strMedicareNumber  |
	 * | ex: A123456789 |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strMedicareNumber"})
	public void editMedicarePolicyInMemberRole(String strMedicareNumber) {
		try {
			
			this.editMedicareInformation();
			this.updateAdditionalMedicareInformation(strMedicareNumber);
			this.clickNextButton();
			this.saveMedicareinformation();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Error while editing medicare Policy details In Member Role"
					+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *
	 * Description  : Verify age checkbox is selected in Medicare Policy details page  for Member in Member Role
	 *
	 * Role : Member Role
	 *
	 * PreCondition : Edit Medicare Policy Page Opened in Member Role
	 *
	 * PostCondition : Verified  Age checkbox is selected in Medicare Policy details page for Member in Member Role
	 *
	 * Java File Name : MedicarePolicyPage.java
	 *
	 * | |
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ })
	 public void verifyMedicareInformationInMemberRole(){
	try {
		this.editMedicareInformation();
		this.verifyMedicareInformation();
	} catch (Exception e) {
		scr.capturePageScreenshot();
		throw new CustomException("Error while Verifying medicare Policy details In Member Role"
				+ e.getMessage());
	}
  }
   	/**
	 * <pre>
	 * Author  : Rajeswari Nimmala
	 *
	 * Description  : Remove Medicare for Member in Member Role
	 *
	 * Role : Member Role
	 *
	 * PreCondition : Medicare Policy remove Page Opened in Member Role
	 *
	 * PostCondition :  Medicare Policy for Member in Member Role
	 *
	 * Java File Name : MedicarePolicyPage.java
	 *
	 *  <b>Parameters & Example </b>
	 *
	 * |strButton-- Button name on which action to be performed ex: Cancel,Remove |
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strButton"})
	public void removeMedicareForMemberInMemberRole(String strButton) {
		try {
			this.remove();
			if(strButton.equalsIgnoreCase("Cancel")){
				this.verifyDeleteConfrimationDialog();
				this.cancelMedicare();
				this.verifyCancelMedicare();
			} else {
				this.removeMedicare();
				this.verifyRemoveMedicare();

			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception during remove medicare"
					+ e.getMessage());
		}
	}
}