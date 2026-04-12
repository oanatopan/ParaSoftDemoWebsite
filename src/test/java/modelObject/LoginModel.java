package modelObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LoginModel {

    private User validUser;
    private User invalidUser;

    public LoginModel(String filePath) {
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