package com.benefitfocus.robot.hradmin;


import java.util.concurrent.TimeUnit;
import java.util.List;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
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
public class ManageEmployee {

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

    // Locators for Update Login Information Page
    By manageEmployeeButton = By
            .xpath("//button[contains(text(),'Manage employee')]");

    By updateLoginInformation = By.linkText("Update Login Information");
    By newMemberId = By.id("newMemberID");
    By newPassword = By.id("newPassword");
    By confirmPasword = By.id("newConfirmPassword");
    By allowMemberToLogin = By.id("enableMemberID");
    By cancelSaving = By.id("//strong[contains(text(),'Cancel')]");
    By saveButton = By.xpath("//strong[contains(text(),'Save')]");
    By allowemployeetologincheckbox = By.id("enableMemberID");

    // Locators of Manage COBRA for Terminated Employee
    By manageCobraLink = By.linkText("Manage COBRA");
    By addPolicy = By.xpath("//strong[text()='Add Policy']");
    By enterNewEvent = By
            .xpath("//td[contains(text(),'Enter a new event')]/..//input");
    By chooseExistingEvent = By
            .xpath("//td[contains(text(),'Choose an existing event')]/..//input");
    By selectExistingEventType = By.name("memberEventKey");
    By selectEventType = By.name("newEventType");
    By eventDate = By.xpath("//input[contains(@id,'ventDate')]");
    By selectPlan = By.xpath("//table[@id='selectedPlanKey'][1]//td[1]/input");
    By nextButton = By.xpath("//*[text()='Next']");
    By effectiveDate = By.name("overallEffectiveDate");
    By employeeEffectiveDate = By.id("effectiveDate");

    // Locators for Cancel Benefits for COBRA

    By editButton = By.linkText("Edit");
    By cancelBenefitsForAll = By.linkText("Cancel Benefits for All");
    By clickOtherButton = By.xpath("//input[@id='other']");

    By terminateEmployee = By.linkText("Terminate Employee");
    By terminationDate = By.id("terminationDate");
    By disableLogin = By.id("disableLogin-0");
    //By terminationReasonType = By.id("terminationReasonType");
    By terminationReasonType = By.xpath("//*[@id='terminationReasonType' or @id='isTerminationForGrossMisconduct']");
    By disableLoginStatusInTerminationYes = By.id("disableLogin-0");
    By disableLoginStatusInTerminationNo = By.id("disableLogin-1");
    By terminationNextButton = By.linkText("Next");
    By terminationSaveButton = By.id("innerLinksave");
    By updateTerminationDetails = By.linkText("Update Termination Details");
    By terminationReason = By.xpath("//div[contains(@id,'r')]/table/tbody/tr/td[contains(text(),'Termination - ')]");

    By rehireEmployee = By.linkText("Rehire Employee");
    By rehireDate = By.id("rehireDate");
    By terminationDateDuringRehire = By
            .xpath("//label[contains(text(),'Termination Date')]/../../td[2]/div");

    By rehireSaveButton = By.linkText("Save");
    By lastRehireDate = By
            .xpath("//label[contains(text(),'Last Rehire Date')]/../../td[2]/div");
    By cobraStartDate = By.id("cobraStartDate");

    // Locators for Initiate Life Event

    By initiateLifeEventLink = By.linkText("Initiate Life Event");
    By cancelButton = By.linkText("Cancel");
    By initiateLifeEventforEmployeeButton = By.linkText("Initiate Life Event for Employee");
    By initiateaLifeEventforEmployee = By.xpath("//h1[text()='Initiate a Life Event for Employee']");
    By lifeEventType = By.xpath("//label[text()='Life Event Type']");
    By saveAndAddAnother = By.linkText("Save and Add Another");
    By eventDateInitialLifeEvent = By.id("eventDate");
    By editProfile = By.xpath("//h2[text()='Employee profile']//span[text()='Edit']");
    By address1 = By.id("address1");
    By city = By.id("city");
    By state = By.id("state");
    By zip = By.id("zip");
    By country = By.id("country");

    // Locators in Manage Employee
    By undoCategoryChangelink = By.linkText("Undo Category Change");
    By undoCategoryChangesMsg = By.xpath("//h1[text()='Undo Category Changes']");
    By currentBenefitsToBeCanceledMsg = By.xpath("//h3[text()='Current Benefits (To Be Canceled)']");
    By toBeRevertedToMsg = By.xpath("//h3[text()='To Be Reverted To']");
    By currentSelectionPlan = By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-default']//tr[2]/td[2]");
    By newSelectionPlan = By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-default']//tr[2]/td[3]");
    By revertToOriginalCategoriesBenefits = By.linkText("Revert to Original Categories & Benefits");
    By revertToOriginalSuccessMesg = By.xpath("//h2[contains(text(),'You have successfully completed the undo category change process for')]");
    By viewInMemberRolelink = By.linkText("View in Member Role");
    By hradminLogout = By.linkText("Logout");
    By logout = By.linkText("Log Out");
    By logoutToSeeLater = By.linkText("Log out to see latest changes");
    By dateExplanation = By.id("reasonEntry");

    // Locators in Employee Page
    By empDetailReport = By.linkText("Employee Detail Report");

    //Locators in Data&Reporting Page
    By standardReportsMainHeaderTable = By.xpath("//td[@class='organizerHierarchy']/div/table/tbody/tr[2]/td/a[text()='']");
    By standardRreportsLink = By.xpath("//span[text()='Standard reports']");
    By reportFormat = By.id("reportOutputType");
    By createReportButton = By.xpath("//strong[text()='Create Report']");
    By yourReportsLink = By.xpath("//span[text()='Your reports']");
    By personalEmail = By.id("homeEmail");

    // Locators on Terminate Employee page
    By employeeTerminationDueToMisconductReason = By.id("isTerminationForGrossMisconduct");
    By undoEmployeeTermination = By.linkText("Undo Employee Termination");
    By undoTerminationButton = By.linkText("Undo Termination");

    //Locators in MemberRole
    By employeeSummaryReport = By.xpath("//div[@id='important-docs']/div[1]/div/ul/li/a/p/span");
    By employeeDetailReport = By.linkText("Employee Detail Report");
    By hradminLogoutInMemberRole = By.xpath("//div[@id='heading']/div[1]/div/div[1]/header/div/div[4]/div/div/div/a/span");

    //Payroll
    By dateReason = By.id("reasonEntry");

    // Variables used in public methods
    String successMesg = "";
    String welcomeMessage = "//h1[contains(text(),'Welcome back')]";
    String COBRAOffer = "//div[@class='regionContent primaryRegionContent']/div";


    /*************
     * setter Methods for Update Login Information in Manage Employee
     *************/

