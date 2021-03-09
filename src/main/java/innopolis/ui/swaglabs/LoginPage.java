package innopolis.ui.swaglabs;

import innopolis.ui.Props;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class LoginPage {

    Props props = ConfigFactory.create(Props.class);

    public SLFirstPage login(String userName, String password) {
        log.info("Авторизуемся в систему с userName = {}, password = {}", userName, password);
        open(props.swagLabsUrl());
        $(By.name("user-name")).setValue(userName);
        $(By.name("password")).setValue(password);
        $x("//form//input[@type='submit']").click();
        return new SLFirstPage();
    }

    public void errorsCredCheck(String errorMessage) {
        log.info("Проверяем наличие сообщения {}", errorMessage);
        Assertions.assertTrue($x("//h3[@data-test='error']").text().contains(errorMessage), "Текст " + errorMessage + " отсутствует");
    }
}
