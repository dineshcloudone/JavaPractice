package com.benefitfocus.robot.vista.groups.benefits;

import java.util.List;

import com.benefitfocus.robot.utils.ReadJsonTestData;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
import com.benefitfocus.robot.vista.groups.basics.BasicCommon;

@RobotKeywords
public class Plans {

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected BasicCommon common;

	//Global Variables
	String UserPlanName="";
	String ActualBenefitType="";
	String ProductTypeName="";
	String EDICarrierType="";
	String UnderwritingCarrierType="";
	String PlanName="";
	String strPlanName="";
	String getPlanName="";

	String coppPlanStartDate = "";
	String coppPlanEndDate = "";
	String copPplanEndDate = "";
	String copyPlanName = "";

	By eeHealthStatement = By.id("healthStatementKey");
	By eeSelectedPlanKey = By.xpath("//select[@name='selectedPlanKey']");
	By eeShow = By.linkText("Show");
	//object locators
	By BenefitsTab = By.id("innerLinkGroupNavMenubenefit");
	By PlanLink = By.xpath("//a[text()='Plans']");
	By createNewPlanButton = By.xpath("//a[contains(text(),'Create New Pla')]");
	By createNewPlanText = By.xpath("//h1[contains(text(),'Create New Pla')]");
	By benefitElementTypeForTheNewPlanDropDownBox = By.xpath("//select[@id='benefitType']");
	By NextButton = By.xpath("//strong[text()='Next']");
	By SaveButton =By.xpath("//strong[text()='Save']");
	By ProductType = By.xpath("//select[@id='productPlanType']");
	By EDICarrier  = By.xpath("//select[@id='EDICarrierKey']");
	By UnderwritingCarrier  = By.xpath("//select[@id='underwritingCarrierKey']");
	//Plans Details locators
	By PlanNameTextBox    = By.id("planName-ENGLISH");
	By ContractEffectiveDateTextBox  = By.xpath("//input[@name='contractEffectiveDate']");
	By ContractExpirationDateTextBox  = By.xpath("//input[@name='contractExpirationDate']");
	By CostDisplayPeriodSelectBox = By.xpath("//select[@id='costDisplayPeriod']");
	By AllowInsuranceCardRequestSelectBox = By.xpath("//select[@id='cardRequestOptionType']");
	By ConfigurationEffectiveDateTextBox  =By.xpath("//input[@name='effectiveDate']");
	By ConfigurationExpirationDateTextBox =By.xpath("//input[@name='expirationDate']");
	//Plans List Page locators
	By FilterByPlansDropDownListBox = By.xpath("//select[@name='selectedPlanKey']");
	By CurrentPlanoptions = By.xpath("//a[contains(@id,'innerLinkplanOptions')]");
	By DeletePlanLink = By.xpath("//a[contains(text(),'Delete Plan')]");
	//By ShowButton = By.xpath("//a[contains(@id,'innerLinktoggleRegionplanItem')]");
	By ShowButton = By.partialLinkText("Show");
	By DeleteConfirmationMessage=By.xpath("//a[text()='Yes']");

	//Plan Enablements locators
	By PlanEnablementEdit =By.xpath("//div[@id='innerInnerInnerInnerPageCore']/div[3]/table/tbody/tr/td/div/a/img");
	By DeleteAllButton =By.xpath("//a[text()='Delete All']");
	By SelectAlllink =By.xpath("//a[text()='Select All']");
	By AddEnablementsButton =By.xpath("//a[text()='Add Enablement(s)']");

	//Hr Role locators
	By HrRoleImg =By.xpath("//img[@alt='Open Group in HR Role (Alt+O)']");
	String HrRoleWindowTitle = "Welcome, BF QA Automation Vista Admin!";
	By EmployeesLink = By.xpath("//span[text()='Employees']");
	By AddanewEmployeeButton =By.xpath("//a[text()='Add a new employee']");
	By allEmployessText=By.xpath("//div[@id='innerInnerInnerInnerPageCore']/div[3]/table/tbody/tr/td[3]/div");
	By createPlanRate = By.linkText("Create New Plan Rate");
	By participationdate = By.id("selectedParticipationPeriodUrl");
	By nextButton = By.linkText("Next");
	By planIncrement = By.id("increment");
	By planConfiguration = By.id("selectedTemplateKey");
	By planRateChange = By.id("planRateLockedOption");
	By planRateEffectiveDateChange = By.id("planRateEffectiveDateLockedOption");
	By savebutton = By.linkText("Save");
	By deletePlanRate = By.linkText("Delete");
	By confirmationMessage = By.xpath("//a[text()='Yes']");
	By copyPlanRate = By.linkText("Copy to Another Plan");
	By copyPlanRateButton = By.linkText("Copy Plan Rates");
	By planList = By.xpath("//h3");
	String showPlan = "/parent::td/following-sibling::td//a";

	By participationPeriodStartDate = By.id("newParticipationPeriodStartDate");
	By participationPeriodEndDate = By.id("newParticipationPeriodEndDate");
	By CopyPlanButton = By.xpath("//strong[text()='Copy Plan']");
	By DeatilsTextBox = By.xpath("//input[@name='newSponsorProductName']");
	By Currentplansoptions = By
			.xpath("//a[contains(@id,'innerLinkmenucache')]");
	// Plan Options
	By CopyPlanLink = By.xpath("//a[contains(text(),'Copy Plan')]");
	By allLink = By.xpath("//a[text()='Previous']/parent::*/preceding-sibling::*//a[text()='All']");
	By planOption = By.xpath("//a[contains(text(),'Plan Options')]");


	private void clickOnCopyPlanRateButton() {
		performAction.click(copyPlanRateButton, "Click on Copy Plan rate Button");
	}

	private void clickOnNextButton() {
		performAction.click(nextButton, "Next Button");
	}

