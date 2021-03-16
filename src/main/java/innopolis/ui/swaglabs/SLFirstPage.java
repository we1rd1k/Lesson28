package innopolis.ui.swaglabs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class SLFirstPage {

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
            if(matcher.find()) {
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
                Assertions.assertEquals(initialNameList.stream().sorted().collect(Collectors.toList()), getItemsNameList());
                break;
            case "Name (Z to A)":
                Collections.sort((List) initialNameList);
                Collections.reverse(initialNameList);
                Assertions.assertEquals(initialNameList, getItemsNameList());
                break;
            case "Price (low to high)":
                Assertions.assertEquals(initialPriceList.stream().sorted().collect(Collectors.toList()), getItemsPriceList());
                break;
            case "Price (high to low)":
                Collections.sort((List) initialPriceList);
                Collections.reverse(initialPriceList);
                Assertions.assertEquals(initialPriceList, getItemsPriceList());
                break;
        }
    }

    public void logout() {
        log.info("Выходим из аккаунта");
        $x("//button[.='Open Menu']").click();
        $x("//a[.='Logout']").click();
    }
}
