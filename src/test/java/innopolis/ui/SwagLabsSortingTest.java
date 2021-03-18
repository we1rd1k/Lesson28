package innopolis.ui;

import innopolis.ui.swaglabs.LoginPage;
import innopolis.ui.swaglabs.SLFirstPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class SwagLabsSortingTest extends BaseTest {

    private LoginPage loginPage;
    private SLFirstPage slFirstPage;
    private String userName = "standard_user";
    private String pass = "secret_sauce";

    @BeforeEach
    void beforeTest() {
        loginPage = new LoginPage();
        slFirstPage = new SLFirstPage();
    }

    @Test
    void loginTest() {
        loginPage.login(userName, pass).
                weAreOnFirstPage();
    }

    @ValueSource(strings = {"Name (A to Z)", "Name (Z to A)", "Price (low to high)", "Price (high to low)"})
    @ParameterizedTest
    void itemsSortTest(String option) {
        loginPage.login(userName, pass).
                weAreOnFirstPage();
        List<String> initialNameList = slFirstPage.getItemsNameList();
        List<Double> initialPriceList = slFirstPage.getItemsPriceList();
        slFirstPage.setSortOption(option);
        slFirstPage.checkSort(option, initialNameList, initialPriceList);
    }

    @Test
    void itemsCheckoutTest() {
        String firstName = "Test";
        String lastName = "Test";
        String zipCode = "Test";
        loginPage.login(userName, pass)
                .weAreOnFirstPage()
                .addItemsToCart(3)
                .goToCart()
                .proceedToCheckout()
                .fillInInformationForm(firstName, lastName, zipCode)
                .goToNextStep()
                .finishOrder();

    }

    @Test
    void formFieldsValidationCheckTest() {
        loginPage.login(userName, pass)
                .weAreOnFirstPage()
                .addItemsToCart(2)
                .goToCart()
                .proceedToCheckout()
                .submitButtonClick()
                .errorMessageCheck("First Name is required")
                .setFirstName("test")
                .submitButtonClick()
                .errorMessageCheck("Last Name is required")
                .setLastName("test")
                .submitButtonClick()
                .errorMessageCheck("Postal Code is required")
                .setZipCode("test").goToNextStep();
    }

}
