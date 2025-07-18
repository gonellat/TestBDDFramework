package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import constants.IConstants;
import constants.UserPSWDConstants;

/**
 * Test-specific configuration loader that extends the base configuration logic.
 * This class is responsible for loading environment-dependent variables.
 */
public class TestConfiguration extends BaseTestConfiguration {

    private static String url;
    private static String getProductsAPI;
    private static String abcdpswd;

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

        getProductsAPI = properties.getProperty(IConstants.GET_PRODUCTS_API);
        abcdpswd = properties.getProperty(UserPSWDConstants.ABCUSER1PSWD);
    }


    /** Get the base URL for the current test environment */
    public static String getURL() {
        return url;
    }

    /** Get the configured Products API endpoint */
    public static String getProductsAPI() {
        return getProductsAPI;
    }

    /** Get the password for the ABC test user */
    public static String getAbcdpswd() {
        return abcdpswd;
    }
}
