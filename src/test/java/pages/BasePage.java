package pages;

import helpMethods.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected ElementsMethods elementsMethods;
    protected AlertMethods alertMethods;
    protected SelectMethods selectMethods;
    protected TabMethods tabMethods;
    protected FrameMethods frameMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.elementsMethods = new ElementsMethods(driver);
        this.alertMethods = new AlertMethods(driver);
        this.frameMethods = new FrameMethods(driver);
        this.selectMethods = new SelectMethods(driver);
        this.tabMethods = new TabMethods(driver);
        PageFactory.initElements(driver, this);
    }

}