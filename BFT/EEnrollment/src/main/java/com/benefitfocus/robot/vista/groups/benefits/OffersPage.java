package com.benefitfocus.robot.vista.groups.benefits;

import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.vista.groups.basics.BasicCommon;
import junit.framework.Assert;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

import java.util.List;

@RobotKeywords
public class OffersPage {

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
	protected BasicCommon common;


	// Global variables
	String GivetheBenefitElementname;
	String strOffername = "";
	String ActualOfferName;
	String getOfferName;
	String getOfferName1;
	String startdate = "";
	String getstartdate = "";
	String coppOfferStartDate = "";
	String coppOfferEndDate = "";
	String strofferType1="";
	String OfferName="";
	String strOfferName="";
	String value = "";

	// Locators on the Page
	By CreateNewOffer = By.xpath("//a[contains(text(),'Create New Offer')]");
	By OfferNameTextBox = By.id("offerName-ENGLISH");
	By ParticipationPeriodDropDown = By
			.xpath("//select[contains(@id,'participationPeriodE')]");
	By StartDate = By.id("participationPeriodEntry.startDate");
	By EndDate = By.id("participationPeriodEntry.endDate");
	By participationPeriodStartDate = By.id("newParticipationPeriodStartDate");
	By participationPeriodEndDate = By.id("newParticipationPeriodEndDate");
	By save = By.xpath("//strong[text()='Save']");
	By OfferTypeDropDown = By.xpath("//select[@id='offerType']");
	By joinWithOfferDropDown = By.id("idOfSelectedOfferToBeMerged");
	By BundleDriver = By.xpath("//select[@id='bundleDriven']");
	By BenefitElement = By
			.xpath("//div[@id='templateElementChoices']/div/div/strong");
	By GroupTemplate = By.name("templateSelection");
	By SelectedBenefitElements = By
			.xpath("//div[contains(@style,'padding')]/ul/li");
	By FilterByOfferDropDownListBox = By
			.xpath("//select[@class='DropDownList']");
	By Currentoffersoptions = By
			.xpath("//a[contains(@id,'innerLinkmenucache')]");
	By deleteOfferLink = By.xpath("//a[contains(@id,'deleteLink')]");
	By CopyOfferLink = By.xpath("//a[contains(text(),'Copy Offer')]");
	By DeleteConfirmationMessage = By.xpath("//a[text()='Yes']");
	By NextButton = By.xpath("//strong[text()='Next']");
	By CopyOfferButton = By.xpath("//strong[text()='Copy Offer']");
	By offerDeatilsTextBox = By
			.xpath("//input[@class='small' and @maxlength='50']");
	By JoinOfferLiink = By.xpath("//a[text()='Join Offer']");

	// Locators in Benefits >> Offers Page
	By offerOptions=By.linkText("Offer Options");
	By collectingEmployeeContribution=By.id("selectedConfigProcessStrategyType-0-FSACONFIG");
	By maximumAnnualContribution=By.xpath("//div[contains(text(),'Maximum Annual Contribution')]//input");
	By minimumAnnualContribution=By.xpath("//div[contains(text(),'Minimum Annual Contribution')]//input");
	By offerText = By.xpath("//div[@id='innerInnerInnerInnerPageCore']/div[3]/table/tbody/tr[3]/td[2]");
	By plaName =By.xpath("//table/tbody/tr[2]/td[2]/div/ul/li");

	// Locators in Current Offers page
	By selectPlanFromCurrentOffersFilter=By.className("DropDownList");
	By editOfferDetails=By.linkText("Edit Offer Details");

	By showbutton   = By.xpath("//a[contains(@id,'innerLinktoggleRegionofferListItem')]");

	//Cobra offer Page Elements
	By createOffersPlanElementPage   = By.xpath("//td/h1[contains(text(),'Create COBRA Offer for Elements')]");

	//Offer Enablements locators
	By OfferEnablementEdit =By.xpath("//div[@id='innerInnerInnerInnerPageCore']/div[3]/table/tbody/tr[3]/td[1]/a/img");
	By SelectAlllink =By.xpath("//a[text()='Select All']");
	By DeleteAllButton =By.xpath("//a[text()='Delete All']");
	By AddEnablementsButton =By.xpath("//a[text()='Add Enablement(s)']");
	By XDaysTextBox=By.xpath("//div[@id='subfieldListContainer-selectedConfigProcessStrategyType-0-BACKDATECONFIG']/table/tbody/tr/td/div/input");
	By editOfferDetailsHeaderText=By.xpath("//h1[text()='Edit Offer Details']");
	//locators in Edit Dependency rule page
	By HideFromUserDropdown = By.id("hideFromUserIfIneligible");
	By ParentOffer = By.id("sponsorBenefitOfferHandle");
	By ParentElementDropdown = By.id("parentElementCacheKey");
	By ParentElementsSelectionBox = By.id("selectedParentBenefitElementKeys");
	By editdependencyrule = By.xpath("//div[@class='minimalRegion'][3]//a[contains(text(),'Edit Dependency Rule')]");
	By deleteButton = By.linkText("Delete");
	By radioButton = By.xpath("//td[@class='fieldListField']//input[@value='DEPEND_CUSTOM_PLAN']");
	By parentElement = By.xpath("//select[@id='newParentBenefitElements']//option[contains(text(),'Health Savings Account (HSA)')]");
	By parentElementPlans = By.xpath("//select[@id='newParentBenefitElementPlans']//option[contains(text(),'Refused/Cancelled')]");
	By dependentElement = By.xpath("//select[@id='newChildBenefitElementPlans']//option[contains(text(),'FSA')]");
	By addRule = By.linkText("Add");
	By allLink = By.xpath("//a[text()='Previous']/parent::*/preceding-sibling::*//a[text()='All']");
	By previousLink = By.linkText("Previous");
	By currentLink = By.linkText("Current");
	By futureLink = By.linkText("Future");
	By hideFromUser = By.xpath("//select[@id='hideFromUserIfIneligible']");
    By filterByOffer = By.className("DropDownList");
    By saveButton = By.xpath("//strong[contains(text(),'Save')]");
	

