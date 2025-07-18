package step_definitions;

import utils.TestContextManager;
import pages.AccountInfoPage;
import pages.HomePage;
import pages.ProductsPage;
import pages.SignUpLoginPage;
import apis.ProductsApi;

/**
 * Provides common page access logic for step definition classes.
 */
public abstract class PageAccessStepsBase extends BaseStepsSupport {

   private final TestContextManager context;

   /**
    * Default constructor.
    */
   public PageAccessStepsBase() {
      this.context = new TestContextManager();
   }

   protected TestContextManager context() {
      return context;
   }

   protected HomePage homePage() {
      return context.web().getHomePage();
   }

   protected ProductsPage productsPage() {
      return context.web().getProductsPage();
   }

   protected SignUpLoginPage signUpLoginPage() {
      return context.web().getSignUpLoginPage();
   }

   protected ProductsApi productsApi() {
      return context.api().getProductsApi();
   }

   protected AccountInfoPage accountInfoPage() {
      return context.web().getAccountInfoPage();
   }
}
