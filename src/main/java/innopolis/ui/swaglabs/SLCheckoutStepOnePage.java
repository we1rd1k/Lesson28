package innopolis.ui.swaglabs;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.*;

public class SLCheckoutStepOnePage {

    public SLCheckoutStepOnePage fillInInformationForm(String firstName, String lastName, String zipCode) {
        setFirstName(firstName);
        setLastName(lastName);
        setZipCode(zipCode);
        return this;
    }

    public SLCheckoutStepOnePage setFirstName(String firstName) {
        $(By.id("first-name")).setValue(firstName);
        return this;
    }

    public SLCheckoutStepOnePage setLastName(String lastName) {
        $(By.id("last-name")).setValue(lastName);
        return this;
    }

    public SLCheckoutStepOnePage setZipCode(String zipCode) {
        $(By.id("postal-code")).setValue(zipCode);
        return this;
    }

    public SLCheckoutStepOnePage submitButtonClick() {
        $x("//div[contains(@class, 'checkout')]//input").submit();
        return this;
    }

    public SLCheckoutStepTwoPage goToNextStep() {
        submitButtonClick();
        $x("//div[contains(@class, 'subheader') and contains(text(), 'Checkout: Overview')]").shouldBe(visible);
        return new SLCheckoutStepTwoPage();
    }

    public SLCheckoutStepOnePage errorMessageCheck(String message) {
        $x("//button[@class='error-button']").shouldBe(visible);
        String actualMessage = $x("//h3[@data-test='error']").shouldBe(visible).text();
        assertTrue(actualMessage.contains(message), "Текст сообщения " + actualMessage + " не соответсвтует " + message);
        return this;
    }
}
