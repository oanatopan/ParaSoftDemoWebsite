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
        try {
            elementsMethods.waitVisible(errorMsg);
            return errorMsg.isDisplayed();
        } catch (Exception e) {
            System.out.println("DEBUG: Mesajul de eroare nu a apărut în timpul setat.");
            return false;
        }
    }
}