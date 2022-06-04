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

@RobotKeywords
public class Basics {
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;	
	@Autowired
	protected Screenshot scr;
	
	By categoryTypeoption=By.xpath("//img[@id='contextualMenuAnchorCatEditMenu']");
	By createNewCategoryType=By.linkText("Create New Category Type");
	By categoryName=By.name("categoryTypeName");
	By active=By.name("active");
	By selectionrequired=By.name("required");
	By nextButton=By.xpath("//strong[text()='Next']");
	By cancelButton=By.xpath("//a[@class='btn btn-default']");
	By createNewAdministratorAccount=By.linkText("Create New Administrator Account");
	By saveButton=By.xpath("//strong[text()='Save']");
	By importCategoryValues=By.xpath("//a[text()='Import Category Values']");
	By uploadedFile=By.id("uploadedFile");
	By importCategories=By.xpath("//a[text()='Import Categories']");
	private void enterCategoryName(String strCategoryName)
	{
		performAction.enter(categoryName, strCategoryName, "Category Name");
	}
	private void selectActive(String strActive)
	{
		performAction.select(active, strActive, "Active");
	}
	private void selectSelectionrequired(String strSelectionReq)
	{
		performAction.select(selectionrequired,strSelectionReq,"Selection Required");
	}
	private void clickNext()
	{
		performAction.click(nextButton, "Next Button");
	}
	private void clickCancel()
	{
		performAction.click(cancelButton,"Cancel Button");
	}
	private void clickSave()
	{
	 performAction.click(saveButton,"Save Button");
	}
	private void clickCreateNewAdminAccount()
	{
		performAction.click(createNewAdministratorAccount, "Create New Administrator Account");
	}
	private void clickImportCategoryValue()
	{
		performAction.click(importCategoryValues, "Import Category Values");
	}
	private void clickfileUpload()
	{
		performAction.click(uploadedFile, "Upload File Button");
	}
	private void clickImportCategory()
	{
		performAction.click(importCategories,"Import Categories Button");
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : editBasicsInformationInHRAdmin keyword to edit the Information of Categories,Group Customization, Personal Customization and Work Customization
	 * 
	 * PreCondition : User Should be in Basics page In HR Admin Role
	 * 
	 * PostCondition : edit the respective Icon
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strIconName - Categories or Group Customization or Personal Customization or Work Customization  | 
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strIconName" })
	public void editBasicsInformationInHRAdmin(String strIconName)
	{		
		String loc="//h2[text()='"+strIconName+"']/../..//table//tr/td[2]//a/img";		
		By editicon=By.xpath(loc);
		performAction.isElementPresent(loc);		
		performAction.click(editicon," Edit Icon");	
		scr.capturePageScreenshot();
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : editBasicsInformationInHRAdmin keyword to edit the Information of Categories,Group Customization, Personal Customization and Work Customization
	 * 
	 * PreCondition : User Should be in Basics page In HR Admin Role
	 * 
	 * PostCondition : edit the respective Icon
	 *
	 * Java FileName : Basics.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strCateggory - give the Categories mandatory information in json file  | 
	 * 
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strIconName" })
	public void createCategoryInHRAdmin(String strCateggory)
	{			
			
		if (strCateggory.startsWith("td:"))
			strCateggory = strCateggory.substring(3);

		try {
			performAction.click(categoryTypeoption, "Category Type Option");
			performAction.click(createNewCategoryType, "Create New Category Type");
			Object object;
			JSONObject fields = ReadJsonTestData.getTestData(strCateggory
					.toLowerCase());

			object = fields.get("categorytype");
            if (object != null) {
            	this.enterCategoryName(object.toString());              
            }
			object = fields.get("active");
			if (object != null) {
				this.selectActive(object.toString());				
			}
			object = fields.get("selectionrequired");
			if (object != null) {
				this.selectSelectionrequired(object.toString());				
			}		
			this.clickNext();			
		} catch (Exception e) {
			logger.info("Exception in performing the Add category in HR Admin"
					+ e.getMessage());
			
			throw new CustomException(
					"Exception in performing the Add category in HR Admin "
							+ e.getMessage());
		
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : verifyErrorAddAdminstratorAccount keyword to create new Administrator account in HR Admin role
	 * 
	 * PreCondition : User Should be in Manage Administrator Accounts page 
	 * 
	 * PostCondition : Error messages should be displayed
	 *
	 * Java FileName : Basics.java
	 *
	 * <b>Parameters & Example </b> 	 
	 * 
	 * </pre> 
	 **/
	@RobotKeyword	
	public void verifyErrorAddAdminstratorAccount()
	{		
		try {
			this.clickCreateNewAdminAccount();
			this.clickSave();			

		} catch (Exception e) {
			logger.info("Exception in performing the Add Adminstrator Acount in HR Admin"
					+ e.getMessage());
			
			throw new CustomException(
					"Exception in performing the Add Adminstrator Acount in HR Admin "
							+ e.getMessage());		
		}
	}
		
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : importCategoryValues keyword to import Category Values under Edit Categories in Basics Information page in HR Admin role
	 * 
	 * PreCondition : User Should be in Categories page HR Admin  
	 * 
	 * PostCondition : Error messages should be displayed
	 *
	 * Java FileName : Basics.java
	 *  
	 * <b>Parameters & Example </b> 	
	 * | FileUpload_BrowserType:FileName.extension| 
	 * 
	 * | Example - FileUpload_GChrome:hrintouchjpg.jpg or FileUpload_FF:hrintouch.jpg|
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strvalue" })
	public void importCategoryValues(String value)
	{		
		try {
			this.clickImportCategoryValue();
			String [] tempValues = value.split(":");
			String[] commands = new String[]{};						
			commands = new String[]{"Q:\\KB\\HrInTouch\\Data\\"+tempValues[0]+".exe", tempValues[1]}; //location of the autoit executable					
			Thread.sleep(5000);
			this.clickfileUpload();			
			System.out.println("Command :: "+commands);
			Runtime.getRuntime().exec(commands);
			Thread.sleep(10000);
			this.clickImportCategory();
							
		} catch (Exception e) {
			logger.info("Exception in impotr Category values in HR Admin"
					+ e.getMessage());
			
			throw new CustomException(
					"Exception in impotr Category values in HR Admin "
							+ e.getMessage());		
		}
	}
	
}
