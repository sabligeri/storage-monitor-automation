package automation.test;

import automation.base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class UnAuthenticatedPathTest extends BaseTest {
    @ParameterizedTest()
    @CsvFileSource(resources = "/path-access.csv", numLinesToSkip = 1)
    public void testUnAuthenticatedPath(String path, Boolean shouldRedirect) {
        setUp(path);
        String current = wait.until(WebDriver::getCurrentUrl);
        if (shouldRedirect) {
            assertTrue(current.contains("/login"));
        } else {
            assertTrue(current.endsWith("/" + path));
        }
        tearDown();
    }
}
