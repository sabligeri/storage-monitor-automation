package automation.test;

import automation.base.BaseTest;
import automation.page.StorageListPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageListTest extends BaseTest {

    private StorageListPage listPage;

    @BeforeEach
    public void init() {
        registerUser();
        login();
        wait.until(d -> d.getCurrentUrl().contains("/home"));

        driver.get(SUT + "storagelist");
        wait.until(d -> d.getCurrentUrl().contains("/storagelist"));
        listPage = new StorageListPage(driver);
    }

    @AfterEach
    public void cleanup() {
        tearDown();
    }

    @Test
    public void testEmptyStorageNameValidation() {
        listPage.clickAddNewStorage();
        listPage.clickCreate();
        assertEquals(
                "Storage name is required.",
                listPage.getValidationMessage()
        );
    }

    @Test
    public void testAddStorageIncreasesCountAndIsPresent() {
        int before = listPage.getStorageCount();

        listPage.clickAddNewStorage();
        listPage.enterStorageName("Test Storage");
        listPage.clickCreate();

        wait.until(d -> listPage.getStorageCount() == before + 1);

        assertEquals(before + 1, listPage.getStorageCount());
        assertTrue(listPage.getAllStorageNames().contains("Test Storage"));
    }
}
