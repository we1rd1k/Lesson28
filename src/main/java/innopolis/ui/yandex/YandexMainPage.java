package innopolis.ui.yandex;

import com.codeborne.selenide.Condition;
import innopolis.ui.MainPage;
import innopolis.ui.Props;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class YandexMainPage extends MainPage {

    private static Props props = ConfigFactory.create(Props.class);

    @Override
    public void openPage() {
        open(props.yandexUrl());
    }

    @Override
    public void fillInSearchField(String request) {
        $(By.name("text")).setValue(request);
    }

    @Override
    public void searchButtonClick() {
        $x("//button[@type='submit']").click();
    }

    @Override
    public void checkPageTitle(String title) {
        super.checkPageTitle(title);
    }
}
