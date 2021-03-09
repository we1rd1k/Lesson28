package innopolis.ui.swaglabs;

import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class SLFirstPage {

    public SLFirstPage weAreOnFirstPage() {
        log.info("Мы находимся на стартовой странице");
        $x("//div[contains(text(), 'Products')]").shouldBe(visible);
        return this;
    }

    public void logout() {
        log.info("Выходим из аккаунта");
        $x("//button[.='Open Menu']").click();
        $x("//a[.='Logout']").click();
    }
}
