
public class OverloadEx_null {
	
	public void temp(Object o)
    {
        System.out.println("The method with the receiving parameter of type Object has been invoked.");
    }

    public void temp(String s)
    {
        System.out.println("The method with the receiving parameter of type String has been invoked.");
    }

    public void temp(int i)
   // public void temp(Integer i)
    {
        System.out.println("The method with the receiving parameter of type int has been invoked.");
    }

    public static void main(String[] args)
    {
    	OverloadEx_null main=new OverloadEx_null();
    	OverloadEx_null main2=new OverloadEx_null();
    	OverloadEx_null main3=null;
        main.temp(null);
        main.temp(main2);
        main.temp(main3);
    }
}
