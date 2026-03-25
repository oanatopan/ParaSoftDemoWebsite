package pages;

import modelObject.LoginModel;
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

    @FindBy(xpath = "//*[contains(text(),'The username and password could not be verified.')]")
    private WebElement errorMsg;

    public void loginProcess(String username, String password) {
        elementsMethods.fillElement(userField, username);
        elementsMethods.fillElement(passField, password);
        elementsMethods.clickJS(loginBtn);
    }

    public void loginInvalid(LoginModel data) {
        loginProcess(
                data.getInvalidUser().getUsername(),
                data.getInvalidUser().getPassword()
        );
    }

    public boolean isErrorDisplayed() {
        return elementsMethods.isElementDisplayed(errorMsg);
    }
}