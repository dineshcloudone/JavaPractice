package com.benefitfocus.robot.hradmin;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class TobaccoAffidavitPage {

    @Autowired
    protected Utilities utils;
    @Autowired
    protected ActionKeywords performAction;
    @Autowired
    protected Logging logger;
    @Autowired
    protected Screenshot scr;

    By checkBox = By.id("customAnswer0");
    By clickNext = By.linkText("Next");

    // To edit the benefit detail from Hr-Admin role
    private void clickTobaccoAffidavitCheckBox() {
        utils.sleep(2000);
        performAction.jsclick(checkBox, "Acknowledging the Tobacco Affidavit");

    }

    private void clickNextButtonInTobaccoAffidavitPage() {
        utils.sleep(2000);
        performAction.jsclick(clickNext,
                "Next button in Tobacco Affidavit Page");
    }

    /**
     * <pre>
     * Author  : Teja Puchala
     * source: hradmin/TobaccoAffidavitPage
     *
     * Description   : This keyword is used to select the checkbox to acknowledge that any misleading information given could result in coverage termination
     * and disciplinary action
     *
     * PreCondition  : Tobacco Affidavit page should be displayed
     *
     * PostCondition : Page will be displayed as per the group configurations, Effective date page will be displayed.
     *
     * </pre>
     **/
    @RobotKeyword
    public void SelectTobaccoAffidavit() {
        try {
            this.clickTobaccoAffidavitCheckBox();
            this.clickNextButtonInTobaccoAffidavitPage();

        } catch (Exception e) {
            scr.capturePageScreenshot();
            //e.printStackTrace();
            logger.error(e.getMessage());
            throw new RuntimeException(
                    "Exception occured while Acknowledging the Tobacco Affidavit.");
        }
    }

}
