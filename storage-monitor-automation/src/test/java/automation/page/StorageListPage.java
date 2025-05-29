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
import java.util.List;
import java.util.stream.Collectors;

public class StorageListPage extends BasePom {

    @FindBy(css = "button[aria-label='Add New Storage']")
    private WebElement addNewStorageButton;

    @FindBy(css = "input")
    private WebElement storageNameInput;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    private WebElement createButton;

    @FindBy(css = "p.Mui-error")
    private WebElement storageNameErrorMessage;

    @FindBy(css = "div.css-qs3qa7")
    private List<WebElement> storageCards;

    @FindBy(css = "button[data-testid='FolderOpenIcon']")
    private List<WebElement> openButtons;

    @FindBy(css = "button[data-testid='DeleteIcon']")
    private List<WebElement> deleteButtons;

    public StorageListPage(WebDriver webDriver) {
        super(webDriver, new WebDriverWait(webDriver, Duration.ofSeconds(5)));
        PageFactory.initElements(driver, this);
    }

    public void clickAddNewStorage() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewStorageButton));
        addNewStorageButton.click();
    }

    public void enterStorageName(String name) {
        wait.until(ExpectedConditions.visibilityOf(storageNameInput));
        storageNameInput.sendKeys(name);
    }

    public void clickCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton));
        createButton.click();
    }

    public String getValidationMessage() {
        wait.until(ExpectedConditions.visibilityOf(storageNameErrorMessage));
        return storageNameErrorMessage.getText().trim();
    }

    public List<String> getAllStorageNames() {
        wait.until(d -> storageCards != null);
        return storageCards.stream()
                .map(card -> card.findElement(By.tagName("h6")).getText().trim())
                .collect(Collectors.toList());
    }

    public boolean hasStorage(String name) {
        return getAllStorageNames().contains(name);
    }

    public int getStorageCount() {
        return storageCards.size();
    }

    public void openStorage(String name) {
        int idx = getAllStorageNames().indexOf(name);
        if (idx < 0) throw new IllegalArgumentException("No storage found with the name: " + name);
        wait.until(ExpectedConditions.elementToBeClickable(openButtons.get(idx)))
                .click();
    }

    public void deleteStorage(String name) {
        int idx = getAllStorageNames().indexOf(name);
        if (idx < 0) throw new IllegalArgumentException("No storage found with the name: " + name);
        WebElement card = storageCards.get(idx);
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(idx)))
                .click();
        wait.until(ExpectedConditions.stalenessOf(card));
    }


}
