package pages;

import modelObject.LoginModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtility;

import java.time.Duration;

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

    public void loginProcess(String u, String p) {
        elementsMethods.fillElement(userField, u);
        LogUtility.infoLog("The user fills the username field with value: " + u);

        elementsMethods.fillElement(passField, p);
        LogUtility.infoLog("The user fills the password field with value: " + p);

        elementsMethods.clickElement(loginBtn);
        LogUtility.infoLog("The user clicks on the Log In button.");
    }

    public void loginInvalid(LoginModel data) {
        LogUtility.infoLog("The user starts the invalid login process using data from the model");
        loginProcess(
                data.getInvalidUser().getUsername(),
                data.getInvalidUser().getPassword()
        );
    }

    public boolean isErrorDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            boolean errorFound = wait.until((ExpectedCondition<Boolean>) webDriver -> {
                String pageText = webDriver.getPageSource();
                return pageText.contains("The username and password could not be verified")
                        || pageText.contains("Please enter a username and password")
                        || pageText.contains("Error!");
            });

            if (errorFound) {
                LogUtility.infoLog("The login error message is displayed on the page.");
                return true;
            }

            LogUtility.errorLog("The login error message was not found in the page source.");
            return false;
        } catch (Exception e) {
            LogUtility.errorLog("The login error message was not displayed");
            return false;
        }
    }
}