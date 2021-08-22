package StepDefinition;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.*;
import mainPackage.baseClass;

public class imageSearch extends baseClass{
	
	private List<String> imagesSearchResultsTitles = new ArrayList<String>();
	private List<String> imagesFilterResultsTitles = new ArrayList<String>();
	
	@Given("^The user opens the image search page$")
	public void the_user_opens_the_image_search_page() throws Throwable {
		OpenChromeBrowser(); 
		driver.get(googleURL + "imghp");
	}

	@When("^The user searches for keyword IOTblue$")
	public void the_user_searches_for_keyword_IOTblue() throws Throwable {
		try {
			TimeUnit.SECONDS.sleep(5);
			WebElement searchBar = driver.findElement(By.xpath("//body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
			searchBar.sendKeys("IOTblue");
			searchBar.sendKeys(Keys.RETURN);
		}
		catch(NoSuchElementException e) {
			assertTrue(false, "The Image Search screen is not opened after accessing the link");
		}
	}

	@Then("^All results should be relevant to IOTblue from \"([^\"]*)\"$")
	public void all_results_should_be_relevant_to_IOTblue_from(String fromScreen) throws Throwable {
		List<WebElement> imagesSearchResults = driver.findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"));
		assertTrue(isImagesRelevant(imagesSearchResults, "IOTblue"), "The images search results are not relevant");
		
		for(WebElement searchResult : imagesSearchResults) {
			imagesSearchResultsTitles.add(searchResult.getAttribute("Title"));
		}
		
		if(fromScreen.equals("check relevant")) {
			//Quit the driver
			driver.close();
			driver.quit();
		}
	}

	@Given("^Accessing the search page$")
	public void accessing_the_search_page() throws Throwable {
		OpenChromeBrowser();
	    driver.get(googleURL);
	}

	@When("^the user searchs with IOTblue as a keyword$")
	public void the_user_searchs_with_IOTblue_as_a_keyword() throws Throwable {
		//Search for keywords using google engine
		googleSearch("IOTblue");
	}

	@When("^the user filter the data with the images tab$")
	public void the_user_filter_the_data_with_the_images_tab() throws Throwable {
		//The user filter the results with Images through the nav bar
		try {
			WebElement navBar = driver.findElement(By.xpath("//body[1]/div[7]/div[1]/div[3]/div[1]/div[1]/div[1]"));
			WebElement imagesFilterTab = navBar.findElement(By.xpath("//a[text()=\"Images\"]"));
			imagesFilterTab.click();
			TimeUnit.SECONDS.sleep(1);
			List<WebElement> imagesFilterResults = driver.findElements(By.xpath("//a[@class='VFACy kGQAp sMi44c lNHeqe WGvvNb']"));
			for(WebElement filterResult : imagesFilterResults) {
				imagesFilterResultsTitles.add(filterResult.getAttribute("Title"));
			}
		}
		catch(NoSuchElementException e) {
			assertTrue(false, "the result screen is not open after the search");
		}
		TimeUnit.SECONDS.sleep(5);
	}

	@When("^user opens the image search page$")
	public void user_opens_the_image_search_page() throws Throwable {
		driver.get(googleURL + "imghp");
	}

	@Then("^Both results should be identical$")
	public void both_results_should_be_identical() throws Throwable {
		boolean isSameResults;
		double similarResults = imagesFilterResultsTitles.size()<imagesSearchResultsTitles.size()? imagesFilterResultsTitles.size() 
				: imagesFilterResultsTitles.size();
		double numberOfResults = similarResults;
		for(int index = 0; index<imagesFilterResultsTitles.size() && index<imagesSearchResultsTitles.size(); index++) {
			String filteredResultTitle = imagesFilterResultsTitles.get(index);
			if(!imagesSearchResultsTitles.contains(filteredResultTitle)) {
				similarResults--;
			}
		}
		//Quit the Driver
		driver.close();
		driver.quit();
		
		double similarResultsRatio = similarResults/ numberOfResults;
		if(similarResultsRatio >= 0.75) {
			isSameResults = true;
		}
		else {
			isSameResults = false;
		}
		
		assertTrue(isSameResults, "Not the same results from the search and the filter");
	}

}
