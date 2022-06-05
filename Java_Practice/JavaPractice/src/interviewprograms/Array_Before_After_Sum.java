package interviewprograms;

//package whatever; // don't place package name!
//Installed Libraries: JSON-Simple, JUNit 4, Apache Commons Lang3


//program to find sum of number in a given number
//https://www.geeksforgeeks.org/java-program-for-sum-the-digits-of-a-given-number/?ref=lbp


//Equilibrium Index : https://www.geeksforgeeks.org/equilibrium-index-of-an-array/

//Find an element in array such that sum of left array is equal to sum of right array
//Input: 2 3 4 1 4 5
//Output:1

//Example:
//Input: 1 4 2 5
//Output: 2
//Explanation: If 2 is the partition, 
//    subarrays are : {1, 4} and {5}

public class Array_Before_After_Sum {
	
	//import java.io.*;	

		public static void main (String[] args) {
	  
	    int[] arr={2,3,4,1,4,5,5};   	    
	    
	    int before;
	    int after;
	    
	    for(int i=1;i<arr.length;i++){
	    	
	      before=0;	      
	      after=0;
	      
	      for(int x=0;x<i;x++){
	        
	        before+=arr[x];
	        
	        }
	        
	      for(int y=arr.length-1;y>i;y--){
	        
	        after+=arr[y];
	        
	      }
	      
	      if(before==after){
	        
	        System.out.println("Middle Value is "+arr[i]);
	      }
	      
	    }
	    
	    
	  
	}


	


}
