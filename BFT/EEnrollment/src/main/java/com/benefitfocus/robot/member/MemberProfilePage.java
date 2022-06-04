package com.benefitfocus.robot.member;

import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.String;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.benefitfocus.robot.member.Navigations;
import com.benefitfocus.robot.portal.EnrollmentLogin;

@RobotKeywords
public class MemberProfilePage {

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
	@Autowired
	protected EnrollmentLogin enrollogin;

	By verifyProfilePage = By.partialLinkText("Profile");
	By personalInformationNextButton = By.id("personalInformationSaveOrNext");
	By verifyPISuccessMsg = By.cssSelector("span.supplementalText");
	By emergencyContactNextButton = By.id("emergencyContactSaveOrNext");
	By verifyECSuccessMsg = By
			.xpath("//a[@id='emergencyContact-header']/span[2]/span");
	By militaryServiceNextButton = By.id("militarySaveOrNext");
	By verifyMSSuccessMsg = By.xpath("//a[@id='military-header']/span[2]/span");
	By directDepositNextButton = By.id("directDepositSaveOrNext");
	By verifyDDSuccessMsg = By
			.xpath("//a[@id='directDeposit-header']/span[2]/span");
	By profilePageSaveButton = By.id("submitForm");
	By addDependentsPageNextButton = By.id("next");
	By verifyHomePage = By.cssSelector("a[href*='goToHomePage']");
	By returnhomepage = By.xpath("//button[@type='button']");
	By getStarted = By.xpath("//a[@class='btn btn-large btn-primary']");
	By medicalCheckBox = By
			.xpath("//img[@border='0' and @name='selectedPlanKeyImg0']");
	By employeeCheckBox = By
			.xpath("//img[@border='0' and @name='selectedTierKeyImg0']");

	// Personal Information page Locators
	By firstNameLabel = By.xpath("//label[@for='firstName']");
	By middleNameLabel = By.xpath("//label[@for='middleName']");
	By lastNameLabel = By.xpath("//label[@for='lastName']");
	By firstName = By.xpath("//input[@id='firstName']");
	By middleName = By.xpath("//input[@id='middleName']");
	By lastName = By.xpath("//input[@id='lastName']");
	By dob = By.xpath("//input[@id='birthDate']");
	By dobLabel = By.xpath("//label[@for='birthDate']");
	By gender = By.xpath("//fieldset[@id='gender']");
	By maritalStatusLabel = By.xpath("//label[@for='maritalStatus']");
	By maritalStatus = By.xpath("//select[@id='maritalStatus']");
	By raceLabel = By.xpath("race");
	By race = By.xpath("//select[@id='race']");
	By address1Label = By.xpath("//label[@for='address1']");
	By address1 = By.xpath("//input[@id='address1']");
	By address2Label = By.xpath("//label[@for='address2']");
	By address2 = By.xpath("//input[@id='address2']");
	By cityLabel = By.xpath("//label[@for='city']");
	By city = By.xpath("//input[@id='city']");
	By countryLabel = By.xpath("//label[@for='country']");
	By country = By.xpath("//select[@id='country']");
	By zipLabel = By.xpath("//label[@for='zip']");
	By zip = By.xpath("//input[@id='zip']");
	By homephoneLabel = By.xpath("//label[@for='homePhone']");
	By homephone = By.xpath("//input[@id='homePhone']");
	By cellphoneLabel = By.xpath("//label[@for='cellPhone']");
	By cellphone = By.xpath("//input[@id='cellPhone']");
	By alternatephoneLabel = By
			.xpath("//label[@for='personalContactAlternatePhone']");
	By alternatephone = By
			.xpath("//input[@id='personalContactAlternatePhone']");
	By workphoneLabel = By.xpath("//label[@for='workPhone']");
	By workphone = By.xpath("//input[@id='workPhone']");
	By workcellphoneLabel = By.xpath("//label[@for='workMobilePhone']");
	By workcellphone = By.xpath("//input[@id='workMobilePhone']");
	By personalEmailLabel = By.xpath("//label[@for='homeEmail']");
	By personalEmail = By.xpath("//input[@id='homeEmail']");
	By workEmailLabel = By.xpath("//label[@for='workEmail']");
	By workEmail = By.xpath("//input[@id='workEmail']");
	By emergencyContact = By.xpath("//a[@id='emergencyContact-header']");
	By militaryServices = By.xpath("//a[@id='military-header']/span[@class='message']");
	By directDeposit = By
			.xpath("//a[@id='directDeposit-header']/span[@class='message']");
	By savebutton = By.xpath("//button[@id='submitForm']");
	By nextButton = By.xpath("//button[contains(text(),'Next')]");

	// Emergency Contact locators
	By emgergencyContactYes = By
			.xpath("//input[@name='emergencyContactAsk' and @value='true']");
	By relationship = By
			.xpath("//input[@id='currentEmergencyContact.relationship']");
	By emergencyconFullName = By.id("currentEmergencyContact.name");
	By emergencyconEmail = By
			.xpath("//input[@id='currentEmergencyContact.email']");
	By emergencyconPhone = By
			.xpath("//input[@id='currentEmergencyContact.primaryPhone']");
	By emergencyconAlternatePhone = By
			.xpath("//input[@id='currentEmergencyContact.alternatePhone']");
	By emergencyConAddress1 = By
			.xpath("//input[@id='currentEmergencyContact.address1']");
	By emergencyConAddress2 = By
			.xpath("//input[@id='currentEmergencyContact.address2']");
	By emergencyconCity = By
			.xpath("//input[@id='currentEmergencyContact.city']");
	By emergencyconState = By
			.xpath("//select[@id='currentEmergencyContact.state']");
	By emergencyconCountry = By
			.xpath("//select[@id='currentEmergencyContact.country']");
	By emergencyconZipCode = By
			.xpath("//input[@id='currentEmergencyContact.zipCode']");

	//Military Service
	By militaryServiceYes = By.xpath("//input[@name='military.hasServedInTheMilitary' and @value='true']");

	By profilepage = By.linkText("Profile");
	By branchservedlink = By.id("military.branch");
	By dishargeStatuslink = By.id("military.dischargeStatus");
	By ranklink = By.id("military.rank");
	By serviceStartDatelink = By.id("military.startDate");
	By serviceEndDatelink = By.id("military.endDate");
	By veteranstatusboxOther = By.xpath(".//*[@id='military.statusNA-nativeHtmlElement']");

