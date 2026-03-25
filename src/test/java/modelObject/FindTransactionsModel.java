package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class FindTransactionsModel {

    private String transactionType;
    private String dateRange;
    private String expectedTitle;
    private String amount;

    public FindTransactionsModel(String filePath) {
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

    public String getTransactionType() { return transactionType; }

    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public String getDateRange() { return dateRange; }

    public void setDateRange(String dateRange) { this.dateRange = dateRange; }

    public String getExpectedTitle() { return expectedTitle; }

    public void setExpectedTitle(String expectedTitle) { this.expectedTitle = expectedTitle; }

    public String getAmount() { return amount; }

    public void setAmount(String amount) { this.amount = amount; }
}