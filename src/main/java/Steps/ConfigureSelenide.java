package Steps;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

// დაექსტენდდება BaseTest კლასში
public class ConfigureSelenide {
    @BeforeSuite
    public void configureAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1024";
        Configuration.timeout = 8000;
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
    }
}
