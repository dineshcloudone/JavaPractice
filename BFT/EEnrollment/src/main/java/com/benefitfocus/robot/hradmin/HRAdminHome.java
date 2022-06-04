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
public class HRAdminHome {	
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected ManageBrowser browser;  
	@Autowired
	protected Logging logger;

	// Locators for Add COBRA Exception
	By cobraManager=By.linkText("COBRA Manager");
	By cobraExceptions=By.linkText("COBRA Exceptions");
	By addCobraExceptionsButton=By.linkText("Add COBRA Exception");
	By ssn = By.id("SSN");
	By firstName = By.id("firstName");
	By lastName = By.id("lastName");
	By birthDate = By.id("birthDate");
	By gender = By.id("gender");
	By address1 = By.id("address1");
	By city = By.id("city");
	By state = By.id("state");
	By zip = By.id("zip");
	By country = By.id("country");
	By plan=By.id("categorySelections");
	By saveButton=By.linkText("Save");
	
	By todolist =By.xpath("//h2[contains(text(),'To-do list')]");

	By searchCriteria = By.id("searchCriteria");
	By goSearchButton = By.xpath("//button[@type='submit']");
	By employeeOverviewpopup = By
			.xpath("//button[contains(text(),'Not right now')]");
    // Locators for To-do List panel in HR Admin Home Page.
	By viewByDropdown=By.id("categoryTypeId");
	By categoryDropDown=By.id("categoryId");
	By refuseAll=By.linkText("Refuse All");
    By noEmployees=By.linkText("No Employees");
    By nextButton=By.linkText("Next");
    By saveAndReturnHome=By.xpath("//strong[text()='Save']");
    By findMedicareEligible=By.linkText("Find Medicare Eligible");
    By personalemployeesneedstobeapproved = By.xpath("//div[@id='task-list-region']/div/div[1]/div/table/tbody/tr/td[2]/p/a");
	By viewbyoffer = By.xpath("//span[contains(text(),'View b')]");
	By cobraEligiblePersons=By.linkText("COBRA Eligible Persons");
	By nextButtonIcon=By.xpath("//img[@alt='Next Page']");
	// Locators for Automatic Enrollment Link in HR Admin Home Page
	By autoEnrollmentSummary=By.xpath("//span[text()='Auto Enrollment Summary']");
	By startDate=By.id("startDate");
	By endDate=By.id("endDate");
	By benefitOffers=By.id("selectedOffers");
	By plans=By.id("selectedPlans");
	By applyFilter=By.linkText("Apply Filter");
	
	By employeesWithBenefitsNotStarted=By.linkText("View all employees with benefits not started");
	By nextButtonIconInBenefitsNotStarted=By.xpath("//img[@alt='Next']");
	By activeInnerEmployeesCount=By.xpath("//a[@id='innerLinkactivesTab']//b");
	By terminatedInnerEmployeesCount=By.xpath("//a[@id='innerLinkterminatedTab']//b");
	By dependentsInnerEmployeesCount=By.xpath("//a[@id='innerLinkdependentsTab']//b");
	By appBuilderLink=By.xpath("//div[2]/a/i");	
	By verifyLinkText;
	
	
	// Locators in Health Statement Approval Manager
	By healthStatementApprovalManager=By.linkText("Health Statement Approval Manager");
	By healthStatementApprovalFilterDropDown=By.xpath("//select[@name='searchCriteria']");
	By goButton=By.linkText("Go");
	By nextButtonInHealthStatement=By.xpath("//img[contains(@src,'Button_NextPage')]");
	By declineReason=By.xpath("//select[@name='declineReason']");
	By cancelRequest=By.linkText("Cancel this request");
	
//////Open Enrollment Date Locators /////////

	By oeDatesandRulestab = By.id("innerLinkGroupNavMenudatesRules");
	
	// Locators in AppLaunherBox
	
	By appLauncherBox = By.xpath("//div[2]/a/i");
	By communicationPlatform = By.xpath("//div[2]/ul/li//span[text()='Communication platform']");
	By billing = By.xpath("//li[1]/a/div/div[2]/span[text()='Billing']");
	By oEDatesandTimeZoneforMultipleOffers =By.xpath("//a[text()='OE Dates and Time Zone for Multiple Offers']");
	
	// Locators in Document Center
	
	By documentCenter=By.linkText("Document Center");
	By addDocument=By.xpath("//a/span[text()='Add Document']");
	By chooseFile=By.xpath("//input[contains(@id,'filEditDocumentFileendUserDocCtrl')]");
	By save=By.xpath("//span[text()='Save']");
	By errorMesg=By.xpath("//ul[@id='cp-notify-messages']");
	
	// Locators in Download Report Page
	
	By downloadReport=By.xpath("//a[contains(text(),'Download Report')]");
	// Variables in Public methods
	String strSelectTaskList="";
	String strCategory="";
	String[] Values=new String[2];
	String employeeFound;
	String employeesCountFromHomePage;
	String employeeSearchResults="//table[@id='dataTable-Active']/tbody/tr";
	String row;
	int employeesCount;
	int totalEmployeesCount=0;
	String toDoListSections="//div[@id='task-list-region']/div/div";
	int toDoListSectionsRowCount=0;
	String toDoListHeader;
	String employeesList="//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-default']/tbody/tr";
	String selectEmployee="";
	By clickNextButtonFromEmployeeApprovalList=By.xpath("//img[@alt='Next Page']");
	By employeeSearchResultsHeader=By.xpath("//h1[text()='Employee Search Results']");
	String startDatev ="";
	String endDatev  ="";
	String date = "";
	String finalstartdate ="";
	String finalEnddate  ="";
	String headerText="";
	By hrUserName=By.xpath("//header//a/span");
	By myProfile=By.linkText("My Profile");
	By currentPassword=By.id("currentPassword");
	By newPassword=By.id("newPassword");
	By confirmPassword=By.id("newConfirmPassword");
	By workEmail=By.id("email");
	String confirmationRows="//td/div[contains(@class,'regionContent')]/div";
	By approveConfirmationRow=By.xpath("//h1/following::div[contains(@class,'secondaryRegionContent')]");
	By cancelButton=By.linkText("Cancel");

    private void clickAppLauncherBox(){
    	performAction.click(appLauncherBox, "AppLauncherBox");
    }
    
    private void clickCommunicationPlatform(){
    	performAction.click(communicationPlatform, "CommunicationPlatform");
    }
    
    private void clickBilling(){
    	performAction.click(billing, "Billing");
    }
	/************ Edit HR OE Dated *************/

