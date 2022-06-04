package com.benefitfocus.robot.member;

import java.util.List;
import junit.framework.Assert;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.python.jline.internal.Log;
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
public class MemberHomePage {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Utilities utils;

	@Autowired
	protected Logging logger;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Screenshot scr;

	By verifyWelcomeMsg = By.cssSelector("h1");
	By verifyCountDown = By
			.cssSelector("span[class*='emphasizedInformationFragment']");
	By getStartedButton = By.cssSelector("button.btn.btn-success");
	By getStartedButton1 = By.xpath("//*[text()='Get Started']");
	By getStartedButton2 = By.xpath("//*[text()='Get started']");

	By verifyHomePage = By.cssSelector("a[href*='goToHomePage']");

	By verifyProfilePage = By.linkText("1 Profile");
	By personalInformationNextButton = By.id("personalInformationSaveOrNext");
	By verifyPISuccessMsg = By
			.xpath("//div[@class='accordion-group '][1]//span[@class='supplementalText']");
	By emergencyContactNextButton = By.id("emergencyContactSaveOrNext");
	By verifyECSuccessMsg = By
			.xpath("//div[@class='accordion-group '][2]//span[@class='supplementalText']");
	By militarySrviceNextButton = By.id("militarySaveOrNext");
	By verifyMSSuccessMsg = By
			.xpath("//div[@class='accordion-group '][3]//span[@class='supplementalText']");
	By directDepositNextButton = By.id("directDepositSaveOrNext");
	By verifyDDSuccessMsg = By
			.xpath("//div[@class='accordion-group '][4]//span[@class='supplementalText']");

	By profilePageSaveButton = By.id("submitForm");
	By addDependentsPageNextButton = By.id("next");
	By carouselLeftButton = By.xpath("//a[@class='carousel-control left']");
	String carouselList = "//ol[@class='carousel-indicators']";
	By getStartedOnCarousel = By.xpath("//a[@class='btn btn-warning']");
	By nextButtonAddDependents = By.xpath("//button[contains(text(),'Next')]");

	// New Member role
	By getStartedButtonNew = By.cssSelector("a.btn.btn-primary");
	/*//span[contains(text(),'Get Started')]*/
	//i[@class='bficon bficon-angle-right bficon-1x']

	By skipGuidedShopping = By.xpath("//span[contains(text(),'Skip')]");
	By beginEnrollmentButton = By
			.xpath("//a[contains(text(),'Begin enrollment')]");
	By dependentLink = By.linkText("Dependents");
	By closeHRNote = By.xpath("//button[contains(Text(),'Close')]");
	By hrNotePanel = By.id("hr-note");
	By hrNotePanelNewMemberRole = By.id("hr-note-label");
	By closeHrNotePanel = By.className("close");
	String carouselNoteslList = "//div[@class='panel-body carousel-inner pan']/div";
	By carouselImage = By
			.xpath("//div[@class='panel-body carousel-inner pan']/div[1]//img");
	By carouselButtonText = By
			.xpath("//div[@class='panel-body carousel-inner pan']/div[1]//span");
	By carouselHeader = By
			.xpath("//div[@class='panel-body carousel-inner pan']/div[1]//h2");
	By successBanner = By.xpath("//div[@class='alert-message-banner']");
	By successMessage = By.xpath("//div[@class='alert-message-banner']//h3");
	By carousel = By.xpath("//div[@id='bfCarousel']");
	By startButton = By.xpath("//button[text()='Start ']");
	//Change Password locators	
	By loginInformation = By.linkText("Login Information");
	By nextBtn = By.xpath("//button[contains(text(),'Next')]");
	By passwordEditBtn = By.id("PasswordEditor");
	By newPasswordTxt = By.id("newPassword");
	By confirmPasswordTxt = By.id("newConfirmPassword");
	By userNameSaveBtn = By.xpath("//fieldset[@id='password']//button[contains(text(),'Save')]");
	By passwordSaveBtn = By.xpath("//fieldset[@id='password']//button[contains(text(),'Save')]");
	By passwordCancelBtn = By.xpath("//fieldset[@id='password']//button[contains(text(),'Cancel')]");
	By employeeDetailReportLink = By.xpath("//span[contains(text(),'Employee Detail Report')]");
	By verifySSNMask = By.xpath("//div[@id='SSN-wrapper']//input[@id='SSN']");
	By toggleMenuHideOrShow = By.xpath("//*[@id='branding-bar']/div/button/span");
	By sideMenuPanel = By.id("//*[@id='main-nav']");
	By myDocumentsLink = By.linkText("My Documents");
	By languagePreferencesMenuItem = By.xpath("//*[@id='main-nav']/div[1]/nav/div[1]/ul/li[3]/a/span");


