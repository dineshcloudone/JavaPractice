package com.benefitfocus.robot.hradmin;

import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

@RobotKeywords
public class HRAdminAddDependentPage {

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

    // Locators in Persons Covered page
    By waiveCoverage = By.linkText("Waive Coverage");
    By applyCoverage = By.linkText("Apply Coverage");
    By addAnotherDependentPersonsCovered = By.linkText("Add Another Dependent");
    By nextButton = By.linkText("Next");
    By addDependentPersonsCoveredPage = By.xpath("//a[contains(@href,'method=addPerson')]");
    By addBeneficiaryButton = By.linkText("Add Beneficiary");
    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By birthDate = By.id("dob");
    By dateOfBirth = By.id("dateOfBirth");
    By gender = By.id("gender");
    By relation = By.id("relationship");
    By ssn = By.id("rawSsn");
    // Locator in SSN after selecting Employee and Children as coverage Level
    // while adding HR Employee
    By ssnInCurrentBenefitsPage = By.id("SSN");

    By dependentSaveButton = By.id("save-edit");
    By saveAndGoToBenefitsbutton = By.linkText("Save and Go to Benefits");
    By addbutton = By.linkText("Add");
    By saveButton = By.linkText("Save");

    By beneficiaryType = By
            .cssSelector("select[id*='beneficiaryDataDisplays']");
    By allocation = By.cssSelector("input[name*='beneficiaryDataDisplays']");
    By dependentsAndBeneficiaries = By.linkText("Dependents / Beneficiaries");
    By dependentLinkOnBeneficiariesPage = By.linkText("Use for this Benefit");
    By birthDateOfAddBeneficiary = By.id("dateOfBirth");


    By dependentsTable = By.xpath("//div[@id='dependents']/div/ul/div");
    By beneficiariesNextButton = By.id("innerLinkbeneficiariesNextButton");
    By effDateNextbutton = By.linkText("Next");
    By dependents = By.linkText("Dependents");
    By beneficiaryLink = By.xpath("//span[text()='Beneficiaries']");
    By beneficiaryHide = By.id("hidden-nativeHtmlElement");
    By updateHide = By.xpath("//strong[text()='Update']");
    By addAnotherDependent = By.xpath("//span[text()='Add another dependent']");
    String benefitsList = "//div[@id='preFurlBenefitsDisplay']";
    String beneficiarys = "//td[@class='iscc']/div[1]/div[1]/table/tbody";


    By eidtLink = By.linkText("Edit");
    String dependentsList = "//*[@class='dependent-list list-group clearfix']";
    String dependentsNamesList = "//*[@class='dependent-list list-group clearfix']/div//p";
    String dependentListElements = "//*[@class='dependent-list list-group clearfix']//div[@class='row']";

    // Locators in Add Beneficiary Page
    By address1Person = By.id("address1-person");
    By cityPerson = By.id("city-person");
    By statePerson = By.id("state-person");
    By zipPerson = By.id("zip-person");
    By countryPerson = By.id("country-person");
    By phoneNumber = By.id("phoneNumber");

    //payroll
    By verifyTheDependentForBenefits = By
            .xpath("//div//strong[text()='Verify this person for all benefits?']/../../following-sibling::div//*[@class='toggle-group']");
    By backButton = By.id("back-detail");
    By addNewDependentButton = By.linkText("Add new dependent");
    By addDependentButton = By.linkText("Add Dependent");

    //Hide dependant
    By viewDetailsButton = By.xpath("//a[text()='View Details']");
    By hideSwitchButton = By.xpath("//label[text()='No']");

    /****************
     * Setter Methods for Persons Covered page
     *********************/

