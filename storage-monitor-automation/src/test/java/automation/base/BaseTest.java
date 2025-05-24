package automation.base;

import automation.page.LoginPage;
import automation.page.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

public abstract class BaseTest {
    protected final String SUT = "http://localhost:3000/";
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected String username;
    protected final String PASSWORD = "Password123";

    public void setUp(String path) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(SUT + path);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void registerUser() {
        username = "testuser_" + UUID.randomUUID();
        setUp("register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(username, PASSWORD);
        tearDown();
    }

    public void login() {
        setUp("login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, PASSWORD);
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
