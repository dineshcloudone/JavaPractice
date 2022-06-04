package exceptions.customexception;

/*
 * Extend Exception :
 * 	- While defining custom exception, if you extend Exception class you will get 'unhandle exception while using the custom exception' in case of not 
 * 		keeping the calling method in try catch.
 * 	- in this case when you throw an exception in method you must use throws at method header 
 * 
 * Ex : Try editing below class extends with RuntimeException and Exception and check it in CustomException2.java
 * 
 *  If you extend RuntimeExcecption :
 *  - you won't get /unhandled exception message/ even if you are not keeping the method in try catch block while calling it. 
 *  - in this case when you throw an exception in method, it won't ask you to use throws at method header	
 *  
 *  Ex : Try editing below class extends with RuntimeException and Exception and check it in CustomException2.java
 *  
 */


public class NSalException extends Exception{

	public NSalException(String msg) {
		// TODO Auto-generated constructor stub
	super(msg);
	}

}
