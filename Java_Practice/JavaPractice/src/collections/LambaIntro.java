package collections;

class LambaIntro {
    public static void main(String args[]) {
        FunctionalInterface fi;
        fi = (x) -> x * 3; 

        int i = 0;
        i = fi.mysteryMethod(5);
        System.out.println(i); 
        System.out.println(fi.mysteryMethod(38));

        fi = (x) -> x - 7; 
        System.out.println(fi.mysteryMethod(5)); 
        System.out.println();

        AnotherOne ao = (x, y) -> x * y;
        System.out.println(ao.doThings(20, 5));

        ao = (x, y) -> x / y;
        System.out.println(ao.doThings(20, 5));	

        ao = (x, y) -> x + y;
        System.out.println(ao.doThings(20, 5));	

        ao = (x, y) -> x - y;
        System.out.println(ao.doThings(20, 5));	
    }
}

interface FunctionalInterface {
    int mysteryMethod(int x); // implicitly abstract
}

interface AnotherOne {
    int doThings( int x, int y);
}