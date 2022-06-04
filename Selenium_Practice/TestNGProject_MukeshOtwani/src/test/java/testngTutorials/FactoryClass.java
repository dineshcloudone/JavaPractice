package testngTutorials;

import org.testng.annotations.Factory;

public class FactoryClass {

	@Factory
	public Object[] invokeObject()
	{
		Object[] obj=new Object[3];
		obj[0]=new FactoryAnnotationPractice(1);
		obj[1]=new FactoryAnnotationPractice(2);
		obj[2]=new FactoryAnnotationPractice(3);
		/*for(int i=0;i<3;i++)
			obj[i]=new FactoryAnnotationPractice(i*2);*/
		return obj;
	}

   /* @Factory
    public Object[] factoryMethod() {
        return new Object[] { new SimpleTest(0), new SimpleTest(1) };
    }*/

}
