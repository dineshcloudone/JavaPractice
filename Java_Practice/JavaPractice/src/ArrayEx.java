import java.util.Arrays;

//Arrays : https://www.programiz.com/java-programming/arrays

//Access specifiers for classes and interfaces : https://www.geeksforgeeks.org/access-specifiers-for-classes-or-interfaces-in-java/

//Equilibrium Index : https://www.geeksforgeeks.org/equilibrium-index-of-an-array/


public class ArrayEx {
	
	void charArray_toString() {
		
		char[] ch=new char[] {'d','b','c','a'};
		
		Arrays.sort(ch);
		
		String s= String.valueOf(ch);
		
		//String s= new String(ch);
		
		System.out.println("converted to string using valueOf : "+s);
		
	}
	
	private void declare_Instantiate_Initilize() {
		
		int a[]=new int[5];//declaration and instantiation
		
		a[0]=10;//initialization  
		a[1]=20;  
		a[2]=70;  
		a[3]=40;  
		a[4]=50;  
		
		//traversing array  
		for(int i=0;i<a.length;i++)//length is the property of array  
		System.out.println(a[i]);  
		
		System.out.println("Array size : "+a.length);
		System.out.println("Array : "+Arrays.toString(a));
		
	}
		
	private void copyArrayEx1() {
		
		char[] array1= new char[]{'a','b','c','d','e','f'};
		char[] array2=new char[array1.length];
		
		//System.arraycopy(n1,0,n2,0,n1.length);
		System.arraycopy(array1, 0, array2, 0, array1.length);
		
		System.out.println("Array output :"+Arrays.toString(array2));
		System.out.println("String output :"+new String(array2));		
	}
		
	private void copyArrayEx2() {
		
		int[] i= {1,2,3,4};
		int[] j=i;
		
		i[1]=22;
		
		for(int x:i) {
			System.out.println(x);
		}
		
		for(int x:j) {
			System.out.println(x);
		}
		
	}
	
	private void copyArrayEx3() {
		
		int[] i= {1,2,3,4,5};
		int[] j=new int[i.length];
		
		for(int x=0;x<i.length;++x) {
			j[x]=i[x];
		}
		
		System.out.println(Arrays.toString(j));
		
		//spliterator,parallelsort,sort
	}
	
	//we can clone() method since Array class implements Cloneable interface
	private void copyarray_clone() {
		
		int[] a= {1,2,3,4,5,6};
		int[] b=a.clone();
		
		a[0]=11;
		
		System.out.println("array a "+Arrays.toString(a));
		System.out.println("array b "+Arrays.toString(b));
	}
	
	private void multi_dimensional_array() {
		
		int arr[][] = {{1,2,3},{4,5,6},{7,8,9}};
		
		System.out.println("Using Single line "+Arrays.deepToString(arr));
		
		for(int i=0;i<arr.length;++i) {
			for(int j=0;j<arr[i].length;++j) {
				System.out.print(arr[i][j]+" ");
			}
		}
	}
	
	private void jagged_Array() {
		
		int[][] a=new int[3][];
	
		a[0]=new int[3];
		a[1]=new int[4];
		a[2]=new int[3];
		
		int count=1;
		
		for(int i=0;i<a.length;++i) {
			for(int j=0;j<a[i].length;++j) {
				
				a[i][j]=count;
				++count;
			}
		}
		System.out.println(Arrays.deepToString(a));
	}
	
	private void getArrayClass() {
		int[] array1= {1,2,3};
		
		Class c=array1.getClass();
		
		System.out.println("class name :"+c.getName());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayEx ae=new ArrayEx();
		
		//ae.copyArrayEx1();
		//ae.getArrayClass();
		//ae.copyArrayEx2();
		//ae.copyArrayEx3();
		//ae.multi_dimensional_array();
		//ae.copyarray_clone();
		//ae.jagged_Array();
		
		//ae.charArray_toString();
		ae.declare_Instantiate_Initilize();
	}

	
}
