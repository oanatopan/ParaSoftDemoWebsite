package pages;

import helpMethods.ElementsMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsOverviewPage extends BasePage {
    private ElementsMethods elementsMethods;

    @FindBy(linkText = "Accounts Overview")
    private WebElement accountsOverviewLink;

    @FindBy(id = "accountTable")
    private WebElement accountTable;

    @FindBy(xpath = "//table[@id='accountTable']/tbody/tr[1]/td[1]/a")
    private WebElement firstAccountLink;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.elementsMethods = new ElementsMethods(driver);
    }

    public void goToAccountsOverview() {
        elementsMethods.clickElement(accountsOverviewLink);
    }

    public void refreshUntilTableVisible() {
        for (int i = 0; i < 3; i++) {
            if (!elementsMethods.isElementDisplayed(accountTable)) {
                driver.navigate().refresh();
            } else {
                break;
            }
        }
    }

    public void selectFirstAccount() {
        elementsMethods.clickElement(firstAccountLink);
    }

    public String getPageTitleText() {
        return elementsMethods.getElementText(pageTitle);
    }

    public boolean isTitleDisplayed() {
        return elementsMethods.isElementDisplayed(pageTitle);
    }
}