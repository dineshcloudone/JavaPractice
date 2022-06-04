package lambdaexpressions;

interface Sayable2{
	public String say(String name);
}

public class LambdaExpression_Ex4_Single_Param {

	public static void main(String args[]) {
		
		Sayable2 s1=(name)->{
			return "Test"+name;
		};
		
		s1.say("lambda");
	}
}
