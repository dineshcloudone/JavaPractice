package com.apsrtc.demo.TestSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.apsrtc.demo.Base;
import com.apsrtc.demo.Business.Sample01;
import com.apsrtc.demo.Login.*;

public class Set01 {
	
	@Test
	public void test01()
	{
		try
		{
		Base b=new Base();
		WebDriver driver=b.getDriver();
		Sample01 smp1=PageFactory.initElements(driver, Sample01.class);
		driver.get("http://www.apsrtconline.in/oprs-web/login/show.do");
		driver.manage().window().maximize();		
		smp1.APSRTC_FailLogin("dinesh","dinesh");
		driver.quit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
