package Final.Steps;

import Final.Pages.CartPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;

public class CartSteps {

    private final CartPage cartPage = new CartPage();

    public CartSteps verifyCartPageLoaded() {
        cartPage.title.shouldHave(text("Your Cart"));
        return this;
    }

    public CartSteps verifyCartItemsCount(int expectedCount) {
        cartPage.cartItems.shouldHave(size(expectedCount));
        return this;
    }

    public CartSteps verifyProductInCart(String productName) {
        cartPage.productName.shouldHave(text(productName));
        return this;
    }

    public CartSteps removeBikeLight() {
        cartPage.removeBikeLight();
        return this;
    }

    public CartSteps checkout() {
        cartPage.checkoutButton.click();
        return this;
    }
}