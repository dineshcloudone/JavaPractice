package collections;

import java.util.*;
import java.io.*;

class Dog implements Comparable<Dog>{
	int size;
	
	public Dog(int s) {
	size = s;
	}
	
	public String toString() {
	return size + "";
	}
	
	@Override
	public int compareTo(Dog o) {
	        //return size - o.size;
		
		if(size>o.size){  
	        return 1;  
	    }else if(size<o.size){  
	        return -1;  
	    }else{  
	    return 0;  
	    }  
	}
}


public class TreeSetEx2 {
	public static void main(String[] args)
	{
		LinkedHashSet dset = new LinkedHashSet();
		
		dset.add(new Dog(2));
		dset.add(new Dog(1));
		dset.add(new Dog(3));
		dset.add(new Dog(5));
		dset.add(new Dog(4));
		
		//Collections.sort(dset);
		
		Iterator iterator = dset.iterator();
		while (iterator.hasNext()) {
		System.out.print(iterator.next() + " ");
		}
		
		Dog d=new Dog(9);
		
		
		
	}

}
