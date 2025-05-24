package automation.page;

import automation.base.BasePom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePom {

    @FindBy(id = ":r0:")
    private WebElement usernameInputField;

    @FindBy(id = ":r1:")
    private WebElement passwordInputField;

    @FindBy(css = "#root > div > div > div.MuiBox-root.css-3e112s > form > button")
    private WebElement loginButton;

    @FindBy(css = "#root > div > div > div.MuiBox-root.css-1upjcje > a")
    private WebElement registerHereLink;

    @FindBy(css = "#root > div > div > div.MuiBox-root.css-3e112s > form > p")
    private WebElement errorMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver, new WebDriverWait(webDriver, Duration.ofSeconds(5)));
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        usernameInputField.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText().trim();
    }

    public void clickRegisterHereLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerHereLink));
        registerHereLink.click();
    }

}
