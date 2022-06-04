package streamspractice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Product3{
	int id;
	String name;
	float price;
	
	public Product3(int id,String name,float price) {
		this.id=id;
		this.name=name;
		this.price=price;
	}		
}

public class AllBasicEx {
	
	private static void convertListtoSet() {
		
		List<String> l1=new ArrayList<>();
		l1.add("Dinesh");
		l1.add("Dinesh");
		l1.add("Kumar");
		l1.add("Gurram");
		
		l1.forEach(x->System.out.println("l1 value :"+x));
		
		Set<String> s1=l1.stream().collect(Collectors.toSet());
		
		s1.forEach(x->System.out.println("s1 value :"+x));
		
		Set<String> s2=new HashSet<String>(l1);
		
		s2.forEach(x->System.out.println("s2 value :"+x));
		
	}	
	
	public static void main(String[] args) {
		
		List<Product> productlist=new ArrayList<Product>();
		
		productlist.add(new Product(1,"HP Laptop",25000f));
		productlist.add(new Product(2,"Dell Laptop",30000f));  
		productlist.add(new Product(3,"Lenevo Laptop",28000f));  
		productlist.add(new Product(4,"Sony Laptop",28000f));  
		productlist.add(new Product(5,"Apple Laptop",90000f));
		
		//Using reduce
		
		Float totalPrice=productlist.stream()
					.map(p->p.price)
					.reduce(0.0f, Float::sum);
		System.out.println("Total Price 1 :"+totalPrice);
		
		float totalPrice2=productlist.stream()
				.map(p->p.price)
				.reduce(0.0f,(sum,price)-> sum+price);
		System.out.println("Total Price 2 :"+totalPrice2);
			
		//Using Collectors
	
		double totaldouble=productlist.stream()
					.collect(Collectors.summingDouble(p->p.price));
		
		System.out.println("Collectors :" + totaldouble);
		
		//max price
		Product maxprice=productlist.stream()
					.max((product1,product2) -> product1.price>product2.price?1:-1).get();		
		System.out.println("maxprice :" +maxprice.price);
					
		//min price
		Product minprice=productlist.stream().min((product1,product2)->product1.price<product1.price?1:-1).get();
		System.out.println("min price :"+minprice.price);
		
		//count
		long count=productlist.stream().filter(p->p.price<30000).count();
		System.out.println("count of lessthan 30000 : "+ count);
	
		//converting list into Set
		Set<Float> productpriceset=productlist.stream()
					.filter(p->p.price<30000)
					.map(p->p.price)
					.collect(Collectors.toSet());
		
		System.out.println("Values in Set :"+ productpriceset);
		
		//converting to map
		Map<Integer, String> productpricemap=productlist.stream().collect(Collectors.toMap(p->p.id, p->p.name));
				System.out.println("Values in map :"+ productpricemap);
	
		//Method reference
				
				//this code can be used if we write methods getId(), getName() and getPrice() in Product class 
				
				/*List<Float> productprice=productlist.stream()
					.filter(p->p.price<30000)
					.map(Product::getPrice)
					.collect(Collectors.toList());*/
	
	AllBasicEx.convertListtoSet();			
				
	}

}
