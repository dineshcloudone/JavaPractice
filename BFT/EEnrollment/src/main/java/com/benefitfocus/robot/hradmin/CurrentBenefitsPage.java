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
public class CurrentBenefitsPage {

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

    By editBox = By.id("reasonEntry");
    //By nextButton = By.linkText("Next");
    By nextButton = By.xpath("//strong[text()='Next']");
    By nextButtonFromMember=By.xpath("//button[text()='Next ']");
    By startButton = By.linkText("Start");
    By applyCoverage = By.linkText("Apply Coverage");
    By cancelCoverage = By.linkText("Cancel Coverage");
    By saveButton = By.linkText("Save");
    By waiveCoverage = By.linkText("Waive Coverage");
    By employeeOverviewpopup = By
            .xpath("//button[contains(text(),'Not right now')]");
    By applyCoverageLevelPage = By
            .xpath("//button[contains(text(),'Persons Covered')]");
    String currentBenefitsList = "//div[@id='preFurlBenefitsDisplay']";
    String coverageAmountList = "//table[@id='selectedTierKey']";
    String planList = "//table[@id='selectedPlanKey']";
    String coverageLevelList = "//table[@id='selectedTierKey']";
    String benefitEligibilityList = "//div[@class='phl benefit-type']";
    By cancelBenefitForAll = By.xpath("//a[text()='Cancel Benefits for All']");
    By verifyCoverage = By.xpath("//div[contains(text(),'Employee')]");
    By editCoverage = By
            .xpath("//span[text()='Coverage Level']/../..//a[text()='Edit']");
    By employeeCoverage = By.xpath("//label[text()='Employee Only']");
    By addDependent = By.xpath("//span[text()='Add new dependent']");
    By editCoverageAmountButton = By.linkText("Edit");

    // Locators for adding benefit to Employee
    By continueWithSuggestedAddress = By
            .linkText("Continue with suggested address");
    By other = By.id("other");
    By clickContinueButton = By.linkText("Continue");
    By eventChoice = By.name("eventChoice");
    By newEventType = By.name("newEventType");
    By refuseAll = By.id("cancelOnly");
    By effectiveDate = By.name("overallEffectiveDate");

    // Locators for Premium Deduction page
    By preTax = By.id("preTaxChoice[PRETAX]");
    By postTax = By.id("preTaxChoice[POSTTAX]");

    By medicareTrue = By.id("hasMedicare[YES]");
    By medicareFalse = By.id("hasMedicare[NO]");

    By additionInsuranceYes = By
            .id("typeOfPolicyUserHasRequestedToCreate[YES_DETAILS]");
    By additionInsuranceYesNoDetails = By
            .id("typeOfPolicyUserHasRequestedToCreate[YES_NO_DETAILS]");
    By additionInsuranceNo = By.id("typeOfPolicyUserHasRequestedToCreate[NO]");

    // Loactors for Medicare in HR Role
    By usersCovered = By.name("policyHolderCacheKey-nativeHtmlElement");
    By hasRequiredDetails = By.id("hasPoliciesQuestion[YES_DETAILS]");
    By hasNoRequiredDetails = By.id("hasPoliciesQuestion[YES_NO_DETAILS]");
    By medicareNumber = By.id("hicNumber");
    By policyHolder = By.id("policyHolderCacheKey");
    By eligibleReasons = By.id("reasons");
    By partA = By.id("isPartA-0");
    By partB = By.id("isPartB-0");
    By partAEligibilityDate = By.id("partAEligDate");
    By partBEligibilityDate = By.id("partBEligDate");
    By partAEffectiveDate = By.id("partAEffDate");
    By partBEffectiveDate = By.id("partBEffDate");
    By partAEndDate = By.id("partAExpDate");
    By partBEndDate = By.id("partBExpDate");
    By addButton = By.linkText("Add");
    // Locators for Additional Insurance Details information in HR Role.
    By policyNumber = By.id("policyNumber");
    By policyHolderAddtionalInsurance = By.id("policyHolderKey");
    By carrierName = By.id("carrierName");
    By coverageInformation = By.id("coveredPersonPolicyActive0");
    By coverageEffectiveDate = By.id("coveredPersonEffDate0");
    By goToSummaryLink = By.linkText("Go to Summary");
    By healthScreeningTrue = By.id("answer[true]");
    By healthScreeningFalse = By.id("answer[false]");
    By contributionAmount = By.id("contributionAmount");
    By FSADeductionPeriods = By.id("newPeriodsRemaining");
    // Locators for Reason for Change
    By reasonForChange = By.xpath("//div/span[text()='Reason for Change']");
    By reason = By.xpath("//div/span[text()='Reason for Change']/../..//div[@class='inactiveStepSummary']");
    By saveInSnapshotPage = By.xpath("(//*[@class='btn btn-success'])[1]");
    By adminOrBrokerApproval = By.xpath("//div[@id='preFurlBenefitsDisplay']/table[1]//a[contains(text(),'Admin or Broker Approval')]");
    By currentBenefitsListLocator = By.xpath("//div[@id='preFurlBenefitsDisplay']");
    By state = By.id("carrierState");
    By country = By.id("carrierCountry");
    By nextButtonLink=By.linkText("Next");
    
    //Global Varibale
    String value = "";
    
    /********************
     * Setter Methods for Add Employee Plan Selection
     *********************/

    // Click on Continue Button
    private void clickContinueButton() {
        performAction.click(clickContinueButton, "Click on Continue Button");
    }

    // Click on Event Choice radio button
    private void clickEventChoicebutton() {
        performAction.click(eventChoice, "Click Event Choice Radi Button");
    }

    // Select New Event Type
    private void selectNewEventType(String strNewEventType) {
        performAction.select(newEventType, strNewEventType,
                "Select New Event Type");
    }

    // Click Refuse All radio button
    private void clickRefuseAllbutton() {
        performAction.click(refuseAll, "Refuse All Radio Button");
    }

    // Select other option from "Reason for Medical Change" page
    private void selectOtherOption() {
        performAction.click(other, "Select Other Option");
    }

    // Click on Next Button
    private void clickNextButton() {
    	if(performAction.isElementPresent(nextButton)){
    		performAction.jsclick(nextButton, "Click on Next Button");
    	}else if(performAction.isElementPresent(nextButtonFromMember)){
    		performAction.jsclick(nextButtonFromMember, "Click on Next Button");
    	}else{
    		performAction.jsclick(nextButtonLink, "Click on Next Button Link");
    	}
        
    }

    // Enter Effective Date
    private void effectiveDate(String strenterEffectiveDate) {
        if (strenterEffectiveDate.startsWith("d:")) {
            strenterEffectiveDate = strenterEffectiveDate.substring(2);
        }
        performAction.clearEnter(effectiveDate,
                utils.getDate(strenterEffectiveDate), "Enter Effective Date");
    }

    // Select Users covered
    private void selectUsersCovered() {
        performAction.click(usersCovered, "Select Users covered");
    }

    // Select 'Yes' or 'No' option against medicare required details
    private void selectHasRequiredDetails(String strHasRequiredDetails) {
        if (strHasRequiredDetails.equalsIgnoreCase("yes")) {
            performAction.click(hasRequiredDetails, "Select Users Has Required Details");
        } else {
            performAction.click(hasNoRequiredDetails, "Select Users Has No Required Details");
        }
    }

    // Enter Medicare Number
    private void enterMedicareNumber(String strMedicareNumber) {
        performAction.clearEnter(medicareNumber, strMedicareNumber, "Enter Medicare Number");
    }

    // Select Policy Holder
    private void selectPolicyHolder() {
        performAction.select(policyHolder, "index=1", "Select Policy Holder");
    }

    // Select Eligible Persons
    private void selectEligibleReasons(String strEligibleReasons) {
        performAction.select(eligibleReasons, strEligibleReasons, "Select eLigible Reasons");
    }

    // Select Yes against Part A
    private void setPartA() {
        performAction.click(partA, "Click Yes against Part A");
    }

    // Select Yes against Part B
    private void setPartB() {
        performAction.click(partB, "Click Yes against Part B");
    }

