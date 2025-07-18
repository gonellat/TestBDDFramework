package step_definitions.xml_steps;

import step_definitions.PageAccessStepsBase;
import utils.XMLDataHandler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class XmlSteps extends PageAccessStepsBase {

   @When("I validate the {string} against xsd {string}")
   public void i_validate_it_against_xsd(String xml, String xsd) {
      XMLDataHandler.validateXML(xml,xsd);
   }
   
   @Then("I get a valid response")
   public void i_get_a_valid_response() {
      assertThat("The XML is not valid:" + XMLDataHandler.getXmlError(), XMLDataHandler.isXmlIsValid(), is(true));
   }
}
