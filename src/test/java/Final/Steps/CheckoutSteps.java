package Final.Steps;

import Final.Pages.CheckoutCompletePage;
import Final.Pages.CheckoutInfoPage;
import Final.Pages.CheckoutOverviewPage;

import static Final.Data.Constants.FIRST_NAME;
import static Final.Data.Constants.LAST_NAME;
import static Final.Data.Constants.POSTAL_CODE;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class CheckoutSteps {

    private final CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage();
    private final CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    private final CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    public CheckoutSteps verifyCheckoutInfoPageLoaded() {
        checkoutInfoPage.title.shouldHave(text("Checkout: Your Information"));
        return this;
    }

    public CheckoutSteps fillCheckoutInfo() {
        checkoutInfoPage.fillInformation(FIRST_NAME, LAST_NAME, POSTAL_CODE);
        return this;
    }

    public CheckoutSteps continueCheckout() {
        checkoutInfoPage.continueButton.click();
        return this;
    }

    public CheckoutSteps verifyOverviewPageLoaded() {
        checkoutOverviewPage.title.shouldHave(text("Checkout: Overview"));
        checkoutOverviewPage.summaryInfo.shouldBe(visible);
        checkoutOverviewPage.subtotal.shouldHave(text("Item total"));
        checkoutOverviewPage.tax.shouldHave(text("Tax"));
        checkoutOverviewPage.total.shouldHave(text("Total"));
        return this;
    }

    public CheckoutSteps finishCheckout() {
        checkoutOverviewPage.finishButton.click();
        return this;
    }

    public CheckoutSteps verifyOrder() {
        checkoutCompletePage.title.shouldHave(text("Checkout: Complete!"));
        checkoutCompletePage.completeHeader.shouldHave(text("Thank you for your order!"));
        checkoutCompletePage.completeText.shouldBe(visible);
        checkoutCompletePage.backToProductsButton.shouldBe(visible);
        return this;
    }
}