	//Select plan to create Plan rate
	private void selectPlanToCreatePlanRate(String strPlanRate) {
		By planrate=By.xpath("//a[contains(text(),'"+strPlanRate+"')]/parent::*/preceding-sibling::td//a[@class='buttonInnerLink']");
		performAction.click(planrate, "Create Plan Rate");
	}

	//Select plan to delete Plan rate
	private void planToDeletePlanRate(String strPlanRate) {
		By planratedelete=By.xpath("//a[contains(text(),'"+strPlanRate+"')]//following::td[1]//a[@class='buttonInnerLink']");
		performAction.click(planratedelete, "Delete Plan Rate");
	}

	//Click on create New Plan rate for selected Plan
	private void clickCreateNewPlanRate() {
		performAction.click(createPlanRate,"Click on Create plan rate");
	}

	//Click on delete Plan rate for selected Plan
	private void clickDeletePlanRate() {
		performAction.click(deletePlanRate,"Click on Delete plan rate");
	}

	//click Plan Name in a page
	private void clickPlan(String strPlanName) throws Exception{
		performAction.select(eeSelectedPlanKey, strPlanName,
				"Plan Name In Drop Down");
		if (performAction.isElementPresent(eeShow))
			performAction.click(eeShow,"Show");
	}

	private void verifyFilterByPlan(String strPlanName) {
		By plan = By.xpath("//h3[text()='" + strPlanName + "']");
		Assert.assertTrue(performAction.isDisplayed(plan, "Plan displayed")); // verify
		// the
		// selected
		// plan
		// is
		// displayed
		List<WebElement> plansList = browser.getCurrentWebDriver()
				.findElements(planList);
		Assert.assertEquals(1, plansList.size()); // verify only the selected
		// plan (count 1) is
		// displayed
	}

	private void confirmDeletePlanRate(){
		performAction.click(confirmationMessage,
				"Delete Confirmation Message");
	}

	//Select participation date while creating plan rate
	private void selectParicipationtDate(String strPlanDate){
		performAction.select(participationdate, strPlanDate, "Effective Plan date");
	}

	//Select increment time while creating plan rate
	private void selectIncrement(String strIncrement){
		performAction.select(planIncrement, strIncrement, "Plan rate Increment time");
	}

	//Select configuration while creating plan rate
	private void selectConfiguration(String strConfiguration){
		performAction.select(planConfiguration, strConfiguration, "Plan rate Configuration");
	}

	//Enter plan rate values
	private void enterPlanRate(String strempgroup, String strplanRateAmount1, String strplanRateAmount2 ){
		By planrateamount1=By.xpath("//td[text()='"+strempgroup+"']/following-sibling::td[1]/input");
		By planrateamount2=By.xpath("//td[text()='"+strempgroup+"']/following-sibling::td[2]/input");

		performAction.clearEnter(planrateamount1, strplanRateAmount1, "Enter Plan rate Employee amount for Employee Only");
		performAction.clearEnter(planrateamount2, strplanRateAmount2, "Enter Plan rate Employer amount for Employee Only");
	}

	//Select plan rate change
	private void selectPlanRateChange(String strRateChange){
		performAction.select(planRateChange, strRateChange, "Plan rate Configuration");
	}

	//Select plan rate date change
	private void selectPlanRateDateChange(String strRateDateChange){
		performAction.select(planRateEffectiveDateChange, strRateDateChange, "Plan rate Effective date change");
	}

	// click on save button
	private void clickSavebutton() throws Exception {
		performAction.click(savebutton, "save button");
	}

	//Verify Plan rate created
	private void VerifyCreatePlanRate(String strPlanRate) {
		By planratecreated=By.xpath("//a[contains(text(),'"+strPlanRate+"')]//following::td[1]//a[@class='buttonInnerLink']");
		Assert.assertTrue("Create Plan Rate Success", (performAction.isElementPresent(planratecreated)));
	}

	//Verify Plan rate deleted
	private void  VerifyDeletePlanRate(String strPlanRate){
		By planratecreated=By.xpath("//a[contains(text(),'"+strPlanRate+"')]//following::td[1]//a[@class='buttonInnerLink']");
		Assert.assertFalse("Delete Plan Rate Success", (performAction.isElementPresent(planratecreated)));
	}

	// plan from where to copy plan rate
	private void planFromToCopyPlanRate(String strPlanRate){
		By copyplanrate=By.xpath("//a[contains(text(),'"+strPlanRate+"')]//following::td[1]//a[@class='buttonInnerLink']");
		performAction.click(copyplanrate, "Copy Plan Rate");
	}

	//Click on copy Plan rate to another for selected Plan
	private void clickCopyPlanRate( ){
		performAction.click(copyPlanRate,"Click on copy plan rate");
	}

	//Select check box for the plan to copy plan rate
	private void selectPlanToCopyPlanRate(String selectPlanToCopyPlanRate){
		By copyplanrate=By.xpath("//*[contains(text(),'"+selectPlanToCopyPlanRate+"')]/parent::td/preceding-sibling::td//input[@type='checkbox']");
		performAction.click(copyplanrate,"Click check box for the plan rate to copy");
	}

	//Confirm copy plan rate
	private void confirmCopyPlanRate(){
		performAction.click(confirmationMessage,
				"copy Confirmation Message");
	}

