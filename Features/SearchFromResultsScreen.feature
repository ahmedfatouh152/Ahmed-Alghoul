Feature: Search from the results screen

Scenario: Search for only one word using IOTblue
Given The user opens the google search page
When The user searches for IOTblue
Then The results screen should open with IOTblue relevant results from "Search from the results screen"
When The user searches for IOT from the results screen
Then The results screen should open with IOT relevant results
