package innopolis.ui;

import innopolis.ui.swaglabs.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Login Tests")
public class SwagLabsLoginTest extends BaseTest{

    private LoginPage loginPage;

    @BeforeEach
    void beforeTest() {
        loginPage = new LoginPage();
    }

    @DisplayName("Positive Login Test")
    @MethodSource("correctLoginCred")
    @ParameterizedTest
    void test1(String userName, String pass) {
        loginPage.login(userName, pass).
                weAreOnFirstPage().
                logout();
    }

    @DisplayName("Negative Login Test")
    @MethodSource("incorrectLoginCred")
    @ParameterizedTest
    void test2(String userName, String pass) {
        loginPage.login(userName, pass);
        if (userName.equals("")) {
            loginPage.errorsCredCheck("Username is required");
        } else if(pass.equals("")) {
            loginPage.errorsCredCheck("Password is required");
        } else {
            loginPage.errorsCredCheck("Username and password do not match any user in this service");
        }

    }

    private static Stream<Arguments> correctLoginCred() {
        return Stream.of(
                Arguments.of("standard_user","secret_sauce"),
                Arguments.of("problem_user","secret_sauce"),
                Arguments.of("performance_glitch_user","secret_sauce")
        );
    }

    private static Stream<Arguments> incorrectLoginCred() {
        return Stream.of(
                Arguments.of("",""),
                Arguments.of("ler",""),
                Arguments.of("ler","_sauce")
        );
    }
}
