package this_practice;



class Employee{
	
	int id;
	String name;
	float salary;
	
	Employee(String name,float salary){
		this.name=name;
		this.salary=salary;
	}
	
	Employee(int id,String name, float salary){
		
		this(name,salary);
		this.id=id;
		
	}
	
	void display() {
		
		System.out.println(id+" "+name+" "+salary);
	}
}

public class Real_Usage_Constructor_Chaining {
	
	public static void main(String[] args) {
		Employee e=new Employee(1,"abc",5000.00f);
		e.display();
		
	}

}
