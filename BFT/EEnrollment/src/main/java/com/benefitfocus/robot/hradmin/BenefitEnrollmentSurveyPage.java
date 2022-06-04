package com.benefitfocus.robot.hradmin;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
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
public class BenefitEnrollmentSurveyPage {

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

    // Selenium Locators
    By saveAndCompleteButton = By.linkText("Save and Complete");

    // Locators to handle PCP page
    By pcpNameTextBox = By.id("providerName");
    By pcpCodeTextBox = By.id("providerCode");
    By deductionPeriodsRemainingTextbox = By.id("newPeriodsRemaining");
    By selectFullTimeEmployerRadio = By
            .xpath("(//*[contains(@id,'surveyQuestionAnswerSelection')])[position()=1]");
    By selectReplaceExistingEmployerRadio = By
            .xpath("(//*[contains(@id,'surveyQuestionAnswerSelection')])[position()=2]");
    By PolicyNumberTextbox = By
            .xpath("//*[contains(@id,'surveyQuestionContainerAsFollowUpToOption')]/input");
    By agreeTermsAndConditionsCheckbox = By
            .xpath(".//*[contains(@id,'customAnswer')]");
    By hsaDentalVisionRadio = By
            .id("hsaUsedOutsideSystem[DENTAL_VISION]");
    By hsaNotApplicableRadio = By
            .id("hsaUsedOutsideSystem[NOT_APPLICABLE]");
    String spouseCoverageQuestionList = "//*[contains(@id,'~question~bf.cbm.implementation.interfaces.survey.SurveyQuestion')]//tr";
    By selectSpouseCoverageAnswer = By
            .xpath("//div[@class='surveyQuestionContainer']//select");

    By nextButton = By.xpath("//*[text()='Next' or @class='btn btn-success']");
    String radioButtonHeaders = "//div[@class='regionHeader']";
    String allRadioButtonsRows = "//*[@class='fieldListRow']";
    String allRadioButtonsNames = ".//*[@class='fieldListLabelRight']";
    String allRadioButtons = ".//*[@class='fieldListFieldLeft']//input";

    By selectTobaccoSurvey = By
            .xpath("//*[@class='surveyQuestionContainer']//select[1]");
    By selectTaxType = By
            .xpath("(//*[@class='surveyQuestionContainer'])[2]//select");
    By next = By.xpath("//*[contains(text(),'Next')]");

    By saveButton = By.xpath("//*[@class='btn btn-success']");
    By nextButtonTermsConditions=By.xpath("//strong[text()='Next']");

    // Enter PrimaryCareProviderName into the text box
    private void setPCPName(String strPCPName) {
        performAction.waitUntilElementPresent(pcpNameTextBox);
        performAction.clearEnter(pcpNameTextBox, strPCPName,
                "Primary Care Provder Name");
    }

    // Enter PrimaryCareProviderCode into text box
    private void setPCPCode(String strPCPCode) {
        performAction.waitUntilElementPresent(pcpCodeTextBox);
        performAction.clearEnter(pcpCodeTextBox, strPCPCode,
                "Primary care Provder Code");
    }

    // Select Radio Button to confirm current patient with the PCP
    private void selectCurrentPatientProviderRadio(
            String strCurrentPatientConfirmation) {
        if (strCurrentPatientConfirmation.equalsIgnoreCase("Yes")) {
            By yesRadioButton = By.xpath(".//*[@id='providerInfo0-0']");

            performAction.click(yesRadioButton, "Yes Radio Button");
        } else {
            By noRadioButton = By.xpath(".//*[@id='providerInfo0-1']");
            performAction.click(noRadioButton, "No Radio Button");
        }
        performAction.click(nextButton, "Next Button");
    }

    private void selectRadioButtonInformation(String radioButtonName) {

		String radioBtn = "//span[contains(text(),'"+radioButtonName+"')]/preceding-sibling::input[@type='radio']";
		performAction.click(radioBtn, "Spouse Information Radio button");

}

