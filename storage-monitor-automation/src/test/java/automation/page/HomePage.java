package automation.page;

import automation.base.BasePom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePom {

    public HomePage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, new WebDriverWait(webDriver, Duration.ofSeconds(5)));
        PageFactory.initElements(webDriver, this);
    }
}
