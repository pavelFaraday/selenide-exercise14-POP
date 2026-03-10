package Final.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {

    public ElementsCollection products = $$(".inventory_item");
    public ElementsCollection productNames = $$(".inventory_item_name");
    public ElementsCollection productPrices = $$(".inventory_item_price");

    public SelenideElement title = $("span.title");
    public SelenideElement sortDropdown = $(".product_sort_container");
    public SelenideElement cartBadge = $(".shopping_cart_badge");
    public SelenideElement cartLink = $(".shopping_cart_link");

    public SelenideElement productTitle = $(".inventory_details_name");
    public SelenideElement productPrice = $(".inventory_details_price");
    public SelenideElement productDescription = $(".inventory_details_desc");
    public SelenideElement addToCartOnDetails = $("[data-test^='add-to-cart']");
    public SelenideElement backButton = $("[data-test='back-to-products']");

    public void sortByZA() {
        sortDropdown.selectOption("Name (Z to A)");
    }

    public void sortByPriceLowToHigh() {
        sortDropdown.selectOption("Price (low to high)");
    }

    public void addBackpack() {
        $("[data-test='add-to-cart-sauce-labs-backpack']").click();
    }

    public void addBikeLight() {
        $("[data-test='add-to-cart-sauce-labs-bike-light']").click();
    }

    public void openCart() {
        cartLink.click();
    }
}