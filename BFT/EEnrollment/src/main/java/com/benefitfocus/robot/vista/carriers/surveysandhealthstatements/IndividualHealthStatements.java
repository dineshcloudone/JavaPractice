package com.benefitfocus.robot.vista.carriers.surveysandhealthstatements;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class IndividualHealthStatements {
	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Utilities utils;

	@Autowired
	protected ManageBrowser browser;
	
	@Autowired
	protected Screenshot scr;
	
	By eeCreateProfileButton = By.linkText("Create New Profile");
	By eeName = By.xpath("//input[@id = 'healthStatementName-ENGLISH']");
	By eePresentation = By.id("presentationType");
	By eeSave = By.xpath("//strong[text()='Save']");
	By eeAdd = By.xpath("//strong[text()='Add New Page']");
	By eeAddQuestion = By.xpath("//strong[text()='Add Question']");
	By eeQuestionType = By.xpath("//input[@value = 'SIMPLE_YES_NO']");
	By eeNext = By.linkText("Next");
	By eeQuestionName = By.xpath("//input[@id = 'questionHeading-ENGLISH']");
	
	// click Create New Profile
	private void clickCreateNewProfile() throws Exception{
		performAction.click(eeCreateProfileButton, "create new profile button");
	}
	//click Add or Edit Page button of specified Health Statement 
	private void clickAddOrEditPageButton(String strHealthStatementName)throws Exception{
		By eeAddpage = By.xpath("//td[contains(text(),'"+strHealthStatementName+"')]/following-sibling::td[3]/a");
		performAction.click(eeAddpage, "Add or Edit Page");
	}
	//click Add Page Button
	private void clickAddPageButton()throws Exception{
		performAction.click(eeAdd, "Add Page");
	}
	//Select Presentation Type
	private void selectPresentationType(String presentationType)throws Exception{
		performAction.select(eePresentation, presentationType, "Presentation Type");
	}
	//set the health statement name text box
	private void enterHealthStatementName (String strHealthStatementName)throws Exception{
		performAction.clearEnter(eeName, strHealthStatementName, "Health Statement Name");
	}
	//click show button of specified page
	private void clickShowButton(String pageName) throws Exception{
		By eeShow = By.xpath("(//a[contains(@id,'innerLinktoggleRegionpageRegion')])["+pageName+"]");
		performAction.click(eeShow, "Show Button");
	}
	//click Add Question button in a page
	private void clickAddQuestionButton() throws Exception{
		performAction.click(eeAddQuestion, "Add Question");
	}
	//Selects the question type using radio button
	private void clickQuestionType() throws Exception{
		performAction.click(eeQuestionType, "Question Type");
	}
	//click on next button
	private void clickNext() throws Exception{
		performAction.click(eeNext, "Next");
	}
	//Set the question name text box
	private void enterQuestionName(String strQuestionName) throws Exception{
		performAction.clearEnter(eeQuestionName, strQuestionName, "Question Name");
	}
	//click on save button
	private void clickSaveButton() throws Exception{
		performAction.click(eeSave, "Save");
	}
	//click Delete button of specified Health Statement 
	private void clickDeleteButton(String strHealthStatementName)throws Exception{
		By eeDelete = By.xpath("//td[contains(text(),'"+strHealthStatementName+"')]/following-sibling::td[4]/a");
		performAction.click(eeDelete, "Delete");
		}
	
	/**
     * <pre>
     * Author  : Arun Kasarla
     * 
     * Description : 'addNewHealthStatementProfile' keyword is used to create health statement
	 * in vista role.
     *
     * Role : Vista Role
     * 
     * PreCondition : Carrier page should be open.
     *
     * PostCondition : New profile name should be listed in 	
     * individual health statements page. 
     * 
     * <b>Parameters & Example </b>
     *
     * | sampleHealthSatement -  string | presentationType - string |
     * </pre>
     * Java file Name :  IndividualHealthStatements.java
     **/
	@RobotKeyword
	@ArgumentNames({ "strHealthStatementName", "presentationType" })
	public void addNewHealthStatementProfile(String strHealthStatementName, String presentationType) {
		try {
			this.clickCreateNewProfile();
			if (strHealthStatementName.startsWith("RND")){
				strHealthStatementName = utils.generateRandomNumber(strHealthStatementName);
			}
			//performAction.clearEnter(eeName, strHealthStatementName, "Health Statement Name");
			this.enterHealthStatementName(strHealthStatementName);
			browser.hMap.put("profileName", strHealthStatementName);
			this.selectPresentationType(presentationType);
			scr.capturePageScreenshot();
			this.clickSaveButton();

		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in adding new health statement profile "
					+ e.getMessage());
		}
	}

	/**
     * <pre>
     * Author  : Arun Kasarla
     * 
     * Description : 'addPageToHealthStatement' keyword is used to add new page to health statement
	 * in vista role.
	 * 
	 * Role : Vista Role
     * 
     * PreCondition : Individual health statements page should be open. 
     *
     * PostCondition : Page should be created and should be displayed in the summary page
     * 
     * <b>Parameters & Example </b>
     *
     * | healthSatementName -  string |
     * </pre>
     * Java file Name :  IndividualHealthStatements.java
     **/
	@RobotKeyword
	@ArgumentNames({ "strHealthStatementName"})
	public void addPageToHealthStatement(String strHealthStatementName) {
		try {
			if (strHealthStatementName.startsWith("HMV")){
				strHealthStatementName = utils.getValue(strHealthStatementName);
			}
			this.clickAddOrEditPageButton(strHealthStatementName);
			this.clickAddPageButton();
			scr.capturePageScreenshot();
			this.clickSaveButton();

		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in adding new health statement profile "
					+ e.getMessage());
		}
	}
	/**
     * <pre>
     * Author  : Arun Kasarla
     * 
     * Description : 'addQuestionToHealthStatement' keyword is used to add new question to health statement
	 * in vista role.
	 * 
	 * Role : Vista Role
     * 
     * PreCondition : Summary page of the health statement with required page name should be displayed.
     *
     * PostCondition : Question should be added in the specified page.
     * 
     * <b>Parameters & Example </b>
     *
     * | strPageName -  string | strQuestionName |
     * </pre>
     * Java file Name :  IndividualHealthStatements.java
     **/
	@RobotKeyword
	@ArgumentNames({ "strPageName","strQuestionName"})
	public void addQuestionToHealthStatement(String strPageName, String strQuestionName) {
		try {
			String pageName = strPageName.substring(strPageName.length()-1);
			
			this.clickShowButton(pageName);
			this.clickAddQuestionButton();
			this.clickQuestionType();
			this.clickNext();
			if (strQuestionName.startsWith("RND")){
				strQuestionName = utils.generateRandomNumber(strQuestionName);
			}
			this.enterQuestionName(strQuestionName);
			this.clickSaveButton();
			scr.capturePageScreenshot();
			this.verifyQuestion(pageName, strQuestionName);

		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in adding Question to health statement"
					+ e.getMessage());
		}
	}
	
	
	/**
     * <pre>
     * Author  : Arun Kasarla
     * 
     * Description : 'deleteHealthStatement' keyword is used to delete health statement
	 * in vista role.
	 * 
	 * Role : Vista Role
     * 
     * PreCondition : Individual health statements page should be open. 
     *
     * PostCondition : deleted health statement should not be displayed.
     * 
     * <b>Parameters & Example </b>
     *
     * | healthSatementName -  string |
     * </pre>
     * Java file Name :  IndividualHealthStatements.java
     **/
	@RobotKeyword
	@ArgumentNames({ "strHealthStatementName"})
	public void deleteHealthStatement(String strHealthStatementName) {
		try {
			if (strHealthStatementName.startsWith("HMV")){
				strHealthStatementName = utils.getValue(strHealthStatementName);
			}
			this.clickDeleteButton(strHealthStatementName);
			this.verifyDelete(strHealthStatementName);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in deleting health statement profile "
					+ e.getMessage());
		}
	}
	/**
	 * Method to verify the Question Added in a page
	 */
	private void verifyQuestion(String strPageName, String strQuestionName) {

		try {
			this.clickShowButton(strPageName);
			if (browser.getCurrentWebDriver().getPageSource().contains("<div>"+strQuestionName+"</div>")){
				System.out.println("Question verified successfully");
				scr.capturePageScreenshot();
			}
			else{
				throw new RuntimeException("Question not added in the page");
			}
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in verifying Question of health statement "
					+ e.getMessage());
		}
	}
	
	/**
	 * Method to verify the Health Statement is deleted
	 */
	private void verifyDelete(String strHealthStatementName) {

		try {
			if(browser.getCurrentWebDriver().getPageSource().contains(strHealthStatementName)){
				throw new RuntimeException("Health statement not deleted successfully");
			}
			else{
				System.out.println("Health statement deleted sucessfully");
				scr.capturePageScreenshot();
			}
			
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in deleting health statement"
					+ e.getMessage());
		}
	}
}
