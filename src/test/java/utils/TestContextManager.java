package utils;

/**
 * Context manager for aggregating web and API object managers per scenario.
 */
public class TestContextManager implements BaseObjectManager {

   private final WebObjectManager webObjectManager;
   private final ApiObjectManager apiObjectManager;

   /**
    * Default Constructor
    */
   public TestContextManager() {
      this.webObjectManager = new WebObjectManager();
      this.apiObjectManager = new ApiObjectManager();
   }

   /**
    * Returns the WebObjectManager instance for page access.
    * 
    * @return WebObjectManager
    */
   public WebObjectManager web() {
      return webObjectManager;
   }

   /**
    * Returns the ApiObjectManager instance for API access.
    * 
    * @return ApiObjectManager
    */
   public ApiObjectManager api() {
      return apiObjectManager;
   }
}
