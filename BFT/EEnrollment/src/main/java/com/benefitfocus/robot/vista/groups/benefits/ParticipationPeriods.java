package com.benefitfocus.robot.vista.groups.benefits;

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
public class ParticipationPeriods {

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

	//object locators
	By CreateParticipationPeriod = By.xpath("//a[contains(text(),'Create New Parti')]");
	By ParticipationPeriodtext =By.xpath("//td/h1[contains(text(),'Create Particip')]");
	By ParticipationPeriodlistMessage=By.xpath("//div[contains(text(),'Participation Periods')]");
	By Effectivedatefield  = By.id("startDate");
	By Expirationdatefield  = By.id("endDate");
	By saveButton          =By.xpath("//strong[text()='Save']");

	//Effectivedate field
	private void effectiveDate(String strEffectiveDate){
		performAction.clearEnter(Effectivedatefield, strEffectiveDate, "Effective Date Field");
	}

	//Expiration Date Field
	private void expirationDate(String strExpirationDate) throws Exception{

		performAction.clearEnter(Expirationdatefield, strExpirationDate, "Expiration Date Field");
		//click on Save button
		Thread.sleep(2000);
		performAction.click(saveButton, "Click on Save Button");
	}
	//createParticipationPeriod
	private void participationperiod( String strEffectiveDate, String  strExpirationDate) throws Exception{
		try {
			performAction.click(CreateParticipationPeriod, "click on CreateParticipationPeriod Button");
			performAction.waitForPageLoad();
			String ExpectedPage = "Create Participation Period";
			String ActualPage =browser.getCurrentWebDriver().findElement(ParticipationPeriodtext).getText();

			if (ExpectedPage.trim().equals(ActualPage.trim())) {

				logger.info("your page  has been verifed successfully : "+"'"+ActualPage+"'"+".");
				//EffectiveDate
				this.effectiveDate(strEffectiveDate);
				//ExpirationDate
				this.expirationDate(strExpirationDate);

				boolean flag =performAction.isElementPresent(ParticipationPeriodlistMessage);

				if (flag) {

					logger.info("Your  participation period dates has been created successfully.");

				}else {
					logger.info("your  participation period dates has not been created successfully.");

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 * 
	 * Description  : createVistaParticipationPeriod Keyword or method is used to perform creating NewParticipationPeriods in Vista Admin Role.
	 * 
	 * Role : Vista Role
	 * 
	 * Precondition : Participation Periods Page in Vista Admin  Role.
	 * 
	 * PostConditions : Participation Periods is saved successfully.
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | classification | 
	 * 
	 * | createparticipationperiod - is used to get the data set from the Json File "eEnrollmentCommon.json". | 
	 * 
	 * 
	 * Java File Name : ParticipationPeriods.java
	 * 
	 * </pre>
	 * 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strParticipationPeriodDetails"} )
	public void createVistaParticipationPeriod(String participationPeriodDetails ) {

		try {

			if (participationPeriodDetails.startsWith("td:"))
				participationPeriodDetails = participationPeriodDetails.substring(3);

			Object object = null;
			Object object1 = null;
			JSONObject fields = ReadJsonTestData.getTestData(participationPeriodDetails.toLowerCase());
			System.out.println("fields = " + fields.toJSONString());

			object = fields.get("effectivedate");
			object1 =fields.get("expirationdate");

			if (object != null) {
				this.participationperiod(object.toString(), object1.toString());
			}

		} catch (Exception e) {
			logger.info("Exception occured while Creating Participation Period  "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Creating Participation Period "
					+ e.getMessage());

		}


	}

}
