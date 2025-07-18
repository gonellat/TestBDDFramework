package utils;

import pages.AccountInfoPage;
import pages.HomePage;
import pages.ProductsPage;
import pages.SignUpLoginPage;

import java.util.function.Supplier;

public class WebObjectManager {

    private final Supplier<HomePage> homePageSupplier;
    private final Supplier<ProductsPage> productsPageSupplier;
    private final Supplier<SignUpLoginPage> signUpLoginPageSupplier;
    private final Supplier<AccountInfoPage> accountInfoPageSupplier;

    private HomePage homePage;
    private ProductsPage productsPage;
    private SignUpLoginPage signUpLoginPage;
    private AccountInfoPage accountInfoPage;

    public WebObjectManager() {
        homePageSupplier = HomePage::new;
        productsPageSupplier = ProductsPage::new;
        signUpLoginPageSupplier = SignUpLoginPage::new;
        accountInfoPageSupplier = AccountInfoPage::new;
    }

    public HomePage getHomePage() {
        if (homePage == null) homePage = homePageSupplier.get();
        return homePage;
    }

    public ProductsPage getProductsPage() {
        if (productsPage == null) productsPage = productsPageSupplier.get();
        return productsPage;
    }

    public SignUpLoginPage getSignUpLoginPage() {
        if (signUpLoginPage == null) signUpLoginPage = signUpLoginPageSupplier.get();
        return signUpLoginPage;
    }
    
    public AccountInfoPage getAccountInfoPage() {
        if (accountInfoPage == null) accountInfoPage = accountInfoPageSupplier.get();
        return accountInfoPage;
    }
}
