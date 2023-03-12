package registration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class WebDriverParams {

    private void setupWebDriver() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        Configuration.browser = "chrome";
//        Configuration.browserSize = "1920x1080";
//        Configuration.timeout = 15_000L;
    }

    @BeforeEach
    void init() {
        setupWebDriver();
    }

    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }
}