	// Pre-Qualifying survey
	By preQualifySurvey = By.id("surveyContainer");
	By preQualifyQuestion = By.className("surveyQuestionContainer");
	By empName = By.xpath("//div[@id='surveyContainer']/label");
	By Questions = By.xpath("(//span[@class='requiredIndicator']/..)");
	By nextButton = By.xpath("//button[contains(text(),'Next')]");
		
	By editPreQualifyingSurveys=By.linkText("Edit Prequalifying Surveys");
	By editPreQualifyingSurvey = By.xpath("//a[contains(@href,'javascript:void(0);')]//img");
	By editPreQualifyingSurveyDetails = By.linkText("Edit Prequalifying Survey Details");
	By preQualifyingLogic = By.id("preQualifyingSurveyLogicType");
	By prequalifyingSurvey = By.id("selectedSurveyKey");
	
	//PCP General
		By planOptions  = By.xpath("//table[contains(@id,'planOptions')]");
		By editPlanLink = By.linkText("Edit Plan Details");

		By currentplantext = By.xpath("//h1[text()='Current Plans']");
	@ArgumentNames({ "strPlanName" })
	private void editPlanSurvey(String strPlanName) {
		if (strPlanName.equalsIgnoreCase("Medical")) {
			performAction.mouseOver(editPreQualifyingSurvey, "Edit Medical PreQualifying Survey");
			performAction.click(editPreQualifyingSurveyDetails, "Edit Medical PreQualifying Surveys");
			
		}else if (strPlanName.equalsIgnoreCase("HSA")) {
			performAction.mouseOver(editPreQualifyingSurvey, "Edit HSA PreQualifying Survey");
			performAction.click(editPreQualifyingSurveyDetails, "Edit HSA PreQualifying Surveys");
		}
	}
	
	@ArgumentNames({ "strprequalifyinglogic" })
	private void qualifyingLogic(String strprequalifyinglogic) {
		performAction.select(preQualifyingLogic, strprequalifyinglogic, "Select as Static Prequalifying Survey");
	}
	
	@ArgumentNames({ "strprequalifyingsurvey" })
	private void qualifyingSurvey(String strprequalifyingsurvey) {
		performAction.select(prequalifyingSurvey, strprequalifyingsurvey, "Select as The University of Kansas Hospital New Hire Tobacco Use Declaration");
	}
	
	private void saveConfiguration() {
		performAction.click(save, "Click Save");
	}
	
	// Edit Offer Details from Current Offer options
	private void editPreQualifyingSurveysFromCurrentOfferOptions(){
		performAction.click(editPreQualifyingSurveys, "Edit PreQualifying Surveys from Current Offer options");
	}

	String showOffer = "/parent::td/following-sibling::td//a";


	//Global Variables
	String CreatedPlaname ="";

	// click on New offer
	private void ClickonNextButton() {
		performAction.click(NextButton, "Next Button");
	}

	// click on New offer
	private void ClickonNewOffer() {
		performAction.click(CreateNewOffer, "Create New Offer Button");
	}

	// Save Button
	private void clickSaveButton() throws Exception {
		performAction.click(save, "save Field");
	}

	// offer name
	private void setOfferName(String strOffername) {

		performAction.clearEnter(OfferNameTextBox, strOffername, "OfferName");
	}

	// Start Date
	private void setStartDate(String strStartdate) {

		performAction.clearEnter(StartDate, strStartdate, "StartDate");
		ActualOfferName = browser.getCurrentWebDriver()
				.findElement(By.id("offerName-ENGLISH")).getAttribute("value");
	}

	// End Date
	private void setEndDate(String strEnddate) {

		performAction.clearEnter(EndDate, strEnddate, "EndDate");
	}

	// Group Template
	private void groupTemplateDropDown(String strGroupTemplateName)
	{
		performAction.select(GroupTemplate, strGroupTemplateName,
				"Group Template DropDownList");
		performAction.waitForPageLoad();
	}

