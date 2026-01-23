package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseTest {

    //  ტესტს უნდა ჰქონდეს `priority = 2`
    @Test(priority = 2)
    public void shoppingCartTest() {

        commonSteps.validateTitle("Swag Labs");
        swag.openLoginPage()
            .typeUsername("standard_user")
            .typePassword("secret_sauce")
            .clickLogin()
            .loginSuccess()
            .addFirstNProductsToCart(2)
            .cartBadgeEquals(2)
            .openCartFromHeader()
            .cartItemsCount(2)
            .clickCheckout()
            .fillCheckoutInfo("Giorgi", "Tester", "0101")
            .clickContinueCheckout()
            .itemTotalIsCorrect();
    }
}
