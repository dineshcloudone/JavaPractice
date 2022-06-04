package exceptions.ncr;

import exceptions.customexception.*;

public class NCREmp {

	public void checkSal(String s) throws NSalException,PSalException, NumberFormatException{
		
		int sal=Integer.parseInt(s);
		if(sal<0)
		{
			throw new NSalException("Less than zero is not allowed to enter");
		}
		/*else
		{
			throw new PSalException("Greater than zero is allowed");
		}*/
		
	}

}
