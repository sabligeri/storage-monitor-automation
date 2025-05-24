package automation.test;

import automation.base.BaseTest;
import automation.page.NavBarComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class NavBarTest extends BaseTest {

    private NavBarComponent navBar;

    @BeforeEach
    public void init() {
        registerUser();
        login();
        navBar = new NavBarComponent(driver);
    }

    @AfterEach
    public void cleanUp() {
        tearDown();
    }

    @Test
    public void testNavBarVisibilityAfterLogin() {
        assertTrue(navBar.isDisplayed());
    }

    @Test
    public void testNavBarNavigationLinks() {
        navBar.clickStorageLink();
        assertTrue(navBar.isOnPage("storagelist"));

        navBar.clickProductsLink();
        assertTrue(navBar.isOnPage("products"));

        navBar.clickProductionLink();
        assertTrue(navBar.isOnPage("production"));

        navBar.clickHomeLink();
        assertTrue(navBar.isOnPage("home"));
    }
}
