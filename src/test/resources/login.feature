Feature: User Login
  As a website user
  I want to login to the system
  So that I can access my personal information

  Scenario: Login with correct credentials
    Given user is on the login page
    When user enters username "testuser"
    And user enters password "password123"
    And user clicks the login button
    Then user should be logged in successfully
    And user should see welcome message "Welcome, testuser!"

  Scenario: Login with wrong password
    Given user is on the login page
    When user enters username "testuser"
    And user enters password "wrongpassword"
    And user clicks the login button
    Then user should see error message "Invalid username or password"

