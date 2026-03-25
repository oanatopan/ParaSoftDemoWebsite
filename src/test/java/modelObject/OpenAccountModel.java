package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class OpenAccountModel {

    private String accountType;
    private String successMessage;

    public OpenAccountModel(String filePath) {
        loadFromJson(filePath);
    }

    private void loadFromJson(String filePath) {
        try (InputStream inputStream =
                     getClass().getClassLoader().getResourceAsStream(filePath)) {

            if (inputStream == null) {
                throw new RuntimeException("File not found in resources: " + filePath);
            }

            new ObjectMapper().readerForUpdating(this).readValue(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load data from: " + filePath, e);
        }
    }
    public String getAccountType() { return accountType; }

    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getSuccessMessage() { return successMessage; }

    public void setSuccessMessage(String successMessage) { this.successMessage = successMessage; }
}