package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    public final SelenideElement title = $(".title");
    public final ElementsCollection cartItems = $$(".cart_item");
    public final SelenideElement checkoutButton = $("#checkout");
}
