package stepdefinitions;

import AmazonImplementation.Product;
import AmazonImplementation.Search;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class SearchSteps {

	Product productDetails;
	Search search;
	
	@Given("I have a serach field on amazon page")
	public void i_have_a_serach_field_on_amazon_page() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.print("step 1 : I am on search page");
	}

	@When("^I search for a product \"([^\"]+)\" and price (\\d+)$")
	public void i_search_for_a_product_and_price(String product, Integer price) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.print("step 2 : Search the product with name : " + product + " And Price" + price);
		
		productDetails=new Product(product, price);
	}

	@Then("Product with name {string} should be displayed")
	public void product_with_name_should_be_displayed(String productName) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("step 3 : product " + productName + " is displayed");
		
		search=new Search();
		String s=search.displayProduct(productDetails);
		System.out.println("searched product is: "+s);
		
		
	}
	
	@Then("Order id is {int} and username is {string}")
	public void order_id_is_andd_username_is(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}


}