	private void mouseoverDatesAndRulesButton(){
		performAction.mouseOver(oeDatesandRulestab, "DatesandRulesTab");
		performAction.click(By.linkText("Enrollment Dates and Time Zone"), "click on Enrollment Dates and Time Zone Link");
	}

	private void clickondatelinks(String strGiveThePlanLink){
		performAction.click(By.linkText(strGiveThePlanLink), "Plan Links");
		scr.capturePageScreenshot();
		performAction.click(oEDatesandTimeZoneforMultipleOffers, "Enrollment Dates and Time Zone Button");
	}

	private void enterHrBrokerOEDates(){
		performAction.click(By.linkText("Select All"), "Select All Link ");
		performAction.clearEnter(By.id("hrAdminOpenEnrollmentStartDate"), "d:currentdate:d:-8", "HR/Broker TextBox");
		//get Start date value
		startDatev=utils.getDate("d:currentdate:d:-8");
		performAction.clearEnter(By.id("hrAdminOpenEnrollmentEndDate"), "d:currentdate:M:+5", "HR/Broker TextBox");
		//get end date value
		endDatev=utils.getDate("d:currentdate:M:+5");
		scr.capturePageScreenshot();
		performAction.click(By.xpath("//Strong[text()='Save']"), "Click on Save Button");

		date = browser.getCurrentWebDriver().findElement(By.xpath("//div[contains(@class,'regionContent secondaryRegionContent')]/table/tbody/tr/td[2]/div/table/tbody/tr[2]/td[3]")).getText().trim();
		finalstartdate =date.split("-")[0];
		finalEnddate  =date.split("-")[1];

		if (finalstartdate.trim().equalsIgnoreCase(startDatev.trim())&& finalEnddate.trim().equalsIgnoreCase(endDatev.trim()) ) {
			System.out.println("you have enter the Start Date &&  End Date has been verified successfully.");
			scr.capturePageScreenshot();
		}
	}
	/********* Private methods for Add COBRA Exception **********/
	private void clickCOBRAManager(){
		performAction.click(cobraManager, "Click on COBRA Manager");
	}
	private void clickCOBRAExceptionsButton(){
		performAction.click(cobraExceptions, "Click on COBRA Exceptions Link");
	}
	private void clickAddCOBRAExceptionsButton(){
		performAction.click(addCobraExceptionsButton, "Clik on Add COBRA Exceptions Button");
	}

	// set the ssn text box
	private void setSSNField(String strssn) {
		performAction.enter(ssn, strssn, "SSN Text Box");

	}

	// set the firstName text box
	private void setFirstNameField(String strfirstName) {
		performAction.enter(firstName, strfirstName, "First Name Text Box");
	}

	// set the lastName text box
	private void setLastNameField(String strlastName) {
		performAction.enter(lastName, strlastName, "Last Name Text Box");
	}

	// set the birthDate text box
	private void setBirthDateField(String strbirthDate) {
		performAction.enter(birthDate, strbirthDate, "Date of Birth Text Box");
	}

	// set the gender combo box
	private void selectgenderField(String strValuetoSelect) {
		performAction.select(gender, strValuetoSelect, "Gender Combo Box");
	}

	// set the address1 textbox
	private void setAddress1Field(String straddress1) {
		performAction.enter(address1, straddress1, "Address Text Box");
	}

	// set the city text box
	private void setCityField(String strcity) {
		performAction.enter(city, strcity, "City Text Box");
	}

	// set the state combo box
	private void selectStateField(String strValuetoSelect) {
		performAction.select(state, strValuetoSelect, "State Combo Box");
	}

	// set the zip text box
	private void setZipField(String strzip) {
		performAction.enter(zip, strzip, "Zip Code Text Box");
	}

