package com.benefitfocus.robot.member;

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

@RobotKeywords
public class RateFactorSurveyPage {
	
	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Logging logger;
	
	@Autowired
	protected ManageBrowser browser;
	
	@Autowired
	protected Screenshot scr;
	//Locators
	
	By RFSNo = By.xpath("//span[contains(text(),'NO')]");
	By RFSYes = By.xpath("//span[contains(text(),'YES')]");
	By RFSQue = By.xpath("//h3");
	By RFSSave = By.xpath("//button[@class='btn btn-primary']");
	By Error = By.id("errors");
	By EmpFirstName = By.xpath("(//span[@class='message'])[2]") ;
	By skipShopping = By.xpath("//span[contains(text(),'Skip')]");
	By beginEnroll = By.xpath("//a[text()='Begin enrollment']");
	By returnHome = By.id("returnHome");
	
	
	//Perform click on save button
	private void clickSave() {
		performAction.click(RFSSave, "Save Button");
	}
	//Perform click on RFS Question in home page
	private void clickRfs(String StrQuest) {
		String Quest = "//span[text()='"+StrQuest+"']";
		performAction.click(By.xpath(Quest), "Survey Question");
	}

	private void clickResponse(String StrOption) {
		String opt = "//span[contains(text(),'"+StrOption+"')]";
		performAction.click(By.xpath(opt), "Option to be selected");
		
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : createGlobalRateFactorSurvey keyword or method is used to select an option for the questions in Member role.   
     * 
      * PreCondition : Member should be in Beneficiary Information page
     * 
      * PostCondition : New Beneficiary is saved successfully.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrOption | 
      * | Yes / No - String type argument takes YES/No or any option which has to select as an input  |
      * 
      * JavaFileName : RateFactorSurevyPage.java
      * </pre> 
      **/
	
@RobotKeyword
@ArgumentNames({"StrOption"})
public void performRateFactorSurevy(String StrOption) {
	try {
		if(performAction.isElementPresent(RFSQue)){
			String RFSQuest = browser.getCurrentWebDriver().findElement(RFSQue).getText();
			logger.info("Survey Question is..." +RFSQuest);
			
			this.clickResponse(StrOption);
		}
		scr.capturePageScreenshot();
		this.clickSave();
		if(performAction.isAlertPresent()){
			performAction.acceptAlert();
		}
		
	} catch (Exception e) {
		scr.capturePageScreenshot();
		logger.warn("Exception occured " + e.getMessage());
		throw new CustomException(
				"Exception while verifying and performing operations in RFS page "
						+ e.getMessage());
		}
	}

/**
 * <pre> 
  * Author  : Nagarjuna Thallam
 *  
  * Description : returnToRateFactorSurveyPage keyword or method to perform Return operation to 
  * the Member home page after completing the survey by selecting the survey from the member Home page.   
 * 
  * PreCondition : Member must be complete the Rate Factor Survey
 * 
  * PostCondition : member successfully redirected to Rate factor survey page.
 *  
  * <b>Parameters & Example </b> 
  * 
  * | StrSurvey | 
  * | Tobacco Survey / Auto Test  - String type argument takes Survey name as an input  |  
  * 
  *  JavaFileName : RateFactorSurevyPage.java
  * </pre> 
  **/

	@RobotKeyword
	@ArgumentNames({"StrSurvey"})
	public void returnToRateFactorSurveyPage(String StrSurvey) {
		try {
			if(performAction.isElementPresent(skipShopping)){
				performAction.click(skipShopping, "Skip Button in Shopping");
				if(performAction.isElementPresent(beginEnroll)){
					performAction.click(returnHome, "Return Home Button");
					this.clickRfs(StrSurvey);
				}
			}
			else if(performAction.isElementPresent(returnHome)){
				performAction.click(returnHome, "Return Home Button");
				this.clickRfs(StrSurvey);
			}
			else {
				this.clickRfs(StrSurvey);
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while verifying and performing operations in RFS pageException while Redirecting" +
					" Home and It is not redirected to neighter guided shopping" +
						"nor offers page "
							+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	  * Author  : Nagarjuna Thallam
	 *  
	  * Description : createGlobalRateFactorSurvey keyword or method used to verify the rate factor survey names and survey Questions in
	 *  Rate factor survey page for the member role .  
	 * 
	  * PreCondition :  Member must be in the Rate Factor Survey page 
	 * 
	  * PostCondition : member successfully redirected to member offers page or guided shopping page.
	 *  
	 *   JavaFileName : RateFactorSurevyPage.java
	  * </pre> 
	  **/
	
	@RobotKeyword
	@ArgumentNames({"strQuestion"})
	public void verifyRateFactorSurvey(String strQuestion) {
		try {
			String Survey = browser.getCurrentWebDriver().findElement(By.xpath("//h2/span")).getText();			
			logger.info("Survey Name is......" +Survey);
			String Question = browser.getCurrentWebDriver().findElement(By.xpath("//h3")).getText();
			logger.info("Survey Quesyion is ..."+Question);
			if(strQuestion.equalsIgnoreCase(Question)){
				logger.info("Survey Question is same");
			}
			else{
				logger.error("Survey Question is different");
			}
			int count = browser.getCurrentWebDriver().findElements(By.xpath("//span[@class='message']")).size();
			for (int i = 2; i<=count ; i++){
				By msgLocator = By.xpath("(//span[@class='message'])["+i+"]");
				String message = browser.getCurrentWebDriver().findElement(msgLocator).getText();
				logger.info("Employee name is..." +message);
			}
			scr.capturePageScreenshot();
			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception while verifying RFS page "
							+ e.getMessage());
		}
	}
}

