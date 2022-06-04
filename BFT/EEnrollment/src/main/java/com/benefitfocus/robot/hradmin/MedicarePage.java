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
public class MedicarePage {

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
                        if (name.trim().toLowerCase()
                                .contains(radioButtonName.trim().toLowerCase())) {
                            Thread.sleep(3000);
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

            //System.out.println("============================RadioButton Rows:"+radioButtonRows);


            if (!Status) {
                throw new RuntimeException("Failed to select the Radio Button :" + radioButtonName);
            }

        } catch (Exception e) {
            System.out.print("Failed to select the Radio Button :" + radioButtonName);
            e.printStackTrace();
        }

    }


    /**
     * <pre>
     * Author  : Teja P
     *
     * Description : This Keyword is used to select answer in combobox for other coverage survey question in HR-Admin
     * and clicks Next button
     *
     * PreCondition : This keyword is used in SEFL group
     *
     * <b>Parameters & Example </b>
     *
     *
     * <b>strSEFLPlanEligibility Example :</b>
     * |Yes, my spouse has access to other coverage |Yes, my dependent children have access to other coverage|
     * |Yes, both my spouse and dependent children have access to other coverage|
     * |No, neither my spouse or dependent children have access to other coverage|
     * |No, I do not have a spouse or dependent children |
     *
     *
     * </pre>
     **//*
    @RobotKeyword
	@ArgumentNames({"strOtherCoverageSurvey"})
	public void selectComboboxAnswerForOtherCoverageSurvey(String strOtherCoverageSurvey) {

		try {
			performAction.select(selectSpouseCoverageAnswer, strOtherCoverageSurvey, "Dependent Coverage Combobox");
			
			performAction.click(nextButton, "Clicking Next button in Select answer for Other Coverage in SEFL group");			
			
		} catch (Exception e) {

			throw new CustomException(
					"Exception occured while selecting select answer for Other Coverage in SEFL group"
							+ " " + e.getMessage());
		}
		
	}
	*/


    /**
     * <pre>
     * Author  : Teja P
     * source : MedicarePage
     * Description : This Keyword selects the Radio Button answers for Medicare question in HR-Admin Role
     * and clicks Next button
     *
     * PreCondition : Medicare Question page should be displayed
     *
     * PostCondition : Page will be displayed as per the Group Configurations. Addtional Insurance options should be displayed.
     *
     * <b>Parameters & Example </b>
     *
     * <b>strYesNo Example :</b>
     * |Yes,"No, neither the employee nor any covered dependent is enrolled in Medicare."|
     *
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strYesNo"})
    public void selectMedicareAnswer(String strYesNo) {

        try {
            this.selectRadioButton(strYesNo);
            performAction.click(nextButton, "Next Button in Medicare page");

        } catch (Exception e) {

            throw new CustomException(
                    "Exception occured while selecting Yes or No for Medicare question"
                            + " " + e.getMessage());
        }
    }

}
