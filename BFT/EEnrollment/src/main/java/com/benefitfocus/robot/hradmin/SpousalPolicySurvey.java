package com.benefitfocus.robot.hradmin;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class SpousalPolicySurvey {

    @Autowired
    protected Utilities utils;
    @Autowired
    protected ActionKeywords performAction;

    @Autowired
    protected Logging logger;
    @Autowired
    protected Screenshot scr;

    By spousePolicy = By.xpath("//div[@id='surveyContainer']//label[contains(text(),'Answer required:')]/following-sibling::select");
    By checkBoxAcknowledgement = By.xpath("//div[@id='surveyContainer']//input");

    By clickNext = By.linkText("Next");


    // To edit the benefit detail from Hr-Admin role
    private void spousePolicySurveyComboboxValue(String SpousePolicy) {
        utils.sleep(2000);
        performAction.select(spousePolicy, SpousePolicy, "Spousal Policy Survey Combobox");

    }

    private void selectSpousePolicyAcknowledgementCheckBox() {
        utils.sleep(2000);
        performAction
                .jsclick(checkBoxAcknowledgement,
                        "Spousal Policy Survey Acknowledgement checkbox");
    }

    private void clickNextButtonInSpouseSurveyPage() {
        utils.sleep(2000);
        performAction.jsclick(clickNext, "Next button in Spousal Policy Survey Page");
    }


    /**
     * <pre>
     * Author  : Teja Puchala
     * source: hradmin/SpousalPolicySurvey
     *
     * Description   : This keyword is used to select the Spouse Policy Survey while enrolling a benefit with dependent coverage
     *
     * PreCondition  : Spouse Policy Survey page should be displayed
     *
     * PostCondition : Page will be displayed as per the group configurations, Persons Covererd page will be displayed.
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strAnswer"})
    public void selectSpousePolicySurveyAnswer(String answer) {
        try {

            this.spousePolicySurveyComboboxValue(answer);
            this.selectSpousePolicyAcknowledgementCheckBox();
            this.clickNextButtonInSpouseSurveyPage();

        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.error(e.getMessage());
            throw new RuntimeException(
                    "Exception occured while Selecting Spousal Policy Survey Combobox Value");
        }
    }

}
