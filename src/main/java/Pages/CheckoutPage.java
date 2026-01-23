package Pages;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage {
    public SelenideElement
        firstNameInput = $("#first-name"),
        lastNameInput = $("#last-name"),
        postalCodeInput = $("#postal-code"),
        continueButton = $("#continue"),
        itemTotalLabel = $(".summary_subtotal_label");

    public ElementsCollection
            itemPrices = $$(".inventory_item_price");
}
