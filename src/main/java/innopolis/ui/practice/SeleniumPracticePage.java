package innopolis.ui.practice;

import com.codeborne.selenide.SelenideElement;
import innopolis.ui.Props;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SeleniumPracticePage {


    Props props = ConfigFactory.create(Props.class);

    public SeleniumPracticePage openPage() {
        open(props.seleniumPracUrl());
        return this;
    }

    public SeleniumPracticePage getTask(String taskName) {
        $x(String.format("//a[.='%s']", taskName)).click();
        return this;
    }

    public SeleniumPracticePage firstTaskButtonClick() {
        $x("//button[.='Click me!']").click();
        $x("//label[contains(text(),'Excellent!')]").shouldBe(visible);
        return this;
    }

    public SeleniumPracticePage firstTaskButtonClickMeToo() {
        $x("//input[@value='Click me too!']").click();
        return this;
    }

    public SeleniumPracticePage checkAndClickLink(String linkName) {
        Assertions.assertEquals(linkName, $x("//label/a").shouldBe(visible).text());
        $x("//label/a").click();
        return this;
    }

    public SeleniumPracticePage secondTaskCheckboxCheck(Integer checkboxToSelect) {
        List<String> checkboxValues = $$x("//label/input[@type='checkbox']").stream().map(SelenideElement::getValue).collect(Collectors.toList());
        $$x("//label/input[@type='checkbox']").stream().limit(checkboxToSelect).forEach(SelenideElement::click);
        $(By.id("go")).click();
        String result = $(By.id("result")).text();
        String[] words;
        words = result.split(" ");
        Assertions.assertTrue(checkboxValues.containsAll(Arrays.asList(words)));
        return this;
    }


    public SeleniumPracticePage secondTaskRadioCheck(String radioValue) {
        $x("//label/input[@type='radio']").selectRadio(radioValue);
        $(By.id("radio_go")).click();
        Assertions.assertEquals(radioValue, $(By.id("radio_result")).shouldBe(visible).text());
        return this;
    }

    public SeleniumPracticePage thirdTaskSelectDropDown(String value) {
        $(By.name("hero")).selectOption(value);
        return this;
    }

    public SeleniumPracticePage thirdTaskSelectFromList(String value) {
        $(By.name("languages")).selectOptionByValue(value);
        return this;
    }

    public SeleniumPracticePage thirdTaskGetAndCheckResult(String dropDown, String optionList) {
        $(By.id("go")).click();
        Assertions.assertEquals($x("//label[2]").text(), dropDown);
        $x("//label[4]").shouldHave(text(optionList));
        return this;
    }

    public SeleniumPracticePage fourthTaskFillFormField(String fieldName, String value) {
        $x(String.format("//label[contains(text(),'%s')]/following-sibling::input",fieldName)).setValue(value);
        return this;
    }

    public SeleniumPracticePage fourthTaskSelectSex(String sex) {
        switch (sex) {
            case ("Male") -> $x("//input[@type='radio'][1]").click();
            case ("Female") -> $x("//input[@type='radio'][2]").click();
        }
        return this;
    }

    public SeleniumPracticePage fourthTaskUploadFile(String fileName) {
        $x("//input[@type='file']").uploadFile(new File("src//test//resources//" + fileName));
        return this;
    }

    public SeleniumPracticePage fourthTaskSendForm() {
        $x("//input[@type='submit']").click();
        return this;
    }

    public SeleniumPracticePage fourthTaskFillTextArea(String value) {
        $x("//textarea").setValue(value);
        return this;
    }

    public String fifthTaskGetValueFromIFrame() {
        return switchTo().frame("code-frame").findElement(By.xpath("//div//form//label[@id='code']")).getText().substring(14);
    }

    public SeleniumPracticePage fifthTaskVerifyCode(String code) {
        switchTo().parentFrame();
        $(By.name("code")).setValue(code);
        $(By.name("ok")).click();
        return this;
    }

    public String sixthTaskGetPassFromAlert() {
        $x("//button[@class='get']").click();
        return switchTo().alert().getText().substring(15);
    }

    public SeleniumPracticePage sixthTaskSetPassToAlert(String pass) {
        $x("//button[@class='set']").click();
        switchTo().alert().sendKeys(pass);
        switchTo().alert().accept();
        return this;
    }

    public SeleniumPracticePage sixthTaskGetMessageAndReturnToMenu(String message) {
        $x("//label").shouldHave(text(message));
        $x("//button[@class='return']").click();
        switchTo().alert().accept();
        return this;
    }

    private Integer seventhTaskCountTableElements() {
        return $$x("//table/tbody/tr/td/input").size();
    }

    public SeleniumPracticePage seventhTaskDeleteElements(Integer quantity) {
        Integer initialQuantity = seventhTaskCountTableElements();
        $$x("//table/tbody/tr/td/input").stream().limit(quantity).forEach(SelenideElement::click);
        $x("//input[@value='Delete']").click();
        Assertions.assertEquals(initialQuantity-quantity, seventhTaskCountTableElements());
        return this;
    }


    public SeleniumPracticePage seventhTaskFillFormElement(String fieldName, String value) {
        $x(String.format("//form//label[.='%s']/following-sibling::input", fieldName)).setValue(value);
        return this;
    }

    public SeleniumPracticePage seventhTaskAddElement(String company, String contact, String country) {
        Integer initialQuantity = seventhTaskCountTableElements();
        seventhTaskFillFormElement("Company", company);
        seventhTaskFillFormElement("Contact", contact);
        seventhTaskFillFormElement("Country", country);
        $x("//input[@value='Add']").click();
        Assertions.assertEquals(initialQuantity+1, seventhTaskCountTableElements());
        return this;
    }
}