	// set the Country combo box
	private void selectcountryField(String strValuetoSelect) {
		performAction.select(country, strValuetoSelect, "Country Combo Box");
	}
	// select plan in Add COBRA Exception page
	private void selectPlan(String strPlan){
		performAction.select(plan, strPlan, "Select Plan");
	}
	// Click on Save Button
	private void clickSaveButton(){
		performAction.click(saveButton, "Click on Save Button");
	}
	private void setSearchCriteria(String strSearchCriteria) {
		performAction.enter(searchCriteria, strSearchCriteria,
				"Search Criteria");
	}
	// click on save button
	private void clickGobutton() {
		performAction.click(goSearchButton, "Search Button");
	}
	// Select option from View By drop down
	private void selectViewByDropdown(String strViewByDropdown) {
		performAction.select(viewByDropdown, strViewByDropdown, "Select View By Drop down");
	}
    // Select Category or Benefit Name from Drop down
	private void selectCategoryDropDown(String strCategoryDropDown) {
		performAction.select(categoryDropDown, strCategoryDropDown, "Select option from Category drop down");
	}
    // Click on Refuse All Button from Home tab in Hr Admin
	private void clickRefuseAllButton() {
		performAction.click(refuseAll, "Click on Refuse All Button");
	}
	// Click on No Employees Link
	private void clickNoEmployeesLink() {
		performAction.waitUntilElementPresent(noEmployees);
		performAction.click(noEmployees, "Click on No Employees Link");
	}
	// Click on Next Button
	private void clickNextButton() {
		performAction.click(nextButton, "Click on Next Button");
	}
	// Click on Save And Return Home Button
	private void clickSaveAndReturnHome() {
		performAction.click(saveAndReturnHome, "Click on Save And Return Home Button");
	}
	// Click on Approve All Button from Home tab in Hr Admin
	private void clickApproveAllButton(String strInfo) {
		browser.getCurrentWebDriver().findElement(By.xpath("//h2[text()='"+strInfo+"']/../../../..//a[text()='Approve All']")).click();
	}
	//click on Personal Employees Need to be Approved
	private void clickpersonalemployeesneedtobeapproved(){
		performAction.click(personalemployeesneedstobeapproved, "Click on Save And Return Home Buttonpersonalemployeesneedstobeapproved Link");
	}
    //click on view by offer link
	private void clickviewbyoffers(){
		performAction.click(viewbyoffer, "click on ViewByOffer Link");
	}
	//click on view by offer link
	private void clickCOBRAEligiblePersonsButton(){
		performAction.click(cobraEligiblePersons, "click on Cobra Eligible Persons Link");
	}
	// Click on Auto Enrollment Summary Link
	private void clickAutoEnrollmentSummaryLink(){
		performAction.click(autoEnrollmentSummary, "Click on Auto Enrollment Summary Link");
	}
	// Enter Start Date
	private void enterStartDate(String strStartDate) {
		if (strStartDate.startsWith("d:")) {
			strStartDate = strStartDate.substring(2);
			performAction.clearEnter(startDate,
					utils.getDate(strStartDate), "Enter Start Date");
		} else {
			performAction.clearEnter(startDate, strStartDate,
					"Enter Start Date");
		}
	}
	// Enter End Date
	private void enterEndDate(String strEndDate) {
		if (strEndDate.startsWith("d:")) {
			strEndDate = strEndDate.substring(2);
			performAction.clearEnter(endDate,
					utils.getDate(strEndDate), "Enter End Date");
		} else {
			performAction.clearEnter(endDate, strEndDate,
					"Enter End Date");
		}
	}
	// Select Benefit Offers
	private void selectBenefitOffersInAutoEnrollment(String strBenefitOffers){
		performAction.select(benefitOffers, strBenefitOffers, "Select Benefit Offers");
	}
	// Select Plan from Auto Enrollment Summary Page
	private void selectPlanInAutoEnrollment(String strPlans){
		performAction.select(plans, strPlans, "Select Plans");
	}
	// Click on Apply Filter Button
	private void clickApplyFilterButton(){
		performAction.click(applyFilter, "Click on Apply Filter Button");
	}
	// Click on App Builder Button
	private void clickAppBuilderButton(){
		performAction.click(appBuilderLink, "Click on App Builder Link");
	}
	// Click on Link from App Builder Drop down
	private void clickAppBuilderOptionLink(String strLinkText){
		browser.getCurrentWebDriver().findElement(By.linkText(strLinkText)).click();
	}
	// Click on Health Statement Approval Manager Link
	private void clickHealthStatementApprovalManagerLink(){
		performAction.click(healthStatementApprovalManager, "Click on Health Statement Approval Manager Link");
	}
	// Select Health Statement Approval Manager Filter 
	private void selectHealthStatementApprovalManagerFilter(String strFilter){
		performAction.select(healthStatementApprovalFilterDropDown, strFilter, "Select Health Statement Approval Manager Filter");
	}
	// Click on Go Button
	private void clickGoButtonInHealthStatement(){
		performAction.click(goButton, "Click on Go Button");
	}
	// Click on Go Button
	private void clickNextButtonInHealthStatement(){
		performAction.click(nextButtonInHealthStatement, "Click on Next Button");
	}
	// Click on Go Button
	private void selectDeclineReasonForHealthStatement(String strReason){
		performAction.select(declineReason, strReason,"Click on Next Button");
	}
	// Click on Cancel Request Button
	private void clickCancelRequestButton(){
		performAction.click(cancelRequest, "Click on Cancel Request Button");
	}
	// Click on Link from Home Page
	private void clickLinkInHomePage(String strLinkText){
		verifyLinkText=By.linkText(strLinkText);
		performAction.isElementPresent(verifyLinkText);
		performAction.click(verifyLinkText, "Click on "+strLinkText+" Link");
	}
	// Click on Approve Link
	private void clickApproveLink(String strLastName){
		String employeeFound="";	
		// Do While Loop For clicking on Approve / Decline Link against employee
		  do{
			  if(performAction.isElementPresent(By.xpath("//div[contains(text(),'"+strLastName+"')]/following::a[text()='Approve / Decline'][1]"))){
				  performAction.click(By.xpath("//div[contains(text(),'"+strLastName+"')]/following::a[text()='Approve / Decline'][1]"), "Click on Approve / Decline Link from Pending Approval Employees List");
				  logger.info("Employee Found : "+strLastName);
				  employeeFound="found";
				  break;
			  }	
			  if(performAction.isElementPresent(nextButtonInHealthStatement)){
				  this.clickNextButtonInHealthStatement();
			  }else{
				  logger.info("Employee With Last Name ==> "+strLastName+"Not Found");
			  }
		  }while(employeeFound.equals(""));
	}
	// Click on Cancel Link
	private void clickCancelLink(String strLastName){
		String employeeFound="";	
		// Do While Loop For clicking on Cancel Link against employee
		do{
			  if(performAction.isElementPresent(By.xpath("//div[contains(text(),'"+strLastName+"')]/following::a[contains(text(),'Cancel')][1]"))){
				  performAction.click(By.xpath("//div[contains(text(),'"+strLastName+"')]/following::a[contains(text(),'Cancel')][1]"), "Click on Cancel Link from Pending Approval Employees List");
				  logger.info("Employee Found : "+strLastName);
				  employeeFound="found";
				  break;
			  }	
			  if(performAction.isElementPresent(nextButtonInHealthStatement)){
				  this.clickNextButtonInHealthStatement();
			  }else{
				  logger.info("Employee With Last Name ==> "+strLastName+"Not Found");
			  }
		  }while(employeeFound.equals(""));
	}
	// Click on Decline Link
	private void clickDeclineLink(String strLastName){
		String employeeFound="";			
		  do{
			  if(performAction.isElementPresent(By.xpath("//div[contains(text(),'"+strLastName+"')]/following::a[text()='Approve / Decline'][1]"))){
				  performAction.click(By.xpath("//div[contains(text(),'"+strLastName+"')]/following::a[text()='Approve / Decline'][1]"), "Click on Approve / Decline Link from Pending Approval Employees List");
				  logger.info("Employee Found : "+strLastName);
				  employeeFound="found";
				  break;
			  }	
			  if(performAction.isElementPresent(nextButtonInHealthStatement)){
				  this.clickNextButtonInHealthStatement();
			  }else{
				  logger.info("Employee With Last Name ==> "+strLastName+"Not Found");
			  }
		  }while(employeeFound.equals(""));
	}
	