	//Create new plan rate for the given plan
	private void createNewPlanRate(String planRatervalues) throws Exception {
		if (planRatervalues.startsWith("td:"))
			planRatervalues = planRatervalues.substring(3);

		Object object = null;


		JSONObject fields = ReadJsonTestData.getTestData(planRatervalues
				.toLowerCase());
		logger.info("fields = " + fields.toJSONString());
		object = fields.get("planRates");

		object = fields.get("date");
		if (object != null) {
			this.selectParicipationtDate(object.toString());
		}

		this.clickOnNextButton();

		object = fields.get("increment");
		if (object != null) {
			this.selectIncrement(object.toString());
		}

		this.clickOnNextButton();

		object = fields.get("configuration");
		if (object != null) {
			this.selectConfiguration(object.toString());
		}

		this.clickOnNextButton();

		//Get values from Json file for Employee only
		object = fields.get("employeeonly");
		String employeeonly = object.toString();

		object = fields.get("employeeamount1");
		String employeeamount1 = object.toString();

		object = fields.get("employeeamount2");
		String employeeamount2 = object.toString();

		this.enterPlanRate(employeeonly, employeeamount1, employeeamount2);

		//Get values from Json file for Employee and Spouse
		object = fields.get("employeeandspouse");
		String employeeandspouse = object.toString();

		object = fields.get("employeeandspouseamount1");
		String employeeandspouseamount1 = object.toString();

		object = fields.get("employeeandspouseamount2");
		String employeeandspouseamount2 = object.toString();

		this.enterPlanRate(employeeandspouse, employeeandspouseamount1, employeeandspouseamount2);

		//Get values from Json file for Employee plus Children
		object = fields.get("employeepluschildren");
		String employeepluschildren = object.toString();

		object = fields.get("employeepluschildrenamount1");
		String employeepluschildrenamount1 = object.toString();

		object = fields.get("employeepluschildrenamount2");
		String employeepluschildrenamount2 = object.toString();

		this.enterPlanRate(employeepluschildren, employeepluschildrenamount1, employeepluschildrenamount2);

		//Get values from Json file for Employee and Children
		object = fields.get("employeeandchildren");
		String employeeandchildren = object.toString();

		object = fields.get("employeeandchildrenamount1");
		String employeeandchildrenamount1 = object.toString();

		object = fields.get("employeeandchildrenamount2");
		String employeeandchildrenamount2 = object.toString();

		this.enterPlanRate(employeeandchildren, employeeandchildrenamount1, employeeandchildrenamount2);

		this.clickOnNextButton();

		object = fields.get("planratechange");
		if (object != null) {
			this.selectPlanRateChange(object.toString());
		}

		this.clickOnNextButton();

		object = fields.get("effectivedate");
		if (object != null) {
			this.selectPlanRateDateChange(object.toString());
		}
		this.clickOnNextButton();
		this.clickSavebutton();

	}

	//click links in a Plan page
	private void clickLinksInPlan(String strPlanName) throws Exception{
		By eeClickPlan = By.linkText(strPlanName);
		performAction.click(eeClickPlan,"link in plan");
	}
	// clicks on show or hide button of a plan
	private void clickShowPlan(String planName, String action) {
		By showPlanLink = By
				.xpath("//h3[text()='" + planName + "']" + showPlan);
		performAction.click(showPlanLink, "Show or Hide Plan Button");
		String buttonText = browser.getCurrentWebDriver()
				.findElement(showPlanLink).getText();
		if (action.equalsIgnoreCase("Show"))
			Assert.assertTrue(buttonText.equalsIgnoreCase("Hide"));
		else
			Assert.assertTrue(buttonText.equalsIgnoreCase("Show"));
		logger.info(" ***** Assert passed "
				+ browser.getCurrentWebDriver().findElement(showPlanLink)
				.getText());

	}


	//click on Next Button
	private void NextButton(){
		performAction.click(NextButton,"Click on Next Button");

	}
	//click on save Button
	private void SaveButton(){

		performAction.click(SaveButton,"Click On SaveButton");
	}


	//click on createplanButton step 1 of 3
	private void  createPlanButton(String giveTheBenefitTypePlanName) throws Exception{

		performAction.click(createNewPlanButton, "Create New Plan Button");
		performAction.waitForPageLoad();
		performAction.select(benefitElementTypeForTheNewPlanDropDownBox, giveTheBenefitTypePlanName,
				"Please select the benefit type for the new plan DropDown");
		String  str = giveTheBenefitTypePlanName.trim();
		UserPlanName = str.substring(3);
		System.out.println("User Benefit Element Plan Name is : "+UserPlanName);
		this.NextButton();
		ActualBenefitType = browser.getCurrentWebDriver().findElement(By.xpath("//div[contains(text(),'"+UserPlanName+"')]")).getText();
		logger.info("Benefit Type is : "+ActualBenefitType);
	}


	//select ProductType DropDown Box step 2 of 3
	private void selectproductType(String giveTheProductType){
		performAction.select(ProductType, giveTheProductType, "Product Type DropDown");
		String  str = giveTheProductType.trim();
		ProductTypeName = str.substring(3);
		logger.info("User ProductType Plan  Name is : "+ProductTypeName);
	}

	//select EDICarrierType DropDown Box step 2 of 3
	private void selectEDICarrierType(String giveEDICarrierType){
		performAction.select(EDICarrier, giveEDICarrierType, "EDICarrier Type DropDown");
		String  str = giveEDICarrierType.trim();
		EDICarrierType = str.substring(3);
		logger.info("User EDICarrier Plan  Name is : "+EDICarrierType);
	}

	//select UnderWriterCarrierType DropDown Box step 2 of 3
	private void selectUnderWriterCarrierType(String giveTheUnderwritingCarrierType){

		performAction.select(UnderwritingCarrier, giveTheUnderwritingCarrierType, "UnderwritingCarrierType DropDown");
		String  str = giveTheUnderwritingCarrierType.trim();
		UnderwritingCarrierType = str.substring(3);
		logger.info("User UnderwritingCarrier Plan  Name is : "+UnderwritingCarrierType);
	}


	//verify Producttype plan name Step 3 of 3
	private void verifyProductType(){
		String Expected=ProductTypeName;
		String Actual=browser.getCurrentWebDriver().findElement(By.xpath("//div[contains(text(),'"+Expected+"')]")).getText();
		if (Expected.trim().equals(Actual.trim())) {
			logger.info(Actual+" : Your ProductType plan is successfully  Verifed in the page.");
		} else {
			logger.info(Actual+" : Your ProductType plan is not Available in the page.");
		}
	}

