package stringpractice;

import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;

public class StringFAQS {
	
	void removeDuplicateChar(String s) {
		
		int n=s.length();
		
		char[] str=s.toCharArray();
		
		// Used as index in the modified string
		   int index = 0;  
		    
		   // Traverse through all characters
		   for (int i=0; i<n; i++) {
		        
		     // Check if str[i] is present before it 
		     int j; 
		     for (j=0; j<i; j++)
		        if (str[i] == str[j])
		           break;
		      
		     // If not present, then add it to
		     // result.
		     if (j == i)
		        str[index++] = str[i];
		   }
		    
		  System.out.println("Data Printed on console :"+String.valueOf(Arrays.copyOf(str, index)));
		  
		  System.out.println(" Data Printed on console :"+(new String(str)));
		  
			/*
			 * char[] a1= {'a','b'};
			 * 
			 * char[] a2= {'c','d'};
			 * 
			 * a1[0]=a2[1];
			 * 
			 * System.out.println("char[] :"+String.valueOf(a1));
			 */
		    
		
	}
	
	void reverseStringEx1(String s)
	{		
		char[] ch=new char[s.length()];
		char[] ch2=new char[s.length()];
		
		ch=s.toCharArray();
		
		/*for(int i=0;i<s.length();i++)
		{
			ch[i]=s.charAt(i);
		}*/
		
		for(int i=s.length()-1,j=0;i>=0;i--,j++)
		{
			System.out.print(ch[i]);
			ch2[j]=ch[i];			
		}
		
		System.out.println();
		System.out.println("Reversed String: "+new String(ch2));
		
		//System.out.println("Using reverse funtion :"+new StringBuilder(s).reverse());
	}

	void reverseStringEx2(String s) {
		
		//System.out.println("removing spaces : "+s.replace(" ", ""));
		
		char[] ch=s.toCharArray();			
		
		String rev="";
		
		for(int i=s.length()-1;i>=0;i--) {
			rev+=ch[i];
		}
		
		System.out.println("reversed string : "+rev.replace(" ", ""));
	}
	
	void verifyPalindrome(String s)
	{
		char[] ch=new char[s.length()];		
		
		for(int i=s.length()-1,j=0;i>=0;i--,j++)
		{
			ch[j]=s.charAt(i);			
		}		
		
		System.out.println("Palindrom:"+ new String(ch).equals(s));
		
	}
	
	void toggle(String s)
	{
		String[] words=s.split(" ");		
		String toggle="";
		for(String word : words)
		{
			String first=word.substring(0,1).toUpperCase();
			String rem=word.substring(1,word.length());
			toggle+=first+rem+" ";
		}
		System.out.println("After completion of toggle :"+toggle);
				
	}
	
	void sortWordsEx()
	{
		String s="java collections string constructor ab";		
		
		String[] sA=s.split(" ");
		
		String temp=null;
		
		for(int i=0;i<sA.length;++i)
		{
			for(int j=i+1;j<sA.length;++j) {
				
				int a=sA[i].length();
				int b=sA[j].length();
				
				if(a>b) {
					temp=sA[i];
					sA[i]=sA[j];
					sA[j]=temp;
				}
			}
		}
		
		for(String fString:sA) {
			System.out.println(" "+fString);
		}
		
	}
	
	void sortArray() {
		
		int[] iArray= {4,1,7,4,3,9,7};
		int temp;
		for (int i = 0; i < iArray.length; ++i) {
			for (int j = i+1; j < iArray.length; ++j) {
				if (iArray[i] > iArray[j]) {

					temp = iArray[i];
					iArray[i] = iArray[j];
					iArray[j] = temp;
				}
			}
		}
		
		for (int k : iArray) {
			System.out.print(" " + k);
		}
		
	
	}
	
	void binarySearch() {
		
		int[] array= {2,3,4,5,6,7,8,9};
		
		int searchnumber=12;
		
		int lowerbound=0;
		int upperbound=array.length-1;		
		
		boolean temp=false;
		
		while(lowerbound<=upperbound) {
			
			int mid=(lowerbound+upperbound);
			mid=mid/2;
			
			if(searchnumber ==array[mid]) {
				temp=true;
			}
			if(searchnumber<array[mid]) {
				upperbound=mid-1;
			}else {
				lowerbound=mid+1;
			}
		}
		
		if(temp) {
			System.out.println("search element found :" +searchnumber);
		}else {
			System.out.println("search element not found 	");
		}		
		
	}
	
