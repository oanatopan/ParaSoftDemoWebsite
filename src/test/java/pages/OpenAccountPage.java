package pages;

import modelObject.OpenAccountModel;
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
    private WebElement accountTypeDropdown;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountIdDropdown;

    @FindBy(xpath = "//input[@value='Open New Account']")
    private WebElement openAccountBtn;

    @FindBy(xpath = "//h1[text()='Account Opened!']")
    private WebElement accountOpenedMessage;

    @FindBy(id = "newAccountId")
    private WebElement newAccountId;

    public OpenAccountPage goToOpenAccount() {
        elementsMethods.clickElement(openAccountLink);
        elementsMethods.waitVisible(accountTypeDropdown);
        return this;
    }

    public OpenAccountPage openNewAccount(String type) {
        selectMethods.selectByText(accountTypeDropdown, type);
        elementsMethods.waitVisible(fromAccountIdDropdown);
        selectMethods.selectByIndex(fromAccountIdDropdown, 0);
        elementsMethods.clickElement(openAccountBtn);
        elementsMethods.waitVisible(accountOpenedMessage);
        elementsMethods.waitVisible(newAccountId);
        return this;
    }

    public OpenAccountPage openNewAccount(OpenAccountModel data) {
        return openNewAccount(data.getAccountType());
    }

    public boolean isAccountOpened() {
        return elementsMethods.isElementDisplayed(accountOpenedMessage);
    }

    public boolean isAccountOpened(OpenAccountModel data) {
        String actualText = elementsMethods.getElementText(accountOpenedMessage);
        return actualText != null && actualText.contains(data.getSuccessMessage());
    }

    public String getNewAccountIdText() {
        return elementsMethods.getElementText(newAccountId);
    }
}