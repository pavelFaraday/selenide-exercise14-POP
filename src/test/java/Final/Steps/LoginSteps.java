package Final.Steps;

import Final.Pages.LoginPage;

import static Final.Data.Constants.PASSWORD;
import static Final.Data.Constants.USERNAME;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    public void login() {
        loginPage.login(USERNAME, PASSWORD);
        $("span.title").shouldHave(text("Products"));
    }
}