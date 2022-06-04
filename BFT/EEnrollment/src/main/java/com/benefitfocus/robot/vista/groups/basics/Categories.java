package com.benefitfocus.robot.vista.groups.basics;

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

@RobotKeywords
public class Categories {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Utilities utils;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Screenshot scr;

	@Autowired	
	protected BasicCommon common;
	@Autowired
	protected Logging logger;

	//Locators on the Page
	By category=By.id("contextualMenuAnchorCatEditMenu");
	By newCategory=By.linkText("Create New Category Type");
	By next=By.xpath("//*[text()='Next']");	
	By categoryName=By.id("newCategoryName");
	By categoryDescription=By.id("newCategoryDescription");
	By add=By.xpath("//*[text()='Add']");
	By cancel=By.xpath("//*[text()='Cancel']");
	By save=By.xpath("//*[text()='Save']");
	By addNewCategory=By.xpath("//*[text()='Add New Category']");
	By importCategoryValues=By.xpath("//*[text()='Import Category Values']");
	By addMultipleCategoryValues=By.xpath("//*[text()='Add Multiple Category Values']");
	By deleteCategoryType=By.linkText("Delete Category Type");
	By editCategoryType=By.linkText("Edit Category Type");
	By yesButtonOnDeleteCategoryConfirmation=By.linkText("Yes");
	By categoryTypeForm = By.xpath("//label[@for='categoryTypeForm']");

	String loc="//tr[@class='fieldListRow']//td[1]/..//..//span[text()='*']//..//..//label";			
	String loc1="//tr[@class='fieldListRow']//td[1]//label";
	String tagName="";
	String attributeType="";
	String currentlabelname="";
	String labelLoc = "";
	String currentLocator="";
	String objectLoc="";
	String strValue="";

	By nextButton = By.xpath("//*[contains(text(),'Next')]");

