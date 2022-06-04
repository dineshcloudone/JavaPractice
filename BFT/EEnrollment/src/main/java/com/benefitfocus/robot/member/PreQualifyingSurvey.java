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
public class PreQualifyingSurvey {

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected Utilities utils;

	// Pre-Qualifying survey
	By preQualifySurvey = By.id("surveyContainer");
	By preQualifyQuestion = By.className("surveyQuestionContainer");
	By empName = By.xpath("//div[@id='surveyContainer']/label");
	By Questions = By.xpath("(//span[@class='requiredIndicator']/..)");
	By nextButton = By.xpath("//button[contains(text(),'Next')]");	
	By getStarted=By.linkText("Get Started");		
	By startButton=By.xpath("//button[@class='btn btn-primary']");
	By surveyQuestionDropdown=By.cssSelector("select[id*='surveyQuestionAnswerSelection']");	
	By additionalInsurance=By.xpath("//span[text()='Yes']");
	By preQualifyingSurvey = By.xpath("//header//h4[text()='Pre Qualifying Survey']");
	By offerOptions=By.linkText("Offer Options");
	By selectPlanFromCurrentOffersFilter=By.className("DropDownList");
	By editPreQualifyingSurveys=By.linkText("Edit Prequalifying Surveys");
	By editMedicalPreQualifyingSurvey = By.xpath("//a[contains(@href, 'javascript:void(0);')]");
	By editMedicalPreQualifyingSurveys = By.xpath("//a[contains(text(),'Edit Prequalifying Survey Details')]");
	By editHSAPreQualifyingSurvey = By.xpath("(//a[contains(@href, 'javascript:void(0);')])[2]");
	By editHSAPreQualifyingSurveys = By.xpath("(//a[contains(text(),'Edit Prequalifying Survey Details')])[2]");
	By preQualifyingLogic = By.id("preQualifyingSurveyLogicType");
	By prequalifyingSurvey = By.id("selectedSurveyKey");
	By save = By.linkText("Save");

	By tobaccoCheckBox = By.xpath("//div[@class='surveyQuestionContainer'][1]/input");
	By declaration = By.xpath("//div[@class='surveyQuestionContainer'][2]/select");
	By globalDeclaration = By.xpath("//div[@class='surveyQuestionContainer'][3]/input");
	By nextOnSurveyPage = By.xpath("//button[contains(text(),'Next')]");
	By nextOnDeclarationPage = By.xpath("//button[contains(text(),'Next')]");

	// Variables used in Public methods
	String preQual="";
	// method to click on the next Button
	private void clickOnNext() {
		performAction.click(nextButton, "Next Button");
	}
	private void clickNext()
	{
		performAction.click(nextButton, "Next Button");
	}
	private void clickGetStarted()
	{
		performAction.click(getStarted, "Get Started");		
	}
	private void clickStart()
	{
		performAction.click(startButton, "Start Button");
	}
	private void selectSurveyQuestion(String lastName)
	{
		performAction.select(surveyQuestionDropdown, "Yes", "Select Survey Question");
		String member=browser.getCurrentWebDriver().findElement(By.xpath("//div[@id='surveyContainer']/label")).getText();
		if(member.contains(lastName))
		{
			System.out.println(lastName+" Verified in Survey Page");
			scr.capturePageScreenshot();
		}
	}
	private void coverageLevel(String coverage)
	{

		String coverageLevelTableLoc="//table[@class='table table-hover table-bordered table-striped']/tbody//tr";
		String tempCoverage=coverageLevelTableLoc;
		int rowSize=browser.getCurrentWebDriver().findElements(By.xpath(coverageLevelTableLoc)).size();
		String tempValue[]=new String[rowSize];
		for(int i=1;i<=rowSize;i++)
		{
			coverageLevelTableLoc=tempCoverage;
			coverageLevelTableLoc=coverageLevelTableLoc+"["+i+"]/td[2]";
			tempValue[i]=browser.getCurrentWebDriver().findElement(By.xpath(coverageLevelTableLoc)).getText();
			if(tempValue[i].equalsIgnoreCase(coverage))
			{
				coverageLevelTableLoc=coverageLevelTableLoc+"/../td[1]/a";
				browser.getCurrentWebDriver().findElement(By.xpath(coverageLevelTableLoc)).click();
				break;
			}
		}	
	}
	private void premiumDeductPremiumTax()
	{
		browser.getCurrentWebDriver().findElement(By.xpath("//label/span[text()='No']")).click();
	}
	private void additionalInsuranceOption()
	{
		browser.getCurrentWebDriver().findElement(By.xpath("//label/span[text()='No']")).click();
	}
	private void completeTobaccoSurvey() {
		performAction.click(tobaccoCheckBox, "Click Tobacco CheckBox");
		performAction.select(declaration, "I am not a smoker or user of tobacco products", "I am not a smoker or user of tobacco products");
		performAction.click(globalDeclaration, "Click Global Declaration and Agreement");
		performAction.click(nextOnSurveyPage, "Click Next on Survey Page");
	}

