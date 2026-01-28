package helpMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ElementsMethods {
    private WebDriver driver;
    private WebDriverWait wait;

    public ElementsMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        waitVisible(element);
        element.click();
    }

    public void fillElement(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            waitVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getElementText(WebElement element) {
        waitVisible(element);
        return element.getText();
    }

    public void clickJS(WebElement element) {
        waitVisible(element);
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
}