package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Open New Account")
    private WebElement openAccountLink;

    @FindBy(id = "type")
    private WebElement accountType;

    // dropdown complet, nu option
    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Open New Account']")
    private WebElement openAccountBtn;

    @FindBy(id = "openAccountResult")
    private WebElement result;

    public OpenAccountPage goToOpenAccount() {
        elementsMethods.clickElement(openAccountLink);
        return this;
    }

    public OpenAccountPage openNewAccount(String type) {

        selectMethods.selectByText(accountType, type);

        // sincronizare robustă: dropdown vizibil
        elementsMethods.waitVisible(fromAccountDropdown);

        elementsMethods.clickElement(openAccountBtn);

        elementsMethods.waitVisible(result);

        return this;
    }

    public boolean isSuccessDisplayed() {
        return elementsMethods.isElementDisplayed(result);
    }
}