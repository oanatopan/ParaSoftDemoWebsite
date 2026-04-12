package pages;

import modelObject.OpenAccountModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @FindBy(xpath = "//div[@id='rightPanel']//p[contains(text(),'Congratulations')]")
    private WebElement confirmationMessage;

    @FindBy(id = "newAccountId")
    private WebElement newAccountId;

    public void goToOpenAccount() {
        elementsMethods.clickElement(openAccountLink);
        LogUtility.infoLog("The user navigates to the Open New Account page.");
    }

    public void openNewAccount(OpenAccountModel data) {
        selectMethods.selectByText(accountTypeDropdown, data.getAccountType());
        LogUtility.infoLog("The user selects account type: " + data.getAccountType());

        // REPARAT: wait explicit pana cand dropdown-ul fromAccountId are optiuni
        // Pagina Angular populeaza dropdown-ul async dupa selectarea tipului de cont
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                org.openqa.selenium.By.cssSelector("#fromAccountId option"), 0));
        LogUtility.infoLog("The user waits for the source account dropdown to be populated.");

        selectMethods.selectByIndex(fromAccountDropdown, 0);
        LogUtility.infoLog("The user selects the first available source account.");

        elementsMethods.clickElement(openAccountBtn);
        LogUtility.infoLog("The user clicks the Open New Account button.");

        // wait explicit dupa click - pagina e Angular async
        elementsMethods.waitVisible(newAccountId);
        LogUtility.infoLog("The user waits for the new account confirmation to appear.");
    }

    public boolean isAccountOpened(OpenAccountModel data) {
        String message = elementsMethods.getElementText(confirmationMessage);
        boolean result = message.contains(data.getSuccessMessage());

        if (result) {
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