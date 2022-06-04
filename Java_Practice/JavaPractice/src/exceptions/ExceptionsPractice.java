package exceptions;

import java.io.IOException;

class ExceptionExample {

	void internalTryCatch() {
		try {
			try {
				System.out.println("going to divide");
				int b = 39 / 0;
			} catch (ArithmeticException e) {
				System.out.println("Arithmetic Exception handles");
				System.out.println(e);
				
				// e.getMessage();
				/*
				 * System.out.println("========Printing stack trace=========");
				 * e.printStackTrace();
				 * System.out.println("=======stack trace was printed=========");
				 */
			}

			try {
				int a[] = new int[5];
				a[5] = 4;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Array IndexOutOfBoundsException handled");
				System.out.println(e);
			}

			System.out.println("other statement");
		} catch (Exception e) {
			System.out.println("handeled");
		}

		System.out.println("normal flow..");
	}

	void throwExample(int age)  //throws Exception
	{

		int a = 1;

		try {

			if (age < 18)
				throw new IOException("Not Valid Age");
			else
				System.out.println("Welcom to Vote");

		} 
		catch (Exception e) {
			
			System.out.println("Your age is not eligible for voting");
			System.out.println("====================PS=============================");
			e.printStackTrace();
			System.out.println("====================getMessage=============================");
			System.out.println(e.getMessage());
			System.out.println("======================print e===========================");
			System.out.println(e);
		}
		
		
	}

	void try_NoCatchBlock() {

		try {
			int a = 1 / 0;
			}

		catch (ArithmeticException ae) {

			// int b=1/0;
			ae.printStackTrace();
		}

		finally {

			System.out.println("From finally block where : try_NoCatchBlock");
		}
	}
}

public class ExceptionsPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExceptionExample ee = new ExceptionExample();
		ee.internalTryCatch();

		// System.out.println("Execution in main method");
		//ee.throwExample(15);

		//ee.try_NoCatchBlock();
	}
}
