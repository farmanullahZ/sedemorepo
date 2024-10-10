package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	Logger log;
	WebDriver driver;
	
	@Test(dataProvider="getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {
	
		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropDown().click();
		log.debug("Clicked on My Account Dropdown in Landing Page");
		landingpage.abc().click();
		log.debug("Clicked on Login option under My Account Dropdown");
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailField().sendKeys(email);
		log.debug("Email Address got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginpage.loginbutton().click();
		log.debug("Clicked On Login Button");
		
		AccountPage accountPage = new AccountPage(driver);
		
		String actualResult="";
		try {
			if(accountPage.accountEditPage().isDisplayed())
			{
				log.debug("User got logged in");
				actualResult="Successful";
			}
		}catch(Exception e) {
			log.debug("User didn't log in");
			actualResult="Failure";
		}
		
		Assert.assertEquals(actualResult, expectedResult);
		//if the assertion not failing then it is passed
		log.info("Login Test got passed");
	}
	
	@BeforeMethod
	public void openApplication() throws IOException {
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver = initializeDriver();
		//log.debug("Browser Got Launched");
		driver.get(prop.getProperty("url"));
		//log.debug("Navigated to application URL");
	}
	
	@AfterMethod
	public void closure()
	{
		driver.close();
		log.debug("Browser got closed");
	}
	
	
	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = {{"ahmad@gmail.com","123456","Successful"},{"dummy@gmail.com","dummy@123","Failure"}};
		return data;
	}
	
}
