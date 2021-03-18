package innopolis.ui.swaglabs;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SLCheckoutStepTwoPage {

    public void finishOrder() {
        $x("//div[contains(@class, 'cart')]//a[contains(text(), 'FINISH')]").click();
        $x("//div[contains(@class, 'subheader') and contains(text(), 'Finish')]").shouldBe(visible);
    }
}
