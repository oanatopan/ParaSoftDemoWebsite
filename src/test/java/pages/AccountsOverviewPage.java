package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//table[@id='accountTable']//a")
    private WebElement firstAccountLink;

    public void goToAccountsOverview() {
        elementsMethods.clickElement(accountsOverviewLink);
        waitForPageToLoad();
    }

    public void waitForPageToLoad() {
        elementsMethods.waitVisible(accountsOverviewTitle);
        elementsMethods.waitVisible(accountTable);
    }

    public void selectFirstAccount() {
        elementsMethods.clickElement(firstAccountLink);
    }

    public boolean isTitleDisplayed() {
        return elementsMethods.isElementDisplayed(accountsOverviewTitle);
    }

    public boolean isAccountTableDisplayed() {
        return elementsMethods.isElementDisplayed(accountTable);
    }
}