    // Click Waive Coverage Button
    private void clickWaiveCoverage(String strCoverage, String strLastName) {
        if (strLastName.startsWith("HMV")) {
            strLastName = utils.getValue(strLastName);
        }
        int personsCoveredCount = browser.getCurrentWebDriver().findElements(By.xpath("//tr[contains(@class,'Passive')]/td[2]")).size();
        int j = 3;
        for (int i = 0; i < personsCoveredCount; i++) {

            if (browser.getCurrentWebDriver().findElement(By.xpath("//tr[" + j + "][contains(@class,'Passive')]/td[2]")).getText().contains(strLastName)) {
                logger.info("Member Found " + browser.getCurrentWebDriver().findElement(By.xpath("//tr[" + j + "][contains(@class,'Passive')]/td[2]")).getText());
                performAction.isElementPresent(By.xpath("//tr[" + j + "][contains(@class,'Passive')]/td[2]/..//strong"));
                performAction.click(By.xpath("//tr[" + j + "][contains(@class,'Passive')]/td[2]/..//strong"), "Click Waive Coverage Button");
                break;
            }
            j++;
        }

    }

    // Click Next Button
    private void clickNextButton() {
        performAction.click(nextButton, "Click on Next Button");
    }

    // Click Add Another Dependent Link from Persons Covered Page
    private void clickAddAnotherDependentButton() {
        performAction.click(addAnotherDependentPersonsCovered, "Click Add Another Dependent Button");
    }

    // Click Add Dependent Button
    private void clickAddDependentButton() {
        if (performAction.isElementPresent(addDependentButton)) {
            performAction.click(addDependentButton, "Add Dependent Button");
        }
        /*
         * else{ performAction.click(addAnotherDependent,
		 * "Add Another Dependent"); }
		 */
    }

    // Click on Save Button
    private void saveButton() {
        performAction.click(saveButton, "Clik on Save Button");
    }

    // Click Add Beneficiary Button
    private void clickAddBeneficiaryButton() {
        performAction.click(addBeneficiaryButton, "Add Beneficiary Button");
    }

    // set the firstName text box
    private void setFirstNameField(String strfirstName) {
        performAction.clearEnter(firstName, strfirstName, "First Name Text Box");
    }

    // set the lastName text box
    private void setLastNameField(String strlastName) {
        performAction.clearEnter(lastName, strlastName, "Last Name Text Box");
    }

    // set the birthDate text box
    private void setBirthDateField(String strbirthDate) {

        if (performAction.isElementPresent(birthDate)) {
            boolean bool = browser.getCurrentWebDriver().findElement(birthDate)
                    .isEnabled();

            logger.info("Birthdate Text field enabled :" + bool);
            if (bool) {
                logger.info("Birthdate Text field enabled :" + bool
                        + " In side if condition");
                performAction.clearEnter(birthDate, strbirthDate,
                        "Date of Birth Text Box");
            }
        } else {
            boolean bool = browser.getCurrentWebDriver().findElement(dateOfBirth)
                    .isEnabled();

            logger.info("Birthdate Text field enabled :" + bool);
            if (bool) {
                logger.info("Birthdate Text field enabled :" + bool
                        + " In side if condition");
                performAction.clearEnter(dateOfBirth, strbirthDate,
                        "Date of Birth Text Box");
            }
        }
    }

    // set the SSN text box
    private void setSSNField(String strSSN) {
        performAction.clearEnter(ssn, strSSN, "SSN Text Box");
    }

    // set the SSN text box after selecting Employee and Children as coverage
    // Level while adding HR Employee
    private void enterSSNFromCurrentBenefitsPage(String strSSNBenefitsPage) {
        performAction.clearEnter(ssnInCurrentBenefitsPage, strSSNBenefitsPage,
                "SSN Text Box");
    }

    // click the gender radio button
    private void selectGenderField(String strgender) {
        performAction.select(gender, strgender, "Gender Select Box");

    }

    // select the relationship combo box
    private void selectRelationship(String strValuetoSelect) {
        performAction.select(relation, strValuetoSelect,
                "Relationship Select Box");
    }

    // set the relationship text box
    private void setRelationship(String strRelationshipValue) {
        performAction.enter(relation, strRelationshipValue,
                "Relationship Text Box");
    }

    // select the Beneficiary Type combo box
    private void selectBeneficiaryType(String strBeneficiaryType) {
        performAction.select(beneficiaryType, strBeneficiaryType,
                "Beneficiary Type Select Box");
    }

