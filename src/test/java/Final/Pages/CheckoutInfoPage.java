package Final.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutInfoPage {

    public SelenideElement title = $("span.title");
    public SelenideElement firstName = $("[data-test='firstName']");
    public SelenideElement lastName = $("[data-test='lastName']");
    public SelenideElement postalCode = $("[data-test='postalCode']");
    public SelenideElement continueButton = $("[data-test='continue']");

    public void fillInformation(String name, String surname, String zip) {
        firstName.setValue(name);
        lastName.setValue(surname);
        postalCode.setValue(zip);
    }
}