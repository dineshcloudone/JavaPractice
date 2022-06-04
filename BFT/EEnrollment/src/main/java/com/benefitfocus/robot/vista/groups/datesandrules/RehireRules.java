package com.benefitfocus.robot.vista.groups.datesandrules;

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
import com.benefitfocus.robot.vista.groups.basics.BasicCommon;



@RobotKeywords	
public class RehireRules {
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
	@Autowired
	protected BasicCommon common;
	
	public enum strRehireRules {
		ASIFTHEEMPLOYEEWASNEVERTERMINATED, WITHNOLAPSEINCOVERAGE, WITHAWAITPERIOD, WITHOUTAWAITPERIOD, RETRODATERULE
	};

	
	//Locators on this page
	
	By rehireRulesEditButton = By.xpath("//img[@alt='Edit Contents']");
	By asIfTheEmployeeWasNeverTerminated = By
			.xpath("//tr[@class='fieldListRow']/td/label[contains(text(),'As if the employee was never terminated')]");
	By withNoLapseInCoverage = By
			.xpath("//tr[@class='fieldListRow']/td/label[contains(text(),'With no lapse in coverage')]");
	By withAWaitPeriod = By
			.xpath("//tr[@class='fieldListRow']/td/label[contains(text(),'With a wait period')]");
	By WithoutAWaitPeriod = By
			.xpath("//tr[@class='fieldListRow']/td/label[contains(text(),'Without a wait period')]");
	By retroDateRule = By
			.xpath("//tr[@class='fieldListRow']/td/label[contains(text(),'Retro Date Rule')]");
	By terminatedAlwaysRadioButton = By
			.xpath("//input[@id='neverTerminated.dateRuleType[ALWAYS]']");
	By terminatedNeverRadioButton = By
			.xpath("//input[@id='neverTerminated.dateRuleType[NEVER]']");
	By noLapseAlwaysRadioButton = By
			.xpath("//input[@id='noLapseInCoverage.dateRuleType[ALWAYS]']");
	By noLapseNeverRadioButton = By
			.xpath("//input[@id='noLapseInCoverage.dateRuleType[NEVER]']");
	By withWaitAlwaysRadioButton = By
			.xpath("//input[@id='withWaitPeriod.dateRuleType[ALWAYS]']");
	By withWaitNeverRadioButton = By
			.xpath("//input[@id='withWaitPeriod.dateRuleType[NEVER]']");
	By withOutWaitAlwaysRadioButton = By
			.xpath("//input[@id='withoutWaitPeriod.dateRuleType[ALWAYS]']");
	By withOutWaitNeverRadioButton = By
			.xpath("//input[@id='withoutWaitPeriod.dateRuleType[NEVER]']");
	By retroDateDoNotDistrub = By
			.xpath("//input[@id='retroDate.dateRuleType[ALWAYS]']");
	By retroDateRestrictToMonths = By
			.xpath("//input[@id='retroDate.dateRuleType[RESTRICT_MONTHS]']");
	By savebutton = By.linkText("Save");
	
	private void clickRehireRulesEditButton(){
		
		if(performAction.isElementPresent(rehireRulesEditButton))
		{
			//performAction.mouseOver(rehireRulesEditButton, "Rehire Rules Edit Button");
			performAction.click(rehireRulesEditButton, "Rehire Rules Edit Button");
		 
			logger.info("Rehire Rules Edit page is displayed");
		}
	}

	private void updateRehireRules(String strlabelName, String strValue){
		
		logger.info("Configuring Rehire Rules");
		
		strRehireRules sln = strRehireRules.valueOf(strlabelName.toUpperCase());
		System.out.println(sln);
		
		switch (sln) {
		
		case ASIFTHEEMPLOYEEWASNEVERTERMINATED:
				if(strValue.equalsIgnoreCase("Always")){
					
					performAction.click(terminatedAlwaysRadioButton, "Always Radio Button");
					
				}else if(strValue.equalsIgnoreCase("Never")){
					
					performAction.click(terminatedNeverRadioButton, "Never Radio Button");
					
				}
				break;
		case WITHNOLAPSEINCOVERAGE:
				if(strValue.equalsIgnoreCase("Always")){
					
					performAction.click(noLapseAlwaysRadioButton, "Always Radio Button");
					
				}else if(strValue.equalsIgnoreCase("Never")){
					
					performAction.click(noLapseNeverRadioButton, "Never Radio Button");
					
				}
				break;
		case WITHAWAITPERIOD:
				if(strValue.equalsIgnoreCase("Always")){
					
					performAction.click(withWaitAlwaysRadioButton, "Always Radio Button");
					
				}else if(strValue.equalsIgnoreCase("Never")){
					
					performAction.click(withWaitNeverRadioButton, "Never Radio Button");
					
				}
				break;
		case WITHOUTAWAITPERIOD:
				if(strValue.equalsIgnoreCase("Always")){
					
					performAction.click(withOutWaitAlwaysRadioButton, "Always Radio Button");
					
				}else if(strValue.equalsIgnoreCase("Never")){
					
					performAction.click(withOutWaitNeverRadioButton, "Never Radio Button");
					
				}
				break;
		case RETRODATERULE:
				if(strValue.equalsIgnoreCase("Do not restrict")){
					
					performAction.click(retroDateDoNotDistrub, "Always Radio Button");
					
				}else if(strValue.equalsIgnoreCase("Restrict to")){
					
					performAction.click(retroDateRestrictToMonths, "Never Radio Button");
					
				}
				break;
		}
		performAction.click(savebutton, "Save Button");
	}
		


	/**
	 * <pre> 
	 * Author  : Rajeswari Nimmala
	 *  
	 * Description : 'updateRehireRules' keyword used to update the Rehire Rules configurations in vista role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : User should be in Date & Rules / Rehire Rules  Page
	 * 
	 * PostCondition : user  will be redirected to the Rehire Rules page with updated configurations
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |selectValue -Radio button  Value to be selected |
	 * 
	 * Java file Name :  RehireRules.java
	 * 
	 * </pre> 
	 * 
	 **/
	@RobotKeyword
	@ArgumentNames({"labelName ","selectValue" })
	public void updateRehireRulesConfigurations(String strlabelName,String strSelectValue){
		try	{
			this.clickRehireRulesEditButton();
		    this.updateRehireRules(strlabelName, strSelectValue);
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured " + e.getMessage());
			throw new CustomException("Exception in performing update identification information "
					+ e.getMessage());
		}
	}	
}
