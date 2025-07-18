package pages;

import utils.TestLoggerHolder;

public class SignUpLoginPage extends BasePageClass{

   //Locators
   private static final String NAME = "css::input[type='text'][name='name']";
   private static final String EMAIL = "css::input[type='email'][data-qa='signup-email']";
   private static final String SIGNUP_BUTTON = "css::button[data-qa='signup-button']";

   /**
    * This method enters a name
    */
   public void enterName(String name) {
      TestLoggerHolder.getLogger().info("name=" +name);
      sendKeys(NAME, name);
   }

   /**
    * This method enters an email
    */
   public void enterEmail(String emailAddress) {
	   TestLoggerHolder.getLogger().info("emailAddress=" + emailAddress);
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