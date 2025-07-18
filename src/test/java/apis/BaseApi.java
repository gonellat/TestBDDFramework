package apis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.response.Response;
import utils.EndPoints;

/**
 * Base class for all API interactions and reusable validation methods.
 */
public class BaseApi {

   /**
    * Constructs the base API object.
    */
   public BaseApi() {
      endPoints = new EndPoints();
   }

   EndPoints endPoints;
   // Standard logger instance for this class
   private static final Logger LOG = LogManager.getLogger(BaseApi.class);

   /**
    * This method checks the response is successful
    * 
    * @param response The API response
    */
   public static void check200AndLog(Response response) {
      response.then().log().all().and().statusCode(200).and().extract().response();
   }

   /**
    * Verifies that the response has a 200 OK status code.
    *
    * @param response The response object returned from the API call
    */
   public void iShouldReceiveASuccessStatusCode(Response response) {
      check200AndLog(response);
      LOG.info("The response status code is:" + response.getStatusCode());
   }
}