	//verify EDICarrierType plan name Step 3 of 3
	private void verifyEDICarrierType(){
		String Expected=EDICarrierType;
		WebElement EDICarrier = browser.getCurrentWebDriver().findElement(By.xpath("//select[@id='EDICarrierKey']"));
		Select sel = new Select(EDICarrier);
		List<WebElement> lst=sel.getOptions();
		for (int i = 0; i < lst.size(); i++) {

			String Actualtext= lst.get(i).getText();

			if (Expected.trim().equals(Actualtext.trim())) {

				logger.info(Actualtext+" : Your EDICarrierType plan is successfully  Verifed in the page.");
				break;
			}
		}
	}

	//verify UnderWriterCarrierType plan name Step 3 of 3
	private void verifyUnderWriterCarrierType(){

		String Expected=UnderwritingCarrierType;
		WebElement UnderwritingCarrier = browser.getCurrentWebDriver().findElement(By.xpath("//select[@id='underwritingCarrierKey']"));
		Select sel1 = new Select( UnderwritingCarrier);
		List<WebElement> lst1=sel1.getOptions();
		for (int i = 0; i < lst1.size(); i++) {
			String Actualtext= lst1.get(i).getText();
			if (Expected.trim().equals(Actualtext.trim())) {
				System.out.println(Actualtext+" : Your UnderWriterCarrierType plan is successfully  Verifed in the page.");
				break;
			}
		}
	}


	//plan name
	private void strPlanName(String giveThePlanName) throws Exception{

		//planaName
		performAction.clearEnter(PlanNameTextBox, giveThePlanName, "Plan Name TextBox");
		performAction.waitForPageLoad();
		//store The Plan Name
		PlanName =browser.getCurrentWebDriver().findElement(By.id("planName-ENGLISH")).getAttribute("value");
		logger.info("Your Created Plan  Is : "+PlanName);

	}



	//plan Details Step 3 of 3
	private void planDetails(String giveContractEffectiveDate, String  giveContractExpirationDate
			,String giveConfigurationEffectiveDate,String giveConfigurationExpirationDate, String giveTheCostDisplayPeriodType
			,String giveTheAllowInsuranceCardRequestsType){

		//contract EffectiveDate nad ExipryDate
		performAction.clearEnter(ContractEffectiveDateTextBox, giveContractEffectiveDate, "Contract EffectiveDate TextBox");

		//contract ExpiryDate
		performAction.clearEnter(ContractExpirationDateTextBox, giveContractExpirationDate, "Contract ExpirationDate TextBox");

		//configuration EffectiveDate
		performAction.clearEnter(ConfigurationEffectiveDateTextBox, giveConfigurationEffectiveDate, "configuration EffectiveDate TextBox");

		//configuration ExpiryDate
		performAction.clearEnter(ConfigurationExpirationDateTextBox, giveConfigurationExpirationDate, "configuration ExpirationDate TextBox");

		//CostDisplayPeriodSelectBox
		performAction.select(CostDisplayPeriodSelectBox, giveTheCostDisplayPeriodType,"CostDisplayPeriodSelectBox");

		//Allow Insurance Card Requests
		performAction.select(AllowInsuranceCardRequestSelectBox, giveTheAllowInsuranceCardRequestsType,"Allow Insurance Card Requests");

	}

	//Filtered By Plan DropDown and verify the Planname in the Avalibale Plans list
	private void verifyPlanName(String strPlan) throws Exception{

		//get the pla name from the Hashmap
		getPlanName = utils.getValue(strPlan);
		//select the plan name in the drp down box
		performAction.select(FilterByPlansDropDownListBox, getPlanName, "FilterByPlan DropDownList");

		if (getPlanName .trim().equals(PlanName.trim()) ) {

			//clcik on Show Button
			if(performAction.isElementPresent(ShowButton)){
				performAction.click(ShowButton, "click on Show Button");
				performAction.waitForPageLoad();
			}

			//print the verifation message of your plan
			logger.info("your Created Plan   : "+getPlanName+" has been verifed Successfullly " );

		}else {
			logger.info("Your Created Plan  : "+getPlanName+" is Not Available in the below mentioned plans List. " );
		}

	}


	//Filtered By Plan DropDown and delete the Plan name in the Avalibale Plans list
	private void deletePlanName(String strPlan) throws Exception{

		//mouse over on Benefits Tab .
		//performAction.mouseOver(BenefitsTab, "Mouse Over On Benefits Tab");
		//clcik on Plans Link
		//performAction.click(PlanLink, "click on Plan Link");
		//get the plan name from the Hash map
		getPlanName = utils.getValue(strPlan);
		//performAction.waitForPageLoad();
		//select the 'plan name' in the  "Filter By Plan"  DropDown ListBox
		performAction.select(FilterByPlansDropDownListBox, getPlanName, "FilterByPlan DropDownList");
		//performAction.waitForPageLoad();

		if (getPlanName.trim().equals(PlanName.trim())) {
			//clcik on Show Button
			if(performAction.isElementPresent(ShowButton)){
				performAction.click(ShowButton, "click on Show Button");
				performAction.waitForPageLoad();
			}

			//mouseOver on Plans Options
			performAction.mouseOver(CurrentPlanoptions, "PlansOptions");
			performAction.waitForPageLoad();
			//click on Delete link
			performAction.click(DeletePlanLink, "Delete Plan");
			performAction.waitForPageLoad();
			//click on Delete yes button
			performAction.click(DeleteConfirmationMessage, "Delete Confirmation Message.");
			///print the success message after delete your plan
			logger.info("Your Plan  : "+getPlanName+" has been deleted successfullly. ");
		}
	}


