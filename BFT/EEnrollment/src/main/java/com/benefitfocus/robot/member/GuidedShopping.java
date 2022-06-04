package com.benefitfocus.robot.member;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
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
public class GuidedShopping {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Logging logger;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Screenshot screen;

	// Locators
	String QuestionsList = "//fieldset[contains(@class,'unit size1of1')]/div";
	By submitButton = By.xpath("//span[contains(text(),'Save')]");
	By beginEnrollmentButton = By
			.xpath("//a[contains(text(),'Begin enrollment')]");
	By skipShopping = By.xpath("//span[contains(text(),'Skip')]");
	String QuestionList = "//fieldset[@class='row-fluid']/div";
	By save = By.cssSelector("button.btn.btn-primary");
	By skipGuidedShopping = By.cssSelector("a[href*='skip']");
	String newMarketPlace = "//div[@class='region-plan-selection-plan-list']/div";
	By bestMacthLogo = By
			.xpath("//i[@class='bficon bficon-best-match bficon-1x']");
	By bestMacthText = By
			.xpath("//div[@class='region-plan-selection-plan-list']/div[1]//span[text()='Best Match']");
	By sortPlans = By.id("sort");
	By mercerLogo = By
			.xpath("//img[@src='http://eeshrel/public/control/publicClientContent/Mercer_Marketplace_4c.gif']");
	By clientLogo = By.xpath("//span[@class='secondary-logo']");
	By bestMatchLable = By
			.xpath("//i[@class='bficon bficon-best-match-alt bficon-3x bficon-primary']");

	// Private Methods

	// perform click on radio buttons
	private void clickOnSubmit() {
		performAction.click(submitButton, "Save the Survey");
	}

