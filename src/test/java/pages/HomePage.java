package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtility;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Log Out")
    private WebElement logOutLink;

    @FindBy(xpath = "//input[@value='Log In']")
    private WebElement loginBtn;

    public HomePage clickLogOut() {
        elementsMethods.clickElement(logOutLink);
        LogUtility.infoLog("The user clicks on the Log Out link to terminate the session");
        return this;
    }

    public boolean isLoginVisible() {
        boolean isVisible = elementsMethods.isElementDisplayed(loginBtn);
        if (isVisible) {
            LogUtility.infoLog("The user validates that the Login button is visible on the Home Page");
        } else {
            LogUtility.errorLog("The Login button was not found or is not visible");
        }
        return isVisible;
    }

    public boolean isLogOutVisible() {
        boolean isVisible = elementsMethods.isElementDisplayed(logOutLink);
        if (isVisible) {
            LogUtility.infoLog("The user validates that the Log Out link is visible (User is logged in)");
        } else {
            LogUtility.errorLog("The Log Out link was not found; the user might not be logged in");
        }
        return isVisible;
    }
}