	//Vista Plan Enablement
	private void PlanEnablement(String strPlan) throws Exception{

		logger.info("Plan Enablement PlanName is : "+strPlan);

		//get the pla name from the Hashmap
		getPlanName = utils.getValue(strPlan);
		//select the plan name in the drop down list box
		performAction.select(FilterByPlansDropDownListBox, getPlanName, "FilterByPlan DropDownList");

		//Click on EnablementEdit Button
		performAction.click(PlanEnablementEdit , "Click EnablementEdit Button");

		if (performAction.isElementPresent(DeleteAllButton)) {

			//Click on EnablementEdit Button
			performAction.click(DeleteAllButton , "Click Delete Button");

			//click on Select All Link
			performAction.click(SelectAlllink  , "Click SelectAll Link ");

			//click on AddEnablements Button
			performAction.click(AddEnablementsButton  , "Click AddEnablementsButton ");

			//click on Save Button
			this.SaveButton();
			performAction.waitForPageLoad();

			if ("All Employees".equals(browser.getCurrentWebDriver().findElement
					(allEmployessText).getText().trim())) {
				logger.info("Your Plan  : "+PlanName+" Enablements has been Enabled successfullly. ");
			} else {
				logger.info("Your Plan  : "+PlanName+" Enablements are not completed successfullly.");
			}
		} else {

			//click on Select All Link
			performAction.click(SelectAlllink  , "Click SelectAll Link ");
			//click on AddEnablements Button
			performAction.click(AddEnablementsButton  , "Click AddEnablementsButton ");

			//click on Save Button
			this.SaveButton();
			performAction.waitForPageLoad();

			if ("All Employees".equals(browser.getCurrentWebDriver().findElement(allEmployessText).getText().trim())) {

				logger.info("Your Plan  : "+PlanName+" Enablements has been Enabled successfullly. ");

			} else {
				logger.info("Your Plan  : "+PlanName+" Enablements are not completed successfullly.");
			}
		}

	}


	//click on NewPlan Button and select the plan type
	private void NewPlan(String giveTheBenefitTypePlanName,
			String giveTheProductType,String giveEDICarrierType,String giveTheUnderwritingCarrierType,
			String giveContractEffectiveDate,String giveContractExpirationDate,String giveConfigurationEffectiveDate ,
			String giveConfigurationExpirationDate,String giveTheCostDisplayPeriodType,String giveTheAllowInsuranceCardRequestsType ) throws Exception{


		//clickm on NewCreate PlanButton and giving he benefit element name
		this.createPlanButton(giveTheBenefitTypePlanName);
		//verify the page  and continue remaining Steps
		if (UserPlanName.trim().equals(ActualBenefitType.trim())) {
			this.selectproductType(giveTheProductType);
			this.selectEDICarrierType(giveEDICarrierType);
			this.selectUnderWriterCarrierType(giveTheUnderwritingCarrierType);
			this.NextButton();
		}
		//verifications
		this.verifyProductType();
		this.verifyEDICarrierType();
		this.verifyUnderWriterCarrierType();

		//Enter the planDetails
		this.planDetails( giveContractEffectiveDate,
				giveContractExpirationDate, giveConfigurationEffectiveDate, giveConfigurationExpirationDate,giveTheCostDisplayPeriodType
				,giveTheAllowInsuranceCardRequestsType);

	}

