package java9features;

interface Example1{
	void test();
}

public class MethodReferenceEx1 {
	
public static void testingMethodReference() {
	System.out.println("testing method reference");
}

public static void main(String[] args) {
	Example1 ex=MethodReferenceEx1::testingMethodReference;
	
	ex.test();
}
}


/*interface Sayable2{  
    void say();  
}  
public class MethodReference1 {  
    public static void saySomething(){  
        System.out.println("Hello, this is static method.");  
    }  
    public static void main(String[] args) {  
        // Referring static method  
        Sayable2 sayable = MethodReference1::saySomething;  
        // Calling interface method  
        sayable.say();  
    }  
}  */