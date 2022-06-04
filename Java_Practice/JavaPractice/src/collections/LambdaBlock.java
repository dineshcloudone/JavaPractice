package collections;

class LambdaBlock {
    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("Please input character(s) argument for filtering - not case sensitive.");
            return;
        }
		
        String nameArray[] = { "Sam", "Larry", "Steve", "Mary", "Dan", "Shelly", "Adam", "Sheila" };
          
        // create a lambda expression to filter names based on starting characters 
        FunctionalInterface2 fi = (n, f) -> {
            if ( n.toUpperCase().substring(0, f.length()).equals(f.toUpperCase()) )
                return true;
            else 
                return false;
        };
         
        System.out.println("Names that begin with " + args[0] + ":");   
        for (String temp : nameArray) {
            if (fi.nameFilter(temp, args[0]))
                System.out.println(temp);
        }
        System.out.println();

        // let's change up the filter to check for any characters in the name
        fi = (n, f) -> {
            if ( n.toUpperCase().contains(f.toUpperCase()) )
                return true;
            else 
                return false;
        };

        System.out.println("Names that contain " + args[0] + ":");   
            for (String temp : nameArray) {
                if (fi.nameFilter(temp, args[0]))
                    System.out.println(temp);
        }        
    }
}

interface FunctionalInterface2 {
    boolean nameFilter(String name, String filter);
}