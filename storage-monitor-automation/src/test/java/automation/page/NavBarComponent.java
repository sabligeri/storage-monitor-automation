package automation.page;

import automation.base.BasePom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavBarComponent extends BasePom {

    @FindBy(css = "header a[aria-label='Home']")
    private WebElement homeLink;

    @FindBy(css = "header a[aria-label='Storage']")
    private WebElement storageLink;

    @FindBy(css = "header a[aria-label='Products']")
    private WebElement productsLink;

    @FindBy(css = "header a[aria-label='Production']")
    private WebElement productionLink;

    @FindBy(css = "header button[aria-label='Toggle Theme']")
    private WebElement themeToggleButton;

    @FindBy(css = "header button[aria-label='Logout']")
    private WebElement logoutButton;

    public NavBarComponent(WebDriver webDriver) {
        super(webDriver, new WebDriverWait(webDriver, Duration.ofSeconds(5)));
        PageFactory.initElements(webDriver, this);
    }

    public Boolean isDisplayed() {
        try {
            return wait.until(d -> homeLink.isDisplayed() && logoutButton.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentTheme() {
        return driver.findElement(By.tagName("body"))
                .getAttribute("data-theme");
    }

    public String getCurrentThemeIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(themeToggleButton));
        WebElement svg = themeToggleButton.findElement(By.tagName("svg"));
        return svg.getAttribute("data-testid").trim();
    }

    public void clickHomeLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeLink));
        homeLink.click();
    }

    public void clickStorageLink() {
        wait.until(ExpectedConditions.elementToBeClickable(storageLink));
        storageLink.click();
    }

    public void clickProductsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(productsLink));
        productsLink.click();
    }

    public void clickProductionLink() {
        wait.until(ExpectedConditions.elementToBeClickable(productionLink));
        productionLink.click();
    }

    public void clickThemeToggleButton() {
        wait.until(ExpectedConditions.elementToBeClickable(themeToggleButton));
        themeToggleButton.click();
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }
}