    // Enter Bith Date in Add Beneficiary Page
    private void enterBeneficiaryBirthDate(String strBeneficiaryBirthDate) {
        if (strBeneficiaryBirthDate.startsWith("d:")) {
            strBeneficiaryBirthDate = utils.getValue(strBeneficiaryBirthDate);
            performAction.clearEnter(birthDateOfAddBeneficiary,
                    strBeneficiaryBirthDate, "Enter Birth Date of Beneficiary");
        }
    }

    // set the Allocation text box
    private void setAllocation(String strAllocationValue) {
        performAction.clearEnter(allocation, strAllocationValue,
                "Allocation Text Box");
    }

    // click on save button
    private void clickSaveButton() {
        performAction.click(dependentSaveButton, "Save Button");
    }

    // click on Add button
    private void clickAddButton() {
        performAction.click(addbutton, "Add Button");
    }

    // click on Add button
    private void clickDependentLinkOnBeneficiariesPage() {
        performAction.click(dependentLinkOnBeneficiariesPage,
                "Dependent Link on Beneficiaries page");
    }

    // Add Another Dependent to Employee
    private void addAnotherDependent() {
        if (performAction.isElementPresent(addAnotherDependent)) {
            performAction.click(addAnotherDependent, "Add Another Dependent");
        } else {
            performAction.click(addAnotherDependentPersonsCovered, "Add Another Dependent While Enrolling");
        }
    }

    /* Enter Dependent Details */
    private void enterDependentDetails(String depRelationshipType,
                                       String outLastName) {
        Object object = null;
        JSONObject fields = ReadJsonTestData.getTestData(depRelationshipType
                .toLowerCase());
        object = fields.get("firstname");
        if (object != null) {
            this.setFirstNameField(object.toString().toString());
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
            this.selectGenderField(object.toString());
        }

        object = fields.get("ssn");
        if (object != null) {
            this.setSSNField(utils.generateRandomNumber(object.toString()));
        }

        this.selectRelationship(depRelationshipType);

        // submit the add new member form
        this.clickSaveButton();

    }

    /* Edit the Dependent Information of Employee */
    private void editLinkDependentInformationInDependentsPage() {
        performAction.click(eidtLink,
                "Edit Link in the dependent information page in HR-Role");
    }

    // Enter Address1
    private void enterAddress1Person(String strAddress1Person) {
        performAction.enter(address1Person, strAddress1Person, "Enter Address1");
    }

    // Enter City
    private void enterCityPerson(String strCityPerson) {
        performAction.enter(cityPerson, strCityPerson, "Enter City");
    }

    // Select State
    private void selectStatePerson(String strStatePerson) {
        performAction.select(statePerson, strStatePerson, "Select State");
    }

    // Enter Zip Code
    private void enterZipCode(String strZipCode) {
        performAction.enter(zipPerson, strZipCode, "Enter Zip Code");
    }

    // Select Country
    private void selectCountryPerson(String strCountryPerson) {
        performAction.select(countryPerson, strCountryPerson, "Select Country");
    }

    // Enter Phone Number
    private void enterPhoneNumber(String strPhoneNumber) {
        performAction.enter(phoneNumber, strPhoneNumber, "Enter Phone Number");
    }

    // Add Dependent Button in Persons Covered Page
    private void clickAddDependentPersonsCoveredPage() {
        performAction.click(addDependentPersonsCoveredPage, "Add Dependent Button in Persons Covered Page");
    }

