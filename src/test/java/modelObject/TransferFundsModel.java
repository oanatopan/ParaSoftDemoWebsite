package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TransferFundsModel {

    private String amount;
    private String successMessage;

    public TransferFundsModel(String filePath) {
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

    public String getAmount() { return amount; }

    public void setAmount(String amount) { this.amount = amount; }

    public String getSuccessMessage() { return successMessage; }

    public void setSuccessMessage(String successMessage) { this.successMessage = successMessage; }
}