package com.benefitfocus.robot.vista.groups.users;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
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
public class Agents {

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

    // Locators on the Page

    By selectPrimaryAgent = By.linkText("Select Primary Agent");
    By assignPrimaryAgent = By.linkText("Assign Primary Agent");
    By updatePrimaryAgent = By.linkText("Update Primary Agent");
    By addAdditionalAgent = By.linkText("Add Additional Agent");
    By agentSearch = By.id("agentSearchCriteria");
    By searchButton = By.xpath("//a[@class='buttonInnerLink']//strong[contains(text(),'Search')]");
    By assignAgent = By.linkText("Assign Agent");
    By updateAgentSettings = By.xpath("//a[@class='buttonInnerLink']//strong[contains(text(),'Update Agent Settings')]");

    // Private Methods

    private void clickSelectPrimaryAgent() {
        performAction.click(selectPrimaryAgent, "Click Select Primary Agent Link");
    }

    private void clickAssignPrimaryAgent() {
        performAction.click(assignPrimaryAgent, "Click Assign Primary Agent Link");
    }

    private void enterAgentLastName(String strAgentLastName) {
        performAction.enter(agentSearch, strAgentLastName, "Enter Agent Last Name");
    }

    private void clickSearchButton() {
        performAction.click(searchButton, "Click Search Button");
    }
    private void clickAssignAgent() {
        performAction.click(assignAgent, "Click Assign Agent");
    }

    private void clickUpdateAgentSettings() {
        performAction.click(updateAgentSettings, "Click Update Agent Settings");
    }

    private void clickAddAdditionalAgentLink() {
        performAction.click(addAdditionalAgent, "Click Add Additional Agent");
    }
    //Robot Keywords

    /**
     * <pre>
     * Author  : Varun Reddy Proddutoori
     *
     * Description  : Set/Assign Agent as Primary to Group in Vista Role
     *
     * Role : Vista Role
     *
     * PreCondition : Users >> Agents Page Opened
     *
     * PostCondition : Agent is Assigned to Group successfully
     *
     * Java File: Agents.java
     *
     * Parameters
     * | strAgentLastName|
     * |td:strAgentlastname/HMVagentlastname |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({ "strAgentLastName" })
    public void assignAgentToGroup(String strAgentLastName) {
        try {
            if (performAction.isElementPresent(updatePrimaryAgent)) {
                this.clickAddAdditionalAgentLink();
            }else {
                this.clickSelectPrimaryAgent();
                this.clickAssignPrimaryAgent();
            }
            if(strAgentLastName.startsWith("HMV")){
            	strAgentLastName=utils.getValue(strAgentLastName);
            	this.enterAgentLastName(strAgentLastName);
    		}
            this.clickSearchButton();
            performAction.waitForPageLoad();
            this.clickAssignAgent();
            this.clickUpdateAgentSettings();
            scr.capturePageScreenshot();
            performAction.verifyMessage(strAgentLastName);
        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception occured while assigning New Agent to Group"
                    + e.getMessage());
        }
    }
}