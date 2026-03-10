package Final.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {

    public SelenideElement title = $("span.title");
    public SelenideElement summaryInfo = $(".summary_info");
    public SelenideElement subtotal = $(".summary_subtotal_label");
    public SelenideElement tax = $(".summary_tax_label");
    public SelenideElement total = $(".summary_total_label");
    public SelenideElement finishButton = $("[data-test='finish']");
}