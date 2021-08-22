package StepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.*;
import mainPackage.baseClass;

public class signIn extends baseClass{
	
	private void isSignInScreenOpen() {
		try {
			
			//Check the sign in screen open through the link
			String currentURL = driver.getCurrentUrl();
			assertTrue(currentURL.contains("https://accounts.google.com/signin"), "The Sign in screen is not opened");
			
			//Check if the email field is there in the screen
			WebElement email = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]"
					+ "/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
			assertTrue(email.getAttribute("Type").equals("email"), "The Sign in screen is not opened");
		}
		catch (NoSuchElementException e) {
			assertTrue(false, "The Sign in screen is not opened");
		}
	}
	
	private void isSignInPasswordScreenOpen() throws Throwable{
		
		//wait for the page to load
		TimeUnit.SECONDS.sleep(5);
		
		try {
			//Check the sign in screen open through the link
			String currentURL = driver.getCurrentUrl();
			assertTrue(currentURL.contains("https://accounts.google.com/signin"), "The Sign in screen is not opened");
			
			//Check if the email field is there in the screen
			WebElement password = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]"
					+ "/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
			assertTrue(password.getAttribute("Type").equals("password"), "The Sign in screen is not opened");
		}
		catch (NoSuchElementException e) {
			assertTrue(false, "The Sign in screen is not opened");
		}
	}
	
	@Given("^The user opens the sign in screen$")
	public void the_user_opens_the_sign_in_screen() throws Throwable {
		OpenChromeBrowser(); 
		driver.get(googleURL);
		try {
			WebElement signInButton = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]"));
			signInButton.click();
		}
		catch(NoSuchElementException e) {
			assertTrue(false, "The Sign in screen is not opened after accessing the link");
		}
	}

	@When("^the user enters invalid email$")
	public void the_user_enters_invalid_email() throws Throwable {
		isSignInScreenOpen();
		
		//Type an invalid email in the email field
		WebElement email = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]"
				+ "/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		email.sendKeys("Invalid.email@test.com");
		email.sendKeys(Keys.RETURN);
	}

	@Then("^the system should blocks the user from sign in from \"([^\"]*)\"$")
	public void the_system_should_blocks_the_user_from_sign_in_from(String fromScreen) throws Throwable {
		
		if(fromScreen.equals("email")) {
			//Checks if the email navigated to any screen other the sign in screen or not
			isSignInScreenOpen();
			
			//wait for the page to load
			TimeUnit.MILLISECONDS.sleep(5000);
			
			//Checks if the system fired a toast informing the user that the email is invalid
			WebElement invalidEmailToast = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]"
					+ "/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]"));
			assertTrue(invalidEmailToast.getText().equals("Couldn’t find your Google Account"), "The system didn't display any invalid email "
					+ "toast");
		}
		else {
			//Checks if the password navigated to any screen other the sign in password screen or not
			isSignInPasswordScreenOpen();
			
			try {
				//Checks if the system fired a toast informing the user that the password is invalid
				WebElement invalidPasswordToast = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]"
						+ "/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/span[1]"));
				assertTrue(invalidPasswordToast.getText().contains("Wrong password"), "The system didn't display any invalid password"
						+ " toast");
			}
			catch(NoSuchElementException e) {
				assertTrue(false, "invalid email The toast did not appear");
			}
		}
		//Quit the driver
		driver.close();
		driver.quit();
	}

	@When("^the user enters valid email$")
	public void the_user_enters_valid_email() throws Throwable {
		isSignInScreenOpen();
		
		//Type a valid email in the email field
		WebElement email = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]"
				+ "/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		email.sendKeys(googleAccountUser);
		email.sendKeys(Keys.RETURN);
	}
	
	@When("^the user enters invalid password$")
	public void the_user_enters_invalid_password() throws Throwable {
		isSignInPasswordScreenOpen();
		
		//Type an invalid password to the password field
		WebElement password = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]"
				+ "/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		password.sendKeys("InvalidPassword");
		password.sendKeys(Keys.RETURN);
	}

	@When("^the user enters valid password$")
	public void the_user_enters_valid_password() throws Throwable {
		isSignInPasswordScreenOpen();
		
		//Type a valid password to the password field
		WebElement password = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]"
				+ "/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		password.sendKeys(googleAccountPass);
		password.sendKeys(Keys.RETURN);
	}

	@Then("^the user should sign in successfully$")
	public void the_user_should_sign_in_successfully() throws Throwable {
		
		//wait for the page to load
		TimeUnit.SECONDS.sleep(5);
		
		//check if the search screen opens
		try{
			//This section is to check if the page is opened and the search buttons are there in the screen
			WebElement googleSearchButton = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[3]/center[1]/input[1]"));
			assertEquals(googleSearchButton.getAttribute("Value"), "Google Search", "The Search screen is not opened after sign in");
			
			WebElement iFeelLuckyButton = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[3]/center[1]/input[2]"));
			assertEquals(iFeelLuckyButton.getAttribute("Value"), "I'm Feeling Lucky", "The Search screen is not opened after sign in");
			
			//This section is to check if the search bar is there in the screen
			WebElement searchBar = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
			assertEquals(searchBar.getAttribute("Class"), "gLFyf gsfi", "The Search screen is not opened after sign in");
		}
		catch(NoSuchElementException e) {
			assertTrue(false, "The Search screen is not opened after accessing the link");
		}
		
		try {
			WebElement signInButton = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]"));
			assertFalse(signInButton.getAttribute("Class").contains("gb_4 gb_5 gb_ae gb_4c"), "The user is not signed in");
		}
		catch (NoSuchElementException e) {
			
		}
		
		//Quit the driver
		driver.close();
		driver.quit();
	}

}
