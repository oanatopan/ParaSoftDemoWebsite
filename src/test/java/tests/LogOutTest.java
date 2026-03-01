package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class LogOutTest extends SharedData {

    @Test
    public void automationTest() {

        registerPage.goToRegister();

        String userUnic = "logout" + System.currentTimeMillis();

        registerPage.registerUserUniq(
                "Oana",
                "Topan",
                "Republicii",
                "Baia Mare",
                "Romania",
                "123456",
                "0722000000",
                "123-45-678",
                userUnic,
                "Parola123!"
        );

        Assert.assertTrue(
                homePage.isLogOutVisible(),
                "Log Out button not visible after registration!"
        );

        homePage.clickLogOut();

        Assert.assertTrue(
                homePage.isLoginVisible(),
                "Login button not visible after logout!"
        );
    }
}