package apis;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.response.Response;
import utils.JsonDataHandler;
import utils.BaseTestConfiguration;
import utils.Helpers.Products;

public class ProductsApi extends BaseApi{

   // Standard logger instance for this class 
   private static final Logger LOG = LogManager.getLogger(ProductsApi.class);
   
   /**
    * This method calls a get request on the products api and saves the response in the products helper class
    */
   public void getAllProducts() {
      Response response = endPoints.getRequest(BaseTestConfiguration.getProductsApi());
      Products.setProductResponses(response);
   }

   /**
    * This method calls the products helper class and verifies that it contains a blue top
    */
   public void it_will_contain_a_blue_top() {
      iShouldReceiveASuccessStatusCode(Products.getTheLatestProductResponse());

      // Extract the actual results array from the Json response message 
      JsonObject jsonObjResponse = JsonDataHandler.createJsonObject(Products.getTheLatestProductResponse().asString());
      JsonArray jsonResponseProductsArray = (JsonArray) jsonObjResponse.get("products");

      // Now see if the expected products has been returned 
      boolean foundExpectedData = false;
      for (int x=0; x<jsonResponseProductsArray.size(); x++) {
         JsonObject jsonResponseObject = (JsonObject) jsonResponseProductsArray.get(x);
         LOG.info(jsonResponseObject);

         if (jsonResponseObject.get("name").getAsString().equals("Blue Top")) {
            foundExpectedData = true;
            break;
         }
      }
      
      assertThat("Blue Top Not Found", foundExpectedData, is(true));
   }
}
