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
public class EditBenefitChangeReasonPage {

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

	
	//By nextButton=By.xpath("(//*[@class='btn btn-success'])[1]");
	
	By nextButton = By.xpath("//strong[text()='Next']");
	By dateReason=By.id("reasonEntry");
	
	String mediacalDetails = "(//div[@class='clumpRegion' or @id='furlPanels'])[1]";
	String hsaAccountDetails = "(//div[@class='clumpRegion'])[2]";
	String fsaAccountDetails = "(//div[@class='clumpRegion'])[3]";
	String wellnessprogram = "(//div[@class='clumpRegion'])[4]";
	
	private void editDueChangeReason(String editReasonForChange) {
		if (editReasonForChange.toUpperCase().trim().equalsIgnoreCase("fsa")) {
			By editChangeReason = By.xpath(fsaAccountDetails + "//strong");
			performAction.click(editChangeReason,
					"Edit Due to Change Reason for " + editReasonForChange);
		} else if (editReasonForChange.toUpperCase().trim()
				.equalsIgnoreCase("hsa")) {
			By editChangeReason = By.xpath(hsaAccountDetails + "//strong");
			performAction.click(editChangeReason,
					"Edit Due to Change Reason for " + editReasonForChange);
		} else if (editReasonForChange.toUpperCase().trim()
				.equalsIgnoreCase("medical")) {
			By editChangeReason = By.xpath(mediacalDetails + "//strong");
			performAction.click(editChangeReason,
					"Edit Due to Change Reason for " + editReasonForChange);
		} else if (editReasonForChange.toUpperCase().trim()
				.equalsIgnoreCase("wellness program")) {
			By editChangeReason = By.xpath(wellnessprogram + "//strong");
			performAction.click(editChangeReason,
					"Edit Due to Change Reason for " + editReasonForChange);
		} else if (editReasonForChange.toUpperCase().trim()
				.equalsIgnoreCase("current")) {
			By editDueToChangeReason = By.linkText("Edit due to Change Reason");
			performAction.click(editDueToChangeReason,
					" Clicking Edit Due to Change Reason for "
							+ editReasonForChange);
		}
	}
	
	
	
	/**
	 * <pre>
	 * Author  : Teja Puchala
	 * source : EditBenefitChangeReasonPage
	 *  
	 * Description   : This keyword is used to edit the change Reason for medical related benefits and other single benefit
	 * in HR-Admin Role
	 * 
	 * PreCondition  : Edit Due to change Reason page of benefit should be displayed
	 * 
	 * PostCondition : Page will be displayed as per the Group configurations. Reason for benefit change page should be displayed
	 * 
	 * <b>Parameters & Example </b> 
	 * 
	 * | strChange - fsa,hsa,medical,wellness program,current |
	 * 
	 * current parameter can be used for single plan benefit
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strChangeReason" })
	public void editDueToChangeReason(String strChangeReason) {
		try {
			
			if(strChangeReason.equals("edit")){
				performAction.click(By.linkText("Edit due to Change Reason"), "Click on Edit Due to Change Reason");
				
			}else{
				this.editDueChangeReason(strChangeReason);
			}

			

		} catch (Exception e) {
			throw new CustomException(
					"Exception occured while editing the benefit detail "
							+ strChangeReason + "+:+" + e.getMessage());
		}
	}

}
