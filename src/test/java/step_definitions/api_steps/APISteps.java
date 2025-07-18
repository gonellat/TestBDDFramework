package step_definitions.api_steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import step_definitions.PageAccessStepsBase;
import utils.Helpers.Products;

/**
 * Step definitions for testing product-related API endpoints.
 */
public class APISteps extends PageAccessStepsBase {

   /**
    * Default Constructor
    */
   public APISteps() {

   }

   // ************//
   // POST STEPS //
   // ************//

   // ***********//
   // GET STEPS //
   // ***********//

   /** Calls the GET all products API. */
   @When("I get a list of all products")
   public void i_get_a_list_of_all_products() {
      productsApi().getAllProducts();
   }

   // **************//
   // VERIFY STEPS //
   // **************//

   /** Asserts the product list contains "Blue Top". */
   @Then("it will contain a blue top")
   public void it_will_contain_a_blue_top() {
      productsApi().it_will_contain_a_blue_top();
   }

   // **************//
   // OTHER STEPS //
   // **************//

   /** Validates successful status code from API. */
   @Then("I should receive a success status code for the products api")
   public void iShouldReceiveASuccessStatusCode() {
      productsApi().iShouldReceiveASuccessStatusCode(Products.getTheLatestProductResponse());

   }
}