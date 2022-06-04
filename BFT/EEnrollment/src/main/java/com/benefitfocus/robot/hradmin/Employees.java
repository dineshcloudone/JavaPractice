package com.benefitfocus.robot.hradmin;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.Alert;
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

@RobotKeywords
public class Employees {

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected Logging logger;

	By homeLink = By.linkText("Home");
	By employeesLink = By.linkText("Employees");
	By searchType = By.id("searchType");

	By searchCriteria = By.id("searchCriteria");
	By advancedSearchCriteria = By
			.xpath("//div[@class='secondaryRegion']//input[@name='searchCriteria']");
	By goSearchButton = By.xpath("//button[@type='submit']");
	By advancedSearchButton = By.xpath("//b[text()='Search']");
	By advancedSearchType = By.id("searchType");
	By noResultsFound = By.className("dtrException");

	By employeeOverviewpopup = By
			.xpath("//button[contains(text(),'Not right now')]");
	By activeEmployees = By.id("includeActive");
	By retiredEmployees = By.id("includeRetired");
	By terminatedEmployees = By.id("includeTerminated");

	By approveAll = By.linkText("Approve All");
	By clickDropDownToApprove = By
			.xpath("//*[@class='btn btn-default btn-md dropdown-toggle']");
	By clickApproveLink = By.linkText("Approve");

	// Locators Related to COBRA Manager
	By cobraManagerButton = By.linkText("COBRA Manager");
	By cobraParticipantsButton = By.linkText("COBRA Participants");
	By newBenefitEnrolleesButton = By.linkText("New Benefit Enrollees");
	By cobraEligiblePersonsButton = By.linkText("COBRA Eligible Persons");

	// Next Button in Search Results of COBRA Manager
	By nextButton = By.xpath("//img[@alt='Next']");
	By nextButtonByLinkText = By.linkText("Next");

	// Locators related COBRA Manager >> New Benefit Enrollees
	By searchDays = By.xpath("//input[@value='SEARCH_DAYS']");
	By days = By.name("days");
	By searchButton = By.linkText("Search");

	// Locators of Overview tab
	By overview = By.linkText("Overview");
	By editEmployeeProfile = By
			.xpath("//a[contains(@href,'collectPersonalAndWork')]");
	By workPhone = By.id("workPhone");
	By cellPhone = By.id("mobilePhone");
	By pager = By.id("pager");
	By email = By.id("workEmail");
	By clickSaveButton = By.linkText("Save");

	// Locators of military tab
	By military = By.linkText("Military");
	By militaryService = By.id("military");
	By branch = By.id("branch");
	By startDate = By.id("dateStarted");
	By dischargeStatus = By.id("dischargeStatus");
	By endDate = By.id("endDate");
	By veteranStatus = By.id("statusNA");

	// Locators of Direct Deposit Tab
	By directDeposit = By.linkText("Direct deposit");
	By directDepositAccount = By.id("hasAccount");
	By bankName = By.id("bankName");
	By routingNumber = By.id("routingNumber");
	By bankAccountNumber = By.id("accountNumber");
	By accountType = By.id("accountType");
	By entireAmount = By
			.id("//input[contains(@onclick,'clearDepositAmount()')]");
	By fixedAmount = By
			.id("//input[contains(@onclick,'accountForm.depositAmount')]");
	By depositAmount = By.name("depositAmount");

	By verifySSNMask = By.xpath("//div[@id='SSN-wrapper']//input[@id='SSN']");
	String employeeSearchresults = "//table[@id='dataTable-Active']/tbody/tr";
	By SSNSearchResultsPage = By.xpath("//table[@id='dataTable-Active']//tr[2]/td[4]");
	By SSNOverviewPage = By.xpath("//ul[@class='list-inline list-divided']/li");

	//Locators for Advanced Employee Search
	By advanceSearchButton = By.xpath("//a[@class='btn btn-success']/strong");
	By advancedCategoryType = By.xpath("//select[@class='form-control'][@multiple='multiple']");
	By includeActive = By.id("includeActive");
	By includeRetired = By.id("includeRetired");
	By includeTerminated = By.id("includeTerminated");
	By someBenefits = By.id("someBenefits");
	By noBenefits = By.id("noBenefits");
	By needsReview = By.id("needsReview");
	By needsCompletion = By.id("needsCompletion");
	By includePendingCarrierApproval = By.id("includePendingCarrierApproval");
	By includeReviewCarrierChanges = By.id("includeReviewCarrierChanges");
	By needsMemberVerification = By.id("needsMemberVerification");
	By employeesCount = By.xpath("//*[@id='innerLinkactivesTab']/b");
	By enterRetired = By.id("retired");

	//Variables used in public methods    
	String loc = "";
	String tempLoc = "";
	int rowsize;
	String loginId="";
	String bankacctnumber="";
	// Click on Direct Deposit Tab
	private void directDepositTab() {
		performAction.click(directDeposit, "Click on Direct Deposit Link");
	}

	private void enterRetiredDetails() {
		performAction.click(enterRetired, "Retired");
	}

	// Select Direct Deposit Account
	private void selectDirectDeposit(String strDirectDeposit) {
		performAction.select(directDepositAccount, strDirectDeposit,
				"Select Direct Deposit Account");
	}

	// Enter Bank Name
	private void enterBankName(String strBankName) {
		performAction.clearEnter(bankName, strBankName, "Enter Bank Name");
	}

	// Enter Routing Number
	private void enterRoutingNumber(String strRoutingNumber) {
		performAction.clearEnter(routingNumber, strRoutingNumber,
				"Enter RoutingNumber");
	}

