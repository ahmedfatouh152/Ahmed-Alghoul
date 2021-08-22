Feature: Check the country in the search screen footer

Scenario: Check the country in the footer
Given The chrome browser is open
When The user searches for the link 
Then The google search screen should opens from "Check the country in the search screen footer"
Then The footer of the page should contains the country the user opens from