	// Select Employee from Search Results
		private boolean selectEmployeeFromSearchResults(String strLastName){
			String employeeFound="";
			int rowSize=browser.getCurrentWebDriver().findElements(By.xpath(employeesList)).size();
			if(rowSize>0){
				do{
					for(int employeeName=5;employeeName<rowSize;employeeName++){
						this.clickNoEmployeesLink();
						row=employeesList+"["+employeeName+"]";
						if(browser.getCurrentWebDriver().findElement(By.xpath(row)).getText().trim().toLowerCase().contains(strLastName.toLowerCase())){
							selectEmployee=row+"//input";
							performAction.click(By.xpath(selectEmployee), "Click on radio button against Employee from Approval / Refusal List");
							logger.info("Employee Found : "+strLastName);
							employeeFound="found";
							break;
						}
					}
					if(employeeFound.equals("found")){
						break;
					}
					// Break When No More Employees List Available
					if(performAction.isElementPresent(clickNextButtonFromEmployeeApprovalList)){
						performAction.click(clickNextButtonFromEmployeeApprovalList, "Click on Next Button Icon");
					}else if(employeeFound.equals("")){
						logger.info("No More Employees List Available");
						break;
					}
					// Wait Till Employees List is displayed
					performAction.waitUntilElementPresent(By.xpath(employeesList));
					rowSize=browser.getCurrentWebDriver().findElements(By.xpath(employeesList)).size();
				}while(employeeFound.equals(""));
			}
			if(rowSize==0){
				logger.info("Employees List Not Found with Row Size="+rowSize);
				return false;
			}
			if(employeeFound.equals("")){
				logger.info("Employee With Last Name "+strLastName+" Not Found");
				return false;
			}else if(employeeFound.equals("found")){
				return true;
			}
			return false;
		}
	// Verify Employee from Search Results
		private void verifyEmployeeSearchResultsList(String strLastName){
			Boolean employeeFound = false;
			performAction.waitUntilElementPresent(employeeSearchResultsHeader);
			int rowSize=browser.getCurrentWebDriver().findElements(By.xpath(employeeSearchResults)).size();
			if(rowSize>0){
				do{
					for(int employeeName=3;employeeName<rowSize;employeeName++){
						row=employeeSearchResults+"["+employeeName+"]";
						if(browser.getCurrentWebDriver().findElement(By.xpath(row)).getText().trim().toLowerCase().contains(strLastName.toLowerCase())){
							logger.info("Employee Found : "+strLastName);
							  employeeFound = true;
							  break;
						}
					}
					if(employeeFound){
						break;
					}
					if(performAction.isElementPresent(nextButtonIconInBenefitsNotStarted)){
						performAction.click(nextButtonIconInBenefitsNotStarted, "Click on Next Button Icon");
					}
					performAction.waitUntilElementPresent(By.xpath(employeeSearchResults));
					rowSize=browser.getCurrentWebDriver().findElements(By.xpath(employeeSearchResults)).size();
				}while(!employeeFound);
			}
			if(rowSize==0){
				logger.info("No Employees Found");
			}
			if(employeeFound.equals("")){
				logger.info("Employee With Last Name "+strLastName+" Not Found");
			}
		}
	// Verify Employees Count
		private void verifyEmployeesCount(String strHeader,String strLinkText){
			boolean linkFound=false;
			toDoListSectionsRowCount=browser.getCurrentWebDriver().findElements(By.xpath(toDoListSections)).size();
			if(toDoListSectionsRowCount>0){
				// Looping Sections in To DO List
				for(int intToDoListSections=1;intToDoListSections<=toDoListSectionsRowCount;intToDoListSections++){
					toDoListHeader=toDoListSections+"["+intToDoListSections+"]"+"//h2";
					if(browser.getCurrentWebDriver().findElement(By.xpath(toDoListHeader)).getText().trim().equalsIgnoreCase(strHeader)){
						String tableRows=toDoListSections+"["+intToDoListSections+"]"+"//tr";
						// Looping Rows in Individual Sections
						for(int rowCount=1;rowCount<browser.getCurrentWebDriver().findElements(By.xpath(tableRows)).size();rowCount++){
							String strToDoListLinkText=tableRows+"["+rowCount+"]";
							if(browser.getCurrentWebDriver().findElement(By.xpath(strToDoListLinkText)).getText().trim().contains(strLinkText)){
								employeesCount=Integer.valueOf(browser.getCurrentWebDriver().findElement(By.xpath(strToDoListLinkText+"/td[1]")).getText());
								performAction.click(By.xpath(strToDoListLinkText+"//a[text()='"+strLinkText+"']"),"Click on Link Text");
								logger.info("Clicked On Link with Text ===> "+strLinkText);
								linkFound=true;
								break;
							}
						}
					}
					if(linkFound){
						break;
					}
				}
			}
		}
		private void clickDocumentCenter()
		{
			performAction.click(documentCenter, "Document Center");
		}
		private void clickaddDocument()
		{
			performAction.click(addDocument, "Add Document");
		}
		private void clickChooseFile()
		{
			performAction.click(chooseFile, "Choose File");
		}
		private void clickSave()
		{
			performAction.click(save, "Save Button");
		}
		private void verifyErrorMessageDocumentCenter()
		{
			/*int n=browser.getCurrentWebDriver().findElements(By.xpath("//ul[@id='cp-notify-messages']/li")).size();			
			/*for(int i=1;i<=n;i++)
			{*/
			 String mesg=browser.getCurrentWebDriver().findElement(By.xpath("//ul[@id='cp-notify-messages']/li[1]")).getText();
			 logger.info(mesg+ " verified in the page");
			//}
		}
		private void clickDownloadReport()
		{
			performAction.click(downloadReport, "Download Report");
		}
			