	private void setCategoryName(String strCategoryName)
	{		
		performAction.enter(categoryName, strCategoryName, "New Category Name");
	}
	private void setDescriptionName(String strDescriptionName)
	{		
		performAction.clearEnter(categoryDescription, strDescriptionName, "Category Description");
	}
	private void clickAdd()
	{
		performAction.click(add, "Add Button");
	}
	private void clickSave()
	{
        if (performAction.isElementPresent(save))
		    performAction.jsclick(save,"Save Button");
	}
	private void clickNext()
	{
		if(performAction.isElementPresent(next)){
		performAction.click(next,"Next Button");
		}else if(performAction.isElementPresent(nextButton)){
			performAction.click(nextButton, "Next Button");
		}
	}
	@RobotKeyword
	public void SaveConfiguration()
	{
		this.clickSave();
	}	
	//select or enter a value based on the Label we have selected in the page
	private void actionPerform(String tagName,String locator,String type,String value)
	{
		if(tagName.equalsIgnoreCase("select"))
		{
			By loctor=By.xpath(locator);
			System.out.println(loctor);
			performAction.select(loctor, value, "Select drop down element");
		}
		if((tagName.equalsIgnoreCase("input"))&&(type.equalsIgnoreCase("text")))
		{
			By loctor=By.xpath(locator);
			performAction.clearEnter(loctor, value,"Text Box");
		}
		if((tagName.equalsIgnoreCase("input"))&&(type.equalsIgnoreCase("checkbox")))
		{

		}
		if(tagName.equalsIgnoreCase("textarea"))
		{
			By loctor=By.xpath(locator);
			System.out.println(loctor);
			performAction.clearEnter(loctor, value,"Text Box");

		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'addNewCategory' keyword used to add new category  in vista role
	 * 
	 * Java file Name :  Categories.java
	 * 
	 * PreCondition : user should be in Add/Edit Categories Page 
	 * 
	 * PostCondition : user  will be redirected to the Add/Edit Categories Page
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * |strCategoryName - Category Name |strDecsription - Category Description |
	 * 
	 * Role : Vista Role
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"categoryName ","DescriptionName" })
	public void addNewCategory(String strCategoryName,String strDecsription){
		try	{
			this.setCategoryName(strCategoryName);
			this.setDescriptionName(strDecsription);
			this.clickAdd();
			scr.capturePageScreenshot();
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing update identification information "
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Sekhar Tirumala
	 *  
	 * Description : 'editCategoryTypeInVistaRole' keyword used to edit Category in vista role.
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in Categories Page 
	 * 
	 * PostCondition : user  will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * 
	 * | Old Category Type Name  |Label Name | strLabelValue |
	 * 
	 * | AutoTest05022 | HR Administrator/Broker Category Type Restrictions  | view Only |
	 * 
	 * Java FileName : Categories.java
	 * 
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"oldcategoryName","labelName ","strLabelValue" })
	public void editCategoryTypeInVistaRole(String strcategoryName,String labelName,String strLabelValue){
		try	{
			String strvlaue=utils.getValue(strcategoryName);		
			By contextualMenu=By.xpath("//td[contains(text(),'"+strvlaue+"')]/..//td[2]/a/img");
			performAction.mouseOver(contextualMenu, "ContextualMenu");
			scr.capturePageScreenshot();
			performAction.jsclick(editCategoryType, "Edit Category Type");
			scr.capturePageScreenshot();
			logger.info("Object created");
			common.updateInfo(labelName,strLabelValue);
		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in Category page "
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'Next' keyword used to click on Next button  in vista role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition :If Next Button is visible   
	 * 
	 * PostCondition : user  will be redirected Next Page
	 * 
	 * <b>Parameters & Example </b> 
	 * | None |
	 * 
	 * Java file Name :  Categories.java
	 * </pre> 
	 **/
	@RobotKeyword	
	public void Next(){
		try	{
			this.clickNext();
		}catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing update identification information "
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'addCategoryType' keyword used to add new category  in vista role
	 *  
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in Categories Page 
	 * 
	 * PostCondition : user will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters & Example </b> 
	 * |labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * srtCategoryName - Category Name |
	 * 
	 * Java file Name :  Categories.java
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({"labelName","strValue", "srtCategoryName" })
	public void addCategoryType(String labelName,String strValue,String srtCategoryName){
		try	{
			if(strValue.startsWith("RND"))
			{
				strValue=utils.getValue(strValue);
			}
			browser.hMap.put(srtCategoryName,strValue);
			if(performAction.isElementPresent(categoryTypeForm))
			{
				logger.info("Add Category Type Page is reloaded");	
			}
			else
			{
				performAction.mouseOver(category, "Categories Type");
				performAction.click(newCategory, "Create New Category Type");
			}
			int rowsize=browser.getCurrentWebDriver().findElements(By.xpath("//tr[@class='fieldListRow']//td[1]/..//..//span[text()='*']//..//..//label")).size();
			logger.info("Row size :"+rowsize);								
			labelLoc = loc+"[text()='"+labelName+"']";
			currentlabelname=browser.getCurrentWebDriver().findElement(By.xpath(labelLoc)).getText();
			objectLoc = labelLoc+"/../..//..//td[2]/*";			
			tagName=browser.getCurrentWebDriver().findElement(By.xpath(objectLoc)).getTagName();
			if(currentlabelname.equalsIgnoreCase(labelName))
			{
				if(tagName.equalsIgnoreCase("input")){	
					currentLocator=	labelLoc+"/../..//..//td[2]/input";		
					attributeType=browser.getCurrentWebDriver().findElement(By.xpath(objectLoc)).getAttribute("type");
					logger.info("Attribute Name :"+attributeType);
					actionPerform(tagName,currentLocator,attributeType,strValue);
				}
				if(tagName.equalsIgnoreCase("select")){
					currentLocator=	labelLoc+"/../..//..//td[2]/select";
					actionPerform(tagName,currentLocator,attributeType,strValue);
				}
			}

			labelLoc = loc1+"[text()='"+labelName+"']";
			currentLocator="";
			//System.out.println("Label : "+browser.getCurrentWebDriver().findElement(By.xpath(labelLoc)).getText());
			currentlabelname=browser.getCurrentWebDriver().findElement(By.xpath(labelLoc)).getText();
			objectLoc = labelLoc+"/../..//..//td[2]/*";			
			//System.out.println("object Tag : "+browser.getCurrentWebDriver().findElement(By.xpath(objectLoc)).getTagName());
			tagName=browser.getCurrentWebDriver().findElement(By.xpath(objectLoc)).getTagName();
			if(currentlabelname.equalsIgnoreCase(labelName))
			{
				if(tagName.equalsIgnoreCase("input")){	
					currentLocator=	labelLoc+"/../..//..//td[2]/input";		
					attributeType=browser.getCurrentWebDriver().findElement(By.xpath(objectLoc)).getAttribute("type");
					logger.info("Attribute Name :"+attributeType);
					actionPerform(tagName,currentLocator,attributeType,strValue);
				}
				if(tagName.equalsIgnoreCase("select")){
					currentLocator=	labelLoc+"/../..//..//td[2]/select";
					actionPerform(tagName,currentLocator,attributeType,strValue);
				}
			}

		}catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing Add New Category Type "
					+ e.getMessage());
		}
	}	

	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'deleteCategory' keyword used to delete category in vista role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in Categories Page 
	 * 
	 * PostCondition : user  will be redirected to the Basics page.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * </pre>
	 * 
	 * Java file Name : Categories.java
	 **/

	@RobotKeyword
	@ArgumentNames({"strcategoryName"})
	public void deleteCategory(String strcategoryName){

		strValue=utils.getValue(strcategoryName);		
		strValue="//td[contains(text(),'"+strValue+"')]/..//td[2]/a/img";
		By contextualMenu=By.xpath(strValue);
		performAction.mouseOver(contextualMenu, "ContextualMenu");
		scr.capturePageScreenshot();
		performAction.click(deleteCategoryType, "Delete Category Type");
		scr.capturePageScreenshot();
		performAction.click(yesButtonOnDeleteCategoryConfirmation,"Yes");	
		scr.capturePageScreenshot();
	}
	/**
	 * <pre>
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : keyword used to update the configuration field value in vista role
	 * 
	 * PreCondition : Should on the page where configurations are available in FieldLabel and FieldValue patter
	 * 
	 * PostCondition : Enter / Selects any field value
	 *  
	 * <b>Parameters & Example </b> 
	 * | labelName | strValue |
	 * | Phone Number | 253-123-4569 / xxx-xxx-xxxx |
	 * | Status | Live / Not Live |
	 * | Sponsoring Carrier ID | Test |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "labelName ", "selectValue" })
	public void updateConfigurationFor(String labelName, String value) {
		try {

			String Sections = "//div[@class='secondaryRegion']";

			int sectionsCount = browser.getCurrentWebDriver()
					.findElements(By.xpath(Sections)).size();
			System.out.println("sectionsCount : "+sectionsCount);

			for (int i = 1; i < sectionsCount; i++) {

				String rows = "//table[@class='fieldList']/tbody/tr";

				int fieldListRowsSize = browser.getCurrentWebDriver()
						.findElements(By.xpath(Sections + rows)).size();
				System.out.println("fieldListRowsSize : "+fieldListRowsSize);
				for (int j = 1; j < fieldListRowsSize; j++) {

					String subFields = Sections + "[" + i + "]" + rows + "["
							+ j + "]" + "//tr";

					System.out.println("subFields : "+subFields);

					if (browser.getCurrentWebDriver()
							.findElements(By.xpath(subFields)).size() > 0) {

					} else {

						String label = Sections + "[" + i + "]" + rows + "["
								+ j + "]//label";

						System.out.println("label loc : "+label);
						System.out.println("label text : "+browser.getCurrentWebDriver()
								.findElement(By.xpath(label)).getText());

						if (browser.getCurrentWebDriver()
								.findElement(By.xpath(label)).getText().trim()
								.equalsIgnoreCase(labelName.trim())) {

							String fieldId = browser.getCurrentWebDriver()
									.findElement(By.xpath(label))
									.getAttribute("for");

							System.out.println("For value : "+browser.getCurrentWebDriver()
									.findElement(By.id(label)).getAttribute("for"));

							String tagtype = browser.getCurrentWebDriver()
									.findElement(By.id(fieldId)).getTagName();
							System.out.println("tagtype : "+tagtype);

							System.out.println("atttype : "+browser
									.getCurrentWebDriver()
									.findElement(By.id(fieldId))
									.getAttribute("type"));

							if (tagtype.equalsIgnoreCase("input")) {
								String attributeType = browser
										.getCurrentWebDriver()
										.findElement(By.id(fieldId))
										.getAttribute("type");
								updateField(tagtype, fieldId, attributeType,
										value);
							} else if (tagtype.equalsIgnoreCase("input")) {
								updateField(tagtype, fieldId, "", value);
							}
						}
					}
				}
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException(
					"Exception in performing update information "
							+ e.getMessage());

		}
	}


	private void updateField(String tagName, String strLocator, String type, String value) {
		By locator = By.id(strLocator);
		if (tagName.equalsIgnoreCase("select")) {             
			System.out.println(locator);
			performAction.select(locator, value, "Select drop down element");
		}
		if ((tagName.equalsIgnoreCase("input"))
				&& (type.equalsIgnoreCase("text"))) {
			By loctor = By.id(strLocator);
			performAction.clearEnter(loctor, value, "Text Box");
		}
		if ((tagName.equalsIgnoreCase("input"))
				&& (type.equalsIgnoreCase("checkbox"))) {
			performAction.click(locator, "Text Box");
		}
		if (tagName.equalsIgnoreCase("textarea")) {
			By loctor = By.id(strLocator);
			System.out.println(loctor);
			performAction.clearEnter(loctor, value, "Text Box");
		}
		scr.capturePageScreenshot();
	}
}