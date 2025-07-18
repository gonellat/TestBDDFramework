package apis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.response.Response;
import utils.EndPoints;

public class BaseApi {

   // Constructor
   public BaseApi () {
      endPoints = new EndPoints();
   }

   EndPoints endPoints;
   // Standard logger instance for this class 
   private static final Logger LOG = LogManager.getLogger(BaseApi.class);

   /**
    * This method checks the response is successful
    * @param response
    */
   public static void check200AndLog(Response response) {
      response
      .then()     
      .log()
      .all()
      .and()
      .statusCode(200)
      .and()
      .extract()
      .response();
   }

   /**
    * This method checks if response is 200.
    */
   public void iShouldReceiveASuccessStatusCode(Response response)
   {
      check200AndLog(response);
      LOG.info("The response status code is:" + response.getStatusCode());
   }
}
