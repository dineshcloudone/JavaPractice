package comparatorInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student2 {

	int rollno;
	String name;
	int age;

	Student2(int rollno, String name, int age) {
		this.rollno = rollno;
		this.name = name;
		this.age = age;
	}
}


public class Example_using_Lambda_Exp {

	public static void main(String[] args) {
		ArrayList<Student> al = new ArrayList<Student>();
		al.add(new Student(101, "Vijay", 23));
		al.add(new Student(106, "Ajay", 27));
		al.add(new Student(105, "Jai", 21));
		al.add(new Student(100, "BVijay", 24));
		al.add(new Student(1016, "CAjay", 1627));
		al.add(new Student(1015, "DJai", 1521));

		//sorting for String instance variables of class		
		//Collections.sort(al, (s1,s2)->{ return s1.name.compareTo(s2.name); });
		
		//sorting for integer kind of instance varaibles
		Collections.sort(al,(s1,s2)->{
			  if(s1.rollno==s2.rollno)
				  return 0;
			  else if(s1.rollno>s2.rollno)
				  return 1;
			  else 
				  return -1;
		  });
		
		
		// al.sort(new NameComparator());

		for (Student s : al) {
			System.out.println(s.rollno + " " + s.name + " " + s.age);
		}

		//al.forEach(x -> System.out.println("Using For Each : " + x.rollno + " " + x.name + " " + x.age));
	}
}
