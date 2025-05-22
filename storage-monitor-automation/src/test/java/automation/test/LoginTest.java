package automation.test;

import automation.base.BaseTest;
import automation.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void init() {
        registerUser();
        setUp("login");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void cleanup() {
        tearDown();
    }

    @Test
    public void testValidLogin() {
        loginPage.login(username, PASSWORD);
        assertTrue(loginPage.isOnPage("home"));
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("invalidUsername", PASSWORD);
        assertFalse(loginPage.isOnPage("home"));
        loginPage.login(username, "invalidPassword");
        assertFalse(loginPage.isOnPage("home"));
    }

    @Test
    public void testErrorMessage() {
        loginPage.login("invalidUsername", PASSWORD);
        assertTrue(loginPage.getErrorMessage().contains("Invalid username or password"));
        loginPage.login(username, "invalidPassword");
        assertTrue(loginPage.getErrorMessage().contains("Invalid username or password"));
    }

    @Test
    public void tesRegisterHereLink() {
        loginPage.clickRegisterHereLink();
        assertTrue(loginPage.isOnPage("register"));
    }
}
