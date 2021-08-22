package StepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.*;
import mainPackage.baseClass;

public class searchWithEmptyInput extends baseClass{
	
	@Given("^Opening the google search page$")
	public void opening_the_google_search_page() throws Throwable {
		OpenChromeBrowser();
	    driver.get(googleURL);
	}
	

	@When("^The user searches for nothing$")
	public void the_user_searches_for_nothing() throws Throwable {
		//Search for keywords using google engine
		googleSearch("");
	}

	@Then("^The system should do nothing$")
	public void the_system_should_do_nothing() throws Throwable {
		try{
			//This part checks if the Page URL has changed or not
			String currentURL = driver.getCurrentUrl();
			assertEquals(currentURL, googleURL, "Upon Searching for nothing the system should do nothing");
			
			//This section is to check if the page is opened and the search buttons are there in the screen
			WebElement googleSearchButton = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[3]/center[1]/input[1]"));
			assertEquals(googleSearchButton.getAttribute("Value"), "Google Search", "Upon Searching for nothing the system should do nothing");
			
			WebElement iFeelLuckyButton = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[3]/center[1]/input[2]"));
			assertEquals(iFeelLuckyButton.getAttribute("Value"), "I'm Feeling Lucky", "Upon Searching for nothing the system should do nothing");
			
			//This section is to check if the search bar is there in the screen
			WebElement searchBar = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
			assertEquals(searchBar.getAttribute("Class"), "gLFyf gsfi", "Upon Searching for nothing the system should do nothing");
		}
		catch(NoSuchElementException e) {
			assertTrue(false, "Upon Searching for nothing the system should do nothing");
		}
		
		driver.close();
		driver.quit();
	}

	@When("^The user writes keyword in the search bar$")
	public void the_user_writes_keyword_in_the_search_bar() throws Throwable {
		try {
			   WebElement searchBar = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
			   searchBar.sendKeys("IOTblue Test");
		}
		catch(NoSuchElementException e) {
			   assertTrue(false, "The google search screen is not open");
		}
	}

	@When("^Clear the search bar then search$")
	public void clear_the_search_bar_then_search() throws Throwable {
		try {
			   WebElement searchBar = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
			   searchBar.clear();
			   searchBar.sendKeys(Keys.RETURN);
		}
		catch(NoSuchElementException e) {
			   assertTrue(false, "The google search screen is not open");
		}
	}


}
