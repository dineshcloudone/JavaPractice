package com.benefitfocus.robot.hradmin;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class SummaryOfBenefitsAndCoverageReview {

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

    By checkBox = By.id("customAnswer0");
    By clickNext = By.linkText("Next");

    // To edit the benefit detail from Hr-Admin role
    private void clickBenfitReviewChecBox() {

        utils.sleep(2000);
        performAction
                .jsclick(checkBox,
                        "Acknowledging the Summary of Benefits and Coverage for the plan you elected.");

    }

    private void clickNextButtonInBenefitCoverageReviewPage() {
        utils.sleep(2000);
        performAction.jsclick(clickNext,
                "Next button in Tobacco Affidavit Page");
    }

    /**
     * <pre>
     * Author  : Teja Puchala
     * source: hradmin/SummaryOfBenefitsAndCoverageReview
     *
     * Description   : This keyword is used to select the checkbox to acknowledge the summary of Benefits and Benefits Coverage
     *
     * PreCondition  : Summary of Benefits and Coverage page should be displayed
     *
     * PostCondition : Page will be displayed as per the group configurations, Effective date page will be displayed.
     *
     * </pre>
     **/
    @RobotKeyword
    public void selectSummaryOfBenefitsAndCoverageReviewCertification() {
        try {
            this.clickBenfitReviewChecBox();
            this.clickNextButtonInBenefitCoverageReviewPage();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            e.printStackTrace();
            throw new RuntimeException(
                    "Exception occured while Acknowledging the Summary of Benefits and Coverage for the plan you elected.");
        }
    }

}
