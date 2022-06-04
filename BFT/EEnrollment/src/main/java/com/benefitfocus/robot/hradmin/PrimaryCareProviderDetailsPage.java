package com.benefitfocus.robot.hradmin;


import org.openqa.selenium.By;
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
public class PrimaryCareProviderDetailsPage {

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
    By facilityID = By.id("providerCode");
    By previouslyVisitedPhysicianCombobox = By.xpath("//*[@class='dtrException']//select");

    private void enterFacilityID(String facilityNumber) {
        performAction.enter(facilityID, facilityNumber, "Facility ID Text Box");
    }

    private void selectPreviouslyVisitedPhysician(String previousVisit) {
        performAction.select(previouslyVisitedPhysicianCombobox, previousVisit, "Previous Visit Combobox");
    }

    /**
     * <pre>
     * Author  : Teja P
     * source : Primary Care ProviderDetailsPage
     *
     * Description : This Keyword is used to enter Facility ID in HR-Admin Role
     * and clicks Next button
     *
     * PreCondition : Primary Care Provider details should be displayed under Current Benefits
     *
     * PostCondition : Page will be displayed as per the Group configuration.Previous Visit Combobox should be displayed
     *
     * <b>Parameters & Example </b>
     *
     * <b>facilityID </b>
     * |2355 or 2345 or etc..|
     *
     * <b>previousVisit </b>
     * |Yes or No|
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strfacilityID", "strpreviousVisit"})
    public void enterPrimaryCareProviderDetails(String strfacilityID, String strpreviousVisit) {

        try {
            this.enterFacilityID(strfacilityID);
            performAction.click(nextButton, "Next Button in Medicare page");

            utils.sleep(2000);
            this.selectPreviouslyVisitedPhysician(strpreviousVisit);
            performAction.click(nextButton, "Next Button in Medicare page");

        } catch (Exception e) {
            throw new CustomException(
                    "Exception occured while entering data for Primary Care provider details"
                            + " " + e.getMessage());
        }
    }
}
