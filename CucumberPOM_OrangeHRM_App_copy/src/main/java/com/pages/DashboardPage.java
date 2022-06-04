package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {

	private WebDriver driver;

	private By lnkAssignLeave = By.xpath("//span[text()='Assign Leave']");

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void click_AssignLeave_Link() {
		
		driver.findElement(lnkAssignLeave).click();
		
	}

	/*
	 * public int getAccountsSectionCount() { return
	 * driver.findElements(accountSections).size(); }
	 * 
	 * public List<String> getAccountsSectionsList() {
	 * 
	 * List<String> accountsList = new ArrayList<>(); List<WebElement>
	 * accountsHeaderList = driver.findElements(accountSections);
	 * 
	 * for (WebElement e : accountsHeaderList) { String text = e.getText();
	 * System.out.println(text); accountsList.add(text); }
	 * 
	 * return accountsList;
	 * 
	 * }
	 */

}
