package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shareData.SharedData;

public class LoginInvalidTest extends SharedData {

    @Test
    public void automationTest() {

        loginPage.loginProcess("utilizator_inexistent", "parola123");

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message not displayed for invalid login!"
        );
    }
}