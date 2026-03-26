package pages;

import modelObject.OpenAccountModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtility;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Open New Account")
    private WebElement openAccountLink;

    @FindBy(id = "type")
    private WebElement accountTypeDropdown;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Open New Account']")
    private WebElement openAccountBtn;

    @FindBy(xpath = "//h1[contains(text(),'Account Opened')]")
    private WebElement successMessage;

    @FindBy(id = "newAccountId")
    private WebElement newAccountId;

    public void goToOpenAccount() {
        elementsMethods.clickElement(openAccountLink);
        LogUtility.infoLog("The user navigates to the Open New Account page.");
    }

    public void openNewAccount(OpenAccountModel data) {

        selectMethods.selectByText(accountTypeDropdown, data.getAccountType());
        LogUtility.infoLog("The user selects account type: " + data.getAccountType());

        selectMethods.selectByIndex(fromAccountDropdown, 0);
        LogUtility.infoLog("The user selects the first available source account.");

        elementsMethods.clickElement(openAccountBtn);
        LogUtility.infoLog("The user clicks the Open New Account button.");
    }

    public boolean isAccountOpened(OpenAccountModel data) {
        String message = elementsMethods.getElementText(successMessage);
        boolean result = message.contains(data.getSuccessMessage());

        if(result){
            LogUtility.infoLog("The account was successfully opened.");
        } else {
            LogUtility.errorLog("Account opening confirmation message is incorrect: " + message);
        }

        return result;
    }

    public String getNewAccountIdText() {
        String accountId = elementsMethods.getElementText(newAccountId);
        LogUtility.infoLog("New account ID generated: " + accountId);
        return accountId;
    }
}