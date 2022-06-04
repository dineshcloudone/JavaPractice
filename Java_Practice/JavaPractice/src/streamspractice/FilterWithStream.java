package streamspractice;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.*;

class ProductStream{
	
	int id;
	String name;
	float price;
	
	public ProductStream(int id,String name,float price) {
		this.id=id;
		this.name=name;
		this.price=price;
	}
		
}

public class FilterWithStream {
	
	static List<ProductStream> productlist=new ArrayList<ProductStream>();
	
	static void filterUsingStream() {
		
		List<Float> productpricelist2=productlist.stream()
				.filter(p->p.price>30000)
				.map(p->p.price)
				.collect(Collectors.toList());
		
		System.out.println(productpricelist2);
		
		List<Float> productpricelist3=productlist.stream()
				.filter(p->p.price>30000)
				.map(p->p.price)
				.collect(Collectors.toList());
		
		System.out.println("Parallel Stream :"+productpricelist3);
	}
	
	static void filterAndIterate() {
		
		productlist.stream().filter(product->product.price==30000)
		.forEach(product->System.out.println(product.name));
	}
	
	static void reduceMethod() {
				Float totalprice=productlist.stream().map(p->p.price)
				.reduce(0.0f, Float::sum);
		System.out.println("total price calculated using stream : "+totalprice);
		
	}
	
	static  void sumUsingCollectors() {
		
		double f=productlist.stream().collect(Collectors.summingDouble(p->p.price));
		System.out.println(f);
	}
	
	static void findMaxAndMinPrice() {
		
		ProductStream maxp=productlist.stream().max((p1,p2)->p1.price>p2.price?1:-1).get();
		
		System.out.println("Maximum Price of the product :"+ maxp.price);
		
		
		
		ProductStream minp=productlist.stream().min((p1,p2)->p1.price>p2.price?1:-1).get();
		System.out.println("Minimum price of the product :"+ minp.price);
	}
	
	static void usingGet() {
		
		List<String> s=productlist.stream().filter(p->p.name.equals("Apple Laptop")).map(p->p.name).collect(Collectors.toList());
		
		s.forEach(x->System.out.println("Laptop name " +x));
	}
			
	public static void main(String[] args) {
				
		productlist.add(new ProductStream(1,"HP Laptop",25000f));
		productlist.add(new ProductStream(2,"Dell Laptop",30000f));  
		productlist.add(new ProductStream(3,"Lenevo Laptop",28000f));  
		productlist.add(new ProductStream(4,"Sony Laptop",28000f));  
		productlist.add(new ProductStream(5,"Apple Laptop",90000f));
		
		Predicate<ProductStream> s=p->p.name.equals("HP Laptop");		
		
		List<ProductStream>p3=productlist.stream().filter(p->p.name.equals("Apple Laptop")).collect(Collectors.toList());			
		
		ProductStream ps=productlist.stream().filter(p->p.name.equals("Apple Laptop"))
		.reduce((a, b) -> {
            throw new IllegalStateException("Multiple elements: " + a + ", " + b);
        }).get();
		
		System.out.println("Product Stream Value :"+ps.name);		
		
		//filterUsingStream();
		//filterAndIterate();
		//reduceMethod();
		//sumUsingCollectors();
		//findMaxAndMinPrice();
		//usingGet();		
	}

}
