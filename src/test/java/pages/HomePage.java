package pages;

import utils.TestConfiguration;
import utils.TestLoggerHolder;

public class HomePage extends BasePageClass{

   //Locators
   private static final String PRODUCTS = "css::a[href*='/products']";
   private static final String SIGNUP_LOGIN = "css::a[href*='/login']";
   
   /**
    * This method opens the web site provided in the property file
    */
   public void openPage() {
	  TestLoggerHolder.getLogger().info("Opening website");
      String site = TestConfiguration.getURL();
      getDriver().get(site);
   }

   /** 
    * This method clicks the products link
    */
   public void clickProducts() {
	  TestLoggerHolder.getLogger().info("Click Products");
      click(PRODUCTS);
      // Get rid of the advert by clicking outside of it..
      clickOutside();
      waitForUrlTitle("products");
   }
   
   /**
    * This method clicks Signup / login
    */
   public void clickSignupLogin() {
	  TestLoggerHolder.getLogger().info("Click Login Signup");
      click(SIGNUP_LOGIN);
   }
}
