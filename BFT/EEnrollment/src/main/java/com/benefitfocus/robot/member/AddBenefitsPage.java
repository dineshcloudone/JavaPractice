package com.benefitfocus.robot.member;

import java.util.List;
import junit.framework.Assert;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class AddBenefitsPage {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Logging logger;

	@Autowired
	protected Screenshot scr;

	@Autowired
	protected Utilities utils;

	// Locators
	By addDependentsPageNextButton = By.id("next");
	By verifyBenefitsPageHeader = By.cssSelector("span.message");
	By getStartedButton = By.linkText("Get Started");
	By medicalPlan = By.name("selectedPlanKeyImg0");
	By pageNextButton = By.cssSelector("button.btn.btn-primary");
	By coverageLevel = By.name("selectedTierKeyImg0");
	By coverageLevelPageNextButton = By.cssSelector("button.btn.btn-primary");
	By benefitsPageSaveButton = By.id("submitForm");
	By currentBenefitsSummaryCard = By.xpath("//div[@class='benefit-element summary-card']");
	By benefitsPageSaveAndContinueButton = By.linkText("Save & Continue");    
	By congratulationsMsg = By.cssSelector("strong");
	String currentBenefitsList = "//ol[@class='offer-list']";
	String planList = "//fieldset[@id='primaryColumn']/div/table[1]";
	String declinePlan = "//fieldset[@id='primaryColumn']/table[2]";
	String coverageLevelList = "//table[@class='planDetails']//table[1]";
	By preTaxChioce = By.id("preTaxChoice[PRETAX]");
	By nextButton = By.xpath("//button[contains(text(),'Next')]");
	By nextLink = By.linkText("Next");
	By benefitsSummaryCard = By.xpath("//div[@class='summary-card']");
	String marketPlanList = "//div[@id='product-selection-app']//ul";
	String newMarketPlace = "//div[@class='region-plan-selection-plan-list']/div";
	By marketDeclinePlan = By
			.xpath("//button[contains(text(),'Decline Coverage')]");
	By notNowButton = By.xpath("//a[contains(text(),'Not Now')]");
	// New Member role
	String LifeCoverage = "//table[@class='table table-striped']/tbody";
	String offersList = "//div[@class='benefit-element-list']";


	By healthCareFSAPlan = By.name("selectedPlanKey");
	By flexDeductionPeriods = By.id("newPeriodsRemaining");  
	By saveLink = By.linkText("Save");

	// payroll
	By contributionAmount = By.id("contributionAmount");
	By saveButton = By.id("submitForm");
	By nextContributionAmountPage = By.xpath("//*[@class='btn btn-primary']");
	By getStartedAfterFSAEnrollment = By.xpath("//*[@class='btn btn-success']");

	By viewBenefitDetails = By.xpath("//*[@class='offer-list']/li[1]//span[3]");
	By returnHomeButton = By
			.xpath("//button[@type='button' and contains(.,'Return home')]");
	By viewdetailsTestHS = By
			.xpath("//*[@class='offer-list']/li[2]//span[@class='icon-angle-down']");
	By finishLater = By.linkText("");
	By enrollmentSuccess = By
			.xpath("//*[@class='alert-message-banner']//strong");
	By benefitsLink = By.linkText("Benefits");
	By editInformation = By.linkText("View / Edit Information");
	By editContribution = By.linkText("Edit contribution");
	By finalizeButtonEvidencePage = By.id("submitForm");

	By nextButtonInHealthstatementPage=By.cssSelector("button.btn.btn-primary");

	// Locators in FSA Enrollment
	By startButtonFSABenefit=By.xpath("//button[contains(text(),'Start')]");
	By fsaErrorMessage = By.xpath("//div[@id='errorListContainerForEntirePage']/ul/li[contains(text(),'Please enter an amount less than or equal to the maximum value')]");

	By benefitsSummaryinNewMemberRole = By
			.xpath("//div[@class='benefit-summary']");
	By completeEnrollment = By.id("completeEnrollment");
	By healthScreeningTrue = By
			.name("//input[@name='answer' and @value='true']");
	By healthScreeningFalse = By
			.name("//input[@name='answer' and @value='false']");

	By medicareTrue = By.id("hasMedicare[YES]");
	By medicareFalse = By.id("hasMedicare[NO]");
	By additionInsuranceYesNoDetails = By
			.id("typeOfPolicyUserHasRequestedToCreate[YES_NO_DETAILS]");
	By additionInsuranceNo = By.id("typeOfPolicyUserHasRequestedToCreate[NO]");
	By additionInsuranceYes = By
			.id("typeOfPolicyUserHasRequestedToCreate[YES_NO_DETAILS]");

	By beginEnrollmentButton = By.linkText("Begin enrollment");
	//By beginEnrollmentButton = By.xpath("//*[@id='benefit-actions-region']/div/div/a");
	By editLifeCoverageLink = By.linkText("Edit coverage");
	By editLifeCoverageButton = By.xpath("//h4[contains(text(),'Life 2016')]/ancestor::div[@class='panel-heading']/following-sibling::div[@class='panel-footer']//a[contains(text(),'Edit coverage')]");
	//By editLifeCoverageLink = By.xpath("//*[@id='benefit-actions-region']/div/div/a");
	By nextButtonInLifeCoverEdit = By.xpath("//*[@class='btn btn-primary'][contains(text(),'Next')]");
	//Locators for Compare Medical Plans in New UI
	By firstCompareCheckBox = By.xpath("//h4[contains(text(),'Medical 2016')]//preceding-sibling::div[@class='compare pull-left']//input");
	By secondCompareCheckBox = By.xpath("//h4[contains(text(),'Medical plus 2016')]//preceding-sibling::div[@class='compare pull-left']//input");
	By compareButton = By.xpath("//a[contains(text(),'Compare plans & estimate your cost')]");
	By nextNewUI = By.id("next");
	By selectPlan = By.xpath("//button[contains(text(),'Select plan')]");
	By editLifeCoverageFromBenefitsSnapshot = By.xpath("//h3[contains(text(),'Supplemental Life')]");
	By editLifeCoverageButtonInBenifitsSnapShotPage = By.xpath("//h3[contains(text(),'Life')]");

	String SummaryCards = "//section[@class='summary-box-content noHeader']//div[@class='summary-card']";
	String SummaryCardsAdditionalInfo = "//section[@class='summary-box-content noHeader']//div[@class='summary-card inner last']";
	String headerText="";
	int benefitsCount=0;
	String HRAFound="";
	String cartSummary = "//div[@id='cost-tracker-container']";

	//locators for cart summary
	By cartSummaryLink = By.xpath("//i[@class='bficon bficon-cart bficon-lg']/..");
	By cartSummaryHeader = By.xpath("//div[@class='panel panel-default panel-dropdown drop-panel-small']//h2[@class='mvs'][contains(text(),'Cart Summary')]");  //contains text Cart Summary
	By benefitAmounts = By.xpath("//div[@class='col-md-3 col-xs-3 text-right line-item-value']"); //returns list of amounts
	By benefitAmountsOnPage = By.xpath("//div[@class='col-md-4']/div[@id='cost-tracker-container']//div[@class='col-md-3 col-xs-3 text-right line-item-value']");
	By cartSummaryOnPage = By.xpath("//div[@class='col-md-4']/div[@id='cost-tracker-container']//h2[@class='mvs'][contains(text(),'Cart Summary')]");
	By selectReason = By.id("event-choice-none");
	By lifeChangeLink = By.xpath("//*[@class='btn collapsed']");
	By editCoverageForLifePlan = By.xpath("//h4[contains(text(),'Flat Term Life')]/ancestor::div[@class='panel-heading']/following-sibling::div[@class='panel-footer']//a[contains(text(),'Edit coverage')]");
	By lifePlan = By.xpath("//h4[contains(text(),'Flat Term Life')]");
	By lifePlanApprovalMessage = By.xpath("//h3[contains(text(),'Employee Supplemental Term Life Insurance')]/parent::div//*[contains(text(),'pending approval')]");
	By spouseLifePlanApprovalMessage = By.xpath("//h3[contains(text(),'Spouse/Domestic Partner Supplemental Term Life Insurance')]/parent::div//*[contains(text(),'pending approval')]");
	By childLifePlanApprovalMessage = By.xpath("//h3[contains(text(),'Child Supplemental Term Life Insurance')]/parent::div//*[contains(text(),'pending approval')]");

	// Locators in Current Benefit Details in Member Role

	By employeeAgreementYes = By.id("answer[true]");
	By lifeNewEventChkbox = By.id("eventChoice[newEvent]");
	By eventDate =By.id("newEventDate");
	By pageHeader= By.xpath("//header[@class='page-header ']");


	//Member HRA summary page elements
	By benefitEffectivedate = By.xpath("//div[@class='col-xs-6 col-sm-5 col-md-4 col-lg-3'][contains(text(),'Effective Date')]/following-sibling::*");
	By empOGContr = By.xpath("//div[@class='col-xs-6 col-sm-5 col-md-4 col-lg-3'][contains(text(),'Employer Ongoing Contributions')]/following-sibling::*"); 	
	By empICContr = By.xpath("//div[@class='col-xs-6 col-sm-5 col-md-4 col-lg-3'][contains(text(),'Employer Initial Contribution')]/following-sibling::*");
	By empTotalContr = By.xpath("//div[@class='col-xs-6 col-sm-5 col-md-4 col-lg-3'][contains(text(),'Total Employer Contributions')]/following-sibling::*");

	By continueToHome = By.xpath("//a[@class='btn']//span[contains(text(),'Continue to home')]");
	String coverageAmount = "//div[@class='coverage-level-plan-table']/div";
	By nextNewMemberUi = By.xpath("//button[contains(text(),'Next')]");

	By allRequiredDetails = By.id("sectionIncomplete[false]");
	By medicareNumber = By.id("hicNumber");
	By eligibilityReason = By.id("reasonAge-nativeHtmlElement");
	By hospitalInsuranceForPartA = By.id("isPartA[true]");
	By enterEffectiveDate = By.id("partAEffDate");

	By clickNextAdditionalInsurance = By.xpath("//button[contains(text(),'Next')]");
	By policyNumber = By.id("policyNumber");
	By policyHolder = By.xpath("//input[@name='policyHolderKey']");
	By carrierName = By.id("carrierName");
	By coveredPersonsEffDate = By.id("coveredPersonEffDate0");
	By saveBenefitChanges = By.id("completeEnrollment");
	By saveAndContinue = By.xpath("//button[contains(text(),'Save ')]");
	By startBenefit = By.xpath("//button[@class='btn btn-primary'][contains(text(),'Start')]");
	By optionNo = By.id("sectionIncomplete[true]");
	String rowCountFromDependentsCoverageLevel="//div[@class='legacy-table-container']//tbody//tr";
	By clickPreviousAdditionalInsurance = By.xpath("//button[contains(text(),'Previous ')]");

	/* Add Dependents Page Next Button */
	private void addDependentsPageNextButton() {
		performAction.click(addDependentsPageNextButton,
				"Next Button on Add Dependent Page");
	}

	/* Get Started Button */
	private void clickGetStartedButton() {
		performAction.click(getStartedButton, "Get Started Button");
	}

	/* Begin enrollment Button */
	private void clickBeginEnrollmentButton() {
		performAction.jsclick(beginEnrollmentButton, "Begin Enrollment Button");
	}

	/* Medical Benefit Radio Button */
	private void selectMedicalPlan() {
		performAction.click(medicalPlan, "Medical Plan Radio Button");
	}

	/* Benefits Page Next Button */
	private void clickBenefitsPageNextButton() {
		performAction.click(pageNextButton, "Benefits Page Next Button");
	}

	/* Coverage Level Radio Button */
	private void selectCoverageLevel() {
		performAction.click(coverageLevel, "Coverage Level Radio");
	}

	/* Benefits Page Save Button */
	private void clickBenefitsPageSaveButton() {
		performAction
		.click(benefitsPageSaveButton, "Benefits Page Save Button");
	}

	/* Benefits page Save and Continue button*/
	private void clickSaveAndContinueButton(){
		performAction.click(benefitsPageSaveAndContinueButton, "Save and Continue Button");
	}

	/* Decline Coverage button in Marketplace */
	private void marketDeclinePlan() {
		performAction.click(marketDeclinePlan, "Decline Coverage Button");

	}

	// Not now button in offer page to skip Guided shopping
	private void notNowButtonGuidedShopping() {
		try {
			if (performAction.isElementPresent(notNowButton)) {
				this.performAction.click(notNowButton, "Not Now Button");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// *Next Button in plans page
	private void clickNextInOfferspage() {
		performAction.click(nextButton, "NeXt Button");
	}
	// Click on Start Button
	private void clickStartButton(){
		performAction.jsclick(startButtonFSABenefit, "Click on Start Button");
	}

	// Click on Next Button
	private void clickNextButton() {
		performAction.click(nextButton, "Click on Next Button");
	}
	// Click on Additional Information Link	
	private void clickAdditionalInformationLink(){
		boolean HRAFound=false;
		benefitsCount=browser.getCurrentWebDriver().findElements(By.xpath(SummaryCards)).size();
		if(benefitsCount>0){
			for(int row=1;row<=benefitsCount;row++){
				headerText=browser.getCurrentWebDriver().findElement(By.xpath(SummaryCards+"["+row+"]")).getText();
				if(headerText.contains("Health Reimbursement Account (HRA)")){
					performAction.click(By.xpath(SummaryCardsAdditionalInfo+"["+row+"]"+"//a"), "Click on Additional Information Link");
					HRAFound=true;
					break;
				}
			}
		}
		if(benefitsCount==0){
			logger.info("No Benefits Found");
		}
		if(!HRAFound){
			logger.info("HRA Plan Not Found");
		}
	}

	// *Next Button in plans page
	private void clickEditLifeCoverageButton() {
		performAction.click(editLifeCoverageButton, "Edit Life coverage Button");
	}
	// Click on edit Life Coverage From Benefits Snapshot
	private void clickEditLifeCoverageFromBenefitsSnapshot(){
		WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(editLifeCoverageFromBenefitsSnapshot));
		wait.until(ExpectedConditions.elementToBeClickable(editLifeCoverageFromBenefitsSnapshot));
		performAction.jsclick(editLifeCoverageFromBenefitsSnapshot,"Edit Life Coverage from Benefits Snapshot");
	}

	// Click on edit Life Coverage Link
	private void clickEditLifeCoverageLink() {
		WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(editLifeCoverageLink));
		wait.until(ExpectedConditions.elementToBeClickable(editLifeCoverageLink));
		performAction.jsclick(editLifeCoverageLink, "Click Edit coverage for life in current Benefits page");
	}

	// Click on nexButton Life Cover Edit
	private void clicknextButtonInLifeCoverEdit() {
		WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(nextButtonInLifeCoverEdit));
		wait.until(ExpectedConditions.elementToBeClickable(nextButtonInLifeCoverEdit));
		performAction.jsclick(nextButtonInLifeCoverEdit,"Next Button in Life Plan Edit page");

	}


	//enter event date
	private void enterEventDate(String strEnterEventDate) {
		if (strEnterEventDate.startsWith("d:")) {
			strEnterEventDate = strEnterEventDate.substring(2);
			performAction.enter(eventDate,
					utils.getDate(strEnterEventDate), "Enter Event Date");
		} else {
			performAction.enter(eventDate, strEnterEventDate,
					"Enter Event Date");
		}

	}

	//click on edit coverage button asper given plan
	private void clickEditCoverageOfGivenPlan(String Strplan){

		int offersCount = browser.getCurrentWebDriver()
				.findElements(By.xpath(offersList)).size();
		logger.info("Offers count..." + offersCount);

		By editCoverage = By.xpath("//h4[contains(text(),'"+ Strplan + "')]/ancestor::div[@class='panel-heading']/following-sibling::div[@class='panel-footer']//a[contains(text(),'Edit coverage')]");			

		performAction.click(editCoverage, "Edit coverage for medical benefits");
	}


	// Verify Success message 
	private void verifymessage(String strMessage) {
		performAction.verifyMessage(strMessage);

	}

	//select life event radio button and click next
	private void selectLifeEventRadiobuttonandClickNext(){
		performAction.waitForPageLoad();
		performAction.click(lifeNewEventChkbox, "select new life event");
		performAction.click(nextButton, "Next button");
	}

	//select event type
	private void selectEventType(String StrReason){

		By eventTypeLocator;

		if (StrReason.equalsIgnoreCase("Marriage")){
			eventTypeLocator = By.id("newEventType[MARRIAGE_GENERIC]");				
		}else if(StrReason.equalsIgnoreCase("Divorce")){
			eventTypeLocator = By.id("newEventType[DIVORCE_GENERIC]");
		}else if(StrReason.equalsIgnoreCase("Loss of other coverage")){
			eventTypeLocator = By.id("newEventType[LOSS_OF_COVERAGE]");
		}else if(StrReason.equalsIgnoreCase("I do not have any life or family change events")){
			eventTypeLocator =By.id( "newEventType[none]");
		}else {
			StrReason = StrReason.toUpperCase().replace(" ", "_");
			eventTypeLocator = By.id("newEventType["+StrReason+"]");
		}	


		performAction.click(eventTypeLocator, "select new life event type");
		performAction.click(nextButton, "Next button");

	}

	//select event date
	private void enterLifeEventDate(String strEnterEventDate){
		if(performAction.isElementPresent(eventDate)){
			this.enterEventDate(strEnterEventDate);
			performAction.click(pageHeader, "Page Header");
			performAction.waitUntilElementPresent(nextButton);
			performAction.click(nextButton, "Next button");
			performAction.waitForPageLoad();
		}
	}

	//enterFSAamoutnandclickNext
	private void enterFSAamoutnAndClickNext(String strAmount){
		performAction.clearEnter(contributionAmount, strAmount, "contribution amount textfield");
		performAction.click(nextContributionAmountPage,	"Next button in Contribution Amount Page");
		//performAction.verifyMessage(strAmount);
	}

	private void clickSummaryCartLink()
	{
		performAction.jsclick(cartSummaryLink, "Cart Summary Link");
	}

	private void clickSelectPlan()
	{
		performAction.jsclick(selectPlan, "Select Plan");
		performAction.waitForPageLoad();
	}

	private String getSummaryCartBenefitCost()
	{
		String benefitCost="";
		if(performAction.isElementPresent(cartSummaryHeader))
		{
			performAction.verifyMessage("Cart Summary");
			List<WebElement> totalAmounts = browser.getCurrentWebDriver().findElements(benefitAmounts);
			if(totalAmounts!=null && totalAmounts.size()>1)
			{
				Object test = ((JavascriptExecutor) browser.getCurrentWebDriver()).executeScript("return arguments[0].innerHTML;", totalAmounts.get(0));
				benefitCost = test.toString();

			}
		}
		return benefitCost;
	}

	private void verifySummaryCartBenefitCostFromPage(String benefitCost)
	{
		if(performAction.isElementPresent(cartSummaryOnPage))
		{
			performAction.verifyMessage("Cart Summary");
			List<WebElement> totalAmounts = browser.getCurrentWebDriver().findElements(benefitAmountsOnPage);
			logger.info("Cart Summary On Page Cost count .." + totalAmounts.size());
			scr.capturePageScreenshot();
			if(totalAmounts!=null && totalAmounts.size()>1)
			{
				Assert.assertEquals(benefitCost, totalAmounts.get(0).getText());
			}
		}
	}

	private void verifyApprovalMessages()
	{
		scr.capturePageScreenshot();
		Assert.assertEquals(true, performAction.isElementPresent(lifePlanApprovalMessage));
		Assert.assertEquals(false, performAction.isElementPresent(spouseLifePlanApprovalMessage));
		Assert.assertEquals(false, performAction.isElementPresent(childLifePlanApprovalMessage));
	}

	private void clickSelectReason()
	{
		performAction.click(selectReason, "Select Change Reason");
	}


	private void clickLifeChangeLink()
	{
		performAction.click(lifeChangeLink, "Life Change Link");
	}

	private void verifyBenefitIsEditable(String editableBenefit, String nonEditableBenefit)
	{
		String editablePlanEditCoverageBtn = "//h3[contains(text(),'"+editableBenefit+"')]/ancestor::div[@class='panel-heading']/following-sibling::div[@class='panel-footer']//a[contains(text(),'Edit coverage')]";
		String nonEditablePlanEditCoverageBtn = "//h3[contains(text(),'"+nonEditableBenefit+"')]/ancestor::div[@class='panel-heading']/following-sibling::div[@class='panel-footer']//a[contains(text(),'Edit coverage')]";
		Assert.assertEquals(true, performAction.isElementPresent(By.xpath(editablePlanEditCoverageBtn)));
		Assert.assertEquals(false, performAction.isElementPresent(By.xpath(nonEditablePlanEditCoverageBtn))); //verifying editCoverage doesn't exist
	}

	//verify HRA contributions and effective dates

	private void verifyHRAandEffectivedates(String Hradetails){

		if (Hradetails.startsWith("td:"))
			Hradetails = Hradetails.substring(3);             

		Double actualInitialAmount = (double) 0;
		Double actualOngoingAmount = (double) 0;
		Double finalInitialAmount = (double) 0;
		Double finalOngoingAmount = (double) 0;
		String actualInitialProrateVal = null;
		String actualOngoingProrateVal= null;
		String payFrequency = null;


		Object object = null;
		JSONObject fields = ReadJsonTestData.getTestData(Hradetails
				.toLowerCase());

		object = fields.get("InitialContribution");
		if (object != null) {
			actualInitialAmount = Double.parseDouble(object.toString());
		}

		object = fields.get("OngoingContribution");
		if (object != null) {
			actualOngoingAmount = Double.parseDouble(object.toString());
		}

		object = fields.get("InitialProrate");
		if (object != null) {
			actualInitialProrateVal= object.toString();
		}

		object = fields.get("OngoingProrate");
		if (object != null) {
			actualOngoingProrateVal= object.toString();
		}

		object = fields.get("payfrequency");
		if (object != null) {
			payFrequency= object.toString();
		}      			      			

		List<WebElement> totalEffectiveDates = browser.getCurrentWebDriver()
				.findElements(benefitEffectivedate);
		List<WebElement> onGoingcontribution = browser.getCurrentWebDriver()
				.findElements(empOGContr);
		List<WebElement> initialcontribution = browser.getCurrentWebDriver()
				.findElements(empICContr);
		List<WebElement> totalcontributonAmounts = browser
				.getCurrentWebDriver().findElements(empTotalContr);

		logger.info("Number of effective dates elements In Summary===>"
				+ totalEffectiveDates.size());
		logger.info("Number of OnGoing contribution elements In Summary===>"
				+ onGoingcontribution.size());
		logger.info("Number of Initial contribution elements In Summary===>"
				+ initialcontribution.size());
		logger.info("Number of contribution elements In Summary===>"
				+ totalcontributonAmounts.size());

		String Effectdate = totalEffectiveDates.get(0).getText(); // getting
																	// effective
																	// date from
																	// application
		String[] arrSplit = Effectdate.split("/");

		//getting HRA contribution amounts from application
		String appInitialamt = initialcontribution.get(0).getText();
		String appOngoingamt = onGoingcontribution.get(0).getText();                 
		String appTotalcontributionamt = totalcontributonAmounts.get(0)
				.getText();

		// calculating HRA amounts according to pay frequency and prorate
		// setting
		if (actualInitialProrateVal.equalsIgnoreCase("yes")){
			if (payFrequency.equalsIgnoreCase("monthly")) {
				int remainMonths = 12-(Integer.parseInt(arrSplit[0]));
				logger.info("Calculated initital remainMonths===>" + remainMonths);
				logger.info("actual intial from json===>" + actualInitialAmount);
				finalInitialAmount=( actualInitialAmount/12)*remainMonths;
				finalInitialAmount = (double)Math.round(finalInitialAmount*100)/100;
			}else if (payFrequency.equalsIgnoreCase("bi-weekly")){
				double weekpermonth = 4.33;
				double elapsedweeks = Double.parseDouble(arrSplit[0])
						* weekpermonth;
				int remainWeeks = 52-(int)elapsedweeks;
				float remainbiweeks =remainWeeks/2;
				finalInitialAmount = (actualInitialAmount / 26)
						* (int) remainbiweeks;
				finalInitialAmount = (double)Math.round(finalInitialAmount*100)/100;
			}
		}else{
			finalInitialAmount=actualInitialAmount;
		}

		logger.info("Calculated Initial final amount===>"+ finalInitialAmount);

		if (actualOngoingProrateVal.equalsIgnoreCase("yes")){
			if (payFrequency.equalsIgnoreCase("monthly")) {
				int remainMonths = 12-(Integer.parseInt(arrSplit[0]));
				if ((Integer.parseInt(arrSplit[1]))>=1 && (Integer.parseInt(arrSplit[1]))<=5){
					remainMonths=remainMonths+1;
				}
				finalOngoingAmount=( actualOngoingAmount/12)*remainMonths;
				finalOngoingAmount = (double)Math.round(finalOngoingAmount*100)/100;
			}else if (payFrequency.equalsIgnoreCase("bi-weekly")){
				double weekpermonth = 4.33;
				double elapsedweeks = Double.parseDouble(arrSplit[0])
						* weekpermonth;

				int remainWeeks = 52-(int)elapsedweeks;
				float remainbiweeks =remainWeeks/2;
				finalOngoingAmount = (actualOngoingAmount / 26)
						* (int) remainbiweeks;
				finalOngoingAmount = (double)Math.round(finalOngoingAmount*100)/100;
			}
		}else{
			finalOngoingAmount=actualOngoingAmount;
		}
		// displaying HRA contribution amounts
		logger.info("Calculated Ongoing final amount===>"+ finalOngoingAmount);      

		logger.info("Initial contribution amount===>"+ appInitialamt);
		logger.info("On Going contribution amount===>"+ appOngoingamt);                 
		logger.info("Total contribution amount===>"+ appTotalcontributionamt);   

		appOngoingamt= appOngoingamt.substring(1);
		appOngoingamt=appOngoingamt.replace(",","");                 
		String[] arrappOngoing = appOngoingamt.split(" ");
		appOngoingamt = arrappOngoing[0];

		appInitialamt= appInitialamt.substring(1);
		appInitialamt=appInitialamt.replace(",","");                 
		String[] arrappInitial = appInitialamt.split(" ");
		appInitialamt = arrappInitial[0];

		appTotalcontributionamt= appTotalcontributionamt.substring(1);
		appTotalcontributionamt=appTotalcontributionamt.replace(",","");                 
		String[] arrappTotalcontribution = appTotalcontributionamt.split(" ");
		appTotalcontributionamt = arrappTotalcontribution[0];

		scr.capturePageScreenshot();

		//verifying HRA contributions
		Assert.assertEquals(finalInitialAmount, Double.parseDouble(appInitialamt));

		Assert.assertEquals(finalOngoingAmount, Double.parseDouble(appOngoingamt));

		Assert.assertEquals((Double.parseDouble(appInitialamt) + Double.parseDouble(appOngoingamt)), Double.parseDouble(appTotalcontributionamt));

		//effective dates of benefit elements verification
		for (int i = 0; i <= totalEffectiveDates.size()-1; i++) {
			String Effdate = totalEffectiveDates.get(i).getText();
			logger.info("Effective date for benefit No-->" + i + " is "
					+ Effdate);
			if((i+1) <= (totalEffectiveDates.size()-1)){
				String Effdate1 = totalEffectiveDates.get(i+1).getText();
				int j = i+1;
				logger.info("Effective date for benefit No-->" + j + " is "
						+ Effdate1);
				Assert.assertEquals(Effdate, Effdate1);               	 

			}
		}     				
	}
    private void clickYesOption() {
		performAction.click(allRequiredDetails, "Actions");
		performAction.click(clickNextAdditionalInsurance, "Click Next After Yes All The Info");
	}
	private void clickAllDetails() {
		performAction.click(allRequiredDetails, "Actions");
		performAction.click(clickNextAdditionalInsurance, "Click Next After Yes All The Info");
	}
	
	private void clickNoOption() {
		performAction.click(optionNo, "No Option");
		performAction.click(clickNextAdditionalInsurance, "Click Next After No All The Info");
	}

	private void enterPolicyNumber(String strPolicyNumber) {
		performAction.enter(policyNumber, strPolicyNumber, "Enter Policy Number");
		performAction.click(clickNextAdditionalInsurance, "Click Next");
	}

	private void enterPolicyHolder() {
		performAction.click(policyHolder, "Select Policy Holder");
		performAction.click(clickNextAdditionalInsurance, "Click Next");
	}

	private void enterCarrierName(String strCarrierName) {
		performAction.enter(carrierName, strCarrierName, "Enter Carrier Name as Mercer");
		performAction.click(clickNextAdditionalInsurance, "Click Next");
	}

	private void enterPersonsEffectiveDate(String strDate) {
		performAction.enter(coveredPersonsEffDate, strDate, "Enter Effective Date");
		performAction.click(clickNextAdditionalInsurance, "Click Next");
	}

	private void saveAdditionalInsurance() {
		//performAction.click(clickNextAdditionalInsurance, "Click Next");
		performAction.waitUntilElementPresent(saveAndContinue);
		performAction.click(saveAndContinue, "Click Save Member UI");
	}

	private void saveAdditionalBenefit() {
		scr.capturePageScreenshot();
		if (performAction.isElementPresent(saveBenefitChanges)) {
			performAction.click(saveBenefitChanges, "Click Save Member UI");
		}
	}

	private void startBenefitElement() {
		performAction.click(startBenefit, "Click Start Button");
	}
	// Select Dependents to be covered
    private boolean selectDependents(String strDependentName){
    	
    	int rowCount=browser.getCurrentWebDriver().findElements(By.xpath(rowCountFromDependentsCoverageLevel)).size();
    	boolean flag=false;
    	if(rowCount>0){
    		
    		for(int i=1;i<rowCount;i++){
    			String actualText=browser.getCurrentWebDriver().findElement(By.xpath(rowCountFromDependentsCoverageLevel+"["+i+"]")).getText(); 
    			logger.info("Actual Text ==> "+actualText);
    			if(actualText.trim().contains(strDependentName)){
    				logger.info("Entered IF Loop");
    				flag=true;
    				performAction.click(By.xpath(rowCountFromDependentsCoverageLevel+"["+i+"]//a"), "Select Dependent for Benefit Coverage");
    				break;
    			}
    		}
    	}
    	if(rowCount==0){
    		logger.info("No Rows Found");
    	}
    	
    	if(flag){
    		logger.info("Dependent with Name = " + strDependentName + " Found");
    	}else{
    		logger.info("Dependent not found");
    	}
    	scr.capturePageScreenshot();
    	return flag;
    }

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword to perform start on particular benefit for the employee in eEnrollment Member role
	 * 
	 * Role : Member Role
	 *
	 * PreCondition : Member Benefits page having list of eligible benenfits for the Member 
	 * 
	 * PostCondition : Clicks on Start button for the benefit and plan selection page is shown.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strBenefitName | 
	 * | MEDICAL 2015 / DENTAL 2015 / VISION 2015 / Life Plan 2015 (available in 'BF QA Test Automation Group') | 
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strBenefitName" })
	public void startCurrentBenefitForMember(String strBenefitName) {

		boolean benefitFound = false;

		try {
			Thread.sleep(2000);
			if (browser.getCurrentWebDriver()
					.findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

				By list = By.xpath(currentBenefitsList + "/li");

				System.out.println("list" + list.toString());

				int benefitsCount = browser.getCurrentWebDriver()
						.findElements(list).size();

				System.out.println("benefitsCount" + benefitsCount);

				if (benefitsCount > 0) {
					if (performAction.isElementPresent(notNowButton)) {
						this.notNowButtonGuidedShopping();
					}

					for (int i = 1; i <= benefitsCount; i++) {

						By BenefitHeader = By.xpath(currentBenefitsList
								+ "/li[" + i + "]//h4");

						if (browser.getCurrentWebDriver()
								.findElement(BenefitHeader).getText().trim()
								.equalsIgnoreCase(strBenefitName)) {

							By startButton = By.xpath(currentBenefitsList
									+ "/li[" + i
									+ "]//a[contains(text(),'Get')]");
							browser.getCurrentWebDriver()
							.findElement(startButton).click();
							benefitFound = true;
							Thread.sleep(5000);
							break;
						}
					}
					scr.capturePageScreenshot();
				} else {
					scr.capturePageScreenshot();
					System.out.println("No Benefits available ");
					throw new CustomException("No Benefits available");
				}

				if (!benefitFound) {
					scr.capturePageScreenshot();
					System.out.println(strBenefitName
							+ " benefit NOT available.");
					throw new CustomException(strBenefitName
							+ "benefit NOT available.");
				}
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while selecting benefit "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while selecting benefit "
							+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword to perform selection on plans page in eEnrollment Member role
	 * 
	 * Role : Member Role
	 *  
	 * PreCondition : Select the given plan in the list of eligible plans on the page and click next  
	 * 
	 * PostCondition : Next page as per the configuration. Coverage level page.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPlan - String type argument takes Plan Name as input value | 
	 * | MEDICAL 2015 / MEDICAL PLUS 2015 / DECLINE COVERAGE (available in 'BF QA Test Automation Group') | 
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPlan" })
	public void selectPlanForMember(String strPlan) {

		boolean planFound = false;

		try {
			if (strPlan.contains("decline")) {
				browser.getCurrentWebDriver()
				.findElement(By.xpath(declinePlan + "//tr[1]//a"))
				.click();

			} else {
				int plansCnt = browser.getCurrentWebDriver()
						.findElements(By.xpath(planList + "//tr")).size();

				if (plansCnt > 0) {
					for (int i = 2; i < plansCnt; i++) {
						By cols = By.xpath(planList + "//tr[" + i + "]//td");
						if (browser.getCurrentWebDriver().findElements(cols)
								.size() > 1) {
							By plan = By.xpath(planList + "//tr["+i+"]/td[2]");
							// for (WebElement category : categories)
							if (browser.getCurrentWebDriver().findElement(plan)
									.getText().trim().equalsIgnoreCase(strPlan)) {
								By checkbox = By.xpath(planList + "//tr[" + i
										+ "]//a");
								performAction.highlightElement(checkbox);
								browser.getCurrentWebDriver()
								.findElement(checkbox).click();
								planFound = true;
								Thread.sleep(5000);
								break;
							}
						}
					}
				} else {
					scr.capturePageScreenshot();
					System.out.println("No plan available ");
					throw new CustomException("No plan available");
				}

				if (!planFound) {
					scr.capturePageScreenshot();
					System.out.println(strPlan + " plan NOT available.");
					throw new CustomException(strPlan + "plan NOT available.");
				} else {
					performAction.click(nextButton, "Next button.");
				}
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while selecting plan"
					+ e.getMessage());
			throw new CustomException("Exception occured while selecting plan "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : selectMarketPlanForMember keyword or method  to perform selection on plans in market place page in eEnrollment
	 * member role
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : Member must be in Plans selection page.
	 * 
	 * PostCondition : member successfully selects the plan and navigates to the next page
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPlan | 
	 * | 2015 Cigna $800 Deductible Plan / 2015 Cigna $1,500 Deductible Plan  / DECLINE COVERAGE  | 
	 * 
	 *  Java file Name :  AddBenefitsPage.java
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strPlan" })
	public void selectMarketPlanForMember(String strPlan) {

		try {
			if (strPlan.contains("decline")) {
				this.marketDeclinePlan();

			} else {
				int plansCnt = browser.getCurrentWebDriver()
						.findElements(By.xpath(marketPlanList)).size();

				if (plansCnt > 0) {
					for (int i = 1; i <= plansCnt; i++) {

						By plan = By.xpath(marketPlanList + "[" + i
								+ "]//h4");

						if (browser.getCurrentWebDriver().findElement(plan)
								.getText().trim().equalsIgnoreCase(strPlan)) {
							By selectButton = By
									.xpath(marketPlanList
											+ "["
											+ i
											+ "]//button[@class='btn btn-warning btn-block']");
							browser.getCurrentWebDriver()
							.findElement(selectButton).click();
							break;
						}
					}
				} else {
					scr.capturePageScreenshot();
					logger.info("No plan available ");
					throw new CustomException("No plan available");
				}

			}

		} catch (Exception e) {
			logger.info("Exception occured while selecting plan"
					+ e.getMessage());
			throw new CustomException("Exception occured while selecting plan "
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword to perform selection on coverage level page in eEnrollment Member role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Select the given level in the list of coverage levels on the page and click next  
	 * 
	 * PostCondition : Next page as per the configuration. 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strCoverageLevel - String type argument takes coverage level as input value | 
	 * | Employee Only / Employee and Spouse / Employee + Children (available in 'BF QA Test Automation Group') | 
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strCoverageLevel" })
	public void selectCoverageLevelForMember(String strCoverageLevel) {

		boolean coverageLevelFound = false;

		try {
			int coveragelistCnt = browser.getCurrentWebDriver()
					.findElements(By.xpath(coverageLevelList + "//tr")).size();

			if (coveragelistCnt > 0) {
				for (int i = 1; i < coveragelistCnt; i++) {

					By coverage = By.xpath(coverageLevelList + "//tr[" + i
							+ "]/td[2]");
					logger.info(browser.getCurrentWebDriver()
							.findElement(coverage).getText());
					// for (WebElement category : categories)
					if (browser.getCurrentWebDriver().findElement(coverage)
							.getText().trim()
							.equalsIgnoreCase(strCoverageLevel)) {

						By checkbox = By.xpath(coverageLevelList + "//tr[" + i
								+ "]//a");
						browser.getCurrentWebDriver().findElement(checkbox)
						.click();
						coverageLevelFound = true;
						Thread.sleep(2000);
						break;
					}
				}
			} else {
				logger.info("No coverageLevels available ");
				throw new RuntimeException("No coverageLevels available");
			}

			if (!coverageLevelFound) {
				logger.info(strCoverageLevel
						+ " coverageLevel NOT available.");
				throw new CustomException(strCoverageLevel
						+ " coverageLevel NOT available.");
			} else {
				performAction.click(nextButton, "Next button.");
			}

			if (performAction.isElementPresent(preTaxChioce)) {
				performAction.click(nextButton, "Pretax Next button.");
			}

		} catch (Exception e) {
			logger.info("Exception occured while selecting coverage level"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while selecting coverage level "
							+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : verifyMemberSummaryCardAndSave' to verify the member summary card and click save
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Summary page for the Benefit enrollment in Member Role  
	 * 
	 * PostCondition : Plan details are verified
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPlan - String type argument takes Plan Name as input value | 
	 * | MEDICAL 2015 / MEDICAL PLUS 2015 / DECLINE COVERAGE | 
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "planName" })
	public void verifyMemberSummaryCardAndSave(String planName) {
		// TODO need to enhance to verify elements in summary page.
		try {
			performAction.verify(benefitsSummaryCard, planName,
					"verifying plan exist on summary page");

			this.clickBenefitsPageSaveButton();

			performAction.isElementPresent(congratulationsMsg,
					"Congratulations message");

		} catch (Exception e) {
			logger.info("Exception occured while verify plan details in summary page"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while verify plan details in summary page "
							+ e.getMessage());
		}

	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : declineCoverage keyword or method is used to Decline Coverage button in that page and
	 * 
	 * navigates to next page
	 *
	 * Role: Member Role
	 *
	 * PreCondition : Member should be in Plans selection page
	 *
	 * PostCondition : Member will decline the coverage for the benefit and navigates to next page.
	 *  
	 *  Java file Name :  AddBenefitsPage.java
	 *  
	 * </pre> 
	 **/

	@RobotKeyword
	public void declineCoverage() {
		try {
			this.marketDeclinePlan();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Declining plan in Member page "
							+ e.getMessage());
		}

	}

	/**
	 * Keyword or method 'enrollInMedicalBenefitsWithEmployeeOnlyCoverage' used
	 * to Select Medical Benefit with Employee Only Coverage to a member from
	 * Member Role
	 */
	@SuppressWarnings("unused")
	@RobotKeyword
	private void enrollInMedicalBenefitsWithEmployeeOnlyCoverage() {

		try {

			this.addDependentsPageNextButton();
			performAction.isElementPresent(verifyBenefitsPageHeader,
					"Benefits page header");
			performAction.isElementPresent(getStartedButton,
					"GetStarted button");

			this.clickGetStartedButton();
			this.selectMedicalPlan();
			this.clickBenefitsPageNextButton();

			this.selectCoverageLevel();
			this.clickBenefitsPageNextButton();

			this.clickBenefitsPageSaveButton();
			performAction.isElementPresent(congratulationsMsg,
					"Congratulations message");

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
	 * Description : selectMarketPlanForNewMemberRole keyword or method  to perform selection on plans in market place page in eEnrollment
	 * member role
	 * 
	 * Role: New Member Role
	 * 
	 * PreCondition : Member must be in Plans selection page.
	 * 
	 * PostCondition : member successfully selects the plan and navigates to the next page
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPlan | 
	 * | 2015 Cigna $800 Deductible Plan / 2015 Cigna $1,500 Deductible Plan  / DECLINE COVERAGE  |  
	 * 
	 * Java file Name :  AddBenefitsPage.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPlan" })
	public void selectMarketPlanForNewMemberRole(String strPlan) {

		try {
			strPlan = utils.getValue(strPlan);
			if (strPlan.contains("decline")) {
				this.marketDeclinePlan();

			} else {
				int plansCnt = browser.getCurrentWebDriver()
						.findElements(By.xpath(newMarketPlace + "/div")).size();
				logger.info("Plans Count....." + plansCnt);
				if (plansCnt > 0) {
					for (int i = 1; i <= plansCnt; i++) {

						By plan = By.xpath(newMarketPlace + "/div[" + i
								+ "]//h4");

						if (browser.getCurrentWebDriver().findElement(plan)
								.getText().trim().equalsIgnoreCase(strPlan)) {
							By selectButton = By
									.xpath(newMarketPlace
											+ "/div["
											+ i
											+ "]//button[@data-action='select-plan']");
							performAction.jsclick(selectButton, "Select plan button");

							break;
						}
					}
				} else {
					logger.info("No plan available ");
					throw new CustomException("No plan available");
				}

				// if (!planFound) {
				// System.out.println(strPlan + " plan NOT available.");
				// throw new CustomException(strPlan + "plan NOT available.");
				// } else {
				// performAction.click(nextButton, "Next button.");
				// }
			}
			if (performAction.isElementPresent(preTaxChioce)) {
				performAction.click(nextButton, "Pretax Next button.");
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			e.printStackTrace();
			System.out.println("Exception occured while selecting plan"
					+ e.getMessage());
			throw new CustomException("Exception occured while selecting plan "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : selectLifeCoveragePlan keyword or method to perform selecting Coverage Amount for LIfe plans in
	 * eEnrollment New member role   
	 * 
	 * Role: New Member Role
	 * 
	 * PreCondition : Member must be in the selecting coverage amount page.
	 * 
	 * PostCondition : member successfully redirected to select beneficiary page.
	 *  
	 * <b>Parameters & Example </b> 
	 *
	 *
	 * | StrAmount | 
	 * | $10,000 / $20,000   | 
	 * 
	 *  Java file Name :  AddBenefitsPage.java
	 * </pre> 
	 **/


	@RobotKeyword
	@ArgumentNames({ "StrAmt" })
	public void selectLifeCoveragePlan(String StrAmount) {
		try {
			int planscount = browser.getCurrentWebDriver()
					.findElements(By.xpath(LifeCoverage + "/tr")).size();
			logger.info("Plan count .." + planscount);
			String amount = browser.getCurrentWebDriver().findElement(By.xpath(LifeCoverage+"/tr[1]//span")).getText();
			logger.info("coverage is.... "+amount);

			for (int i = 1; i <= planscount; i++) {

				By plan = By.xpath(LifeCoverage + "/tr[" + i
						+ "]//span[contains(text(),'" + StrAmount + "')]");
				logger.info("Plan is  ..." + plan);
				if (performAction.isElementPresent(plan)) {

					performAction.click(plan, "clicking on Plan");

					this.clickNextInOfferspage();
					break;
				}
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while selecting Coverage Amount"
					+ e.getMessage());
			throw new CustomException("Exception occured while selecting plan "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : editCoverageNewMemberRole keyword or method is used  to perform Edit Coverage for a particularly selected plan in
	 * Offer summary page for New member role
	 * 
	 * Role : New Member Role
	 * 
	 * PreCondition : Member should be in Offer summary page and Must be completed enrollment. 
	 * 
	 * PostCondition : member will be redirected to the beneficiary information page.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | StrOffer |
	 * | Medical coverage / Employer Paid Term Life coverage    |   
	 * 
	 * Java file Name :  AddBenefitsPage.java
	 * </pre> 
	 **/


	@RobotKeyword
	@ArgumentNames({ "StrOffer" })
	public void editCoverageInNewMemberRole(String StrOffer) {
		try {
			String offersList = "//div[@class='benefit-element-list']";

			int offersCount = browser.getCurrentWebDriver()
					.findElements(By.xpath(offersList)).size();
			logger.info("Offers count..." + offersCount);

			By editCoverage = By.xpath("//h4[contains(text(),'"+ StrOffer + "')]/ancestor::div[@class='panel-heading']/following-sibling::div[@class='panel-footer']//a[contains(text(),'Edit coverage')]");
			performAction.click(editCoverage, " Edit Coverage for Life Benefits");
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while selecting Offer in Offer Summary Page"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while selecting Offer in Offer Summary Page "
							+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : editBeneficiaryNewMemberRole keyword or method is used to perform Edit Beneficiary for a particularly selected Life plan
	 * in Offer summary page for New member role
	 * 
	 * Role: New Member Role
	 * 
	 * PreCondition : Member should be in Offer summary page and Must be completed enrollment. 
	 * 
	 * PostCondition : member will be redirected to the beneficiary information page.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | StrOffer |
	 * | Employer Paid Accidental Death & Dismemberment coverage / Employer Paid Term Life coverage    |   
	 * 
	 * Java file Name :  AddBenefitsPage.java
	 * </pre> 
	 **/


	@RobotKeyword
	@ArgumentNames({ "StrOffer" })
	public void editBeneficiaryInNewMemberRole(String StrOffer) {
		try {
			String offer = "//h3[contains(text(),'"+StrOffer+"')]";
			if(performAction.isElementPresent(By.xpath(offer)))
			{
				String beneficiary = offer + "/parent::div//a/i[@class='bficon bficon-pencil bficon-1x']";
				By beneficiaryLink = By.xpath(beneficiary);
				performAction.click(beneficiaryLink, "Edit Beneficiary");
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while editing Beneficiary"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while selecting Offer in Offer Summary Page "
							+ e.getMessage());
		}
	}

	/* Enter and Save contribution Amount for FSA benefit */
	private void provideContributionAmountForFSA(String value) {

		try {
			performAction.clearEnter(contributionAmount, value,
					"contribution amount textfield");
			performAction.click(nextContributionAmountPage,
					"Next button in Contribution Amount Page");
			performAction.verifyMessage(value);
			performAction.click(saveButton,
					"Save button in Contribution Amount Page");
		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while entering and saving contribution amount"
							+ e.getMessage());
		}
	}

	/**
	 * startFSAEnrollment keyword to click start for enrollment in FSA plan
	 * 
	 * <pre>
	 * <precondition>
	 * <b>This keyword can be used after starting current benefit for FSA enrollment</b>
	 */
	@RobotKeyword 
	public void startFSAEnrollment() { 
		try {
			performAction.isElementPresent(startButtonFSABenefit);
			this.clickStartButton();
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while clicking Start button to enroll for FSA plan"
							+ e.getMessage());
		}
	}

	/**
	 * enterHealthStatementAndClickNext keyword to enter Health Statement data
	 * and Click Next button in that page
	 * 
	 * <pre>
	 * <precondition>
	 * <b>This keyword can be used after selecting the current benefit for FSA enrollment</b>
	 */
	@RobotKeyword
	public void enterHealthStatement() {
		try {
			performAction.click(nextButtonInHealthstatementPage,
					"Next Button in Health Statement page");
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while entering Health Statement data while enrolling for FSA plan"
							+ e.getMessage());
		}
	}


	/**
	 * EnterContributionForFSAPlan keyword or method used to enter contribution
	 * amount for FSA plan and value(FSA amount) should be between 50 and 500
	 * 
	 * <b>Parameter1 :</b> | value - String type argument takes Flex
	 * contribution amount | <b>Example :</b> |300 (value should be between 50
	 * and 500)|
	 * 
	 */
	@RobotKeyword
	@ArgumentNames({ "value" })
	public void enterContributionForFSAPlan(String value) {

		try {

			this.provideContributionAmountForFSA(value);

			this.verifyContributionAmount(value);

			this.returnHomePage();

		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while entering contribution amount for FSA plan"
							+ e.getMessage());
		}

	}
	/**
	 * EditFlexContributionAmount keyword or method used to increase or decrease
	 * contribution amount for FSA plan Parameter value(FSA amount) should be
	 * between 50 and 500
	 * 
	 * <pre>
	 * <precondition>
	 * <b> Member should be enrolled to FSA plan before increasing or decreasing the Flex contribution amount</b>
	 * <b>Parameter1 :</b>
	 * | value - String type argument takes Flex contribution amount |
	 * <b>Example :</b>
	 * |300 (value should be between 50 and 500)|
	 * <postcondition>
	 * <b>After modifying the Flex contribution amount screen will navigates to employee home screen </b>
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "value" })
	public void editFlexContributionAmount(String value) {

		try {

			this.nextContributionAmountPage();
			this.getStartedAfterFSAEnrollment();

			this.editAndSaveFlexContribution(value);
			this.verifyContributionAmount(value);

			this.returnHomePage();
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while changing the contribution amount for FSA plan "
							+ e.getMessage());
		}

	}

	/* Next button in Contribution Amount page */
	private void nextContributionAmountPage() {
		if (performAction.isElementPresent(nextContributionAmountPage,
				"Next button in Contribution Amount page")) {

			performAction.click(nextContributionAmountPage,
					"Next button in Contribution Amount Page");
		}
	}

	/* Get Started button after FSA enrollment */
	private void getStartedAfterFSAEnrollment() {
		performAction.click(getStartedAfterFSAEnrollment,
				"Get Started button in Member home page");
	}

	/* Edit and Save flex contribution amount to increase or decrease it */
	private void editAndSaveFlexContribution(String value) {

		try {

			performAction.click(viewBenefitDetails,
					"Link to see the benefit details in detail");
			performAction.click(editInformation,
					"View / Edit Information button");
			performAction.click(editContribution,
					"Edit Contribution button to modify FSA amount");
			utils.sleep(1000);
			performAction.clearEnter(contributionAmount, value,
					"contribution amount textfield");
			performAction.click(nextContributionAmountPage,
					"Next button in Contribution Amount Page");
			performAction.click(saveButton,
					"Save button in Contribution Amount Page");
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while editing and saving the Flex contribution amount"
							+ e.getMessage());
		}
	}

	/* Method to verify the contribution amount entered for FSA enrollment */
	private void verifyContributionAmount(String value) {
		try {
			performAction.click(viewBenefitDetails,
					"Link to see the benefit details in detail");
			performAction.verifyMessage(value);
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while verifying the contribution amount for FSA plan "
							+ e.getMessage());
		}
	}

	/* Return Home Page */
	private void returnHomePage() {
		performAction.click(returnHomeButton,
				"Return Home button after Enrollment in FSA");
	}

	/* Verifies if a string is present in Summary cart */
	private void verifyIsDataPresent(String strData)
	{
		String data = cartSummary + "//*[contains(text(),'" + strData
				+ "')]";
		Assert.assertTrue(performAction.isElementPresent(By.xpath(data)));
	}

	private void clickPreviousButtonAndVerify(String strPolicyNumber){
		performAction.enter(policyNumber, strPolicyNumber, "Enter Policy Number");
		performAction.click(clickNextAdditionalInsurance, "Click Next");
		performAction.click(clickPreviousAdditionalInsurance, "Click Previous");
		//verify the policy number is displayed when clicked on 'previous'
		String policynumber = browser.getCurrentWebDriver().findElement(policyNumber).getText();
		scr.capturePageScreenshot();
		Assert.assertTrue("Policy Number is not same as entered", policynumber.equalsIgnoreCase(policynumber));
		performAction.click(clickNextAdditionalInsurance, "Click Next");
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *  
	 * Description   : Perform Save button click action on Benefit enrollment Summary page in new member role
	 * 
	 *  Role : Member Role
	 * 
	 * PreCondition  : Summary page
	 * 
	 * PostCondition : Confirmation page
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPlan - String type argument takes Plan Name as input value | 
	 * | MEDICAL 2015 / MEDICAL PLUS 2015 / DECLINE COVERAGE |
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre>
	 * 
	 **/
	@RobotKeyword
	@ArgumentNames({ "planName" })
	public void saveBenefitEnrollmentInNewMember(String planName) {
		try {
			performAction.verify(benefitsSummaryinNewMemberRole, planName,
					"verifying plan exist on summary page");

			this.clickBenefitsPageSaveButton();

			performAction.isElementPresent(congratulationsMsg,
					"Congratulations message");

		} catch (Exception e) {
			logger.info("Exception occured while verify plan details in summary page"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while verify plan details in summary page "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *  
	 * Description   : Perform complete enrollment button click on new member role summary page 
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition  : Summary page
	 * 
	 * PostCondition : Confirmation page
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 **/
	@RobotKeyword
	public void completeEnrollemntInNewMemberRole() {
		performAction.click(completeEnrollment, "Complete Enrollment");
		scr.capturePageScreenshot();
		if (performAction.isElementPresent(continueToHome)) {
			performAction.click(continueToHome, "Continue To Home");
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *  
	 * Description   : 'Health Screening/HRA' complete all 3 requirements {by October 31, 2014} to obtain a discount on your health premiums next year in member role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition  : Medical plan enrollment with FSA in member role
	 * 
	 * PostCondition : Health screening question is selected 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strCompleteHealthScreening  |
	 * | yes / no              |
	 *
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strCompleteHealthScreening" })
	public void healthScreeningNeededInMemberRole(
			String strCompleteHealthScreening) {

		try {
			if (strCompleteHealthScreening.equalsIgnoreCase("yes")) {
				performAction.click(healthScreeningTrue,
						"Click on health screening yes option");
			} else {
				performAction.click(healthScreeningFalse,
						"Click on health screening yes option");
			}
			this.clickNextButton();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting health screening for employee"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting health screening for employee"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *  
	 * Description   : 'Medical care information for employee in member role 
	 *
	 * Role : Member Role
	 * 
	 * PreCondition  : Medical plan enrollment with FSA in member role
	 * 
	 * PostCondition : Health screening question is selected 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strMedicareInformation  |
	 * | yes / no              |
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strMedicareInformation" })
	public void medicareInformationInMemberRole(String strMedicareInformation) {

		try {
			if (strMedicareInformation.equalsIgnoreCase("yes")) {
				performAction.click(medicareTrue,
						"Click on Medicare Yes option");
			} else {
				performAction.click(medicareFalse,
						"Click on Medicare No option");
			}
			this.clickNextButton();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting medicare for employee"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting medicare for employee"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *  
	 * Description   : 'Additional information coverage other that health insurance in the benefit
	 * 
	 *  Role : Member Role
	 * 
	 * PreCondition  : Additional insurance section
	 * 
	 * PostCondition : Insurance details is specified 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strAddtionalInsurance  |
	 * | yes / no  / yesno      |
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAddtionalInsurance" })
	public void additionalInsuranceInformationInMemberRole(
			String strAddtionalInsurance) {

		try {
			if (strAddtionalInsurance.equalsIgnoreCase("yes")) {
				performAction.click(additionInsuranceYes,
						"Click on Medicare Yes option");
			} else if (strAddtionalInsurance.equalsIgnoreCase("no")) {
				performAction.click(additionInsuranceNo,
						"Click on Medicare No option");
			} else {
				performAction.click(additionInsuranceYesNoDetails,
						"Click on Medicare Yes but no details option");
			}
			this.clickNextButton();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting medicare for employee"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting medicare for employee"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *  
	 * Description   : Enter the contribution amount for FSA plan in member role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition  : Contribution amoujnt page after selecting FSA plan
	 * 
	 * PostCondition : Effective date page after clicking on next button 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | value  |
	 * | 200    |
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "value" })
	public void contributionAmountForFSAPlanInMemberRole(String value) {

		try {

			performAction.clearEnter(contributionAmount, value,
					"contribution amount textfield");

			this.clickNextButton();

		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while entering contribution amount for FSA plan"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Dilip K
	 *
	 * Role : Member Role
	 *
	 * Description   : "Edit Current Benefit Of Member" Keyword to click on 'View / Edit Information' link based on benefit name from Current Benefit page in Member Role 
	 *
	 * PreCondition  : Navigate to Current Benefits page
	 *
	 * PostCondition : Able to click on view details page
	 * <pre>
	 * <b>Parameters :</b>
	 * | "strBenefitName"|
	 * | Medical Plus HRA 2016 |
	 * 
	 * Java File Path : com.benefitfocus.robot.member >> AddBenefitsPage.java
	 * 
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strBenefitName"})
	public void editCurrentBenefitOfMember(String strBenefitName) {

		boolean benefitFound = false;
		try {
			Thread.sleep(2000);
			if (browser.getCurrentWebDriver()
					.findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

				By list = By.xpath(currentBenefitsList + "/li");

				logger.info("list" + list.toString());

				int benefitsCount = browser.getCurrentWebDriver()
						.findElements(list).size();

				logger.info("benefitsCount" + benefitsCount);

				if (benefitsCount > 0) {
					for (int i = 1; i <= benefitsCount; i++) {

						By BenefitHeader = By.xpath(currentBenefitsList
								+ "/li[" + i + "]//h4");

						if (browser.getCurrentWebDriver()
								.findElement(BenefitHeader).getText().trim()
								.equalsIgnoreCase(strBenefitName)) {

							By viewDetailsLink = By.xpath(currentBenefitsList
									+ "/li[" + i
									+ "]//span[text()='View details']");
							performAction.click(viewDetailsLink, "Click on View Details Link");

							By editDetails = By.xpath(currentBenefitsList
									+ "/li[" + i
									+ "]//section//a");

							performAction.click(editDetails, "Click on View/Edit Information button");
							benefitFound = true;
							Thread.sleep(5000);
							break;
						}
					}
					scr.capturePageScreenshot();
				} else {
					scr.capturePageScreenshot();
					System.out.println("No Benefits available ");
					throw new CustomException("No Benefits available");
				}

				if (!benefitFound) {
					scr.capturePageScreenshot();
					System.out.println(strBenefitName
							+ " benefit NOT available.");
					throw new CustomException(strBenefitName
							+ "benefit NOT available.");
				}
			}
		} 
		catch (Exception e) {
			logger.info("Exception while clicking on View details link");
			scr.capturePageScreenshot();
			throw new CustomException("Exception while clicking on View details link"
					+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : Member Role
	 *
	 * Description   : "Calculate Contribution Amount In Member Role" Keyword to calculate amount using prorate from Additional Information in Member Role.Formula used for calculating Contribution Amount is '[(Annual Amount/pay frequency)*remaining pay periods] (i.e If pay frequency is bi-weekly divide by 26, semimonthly by 24'
	 *
	 * PreCondition  : Navigate to Benefit Summary page
	 *
	 * PostCondition : Able to calculate contribution amount from Additional information using prorate amount
	 * <pre>
	 * <b>Parameters :</b>
	 * | "strAnnualAmount","strPayFrequency","strEffectiveDate","strContributionType" |
	 * | 1200, semimonthly, d:effectivedate,Initial Contribution|
	 * 
	 * Java File Path : com.benefitfocus.robot.member >> AddBenefitsPage.java
	 * 
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strAnnualAmount","strPayFrequency","strEffectiveDate","strContributionType"})
	public void calculateContributionAmountInMemberRole(int strAnnualAmount,String strPayFrequency,String strEffectiveDate,String strContributionType) {
		this.clickAdditionalInformationLink();
		strEffectiveDate=(utils.getValue(strEffectiveDate)).substring(0, 2);
		try {
			int remainingpayperiod=0;
			int amount=0;

			if(strPayFrequency.equals("semimonthly")){
				remainingpayperiod=(13-Integer.valueOf(strEffectiveDate))*2;
				logger.info("Remaining Pay Period ====>"+remainingpayperiod);
				amount=Integer.valueOf(strAnnualAmount)/Integer.valueOf(24);
				logger.info("amount====>"+amount);
			}
			int proratedamount=amount*remainingpayperiod;  		   
			logger.info("Prorated Amount ===> "+proratedamount);
			String contributionAmount=browser.getCurrentWebDriver().findElement(By.xpath("//dt[contains(text(),'"+strContributionType+"')]/following-sibling::dd[1]/div")).getText();
			contributionAmount=(contributionAmount.substring(1, contributionAmount.indexOf("."))).replaceAll(",", "");
			logger.info("Contribution Amount displayed from Member Role ===>"+contributionAmount);
			if(Integer.valueOf(contributionAmount)==proratedamount){
				logger.info("Both Values are same");
			}else{
				logger.info("Amount doesnot match");
			}

			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception in calculating amounts");
			scr.capturePageScreenshot();
			throw new CustomException("Exception in calculating amounts"
					+ e.getMessage());
		}
	}


	/**
	 * <pre> 
	 * Author  : Bhavan Mettu
	 * 
	 * Role : Member role
	 *  
	 * Description : "BeginMedicalEnrollmentInCurrentBenefitsPageNewUI" keyword or method will navigate the user to Benefits list page. 
	 * 				This keyword will skip the adding dependents in "Before you enroll benefits" page (clicks on next button).
	 * 				Then keyword will click on begin enrollment button in "current benefits page" to get benefits list.
	 * 
	 * PreCondition : runMemberProfilePages(personal information,Emergency contacts, Military service and Direct Deposit) should be completed.
	 * 
	 * PostCondition : successfully navigated to benefits listing page
	 * 
	 * Java File Name: AddBenefitsPage.java

	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void BeginMedicalEnrollmentInCurrentBenefitsInPageNewUI() {
		try {

			performAction.click(nextNewUI, "Next Button in Before you enroll page");
			WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(beginEnrollmentButton));
			wait.until(ExpectedConditions.elementToBeClickable(beginEnrollmentButton));
			logger.info("*****Wait for Begin Enrollment Button*******");
			//browser.getCurrentWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			performAction.click(beginEnrollmentButton, "Begin Enrollment button in Your Benefits page");

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while Begin Medical EnrollmentIn Current Benefits In Page New UI"
							+ e.getMessage());
		}
	}	
	/**
	 * <pre> 
	 * Author  : Bhavan Mettu
	 * 
	 * Role : Member role
	 *  
	 * Description : "EditLifeCoverageInCurrentBenefitsPageInNewUI" keyword or method will edit life coverage in Current Benefits page. 
	 * This keyword will skip the adding dependents in "Before you enroll benefits" page (clicks on next button).
	 * Then keyword will click on edit life coverage button in "current benefits page".
	 * 
	 * PreCondition : runMemberProfilePages(personal information,Emergency contacts, Military service and Direct Deposit) should be completed.
	 * 
	 * PostCondition : successfully navigated to edit life coverage page
	 *
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void EditLifeCoverageInCurrentBenefitsPageInNewUI() {
		try {
			if(performAction.isElementPresent(editLifeCoverageButton))
				this.clickEditLifeCoverageButton();
			else if(performAction.isElementPresent(editLifeCoverageFromBenefitsSnapshot)){
				this.clickEditLifeCoverageFromBenefitsSnapshot();
			}
			this.clickEditLifeCoverageLink();
			this.clicknextButtonInLifeCoverEdit();
			logger.info("Life coverage edited successfully");

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while Edit Life Coverage In Current Benefits Page In New UI"
							+ e.getMessage());
		}
	}	
	/**
	 * <pre> 
	 * Author  : Bhavan Mettu
	 * 
	 * Role : Member role
	 *  
	 * Description : "compareMedicalPlansInMemberRoleInNewUI" keyword or method will compare tow medical plans in New  UI. 
	 * "runMemberProfilePagesInNewUI" keyword should be used before using this keyword.
	 * 
	 * PreCondition : runMemberProfilePages(personal information,Emergency contacts, Military service and Direct Deposit) should be completed.
	 * 
	 * PostCondition : successfully displays medical plans comparison page
	 * 
	 * Java File Name: AddBenefitsPage.java
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void compareMedicalPlansInMemberRoleInNewUI() {
		try {

			performAction.click(nextNewUI, "Next Button in Before you enroll page");
			//browser.getCurrentWebDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			performAction.waitForPageLoad();
			performAction.click(beginEnrollmentButton, "Begin Enrollment button in Your Benefits page");
			performAction.click(nextNewUI, "Next Button in Before you enroll page");
			performAction.click(firstCompareCheckBox, "First Compare Check Box");
			performAction.click(secondCompareCheckBox, "Second Compare Check Box");
			performAction.click(compareButton, "Compare Plans & Estimate Cost");

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while compare Medical Plans In Member Role In New UI"
							+ e.getMessage());
		}

	}

	/**
	 * <pre> 
	 * Author  : Sunitha Yerra
	 * 
	 * Role :  Member role
	 *  
	 * Description : enrollLifeBenefit keyword or method is used to enroll into LTD and STD plan in
	 * eEnrollment New member role   
	 * 
	 * PreCondition : Member must be logged into New member role.
	 * 
	 * PostCondition : member successfully enrolled to LTD and STD Benefit.
	 *  
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 * </pre> 
	 **/


	@RobotKeyword
	@ArgumentNames({  })
	public void enrolLTDAndSTDBenefit() {
		try {
			WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 50);

			this.clickBenefitsPageSaveButton();
			wait.until(ExpectedConditions.visibilityOfElementLocated(completeEnrollment));
			wait.until(ExpectedConditions.elementToBeClickable(completeEnrollment));
			this.completeEnrollemntInNewMemberRole();
			performAction.waitForPageLoad();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while enrolling into LTD and STD benefit"
					+ e.getMessage());
			throw new CustomException("Exception occured while enrolling into LTD and STD benefit "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author : Sunitha Yerra
	 *
	 * Role: Member role
	 *
	 * Description : This keyword or method is used to check if cart summary contains the data or 
	 * 				 message passed to it in New member role
	 *
	 * PreCondition : Member must be enrolled into at least one plan in New member role.
	 *
	 * PostCondition : Verifies the message is present or not in Cart Summary drop down 
	 * 
	 * <b>Parameters :</b>
	 * | "strData" - String that needs to be verified in the cart summary|
	 *
	 * Java File Name: AddBenifitsPage.java
	 *
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "strData" })
	public void verifySummaryCartData(String strData) {
		try {
			this.clickSummaryCartLink();
			scr.capturePageScreenshot();
			this.verifyIsDataPresent(strData);
		} catch (Exception ex) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while enrolling into Life benefit"
					+ ex.getMessage());
			throw new CustomException(
					"Exception occured in Verify Summary Cart Data "
							+ ex.getMessage());
		}

	}


	/**
	 * <pre> 
	 * Author  : Sunitha Yerra
	 * 
	 * Role:  Member role
	 *  
	 * Description : This keyword or method is used to verify that pending
	 * 				approval message is displayed only for Employee life insurance and not for dependents life insurance
	 * 
	 * PreCondition : Member is enrolled into 'employee and dependents supplemental life insurance benefits'
	 * 
	 * PostCondition : Verified that Pending approval message is displayed only for employee benefit in summary page.
	 *  
	 *  Java File Name: AddBenifitsPage.java
	 *  
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({  })
	public void verifyHealthStatementPendingApprovalMessages() {
		try{
			this.verifyApprovalMessages();
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured in verifyHealthStatementPendingApprovalMessages"
					+ e.getMessage());
			throw new CustomException("Exception occured in verifyHealthStatementPendingApprovalMessages"
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Sunitha Yerra
	 * 
	 * Role  : Member role
	 *  
	 * Description : This keyword or method is used to verify that a member can't modify current benefits without a change reason
	 *  			New member role   
	 * 
	 * PreCondition : Navigate to Life Change link page
	 * 
	 * PostCondition : Benefits listed under the 'The Life change you entered doesn't affect these benefits' link can't be edited.
	 *  
	 * <b>Parameters & Example </b>
	 *
	 * | editableBenefit | nonEditableBenefit |
	 * | Benefits which are editable with no change reason | Benefits which are not editable with no change reason.
	 *
	 *  Java File Name: AddBenefitsPage.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({  "editableBenefit",  "nonEditableBenefit" })
	public void editingBenefitsWithNoChangeReason(String editableBenefit, String nonEditableBenefit) {
		try{
			this.clickSelectReason();
			this.clickNextButton();
			this.clickNextButton();
			performAction.waitForPageLoad();
			WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(lifeChangeLink));
			performAction.verifyMessage("The life change you entered doesn't affect these benefits");
			this.clickLifeChangeLink();
			this.verifyBenefitIsEditable(editableBenefit,nonEditableBenefit);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured in verifyNoChangeReason"
					+ e.getMessage());
			throw new CustomException("Exception occured in verifyNoChangeReason"
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Srikanth G
	 *  
	 * Description : Click next button on document Manager Page In New Member Role
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : Member must be in document manager page
	 * 
	 * PostCondition : successfully clicks Next and proceed to next page
	 * 
	 * Java File Name: AddBenefits.java

	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({ })
	public void documentManagerPageInNewMember() {

		try {	
			performAction.click(nextButton,
					"Next button in Document ManagerPage");

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception in Document manager page"
							+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Srikanth G
	 *  
	 * Description : Click on Edit coverage button for given medical plan in new member role for change reason functionality
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : Member must be in Benefits page and hire date should be updated to previous date/year to enable change reason screen after edit coverage click
	 * 
	 * PostCondition : successfully clicks Edit coverage and proceed to next page
	 * 
	 * Java File Name: AddBenefits.java

	 * <b>Parameters & Example </b> 
	 *      
	 * </pre> Strplan | Medicalplan2016 | Florida Blue $2,500 Deductible Plan
	 **/	  

	@RobotKeyword
	@ArgumentNames({ "Strplan" })
	public void editCoverageNewMemberRoleInNewUI(String Strplan) {
		try {
			this.clickEditCoverageOfGivenPlan(Strplan);
			this.verifymessage("Please select a reason for changing your benefit coverage.");			
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while clicking Edit Offer button in Benefits Summary Page"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while clicking Edit Offer button in Benefits Page "
							+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Srikanth G
	 *  
	 * Description : Selects change reason and on Edit coverage button for given medical plan in new member role
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : Member must be in change reason screen which can appear after click on edit coverage during offcycle
	 * 
	 * PostCondition : successfully selects change reason and event date and proceed to next page
	 * 
	 * Java File Name: AddBenefits.java

	 * <b>Parameters & Example </b> 
	 *      
	 * </pre> StrReason | strEnterEventDate | 
	 *    Marriage | currendate:d:-1
	 **/		
	@RobotKeyword
	@ArgumentNames({ "StrReason","strEnterEventDate" })
	public void selectChangereasonForNewMemberUI(String StrReason,String strEnterEventDate) {
		try {

			this.selectLifeEventRadiobuttonandClickNext();	
			this.selectEventType(StrReason);
			this.enterLifeEventDate(strEnterEventDate);			
			this.verifymessage("Eligible For Coverage");
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while selecting Life event while editing coverage"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while selecting Life event while editing coverage "
							+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Srikanth G
	 *  
	 * Description : Add contribution amount for FSA plan
	 * 
	 * Role: Member Role
	 * 
	 * PreCondition : Member must be in FSA plan enrollment page
	 * 
	 * PostCondition : FSA contribution amount entered
	 * 
	 * Java File Name: AddBenefits.java

	 * <b>Parameters & Example </b> 
	 *      
	 * </pre> strAmount  
	 *    any amount ex: 100,200
	 **/	

	@RobotKeyword
	@ArgumentNames({ "amount" })
	public void addContributionAmountForFSA(String strAmount) {

		try {			
			this.enterFSAamoutnAndClickNext(strAmount);
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while entering FSA contribution amount"
							+ e.getMessage());
		}
	}


	/** * <pre> 
	 * Author  : Srikanth G
	 *  
	 * Description : verify HRA(ongoing and initial only)[Initial contributon prorate set to "Yes" where as ongoing contribution set to "No"] and effective dates values in member benefits summary page
	 * 
	 * PreCondition : Member must be in benefits summary page.
	 * 
	 * PostCondition : HRA and effective dates will be verified
	 *	    
	 * Role : MemberRole

	 * Java file Name : MemberHomePage.java
	 * 
	 * <b>Parameters & Example </b> 
	 *      
	 * </pre> td:Hradetails. Test date required are InitialContribution,OngoingContribution,InitialProrate,OngoingProrate and pay frequency. These needs to be send through json file
	 **/
	@RobotKeyword
	@ArgumentNames({"Hradetails"})
	public void verifyHRAcontributionandEffectivedatesInMemberSummaryPage(String Hradetails) {

		try{			   
			this.verifyHRAandEffectivedates(Hradetails);
		} catch(Exception e)
		{
			scr.capturePageScreenshot();
			throw new CustomException("Failed in comparing benefits HRA and effective date values In Benefits summary page"+e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description : Keyword to perform selection on coverage Amount page in eEnrollment Member role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Select the given Amount in the list of coverage Amounts on the page and click next  
	 * 
	 * PostCondition : Next page as per the configuration.
	 * 
	 *  Java File Name: AddBenefitsPage.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strCoverageAmount - String type argument takes coverage Amount as input value | 
	 * | $10,000.00 / $20,000.00 | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strCoverageAmount" })
	public void selectCoverageAmountForMember(String strCoverageAmount) {

		if (performAction.isElementPresent(startBenefit)) {
			this.startBenefitElement();
		}

		boolean coverageAmountFound = false;

		try {
			int coveragelistCnt = browser.getCurrentWebDriver()
					.findElements(By.xpath(coverageAmount + "//table" + "//tbody" +"//tr")).size();

			if (coveragelistCnt > 0) {
				for (int i = 1; i < coveragelistCnt; i++) {

					By coverage = By.xpath(coverageAmount + "//table" + "//tbody" + "//tr[" + i
							+ "]//td");
					logger.info(browser.getCurrentWebDriver()
							.findElement(coverage).getText());
					// for (WebElement category : categories)
					if (browser.getCurrentWebDriver().findElement(coverage)
							.getText().trim()
							.equalsIgnoreCase(strCoverageAmount)) {

						By checkbox = By.xpath(coverageAmount + "//table" + "//tbody" + "//tr[" + i
								+ "]//td//label//input");
						//browser.getCurrentWebDriver().findElement(checkbox)
						//.click();
						performAction.click(checkbox, "Check box");
						coverageAmountFound = true;
						//Thread.sleep(2000); // Wait after Coverage Amount Selection
						break;
					}
				}
			} else {
				logger.info("No coverageLevels available ");
				throw new RuntimeException("No coverageLevels available");
			}

			if (!coverageAmountFound) {
				logger.info(strCoverageAmount
						+ " coverageAmount NOT available.");
				throw new CustomException(strCoverageAmount
						+ " coverageAmount NOT available.");
			} else {
				performAction.click(nextButton, "Next button.");
			}

			if (performAction.isElementPresent(preTaxChioce)) {
				performAction.click(nextButton, "Pretax Next button.");
			}
			if (performAction.isElementPresent(nextNewMemberUi)) {
				performAction.click(nextNewMemberUi, "Click Next button.");
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while selecting coverage Amount"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while selecting coverage Amount "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Enter Additional Insurance Details for Member in Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Additional Insurance Page Opened in Member Role
	 * 
	 * PostCondition : Updated Additional Insurance Details for Member in Member Role, verify policy number when previous button is clicked
	 * 
	 * Java File Name : AddBenefitsPage.java
	 * 
	 *  * <b>Parameters & Example </b>
	 *
	 * |yes/no option  , policyNumber , previous  etc - is used to get the data set from the Json|
	 *
	 * | ex: A123456789 | ex: Mercer | ex: currentdate |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPolicyDetails" })
	public void addAdditionalInsuranceDetailsAndVerify(String strPolicyDetails) {
		try {
			if (strPolicyDetails != null) {
				Object object = null;
				JSONObject fields = ReadJsonTestData
						.getTestData(strPolicyDetails.toLowerCase());


				object = fields.get("option");
				if (object != null) {
					String buttonName=object.toString();
					if (buttonName.equalsIgnoreCase("no")){
						this.clickNoOption();
						scr.capturePageScreenshot();
						performAction.waitForPageLoad();
						Assert.assertTrue(performAction.isElementPresent(benefitsPageSaveButton, "Save Button displayed"));
					}
					else if (buttonName.equalsIgnoreCase("yes")){
						this.clickYesOption();

						object = fields.get("policynumber");
						String policynumber = object.toString();

						object = fields.get("buttontype");
						if (object != null) {
							String buttontype = object.toString();
							if (buttontype.equalsIgnoreCase("previous")){
								this.clickPreviousButtonAndVerify(policynumber);
						}
						}
						else {
							this.enterPolicyNumber(policynumber);
						}
			this.enterPolicyHolder();
						object = fields.get("carriername");
						if (object != null) {
							this.enterCarrierName(object.toString());
						}

						object = fields.get("effectivedate");
						if (object != null) {
							this.enterPersonsEffectiveDate(object.toString());
						}
						this.clickNextButton();
						scr.capturePageScreenshot();
			this.saveAdditionalInsurance();
						performAction.waitForPageLoad();
						Assert.assertTrue(performAction.isElementPresent(completeEnrollment, "complete Enrollment displayed"));
					}
				}
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while adding additional insurance details"
					+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 *
	 * Role : Member Role
	 *
	 * Description   : "Select Dependents To Be Covered By Benefit" Keyword to select dependents based on the Coverage Level selected. 
	 *
	 * PreCondition  : Navigate to Dependents to be covered by Benefit page(eg : This page is available after selecting Coverage Level)
	 *
	 * PostCondition : Able to select Dependents
	 * <pre>
	 * <b>Parameters :</b>
	 * | "strDependentName"|
	 * | "HMVlastname or DepChild57545" |
	 * 
	 * Java File Path : com.benefitfocus.robot.member >> AddBenefitsPage.java
	 * 
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strDependentName"})
	public void selectDependentsToBeCoveredByBenefit(String strDependentName) {
		try {
			String dependentName;
			if(strDependentName.startsWith("HMV")||strDependentName.startsWith("td:")){
				dependentName=utils.getValue(strDependentName);
			}else{
				dependentName=strDependentName;
			}
			if(this.selectDependents(dependentName)){
				this.clickNextButton();
			}
			scr.capturePageScreenshot();
		}catch (Exception e) {
			logger.info("Exception while selecting dependents to be covered");
			scr.capturePageScreenshot();
			throw new CustomException("Exception while selecting dependents to be covered"
					+ e.getMessage());
		}
	}
}