	// Click on HR Administrator
		private void clickHRAdministartor(){
			performAction.click(hrUserName, "Click on HR Administrator");
		}
	// Click on My Profile Link
		private void clickMyProfile(){
			performAction.click(myProfile, "Click on My Profile Link");
		}
	// Enter Current Password
		private void enterCurrentPassword(String strCurrentPassword){
			performAction.clearEnter(currentPassword, strCurrentPassword,"Enter Current Password");
		}
	// Enter New Password
		private void enterNewPassword(String strNewPassword){
			performAction.clearEnter(newPassword, strNewPassword, "Enter New Password");
		}
	// Enter Confirm Password
		private void enterConfirmPassword(String strConfirmPassword){
			performAction.clearEnter(confirmPassword, strConfirmPassword, "Enter Confirm Password");
		}	
	// Enter Work Email
		private void enterWorkEmail(String strWorkEmail){
			performAction.clearEnter(workEmail, strWorkEmail, "Enter Work Email");
		}
	// Verify Success Message
		private void verifyProfileSuccessMessage(String strMessage){
			boolean flag=false;
			int rowCount=browser.getCurrentWebDriver().findElements(By.xpath(confirmationRows)).size();
			for(int i=1;i<rowCount;i++){
				String actualText=browser.getCurrentWebDriver().findElement(By.xpath(confirmationRows+"["+i+"]")).getText();
				logger.info(actualText);
				if(actualText.trim().equalsIgnoreCase(strMessage)){
					flag=true;
					break;
				}
			}
			if(flag){
				logger.info(strMessage+" Found Successfully");
			}else{
				logger.info(strMessage+" Not Found");
			}
		}
	// Verify Success Message
			private void verifyApproveConfirmationMessage(String strMessage){
				boolean flag=false;
				String actualText=browser.getCurrentWebDriver().findElement(approveConfirmationRow).getText();
					logger.info(actualText);
					if(actualText.trim().equalsIgnoreCase(strMessage)){
						flag=true;
					}
				if(flag){
					logger.info(strMessage+" Found Successfully");
				}else{
					logger.info(strMessage+" Not Found");
				}
			}
	// Click on Cancel Button
			private void clickCancelButton(){
				performAction.click(cancelButton, "Click on Cancel Button");
			}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Add COBRA Exception' keyword or method is used to add COBRA Exception from COBRA Manager present in Additional Tools panel of HR Admin Home page.
	 * 
	 * PreCondition  : COBRA Exceptions button should be displayed after navigating clicking on COBRA Manager Link.
	 * 
	 * PostCondition : Able to Add COBRA Exception.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | JSON Tag Name |
	 * | td:addcobraexception |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	
	@RobotKeyword
	@ArgumentNames({ "cobraexception","outlastname" })
	public void addCOBRAException(String cobraexception,String outLastName) {
		try {
			if (cobraexception.startsWith("td:"))
				cobraexception = cobraexception.substring(3);
			
			performAction.isElementPresent(cobraManager);
			this.clickCOBRAManager();
			this.clickCOBRAExceptionsButton();
			performAction.verifyMessage("Manage COBRA Exceptions");
			this.clickAddCOBRAExceptionsButton();
			performAction.verifyMessage("Load COBRA Exception");

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(cobraexception.toLowerCase());
			
			object = fields.get("ssn");
			if (object != null) {
				this.setSSNField(utils.generateRandomNumber(object.toString()));

			}
			object = fields.get("firstname");
			if (object != null) {
				this.setFirstNameField(object.toString());
			}

			object = fields.get("lastname");
			if (object != null) {
				String value = utils.generateRandomNumber(object.toString());
				this.setLastNameField(value);
				browser.hMap.put(outLastName, value);
			}
			object = fields.get("birthdate");
			if (object != null) {
				this.setBirthDateField(utils.getDate(fields.get("birthdate")
						.toString()));
			}

			object = fields.get("gender");
			if (object != null) {
				this.selectgenderField(object.toString());
			}

			object = fields.get("address1");
			if (object != null) {
				this.setAddress1Field(object.toString());
			}

			object = fields.get("city");
			if (object != null) {
				this.setCityField(object.toString());
			}

			object = fields.get("state");
			if (object != null) {
				this.selectStateField(object.toString());
			}

			object = fields.get("zipcode");
			if (object != null) {
				this.setZipField(object.toString());
			}

			object = fields.get("country");
			if (object != null) {
				this.selectcountryField(object.toString());
			}
			object = fields.get("plan");
			if (object != null) {
				this.selectPlan(object.toString());
			}
			this.clickSaveButton();
			headerText="//h1[contains(text(),'"+browser.hMap.get(outLastName)+"')]";
			performAction.waitUntilElementPresent(By.xpath(headerText));
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception While adding COBRA Exception" + e.getMessage());
			throw new CustomException(
					"Exception While adding COBRA Exception" + e.getMessage());
		}
	}
	
