package exceptions;

public class TestMultipleCatchBlock{  
	  public static void main(String args[]){  
	   try{  
	    int a[]=new int[5];  
	    a[5]=30/0;  
	   }
	   
	   catch(ArithmeticException e){
		   
	   System.out.println("task1 is completed");
	   
	   System.out.println("==================Printing e==================");
	   System.out.println(e);
	   
	   System.out.println("\n");
	   System.out.println("\n");
	   
	   System.out.println("==================Printing estackTrace==================");
	   e.printStackTrace();
	   
	   System.out.println("\n");
	   System.out.println("\n");
	   
	   System.out.println("==================e.getMessage()==================");
	   System.out.println(e.getMessage());
	   
	   }  
	   catch(ArrayIndexOutOfBoundsException e){
		   System.out.println("task 2 completed");
		   }  
	   catch(Exception e){System.out.println("common task completed");}
	     
	  
	   System.out.println("rest of the code...");  
	 }  
	}  