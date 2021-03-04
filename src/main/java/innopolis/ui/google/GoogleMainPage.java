package innopolis.ui.google;

import com.codeborne.selenide.Condition;
import innopolis.ui.MainPage;
import innopolis.ui.Props;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.*;

public class GoogleMainPage extends MainPage {

    private static Props props = ConfigFactory.create(Props.class);
    private static Logger LOG = LoggerFactory.getLogger(GoogleMainPage.class);

    @Override
    public void openPage() {
        LOG.info("Открываем главную страницу Google");
        open(props.googleUrl());
    }

    @Override
    public void fillInSearchField(String request) {
        LOG.info("Вводим в строку поиска {}", request);
        $(By.name("q")).setValue(request);
    }

    @Override
    public void searchButtonClick() {
        LOG.info("Кликаем кнопку поиска");
        $(By.name("btnK")).click();
    }

    @Override
    public void checkPageTitle(String title) {
        LOG.info("Проверяем, что в заголовке присутствует {}", title);
        super.checkPageTitle(title);
    }
}
