package automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePom {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePom(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isOnPage(String pathToPage) {
        try {
            return wait.until(driver -> driver.getCurrentUrl().contains(pathToPage));
        } catch (Exception e) {
            return false;
        }
    }
}
