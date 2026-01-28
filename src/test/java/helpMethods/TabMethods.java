package helpMethods;

import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

public class TabMethods {
    private WebDriver driver;
    public TabMethods(WebDriver driver) { this.driver = driver; }

    public void switchToTab(int index) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (index < tabs.size()) {
            driver.switchTo().window(tabs.get(index));
        }
    }
}