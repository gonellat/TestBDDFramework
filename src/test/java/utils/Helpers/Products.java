package utils.Helpers;

import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;

/**
 * This class deals with data used on products throughout the framework
 * @author gonellat
 */
public class Products { 

   /**
    * Constructor required for sonar
    */
   private Products() {
      throw new IllegalStateException("Utility class");
   }
   
   private static List<Response> productResponses = new ArrayList<>();
   
   /**
    * This method adds the response to a list array
    * @param productResponse
    */
   public static void setProductResponses(Response productResponse) {
      if (productResponse==null) {
         productResponses = new ArrayList<>();
      }
      else {
         productResponses.add(productResponse);
      }
   }
   
   /**
    * This method gets the list of responses
    * @return list of responses
    */
   public static List<Response> getProductResponses() {
      return productResponses;
   }
   
   /**
    * This gets the last Product Response from the array
    * @return product response
    */
   public static Response getTheLatestProductResponse() {
      return productResponses.get(productResponses.size()-1);
   }
   
   /**
    * This method rests the local variables to null
    */
   public static void resetProducts() {
      setProductResponses(null);
   }
}