	// Direct Deposit
	By directDepositYes = By
			.xpath("//input[@name='directDepositAsk' and @value='true']");
	By selectaccountType = By.id("currentDirectDeposit.accountType");
	By routingNumber = By.id("currentDirectDeposit.routingNumber");
	By accountNumber = By.id("currentDirectDeposit.accountNumber");
	By selecthowmuchdepositedselctbox = By
			.id("currentDirectDeposit.depositType");
	By specifyamount = By.id("currentDirectDeposit.amount");
	By getStartedButton = By.xpath("//*[text()='Get Started']");
	By getStartedMemberRole = By.cssSelector("button[class='btn btn-success']");
	String commtabs = "//div[@id='messageCenterDeliveryType-wrapper']";

	By getStartedButtonNew = By.cssSelector("a.btn.btn-primary");
	// Locators after Switching to New UI
	By getStartedMemberRoleInNewUI = By.xpath(".//*[@id='bfCarousel']/div/div/div/div/div[2]/div[2]/a/span");
	By verifyProfilePageInNewUI = By.xpath("//*[@id='personalAndContact-header']/span[1]");
	By personalInformationSaveOrNext = By.id("personalInformationSaveOrNext");
	By emergencyContactSaveOrNext = By.id("emergencyContactSaveOrNext");
	By militarySaveOrNext = By.id("militarySaveOrNext");
	By submitFormInPersonalInfoInNewUI = By.id("submitForm");
	By directDepositSaveOrNext = By.id("directDepositSaveOrNext");
	By directDepositAddAnother= By.id("directDepositAddAnother");
	By getStartedButtonMemberRole = By.linkText("Get Started");
	By sectionComplete= By.xpath("//a[@id='directDeposit-header']//span[text()='Section complete']");

	By consentField = By.id("communicationConsentInd-wrapper");
	By consentCheckbox = By.id("communicationConsentInd-nativeHtmlElement");
	By effectiveDate = By.id("effectiveDate");
	By commOpCheckBox = By.id("messageCenterDeliveryType[DC]");
	By commEmailSelection = By.id("emailOptionForCarrier");
	By commEmail = By.xpath("//span[text()='Email']");
	By continueWithoutSavingPopUp = By.id("confirmSwitchSection");
	By personalInfoTitle = By.xpath("//span[contains(text(),'demographic')]");
	By ssn = By.id("SSN");
	By personalInfo = By.xpath("//a[@id='personalAndContact-header']");
	By personalMail = By.id("personalEmailOption");
	By workMail = By.id("workEmailOption");
	By planCost = By.xpath("//div[@class='text-right']/span");

	By retirementAmount = By.name("flatDeferral");
	By retirementPercentage = By.name("percentDeferral");

	By personalInformationSuccessMessage = By.xpath("//a[@id='personalAndContact-header']//span[text()='Section complete']");
	By emegrgencyContatc =By.xpath("//a[@id='emergencyContact-header']//span[text()='Section complete']");
	By directDepositheader = By.xpath("//a[@id='directDeposit-header']//span[text()='Section complete']");

    By w4Allocations = By.id("w4");
	By claimExemptionFederal = By.name("w4.exemptFederal");
	By claimExemptionState = By.name("w4.exemptState");
	By federalStatus = By.id("w4.statusFederal");
	By federalAllowances = By.id("w4.numberExemptionsFederal");
	By stateStatus = By.id("w4.statusState");
	By stateAllowances = By.id("w4.numberExemptionsState");
	By state = By.id("w4.state");
    By dateField = By.id("w4.effDate");

	// click 'GetStarted' button on Member Home Page
	private void clickGetStartedbuttonOnMemberHomePage() {
		if (performAction.isElementPresent(getStartedMemberRole))
			performAction.click(getStartedMemberRole, "Get Started Button");
		else if (performAction.isElementPresent(getStartedButtonNew))
			performAction.click(getStartedButtonNew, "Get Started Button in new UI");
		else
			performAction.click(getStartedButton, "Get Started Button");

	}

	// click 'Next' button for Personal Information Tab on Member Profile Page
	private void clickPersonalInformationNextButton() {
		performAction.jsclick(personalInformationNextButton,
				"Next Button on Personal Information Tab");
	}

	// click 'Next' button for Emergency Contact Tab on Member Profile Page
	private void clickEmergencyContactNextButton() {
		performAction.jsclick(emergencyContactNextButton,
				"Next Button on Embergency Contact Tab");
	}

	// click 'Next' button for Military Service Tab on Member Profile Page
	private void clickMilitaryServiceNextButton() {
		performAction.jsclick(militaryServiceNextButton,
				"Next Button on Military Services Tab");
	}

	// click 'Next' button for Direct Deposit Tab on Member Profile Page
	private void clickDirectDepositNextButton() {
		performAction.jsclick(directDepositNextButton,
				"Next Button on direct Deposit Tab");
	}

	// click 'Save' button on Member Profile Page
	private void clickProfilePageSaveButton() throws InterruptedException {
		// Thread.sleep(3000);
		performAction.waitForPageLoad();
		performAction.click(profilePageSaveButton,
				"Save Button on Member Profile Page");
	}

	//
	private void clickEmergencyContact() {
		performAction.jsclick(emergencyContact, "Emergency Contact");
	}

	// click directdeposit
	private void clickDirectDeposit() {
		performAction.jsclick(directDeposit, "Emergency Contact");

	}

	// click directdeposit
	private void clickMilitaryService() {
		performAction.jsclick(militaryServices, "Military Services Link");

	}

	// personal Inforamtion Fields
	// LastName
	private void lastname(String stalastName) {
		performAction.clearEnter(lastName, stalastName, "LastNameField");
	}

	// Address1 Field
	private void address1(String straddress1) {

		performAction.clearEnter(address1, straddress1, "Address1 Field");
	}
	//Addres2 Field
	private void address2(String straddress2) {

		performAction.clearEnter(address2, straddress2, "Address2 Field");
	}

	// ZipCode
	private void zipCode(String strzipCode) {

		performAction.clearEnter(zip, strzipCode, "zipCode Field");
	}

	// SaveButton
	private void saveButton() {

		performAction.click(savebutton, "Personal Inforamtion Save Button");
	}

	private void clearEnterRelationship(String strRelationship) {
		performAction.clearEnter(relationship, strRelationship, "Relationship");
	}

	private void clearEnterFullName(String strFullName) {
		performAction
		.clearEnter(emergencyconFullName, strFullName, "Full Name");
	}

	private void clearEnterEmail(String strEmail) {
		performAction.clearEnter(emergencyconEmail, strEmail, "Email");
	}

	private void selectState(String strState) {
		performAction.select(emergencyconState, strState, "State");
	}