	// Enter Bank Account Number
	private void enterBankAccountNumber(String strBankAccountNumber) {
		performAction.clearEnter(bankAccountNumber, strBankAccountNumber,
				"Enter Bank Accout Number");
	}

	// Select Account Type
	private void selectAccountType(String AccountType) {
		performAction.select(accountType, AccountType, "Select Account Type");
	}

	// Select Amount
	private void selectAmount(String strAmount) {
		if (strAmount.equalsIgnoreCase("entire amount")) {
			this.entireAmount();
		} else {
			this.fixedAmount(strAmount);
		}
	}

	// Select Entire Amount
	private void entireAmount() {
		performAction.click(entireAmount, "Select Entire Amount");
	}

	// Enter Fixed Amount
	private void fixedAmount(String strAmount) {
		// performAction.click(fixedAmount, "Select Fixed Amount");
		performAction.clearEnter(depositAmount, strAmount, "Enter Amount");
	}

	/*
	 * private void enterDepositAmount(String strDepositAmount){
	 * performAction.clearEnter(depositAmount, strDepositAmount,
	 * "Enter Deposit Amount"); }
	 */

	// Click on Military Tab
	private void clickMilitaryTab() {
		performAction.click(military, "Click on Military Tab");
	}

	// Select Military service
	private void selectMilitaryService(String strMilitaryService) {
		performAction.select(militaryService, strMilitaryService,
				"Select Miliary service");
	}

	// Select Branch
	private void selectBranch(String strBranch) {
		performAction.select(branch, strBranch, "Select Branch");
	}

	// Enter Start Date
	private void enterStartDate(String strStartDate) {
		if (strStartDate.startsWith("d:")) {
			performAction.clearEnter(startDate, strStartDate,
					"Enter Start Date");
		}

	}

	// Select Discharge Status
	private void selectDischargeStatus(String strDischargeStatus) {
		performAction.select(dischargeStatus, strDischargeStatus,
				"Select Discharge Status");
	}

	// Enter End Date
	private void enterEndDate(String strEndDate) {
		if (strEndDate.startsWith("d:")) {
			performAction.clearEnter(endDate, utils.getValue(strEndDate),
					"Enter End Date");
		}

	}

	// Select Veteran Status
	private void selectVeteranStatus(String strVeteranStatus) {
		performAction.select(veteranStatus, strVeteranStatus,
				"Select Vetran Status");
	}

	// Click on Overview Tab of Employee
	private void clickOverviewTab() {
		performAction.click(overview, "Click on Overview Tab");
	}

	// Click on Edit Employee Profile
	private void clickEditEmployeeProfile() {
		performAction.click(editEmployeeProfile,
				"Click on Edit Employee Profile");
	}

	// Enter Work Phone
	private void enterWorkPhone(String strWorkPhone) {
		performAction.enter(workPhone, strWorkPhone, "Enter Work Phone");
	}

	// Enter Cell Phone
	private void enterCellPhone(String strCellPhone) {
		performAction.enter(cellPhone, strCellPhone, "Enter Cell Phone");
	}

	// Enter Pager information
	private void enterPager(String strPager) {
		performAction.enter(pager, strPager, "Enter Pager");
	}

	// Enter Email
	private void enterEmail(String strEmail) {
		performAction.enter(email, strEmail, "Enter Email");
	}

	// Clear Enter Work Phone
	private void clearEnterWorkPhone(String strWorkPhone) {
		performAction.clearEnter(workPhone, strWorkPhone, "Enter Work Phone");
	}

	// Clear Enter Cell Phone
	private void clearEnterCellPhone(String strCellPhone) {
		performAction.clearEnter(cellPhone, strCellPhone, "Enter Cell Phone");
	}

	// Clear Enter Pager information
	private void clearEnterPager(String strPager) {
		performAction.clearEnter(pager, strPager, "Enter Pager");
	}

	// Clear Enter Email
	private void clearEnterEmail(String strEmail) {
		performAction.clearEnter(email, strEmail, "Enter Email");
	}

	// Click on Save Button
	private void clickSaveButton() {
		performAction.click(clickSaveButton, "Click on Save Button");
	}

	// Click on Home link from HR Admin Role.
	private void goToHomePage() {
		performAction.click(homeLink, "Click on Home Link");
	}

	// Select Search Type Employees Page of Advanced Search
	private void selectSearchType(String strSelectSearchType) {
		performAction.select(searchType, strSelectSearchType,
				"Select Search Type");
	}

	// set Search Criteria
	private void setSearchCriteria(String strSearchCriteria) {
		performAction.enter(searchCriteria, strSearchCriteria,
				"Search Criteria");
	}

	// click on save button
	private void clickGobutton() {
		performAction.jsclick(goSearchButton, "Go Button");
	}

	// set Search Criteria
	private void setAdvancedSearchCriteria(String strSearchCriteria) {
		performAction.enter(advancedSearchCriteria, strSearchCriteria,
				"Advanced Search Criteria");
	}

	// click Active Employees
	private void checkActiveEmployees() {
		performAction.click(activeEmployees, "Include active employees");
	}

	// click Retired Employees
	private void checkRetiredEmployees() {
		performAction.click(retiredEmployees, "Include active employees");
	}

	// click Terminated Employees
	private void checkTerminatedEmployees() {
		performAction.click(terminatedEmployees, "Include active employees");
	}
	// click on save button
	private void clickAdvancedSearchButton() {
		performAction.click(advancedSearchButton, "Advanced Search Button");
	}

	// Click on COBRA Manager Link from Home Page
	private void clickCOBRAManagerLink() {
		performAction.jsclick(cobraManagerButton, "Click on COBRA Manager Link");
	}

