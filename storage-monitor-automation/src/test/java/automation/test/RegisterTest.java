package automation.test;

import automation.base.BaseTest;
import automation.page.RegisterPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeEach
    public void init() {
        setUp("register");
        registerPage = new RegisterPage(driver);
    }

    @AfterEach
    public void cleanup() {
        tearDown();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/registration.csv", numLinesToSkip = 1)
    public void testRegister(String username, String password, boolean expectedResult) {

        if(!username.isEmpty()) {
            username += "_" + Instant.now().toEpochMilli();
        }

        registerPage.register(username, password);
        boolean actualResult = registerPage.isOnLoginPage();
        assertEquals(expectedResult, actualResult, String.format("Username: '%s', Password: '%s'", username, password));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/password-validation.csv", numLinesToSkip = 1)
    public void testPasswordValidationMessages(
            String baseUsername,
            String password,
            String expectedMessagePart
    ) {
        String uniqueUsername = baseUsername + "_" + Instant.now().toEpochMilli();

        registerPage.register(uniqueUsername, password);

        assertFalse(registerPage.isOnLoginPage());

        String actual = registerPage.getPasswordValidationText();
        assertTrue(actual.contains(expectedMessagePart));
    }

    @Test
    public void testLoginHereLinkNavigatesToLoginPage() {
        registerPage.clickLoginHere();

        assertTrue(registerPage.isOnLoginPage());
    }

}