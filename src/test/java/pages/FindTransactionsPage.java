package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        elementsMethods.waitVisible(accountIdDropdown);
        elementsMethods.waitVisible(amountField);
    }

    public void filterTransactionsByAmount(String amount) {
        elementsMethods.fillElement(amountField, amount);
        elementsMethods.clickElement(findByAmountBtn);
    }

    public boolean isTransactionTableDisplayed() {
        return elementsMethods.isElementDisplayed(transactionTable);
    }

    public boolean isNoTransactionsMessageDisplayed() {
        return elementsMethods.isElementDisplayed(noTransactionsMessage);
    }

    public boolean isSearchResultDisplayed() {
        return isTransactionTableDisplayed() || isNoTransactionsMessageDisplayed();
    }

    public boolean isFindTransactionsPageLoaded() {
        return elementsMethods.isElementDisplayed(accountIdDropdown)
                && elementsMethods.isElementDisplayed(amountField);
    }
}