package exceptions.ncr;

import exceptions.customexception.*;

public class CustomException2 {
	
	void salary(String sal) throws NSalException
	{
		
		int s=Integer.parseInt(sal);
		if(s<0) {
			throw new NSalException("Negative Salary is not allowed");
		}
	}

	public static void main(String[] args) throws NSalException{
		// TODO Auto-generated method stub
				
		CustomException2 cst=new CustomException2();		
		cst.salary("-1");
	}

}
