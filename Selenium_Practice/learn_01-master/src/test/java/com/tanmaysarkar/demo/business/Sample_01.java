package com.tanmaysarkar.demo.business;

import org.openqa.selenium.WebDriver;
import com.tanmaysarkar.demo.page.Sample_01_;

public class Sample_01 extends Sample_01_{

	public Sample_01(WebDriver driver) {
		super(driver);		
	}
	public void formFillUp(String fname,String lname,String address){
		getFName().clear();
		getFName().sendKeys(fname);
		getLName().clear();
		getLName().sendKeys(lname);
		getAddress().clear();
		getAddress().sendKeys(address);		
	}

}