    // Click Update Login Information Button
    private void clickUpdateLoginInformationLink() throws InterruptedException {
        performAction.click(manageEmployeeButton, "Manage Employee Button");
        performAction.click(updateLoginInformation,
                "Update Login Information Link");

    }

    // Enter New Login ID
    private void enterNewLoginID(String strNewLoginID) {
        performAction.enter(newMemberId, strNewLoginID,
                "Enter New Member/Login ID");
    }

    // Enter New Password
    private void enterNewPassword(String strNewPassword) {
        performAction.enter(newPassword, strNewPassword, "Enter New Password");
    }

    // Enter Confirm Password
    private void enterConfirmPassword(String strConfirmPassword) {
        performAction.enter(confirmPasword, strConfirmPassword,
                "Enter Confirm Password");
    }

    // Click Save Button
    private void saveButton() {
        performAction.click(saveButton, "Click on Save Button");
    }

	/*// Verify Success message on Update Login Information Page
    private void verifySucessMessageUpdateLoginPage(String verifySuccessMessage) {
		browser.getCurrentWebDriver().getPageSource()
		.contains(verifySuccessMessage);
	}*/

    // Verify Message
    private void verifyMessage(String verifySuccessMessage) {
        browser.getCurrentWebDriver().getPageSource()
                .contains(verifySuccessMessage);

    }

    /*************
     * setter Methods for Update Login Information in Manage Employee
     *************/

    // Click on Manage Cobra Button
    private void clickManageCobraLink() {
        //browser.getCurrentWebDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        performAction.click(manageEmployeeButton, "Manage Employee Button");
        performAction.click(manageCobraLink, "Manage Cobra");
    }

    // Click on Add Policy Button
    private void addPolicyButton(String strCOBRAOfferName) {
        int rowSize = 0;
        boolean offerFound = false;

        rowSize = browser.getCurrentWebDriver().findElements(By.xpath(COBRAOffer)).size();
        logger.info("Row Size = " + rowSize);
        if (rowSize > 0) {
            for (int i = 2; i <= rowSize; i++) {
                String OfferName = browser.getCurrentWebDriver().findElement(By.xpath(COBRAOffer + "[" + i + "]")).getText();
                if (OfferName.contains(strCOBRAOfferName)) {
                    logger.info("Offer Found : " + strCOBRAOfferName);
                    //browser.getCurrentWebDriver().findElement(By.xpath(COBRAOffer+"["+i+"]"+"//a")).click();
                    performAction.click(By.xpath(COBRAOffer + "[" + i + "]" + "//a"), "Add policy button");
                    offerFound = true;
                    break;
                }
            }
        } else {
            logger.info("No COBRA Offers Found");
        }

        if (!offerFound) {
            logger.info(strCOBRAOfferName + " Offer NOT available.");
        }
    }

    // Select Event Type
    private void selectEventType(String strSelectEventType) {
        performAction.select(selectEventType, strSelectEventType,
                "Select Event Type");
    }

    // Select Event Date
    private void enterEventDate(String strEnterEventDate) {
        if (strEnterEventDate.startsWith("d:")) {
            strEnterEventDate = strEnterEventDate.substring(2);
            performAction.clearEnter(eventDate,
                    utils.getDate(strEnterEventDate), "Enter Event Date");
        } else {
            performAction.clearEnter(eventDate, strEnterEventDate,
                    "Enter Event Date");
        }

    }

    // Click on Next Button
    private void nextButton() {
        performAction.waitUntilElementPresent(nextButton);
        performAction.click(nextButton, "Next Button");
    }

    // Enter Effective Date
    private void effectiveDate(String strenterEffectiveDate) {
        performAction.waitUntilElementPresent(effectiveDate);
        if (strenterEffectiveDate.startsWith("d:")) {
            strenterEffectiveDate = strenterEffectiveDate.substring(2);
            performAction.clearEnter(effectiveDate,
                    utils.getDate(strenterEffectiveDate),
                    "Enter Effective Date");
        } else {
            performAction.clearEnter(effectiveDate, strenterEffectiveDate,
                    "Enter Event Date");
        }
    }

    // Enter Effective Date
    private void employeeEffectiveDate(String strenterEffectiveDate) {
        if (strenterEffectiveDate.startsWith("d:")) {
            strenterEffectiveDate = strenterEffectiveDate.substring(2);
            performAction.clearEnter(employeeEffectiveDate,
                    utils.getDate(strenterEffectiveDate),
                    "Enter Effective Date");
        } else {
            performAction.clearEnter(effectiveDate, strenterEffectiveDate,
                    "Enter Event Date");
        }
    }

    /******************
     * Setter Methods for Cancel Benefits for All
     ****************************/

    // Click on Edit Button in Manage COBRA Page
	/*
	 * private void clickEditButton() { performAction.click(editButton,
	 * "Click on Edit Button"); }
	 */

    // Click on Cancel Benefits For All Button
    private void clickCancelBenefitsForAll() {
        performAction.click(cancelBenefitsForAll,
                "Click on Cancel Benefits For All Button");
    }

    // Accept Alert button
    private void acceptAlert() {
        performAction.acceptAlert();
    }

    // Click Other button
    private void clickOtherButton() {
        performAction.click(clickOtherButton, "Click on Other Button");
    }

    // Click revertToOriginalCategoriesBenefits
    private void clickRevertToOriginalCategoriesBenefits() {
        performAction.click(revertToOriginalCategoriesBenefits, "Revert To Original Categories &Benefits");
    }

    /******************************************************************************************/
    // click on save Employee profile
    private void clickTerminateEmployee() throws InterruptedException {
        utils.sleep(2000);
        performAction.highlightElement(manageEmployeeButton);
        Thread.sleep(1000);
        performAction.click(manageEmployeeButton, "Manage Employee Button");
        utils.sleep(2000);
        performAction.highlightElement(terminateEmployee);
        Thread.sleep(2000);
        performAction.click(terminateEmployee, "Terminate Employee link");
    }

    //Enable or Disable Employee Login
    private void disableEmployeeLoginInTermination(String strStatus) {
        if (strStatus.equalsIgnoreCase("Yes")) {
            performAction.click(disableLoginStatusInTerminationYes, "Employee Login Disabled After Termination ");
        } else {
            performAction.click(disableLoginStatusInTerminationNo, "Employee Login Enabled After Termination ");
        }

    }

    // Set termination date
    private void setTerminationDate(String strDate) {
        performAction.enter(terminationDate, strDate, "termination date");
    }

    // set the termination reason combo box
    private void selectTerminationReason(String strValuetoSelect) {
        performAction.select(terminationReasonType, strValuetoSelect,
                "Termination reason");
    }

    // click on save termination next button
    private void clickNextButtonOnTerminationPage() {
        performAction.click(terminationNextButton, "Next Button");
    }

    // click on save termination
    private void clickSaveButtonOnTerminationPage() {
        performAction.click(terminationSaveButton, "Save Button");
    }