    // Enter Part A Eligibility Date
    private void enterPartAEligibilityDate(String strPartAEligibilityDate) {
        if (strPartAEligibilityDate.startsWith("d:")) {
            strPartAEligibilityDate = strPartAEligibilityDate.substring(2);
        }
        performAction.enter(partAEligibilityDate, utils.getDate(strPartAEligibilityDate), "Enter Part A Eligibility Date");
    }

    // Enter Part A Effective Date
    private void enterPartAEffectiveDate(String strPartAEffectiveDate) {
        if (strPartAEffectiveDate.startsWith("d:")) {
            strPartAEffectiveDate = strPartAEffectiveDate.substring(2);
        }
        performAction.enter(partAEffectiveDate, utils.getDate(strPartAEffectiveDate), "Enter Part A Effective Date");
    }

    // Enter Part A End Date
    private void enterPartAEndDate(String strPartAEndDate) {
        if (strPartAEndDate.startsWith("d:")) {
            strPartAEndDate = strPartAEndDate.substring(2);
        }
        performAction.enter(partAEndDate, utils.getDate(strPartAEndDate), "Enter Part A End Date");
    }

    // Enter Part B Eligibility Date
    private void enterPartBEligibilityDate(String strPartBEligibilityDate) {
        if (strPartBEligibilityDate.startsWith("d:")) {
            strPartBEligibilityDate = strPartBEligibilityDate.substring(2);
        }
        performAction.enter(partBEligibilityDate, utils.getDate(strPartBEligibilityDate), "Enter Part B Eligibility Date");
    }

    // Enter Part B Effective Date
    private void enterPartBEffectiveDate(String strPartBEffectiveDate) {
        if (strPartBEffectiveDate.startsWith("d:")) {
            strPartBEffectiveDate = strPartBEffectiveDate.substring(2);
        }
        performAction.enter(partBEffectiveDate, utils.getDate(strPartBEffectiveDate), "Enter Part B Effective Date");
    }

    // Click on Add Button
    private void clickAddButton() {
        performAction.click(addButton, "Click Add Button");
    }

    /***************
     * Private Methods for Additonal Insurance Details Page
     ******************/
    // Enter Policy Number in Additional Insurance details page
    private void enterPolicyNumber(String strPolicyNumber) {
        performAction.enter(policyNumber, strPolicyNumber, "Enter policy Number in Aditional Insurance");
    }

    // Select Policy Holder
    private void selectPolicyHolderAddtionalInsurance(String strPolicyHolderAddtionalInsurance) {
        performAction.select(policyHolderAddtionalInsurance, strPolicyHolderAddtionalInsurance, "Enter Additonal Insurance");
    }

    // Enter Carrier Name
    private void enterCarrierName(String strCarrierName) {
        performAction.enter(carrierName, strCarrierName, "Enter Carrier Name");
    }

    // Select Coverage Information
    private void selectCoverageInformation(String strCoverageInformation) {
        performAction.select(coverageInformation, strCoverageInformation, "Select Coverage Information");
    }

    // Enter Coverage Effective Date
    private void enterCoverageEffectiveDate(String strCoverageEffectiveDate) {
        if (strCoverageEffectiveDate.startsWith("d:")) {
            strCoverageEffectiveDate = strCoverageEffectiveDate.substring(2);
        }
        performAction.enter(coverageEffectiveDate, strCoverageEffectiveDate, "Enter Coverage Effective Date");
    }

    // Click on Go To Summary Page
    private void clickGoToSummaryPage() {
        performAction.click(goToSummaryLink, "Click on Got To Summary Button");
    }

    // Click on Save Button
    private void clickSaveButton() {
        performAction.click(saveButton, "Click on Save Button");
    }

    //verifySaveBenefitDeatilsInHrRole
    private void verifySaveBenefitDeatilsInHrRole(String strplanName, String strtypeOfthePlan, String strexpectedValue) {

        String object = "//h2[text()='" + strplanName + "']//following::span[text()='" + strtypeOfthePlan + "']/following::div[2]";
        if (strexpectedValue.startsWith("d:")) {
            value = utils.getValue(strexpectedValue);
            System.out.println("Entered value is : " + value);
        } else {
            value = strexpectedValue;
            System.out.println("Entered value is : " + value);
        }
        if (value.trim().equals(browser.getCurrentWebDriver().findElement(By.xpath(object)).getText().trim())) {
            logger.info(strplanName + " values are verified successfully");
        } else {
            logger.info(strplanName + " values are not verified successfully");
        }
    }
    
    // Click on Benefit Details Edit Button
	private void clickBenefitDetailsEditButton(String strEdit){
		performAction.click(By.xpath("//span[text()='"+strEdit+"']/../..//a[text()='Edit']"), "Clik on Edit button from Current Details page");
	}

    // set the state combo box
    private void selectStateField(String strValuetoSelect) {
        performAction.select(state, strValuetoSelect, "state combobox");
    }
    //
    //set the country combo box
    private void selectCountryField(String strValuetoSelect) {
        performAction.select(country, strValuetoSelect, "country combobox");
    }


	/**
	 * <pre>
	 * 
	 * Author  : Sekhar Tirumala
	 *  
	 * Description : Keyword to verify the values in Save Benefit Page In HrRole
	 * 
	 * Role : HR Role
	 * 
	 * PreCondition : Currents Benefits Plans Page InHR Role   
	 * 
	 * PostCondition : verify the User values in the Current Benefit Plans Page.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strplanName | strtypeOfthePlan | strexpectedValue | 
	 * 
	 * | Medical | Coverage Level | Accepted |
	 * 
	 * Java FileName : CurrentBenefitsPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strplanName" , "strtypeOfthePlan" , "strexpectedValue"})
	public void verifyBenefitDetailsInSaveBenefitPageInHrRole(String strplanName , String strtypeOfthePlan , String strexpectedValue ) {

		try {
			this.verifySaveBenefitDeatilsInHrRole(strplanName, strtypeOfthePlan, strexpectedValue);
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while Verify the benefit details "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while Verify the benefit details  "
					+ e.getMessage());
		}
	}


    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : Keyword to perform start on particular benefit for the employee in eEnrollment HR admin role.
     *
     * PreCondition : Member Benefits page having list of eligible benenfits for the Member
     *
     * PostCondition : Clicks on Start button for the benefit and plan selection page is shown.
     *
     * <b>Parameters & Example </b>
     *
     *
     * | strBenefitName |
     * | MEDICAL 2015 / DENTAL 2015 / VISION 2015 / Life Plan 2015 (available in 'BF QA Test Automation Group') |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strBenefitName"})
    public void startCurrentBenefit(String strBenefitName) {

        boolean benefitFound = false;

        try {
            Thread.sleep(1000);
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

                By list = By.xpath(currentBenefitsList + "/table");

                logger.info("list" + list.toString());

                int benefitsCount = browser.getCurrentWebDriver()
                        .findElements(list).size();

                logger.info("benefitsCount" + benefitsCount);

                if (benefitsCount > 0) {

                    for (int i = 1; i <= benefitsCount; i++) {

                        By BenefitHeader = By.xpath(currentBenefitsList
                                + "/table[" + i + "]//h1");

                        if (browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText().trim()
                                .equalsIgnoreCase(strBenefitName)) {
                            Thread.sleep(1000);
                            By startButton = By.xpath(currentBenefitsList
                                    + "/table[" + i + "]//a");
                            // webdriver.findElement(startButton).click();
                            performAction.jsclick(startButton, strBenefitName
                                    + " start button");
                            benefitFound = true;
                            break;
                        }
                    }
                } else {
                    logger.info("No Benefits available ");
                    throw new RuntimeException("No Benefits available");
                }

                if (!benefitFound) {
                    logger.info(strBenefitName
                            + " benefit NOT available.");
                    throw new RuntimeException(strBenefitName
                            + "benefit NOT available.");
                }
            }
            scr.capturePageScreenshot();
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
     * Description : Keyword to perform selection on Coverage Amount page in eEnrollment HR admin role for Life benefit
     *
     * PreCondition : Select the given amount as coverage amount and click next
     *
     * PostCondition : Next page as per the configuration of Life benefit.
     *
     * <b>Parameters & Example </b>
     *
     * | strCoverageAmount - String argument |
     * | $10,000 / $20,000 etc (available in 'BF QA Test Automation Group') |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strCoverageAmount"})
    public void selectCoverageAmount(String strCoverageAmount) {

        performAction.verifyMessage("Coverage Amount");

        boolean coverageAmountFound = false;

        try {

			/*if (performAction.isElementPresent(editCoverageAmountButton))
                performAction.click(editCoverageAmountButton,
						"Edit Coverage Amount");*/

