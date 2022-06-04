package com.benefitfocus.robot.member;

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
public class AgreementsPage {
	
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
	
	By agreementNo = By.xpath("//input[@id='answer[false]']");
	By agreementYes = By.xpath("//input[@id='answer[true]']");
	By nextAgreementScreen = By.xpath("//button[@class='btn btn-primary']");
	By nextOnNotePage = By.xpath("//button[contains(text(), 'Next')]");

	// Private Methods
	
	
	private void disAgreement() {
		performAction.click(agreementNo, "DisAgree Agreement");
		performAction.click(nextAgreementScreen, "Click Next");
	}
	
	private void agreement() {
		performAction.click(agreementYes, "Agree Agreement");
		performAction.click(nextAgreementScreen, "Click Next");
		if (performAction.isElementPresent(nextOnNotePage)) {
			performAction.click(nextOnNotePage, "Click on Next in Note Page");
		}
	}
	
	// Robot Methods
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Verify Employee Agreements for Member in Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Home Page Opened in Member Role
	 * 
	 * PostCondition : Verify Employee Agreements for Member in Member Role
	 * 
	 * Java File Name : AgreementsPage.java
	 * 
	 * | strAgreement |
	 * | ex: Agree/DisAgree |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAgreement" })
	public void verifyEmployeeAgreementsInMemberRole(String strAgreement) {
		try {
			
			if (strAgreement.equalsIgnoreCase("Disagree")) {
				this.disAgreement();
			}else if (strAgreement.equalsIgnoreCase("Agree")) {
				this.agreement();
			}
			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Agreement Radio Button Found"
					+ e.getMessage());
		}
	}
	
}
