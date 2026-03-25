package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class LoginModel {

    private User validUser;
    private User invalidUser;

    public LoginModel(String filePath) {
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

    public User getValidUser() { return validUser; }

    public void setValidUser(User validUser) { this.validUser = validUser; }

    public User getInvalidUser() { return invalidUser; }

    public void setInvalidUser(User invalidUser) { this.invalidUser = invalidUser; }

    public static class User {
        private String username;
        private String password;

        public String getUsername() { return username; }

        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }

        public void setPassword(String password) { this.password = password; }
    }
}