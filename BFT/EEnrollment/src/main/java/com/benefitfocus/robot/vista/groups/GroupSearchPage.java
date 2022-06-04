package com.benefitfocus.robot.vista.groups;

import junit.framework.Assert;

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
public class GroupSearchPage {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Utilities utils;

	@Autowired
	protected ManageBrowser browser;
	
	@Autowired
	protected Screenshot scr;
	
	@Autowired
	protected Logging logger;

	By eeSearchBox = By.id("simpleSearchCriteria");
	By eeSearchButton = By.cssSelector("a[href*='simpleSearch']");
	By groupList = By.xpath("//div[@class='dtrException']");
	String groupsList = "//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate']/tbody";
	By groupNameTitle = By.className("Text_GroupName");

	private void setSearchField(String strSearchString) {
		performAction.enter(eeSearchBox, strSearchString, "Search textbox");
		// performAction.enter(eesearchbox, strSearchString, "Search textbox");
	}

	private void clickSearch() {
		performAction.click(eeSearchButton, "search button");
	}

	/**
	 * <pre> 
	 * Author  : Phani Srikar ch
	 *  
	 * Description : 'performGroupSearch' keyword used to perform the group search operation
	 * in vista role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : user should be in group search page in Hr Role 
	 * 
	 * PostCondition : group search results should be displayed
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | strSearchString - search string |
	 * 
	 * Java file Name : GroupSearchPage.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strGroupName" })
	public void performGroupSearch(String strGroupName) {
		try {
			strGroupName = utils.getValue(strGroupName);

			// Fill group name
			this.setSearchField(strGroupName);

			// Click search button
			this.clickSearch();

			this.verifyGroupSearchResults(strGroupName);
			scr.capturePageScreenshot();

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing group search "
					+ e.getMessage());
		}
	}

	/**
	 * Method to verify the search results
	 */
	private void verifyGroupSearchResults(String strGroupName) {

		try {

			if (performAction.isElementPresent(By.xpath(groupsList))) {

				String tablerows = groupsList + "/tr";
				int rowsize = browser.getCurrentWebDriver()
						.findElements(By.xpath(tablerows)).size();

				for (int i = 4; i <= rowsize; i++) {
					String row = tablerows + "[" + i + "]/td[1]";
					String exp = browser.getCurrentWebDriver()
							.findElement(By.xpath(row)).getText();
					if (exp.toLowerCase().contains(strGroupName.toLowerCase())) {
						System.out.println(strGroupName + "Found in the row "
								+ i);
						browser.getCurrentWebDriver()
								.findElement(By.linkText(exp)).click();
						break;
					}
				}
			} else {
				Assert.assertTrue(browser.getCurrentWebDriver().getPageSource()
						.toLowerCase().contains(strGroupName.toLowerCase()));

				performAction
						.verify(groupNameTitle, strGroupName, "Group Name");
			}

		} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in verifying group search "
					+ e.getMessage());
		}
	}

}
