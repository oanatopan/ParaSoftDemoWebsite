package pages;

import modelObject.BillPayModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtility;

public class BillPayPage extends BasePage {

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Bill Pay")
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
        LogUtility.infoLog("The user clicks on the Bill Pay link from the side menu");
        return this;
    }

    public BillPayPage payBill(BillPayModel data) {
        elementsMethods.fillElement(payeeNameField, data.getPayeeName());
        LogUtility.infoLog("The user fills payee name: " + data.getPayeeName());

        elementsMethods.fillElement(addressField, data.getAddress());
        LogUtility.infoLog("The user fills payee address: " + data.getAddress());

        elementsMethods.fillElement(cityField, data.getCity());
        LogUtility.infoLog("The user fills payee city: " + data.getCity());

        elementsMethods.fillElement(stateField, data.getState());
        LogUtility.infoLog("The user fills payee state: " + data.getState());

        elementsMethods.fillElement(zipField, data.getZipCode());
        LogUtility.infoLog("The user fills payee zip code: " + data.getZipCode());

        elementsMethods.fillElement(phoneField, data.getPhone());
        LogUtility.infoLog("The user fills payee phone: " + data.getPhone());

        elementsMethods.fillElement(accountField, data.getAccount());
        LogUtility.infoLog("The user fills payee account number: " + data.getAccount());

        elementsMethods.fillElement(verifyAccountField, data.getVerifyAccount());
        LogUtility.infoLog("The user fills the account verification field");

        elementsMethods.fillElement(amountField, data.getAmount());
        LogUtility.infoLog("The user fills the payment amount: " + data.getAmount());

        selectMethods.selectByIndex(fromAccountDropdown, 0);
        LogUtility.infoLog("The user selects the first available source account for the bill payment");

        elementsMethods.clickElement(sendPaymentBtn);
        LogUtility.infoLog("The user clicks on the Send Payment button");

        elementsMethods.waitVisible(billpayResult);
        return this;
    }

    public boolean isPaymentSuccessful() {
        String resultText = elementsMethods.getElementText(billpayResult);
        boolean isSuccess = resultText.toLowerCase().contains("bill payment complete");

        if (isSuccess) {
            LogUtility.infoLog("The user validates that the bill payment was completed successfully");
            System.out.println("Bill Pay Result: " + resultText);
        } else {
            LogUtility.errorLog("The bill payment success message was not found or is incorrect");
        }
        return isSuccess;
    }
}