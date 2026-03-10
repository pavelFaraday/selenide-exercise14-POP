package Final.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    public SelenideElement title = $("span.title");
    public ElementsCollection cartItems = $$(".cart_item");
    public SelenideElement productName = $(".cart_item .inventory_item_name");
    public SelenideElement checkoutButton = $("[data-test='checkout']");

    public void removeBikeLight() {
        $("[data-test='remove-sauce-labs-bike-light']").click();
    }
}