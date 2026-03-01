package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindTransactionsPage extends BasePage {
    public FindTransactionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Find Transactions") private WebElement findTransactionsLink;
    @FindBy(id = "criteria.amount") private WebElement amountField;
    @FindBy(xpath = "//button[@ng-click=\"find('AMOUNT')\"]") private WebElement findByAmountBtn;
    @FindBy(id = "transactionTable") private WebElement transactionTable;

    public void goToFindTransactions() {
        elementsMethods.clickElement(findTransactionsLink);
        elementsMethods.waitVisible(amountField);
    }

    public void filterTransactionsByAmount(String amount) {
        elementsMethods.fillElement(amountField, amount);
        elementsMethods.clickElement(findByAmountBtn);
    }

    public boolean isTransactionTableDisplayed() {
        return elementsMethods.isElementDisplayed(transactionTable);
    }
}