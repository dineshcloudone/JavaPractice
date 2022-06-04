package com.benefitfocus.robot.vista.carriers;


import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class CarrierSearchPage {
	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Utilities utils;
	
	@Autowired
    protected Logging logger;
	
	@Autowired
	protected ManageBrowser browser;
	
	@Autowired
	protected Screenshot scr;

	By eeSearchBox = By.id("searchCriteria");
	By eeSearchButton = By.xpath("//strong[text()='Search']");
	By basicsSubMenuCarrier=By.xpath("//td[@id='innerContextualMenu-carrierSetupBasicsTab']/a");
	By datesAndRulesSeubMenuCarrier=By.xpath("//td[@id='innerContextualMenu-carrierSetupDatesAndRulesTab']/a");
	By languageAndAgreementsSeubMenuCarrier=By.xpath("//td[@id='innerContextualMenu-carrierSetupLanguageAndAgreementsTab']/a");
	By surveysAndHealthStatementsSeubMenuCarrier=By.xpath("//td[@id='innerContextualMenu-carrierSetupSurveyHealthStatementsTab']/a");
	By benefitConfigurationTabSeubMenuCarrier=By.xpath("//td[@id='innerContextualMenu-carrierSetupBenefitConfigurationTab']/a");
	By setupIndividualSeubMenuCarrier=By.xpath("//td[@id='innerContextualMenu-carrierSetupIndividualTab']/a");
	By basics=By.id("innerLinkcarrierSetupBasicsTab");
	By datesAndRules=By.id("innerLinkcarrierSetupDatesAndRulesTab");
	By languageAndAgreements=By.id("innerLinkcarrierSetupLanguageAndAgreementsTab");
	By surveyHealthStatements=By.id("innerLinkcarrierSetupSurveyHealthStatementsTab");
	private void setSearchField(String strSearchString) {
		performAction.enter(eeSearchBox, strSearchString, "Search textbox");
	}

	private void clickSearch() {
		if(performAction.isElementPresent(eeSearchButton)){
			performAction.click(eeSearchButton, "search button");
		}else{
			performAction.click(By.linkText("Search"), "Search Button");
		}		
	}
	
	/**
     * <pre>
     * Author  : Arun Kasarla
     * 
     * Description : 'performCarrierSearch' keyword used to perform the Carrier search operation
	 * in vista role.
	 * 
	 * Role : Vista Role
     * 
     * PreCondition : Carrier search page in Vista Role
     *
     * PostCondition : Carrier page should be opened successfully.
     * 
     * <b>Parameters & Example </b>
     *
     * | strSearchString - search string |
     * </pre>
     * Java file Name :  CarrierSearchPage.java
     **/
	@RobotKeyword
	@ArgumentNames({ "strCarrierName" })
	public void performCarrierSearch(String strCarrierName) {
		try {
			
			strCarrierName = utils.getValue(strCarrierName);

			this.setSearchField(strCarrierName);

			// Click search button
			this.clickSearch();

			this.verifyCarrierSearchResults(strCarrierName);

		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing carrier search "
					+ e.getMessage());
		}
	}

	/**
	 * Method to verify the Carrier search results
	 */
	private void verifyCarrierSearchResults(String strCarrierName) {

		try {
			performAction.click(By.linkText(strCarrierName), "carrier name");
			scr.capturePageScreenshot();
			performAction.verifyMessage(strCarrierName);

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing click on carrier search "
					+ e.getMessage());
		}
	}
	/**
     * <pre>
     * Author  : Nagarjuna Behara
     * 
     * Description : 'verifySubLinks' keyword used to verify the links under Menu Items
	 * 
	 * Role : Vista Role
     * 
     * PreCondition : Carrier search page in Vista Role
     *
     * PostCondition : Links verified under menu items successfully.
     * 
     * <b>Parameters & Example </b>
     *
     * | strMenuItem - menu name as string |
     * </pre>
     * Java file Name :  CarrierSearchPage.java
     **/
	@RobotKeyword
	@ArgumentNames({ "strMenuItem" })
	public void verifySubLinks(String strMenuItem) {
		if (strMenuItem.startsWith("td:"))
			strMenuItem = strMenuItem.substring(3);
		try {
			Object object;
			JSONObject fields = ReadJsonTestData.getTestData(strMenuItem
					.toLowerCase());
			object = fields.get("basics");
			if (object != null) {
				performAction.mouseOver(basics, "BASICS");
				String[] linkName=object.toString().split(",");                            				
				List<WebElement> elements=browser.getCurrentWebDriver().findElements(basicsSubMenuCarrier);                                          
				for(WebElement e:elements)
				{   
					for(int i=0;i<linkName.length;i++)
					{
						if(e.getText().equalsIgnoreCase(linkName[i]))						
						logger.info(e.getText()+" verified in the page");												
					}					
				}
			}
			object = fields.get("datesandrules");
			if (object != null) {
				performAction.mouseOver(datesAndRules, "DatesAndRules");
				String[] linkName=object.toString().split(",");                            				
				List<WebElement> elements=browser.getCurrentWebDriver().findElements(datesAndRulesSeubMenuCarrier);                                          
				for(WebElement e:elements)
				{   
					for(int i=0;i<linkName.length;i++)
					{
						if(e.getText().equalsIgnoreCase(linkName[i]))						
						logger.info(e.getText()+" verifyed in the page");												
					}					
				}
			}
			object = fields.get("languageandagreements");
			if (object != null) {
				performAction.mouseOver(languageAndAgreements, "LanguageAndAgreements");
				String[] linkName=object.toString().split(",");                            				
				List<WebElement> elements=browser.getCurrentWebDriver().findElements(languageAndAgreementsSeubMenuCarrier);                                          
				for(WebElement e:elements)
				{   
					for(int i=0;i<linkName.length;i++)
					{
						if(e.getText().equalsIgnoreCase(linkName[i]))						
						logger.info(e.getText()+" verifyed in the page");												
					}					
				}
			}
			object = fields.get("surveyhealthstatements");
			if (object != null) {
				performAction.mouseOver(surveyHealthStatements, "SurveyHealthStatements");
				String[] linkName=object.toString().split(",");                            				
				List<WebElement> elements=browser.getCurrentWebDriver().findElements(surveysAndHealthStatementsSeubMenuCarrier);                                          
				for(WebElement e:elements)
				{   
					for(int i=0;i<linkName.length;i++)
					{
						if(e.getText().equalsIgnoreCase(linkName[i]))						
						logger.info(e.getText()+" verifyed in the page");												
					}					
				}
			}
		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing  "
					+ e.getMessage());
		
	}
		}
}