    // click on save Employee profile
    private void clickRehireEmployee() {
        performAction.click(manageEmployeeButton, "Manage Employee Button");
        performAction.click(rehireEmployee, "Rehire Employee link");
    }

    // Set termination date
    private void setRehireDate(String strDate) {
        strDate = utils.getDate(strDate);
        performAction.enter(rehireDate, strDate, "rehire date");
    }

    // click on save termination
    private void clickSaveButtonOnRehirePage() {
        performAction.click(rehireSaveButton, "Save Button");
    }

    // click on save Employee profile
    private void verifyTerminationDateDuringRehire() {
        performAction.verify(terminationDateDuringRehire, "d:currentdate",
                "verifying the termination date on rehire page");
    }

    // click on Manage Employee Button
    private void clickManageEmployee() {
        utils.sleep(2000);
        performAction.waitUntilElementPresent(manageEmployeeButton);
        performAction.highlightElement(manageEmployeeButton);
        performAction.click(manageEmployeeButton, "Manage Employee Button");
    }

    // Click on Initiate Life Event
    private void clickInitiateLifeEvent() {
        performAction.click(initiateLifeEventLink,
                "initiate Life Event Link");
    }

    // Click on Undo Category Change
    private void clickUndoCategoryChange() {
        performAction.click(undoCategoryChangelink, "Undo Category Change ");
        performAction.verify(undoCategoryChangesMsg, "Undo Category Changes", "Undo Category Changes");
        performAction.verify(currentBenefitsToBeCanceledMsg, "Current Benefits (To Be Canceled)", "Current Benefits (To Be Canceled)");
        performAction.verify(revertToOriginalCategoriesBenefits, "Revert to Original Categories & Benefits", "Revert to Original Categories & Benefits Button");
    }

    // Click on Initiate Life Event
    private void clickInitiateaLifeEventforEmployee() {
        performAction.click(initiateLifeEventforEmployeeButton,
                "initiate a Life Event for Employee Button");
        String strMessage = browser.getCurrentWebDriver().findElement(initiateaLifeEventforEmployee).getText();
        performAction.verifyMessage(strMessage);
        strMessage = browser.getCurrentWebDriver().findElement(lifeEventType).getText();
        performAction.verifyMessage(strMessage);
        scr.capturePageScreenshot();
    }

    // select Life Event Option
    private void selectLifeEventType(String strLifeEventType) {
        String loc = "//select[@id='eventType']/option[text()='" + strLifeEventType + "']";
        browser.getCurrentWebDriver().findElement(By.xpath(loc)).click();
    }

    // Enter COBRA Start Date while Adding Policy for COBRA Exception
    private void enterCobraStartDate(String strCobraStartDate) {
        if (strCobraStartDate.startsWith("d:")) {
            strCobraStartDate = strCobraStartDate.substring(2);
            performAction.clearEnter(cobraStartDate,
                    utils.getDate(strCobraStartDate),
                    "Enter COBRA Start Date");
        } else {
            performAction.clearEnter(cobraStartDate, strCobraStartDate,
                    "Enter COBRA Start Date");
        }
    }

    // click on edit profile button
    private void clickEditProfile() {
        performAction.click(editProfile, "Edit Profile Button");
    }

    // set the address1 textbox
    private void setAddress1Field(String straddress1) {
        performAction.clearEnter(address1, straddress1, "Address Text Box");
    }

    // set the city text box
    private void setCityField(String strcity) {
        performAction.clearEnter(city, strcity, "City Text Box");
    }

    // set the state combo box
    private void selectStateField(String strValuetoSelect) {
        performAction.select(state, strValuetoSelect, "State Combo Box");
    }

    // set the zip text box
    private void setZipField(String strzip) {
        performAction.clearEnter(zip, strzip, "Zip Code Text Box");
    }

    // set the Country combo box
    private void selectcountryField(String strValuetoSelect) {
        performAction.select(country, strValuetoSelect, "Country Combo Box");
    }

    // click View In Member Role under Manage Employee
    private void clickViewInMemberRole() {
        performAction.click(viewInMemberRolelink, "View In Member Role");
    }

    // click on Logout Button
    private void clickLogoutToSee() {
        performAction.click(logoutToSeeLater, "Log Out To See Later");
    }

    //click on Standard Reporting MainMenuTab
    private void stadardReportMainMenuTab(String standardReportsMainMenu) {

        //Click on  standardRreportsLink
        performAction.click(standardRreportsLink, "Click on Standard Reports Link");
        By menu = By.xpath("//td[@class='organizerHierarchy']/div/table/tbody/tr[2]/td/a[contains(text(),'" + standardReportsMainMenu + "')]");
        performAction.click(menu, "Click on Standard Reports Main Menu Tab");
    }

    //click on Standard Reporting SubMenuTab
    private void stadardReportsubMenuTab(String standardReportSubMenu, String reportFormatValue) throws InterruptedException {
        By menu = By.linkText(standardReportSubMenu);
        performAction.click(menu, "Click on Standard Reports Submenu Tab");
        scr.capturePageScreenshot();
        performAction.select(reportFormat, reportFormatValue, "Report Format Select Box");
        scr.capturePageScreenshot();
        //verify weather Effective DAe is dispalyed are not
        if (performAction.isElementPresent(By.xpath("//label[@for='endDate']"))) {
            performAction.clearEnter(By.id("endDate"), "currentdate", "Effective Date");
        }
        scr.capturePageScreenshot();
        //click on Create Report Button
        performAction.click(createReportButton, "Create Report Button");
        //wait command to grenerate pdf Report
        Thread.sleep(9000);
        performAction.click(yourReportsLink, "Your  Reports Link");
        //wait command to grenerate pdf Report
        Thread.sleep(9000);
        scr.capturePageScreenshot();
    }

    //set the personal email text box
    private void setPersonalEmailField(String strPersonalEmail) {
        performAction.enter(personalEmail, strPersonalEmail, "Enter email into personal email filed");
    }

    // Click Employee Summary Report
    private void clickEmployeeSummaryReport() throws InterruptedException {

        performAction.selectLatestWindow("Get started");
        browser.getCurrentWebDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        performAction.click(employeeSummaryReport, "Employee Summary Report");
        logger.info("Employee Summary Report clicked");
    }

    // Click Employee Detail Report
    private void clickEmployeeDetailReport() throws InterruptedException {

        performAction.selectLatestWindow("Get started");
        browser.getCurrentWebDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        performAction.click(employeeDetailReport, "Employee Detail Report");
        logger.info("Employee Detail Report clicked");
    }

    // click on Undo Employee Termination
    private void undoEmployeeTermination() {
        performAction.click(undoEmployeeTermination, "Undo Employee Termination");

        performAction.click(undoTerminationButton, "Undo Termination Button");
    }