	/**
	 * <pre> 
	 * Author  : Sekhar Tirumala
	 *  
	 * Description : Keyword or method 'performEmployeeSearch' used to perform the Employee
	 * Search from HR Admin Role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : user should be in Employee search page in  Hr Role 
	 * 
	 * PostCondition : Employee details should be displayed.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | strSearchString - String type argument takes search string like <Last Name> |
	 * <b>Example :</b>
	 * | Auto1234,654578912 |
	 *  
	 * Java file Name : HRAdminHome.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strSearchString" })
	public void performEmployeeSearch(String strSearchString) {
		try {
			this.setSearchCriteria("Auto");
			this.clickGobutton();
			scr.capturePageScreenshot();
			performAction.click(employeeOverviewpopup,
					"Employee Overview Pop UP");
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the employee search "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Filter Task List' keyword used to filter and verify tasks from To-do List in HR Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin.
	 * 
	 * PostCondition : Able to Filter tasks based on filter criteria
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * Description : Enter two parameters say 'Task List' and 'Sub Task List'. If Sub Task List is not available then give parameter as 'none'
	 * 
	 * | strSelectTaskList , strSelectSubList| 
	 * | All Tasks,none or Plan,Dental | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strSelectTaskList","strSelectSubList"})
	public void filterTaskList(String strSelectTaskList,String strSelectSubList) {
		try {
			performAction.isElementPresent(viewByDropdown);
			this.selectViewByDropdown(strSelectTaskList);
			if(!strSelectSubList.equalsIgnoreCase("none")){
				performAction.waitUntilElementPresent(categoryDropDown);
				this.selectCategoryDropDown(strSelectSubList);
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting Filter Task List of HR Admin Home page"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting Filter Task List of HR Admin Home page"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Refuse All From Filter Task List' keyword used to refuse Benefits from TO-do list in Hr Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin, Select Benefit Name from View by drop down and Select Benefit.
	 * 
	 * PostCondition : Able to Refuse Benefits
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strSelectTaskList | 
	 * | category Name or Plan | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLastName"})
	public void refuseAllFromFilterTaskList(String strLastName) {
		try {
			strLastName=utils.getValue(strLastName);
			employeeFound="";
			performAction.isElementPresent(refuseAll);
			this.clickRefuseAllButton();
			this.selectEmployeeFromSearchResults(strLastName);
			this.clickNextButton();
			performAction.verifyMessage("Refuse Benefits Confirmation");
			performAction.verifyMessage("You have chosen to refuse benefits for 1 employee");
			this.clickSaveAndReturnHome();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting Refuse All from Filter Task List of HR Admin Home page"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting Refuse All from Filter Task List of HR Admin Home page"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Approve All From Filter Task List' keyword used to Approve Benefits or Personal Information of Employee from TO-do list in Hr Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin, Select Benefit Name from View by drop down and Select Benefit.
	 * 
	 * PostCondition : Able to Approve Benefits and other employee information
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strNeedToApprove,strLastName | 
	 * | Personal,HMVlastname | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strNeedToApprove","strLastName","strMessage"})
	public void approveAllFromFilterTaskList(String strNeedToApprove,String strLastName,String strMessage) {
		try {
			boolean approveFlag=false;
			this.clickApproveAllButton(strNeedToApprove);
			if(strLastName.equalsIgnoreCase("none")){
				this.clickNoEmployeesLink();
			}else{
				strLastName=utils.getValue(strLastName);
				//approveFlag=true;
				approveFlag=this.selectEmployeeFromSearchResults(strLastName);
			}
			this.clickNextButton();
			
			this.verifyApproveConfirmationMessage(strMessage);
			scr.capturePageScreenshot();
			//performAction.verifyMessage("Approve Employees Confirmation");
			//performAction.verifyMessage("You have chosen to approve changes for 1 employee");
			if(approveFlag){
				this.clickSaveAndReturnHome();
			}else{
				this.clickCancelButton();
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting Approve All from Filter Task List of HR Admin Home page"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting Approve All from Filter Task List of HR Admin Home page"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *  
	 * Description   : 'personalEmployeesLinkInHrRole' keyword used to Employees Need to be Approved Employee from TO-do list in Hr Admin Home page
	 * 
	 * Role : Hr Role
	 * 
	 * PreCondition  : Login as HR Admin, Select AllTasks Name from View by drop down and Select Benefit.
	 * 
	 * PostCondition : Able to Approve Benefits and other employee information
	 * 
	 * Java File Name : HrAdminHome.java
	 *  
	 * </pre>
	 **/
	@RobotKeyword
	public void personalEmployeesLinkInHrRole() {
		try {
			this.clickpersonalemployeesneedtobeapproved();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Navigating Employee Search Result page, Task List of HR Admin Home page "
					+ e.getMessage());
			throw new CustomException(
					"Exception in Navigating Employee Search Result page, Task List of HR Admin Home page"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *  
	 * Description   : 'editHrOEDatesInHrRole' keyword used to change the OE Dates  in Hr Admin Group Setup page
	 * 
	 * Role : HR Role
	 * 
	 * PreCondition  : Login as HR Admin, click on DatesandRules Link.
	 * 
	 * PostCondition : Able to Edit the OE dates for Single Offer/ Multiple Offer's.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPlanLinkname | 
	 * 
	 * | Future/ Current/ All |  
	 * 
	 * Java FileName : HRAdminHome.java
	 *  
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strGiveThePlanLink"})
	public void editHrOEDatesInHrRole(String strGiveThePlanLink) {
		try {

			this.mouseoverDatesAndRulesButton();
			this.clickondatelinks(strGiveThePlanLink);
			this.enterHrBrokerOEDates();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception While Changing the Open Enrollment Dates in HR Admin."
					+ e.getMessage());
			throw new CustomException(
					"Exception While Changing the Open Enrollment Dates in HR Admin."
					+ e.getMessage());
		}
	}



	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 * 
	 * Description   : 'ViewByOffersLinkInHrRole' keyword used to Employees Need to be Approved Employee from TO-do list in Hr Admin Home page
	 * 
	 * Role: HR Role
	 * 
	 * PreCondition  : Login as HR Admin, click on ViewByOffrs Link.
	 * 
	 * PostCondition : Able to Approve Benefits and other employee information
	 *  
	 * Java File Name : HRAdminHome.java
	 * </pre>
	 **/
	@RobotKeyword
	public void viewByOffersLinkInHrRole() {
		try {
			this.clickviewbyoffers();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Navigating To-do list page, Task List of HR Admin Home page "
					+ e.getMessage());
			throw new CustomException(
					"Exception in Navigating To-do list page, Task List of HR Admin Home page"
					+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Cobra Eligible Persons In HR Role' keyword used to verify COBRA Eligible Persons from COBRA Manager in Hr Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin
	 * 
	 * PostCondition : Able to verify COBRA Eligible employees in HR Role
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strLastName | 
	 * | HMVlastname | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLastName"})
	public void cobraEligiblePersonsInHRRole(String strLastName) {
		try {
			if(strLastName.startsWith("HMV")){
				strLastName=browser.hMap.get(strLastName.substring(3));
			}
			this.clickCOBRAManager();
			this.clickCOBRAEligiblePersonsButton();
			performAction.verifyMessage(strLastName);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in verifying COBRA Eligible Persons from COBRA Manager"
					+ e.getMessage());
			throw new CustomException(
					"Exception in verifying COBRA Eligible Persons from COBRA Manager"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Auto Enrollment Summary In Hr Role' keyword used to filter Auto Enrollment in Hr Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin
	 * 
	 * PostCondition : Able to filter Options in Auto Enrollment Summary in HR Role
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | JSONTagName | 
	 * | td:autoenrollmentsummaryfilteroptions | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strautoenrollmentfilter"})
	public void AutoEnrollmentSummaryInHrRole(String strautoenrollmentfilter) {
		try {
			if (strautoenrollmentfilter.startsWith("td:"))
				strautoenrollmentfilter = strautoenrollmentfilter.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(strautoenrollmentfilter.toLowerCase());
			logger.info("fields = " + fields.toJSONString());
			
			this.clickAutoEnrollmentSummaryLink();
			object = fields.get("startdate");
			if (object != null) {
				this.enterStartDate(utils.getValue(object.toString()));
			}
			object = fields.get("enddate");
			if (object != null) {
				this.enterEndDate(utils.getValue(object.toString()));
			}
			object = fields.get("benefitoffers");
			if (object != null) {
				this.selectBenefitOffersInAutoEnrollment(object.toString());
			}
			object = fields.get("plans");
			if (object != null) {
				this.selectPlanInAutoEnrollment(object.toString());
			}
			this.clickApplyFilterButton();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Auto Enrollment Summary Page from COBRA Manager"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Auto Enrollment Summary Page from COBRA Manager"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Verify Employees From To Do List' keyword used to verify Employee whose benefits not started in HR Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin, Select Benefit Name from View by drop down and Select Benefit.
	 * 
	 * PostCondition : Able to verify Employee whose benefits not started
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | StrLastname, strLinkText | 
	 * | HMVlastname, Employees whose enrollment period has passed|
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 *  
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLastName","strLinkText"})
	public void verifyEmployeesFromToDoList(String strLastName,String strLinkText) {
		try {
			if(strLastName.startsWith("HMV")){
				strLastName=browser.hMap.get(strLastName.substring(3));
			}
			this.clickLinkInHomePage(strLinkText);
			this.verifyEmployeeSearchResults(strLastName);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in verifying "+strLinkText+" Link from HR Admin Home page"
					+ e.getMessage());
			throw new CustomException(
					"Exception in verifying "+strLinkText+" Link from HR Admin Home page"
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Verify Employees Count From To Do List' keyword used to verify Employees count from links available from Filter Task List in HR Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin, Click on Link Text populated in To do List.
	 * 
	 * PostCondition : Able to verify Employees count from link text.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strLinkText | 
	 * | Employees whose enrollment period has passed | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strHeader","strLinkText"})
	public void verifyEmployeesCountFromToDoList(String strHeader,String strLinkText) {
		
		try {
			
			this.verifyEmployeesCount(strHeader,strLinkText);
			String activeEmployeesCount=browser.getCurrentWebDriver().findElement(activeInnerEmployeesCount).getText();
			String terminatedEmployeesCount=browser.getCurrentWebDriver().findElement(terminatedInnerEmployeesCount).getText();
			String dependentsCount=browser.getCurrentWebDriver().findElement(dependentsInnerEmployeesCount).getText();
			
			totalEmployeesCount=totalEmployeesCount+Integer.valueOf(activeEmployeesCount);
			totalEmployeesCount=totalEmployeesCount+Integer.valueOf(terminatedEmployeesCount);
			totalEmployeesCount=totalEmployeesCount+Integer.valueOf(dependentsCount);
			
			if(employeesCount==totalEmployeesCount){
				logger.info("Employees Count from "+strLinkText+" : "+employeesCount+ " is equal to "+ totalEmployeesCount);
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in verifying "+strLinkText+" Count from HR Admin Home page"
					+ e.getMessage());
			throw new CustomException(
					"Exception in verifying "+strLinkText+" Count from HR Admin Home page"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Navigate to Partner Site from App Launcher' keyword used to navigate to Partner site from App Launcher in Home Page in Hr Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin, App Launcher is available.
	 * 
	 * PostCondition : Able to navigate to Partner Site.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strLinkText , strUniqueValueInOpenableWindow | 
	 * | Equifax workforce solutions , Benefitfocus | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLinkText","strUniqueValueInOpenableWindow"})
	public void navigateToPartnerSiteFromAppLauncher(String strLinkText,String strUniqueValueInOpenableWindow) {
		
		try {
			this.clickAppBuilderButton();
			this.clickAppBuilderOptionLink(strLinkText);
			performAction.selectLatestWindow(strUniqueValueInOpenableWindow);
			logger.info("Switched to Window : "+strUniqueValueInOpenableWindow);
			performAction.closeLatestWindow(strUniqueValueInOpenableWindow);
			performAction.selectLatestWindow("Welcome back");
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Navigate to Partner Site from App Launcher In Home Page In HR Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Navigate to Partner Site from App Launcher In Home Page In HR Role"
							+ e.getMessage());
		}
	}
	
	
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 * 
	 * Description  : 'viewHRInTouch' keyword or method is used to verify by clicking on Communication Platform button 
	 * in HRAdmin Role it navigates to HRInTouch Home Page.
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : Login as HRAdmin, AppLauncher Box is available. 
	 * 
	 * PostCondition: Able to navigate to HRInTouch Home Page. 
	 * 
	 * <b>Parameters & Example </b> 
	 * | None |
	 * 
	 * Java file Name : HRAdminHome.java
	 * </pre>
	 **/
	@RobotKeyword
	
	public void viewHRInTouch() {		
		try {
			this.clickAppLauncherBox();
			this.clickCommunicationPlatform();		
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Navigate to CommunicationPlatform In HR Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Navigate to CommunicationPlatform In HR Role"
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 * 
	 * Description  : 'vieweBilling' keyword or method is used to verify by clicking on Billing button in HRAdmin Role it navigates to eBilling Home Page.
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : Login as HRAdmin, AppLauncher Box is available. 
	 * 
	 * PostCondition: Able to navigate to eBilling Home Page. 
	 * 
	 * <b>Parameters & Example </b> 
	 * | None |
	 * 
	 * Java file Name : HRAdminHome.java
	 * </pre>
	 **/
	@RobotKeyword
	
	public void viewEbilling() {		
		try {
			this.clickAppLauncherBox();
			this.clickBilling();		
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Navigate to Billing In HR Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Navigate to Billing In HR Role"
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Navigate To Health Statement Approval Manager And Filter' keyword used to navigate to Health Statement Approval Manager and Filter based on Employee status in HR Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin, Health Statement Approval Manager link is available in Additional Tools Panel.
	 * 
	 * PostCondition : Able to filter employees based on Health Statements.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strFilter | 
	 * | Employees Pending Approval | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strFilter"})
	public void navigateToHealthStatementApprovalManagerAndFilter(String strFilter) {
		
		try {
			this.clickHealthStatementApprovalManagerLink();
			this.selectHealthStatementApprovalManagerFilter(strFilter);
			this.clickGoButtonInHealthStatement();		
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Navigate To Health Statement Approval Manager And Filter Employees In Home Page In HR Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Navigate To Health Statement Approval Manager And Filter Employees In Home Page In HR Role"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Verify Employee In Health Statement Approval Manager' keyword used to navigate to Health Statement Approval Manager and Filter Employees In Home Page in Hr Admin Home page
	 * 
	 * PreCondition  : Login as HR Admin, Navigate to Employees Filter List from Additional Tools Panel.
	 * 
	 * PostCondition : Able to verify employees based on Health Statement Filter option.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strFilter | 
	 * | Employees Pending Approval | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLastName"})
	public void verifyEmployeeInHealthStatementApprovalManager(String strLastName) {
		strLastName=utils.getValue(strLastName);
		try {
			//performAction.verifyMessage(utils.getValue(strLastName));
			String employeeFound="";			

			if(browser.getCurrentWebDriver().getPageSource().contains(strLastName)){
				logger.info("Employee Found : "+strLastName);
			}else {
			  do{
				  performAction.isElementPresent(nextButtonIconInBenefitsNotStarted);
				  this.clickNextButtonInHealthStatement();
				  if(browser.getCurrentWebDriver().getPageSource().contains(strLastName)){
					  logger.info("Employee Found : "+strLastName);
					  employeeFound="found";
					  break;
				  }				  
			  }while(employeeFound.equals(""));
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Verifying Employee In Health Statement Approval Manager Filter In HR Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Verifying Employee In Health Statement Approval Manager Filter In HR Role"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Approve Or Cancel Requested Amount In HR Role' keyword used to navigate to Health Statement Approval Manager and Filter based on Employee status, Approve or Decline Requested Amount In HR Admin Home Page
	 * 
	 * PreCondition  : Login as HR Admin, Navigate to Pending Approval Employees List from Additional Tools Panel.
	 * 
	 * PostCondition : Able to approve/decline employees.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strLastName,strApproval | 
	 * | HMVlastname,Approve $60,000.00 | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLastName","strApproval"})
	public void approveOrCancelRequestedAmountInHRRole(String strLastName,String strApproval) {
		strLastName=utils.getValue(strLastName);
		try {
			
			if(strApproval.contains("Approve")){
			    this.clickApproveLink(strLastName);
				browser.getCurrentWebDriver().findElement(By.xpath("//label[contains(text(),'"+strApproval+"')]/preceding::input[1]")).click();
				this.clickNextButton();
				this.clickNextButton();
				this.clickSaveButton();
			}else{
				this.clickCancelLink(strLastName);
				this.clickCancelRequestButton();
			}
			performAction.verifyMessage(strLastName);			
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Verifying Approving/Cancelling Requested Amount for Employee In HR Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Verifying Approving/Cancelling Requested Amount for Employee In HR Role"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Decline Requested Amount In HR Role' keyword used to navigate to Health Statement Approval Manager and Filter Employees, Decline Requested Amount for Employee In HR Admin Home Page
	 * 
	 * PreCondition  : Login as HR Admin, Navigate to Pending Approval Employees List from Additional Tools Panel.
	 * 
	 * PostCondition : Able to approve/decline employees.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strLastName,strReason | 
	 * | HMVlastname,Cancer | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLastName","strReason"})
	public void declineRequestedAmountInHRRole(String strLastName,String strReason) {
		strLastName=utils.getValue(strLastName);
		try {
			    this.clickDeclineLink(strLastName);
				browser.getCurrentWebDriver().findElement(By.xpath("//label[contains(text(),'Decline')]/preceding::input[1]")).click();
				this.clickNextButton();
				this.selectDeclineReasonForHealthStatement(strReason);
				this.clickNextButton();
			    this.clickSaveButton();
			performAction.verifyMessage(strLastName);			
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Declining Requested Amount In HR Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Declining Requested Amount In HR Role"
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Verify Employee Search Results keyword used to verify an Employee from Search Results.
	 * 
	 * PreCondition  : Login as HR Admin, Navigate to Employee Search Results page.
	 * 
	 * PostCondition : Able to find an employee in Employee Search Results.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strLastName| 
	 * | HMVlastname| 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLastName"})
		public void verifyEmployeeSearchResults(String strLastName){
		try{
			if(strLastName.startsWith("HMV")){
				strLastName=utils.getValue(strLastName);
			}
			this.verifyEmployeeSearchResultsList(strLastName);
			scr.capturePageScreenshot();
		}catch(Exception e){
			scr.capturePageScreenshot();
			logger.info("Exception in Searching an Employee from Search Results"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Searching an Employee from Search Results"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Update Profile Information'  keyword is used to update Work Email and Password of an HR Administrator from My Profile Page in HR Admin Home Page.
	 * 
	 * PreCondition  : Login as HR Admin and Navigate to Home Page.
	 * 
	 * PostCondition : Able to update Work Email or Password of an HR Administrator.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | JSON TAG Name| 
	 * | td:updatehradmininformation| 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminHome.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strUpdateHRAdminInformation"})
		public void updateProfileInformation(String strUpdateHRAdminInformation){
		try{
			if(performAction.isElementPresent(hrUserName)){
				this.clickHRAdministartor();
			}
			this.clickMyProfile();
			boolean flag=false;
			Object object=null;
	        JSONObject fields=ReadJsonTestData.getTestData(strUpdateHRAdminInformation.toLowerCase());
	        object=fields.get("currentpassword");
	        if(object!=null){
	        	this.enterCurrentPassword(object.toString());
	        	flag=true;
	        }
			object=fields.get("newpassword");
			if(object!=null){
				this.enterNewPassword(object.toString());
			}
			object=fields.get("newpassword");
			if(object!=null){
				this.enterConfirmPassword(object.toString());
			}
			object=fields.get("workemail");
			if(object!=null){
				this.enterWorkEmail(object.toString());
				flag=false;
			}
			this.clickSaveButton();
			// Verification of Message after updating HR Administrator Profilr Information. 
			if(flag){
				this.verifyProfileSuccessMessage("Your administrator password has been changed.");
			}else{
				this.verifyProfileSuccessMessage("Your work email has been changed.");
			}
			scr.capturePageScreenshot();
			this.clickNextButton();
			scr.capturePageScreenshot();
		}catch(Exception e){
			scr.capturePageScreenshot();
			logger.info("Exception in Updating Profile Information of an HR Administrator"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Updating Profile Information of an HR Administrator"
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *
	 * Description   : 'addDocumentInDocumentCenter' keyword or method is used to add the Document in Document in HR Admin
	 * 
	 * Role : HR Role
	 *
	 * PreCondition  : User should be HR Admin Home page
	 *
	 * PostCondition : Successfully adding the Document	  
	 * 
	 * Java FileName : HRAdminHome.java
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strLastName"})
		public void addDocumentInDocumentCenter(String value){
		try{
			this.clickDocumentCenter();
			Thread.sleep(3000);
			this.clickaddDocument();	
			String [] tempValues = value.split(":");
			String[] commands = new String[]{};						
			commands = new String[]{"Q:\\KB\\HrInTouch\\Data\\"+tempValues[0]+".exe", tempValues[1]}; //location of the autoit executable					
			Thread.sleep(5000);
			//this.clickfileUpload();
			this.clickChooseFile();
			System.out.println("Command :: "+commands);
			Runtime.getRuntime().exec(commands);
			Thread.sleep(10000);				
			this.clickSave();
			Thread.sleep(4000);
			scr.capturePageScreenshot();
			if(performAction.isElementPresent(errorMesg))
				System.out.println(browser.getCurrentWebDriver().findElement(By.xpath("//ul[@id='cp-notify-messages']/li[1]")).getText()+ " verified in the page");
			 			
			//this.verifyErrorMessageDocumentCenter();
		}catch(Exception e){
			scr.capturePageScreenshot();
			logger.info("Exception in Uploading document in Documnet Center as HR Admin"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Uploading document in Documnet Center as HR Admin"
							+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *
	 * Description   : 'addDocumentInDocumentCenter' keyword or method is used to add the Document in Document in HR Admin
	 * 
	 * Role : HR Role
	 *
	 * PreCondition  : User should be in Group Information page as HR Admin Role
	 *
	 * PostCondition : Successfully docwnload the Document	  
	 * 
	 * Java FileName : HRAdminHome.java
	 *
	 * </pre>
	 **/
	
	@RobotKeyword	
		public void downloadGroupInformationInHrRole(){
		try{
			
			Thread.sleep(3000);			
			String[] commands = new String[]{};						
			commands = new String[]{"Q:\\KB\\HrInTouch\\Data\\DownloadFile_GroupInfo.exe"}; //location of the autoit executable										
			Runtime.getRuntime().exec(commands);
			Thread.sleep(10000);				
			this.clickDownloadReport();
			Thread.sleep(7000);
			scr.capturePageScreenshot();			 		
		}catch(Exception e){
			scr.capturePageScreenshot();
			logger.info("Exception in Dowinload Group Information file in HR Admin"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Dowinload Group Information file in HR Admin"
							+ e.getMessage());
		}
	}
}