    // Enter Date for Birth Persons Covered Page
    private void enterDateOfBirth(String strDateOfBirth) {
        if (performAction.isElementPresent(dateOfBirth)) {
            performAction.clearEnter(dateOfBirth, strDateOfBirth, "Enter Date for Birth for Persons Covered Page");
        } else if (performAction.isElementPresent(birthDate)) {
            performAction.clearEnter(birthDate, strDateOfBirth, "Enter Date for Birth for Persons Covered Page");
        }
    }

    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : Keyword to verify the dependents and beneficiaries link.
     *
     * Role : Member Role
     *
     * PreCondition : User should be in Member home page.
     *
     * PostCondition : Should return whether dependents and beneficiaries link exists or not.
     *
     * <b>Parameters & Example </b>
     *
     * Java file Name : HRAdminAddDependentPage.java
     * </pre>
     **/
    @RobotKeyword
    public void verifyDependentsAndBeneficiariesLink() {

        try {
            performAction.isElementPresent(dependentsAndBeneficiaries);
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.warn("Exception occured while verifying dependents and beneficiaries link "
                    + e.getMessage());
            throw new CustomException("Exception occured "
                    + e.getMessage());
        }
    }

    // Method to navigate through Member initial login pages
    private void clickDependentsLink() {
        performAction.click(dependents, "Dependents link");
        if (performAction.isAlertPresent()) {
            performAction.acceptAlert();
        }

    }

