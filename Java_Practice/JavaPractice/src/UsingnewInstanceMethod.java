
class Test1 {  int a; }
class Test2 {  int b; }
  

public class UsingnewInstanceMethod {
	
	static void fun(String c)  throws InstantiationException,
    IllegalAccessException, ClassNotFoundException
{
    // Create an object of type 'c' 
    Object obj = Class.forName(c).newInstance();
    	
    
    // This is to print type of object created
    System.out.println("Object created for class:"
                    + obj.getClass().getName());
    
    System.out.println("Value of variable a:"
            + obj.getClass().getFields().length);
}

// Driver code that calls main()
public static void main(String[] args) throws InstantiationException,
IllegalAccessException, ClassNotFoundException
{
     fun("Test1");
}   

}
