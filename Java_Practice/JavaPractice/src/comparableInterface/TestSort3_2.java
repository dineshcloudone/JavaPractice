package comparableInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

class Employee implements Comparable<Employee>{

	int id;
	String name;
	int age;

	Employee(int rollno, String empname, int empage) {

		this.id = rollno;
		this.name = empname;
		this.age = empage;
	}
	
	@Override
	public int compareTo(Employee e) {

		if (age == e.age) {
			return 0;
		} else if (age > e.age) {
			return 1;
		} else {
			return -1;
		}
	}

	

}

public class TestSort3_2 {
	
	public static void main(String[] args) {
		
		ArrayList<Employee> al1=new ArrayList<Employee>();
		
		Employee e1=new Employee(1,"abc",24);
		Employee e2=new Employee(2,"def",30);
		Employee e3=new Employee(3,"def",33);
		
		al1.add(e1);
		al1.add(e2);
		al1.add(e3);
		
		Collections.sort(al1);
		
	}

}
