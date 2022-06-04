package com.benefitfocus.robot.hradmin;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.openqa.selenium.support.ui.Select;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;


import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.*;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;


@RobotKeywords
public class ATIPhysicalTherapyPage {

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

    By nextButton = By.xpath("//strong[text()='Next']");
    By selectMedicalPlanEnrollment = By.xpath("//*[@class='surveyQuestionContainer']/select[1]");
    By selectWorkForATI = By.xpath("//*[@class='surveyQuestionContainer']//div//select");


    private void selectAreYouEnrollingMedicaPlanAnswer(String strEnrollMedicalPlan) {
        performAction.select(selectMedicalPlanEnrollment, strEnrollMedicalPlan, "selecting answer for Are you enrolling Medical Plan");
    }

    private void selectMedicaPlanAndDependentWorkAnswer(String strEnrollMedicalPlan, String strSpouseOrParentWork) {

        performAction.select(selectMedicalPlanEnrollment, strEnrollMedicalPlan, "selecting answer for Are you enrolling Medical Plan");

        if (performAction.isDisplayed(selectWorkForATI, "Spouse or Work selection field")) {
            performAction.select(selectWorkForATI, strSpouseOrParentWork, "selecting spouse or parent work field");
        }

    }


    /**
     * <pre>
     * Author  : Teja P
     * source : ATIPhysicalTherapyPage
     *
     * Description : This Keyword is used to select combobox answer for ATI medical plan and clicks Next button in HR-Admin role
     *
     * PreCondition : ATI Physical Therapy page should be displayed
     *
     * PostCondition : Page will be displayed as per the Group configuration.Plan selection page will be displayed
     *
     * <b>Parameters & Example </b>
     *
     * <b>strATIMedicalPlanAnswer </b>
     * |Yes ,No|
     *
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strATIMedicalPlanAnswer"})
    public void selectATIPhysicalTherapyMedicalPlanAnswer(String strATIMedicalPlanAnswer) {

        try {

            this.selectAreYouEnrollingMedicaPlanAnswer(strATIMedicalPlanAnswer);
            performAction.click(nextButton, "Next Button in ATI Physical Therapy page");

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while entering data for date explanation"
                            + " " + e.getMessage());
        }

    }

    /**
     * <pre>
     * Author  : Teja P
     * source : ATIPhysicalTherapyPage
     *
     * Description : This Keyword is used to select combobox answer for ATI medical plan and Spouse or Parent work,
     * clicks Next button in HR-Admin role
     *
     * PreCondition : ATI Physical Therapy page should be displayed
     *
     * PostCondition : Page will be displayed as per the Group configuration.Plan selection page will be displayed
     *
     * <b>Parameters & Example </b>
     *
     * <b>strEnrollMedicalPlan </b>
     * |Yes ,No|
     *
     * <b>strSpouseOrParentWork </b>
     * |Yes ,No|
     *
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strEnrollMedicalPlan", "strSpouseOrParentWork"})
    public void selectATIMedicalPlanAndDependnetWorkAnswers(String strEnrollMedicalPlan, String strSpouseOrParentWork) {

        try {

            this.selectMedicaPlanAndDependentWorkAnswer(strEnrollMedicalPlan, strSpouseOrParentWork);
            performAction.click(nextButton, "Next Button in ATI Physical Therapy page");

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while entering data for date explanation"
                            + " " + e.getMessage());
        }

    }

}
