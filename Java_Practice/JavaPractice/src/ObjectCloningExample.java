
public class ObjectCloningExample implements Cloneable{

	int a ;
	int b ;
	
	 ObjectCloningExample(int a, int b) {
		// TODO Auto-generated constructor stub
		 this.a=a;
		 this.b=b;
	}
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
		}  
	
	
	public static void main(String[] args) {
		
		
		try {
			ObjectCloningExample obj1=new ObjectCloningExample(1,2);
			ObjectCloningExample obj2=(ObjectCloningExample)obj1.clone();
			
			System.out.println("obj1 values "+ obj1.a +" "+ obj1.b);
			System.out.println("obj1 values "+ obj2.a +" "+ obj2.b);
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

