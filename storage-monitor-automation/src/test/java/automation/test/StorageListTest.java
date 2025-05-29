package automation.test;

import automation.base.BaseTest;
import automation.page.StorageListPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageListTest extends BaseTest {

    private StorageListPage listPage;
    private final String TEST_STORAGE = "Test Storage";

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
        listPage.enterStorageName(TEST_STORAGE);
        listPage.clickCreate();

        wait.until(d -> listPage.getStorageCount() == before + 1);

        assertEquals(before + 1, listPage.getStorageCount());
        assertTrue(listPage.getAllStorageNames().contains(TEST_STORAGE));
    }

    @Test
    public void testOpenStorageNavigatesToStoragePage() {
        if (!listPage.hasStorage(TEST_STORAGE)) {
            listPage.clickAddNewStorage();
            listPage.enterStorageName(TEST_STORAGE);
            listPage.clickCreate();
            wait.until(d -> listPage.hasStorage(TEST_STORAGE));
        }

        listPage.openStorage(TEST_STORAGE);
        String current = driver.getCurrentUrl();
        assertTrue(
                current.contains("/storage/"));
    }

    @Test
    public void testDeleteStorageRemovesStorage() {
        if (!listPage.hasStorage(TEST_STORAGE)) {
            listPage.clickAddNewStorage();
            listPage.enterStorageName(TEST_STORAGE);
            listPage.clickCreate();
            wait.until(d -> listPage.hasStorage(TEST_STORAGE));
        }

        int before = listPage.getStorageCount();
        listPage.deleteStorage(TEST_STORAGE);
        wait.until(d -> listPage.getStorageCount() == before - 1);

        assertEquals(before - 1, listPage.getStorageCount());
        assertFalse(listPage.hasStorage(TEST_STORAGE));
    }
}
