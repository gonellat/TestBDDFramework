package step_definitions.xml_steps;

import step_definitions.PageAccessStepsBase;
import utils.XMLDataHandler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for XML validation scenarios.
 */
public class XmlSteps extends PageAccessStepsBase {

   /**
    * Default Constructor
    */
   public XmlSteps() {

   }

   /**
    * Validates that the returned XML matches the XSD.
    *
    * @param xml The XML response string
    * @param xsd The path to the XSD file
    */
   @When("I validate the {string} against xsd {string}")
   public void i_validate_it_against_xsd(String xml, String xsd) {
      XMLDataHandler.validateXML(xml, xsd);
   }

   /**
    * Validates that a valid response is returned
    */
   @Then("I get a valid response")
   public void i_get_a_valid_response() {
      assertThat("The XML is not valid:" + XMLDataHandler.getXmlError(), XMLDataHandler.isXmlIsValid(), is(true));
   }
}
