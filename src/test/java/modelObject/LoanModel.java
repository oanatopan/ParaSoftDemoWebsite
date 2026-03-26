package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
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

    public void loadFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readerForUpdating(this)
                    .readValue(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Nu s-a putut încărca JSON-ul de la calea: " + filePath, e);
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