package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtility;

public class AccountsOverviewPage extends BasePage {

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Accounts Overview")
    private WebElement accountsOverviewLink;

    @FindBy(xpath = "//h1[normalize-space()='Accounts Overview']")
    private WebElement accountsOverviewTitle;

    @FindBy(id = "accountTable")
    private WebElement accountTable;

    // REPARAT: wait explicit inainte de getText() — tabelul se incarca async
    @FindBy(xpath = "//table[@id='accountTable']/tbody/tr[1]/td[1]/a")
    private WebElement firstAccountLink;

    public void goToAccountsOverview() {
        elementsMethods.clickElement(accountsOverviewLink);
        LogUtility.infoLog("The user clicks on the Accounts Overview link from the side menu");
        waitForPageToLoad();
    }

    public void waitForPageToLoad() {
        elementsMethods.waitVisible(accountsOverviewTitle);
        elementsMethods.waitVisible(accountTable);
        // REPARAT: asteptam si primul link din tabel — se populeaza async
        elementsMethods.waitVisible(firstAccountLink);
        LogUtility.infoLog("The user waits for the Accounts Overview table to be fully loaded");
    }

    public void selectFirstAccount() {
        String accountId = firstAccountLink.getText();
        elementsMethods.clickElement(firstAccountLink);
        LogUtility.infoLog("The user selects the first account from the list with ID: " + accountId);
    }

    public boolean isTitleDisplayed() {
        boolean isDisplayed = elementsMethods.isElementDisplayed(accountsOverviewTitle);
        if (isDisplayed) {
            LogUtility.infoLog("The user validates that the 'Accounts Overview' title is visible");
        }
        return isDisplayed;
    }

    public boolean isAccountTableDisplayed() {
        boolean isDisplayed = elementsMethods.isElementDisplayed(accountTable);
        if (isDisplayed) {
            LogUtility.infoLog("The user validates that the accounts table is displayed correctly");
        } else {
            LogUtility.errorLog("The accounts table was not found on the page");
        }
        return isDisplayed;
    }
}