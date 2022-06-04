
public class BoxingEx {
	
	Integer x=new Integer(50);
	Integer i=10;
	Double d=10.00;
	Float f=5f;
	Character c='a';
	Byte by=2;
	Short s=3;
	Long l=9l;
	Boolean bo=false;
	
	//unboxing
	int i2=10;
	int i3=i.intValue();
	
	double d2=d;
	double d3=d.doubleValue();
	
	float f2=f;
	float f3=f.floatValue();
	
	//boxing
	int a=10;
	Integer a2=Integer.valueOf(a);
	
	float f4=1.1f;
	Float f5=Float.valueOf(f4);
	
}
