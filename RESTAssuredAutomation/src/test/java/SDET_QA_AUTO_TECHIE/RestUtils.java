package SDET_QA_AUTO_TECHIE;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String getName() {
		
		String generatedString=RandomStringUtils.randomAlphabetic(1);
		//String generatedString=RandomStringUtils.randomNumeric(3);
		
		return ("abc"+generatedString);
	}

	public static String getJob() {
		String generatedString=RandomStringUtils.randomAlphabetic(1);
		return ("def"+generatedString);
	}
	
}
