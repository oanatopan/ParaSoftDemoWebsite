package pages;

import modelObject.RegisterModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtility;

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
        LogUtility.infoLog("The user clicks on Register link from the top menu");
    }

    public void registerUserUniq(String fName, String lName, String street, String city, String state,
                                 String zip, String phone, String ssn, String user, String pass) {

        elementsMethods.fillElement(firstNameField, fName);
        LogUtility.infoLog("The user fills first name field with value: " + fName);
        elementsMethods.fillElement(lastNameField, lName);
        LogUtility.infoLog("The user fills last name field with value: " + lName);
        elementsMethods.fillElement(streetField, street);
        LogUtility.infoLog("The user fills street address field with value: " + street);
        elementsMethods.fillElement(cityField, city);
        LogUtility.infoLog("The user fills city field with value: " + city);
        elementsMethods.fillElement(stateField, state);
        LogUtility.infoLog("The user fills state field with value: " + state);
        elementsMethods.fillElement(zipField, zip);
        LogUtility.infoLog("The user fills zip code field with value: " + zip);
        elementsMethods.fillElement(phoneField, phone);
        LogUtility.infoLog("The user fills phone number field with value: " + phone);
        elementsMethods.fillElement(ssnField, ssn);
        LogUtility.infoLog("The user fills SSN field with value: " + ssn);
        elementsMethods.fillElement(usernameField, user);
        LogUtility.infoLog("The user fills username field with value: " + user);
        elementsMethods.fillElement(passwordField, pass);
        LogUtility.infoLog("The user fills password field with value: " + pass);
        elementsMethods.fillElement(confirmPasswordField, pass);
        LogUtility.infoLog("The user fills confirm password field with value: " + pass);

        elementsMethods.clickElement(registerBtn);
        LogUtility.infoLog("The user clicks on Register button to submit the form");
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
        boolean isSuccess = elementsMethods.isElementDisplayed(successMessage) ||
                elementsMethods.isElementDisplayed(logOutLink);

        if (isSuccess) {
            LogUtility.infoLog("The user validates that the registration was successful");
        } else {
            LogUtility.errorLog("Registration failed!");
        }

        return isSuccess;
    }
}