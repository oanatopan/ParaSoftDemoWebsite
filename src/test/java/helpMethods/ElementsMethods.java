package helpMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ElementsMethods {
    public WebDriver driver;

    public ElementsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void waitVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
    }

    // Metoda noua pentru corelarea corecta cu locatarii (By)
    public void waitVisibleBy(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void fillElement(WebElement element, String text) {
        waitVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public void clickElement(WebElement element) {
        waitVisible(element);
        element.click();
    }

    public void clickJS(WebElement element) {
        waitVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}