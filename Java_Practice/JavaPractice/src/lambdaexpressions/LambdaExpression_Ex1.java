package lambdaexpressions;

import java.util.*;

public class LambdaExpression_Ex1 {

	public static void main(String[] args) {
		
		AddAnother ao = (x) -> x * 3;
		System.out.println("FirstMystery Implementation :" + ao.mysteryMethod(5));
		
		AddAnother ao2=(x)->x+3;
		System.out.println("Second Mystery Implementation :" + ao2.mysteryMethod(9));
		
	}
}

interface AddAnother {
	int mysteryMethod(int x);
	
}
