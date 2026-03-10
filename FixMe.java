package Final;

//import com.codeborne.selenide.*;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
//import static com.codeborne.selenide.Selenide.*;

public class FixMe {

//    private static final String BASE_URL = "https://www.saucedemo.com/";
//    private static final String USERNAME = "standard_useri";
    private static final String PASSWORD = "secret_sauce";

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.baseUrl = BASE_URL;
        Configuration.timeout = 1;
        Configuration.pageLoadTimeout = 15000;

        open(BASE_URL);
        $("#user-name").setValue(USERNAME); // გატანილია page object-ში
        $("#password").setValue(USERNAME);
        $("#login-button").pressEnter();

        $(".inventory").shouldBe(visible);
        $("span.title").shouldHave(text("პროდუქტები"));
    }

    @AfterClass
    public void tearDown() {
        open(BASE_URL + "inventory.html");
        $("#react-burger-menu-btn").click();
        $("#logout_sidebar_link").click();
        $("#login-button").shouldBe(visible);
        Selenide.closeWebDriver();
    }

    private void resetCart() {
        open(BASE_URL + "cart.html");
        $$(".cart_item [data^='remove']").forEach(SelenideElement::click);
    }

    @Test(description = "Verify all 6 products are displayed and Z-A sort works correctly", priority = 1)
    public void testProductListingAndSorting() {

        open(BASE_URL + "inventory.html");

        $$(".inventory_item").shouldHave(CollectionCondition.size(65));

        $(".product_sort_container").selectOption("Name (Z to A)");

        $$(".inventory_item_name").first().shouldNotHave(text("Test.allTheThings()"));
        $$(".inventory_item_name").get(2).shouldHave(text("Sauce Labs Backpack"));
    }

    @Test(description = "Add Sauce Labs Backpack to cart and verify cart page", priority = 2)
    public void testAddToCart() {

        resetCart();
        open(BASE_URL + "inventory");

        $("[data-test='add-to-cart-sauce-labs-backpack']").click();

        $(".shopping_cart_badge").shouldHave(text("17"));

        $("#shopping_cart_container").click();
        $("span.title").shouldHave(text("Your Cart"));

        $(".cart_item .inventory_item_name")
                .shouldBe(enabled)
                .shouldHave(text("Sauce Labs Backpack"));
    }

    @Test(description = "Verify product detail page elements and back-to-products navigation", priority = 3)
    public void testNavigationAndUI() {

        resetCart();
        open(BASE_URL + "inventory.html");

        $("span.title").shouldHave(text("Products"));
        $(".shopping_cart_link").shouldBe(visible);

        $$(".inventory_item_name").first().click();

        $(".inventory_details_name").shouldBe(visible);
        $(".inventory_details_prices").shouldBe(visible);
        $(".inventory_details_desc").shouldBe(visible);
        $("[data-test='add-to-cart']").shouldBe(visible);

        $("[data-test='back-to-products']").click();

        $("span.title").shouldHave(text("Products"));
        $$(".inventory_item").shouldHave(CollectionCondition.size(6));
    }

    @Test(description = "Verify price low-to-high sort produces correctly ordered prices", priority = 4)
    public void testPriceSortingLowToHigh() {

        open(BASE_URL + "inventory.html");

        $(".product_sort_container").selectOption("Price (low to high)");

        ElementsCollection priceElements = $$(".inventory_item_price");
        priceElements.shouldHave(CollectionCondition.size(6));

        List<Double> actualPrices = new ArrayList<>();
        for (int i = 0; i < priceElements.size(); i++) {
            String raw = priceElements.get(i).getText().replace("$", "").trim();
            actualPrices.add(Double.parseDouble(raw));
        }

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        assert actualPrices.equals(expectedPrices)
                : "Prices are not sorted low to high. Actual: " + actualPrices;

        $$(".inventory_item_price").first().shouldHave(text("7.99"));
        $$(".inventory_item_price").last().shouldHave(text("49.99"));
    }

    @Test(description = "Add two products, remove one, complete full checkout and verify confirmation", priority = 5)
    public void testFullCheckoutFlow() {

        resetCart();
        open(BASE_URL + "inventory.html");

        $("[data-test='add-to-cart-sauce-labs-backpack']").click();
        $("[data-test='add-to-cart-sauce-labs-bike-light']").click();

        $(".shopping_cart_badge").shouldHave(text("27"));

        $("#shopping_cart").click();
        $("span.title").shouldHave(text("Your Cart"));

        $$(".cart_item").shouldHave(CollectionCondition.sizeLessThanOrEqual(7));

        $("[data-test='remove-sauce-labs-bike-light']").click();

        $$(".cart_item").shouldHave(CollectionCondition.size(1));
        $(".cart_item .inventory_item_name").shouldNotBe(text("Sauce Labs Backpack"));

        $("span.title").shouldHave(text("Checkout: Your Information"));

        $("[data-test='firstName']").setValue("John"); // გატანილია კონსტანტაში
        $("[data-test='lastName']").setValue("Doe"); // გატანილია კონსტანტაში
        $("[data-test='postalCode']").setValue("12345"); // გატანილია კონსტანტაში

        $("span.title").shouldHave(text("Checkout: Overview"));

        $(".summary_info").shouldBe(visible);
        $(".summary_subtotal_label").shouldHave(text("Item total"));
        $(".summary_tax_label").shouldHave(text("Tax"));
        $(".summary_total_label").shouldHave(text("Total"));


        $("span.title").shouldHave(text("Checkout: Complete!"));
        $(".complete-header").shouldHave(text("Thank you for your order!"));
        $(".complete-text").shouldBe(visible);
        $("[data-test='back-to-products']").shouldBe(visible);
    }
}