    // Uncheck allow employee login check box
    private void unCheckAllowEmployeeLoginCheckbox() {
        performAction.click(allowemployeetologincheckbox, "Click on Allow Employee to login checkbox");
    }

    // click on update Termination Details
    private void clickUpdateTerminationDetails() {
        performAction.jsclick(updateTerminationDetails, "Update Termination Details Link");
    }

    //enterTextInDateExplanationTextBox
    private void enterTextInDateExplanationTextBox() {
        performAction.enter(dateExplanation, "test", "Date Explanation text box");
    }

    // Click on Edit Policy Button
    private void editPolicyButton(String strCOBRAOfferName) {
        int rowSize = 0;
        boolean offerFound = false;

        rowSize = browser.getCurrentWebDriver().findElements(By.xpath(COBRAOffer)).size();
        logger.info("Row Size = " + rowSize);
        if (rowSize > 0) {
            for (int i = 2; i <= rowSize; i++) {
                String OfferName = browser.getCurrentWebDriver().findElement(By.xpath(COBRAOffer + "[" + i + "]")).getText();
                if (OfferName.contains(strCOBRAOfferName)) {
                    logger.info("Offer Found : " + strCOBRAOfferName);
                    browser.getCurrentWebDriver().findElement(By.xpath(COBRAOffer + "[" + i + "]" + "//a")).click();
                    offerFound = true;
                    break;
                }
            }
        } else {
            logger.info("No COBRA Offers Found");
        }

        if (!offerFound) {
            logger.info(strCOBRAOfferName + " Offer NOT available.");
        }
    }

    // Enter reason to change Effective Date
    private void enterReasonToChangeEffectiveDate(String strDateChangeReason) {

        utils.sleep(10000);

        if (performAction.isElementPresent(dateReason)) {
            performAction.enter(dateReason, strDateChangeReason,
                    "Giving data for Date Explanation");
            performAction.click(nextButton, "Next Button in Medicare page");
        }

    }

    //Method to select the benefit reinstatement
    private void selectBenefitReinstatement(String reinstatement) {

        System.out.println("Benefit Reinstatement :"+reinstatement);

        String benefitReinstatement="//table[@id='reinstateChoice']//tr";

        List<WebElement> elements = browser.getCurrentWebDriver().findElements(
                By.xpath(benefitReinstatement));

        System.out.println(" Webelements size :"+ elements.size());

        for (int i=1;i<=elements.size();i++) {
            String reinstatementText = browser.getCurrentWebDriver().findElement(By.xpath(benefitReinstatement+"["+i+"]/td[2]/label")).getText();

            System.out.println("=====Passed :"+reinstatement);
            System.out.println("=====From App :"+reinstatementText);

            System.out.println("Before IF");
            if (reinstatement.trim().equalsIgnoreCase(reinstatementText.trim())) {
                System.out.println("After IF");
                utils.sleep(3000);
                By radioButton=By.xpath(benefitReinstatement+"["+i+"]/td[1]//input");
                System.out.println("radio button :"+radioButton);
                performAction.jsclick(radioButton, "Reinstatement Radio Button");
                break;
            }
        }

        performAction.click(nextButton,
                "clicking next button in benefit reinstatement page");
    }

    //Method to select the reinstate strategy in combobox
    private void selectReinstateStrategy(String strategy)
    {
        System.out.println("Benefit Reinstatement Strategy :" + strategy);
        By reinstateStrategy = By.id("rehireStrategy");

        if (performAction.isElementPresent(reinstateStrategy)) {

            performAction.select(reinstateStrategy, strategy,
                    "selecting the reinstate strategy value");

            performAction.click(nextButton,
                    "clicking next button in benefit reinstate strategy page");
        }
    }





