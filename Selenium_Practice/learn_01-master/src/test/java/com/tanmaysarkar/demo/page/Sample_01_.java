package com.tanmaysarkar.demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tanmaysarkar.demo.Base;

public class Sample_01_ extends Base{
	public WebDriver driver;
	public Sample_01_(WebDriver driver){
		this.driver = driver;		
	}
	public By fname = By.id("ts_first_name");
	public WebElement getFName(){
		return driver.findElement(fname);
	}
	public By lname = By.id("ts_last_name");
	public WebElement getLName(){
		return driver.findElement(lname);
	}
	public By address = By.id("ts_address");
	public WebElement getAddress(){
		return driver.findElement(address);
	}
}
