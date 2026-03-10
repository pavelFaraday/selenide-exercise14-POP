package Final.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {

    public SelenideElement title = $("span.title");
    public SelenideElement completeHeader = $(".complete-header");
    public SelenideElement completeText = $(".complete-text");
    public SelenideElement backToProductsButton = $("[data-test='back-to-products']");
}