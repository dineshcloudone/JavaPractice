//import covariantreturntype_2.Base;

class AA {}
class BB extends AA {}
  
class Base1
{
    AA fun()
    {
        System.out.println("Base fun()");
        return new AA();
    }
}
  
class Derived1 extends Base
{
    BB fun()
    {
        System.out.println("Derived fun()");
        return new BB();
    }
}
  
public class OverridingEx2
{
    public static void main(String args[])
    {
       Base1 base = new Base1();
       base.fun();
  
       Derived1 derived = new Derived1();
       derived.fun();
    }
}