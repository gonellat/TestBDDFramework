package pages;

import utils.TestLoggerHolder;

/**
 * Page object for the Sign Up / Login page.
 */
public class SignUpLoginPage extends BasePageClass {

   // Locators
   private static final String NAME = "css::input[type='text'][name='name']";
   private static final String EMAIL = "css::input[type='email'][data-qa='signup-email']";
   private static final String SIGNUP_BUTTON = "css::button[data-qa='signup-button']";

   /**
    * Default Constructor
    */
   public SignUpLoginPage() {

   }

   /**
    * Enters a name into the name field.
    * 
    * @param name The name to enter
    */
   public void enterName(String name) {
      TestLoggerHolder.getLogger().info("{} {}", "name=", name);
      sendKeys(NAME, name);
   }

   /**
    * Enters an email address.
    * 
    * @param emailAddress The email to enter
    */
   public void enterEmail(String emailAddress) {
      TestLoggerHolder.getLogger().info("{} {}", "emailAddress=", emailAddress);
      sendKeys(EMAIL, emailAddress);
   }

   /**
    * This method clicks signup
    */
   public void clickSignUp() {
      TestLoggerHolder.getLogger().info("clicking signup");
      click(SIGNUP_BUTTON);
   }
}