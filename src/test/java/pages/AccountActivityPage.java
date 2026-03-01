package pages;

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

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "transactionTable")
    private WebElement transactionTable;

    /**
     * Asigură că pagina Account Activity este încărcată
     * (echivalentul logic din Varianta 1 unde așteptai titlul)
     */
    public void waitForPageToLoad() {
        elementsMethods.waitVisible(pageTitle);
    }

    /**
     * Filtrează tranzacțiile după lună și tip
     */
    public void filterTransactions(String month, String type) {
        waitForPageToLoad();
        selectMethods.selectByText(monthDropdown, month);
        selectMethods.selectByText(typeDropdown, type);
        elementsMethods.clickElement(goBtn);
    }

    public boolean isActivityContentPresent() {
        return elementsMethods.isElementDisplayed(pageTitle);
    }

    public String getPageTitleText() {
        return elementsMethods.getElementText(pageTitle);
    }

    public boolean isTransactionTableDisplayed() {
        return elementsMethods.isElementDisplayed(transactionTable);
    }
}