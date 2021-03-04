package innopolis.ui;

import innopolis.ui.google.GoogleMainPage;
import innopolis.ui.yandex.YandexMainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("SearchTest")
public class SearchHelloWorldTest extends BaseTest {

    private GoogleMainPage googleMainPage;
    private YandexMainPage yandexMainPage;

    @BeforeEach
    private void beforeTest() {
        googleMainPage = new GoogleMainPage();
        yandexMainPage = new YandexMainPage();
    }

    @DisplayName("Google search test")
    @ParameterizedTest
    @ValueSource(strings = {"Hello World"})
    void test1(String request) {

        googleMainPage.openPage();
        googleMainPage.fillInSearchField(request);
        googleMainPage.searchButtonClick();
        googleMainPage.checkPageTitle(request);
    }

    @DisplayName("Yandex search test")
    @ParameterizedTest
    @ValueSource(strings = {"Hello World"})
    void test2(String request) {

        yandexMainPage.openPage();
        yandexMainPage.fillInSearchField(request);
        yandexMainPage.searchButtonClick();
        yandexMainPage.checkPageTitle(request);
    }

}
