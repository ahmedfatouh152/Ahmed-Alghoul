package StepDefinition;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.*;
import mainPackage.ResultScreen;
import mainPackage.baseClass;

public class searchUsingOneWord extends baseClass{
	
	@Given("^The user opens the google search page$")
	public void the_user_opens_the_google_search_page() throws Throwable {
	    OpenChromeBrowser();
	    driver.get(googleURL);
	}

	@When("^The user searches for IOTblue$")
	public void the_user_searches_for_IOTblue() throws Throwable {
		//Search for keywords using google engine
		googleSearch("IOTblue");
	}
	
	@Then("^The results screen should open with IOTblue relevant results from \"([^\"]*)\"$")
	public void the_results_screen_should_open_with_IOTblue_relevant_results_from(String fromScreen) throws Throwable {
	
		//This call is to get all the results in the results screen
		ResultScreen resultScreen = getAllResults();
		
		//This call is to check if the output results are relevant to the search keyword
		isResultsRelevant(resultScreen, "IOTblue IOT smart solution technology internet of things IOT");
		
		if(fromScreen.equals("Search for only one word")) {
			//Quit the driver
			driver.close();
			driver.quit();
		}
		
	}

	@When("^The user searches for IOT$")
	public void the_user_searches_for_IOT() throws Throwable {
		//Search for keywords using google engine
		googleSearch("IOT");
	}

	@Then("^The results screen should open with IOT relevant results$")
	public void the_results_screen_should_open_with_IOT_relevant_results() throws Throwable {

		//This call is to get all the results in the results screen
		ResultScreen resultScreen = getAllResults();
		
		//This call is to check if the output results are relevant to the search keyword
		isResultsRelevant(resultScreen, "IOT Internet of things smart soltions IOT");
		
		//Quit the driver
		driver.close();
		driver.quit();
	}
	
	@When("^The user searches for IOT from the results screen$")
	public void the_user_searches_for_IOT_from_the_results_screen() throws Throwable {
		try {
			TimeUnit.SECONDS.sleep(2);
			WebElement searchBar = driver.findElement(By.xpath("//body[1]/div[4]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]"));
			searchBar.clear();
			searchBar.sendKeys("IOT");
			searchBar.sendKeys(Keys.RETURN);
		}
		catch(NoSuchElementException e) {
			   assertTrue(false, "The google search results screen is not open");
		}
	}

}
