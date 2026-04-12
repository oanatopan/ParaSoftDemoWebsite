package helpMethods;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtility;

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
        wait.until(ExpectedConditions.elementToBeClickable(element));
        LogUtility.infoLog("The user clicks on the element: " + element);
        element.click();
    }

    public void fillElement(WebElement element, String value) {
        waitVisible(element);
        element.clear();
        element.sendKeys(value);
        LogUtility.infoLog("The user fills the field with value: " + value);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            waitVisible(element);
            boolean isDisplayed = element.isDisplayed();
            LogUtility.infoLog("The user checks if the element is displayed. Result: " + isDisplayed);
            return isDisplayed;
        } catch (TimeoutException | StaleElementReferenceException e) {
            LogUtility.errorLog("The element is not displayed or has become stale.");
            return false;
        }
    }

    public String getElementText(WebElement element) {
        try {
            waitVisible(element);
            String text = element.getText();
            LogUtility.infoLog("The user retrieves the text from the element: " + text);
            return text;
        } catch (TimeoutException | StaleElementReferenceException e) {
            LogUtility.errorLog("Could not retrieve text from the element.");
            return "";
        }

    }
}