package innopolis.ui;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public abstract class MainPage {

    public abstract void openPage();

    public abstract void fillInSearchField(String request);

    public abstract void searchButtonClick();

    public void checkPageTitle(String title) {
        $x("//title").should(Condition.exist);
        Assertions.assertTrue($x("//title").innerText().contains(title), "Заголовок не содержит фразы " + title);
    }
}
