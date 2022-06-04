package com.benefitfocus.robot.hradmin;


import org.json.simple.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.python.modules.thread.thread;
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
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

@RobotKeywords
public class EmployeeHomePage {

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
	// Selenium locators
	By editEmployeeProfile = By
	.cssSelector("a[href*='collectPersonalAndWork']");
	By editEmployeeCategory = By.xpath("//h2[text()='Categories']//a/span[text()='Edit']");
	By earningAmount = By.id("earningsUpdateRepresentation.earningsAmount");
	By earningFrequency = By.id("earningsUpdateRepresentation.earningsClass");
	By payFrequency = By.id("earningsUpdateRepresentation.payPeriod");
	By earningEffectiveDate = By
	.id("earningsUpdateRepresentation.earningsEffectiveDate");
	By employeeProfileSaveButton = By
	.cssSelector("a[href*='saveIndividualEmployeeSection']");
	By employeeOverviewpopup = By
	.xpath("//button[contains(text(),'Not right now')]");
	By employeeSalary = By.xpath("//dt[contains(text(),'Salary')]/../dd[2]");

	By categoryEffectiveDate = By.id("effectiveDate");
	String categoryList = "//h2[contains(text(),'Current Category Change')]/../../div[2]//tr";
	String emergencyContactInformation="Emergency Contact Information";
	By confirmationMsg=By.xpath("//h1[text()='Confirmation']");
	By categoryChangeNextButton = By.linkText("Next");	
	By employeeHistory=By.linkText("Employee History");
	By pointInTimeeffectiveDate=By.id("effectiveDate");
	By pointInTimeRadioButton=By.id("employeeHistoryViewMode-0");
	By selectPointInTimeDate=By.id("pit1Date");
	By NextButtom=By.xpath("//button[text()='Next']");
	By compareTrigger=By.id("compareTrigger");
	By compareDateTextbox=By.id("pit2Date");
	By afterSalUpdated=By.xpath("//div[text()='Salary']/../..//div[@class='col-md-5 spacer spacer-after']");
	By beforeSalUpdated=By.xpath("//div[text()='Salary']/../..//div[@class='col-md-5 spacer spacer-before']");
	// Locators Add Contact Information page
	By effectiveDate=By.id("effectiveDate");	
	By addEmergencyContact=By.linkText("Add Emergency Contact");	
	By relationShip=By.xpath("//input[@id='relationship']");
	By name=By.xpath("//input[@id='name']");
	By phoneNumber=By.id("homePhoneNumber");
	By alternatePhoneNo=By.id("mobilePhoneNumber");
	By email=By.id("email");
	By saveButton=By.linkText("Save");

	By EmployeeOverView = By.xpath("//span[contains(text(),'Overview')]");
	By retireEmployeeCheckButton = By.id("retired-nativeHtmlElement");
	By updateEmployeeEarningsButton = By.linkText("Update Employee's Earnings");


	//View in History of Changes Locators
	
	By viewInHistoryOfChangesLink = By.linkText("View in History of Changes");
	By runButton   = By.xpath("//strong[text()='Run']");
	By closeButton  = By.id("close");	
	By searchButtonHRAdmin = By.xpath("//a/strong[text()='Search']");
	By benefitsNotStartedMesg=By.xpath("//h2[text()='Benefits not started']");
	By participationPeriod=By.xpath("//form[@class='form form-inline']/..//select[@id='participationPeriods']");
	By refuseAll=By.linkText("Refuse All");
	By employeeWithBenefitsnotstartedMsg=By.linkText("Employee with benefits not started");
	By nextForRefuseAll=By.xpath("//strong[text()='Next']");
	By refuseBenefitsConfirmationMsg=By.xpath("//h1[text()='Refuse Benefits Confirmation']");
	By saveAndReturnHomeButton=By.linkText("Save and return Home");
	By thereAreNoTasksMsg=By.xpath("//p[text()='There are no tasks.']");
	By oeManagerMsg=By.xpath("//h2[text()='OE Manager']");
	By endOELink=By.linkText("End OE");
	String addContactIfoMsg="Add Contact Information";
	By payFreqyency  = By.id("earningsUpdateRepresentation.payPeriod");
	
	// Locators in Emergency Contact Information Page
	
