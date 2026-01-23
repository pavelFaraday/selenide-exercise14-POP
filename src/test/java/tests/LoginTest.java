package tests;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest { ;

    // გამოიყენეთ DataProvider
    @DataProvider(name = "users")
    public Object[][] users() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"invalid_user", "wrong_password"}
        };
    }

    // გამოიყენეთ DataProvider ტესტ მეთოდში
    @Test(priority = 1, dataProvider = "users")
    public void loginTest(String username, String password) {
        commonSteps.validateTitle("Swag Labs");

        swag.openLoginPage()
                .typeUsername(username)
                .typePassword(password)
                .clickLogin();

        if (username.equals("standard_user") || username.equals("problem_user")) {
            swag.loginSuccess();
        } else {
            swag.loginErrorVisible();
        }
    }
}
