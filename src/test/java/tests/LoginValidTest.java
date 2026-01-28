package tests;

import org.testng.annotations.Test;
import pages.RegisterPage;
import shareData.SharedData;

public class LoginValidTest extends SharedData {

    @Test
    public void metodaTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.goToRegister();
        String userUnic = "user" + System.currentTimeMillis();

        registerPage.registerUserUniq("Oana", "Topan", "Republicii", "Baia Mare", "Romania", "123456", "0722000000","123-45-678",userUnic, "Parola123!");

        System.out.println("SUCCES: User " + userUnic + " was created in LoginValidTest.");
    }
}