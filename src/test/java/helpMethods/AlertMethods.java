package helpMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AlertMethods {
    private WebDriver driver;
    public AlertMethods(WebDriver driver) { this.driver = driver; }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }
}