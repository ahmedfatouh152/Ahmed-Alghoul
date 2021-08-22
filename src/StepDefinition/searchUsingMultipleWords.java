package StepDefinition;

import cucumber.api.java.en.*;
import mainPackage.ResultScreen;
import mainPackage.baseClass;

public class searchUsingMultipleWords extends baseClass{
	
	@Given("^Open the google search page$")
	public void Open_the_google_search_page() throws Throwable {
	    OpenChromeBrowser();
	    driver.get(googleURL);
	}
	
	@When("^The user searches for IOTblue Business$")
	public void the_user_searches_for_IOTblue_Business() throws Throwable {
		//Search for keywords using google engine
		System.out.println(driver.getCurrentUrl());
		googleSearch("IOTblue Business");
	}

	@Then("^The results screen should open with IOTblue Business relevant results$")
	public void the_results_screen_should_open_with_IOTblue_Business_relevant_results() throws Throwable {

		//This call is to get all the results in the results screen
		ResultScreen resultScreen = getAllResults();
		
		//This call is to check if the output results are relevant to the search keyword
		isResultsRelevant(resultScreen, "IOTblue Business IOT Internet of things smart solution technology");
		
		//Quit the driver
		driver.close();
		driver.quit();
	}

	@When("^The user searches for IOT Smart Home$")
	public void the_user_searches_for_IOT_Smart_Home() throws Throwable {
		//Search for keywords using google engine
		googleSearch("IOT Smart home");
	}

	@Then("^The results screen should open with IOT Smart Home relevant results$")
	public void the_results_screen_should_open_with_IOT_Smart_Home_relevant_results() throws Throwable {

		//This call is to get all the results in the results screen
		ResultScreen resultScreen = getAllResults();
		
		//This call is to check if the output results are relevant to the search keyword
		isResultsRelevant(resultScreen, "IOT Smart home internet of things solution technology");
		
		//Quit the driver
		driver.close();
		driver.quit();
	}
	

}
