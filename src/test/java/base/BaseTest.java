package base;

import Steps.ConfigureSelenide;
import Steps.CommonSteps;
import Data.Constants;
import Steps.SwagSteps;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest extends ConfigureSelenide {
    // Steps ხელმისაწვდომი იქნება შვილ კლასებში (ტესტებში)
    protected CommonSteps commonSteps = new CommonSteps();
    protected SwagSteps swag = new SwagSteps();

    // `@BeforeMethod` — Browser-ის გასახსნელად
    @BeforeMethod
    public void openBrowser() {
        open(Constants.BASE_URL);
    }

    // `@AfterMethod` — Browser-ის დასახურად
    @AfterMethod
    public void closeBrowser() {
        closeWebDriver();
    }
}
