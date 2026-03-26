package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtility;

public class FindTransactionsPage extends BasePage {

    public FindTransactionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Find Transactions']")
    private WebElement findTransactionsLink;

    @FindBy(id = "accountId")
    private WebElement accountIdDropdown;

    @FindBy(id = "amount")
    private WebElement amountField;

    @FindBy(id = "findByAmount")
    private WebElement findByAmountBtn;

    @FindBy(id = "transactionTable")
    private WebElement transactionTable;

    @FindBy(xpath = "//*[contains(text(),'No transactions found')]")
    private WebElement noTransactionsMessage;

    public void goToFindTransactions() {
        elementsMethods.clickElement(findTransactionsLink);
        LogUtility.infoLog("The user clicks on the Find Transactions link from the side menu");

        elementsMethods.waitVisible(accountIdDropdown);
        elementsMethods.waitVisible(amountField);
        LogUtility.infoLog("The user waits for the transactions search fields to be visible");
    }

    public void filterTransactionsByAmount(String amount) {
        elementsMethods.fillElement(amountField, amount);
        LogUtility.infoLog("The user fills the search amount field with: " + amount);

        elementsMethods.clickElement(findByAmountBtn);
        LogUtility.infoLog("The user clicks on the Find By Amount button");
    }

    public boolean isTransactionTableDisplayed() {
        boolean isDisplayed = elementsMethods.isElementDisplayed(transactionTable);
        if (isDisplayed) {
            LogUtility.infoLog("The user validates that the transaction table is displayed");
        }
        return isDisplayed;
    }

    public boolean isNoTransactionsMessageDisplayed() {
        boolean isDisplayed = elementsMethods.isElementDisplayed(noTransactionsMessage);
        if (isDisplayed) {
            LogUtility.infoLog("The user validates that the 'No transactions found' message is displayed");
        }
        return isDisplayed;
    }

    public boolean isSearchResultDisplayed() {
        LogUtility.infoLog("The user checks if any search result (table or no-transactions message) is visible");
        return isTransactionTableDisplayed() || isNoTransactionsMessageDisplayed();
    }

    public boolean isFindTransactionsPageLoaded() {
        boolean isLoaded = elementsMethods.isElementDisplayed(accountIdDropdown)
                && elementsMethods.isElementDisplayed(amountField);

        if (isLoaded) {
            LogUtility.infoLog("The user validates that the Find Transactions page loaded correctly");
        } else {
            LogUtility.errorLog("The Find Transactions page failed to load required search fields");
        }
        return isLoaded;
    }
}