	// Verify COBRA Participants Options also BenefitEnrollees
	private void verifyCOBRAManagerOptions(String strMessage) {
		performAction.verifyMessage(strMessage);
	}

	// Click on 'Next' button from COBRA Manager Search Results
	private void clickNextButton() {
		if (performAction.isElementPresent(nextButton, "Next Button")) {
			performAction.click(nextButton, "Click on Next Button");
		} else {
			performAction.click(nextButtonByLinkText, "Click on Next Button");
		}

	}

	// Click on 'New Benefit Enrollees' Link
	private void clickNewBenefitEnrolleesLink() {
		performAction.click(newBenefitEnrolleesButton,
				"Click on New Benefit Enrollees Link");
	}

	// Select by days radio button
	private void clickSelectByDays() {
		performAction.click(searchDays, "Select days");
	}

	// Select number of days from drop down
	private void selectNumberOfDays(String strDays) {
		performAction.select(days, strDays,
				"Select Number of days to search an COBRA Eligible persons");
	}

	// Click on 'Search' button in COBRA Manager >> Employees Page
	private void clickSearchButton() {
		performAction.click(searchButton,
				"Click on Search Button from COBRA Manager >> Employees Page");
	}

	// Verify Last Name from New Benefits Enrollees
	private boolean findNewBenefitsEnrollees(String strLastName) {
		boolean res = false;
		do {
			res = browser.getCurrentWebDriver().getPageSource().toLowerCase()
					.contains(strLastName.toLowerCase());
			logger.info("res===>" + res);
			if (res) {
				return res;
			} else {
				this.clickNextButton();
			}
		} while (performAction.isElementPresent(nextButton, "Next Button"));
		return res;
	}

