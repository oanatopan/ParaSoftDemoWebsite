package pages;

import modelObject.BillPayModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BillPayPage extends BasePage {

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Bill Pay']")
    private WebElement billPayLink;

    @FindBy(name = "payee.name")
    private WebElement payeeNameField;

    @FindBy(name = "payee.address.street")
    private WebElement addressField;

    @FindBy(name = "payee.address.city")
    private WebElement cityField;

    @FindBy(name = "payee.address.state")
    private WebElement stateField;

    @FindBy(name = "payee.address.zipCode")
    private WebElement zipField;

    @FindBy(name = "payee.phoneNumber")
    private WebElement phoneField;

    @FindBy(name = "payee.accountNumber")
    private WebElement accountField;

    @FindBy(name = "verifyAccount")
    private WebElement verifyAccountField;

    @FindBy(name = "amount")
    private WebElement amountField;

    @FindBy(name = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Send Payment']")
    private WebElement sendPaymentBtn;

    @FindBy(id = "billpayResult")
    private WebElement billpayResult;

    public BillPayPage goToBillPay() {
        elementsMethods.clickElement(billPayLink);
        return this;
    }

    public BillPayPage payBill(BillPayModel data) {
        elementsMethods.fillElement(payeeNameField, data.getPayeeName());
        elementsMethods.fillElement(addressField, data.getAddress());
        elementsMethods.fillElement(cityField, data.getCity());
        elementsMethods.fillElement(stateField, data.getState());
        elementsMethods.fillElement(zipField, data.getZipCode());
        elementsMethods.fillElement(phoneField, data.getPhone());
        elementsMethods.fillElement(accountField, data.getAccount());
        elementsMethods.fillElement(verifyAccountField, data.getVerifyAccount());
        elementsMethods.fillElement(amountField, data.getAmount());

        selectMethods.selectByIndex(fromAccountDropdown, 0);

        elementsMethods.clickElement(sendPaymentBtn);
        elementsMethods.waitVisible(billpayResult);

        return this;
    }

    public boolean isPaymentSuccessful() {
        return elementsMethods.getElementText(billpayResult)
                .toLowerCase()
                .contains("bill payment complete");
    }
}