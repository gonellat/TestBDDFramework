package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestConfiguration;
import utils.TestLoggerHolder;

/**
 * Page object representing the [Home/Account Info/Products/Sign Up-Login] page.
 */
public class HomePage extends BasePageClass {

   /**
    * Default Constructor
    */
   public HomePage() {

   }

   // Locators
   private static final String PRODUCTS = "css::a[href*='/products']";
   private static final String SIGNUP_LOGIN = "css::a[href*='/login']";
   private static final String CONSENT = "css::div[aria-label='This site asks for consent to use your data'] button[aria-label='Consent']";

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
   
   /**
    * This method clicks Signup / login
    */
   public void clickConsent() {
      TestLoggerHolder.getLogger().info("Trying to click Consent (if present)");
      try {
          List<WebElement> consentButtons = findElementsBy(CONSENT); // modify your getElements to support the prefix
          if (!consentButtons.isEmpty()) {
              WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
              WebElement consent = wait.until(ExpectedConditions.elementToBeClickable(consentButtons.get(0)));
              consent.click();
              TestLoggerHolder.getLogger().info("✅ Consent clicked");
          } else {
              TestLoggerHolder.getLogger().info("ℹ️ Consent popup not shown");
          }
      } catch (Exception e) {
          TestLoggerHolder.getLogger().warn("⚠️ Consent popup present but not clickable: " + e.getMessage());
      }
  }
}