	void countCharOccurance() {
		
		String str="Dinesh Dine";
	
		int MAX_CHAR=256;
		
		
		int count[] = new int[MAX_CHAR];		 
        int len = str.length();
        
               
        // Initialize count array index
        for (int i = 0; i < len; i++) {
            count[str.charAt(i)]++;
            //System.out.println("count arry initialization :"+count[str.charAt(i)]);
        }
          
        char[] char2=str.toCharArray();        
        
        List<Character> l=new ArrayList<Character>();
        
        for(char c:char2) {
        	
        	if(!(l.contains(c)))
        		l.add(c);        	        		
        }        
        
        
        l.forEach(x->System.out.println("each char occurance :"+x+" - "+count[x.charValue()]));
        
        // Create an array of given String size
        /*char ch[] = new char[str.length()];
        for (int i = 0; i < len; i++) {
            ch[i] = str.charAt(i);
            int find = 0;
            for (int j = 0; j <= i; j++) {
 
                // If any matches found
                if (str.charAt(i) == ch[j]) 
                    find++;                
            }
 
            if (find == 1) 
                System.out.println("Number of Occurrence of " +
                 str.charAt(i) + " is:" + count[str.charAt(i)]);            
        }*/
		
	}
	
	void countChar_UsingHashMap() {
		
		String str="Dinesh Dine";
		HashMap<Character,Integer> hm=new HashMap<Character,Integer>();
		char[] charArray=str.toCharArray();
		for(Character ch:charArray) {
			if(hm.containsKey(ch)) {
				hm.put(ch, hm.get(ch)+1);
			}else {
				hm.put(ch, 1);
			}
		}
		for(Entry<Character, Integer> e:hm.entrySet()) {
			
			System.out.println(e.getKey()+" : "+e.getValue());
		}
		
	}
	
	void nullStringInIf() {
		
		String s1="Dinesh";
		String s2=null;
		
		if(s2==null) {
			System.out.println("In if condition");
		}
		else {
			System.out.println("in else condtion");
		}
	}
	
	void countChar() {
		
		String s="dinesh:dinesh:dineshidi";
		
		String[] sarray=s.split("i",0);
		
		int length=sarray.length;
		
		System.out.println("no of i's : "+ length);
		
		//System.out.println("String length :"+ "D inesh".length());
	}
	
	void countChar2() {
		
		String s="dineshdsh";
			
		char[] c=s.toCharArray();
		
		int temp;
		
		for(int i=s.length()-1;i>0;i--) {
			temp=0;
			for(int j=0;j<=s.length()-1;j++) {
				if(c[i]==c[j]) {
					temp++;
				}
			}
			System.out.println("count of char : "+c[i]+ " :"+ temp);
		}
	}
	
	void countWord() {
		
		String sentence="welcomewelcomewelcomewelcomewel";
		String name="come";
		
		char[] ch1=sentence.toCharArray();
		
		char[] ch2=name.toCharArray();
		int wordcount=0;
		int loopcount;
		boolean bool=true;
		
		for(int i=0;i<ch1.length;i++) {
			loopcount=0;
			for(int j=0;j<ch2.length;j++) {
				
				if(ch1[i]==ch2[j]&&i<ch1.length) {
					i++;
					loopcount++;
					if(loopcount==name.length()) {
						wordcount++;
					}
					continue;						
				}else {
					break;
				}
			}
		}
		
		System.out.println("Word repeated :"+ Integer.valueOf(wordcount));
	}
	
	void removeParticularCharacterFromString(String s) {
		
		char c='e';
		String s1=String.valueOf(c);
		System.out.println("After removing char e : "+s.replace(s1, ""));
	}
	
	void swapTwoStringWithoutThirdVar() {
		
		String s1="abc";
		String s2="def";
		System.out.println("Before Swap : "+s1+" "+s2);
		s1=s1+s2;
		
		s2=s1.substring(0,s1.length()-s2.length());
		s1=s1.substring(s2.length());
		
		System.out.println("After Swap : "+s1+" "+s2);
	}
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		StringFAQS faq=new StringFAQS();
				
		//faq.reverseStringEx("This is Dinesh");
		
		//faq.verifyPalindrome("madam");
		//faq.toggle("this is dinesh");
		
		//faq.sortWords();
		//faq.sortArray();
		//faq.binarySearch();
		//faq.countCharOccurance();
		
		faq.nullStringInIf();
		
		//faq.countChar();
		//faq.countWord();		
		
		//String s="dinesh kumar";
		
		String s="geeks for geeks";
		
		//faq.removeDuplicateChar(s);
		
		//String a=Character.toString('a')+Character.toString('b');
		
		//System.out.println("String Reverse :"+ new StringBuilder(s).reverse());
		
		//faq.countChar2();
		
		//faq.reverseStringEx2(s);
		//faq.sortWordsEx();
		
		//faq.removeParticularCharacterFromString(s);
		//faq.swapTwoStringWithoutThirdVar();
		
		faq.countChar_UsingHashMap();
	}
	
	

}
