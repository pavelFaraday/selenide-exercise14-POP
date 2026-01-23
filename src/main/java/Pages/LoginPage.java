package Pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public final SelenideElement
        usernameInput = $("#user-name"),
        passwordInput = $("#password"),
        loginButton   = $("#login-button"),
        errorMessage  = $("[data-test='error']");
}
