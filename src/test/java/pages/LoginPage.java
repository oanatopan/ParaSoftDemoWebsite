package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "username")
    private WebElement userField;

    @FindBy(name = "password")
    private WebElement passField;

    @FindBy(xpath = "//input[@value='Log In']")
    private WebElement loginBtn;

    @FindBy(xpath = "//p[@class='error']")
    private WebElement errorMsg;

    public void loginProcess(String u, String p) {
        elementsMethods.fillElement(userField, u);
        elementsMethods.fillElement(passField, p);
        elementsMethods.clickJS(loginBtn);
    }

    public boolean isErrorDisplayed() {
        return elementsMethods.isElementDisplayed(errorMsg);
    }
}