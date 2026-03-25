package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return this;
    }

    public boolean isLoginVisible() {
        try {
            elementsMethods.waitVisible(loginBtn);
            return loginBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogOutVisible() {
        try {
            elementsMethods.waitVisible(logOutLink);
            return logOutLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}