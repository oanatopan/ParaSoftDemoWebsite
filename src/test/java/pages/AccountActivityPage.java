
package pages;

import modelObject.FindTransactionsModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.LogUtility;

public class AccountActivityPage extends BasePage {

    public AccountActivityPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='rightPanel']//h1")
    private WebElement pageTitle;

    @FindBy(id = "month")
    private WebElement monthDropdown;

    @FindBy(id = "transactionType")
    private WebElement typeDropdown;

    @FindBy(xpath = "//input[@value='Go']")
    private WebElement goBtn;

    @FindBy(id = "transactionTable")
    private WebElement transactionTable;

    @FindBy(id = "noTransactions")
    private WebElement noTransactionsMessage;

    public void waitForPageToLoad() {
        elementsMethods.waitVisible(monthDropdown);
        elementsMethods.waitVisible(typeDropdown);
    }

    public void filterTransactions(String month, String type) {
        waitForPageToLoad();
        selectMethods.selectByText(monthDropdown, month);
        LogUtility.infoLog("The user selects " + month + " value from the month drop down");
        selectMethods.selectByText(typeDropdown, type);
        LogUtility.infoLog("The user selects " + type + " value from the transaction type drop down");
        elementsMethods.clickElement(goBtn);
        LogUtility.infoLog("The user clicks on the Go button");

        // ADAUGAT: wait dupa click Go - pagina se reincarca async (Angular)
        // Asteptam ca fie tabelul, fie mesajul "no transactions" sa devina vizibil
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(transactionTable),
                ExpectedConditions.visibilityOf(noTransactionsMessage)
        ));
        LogUtility.infoLog("The user waits for the transaction results to load after applying filter");
    }

    public void filterTransactions(FindTransactionsModel data) {
        filterTransactions(data.getDateRange(), data.getTransactionType());
    }

    public boolean isPageTitleCorrect(FindTransactionsModel data) {
        String actualTitle = elementsMethods.getElementText(pageTitle);
        boolean isCorrect = actualTitle.contains(data.getExpectedTitle());
        if (isCorrect) {
            LogUtility.infoLog("The user validates that the page title is correct: " + data.getExpectedTitle());
        } else {
            LogUtility.errorLog("The page title is incorrect. Actual title: " + actualTitle);
        }
        return isCorrect;
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