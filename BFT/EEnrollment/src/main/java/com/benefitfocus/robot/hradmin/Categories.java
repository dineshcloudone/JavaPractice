package com.benefitfocus.robot.hradmin;

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
public class Categories {
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;
	
	By editCategory=By.xpath("//h2[text()='Categories']//a/span[text()='Edit']");
	By effectiveDate=By.id("effectiveDate");
	By newCategorySelection=By.xpath("//select[contains(@id,'categoryTypeEntries')]");
	By cancelWithoutSavingButton=By.linkText("Cancel without Saving");
	By nextButton=By.xpath("//*[text()='Next']");
	By changeCategoriesMesg=By.xpath("//h1[text()='Change Categories']");
	By effectiveDateOfChangeMsg=By.xpath("//label[text()='Effective Date of Category Change']");
	By changeCategoriesEffectiveDate=By.xpath("//tr/td[2]/div[@class='labeledText']");
	By newCategoriesMsg=By.xpath("//div[@class='regionHeader']/h3[text()='New Categories']");
	By saveButton=By.xpath("//*[text()='Save']");
	By confirmationMsg=By.xpath("//h1[text()='Confirmation']");
	By successfullyCahngedMesg=By.xpath("//div[@class='regionHeader']/h2");
	By returntoEmployeeProfile=By.linkText("Return to Employee Profile");
	By categoryOption=By.id("categoryOption");
	//By cancellationDate=By.name("cancellationDate");
	
	By cancelFutureCategoryMesg=By.xpath("//label[text()='Cancel future category changes']");
	By future=By.xpath("//tr[@class='fieldListRow']//td//label[text()='Future Category Selection Effective Date']");
	By futureDate=By.xpath("//table/tbody/tr[1]/td[2]/div/div[@class='deemphasized']");
	String futurDate="";
	private void clickEditCategory()
	{
		performAction.click(editCategory, "Edit Category");
	}
	private void setEffectiveDate(String date)
	{
		performAction.enter(effectiveDate, date, "effectiveDate");
	}
	private void setNewCategory(String category)
	{
		performAction.select(newCategorySelection, category, "New Category");
	}
	private void clickSave()
	{
		performAction.click(saveButton, "Save Button");
	}
	private void selectCategroy(String option)
	{
		performAction.select(categoryOption, option, "Category Option");
	}
	private void verifyCancellationDate(String futuredate)
	{	
			performAction.verify(changeCategoriesMesg,"Change Categories","Change Categories");
			performAction.verify(cancelFutureCategoryMesg, "Cancel future category changes", "Cancel Future CategoryMesg");	
			performAction.verify(future, "Future Category Selection Effective Date", "Label");
			performAction.verify(futureDate, futuredate, "Date value");						
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : This keyword used to change the categories in Hr admin role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : user should be in Employee Overview page in Hr Role 
	 * 
	 * PostCondition : user  will be redirected to Changed Categories Page
	 *   
	 * <pre>
	 * <b>Parameters & Example </b> 
	 * | strCategoryName - Category Name |strDecsription - Category Description |
	 * 
	 * Java file Name : Categories.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"changecategoryType" })
	public void changeCategory(String changeCategoryType)
	{
		
		if (changeCategoryType.startsWith("td:"))
			changeCategoryType = changeCategoryType.substring(3);
		Object object = null;
		JSONObject fields = ReadJsonTestData.getTestData(changeCategoryType
				.toLowerCase());
		this.clickEditCategory();		
		try{
			if(browser.getCurrentWebDriver().getPageSource().contains("What would you like to do?"))
			{	
				object = fields.get("whatwouldyouliketodo");
				if (object != null) {
					this.selectCategroy(object.toString());	
					performAction.click(nextButton, "Next Button");
				}
			}
			object = fields.get("effectivedate");
			if (object != null) {
				this.setEffectiveDate(object.toString());
				browser.hMap.put("effectivedateofchange", object.toString());
				futurDate=object.toString();
			}
			object = fields.get("category");
			if (object != null) {
				this.setNewCategory(object.toString());
			}	

			if(performAction.isElementPresent(cancelFutureCategoryMesg))
			{			
				verifyCancellationDate(futurDate);
			}
			performAction.click(nextButton, "Next Button");
			this.clickSave();
		} catch (Exception e) {
			logger.info("Exception in performing the Add employee "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the add employee "
							+ e.getMessage());				
		}
	}
 }