	private void selectCountry(String strCountry) {
		performAction.select(emergencyconCountry, strCountry, "Country");
	}

	private void clearEnterPhoneNumber(String strPhoneNumber) {
		performAction.clearEnter(emergencyconPhone, strPhoneNumber,
				"Phone Number");
	}

	private void clearEnterAlternatePhoneNumber(String strAlterPhoneNumber) {
		performAction.clearEnter(emergencyconPhone, strAlterPhoneNumber,
				"Phone Number");
	}

	// Direct Deposit Account Type
	private void selectdirectdepositAccountType(String setaccountType) {

		performAction.select(selectaccountType, setaccountType, "Account Type");
	}

	// Direct Deposit Account Type
	private void directDepositRoutingNumber(String setroutingNumber) {

		performAction.clearEnter(routingNumber, setroutingNumber,
				"Routing Number");
	}

	// Direct Deposit accountNumber
	private void directDepositAccountNumber(String setAccountNumber) {

		performAction.clearEnter(accountNumber, setAccountNumber,
				"Account Number");
	}

	// Direct Deposit accountNumber
	private void selectHowMuchDeposit(String selecthowmuchdeposited) {
		String userinput = selecthowmuchdeposited;

		if (userinput.equals("Fixed")) {
			performAction.select(selecthowmuchdepositedselctbox,
					selecthowmuchdeposited, " How much Deposited");
			performAction.clearEnter(specifyamount, "10.00", "Specify Amount");
		}
		performAction.select(selecthowmuchdepositedselctbox,
				selecthowmuchdeposited, " How much Deposited");

	}

	//Setter methods for new UI

