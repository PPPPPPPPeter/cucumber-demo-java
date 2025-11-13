package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class LoginSteps {
    private String username;
    private String password;
    private boolean loginSuccessful;
    private String message;

    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        System.out.println("Opening login page");
        loginSuccessful = false;
        message = "";
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        this.username = username;
        System.out.println("Entering username: " + username);
    }

    @And("user enters password {string}")
    public void userEntersPassword(String password) {
        this.password = password;
        System.out.println("Entering password: " + password);
    }

    @And("user clicks the login button")
    public void userClicksTheLoginButton() {
        System.out.println("Clicking login button");
        if ("testuser".equals(username) && "password123".equals(password)) {
            loginSuccessful = true;
            message = "Welcome, " + username + "!";
        } else {
            loginSuccessful = false;
            message = "Invalid username or password";
        }
    }

    @Then("user should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        if (!loginSuccessful) {
            throw new AssertionError("Login failed");
        }
        System.out.println("Login successful");
    }

    @And("user should see welcome message {string}")
    public void userShouldSeeWelcomeMessage(String expectedMessage) {
        if (!message.equals(expectedMessage)) {
            throw new AssertionError("Expected: " + expectedMessage + ", Actual: " + message);
        }
        System.out.println("Seeing message: " + message);
    }

    @Then("user should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedMessage) {
        if (!message.equals(expectedMessage)) {
            throw new AssertionError("Expected: " + expectedMessage + ", Actual: " + message);
        }
        System.out.println("Seeing error message: " + message);
    }


}