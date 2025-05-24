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

    @Test
    public void testSwitchToDarkMode() {
        navBar.clickThemeToggleButton();
        assertEquals("dark", navBar.getCurrentTheme());
    }

    @Test
    public void testReturnToLightMode() {
        navBar.clickThemeToggleButton();
        navBar.clickThemeToggleButton();
        assertEquals("light", navBar.getCurrentTheme());
    }

    @Test
    public void testThemeToggleButtonIconChange() {
        assertEquals("DarkModeIcon", navBar.getCurrentThemeIcon());
        navBar.clickThemeToggleButton();
        assertEquals("LightModeIcon", navBar.getCurrentThemeIcon());
        navBar.clickThemeToggleButton();
        assertEquals("DarkModeIcon", navBar.getCurrentThemeIcon());
    }

    @Test
    public void testLogoutButtonRedirectToLoginPage() {
        navBar.clickLogoutButton();
        assertTrue(navBar.isOnPage("login"));
    }

    @Test
    public void testProtectedPathsAfterLogout() {
        navBar.clickLogoutButton();
        String[] protectedPaths = {"home", "storagelist", "products", "production"};
        for (String protectedPath : protectedPaths) {
            driver.get(SUT + protectedPath);
            assertTrue(navBar.isOnPage("login"));
        }

    }
}
