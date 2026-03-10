package Final.Base;

import Data.Constants;
import com.codeborne.selenide.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 1;
        Configuration.pageLoadTimeout = 15000;
        open(Constants.BASE_URL);
    }
    @AfterClass
    public void tearDown() {
        Selenide.closeWebDriver(); // closeWebDriver() ---> Steps
    }
}
