package pages;

import helpMethods.ElementsMethods;
import helpMethods.SelectMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtility;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ElementsMethods elementsMethods;
    protected SelectMethods selectMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.elementsMethods = new ElementsMethods(driver, wait);
        this.selectMethods = new SelectMethods();

        PageFactory.initElements(driver, this);
        LogUtility.infoLog("The Page Factory has initialized elements for: " + this.getClass().getSimpleName());
    }
}