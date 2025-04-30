package automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {
    protected final String SUT = "http://localhost:3000/";
    protected WebDriver driver;
    protected WebDriverWait wait;

    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(SUT);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
