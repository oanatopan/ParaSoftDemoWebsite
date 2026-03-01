package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountsOverviewPage extends BasePage {

    @FindBy(linkText = "Accounts Overview")
    private WebElement accountsOverviewLink;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "accountTable")
    private WebElement accountTable;

    @FindBy(xpath = "//table[@id='accountTable']/tbody/tr[1]/td[1]/a")
    private WebElement firstAccountLink;

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void goToAccountsOverview() {
        // În Varianta 1 făceai elementToBeClickable -> click.
        // Aici ne bazăm pe clickElement + waitVisible din ElementsMethods.
        elementsMethods.clickElement(accountsOverviewLink);

        // Asigură că pagina e încărcată (title + tabel)
        waitForPageToLoad();
    }

    /**
     * Înlocuiește refreshUntilTableVisible() cu un wait predictibil.
     * Nu mai folosim refresh loops în Page Object.
     */
    public void waitForPageToLoad() {
        elementsMethods.waitVisible(pageTitle);
        elementsMethods.waitVisible(accountTable);
    }

    public void selectFirstAccount() {
        // tabelul trebuie să fie prezent înainte să selectăm contul
        elementsMethods.waitVisible(firstAccountLink);
        elementsMethods.clickElement(firstAccountLink);
    }

    public String getPageTitleText() {
        return elementsMethods.getElementText(pageTitle);
    }

    public boolean isTitleDisplayed() {
        return elementsMethods.isElementDisplayed(pageTitle);
    }
}