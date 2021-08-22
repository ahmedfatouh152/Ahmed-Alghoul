Feature: Sign in to google

Scenario: Enter invalid email and invalid password
Given The user opens the sign in screen
When the user enters invalid email
Then the system should blocks the user from sign in from "email"

Scenario: Enter valid email and invalid password
Given The user opens the sign in screen
When the user enters valid email 
And the user enters invalid password
Then the system should blocks the user from sign in from "password"

Scenario: Enter valid email and valid password
Given The user opens the sign in screen
When the user enters valid email 
And the user enters valid password
Then the user should sign in successfully