	By videoPlayer = By.id("video-overlay-player");
	By employeeSummaryReport=By.linkText("Employee Summary Report");
	By close = By.xpath("//button[@class='close']//span");
	By errorMsg = By.id("errors");

	By liveOnlineAssistance = By.id("liveOnlineAssistanceLinkImage");
	By liveAssistanceConfirmation = By.xpath("//div[@class='modal-header']//span[contains(text(),'Live Online Assistance')]");
	By liveAssistanceCancelBtn = By.xpath("//div[@class='modal-footer']//button[contains(text(),'Cancel')]");
	By liveAssistanceChatNowBtn = By.xpath("//div[@class='modal-footer']//button[contains(text(),'Chat now')]");
    By switchUI=By.linkText("Switch UI Style");
	//Change Username Locators
	By usernameEditBtn = By.id("UserNameEditor");
	By newUsernameTxt = By.id("newUserId");
	By confirmUsernameTxt = By.id("confirmNewUserId");
	By usernameSaveBtn = By.xpath("//fieldset[@id='username']//button[contains(text(),'Save')]");
	By usernameCancelBtn = By.xpath("//fieldset[@id='username']//button[contains(text(),'Cancel')]");
	By currentUserIdTxt = By.id("currentUserId");
	//Variables used in public methods

	String ssn = "";

	// click 'GetStarted' button on Member Home Page
	private void clickGetStartedbuttonOnMemberHomePage() {
		performAction.click(getStartedButton, "Get Started Button");
	}

	// click 'Next' button for Personal Information Tab on Member Profile Page
	private void clickPersonalInformationNextButton() {
		performAction.jsclick(personalInformationNextButton,
				"Next Button on Personal Information Tab");
	}

	// click 'Next' button for Emergency Contact Tab on Member Profile Page
	private void clickEmergencyContactNextButton() {
		performAction.click(emergencyContactNextButton,
				"Next Button on Embergency Contact Tab");
	}

	// click 'Next' button for Military Service Tab on Member Profile Page
	private void clickMilitaryServiceNextButton() {
		performAction.click(militarySrviceNextButton,
				"Next Button on Military Services Tab");
	}

	// click 'Next' button for Direct Deposit Tab on Member Profile Page
	private void clickDirectDepositNextButton() {
		performAction.click(directDepositNextButton,
				"Next Button on direct Deposit Tab");
	}

	// click Get Started button in carousel pages in Member Home Page
	private void clickCarouselButton() {
		performAction.click(carouselLeftButton, "carousel Left button");
	}

	private void clickGetStartedOnCarousel() {
		performAction.click(getStartedOnCarousel, "Get Started on Carousel");
	}

	private void clickNextAddDependents() {
		performAction.waitUntilElementPresent(nextButtonAddDependents);
		performAction.jsclick(nextButtonAddDependents, "Next Button");
	}

	private void clickBeginEnrollment() {
		performAction.waitUntilElementPresent(beginEnrollmentButton);
		performAction.jsclick(beginEnrollmentButton, "Begin Enrollment");
	}

	private void closeVideoGlossaryItem(){
		performAction.click(close, "close button");
	}

	private void closeAndNavigateBacktoNativeWindow(){
		browser.getCurrentWebDriver().close();
		performAction.selectLatestWindow("Employee Detail Report");
	}

	private void clickandNavigateToEmployeeDetailReport(String strNewWindowTitle){
		performAction.click(employeeDetailReportLink, "Employee Detail Report");                 
		performAction.selectLatestWindow(strNewWindowTitle);
	}

	private void verifyMessage(String strmessage) {
		performAction.verifyMessage(strmessage);
	}


	private void verifyLiveAssistanceConfirmationBox() {
		Assert.assertTrue(performAction.isElementPresent(liveAssistanceConfirmation));
		scr.capturePageScreenshot();
	}

	private void verifyLiveAssistanceCancel() {
		if(performAction.isElementPresent(liveAssistanceCancelBtn))
			performAction.click(liveAssistanceCancelBtn, "Live Assistance Cancel Button");
		scr.capturePageScreenshot();
		performAction.verifyMessage("Home");
	}

