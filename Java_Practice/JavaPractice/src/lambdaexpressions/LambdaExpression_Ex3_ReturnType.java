package lambdaexpressions;

interface Sayable{
	public String say();
}

public class LambdaExpression_Ex3_ReturnType {

	public static void main(String[] args) {
		Sayable s=()->{
			return "this is lambda expression";
		};
		
		System.out.println(s.say());		
	}
}
