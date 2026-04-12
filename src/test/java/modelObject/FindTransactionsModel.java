package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FindTransactionsModel {

    private String transactionType;
    private String dateRange;
    private String expectedTitle;
    private String amount;

    public FindTransactionsModel(String filePath) {
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
    public String getTransactionType()
    { return transactionType; }

    public void setTransactionType(String transactionType)
    { this.transactionType = transactionType; }

    public String getDateRange()
    { return dateRange; }

    public void setDateRange(String dateRange)
    { this.dateRange = dateRange; }

    public String getExpectedTitle()
    { return expectedTitle; }

    public void setExpectedTitle(String expectedTitle)
    { this.expectedTitle = expectedTitle; }

    public String getAmount()
    { return amount; }

    public void setAmount(String amount)
    { this.amount = amount; }
}