	// Filtered By offer DropDown and delete the offer name in the offers list
	private void verifyVistaOfferName(String strFilterByTimeValue,
			String strOfferName) {

		try {
			By FilterByTime = By.xpath("//a[contains(text(),'"
					+ strFilterByTimeValue + "')]");
			performAction.click(FilterByTime, "Filter By Time Link");
			performAction.waitForPageLoad();
			// get the plan name from the Hashmap
			getOfferName = utils.getValue(strOfferName);
			performAction.select(FilterByOfferDropDownListBox, getOfferName,
					"FilterByOffer DropDownList");

			if (getOfferName.trim().equals(ActualOfferName.trim())) {

				logger.info("your Offer name has been verifed Successfullly : "
						+ getOfferName);
			} else {
				logger.info("Your Created Offer  : " + getOfferName
						+ " is not Available in the below mentioned Offer List. ");
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			System.out.println("No Such Offer name in the page " + e.getMessage());
		}
	}

	// Filtered By offer DropDown and delete the offer name in the offers list
	private void deleteVistaOfferr(String strOfferName) {
		try {
			// Giving the All value in the DropDown List
			performAction.select(FilterByOfferDropDownListBox, "All",
					"FilterByOffer DropDownList");

			performAction.waitForPageLoad();

			// get the offer name from the Hashmap
			getOfferName = utils.getValue(strOfferName);
			performAction.select(FilterByOfferDropDownListBox, getOfferName,
					"FilterByOffer DropDownList");
			performAction.waitForPageLoad();
			System.out.println(ActualOfferName);
			//performAction.click(showbutton, "Show Button");
			performAction.mouseOver(Currentoffersoptions, "OfferOptions");
			performAction.waitForPageLoad();
			performAction.click(deleteOfferLink, "Delete the Offer");
			performAction.waitForPageLoad();
			performAction.click(DeleteConfirmationMessage,
					"Delete Confirmation Message");
			logger.info("your Offer : " + getOfferName
					+ " has been Deleted Successfullly ");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Benefit Elements
	private void BenefitElements( String strofferType1, String strofferName1)  {

		try {

			//select ting the benefit value from the drop down list
			By BenefitElement = By
					.xpath("//div[@id='templateElementChoices']/..//div/strong[text()='"+strofferType1+"']/..//div[contains(text(),'"+strofferName1+"')]");
			performAction.click(BenefitElement, "Benefit Element");

			// verifying the Benefit value in the SelectedBenefitElements list
			List<WebElement> selectedBenefitElements = browser
					.getCurrentWebDriver().findElements(
							By.xpath("//div[contains(@style,'padding')]/ul/li"));
			for (int j = 1; j <=selectedBenefitElements.size(); j++) {

				String ActualSelectedBenefitElementName = browser
						.getCurrentWebDriver()
						.findElement(
								By.xpath("//div[contains(@style,'padding')]/ul/li["
										+ j + "]")).getText();
				if (strofferName1.trim().equalsIgnoreCase(
						ActualSelectedBenefitElementName.trim())) {
					logger.info("your Benefit Plan name is available in the SelectedBenefitElements list : "
							+ ActualSelectedBenefitElementName);
					break;
				} else {
					logger.error("your Benefit Plan name is not available in the SelectedBenefitElements list");
				}
			}


		}catch (Exception e) {
			throw new CustomException(
					"Exception occured while  Creating the New Vista Offer "
							+ e.getMessage());
		}
	}


	private void addPlans(String strOfferName,String strPlanName) {

		try {
			// get the plan name from the Hashmap
			getOfferName = utils.getValue(strOfferName);
			performAction.select(FilterByOfferDropDownListBox, getOfferName,
					"FilterByOffer DropDownList");

			if (getOfferName.trim().equals(ActualOfferName.trim())) {
				//click on Add plans Button for particular offer name
				performAction.click(By.xpath("//a[contains(text(),'Add Plans')]"), "Add Plans ");
				List<WebElement> availablepalns = browser.getCurrentWebDriver().findElements(By.xpath("//div[@id='availablePlans']/..//div[@title='Template Plan']"));
				for (int i = 1; i <= availablepalns.size(); i++) {
					String availableplanname = browser.getCurrentWebDriver().
							findElement(By.xpath("//div[@id='availablePlans']/..//div[@title='Template Plan']["+i+"]")).getText();
					if (strPlanName.equals(availableplanname)) {
						performAction.click(By.xpath("//div[@id='availablePlans']/..//div[@title='Template Plan']["+i+"]"), "Select Plan Box");
						break;
					}
				}
				//click on save button
				this.clickSaveButton();
				performAction.waitForPageLoad();
				// get the plan name from the Hashmap
				getOfferName = utils.getValue(strOfferName);
				performAction.select(FilterByOfferDropDownListBox, getOfferName,
						"FilterByOffer DropDownList");
				CreatedPlaname = browser.getCurrentWebDriver().findElement(plaName).getText();
				if (strPlanName.equals(CreatedPlaname)) {
					logger.info("you have successfully created a plan to offer and your plan name is : " +CreatedPlaname);
				}
			} else {
				logger.info("you offer to plan has not been  created successfully");
			}
		} catch (Exception e) {

			throw new CustomException(
					"Exception occured while  Creating plan to offer "
							+ e.getMessage());
		}

	}

	// Filtered By offer DropDown and click on the offer name in the offers list
	// and mouse over on that offer options button and click CopyOffer Link.
	private void CopyOffer(String strOfferName, String strFilterByTimeValue) throws Exception {
		try {

			By FilterByTime = By.xpath("//a[contains(text(),'"
					+ strFilterByTimeValue + "')]");
			performAction.click(FilterByTime, "Filter By Time Link");
			//performAction.waitForPageLoad();
			// Giving the All value in the DropDown List
			performAction.select(FilterByOfferDropDownListBox, "All",	"FilterByOffer DropDownList");
			//performAction.waitForPageLoad();
			// get the offer name from the Hashmap
			getOfferName = utils.getValue(strOfferName);
			performAction.select(FilterByOfferDropDownListBox, getOfferName,
					"FilterByOffer DropDownList");
			//performAction.waitForPageLoad();
			if (getOfferName.trim().equals(ActualOfferName.trim())) {
				performAction.mouseOver(Currentoffersoptions, "OfferOptions");
				performAction.waitForPageLoad();
				performAction.click(CopyOfferLink, "Copy the Offer");
				performAction.waitForPageLoad();
				String ExpectedPage = "Copy Offer";
				String ActualPage = browser.getCurrentWebDriver()
						.findElement(By.xpath("//h1")).getText();
				if (ExpectedPage.equals(ActualPage.trim())) {

					logger.info("Now your are in " + ActualPage + " Page.");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// copy offer page , Participation Period StartDate
	private void setcopyofferStarDate(String strStartDate) {

		performAction.clearEnter(participationPeriodStartDate, strStartDate,
				"Participation Period StartDate ");
		// read the start date text value
		coppOfferStartDate = browser.getCurrentWebDriver()
				.findElement(By.id("newParticipationPeriodStartDate"))
				.getAttribute("value");

	}

	// copy offer page , Participation Period EndDate
	private void setcopyofferEndDate(String strEndDate) throws Exception {

		try {
			performAction.clearEnter(participationPeriodEndDate, strEndDate,
					"Participation Period EndDate ");
			Thread.sleep(2000);
			// read the End date text value
			coppOfferEndDate = browser.getCurrentWebDriver()
					.findElement(By.id("newParticipationPeriodEndDate"))
					.getAttribute("value");
			this.ClickonNextButton();
			// to append the values coppOfferStartDate and coppOfferEndDate and
			// storing the two values in the "ExpectedCopyofferDate".
			String ExpectedCopyofferDate = coppOfferStartDate + " - "
					+ coppOfferEndDate;

			String ActualCopyofferDate = browser
					.getCurrentWebDriver()
					.findElement(
							By.xpath("//label[@for='newParticipationPeriod']/../../td[2]/div"))
							.getText();

			if (ExpectedCopyofferDate.trim().equals(ActualCopyofferDate.trim())) {

				logger.info("Your New Participation Period Date:"
						+ ActualCopyofferDate
						+ "has been verifed successfully in the  Coppy Offer page. ");
				performAction.clearEnter(offerDeatilsTextBox, getOfferName,
						"Entering Offer Name inthe offer Details TExt Box");
				Thread.sleep(2000);
				performAction.click(CopyOfferButton, "CopyOffer Button ");
				Thread.sleep(2000);
				// read the successfull message on the console screen
				String copyOfferPageMessage = browser
						.getCurrentWebDriver()
						.findElement(
								By.xpath("//div[contains(text(),'Your inform')]"))
								.getText();
				logger.info("" + copyOfferPageMessage + "");

			} else {
				logger.info("Your New Participation Period Date:"
						+ ActualCopyofferDate
						+ " has not been verifed successfully in the Coppy Offer page. ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Click Show Details link against Benefit Name in Benefits >> Offers page
	private void clickHFSAShowLink(String strBenefitName){
		browser.getCurrentWebDriver().findElement(By.xpath("//h2[text()='"+strBenefitName+"']/../..//a")).click();
	}
	// Click Offer Options
	private void clickOfferOptions(String strBenefitName){
		browser.getCurrentWebDriver().findElement(By.xpath("//h2[text()='"+strBenefitName+"']/../../../../..//a[contains(text(),'Offer Options')]")).click();
	}
	// Click Edit Offer Details Option
	private void clickEditOfferDetails(String strBenefitName){
		browser.getCurrentWebDriver().findElement(By.xpath("//h2[text()='"+strBenefitName+"']/../../../../..//a[contains(text(),'Edit Offer Details')]")).click();
	}
	// Select option from Collecting Employee Contribution
	private void selectEmployeeContribution(String strEmployeeContribution){
		performAction.select(collectingEmployeeContribution, strEmployeeContribution, "Select Employee Contribution");
	}
	// Select option from Collecting Employee Contribution
	private void enterMinimumContributionAmount(String strMinimumContributionAmount){
		performAction.clearEnter(minimumAnnualContribution, strMinimumContributionAmount, "Enter Minimum Anuual Contribution");
	}
	// Select option from Collecting Employee Contribution
	private void enterMaximumContributionAmount(String strMaximumContributionAmount){
		performAction.clearEnter(maximumAnnualContribution, strMaximumContributionAmount, "Enter Maximum Anuual Contribution");
		scr.capturePageScreenshot();
	}
	// Select Offer from Current Offer Filter options
	private void selectOfferfromCurrentOfferFilter(String strOfferName){
		performAction.select(selectPlanFromCurrentOffersFilter, strOfferName, "Select Offer from Current Offer Filter options");
	}
	// Mouse Over Offer from Current Offers
	private void mouseOverOnCurrentOffers(){
		performAction.mouseOver(offerOptions, "Mouse Over Offer from Current Offers");
	}
	// Edit Offer Details from Current Offer options
	private void editOfferFromCurrentOfferOptions(){
		performAction.click(editOfferDetails, "Edit Offer Details from Current Offer options");
	}
	// Click on Offer Type from Benefits >> Offers
	private void clickOnOfferType(String strOfferType){
		By OfferType=By.linkText(strOfferType);
		performAction.click(OfferType, "Click on Offer Type from Offers page");
	}

	// Select dependency rule for given plan
	private void clickDependencyRuleForPlan(String strPlanName){
		By editDependancyRule=By.xpath("//h3[contains(text(),'"+ strPlanName +"')]/parent::div/following-sibling::div//a[contains(text(),'Edit Dependency Rule')]");
		performAction.click(editDependancyRule, "Click on Edit Dependency Rule for given plan");
	}

	//select radio button
	private void selectRadiobutton(By locator,String value,String message){
		performAction.select(locator, value, message);
	}

	//set dependency rule
	private void setDependencyRule(String strDependancyRule){		
		String LocatorVal=null;
		switch(strDependancyRule){

		case "Always Available":
			LocatorVal="ALWAYS";
			break;
		case "Available if user accepts parent element":
			LocatorVal="DEPEND_ACCEPT";
			break;
		case "Available if user accepts parent element(s)":
			LocatorVal="DEPEND_ACCEPT_ALL";
			break;
		case "Available if user refuses parent element":
			LocatorVal="DEPEND_REFUSE";
			break;
		case "Available if user accepts any one of the previous elements":
			LocatorVal="DEPEND_ACCEPT_ANY";
			break;
		case "Element guarantees that the employee is covered on one of the accepted elements":
			LocatorVal="REQUIRE_EMPLOYEE_ONCE";					
		}

		By ruleLocator = By.xpath("//input[@type='radio'][@name='dependRule'][@value='"+ LocatorVal +"']");
		performAction.click(ruleLocator, "select dependency rule for given plan");
	}

	//Vista Offer Enablement
	private void offerEnablement(String strOffer,String strOption) throws Exception{
		//get the Offer name from the Hashmap
		String getOfferName = utils.getValue(strOffer);

		//select the plan name in the drop down list box
		performAction.select(FilterByOfferDropDownListBox, getOfferName, "FilterByPlan DropDownList");

		performAction.click(OfferEnablementEdit , "Click EnablementEdit Button");

		performAction.click(SelectAlllink  , "Click SelectAll Link ");

		performAction.click(AddEnablementsButton  , "Click AddEnablementsButton ");
		if (performAction.isElementPresent(DeleteAllButton)) {
			performAction.click(DeleteAllButton , "Click Delete Button");
		}

		if(strOption.equalsIgnoreCase("enable")){

			if (performAction.isElementPresent(DeleteAllButton)) {
				performAction.click(DeleteAllButton , "Click Delete Button");
			}
			performAction.click(SelectAlllink  , "Click SelectAll Link ");
			performAction.click(AddEnablementsButton  , "Click AddEnablementsButton ");

		}else if(strOption.equalsIgnoreCase("disable")) {
			if (performAction.isElementPresent(DeleteAllButton)) {
				performAction.click(DeleteAllButton , "Click Delete Button");
			}
		}

		this.clickSaveButton();
		performAction.waitForPageLoad();
		String expectedOfferText=getOfferName;
		String offerNameText =browser.getCurrentWebDriver().findElement
				(offerText).getText();

		scr.capturePageScreenshot();
		Assert.assertEquals(expectedOfferText, offerNameText);
		logger.info("Your Offer  : "+OfferName+"");

	}

	private void editDependentRule() {
		performAction.click(editdependencyrule, "Click Dependency Rule");
	}

	private void deleteRule() {
		if (performAction.isElementPresent(deleteButton, "Delete Button")) {
			performAction.click(deleteButton, "Delete button");
		}
	}

	private void dependencyRuleRadioButton() {
		performAction.click(radioButton, "Click on Dependency Rule RadioButton");
	}

	private void setDependencyRulePlans(String strParentElement, String strParentElementPlans, String strDependentPlans) {
        By parentElement = By.xpath("//select[@id='newParentBenefitElements']//option[contains(text(),'"+strParentElement+"')]");
        By parentElementPlans = By.xpath("//select[@id='newParentBenefitElementPlans']/option[contains(text(),'"+strParentElementPlans+"')]");
        By dependentPlans = By.xpath("//select[@id='newChildBenefitElementPlans']//option[contains(text(),'"+strDependentPlans+"')]");
		performAction.click(parentElement, "Click Dependency Rule");
		performAction.click(parentElementPlans, "Click on Parent Element Option");
		performAction.click(dependentPlans, "Click on Child Element Option");
		performAction.click(addRule, "Click on Add Button");
	}
	// clicks on show or hide button of a plan
	private void clickShowOffer(String offerName, String action) {
		By showOfferLink = By
				.xpath("//h2[text()='" + offerName + "']" + showOffer);
		performAction.click(showOfferLink, "Show or Hide Plan Button");
		String buttonText = browser.getCurrentWebDriver()
				.findElement(showOfferLink).getText();
		if (action.equalsIgnoreCase("Show"))
			Assert.assertTrue(buttonText.equalsIgnoreCase("Hide"));
		else
			Assert.assertTrue(buttonText.equalsIgnoreCase("Show"));
		logger.info(" ***** Assert passed "
				+ browser.getCurrentWebDriver().findElement(showOfferLink)
				.getText());

	}

	/* Click on "Current, Previous, Future," links in Offers Page */
	private void selectOfferByTime(String action) {
		By actionLink =  By.linkText(action);
		if(action.equalsIgnoreCase("All")){
			performAction.click(allLink, "All");
		}
		else{
			performAction.click(actionLink, "Click on "+action);
		}
		performAction.waitForPageLoad();
		performAction.verifyMessage(action+" Offers");
	}
	
	private void setHideFromUser(String strHideFromUser){
		performAction.select(hideFromUser, strHideFromUser, "Select hide from user value");
	}
	private void filterByOffer(String strOffer){
		performAction.select(filterByOffer, strOffer, "Select Offer");
	}


	//we can give multiple benefitelemnts with this keyword
	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : 'createOfferInVistaRole' Keyword or method is used to perform CreateVistaOffer in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Create New Offer Page in Vista Admin  Role.
	 *
	 * PostConditions : Current Offers list Page.
	 *
	 * <b>Parameters & Example </b>
	 *
	 *  | classification | outOfferName |
	 *
	 * | createnewoffer - is used to get the data set from the Json File "eEnrollmentCommon.json". |
	 *
	 * | outOfferName - is a variable name and it will be used to store the OfferName of the created Offer. |
	 *
	 * Java FileName : OffersPage.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strCreateNewOffer", "strOutOfferName" })
	public void createOfferInVistaRole(String CreateOffervalues, String OutOfferName) {

		try {

			this.ClickonNewOffer();
			if (CreateOffervalues.startsWith("td:"))
				CreateOffervalues = CreateOffervalues.substring(3);

			Object object = null;
			Object object1 = null;

			JSONObject fields = ReadJsonTestData.getTestData(CreateOffervalues
					.toLowerCase());
			System.out.println("fields = " + fields.toJSONString());
			object = fields.get("offerName");

			if (object != null) {

				// OfferName Field and return the Offer Name as a
				// OutputOffername
				strOffername = utils.generateRandomNumber(object.toString());
				this.setOfferName(strOffername);
				browser.hMap.put(OutOfferName, strOffername);
			}
			object = fields.get("startdate");
			if (object != null) {
				this.setStartDate(utils.getDate(object.toString()));
			}

			object = fields.get("enddate");
			if (object != null) {
				this.setEndDate(utils.getDate(object.toString()));
			}
			// Group Template
			object = fields.get("grouptemplate");
			if (object != null) {
				this.groupTemplateDropDown(object.toString());
			}
			// selecting multiple Benefit Elements
			//offertype
			object = fields.get("offerType1");
			String[] offertype1=object.toString().split("_");
			int offertype1count=offertype1.length;
			//offerName
			object1 = fields.get("offerName1");
			String[] offername1=object1.toString().split("_");
			int offername1count=offername1.length;

			//selecting offertype and offername values
			if (object != null) {
				for (int i = 0; i <offertype1count; i++) {
					for (int j = 0; j <offername1count; j++) {
						if (i==j) {
							this.BenefitElements(offertype1[i],offername1[j]);
						}
					}
				}
			}

			//click on save button
			this.clickSaveButton();
			//verify the plans page is available or not
			boolean flag=performAction.isElementPresent(createOffersPlanElementPage);
			if (flag) {
				performAction.click(By.xpath("//label[contains(text(),'"+strofferType1+"')]"), "BenefitElement Check Box");
				//Click on Save button
				this.clickSaveButton();
			}

		} catch (Exception e) {
			logger.info("Exception occured while  Creating the New Vista Offer "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while  Creating the New Vista Offer "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : verifyVistaOffer Keyword or method is used to perform verify the VistaOffer in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Current Offer Page in Vista Admin  Role.
	 *
	 * PostConditions : Current vista Offer .
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification |
	 *
	 * | strOfferName |
	 *
	 * | Auto1245 |
	 *
	 * Java File Name : OffersPage.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strFilterByTimeValue", "strOfferName" })
	public void verifyVistaOffer(String strFilterByTimeValue,
			String strOfferName) {

		try {
			// verify the Offer and delete offer.
			this.verifyVistaOfferName(strFilterByTimeValue, strOfferName);

		} catch (Exception e) {
			logger.info("Exception occured while Verifying the VistaOfferName " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while Verifying the VistaOfferName "
					+ e.getMessage());

		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : DeletVistaOffer Keyword or method is used to perform to Delete the VistaOffer in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Current Offer Page in Vista Admin  Role.
	 *
	 * PostConditions : Current vista Offer .
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification |
	 *
	 * | strOfferName |
	 *
	 * | Auto1245 |
	 *
	 * Java File Name : OffersPage.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strOfferName" })
	public void deleteVistaOffer(String strOfferName) {

		try {
			// Delete offer.
			this.deleteVistaOfferr(strOfferName);

		} catch (Exception e) {
			logger.info("Exception occured while Deleting the VistaOffer"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Deleting the VistaOffer "
							+ e.getMessage());

		}

	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : CopyOffer Keyword or method is used to perform to Copy the Current VistaOffer in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Current Offer Page in Vista Admin  Role.
	 *
	 * PostConditions : Current vista Offer is  copied successfully..
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification | strPlanName | strFilterByTimeValue |
	 *
	 * | copyoffer - is used to get the data set from the Json File "eEnrollmentCommon.json". | 
	 *
	 * | strOfferName - is a variable name and it will be used to use from the strOffername to copy the current plan. |
	 * 
	 * | strFilterByTimeValue - is a variable name and it will be used to pass the Filter type By Time Link |
	 * 
	 * Java File Name : OffersPage.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strOfferName", "copyofferdates" , "strFilterByTimeValue" })
	public void copyVistaOffer(String strOfferName, String copyofferdates, String strFilterByTimeValue) {

		try {

			// Delete offer.
			this.CopyOffer(strOfferName, strFilterByTimeValue);

			// Json Data Read
			if (copyofferdates.startsWith("td:"))
				copyofferdates = copyofferdates.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(copyofferdates
					.toLowerCase());
			System.out.println("fields = " + fields.toJSONString());

			object = fields.get("startdate");
			if (object != null) {

				this.setcopyofferStarDate(utils.getDate(object.toString()));
			}
			object = fields.get("enddate");
			if (object != null) {

				this.setcopyofferEndDate(utils.getDate(object.toString()));
			}

		} catch (Exception e) {
			logger.info("Exception occured while Copying the VistaOffer"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Copying the VistaOffer "
							+ e.getMessage());

		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 *
	 * Description  : 'Enter HFSA Min/Max Contribution' keyword used to enter Min/Max contribution for Health FSA from 'Benefits >> Offers' in Vista Role.
	 *
	 * PreCondition : Navigate to 'Benefits >> Offers' page.
	 *
	 * PostCondition: Able to enter Min/Max contributions.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strBenefitName | strMinimumContributionAmount | strMaximumContributionAmount |
	 * | COBRA HFSA Offer 2016  | 50 | 500 |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strBenefitName","strMinimumContributionAmount","strMaximumContributionAmount" })
	public void enterHFSAMinMaxContribution(String strBenefitName,String strMinimumContributionAmount,String strMaximumContributionAmount) {

		try {
			this.clickHFSAShowLink(strBenefitName);
			this.clickOfferOptions(strBenefitName);
			this.clickEditOfferDetails(strBenefitName);
			performAction.waitUntilElementPresent(editOfferDetailsHeaderText);
			performAction.verifyMessage(strBenefitName);
			this.selectEmployeeContribution("Ask for Employee Contribution");
			this.enterMinimumContributionAmount(strMinimumContributionAmount);
			this.enterMaximumContributionAmount(strMaximumContributionAmount);
			this.clickSaveButton();
		} catch (Exception e) {
			throw new CustomException(
					"Exception in entering HFSA Min/Max Contribution "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : addOfferToPlansInVistaRole Keyword or method is used to perform to addplan to VistaOffer in   Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Current Offer Page in Vista Admin  Role.
	 *
	 * PostConditions : Current vista Offer .
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification |
	 *
	 * | strOfferName | strPlanName |
	 *
	 * | Auto1245 | Narrow/Savenet HMO (DMHC) |
	 *
	 * Java File Name : OffersPage.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({  "strOfferName" , "strPlanName"})
	public void addOfferToPlansInVistaRole(String strOfferName,String strPlanName) {

		try {

			// creating offer to plan
			this.addPlans( strOfferName, strPlanName);
			scr.capturePageScreenshot();

		} catch (Exception e) {
			logger.info("Exception occured adding offer to plan"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured adding offer to plan "
							+ e.getMessage());

		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 *
	 * Description   : 'Edit Offer In Vista Role' keyword used to edit offers and navigate to 'Edit Offer Details' page in Vista Role
	 *
	 * PreCondition  : Login as Vista Admin >> Perform Group Search. Navigate to "Benefits >> Offers" page
	 *
	 * PostCondition : Able to navigate to Edit Offer Details page
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strOfferType,strOfferName |
	 * | Current or Previous,Medical 2016 |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strOfferType","strOfferName"})
	public void editOfferInVistaRole(String strOfferType,String strOfferName) {
		try {
			this.clickOnOfferType(strOfferType);
			performAction.isElementPresent(selectPlanFromCurrentOffersFilter);
			//updated to retrieve the name of the Offer from the Hashmap......
			if(strOfferName.startsWith("HMV")){
				String getOfferName = utils.getValue(strOfferName);
				this.selectOfferfromCurrentOfferFilter(getOfferName);
			}
			else
				this.selectOfferfromCurrentOfferFilter(strOfferName);
			this.mouseOverOnCurrentOffers();
			this.editOfferFromCurrentOfferOptions();
			//performAction.verifyMessage("Edit Offer Details");
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in editing Offers In Vista Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in editing Offers In Vista Role"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 *
	 * Description   : 'Enable Options From Edit Offer Details In Vista Role' keyword used to enable required options from edit offer details page in Vista Role
	 *
	 * PreCondition  : Login as Vista Admin >> Perform Group Search. Navigate to "Benefits >> Offers >> Edit Offer Details" page
	 *
	 * PostCondition : Able to enable options from Edit Offer Details page
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strBenefit , strLabel,strOption |
	 * | Medical/HRA, Accepting Benefit,Require user to Accept or Refuse |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strBenefit","strLabel","strOption"})
	public void enableOptionsFromEditOfferDetailsInVistaRole(String strBenefit, String strLabel,String strOption) {
		try {

			String benefitElement = ".//h2[text()='"+ strBenefit +"']/ancestor::div[@class='secondaryRegion']";
			By selectOption=By.xpath(benefitElement + "//label[text()='"+strLabel+"']/../..//select");
			performAction.select(selectOption, strOption, "Select Option from Edit Offer Details page");
			this.clickSaveButton();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting options from Edit Offer Details page In Vista Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting options from Edit Offer Details page In Vista Role"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Srilatha A
	 *
	 * Description   : 'Enable the offer in Vista Role
	 *
	 * PreCondition  : Login as Vista Admin >> Perform Group Search. Navigate to "Benefits >> Offers >> Offer Enablements
	 *
	 * PostCondition : Offer is already created
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strOffer |
	 * | Accepting strOffer |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strOfferName" ,"strOption" })
	public void vistaOfferEnablement(String strOffer,String strOption){

		try {
			// Offer Enablement or disablement as per stroption
			this.offerEnablement(strOffer,strOption);
			scr.capturePageScreenshot();

		}catch (Exception e) {
			logger.info("Exception occured While performing Vista Offer Enablement "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured While performing Vista Offer Enablement "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Set Dependency Rule for Medical/HSA plan to HSA/FSA Plans in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Open Offers Page in Vista Role
	 * 
	 * PostCondition : Dependency Rule Set for Plans
	 * 
	 * Java File: OfferPage.java
	 *
     * Parameters:
     * | strOffer | strParentElement | strParentElementPlans | strDependentPlans | strHideFromUser |
     * | Medical | Medical/HSA | Medical/Refused | Medical/FSA | Display this benefit Element |
     *
	 * </pre>
	 **/
    @RobotKeyword
    @ArgumentNames({"strOffer", "strParentElement", "strParentElementPlans", "strDependentPlans", "strHideFromUser"})
	public void setDependencyRuleForPlans(String strOffer, String strParentElement, String strParentElementPlans, String strDependentPlans, String strHideFromUser) {
		try {
            this.filterByOffer(strOffer);
            this.editDependentRule();
            this.deleteRule();
            this.dependencyRuleRadioButton();
            this.setDependencyRulePlans(strParentElement, strParentElementPlans, strDependentPlans);
            this.setHideFromUser(strHideFromUser);
            scr.capturePageScreenshot();
            this.clickSaveButton();
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception occured while adding dependency rule for plans"
                    + e.getMessage());
        }
	}
	/**
	 * <pre>
	 * Author  : Sunitha Yerra
	 * 
	 * Description : This keyword is used to show or hide a offer details in Offers page in Vista role 
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Offers page should be open in Vista Role
	 * 
	 * PostCondition : Details of a offer should be displayed or hidden 
	 * 
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | strOfferName - string | strAction - string
	 * | Medical 2016    |  Show  |
	 * | Medical 2016    |  Hide  |
	 * </pre>
	 * 
	 * Java file Name : OffersPage.java
	 **/
	@RobotKeyword
	@ArgumentNames({ "strOfferName", "strAction" })
	public void showHideOffer(String strOfferName, String strAction) {
		try {
			this.clickShowOffer(strOfferName, strAction);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception occured in click on Show or Hide button of a offer"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in click on Show or Hide button of a offer "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sunitha Yerra
	 * 
	 * Description : This keyword is used to filter the offers by time (Current, Future, ..) 
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Offers page should be open in Vista Role
	 * 
	 * PostCondition : Offers will be filtered on the basis of selected time 
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | strAction - string
	 * |  Show  |
	 * |  Hide  |
	 * </pre>
	 * 
	 * Java file Name : OffersPage.java
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAction" })
	public void FilterOfferByTime(String strAction) {
		try {
			this.selectOfferByTime(strAction);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception occured in Filter Offer by time"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in  Filter Offer by time "
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Srikanth G
	 *  
	 * Description   : Set dependency rule for given plan under given offer.
	 * 
	 * Role: Vista Role
	 * 
	 * PreCondition  : Login as Vista Admin >> Perform Group Search. Navigate to "Benefits >> Offers" page
	 * 
	 * PostCondition : Dependency Rule applied
	 * 
	 * Java File Name: OffersPage.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strOfferName | strPlanName | strParentPlanName | strDependancyRule
	 * |  offer name | plan name to which dependency rule to apply | parent plan name required for dependency rule | rule value
	 * | ex: HFSA Offer 2016 | Health Savings Account (HSA) |  Health Care Flexible Spending Account (FSA) | Available if user refuses parent element/Always Available/Available if user accepts parent element
	 * Available if user accepts parent element(s)/Available if user accepts any one of the previous elements/Element guarantees that the employee is covered on one of the accepted elements
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strOfferName","strPlanName","strParentPlanName","strDependancyRule"})
	public void setDependencyRuleForCurrentOfferPlanInVistaRole(String strOfferName,String strPlanName,String strParentPlanName, String strDependancyRule) {
		try {		

			this.clickOnOfferType("Current");
			performAction.isElementPresent(selectPlanFromCurrentOffersFilter);
			//updated to retrieve the name of the Offer from the Hashmap......
			if(strOfferName.startsWith("HMV")){
				String getOfferName = utils.getValue(strOfferName);   				
				this.selectOfferfromCurrentOfferFilter(getOfferName);
			}
			else
				this.selectOfferfromCurrentOfferFilter(strOfferName);

			this.clickDependencyRuleForPlan(strPlanName);			

			this.setDependencyRule(strDependancyRule);

			if (performAction.isDisplayed(ParentOffer , "Parent offer dropdown list" )) {		
				this.selectRadiobutton(ParentOffer, strOfferName, "Parent offer dropdown list");
			}				

			if (performAction.isDisplayed(ParentElementDropdown,"Parent element DropDownList" )) {	
				this.selectRadiobutton(ParentElementDropdown, strParentPlanName, "Parent element DropDownList");			

			}

			if (performAction.isDisplayed(ParentElementsSelectionBox,"Parent elements selection box" )) {
				this.selectRadiobutton(ParentElementsSelectionBox, strParentPlanName, "Parent elements selection box");				
			}

			if (performAction.isDisplayed(HideFromUserDropdown,"Hide from user or not DropDownList" )) {				
				this.selectRadiobutton(HideFromUserDropdown, "Display this benefit element", "Hide from user or not DropDownList");				
			}

			this.clickSaveButton();				
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception while setting dependency rule for plans In Vista Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception while setting dependency rule for plans In Vista Role"
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author : Varun Reddy Proddutoori
	 * 
	 * Description  : Create PreQualifying Survey for Member in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Edit PreQualifying Page Opened in Vista Role
	 * 
	 * PostCondition : Survey Created and Offers Page Opened in Vista Role
	 * 
	 * Java File Name : PreQualifyingSurvey.java
	 * 
	 * | Offer Type | Offer Name | Plan Name | strprequalifyinglogic | strprequalifyingsurvey | 
	 * | ex: Current | ex: Test Offer | ex: Medical/HSA | ex: Static Survey | ex: University of Kansas |
	 * 
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "strOfferType", "strOfferName", "strPlanName", "strprequalifyinglogic", "strprequalifyingsurvey" })
	public void createPreQualifyingSurveyForPlanInVistaRole(String strOfferType, String strOfferName, String strPlanName, String strprequalifyinglogic, String strprequalifyingsurvey) {
		try {
			if (strprequalifyinglogic.startsWith("td:"))
				strprequalifyinglogic = strprequalifyinglogic.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(strprequalifyinglogic.toLowerCase());
			object = fields.get("prequalifyinglogic");
			String strPreQualifyLogic = utils.getValue(object.toString());

            if (strprequalifyingsurvey.startsWith("td:"))
                strprequalifyingsurvey = strprequalifyingsurvey.substring(3);
            Object object1 = null;
            JSONObject fields1 = ReadJsonTestData.getTestData(strprequalifyingsurvey.toLowerCase());
            object1 = fields1.get("prequalifyingsurvey");
            String strPreQualifySurvey = utils.getValue(object1.toString());

			this.clickOnOfferType(strOfferType);
			this.selectOfferfromCurrentOfferFilter(strOfferName);
			this.mouseOverOnCurrentOffers();
			this.editPreQualifyingSurveysFromCurrentOfferOptions();
			this.editPlanSurvey(strPlanName);
			this.qualifyingLogic(strPreQualifyLogic);
			Thread.sleep(1000);
			this.qualifyingSurvey(strPreQualifySurvey);
			this.saveConfiguration();
			
			} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in creating PreQualifying survey for plan in Vista Role "
					+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : Update PCP Setup General Tab Selectbox values  in Vista Role
	 *
	 * Role : Vista Role
	 *
	 * PreCondition : Plans Page in Vista Role
	 *
	 * PostCondition : Edit Plan Details Page
	 *
	 * Java File: OfferPage.java
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updatePCPSetUp(String labelName,String strValue){
		try	{

			System.out.println("Current Plans Text is : "+performAction.isElementPresent(currentplantext, "Mouse Over on Plan optionsDropDown"));
			if (performAction.isElementPresent(currentplantext, "Mouse Over on Plan optionsDropDown")) {
				//Mouse over on Plan dropdown
				performAction.mouseOver(planOptions, "Mouse Over on Plan optionsDropDown");
				performAction.click(editPlanLink, "Edit Plan Link");
			}
			logger.info("Object created");
			common.updateInfo(labelName,strValue);
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in Edit Plan page "
					+ e.getMessage());
		}
	}

}