	private void completeDeclaration() {
		if (performAction.isElementPresent(nextOnDeclarationPage)) {
			performAction.click(nextOnDeclarationPage, "Click Next on Declaration Page");
		}
	}
	/**
	 * <pre>
	 * <b>Author :</b> | Nagarjuna Thallam |
	 * 
	 * <b>Description : </b> | Keyword or method 'completePreQualifyingSurvey'
	 * used to takes the survey questions, answers from test data from JSON file
	 * and fills the data depends on the fields. |
	 * 
	 * <b>Pre-Condition : </b> | User must be in the PreQualifying survey
	 * configured for the offer and must have the test data for the
	 * Prequalifying surveys questiong in the test data json file |
	 * 
	 * <b>Parameters : </b> | jsonTestDataKey - json testdata key with required
	 * prequalifying survey questions |
	 * 
	 * <b>Example : </b> | "dfPreQualifyingSurvey":{ "survey":"yes",
	 * "surveys":"2", "question1": "Will you be covering a spouse for Medical?",
	 * "answer1":"Yes", "question2":
	 * "Is your spouse eligible for coverage under his/her employer's plan",
	 * "answer2":"Yes" }|
	 * 
	 * JavaFileName  : PreQualifyingSurvey.java
	 * 
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "jsonTestDataKey" })
	public void completePreQualifyingSurvey(String jsonTestDataKey) {
		try {
			JSONObject survey = ReadJsonTestData.getTestData(jsonTestDataKey);

			String SurveyStatus = survey.get("survey").toString();

			if (performAction.isElementPresent(preQualifySurvey)
					&& (SurveyStatus.toLowerCase().equalsIgnoreCase("yes"))) {

				String Quest = survey.get("surveys").toString();
				int QuestionsCount = Integer.parseInt(Quest);
				// System.out.println("Questions Count is..." + QuestionsCount);
				for (int i = 1; i <= QuestionsCount; i++) {

					String Question = survey.get("question" + i).toString();
					logger.info("Question is ...." + Question);

					String QuestPath = "//label[contains(text()," + "\""
							+ Question + "\"" + ")]";

					String questId = browser.getCurrentWebDriver()
							.findElement(By.xpath(QuestPath))
							.getAttribute("for");

					String QuestTag = browser.getCurrentWebDriver()
							.findElement(By.id(questId)).getTagName();

					String Answer = survey.get("answer" + i).toString();
					logger.info("Answer is" + Answer);
					By loc = By.id(questId);
					String attributeType = "";
					if (QuestTag.equalsIgnoreCase("input")) {

						attributeType = browser.getCurrentWebDriver()
								.findElement(loc).getAttribute("type");
						if (attributeType.equalsIgnoreCase("text")) {
							performAction.clearEnter(loc, Answer, "Text Box");
						} else if (attributeType.equalsIgnoreCase("checkbox")) {
							performAction.click(loc, "Click button");
						} else if (attributeType.equalsIgnoreCase("textarea")) {

							performAction.clearEnter(loc, Answer, "Text Box");
						}

					} else if (QuestTag.equalsIgnoreCase("select")) {

						performAction.select(loc, Answer, "Selecting Answer");
						logger.info("Answer is selected");
					} else {
						logger.info("Unable to compare tag names");
					}
				}
				scr.capturePageScreenshot();
				this.clickOnNext();
				performAction
				.verify("//div[@class='surveyQuestionContainer']",
						survey.get("question1").toString(),
						"Survery questions in the prequalifying confirmation page.");
				this.clickOnNext();

			} else {
				scr.capturePageScreenshot();
				logger.info("Survey is not available in the page");
				throw new CustomException(
						"PreQualifying Survey page not available.");
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Survey is not available in the page");
			throw new CustomException("Exception in PreQualifying Survey page "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'preQualifyingSurveyMemberRole' keyword used to verify the pre-Qualifying Survey in MemberRole
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : user should be in Member profile Page 
	 * 
	 * PostCondition : user  will be redirected to the Member profile Page
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * </pre> 
	 * 
	 * Java file Name :  PreQualifyingSurvey.java
	 **/

	@RobotKeyword
	@ArgumentNames({"lastName ","Coverage" })
	public void preQualifyingSurveyInMemberRole(String lastName,String Coverage){
		try	{
			lastName=utils.getValue(lastName);			
			this.clickNext();
			this.clickGetStarted();		
			this.clickStart();
			this.selectSurveyQuestion(lastName);
			this.clickNext();
			this.clickNext();
			this.coverageLevel(Coverage);
			this.clickNext();
			this.clickNext();
			//Choosing Premiunm Deducted Premium Tax Option No
			this.premiumDeductPremiumTax();
			scr.capturePageScreenshot();
			this.clickNext();
			//Choosing Additional Insurance Option No
			this.additionalInsuranceOption();
			scr.capturePageScreenshot();
			this.clickNext();
			preQual=browser.getCurrentWebDriver().findElement(preQualifyingSurvey).getText();
			logger.info(preQual+" Verified in the Page");
			scr.capturePageScreenshot();			
		}catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in Pre Qualifying Survey In MemberRole page "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author : Varun Reddy Proddutoori
	 * 
	 * Description  : Complete PreQualifying Tobacco Survey for Member in New Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : PreQualifying Survey Page Opened in New Member Role
	 * 
	 * PostCondition : Survey Completed and Select Plan Page Opened in New Member Role
	 * 
	 * Java File Name : PreQualifyingSurvey.java
	 * 
	 * | |
	 * 
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({})
	public void preQualifyingTobaccoSurveyInMemberRole() {
		try {

			this.completeTobaccoSurvey();
			this.completeDeclaration();
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Survey is not available in the page");
			throw new CustomException("Exception in PreQualifying Survey page "
					+ e.getMessage());
		}
	}

}
