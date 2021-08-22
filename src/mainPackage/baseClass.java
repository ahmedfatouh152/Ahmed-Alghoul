package mainPackage;


import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;

public class baseClass {
	
	public WebDriver driver;
	public String googleURL = "https://www.google.com/";
	public String googleAccountUser = "automationtestIOTblue";
	public String googleAccountPass = "Autotest15";
	
	//The function is used to initiate the chrome browser and prevent any pop ups and to work in the background
	public void OpenChromeBrowser(){
		System.setProperty("webdriver.chrome.driver", "BrowserDrivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--lang=en-GB");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}
	
	//This function is to find the search bar and enter the search keyword
	public void googleSearch(String searchText) {
		try {
			   WebElement searchBar = driver.findElement(By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
			   searchBar.sendKeys(searchText);
			   searchBar.sendKeys(Keys.RETURN);
		}
		catch(NoSuchElementException e) {
			   assertTrue(false, "The google search screen is not open");
		}
	}
	
	//The function used to get all the result in the search result screen according to their classes
	public ResultScreen getAllResults() {
		ResultScreen resultScreen = new ResultScreen();
			
		resultScreen.basicSearchResults = driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"));
		resultScreen.peopleAlsoAsk = driver.findElements(By.xpath("//div[@class='cbphWd']"));
		resultScreen.videos = driver.findElements(By.xpath("//div[@class='fJiQld oz3cqf p5AXld']"));
		resultScreen.topStories = driver.findElements(By.xpath("//div[contains(@class, 'nDgy9d')]"));
		resultScreen.relatedSearch = driver.findElements(By.xpath("//div[@class='s75CSd']"));
			
		return resultScreen;
	}
	
	//This function is to check if all results in the results screen are relevant to the search keywords
	public void isResultsRelevant(ResultScreen resultScreen, String keyword) {
		
		SoftAssert softAssert = new SoftAssert();
			
		softAssert.assertTrue(isResultsMatched(resultScreen.basicSearchResults, keyword), "The basic search results contains irrelevant data to '"
				+ keyword.toUpperCase() + "' Search");
		softAssert.assertTrue(isResultsMatched(resultScreen.peopleAlsoAsk, keyword), "The people also asks contains irrelevant data to '"
				+ keyword.toUpperCase() + "' Search");
		softAssert.assertTrue(isResultsMatched(resultScreen.videos, keyword), "The videos contains irrelevant data to '"
				+ keyword.toUpperCase() + "' Search");
		softAssert.assertTrue(isResultsMatched(resultScreen.topStories, keyword), "The top stories contains irrelevant data to '"
				+ keyword.toUpperCase() + "' Search");
		softAssert.assertTrue(isResultsMatched(resultScreen.relatedSearch, keyword), "The realted search contains irrelevant data to '"
				+ keyword.toUpperCase() + "' Search");
			
		softAssert.assertAll();
	}
	
	//this function checks if the results appeared to the user is relevant or not
	private boolean isResultsMatched(List<WebElement> results, String searchKeywords) {
		boolean isRelevant = true;
		String[] keywords = searchKeywords.split(" ");
		for(WebElement result : results) {
			if(!keywordmatch(result, keywords)) {
				isRelevant = false;
				break;
			}
		}
		return isRelevant;
	}
	
	private boolean keywordmatch(WebElement result, String[] searchKeywords) {
		boolean isRelevant = false;
		for(String keyword : searchKeywords) {
			if(result.getAttribute("textContent").toLowerCase().contains(keyword.toLowerCase())) {
				isRelevant = true;
				break;
			}
		}
		return isRelevant;
	}
	
	//This function is to check if the images results are relevant to the search
	public boolean isImagesRelevant(List<WebElement> imagesResults, String keywords) {
		boolean isRelevant;
		double relevantResultsCount = 0;
		double relevantResultsRatio;
		
		//Loops over all results to count the relevant results
		for(WebElement result : imagesResults) {
			if(isImageRelevant(result, keywords)) {
				relevantResultsCount++;
			}
		}
		
		relevantResultsRatio = relevantResultsCount / (double)(imagesResults.size());
		if(relevantResultsRatio >= 0.7) {
			isRelevant = true;
		}
		else {
			isRelevant = false;
		}
		return isRelevant;
	}
	
	//This function is to check if each result is relevant
	private boolean isImageRelevant(WebElement result, String keywords) {
		boolean isRelevant = false;
		String[] keywordsList = keywords.split(" ");
		for(String keyword : keywordsList) {
			if(result.getAttribute("Title").toLowerCase().contains(keyword.toLowerCase())) {
				isRelevant = true;
				break;
			}
		}
		
		return isRelevant;
	}
}