    private void clickAddNewDependentButton() {
        if (performAction.isElementPresent(addNewDependentButton)) {
            performAction.click(addNewDependentButton, "Add Dependent Button");
        }
    }

    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description: Keyword to add a dependent to member in HR role
     *
     * Role : HR Admin Role
     *
     * <b>Parameters :</b>
     * | strRelation - type argument used to input the Relation of the dependent | outLastName |
     * <b>Example :</b>
     * | Spouse / Child etc | any variable to store the dependent lastname |
     *
     * Java file Name : HRAdminAddDependentPage.java
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"depRelationshipType", "outLastName"})
    public void addDependentToEmployee(String depRelationshipType,
                                       String outLastName) {
        String temp = depRelationshipType.substring(0, 3);
        try {

            if (depRelationshipType.startsWith("td:"))
                depRelationshipType = depRelationshipType.substring(3);

            if (depRelationshipType.equalsIgnoreCase("none")
                    || (depRelationshipType == null)) {
                // this.clickDependentsLink();
                if (performAction.isElementPresent(addNewDependentButton))
                    this.clickAddNewDependentButton();
                else {
                    this.clickAddDependentButton();
                }
            } else {

                Object object = null;
                JSONObject fields = ReadJsonTestData
                        .getTestData(depRelationshipType.toLowerCase());

                if (performAction.isElementPresent(firstName,
                        "First Name Text Box")) {
                    logger.info("Already in Dependents page");
                } else {
                    if (performAction.isElementPresent(addNewDependentButton))
                        this.clickAddNewDependentButton();
                    else {
                        this.clickAddDependentButton();
                    }
                }

                object = fields.get("firstname");
                if (object != null) {
                    this.setFirstNameField(object.toString().toString());
                }

                object = fields.get("lastname");
                if (object != null) {
                    String value = utils
                            .generateRandomNumber(object.toString());
                    this.setLastNameField(value);
                    browser.hMap.put(outLastName, value);
                }

                object = fields.get("birthdate");
                if (object != null) {
                    this.setBirthDateField(utils.getValue(object.toString()));
                }

                object = fields.get("gender");
                if (object != null) {
                    this.selectGenderField(object.toString());
                }

                object = fields.get("ssn");
                if (object != null) {
                    if (performAction.isElementPresent(ssn, "SSN Text Box")) {
                        this.setSSNField(utils.generateRandomNumber(object
                                .toString()));
                    } else {
                        this.enterSSNFromCurrentBenefitsPage(utils
                                .generateRandomNumber(object.toString()));
                    }

                }

                if (temp.equalsIgnoreCase("td:")) {
                    object = fields.get("relationship");
                    System.out
                            .println("Relation ship ==> " + object.toString());
                    if (object != null) {
                        this.selectRelationship(object.toString());
                    }
                } else {
                    this.selectRelationship(depRelationshipType);
                }

                // submit the add new member form
                if (performAction.isElementPresent(saveButton)) {
                    this.saveButton();
                } else if (performAction.isElementPresent(dependentSaveButton)) {
                    this.clickSaveButton();
                } else {
                    this.clickAddButton();
                }
            }
            //performAction.waitUntilElementPresent(dependentsTable);
            /*Assert.assertTrue("Dependents table", performAction.isElementPresent(dependentsTable,
                    "Dependents Table"));*/
            scr.capturePageScreenshot();


        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured " + e.getMessage());
            throw new CustomException(
                    "Exception occured while adding dependent to Employee"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Thallam
     *
     * Description : addAnotherDependentToEmployee keyword or method is used to perform adding another
     *  Dependent to Employee in HR Admin Role.
     *
     * Role: HR Role
     *
     * PreCondition : Member should be in Dependents page in HR Admin Role
     *
     * PostCondition : New Dependent is saved successfully.
     *
     * <b>Parameters & Example </b>
     *
     * | strRelation | outLastName |
     * |  Spouse / Child etc - is used to get the data set from the Json | lastname - is a variable name and it will be used to store the LastName of the Employee |
     *
     * JavaFileName: HrAdminAddDependentpage.java
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"depRelationshipType", "outLastName"})
    public void addAnotherDependentToEmployee(String depRelationshipType,
                                              String outLastName) {
        try {
            this.addAnotherDependent();
            this.addDependentToEmployee(depRelationshipType, outLastName);

        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured " + e.getMessage());
            throw new CustomException("Exception in adding beneficiary "
                    + e.getMessage());
        }

    }


    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : Keyword to Add Beneficiary To Member in HR role
     *
     * Role : HR Admin Role
     *
     * <b>Parameters :</b>
     * | strRelationship - type argument used to input the Relation of the dependent | strBeneficiary | strAllocation |outLastName |
     * <b>Example :</b>
     * | Spouse / Child etc | Beneficiary type - Primary / Secondary | % allocation for beneficiary | any variable to store the dependent lastname |
     *
     * Java file Name : HRAdminAddDependentPage.java
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strRelationship", "strBeneficiary", "strAllocation",
            "outLastName"})
    public void addNewBeneficiaryToEmployee(String strRelationship,
                                            String strBeneficiary, String strAllocation, String outLastName) {

        try {
            if (strRelationship.equalsIgnoreCase("none")
                    || (strRelationship == null)) {
                this.clickNextButton();
                scr.capturePageScreenshot();
            } else {

                Thread.sleep(1000);

                Object object = null;
                JSONObject fields = ReadJsonTestData
                        .getTestData(strRelationship.toLowerCase());

                this.clickAddBeneficiaryButton();

                object = fields.get("firstname");
                if (object != null) {
                    this.setFirstNameField(object.toString().toString());
                }

                object = fields.get("lastname");
                if (object != null) {
                    String value = utils
                            .generateRandomNumber(object.toString());
                    this.setLastNameField(value);
                    browser.hMap.put(outLastName, value);
                }

                object = fields.get("birthdate");
                if (object != null) {
                    this.enterBeneficiaryBirthDate(object.toString());
                }

                object = fields.get("relationship");
                if (object != null) {
                    this.setRelationship(object.toString().toString());
                } else {
                    this.setRelationship(strRelationship);
                }
                object = fields.get("address1");
                if (object != null) {
                    this.enterAddress1Person(object.toString());
                }
                object = fields.get("city");
                if (object != null) {
                    this.enterCityPerson(object.toString());
                }
                object = fields.get("zipcode");
                if (object != null) {
                    this.enterZipCode(object.toString());
                }
                object = fields.get("state");
                if (object != null) {
                    this.selectStatePerson(object.toString());
                }
                object = fields.get("country");
                if (object != null) {
                    this.selectCountryPerson(object.toString());
                }
                object = fields.get("phone");
                if (object != null) {
                    this.enterPhoneNumber(utils.getValue(object.toString()));
                }
                // submit the add new member form
                this.clickAddButton();

                if (strBeneficiary.startsWith("json")) {
                    object = fields.get("beneficiarytype");
                    if (object != null) {
                        this.selectBeneficiaryType(object.toString());
                    }
                } else {
                    this.selectBeneficiaryType(strBeneficiary);
                }

                object = fields.get("allocation");
                if (object != null) {
                    this.setAllocation(object.toString().toString());

                }
                // Click Next Button on Beneficiary Page
                performAction.click(beneficiariesNextButton,
                        "Beneficiaries next button");
                scr.capturePageScreenshot();
            }

        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception in adding beneficiary "
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Sekhar Tirumala
     *
     * Description : Keyword to Add Dependent as Beneficiary To Member in HR role
     *
     * Role : HR Admin Role
     *
     * <b>Parameters :</b>
     * | strRelation - type argument used to input the Relation of the | outLastName |
     * <b>Example : </b>
     * | Spouse / Child etc | out parameter to store the dependent lastname |
     *
     * Java file Name : HRAdminAddDependentPage.java
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"strRelationship", "strBeneficiary", "strAllocation",
            "outLastName"})
    public void addDependentAsBeneficiary(String strRelationship,
                                          String strBeneficiary, String strAllocation, String outLastName) {

        try {
            if (strRelationship.equalsIgnoreCase("none")
                    || (strRelationship == null)) {
                this.clickDependentLinkOnBeneficiariesPage();
            } else {

                Thread.sleep(2000);

                Object object = null;
                JSONObject fields = ReadJsonTestData
                        .getTestData(strRelationship.toLowerCase());

                this.clickDependentLinkOnBeneficiariesPage();

                if (strBeneficiary.startsWith("json")) {
                    object = fields.get("beneficiarytype");
                    if (object != null) {
                        this.selectBeneficiaryType(object.toString());
                    }
                } else {
                    this.selectBeneficiaryType(strBeneficiary);
                }

                object = fields.get("allocation");
                if (object != null) {
                    this.setAllocation(object.toString().toString());

                }
                // Click Next Button on Beneficiary Page
                performAction.click(beneficiariesNextButton,
                        "Beneficiaries next button");

                // Click Next Button on Effective Date Page
                performAction.click(effDateNextbutton, "Next button");

                // Click Save Button
                performAction.click(saveAndGoToBenefitsbutton,
                        "Save and Go to Benefits button");
                scr.capturePageScreenshot();

            }
        } catch (Exception e) {
            logger.info("Exception in adding beneficiary " + e.getMessage());
            scr.capturePageScreenshot();
            throw new CustomException("Exception in adding beneficiary "
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Thallam
     *
     * Description : hideBeneficiaryInHrRole keyword or method is used  to hide the beneficiary in the HR Role
     *
     * PreCondition : Member should be in Offer summary page and Must be completed enrollment.
     *
     * Role: HR role
     *
     * PostCondition : member will be redirected to the beneficiary information page.
     *
     * <b>Parameters & Example </b>
     *
     * | strBenefitName |
     * |  Life Plan 2015 - is an offer name and it will be select from the list |
     *
     * JavaFileName: HrAdminAddDependentpage.java
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strBenefitName"})
    public void hideBeneficiaryInHrRole(String strBenefitName) {
        boolean benefitFound = false;
        try {
            Thread.sleep(1000);
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(benefitsList)).isDisplayed()) {


                By list = By.xpath(benefitsList + "/table");

                logger.info("list" + list.toString());

                int benefitsCount = browser.getCurrentWebDriver()
                        .findElements(list).size();

                logger.info("benefitsCount" + benefitsCount);

                if (benefitsCount > 0) {

                    for (int i = 1; i <= benefitsCount; i++) {

                        By BenefitHeader = By.xpath(benefitsList + "/table["
                                + i + "]//h1");
                        logger.info("Benefit Header   " + BenefitHeader);

                        if (browser.getCurrentWebDriver()
                                .findElement(BenefitHeader).getText().trim()
                                .equalsIgnoreCase(strBenefitName)) {
                            Thread.sleep(1000);
                            By editButton = By.xpath(benefitsList + "/table["
                                    + i + "]//strong[text()='Edit']");
                            // webdriver.findElement(startButton).click();
                            performAction.jsclick(editButton, strBenefitName
                                    + " Edit button");
                            By beneficiaryList = By.xpath(beneficiarys + "/tr");
                            int beneficiaryCount = browser
                                    .getCurrentWebDriver()
                                    .findElements(beneficiaryList).size();
                            for (int x = 1; x <= beneficiaryCount; x++) {
                                By Beneficiary = By
                                        .xpath(beneficiarys
                                                + "/tr["
                                                + i
                                                + "]//a[text()='Use for this Benefit']");
                                if (performAction.isElementPresent(Beneficiary)) {
                                    By hideBeneficiary = By.xpath(beneficiarys
                                            + "/tr[" + i + "]//td[1]//a");
                                    performAction.click(hideBeneficiary,
                                            "Beneficiary to Hide");
                                    Thread.sleep(2000);
                                    performAction.click(beneficiaryHide,
                                            "Hide Button");
                                    performAction.click(updateHide,
                                            "Update Button");

                                } else {
                                    System.out
                                            .println("No Beneficiaries are found to hide ");
                                    throw new RuntimeException(
                                            "No Beneficiaries are found to hide");


                                }
                            }


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
     * Description: Keyword to change the dependent details in HR role
     *
     * Role : HR Admin Role
     *
     * PreCondition : user should be in dependent details page in Hr Role
     *
     * PostCondition : dependent details should be changed successfully
     *
     * <b>Parameters :</b>
     * | depRelationshipType - type argument used to input the Relation of the dependent | outLastName |
     * <b>Example :</b>
     * | Spouse / Child etc | any variable to store the dependent lastname |
     *
     * Java file Name : HRAdminAddDependentPage.java
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"depRelationshipType", "outLastName"})
    public void editDependentDetails(String depRelationshipType,
                                     String outLastName) {
        try {
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(dependentsList)).isDisplayed()) {

                List<WebElement> listElements = browser.getCurrentWebDriver()
                        .findElements(By.xpath(dependentListElements));

                for (WebElement element : listElements) {

                    String relationType = element.findElement(
                            By.xpath("./div[1]//p")).getText();

                    logger.info("RelationShip with Employee :"
                            + relationType);

                    if (depRelationshipType.equalsIgnoreCase(relationType)) {
                        Thread.sleep(1000);
                        element.findElement(By.xpath("./div[2]//a")).click();
                        Thread.sleep(1000);
                        break;
                    }

                }

                this.editLinkDependentInformationInDependentsPage();

                this.enterDependentDetails(depRelationshipType, outLastName);

            }

        } catch (Exception e) {
            logger.info("Exception occured while changing the dependent details"
                    + e.getMessage());
            scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception occured while Editing the dependent details"
                            + e.getMessage());
        }

    }


    /**
     * <pre>
     * Author  : Dilip K
     *
     * Role : HR Role
     *
     * Description   : "Persons Covered" keyword used to add dependent also waive coverage of dependent.
     *
     * PreCondition  : Navigate to Persons Covered page
     *
     * PostCondition : Able to Add another dependent/subscriber also waive coverage of a person.
     *
     * <b>Parameters & Example </b>
     *
     * | Waive Coverage,Last Name of Employee | or | Add Another Dependent, JSON Tag Name  | or | To Click on Next Button |
     * | waive coverage,auto123               | or | add another dependent,td:adddependent | or | click on,next button    |
     *
     * <b> Java File Path : com.benefitfocus.robot.hradmin >> HRAdminAddDependentPage.java </b>
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"actiontobeperformed", "value"})
    public void personsCovered(String actiontobeperformed, String value) {
        performAction.verifyMessage("Persons Covered");

        if (actiontobeperformed.equalsIgnoreCase("waive coverage")) {
            this.clickWaiveCoverage(actiontobeperformed, value);
            //this.clickNextButton();
        } else if (actiontobeperformed.equalsIgnoreCase("add dependent") || actiontobeperformed.equalsIgnoreCase("add another dependent")) {
            if (performAction.isElementPresent(addDependentPersonsCoveredPage, "Add Dependent button displayed in Persons Covered page")) {
                this.clickAddDependentPersonsCoveredPage();
            } else if (performAction.isElementPresent(addAnotherDependentPersonsCovered)) {
                performAction.click(addAnotherDependentPersonsCovered, "Click Add another Dependent Button from Persons Covered page");
            } else if (performAction.isElementPresent(firstName)) {
                logger.info("Employee is already in add dependent page");
            }
            if (value.startsWith("td:"))
                value = value.substring(3);

            Object object = null;
            JSONObject fields = ReadJsonTestData
                    .getTestData(value.toLowerCase());

            object = fields.get("firstname");
            if (object != null) {
                this.setFirstNameField(object.toString().toString());
            }

            object = fields.get("lastname");
            if (object != null) {
                String lastname = utils.generateRandomNumber(object.toString());
                this.setLastNameField(lastname);
            }

            object = fields.get("birthdate");
            if (object != null) {
                this.enterDateOfBirth(utils.getValue(object.toString()));
            }

            object = fields.get("gender");
            if (object != null) {
                this.selectGenderField(object.toString());
            }

            object = fields.get("relationship");
            logger.info("Relation ship ==> " + object.toString());
            if (object != null) {
                this.selectRelationship(object.toString());
            }
            if (performAction.isElementPresent(addbutton)) {
                this.clickAddButton();
            } else {
                this.saveButton();
            }
            //this.clickNextButton();
            scr.capturePageScreenshot();
        }
        this.clickNextButton();
        scr.capturePageScreenshot();
    }


    /**
     * Author : Teja
     * <p/>
     * Keyword to change the dependent status to verified
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | strRelation - type argument used to input the Relation of the dependent | outLastName |
     * <b>Example :</b>
     * | Spouse / Child etc | any variable to store the dependent lastname |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"depRelationshipType"})
    public void verifyDependentForBenefits(String depRelationshipType) {
        try {
            if (browser.getCurrentWebDriver()
                    .findElement(By.xpath(dependentsList)).isDisplayed()) {


                List<WebElement> listElements = browser.getCurrentWebDriver()
                        .findElements(By.xpath(dependentListElements));

                for (WebElement element : listElements) {

                    logger.info("==============" + element.getTagName() + "==================");
                    logger.info("==============" + element.toString() + "==================");

                    String relationType = element.findElement(
                            By.xpath("./div[1]//p")).getText();

                    logger.info("RelationShip with Employee :"
                            + relationType);

                    if (depRelationshipType.equalsIgnoreCase(relationType)) {
                        Thread.sleep(1000);
                        element.findElement(By.xpath("./div[2]//a")).click();
                        Thread.sleep(1000);
                        scr.capturePageScreenshot();
                        break;
                    }

                }

                performAction.click(verifyTheDependentForBenefits, "Clicking verify button to enable the dependent for all Benefits");
                utils.sleep(3000);

                performAction.click(backButton, "Clicking back button after verifying the dependent for all benefits");

            }

        } catch (Exception e) {
            System.out
                    .println("Exception occured while verifying the dependent"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while verifying the dependent"
                            + e.getMessage());
        }
    }

    /**
     * Author  : Ch Phani Srikar
     * Keyword to Hide a dependent in HR role
     */
    @RobotKeyword
    public void hideDependentInHrRole() {

        try {
            //click on View Details Button
            performAction.click(viewDetailsButton, "ViewDetailsButton");
            scr.capturePageScreenshot();
            //clcik on hide button yes or no
            performAction.click(hideSwitchButton, "HideSwitchButton");
            scr.capturePageScreenshot();
            //click on Dependants link
            this.clickDependentsLink();
            // Dependant Status in Console Screen
            logger.info(" Dependent " + browser.getCurrentWebDriver().findElement
                    (By.xpath("//p[@class='man fs-italic']")).getText() + " Status is : "
                    + browser.getCurrentWebDriver().findElement(By.xpath("//span[text()='Hidden']")).getText());
            //taking Screen Shot
            scr.capturePageScreenshot();
        } catch (Exception e) {
            logger.info("Exception occured while Hiding the dependent "
                    + e.getMessage());
            scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception occured while Hiding the dependent"
                            + e.getMessage());
        }

    }
}
