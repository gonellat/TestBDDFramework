package utils;

import pages.AccountInfoPage;
import pages.HomePage;
import pages.ProductsPage;
import pages.SignUpLoginPage;

import java.util.function.Supplier;

/**
 * Provides access to all Web page objects for the test context.
 */
public class WebObjectManager {

   private final Supplier<HomePage> homePageSupplier;
   private final Supplier<ProductsPage> productsPageSupplier;
   private final Supplier<SignUpLoginPage> signUpLoginPageSupplier;
   private final Supplier<AccountInfoPage> accountInfoPageSupplier;

   private HomePage homePage;
   private ProductsPage productsPage;
   private SignUpLoginPage signUpLoginPage;
   private AccountInfoPage accountInfoPage;

   /**
    * Default Constructor
    */
   public WebObjectManager() {
      homePageSupplier = HomePage::new;
      productsPageSupplier = ProductsPage::new;
      signUpLoginPageSupplier = SignUpLoginPage::new;
      accountInfoPageSupplier = AccountInfoPage::new;
   }

   /**
    * Returns the page object for Home Page.
    *
    * @return HomePage instance
    */
   public HomePage getHomePage() {
      if (homePage == null)
         homePage = homePageSupplier.get();
      return homePage;
   }

   /**
    * Returns the page object for Products Page
    *
    * @return ProductsPage instance
    */
   public ProductsPage getProductsPage() {
      if (productsPage == null)
         productsPage = productsPageSupplier.get();
      return productsPage;
   }

   /**
    * Returns the page object for Sign Up Login Page.
    *
    * @return SignUpLoginPage instance
    */
   public SignUpLoginPage getSignUpLoginPage() {
      if (signUpLoginPage == null)
         signUpLoginPage = signUpLoginPageSupplier.get();
      return signUpLoginPage;
   }

   /**
    * Returns the page object for Account Info Page.
    *
    * @return AccountInfoPage instance
    */
   public AccountInfoPage getAccountInfoPage() {
      if (accountInfoPage == null)
         accountInfoPage = accountInfoPageSupplier.get();
      return accountInfoPage;
   }
}
