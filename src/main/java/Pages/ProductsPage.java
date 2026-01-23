package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {
    public SelenideElement
            title = $(".title"),
            cartLink = $(".shopping_cart_link"),
            cartBadge = $(".shopping_cart_badge");

    public ElementsCollection
            addToCartButtons = $$("button.btn_inventory"),
            inventoryPrices = $$("div.inventory_item_price");
}
