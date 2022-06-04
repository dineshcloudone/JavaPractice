package com.benefitfocus.robot.vista.groups;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.Autowired;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.Utilities;

public class ImportGroupConfig {

	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ReadJsonTestData readJson;

	// Selenium object locators
	By newGroupName = By.id("newSponsorName");
	By newSponsoringCarrierId = By.id("newSponsoringCarrierId");
	By newSponsorId = By.id("newSponsorId");
	By newGmaxTemplateId = By.id("newGmaxTemplateId");
	By sponsorFileName = By.id("sponsorFile");

	// Set user name in textbox
	/*private void setUserName(String strUserName) {
		performAction.enter(eeUsername, strUserName, "username textbox");
	}

	// Set password in password textbox
	private void setPassword(String strPassword) {
		performAction.enter(eePassword, strPassword, "Password textbox");
	}

	// Click on login button
	private void clickResetSession() {
		performAction.click(resetSession, "reset session link");
	}*/

}
