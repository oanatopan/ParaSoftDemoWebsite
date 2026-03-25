package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class LoanModel {

    private String loanAmount;
    private String downPayment;
    private String approvedText;
    private String deniedText;

    public LoanModel(String filePath) {
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

    public String getLoanAmount() { return loanAmount; }

    public void setLoanAmount(String loanAmount) { this.loanAmount = loanAmount; }

    public String getDownPayment() { return downPayment; }

    public void setDownPayment(String downPayment) { this.downPayment = downPayment; }

    public String getApprovedText() { return approvedText; }

    public void setApprovedText(String approvedText) { this.approvedText = approvedText; }

    public String getDeniedText() { return deniedText; }

    public void setDeniedText(String deniedText) { this.deniedText = deniedText; }
}