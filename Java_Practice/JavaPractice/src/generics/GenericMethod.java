package generics;

public class GenericMethod {
	
	private <E> void printArray(E[] elements){
		for(E element:elements) {
			System.out.println("Elements in the Array :"+element);
		}
	}
	
	public static void main(String[] args) {
		
		//Integer[] intArray= {1,2,3,4};
		Integer[] intArray= {1,2,3,4};
		Character[] charArray= {'a','b','c','d'};

	GenericMethod gm=new GenericMethod();
	gm.printArray(intArray);
	gm.printArray(charArray);
	
	}

}