	// This method to select the answer in guided shopping page
	private void selectAnswer(String strOption) {
		if (strOption != "") {
			String loc = "//h6[contains(text(),'" + strOption + "')]";
			System.out.println("Print Locator..." + loc);
			performAction.click(loc, "Answer to select");
			System.out.println("Entered into selecting answer");
		} else {
			System.out.println("No option is provided for the Question");
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : completeGuidedShopping keyword or method is selects the first option for every  
	 * guided shopping question and Navigates to Offers Page in New Member role.  
	 * 
	 * Role: New Member Role
	 * 
	 * PreCondition : Member should be in Guided shopping page
	 * 
	 * PostCondition : Member will be redirected to the offer page by completing the guide shopping.
	 * 
	 * JavaFileName : guidedShoppingPage.java
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	public void completeGuidedShoppingInMemberRole() {
		try {

			int QuestList = browser.getCurrentWebDriver()
					.findElements(By.xpath(QuestionsList)).size();
			logger.info("Question Count..." + QuestList);

			for (int i = 2; i <= QuestList; i++) {

				String QuestName = browser
						.getCurrentWebDriver()
						.findElement(
								By.xpath(QuestionsList + "[" + i
										+ "]/label/span")).getText();

				logger.info("Question is ..." + QuestName);

				By QuestAnswer = By.xpath(QuestionsList + "[" + i
						+ "]/div/div[1]//h6");
				browser.getCurrentWebDriver().findElement(QuestAnswer).click();
				String FirstAnswer = browser.getCurrentWebDriver()
						.findElement(QuestAnswer).getText();
				logger.info("Selected First Answer..." + FirstAnswer);
			}
			screen.capturePageScreenshot();
			this.clickOnSubmit();
			performAction.verify(beginEnrollmentButton, "Begin enrollment",
					"Begin Enrollment button");

		} catch (Exception e) {
			screen.capturePageScreenshot();
			logger.warn("Exception occured in Guided shopping page verification. "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured in Guided shopping page verification. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : skipGuidedShopping keyword or method is skip the guided shopping for the new member role. 
	 * 
	 * Role : New Member role
	 * 
	 * PreCondition : Member should be in Guided shopping page
	 * 
	 * PostCondition : Member will be redirected to the offer page by skipping the guide shopping.
	 * 
	 * JavaFileName : guidedShoppingPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void skipGuidedShopping() {
		try {
			if (performAction.isAlertPresent()) {
				performAction.acceptAlert();
			}
			if (performAction.isElementPresent(skipShopping)) {
				performAction.click(skipShopping, "Skip Guided Shopping");
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}
				screen.capturePageScreenshot();
			} else if (performAction.isElementPresent(skipGuidedShopping)) {
				performAction.click(skipGuidedShopping, "Skip Guided shopping");
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}
				screen.capturePageScreenshot();
			}
		} catch (Exception e) {
			screen.capturePageScreenshot();
			logger.warn("Exception occured in skipping Guided shopping page.  "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured in skipping Guided shopping page. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : completeGuidedShopping keyword or method is selects the first option for every  
	 * guided shopping question and Navigates to Offers Page in New Member role.  
	 * 
	 * Role : New Member Role
	 * 
	 * PreCondition : Member should be in Guided shopping page
	 * 
	 * PostCondition : Member will be redirected to the offer page by completing the guide shopping.
	 * 
	 * JavaFileName : guidedShoppingPage.java
	 * 
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({ "strTagName" })
	public void completeGuidedShoppingInNewMemberRole(String guidedShopping) {
		try {
			Thread.sleep(1000);
			if (performAction.isAlertPresent()) {
				Thread.sleep(1000);
				performAction.acceptAlert();
			}
			if (guidedShopping.startsWith("td:"))
				guidedShopping = guidedShopping.substring(3);
			int QuestList = browser.getCurrentWebDriver()
					.findElements(By.xpath("QuestionList")).size();
			logger.info("Question Count..." + QuestList);
			JSONObject shopping = ReadJsonTestData.getTestData(guidedShopping);
			logger.info("shopping Answers size..." + shopping.size());
			if (performAction.isElementPresent(skipGuidedShopping)) {
				for (int i = 2; i <= shopping.size() + 1; i++) {
					if (i < 5 || i > 5) {
						String QuestName = browser
								.getCurrentWebDriver()
								.findElement(
										By.xpath(QuestionList + "[" + i
												+ "]/label/span")).getText();
						logger.info("Question is ..." + QuestName);
						String Answer = shopping.get("answer" + (i - 1))
								.toString();
						logger.info("Selected Answer is...." + Answer);
						By ansLoc = By.xpath(QuestionList + "[" + i
								+ "]/div//h6[text()='" + Answer
								+ "']");
						performAction.jsclick(ansLoc, "Answer Locator");
						/*browser.getCurrentWebDriver()
								.findElement(
										By.xpath(QuestionList + "[" + i
												+ "]/div//h6[text()='" + Answer
												+ "']")).click();*/
					} else if (i == 5) {
						String QuestName = browser
								.getCurrentWebDriver()
								.findElement(
										By.xpath(QuestionList + "[" + i
												+ "]/label/span")).getText();
						logger.info("Question is ..." + QuestName);
						String[] Answer = shopping.get("answer" + (i - 1))
								.toString().split(",");
						logger.info("Selected Answer is...." + Answer);
						logger.info("answer lenght is.." + Answer.length);
						for (int x = 0; x < Answer.length; x++) {
							this.selectAnswer(Answer[x]);
						}

					}
				}
				screen.capturePageScreenshot();
				performAction.click(save, "Save Button");

				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}

				/* screen.capturePageScreenshot(); */
			} else {
				logger.warn("Guided shopping is not available");
			}
			if (performAction.isElementPresent(mercerLogo)) {
				logger.info("Mercer Logo found in the page");
			} else {
				logger.warn("Mercer Logo Not found");
			}
			if (performAction.isElementPresent(clientLogo)) {
				logger.info("Client logo found in the page");
			} else {
				logger.warn("Client Logo not found in the page");
			}

		} catch (Exception e) {
			screen.capturePageScreenshot();
			logger.warn("Exception occured in Guided shopping page verification. "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured in Guided shopping page verification. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : verifyGuidedShoppingOffer keyword or method verify the Bes Match label,Best match sorting and   
	 * best match symbol in offers page in New Member role.  
	 * 
	 * Role: New Member Role
	 * 
	 * PreCondition : Member should be in Guided shopping page
	 * 
	 * PostCondition : Member will be redirected to the Plans page by completing the guide shopping.
	 * 
	 * JavaFileName : guidedShoppingPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	public void verifyGuidedShoppingOfferInNewMemberRole() {
		try {
			if (performAction.isAlertPresent()) {
				performAction.acceptAlert();
			}
			boolean bestMacth = performAction.isElementPresent(bestMacthLogo);
			if (bestMacth == true) {
				logger.info("Best Match Logo is Visible in Plans Page");
				performAction.verify(bestMacthText, "Best Match",
						"Best Match Text");
			} else {
				logger.warn("Best Match Lable is not Visible in Plans Page");
			}
			if (performAction.isElementPresent(sortPlans)) {
				Select opt = new Select(browser.getCurrentWebDriver()
						.findElement(sortPlans));
				String sel = opt.getFirstSelectedOption().getText();
				if (sel.equals("Best Match")) {
					logger.info("plans sorted in" + sel);
				}

				else {
					logger.warn("plans not sorted in" + sel
							+ "  instead of Best Match");
				}
			} else {
				logger.warn("plans not sorted in Best Match Order");
			}
			screen.capturePageScreenshot();
		} catch (Exception e) {
			screen.capturePageScreenshot();
			logger.warn("Exception occured in Guided shopping page verification. "
					+ e.getMessage());
			throw new CustomException(
					"Exception occured in Guided shopping page verification. "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : verifyGuidedShoppingOffer keyword or method verify the Bes Match label,Best match sorting and   
	 * best match symbol in coverage amounts page for Life plans in New Member role.  
	 * 
	 * Role : New Member role
	 * 
	 * PreCondition : Member should be in Guided shopping page
	 * 
	 * PostCondition : Member will be redirected to the Plans page by completing the guide shopping.
	 * 
	 *  <b>Parameters & Example </b> 
	 * 
	 * | strPlan |
	 * |  2016 Employee Term Life  |
	 * 
	 * JavaFileName : guidedShoppingPage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPlan" })
	public void verifyBestMatchInGuidedShoppingForLifePlans(String strPlan) {
		try {
			/*
			 * Thread.sleep(5000); performAction.verifyMessage(strPlan);
			 */
			boolean bestMacth = performAction.isElementPresent(bestMatchLable);
			if (bestMacth == true) {
				System.out.println("Best Match Lable is Visible in Plans Page");
				performAction
						.verifyMessage("Based on the answers you provided, we determined that this product is a best match for you!");
			} else {
				logger.warn("Best Match Lable is not Visible in Plans Page");
			}
			screen.capturePageScreenshot();
		} catch (Exception e) {
			throw new CustomException(
					"Exception occured in Best Match lable verification. "
							+ e.getMessage());
		}
	}
}