    /**
     * <pre>
     * Author  : Dilip K
     * 
     * Role : HR Role
     *
     * Description   : 'Add Direct Deposit And Edit' used to add Deposit information to Employee and edit information from 'Direct Deposit' tab
     *
     * PreCondition  : Employee should be created and navigated to Direct deposit tab.
     *
     * PostCondition : It will add direct deposit and edit information.
     *
     * <b>Parameters & Example </b>
     *
     * | JSONTagName      |
     * | newdirectdeposit |
     * 
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> Employees.java </b>
     * 
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"newdirectdeposit", "bankaccountnumber"})
    public void addDirectDepositAndEdit(String newdirectdeposit,
                                        String bankaccountnumber) {
        if (newdirectdeposit.startsWith("td:"))
            newdirectdeposit = newdirectdeposit.substring(3);
        
        if (bankaccountnumber.startsWith("HMV")) {
            bankacctnumber = utils.getValue(bankaccountnumber);
        } else {
            bankacctnumber = bankaccountnumber;
        }
        try {
            browser.getCurrentWebDriver()
                    .findElement(
                            By.xpath("//td[contains(text(),'" + bankacctnumber
                                    + "')]/..//a[text()='Edit']")).click();
            performAction.verifyMessage("Edit Direct Deposit Account");
            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(newdirectdeposit
                    .toLowerCase());

            object = fields.get("bankname");
            if (object != null) {
                this.enterBankName(object.toString());
                browser.hMap.put("bankname", object.toString());
            }
            object = fields.get("bankroutingnumber");
            if (object != null) {
                this.enterRoutingNumber(object.toString());
                browser.hMap.put("bankroutingnumber", object.toString());
            }
            object = fields.get("bankaccountnumber");
            if (object != null) {
                String bankaccntnumber = utils.getValue(object.toString());
                this.enterBankAccountNumber(bankaccntnumber);
                browser.hMap.put("bankaccountnumber", bankaccntnumber);
            }
            object = fields.get("accounttype");
            if (object != null) {
                this.selectAccountType(object.toString());
                browser.hMap.put("accounttype", object.toString());
            }
            object = fields.get("amount");
            if (object != null) {
                this.selectAmount(object.toString());
                browser.hMap.put("amount", object.toString());
            }
            this.clickSaveButton();
            scr.capturePageScreenshot();
            performAction.verify(
                    By.xpath("//td[contains(text(),'"
                            + browser.hMap.get("bankaccountnumber") + "')]"),
                    browser.hMap.get("bankaccountnumber"),
                    "Verify Bank Account Number");
            browser.getCurrentWebDriver()
                    .findElement(
                            By.xpath("//td[contains(text(),'"
                                    + browser.hMap.get("bankaccountnumber")
                                    + "')]/..//a[text()='Edit']")).click();
            performAction.verify(routingNumber,
                    browser.hMap.get("bankroutingnumber"),
                    "Verify New Routing Number");
            performAction.verify(bankAccountNumber,
                    browser.hMap.get("bankaccountnumber"),
                    "Verify New Bank Account Number");
            performAction.verify(depositAmount, browser.hMap.get("amount"),
                    "Verify New Amount");
            this.clickSaveButton();
            scr.capturePageScreenshot();

        } catch (Exception e) {
        	scr.capturePageScreenshot();
        	logger.info("Exception in adding Direct Deposit to Employee"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in adding Direct Deposit to Employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     * 
     * Role : HR Role
     *
     * Description   : 'Add Direct Deposit' used to add Deposit information to Employee from 'Direct Deposit' tab
     *
     * PreCondition  :  Employee should be created and navigated to Direct deposit tab.
     *
     * PostCondition : It will add direct deposit information.
     *
     * <b>Parameters & Example </b>
     *
     * | JSONTagName   |
     * | directdeposit |
     * 
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> Employees.java </b>
     *  
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"directdeposit"})
    public void addDirectDeposit(String directdeposit) {
        if (directdeposit.startsWith("td:"))
            directdeposit = directdeposit.substring(3);

        try {
            this.directDepositTab();
            performAction.verifyMessage("Direct Deposit");
            this.selectDirectDeposit("Yes");
            this.clickNextButton();
            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(directdeposit
                    .toLowerCase());

            object = fields.get("bankname");
            if (object != null) {
                this.enterBankName(object.toString());
            }
            object = fields.get("bankroutingnumber");
            if (object != null) {
                this.enterRoutingNumber(object.toString());
            }
            object = fields.get("bankaccountnumber");
            if (object != null) {
                String bankaccntnumber = utils.getValue(object.toString());
                this.enterBankAccountNumber(bankaccntnumber);
                browser.hMap.put("bankaccountnumber", bankaccntnumber);
            }
            object = fields.get("accounttype");
            if (object != null) {
                this.selectAccountType(object.toString());
            }
            object = fields.get("amount");
            if (object != null) {
                this.selectAmount(object.toString());
            }
            this.clickSaveButton();
            scr.capturePageScreenshot();
            performAction.verify(
                    By.xpath("//td[contains(text(),'"
                            + browser.hMap.get("bankaccountnumber") + "')]"),
                    browser.hMap.get("bankaccountnumber"),
                    "Verify Bank Account Number");
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in adding Direct Deposit to Employee"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in adding Direct Deposit to Employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     * 
     * Role : HR Role
     *
     * Description   : 'Add Military Information' used to add military information of Employee
     *
     * PreCondition  :  Employee should be created and navigated to military information tab.
     *
     * PostCondition : It will add military information.
     *
     * <b>Parameters & Example </b>
     *
     * | JSONTagName   |
     * | militaryinformation |
     * 
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> Employees.java </b>
     * 
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"militaryinformation"})
    public void addMilitaryInformation(String militaryinformation) {
        if (militaryinformation.startsWith("td:"))
            militaryinformation = militaryinformation.substring(3);

        try {
            this.clickMilitaryTab();
            Object object = null;
            JSONObject fields = ReadJsonTestData
                    .getTestData(militaryinformation.toLowerCase());

            object = fields.get("militaryservice");
            if (object != null) {
                this.selectMilitaryService(object.toString());
                browser.hMap.put("militaryservice", object.toString());
            }
            object = fields.get("branch");
            if (object != null) {
                this.selectBranch(object.toString());
                browser.hMap.put("branch", object.toString());
            }
            object = fields.get("startdate");
            if (object != null) {
                this.enterStartDate(object.toString());
                browser.hMap
                        .put("startdate", utils.getValue(object.toString()));
            }
            object = fields.get("dischargestatus");
            if (object != null) {
                this.selectDischargeStatus(object.toString());
                browser.hMap.put("dischargestatus", object.toString());
            }
            object = fields.get("enddate");
            if (object != null) {
                this.enterEndDate(object.toString());
                browser.hMap.put("enddate", utils.getValue(object.toString()));
            }
            object = fields.get("veteranstatus");
            if (object != null) {
                this.selectVeteranStatus(object.toString());
                browser.hMap.put("veteranstatus", object.toString());
            }
            this.clickSaveButton();
            scr.capturePageScreenshot();
            this.clickMilitaryTab();

            performAction
                    .verify(startDate, "HMVstartdate", "Verfiy Start Date");
            performAction.verify(endDate, "HMVenddate", "Verfiy End Date");
            scr.capturePageScreenshot();

        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in adding Military Information to Employee"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in adding Military Information to Employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     * 
     * Role : HR Role
     *
     * Description   : 'Edit Work Information' used to edit work contact information of Employee from Overview Tab
     *
     * PreCondition  : Employee should be created and navigated to Overview tab.
     *
     * PostCondition : It will display updated work information.
     *
     * <b>Parameters & Example </b>
     *
     * | JSONTagName        |
     * | newworkinformation |
     * 
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> Employees.java </b>
     * 
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"newworkinformation"})
    public void editWorkInformation(String newworkinformation) {
        if (newworkinformation.startsWith("td:"))
            newworkinformation = newworkinformation.substring(3);

        try {
            this.clickOverviewTab();
            Alert alert;
            if (performAction.isAlertPresent()) {
                alert = browser.getCurrentWebDriver().switchTo().alert();
                alert.accept();
            } else {
                logger.info("No Alert is Present");
            }
            this.clickEditEmployeeProfile();
            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(newworkinformation
                    .toLowerCase());

            object = fields.get("newphone");
            if (object != null) {
                this.clearEnterWorkPhone(object.toString());
                browser.hMap.put("newphone", object.toString());
            }
            object = fields.get("newcellphone");
            if (object != null) {
                this.clearEnterCellPhone(object.toString());
                browser.hMap.put("newcellphone", object.toString());
            }
            object = fields.get("newpager");
            if (object != null) {
                this.clearEnterPager(object.toString());
                browser.hMap.put("newpager", object.toString());
            }
            object = fields.get("newemail");
            if (object != null) {
                this.clearEnterEmail(object.toString());
                browser.hMap.put("newemail", object.toString());
            }
            this.clickSaveButton();
            scr.capturePageScreenshot();

            this.clickOverviewTab();
            this.clickEditEmployeeProfile();
            performAction.verify(workPhone, "HMVnewphone", "Verfiy Work Phone");
            performAction.verify(cellPhone, "HMVnewcellphone",
                    "Verfiy Cell Phone");
            performAction.verify(pager, "HMVnewpager", "Verfiy Pager");
            performAction.verify(email, "HMVnewemail", "Verfiy Email");
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in adding Work Information to Employee"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in adding Work Information to Employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     * 
     * Role : HR Role
     *
     * Description   : 'Add Work Information' used to add work contact information for Employee from Overview Tab
     *
     * PreCondition  : Employee should be created and navigated to Overview tab.
     *
     * PostCondition : It will display updated work information.
     *
     * <b>Parameters & Example </b>
     *
     * | JSONTagName        |
     * | newworkinformation |
     * 
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> Employees.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"workinformation"})
    public void addWorkInformation(String workinformation) {
        if (workinformation.startsWith("td:"))
            workinformation = workinformation.substring(3);

        try {
            this.clickOverviewTab();
            Alert alert;
            if (performAction.isAlertPresent()) {
                alert = browser.getCurrentWebDriver().switchTo().alert();
                alert.accept();
            } else {
                logger.info("No Alert is Present");
            }
            this.clickEditEmployeeProfile();
            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(workinformation
                    .toLowerCase());

            object = fields.get("phone");
            if (object != null) {
                this.enterWorkPhone(object.toString());
                browser.hMap.put("phone", object.toString());
            }
            object = fields.get("cellphone");
            if (object != null) {
                this.enterCellPhone(object.toString());
                browser.hMap.put("cellphone", object.toString());
            }
            object = fields.get("pager");
            if (object != null) {
                this.enterPager(object.toString());
                browser.hMap.put("pager", object.toString());
            }
            object = fields.get("email");
            if (object != null) {
                this.enterEmail(object.toString());
                browser.hMap.put("email", object.toString());
            }

            object =fields.get("retired");
            if(object!=null){
            	this.enterRetiredDetails();
            	browser.hMap.put("retired", object.toString());
            }
            this.clickSaveButton();
            scr.capturePageScreenshot();
            this.clickOverviewTab();
            this.clickEditEmployeeProfile();
            performAction.verify(workPhone, "HMVphone", "Verfiy Work Phone");
            performAction
                    .verify(cellPhone, "HMVcellphone", "Verfiy Cell Phone");
            performAction.verify(pager, "HMVpager", "Verfiy Pager");
            performAction.verify(email, "HMVemail", "Verfiy Email");
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in adding Work Information to Employee"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in adding Work Information to Employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     * 
     * Role : HR Role
     *
     * Description   : 'Search New COBRA Benefit Enrollees In HR Role' used to search newly enrolled employees in COBRA Policy from COBRA Manager in HR Role
     *
     * PreCondition  : Employee should be created and Terminated,Manage COBRA Option should be available.
     *
     * PostCondition : It will display newly enrolled members for COBRA Benefits.
     *
     * <b>Parameters & Example </b>	 *
     * | strLastName - Last Name of Employee | strDays - Persons with COBRA eligible benefits accepted within days |
     * | HMVlastname | 1 |
     * 
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> Employees.java </b>
     * 
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strLastName", "strDays"})
    public void searchNewCOBRABenefitEnrolleesInHRRole(String strLastName, String strDays) {
        if (strLastName.startsWith("HMV")) {
            strLastName = utils.getValue(strLastName);
        }
        try {
            this.goToHomePage();
            this.clickCOBRAManagerLink();
            this.verifyCOBRAManagerOptions("New Benefit Enrollees");
            this.clickNewBenefitEnrolleesLink();
            this.clickSelectByDays();
            this.selectNumberOfDays(strDays);
            this.clickSearchButton();
            if (this.findNewBenefitsEnrollees(strLastName)) {
                logger.info("Employee with Last Name ==> "+strLastName + " Found");
            } else {
                logger.info(strLastName
                        + " Not Found in New Benefit Enrollees");
            }
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in performing New COBRA Benefit Enrollees Search"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in performing New COBRA Benefit Enrollees Search"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     * 
     * Role : HR Role
     *
     * Description   : 'performAdvancedEmployeeSearch' keyword used to perform Advanced Employee Search from Employees Tab In HR Admin Role includes Active members.
     *
     * PreCondition  : Employee should be created and active.
     *
     * PostCondition : Will return search Results else display warning message if no results found.
     *
     * <b>Parameters & Example </b>
     *
     * | Search Type - Select Type of Search from Drop down | Search String - Value to be entered in search criteria |
     * | Name or SSN,Login ID,Member ID,Alternate ID        | hradminauto1                                           |
     * 
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> Employees.java </b>
     * 
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strSearchType", "strSearchString","strIncludeEmployees"})
    public void performAdvancedEmployeeSearch(String strSearchType,
                                              String strSearchString,String strIncludeEmployees) {
        try {
            if (performAction.isAlertPresent()) {
                performAction.acceptAlert();
            }
            this.selectSearchType(strSearchType);

            // Enter Search String
            this.setAdvancedSearchCriteria(strSearchString);


            if(strIncludeEmployees.equalsIgnoreCase("none")){

            }else if (strIncludeEmployees.equalsIgnoreCase("All")) {
				this.checkActiveEmployees();
				this.checkRetiredEmployees();
				this.checkTerminatedEmployees();
			}
            else{
            //if loop created for clear the previous checkbox andselect the checkbox for empoyee search
            	String value=browser.getCurrentWebDriver().findElement(By.xpath("//label[text()='"+strIncludeEmployees+"']//preceding::input[1]")).getAttribute("checked");
            	if(value!=null){
            		performAction.click(By.xpath("//label[text()='"+strIncludeEmployees+"']//preceding::input[1]"), "Click on Include Employees Check box");
            	}
            	performAction.click(By.xpath("//label[text()='"+strIncludeEmployees+"']//preceding::input[1]"), "Click on Include Employees Check box");
            }

            this.clickAdvancedSearchButton();

            utils.sleep(3000);

            if (performAction.isElementPresent(employeeOverviewpopup,
                    "Employee Overview Pop UP")) {
                performAction.click(employeeOverviewpopup,
                        "Employee Overview Pop UP");
            }

            // Verifying the Search Results of an Employee
            if (performAction.isElementPresent(noResultsFound)) {
                logger.info(""
                        + browser.getCurrentWebDriver()
                        .findElement(noResultsFound).getText());
            } else {
                String employee = browser
                        .getCurrentWebDriver()
                        .findElement(By.xpath("//div[@class='media']/div[2]/p"))
                        .getText();
                logger.info("Employee with Login ID = " + employee
                        + " Found ");
            }
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in performing the employee search "
                    + e.getMessage());
            throw new CustomException(
                    "Exception in performing the employee search "
                            + e.getMessage());
        }
    }

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *
	 * Description   : keyword used to perform the Employee Search from HR Admin Role
	 *
	 * PreCondition  : HR admin should be logged in and home page is shown
	 *
	 * PostCondition : Will return search Results else display warning message if no results found.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strSearchString - last name of the employee to search |
	 * | AutoTest |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strSearchString"})
	public void performSimpleEmployeeSearch(String strSearchString) {
		try {
			// get the value from hashmap if available
			strSearchString = utils.getValue(strSearchString);

			this.setSearchCriteria(strSearchString);
			this.clickGobutton();
			utils.sleep(3000);

			if (performAction.isElementPresent(employeeOverviewpopup,
					"Employee Overview Pop UP")) {
				performAction.click(employeeOverviewpopup,
						"Employee Overview Pop UP");
			}

			if (performAction.isElementPresent(noResultsFound)) {
				logger.info(""
						+ browser.getCurrentWebDriver()
						.findElement(noResultsFound).getText());
			} else {
				performAction.verifyMessage(strSearchString);
				logger.info("Employee with " + strSearchString + " Found ");
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the employee search "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *
	 * Description   : used to perform the advanced Employee Search from HR Admin Role includes Active members
	 *
	 * PreCondition  : HR admin should be logged in and Employees page is shown to perform advanced search
	 *
	 * PostCondition : Will return search Results else display warning message if no results found.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strSearchString - last name of the employee to search |
	 * | AutoTest |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strSearchString"})
	public void performActiveEmployeeSearch(String strSearchString) {
		try {

			// get the value from hashmap if available
			strSearchString = utils.getValue(strSearchString);

			this.setAdvancedSearchCriteria(strSearchString);
			this.checkActiveEmployees();
			this.clickAdvancedSearchButton();
			utils.sleep(3000);

			if (performAction.isElementPresent(employeeOverviewpopup,
					"Employee Overview Pop UP")) {
				performAction.click(employeeOverviewpopup,
						"Employee Overview Pop UP");
			}

			if (performAction.isElementPresent(noResultsFound)) {
				logger.info(""
						+ browser.getCurrentWebDriver()
						.findElement(noResultsFound).getText());
			} else {
				String employee = browser
						.getCurrentWebDriver()
						.findElement(By.xpath("//div[@class='media']/div[2]/p"))
						.getText();
				logger.info("Employee with Login ID" + employee
						+ " Found ");
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the employee search "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *
	 * Description   : keyword used to approve the Employee benefits from HR Admin Role
	 *
	 * PreCondition  : click on approve all benefits button on the employee home page.
	 *
	 * PostCondition : approves all pending tasks
	 *
	 * <b>Parameters & Example </b>
	 * NA
	 * </pre>
	 **/
	@RobotKeyword
	public void approveAllBenefits() {
		try {

			utils.sleep(2000);
			performAction.click(approveAll, "ApproveAllButton");

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in approving the Employeebenefits "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : CH Phani Srikar
	 *
	 * Description   : Keyword used to approve the Employee Benefits from HR Admin Role after editing the employee
	 * details which are initially approved
	 *
	 * PreCondition  : click on approve all benefits button on the employee home page.
	 *
	 * PostCondition : approves all pending tasks
	 *
	 * <b>Parameters & Example </b>
	 * NA
	 * </pre>
	 **/
	@RobotKeyword
	public void approveAllBenefitsAfterEmployeeEdit() {
		try {

			utils.sleep(2000);
			performAction.click(clickDropDownToApprove,
					"DropDown to Click Approve All button");
			performAction.click(clickApproveLink, "ApproveAllButton");

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in approving the Employeebenefits after employee edit "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description   : Keyword used to perform the actions like Terminate Employee/ Employee Detail Report 
	 * using actions button on employee search results page in HR Admin Role
	 *
	 * Role : HR Admin Role
	 * 
	 * PreCondition  : Employee Search results page in HR Admin
	 * 
	 * PostCondition : Specific action performed for the employee 
	 *  
	 * <b>Parameters & Example </b>
	 * | strSearchLoginId - last name of the employee to search |
	 * | value - Employee Detailed Report  or any other action for the employee |
	 * 
	 * Java file Name :  Employees.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strSearchLoginId", "Action"})
	public void performEmployeeActions(String strSearchLoginId, String value) {
		try {
			rowsize = browser.getCurrentWebDriver()
					.findElements(By.xpath(employeeSearchresults)).size();            
			logger.info("Search Login ID " + strSearchLoginId);
			logger.info("Action value " + value);
			utils.sleep(2000);
			for (int i = 3; i < rowsize; i++) {
				loc = employeeSearchresults + "[" + i + "]/td[5]";
				loginId = browser.getCurrentWebDriver()
						.findElement(By.xpath(loc)).getText();
				logger.info("Login Id " + loginId);
				if (strSearchLoginId.equalsIgnoreCase(loginId)) {
					tempLoc = loc;
					loc = loc + "/..//table/tbody/tr[2]/td[2]/a";
					By actions = By.xpath(loc);
					performAction.mouseOver(actions, "Mouse over on Actions");
					loc = tempLoc + "/..//a[text()='" + value + "']";
					By action = By.xpath(loc);
					performAction.click(action, "click on Action");
					break;
				}
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the employee search "
							+ e.getMessage());
		}
	}

	private void verifySSNInMaskByLocator(By locator) {
		try {
			verifySSNMask = locator;
			String ssn = browser.getCurrentWebDriver()
					.findElement(verifySSNMask).getText();
			logger.info("SSN Value in mask " + ssn);
			if (ssn.contains("***_**_****")) {
				logger.info("SSN " + ssn + " is displayed in Full Mask");
				scr.capturePageScreenshot();
			}

		} catch (Exception e) {
			logger.warn("Exception occured verifying SSN masked. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured verifying SSN masked. " + e.getMessage());
		}
	}

	private void verifySSNInPartialMaskByLocator(By locator) {
		try {

			verifySSNMask = locator;
			String ssn = browser.getCurrentWebDriver()
					.findElement(verifySSNMask).getText();
			logger.info("SSN Value Partial " + ssn);
			String[] ssnValues = ssn.split("-");
			logger.info(ssnValues[0] + "  " + ssnValues[1] + " "
					+ ssnValues[2]);
			if (ssnValues[0].contains("***") && (ssnValues[2].substring(0, 1).matches("\\d"))) {
				logger.info("SSN " + ssn
						+ " is displayed in Partial Mask");
				scr.capturePageScreenshot();
			}

		} catch (Exception e) {
			logger.warn("Exception occured verifying SSN Partial masked. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured verifying SSN Partial masked. "
							+ e.getMessage());
		}
	}

	private void verifySSNInNoMaskByLocator(By locator) {
		try {
			verifySSNMask = locator;

			String ssn = browser.getCurrentWebDriver()
					.findElement(verifySSNMask).getText();
			logger.info("SSN Value with out mask " + ssn);
			String[] ssnValues = ssn.split("-");
			logger.info(ssnValues[0] + "  " + ssnValues[1] + " "
					+ ssnValues[2]);
			if (ssnValues[0].substring(0, 1).matches("\\d")
					&& (ssnValues[2].substring(0, 1).matches("\\d"))) {
				logger.info("SSN " + ssn + " is displayed in No Mask");
				scr.capturePageScreenshot();
			}

		} catch (Exception e) {
			logger.warn("Exception occured verifying SSN When No mask selected. "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured verifying When No mask selected. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : VerifySSNMaskInOverviewPage keyword or method is used to verify the SSN Mask Type in Overview Page 
	 * 
	 * Role : HR Admin Role
	 * 
	 * Java file Name :  Employees.java
	 * 
	 * PreCondition : Add New Employee page in HR Admin Role
	 * 
	 * PostCondition : New employee is saved successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strMaskType |  
	 * | strMaskType - is used to give the SSN Mask Type for HR Admin Role |
	 * 
	 * Java file Name :  Employees.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strMaskType "})
	public void VerifySSNMaskInOverviewPage(String strMaskType) {

		if (strMaskType.toLowerCase().equalsIgnoreCase("full mask ")) {
			this.verifySSNInMaskByLocator(SSNOverviewPage);
		} else if (strMaskType.toLowerCase().equalsIgnoreCase("no mask")) {
			this.verifySSNInNoMaskByLocator(SSNOverviewPage);
		} else if (strMaskType.toLowerCase().equalsIgnoreCase("partial mask")) {
			this.verifySSNInPartialMaskByLocator(SSNOverviewPage);
		}

	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *
	 * Description : VerifySSNMaskInSearchResultsPage keyword or method is used to verify the SSN Mask Type in Search results page
	 *
	 * Role : HR Admin Role
	 *
	 * PreCondition : Add New Employee page in HR Admin Role
	 *
	 * PostCondition : New employee is saved successfully.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * |  strMaskType |
	 * | strMaskType - is used to give the SSN Mask Type for HR Admin Role |
	 * 	
	 * Java file Name :  Employees.java 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strMaskType "})
	public void VerifySSNMaskInSearchResultsPage(String strMaskType) {
		if (strMaskType.toLowerCase().equalsIgnoreCase("full mask ")) {
			this.verifySSNInMaskByLocator(SSNSearchResultsPage);
		} else if (strMaskType.toLowerCase().equalsIgnoreCase("no mask")) {
			this.verifySSNInNoMaskByLocator(SSNSearchResultsPage);
		} else if (strMaskType.toLowerCase().equalsIgnoreCase("partial mask")) {
			this.verifySSNInPartialMaskByLocator(SSNSearchResultsPage);
		}

	}

	
	/**
	 * <pre>
	 * Author  : Teja
	 * source : hradmin/PayrollEmployees
	 *
	 * Description   : keyword used to approve the Employee tasks from HR Admin Role
	 *
	 * PreCondition  : clicks on approve taks button on the employee home page.
	 *
	 * PostCondition : approves all pending tasks
	 *
	 * <b>Parameters & Example </b>
	 * NA
	 * </pre>
	 **/
	@RobotKeyword
	public void approveFromHRAdmin() {
		try {

			utils.sleep(2000);
			if (performAction.isElementPresent(approveAll)) {
				performAction.highlightElement(approveAll);

				performAction.click(approveAll, "ApproveAllButton");
			} else if (performAction.isElementPresent(clickDropDownToApprove)) {
				utils.sleep(3000);
				performAction.highlightElement(clickDropDownToApprove);
				utils.sleep(3000);
				performAction.jsclick(clickDropDownToApprove,
						"DropDown to Click Approve All button");

				performAction.click(clickApproveLink, "Approve Link");

			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception while approving the member related tasks from HR-Admin"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * 
	 * Author  : Sunitha Yerra
	 * 
	 * Role:  HR Role
	 * 
	 * Description: Keyword or method 'performAdvanceEmployeeSearch' used to perform the Advanced Employee Search based on any criteria mentioned in the page from HR Admin Role
	 * 
	 * PreCondition  :  Should be in Employees page.
	 * 
	 * PostCondition :  Will return search Results else display warning message if no results found.
	 * 
	 * <b>Parameters :</b>
	 * | strCriteriaSearchString - Specific employee name or SSN or giving 'All' would fetch all the employee who meet the criteria
	 * | strSearchString - search criteria (field name) on which search need to be performed eg: Name or SSN or Categories or Terminated etc.   |
	 * | strValue   - String type argument which holds the value of the search criteria
	 * 
	 * Java File Name : Employees.java
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strCriteriaSearchString", "strSearchString", "strValue"})
	public void performAdvanceEmployeeSearch(String strCriteriaSearchString, String strSearchString, String strValue) {
		try {
			if(strCriteriaSearchString.equalsIgnoreCase("All"))
				this.setAdvancedSearchCriteria(",");// indicates all employees
			else
				this.setAdvancedSearchCriteria(strCriteriaSearchString);
			String searchString = strSearchString.trim().toLowerCase();
			if (searchString.equalsIgnoreCase("Name or SSN") || strSearchString.equalsIgnoreCase("Login ID") || strSearchString.equalsIgnoreCase("Employer assigned ID") ||
					strSearchString.equalsIgnoreCase("Member ID") || strSearchString.equalsIgnoreCase("Alternate ID") || strSearchString.equalsIgnoreCase("Employee OID")) {
				performAction.select(searchType, strSearchString, strSearchString);
				performAction.enter(searchCriteria, strValue, strSearchString);

			} else if (strSearchString.equalsIgnoreCase("Categories")) {
				int rowsize = browser.getCurrentWebDriver().findElements(advancedCategoryType).size();
				List<WebElement> categoryList = browser.getCurrentWebDriver().findElements(advancedCategoryType);

				if (rowsize > 0) {
					for (WebElement element : categoryList) {
						Select categorySelect = new Select(element);
						List<WebElement> optionsList = categorySelect.getOptions();
						for (WebElement option : optionsList) {
							if (option.getText().equalsIgnoreCase(strValue))
								option.click();
						}
					}
				}
			} else {
				if (searchString.equalsIgnoreCase("terminated"))
					performAction.click(includeTerminated, strSearchString);

				else if (searchString.equalsIgnoreCase("active"))
					performAction.click(includeActive, strSearchString);

				else if (searchString.equalsIgnoreCase("retired"))
					performAction.click(includeRetired, strSearchString);

				else if (searchString.trim().equalsIgnoreCase("one or more accepted benefits"))
					performAction.click(someBenefits, strSearchString);

				else if (searchString.trim().equalsIgnoreCase("no benefits"))
					performAction.click(noBenefits, strSearchString);

				else if (searchString.equalsIgnoreCase("sections that need to be approved"))
					performAction.click(needsReview, strSearchString);

				else if (searchString.equalsIgnoreCase("sections that need to be completed"))
					performAction.click(needsCompletion, strSearchString);

				else if (searchString.equalsIgnoreCase("sections pending carrier approval"))
					performAction.click(includePendingCarrierApproval, strSearchString);

				else if (searchString.equalsIgnoreCase("carrier changes that need to be reviewed"))
					performAction.click(includeReviewCarrierChanges, strSearchString);

				else if (searchString.equalsIgnoreCase("sections that need to be reviewed by employee"))
					performAction.click(needsMemberVerification, strSearchString);

			}
			scr.capturePageScreenshot();
			performAction.click(advanceSearchButton, "Employee Advanced Seacrh"); //Click on search button


		} catch (Exception e) {
			logger.info("Exception occured in performing the employee advanced search " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the employee advanced search "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Rajeswari Nimmala
	 *
	 * Description : 'verifyNoOfEmployees' keyword used to verify no of active employees
	 *
	 * Role : HR ROle
	 *
	 * PreCondition : User should be in Employees  Page
	 *
	 * PostCondition : Results Search Page is displayed
	 *
	 * <pre>
	 * <b>Parameters :</b>
	 * | noOfEmployeesPresent -Enter number of employees to verify , employeesType -  employees type allowed are actives ,terminated ,dependents |
	 * 
	 * Java file Name :  Employees.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"employeesType", "noOfEmployeesPresent"})
	public void verifyNoOfEmployees(String employeesType, String noOfEmployeesPresent) {
		try {

			String empType=employeesType.toLowerCase();
			By loc=By.xpath("//a[contains(text(),'"+employeesType+"')]");
			if(empType.equalsIgnoreCase("Active")){
				By employeesCount = By.xpath("//*[@id='innerLink"+empType+"sTab']/b");
			}else {
				By employeesCount = By.xpath("//*[@id='innerLink"+empType+"Tab']/b");
			}
			
			String noOfEmployees = browser.getCurrentWebDriver().findElement(employeesCount).getText();
			String employeeType = browser.getCurrentWebDriver().findElement(loc).getText();

			if (!noOfEmployees.equals(noOfEmployeesPresent)) {

				logger.info("Verifying that " + noOfEmployees + "" +employeeType+ "exits");
				scr.capturePageScreenshot();

			} else {
				scr.capturePageScreenshot();
				throw new RuntimeException(noOfEmployeesPresent + "Employess found");

			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured " + e.getMessage());
			throw new CustomException("Exception in verifying no of active employees "
					+ e.getMessage());
		}
	}
}
