package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import shareData.SharedData;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof SharedData) {
            // Aici apelăm metoda de screenshot din SharedData
            ((SharedData) currentClass).saveScreenshot();
        }
    }
}