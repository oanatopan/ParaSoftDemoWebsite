package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class OpenAccountModel {

    private String accountType;
    private String successMessage;

    public OpenAccountModel(String filePath) {
        loadFromJson(filePath);
    }

    public void loadFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readerForUpdating(this).readValue(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Nu s-a putut incarca JSON-ul de la calea: " + filePath, e);
        }
    }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getSuccessMessage() { return successMessage; }
    public void setSuccessMessage(String successMessage) { this.successMessage = successMessage; }
}