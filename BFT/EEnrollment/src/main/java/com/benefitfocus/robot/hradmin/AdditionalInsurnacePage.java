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

import java.util.*;

import org.openqa.selenium.*;

@RobotKeywords
public class AdditionalInsurnacePage {

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


    String radioButtonHeaders = "//div[@class='regionHeader']";
    String allRadioButtonsRows = "//*[@class='fieldListRow']";
    String allRadioButtonsNames = ".//*[@class='fieldListLabelRight']";
    String allRadioButtons = ".//*[@class='fieldListFieldLeft']//input";
    By nextButton = By.xpath("(//*[@class='btn btn-success'])[1]");


    //Selecting the Radio button as per its Name
    private void selectRadioButton(String radioButtonName) {
        try {

            boolean Status = false;
            List<WebElement> radioButtonRows = browser.getCurrentWebDriver().findElements(By.xpath(allRadioButtonsRows));

            //System.out.println("============================RadioButton Rows:"+radioButtonRows);

            for (WebElement element : radioButtonRows) {
                String radioButtonName1 = element.findElement(By.xpath(allRadioButtonsNames)).getText();
                //System.out.println("============================RadioButton Name1:"+radioButtonName1);
                //System.out.println("============================RadioButton Name:"+radioButtonName);
                if (radioButtonName.trim().toUpperCase().contains(radioButtonName1.toUpperCase())) {
                    Thread.sleep(2000);
                    element.findElement(By.xpath(allRadioButtons)).click();
                    Status = true;
                    break;
                }
            }
            if (!Status) {
                throw new RuntimeException("Failed to select the Radio Button" + radioButtonName);
            }

        } catch (Exception e) {
            System.out.print("Failed to select the Radio Button as per the name passed");
            e.printStackTrace();
        }

    }

    //Selecting the Radio button as per its Header Name
    private void selectRadioButtonBasedOnHeader(String strQuestion, String radioButtonName) {
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
                    if (name.trim().toLowerCase()
                            .contains(radioButtonName.trim().toLowerCase())) {
                        utils.sleep(3000);
                        NameElement
                                .findElement(
                                        By.xpath("./preceding-sibling::td/input"))
                                .click();
                        Status = true;
                        break;
                    }
                }
            }
        }

        if (!Status) {
            throw new RuntimeException("Failed to select the Radio Button :" + radioButtonName);
        }
    }

    private void clickNextButton() {
        utils.sleep(1000);
        performAction.click(nextButton, "Clicking Next Button in Additional Insurance Page");
    }

    /**
     * <pre>
     * Author  : Teja P
     * source : hradmin/AdditionalInsurancePage
     * Description : This Keyword selects the Radio Button answers for Additional Insurance question in HR-Admin Role
     * and clicks Next button
     *
     * PreCondition : Additional Insurance Question page should be displayed
     *
     * PostCondition : Page will be displayed as per the Group Configurations. Effective Date page should be displayed.
     *
     * <b>Parameters & Example </b>
     *
     * <b>strYesNo Example :</b>
     * |"Yes, and I have all the required information: Policy Number, Policyholder, Carrier's Name, and Effective Date."|
     * |"Yes, but I do NOT have all the required details."|
     * |"No, neither the employee nor any dependent has had health insurance."|
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strCoverage"})
    public void selectAdditionalInsuranceAnswer(String strCoverage) {

        try {
            this.selectRadioButton(strCoverage);
            this.clickNextButton();

        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception occured while selecting Coverage answer for Addtional Insurance"
                            + " " + e.getMessage());
        }
    }
}
