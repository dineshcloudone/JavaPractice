package AmazonImplementation;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	private String product;
	int price;
	
	public Product(String product, int price) {
		
		this.product=product;
		this.price=price;
	}
	
	public String getProductName() {
		
		return product;
	}
	
	public void setProductName(String product) {
	
		this.product=product;
		
	}
	
	public int getPrice() {
	
			return price;
		}
	
	public void setPrice(int price) {
		this.price=price;
	}
	
	public List<String> getProductList(){
		
		List<String> l=new ArrayList<>();
		l.add("Apple Mcbook");
		l.add("Apple 2");
		l.add("Apple 3");
		
		return l;
		
	}
	
}
