package collections;

import java.util.HashMap;
import java.util.LinkedHashMap;

class LinkedHashMapPractice{
	
	
	void accessOrderLinkedHashMap(){
		
		LinkedHashMap<Integer,String> mapVehicleNoAndOwner=new LinkedHashMap(2, 0.75f, true);
		
		mapVehicleNoAndOwner.put(1, "Dinesh");
		mapVehicleNoAndOwner.put(2,"Kumar");
		mapVehicleNoAndOwner.put(3,"Gurram");
		
		mapVehicleNoAndOwner.forEach((x,y)->System.out.printf("Vehicle ID : %d and Owner : %s \n",x,y));
		
		System.out.println("value of key 1 :" +mapVehicleNoAndOwner.get(1));
		System.out.println("value of key 3 :" +mapVehicleNoAndOwner.get(3));
		
		//Least accessed element will be printed first
		mapVehicleNoAndOwner.forEach((x,y)->System.out.printf("After accessing linked hash map -> Vehicle ID : %d and Owner : %s \n",x,y));
		
	}	
}


public class LinkedHashMapEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedHashMapPractice lhmp=new LinkedHashMapPractice();
		lhmp.accessOrderLinkedHashMap();

	}

}
