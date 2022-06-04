package com.benefitfocus.robot.hradmin;

import org.openqa.selenium.By;
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
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;


@RobotKeywords
public class BenefitChangeForAMember {

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


    By manageEmployee = By.cssSelector("button[class*='dropdown-toggle']");
    By changeCategaries = By.linkText("Change Categories");
    By changeBenefitPlanNextButton = By.linkText("Next");
    By changeBenefitPlanSaveButton = By.linkText("Save");
    By selectChangeBenefitPlanDate = By.id("effectiveDate");
    By selectChangebenefitType = By.id("categoryTypeEntries(bf.cbm.implementation.interfaces.category.CategoryType|8278638908).selectedCategoryKey");
    By returnMemberHomePage = By.linkText("Return to Employee Profile");

    //Click Manage Employee button from Member Home Page from HR Role
    private void clickManageEmployeeButton() throws InterruptedException {
        performAction.click(manageEmployee, "Manage Employee");
    }

    //Select Change Categories option from Manage Employee Dropdown
    private void clickChangeCategaries() {
        performAction.click(changeCategaries, "Change Categaries");
    }

    //Click on Next button to move forward after entering required fields
    private void clickChangeCategoriesNextButton() {
        performAction.click(changeBenefitPlanNextButton, "Next");
    }

    private void clickSaveChangeCategoriesButton() {
        performAction.click(changeBenefitPlanSaveButton, "Save");
    }

    private void clickReturnMemberHomePage() {

        performAction.click(returnMemberHomePage, "Return to Employee Profile");
    }

    /**
     * <pre>
     * Author : Phani Srikar Ch
     *
     * Keyword to Change Benefits for Employee in HR Mode
     * <precondition>
     * <b> HR should be logged in and member home page should open </b>
     * <b>Parameter1 :</b>
     * | strBenefitName - String type argument takes Benefit Name as input value |
     * <b>Example :</b>
     * | Dental / Medical  / vision  / Life / FSA / Flex PTO / LTD / Medical plus HSA / STD / ..etc|
     * <b>Parameter2: </b>
     * | effective date - Effective date to start changed benefit |
     *  <b>Example :</b>
     * | 10/12/2015 |
     * <postcondition>
     * <b>Member Benefit will be changed and screen will navigates to employee home screen </b>
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"newBenefitType", "newEffectiveDate"})
    public void changeMemberBenefits(String strBenefitName, String effectivedate) {
        try {

            this.clickManageEmployeeButton();
            Thread.sleep(10000);

            this.clickChangeCategaries();
            Thread.sleep(5000);

            browser.getCurrentWebDriver().findElement(selectChangeBenefitPlanDate).sendKeys(effectivedate);

            browser.getCurrentWebDriver().findElement(selectChangebenefitType).sendKeys(strBenefitName);

            this.clickChangeCategoriesNextButton();

            performAction.verifyMessage("New Categories");

            WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(changeBenefitPlanSaveButton));
            this.clickSaveChangeCategoriesButton();

            performAction.verifyMessage("Confirmation");
            this.clickReturnMemberHomePage();

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while changing New benefits to the Employee in HR Mode " + " "
                            + e.getMessage());
        }
    }


}