    // Enter Remaining Periods in the year for Flex benefit
    private void setRemainingDedcutionPeriodsForTheYear(
            String strDeductionPeriodsRemaining) {
        performAction.waitUntilElementPresent(deductionPeriodsRemainingTextbox);
        performAction.clearEnter(deductionPeriodsRemainingTextbox,
                strDeductionPeriodsRemaining,
                "Remaining Periods In the year For Flex benefit");
    }

    // Select values for Accident Insurance Policy
    private void selectAccidentPolicy(String strFullTimeEmployer,
                                      String strReplaceExistingEmployer, String strPolicyNumber) {

        performAction.waitUntilElementPresent(selectFullTimeEmployerRadio);
        performAction.select(selectFullTimeEmployerRadio, strFullTimeEmployer,
                "Select Full Time Employer or Not");

        performAction.select(selectReplaceExistingEmployerRadio,
                strReplaceExistingEmployer,
                "Select Replace Existing Employer or Not");

        utils.sleep(5);
        if (strReplaceExistingEmployer.equalsIgnoreCase("Yes")) {

            performAction.enter(PolicyNumberTextbox, strPolicyNumber,
                    "Enter Policy Number");

        }
    }

    // Select HSA Participation
    private void selectHSAParticipationForMedical(String strHSAParticipation) {
        performAction.waitUntilElementPresent(hsaDentalVisionRadio);
        if (strHSAParticipation.equalsIgnoreCase("Yes")) {
            performAction.click(hsaDentalVisionRadio,
                    "Yes for HSA participation");
        } else {
            performAction.click(hsaNotApplicableRadio,
                    "No for HSA Participation");
        }
    }

    // Selecting answer for Tobacco Survey
    private void selectAnswerToTobaccoSurvey(String strTobaccoSurvey) {
        this.selectRadioButton(strTobaccoSurvey);
        performAction.click(nextButton,
                "Clicking Next button in Tobacco Survey");
        performAction.click(nextButton,
                "Clicking Save and Complete button in Tobacco Survey");
    }

    // Select combobox answer for spouse coverage
    private void selectComboboxAnswerToSpouseCoverageSurvey(
            String strSpouseCoverageAnswer) {

        performAction.select(selectSpouseCoverageAnswer,
                strSpouseCoverageAnswer,
                "Selecting Answer for Spouse Coverage in spouse survey");
        performAction
                .click(nextButton, "Clicking Next button in Spouse Survey");
    }

    // Selecting the Radio button as per its Name
    private void selectRadioButton(String radioButtonName) {
        try {

            boolean Status = false;
            List<WebElement> radioButtonRows = browser.getCurrentWebDriver()
                    .findElements(By.xpath(allRadioButtonsRows));

            System.out.println("============================RadioButton Rows:" + radioButtonRows);

            for (WebElement element : radioButtonRows) {
                String radioButtonName1 = element.findElement(
                        By.xpath(allRadioButtonsNames)).getText();
                System.out.println("============================RadioButton Name1:" + radioButtonName1);
                System.out.println("============================RadioButton Name:" + radioButtonName);


                if (radioButtonName.trim().toUpperCase()
                        .contains(radioButtonName1.toUpperCase())) {
                    Thread.sleep(2000);
                    element.findElement(By.xpath(allRadioButtons)).click();
                    Status = true;
                    break;
                }
            }
            if (!Status) {
                throw new RuntimeException("Failed to select the Radio Button"
                        + radioButtonName);
            }

        } catch (Exception e) {
            System.out
                    .print("Failed to select the Radio Button as per the name passed");
            e.printStackTrace();
        }

    }

