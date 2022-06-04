package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UberCarBookingSteps {
	
	@Given("user wants to select car type {string} from uber app")
	public void user_wants_to_select_car_type_from_uber_app(String carType) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		System.out.println("Step 1"+" "+carType);
	}

	@When("user selects car {string} and pick up point {string} and drop location {string}")
	public void user_selects_car_and_pick_up_point_and_drop_location(String carType, String pickUpLocation, String dropLocation) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		System.out.println(" Step 2 " +carType+ " "+dropLocation+ " "+ pickUpLocation);
	}

	@Then("Driver starts the journey")
	public void driver_starts_the_journey() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		System.out.println(" Step 3 ");
	}

	@Then("Driver ends the journey")
	public void driver_ends_the_journey() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		System.out.println(" Step 4 ");
	}

	@Then("User pay {int} USD")
	public void user_pay_usd(Integer price) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		System.out.println(" Step 5 ");
	}

}
