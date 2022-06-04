package testngTutorials;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionExample {
	
	
	@Test
	public void test_UsingSoftAssert()
	{
		
		SoftAssert asser=new SoftAssert();
		System.out.println("Before Assertion");
		asser.assertEquals(true, false);
		
		asser.assertEquals(true, true);
		System.out.println("After Assertion");
		asser.assertAll();
		
	}

}