    // Selecting the Radio button as per its Header Name
    private void selectRadioButtonBasedOnHeader(String strQuestion,
                                                String radioButtonName) {
        try {

            // ((//div[@class='regionHeader'])[2]/following-sibling::div//*[@class='fieldListLabelRight'])[1]/preceding-sibling::td/input
            boolean Status = false;
            List<WebElement> radioButtonHeaderList = browser
                    .getCurrentWebDriver().findElements(
                            By.xpath(radioButtonHeaders));

            for (WebElement headerElement : radioButtonHeaderList) {
                String headerText = headerElement.findElement(By.xpath("./h3"))
                        .getText();
                if (headerText.trim().toLowerCase()
                        .contains(strQuestion.trim().toLowerCase())) {
                    List<WebElement> radioButtonNameElements = headerElement
                            .findElements(By
                                    .xpath("./following-sibling::div//*[@class='fieldListLabelRight']"));

                    for (WebElement NameElement : radioButtonNameElements) {
                        String name = NameElement.getText();
                        System.out.println("Radio button Element Text :" + name);

                        if (name.trim().toLowerCase()
                                .contains(radioButtonName.trim().toLowerCase())) {

                            System.out.println("Selecting the radio button :" + name);

                            Thread.sleep(3000);
                            NameElement.findElement(
                                    By.xpath("./preceding-sibling::td/input"))
                                    .click();
                            Status = true;
                            break;
                        }
                    }
                }
            }

            // System.out.println("============================RadioButton Rows:"+radioButtonRows);

            if (!Status) {
                throw new RuntimeException(
                        "Failed to select the Radio Button :" + radioButtonName);
            }

        } catch (Exception e) {
            System.out.print("Failed to select the Radio Button :"
                    + radioButtonName);
            e.printStackTrace();
        }

    }

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description   : 'Save Spouse Coverage Question' keyword or method is used to click on 'Save and Complete' button in HR Admin Role
     *
     * PreCondition  : Spouse Coverage Question page should be opened
     *
     * PostCondition : It will be navigated to next page as per the group configuration after clicking on Save Button
     *
     * </pre>
     **/

