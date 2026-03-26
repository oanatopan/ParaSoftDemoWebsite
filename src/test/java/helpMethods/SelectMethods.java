package helpMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.LogUtility;

public class SelectMethods {

    public void selectByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
        LogUtility.infoLog("The user selects the value '" + text + "' from the drop down");
    }

    public void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
        LogUtility.infoLog("The user selects the option at index " + index + " from the drop down");
    }
}