	By emergencyContactInfoMsg=By.xpath("//h2[text()='Emergency Contact Information']");
	By addContactInformationMsg=By.xpath("//h1[text()='Add Contact Information']");
	// Locators in Edit Profile Page in HR Amdin
	By firstName=By.id("firstName");
	By lastName=By.id("lastName");
	By address1=By.id("address1");
	By address2=By.id("address2");
	By city=By.id("city");
	By state=By.id("state");
	By zip=By.id("zip");
	By country=By.id("country");
	By birthDate=By.id("birthDate");
	By homePhone=By.id("homePhone");
	By cellPhone=By.id("cellPhone");
	By alternatePhone=By.id("alternatePhone");
	By personalEmail=By.id("homeEmail");
	By gender=By.id("gender");
	By maritalStatus=By.id("maritalStatus");
	By hireDate=By.id("hireDate");
	By amount=By.id("earningsUpdateRepresentation.earningsAmount");
	By amountBasedOn=By.id("earningsUpdateRepresentation.earningsClass");
	By calendarSet=By.id("earningsUpdateRepresentation.calendarSetUrl");
	By earningsEffeciveDate=By.id("earningsUpdateRepresentation.earningsEffectiveDate");
	By occupation=By.id("occupation");
	By ssoId=By.id("ssoId");
	// Locators for Work Contact Information in Employee Edit Profile Page
	By workPhone=By.id("workPhone");
	By mobilePhone=By.id("mobilePhone");
	By pager=By.id("pager");
	By workEmail=By.id("workEmail");
	By empHiredate=By.id("hireDate");
	
	// Variables used in public Methods	
	String datevalue="";
	//click on Search Button of Employees on HRAdmin 
	
    private void clickSearchButton(){
    	performAction.click(searchButtonHRAdmin, "Search Button");
    }


	// click on Edit button for Employee profile
	private void clickEditEmployeProfile() {
		performAction.click(editEmployeeProfile, "Employee Profile");
	}

	// click on Edit button for Employee profile
	private void clickEditCategories() {
		performAction.jsclick(editEmployeeCategory, "Employee category");
	}

	// Set earnings amount
	private void setEarningsAmount(String strAmount) {
		if (performAction.isElementPresent(updateEmployeeEarningsButton)) {
			performAction.click(updateEmployeeEarningsButton,
					"Update Employee Earnings Button");
		}
		performAction.clearEnter(earningAmount, strAmount, "Earnings Amount");
	}

	// Set category effectivedate
	private void setCategoryEffectiveDate(String strDate) {
		performAction.enter(categoryEffectiveDate, strDate, "Earnings Amount");
	}

	// set the earnings frequency combo box
	private void selectEarningsFrequency(String strValuetoSelect)
	throws Exception {
		performAction.select(earningFrequency, strValuetoSelect,
		"Earnings Frequency");
	}

	// set the pay frequency combo box
		private void selectPayFrequency(String strValuetoSelect)
		throws Exception {
			performAction.select(payFrequency, strValuetoSelect,
			"Earnings Frequency");
		}
	// click on save Employee profile
	private void clickSaveEmployeProfile() {
		performAction.click(employeeProfileSaveButton, "Save Button");
	}

	// click on Next button on Category change
	private void clickNextButtonOnChangeCategory() {
		performAction.click(categoryChangeNextButton, "Next Button");
	}

