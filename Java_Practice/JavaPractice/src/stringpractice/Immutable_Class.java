package stringpractice;

final class Employee {

	final String employee_name;
	
	public Employee(String name) {
		this.employee_name=name;
	}
	
	public String getEmployeeName() {
		return employee_name;
	}
	
	
	
}

public class Immutable_Class{
	
	public static void main(String[] args) {
		
		Employee e=new Employee("dinesh");
		String s1=e.getEmployeeName();
		
		System.out.println("Employee Name is : " + s1);
	}
	
}