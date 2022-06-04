package innerclasses;

abstract class Person
{
	abstract void salary();
}

public class AnonymousInnerClass {
	
public static void main(String[] args) {

	Person p=new Person(){
		void salary() {
			System.out.println("Person Salary Method");}
		};
		
		p.salary();
		
	}
}

