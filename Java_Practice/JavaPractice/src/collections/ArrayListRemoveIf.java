package collections;
/*
*
*link : http://www.javacjava.com/ArrayListRemoveIf.html
 
 *https://sanaulla.info/2013/03/21/introduction-to-functional-interfaces-a-concept-recreated-in-java-8/
 

import java.util.*;
import java.util.function.Predicate;

class ArrayListRemoveIf {
    public static void main(String args[]) {

        FunctionalInterface<Integer> fi;
        fi = (x) -> x < 5 ;
        int counter = 0;
        while(fi.test(counter)) {
            System.out.println("Counter = " + counter);
            counter++;            
        }
        System.out.println();

        counter = 0;
        Predicate<Integer> pi = (x) -> x < 5;
        while(pi.test(counter)) {
            System.out.println("Counter = " + counter);
            counter++;            
        }
        System.out.println();        
    
        ArrayList<String> states = new ArrayList<String>();
        states.addAll(Arrays.asList("Alaska", "Alabama", "California", "Colorado", "Hawaii", "New York"));
        System.out.println("states = " + states);
        Predicate<String> p = (x) -> x.substring(0, 1).equals("A");
        states.removeIf(p);
        System.out.println("states.removeIf(p) = " + states);
        System.out.println();

        states.removeIf( (String x) -> x.substring(0, 1).equals("C") );
        System.out.println("Now states = " + states);

    }
}

interface FunctionalInterface<T> {
    boolean test(T t) ;
}*/