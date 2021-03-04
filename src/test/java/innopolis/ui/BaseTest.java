package innopolis.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {

    @BeforeAll
    private static void init() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
    }

    @AfterEach
    void afterTest() {
        Selenide.closeWebDriver();
    }

}