	// click on save category change profile
	private void clickSaveCategoryChange() {
		performAction.click(saveButton, "Save Button");
	}
	// click on employee history
	private void clickEmployeeHistory()
	{
		performAction.click(employeeHistory, "Employee History");
	}
	// click on point in time Radion Button
	private void clickPointInTimeRadio()
	{
		try
		{
		 Thread.sleep(2000);		 
		 performAction.click(pointInTimeRadioButton, "Point In time Radio button");
		}catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	private void selectCategory(String strCategoryName, String strNewSelection) {

		int rowsize = browser.getCurrentWebDriver()
		.findElements(By.xpath(categoryList)).size();

		for (int i = 2; i <= rowsize; i++) {
			String row = categoryList + "[" + i + "]/td[1]";
			if (browser.getCurrentWebDriver().findElement(By.xpath(row))
					.getText().trim().toLowerCase()
					.contains(strCategoryName.toLowerCase())) {
				logger.info(strCategoryName + "Found in the row no : "
						+ i);
				String selectboxid = browser
				.getCurrentWebDriver()
				.findElement(
						By.xpath(categoryList + "[" + i
								+ "]/td[3]//select"))
								.getAttribute("id");
				By selectObj = By.id(selectboxid);
				performAction.select(selectObj, strNewSelection,
						strNewSelection + " value selected for "
						+ strCategoryName);

				break;
			}
		}
	}
	// Enter Effective Date
	private String effectiveDate(String strenterEffectiveDate) {
		if (strenterEffectiveDate.startsWith("d:")) {
			strenterEffectiveDate = strenterEffectiveDate.substring(2);

		}else {
			performAction.clearEnter(pointInTimeeffectiveDate, strenterEffectiveDate,
					"Enter Event Date");
		}
		return utils.getDate(strenterEffectiveDate);
	}
	// Click on Next Button
	private void clickNext()
	{
		performAction.click(NextButtom, "Next Button");
	}
	// click on Add another date compare Button
	private void clickAnotherDateCompare()
	{
		performAction.click(compareTrigger, "Add Another Date Compare Button");
	}
	// Enter Comparing date in the Compare date Text Box
	private void setCompareDate(String date)
	{
		performAction.clearEnter(compareDateTextbox, date, "Caompare Date");
	}

	private void clickAddEmergencyContact()
	{
		performAction.click(addEmergencyContact, "Add Emergency Contact");
	}
	private void setRelationShip(String reliation)
	{
		performAction.enter(relationShip,reliation ,"Relationship");
	}
	private void setFullName(String fullName)
	{
		performAction.enter(name, fullName, "Full Name");
	}
	private void setPhoneNumber(String phoneNo)
	{
		performAction.enter(phoneNumber, phoneNo, "PhoneNumber");
	}
	private void setAlternatePhone(String phoneNo)
	{
		performAction.enter(alternatePhoneNo, phoneNo, "AlternatePhone");
	}
	private void setEmail(String mailid)
	{
		performAction.enter(email, mailid, "email");
	}
	private void clickSave()
	{
		performAction.click(saveButton, "Save Button");
	}

	// Click Employee Overview tab
	private void clickEmployeeOverView() {
		performAction.click(EmployeeOverView, "Employee");
	}

	// select check box for retire employee
	private void selectRetireEmployeeCheckBox() {
		performAction.click(retireEmployeeCheckButton,
		"Retire Employee Check Button");

	}
	//clear and enter new hire date
	private void enterNewHiredate(String strHireDate) {
		performAction.clearEnter(empHiredate, utils.getDate(strHireDate),"Enter Hire Date");   	
	}

	
	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *
	 * Description   :verifyEmployeeSalary keyword or method is used to verify the Employee
	 * salary in HR Admin Role
	 * 
	 * Role : HR Admin Role
	 *
	 * PreCondition  : Employee should be available for the corresponding group
	 *
	 * PostCondition : Employee Home Page will be displayed in HR Admin Role
	 *  
	 * <b>Parameters & Example </b>
	 * 
	 * | Amount - This arguments allow us to verify salary amount |
	 * | 10,000 / 5,000 |
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 */

	@RobotKeyword
	@ArgumentNames({ "Amount" })
	public void verifyEmployeeSalary(String Amount) {
		performAction.verify(employeeSalary, Amount,
				"Employee Salary updated : " + Amount);
	}

	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 *
	 * Description   : 'navigateToEmployeeHomePage' keyword or method is used to navigate to Employee Home Page in HR Admin Role
	 *
	 * PreCondition  : Employee should be available for the corresponding group
	 *
	 * PostCondition : Employee Home Page will be displayed in HR Admin Role
	 *
	 * </pre>
	 **/

	@RobotKeyword
	public void navigateToEmployeeHomePage() {
		try {
			this.clickEmployeeOverView();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out
			.println("Exception occured while cicking EmployeeOverView tab"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while cicking EmployeeOverView tab"
					+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *
	 * Description   :updateEmployeeSalary keyword or method is used to update the Employee
	 * salary in HR Admin Role
	 *
	 * PreCondition  : Employee should be available for the corresponding group
	 *
	 * PostCondition : Employee Home Page will be displayed in HR Admin Role
	 *  
	 * <b>Parameters & Example </b>
	 * 
	 * | Amount - This arguments allow us to set the updated salary amount | frequency - This argument allow us to set the pay frequency |
	 * | 10,000 / 5,000 | Bi-Weekly, per Month |
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "Amount", "frequency" })
	public void updateEmployeeSalary(String Amount, String frequency) {

		try {
			this.clickEditEmployeProfile();			
			//performAction.waitUntilElementPresent(updateEmployeeEarningsButton);
			this.setEarningsAmount(Amount);
			this.selectEarningsFrequency(frequency.toLowerCase());
			performAction.waitUntilElementPresent(employeeProfileSaveButton);
			this.clickSaveEmployeProfile();
			Thread.sleep(3000);
		} catch (Exception e) {
			throw new CustomException("Exception in the employee salary "
					+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *  
	 * Description  : changeEmployeeCategory keyword or method is used to update the Employee
	 * category information 
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : Navigate to Categories page  
	 * 
	 * PostCondition:  Should save the Category with selected category.  
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Category name - Category name that is visible on the page | strNewSelection - category to be selected |
	 * | Plan | Dental | for bfqatestautomationgroup |
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "strCategory", "strNewSelection" })
	public void changeEmployeeCateogry(String strCategory,
			String strNewSelection) {

		try {

			this.clickEditCategories();
			//performAction.verifyMessage("Change Categories");
			this.setCategoryEffectiveDate("d:currentdate");								
			this.selectCategory(strCategory, strNewSelection);
			this.clickNextButtonOnChangeCategory();
			//performAction.verifyMessage("New Categories");
			logger.info(strCategory);
			logger.info(strNewSelection);
			//performAction.verifyMessage(strNewSelection);
			//performAction.verifyMessage(strCategory);
			this.clickSaveCategoryChange();
			//performAction.waitUntilElementPresent(confirmationMsg);
			//performAction
			//.verifyMessage("You have successfully changed this employee's category selections");
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in the employee category "
					+ e.getMessage());
		}
	}


	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description  : 'effectiveDateOfChange' keyword is used to enter Effective Date 
	 * 
	 * Role : HR Admin Role
	 *  
	 * PreCondition : Navigate to Effective Date or End Date Text box.  
	 * 
	 * PostCondition: Able to enter Effective date 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Effective Date |
	 * | d:currentdate or d:currentdate:d:3 |
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strEffectiveDate", "strdatevalue" })
	public void effectiveDateOfChange(String strEffectiveDate,String outdatevalue) {
		try {
			this.clickEditEmployeProfile();
			performAction.waitUntilElementPresent(pointInTimeeffectiveDate);
			datevalue=this.effectiveDate(strEffectiveDate);
			browser.hMap.put(outdatevalue, datevalue);
			performAction.clearEnter(pointInTimeeffectiveDate,
					datevalue, "Enter Effective Date");
			performAction.waitUntilElementPresent(employeeProfileSaveButton);
			this.clickSaveEmployeProfile();
			Thread.sleep(3000);
			scr.capturePageScreenshot();			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in selecting effective date"+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description  : 'selectPointInTime' keyword used to enter Effective Date in Point In Time.
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : Navigate to Point In Time Text box.  
	 * 
	 * PostCondition: Able to enter Effective date 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Effective Date |
	 * | strEffectiveDate  |
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strEffectiveDate" })
	public void selectPointInTime(String strEffectiveDate) {
		try {
			this.clickEmployeeHistory();
			this.clickPointInTimeRadio();			
			datevalue=utils.getValue(strEffectiveDate);
			performAction.enter(selectPointInTimeDate, datevalue, "Select Date");
			this.clickNext();
			scr.capturePageScreenshot();			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in selecting effective date in Point In Time"
					+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 * 
	 * Description  : 'verifyEmployeeLastNameAndSalary' keyword or method is used to verify the Employee Salary for point In time of Employee
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : Navigate to Point In Time Text box.  
	 * 
	 * PostCondition: Able to enter Effective date 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strlastname | strSal |
	 * | d:currentdate  | 40000 |
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "strlastname", "strSal" })
	public void verifyEmployeeLastNameAndSalary(String strlastname, String strSalary) {

		strlastname=utils.getValue(strlastname);
		String strlastnamelocator="//div[contains(text(),'"+strlastname+"')]";
		performAction.verify(strlastnamelocator, strlastname,
				"Employee Last Name : " + strlastname);		
		String strSallocator="//div[text()='Salary']/../..//div[contains(text(),'"+strSalary+"')]";
		performAction.verify(strSallocator, strSalary,
				"Employee Salary Updated : " + strSalary);
		scr.capturePageScreenshot();		
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 * 
	 * Description : updateEmployeeEffectiveDateAndSalary keyword or method is used to update the Employee
	 * salary and Effective date in HR Admin Role
	 * 
	 * Role : HR Admin Role
	 *
	 * PreCondition : Navigate to Employee Page  .  
	 * 
	 * PostCondition: Able to edit Employee profile page and update the data 
	 * 
	 * <b>Parameters :</b>
	 * | Amount - This arguments allow us to set the updated salary amount | frequency - This argument allow us to set the pay frequency |
	 * | effectivedate - This argument allow us to set the effective date |outdatevalue - This argument allow us to store the date value |
	 * 
	 * <b>Example :</b>
	 * | 10,000 / 5,000  | Bi-Weekly, per Month | d:currentdate:d:5 | variable_name  |
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "Amount", "frequency" , "effectivedate" , "outdatevalue" })
	public void updateEmployeeEffectiveDateAndSalary(String Amount, String frequency, String strEffectiveDate,String outdatevalue) {

		try {

			this.clickEditEmployeProfile();
			performAction.waitUntilElementPresent(pointInTimeeffectiveDate);
			String datevalue=this.effectiveDate(strEffectiveDate);
			browser.hMap.put(outdatevalue, datevalue);
			performAction.clearEnter(pointInTimeeffectiveDate,
					datevalue, "Enter Effective Date");
			performAction.waitUntilElementPresent(updateEmployeeEarningsButton);
			this.setEarningsAmount(Amount);
			this.selectEarningsFrequency(frequency.toLowerCase());			
			performAction.waitUntilElementPresent(employeeProfileSaveButton);
			this.clickSaveEmployeProfile();
			Thread.sleep(3000);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in upadting employee salary and Effective date"
					+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description  : 'addAnothreDateToCompare' keyword used to Compare the updated information for two dates
	 * 
	 * PreCondition : Navigate to Point In Time Page .  
	 * 
	 * PostCondition: Able to Compare previous and After Modified data 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Effective Date |
	 * | HMVdatevalue  |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strEffectiveDate" })
	public void addAnotherDateToCompare(String strEffectiveDate) {
		try {
			this.clickAnotherDateCompare();			
			this.setCompareDate(strEffectiveDate);
			logger.info("Before Updated Salary"+browser.getCurrentWebDriver().findElement(beforeSalUpdated).getText());
			logger.info("After Updated Salary"+browser.getCurrentWebDriver().findElement(afterSalUpdated).getText());
			scr.capturePageScreenshot();			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in selecing effective date"
					+ e.getMessage());			

		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description  : 'addEmergencyContactInEmployeeProfile'  keyword used to Edit Employee Profile and add Emergency Contact in HR Admin Role
	 * 
	 * Role : Member Role 
	 * 
	 * PreCondition : User Should be in Employee Overview Page
	 * 
	 * PostCondition: Emergency Contact should be added in Employee Profile 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * |strProfile - "is used to get the data set from the Json file" |
	 * 
	 * Java file Name :  EmployeeHomePage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strProfile" })
	public void addEmergencyContactInEmployeeProfile(String strProfile)
	{
		if (strProfile.startsWith("td:"))
			strProfile = strProfile.substring(3);
		try {
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(strProfile
					.toLowerCase());
			this.clickEditEmployeProfile();			
			scr.capturePageScreenshot();
			performAction.verify(emergencyContactInfoMsg, "Emergency Contact Information", "Emergency Contact Information Message");
			logger.info(emergencyContactInformation+" is Displayed in Employee profile page ");	
			performAction.waitUntilElementPresent(addEmergencyContact);
			performAction.jsclick(addEmergencyContact, "Add Emergency Contact");		
			performAction.verify(addContactInformationMsg, "Add Contact Information", "Add Contact Information");	

			logger.info(addContactIfoMsg+" is Verified in Contact Information page");
			object = fields.get("relationship");
			if (object != null) {
				this.setRelationShip(object.toString());	
			}
			object = fields.get("fullname");
			if (object != null) {
				this.setFullName(object.toString());	
			}
			object = fields.get("phonenumber");
			if (object != null) {
				this.setPhoneNumber(object.toString());	
			}
			object = fields.get("alternatephone");
			if (object != null) {
				this.setAlternatePhone(object.toString());	
			}
			object = fields.get("email");
			if (object != null) {
				this.setEmail(object.toString());	
			}
			this.clickSave();
			object = fields.get("date");
			if (object != null) {
				this.effectiveDate(object.toString());
			}						
			// click on save button
			this.clickSaveEmployeProfile();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in clicking"
					+ e.getMessage());			
		}
	}

	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 *
	 * Description   : 'performEmployeeRetirement' keyword or method is used to retire the Employee from HR Admin Role
	 *
	 * PreCondition  : Employee Profile page should be opened in HR Admin Role
	 *
	 * PostCondition : Employee will be retired and save the changes in HR Admin Role
	 *
	 * </pre>
	 **/
	@RobotKeyword
	public void performEmployeeRetirement() {
		try {
			this.clickEditEmployeProfile();
			this.selectRetireEmployeeCheckBox();
			this.clickSave();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out
			.println("Exception occured while entering number of deduction periods"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while entering number of deduction periods"
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *
	 * Description   : 'clickSearchButtoninHRAdmin' keyword or method is used to click on the Search button after selecting Categories
	 * in HRAdmin Role
	 *
	 * Role : HRAdmin Role
	 * 
	 * PreCondition  : Employee should be in Employees page in HRAdmin Role.
	 *
	 * PostCondition : Particular Employee will be displayed in HRAdmin Role.
	 * 
	 * <b>Parameters & Example </b> 
	 * | None |
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 */
	
	@RobotKeyword
	public void clickSearchButtoninHRAdmin() {
		try {
			this.clickSearchButton();				
			Thread.sleep(5000);
		} catch (Exception e) {
			throw new CustomException("Exception in navigating to Employees Page."
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 * 
	 * Description  : 'employeeSearchByCategory' keyword or method is used to Search the Employee based on the particular Category.
	 * 
	 * Role : HRAdin Role
	 * 
	 * PreCondition : User should select categories in Employees page in HRAdin Role.  
	 * 
	 * PostCondition: User should navigate to a particular Employee based on category. 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strCategoryName | strCategoryValue |
	 * | Dev  | New Delhi Name |
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "strCategoryName", "strCategoryValue" })
	public void employeeSearchByCategory(String strCategoryName, String strCategoryValue) {
		try{

			strCategoryName=utils.getValue(strCategoryName);
			strCategoryValue=utils.getValue(strCategoryValue);
			String cat="//tr[@class='fieldListRow']//label[text()='"+strCategoryName+"']/../..//select[contains(@name,'category')]/option[text()='"+strCategoryValue+"']";	  		
			performAction.click(cat, "Value from Category Drop down");
			scr.capturePageScreenshot();
		}catch (Exception e) {				
			logger.info("Exception occured while navigating to a particular Employee."
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while navigating to a particular Employee."
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 * 
	 * Description  : 'verifyEndOE' keyword used to verify the End OE link in HR Admin Role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : User should be HR Admin Home Page  
	 * 
	 * PostCondition: User should navigate to Pending Employee Benefits Page
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 **/

	@RobotKeyword	
	public void verifyEndOE() {
		try{
			if(performAction.isElementPresent(oeManagerMsg))
			{
				performAction.verifyMessage("OE Manager");
				//performAction.verify(endOELink, "End OE", "End OE Link");	
				scr.capturePageScreenshot();
			}
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured verify the OE Link in HR Admin Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured verify the OE Link in HR Admin Role"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 * 
	 * Description  : 'performRefuseAllBenefits' keyword used to click on Refuse All Button in HR Admin Role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : User should be HR Admin Home Page  
	 * 
	 * PostCondition: User should navigate to Pending Employee Benefits Page click on Next Button then navigate to Home Page
	 * 
	 * Java file Name : EmployeeHomePage.java
	 * </pre>
	 **/

	@RobotKeyword
	
	public void performRefuseAllBenefits() {
		try{

		performAction.verify(benefitsNotStartedMesg, "Benefits not started", "Benefits Not Started");
		performAction.isElementPresent(participationPeriod);		
		performAction.click(refuseAll, "Refuse All");
		scr.capturePageScreenshot();
		performAction.click(nextForRefuseAll, "Next Button");
		performAction.verify(refuseBenefitsConfirmationMsg, "Refuse Benefits Confirmation", "Refuse Benefits Confirmation");
		performAction.click(saveAndReturnHomeButton, "Save And Return Home");
		scr.capturePageScreenshot();
		performAction.verify(thereAreNoTasksMsg,"There are no tasks.","There are no tasks Message");

		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while clicking on Refuse All Button in HR Admin Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while clicking on Refuse All Button in HR Admin Role"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Description   : 'ViewinHistoryofChanges' keyword or method is used to View The Employee History of Changes
	 * 
	 * Role : HR Role
	 *
	 * PreCondition  : Employee History page should be opened in HR Admin Role
	 *
	 * PostCondition : Employee History of Changes and Close/Pritn the Employee History  in HR Admin Role
	 * 
	 * Java FileName : EmployeeHomePage.java
	 *
	 * </pre>
	 **/
	@RobotKeyword
	public void viewInHistoryOfChangesInHrRole () {
		try {
			performAction.click(viewInHistoryOfChangesLink, "View in History of Changes Link");
			performAction.selectLatestWindow("View History of Changes");
			performAction.click(runButton, "Run Button");
			scr.capturePageScreenshot();
			performAction.waitUntilElementPresent(closeButton);
			performAction.click(closeButton, "Close Button");
			performAction.selectLatestWindow("Employee History");
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while performing action on View in History of Changes "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while performing action on View in History of Changes "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Srilatha A
	 *
	 * Role : HR Role
	 *
	 * Description   :'updateEmployeePayFrequency' keyword or method is used to update the Employee
	 * Pay Frequency in HR Admin Role
	 *
	 * PreCondition  : Should be in edit Employee information Page
	 *
	 * PostCondition : Employee Pay frequency should be updated and saved
	 * 
	 * Java file Name : EmployeeHomePage.java
	 *  
	 * <b>Parameters & Example </b>
	 * 
	 * | strPayFrequency - This arguments allow us to set the updated Pay frequency - This argument allow us to set the pay frequency |
	 * | Semi-Annual, Quaterly |
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "payfrequency" })
	public void updateEmployeePayFrequency(String strPayFrequency) {

		try {
			this.clickEditEmployeProfile();			
			//performAction.waitUntilElementPresent(updateEmployeeEarningsButton);
			this.selectPayFrequency(strPayFrequency);
			
			performAction.waitUntilElementPresent(employeeProfileSaveButton);
			scr.capturePageScreenshot();
			this.clickSaveEmployeProfile();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while updating Pay Frequency"
							+ e.getMessage());
			throw new CustomException(
					"Exception occured while updating Pay Frequency "
							+ e.getMessage());
		}
	}

	/** * <pre> 
	    * Author  : Srikanth G
	   *  
	    * Description : Update Hire date of Employee(Member) in Employee profile
	   * 
	    * PreCondition : Member must be in Employee profile page.
	   * 
	    * PostCondition : Hire date will be updated
	   *	    
	    * Role : MemberRole
	   
	    * Java file Name : EmployeeHomePage.java
	    * 
	    * <b>Parameters & Example </b> 
	    *      
	    * </pre> strHireDate | d:currentdate:y:-1
	    **/
	@RobotKeyword
	@ArgumentNames({ "strHireDate" })
	public void updateHiredateInEmployeeProfile(String strHireDate)
	{
		try {			
		    	 if (strHireDate.startsWith("d:")) {
					 strHireDate = strHireDate.substring(2);
				 }			
			this.clickEditEmployeProfile();						
			this.enterNewHiredate(strHireDate);
			this.clickSaveEmployeProfile();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in updating hire date "
					+ e.getMessage());		
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *
	 * Description   : 'verifyAlertMessage' keyword or method is used to verify the alert message
	 * 
	 * Role : HR / Vista /Member Role
	 *
	 * PreCondition  : Alert message should be displayed 
	 *
	 * PostCondition : click on Ok after verify the alert message
	 * 
	 * Java FileName : EmployeeHomePage.java
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strMesg" })
	public void verifyAlertMessage(String value)
	{
		try {			
			String commands  = new String("Q:\\KB\\HrInTouch\\Data\\"+value+".exe"); //location of the autoit executable
			Thread.sleep(5000);
			Runtime.getRuntime().exec(commands);			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in click on Alert message "
					+ e.getMessage());		
		}
	}	
			
}



