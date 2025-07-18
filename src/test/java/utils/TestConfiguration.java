package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import constants.IConstants;
import constants.IProjectConstants;
import constants.UserPSWDConstants;

/**
 * Loads environment-specific configuration for the test project.
 */
public class TestConfiguration extends BaseTestConfiguration {

   private static String url;
   private static String getProductsAPI;
   private static String abcdpswd;

   /**
    * Default Constructor
    */
   private TestConfiguration() {
      throw new IllegalStateException("Utility class");
   }

   /**
    * Reads the configuration file for the given environment.
    * 
    * @param env The environment name (e.g. "dev", "local")
    * @throws IOException if file cannot be read
    */
   public static void readConfig(String env) throws IOException {
      String configPath = "env_config/" + env + ".properties";
      Properties properties = new Properties();

      try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(configPath)) {
         if (input == null) {
            throw new FileNotFoundException("❌ Could not find config file in resources: " + configPath);
         }

         properties.load(input);
      }

      url = properties.getProperty(IConstants.URL);
      if (url == null || url.trim().isEmpty()) {
         throw new IllegalStateException("❌ 'url' is missing in " + configPath + ". Please provide a valid base URL.");
      }

      getProductsAPI = properties.getProperty(IProjectConstants.GET_PRODUCTS_API);
      abcdpswd = properties.getProperty(UserPSWDConstants.ABCUSER1PSWD);
   }

   /**
    * Gets the base URL for the current environment.
    * 
    * @return Base URL string
    */
   public static String getURL() {
      return url;
   }

   /**
    * Gets the Products API endpoint from the environment config.
    * 
    * @return API endpoint string
    */
   public static String getProductsAPI() {
      return getProductsAPI;
   }

   /**
    * Gets the encoded test user password.
    * 
    * @return Password string
    */
   public static String getAbcdpswd() {
      return abcdpswd;
   }
}
