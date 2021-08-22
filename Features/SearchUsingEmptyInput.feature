Feature: Search using empty input

Scenario: Search for Nothing
Given Opening the google search page
When The user searches for nothing
Then The system should do nothing

Scenario: Search for Nothing after clearing the search bar
Given Opening the google search page
When The user writes keyword in the search bar
And Clear the search bar then search
Then The system should do nothing