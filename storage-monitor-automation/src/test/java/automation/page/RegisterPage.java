package automation.page;

import automation.base.BasePom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePom {

    @FindBy(id = ":r0:")
    private WebElement usernameInput;

    @FindBy(id = ":r1:")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement registerButton;

    @FindBy(css = "p.MuiTypography-root.MuiTypography-body1")
    private WebElement passwordValidationMessage;

    public RegisterPage(WebDriver webDriver) {
        super(webDriver, new WebDriverWait(webDriver, Duration.ofSeconds(5)));
        PageFactory.initElements(driver, this);
    }

    public void register(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    public boolean isRegistrationSuccessful() {
        try {
            return wait.until(driver -> driver.getCurrentUrl().contains("/login"));
        } catch (Exception e) {
            return false;
        }
    }

    public String getPasswordValidationText() {
        wait.until(ExpectedConditions.visibilityOf(passwordValidationMessage));
        return passwordValidationMessage.getText().trim();
    }
}
