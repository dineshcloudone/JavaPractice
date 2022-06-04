@All
Feature: Login page feature

  Scenario: Login page title
    Given user is on login page
    When user gets the title of the page
    Then page title should be "OrangeHRM"

  Scenario: Forgot Password link
    Given user is on login page
    Then forgot your password link should be displayed

  Scenario: Login with invalid correct credentials
    Given user is on login page
    When user enters username "dinesh.23040@gmail.com"
    And user enters password "Oracle*Oracle*1"
    And user clicks on Login button
    Then user gets "Invalid credentials" message

  Scenario: Login with valid credentials
    Given user is on login page
    When user enters username "Admin"
    And user enters password "admin123"
    And user clicks on Login button
    Then user is on namrata home page
    Then user log out the application
    Then page title should be "OrangeHRM"
