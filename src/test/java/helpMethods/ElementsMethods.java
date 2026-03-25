package helpMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsMethods {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ElementsMethods(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        waitVisible(element);
        element.click();
    }

    public void fillElement(WebElement element, String value) {
        waitVisible(element);
        element.clear();
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            waitVisible(element);
            return element.isDisplayed();
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public String getElementText(WebElement element) {
        try {
            waitVisible(element);
            return element.getText();
        } catch (TimeoutException | StaleElementReferenceException e) {
            return "";
        }
    }

    public void clickJS(WebElement element) {
        waitVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
}