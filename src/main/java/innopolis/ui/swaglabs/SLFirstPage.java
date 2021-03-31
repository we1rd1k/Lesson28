package innopolis.ui.swaglabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class SLFirstPage {

    @Step("Мы на главной")
    public SLFirstPage weAreOnFirstPage() {
        log.info("Мы находимся на стартовой странице");
        $x("//div[contains(text(), 'Products')]").shouldBe(visible);
        return this;
    }

    public List<String> getItemsNameList() {
        return $$x("//div[@class='inventory_item']//div[@class='inventory_item_name']").texts();
    }

    public List<Double> getItemsPriceList() {
        Pattern pattern = Pattern.compile("\\d*\\.\\d*");
        return $$x("//div[@class='inventory_item']//div[@class='inventory_item_price']")
                .texts()
                .stream()
                .map(s -> {
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        return matcher.group();
                    }
                    return null;
                }).collect(Collectors.toList()).stream().map(Double::parseDouble).collect(Collectors.toList());
    }

    public void setSortOption(String option) {
        $(By.className("product_sort_container")).selectOption(option);
    }

    public void checkSort(String option, List<String> initialNameList, List<Double> initialPriceList) {
        switch (option) {
            case "Name (A to Z)":
                assertEquals(initialNameList.stream().sorted().collect(Collectors.toList()), getItemsNameList());
                break;
            case "Name (Z to A)":
                sort(initialNameList);
                reverse(initialNameList);
                assertEquals(initialNameList, getItemsNameList());
                break;
            case "Price (low to high)":
                assertEquals(initialPriceList.stream().sorted().collect(Collectors.toList()), getItemsPriceList());
                break;
            case "Price (high to low)":
                sort(initialPriceList);
                reverse(initialPriceList);
                assertEquals(initialPriceList, getItemsPriceList());
                break;
        }
    }

    public SLFirstPage addItemsToCart(Integer quantity) {
        Integer itemsAvailable = $$x("//button[.='ADD TO CART']").size();
        if(quantity>itemsAvailable) {
            $$x("//button[.='ADD TO CART']").stream().limit(itemsAvailable).forEach(SelenideElement::click);
            $x("//div[contains(@class, 'shopping_cart')]//span[contains(@class, 'counter')]").shouldHave(text(itemsAvailable.toString()));
        } else {
            $$x("//button[.='ADD TO CART']").stream().limit(quantity).forEach(SelenideElement::click);
            $x("//div[contains(@class, 'shopping_cart')]//span[contains(@class, 'counter')]").shouldHave(text(quantity.toString()));
        }
        return this;
    }

    public SLFirstPage goToCart() {
        $x("//div[contains(@class, 'shopping_cart')]//*[name()='svg']").click();
        $x("//div[contains(@class, 'subheader') and contains(text(), 'Your Cart')]").shouldBe(visible);
        return this;
    }

    public SLCheckoutStepOnePage proceedToCheckout() {
        $x("//div[contains(@class, 'cart')]//a[contains(text(), 'CHECKOUT')]").click();
        $x("//div[contains(@class, 'subheader') and contains(text(), 'Checkout: Your Information')]").shouldBe(visible);
        return new SLCheckoutStepOnePage();
    }

    public void logout() {
        log.info("Выходим из аккаунта");
        $x("//button[.='Open Menu']").click();
        $x("//a[.='Logout']").click();
    }
}
