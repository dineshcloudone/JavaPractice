package stepdefinitions;

import org.testng.Assert;

import com.pages.LoginPage;
import com.qa.factory.DriverFactory;

import io.cucumber.java.en.*;

public class LoginPageHRMSteps {

	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private static String title;

	@Given("user is on login page")
	public void user_is_on_login_page() {
		DriverFactory.getDriver().get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {

		title = loginPage.getLoginPageTitle();
		System.out.println("login page title is : " + title);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) {
		title = loginPage.getLoginPageTitle();
		Assert.assertTrue(title.contains(expectedTitleName));
	}

	@Then("forgot your password link should be displayed")
	public void forgot_your_password_link_should_be_displayed() {

		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@When("user enters username {string}")
	public void user_enters_username(String username) {
		loginPage.enterUserName(username);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) {
		loginPage.enterPassword(password);
	}

	@When("user clicks on Login button")
	public void user_clicks_on_login_button() {
		loginPage.clickOnLogin();
	}

	@Then("user gets {string} message")
	public void user_gets_invalid_credentials_message(String string) {
		Assert.assertTrue(loginPage.msgInvalidCredentials());
	}

	@Then("user log out the application")
	public void user_log_out_the_application() throws InterruptedException {
		loginPage.logOutApplication();
	}

	@Then("user is on namrata home page")
	public void user_is_on_namrata_home_page() {

	}

}
