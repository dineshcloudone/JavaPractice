package comparatorInterface;

/*
 * http://javahungry.blogspot.com/2013/08/difference-between-comparable-and.html
 */

import java.util.*;
import java.io.*;

class Student {
	
	int rollno;
	String name;
	int age;

	Student(int rollno, String name, int age)
	{
		this.rollno=rollno;
		this.name=name;
		this.age=age;
	}
}

class AgeComparator implements Comparator
{
	public int compare(Object obj1,Object obj2)
	{
		Student s1=(Student)obj1;
		Student s2=(Student)obj2;
		
		
		
		if(s1.age==s2.age)
		return 0;
		else if(s1.age>s2.age)
		return 1;
		else
		return -1;
	}
}

class NameComparator implements Comparator
{
	public int compare(Object obj1,Object obj2)
	{
		Student s1=(Student)obj1;
		Student s2=(Student)obj2;
		
		return s1.name.compareTo(s2.name);
	}
}

public class Simple
{
	public static void main(String[] args)
	{
		ArrayList<Student> al=new ArrayList<Student>();
		al.add(new Student(101,"Vijay",23));  
		al.add(new Student(106,"Ajay",27));  
		al.add(new Student(105,"Jai",21)); 
		al.add(new Student(100,"BVijay",24));  
		al.add(new Student(1016,"CAjay",1627));  
		al.add(new Student(1015,"DJai",1521));
		
		Collections.sort(al, new NameComparator());
		//al.sort(new NameComparator());
		
		for(Student s:al) 
		{
			System.out.println(s.rollno+" "+s.name+" "+s.age);
		}

		al.forEach(x->System.out.println("Using For Each : "+x.rollno+" "+x.name+" "+x.age));
	}
}





