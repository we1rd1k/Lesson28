package innopolis.ui.yandex;

import com.codeborne.selenide.Condition;
import innopolis.ui.MainPage;
import innopolis.ui.Props;
import innopolis.ui.google.GoogleMainPage;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class YandexMainPage extends MainPage {

    private static Props props = ConfigFactory.create(Props.class);
    private static Logger LOG = LoggerFactory.getLogger(YandexMainPage.class);

    @Override
    public void openPage() {
        LOG.info("Открываем главную страницу Yandex");
        open(props.yandexUrl());
    }

    @Override
    public void fillInSearchField(String request) {
        LOG.info("Вводим в строку поиска {}", request);
        $(By.name("text")).setValue(request);
    }

    @Override
    public void searchButtonClick() {
        LOG.info("Кликаем кнопку поиска");
        $x("//button[@type='submit']").click();
    }

    @Override
    public void checkPageTitle(String title) {
        LOG.info("Проверяем, что в заголовке присутствует {}", title);
        super.checkPageTitle(title);
    }
}
