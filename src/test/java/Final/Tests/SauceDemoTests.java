package Final.Tests;

import Final.Base.BaseTest;
import Final.Steps.CartSteps;
import Final.Steps.CheckoutSteps;
import Final.Steps.LoginSteps;
import Final.Steps.ProductSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoTests extends BaseTest {

    LoginSteps loginSteps = new LoginSteps();
    CartSteps cartSteps = new CartSteps();
    CheckoutSteps checkoutSteps = new CheckoutSteps();
    ProductSteps productSteps = new ProductSteps();

    @BeforeMethod
    public void login() {
        loginSteps.login();
    }

    @Test(description = "Verify all 6 products are displayed and Z-A sort works correctly", priority = 1)
    public void testProductSorting() {
        productSteps
                .verifyProductsCount()
                .sortProductsByZA()
                .verifyProductsSortedZA();
    }

    @Test(description = "Add Sauce Labs Backpack to cart and verify cart page", priority = 2)
    public void testAddToCart() {
        productSteps
                .addBackpackToCart()
                .verifyCartBadge("1")
                .openCart();

        cartSteps
                .verifyCartPageLoaded()
                .verifyCartItemsCount(1)
                .verifyProductInCart("Sauce Labs Backpack");
    }

    @Test(description = "Verify product detail page elements and back-to-products navigation", priority = 3)
    public void testNavigationAndUI() {
        productSteps
                .openFirstProduct()
                .verifyProductDetailsPage()
                .backToProducts()
                .verifyProductsPageLoaded();
    }

    @Test(description = "Verify price low-to-high sort produces correctly ordered prices", priority = 4)
    public void testPriceSortingLowToHigh() {
        productSteps
                .sortProductsByPriceLowToHigh()
                .verifyProductsSortedByPriceLowToHigh();
    }

    @Test(description = "Add two products, remove one, complete full checkout and verify confirmation", priority = 5)
    public void testFullCheckoutFlow() {
        productSteps
                .addBackpackToCart()
                .addBikeLightToCart()
                .verifyCartBadge("2")
                .openCart();

        cartSteps
                .verifyCartPageLoaded()
                .verifyCartItemsCount(2)
                .removeBikeLight()
                .verifyCartItemsCount(1)
                .verifyProductInCart("Sauce Labs Backpack")
                .checkout();

        checkoutSteps
                .verifyCheckoutInfoPageLoaded()
                .fillCheckoutInfo()
                .continueCheckout()
                .verifyOverviewPageLoaded()
                .finishCheckout()
                .verifyOrder();
    }
}