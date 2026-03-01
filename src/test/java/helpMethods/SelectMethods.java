package helpMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectMethods {
    private WebDriver driver;

    public SelectMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    // alias folosit în proiect
    public void selectByText(WebElement element, String text) {
        selectByVisibleText(element, text);
    }

    // alias (opțional) – dacă ai apeluri deja în cod
    public void selectDropdownElement(WebElement element, String text) {
        selectByVisibleText(element, text);
    }

    public void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }
}