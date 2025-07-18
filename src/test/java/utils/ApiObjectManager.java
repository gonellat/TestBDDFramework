package utils;

import apis.BaseApi;
import apis.ProductsApi;

import java.util.function.Supplier;

/**
 * Manager class responsible for providing access to API-level test objects.
 */
public class ApiObjectManager {

   private final Supplier<BaseApi> baseApiSupplier;
   private final Supplier<ProductsApi> productsApiSupplier;

   private BaseApi baseApi;
   private ProductsApi productsApi;

   /**
    * Constructs a new ApiObjectManager.
    */
   public ApiObjectManager() {
      baseApiSupplier = BaseApi::new;
      productsApiSupplier = ProductsApi::new;
   }

   /**
    * Returns the base API utility instance.
    * 
    * @return BaseApi instance
    */
   public BaseApi getBaseApi() {
      if (baseApi == null)
         baseApi = baseApiSupplier.get();
      return baseApi;
   }

   /**
    * Returns the products API instance.
    * 
    * @return ProductsApi instance
    */
   public ProductsApi getProductsApi() {
      if (productsApi == null)
         productsApi = productsApiSupplier.get();
      return productsApi;
   }
}
