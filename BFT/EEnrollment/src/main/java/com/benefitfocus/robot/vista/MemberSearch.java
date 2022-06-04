package com.benefitfocus.robot.vista;

import com.benefitfocus.robot.utils.Screenshot;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class MemberSearch {

    @Autowired
    protected ActionKeywords performAction;
    @Autowired
    protected Utilities utils;
    @Autowired
    protected ManageBrowser browser;
    @Autowired
    protected Logging logger;
    @Autowired
    protected Screenshot scr;

    // Locators on the page
    By memberSearchTextBox = By
            .xpath("//input[@class='small' and @name='searchCriteria']");
    By memberSearchButton = By.cssSelector("a[href*='quickMemberSearchForm']");
    By memberResultsTable = By.id("memberResults");
    By searchByDropdown = By.id("contextualMenuAnchormemberSearchBy");
    By logInId = By.linkText("Login ID");
    By name = By.linkText("Name");
    By ssn = By.linkText("SSN");
    By editMenu = By.xpath("//div/table/tbody/tr[2]/td[2]/a/img");
    //By editMenu = By.cssSelector("css=img[id*='contextualMenuAnchorNavMemberMenu']");
    By deletePerson = By.linkText("Delete Person");
    By deleteLink = By.linkText("Delete");
    By yesOption= By.xpath("//select[@id='continueWithDelete']");
    By saveButton = By.xpath("//strong[text()='Save']");
    By searchMember=By.xpath("//a[contains(@href, 'javascript:if(aua()){document.quickMemberSearchForm.submit();nomoreua();}else{explainaua();}')]");
    By searchMemberByLastName=By.xpath("//td[2]/a/b");
    By searchTextBox=By.xpath("//input[@id='memberSearchInputField']");
    String tblField = "//table[@id='memberResults']/tbody";
	By newPasswordTxt = By.id("password");
	By confirmPasswordTxt = By.id("passwordConfirmation");
	By saveBtn = By.xpath("//strong[text()='Save']");


    // Set search text box
    private void setMemberSearch(String strSearchMember) {
        performAction.enter(memberSearchTextBox, strSearchMember,
                "Search member textbox");
    }

    // Click on login button
    private void clickSearchButton() {
        performAction.click(memberSearchButton, "search button");
    }

    private void mouseoverEditMenu(){

        performAction.mouseOver(editMenu, "Edit Menu");
    }

    private void clickDeletePerson(){

        performAction.click(deletePerson, "DeletePerson");
    }
    private void clickDeleteLink(){

        performAction.click(deleteLink, "DeleteLink");
    }

    private void selectYesOption(){

        performAction.select(yesOption,"Yes", "yesOption");
    }

    private void clickSaveButton(){

        performAction.click(saveButton, "SaveButton");
    }

    private void clickSearchMember(){

        performAction.click(searchMember, "Search Members in Vista");
    }

    private void enterSearchTextBox(String strLastName){

        performAction.enter(searchTextBox, strLastName, "Search Text Box");
    }

    private void clickSearchMemberByLastName(){

        performAction.click(searchMemberByLastName, "Search Button By Last Name");
    }

	// set the newPassword text box
	private void setNewPassword(String strnewPassword) {
		performAction.enter(newPasswordTxt, strnewPassword,
				"New Password Text box");

	}

	// set the newConfirmPassword text box
	private void setConfirmPassword(String strnewPassword) {
		performAction.enter(confirmPasswordTxt, strnewPassword,
				"New Confirm Password Text box");
	}

	// click the PasswordSave Button
	private void clickPasswordSavebutton(){
		performAction.click(saveBtn, "Save Password Button");
	}

	
    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : verifyMemberSearchResults keyword to verify a member in the search results table
     *
     * PreCondition : Add Member page in vista Role
     *
     * PostCondition : New member is saved successfully.
     *
     * <b>Parameters & Example </b>
     * | strColumnName | column name in which the expected value to be searched |
     * | strSearchMember - String parameter to search | blnExist |
     * | blnExist |  boolean parameter true / false to verify the member exist or not |
     * </pre>
     **/
    
    @RobotKeyword
    @ArgumentNames({"strColumnName", "strSearchMember", "blnExist"})
    public void verifyMemberSearchResults(String strColumnName, String strSearchMember,
                                         String blnExist) {
    	int colNumber=0; 
        strSearchMember = utils.getValue(strSearchMember);
        int col;
        int rowNumber = 4;
        
        try {
            if (browser.getCurrentWebDriver().findElement(By.xpath(tblField))
                    .isDisplayed()) {
                boolean result = false;

                int TableRowSize = browser.getCurrentWebDriver()
                        .findElements(By.xpath(tblField + "/tr")).size();
                logger.info("TableRowSize : " + TableRowSize);

                int TableColSize = browser.getCurrentWebDriver()
                        .findElements(By.xpath(tblField + "//th")).size();
                logger.info("Table Column Size : " + TableColSize);
                logger.info("Column Name::::"+strColumnName);
                for (col = 1; col <= TableColSize; col++) {
                    if(browser.getCurrentWebDriver().findElement(By.xpath(tblField + "//th[" + col + "]")).getText().equalsIgnoreCase(strColumnName))
                    {
                     colNumber = col;                                                                       
                    /* String colName=browser.getCurrentWebDriver().findElement(By.xpath(tblField + "//th[" + col + "]")).getText();
                     System.out.println("Column Name:"+colName+" And Column Number:"+col);*/
                     break;
                    }
                }                

                if (TableRowSize > 3) {
                    rowNumber = 4;
                } else if (TableRowSize == 3) {
                    rowNumber = 3;
                } else {
                    if (Boolean.parseBoolean(blnExist)) {
                        logger.info("sufficient results not returned.");
                        throw new CustomException(
                                "sufficient results not returned.");
                    }
                }

                if ((TableColSize > 0)) {
                    String loc = tblField + "/tr";
                    for (int row = rowNumber; row <= TableRowSize; row++) {
                        String subloc = loc + "[" + row + "]/td[" + colNumber + "]";
                        logger.info("subloc : " + subloc);
                        String text = browser.getCurrentWebDriver()
                                .findElement(By.xpath(subloc)).getText().trim();
                        result = text.contains(strSearchMember);
                        if (result) {
                            logger.info(strSearchMember
                                    + "member found as expected.");
                            break;
                        }
                    }
                }

                if (Boolean.parseBoolean(blnExist)) {
                    if (result) {
                        scr.capturePageScreenshot();
                        logger.info(strSearchMember
                                + " member found as expected.");
                    } else {
                        logger.info(strSearchMember
                                + " member NOT found which is NOT expected.");
                        scr.capturePageScreenshot();
                        throw new CustomException(strSearchMember
                                + " member NOT found which is NOT expected.");
                    }
                } else {
                    if (result) {
                        logger.info(strSearchMember
                                + " member found which is NOT expected.");
                        scr.capturePageScreenshot();
                        throw new CustomException(strSearchMember
                                + " member found which is NOT expected.");
                    } else {
                        scr.capturePageScreenshot();
                        logger.info(strSearchMember
                                + " member NOT found as expected.");
                    }
                }

            } else {
                logger.info("Results table not found.");
                scr.capturePageScreenshot();
                throw new CustomException("Results table not found.");
            }

        } catch (Exception e) {
            logger.info("Exception in verifying the search results : " + e.getMessage());
            scr.capturePageScreenshot();
            throw new CustomException("Exception in verifying the search results : " + e.getMessage());
        }
    }
    
  
    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : performQuickMemberSearchAndVerify keyword to perform quick member search on group menu bar
     *
     * PreCondition : Vista Home page
     *
     * PostCondition : Member search result page
     *
     * <b>Parameters & Example </b>
     *
     * | strSearchMember - String parameter to search |
     * | AutoTest1235 |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"strSearchMember"})
    public void performQuickMemberSearch(String strSearchMember) {
        try {

            // get the value from hashmap if available
            strSearchMember = utils.getValue(strSearchMember);

            this.setMemberSearch(strSearchMember);

            this.clickSearchButton();

            Thread.sleep(3000);

        } catch (Exception e) {
            throw new CustomException("Member search failed: \n"
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  :
     *
     * Description : 'memberSearchInVistaRole' keyword used to search the member by its LastName in Vista Role.
     *
     * PreCondition : user should be in Groups Page
     *
     * PostCondition : user  will navigates to Members page.
     *
     * <pre>
     * <b>Parameters :</b>
     * | strLastName - Value in Stored Member.
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({ "strLastName" })
    public void memberSearchInVistaRole(String strLastName)
    {
        if (strLastName.startsWith("HMV")) {
            strLastName = utils.getValue(strLastName);
        }
        try{

            this.clickSearchMember();
            this.enterSearchTextBox(strLastName);
            this.clickSearchMemberByLastName();

        }catch (Exception e) {
            System.out.println("Exception occured " + e.getMessage());

            throw new CustomException("Exception in navigating to Member Search page "
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Nagarjuna Behara
     *
     * Description : 'deleteMemberInVista' keyword used to delete the member when member has searched by its LastName.
     *
     * Role : Vista Role
     *
     * PreCondition : user should be in Member Page in Vista Role
     *
     * PostCondition : user  will be delete the particular Member.
     *
     * <pre>
     * <b>Parameters & Example </b>
     * | None |
     * Java file Name :  DeleteMember.java
     * </pre>
     **/

    @RobotKeyword

    public void deleteMemberInVista(){
        try {
            this.mouseoverEditMenu();
            this.clickDeletePerson();
            this.clickDeleteLink();
            this.selectYesOption();
            this.clickSaveButton();
            scr.capturePageScreenshot();
        }catch (Exception e) {
            logger.info("Exception occured " + e.getMessage());
            scr.capturePageScreenshot();
            throw new CustomException("Exception in navigating to Member page "
                    + e.getMessage());
        }
    }
    
    /**
	 * <pre> 
	 * Author  : Sunitha Yerra
	 *  
	 * Description : 'updateMemberAccountInVistaRole' keyword used to reset member password in vista role.
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : user should be in Member Page in Vista Role
	 * 
	 * PostCondition : password has been reset for the member
	 *
	 * <b>Parameters & Example </b> 
	 * | newPassword|
	 * 
	 * Java file Name :  MemberSearch.java
	 * </pre> 
	 **/
	
	@RobotKeyword
	@ArgumentNames({ "newPassword" })
	public void updateMemberAccountInVistaRole(String newPassword){		
		try {

			if(newPassword.startsWith("td:")){
				newPassword = utils.getValue(newPassword);
			}
			
			this.setNewPassword(newPassword);
			this.setConfirmPassword(newPassword);
			this.clickPasswordSavebutton();
			scr.capturePageScreenshot();
			performAction.waitForPageLoad();
			Assert.assertTrue(performAction.isElementPresent(searchTextBox, "Member Search Text Box "));
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured while changing password in update member account");
			throw new CustomException(
					"Exception occured while changing password in update member account"
							+ e.getMessage());
		}
	}	


}