	// Filtered By DropDown and click on the name in the s list
	// and click on that options button and click Copy Link.
	private void CopyPlan(String strName, String strFilterByTimeValue)
			throws Exception {
		try {
			// get the name from the Hashmap
			getPlanName = utils.getValue(strName);
			performAction.select(FilterByPlansDropDownListBox, getPlanName,
					"FilterByPlan DropDownList");

			performAction.waitForPageLoad();

			if (performAction.isElementPresent(ShowButton)) {
				performAction.click(ShowButton, "click on Show Button");
				//logger.info("****Clicked on Show Button - Copy Plan*****");
				performAction.waitForPageLoad();
			}

			//performAction.mouseOver(CurrentPlanoptions, "PlanOptions");
			performAction.click(CurrentPlanoptions, "PlanOptions");
			performAction.waitForPageLoad();
			performAction.click(CopyPlanLink, "Copy the ");
			performAction.waitForPageLoad();
			String ExpectedPage = "Copy Plan";
			String ActualPage = browser.getCurrentWebDriver()
					.findElement(By.xpath("//h1")).getText();
			if (ExpectedPage.equals(ActualPage.trim())) {
				logger.info("Now your are in " + ActualPage + " Page.");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// click on save button
	private void ClickonCopyPlanButton() {
		performAction.click(CopyPlanButton, "CopyPlan Button ");
	}

	// copy plan , Participation Period StartDate
	private void setcopyPlanStarDate(String strStartDate) {

		performAction.clearEnter(participationPeriodStartDate, strStartDate,
				"Participation Period StartDate ");
		coppPlanStartDate = browser.getCurrentWebDriver()
				.findElement(By.id("newParticipationPeriodStartDate"))
				.getAttribute("value");

	}

	// copy page , Participation Period EndDate
	private void setcopyPlanEndDate(String strEndDate) throws Exception {

		try {
			performAction.clearEnter(participationPeriodEndDate, strEndDate,
					"Participation Period EndDate ");

			coppPlanEndDate = browser.getCurrentWebDriver()
					.findElement(By.id("newParticipationPeriodEndDate"))
					.getAttribute("value");

			this.NextButton();

			String ActualCopyPlanDate = browser
					.getCurrentWebDriver()
					.findElement(
							By.xpath("//label[@for='newParticipationPeriod']/../../td[2]/div"))
							.getText();

			logger.info("Your New Participation Period Date:"
					+ ActualCopyPlanDate
					+ "has been verifed successfully in the  Coppy Plan page. ");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// copy plan name
	private void strCopyPlanName(String strCopyPlanName) throws Exception {

		// planaName
		performAction.clearEnter(DeatilsTextBox, strCopyPlanName,
				"Entering plan Name in the offer Details TExt Box");

		performAction.waitForPageLoad();
		// store The copied Plan Name
		copyPlanName = browser.getCurrentWebDriver()
				.findElement(DeatilsTextBox).getAttribute("value");
		logger.info("Your copied Plan  Is : " + copyPlanName);

	}



	/* Click on "Current, Previous, Future," links in Plans Page */
	private void selectPlanByTime(String action) {
		By actionLink =  By.linkText(action);
		if(action.equalsIgnoreCase("All")){
			performAction.click(allLink, "All");
		}
		else{
			performAction.click(actionLink, "Click on "+action);
		}
		performAction.waitForPageLoad();
		performAction.verifyMessage(action+" Plans");
	}
	//click on PlanOptions  Button
	private void clickPlanOptionsButton(){
		performAction.click(planOption, "Click on Plan Options");
	}

	//Select on options in Plan Options  Button
	private void selectPlanOptions(String strPlanOption){
		By planOptions = By.xpath("//a[contains(text(),'"+strPlanOption+"')]");
		performAction.click(planOptions, "Click on Plan Options");
	}



	/**
	 * <pre>
	 * Author  : Arun Kasarla
	 * 
	 * Description : 'Verify Health Statement in Edit Plan' keyword used to verify 
	 * whether created Health Statement is available in Choose Health Statement drop 
	 * down while editing a Plan in vista role.
	 *
	 * Role : Vista Role
	 * 
	 * PreCondition : Plans page should be open in Vista Role
	 *
	 * PostCondition : Required Health statement should be displayed in the Choose Health 
	 * Statement drop down.
	 * 
	 * <b>Parameters & Example </b>
	 *
	 * | strPlanName - string | strHealthStatementName - string |
	 * </pre>
	 * Java file Name :  IndividualHealthStatements.java
	 **/
	@RobotKeyword
	@ArgumentNames({"strPlanName", "strHealthStatementName"  })
	public void verifyHealthStatementInEditPlan(String strPlanName,String strHealthStatementName) {
		try {
			if (strHealthStatementName.startsWith("HMV")){
				strHealthStatementName = utils.getValue(strHealthStatementName);
			}
			this.clickPlan(strPlanName);
			scr.capturePageScreenshot();
			this.clickLinksInPlan("Plan Options");
			this.clickLinksInPlan("Edit Plan Details");
			scr.capturePageScreenshot();
			this.verifyHealthStatementName(strHealthStatementName);

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in verifying Health Statement in Edit Plan "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : CreateVistaPlan Keyword or method is used to perform creating VistaPlan in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Create New Plan Page in Vista Admin  Role.
	 *
	 * PostConditions : New vista plan is saved successfully.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification | outPlanName |
	 *
	 * | createnewvistaplan - is used to get the data set from the Json File "eEnrollmentCommon.json". |
	 *
	 * | outPlanName - is a variable name and it will be used to store the PlanName of the created plan. |
	 *
	 * Java FileName : BenefitsCreateNewPlan.java
	 *
	 * </pre>
	 *
	 **/
	@RobotKeyword
	@ArgumentNames({ "strnewPlanDetails","strOutPlanName"})
	public void createVistaPlanInVistaRole(String newPlanDetails,String OutPlanName){

		try {
			if (newPlanDetails.startsWith("td:"))
				newPlanDetails = newPlanDetails.substring(3);

			Object object = null; Object object1 = null;  Object object2 = null; Object object3 = null;
			Object object4 = null;  Object object5 = null;  Object object6 = null; Object object7 = null;
			Object object8 = null;  Object object9 = null;  Object object10 = null;


			JSONObject fields = ReadJsonTestData.getTestData(newPlanDetails.toLowerCase());
			System.out.println("fields = " + fields.toJSONString());

			object = fields.get("NewPlanName");
			object1 =fields.get("ProductPlanType");
			object2 =fields.get("EDICarrierKey");
			object3 =fields.get("UnderwritingCarrierKey");
			object4 =fields.get("strPlanName");
			object5 =fields.get("ContractEffectiveDate");
			object6 =fields.get("ContractExpirationDate");
			object7 =fields.get("ConfigurationEffectivedate");
			object8 =fields.get("ConfigurationExpirationDate");
			object9 =fields.get("CostDisplayPeriod");
			object10 =fields.get("AllowInsuranceCardRequests");

			if (object != null) {

				this.NewPlan(object.toString(), object1.toString(), object2.toString(),object3.toString(),
						utils.getDate(object5.toString()), utils.getDate(object6.toString()), utils.getDate(object7.toString()), utils.getDate(object8.toString()), object9.toString(),
						object10.toString());
			}

			if(object != null) {

				//PlanName Field and return the Plan Name as a OutputPlaname
				strPlanName = utils.generateRandomNumber(object4.toString());
				this.strPlanName(strPlanName);
				browser.hMap.put(OutPlanName, strPlanName);
			}

			this.SaveButton();

		} catch (Exception e) {
			logger.info("Exception occured While Creating The NewPlan Details"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured While Creating The NewPlan Details "
							+ e.getMessage());
		}
	}





	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : verifyVistaPlan Keyword or method is used to perform verify the  VistaPlan in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Current Plans Page in Vista Admin  Role.
	 *
	 * PostConditions : Current vista plan .
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification |
	 *
	 * | strPlanName |
	 *
	 * | Auto1245 |
	 *
	 * Java FileName : BenefitsCreateNewPlan.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strPlanName" })
	public void verifyVistaPlanInVistaRole(String strPlan){

		try {

			//Verify The Plan Name
			this.verifyPlanName(strPlan);


		}catch (Exception e) {
			System.out.println("Exception occured While Verifying The Plan"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured While Verifying The Plan "
							+ e.getMessage());
		}


	}


	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : 'deleteVistaPlanInVistaRole' Keyword or method is used to perform deleting the current VistaPlan in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Current Plans Page in Vista Admin  Role.
	 *
	 * PostConditions : Current vista plan Page .
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification |
	 *
	 * | strPlanName |
	 *
	 * | Auto1245 |
	 *
	 * Java FileName : BenefitsCreateNewPlan.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strPlanName" })
	public void deleteVistaPlanInVistaRole(String strPlan){
		try {
			//Delete The Plan Name
			this.deletePlanName(strPlan);
		}catch (Exception e) {
			logger.info("Exception occured While Deleting The Plan"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured While Deleting The Plan "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description  : vistaPlanEnablementInVistaRole Keyword or method is used to perform VistaPlanEnablement to the current plan in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Current Plan Enablements Page in Vista Admin  Role.
	 *
	 * PostConditions : Current Plan Enablements Page.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | classification |
	 *
	 * | strPlanName |
	 *
	 * | Auto1245 |
	 *
	 * Java FileName : BenefitsCreateNewPlan.java
	 *
	 * </pre>
	 *
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPlanName" })
	public void vistaPlanEnablementInVistaRole(String strPlan){

		try {
			// Plan Enablement
			this.PlanEnablement(strPlan);

		}catch (Exception e) {
			logger.info("Exception occured While performing Vista Plan Enablement "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured While performing Vista Plan Enablement "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Srilatha A
	 *
	 * Description  : 'createPlanRateInVistaRole' Keyword or method is used to CreatePlan Rate in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Plan Rates Page in Vista Admin  Role.
	 *
	 * PostConditions : Created Plan Rate for the specified plan
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | planName, PlanRateValues |
	 *
	 * | PlanName - is a variable name and it will be used to pass the PlanrName to Create Plan Rate, |
	 *
	 * | PlanRateValues - is used to get the data from the Json |
	 *
	 * Java FileName : Plans.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strPlanName" , "planRatervalues" })
	public void createPlanRateInVistaRole(String strPlanRate, String planRatervalues ) {

		try {
			performAction.waitForPageLoad();
			this.selectPlanToCreatePlanRate(strPlanRate);
			performAction.waitForPageLoad();
			this.clickCreateNewPlanRate();
			performAction.waitForPageLoad();
			this.createNewPlanRate(planRatervalues);
			scr.capturePageScreenshot();
			this.VerifyCreatePlanRate(strPlanRate);
		} catch (Exception e) {
			logger.info("Exception occured while Creating the Plan Rate "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Creating the the Plan Rate  "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Srilatha A
	 *
	 * Description  : 'deletePlanRateInVistaRole' Keyword or method is used to delete Plan Rate in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Plan Rates Page in Vista Admin  Role.
	 *
	 * PostConditions : Deleted Plan Rate for the specified plan
	 *
	 * <b>Parameters & Example </b>
	 *
	 *  | planName |
	 *
	 * | PlanName - is a variable name and it will be used to pass the PlanName to Deleting Plan Rate |
	 *
	 * Java FileName : Plans.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strPlanName" })
	public void deletePlanRateInVistaRole(String strPlanRate) {

		try {
			performAction.waitForPageLoad();
			this.planToDeletePlanRate(strPlanRate);

			performAction.waitForPageLoad();
			this.clickDeletePlanRate();

			performAction.waitForPageLoad();
			this.confirmDeletePlanRate();

			scr.capturePageScreenshot();

			performAction.waitForPageLoad();
			this.VerifyDeletePlanRate(strPlanRate);
		} catch (Exception e) {
			logger.info("Exception occured while Deleting the Plan Rate "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Deleting the the Plan Rate  "
							+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : Srilatha A
	 *
	 * Description  : 'copyPlanRateInVistaRole' Keyword or method is used to copy Plan Rate in Vista Admin Role.
	 *
	 * Role : Vista Role
	 *
	 * Precondition : Plan Rates Page in Vista Admin  Role.
	 *
	 * PostConditions : copy Plan Rate for the specified plan
	 *
	 * <b>Parameters & Example </b>
	 *
	 *  | planName , planNameToCopy |
	 *
	 * | PlanNameToCopy - is a variable name to pass the plan name to copy, |
	 *
	 * | planName - is a variable to pass the plan name from where to copy  |
	 *
	 * Java FileName : Plans.java
	 *
	 * </pre>
	 *
	 **/

	@RobotKeyword
	@ArgumentNames({ "strPlanName" , "strPlanNameToCopy" })
	public void copyPlanRateInVistaRole(String strPlanRate, String strPlanRateToCopy) {

		try {
			performAction.waitForPageLoad();
			this.planFromToCopyPlanRate(strPlanRate);

			performAction.waitForPageLoad();
			this.clickCopyPlanRate();

			this.selectPlanToCopyPlanRate(strPlanRateToCopy);

			this.clickOnCopyPlanRateButton();

			performAction.waitForPageLoad();
			this.confirmCopyPlanRate();

			scr.capturePageScreenshot();

			performAction.waitForPageLoad();
			this.VerifyCreatePlanRate(strPlanRate);

		} catch (Exception e) {
			logger.info("Exception occured while Copying the Plan Rate "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Copying the the Plan Rate  "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Srilatha A
	 * 
	 * Description  : Copy Keyword or method is used to perform to Copy the Current Vista in Vista Admin Role.
	 * 
	 * Role : Vista Role
	 * 
	 * Precondition : Plans Current  Page in Vista Admin  Role.
	 * 
	 * PostConditions : Plan in vista  is  copied successfully..
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | classification | strPlanName | strFilterByTimeValue |
	 * 
	 * | copydates - is used to get the data set from the Json File "eEnrollmentCommon.json". | 
	 * 
	 * | strName - is a variable name and it will be used to use from the strname to copy the current plan. |
	 * 
	 * | strFilterByTimeValue - is a variable name and it will be used to pass the Filter type By Time Link |
	 * 
	 * | OutCopyPlanName - is a variable name to store plan name in hash table|
	 * 
	 * Java File Name : Plans.java
	 * 
	 * </pre>
	 * 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strName", "copydates", "strFilterByTimeValue",
	"OutPlanName" })
	public void copyVistaPlan(String strName, String copydates,
			String strFilterByTimeValue, String OutCopyPlanName) {

		try {

			// Copy Plan
			this.CopyPlan(strName, strFilterByTimeValue);

			// Json Data Read
			if (copydates.startsWith("td:"))
				copydates = copydates.substring(3);
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(copydates
					.toLowerCase());
			System.out.println("fields = " + fields.toJSONString());

			object = fields.get("startdate");
			if (object != null) {
				this.setcopyPlanStarDate(utils.getDate(object.toString()));
			}

			object = fields.get("enddate");
			if (object != null) {
				this.setcopyPlanEndDate(utils.getDate(object.toString()));
			}

			object = fields.get("strCopyPlanName");
			if (object != null) {
				String strCopyPlanName = utils.generateRandomNumber(object
						.toString());
				this.strCopyPlanName(strCopyPlanName);
				browser.hMap.put(OutCopyPlanName, strCopyPlanName);
			}

			this.ClickonCopyPlanButton();

			// read the successfull message on the console screen
			String copyPlanPageMessage = browser
					.getCurrentWebDriver()
					.findElement(
							By.xpath("//div[contains(text(),'Your inform')]"))
							.getText();
			logger.info("" + copyPlanPageMessage + "");

			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception occured while Copying the Vista"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Copying the Vista "
							+ e.getMessage());

		}			

	}
	/**
	 * <pre>
	 * Author  : Sunitha Yerra
	 *
	 * Description : This keyword is used to show or hide a plan details in plans page in Vista role
	 *
	 * Role : Vista Role
	 *
	 * PreCondition : Plans page should be open in Vista Role
	 *
	 * PostCondition : Details of a plan should be displayed or hidden
	 *
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strPlanName - string | strAction - string
	 * | Medical 2016    |  Show  |
	 * | Medical 2016    |  Hide  |
	 * </pre>
	 *
	 * Java file Name : Plans.java
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPlanName", "strAction" })
	public void showHidePlan(String strPlanName, String strAction) {
		try {
			this.clickShowPlan(strPlanName, strAction);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception occured in click on Show or Hide button of a plan"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in click on Show or Hide button of a plan "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sunitha Yerra
	 *
	 * Description : This keyword is used to filter the plans by plan name in plans page in Vista role
	 *
	 * Role : Vista Role
	 *
	 * PreCondition : Plans page should be open in Vista Role
	 *
	 * PostCondition : Details of the selected plan should be displayed
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strPlanName - string |
	 * </pre>
	 *
	 * Java file Name : Plans.java
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPlanName" })
	public void sortPlanByFilter(String strPlanName) {
		try {
			this.clickPlan(strPlanName);
			scr.capturePageScreenshot();
			this.verifyFilterByPlan(strPlanName);
		} catch (Exception e) {
			logger.info("Exception occured in  Sort Plan by Filter "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in  Sort Plan by Filter "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sunitha Yerra
	 * 
	 * Description : This keyword is used to filter the plans by time (Current, Future, ..) 
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Plans page should be open in Vista Role
	 * 
	 * PostCondition : Plans will be filtered on the basis of selected time 
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | strAction - string
	 * |  Current  |
	 * |  Previous..  |
	 * </pre>
	 * 
	 * Java file Name : Plans.java
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAction" })
	public void FilterPlanByTime(String strAction) {
		try {
			this.selectPlanByTime(strAction);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception occured in Filter Plan by time"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in  Filter Plan by time "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Rajeswari Nimmala
	 *
	 * Description : Keyword is used to select option in Plan Options button
	 *
	 * Role : Vista Role
	 *
	 * PreCondition : Plans page should be open in Vista Role
	 *
	 * PostCondition : Required option page is opened
	 *
	 * <b>Parameters & Example </b>
	 *
	 * |strPlanOption - option to be selected  |
	 *
	 * </pre>
	 *
	 * Java file Name : Plans.java
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPlanOption" })
	public void editPlanOptions(String strPlanOption) {
		try {
			this.clickPlanOptionsButton();
			this.selectPlanOptions(strPlanOption);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception occured in click on plan options"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in click on plan options "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Rajeswari Nimmala
	 *
	 * Description : Keyword is used to Edit Plan details
	 *
	 * Role : Vista Role
	 *
	 * PreCondition : Edit Plan Details page should be open in Vista Role
	 *
	 * PostCondition : Update configurations will be displayed
	 *
	 * <b>Parameters & Example </b>
	 *
	 * |labelName - Configuration label name ||strValue - value to be set to the configuration |
	 *
	 * </pre>
	 *
	 * Java file Name : Plans.java
	 **/
	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateEditPlanDetails(String labelName,String strValue){
	try	{
		common.updateInfo(labelName,strValue);
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in Edit Plan Detials"
					+ e.getMessage());
		}
	}


	// Verify Health statement name
	private void verifyHealthStatementName(String strHealthStatementName) {
		try {
			Select sel = new Select(browser.getCurrentWebDriver().findElement(
					eeHealthStatement));
			List<WebElement> lst = sel.getOptions();
			for (int i = 0; i < lst.size(); i++) {
				if (lst.get(i).getText().equals(strHealthStatementName)) {
					logger.info("Health statement Name found in the drop down");
					scr.capturePageScreenshot();
					return;
				}
			}
			throw new RuntimeException(
					"Health statement Name not found in the drop down");

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in verifying Health Statement Name "
							+ e.getMessage());

		}

	}
}
