package step_definitions.web_steps;

import java.io.File;
import java.io.IOException;

import constants.FilePathConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import step_definitions.PageAccessStepsBase;
import step_definitions.xml_steps.XmlSteps;
import utils.CSVDataHandler;
import utils.XMLDataHandler;

public class HomePageSteps extends PageAccessStepsBase {

   XmlSteps xmlSteps = new XmlSteps();

   @Given("I open the Automation Exercise website")
   public void i_open_the_automation_exercise_website() {
      homePage().openPage();
   }

   @Given("I've successfully opened the {string} website")
   public void ive_successfully_opened_the_automation_exercise_website(String tabTitle) {
      homePage().openPage();
      homePage().assertPageTitle(tabTitle);
   }

   @Then("The Home Page tab title should contain {string}")
   public void the_home_page_tab_title_should_contain(String tabTitle) {
      homePage().assertPageTitle(tabTitle);
   }

   @Then("The Products Page Tab title should contain {string}")
   public void the_products_page_tab_title_should_contain(String tabTitle) {
      productsPage().assertPageTitle(tabTitle);
   }

   @When("I click the Products link") 
   public void i_click_the_products_link() {
      homePage().clickProducts();
   }

   @When("I click the SignUp Login link") 
   public void i_click_the_signup_login_link() {
      homePage().clickSignupLogin();
   }

   @Given("I have read in the test data {string} for the scenario")
   public void i_have_read_the_test_data_for_the_sceanrio(String dataFile) throws IOException {
      XMLDataHandler.validateXML(dataFile, "scenariodatavalidation.xsd");
      xmlSteps.i_get_a_valid_response();
      XMLDataHandler.storeXML(dataFile);
   }

   @Given("I have read in the csv test data {string} for the scenario")
   public void i_have_read_in_csv_the_test_data_for_the_sceanrio(String dataFile) throws IOException {
      CSVDataHandler.readCSVFile(System.getProperty(FilePathConstants.USER_DIR).concat(File.separator).concat("src").concat(File.separator).concat("test").concat(File.separator).concat("resources").concat(File.separator).concat("data").concat(File.separator).concat(dataFile), ",");
   }
}
