package innopolis.ui.google;

import com.codeborne.selenide.Condition;
import innopolis.ui.MainPage;
import innopolis.ui.Props;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class GoogleMainPage extends MainPage {

    private static Props props = ConfigFactory.create(Props.class);

    @Override
    public void openPage() {
        open(props.googleUrl());
    }

    @Override
    public void fillInSearchField(String request) {
        $(By.name("q")).setValue(request);
    }

    @Override
    public void searchButtonClick() {
        $(By.name("btnK")).click();
    }

    @Override
    public void checkPageTitle(String title) {
        super.checkPageTitle(title);
    }
}
