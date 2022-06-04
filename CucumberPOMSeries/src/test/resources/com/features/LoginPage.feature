Feature: Login page feature

Scenario: Login page title
Given user is on login page
When user gets the title of the page
Then page title should be " Oracle Login - Single Sign On"

Scenario: Forgot Password link
Given user is on login page
Then forgot your password link should be displayed

Scenario: Login with invalid correct credentials
Given user is on login page
When user enters username "dinesh.23040@gmail.com"
And user enters password "Oracle*123*"
And user clicks on Login button
#Then user gets the title of the home page
And page title should be "Oracle Live SQL"