    /**
     * <pre>
     * Author  : Rajeswari Nimmala
     *
     * Description : keyword or method is used to undo terminate the Employee in HR admin role
     *
     * Role : HR Role
     *
     * PreCondition : Employee overview page in HR Admin role
     *
     * PostCondition :Undo Employee Terminate with given  date and undo termination
     *
     * <b>Parameters & Example </b>
     *
     * Java file Name :  ManageEmployee.java
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({})
    public void undoEmployeeTerminationInHRRole() {
        try {
            this.clickManageEmployee();

            this.undoEmployeeTermination();

        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception while undo employee terminate "
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Sekhar Tirumala
     *
     * Description : keyword or method is used to see the Standard Reports  in HR admin role
     *
     * Role : HR Role
     *
     * PreCondition : Data & Reporting page in HR Admin role
     *
     * PostCondition : Standard Reports Task List Report successfully generated
     *
     * <b>Parameters & Example </b>
     *
     * | strStandardMenuName  | strStandardReportSubMenu   |
     * | Administrative | Task List  |  PDF /Excel Compatible (CSV) |
     *
     * Java FileName : ManageEmployee.java
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strStandardMenuName", "strStandardReportSubMenu", "reportFormatValue"})
    public void generateStandardReportsFromDataAndReportingTabInHrRole(String standardReportsMainMenu, String standardReportSubMenu, String reportFormatValue) {

        try {
            this.stadardReportMainMenuTab(standardReportsMainMenu);
            this.stadardReportsubMenuTab(standardReportSubMenu, reportFormatValue);

        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception while Generating Employee Reports "
                    + e.getMessage());

        }

    }


    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : keyword or method is used to terminate the Employee in HR admin role
     *
     * PreCondition : Employee overview page in HR Admin role
     *
     * PostCondition : Terminates employee with given reason and date and save the termination
     *
     * <b>Parameters & Example </b>
     *
     * | strDate - date of employee termination | strTerminationReason - termination reason type or td:terminationreason |
     * | d:currentdate | Voluntary / Involuntary / Involuntary due to Gross Misconduct  |
     * Note: If Login for the employee needs to be enabled or disabled, the second parameter can be passed as json parameter (td:) and include TerminationReason:Yes/No within it.
     *
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strDate", "strTerminationReason"})
    public void performEmployeeTermination(String strDate,
                                           String strTerminationReason) {
        String reasonForTermination;
        try {
            this.clickTerminateEmployee();
            Thread.sleep(1000);
            if (strDate.startsWith("d:")) {
                strDate = strDate.substring(2);
                this.setTerminationDate(utils.getDate(strDate));
            } else {
                this.setTerminationDate(strDate);
            }
            //If Login for the employee needs to be enabled or disabled
            if (strTerminationReason.startsWith("td:")) {
                strTerminationReason = strTerminationReason.substring(3);
                try {

                    Object object;
                    JSONObject fields = ReadJsonTestData.getTestData(strTerminationReason
                            .toLowerCase());

                    //Termination Reason Select Box
                    object = fields.get("terminationreason");
                    reasonForTermination = object.toString();
                    if (object != null) {

                        this.selectTerminationReason(reasonForTermination);

                    } else {
                        System.out.println("Reason Drop Down Is Disabled");
                    }

                    object = fields.get("disablelogin");
                    String disablelogin = object.toString();
                    this.disableEmployeeLoginInTermination(disablelogin);
                } catch (Exception e) {
                    logger.info("Exception while reading json data in terminating employee "
                            + e.getMessage());

                    throw new CustomException(
                            "Exception while reading json data in terminating employee "
                                    + e.getMessage());

                }
                strTerminationReason = reasonForTermination;
            } else {
                this.selectTerminationReason(strTerminationReason);
                Thread.sleep(1000);
            }

            this.clickNextButtonOnTerminationPage();
            Thread.sleep(1000);
            if (!strTerminationReason.equalsIgnoreCase("Death of employee")) {
                logger.info("Tremination Reason : " + browser.getCurrentWebDriver().
                        findElement(terminationReason).getText());
            }
            performAction.verifyMessage("Terminate Employment");
            this.verifyCobraEligilityWhileTermination(strTerminationReason);
            scr.capturePageScreenshot();
            this.clickSaveButtonOnTerminationPage();

            if (strTerminationReason.equalsIgnoreCase("Death of employee")) {
                this.verifyMessage("Deceased");
                scr.capturePageScreenshot();
            } else if (strTerminationReason.contains("Retirement")) {
                this.verifyMessage("Terminated | Retired");
                scr.capturePageScreenshot();
            } else {
                this.verifyMessage("Terminated");
                scr.capturePageScreenshot();
            }

        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception while terminating employee "
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Sunitha Yerra
     *
     * Role :   HR Role
     *
     * Description : keyword or method is used to update termination details of the Employee in HR admin role
     *
     * PreCondition : Group Details - Basics->Cobra Setup->"Termination Reasons" should be "Yes"
     *
     * PostCondition : Able to update the termination reasons for an employee even after his termination
     *
     * <b>Parameters </b>
     *
     * strTerminationReason - Voluntary / Involuntary / Involuntary due to Gross Misconduct / Death of Employee
     *
     * Java File Name :  ManageEmployee.Java
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strTerminationReason"})
    public void updateTerminationDetails(String strTerminationReason) {
        try {

            performAction.waitUntilElementPresent(manageEmployeeButton);
            this.clickManageEmployee();
            performAction.waitUntilElementPresent(updateTerminationDetails);
            this.clickUpdateTerminationDetails();
            String inputReason = (strTerminationReason.toUpperCase()).replace(" ", "_");
            performAction.click(By.xpath("//input[@id='terminationReason[" + inputReason + "]']"), "input reason");
            scr.capturePageScreenshot();
            this.saveButton(); // Clicks on Save button
        } catch (Exception e) {
            logger.info("Failed to update termination details " + e.getMessage());
            scr.capturePageScreenshot();
            throw new CustomException("Failed in Update Termination Details " + e.getMessage());
        }


    }


    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : VerifyCobra eligibility while terminating the Employee in HR admin role
     *
     * PreCondition : Employee termination functionality
     *
     * PostCondition : Verifys the cobra eligibility before saving the termination
     *
     * <b>Parameters & Example </b>
     *
     * | strTerminationReason - termination reason type  |
     * | Voluntary / Involuntary / Involuntary due to Gross Misconduct |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strTerminationReason"})
    public void verifyCobraEligilityWhileTermination(String strTerminationReason) {

        try {
            if (strTerminationReason.equalsIgnoreCase("voluntary"))
                performAction.verifyMessage("Termination - COBRA Eligible");
            else if (strTerminationReason
                    .equalsIgnoreCase("Involuntary due to Gross Misconduct"))
                performAction.verifyMessage("Termination - COBRA Ineligible");
            else if (strTerminationReason
                    .equalsIgnoreCase("Death of employee"))
                performAction.verifyMessage("Death of employee - COBRA Eligible");
            else if (strTerminationReason
                    .equalsIgnoreCase("Retirement (Not COBRA Eligible)"))
                performAction.verifyMessage("Termination - COBRA Ineligible");
            else if (strTerminationReason
                    .equalsIgnoreCase("Retirement (COBRA Eligible)"))
                performAction.verifyMessage("Termination - COBRA Eligible");

        } catch (Exception e) {
            throw new CustomException(
                    "Exception while verifying the cobra eligibility while terminating employee."
                            + e.getMessage());
        }
    }

    /**
     * 'performEmployeeRehire' keyword or method is used to rehire an the
     * Employee in HR admin role
     *
     * <pre>
     * <b>Parameters :</b>
     * | strDate - date of employee rehire date |
     * <b>Example :</b>
     * | d:currentdate |
     * </pre>
     */
    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : keyword or method is used to rehire the terminated Employee in HR admin role
     *
     * PreCondition : Terminated Employee overview page in HR Admin role
     *
     * PostCondition : Rehired employee with given date.
     *
     * <b>Parameters & Example </b>
     *
     * | strDate - date of employee rehire |
     * | d:currentdate |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strDate"})
    public void performEmployeeRehire(String strDate) {

        try {

            //this.clickRehireEmployee();
            this.navigateToManageEmployeeInHRRole("Rehire Employee");
            Thread.sleep(1000);
            performAction.verifyMessage("Rehire Employee");
            this.verifyTerminationDateDuringRehire();
            strDate = "d:currentdate:d:1";
            this.setRehireDate(strDate);
            Thread.sleep(1000);
            this.clickSaveButtonOnRehirePage();
            performAction.verify(lastRehireDate, strDate,
                    "rehire date verification");

        } catch (Exception e) {
            throw new CustomException("Exception while rehiring employee "
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description   : 'Manage Cobra and Add Policy' keyword or method is used to click on 'Add Policy' button against Benefit Name from Manage COBRA page.
     *
     * PreCondition  : HR Employee Should be Terminated and Manage COBRA Option should be displayed.
     *
     * PostCondition : Able to Add COBRA Policy/Benefits.
     *
     * <b>Parameters & Example </b>
     *
     * | COBRA Benefit Name |
     * | COBRA Medical Offer 2016 |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strCOBRAOfferName"})
    public void manageCobraAndAddPolicy(String strCOBRAOfferName) {
        try {
            strCOBRAOfferName = utils.getValue(strCOBRAOfferName);
            //this.clickManageCobraLink();
            this.addPolicyButton(strCOBRAOfferName);
            scr.capturePageScreenshot();

        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception in Managing COBRA of Employee " + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description   : 'Update Login Information Of Employee' keyword or method is used to update Login information of Employee from Manage Employee link in HR admin role.
     *
     * PreCondition  : HR Employee Should be created.
     *
     * PostCondition : New Login Id is created and ready to Login.
     *
     * <b>Parameters & Example </b>
     *
     * | New Member Login ID | New Password |
     * | RNDnewmemberloginid | bfEnrol1     |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strNewLoginID", "strNewPassword"})
    public void updateLoginInformationOfEmployee(String strNewLoginID,
                                                 String strNewPassword) {

        try {
            if ((strNewLoginID.equalsIgnoreCase("none") || (strNewLoginID == null))
                    && (strNewPassword.equalsIgnoreCase("none") || (strNewPassword == null))) {
            	this.clickUpdateLoginInformationLink();
                this.unCheckAllowEmployeeLoginCheckbox();
                this.saveButton();
                this.verifyMessage("The member is not allowed to log in.");
                scr.capturePageScreenshot();
            } else {
                this.clickUpdateLoginInformationLink();
                String newLoginId = utils.getValue(strNewLoginID.toString());
                this.enterNewLoginID(newLoginId);
                browser.hMap.put("NewLoginID", newLoginId);
                this.enterNewPassword(strNewPassword);
                this.enterConfirmPassword(strNewPassword);
                this.saveButton();
                performAction.verifyMessage("The password has been changed.");
                performAction.verifyMessage("The login ID has been changed.");
                // this.verifySucessMessageUpdateLoginPage("The password has been changed.");
                // this.verifySucessMessageUpdateLoginPage("The login ID has been changed.");
                scr.capturePageScreenshot();
            }
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in updating login information of Employee "
                    + e.getMessage());
            throw new CustomException(
                    "Exception in updating login information of Employee "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description  : 'Enter Effective Date' keyword used to enter Effective Date or End Date in HR Role.
     *
     * PreCondition : Navigate to Effective Date or End Date Text box.
     *
     * PostCondition: Able to enter Effective date and click on Next button.
     *
     * <b>Parameters & Example </b>
     *
     * | Effective Date |
     * | d:currentdate  |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strEffectiveDate"})
    public void enterEffectiveDate(String strEffectiveDate) {
        try {
            if (strEffectiveDate.startsWith("td:")) {
                strEffectiveDate = strEffectiveDate.substring(3);

                Object object = null;
                JSONObject fields = ReadJsonTestData.getTestData(strEffectiveDate
                        .toLowerCase());
                object = fields.get("effectivedate");
                if (object != null) {
                    this.effectiveDate(object.toString());
                    this.nextButton();
                }

            } else if (strEffectiveDate.equalsIgnoreCase("default")) {
                this.nextButton();
            } else {
                this.effectiveDate(strEffectiveDate);
                this.nextButton();

                if(performAction.isElementPresent(dateExplanation)){
    				this.enterTextInDateExplanationTextBox();
    				this.nextButton();
    			}

            }
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in entering Effective Date of Employee "
                    + e.getMessage());
            throw new CustomException(
                    "Exception in entering Effective Date of Employee "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description   : 'Select Qualifying Event' keyword used to select new or existing qualifying event for an Employee.
     *
     * PreCondition  : Navigate to Qualifying Event Page.
     *
     * PostCondition : Able to Select Qualifying Event and click on Next Button.
     *
     * <b>Parameters & Example </b>
     *
     * | Type of Event  | strEventTypeAndDate            |
     * | new event      | Leave of Absence,d:currentdate |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"StrTypeOfEvent", "strEventTypeAndDate"})
    public void selectQualifyingEvent(String StrTypeOfEvent,
                                      String strEventTypeAndDate) {
        try {
            String splitEventTypeAndDate[] = strEventTypeAndDate.split(",");
            if (StrTypeOfEvent.toLowerCase().contains("new event")) {
                performAction.click(enterNewEvent,
                        "Choose New Qualifying Event");
                this.selectEventType(splitEventTypeAndDate[0]);
                this.enterEventDate(splitEventTypeAndDate[1]);
            } else if (StrTypeOfEvent.toLowerCase().contains("cobra exception event")) {
                logger.info("Selecting Qualifying Event from COBRA Exception page");
                this.selectEventType(splitEventTypeAndDate[0]);
                this.enterEventDate(splitEventTypeAndDate[1]);
            } else {
                performAction.click(chooseExistingEvent,
                        "Choose Existing Qualifying Event");
            }
            this.nextButton();
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in Selecting Qualifying Event of Employee "
                    + e.getMessage());
            throw new CustomException(
                    "Exception in Selecting Qualifying Event of Employee "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description   : 'Perform Cancel All Benefits' keyword used to click on Cancel Benefits for All button after clicking on Edit button from Manage COBRA.
     *
     * PreCondition  : COBRA Policy should be added to Terminated Employee.
     *
     * PostCondition : Able to cancel COBRA benefits.
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    public void performCancelBenefitsForAll() {
        try {

            this.clickCancelBenefitsForAll();
            this.acceptAlert();
            scr.capturePageScreenshot();
            // this.clickOtherButton();
            // this.nextButton();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in Cancel Benefits of an Employee "
                    + e.getMessage());
            throw new CustomException(
                    "Exception in Cancel Benefits of an Employee "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description   : "Save COBRA Policy" keyword used to Click on Save Button from COBRA Benefits page.
     *
     * PreCondition  : Save Button should be displayed after entering Effective date from COBRA Policy flow.
     *
     * PostCondition : Able to save COBRA Policy.
     *
     * <b>Parameters & Example </b>
     *
     * | Action to Be Performed |
     * | Save |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strActionToBePerformed"})
    public void saveCobraPolicy(String actiontobeperformed) {
        performAction.isElementPresent(saveButton);
        performAction.click(saveButton, "Click on Save Button");
        scr.capturePageScreenshot();
    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description   : Manage Cobra And Edit Policy keyword or method is used to click on 'Edit' link against Benefit Name from Manage COBRA.
     *
     * PreCondition  : COBRA Policy Should be added and Edit Link Should be displayed in HR Admin Role
     *
     * PostCondition : Able to click on Edit link against Benefit Name.
     *
     * <b>Parameters & Example </b>
     *
     * | Benefit Name | Edit Value |
     * | Medical 2015 | Plan |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strBenefitName", "strEdit"})
    public void manageCobraAndEditPolicy(String strBenefitName, String strEdit) {
        try {
            //this.clickManageCobraLink();
            //performAction.verifyMessage("COBRA Benefits");
            this.editPolicyButton(strBenefitName);

            if (strEdit.equalsIgnoreCase("none")) {
                logger.info("No Edit Action");
            } else {
                browser.getCurrentWebDriver().findElement(By.xpath("//span[contains(text(),'" + strEdit + "')]/../..//a")).click();
            }
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in Manage Cobra and Edit Policy of an Employee"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in Manage Cobra and Edit Policy of an Employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Behara
     *
     * Description  : Manage Cobra And Edit Policy keyword or method is used to click on 'Edit' link against Benefit Name from Manage COBRA.
     *
     * Role : HR Admin Role
     *
     * PreCondition  : COBRA Policy Should be added and Edit Link Should be displayed in HR Admin Role
     *
     * PostCondition : Able to click on Edit link against Benefit Name.
     *
     * <b>Parameters & Example </b>
     *
     * | strLifeEventType |strEventDate  |
     *
     * | Birth | d:currentdate |
     *
     * Java file Name : ManageEmployee.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"strLifeEventType", "strEventDate"})
    public void initiateALifeEventForEmployee(String strLifeEventType, String strEventDate) {
        strLifeEventType = utils.getValue(strLifeEventType);
        this.clickManageEmployee();
        this.clickInitiateLifeEvent();
        this.clickInitiateaLifeEventforEmployee();
        this.selectLifeEventType(strLifeEventType);
        this.nextButton();
        this.enterEventDate(strEventDate);
        scr.capturePageScreenshot();
        this.saveButton();
        scr.capturePageScreenshot();
    }

    /**
     * <pre>
     * Author  : Nagarjuna Behara
     *
     * Description   : viewInMemberRole keyword or method is used to click View In Member Role Under Manage Employee
     * when Login as HR Admin
     *
     * Role : HR Admin Role
     *
     * PreCondition  : User Should be in Employee page in HR Admin Login page
     *
     * PostCondition : User Should be Navigate to Member Role through HR Admin
     *
     * <b>Parameters & Example </b>
     *
     * | None |
     *
     * Java file Name : ManageEmployee.java
     * </pre>
     **/

    @RobotKeyword
    public void viewInMemberRole() {
        this.clickManageEmployee();
        this.clickViewInMemberRole();
        performAction.selectLatestWindow("Get Started ");
        performAction.verify(welcomeMessage, "Welcome back", "Message");
        scr.capturePageScreenshot();
    }

    /**
     * <pre>
     * Author  : Nagarjuna Behara
     *
     * Description   : undoCategoryChange keyword or method is used to click on Undo Category Change Under Manage Employee
     * when Login as HR Admin
     *
     * Role : HR Admin Role
     *
     * PreCondition  : User Should be in Employee page in HR Admin Login page
     *
     * PostCondition : User Should be Navigate to Undo Category Change page in HR Admin
     *
     * <b>Parameters & Example </b>
     *
     * | None |
     *
     * Java file Name : ManageEmployee.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"oldcategory", "newcategory"})
    public void undoCategoryChange(String oldcategorydata, String newcategorydata) {
        this.clickManageEmployee();
        this.clickUndoCategoryChange();
        if (newcategorydata.startsWith("td:"))
            newcategorydata = newcategorydata.substring(3);
        if (oldcategorydata.startsWith("td:"))
            oldcategorydata = oldcategorydata.substring(3);
        try {
            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(newcategorydata
                    .toLowerCase());

            object = fields.get("categories");
            performAction.verify(newSelectionPlan, object.toString(), "New Selected Plan");
            fields = ReadJsonTestData.getTestData(oldcategorydata
                    .toLowerCase());

            object = fields.get("category");
            performAction.verify(currentSelectionPlan, object.toString(), "Current Selected Plan");
        } catch (Exception e) {
            logger.info("Exception in performing undoCategory Changes "
                    + e.getMessage());
            throw new CustomException(
                    "Exception in performing undoCategory Changes  "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Behara
     *
     * Description   : revertToOriginalCategoryBenefits keyword or method is used to click on Undo Category Change Under Manage Employee
     * when Login as HR Admin
     *
     * Role : HR Admin Role
     *
     * PreCondition  : User Should be in Employee page in HR Admin Login page
     *
     * PostCondition : User Should be Navigate to Undo Category Change page in HR Admin
     *
     * <b>Parameters & Example </b>
     *
     * | None |
     *
     * Java file Name : ManageEmployee.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"lastname"})
    public void revertToOriginalCategoryBenefits(String lastName) {
        try {
            if (lastName.startsWith("HMV")) {
                lastName = utils.getValue(lastName);
            }
            this.clickRevertToOriginalCategoriesBenefits();
            successMesg = browser.getCurrentWebDriver().findElement(revertToOriginalSuccessMesg).getText();
            if (successMesg.contains(lastName)) {
                performAction.verify(revertToOriginalSuccessMesg, successMesg, "Success Mesg");
            }


        } catch (Exception e) {
            logger.info("Exception in performing click on RevertToOriginalCategoryBenefits "
                    + e.getMessage());
            throw new CustomException(
                    "Exception in performing RevertToOriginalCategoryBenefits "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Behara
     *
     * Description   : logOutToSeeLatestChanges keyword or method is used to click Logout To See LatestChanges Button
     *
     * Role : HR Admin Role
     *
     * PreCondition  : User should navigate to "view in Member role" page
     *
     * PostCondition : Logout from both Member role and Hr Admin Role
     *
     * <b>Parameters & Example </b>
     * | strWindowTitle  |
     * Java file Name :  ManageEmployee.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"strwindowtitle"})
    public void logOutToSeeLatestChanges(String strWindowTitle) {
        if (performAction.isElementPresent(hradminLogoutInMemberRole)) {
            performAction.click(hradminLogoutInMemberRole, "HR Admin Logout in Member role");
        } else {
            performAction.click(hradminLogout, "HR Admin Logout");
        }
        performAction.click(logout, "Log out Button HR Admin");
        performAction.selectLatestWindow(strWindowTitle);
        scr.capturePageScreenshot();
        this.clickLogoutToSee();
    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description  : 'Enter COBRA Start Date' keyword used to enter COBRA Start Date in HR Role.
     *
     * PreCondition : Navigate to COBRA Start Date Text box.
     *
     * PostCondition: Able to enter COBRA Start date and click on Next button.
     *
     * <b>Parameters & Example </b>
     *
     * | Start Date |
     * | d:currentdate  |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strStartDate"})
    public void enterCOBRAStartDate(String strStartDate) {
        try {
            //browser.getCurrentWebDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            performAction.verifyMessage("COBRA Start Date");
            this.enterCobraStartDate(strStartDate);
            this.nextButton();
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in entering COBRA Start Date while adding policy in COBRA Exception "
                    + e.getMessage());
            throw new CustomException(
                    "Exception in entering COBRA Start Date while adding policy in COBRA Exception "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Thallam
     *
     * Description  : 'changeEmloyeeAddress' keyword is used to change the employee address in HR role
     *
     * Role: Hr Role
     *
     * PreCondition : Member must be inMember Overview page in HR role.
     *
     * PostCondition: Addres will be updated and redirected to overview page.
     *
     * <b>Parameters & Example </b>
     *
     * | strAddressTagName |
     * | td:addressdata  |
     *
     * JavaFileName  : ManageEmployee.java
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strTagName"})
    public void changeEmloyeeAddress(String strTagName) {
        if (strTagName.startsWith("td:"))
            strTagName = strTagName.substring(3);
        try {

            this.clickEditProfile();
            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(strTagName
                    .toLowerCase());


            object = fields.get("address1");
            if (object != null) {
                this.setAddress1Field(object.toString());
            }

            object = fields.get("address1");
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
            object = fields.get("date");
            if (object != null) {
                this.employeeEffectiveDate(object.toString());
            }


            // click on save button
            this.saveButton();
            scr.capturePageScreenshot();
        } catch (Exception e) {
            throw new CustomException(
                    "Exception in entering change address fileds in edit profile page"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Behara
     *
     * Description : Keyword used to click on Employee Detailed Report in HR Admin Role
     * and verify the Employee name in the Report.
     *
     * PreCondition : User should be in Employee profile page log in as HR Admin.
     *
     * PostCondition : employeeDetailReport is verified in Employee Detailed Report
     *
     * <b>Parameters & Example </b>
     *
     * |  strSearchString |
     * | strSearchString - is used to verify the employee name in the report |
     * </pre>
     **/
    @RobotKeyword
    public void employeeDetailReport(String strSearchString) {
        strSearchString = utils.getValue(strSearchString);
        try {
            performAction.click(empDetailReport, "Employee Detail Report");
            Thread.sleep(80000);
            performAction.selectLatestWindow("Printed on");
            //browser.getCurrentWebDriver().switchTo().window("Printed on");
            String loc = "//address/div[contains(text(),'" + strSearchString + "')]";
            By employeeName = By.xpath(loc);
            if (performAction.isDisplayed(employeeName, "Employee Name")) {
                logger.info(employeeName + " is Displayed in the page");
                scr.capturePageScreenshot();
            }
            browser.getCurrentWebDriver().close();
            performAction.selectLatestWindow("Employee Detail Report");

        } catch (Exception e) {
            logger.warn("Exception occured verifying Employee Name " + strSearchString + " in the page. "
                    + e.getMessage());
            scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception occured verifying Employee Name " + strSearchString + " in the page. "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Rajeswari Nimmala
     *
     * Description   : clickEmployeeReport keyword or method is used to click required employee report
     *
     * Role : HR Role
     *
     * PreCondition  : User should navigate to "view in Member role" page
     *
     * PostCondition : Logout from both Member role and Hr Admin Role
     *
     * <b>Parameters & Example </b>
     * | strReport |
     * |  |
     *
     * Java file Name :  ManageEmployee.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"strReport"})
    public void clickReportFromViewInmemberRolePage(String strReport) {
        try {
            this.clickManageEmployee();
            this.clickViewInMemberRole();
            if (strReport.equalsIgnoreCase("Employee Summary Report")) {
                this.clickEmployeeSummaryReport();

            } else if (strReport.equalsIgnoreCase("Employee Detail Report")) {
                this.clickEmployeeDetailReport();
            }
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception while clicking report from view in member role "
                    + e.getMessage());

        }

    }

    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description   : 'Navigate to Manage Employee' keyword used to click on options available against Manage Employee in HR Role
     *
     * PreCondition  : Manage Employee Button should be available.
     *
     * PostCondition : Able to Select options against Manage Employee.
     *
     * <b>Parameters & Example </b>
     *
     * | strLinkText |
     * | Manage Medicare |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> ManageEmployee.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strLinkText"})
    public void navigateToManageEmployeeInHRRole(String strLinkText) {
        try {
            this.clickManageEmployee();
            performAction.click(By.linkText(strLinkText), "Click on Link against Manage Employee");
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception in Click on Link against Manage Employee"
                    + e.getMessage());
            throw new CustomException(
                    "Exception in Click on Link against Manage Employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description  : 'Enter New Effective Date' keyword used to enter new Effective Date or End Date in HR Role along with the reason for date change, if applicable.
     *
     * PreCondition : Navigate to Effective Date or End Date Text box.
     *
     * PostCondition: Able to enter Effective date and click on Next button and Will enter reason for Effective date change if available.
     *
     * <b>Parameters & Example </b>
     *
     * | Effective Date |
     * | d:currentdate  |
     *
     * | Effective Date Change Reason - Optional (Based on the group configuration) |
     * | <String>  |
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"strEffectiveDate", "strDateChangeReason"})
    public void enterNewEffectiveDate(String strEffectiveDate,
                                      String strDateChangeReason) {

        try {

            if (strEffectiveDate.startsWith("td:")) {
                strEffectiveDate = strEffectiveDate.substring(3);

                Object object = null;
                JSONObject fields = ReadJsonTestData
                        .getTestData(strEffectiveDate.toLowerCase());
                object = fields.get("effectivedate");
                if (object != null) {
                    this.effectiveDate(object.toString());
                    this.nextButton();

                    this.enterReasonToChangeEffectiveDate(strDateChangeReason);

                }

            } else if (strEffectiveDate.equalsIgnoreCase("default")) {
                this.nextButton();

            } else {
                this.effectiveDate(strEffectiveDate);
                this.nextButton();
                this.enterReasonToChangeEffectiveDate(strDateChangeReason);
            }
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception in Selecting Qualifying Event of Employee "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Teja Puchala
     *
     * Description : keyword or method is used to rehire the terminated Employee with reinstatement in HR admin role
     *
     * PreCondition : Terminated Employee overview page in HR Admin role
     *
     * PostCondition : Rehired employee with given date as per the reinstatement.
     *
     * <b>Parameters & Example </b>
     *
     * | strDate - date of employee rehire |
     * | d:currentdate |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({ "strDate" })
    public void performEmployeeReinstateRehire(String benefitReinstatement) {

        try {

            this.clickRehireEmployee();
            utils.sleep(3000);
            performAction.verifyMessage("Rehire Employee");

            if (benefitReinstatement.startsWith("td:"))
                benefitReinstatement = benefitReinstatement.substring(3);

            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(benefitReinstatement
                    .toLowerCase());
            System.out.println("fields = " + fields.toJSONString());

            object = fields.get("rehiredate");
            if (object != null) {
                this.setRehireDate(object.toString());
                performAction.click(nextButton, "Next button in Rehire Date page");
            }

            object = fields.get("reinstatement");
            if (object != null) {
                this.selectBenefitReinstatement(object.toString());
            }

            object = fields.get("reinstatestrategy");
            if (object != null) {
                this.selectReinstateStrategy(object.toString());
            }

            if(performAction.isElementPresent(nextButton))
            {
                performAction.click(nextButton, "Next button in Benefit reinstate page");
            }

            utils.sleep(1000);
            this.clickSaveButtonOnRehirePage();


        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception while rehiring employee :"
                    + e.getMessage());
        }
    }



}
