package pages;

import modelObject.FindTransactionsModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountActivityPage extends BasePage {

    public AccountActivityPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "month")
    private WebElement monthDropdown;

    @FindBy(id = "transactionType")
    private WebElement typeDropdown;

    @FindBy(xpath = "//input[@value='Go']")
    private WebElement goBtn;

    @FindBy(id = "transactionTable")
    private WebElement transactionTable;

    @FindBy(xpath = "//*[contains(text(),'No transactions found')]")
    private WebElement noTransactionsMessage;

    public void waitForPageToLoad() {
        elementsMethods.waitVisible(monthDropdown);
        elementsMethods.waitVisible(typeDropdown);
    }

    public void filterTransactions(String month, String type) {
        waitForPageToLoad();
        selectMethods.selectByText(monthDropdown, month);
        selectMethods.selectByText(typeDropdown, type);
        elementsMethods.clickElement(goBtn);
    }

    public void filterTransactions(FindTransactionsModel data) {
        filterTransactions(data.getDateRange(), data.getTransactionType());
    }

    public boolean isActivityContentPresent() {
        return elementsMethods.isElementDisplayed(monthDropdown)
                && elementsMethods.isElementDisplayed(typeDropdown);
    }

    public boolean isTransactionTableDisplayed() {
        return elementsMethods.isElementDisplayed(transactionTable);
    }

    public boolean isNoTransactionsMessageDisplayed() {
        return elementsMethods.isElementDisplayed(noTransactionsMessage);
    }

    public boolean isTransactionSectionDisplayed() {
        return isTransactionTableDisplayed() || isNoTransactionsMessageDisplayed();
    }
}