	private void verifyLiveAssistanceChatNow() {
		if(performAction.isElementPresent(liveAssistanceChatNowBtn))
			performAction.click(liveAssistanceChatNowBtn, "Live Assistance Chat Now Button");
		performAction.selectLatestWindow("GoToAssist Corporate");
		scr.capturePageScreenshot();
		performAction.waitForPageLoad();
		performAction.verifyMessage("representative");
		browser.getCurrentWebDriver().close();
		performAction.selectLatestWindow("Home");

	}

	/*
	 * // Perform Click on Dependents link in member Home Page private void
	 * clickDependentButton() { performAction.click(dependentLink,
	 * "Dependents Link"); }
	 */

	// Click on close Hr Panel Button
	private void clickCloseHrPanel() {
		if(performAction.isElementPresent(closeHrNotePanel)){
		performAction.click(closeHrNotePanel, "Click on close Hr Panel Button");
		}else{
			performAction.click(closeHRNote, "Click on close Hr Panel Button");
		}

	}

	// Click on Toggle Menu Show or Hide
	private void clickToggleMenuHideOrShow(){
		performAction.jsclick(toggleMenuHideOrShow, "Click on Toggle Menu Hide");
	}

	// Click on My Documents link in Member Home page
	private void clickMyDocumentsLink(){
		performAction.jsclick(myDocumentsLink, "my documents link verified");
	}

	private void clickstrVideoGlossaryItem(String strVideoGlossary)
	{
		By locator=By.linkText(strVideoGlossary);
		performAction.click(locator, "Training Item");	
	}
	// click the PasswordEdit Button
	private void clickPasswordEditbutton(){
		performAction.click(passwordEditBtn, "Password Edit Button");
	}

	// set the newPassword text box
	private void setNewPassword(String strnewPassword) {
		performAction.enter(newPasswordTxt, strnewPassword,
				"New Password Text box");

	}

	// set the newConfirmPassword text box
	private void setConfirmPassword(String strnewPassword) {
		performAction.enter(confirmPasswordTxt, strnewPassword,
				"New Confirm Password Text box");
	}

	// click the PasswordSave Button
	private void clickPasswordSavebutton(){
		performAction.click(passwordSaveBtn, "Save Password Button");
	}
    // Click on Switch UI Link
	private void clickSwitchUILink(){
		if(performAction.isElementPresent(switchUI)){
			performAction.click(switchUI, "Click on Switch UI Link");
		}
	}
	
	// click the PasswordCancel Button
	private void clickPasswordCancelbutton() {
		performAction.click(passwordCancelBtn, "Cancel Password Button");
	}

	// click the UsernameEdit Button
	private void clickUsernameEditbutton() {
		performAction.click(usernameEditBtn, "Username Edit Button");
	}

	// set the newUsername text box
	private void setNewUsername(String strnewUsername) {
		performAction.enter(newUsernameTxt, strnewUsername,
				"New Username Text box");

	}

	// set the newConfirmusername text box
	private void setConfirmUsername(String strnewUsername) {
		performAction.enter(confirmUsernameTxt, strnewUsername,
				"New Confirm Username Text box");
	}

	// click the UsernameSave Button
	private void clickUsernameSavebutton() {
		performAction.click(usernameSaveBtn, "Save Username Button");
	}

	// click the UsernameCancel Button
	private void clickUsernameCancelbutton() {
		performAction.click(usernameCancelBtn, "Cancel Username Button");
	}

