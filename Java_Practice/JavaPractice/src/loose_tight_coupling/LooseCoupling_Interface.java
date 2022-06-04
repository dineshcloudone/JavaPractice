package loose_tight_coupling;


//https://www.upgrad.com/blog/loose-coupling-vs-tight-coupling-in-java/

interface Food{
	
	public void display(); 
}

class Italian implements Food{
	
	Food s;
	
	public Italian(Food s) {
		this.s=s;
	}
	
	public void display() {
		System.out.println("Italian");
		s.display();
	}	
}

class Chinese implements Food{
	
	Chinese(){}
	
	public void display() {
		System.out.println("Chinese");
	}
}

class Mexican implements Food{
	
	Mexican(){}
	
	public void display() {
		
		System.out.println("Mexican");
	}
}

public class LooseCoupling_Interface {

	public static void main(String[] args) {
		Food c=new Chinese();
		Food m=new Mexican();
		Italian i=new Italian(c);
		
		i.display();
		
		Italian i2=new Italian(m);
		i2.display();
	}
}