            int rowsize = browser.getCurrentWebDriver()
                    .findElements(By.xpath(coverageAmountList)).size();

            if (rowsize > 0) {
                for (int i = 1; i <= rowsize; i++) {
                    String row = coverageAmountList + "[" + i + "]";
                    if (browser.getCurrentWebDriver()
                            .findElement(By.xpath(row)).getText().trim()
                            .toLowerCase()
                            .contains(strCoverageAmount.toLowerCase())) {
                        logger.info(strCoverageAmount
                                + "Found in the row no : " + i);
                        By loc = By.xpath(row + "//input");
                        performAction
                                .click(loc, "coverage amount radio button");
                        coverageAmountFound = true;
                        break;
                    }
                }
            } else {
                logger.info("No Coverage Amount available ");
                throw new RuntimeException("No Coverage Amount available");
            }

            if (!coverageAmountFound) {

                logger.info(strCoverageAmount
                        + " Coverage Amount NOT available.");
                throw new RuntimeException(strCoverageAmount
                        + " Coverage Amount NOT available.");
            } else {
                performAction.click(nextButton, "Next button.");
            }

        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while selecting Coverage Amount ");
            throw new CustomException(
                    "Exception occured while selecting Coverage Amount ");
        }
    }

    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : Keyword to perform selection on plans page in eEnrollment HR admin role
     *
     * PreCondition : Select the given plan in the list of eligible plans on the page and click next
     *
     * PostCondition : Next page as per the configuration. Coverage level page.
     *
     * <b>Parameters & Example </b>
     *
     * | strPlan - String type argument takes Plan Name as input value |
     * | MEDICAL 2015 / MEDICAL PLUS 2015 / DECLINE COVERAGE (available in 'BF QA Test Automation Group') |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strPlan"})
    public void selectPlan(String strPlan) {

        boolean planFound = false;

        try {
            if (strPlan.startsWith("td:")) {
                strPlan = utils.getValue(strPlan);
            }
            Thread.sleep(10000);
            int rowsize = browser.getCurrentWebDriver()
                    .findElements(By.xpath(planList)).size();
            logger.info("rowsize : " + rowsize);
            int count = 0;
            do {
                rowsize = browser.getCurrentWebDriver()
                        .findElements(By.xpath(planList)).size();
                logger.info("rowsize : " + rowsize);
                count++;
            } while (rowsize == 0 && count < 3);

            if (rowsize > 0) {
                for (int i = 1; i <= rowsize; i++) {
                    String row = planList + "[" + i + "]";
                    String availablePlan = browser.getCurrentWebDriver()
                            .findElement(By.xpath(row)).getText().trim()
                            .toLowerCase();
                    logger.info("availablePlan : " + availablePlan);
                    if (availablePlan.contains(strPlan.toLowerCase())) {
                        logger.info(strPlan + "Found in the row no : "
                                + i);
                        By loc = By.xpath(row + "//input");

                        performAction.click(loc, "Plan radio button");
                        planFound = true;
                        break;
                    }
                }
            } else {
                logger.info("No plan available ");
                throw new RuntimeException("No plan available");
            }

            if (!planFound) {
                logger.info(strPlan + " plan NOT available.");
                throw new RuntimeException(strPlan + "plan NOT available.");
            } else {
                performAction.click(nextButton, "Next button.");
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
     * Author  : Ch Phani Srikar
     *
     * Description : Keyword to perform selection on coverage level page in eEnrollment HR admin role
     *
     * PreCondition : Select the given level in the list of coverage levels on the page and click next
     *
     * PostCondition : Next page as per the configuration.
     *
     * <b>Parameters & Example </b>
     *
     * | strCoverageLevel - String type argument takes coverage level as input value |
     * | Employee Only / Employee and Spouse / Employee + Children (available in 'BF QA Test Automation Group') |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strCoverageLevel"})
    public void selectCoverageLevel(String strCoverageLevel) {

        boolean coverageLevelFound = false;

        try {
            int rowsize = browser.getCurrentWebDriver()
                    .findElements(By.xpath(coverageLevelList)).size();

            if (rowsize > 0) {
                for (int i = 1; i <= rowsize; i++) {
                    String row = coverageLevelList + "[" + i + "]";
                    if (browser.getCurrentWebDriver()
                            .findElement(By.xpath(row)).getText().trim()
                            .toLowerCase()
                            .contains(strCoverageLevel.toLowerCase())) {
                        logger.info(strCoverageLevel
                                + "Found in the row no : " + i);
                        By loc = By.xpath(row + "//input");
                        performAction.click(loc, "Plan radio button");
                        coverageLevelFound = true;
                        scr.capturePageScreenshot();
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
                scr.capturePageScreenshot();
            }

        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while selecting coverage level"
                            + e.getMessage());
            throw new RuntimeException(
                    "Exception occured while selecting coverage level "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : Keyword to verify current benefit for the employee in eEnrollment HR admin role
     *
     * PreCondition : Verify the give benefit is shown on the available benefits list
     *
     * PostCondition : NA
     *
     * <b>Parameters & Example </b>
     *
     * | strBenefitName - String type argument takes Benefit Name as input value |
     * | MEDICAL 2015 - Pending Approval, DENTAL 2015, VISION 2015 , Life Plan 2015 (available in 'BF QA Test Automation Group') |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strBenefitName"})
    public void verifyCurrentBenefit(String strBenefitName) {

        boolean benefitFound = false;

        try {

            Thread.sleep(1000);
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

                By list = By.xpath(currentBenefitsList + "/table");

                logger.info("list" + list.toString());

                int benefitsCount = browser.getCurrentWebDriver()
                        .findElements(list).size();

                logger.info("benefitsCount" + benefitsCount);

                if (benefitsCount > 0) {

                    for (int i = 1; i <= benefitsCount; i++) {

                        By BenefitHeader = By.xpath(currentBenefitsList
                                + "/table[" + i + "]//h1");

                        if (browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText().trim()
                                .equalsIgnoreCase(strBenefitName)) {
                            benefitFound = true;
                            break;
                        }
                    }
                } else {
                    logger.info("No Benefits available ");
                    throw new RuntimeException("No Benefits available");
                }

                if (!benefitFound) {
                    logger.info(strBenefitName
                            + " benefit NOT available.");
                    throw new RuntimeException(strBenefitName
                            + "benefit NOT available.");
                }
            }
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while verifying benefit "
                    + e.getMessage());
            throw new RuntimeException(
                    "Exception occured while verifying benefit "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : Keyword to perform verify particular benefit is available in benefits eligibility page
     *
     * PreCondition : Verify the give benefit is shown on the benefits eligibility page
     *
     * PostCondition : NA
     *
     * <b>Parameters & Example </b>
     *
     * | strBenefitName - String type argument takes Benefit Name as input value |
     * | MEDICAL, DENTAL , VISION, Life Plan etc and verifies the title (available in 'BF QA Test Automation Group') |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strBenefitName"})
    public void verifyBenefitEligibility(String strBenefitName) {

        boolean benefitFound = false;

        try {
            Thread.sleep(1000);
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(benefitEligibilityList))
                    .isDisplayed()) {

                By list = By.xpath(benefitEligibilityList);

                // logger.info("list" + list.toString());

                int benefitsEligibilityCount = browser.getCurrentWebDriver()
                        .findElements(list).size();

                logger.info("benefitsEligibilityCount"
                        + benefitsEligibilityCount);

                if (benefitsEligibilityCount > 0) {

                    for (int i = 1; i <= benefitsEligibilityCount; i++) {

                        By BenefitHeader = By.xpath(benefitEligibilityList
                                + "[" + i + "]");

                        if (browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText().trim()
                                .toLowerCase()
                                .contains(strBenefitName.toLowerCase())) {
                            benefitFound = true;
                            break;
                        }
                    }
                } else {
                    logger.info("No Benefits available ");
                    throw new RuntimeException("No Benefits available");
                }

                if (!benefitFound) {
                    System.out
                            .println(" Employee is NOT eligible for the given "
                                    + strBenefitName + "benefit.");
                    throw new CustomException(
                            " Employee is NOT eligible for the given "
                                    + strBenefitName + "benefit.");
                }
            }
        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while verifying benefit eligibiity"
                            + e.getMessage());
            throw new RuntimeException(
                    "Exception occured while verifying benefit eligibiity "
                            + e.getMessage());
        }
    }

    /**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword to Apply Coverage To Dependent in HR role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : User should be in persons covered page in Hr Role 
	 * 
	 * PostCondition : Dependent should be covered with the coverage.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * 
	 * Java file Name : CurrentBenefitsPage.java
	 * </pre> 
	 **/
    @RobotKeyword
    public void applyCoverageToDependent() {
        try {

            if (performAction.isElementPresent(waiveCoverage,
                    "Waive Coverage Button")) {

                performAction.click(nextButton, "Next button");

            } else {

                performAction.click(applyCoverage, "Apply coverage button");

                performAction.click(saveButton,
                        "Save button on Apply coverage page");

                if (!performAction.isElementPresent(waiveCoverage,
                        "Waive Coverage Button")) {
                    logger.info("Waive coverage button not found");
                    throw new RuntimeException();
                }
                performAction.click(nextButton, "Next button");
            }

        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while applying the coverage to dependent"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while applying the coverage to dependent "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Thallam
     *
     * Description : changeCoverageLevel keyword or method is used to Cancel Coverage To Dependent in HR role
     *
     * Role:HR role
     *
     * PreCondition : Member should be apply coverage to dependents page after selecting plan
     *
     * PostCondition : coverage will be cancelled for the dependent.
     * 
     * JavaFileName: CurrentBenefitsPage.java
     *
     * </pre>
     **/

    @RobotKeyword
    public void cancelCoverageToDependent() {
        try {
            // performAction.verifyMessage("Persons Covered");
            performAction.click(cancelCoverage, "Cancel coverage button");

        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while applying the coverage to dependent"
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while applying the coverage to dependent "
                            + e.getMessage());
        }
    }

    /**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword to Save Benefits To Employee in HR role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition :  User should be in benefits page.
	 * 
	 * PostCondition : Benefits Snapshot should be visible.
	 *   
	 * <pre>
	 * <b>Parameters & Example </b> 
	 * | strEffectiveDate - Effective Date |
	 * 
	 * Java file Name : CurrentBenefitsPage.java
	 * </pre> 
	 **/
    @RobotKeyword
    @ArgumentNames({"strEffectiveDate"})
    public void saveBenefit(String strEffectiveDate) {
        try {

            // Premium Deduction Page
            performAction.click(nextButton, "Next button");

            // Effective Date Page
            performAction.verifyMessage("Effective Date");

            this.effectiveDate(strEffectiveDate);

            performAction.click(nextButton, "Next button");

            // Current Benefits Final Page
            performAction.jsclick(saveButton, "Save button");

            if (performAction.isElementPresent(employeeOverviewpopup,
                    "Employee Overview Pop UP")) {
                performAction.click(employeeOverviewpopup,
                        "Employee Overview Pop UP");
            }
            performAction.verifyMessage("Benefits Snapshot");

        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while saving the benefits"
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while saving the benefits "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Thallam
     *
     * Description : saveBenefitAfterEdit keyword or method used to Save Benefits To Employee in after editing the benefit in HR
     * role and it Saves Benefits and Coverage Level to and Employee and verifying.
     *
     * Role: HR Role
     *
     * PreCondition :  Member must complete the selecting benefits in the plans page.
     *
     * PostCondition : member successfully redirected to member Offers page.
     * 
     * JavaFileName: CurrentBenefitsPage.java
     *
     * </pre>
     **/

    @RobotKeyword
    public void saveBenefitAfterEdit() {
        try {

            // Premium Deduction Page
            // performAction.verifyMessage("Premium Deduction");

            // performAction.click(nextButton, "Next button");

            // Effective Date Page
            performAction.verifyMessage("Effective Date");

            performAction.click(nextButton, "Next button");

            // Current Benefits Final Page
            performAction.jsclick(saveButton, "Save button");

            if (performAction.isElementPresent(employeeOverviewpopup,
                    "Employee Overview Pop UP")) {
                performAction.click(employeeOverviewpopup,
                        "Employee Overview Pop UP");
            }

            performAction.verifyMessage("Benefits Snapshot");

        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while saving the benefits"
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while saving the benefits "
                            + e.getMessage());
        }
    }

    /**
     * Author : Nagarjuna Thallam
     * 
     * Description : Keyword to cancel particular benefit for the employee in eEnrollment HR
     * admin role
     * 
     * Role: HR Role
     * 
     * PreCondition : user should be in current benefits page in Hr Role 
	 * 
	 * PostCondition : Benefit should be canceled.
	 *   
     * <pre>
     * <b>Parameters :</b>
     * | strBenefitName - String type argument takes Benefit Name as input value |
     * <b>Example :</b>
     * | MEDICAL 2015 / DENTAL 2015 / VISION 2015 / Life Plan 2015 |
     * 
     * Java file Name : CurrentBenefitsPage.java
     * 
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"strBenefitName"})
    public void cancelBenefitInHrRole(String strBenefitName) {
        boolean benefitFound = false;

        try {
            Thread.sleep(1000);
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

                By list = By.xpath(currentBenefitsList + "/table");

                logger.info("list" + list.toString());

                int benefitsCount = browser.getCurrentWebDriver()
                        .findElements(list).size();

                logger.info("benefitsCount" + benefitsCount);

                if (benefitsCount > 0) {

                    for (int i = 1; i <= benefitsCount; i++) {

                        By BenefitHeader = By.xpath(currentBenefitsList
                                + "/table[" + i + "]//h1");
                        logger.info("Benefit Header   " + BenefitHeader);

                        if (browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText().trim()
                                .contains(strBenefitName)) {
                            Thread.sleep(1000);
                            By editButton = By.xpath(currentBenefitsList
                                    + "/table[" + i
                                    + "]//strong[text()='Edit']");
                            performAction.jsclick(editButton, strBenefitName
                                    + " Edit button");

                            performAction.click(cancelBenefitForAll,
                                    "Cancel Selected Benefit");
                            Thread.sleep(2000);
                            performAction.acceptAlert();
                            Thread.sleep(2000);
                            if (performAction.isElementPresent(effectiveDate)) {
                                performAction.clearEnter(effectiveDate, "d:currentdate", "End date");
                            }
                            performAction.click(nextButton, "Next Button");
                            scr.capturePageScreenshot();
                            performAction.click(saveButton, "Save Button");

                            benefitFound = true;
                            break;
                        }
                    }
                } else {
                    logger.info("No Benefits available ");
                    scr.capturePageScreenshot();
                    throw new RuntimeException("No Benefits available");
                }

                if (!benefitFound) {
                    logger.info(strBenefitName
                            + " benefit NOT available.");
                    throw new RuntimeException(strBenefitName
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
     * Author  : Nagarjuna Thallam
     *
     * Description : changeCoverageLevelAsEmployeeInHrRole keyword or method is used to change coverage level to Employee only to particular benefit
     * in eEnrollment HR admin role
     *
     * Role: HR role
     *
     * PreCondition : Member should be in Offers page
     *
     * PostCondition : coverage will be changed to EMployee only.
     *
     * <b>Parameters & Example </b>
     *
     * | strBenefitName |
     * |  MEDICAL 2015 / DENTAL 2015 / VISION 2015 / Life Plan 2015 - is offer name and it will be select from the list |
     * 
     * JavaFileName: CurrentBenefitsPage.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"strBenefitName"})
    public void changeCoverageLevelAsEmployeeInHrRole(String strBenefitName) {
        boolean benefitFound = false;

        try {
            Thread.sleep(1000);
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

                By list = By.xpath(currentBenefitsList + "/table");

                logger.info("list" + list.toString());

                int benefitsCount = browser.getCurrentWebDriver()
                        .findElements(list).size();

                logger.info("benefitsCount" + benefitsCount);

                if (benefitsCount > 0) {

                    for (int i = 1; i <= benefitsCount; i++) {

                        By BenefitHeader = By.xpath(currentBenefitsList
                                + "/table[" + i + "]//h1");
                        logger.info("Benefit Header   " + BenefitHeader);

                        if (browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText().trim()
                                .equalsIgnoreCase(strBenefitName)) {
                            Thread.sleep(1000);
                            By editButton = By.xpath(currentBenefitsList
                                    + "/table[" + i
                                    + "]//strong[text()='Edit']");
                            // webdriver.findElement(startButton).click();
                            performAction.jsclick(editButton, strBenefitName
                                    + " Edit button");
                            String covergareLevel = browser
                                    .getCurrentWebDriver()
                                    .findElement(verifyCoverage).getText();
                            logger.info("Coverage level is..."
                                    + covergareLevel);

                            performAction.click(editCoverage, "Edit Coverage");
                            this.changeCoverageLevel("Employee Only");
                            // performAction.click(employeeCoverage,
                            // "Selects Employee coverage");
                            //logger.info("1st next");
                            performAction.click(nextButton, "Next Button");
                            //logger.info("2nd Next");
                            // performAction.click(nextButton, "Next Button");
                            String changedCovergareLevel = browser
                                    .getCurrentWebDriver()
                                    .findElement(verifyCoverage).getText();
                            logger.info("Coverage level Changed to..."
                                    + changedCovergareLevel);
                            scr.capturePageScreenshot();
                            performAction.click(saveButton, "Save Button");

                            benefitFound = true;
                            break;
                        }
                    }
                } else {
                    logger.info("No Benefits available ");
                    throw new RuntimeException("No Benefits available");
                }

                if (!benefitFound) {
                    logger.info(strBenefitName
                            + " benefit NOT available.");
                    throw new RuntimeException(strBenefitName
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
     * Author  : Nagarjuna Thallam
     *
     * Description : updatePlanWithDependent keyword or method is used to Update a particular benefit by adding another dependent for
     * the employee in eEnrollment HR admin role
     *
     * Role: Hr Role
     *
     * PreCondition : Member should be in Offers page
     *
     * PostCondition : coverage will be changed to EMployee only.
     *
     * <b>Parameters & Example </b>
     *
     * | depRelationshipType|  strBenefitName |
     * |Spouse / Child  - Dependent relationship to the Employee  |  MEDICAL 2015 / DENTAL 2015 / VISION 2015 / Life Plan 2015 - is an offer name and it will be select from the list |
     *
     * JavaFileName: CurrentBenefitsPage.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"depRelationshipType", "strBenefitName"})
    public void updatePlanWithDependent(String depRelationshipType,
                                        String strBenefitName) {

        try {
            // this.addDependent();
            // AddDependent.addDependentToEmployee(depRelationshipType,
            // outLastName);
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

                By list = By.xpath(currentBenefitsList + "/table");

                logger.info("list" + list.toString());

                int benefitsCount = browser.getCurrentWebDriver()
                        .findElements(list).size();

                logger.info("benefitsCount" + benefitsCount);
                scr.capturePageScreenshot();

                if (benefitsCount > 0) {

                    for (int i = 1; i <= benefitsCount; i++) {

                        scr.capturePageScreenshot();
                        By BenefitHeader = By.xpath(currentBenefitsList
                                + "/table[" + i + "]//h1");
                        logger.info("Benefit Header   " + BenefitHeader);
                        String bheader = browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText();
                        logger.info("Benefit name.." + bheader);
                        if (browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText().trim()
                                .equalsIgnoreCase(strBenefitName)) {
                            Thread.sleep(1000);
                            // logger.info("Entered into the IF loop");
                            By editButton = By.xpath(currentBenefitsList
                                    + "/table[" + i
                                    + "]//strong[text()='Edit']");
                            // webdriver.findElement(startButton).click();
                            performAction.jsclick(editButton, strBenefitName
                                    + " Edit button");
                            String covergareLevel = browser
                                    .getCurrentWebDriver()
                                    .findElement(verifyCoverage).getText();
                            logger.info("Coverage level is..."
                                    + covergareLevel);

                            performAction.click(editCoverage, "Edit Coverage");
                            this.changeCoverageLevel(depRelationshipType);
                            String changedCovergareLevel = browser
                                    .getCurrentWebDriver()
                                    .findElement(verifyCoverage).getText();
                            logger.info("Coverage level Changed to..."
                                    + changedCovergareLevel);
                            this.applyCoverageToDependent();
                            scr.capturePageScreenshot();
                            performAction.click(nextButton, "Next Button");
                            performAction.click(saveButton, "Save Button");
                            scr.capturePageScreenshot();
                            break;
                        }
                    }
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
     * Author  : Nagarjuna Thallam
     *
     * Description : editCurrentBenefit keyword or method is used to Edit a particular benefit for the employee in eEnrollment HR
     *
     * Role : HR Role
     *
     * PreCondition : Member should be in Offers page
     *
     * PostCondition : coverage will be navigated to edit benefit page.
     *
     * <b>Parameters & Example </b>
     *
     * | strBenefit |
     * |  MEDICAL 2015 / DENTAL 2015 / VISION 2015 / Life Plan 2015 - is an offer name and it will be select from the list |
     * 
     * JavaFileName: CurrentBenefitsPage.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"strBenefit"})
    public void editCurrentBenefit(String strBenefit) {
        try {
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

                By list = By.xpath(currentBenefitsList + "/table");

                int benefitsCount = browser.getCurrentWebDriver()
                        .findElements(list).size();

                logger.info("benefitsCount" + benefitsCount);
                scr.capturePageScreenshot();

                if (benefitsCount > 0) {

                    for (int i = 1; i <= benefitsCount; i++) {

                        By BenefitHeader = By.xpath(currentBenefitsList
                                + "/table[" + i + "]//h1");
                        //logger.info("Benefit Header   " + BenefitHeader);
                        String bheader = browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText();
                        logger.info("Benefit name.." + bheader);
                        if (browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText().toLowerCase().trim()
                                .contains(strBenefit.toLowerCase().trim())) {

                            By editButton = By.xpath(currentBenefitsList
                                    + "/table[" + i
                                    + "]//strong[text()='Edit']");
                            performAction.waitUntilElementPresent(editButton);                            // webdriver.findElement(startButton).click();
                            performAction.jsclick(editButton, strBenefit
                                    + " Edit button");
                            break;
                        }
                    }
                    scr.capturePageScreenshot();
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
     * Author  : Nagarjuna Thallam
     *
     * Description : editCoverageInBenefit keyword or method is used to Edit coverage level for a particular benefit for the employee
     * in eEnrollment HR admin role
     *
     * Role: HR Role
     *
     * PreCondition : Member should be in Offers page
     *
     * PostCondition : coverage will be navigated to edit Coverage page.
     *
     *
     * JavaFileName: CurrentBenefitsPage.java
     * </pre>
     **/

    @RobotKeyword
    public void editCoverageInBenefit() {

        try {
            String covergareLevel = browser.getCurrentWebDriver()
                    .findElement(verifyCoverage).getText();
            logger.info("Coverage level is..." + covergareLevel);

            performAction.click(editCoverage, "Edit Coverage");
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while Editing coverage "
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while Editing coverage "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : Keyword used to select reason after adding Employee with 1st of previous month as HireDate in HR Admin Role
     *
     * PreCondition : New Empployed added with 1st of previous month as HireDate and saved [Group :BF QA Tes Automation Group]
     *
     * PostCondition : Asks for reason to change Medical / Dnetal benefit member has enrolled.
     *
     * <b>Parameters & Example </b>
     *
     * | strReason - Reason for the change |
     * | Other,Adoption,Birth etc (available in 'BF QA Test Automation Group') |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strReason"})
    public void reasonForBenefitChange(String strReason) {

        try {
            if (strReason.startsWith("td:")) {
                strReason = utils.getValue(strReason);
            }
            // Click on Continue Button if Present
            if (performAction.isElementPresent(clickContinueButton)) {
                this.clickContinueButton();
            }
            if (strReason.equalsIgnoreCase("refuse all")) {
                this.clickRefuseAllbutton();
            } else if (strReason.equalsIgnoreCase("other")) {
                this.selectOtherOption();
            } else {
                this.clickEventChoicebutton();
                this.selectNewEventType(strReason);
            }
            // Click on Next Button
            this.clickNextButton();

        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in adding Reason for Beefit Change to employee"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in adding Reason for Beefit Change to employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Thallam
     *
     * Description : changeCoverageLevel keyword or method is used to Change the coverage level after selecting a particular benefit
     * for the employee in eEnrollment HR admin role
     *
     *
     * PreCondition : Member should be in Offers page
     *
     * PostCondition : coverage will be changed for the employee.
     *
     * <b>Parameters & Example </b>
     *
     * | strRelation |
     * |  Employee and Child / Employee only / Employee and Spouse - Relationship to change|
     * 
     * JavaFileName: CurrentBenefitsPage.java
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strRelation"})
    public void changeCoverageLevel(String strRelation) {
        try {
            this.editCoverageInBenefit();

            if (strRelation.equalsIgnoreCase("Employee and Spouse")) {
                selectCoverageLevel("Employee and Spouse");
            } else if (strRelation.equalsIgnoreCase("Child")) {
                selectCoverageLevel("Employee + Children");
            } else if (strRelation.equalsIgnoreCase("Employee Only")) {
                selectCoverageLevel("Employee Only");
            } else {
                selectCoverageLevel(strRelation);
            }
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("No coverageLevels available ");
            throw new RuntimeException("No coverageLevels available");
        }

    }

    /**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Select Premium Deduction' keyword for selecting 'yes or no' option from Premium Deduction page in HR Role
	 * 
	 * PreCondition  : Navigate to Premium Deduction page.
	 * 
	 * PostCondition : Able to Select option from premium deduction page.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPremiumDeduction    |
	 * | yes                    |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> CurrentBenefits.java </b>
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPremiumDeduction" })
	public void selectPremiumDeduction(String strPremiumDeduction) {
		try {
			if (strPremiumDeduction.equalsIgnoreCase("yes")) {
				performAction.click(preTax, "Click on Pre Tax option");
				this.clickNextButton();
			} else {
				performAction.click(postTax, "Click on Post Tax option");
				this.clickNextButton();
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in selecting premium deduction for employee"
					+ e.getMessage());
			throw new CustomException(
					"Exception in selecting premium deduction for employee"
							+ e.getMessage());
		}
	}

    /**
     * <pre>
     * Author  : CH Phani Srikar
     *
     * Description   : 'Medical care information for employee
     *
     * PreCondition  : Medical plan enrollment with FSA
     *
     * PostCondition : Health screening question is selected
     *
     * <b>Parameters & Example </b>
     *
     * | strMedicareInformation  |
     * | yes / no              |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strMedicareInformation", "strHasRequiredDetails"})
    public void medicareInformation(String strMedicareInformation, String strHasRequiredDetails) {

        try {
            if (strMedicareInformation.equalsIgnoreCase("yes")) {
                performAction.jsclick(medicareTrue,
                        "Click on Medicare Yes option");
                this.selectUsersCovered();
                this.selectHasRequiredDetails(strHasRequiredDetails);
            } else {
                performAction.jsclick(medicareFalse,
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
     * PreCondition  : Additional insurance section
     *
     * PostCondition : Insurance details is specified
     *
     * <b>Parameters & Example </b>
     *
     * | strAddtionalInsurance  |
     * | yes / no  / yesno      |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strAddtionalInsurance"})
    public void additionalInsuranceInformation(String strAddtionalInsurance) {
        try {
            if (strAddtionalInsurance.equalsIgnoreCase("yes")) {
                performAction.jsclick(additionInsuranceYes,
                        "Click on Additional Insurance Yes option");
            } else if (strAddtionalInsurance.equalsIgnoreCase("no")) {
                performAction.jsclick(additionInsuranceNo,
                        "Click on Additional Insurance No option");
            } else {
                performAction.jsclick(additionInsuranceYesNoDetails,
                        "Click on Additional Insurance Yes but no details option");
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
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Add Medicare Policy Details' used to add policy information for part A and part B in HR Role
	 * 
	 * PreCondition  : Select 'Yes' against Medicare policy and Navigate to 'Add Medicare Policy' page 
	 * 
	 * PostCondition : Able to add Part A and Part B information. 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | JSON Tag Name  |
	 * | addmedicarepolicy |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> CurrentBenefits.java </b>
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAddMedicarePolicyDetails" })
	public void addMedicarePolicydetails(String strAddMedicarePolicyDetails) {
		if (strAddMedicarePolicyDetails.startsWith("td:"))
			strAddMedicarePolicyDetails = strAddMedicarePolicyDetails.substring(3);

		if(performAction.isElementPresent(By.linkText("Add Policy"))){
			performAction.click(By.linkText("Add Policy"), "Click on Add Policy Button");
		}
		Object object = null;
		JSONObject fields = ReadJsonTestData.getTestData(strAddMedicarePolicyDetails
				.toLowerCase());
		logger.info("fields = " + fields.toJSONString());
		try {
			performAction.verifyMessage("Policy Information");
			object = fields.get("medicarenumber");
			if (object != null) {
				this.enterMedicareNumber(utils.getValue(object.toString()));
			}
			if(performAction.isElementPresent(policyHolder)){
				this.selectPolicyHolder();	
			}
			object = fields.get("eligibilityreason");
			if (object != null) {
				if(performAction.isElementPresent(By.xpath("//label[text()='"+object.toString()+"']/../..//input"))){
					performAction.click(By.xpath("//label[text()='"+object.toString()+"']/../..//input"), "Click on Eligible Reasons");
				}else{
					this.selectEligibleReasons(object.toString());
				}
			}
			this.setPartA();
			object = fields.get("partaeligibilitydate");
			if (object != null) {
				this.enterPartAEligibilityDate(object.toString());  
			}
			object = fields.get("partaeffectivedate");
			if (object != null) {
				this.enterPartAEffectiveDate(object.toString());
			}
			object = fields.get("partaenddate");
			if (object != null) {
				this.enterPartAEndDate(object.toString());
			}
			this.setPartB();
			object = fields.get("partbeligibilitydate");
			if (object != null) {
				this.enterPartBEligibilityDate(object.toString());
			}
			object = fields.get("partbeffectivedate");
			if (object != null) {
				this.enterPartBEffectiveDate(object.toString());
			}
				
				object = fields.get("medicarepayerstatus");
				if (object != null) {
					performAction.select(By.id("newMedicarePayerStatus"), object.toString(),"Select Medicare Payer Status");
				}
				object = fields.get("medicarepayereffectivedate");
				if (object != null) {
					performAction.clearEnter(By.xpath("//input[@id='newMedicarePayerStatusEffectiveDate']"), utils.getValue(object.toString()), "Enter effective date in medicare payer");
					this.clickAddButton();
				}
				
			performAction.click(By.xpath("//table[@id='medicareAddEditSaveButton']//a"), "Click on Add Button");
			
			if(performAction.isElementPresent(nextButton)){
				this.clickNextButton();
			}
			if(performAction.isElementPresent(saveButton)){
				this.clickSaveButton();
			}
			
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in adding medicare policy details for employee"
					+ e.getMessage());
			throw new CustomException(
					"Exception in adding medicare policy details for employee"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Add Additional Insurance Information" keyword is used to add additional insurance required information. 
	 * 
	 * PreCondition  : Additional Insurance required information page
	 * 
	 * PostCondition : Able to add Insurance details.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | JSON Tag Name  |
	 * | additonalinsuranceinformation |
	 * 
	 * <b> Java File Path :  com.benefitfocus.robot.hradmin >> CurrentBenefitsPage.java </b>
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAdditonalIndsuranceInformation" })
	public void addAdditionalInsuranceInformation(String strAdditonalIndsuranceInformation) {
		if (strAdditonalIndsuranceInformation.startsWith("td:"))
			strAdditonalIndsuranceInformation = strAdditonalIndsuranceInformation.substring(3);

		Object object = null;
		JSONObject fields = ReadJsonTestData.getTestData(strAdditonalIndsuranceInformation
				.toLowerCase());
		try {
			performAction.verifyMessage("Additional Insurance");
			object = fields.get("policynumber");
			if (object != null) {
				this.enterPolicyNumber(utils.getValue(object.toString()));
			}
			object = fields.get("policyholder");
			if (object != null) {
				this.selectPolicyHolderAddtionalInsurance(object.toString());
			}
			object = fields.get("carriername");
			if (object != null) {
				this.enterCarrierName(object.toString());
			}
			object = fields.get("coverageinformation");
			if (object != null) {
				this.selectCoverageInformation(object.toString());
			}
			object = fields.get("coverageeffectivedate");
			if (object != null) {
				this.enterCoverageEffectiveDate(object.toString());
			}
            object = fields.get("state");
            if (object != null) {
                this.selectStateField(object.toString());
            }
            object = fields.get("country");
            if (object != null) {
                this.selectCountryField(object.toString());
            }
			this.clickAddButton();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in adding additional insurance required information."
					+ e.getMessage());
			throw new CustomException(
					"Exception in adding additional insurance required information."
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 * 
	 * Description  : Navigate to Open Enrollment Tab in HR Role Keyword or method is used navigate to Open Enrollment Tab in HR Role.
	 * 
	 * Precondition : Complete Enrollment using  Current Benefits and able to see Open Enrollment message box.
	 * 
	 * PostConditions : Able to navigate to Open Enrollment Tab.
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | strActionTobePerformed | 
	 * | Next or Go to Summary | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> CurrentBenefits.java </b>
	 * 
	 * </pre>
	 * 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strActionTobePerformed"} )
	public void navigateToOpenEnrollmentTabInHRRole(String strActionTobePerformed ) {
		try {
			if(strActionTobePerformed.equalsIgnoreCase("next button")){
				this.clickNextButton();
			}else{
				this.clickGoToSummaryPage();
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception occured while Navigating to Open Enrollment  "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Navigating to Open Enrollment "
							+ e.getMessage());
		}
	}

    /**
     * <pre>
     * Author  : CH Phani Srikar
     *
     * Description   : 'Health Screening/HRA' complete all 3 requirements {by October 31, 2014} to obtain a discount on your health premiums next year
     *
     * PreCondition  : Medical plan enrollment with FSA
     *
     * PostCondition : Health screening question is selected
     *
     * <b>Parameters & Example </b>
     *
     * | strCompleteHealthScreening  |
     * | yes / no              |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strCompleteHealthScreening"})
    public void healthScreeningNeeded(String strCompleteHealthScreening) {

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
     * Description   : 'Medical care information for employee
     *
     * PreCondition  : Medical plan enrollment with FSA
     *
     * PostCondition : Health screening question is selected
     *
     * <b>Parameters & Example </b>
     *
     * | strMedicareInformation  |
     * | yes / no              |
     * </pre>
     **//*
	@RobotKeyword
	@ArgumentNames({ "strMedicareInformation" })
	public void medicareInformation(String strMedicareInformation) {

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
	}*/


    /**
     * <pre>
     * Author  : CH Phani Srikar
     *
     * Description   : Enter the contribution amount for FSA plan
     *
     * PreCondition  : Contribution amoujnt page after selecting FSA plan
     *
     * PostCondition : Effective date page after clicking on next button
     *
     * <b>Parameters & Example </b>
     *
     * | value  |
     * | 200    |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"value"})
    public void enterContributionAmountForFSAPlan(String value) {

        try {

            performAction.clearEnter(contributionAmount, value,
                    "contribution amount textfield");

            this.clickNextButton();

        } catch (Exception e) {
            logger.info("Exception occured " + e.getMessage());
            throw new CustomException(
                    "Exception while entering contribution amount for FSA plan"
                            + e.getMessage());
        }

    }

    /**
     * <pre>
     * Author  : CH Phani Srikar
     *
     * Description   : Enter the contribution amount for FSA plan
     *
     * PreCondition  : Contribution amoujnt page after selecting FSA plan
     *
     * PostCondition : Effective date page after clicking on next button
     *
     * <b>Parameters & Example </b>
     *
     * | value  |
     * | 200    |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"value"})
    public void FSADeductionPeriodsRemaining(String value) {

        try {

            performAction.clearEnter(FSADeductionPeriods, value,
                    "contribution amount textfield");

            this.clickNextButton();

        } catch (Exception e) {
            logger.info("Exception occured " + e.getMessage());
            throw new CustomException(
                    "Exception while entering contribution amount for FSA plan"
                            + e.getMessage());
        }

    }

    /**
     * <pre>
     * Author  : CH Phani Srikar
     *
     * Description   : Perform Save button click action on Benefit enrollment Summary page
     *
     * PreCondition  : Summary page
     *
     * PostCondition : Confirmation page
     **/
    @RobotKeyword
    public void saveBenefitEnrollment() {
        try {

            // Current Benefits Final Page
            performAction.jsclick(saveButton, "Save button");

            if (performAction.isElementPresent(employeeOverviewpopup,
                    "Employee Overview Pop UP")) {
                performAction.click(employeeOverviewpopup,
                        "Employee Overview Pop UP");
            }

            //click on OE Proceed Next Period Button
            if (performAction.isElementPresent(By.xpath("//a[text()='Next']"))) {
                performAction.click(By.xpath("//a[text()='Next']"), "OE Proceed Next Button");
            }
            //verifying Benefits Snapshot Message
            if (browser.getCurrentWebDriver().getPageSource().contains("Benefits Snapshot")) {
                performAction.verifyMessage("Benefits Snapshot");
            }

        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while saving the benefits"
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while saving the benefits "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : CH Phani Srikar
     *
     * Description   : to capture the tobacco usage the employee
     *
     * PreCondition  : Question with "Have you used any tobacco products regularly in the last 12 months?"
     *
     * <b>Parameters & Example </b>
     *
     * | tobaccoUsedInlast12Months  |
     * | yes / no    |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"tobaccoUsedInlast12Months"})
    public void tobaccoUsage(String tobaccoUsedInlast12Months) {

        try {
            if (browser
                    .getCurrentWebDriver()
                    .findElement(By.xpath("//h3"))
                    .getText()
                    .equalsIgnoreCase(
                            "American Residential Services Tobacco Usage")) {

                if (tobaccoUsedInlast12Months.equalsIgnoreCase("yes")) {
                    performAction.click(
                            By.xpath("//label[contains(text(),'Yes')]"),
                            "Click on tobacco usage Yes option");
                } else {
                    performAction.click(
                            By.xpath("//label[contains(text(),'No')]"),
                            "Click on tobacco usage No option");
                }
                this.clickNextButton();

                performAction.click(By.linkText("Save and Complete"),
                        "Save and complete the tobacco survey");
            }

        } catch (Exception e) {

            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while choosing tobacco usage for the employee"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while choosing tobacco usage for employee."
                            + e.getMessage());

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
     * PreCondition : Verify the give reason is shown in Current benefits page
     *
     * PostCondition : NA
     *
     * <b>Parameters & Example </b>
     *
     * | strReson - String type argument takes reason as input value |
     * | Court order,Birth,Adoption,Divorce, Legal separation,Marriage |
     * </pre>
     * 
     * Java file Name : CurrentBenefitsPage.java
     **/
    @RobotKeyword
    @ArgumentNames({"strBenefitName"})
    public void verifyReasonForChange(String strReson) {

        try {
            Thread.sleep(1000);
            if (strReson.startsWith("td:"))
                strReson = strReson.substring(3);
            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(strReson
                    .toLowerCase());
            if (browser.getCurrentWebDriver()
                    .findElement(reasonForChange).isDisplayed()) {
                String reasonTxtMesg = browser.getCurrentWebDriver().findElement(reason).getText();
                logger.info("reasonTxtMesg:::" + reasonTxtMesg);
                object = fields.get("reason");
                if (object != null) {
                    if (reasonTxtMesg.contains(object.toString())) {
                        String reasonforChangeMsg = browser.getCurrentWebDriver().findElement(reasonForChange).getText();
                        logger.info(reasonforChangeMsg + " is " + reasonTxtMesg);
                        scr.capturePageScreenshot();
                    }
                }
            }
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while verifying reason for change in Current Benefits page "
                    + e.getMessage());
            throw new RuntimeException(
                    "Exception occured while verifying reason for change in Current Benefits page  "
                            + e.getMessage());
        }
    }


    /**
     * <pre>
     * Author  : Teja P
     * source : BenefitEnrollmentHRAdmin
     *
     * Description : This Keyword is used to click the save button in Benefit Enrollment Final Page of HR-Admin Role
     *
     * PreCondition : Benefit Enrollment final page with save button should be displayed
     *
     * PostCondition : Memeber Overview page should be displayed
     *
     *
     * </pre>
     **/
    @RobotKeyword
    public void saveBenefitInSnapshotPage() {

        try {

            logger.info("===============save button in finale page "
                    + saveInSnapshotPage);
            // performAction.click(nextButton, "Save Benefit in Final Pge");
            performAction
                    .click(saveInSnapshotPage, "Save Button in Benefit final page");

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while saving the Benefit in Final page"
                            + " " + e.getMessage());
        }

    }

   /**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Approve Requested Amount For Employee From Benefit Details Page' keyword used to approve health statement amount from Benefit details page in HR Role.
	 * 
	 * PreCondition  : Login as HR Admin, Navigate to Benefit Details page.
	 * 
	 * PostCondition : Able to approve/decline employees.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strBenefitName,strApproveAmount | 
	 * | Life Plan 2016,Approve $60,000.00 |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> CurrentBenefits.java </b>
	 *  
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strBenefitName","strApproveAmount"})
	public void ApproveRequestedAmountForEmployeeFromBenefitDetailsPage(String strBenefitName,String strApproveAmount) {
		try{
			performAction.waitUntilElementPresent(currentBenefitsListLocator);	
			boolean benefitFound = false;
			if (browser.getCurrentWebDriver()
					.findElement(By.xpath(currentBenefitsList)).isDisplayed()) {

				By list = By.xpath(currentBenefitsList + "/table");

				logger.info("list" + list.toString());

				int benefitsCount = browser.getCurrentWebDriver()
						.findElements(list).size();

				logger.info("benefitsCount" + benefitsCount);

				if (benefitsCount > 0) {

					for (int i = 1; i <= benefitsCount; i++) {

						By BenefitHeader = By.xpath(currentBenefitsList
								+ "/table[" + i + "]//h1");

						if (browser.getCurrentWebDriver()
								.findElement(BenefitHeader).getText().trim()
								.equalsIgnoreCase(strBenefitName)) {

							performAction.click(adminOrBrokerApproval,"Click on Approval or Broker Button");
							benefitFound = true;
							break;
						}
					}
				} else {
					logger.info("No Benefits available ");
					throw new RuntimeException("No Benefits available");
				}

				if (!benefitFound) {
					logger.info(strBenefitName
							+ " benefit NOT available.");
					throw new RuntimeException(strBenefitName
							+ "benefit NOT available.");
				}
			}
			// Approve Coverage Amount for Health Statement from Benefit Details page
			browser.getCurrentWebDriver().findElement(By.xpath("//label[contains(text(),'"+strApproveAmount+"')]/../..//input")).click();

			this.clickNextButton();
			this.clickNextButton();
			this.clickSaveButton();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Approving Employee Requested Amount from Benefit Details page In HR Role"
					+ e.getMessage());
			throw new CustomException(
					"Exception in Approving Employee Requested Amount from Benefit Details page In HR Role"
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *
	 * Description   : "Edit Current Benefit Details From Benefit Details In HR Role" Keyword to edit Current Benefits Details from Benefit Details in HR Role
	 *
	 * PreCondition  : Navigate to Current Benefits page
	 *
	 * PostCondition : Able to click on edit buton against Current Benefits page
	 * <pre>
	 * 
	 * <b>Parameters :</b>
	 * | strEdit |
	 * | Persons Covered |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> CurrentBenefits.java </b>
	 * 
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strEdit"})
	public void editCurrentBenefitDetails(String strEdit) {
		if(strEdit.substring(0, 3).equals("str")){
			strEdit=strEdit.substring(3);
		}
		try {
			this.clickBenefitDetailsEditButton(strEdit);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception in editing Current Benefits ");
			scr.capturePageScreenshot();
			throw new CustomException("Exception in editing Current Benefits "
					+ e.getMessage());
		}
	}


    /**
     * <pre>
     * Author  : Sekhar Tirumala
     *
     * Description   : "Date Explanation Edit Box  in HR Role
     * 
     * Role : HR Role
     *
     * PreCondition  : Navigate to Current Benefits page
     *
     * PostCondition : Able to Enter Date Explanation Message in Current Benefits page
     * <pre>
     * <b>Parameters :</b>
     * | strdateMessage |
     * | Changed |
     * 
     * Java File Name : CurrentBenefitPage.java
     * 
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"strdateMessage"})
    public void dateExplanationInHRRole(String strdateMessage) {

        try {
            
            performAction.clearEnter(editBox, strdateMessage, "Date Explanation TextBox");
            scr.capturePageScreenshot();
            this.clickNextButton();
        } catch (Exception e) {
            logger.info("Exception in editing Date Explanation ");
            scr.capturePageScreenshot();
            throw new CustomException("Exception in editing Date Explanation "
                    + e.getMessage());
        }
    }

   /**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *
	 * Description   : "Calculate Contribution Amount In HR Role" Keyword to calculate prorate amount from Save Benefits page in HR Role
	 *
	 * PreCondition  : Navigate to Save Benefits page
	 *
	 * PostCondition : Able to calculate Prorate amount
	 * <pre>
	 * <b>Parameters :</b>
	 * | strAnnualAmount,strPayFrequency,strEffectiveDate,strContributionType |
	 * | 1200, semimonthly,d;currentdate,Initial Contribution |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> CurrentBenefits.java </b>
	 * 
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strAnnualAmount","strPayFrequency","strEffectiveDate","strContributionType"})
	public void calculateContributionAmountInHRRole(int strAnnualAmount,String strPayFrequency,String strEffectiveDate,String strContributionType) {
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
			String contributionAmount=browser.getCurrentWebDriver().findElement(By.xpath("//td[contains(text(),'"+strContributionType+"')]/..//td[2]")).getText();
			contributionAmount=(contributionAmount.substring(1, contributionAmount.indexOf("."))).replaceAll(",", "");
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
     * Author  : Prasad Pasupuleti
     * Description   : It is used to click on Cancel Benefit For All button in HR Role and accepts the alert available in order to cancel the any benefit plan
     *
     * PreCondition  : Navigate to Benefit details page and benefit should be enrolled
     *
     * PostCondition : Clicks on Cancel Benefit For All button and accepts alert then navigates to next page as per group configuration
     *
     * </pre>
     */
    @RobotKeyword
    public void clickOnCancelBenefitForAll()
    {
        try{
            Thread.sleep(2000);

            performAction.click(cancelBenefitForAll,
                    "Cancel Selected Benefit");

            Thread.sleep(2000);
            performAction.acceptAlert();
            Thread.sleep(2000);
        }
        catch (Exception e) {
            logger.info("Exception in clicking on Cancel Benefits For All");
            scr.capturePageScreenshot();
            throw new CustomException("Exception in Cancel Benefits For All"
                    + e.getMessage());
        }

    }
}