	//update Username
	private void updateUsername(String strAction, String strnewUsername)
	{
		if(strAction.equalsIgnoreCase("Cancel")){
			this.clickUsernameCancelbutton();
			performAction.waitForPageLoad();
			String strCurrentUsername = browser.getCurrentWebDriver().findElement(currentUserIdTxt).getAttribute("value");
			Assert.assertTrue(!strCurrentUsername.equalsIgnoreCase(strnewUsername));
		}
		else if(strAction.equalsIgnoreCase("Save")){
			this.clickUsernameSavebutton();
			performAction.waitForPageLoad();
			performAction.verify(currentUserIdTxt, strnewUsername.toUpperCase(), "Current Username");
		}
	}
	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 * 
	 * Description : 'verifyCountDownToElectCurrentEnrollmentBenefits' used
	 * verify the Count Down Option on Current Enrollment Benefits in Member
	 * Role.
	 *
	 * Role : Member Role
	 * 
	 * PreCondition : User should be in Member home page.
	 * 
	 * PostCondition : Count Down Option on Current Enrollment Benefits in Member should be verified successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * Java file Name : MemberHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	public void verifyCountDownToElectCurrentEnrollmentBenefits() {

		try {

			performAction.isElementPresent(verifyCountDown);

		} catch (Exception e) {
			logger.warn("Exception occured verifying Count Down Option on Current Enrollment Benefits in Member. "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured verifying Count Down Option on Current Enrollment Benefits in Member. "
							+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 * 
	 * Description : Keyword or method 'getStartedWithMemberEnrollment' used to click Get
	 * started button in carousel fashion type home pages in Member role.
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : User should be in carousel fashion type home pages in Member role.
	 * 
	 * PostCondition : Get Started Button should be clicked successfully.
	 *  
	 * <b>Parameters & Example </b>
	 * 
	 * Java file Name : MemberHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	public void getStartedForCarousalInMemberRole() {

		try {

			if (performAction.isElementPresent(getStartedOnCarousel)) {
				this.clickGetStartedOnCarousel();

			} else {
				By list = By.xpath(carouselList);

				int carouselCount = browser.getCurrentWebDriver()
						.findElements(list).size();
				for (int i = 0; i <= carouselCount; i++) {
					this.clickCarouselButton();
					if (performAction.isElementPresent(getStartedOnCarousel)) {
						this.clickGetStartedOnCarousel();
						break;
					}
				}

			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured while selecting Getstarted button in Home page "
					+ e.getMessage());
			throw new CustomException("Exception occured in Mmeber Home page. "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : selectGetStartedToOffersNewMemberRole keyword or method to click Get started
	 * button in member home pages and Navigates to Plans Page by skipping the Guided shopping
	 * functionality in New Member role.  
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : Member must be in home page.
	 * 
	 * PostCondition : member successfully redirected to offers page.
	 *
	 * Java file Name : MemberHomePage.java
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	public void selectGetStartedToOffersInNewMemberRole() {

		try{
			performAction.click(getStartedButtonNew, "Get Started Button");
			this.clickNextAddDependents();
			browser.getCurrentWebDriver()
			.navigate()
			.to("http://eeshpatch.benefitfocus.com/member/control/guidedShoppingBestMatchAction?skip=false");
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured while selecting Getstarted button in Home page "
					+ e.getMessage());
			throw new CustomException("Exception occured in Mmeber Home page. "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : performGetStartedInNewMemberRole keyword or method is used to click Get started
	 * button in member home pages and Navigates to Offers Page in New Member role.
	 * 
	 *  Role: Member Role
	 * 
	 * PreCondition : Member should be in Home page 
	 * 
	 * PostCondition : member will be redirected to the offer page.
	 *
	 * Java file Name : MemberHomePage.java
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	public void performGetStartedInNewMemberRole() {

		try {
			performAction.jsclick(getStartedButtonNew, "Get Started Button");					
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured while selecting Getstarted button in Home page "
					+ e.getMessage());
			throw new CustomException("Exception occured in Mmeber Home page. "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : performBeginEnrollment keyword or method is used to perform begin enrollment button in New Member Role..
	 * 
	 *  Role: Member Role
	 * 
	 * PreCondition : Member should be in offers page 
	 * 
	 * PostCondition : member will be redirected to the required offer page.
	 * 
	 * Java file Name : MemberHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	public void performBeginEnrollmentInMemberRole() {
		try {
			this.clickBeginEnrollment();

			if (performAction.isElementPresent(closeHRNote)) {
				performAction.click(closeHRNote, "close button on  HR note ");
			}
			else if(performAction.isElementPresent(startButton)){
				performAction.jsclick(startButton, "Start Button");
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured while selecting begin enrollment button in Home page "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured in Mmeber offers page. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Dilip K
	 *  
	 * Description : "Perform Get Started In MemberRole" keyword or method is used to click on Get Started in Member Role.
	 * 
	 * PreCondition : Member should be in Get Started Button 
	 * 
	 * PostCondition : Able to click on Get Started Button.
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void performGetStartedInMemberRole() {
		try {
			if(performAction.isElementPresent(getStartedButton))
			{
				this.clickGetStartedbuttonOnMemberHomePage();
			}
			else if(performAction.isElementPresent(getStartedButton1))
			{
				performAction.click(getStartedButton1, "Get started Button");
			}
			else{
				performAction.click(getStartedButton2, "Click on Get Started Button");
			}
		} catch(Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured while selecting Get Started button in Home page"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while selecting Get Started button in Home page"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Dilip K
	 *  
	 * Description   : 'Verify HR Note In Member Role' keyword or method is used to verify HR Note in specified page.
	 * 
	 * PreCondition  : Navigate to specified page in HR Note.
	 * 
	 * PostCondition : Able to verify HR Note.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strTitle |
	 * | A note from Hr Note Member Page |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strTitle" })
	public void verifyHRNoteInMemberRole(String strTitle) {
		try {
			if (strTitle.startsWith("HMV")) {
				strTitle = utils.getValue(strTitle);
			}
			By hrNoteTitleVerification=By.xpath("");
			if(performAction.isElementPresent(hrNotePanel)){

				hrNoteTitleVerification = By.xpath("//h1[text()='" + strTitle
						+ "']");
			}else if(performAction.isElementPresent(hrNotePanelNewMemberRole)){
				hrNoteTitleVerification = By.xpath("//h4[text()='" + strTitle
					+ "']");
			}

			performAction.verify(hrNoteTitleVerification, strTitle,
					"Verify HR Note Title");
			scr.capturePageScreenshot();
			this.clickCloseHrPanel();
		} catch (Exception e) {
			throw new CustomException("Exception in Verify HR Note"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description   : 'verifyGetInsuredCarouselInNewMemberRole' keyword or method is used to verify Get Insured note in Confirmation Page.
	 * 
	 *  Role: Member Role
	 * 
	 * PreCondition  : Member should be in Member Home page or COnfirmation Page
	 * 
	 * PostCondition : Member should be redirected to Member Home page or COnfirmation Page
	 *  
	 * <b>Parameters & Example </b>
	 * 
	 * Java file Name : MemberHomePage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void verifyGetInsuredCarouselInNewMemberRole() {
		try {
			if (performAction.isElementPresent(successBanner)) {
				String Message = browser.getCurrentWebDriver()
						.findElement(successMessage).getText();
				logger.info("Meassage in Congratulation banner is...."
						+ Message);
				int carouselSize = browser.getCurrentWebDriver()
						.findElements(By.xpath(carouselNoteslList)).size();
				logger.info("Notes in Carousel are.." + carouselSize);
				String buttonText = browser.getCurrentWebDriver()
						.findElement(carouselButtonText).getText();
				String Header = browser.getCurrentWebDriver()
						.findElement(carouselHeader).getText();
				if (buttonText.equalsIgnoreCase("Go to GetInsured")) {
					String Image = browser.getCurrentWebDriver()
							.findElement(carouselImage).getAttribute("alt");
					logger.info("carousel Image Name is..." + Image);
					logger.info("Carousel Header is... " + Header);
					logger.info("Carousel Button text is..."
							+ buttonText);
					scr.capturePageScreenshot();
				}
				else{
					scr.capturePageScreenshot();
					throw new CustomException(
							"Exception occured while verifying Get Insured note in Home page");
				}
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying carousel notes in Home page"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description   : 'verifyGetInsuredCarouselInNewMemberRole' keyword or method is used to verify Audax Rally note in Confirmation Page.
	 * 
	 *  Role: Member Role
	 * 
	 * PreCondition  : Member should be in Member Home page or COnfirmation Page
	 * 
	 * PostCondition : Member should be redirected to Member Home page or COnfirmation Page
	 *  
	 * <b>Parameters & Example </b>
	 * 
	 * Java file Name : MemberHomePage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void verifyAudaxRallyCarouselInNewMemberRole() {
		try {
			if (performAction.isElementPresent(successBanner)) {
				String Message = browser.getCurrentWebDriver()
						.findElement(successMessage).getText();
				logger.info("Meassage in Congratulation banner is...."
						+ Message);
				int carouselSize = browser.getCurrentWebDriver()
						.findElements(By.xpath(carouselNoteslList)).size();
				logger.info("Notes in Carousel are.." + carouselSize);
				String buttonText = browser.getCurrentWebDriver()
						.findElement(carouselButtonText).getText();
				String Header = browser.getCurrentWebDriver()
						.findElement(carouselHeader).getText();
				if (buttonText.equalsIgnoreCase("Let's Go!")) {
					String Image = browser.getCurrentWebDriver()
							.findElement(carouselImage).getAttribute("alt");
					logger.info("carousel Image Name is..." + Image);
					logger.info("Carousel Header is... " + Header);
					logger.info("Carousel Button text is..."
							+ buttonText);
					scr.capturePageScreenshot();
				}
				else{
					scr.capturePageScreenshot();
					throw new CustomException(
							"Exception occured while verifying Audax rally note in Home page");
				}
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying carousel notes in Home page"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description   : 'verifyCarouselInNewMemberRole' keyword or method is used to verify all plugins are available in carousel manner in member home Page.
	 * 
	 *  Role: Member Role
	 * 
	 * PreCondition  : Member should be in Member Home page or Confirmation Page
	 * 
	 * PostCondition : Member should be redirected to Member Home page or COnfirmation Page
	 *  
	 * <b>Parameters & Example </b>
	 * 
	 * Java file Name : MemberHomePage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void verifyCarouselInNewMemberRole() {
		try {
			if (performAction.isElementPresent(successBanner)) {
				int noteSize = browser.getCurrentWebDriver()
						.findElements(By.xpath(carouselNoteslList)).size();
				logger.info("Notes in Carousel are.." + noteSize);

				for (int i = 1; i <= noteSize - 1; i++) {
					By link = By.xpath("//ol/li[" + i + "]");
					performAction.click(link, "clicking on link");
					String Image = browser
							.getCurrentWebDriver()
							.findElement(
									By.xpath(carouselNoteslList + "[" + i
											+ "]//img")).getAttribute("alt");
					logger.info("carousel Image Name is..." + Image);
					String Header = browser
							.getCurrentWebDriver()
							.findElement(
									By.xpath(carouselNoteslList + "[" + i
											+ "]//h2")).getText();
					logger.info("Carousel Header is... " + Header);
					String buttonText = browser
							.getCurrentWebDriver()
							.findElement(
									By.xpath(carouselNoteslList + "[" + i
											+ "]//span")).getText();
					logger.info("Carousel Button text is..."
							+ buttonText);
				}
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying carousel notes in Home page"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sunitha Yerra
	 * 
	 * Role  :  Member role
	 *  
	 * Description   : 'changePasswordInMemberRole' keyword or method is used to change the password in member home Page.
	 * 
	 * PreCondition  : Member should login to Member role
	 * 
	 * PostCondition : Member should be redirected to Member Home page or COnfirmation Page
	 *  
	 * <b>Parameters & Example </b>
	 *| newPassword| strAction|
	 * New Password | 'Cancel' or 'Save'
	 *  Java File Name: MemberHomePage.java
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "newPassword", "strAction" })
	public void changePasswordInMemberRole(String newPassword, String strAction) {
		try {

			if(newPassword.startsWith("td:")){
				newPassword = utils.getValue(newPassword);
			}
			this.clickPasswordEditbutton();
			this.setNewPassword(newPassword);
			this.setConfirmPassword(newPassword);
			if (strAction.equalsIgnoreCase("Save"))
			this.clickPasswordSavebutton();
			else if (strAction.equalsIgnoreCase("Cancel"))
				this.clickPasswordCancelbutton();
			scr.capturePageScreenshot();
			performAction.waitForPageLoad();
			Assert.assertFalse(performAction.isElementPresent(errorMsg,
					"Error displayed"));
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while changing password in login information page");
			throw new CustomException(
					"Exception occured while changing password in login information page"
							+ e.getMessage());
		}
	}




	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : Keyword used to verify the SSN in Full Mask.
	 * 	 
	 * Role : Member Role
	 * 
	 * PreCondition : Add New Employee page in HR Admin Role and logged in as Member.
	 * 
	 * PostCondition : SSN Masking is verified in member role profile page
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * Java file Name : MemberHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	public void verifySSNInMask() {
		try {
			ssn = browser.getCurrentWebDriver()
					.findElement(verifySSNMask).getAttribute("value");
			logger.info("SSN Valuein mask " + ssn);
			if (ssn.contains("***_**_****")) {
				logger.info("SSN " + ssn + " is displayed in Full Mask");
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.warn("Exception occured while verifying SSN masked. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying SSN masked. " + e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *   	 
	 * Role : Member Role
	 * 
	 * Description : Keyword used to verify the SSN in Partial Mask.
	 * 
	 * PreCondition : Add New Employee page in HR Admin Role and logged in as Member.
	 * 
	 * PostCondition : SSN Masking is verified in member role profile page
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * Java file Name : MemberHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	public void verifySSNInPartialMask() {
		try {
			ssn = browser.getCurrentWebDriver()
					.findElement(verifySSNMask).getAttribute("value");
			String[] ssnValues = ssn.split("-");
			logger.info(ssnValues[0] + "  " + ssnValues[1] + " "
					+ ssnValues[2]);
			if (ssnValues[0].contains("***")
					&& (ssnValues[2].substring(0, 1).matches("\\d"))) {
				logger.info("SSN " + ssn
						+ " is displayed in Partial Mask");
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.warn("Exception occured while verifying SSN Partial masked. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying SSN Partial masked. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 * 
	 * Role : Member Role
	 * 
	 * Description : Keyword used to verify the SSN in No Mask in member role UI
	 * 
	 * PreCondition : Add New Employee page in HR Admin Role and logged in as Member.
	 * 
	 * PostCondition : SSN Masking is verified in member role profile page
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * Java file Name : MemberHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	public void verifySSNInNoMask() {
		try {
			ssn = browser.getCurrentWebDriver()
					.findElement(verifySSNMask).getAttribute("value");
			String[] ssnValues = ssn.split("-");
			logger.info(ssnValues[0] + "  " + ssnValues[1] + " "
					+ ssnValues[2]);
			if (ssnValues[0].substring(0, 1).matches("\\d")
					&& (ssnValues[2].substring(0, 1).matches("\\d"))) {
				logger.info("SSN " + ssn + " is displayed in No Mask");
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.warn("Exception occured while verifying SSN When No mask selected. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying SSN When No mask selected. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  :  	* Bhavan Mettu *  
	 * Description   : 'verifyToggleMenuHideOrShow' keyword or method is used to verify hide or show toggle menu in member home Page.
	 * 
	 * PreCondition  : Member should be in Member Home page and member should be in new UI style
	 * 
	 * PostCondition : Member side menu options should be hidden or shown based on selection.
	 *  
	 * <b>Parameters & Example </b>
	 *  No parameters Required
	 *  
	 * Java File Name : MemberHomePage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void verifyToggleMenuHideOrShow() {
		try {

			WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(toggleMenuHideOrShow));  
			this.clickToggleMenuHideOrShow();
			//click on toggle menu hide or show button and verify side menu panel is hidden

			scr.capturePageScreenshot();
			Assert.assertEquals(false, performAction.isElementPresent(sideMenuPanel));

			//click on toggle menu hide or show button and assert side menu panel is displayed by clicking on my documents link
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(toggleMenuHideOrShow));
				this.clickToggleMenuHideOrShow();
				wait.until(ExpectedConditions.visibilityOfElementLocated(myDocumentsLink));
				this.clickMyDocumentsLink();

			}catch(Exception e){
				throw new CustomException(
						"Exception occured while identifying toggle button"
								+ e.getMessage());
			}

			scr.capturePageScreenshot();
		}catch(Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying Show or Hide button in Home page"
							+ e.getMessage());
		}

	}
	/**
	 * <pre>
	 * Author  : Srilatha A
	 * 
	 * Role: New Member Role
	 *  
	 * Description   : 'clickVideosLinkInMemberRole' keyword used to verify videos in Video Glossary page in new Member Role  
	 * 
	 * PreCondition  : User should navigate to Learning Center in Member Role
	 * 
	 * PostCondition : Able to Click on Featured Video Item in new Window
	 *  
	 * Java file Name : MemberHomePage.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strVideoGlossary | 
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strVideoGlossary"})
	public void clickVideosLinkInMemberRole(String strVideoGlossary)
	{
		try{
			this.clickstrVideoGlossaryItem(strVideoGlossary);
			String pageTitle=browser.getCurrentWebDriver().getTitle();
			performAction.selectLatestWindow(pageTitle);
			scr.capturePageScreenshot();

			this.closeVideoGlossaryItem();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception in click on Video link in Video Glossary"
					+ e.getMessage());
			throw new CustomException(
					"Exception in click on Video link in Video Glossary"
							+ e.getMessage());
		}
	}


	/**
	 * <pre> 
	 * Author  : Srikanth G
	 *  
	 * Description : Keyword to click on Employee report link and Verifies given string in Employee detail report
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : Member must be in Home page
	 * 
	 * PostCondition : successfully open employee detail report and verifies content
	 * 
	 * Java File Name: MemberHomePage.java

	 * <b>Parameters & Example </b> 
	 *      
	 * </pre> strNewWindowTitle | strSearchString |
	 * Report header | search string
	 **/	
	@RobotKeyword
	@ArgumentNames({ "strNewWindowTitle","strSearchString" })
	public void verifyEmployeeDetailReportInNewMemberUI(String strNewWindowTitle,String strSearchString)
	{	              

		try {             

			this.clickandNavigateToEmployeeDetailReport(strNewWindowTitle);     	   


			if (strSearchString.startsWith("td:"))
			{      strSearchString = strSearchString.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(strSearchString
					.toLowerCase());

			object = fields.get("message1");
			if (object != null) {
				this.verifyMessage(object.toString());
			}

			object = fields.get("message2");
			if (object != null) {	                        	   
				this.verifyMessage(object.toString());
			}
			}else{

				strSearchString = utils.getValue(strSearchString);  
				this.verifyMessage(strSearchString);
			}

			scr.capturePageScreenshot();	                     
			this.closeAndNavigateBacktoNativeWindow();


		} catch (Exception e) {
			logger.warn("Exception occured verifying data in Employee Detail Report page. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured verifying data in Employee Detail Report page. "
							+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : Sunitha Yerra
	 *
	 * Description : Keyword used to verify the 'Live Online Assistance' working properly on Member Home Page.
	 *
	 * Role : Member Role
	 *
	 * PreCondition : Add New Employee and logged in as Member.
	 *
	 * PostCondition : Verifies 'Live Online Assistance' works as expected on Member Home Page
	 *
	 * <b>Parameters & Example </b>
	 *
	 * |strAction - 'Cancel' or 'Chat Now'|
	 *
	 * Java file Name : MemberHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAction" })
	public void verifyLiveOnlineAssistance(String strAction) {
		try {
			Assert.assertTrue(performAction.isElementPresent(liveOnlineAssistance));
			performAction.click(liveOnlineAssistance, "live Online Assistance Button");
			this.verifyLiveAssistanceConfirmationBox();
			if(strAction.equalsIgnoreCase("Cancel")){
				this.verifyLiveAssistanceCancel();
			}
			else if(strAction.equalsIgnoreCase("Chat Now")){
				this.verifyLiveAssistanceChatNow();
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.warn("Exception occured while verifying 'Live Online Assistance' button. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying 'Live Online Assistance' button. " + e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Dilip K
	 *  
	 * Description : 'Switch UI Style' keyword is used to switch UI style of a member in Member Role
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : It should be in Member Role
	 * 
	 * PostCondition : Able to switch to Member Role
	 * 
	 * Java File Name: com.benefitfocus.robot.member >> MemberHomePage.java
     *
	 **/	
	@RobotKeyword
	public void switchUIStyle()
	{	              
		try {             
            this.clickSwitchUILink();
     		scr.capturePageScreenshot();	                     
		} catch (Exception e) {
			logger.warn("Exception occured while switching UI style."
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while switching UI style."
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Srilatha A
	 *
	 * Description : Keyword used to verify the 'Username' working properly on Member Home Page.
	 *
	 * Role : Member Role
	 *
	 * PreCondition : User should be on Member Home Page.
	 *
	 * PostCondition : Verifies Username gets updated if Save action is performed and Username
	 * does not get updated when Cancel action is performed
	 *
	 * <b>Parameters & Example </b>
	 *
	 * </pre> strnewUsername | strAction |
	 * New Username | 'Cancel' or 'Save'
	 *
	 * Java file Name : MemberHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strnewUsername","strAction" })
	public void verifyUsernameInNewMemberRole(String strnewUsername, String strAction) {
		try {
			this.clickUsernameEditbutton();
			performAction.waitForPageLoad();
			if(strnewUsername.startsWith("td:"))
				strnewUsername = utils.getValue(strnewUsername);
			String value = utils.generateRandomNumber(strnewUsername);
			logger.info("*** New UserName : " + value);
			this.setNewUsername(value);
			this.setConfirmUsername(value);
			this.updateUsername(strAction, value);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.warn("Exception occured while verifying Username. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verifying Username. " + e.getMessage());
		}
	}

}
