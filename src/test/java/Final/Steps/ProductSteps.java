package Final.Steps;

import Final.Pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ProductSteps {

    private final ProductsPage productsPage = new ProductsPage();

    public ProductSteps verifyProductsCount() {
        productsPage.products.shouldHave(size(6));
        return this;
    }

    public ProductSteps sortProductsByZA() {
        productsPage.sortByZA();
        return this;
    }

    public ProductSteps verifyProductsSortedZA() {
        productsPage.productNames.first()
                .shouldHave(text("Test.allTheThings() T-Shirt (Red)"));

        productsPage.productNames.last()
                .shouldHave(text("Sauce Labs Backpack"));

        return this;
    }

    public ProductSteps addBackpackToCart() {
        productsPage.addBackpack();
        return this;
    }

    public ProductSteps addBikeLightToCart() {
        productsPage.addBikeLight();
        return this;
    }

    public ProductSteps verifyCartBadge(String expectedCount) {
        productsPage.cartBadge.shouldHave(text(expectedCount));
        return this;
    }

    public ProductSteps openCart() {
        productsPage.openCart();
        return this;
    }

    public ProductSteps openFirstProduct() {
        productsPage.productNames.first().click();
        return this;
    }

    public ProductSteps verifyProductDetailsPage() {
        productsPage.productTitle.shouldBe(visible);
        productsPage.productPrice.shouldBe(visible);
        productsPage.productDescription.shouldBe(visible);
        productsPage.addToCartOnDetails.shouldBe(visible);
        return this;
    }

    public ProductSteps backToProducts() {
        productsPage.backButton.click();
        return this;
    }

    public ProductSteps verifyProductsPageLoaded() {
        productsPage.title.shouldHave(text("Products"));
        productsPage.products.shouldHave(size(6));
        return this;
    }

    public ProductSteps sortProductsByPriceLowToHigh() {
        productsPage.sortByPriceLowToHigh();
        return this;
    }

    public ProductSteps verifyProductsSortedByPriceLowToHigh() {

        List<Double> actualPrices = new ArrayList<>();

        for (int i = 0; i < productsPage.productPrices.size(); i++) {
            String raw = productsPage.productPrices.get(i)
                    .getText()
                    .replace("$", "")
                    .trim();

            actualPrices.add(Double.parseDouble(raw));
        }

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        if (!actualPrices.equals(expectedPrices)) {
            throw new AssertionError(
                    "Prices are not sorted low to high. Actual: " + actualPrices
            );
        }

        productsPage.productPrices.first().shouldHave(text("7.99"));
        productsPage.productPrices.last().shouldHave(text("49.99"));

        return this;
    }
}