package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenAccountPage extends BasePage {
    public OpenAccountPage(WebDriver driver) { super(driver); }

    @FindBy(linkText = "Open New Account") private WebElement openAccountLink;
    @FindBy(id = "type") private WebElement accountType;
    @FindBy(id = "fromAccountId") private WebElement fromAccount;
    @FindBy(xpath = "//select[@id='fromAccountId']/option") private WebElement accountOptions;
    @FindBy(xpath = "//input[@value='Open New Account']") private WebElement openAccountBtn;
    @FindBy(id = "openAccountResult") private WebElement result;

    public void goToOpenAccount() { elementsMethods.clickElement(openAccountLink); }

    public void openNewAccount(String type) {
        selectMethods.selectByText(accountType, type);
        elementsMethods.waitVisible(accountOptions);
        elementsMethods.clickElement(openAccountBtn);
    }

    public boolean isSuccessDisplayed() { return elementsMethods.isElementDisplayed(result); }
}