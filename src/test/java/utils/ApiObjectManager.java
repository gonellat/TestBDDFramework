package utils;

import apis.BaseApi;
import apis.ProductsApi;

import java.util.function.Supplier;

public class ApiObjectManager {

    private final Supplier<BaseApi> baseApiSupplier;
    private final Supplier<ProductsApi> productsApiSupplier;

    private BaseApi baseApi;
    private ProductsApi productsApi;

    public ApiObjectManager() {
        baseApiSupplier = BaseApi::new;
        productsApiSupplier = ProductsApi::new;
    }

    public BaseApi getBaseApi() {
        if (baseApi == null) baseApi = baseApiSupplier.get();
        return baseApi;
    }

    public ProductsApi getProductsApi() {
        if (productsApi == null) productsApi = productsApiSupplier.get();
        return productsApi;
    }
}

