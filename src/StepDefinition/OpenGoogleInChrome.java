package StepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.*;
import mainPackage.baseClass;

public class OpenGoogleInChrome extends baseClass{
	
	@Given("^The chrome browser is open$")
	public void the_chrome_browser_is_open() throws Throwable {
	    OpenChromeBrowser(); 
	}

	@When("^The user searches for the link$")
	public void the_user_searches_for_the_link() throws Throwable {
	    driver.get(googleURL);
	}

	@Then("^The google search screen should opens from \"([^\"]*)\"$")
	public void the_google_search_screen_should_opens_from(String fromScreen) throws Throwable {
		try{
			//This section is to check if the page is opened and the search buttons are there in the screen
			WebElement googleSearchButton = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[3]/center[1]/input[1]"));
			assertEquals(googleSearchButton.getAttribute("Value"), "Google Search", "The Search screen is not opened after accessing the link");
			
			WebElement iFeelLuckyButton = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[3]/center[1]/input[2]"));
			assertEquals(iFeelLuckyButton.getAttribute("Value"), "I'm Feeling Lucky", "The Search screen is not opened after accessing the link");
			
			//This section is to check if the search bar is there in the screen
			WebElement searchBar = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
			assertEquals(searchBar.getAttribute("Class"), "gLFyf gsfi", "The Search screen is not opened after accessing the link");
		}
		catch(NoSuchElementException e) {
			assertTrue(false, "The Search screen is not opened after accessing the link");
		}
		
		if(fromScreen.equals("Open Google Search Page")) {
			//Quit the driver
			driver.close();
			driver.quit();
		}
	}
	
	@Then("^The footer of the page should contains the country the user opens from$")
	public void the_footer_of_the_page_should_contains_the_country_the_user_opens_from() throws Throwable {
		try {
			WebElement country = driver.findElement(By.xpath("//body[1]/div[1]/div[5]/div[1]"));
			assertEquals(country.getText(), "Egypt", "The Country shown on the search screen is not correct");
		}
		catch(NoSuchElementException e) {
			assertTrue(false, "The Search screen is not opened after accessing the link");
		}
		
		//Quit the driver
		driver.close();
		driver.quit();
	}
}
