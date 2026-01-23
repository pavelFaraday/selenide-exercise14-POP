package Steps;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.title;

public class CommonSteps {
    public CommonSteps validateTitle(String expectedTitle) {
        webdriver().shouldHave(title(expectedTitle));
        return this;
    }
}
