package step_definitions.web_steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import step_definitions.PageAccessStepsBase;
import utils.CSVDataHandler;
import utils.XMLDataHandler;

/**
 * Step definitions for the login and registration functionality.
 */
public class LoginRegisterPageSteps extends PageAccessStepsBase {

   /**
    * Default Constructor
    */
   public LoginRegisterPageSteps() {

   }

   /**
    * Registers a new user.
    */
   @When("I register a new user")
   public void i_register_a_new_user() {
      homePage().clickSignupLogin();
      String name = XMLDataHandler.getLastElementValueWithTag("name");
      i_enter_a_user_name(name);
      String emailAddress = XMLDataHandler.getLastElementValueWithTag("emailAddress");
      i_enter_the_email_address(emailAddress);
      i_click_signup();
      accountInfoPage().enterAccountInfo();
   }

   /**
    * Registers a new user with csv details
    */
   @When("I register a new user using csv details")
   public void i_register_a_new_user_using_csv_details() {
      homePage().clickSignupLogin();
      String name = CSVDataHandler.getValueForCSVKey("name");
      i_enter_a_user_name(name);
      String emailAddress = CSVDataHandler.getValueForCSVKey("emailAddress");
      i_enter_the_email_address(emailAddress);
      i_click_signup();
   }

   /**
    * Enters the user name provided
    * 
    * @param name The user name
    */
   @When("I enter a user name {string}")
   public void i_enter_a_user_name(String name) {
      signUpLoginPage().enterName(name);
   }

   /**
    * Enters the users email address provided
    * 
    * @param emailAddress The users email address
    */
   @When("I enter the email address {string}")
   public void i_enter_the_email_address(String emailAddress) {
      signUpLoginPage().enterEmail(emailAddress);
   }

   /**
    * Clicks Sign Up
    */
   @And("I click signup")
   public void i_click_signup() {
      signUpLoginPage().clickSignUp();
   }

   /**
    * Verifies a new user is created
    */
   @Then("a new user is created")
   public void a_new_user_is_created() {
      // To be added if desired
   }

   /**
    * Verifies a new user page is displayed
    */
   @Then("I will see the new user page")
   public void i_will_see_the_new_user_page() {
      // To be added if desired
   }

   /**
    * Verifies the login register page is displayed
    */
   @Then("I should see the Login Register page")
   public void i_should_see_the_login_register_page() {
      // To be added if desired
   }
}
