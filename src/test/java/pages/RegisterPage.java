package pages;

import modelObject.RegisterModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(id = "customer.firstName")
    private WebElement firstNameField;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameField;

    @FindBy(id = "customer.address.street")
    private WebElement streetField;

    @FindBy(id = "customer.address.city")
    private WebElement cityField;

    @FindBy(id = "customer.address.state")
    private WebElement stateField;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipField;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneField;

    @FindBy(id = "customer.ssn")
    private WebElement ssnField;

    @FindBy(id = "customer.username")
    private WebElement usernameField;

    @FindBy(id = "customer.password")
    private WebElement passwordField;

    @FindBy(id = "repeatedPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@value='Register']")
    private WebElement registerBtn;

    @FindBy(xpath = "//*[contains(text(),'Your account was created successfully')]")
    private WebElement successMessage;

    @FindBy(linkText = "Log Out")
    private WebElement logOutLink;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void goToRegister() {
        elementsMethods.clickElement(registerLink);
    }

    public void registerUserUniq(String fName, String lName, String street, String city, String state,
                                 String zip, String phone, String ssn, String user, String pass) {

        elementsMethods.fillElement(firstNameField, fName);
        elementsMethods.fillElement(lastNameField, lName);
        elementsMethods.fillElement(streetField, street);
        elementsMethods.fillElement(cityField, city);
        elementsMethods.fillElement(stateField, state);
        elementsMethods.fillElement(zipField, zip);
        elementsMethods.fillElement(phoneField, phone);
        elementsMethods.fillElement(ssnField, ssn);
        elementsMethods.fillElement(usernameField, user);
        elementsMethods.fillElement(passwordField, pass);
        elementsMethods.fillElement(confirmPasswordField, pass);

        elementsMethods.clickElement(registerBtn);
    }

    public void registerUserUniq(RegisterModel data, String uniqueUsername, String overrideFirstName) {
        String firstNameToUse = (overrideFirstName != null && !overrideFirstName.trim().isEmpty())
                ? overrideFirstName
                : data.getFirstName();

        registerUserUniq(
                firstNameToUse,
                data.getLastName(),
                data.getAddress(),
                data.getCity(),
                data.getState(),
                data.getZipCode(),
                data.getPhone(),
                data.getSsn(),
                uniqueUsername,
                data.getPassword()
        );
    }

    public void registerUserUniq(RegisterModel data, String uniqueUsername) {
        registerUserUniq(data, uniqueUsername, null);
    }

    public boolean isRegistrationSuccessful() {
        return elementsMethods.isElementDisplayed(successMessage)
                || elementsMethods.isElementDisplayed(logOutLink);
    }
}