package innerclasses;

interface Eatable{  
	 void eat();  
	}  

public class AnonymoussInnerClassEx2 {

	public static void main(String args[]){  
		 Eatable e=new Eatable(){  
		  public void eat(){System.out.println("nice fruits");}  
		 };  
		 e.eat();  
		 }  
	
}
