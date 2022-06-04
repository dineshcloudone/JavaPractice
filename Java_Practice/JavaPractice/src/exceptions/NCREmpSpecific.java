package exceptions;

import exceptions.customexception.*;
import exceptions.ncr.*;

public class NCREmpSpecific {
	public static void main(String[] args) //throws NSalException, PSalException
	{
		try {
			String s = "-1";
			NCREmp n = new NCREmp();
			n.checkSal(s);

		} catch (NSalException nsal) {
			System.out.println("Negative salary exception"+nsal);
			//nsal.getMessage();
		} catch (PSalException psal) {
			psal.getMessage();
		}
	}

}
