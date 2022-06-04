class Vehicle{  
  //defining a method  
  //static void run(){System.out.println("Vehicle is running");}  
	void run(){System.out.println("Vehicle is running");}
}  


class Vehicle2 extends Vehicle{
	//static void run(){System.out.println("Vehicle2 is running");}
	void run(){System.out.println("Vehicle2 is running");}
}

//Creating a child class  
public class OverridingEx3 extends Vehicle2{  
  //defining the same method as in the parent class  
  //static void run(){System.out.println("Bike is running safely");}  
	void run(){System.out.println("Bike is running safely");}
  
  public static void main(String args[]){  
  Vehicle2 obj = new OverridingEx3();//creating object  
  
  System.out.println(obj.getClass());
  obj.run();//calling method  
  }  
}  