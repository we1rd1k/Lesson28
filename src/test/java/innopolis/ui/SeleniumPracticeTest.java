package innopolis.ui;

import innopolis.ui.practice.SeleniumPracticePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SeleniumPracticeTest extends BaseTest{

    private SeleniumPracticePage seleniumPracticePage;

    @BeforeEach
    void beforeTest() {
        seleniumPracticePage = new SeleniumPracticePage();
    }


    @Test
    void test() {
        String hero = "Alan Mathison Turing";
        String language = "Java";
        String linkText = "Great! Return to menu";

        seleniumPracticePage.openPage();

        seleniumPracticePage.getTask("First task").
                firstTaskButtonClick().
                firstTaskButtonClickMeToo().
                checkAndClickLink(linkText);

        seleniumPracticePage.getTask("Second task").
                secondTaskCheckboxCheck(2).
                secondTaskRadioCheck("two").
                checkAndClickLink(linkText);

        seleniumPracticePage.getTask("Third task").
                thirdTaskSelectDropDown(hero).
                thirdTaskSelectFromList(language).
                thirdTaskGetAndCheckResult(hero, language).
                checkAndClickLink(linkText);

        seleniumPracticePage.getTask("Fourth task").
                fourthTaskFillFormField("First Name", "Test").
                fourthTaskFillFormField("Last Name", "Test").
                fourthTaskFillFormField("Email", "Test@test.ru").
                fourthTaskFillFormField("Address", "Test").
                fourthTaskSelectSex("Male").
                fourthTaskUploadFile("test.txt").
                fourthTaskFillTextArea("ewqgerewgrerb").
                fourthTaskSendForm().
                checkAndClickLink(linkText);

        seleniumPracticePage.getTask("Fifth task").
                fifthTaskVerifyCode(seleniumPracticePage.fifthTaskGetValueFromIFrame()).
                checkAndClickLink(linkText);

        seleniumPracticePage.getTask("Sixth task").
                sixthTaskSetPassToAlert(seleniumPracticePage.sixthTaskGetPassFromAlert()).
                sixthTaskGetMessageAndReturnToMenu("Great!");

        seleniumPracticePage.getTask("Seventh task").
                seventhTaskDeleteElements(3).
                seventhTaskAddElement("werger", "dsfs", "fweg").
                checkAndClickLink(linkText);
    }
}
