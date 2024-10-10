package stepdefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class Login extends Base {
	
	WebDriver driver;
	LandingPage landingpage;
	LoginPage loginpage;
	AccountPage accountPage;
	
	@Given("^Open any browser$")
	public void Open_any_browser() throws IOException {
		
		driver = initializeDriver();
	}
	
	@And("^Navigate to Login page$")
	public void Navigate_to_Login_page() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		landingpage = new LandingPage(driver);
		landingpage.myAccountDropDown().click();
		landingpage.abc().click();
		Thread.sleep(3000);
	}
	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void User_enters_username_as_something_and_password_as_something_into_the_fields(String username, String password){
		loginpage = new LoginPage(driver);
		loginpage.emailField().sendKeys(username);
		loginpage.passwordField().sendKeys(password);
		
	}
	
	@And("^User Clicks on Login button$")
	public void User_Clicks_on_Login_button() {
		loginpage.loginbutton().click();
	}
	@Then("^Verify user is able to successfully login$")
	public void Verify_user_is_able_to_successfully_login() {
		
		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.accountEditPage().isDisplayed());
	}
	@After
	public void closeBrowser() {
		driver.close();
	}
}
