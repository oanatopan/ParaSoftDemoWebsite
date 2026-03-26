package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BillPayModel {

    private String payeeName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String account;
    private String verifyAccount;
    private String amount;
    private String fromAccountId;


    public BillPayModel(String filePath) {
        loadFromJson(filePath);
    }

    public void loadFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readerForUpdating(this)
                    .readValue(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Nu s-a putut încărca JSON-ul de la calea: " + filePath, e);
        }
    }

    public String getPayeeName() { return payeeName; }

    public void setPayeeName(String payeeName) { this.payeeName = payeeName; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }

    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getAccount() { return account; }

    public void setAccount(String account) { this.account = account; }

    public String getVerifyAccount() { return verifyAccount; }

    public void setVerifyAccount(String verifyAccount) { this.verifyAccount = verifyAccount; }

    public String getAmount() { return amount; }

    public void setAmount(String amount) { this.amount = amount; }

    public String getFromAccountId() { return fromAccountId; }

    public void setFromAccountId(String fromAccountId) { this.fromAccountId = fromAccountId; }
}