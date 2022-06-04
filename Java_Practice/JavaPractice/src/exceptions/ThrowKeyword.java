package exceptions;

public class ThrowKeyword{  
	   static void validate(int age) { //throws RuntimeException{  
	     if(age<18)  
	      throw new ArithmeticException("not valid");
	    	 //throw new IOException("not valid");
	    	 //System.out.println("Test");
	     else  
	      System.out.println("welcome to vote");  
	   }  
	   public static void main(String args[]){  
	      validate(13);  
	      System.out.println("rest of the code...");  
	  }  
	}  