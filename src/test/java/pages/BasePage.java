package pages;

import helpMethods.ElementsMethods;
import helpMethods.SelectMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ElementsMethods elementsMethods;
    protected SelectMethods selectMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        elementsMethods = new ElementsMethods(driver, wait);
        selectMethods = new SelectMethods(driver);

        PageFactory.initElements(driver, this);
    }
}