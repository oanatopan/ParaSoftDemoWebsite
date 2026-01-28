package helpMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FrameMethods {
    private WebDriver driver;

    public FrameMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToFrame(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}