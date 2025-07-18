package pages;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;

import utils.FakerUtility;
import utils.TestLoggerHolder;
import utils.XMLDataHandler;

public class AccountInfoPage extends BasePageClass {

   // Locators
   private static final String FIRST_NAME = "css::#first_name";
   private static final String LAST_NAME = "css::#last_name";
   private static final String COMPANY = "css::#company";

   // XML Data Tags
   private static final String FIRST_NAME_TAG = "firstname";
   private static final String LAST_NAME_TAG = "lastname";
   private static final String COMPANY_TAG = "company";

   /**
    * This method enters the first name
    * @param firstName
    */
   public void enterFirstName(String firstName) {
	  TestLoggerHolder.getLogger().info("Entering first name");
      sendKeys(FIRST_NAME, firstName);
   }

   /**
    * This method enters the last name
    * @param lastName
    */
   public void enterLastName(String lastName) {
	   TestLoggerHolder.getLogger().info("Entering last name");
      sendKeys(LAST_NAME, lastName);
   }

   /**
    * This method enters the company name
    * @param company
    */
   public void enterCompany(String company) {
	   TestLoggerHolder.getLogger().info("Entering company");
      sendKeys(COMPANY, company);
   }


   /**
    * This method fills in the account info
    */
   public void enterAccountInfo() {

      PageDataWorkflowMethods pageDataWorkflowMethods = new PageDataWorkflowMethods();
      pageDataWorkflowMethods.generateOrEnterFirstName();
      pageDataWorkflowMethods.generateOrEnterLastName();
      pageDataWorkflowMethods.generateOrEnterCompany();
   }

   /**
    * This class contains methods on entering data or not into the page elements
    */
   private class PageDataWorkflowMethods{

      /**
       * This method enters a first name field either with supplied data or generated data, or is not entered at all
       */
      private void generateOrEnterFirstName() {
         // Get any elements with a tag of FIRST_NAME
         Elements firstNameElements = XMLDataHandler.getNodesCalled(FIRST_NAME_TAG);
         // If the tag exists, see if it has a value
         if (firstNameElements.size() !=0) {
            String firstName = XMLDataHandler.getLastElementValueWithTag(FIRST_NAME_TAG);
            // If no value is supplied generate one
            if (StringUtils.isBlank(firstName)) {
               // Generate a name
               enterFirstName(FakerUtility.forename());
            }
            else {
               enterFirstName(firstName);
            }
         }
      }

      /**
       * This method enters a last name field either with supplied data or generated data, or is not entered at all
       */
      private void generateOrEnterLastName() {
         // Get any elements with a tag of LAST_NAME
         Elements lastNameElements = XMLDataHandler.getNodesCalled(LAST_NAME_TAG);
         // If the tag exists, see if it has a value
         if (lastNameElements.size() !=0) {
            String lastName = XMLDataHandler.getLastElementValueWithTag(LAST_NAME_TAG);
            // If no value is supplied generate one
            if (StringUtils.isBlank(lastName)) {
               // Generate a name
               enterLastName(FakerUtility.surname());
            }
            else {
               enterLastName(lastName);
            }
         }
      }

      /**
       * This method enters a company field either with supplied data or generated data, or is not entered at all
       */
      private void generateOrEnterCompany() {
         // Get any elements with a tag of COMPANY
         Elements companyElements = XMLDataHandler.getNodesCalled(COMPANY_TAG);
         // If the tag exists, see if it has a value
         if (companyElements.size() !=0) {
            String company = XMLDataHandler.getLastElementValueWithTag(COMPANY_TAG);
            // If no value is supplied generate one
            if (StringUtils.isBlank(company)) {
               enterCompany(FakerUtility.company());
            }
            else {
               enterCompany(company);
            }
         }
      }
   }
}