    @RobotKeyword
    public void saveSpouseCoverageQuestion() {
        try {

            performAction.waitUntilElementPresent(saveAndCompleteButton);
            performAction.click(saveAndCompleteButton,
                    "Save And Complete Button");
        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while clicking on save And Complete button in HR Admin Role for Spouse Coverage Question"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while clicking on save And Complete button in HR Admin Role for Spouse Coverage Question "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description   : 'Select Primary Care Provider' keyword or method is used to select the PCP name and PCP code for the current employee while enrolling benefits
     *
     * PreCondition  : Primary Care Provider page should be opened
     *
     * PostCondition : It will be navigated to next page as per the group configuration
     *
     * <b>Parameters & Example </b>
     *
     * | PCPName - String argument | PCPCode - String Argument | CurrentPatientConfirmationText - Yes/No |
     * | Test | 1234 | Yes |
     *
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"PCPName", "PCPCode", "CurrentPatientConfirmationText"})
    public void selectPrimaryCareProviderInformation(String strPCPName,
                                                     String strPCPCode, String strConfirmCurrentPatient) {
        try {
            this.setPCPName(strPCPName);
            this.setPCPCode(strPCPCode);
            this.selectCurrentPatientProviderRadio(strConfirmCurrentPatient);
        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while Selecting on PCP information while enrolloing benefits"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while Selecting on PCP information while enrolloing benefits"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description   : 'Enter Deduction Periods Remaining In The Year' keyword or method is used to enter the number of deduction periods remaining for the Flex benefit
     *
     * PreCondition  : Coverage Amount page should be opened for FSA/DCFSA benefit
     *
     * PostCondition : Coverage AMount will be entered and click on Next button, it navigates to next page as per the configuration
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"Remaining Deduction Periods"})
    public void setDeductionPeriods(
            String strDeductionPeriodsRemaining) {
        try {
            this.setRemainingDedcutionPeriodsForTheYear(strDeductionPeriodsRemaining);

            performAction.click(nextButton, "Deduction Periods Next Button");
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
     * Author  : Prasad Pasupuleti
     *
     * Description   : 'Select Accident Policy For Medical benefit' keyword or method is used to select the values for Accident Insurance
     *
     * PreCondition  : Accident Insurance Policy page should be opened
     *
     * PostCondition : It will be navigated to next page as per the group configuration
     *
     * * <b>Parameters & Example </b>
     *
     * | Full Time Employer - String argument | Replace Existing Employer - String Argument |Policy Number - String Argument|
     * | Yes/No | Yes/No | 1234 |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"Full Time Employer", "Replace Existing Employer",
            "Policy Number"})
    public void selectAccidentPolicyForMedicalBenefit(
            String strFullTimeEmployer, String strReplaceExistingEmployer,
            String strPolicyNumber) {

        try {
            this.selectAccidentPolicy(strFullTimeEmployer,

                    strReplaceExistingEmployer, strPolicyNumber);

            performAction.click(nextButton, "Insurance Policy Next Button");
        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while Selecting values for Accident Policy of Medical Benefit"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while Selecting values for Accident Policy of Medical Benefit"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description   : 'Agree Terms and Conditions' keyword or method is used to Agree the Terms and Conditions while enrolling benefits
     *
     * PreCondition  : Terms and Conditions Page should be opened along with its checkbox
     *
     * PostCondition : Click on Terms and Conditions Agree button and Next button, navigates to the next page as per the group configuration
     *
     * </pre>
     **/
    @RobotKeyword
    public void agreeTermsAndConditions() {

        try {
            performAction
                    .waitUntilElementPresent(agreeTermsAndConditionsCheckbox);
            performAction.click(agreeTermsAndConditionsCheckbox,
                    "Terms And Conditions CheckBox");
            performAction.click(nextButtonTermsConditions,
                    "Agreement Terms and Conditions Next Button");
        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out
                    .println("Exception occured while agreeing terms and conditions"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while agreeing terms and conditions"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description : This Keyword is used to select the Spouse Information in HR-Admin Role
     *
     * PreCondition : Persons Covered  page should be displayed
     *
     * PostCondition : It will be navigated to next page as per the group configuration
     *
     * <b>Parameters & Example </b>
     *
     * <b>strHeaderName  && strRadiobutton</b>
     * |Persons Covered :: I acknowledge that the above requirements are met.|
     * |Dependent Relationship Requirements :: Yes, this dependent meets the above requirements.|--In case of supplemental Life Insurance enrollment
     *
     *  Java File Name: hradmin>>BenefitEnrollmentSurvey.java
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strHeaderName", "strRadiobutton"})
    public void selectSpouseInformation(String strHeaderName, String strRadiobutton) {

        try {
        	if(strHeaderName.equalsIgnoreCase("Dependent Relationship Requirements"))
        	{
        		this.selectRadioButtonInformation(strRadiobutton);
                performAction.click(next, "Spouse Next Button");
        	}
        	else{
            this.selectRadioButtonBasedOnHeader(strHeaderName, strRadiobutton);
            performAction.click(nextButton, "Spouse Next Button");
        	}


        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while select spouse information"
                            + " " + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Prasad Pasupuleti
     *
     * Description : Keyword to perform selection on Coverage Questions in eEnrollment HR admin role
     *
     * PreCondition : Spouse Coverage Question page should be opened
     *
     * PostCondition : Select the given Coverage Question in the list of eligible questions on the page and click next button
     *
     * <b>Parameters & Example </b>
     *
     * | strSpouseCoverageQuestion - String type argument takes Question Name as input value |
     * | |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strSpouseCoverageQuestion"})
    public void selectSpouseCoverageQuestion(String strSpouseCoverageQuestion) {

        boolean coverageQuestionFound = false;

        try {
            Thread.sleep(5000);
            int rowsize = browser.getCurrentWebDriver()
                    .findElements(By.xpath(spouseCoverageQuestionList)).size();
            System.out.println("rowsize : " + rowsize);

            if (rowsize > 0) {
                for (int i = 1; i <= rowsize; i++) {
                    String row = spouseCoverageQuestionList + "[" + i + "]";
                    String availableCoverageQuestion = browser
                            .getCurrentWebDriver().findElement(By.xpath(row))
                            .getText().trim().toLowerCase();
                    System.out.println("availableCoverageQuestion : "
                            + availableCoverageQuestion);
                    if (availableCoverageQuestion
                            .contains(strSpouseCoverageQuestion.toLowerCase())) {
                        System.out.println(strSpouseCoverageQuestion
                                + "Found in the row no : " + i);
                        By loc = By.xpath(row + "//input");

                        performAction.click(loc,
                                "Coverage Question radio button");
                        coverageQuestionFound = true;
                        break;
                    }
                }
            } else {
                System.out.println("No Coverage Question available ");
                throw new RuntimeException("No Coverage Question available");
            }

            if (!coverageQuestionFound) {
                System.out.println(strSpouseCoverageQuestion
                        + " plan NOT available.");
                throw new RuntimeException(strSpouseCoverageQuestion
                        + "plan NOT available.");
            } else {
                performAction.click(nextButton, "Next button.");
            }

        } catch (Exception e) {
            scr.capturePageScreenshot();
            System.out.println("Exception occured while selecting plan"
                    + e.getMessage());
            throw new CustomException("Exception occured while selecting plan "
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Teja P
     * Source : hradmin/BenefitEnrollmentSurveyPage
     *
     * Description : This Keyword is used to select answer either from one or two combobox values for
     * coverage survey question in HR-Admin and clicks Next button
     *
     * PreCondition : Comobox Survey question page should be displayed
     *
     * PostCondition : Page should be displayed as per the configuration of Group
     *
     * <b>Parameters & Example </b>
     *
     * <b>strOtherCoverageSurvey Example :</b>
     * |Yes, my spouse has access to other coverage |Yes, my dependent children have access to other coverage|
     * |Yes, both my spouse and dependent children have access to other coverage|
     * |No, neither my spouse or dependent children have access to other coverage|
     * |No, I do not have a spouse or dependent children |
     * |etc...|
     *
     * <b>secondComboboxValue Example :</b>
     * |Yes,No,if second combobox is not available just simply mention NA(Not Applicable)|
     *
     *
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strFirstAnswer", "strSecondAnswer"})
    public void selectOtherCoverageSurveyDropDown(
            String strOtherCoverageSurvey, String strSecondAnswer) {

        try {
            performAction.select(selectTobaccoSurvey, strOtherCoverageSurvey,
                    "Answeirng for survey likey tobacco usage");

            // Boolean
            // bool=browser.getCurrentWebDriver().findElement(selectTaxType).isDisplayed();

            if (performAction.isElementPresent(selectTaxType)
                    && performAction.isDisplayed(selectTaxType,
                    "Verify the optional combobox is  present or not")) {

                performAction.select(selectTaxType, strSecondAnswer,
                        "Answeirng for survey likey tobacco usage");
            }
            performAction.click(nextButton,
                    "Clicking Next button in Select answer for Other Coverage");

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while selecting select answer for Other Coverage "
                            + " " + e.getMessage());
        }

    }

    /**
     * <pre>
     * Author  : Teja P
     * Source : BenefitEnrollmentSurveyPage
     *
     * Description : This Keyword is used to answer Tobacco ,Health Survey questions then clicks Next ,Save buttons in HR-Admin role
     *
     * Pre-Condition : Survey questions page should be displayed with radio buttton answers then clicks Next button ,Save button
     *
     * Post-Condition : As per configurations page will be displayed. Current Benefit Plans page will be displayed
     *
     * <b>Parameters & Example </b>
     *
     *
     * <b>strFirstQuestion Example :</b>
     * |Did you complete all 3 requirements (by October 31,2015) to
     * obtain a discount on your health premiums next year? |
     *
     * <b>strSecondQuestion</b>
     * |Have you used tobacco products in the past 12 months? |
     *
     * <b>strFirstRadioButton</b>
     * |Yes or No|
     *
     * <b>strSecondRadioButton</b>
     * |Yes or No|
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strFirstQuestion", "strSecondQuestion",
            "strFirstRadioButton", "strSecondRadioButton"})
    public void selectTobaccoAndHealthSurveyAnswer(String strFirstQuestion,
                                                   String strFirstRadioButton, String strSecondQuestion,
                                                   String strSecondRadioButton) {

        try {
            this.selectRadioButtonBasedOnHeader(strFirstQuestion,
                    strFirstRadioButton);
            this.selectRadioButtonBasedOnHeader(strSecondQuestion,
                    strSecondRadioButton);
            performAction.click(nextButton,
                    "Next button in Tobacco and Health Survey page");
            performAction.click(nextButton,
                    "Next button in Tobacco and Health Survey page");

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while answering the health survey" + " "
                            + e.getMessage());
        }

    }

    /**
     * <pre>
     * Author  : Teja P
     * Source : BenefitEnrollmentSurveyPage
     *
     * Description : This Keyword is used to select Radio Button answers for Survey questions in HR-Admin
     *
     * Pre-Condition : Survey questions page should be displayed with radio button answers
     *
     * Post-Condition : As per configurations page will be displayed.
     *
     * <b>Parameters & Example </b>
     *
     *
     * <b>strFirstQuestion Example :</b>
     * |Did you complete all 3 requirements (by October 31,2015) to
     * obtain a discount on your health premiums next year? |
     *
     * <b>strSecondQuestion</b>
     * |Have you used tobacco products in the past 12 months? |
     *
     * <b>strFirstRadioButton</b>
     * |Yes or No|
     *
     * <b>strSecondRadioButton</b>
     * |Yes or No|
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strQuestion", "strRadioButton"})
    public void selectSurveyQuestionsAnswer(String strQuestion,
                                            String strRadioButton) {
        try {
            this.selectRadioButtonBasedOnHeader(strQuestion, strRadioButton);

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while answering the survey" + " "
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Teja P
     * Source : BenefitEnrollmentSurveyPage
     *
     * Description : This Keyword is used to select Yes/No combobox value for Spouse survey questions from HR-Role
     * and clicks Next button
     *
     * PreCondition : Spouse coverage survey page shoud be displayed
     *
     * PostCondition : Page will be displayed as per the group configurations. Effective date page should be displayed
     *
     * <b>Parameters & Example </b>
     *
     *
     * <b>strSpouseCoverageAnswer Example :</b>
     * |Yes,No|
     *
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strSpouseCoverageAnswer"})
    public void selectSpouseCoverageSurveyDropDownAnswer(
            String strSpouseCoverageAnswer) {

        try {
            this.selectComboboxAnswerToSpouseCoverageSurvey(strSpouseCoverageAnswer);

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while answering the Spouse Coverage Survey question"
                            + " " + e.getMessage());
        }

    }

    /**
     * <pre>
     * Author  : Teja P
     * Source : BenefitEnrollmentSurveyPage
     *
     * Description : This Keyword is used to answer Yes/No radion button for Tobacco survey in HR-Admin Role and
     * clicks Next button
     *
     * PreCondition : Plans page should be displayed for the benefit Health Offer
     *
     * PostCondition : Page will be displayed as per group configuration.
     *
     * <b>Parameters & Example </b>
     *
     *
     * <b>strTobaccoSurvey Example :</b>
     * |Yes,No|
     *
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strTobaccoSurvey"})
    public void selectTobaccoSurveyAnswer(String strTobaccoSurvey) {

        try {
            this.selectAnswerToTobaccoSurvey(strTobaccoSurvey);

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while answering the Tobacco survey"
                            + " " + e.getMessage());
        }

    }

}

