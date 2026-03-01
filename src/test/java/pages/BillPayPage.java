package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BillPayPage extends BasePage {
    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Bill Pay") private WebElement billPayLink;
    @FindBy(name = "payee.name") private WebElement payeeNameField;
    @FindBy(name = "payee.address.street") private WebElement addressField;
    @FindBy(name = "payee.address.city") private WebElement cityField;
    @FindBy(name = "payee.address.state") private WebElement stateField;
    @FindBy(name = "payee.address.zipCode") private WebElement zipField;
    @FindBy(name = "payee.phoneNumber") private WebElement phoneField;
    @FindBy(name = "payee.accountNumber") private WebElement accountField;
    @FindBy(name = "verifyAccount") private WebElement verifyAccountField;
    @FindBy(name = "amount") private WebElement amountField;
    @FindBy(name = "fromAccountId") private WebElement fromAccountDropdown;
    @FindBy(xpath = "//input[@value='Send Payment']") private WebElement sendPaymentBtn;
    @FindBy(id = "billpayResult") private WebElement billpayResult;

    public BillPayPage goToBillPay() {
        elementsMethods.clickElement(billPayLink);
        return this;
    }

    // ✅ selectăm primul cont disponibil, ca în Varianta 1 (index 0)
    public BillPayPage payBill(String name, String address, String city, String state, String zip,
                               String phone, String acc, String vAcc, String amount) {

        elementsMethods.fillElement(payeeNameField, name);
        elementsMethods.fillElement(addressField, address);
        elementsMethods.fillElement(cityField, city);
        elementsMethods.fillElement(stateField, state);
        elementsMethods.fillElement(zipField, zip);
        elementsMethods.fillElement(phoneField, phone);
        elementsMethods.fillElement(accountField, acc);
        elementsMethods.fillElement(verifyAccountField, vAcc);
        elementsMethods.fillElement(amountField, amount);

        selectMethods.selectByIndex(fromAccountDropdown, 0);

        elementsMethods.clickElement(sendPaymentBtn);
        elementsMethods.waitVisible(billpayResult);
        return this;
    }

    public boolean isPaymentSuccessful() {
        return elementsMethods.getElementText(billpayResult).contains("Bill Payment Complete");
    }
}