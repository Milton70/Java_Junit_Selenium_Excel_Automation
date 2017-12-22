package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBox {
    private static WebElement element = null;

    public static WebElement Waive_Charges(WebDriver driver) {
        element = driver.findElement(By.id("chargesWaived"));
        return element;
    }
    public static WebElement Currency_Default(WebDriver driver) {
        element = driver.findElement(By.id("search_equals_currencyDefault"));
        return element;
    }
}
