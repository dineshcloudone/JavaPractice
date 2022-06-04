package java8newfeatures;

/*public class LambdaExpressions {

}*/
public class LambdaExpressions {  
    public void printnMsg(){  
        System.out.println("Hello, this is instance method");  
    }  
    public static void main(String[] args) {  
    Thread t2=new Thread(new LambdaExpressions()::printnMsg);  
        t2.start();       
    }  
}