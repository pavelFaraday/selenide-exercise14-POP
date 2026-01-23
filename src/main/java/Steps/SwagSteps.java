package Steps;

 /*  ‼️ Important Note ‼️
  *  baseTest.java-ში არ უნდა იყოს ხედვადი (იმპორტი/ინსტანციები) ელემენტები Pages პაკეტიდან!
  *  ნაცვლად ამისა, Pages ელემენტები ინკაფსულირებული უნდა იყოს Steps პაკეტში (`).
  * */
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.LoginPage;
import Pages.ProductsPage;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;

public class SwagSteps {
    // Page ობიექტები
    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();


    // ---------------- LoginPage  გვერდის მეთოდები ----------------
    public SwagSteps openLoginPage() {
        loginPage.usernameInput.shouldBe(visible);
        return this;
    }
    public SwagSteps typeUsername(String username) {
        loginPage.usernameInput.shouldBe(visible).setValue(username);
        return this;
    }
    public SwagSteps typePassword(String password) {
        loginPage.passwordInput.shouldBe(visible).setValue(password);
        return this;
    }
    public SwagSteps clickLogin() {
        loginPage.loginButton.shouldBe(enabled).click();
        return this;
    }
    public SwagSteps loginSuccess() {
        productsPage.title.shouldBe(visible).shouldHave(text("Products"));
        return this;
    }
    public SwagSteps loginErrorVisible() {
        loginPage.errorMessage.shouldBe(visible);
        return this;
    }

    // ---------------- Products გვერდის მეთოდები ----------------
    public SwagSteps addFirstNProductsToCart(int n) {
        productsPage.addToCartButtons.shouldHave(sizeGreaterThanOrEqual(n));
        for (int i = 0; i < n; i++) {
            productsPage.addToCartButtons.get(i).shouldBe(enabled).click();
        }
        return this;
    }
    public SwagSteps cartBadgeEquals(int expected) {
        productsPage.cartBadge.shouldBe(visible).shouldHave(text(String.valueOf(expected)));
        return this;
    }
    public SwagSteps openCartFromHeader() {
        productsPage.cartLink.shouldBe(visible).click();
        cartPage.title.shouldBe(visible).shouldHave(text("Your Cart"));
        return this;
    }

    // ---------------- CartPage გვერდის მეთოდები ----------------
    public SwagSteps cartItemsCount(int expectedCount) {
        cartPage.cartItems.shouldHave(size(expectedCount));
        return this;
    }
    public SwagSteps clickCheckout() {
        cartPage.checkoutButton.shouldBe(enabled).click();
        checkoutPage.firstNameInput.shouldBe(visible);
        return this;
    }

    // ---------------- Checkout გვერდის მეთოდები ----------------
    public SwagSteps fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        checkoutPage.firstNameInput.shouldBe(visible).setValue(firstName);
        checkoutPage.lastNameInput.shouldBe(visible).setValue(lastName);
        checkoutPage.postalCodeInput.shouldBe(visible).setValue(postalCode);
        return this;
    }
    public SwagSteps clickContinueCheckout() {
        checkoutPage.continueButton.shouldBe(enabled).click();
        checkoutPage.itemTotalLabel.shouldBe(visible);
        return this;
    }
    // გადაამოწმეთ, რომ `Item total` მნიშვნელობა სწორად არის დათვლილი.
    public SwagSteps itemTotalIsCorrect() {
        BigDecimal expected = expectedItemTotalFromOverview();
        String expectedText = "Item total: $" + expected.toPlainString();

        checkoutPage.itemTotalLabel
                .shouldBe(visible)
                .shouldHave(text(expectedText));

        return this;
    }

    // ყველა პროდუქტის ფასის ამოღება Checkout Overview გვერდიდან
    private BigDecimal expectedItemTotalFromOverview() {
        List<String> pricesText = checkoutPage.itemPrices.texts(); // "$29.99"
        BigDecimal sum = BigDecimal.ZERO;

        for (String p : pricesText) {
            sum = sum.add(parseMoney(p));
        }
        return sum.setScale(2, RoundingMode.HALF_UP);
    }

    // იღებს ტექსტს: "$29.99" და აბრუნებს 29.99-ად
    private BigDecimal parseMoney(String text) {
        return new BigDecimal(text.replace("$", "").trim());
    }
}
