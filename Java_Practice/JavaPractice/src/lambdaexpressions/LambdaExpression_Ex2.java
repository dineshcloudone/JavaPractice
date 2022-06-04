package lambdaexpressions;

public class LambdaExpression_Ex2 {
	public static void main(String[] args) {
		int width=10;
		
		Drawable d=()->{
			System.out.println("widht value : "+width);
		};
		
		d.draw();
	}
}


interface Drawable{
	public void draw();
}