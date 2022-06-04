package streamspractice;

import java.util.*;

class Product{
	int id;
	String name;
	float price;
	
	public Product(int id,String name,float price) {
		this.id=id;
		this.name=name;
		this.price=price;
	}
		
}

public class FilterNoStream {

	public static void main(String[] args) {
		List<Product> productlist=new ArrayList<Product>();
		
		productlist.add(new Product(1,"HP Laptop",25000f));
		productlist.add(new Product(2,"Dell Laptop",30000f));  
		productlist.add(new Product(3,"Lenevo Laptop",28000f));  
		productlist.add(new Product(4,"Sony Laptop",28000f));  
		productlist.add(new Product(5,"Apple Laptop",90000f));
		
		List<Float> productPriceList=new ArrayList<Float>();
		
		for(Product p:productlist) {
			if(p.price<30000) {
				productPriceList.add(p.price);
			}
		}
			System.out.println(productPriceList);
	}
}