	private void clickGetStartedbuttonOnMemberHomePageInNewUI() {
		WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getStartedMemberRoleInNewUI));
		//		browser.getCurrentWebDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		performAction.jsclick(getStartedMemberRoleInNewUI, "Get Started Button");		

	}

	// click 'Next' button for Personal Information Tab on Member Profile Page
	private void clickPersonalInformationNextButtonInNewUI() {
		performAction.jsclick(personalInformationNextButton,
				"Next Button on Personal Information Tab");
	}

	// click 'Next' button for Emergency Contact Tab on Member Profile Page
	private void clickEmergencyContactNextButtonInNewUI() {
		performAction.jsclick(emergencyContactSaveOrNext,
				"Next Button on Embergency Contact Tab");
	}

	// click 'Next' button for Military Service Tab on Member Profile Page
	private void clickMilitaryServiceNextButtonInNewUI() {
		performAction.jsclick(militarySaveOrNext,
				"Next Button on Military Services Tab");
	}

	// click 'Next' button for Direct Deposit Tab on Member Profile Page
	private void clickDirectDepositNextButtonInNewUI() {
		performAction.jsclick(directDepositSaveOrNext,
				"Next Button on direct Deposit Tab");
	}

	// click 'Save' button on Member Profile Page
	private void clickProfilePageSaveButtonInNewUI() throws InterruptedException {
		performAction.waitForPageLoad();
		performAction.click(submitFormInPersonalInfoInNewUI,
				"Save Button on Member Profile Page");
	}
	// effective date filed
	private void enterEffectiveDate(String strDate) {
		performAction
		.clearEnter(effectiveDate, strDate, "Enter effective date");
	}

	// Click on Next Button
	private void clickNextButton() {
		performAction.click(nextButton, "Click on Next Button");
	}

	// perform Comm Opt
	private void performCommOpt(String StrOpt) {
		if (StrOpt.toLowerCase().equalsIgnoreCase("yes")) {
			performAction.click(commOpCheckBox, "communication Opt Check Box");
		} else {
			logger.info("No action performed in communication OPt Check box");
		}
	}
	//select communication preferences drop downs
	private void performCommEmailSelection(String strOption) {
		performAction.click(commEmail, "Email Button");
		if(strOption != ""){
			logger.info("Input is given");

		}
		if(strOption.equalsIgnoreCase("Personal Email")){
			performAction.select(commEmailSelection, strOption, "Email selection In comm preferences");
			logger.info("NO input given in the JSON File");
		}
	}

	// Select Branch Served
	private void selectBranchServed(String branchserved) {
		performAction.select(branchservedlink, branchserved, "Branch Most Recently Served");
	}

	// Select Discharge Status
	private void selectDishargeStatus(String dishargeStatus) {
		performAction.select(dishargeStatuslink, dishargeStatus, "Discharge Status");
	}

	//Select Rank
	private void selectRank(String rank) {
		performAction.clearEnter(ranklink, rank, "Rank");
	}

	//Select Service Start date
	private void selectServiceStartDate(String serviceStartDate) {
		performAction.enter(serviceStartDatelink, serviceStartDate, "Service Start Date");
	}

	//Select Service End date
	private void selectServiceEndDate(String serviceEndDate) {
		performAction.enter(serviceEndDatelink, serviceEndDate, "Service Start Date");
	}

	//Select Veteran Status
	private void selectVeteranStatus() {
		performAction.click(veteranstatusboxOther, "Veteran Status checkbox");
	}

	private void selectMailInfo(String mailInfo){
		String userinput = mailInfo;

		logger.info("Mail Input is given"+userinput);
		if (userinput.equals("Personal Email")) {
			//   By.id("personalEmailOption")
			// driver.FindElement(By.Id("someid")).click();
			browser.getCurrentWebDriver().findElement(personalMail).click();
		}
		else if (userinput.equals("Work Email")) {
			browser.getCurrentWebDriver().findElement(workMail).click();
		}
	}

	private void selectStateInPersonalContact(String strState) {
		performAction.select(state, strState, "State");
	}

	private void selectCountryInPersonalContact(String strCountry) {
		performAction.select(country, strCountry, "Country");
	} 

	private void clickPersonalInfo() {
		performAction.click(personalInfo, "Personal Info");
	}

	private void updatePersonalInfoafterEnrolement(){
		try{
			if(performAction.isElementPresent(verifyProfilePageInNewUI)){
				performAction.jsclick(verifyProfilePageInNewUI, "Personal Information Link");
				if(performAction.isDisplayed(continueWithoutSavingPopUp,"Continue Without Saving Pop Up Link"))
				{
					performAction.click(continueWithoutSavingPopUp, "Continue Without Saving Pop Up Link");
				}
			}
			if (browser.hMap.get("address1") != null) {

				Assert.assertEquals(browser.hMap.get("address1"), browser.getCurrentWebDriver().findElement(address1).getAttribute("value"));
			}
			if (browser.hMap.get("address2") != null) {
				Assert.assertEquals(browser.hMap.get("address2"), browser.getCurrentWebDriver().findElement(address2).getAttribute("value"));
			}

			scr.capturePageScreenshot();

		}catch(Exception ex)
		{
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in update PersonalInformationInNewMemberRole "
							+ ex.getMessage());
		}

	}

	private void addMaximumNumberOfDirectDeposit(String noofAccounts, String directdeposit) throws InterruptedException {

		if(noofAccounts.startsWith("td:")){
			noofAccounts = utils.getValue(noofAccounts);
		}
		int noofAs = Integer.parseInt(noofAccounts);


		do {
			for (int i = 1; i <= (noofAs-1); i++) {
				// Click on Profile
				navigations.navigateToNewMemberMenu("Profile");
				// click on Direct Deposit Link
				performAction.jsclick(directDeposit, "DirectDeopsitLink");

				if (performAction.isElementPresent(directDepositAddAnother)
						&& performAction.isDisplayed(directDepositAddAnother,
								"AddAnother button")) {
					performAction.click(directDepositAddAnother,
							"AddAnother button");
				}
				if (directdeposit.startsWith("td:"))
					directdeposit = directdeposit.substring(3);
				Object object = null;
				JSONObject fields = ReadJsonTestData.getTestData(directdeposit
						.toLowerCase());

				object = fields.get("accounttype");
				if (object != null) {
					this.selectdirectdepositAccountType(object.toString());
					browser.hMap.put("accounttype", object.toString());
				}

				// enter routing number
				object = fields.get("routingnumber");
				if (object != null) {
					this.directDepositRoutingNumber(object.toString());
					browser.hMap.put("routingnumber", object.toString());
				}

				// enter account number
				object = fields.get("accountnumber");
				if (object != null) {
					this.directDepositAccountNumber(object.toString());
					browser.hMap.put("accountnumber", object.toString());
				}

				// how much deposited drop down
				object = fields.get("howmuchdeposit");
				if (object != null) {
					this.selectHowMuchDeposit(object.toString());
					browser.hMap.put("howmuchdeposit", object.toString());
				}
				performAction.click(directDepositNextButton,
						"Click on DirectDeposit Next Button");
				//Click save button
				this.clickProfilePageSaveButtonInNewUI();

				boolean flag = performAction.isElementPresent(sectionComplete);
				if (flag) {
					logger.info("Direct Deposit Success Message : "
							+ browser
							.getCurrentWebDriver()
							.findElement(sectionComplete)
							.getText());

				}
			}
			scr.capturePageScreenshot();
		} while (performAction.isElementPresent(directDepositAddAnother));

		performAction.click(directDepositNextButton,
				"Click on DirectDeposit Next Button");
	}
	// set the personal email text box
	private void setPersonalEmailField(String strEmail) {
		performAction.enter(personalEmail, strEmail, "Personal Email Text Box");
	}


	private String getPlanRate() {			
		return browser.getCurrentWebDriver().findElement(planCost).getText();
	}

	// Enter the retirementAmount and retirementPercentage in text box		
	private void setRetirementAmount(String strValue) {
		performAction.clearEnter(retirementAmount, strValue, "contribution amount textfield");
	}

	private void setRetirementPercentage(String strValue) {
		performAction.clearEnter(retirementPercentage, strValue, "contribution percentage textfield");
	}

	// click on Edit Contribution Button
	private void latestWindow(String strWindowName, String strMessage) throws InterruptedException {
		//performAction.waitUntilElementPresent(strWindowName);
		Thread.sleep(80000);
		performAction.selectLatestWindow(strWindowName);
		logger.info("selected window");
		//assert.assertFalse
		enrollogin.verifyMessageNotExist(strMessage);
	}
	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description   : 'verifyMemberProfilePage' used to run through the
	 * Member Profile pages and to verify the Personal Information, Emergency
	 * Contact, Military Services, Direct Deposit Information in Member Role
	 * based on the test data input 'memberprofilepages' from json file.
	 * 
	 * Role : Member Role
	 *
	 * PreCondition  : Employee should be in Profile Page
	 *
	 * PostCondition : Member profile page should be verified successfully.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | JSONTagName |
	 * | expectedProfilePages |
	 * 
	 * Java File Name : MemberProfilePage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"classification"})
	public void verifyMemberProfilePage(String expectedProfilePages) {

		try {
			Object object = null;
			JSONObject fields = ReadJsonTestData
					.getTestData(expectedProfilePages);

			object = fields.get("personalinfomration");
			if ((object != null) && object.toString().equalsIgnoreCase("yes")) {

			}
			object = fields.get("emergencycontact");
			if ((object != null) && object.toString().equalsIgnoreCase("yes")) {
				// performAction.isElementPresent(emergencyContactNextButton);
				// Thread.sleep(2000);
				this.clickEmergencyContactNextButton();
				// Thread.sleep(2000);
				performAction.waitForPageLoad();
				performAction.isElementPresent(verifyECSuccessMsg);
			}

			object = fields.get("militaryservices");
			if ((object != null) && object.toString().equalsIgnoreCase("yes")) {
				// performAction.isElementPresent(militaryServiceNextButton);
				// Thread.sleep(2000);
				this.clickMilitaryServiceNextButton();
				// Thread.sleep(2000);
				performAction.waitForPageLoad();
				performAction.isElementPresent(verifyMSSuccessMsg);
			}

			object = fields.get("directdeposit");
			if ((object != null) && object.toString().equalsIgnoreCase("yes")) {
				this.clickDirectDepositNextButton();
				performAction.waitForPageLoad();
				performAction.isElementPresent(verifyDDSuccessMsg);
			}

			this.clickProfilePageSaveButton();

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception while verifying the member profile pages "
							+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description   : 'addPersonalInformationInMemberRole' used to to update the personal information in my profile page
	 * in Member Login
	 * 
	 * Role :  Member Role
	 *
	 * PreCondition  : Employee should be in Profile Page
	 *
	 * PostCondition : It will display the Succesfully update the PersonalInformationInMemberRole.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | JSONTagName        |
	 * | personalinformation |
	 * 
	 * Java File : MemberProfilePage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"personalinformation"})
	public void addPersonalInformationInMemberRole(String personalInformation) {
		try {

			if (personalInformation.startsWith("td:"))
				personalInformation = personalInformation.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData
					.getTestData(personalInformation.toLowerCase());

			// click on Emergency contact
			if (performAction.isElementPresent(emergencyContact)) {
				String status = browser.getCurrentWebDriver()
						.findElement(emergencyContact)
						.getAttribute("aria-hidden");
				if (status == "true") {
					this.clickEmergencyContact();
				}
			}

			// click on personal information link
			performAction.click(
					By.xpath("//a[@id='personalAndContact-header']"),
					"Persnal Information link");

			// last name
			object = fields.get("lastname");
			if (object != null) {
				this.lastname(object.toString());
				browser.hMap.put("lastname", object.toString());
			}

			// address1
			object = fields.get("address1");
			if (object != null) {
				this.address1(object.toString());
				browser.hMap.put("address1", object.toString());
			}
			//Address2
			object = fields.get("address2");
			if (object != null) {
				this.address2(object.toString());
				browser.hMap.put("address2", object.toString());
			}

			// enter account number
			object = fields.get("zipcode");
			if (object != null) {
				this.zipCode(object.toString());
				browser.hMap.put("zipcode", object.toString());
			}
			// enter the effective date
			object = fields.get("effectivedate");
			if (object != null) {
				String date = utils.getDate(object.toString());
				this.enterEffectiveDate(date);
			}
			performAction.click(personalInfoTitle, "Personal Information Title");
			// Consent check box
			object = fields.get("communicationopt");
			if (object != null) {
				this.performCommOpt(object.toString());
			}

			object = fields.get("emailcomm");
			if (object != null) {
				this.performCommEmailSelection(object.toString());
				browser.hMap.put("emailcomm", object.toString());
			}
			scr.capturePageScreenshot();
			// click on Personal information save button
			if(performAction.isElementPresent(personalInformationNextButton))
				performAction.jsclick(personalInformationNextButton, "Personal Information Save Button");
			else
				this.saveButton();
			// print the personal information Success message in console screen.
			boolean flag = performAction.isElementPresent(directDepositheader);
			if (flag) {
				logger.info("Personal Information Success Message : "+ browser.getCurrentWebDriver().findElement(personalInformationSuccessMessage).getText());
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in add PersonalInformationInMemberRole"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Bhavan Mettu
	 * 
	 * Role: Member role
	 *
	 * Description   : 'updatePersonalInfoAfterEnrollementInMemberRoleInNewUI' used to to update the personal information in my profile page after employee finishes his/her enrollment
	 *
	 * PreCondition  : Employee should be in Profile Page
	 *
	 * PostCondition : It will display the Successfully update the PersonalInformationInMemberRole.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | JSONTagName        |
	 * | personalinformation |
	 * 
	 * Java file name : MemberProfilePage.java
	 * </pre>
	 * @throws InterruptedException 
	 **/
	@RobotKeyword
	@ArgumentNames({"personalinformation"})
	public void updatePersonalInfoAfterEnrollementInMemberRoleInNewUI(String personalInformation) throws InterruptedException {
		try{

			this.addPersonalInformationInMemberRole(personalInformation);
			this.updatePersonalInfoafterEnrolement();
			this.clickEmergencyContact();
			this.clickMilitaryService();
			this.clickDirectDeposit();

			this.clickProfilePageSaveButtonInNewUI();
			performAction.waitForPageLoad();
		}catch(Exception ex)
		{
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in update PersonalInformationInNewMemberRole "
							+ ex.getMessage());
		}

	}

	public void profileInformation(String strJsonTestDataKey) {

		Object object = null;
		JSONObject fields = ReadJsonTestData.getTestData(strJsonTestDataKey);

		this.clickPersonalInformationNextButton();
		// Thread.sleep(2000);
		performAction.isElementPresent(verifyPISuccessMsg);
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description   : 'addEmergencyContact' used to add Emergency contact in My Profile page as Member Login
	 * 
	 * Role : Member Role
	 *
	 * PreCondition  : Employee should be in Profile Page
	 *
	 * PostCondition : It will display updated work information.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | JSONTagName |
	 * | emergencyContact |
	 * 
	 * Java File Name : MemberProfilePage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"emergencycontact"})
	public void addEmergencyContactInMemberRole(String emergencycontact) {
		try {

			if (emergencycontact.startsWith("td:"))
				emergencycontact = emergencycontact.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(emergencycontact
					.toLowerCase());

			this.clickEmergencyContact();

			// click on Yes Rasdio Button
			performAction.click(emgergencyContactYes,
					"Emergency Contact Yes Radio Button");

			object = fields.get("relationship");
			if (object != null) {
				this.clearEnterRelationship(object.toString());
				browser.hMap.put("relationship", object.toString());
			}
			object = fields.get("fullname");
			if (object != null) {
				this.clearEnterFullName(object.toString());
				browser.hMap.put("fullname", object.toString());
			}
			object = fields.get("phonenumber");
			if (object != null) {
				this.clearEnterPhoneNumber(object.toString());
				browser.hMap.put("phonenumber", object.toString());
			}
			object = fields.get("alternatephonenumber");
			if (object != null) {
				this.clearEnterAlternatePhoneNumber(object.toString());
				browser.hMap.put("alternatephonenumber", object.toString());
			}
			object = fields.get("email");
			if (object != null) {
				this.clearEnterEmail(object.toString());
				browser.hMap.put("email", object.toString());
			}
			object = fields.get("state");
			if (object != null) {
				this.selectState(object.toString());
				browser.hMap.put("state", object.toString());
			}
			object = fields.get("country");
			if (object != null) {
				this.selectCountry(object.toString());
				browser.hMap.put("country", object.toString());
			}
			// click on Emergency Contact Next Button
			performAction.click(emergencyContactNextButton,
					"Click on Next Button");

			boolean flag = performAction.isElementPresent(emegrgencyContatc);

			if (flag) {
				logger.info("Emergency Contact Success Message : "
						+ browser.getCurrentWebDriver().findElement(emegrgencyContatc).getText());
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in add Emergency Contact Employee"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Abhijith Desai
	 *  
	 * Description   : 'addMilitaryServiceInformationInMemberRole' used to  Military Information in My Profile page as Member Login
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition  : Employee should be in Profile Page
	 * 
	 * PostCondition : It will display updated military information.
	 * 
	 * Java File Name: MemberProfilePage.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | JSONTagName        |
	 * | militaryinformation |
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "militaryinformarion" })
	public void addMilitaryServiceInformationInMemberRole(String militaryserviceinfo){
		try{
			if(militaryserviceinfo.startsWith("td:"))
				militaryserviceinfo = militaryserviceinfo.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(militaryserviceinfo
					.toLowerCase());

			//Click on Profile link
			performAction.click(profilepage, "Profile Link");

			this.clickPersonalInformationNextButtonInNewUI();

			Thread.sleep(2000);

			//Click on Military Service link
			performAction.click(militaryServices, "Military Service Link");

			//performAction.waitUntilElementPresent(militaryServiceYes);


			//Click on Military Service Yes Radio Button
			performAction.click(militaryServiceYes,
					"Miilitary Service Yes Radio Button");

			// select branch served dropdown value
			object = fields.get("branchserved");
			if (object != null) {
				this.selectBranchServed(object.toString());
				browser.hMap.put("branchserved", object.toString());
			}

			// select Discharge Status dropdown value
			object = fields.get("DishargeStatus");
			if (object != null) {
				this.selectDishargeStatus(object.toString());
				browser.hMap.put("DishargeStatus", object.toString());
			}

			// select Rank value
			object = fields.get("Rank");
			if (object != null) {
				this.selectRank(object.toString());
				browser.hMap.put("Rank", object.toString());
			}

			// Select service start date
			object = fields.get("ServiceStartDate");
			if (object != null) {
				this.selectServiceStartDate(object.toString());
				browser.hMap.put("ServiceStartDate", object.toString());
			}

			// Select Service end date
			object = fields.get("ServiceEndDate");
			if (object != null) {
				this.selectServiceEndDate(object.toString());
				browser.hMap.put("ServiceEndDate", object.toString());
			}

			/*Select Veteran Status
						object = fields.get("VeteranStatus");
						if (object != null) {
							this.selectVeteranStatus();
							//browser.hMap.put("VeteranStatus", object.toString());
						}*/

			//saving the changes
			this.clickMilitaryServiceNextButton();

			//Verifying the changes

			performAction.verify(verifyMSSuccessMsg, "Section complete",
					"Military services section complete");

			scr.capturePageScreenshot();
		}catch(Exception e){
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in add PersonalInformationInMemberRole"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description   : 'addDirectDepositInMemberRole' used to  addDirectDepositInMemberRole in My Profile page as Member Login
	 * 
	 * Role : Member Role 
	 *
	 * PreCondition  : Employee should be in Profile Page
	 *
	 * PostCondition : It will display updated work information.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | JSONTagName |
	 * | directdepositr |
	 * 
	 *  Java File Name : MemberProfilePage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"directdeposit"})
	public void addDirectDepositInMemberRole(String directdeposit) {
		try {

			if (directdeposit.startsWith("td:"))
				directdeposit = directdeposit.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(directdeposit
					.toLowerCase());

			if(performAction.isElementPresent(profilepage)){
				performAction.click(profilepage, "Profile Link");

				this.clickPersonalInformationNextButtonInNewUI();
			}

			// click on Direct Deposit Link
			performAction.click(directDeposit, "DirectDeopsitLink");
			// click on Direct Deposit Radio Button
			performAction.click(directDepositYes,
					"Direct Deposit Yes Radio Button");

			// select account type drop down value
			object = fields.get("accounttype");
			if (object != null) {
				this.selectdirectdepositAccountType(object.toString());
				browser.hMap.put("accounttype", object.toString());
			}

			// enter routing number
			object = fields.get("routingnumber");
			if (object != null) {
				this.directDepositRoutingNumber(object.toString());
				browser.hMap.put("routingnumber", object.toString());
			}

			// enter account number
			object = fields.get("accountnumber");
			if (object != null) {
				this.directDepositAccountNumber(object.toString());
				browser.hMap.put("accountnumber", object.toString());
			}

			// how much deposited drop down
			object = fields.get("howmuchdeposit");
			if (object != null) {
				this.selectHowMuchDeposit(object.toString());
				browser.hMap.put("howmuchdeposit", object.toString());
			}
			performAction.click(directDepositNextButton,
					"Click on DirectDeposit Next Button");
			boolean flag = performAction
					.isElementPresent(directDepositheader);
			if (flag) {
				logger.info("Direct Deposit Success Message : "
						+ browser.getCurrentWebDriver().findElement(directDepositheader).getText());
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in add PersonalInformationInMemberRole"
							+ e.getMessage());
		}
	}

	/**
	 * Keyword or method 'RunMemberProfilePages' used to run through the Member
	 * Profile pages and perform the actions to setup Personal Information,
	 * Emergency Contact , Military Services, Direct Deposit Information in
	 * Member Role.
	 */
	@RobotKeyword
	public void runMemberProfilePages() {

		try {

			this.clickGetStartedbuttonOnMemberHomePage();
			/*Assert.assertTrue("Profile page",
					performAction.isElementPresent(verifyProfilePage));*/

			//performAction.verifyMessage("Profile Page");

			this.clickPersonalInformationNextButton();
			performAction.verify(verifyPISuccessMsg, "Section complete",
					"Personal information section complete");

			this.clickEmergencyContactNextButton();

			performAction.verify(verifyECSuccessMsg, "Section complete",
					"Emergency contact section complete");

			this.clickMilitaryServiceNextButton();

			performAction.verify(verifyMSSuccessMsg, "Section complete",
					"Military services section complete");

			this.clickDirectDepositNextButton();

			//performAction.verify(verifyDDSuccessMsg, "Section complete",
					//"Direct deposit section complete");

			performAction.click(profilePageSaveButton,
					"Profile page save button");

		} catch (Exception e) {
			logger.warn("Exception occured in Profile page verification. "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured in Profile page verification. "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : Keyword or method 'RunMemberProfilePages' used to run through the Member Profile pages and perform the actions to setup Personal Information,Emergency Contact , Military Services, Direct Deposit Information in Member Role.
	 * 
	 * PreCondition  : Should be in Member Profile Page.
	 * 
	 * PostCondition : Able to complete Member Profile.
	 *  
	 *  
	 * <b> Java File Path : com.benefitfocus.robot.member >> MemberProfilePage.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void MemberProfilePages() {

		try {

			this.clickGetStartedbuttonOnMemberHomePage();
			/*Assert.assertTrue("Profile page",
					performAction.isElementPresent(verifyProfilePage));*/

			performAction.verifyMessage("Profile Page");

			this.clickPersonalInformationNextButton();
			performAction.verify(verifyPISuccessMsg, "Section complete",
					"Personal information section complete");

			this.clickEmergencyContactNextButton();

			performAction.verify(verifyECSuccessMsg, "Section complete",
					"Emergency contact section complete");

			this.clickMilitaryServiceNextButton();

			performAction.verify(verifyMSSuccessMsg, "Section complete",
					"Military services section complete");

			this.clickDirectDepositNextButton();

			performAction.verify(verifyDDSuccessMsg, "Section complete",
					"Direct deposit section complete");

			performAction.click(profilePageSaveButton,
					"Profile page save button");

		} catch (Exception e) {
			logger.warn("Exception occured in Profile page verification. "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured in Profile page verification. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *
	 * Description   : 'verifyCommunicationPreferencesInMemberrole' used to  verify the communication preferences in new meember role
	 *
	 * PreCondition  : Employee should be in Profile Page
	 *
	 * PostCondition : It will display configured field in the profile page
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strParam        |
	 * | Text/Mobile/configured field |
	 * 
	 * Java file Name : MemberProfilePage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strParam"})
	public void verifyCommunicationPreferencesInMemberrole(String strParam) {
		try {
			int commCount = browser.getCurrentWebDriver()
					.findElements(By.xpath(commtabs)).size();
			for (int i = 1; i <= commCount; i++) {
				By comLoc = By.xpath("(" + commtabs + ")[" + i + "]//span");
				String commText = browser.getCurrentWebDriver()
						.findElement(comLoc).getText();
				if (strParam.toLowerCase().equalsIgnoreCase(
						commText.toLowerCase())) {
					logger.info(commText + "   is Enabled succesfully ");
					By commInput = By.xpath("(" + commtabs + ")[" + i
							+ "]/..//div[2]");
					if (performAction.isElementPresent(commInput)) {
						logger.info(commText + "  is having input field");
					}
					break;
				}
				/*else{
                    logger.warn(commText + "   is not Enabled succesfully ");
				}*/
			}

			if (performAction.isElementPresent(consentField)) {
				performAction.verifyMessage(strParam);
				Boolean status = performAction
						.isElementPresent(consentCheckbox);
				String checkboxVisible = browser.getCurrentWebDriver()
						.findElement(consentCheckbox).getAttribute("disabled");
				logger.info("check box Visible is "+checkboxVisible);
				if (status == true) {
					logger.info("Consent check box is available");
				}
				if (checkboxVisible == "true") {
					logger.info("Consent checkbox is disabled");
				}

			} else {
				logger.info("Consent Field is not configured");
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			throw new CustomException(
					"Exception occured in communication preferences verification. "
							+ e.getMessage());
		}

	}

	/**
	 * Author  : Bhavan Mettu
	 * <p/>
	 * Description   : Keyword or method 'RunMemberProfilePagesInNewUI' used to run through the Member
	 * Profile pages and perform the actions to setup Personal Information,
	 * Emergency Contact , Military Services, Direct Deposit Information in
	 * Member Role after switching to New UI.
	 * <p/>
	 * Pre condition: UI should be switched to new style
	 * <p/>
	 * Post Condition: Member Personal Information, Emergency Contacts, Military Services
	 * and Direct Deposit information is saved
	 */
	@RobotKeyword
	@ArgumentNames({})
	public void runMemberProfilePagesInNewUI() {

		try {


			this.clickGetStartedbuttonOnMemberHomePageInNewUI();

			Assert.assertTrue("Profile page",
					performAction.isElementPresent(verifyProfilePageInNewUI));

			if (performAction.isElementPresent(personalInformationNextButton)) {
			this.clickPersonalInformationNextButtonInNewUI();
			performAction.verify(verifyPISuccessMsg, "Section complete",
					"Personal information section complete");
			}

			if (performAction.isElementPresent(emergencyContactSaveOrNext)) {
			this.clickEmergencyContactNextButtonInNewUI();
			performAction.verify(verifyECSuccessMsg, "Section complete",
					"Emergency contact section complete");
			}

			if (performAction.isElementPresent(militarySaveOrNext)) {
			this.clickMilitaryServiceNextButtonInNewUI();
			performAction.verify(verifyMSSuccessMsg, "Section complete",
					"Military services section complete");
			}

			if (performAction.isElementPresent(directDepositSaveOrNext)) {
			this.clickDirectDepositNextButtonInNewUI();
			performAction.verify(verifyDDSuccessMsg, "Section complete",
					"Direct deposit section complete");
			}

			if (performAction.isElementPresent(w4Allocations)) {
				performAction.click(claimExemptionFederal, "Click Yes Claim Exemption Federal");
				Thread.sleep(1000);
				performAction.click(claimExemptionState, "Click Yes Claim Exemption State");
				Thread.sleep(1000);
				performAction.select(state, "SC", "Enter State South Carolina");
                Date currentDate = new Date();
                String strmodifiedDate = new SimpleDateFormat("MM/dd/yyyy").format(currentDate);
				performAction.enter(dateField, strmodifiedDate, "Enter Current Date");
			}
			this.clickProfilePageSaveButtonInNewUI();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured in Profile page verification. "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured in Profile page verification. "
							+ e.getMessage());
		}
	}

	/**
	 * Author  : Bhavan Mettu
	 * <p/>
	 * Description   :Keyword or method 'clickGetStartedbuttonOnNewUI' used to run through the Member
	 * <p/>
	 * PreCondition: UI should be switched to new style
	 * <p/>
	 * PostCondition: Get Started button should displayed
	 */
	@RobotKeyword
	@ArgumentNames({})
	public void clickGetStartedbuttonOnNewUI() {

		try {
			this.clickGetStartedbuttonOnMemberHomePageInNewUI();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured while clicking on GetStarted Button in New UI "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while clicking on GetStarted Button in New UI. "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *
	 * Description   : 'verifyCarrierCommunicationVisibility' used to  verify the communication field visibility in new member role
	 *
	 * PreCondition  : Employee should be in Profile Page
	 *
	 * PostCondition : It will display/disable configured field in the profile page
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strOpt        |
	 * | Home Phone/Work Phone/Personal Email|
	 * 
	 * Java file Name : MemberProfilePage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strOpt"})
	public void verifyCarrierCommunicationVisibilityInMemberRole(String strOpt) {

		try {
			if(strOpt.equalsIgnoreCase("Home Phone")){

				String homePhone = browser.getCurrentWebDriver().findElement(homephone).getAttribute("disabled");
				logger.info("field status is "+homePhone);
			}
			else if(strOpt.equalsIgnoreCase("Work Phone")){
				String homePhone = browser.getCurrentWebDriver().findElement(workphone).getAttribute("disabled");
				logger.info("field status is "+homePhone);
			}
			else if(strOpt.equalsIgnoreCase("Personal Email")){
				String homePhone = browser.getCurrentWebDriver().findElement(personalEmail).getAttribute("disabled");
				logger.info("field status is "+homePhone);
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			throw new CustomException(
					"Exception occured in Personal info visibility verification. "
							+ e.getMessage());
		}

	}

	/**
	 * <pre> 
	 * Author  : Rajeswari Nimmala
	 *  
	 * Description : Keyword or method used to add maximum number of accounts in direct deposits
	 * 
	 * Role: VistaRole
	 * 
	 * PreCondition : Employee should be in Profile Page
	 * 
	 * PostCondition :  
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | noofAccounts | 
	 * |  - depending on the maximum accounts of direct deposits in group configuration | 
	 * | directdeposit --JSON tagname|
	 * 
	 * Java File Name: MemberProfilePage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "noofAccounts", "directdeposit" })
	public void maximumNumberOfAccountsInDirectDeposit(String noofAccounts, String directdeposit) {
		try {
			this.addMaximumNumberOfDirectDeposit(noofAccounts, directdeposit);

		} catch (Exception e) {

			logger.warn("Exception occured  "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Srilatha
	 * 
	 * Role: New Member Role
	 *  
	 * Description : Get plan rate after enrollment and verify
	 *  
	 * PreCondition : Member should be member role -> Benefits. 
	 * 
	 * PostCondition : Plan rate should be same as the value passed 
	 * 
	 * Java file Name : MemberProfilePage.java
	 * 
	 * <b>Parameters & Example </b> 
	 * 
	 * | StrPlanRate |
	 * |    |   
	 * </pre> 
	 **/


	@RobotKeyword
	@ArgumentNames({ "StrPlanRate" })
	public void  getAndVerifyPlanRate(String inplanRate) {
		try {
			int rate, rate1;
			String planrate = this.getPlanRate();
			scr.capturePageScreenshot();			
			planrate = planrate.substring(1);
			rate = Double.valueOf(planrate).intValue();

			rate1 = Integer.valueOf(inplanRate);
			Assert.assertTrue("Verification Not Success", rate == rate1);

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occurred while getting plan rate"
					+ e.getMessage());
			throw new CustomException(
					"Exception occurred while getting plan rate "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Srilatha A
	 * 
	 * Role		: 	Member Role
	 *
	 * Description   : 'updatePersonalInformationInMemberRoleInNewUI' used to to update the personal information in Member profile page
	 *
	 * PreCondition  : Employee should be in Member Profile Page
	 *
	 * PostCondition : It will successfully update the Personal Information In Member Role.
	 * 
	 * Java file Name : MemberProfilePage.java
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | JSONTagName        |
	 * | personalinformation |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"personalinformation"})
	public void updatePersonalInformationInMemberRoleInNewUI(String personalInformation) {
		try {

			if (personalInformation.startsWith("td:"))
				personalInformation = personalInformation.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData
					.getTestData(personalInformation.toLowerCase());

			// click on Personal Info contact
			if (performAction.isElementPresent(personalInfo)) {
				String status = browser.getCurrentWebDriver()
						.findElement(personalInfo)
						.getAttribute("aria-hidden");
				if (status == "true") {
					this.clickPersonalInfo();
				}
			}

			// lastname
			object = fields.get("lastname");
			if (object != null) {
				this.lastname(object.toString());
				browser.hMap.put("lastname", object.toString());
			}

			// address1
			object = fields.get("address1");
			if (object != null) {
				this.address1(object.toString());
				browser.hMap.put("address1", object.toString());
			}

			// enter Zip Code
			object = fields.get("zipcode");
			if (object != null) {
				this.zipCode(object.toString());
				browser.hMap.put("zipcode", object.toString());
			}

			object = fields.get("state");
			if (object != null) {
				this.selectStateInPersonalContact(object.toString());
				browser.hMap.put("state", object.toString());
			}
			object = fields.get("country");
			if (object != null) {
				this.selectCountryInPersonalContact(object.toString());
				browser.hMap.put("country", object.toString());
			}

			// enter the effective date
			object = fields.get("effectivedate");
			if (object != null) {
				String date = utils.getDate(object.toString());
				this.enterEffectiveDate(date);
			}

			// enter Personal Email
			object = fields.get("personalmail");
			if (object != null) {
				this.setPersonalEmailField(object.toString());
				browser.hMap.put("personal mail", object.toString());
			}
			object = fields.get("email");
			if (object != null) {
				this.selectMailInfo(object.toString());
			}

			// print the personal information Success message in console screen.
			boolean falg = performAction
					.isElementPresent(By
							.xpath("//a[@id='personalAndContact-header']//span[text()='Section complete']"));
			if (falg) {
				logger.info("Personal Information Success Message : "
						+ browser
						.getCurrentWebDriver()
						.findElement(
								By.xpath("//a[@id='personalAndContact-header']//span[text()='Section complete']"))
								.getText());
			}
			scr.capturePageScreenshot();
			this.clickPersonalInformationNextButtonInNewUI();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception while adding PersonalInformationInMemberRole in New UI " + e.getMessage());
			throw new CustomException(
					"Exception while adding PersonalInformationInMemberRole in New UI"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Srilatha A
	 *  
	 *  Role: New Member Role
	 *  
	 * Description   : Enter the contribution value for Beneficiary
	 * 
	 * PreCondition  : Contribution value page after entering beneficiary value
	 * 
	 * PostCondition : Amount should reflect in Employee Detail page
	 * 
	 * Java file Name : MemberProfilePage.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strValue  |
	 * | 100    |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strValue" })
	public void enterContributionValueForRetirementPlan(String strValue) {

		try {

			if (performAction.isElementPresent(retirementAmount)) {
				setRetirementAmount(strValue);
			}

			if (performAction.isElementPresent(retirementPercentage)) {
				setRetirementPercentage(strValue);
			}
			scr.capturePageScreenshot();
			this.clickNextButton();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception while entering contribution amount for retirement plan " + e.getMessage());
			throw new CustomException(
					"Exception while entering contribution amount for retirement plan"
							+ e.getMessage());
		}

	}


	/**
	 * <pre> 
	 * Author  : Rajeswari Nimmala
	 *  
	 * Description :  keyword  is used to switch to report in new member role and verify message not exist.
	 *
	 * Role: Member Role
	 * 
	 * PreCondition : Member should be in Summary page. 
	 * 
	 * PostCondition : member report page and verification performed
	 * 
	 * <b>Parameters & Example </b> 
	 * 
	 * | strWindowTitle - Common text which present in report  || strMessage - Text that verification to be performed  |
	 * Java File Name: HSAContributionsPage.java  
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strWindowTitle", "strMessage" })
	public void handleEmployeeReport(String strWindowTitle, String strMessage) {
		try {
			this.latestWindow(strWindowTitle, strMessage);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("latest window not found"+ e.getMessage());
		}
	}
}
