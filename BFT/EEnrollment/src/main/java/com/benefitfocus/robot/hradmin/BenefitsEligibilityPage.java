package com.benefitfocus.robot.hradmin;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.Screenshot;
@RobotKeywords
public class BenefitsEligibilityPage {
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;	
	@Autowired
	protected Screenshot scr;
	// Locators in Benefits Eligibility Page
	By benefit=By.xpath("//h3[@class='media-heading has-sub-heading']");
	By benefitsElegibilityHeading=By.xpath("//div[@id='be-heading-content']//h2");
	By currentBenefit=By.xpath("//div[@class='col-md-8']//h2");
	
	
	
	private void verifyPlan(String strPlanName,String strCoverageLaevel){
		By planName=By.xpath("//div[@class='col-md-8']//h2[text()='"+strPlanName+"']");
		performAction.verify(planName, strPlanName, "Plan Name");
		By coverageName=By.xpath("//table//td[text()='"+strCoverageLaevel+"']");
		performAction.verify(coverageName, strCoverageLaevel, "Coverage Level");
	}
	
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : This keywprd is used to verify the benefit eligibility and benefit enrollment for subscriber in Benefits Eligibility page HR Admin Role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : HR admin logged in and Navigate to Benefits Eligibility page
	 * 
	 * PostCondition : verify the Benefit eligibility and benefit 
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * |"strPlanName" , "strCoverageLevel" | 
	 * | Medical 2016 , Employee Only |
	 * 
	 * Java file Name : BenefitsEligibilityPage.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPlanName" , "strCoverageLevel" })
	public void viewBenefitEligibility(String strPlanName, String strCoverageLaevel)
	{
		performAction.verify(benefitsElegibilityHeading, "Benefit eligibility", "Benefit eligibility");
		performAction.click(benefit, "Benefit Name");
		this.verifyPlan(strPlanName,strCoverageLaevel);
	}

}
