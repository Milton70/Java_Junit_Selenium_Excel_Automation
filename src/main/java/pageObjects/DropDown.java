package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DropDown {
    private static WebElement element = null;

    public static WebElement Branch(WebDriver driver) {
        element = driver.findElement(By.id("instruction_bankKey"));
        return element;
    }
    public static WebElement Batch_Booking(WebDriver driver) {
        element = driver.findElement(By.id("instruction_batchBooking"));
        return element;
    }
    public static WebElement Category_Purpose(WebDriver driver) {
        element = driver.findElement(By.id("instruction_categoryPurpose"));
        return element;
    }
    public static WebElement Currency(WebDriver driver) {
        element = driver.findElement(By.id("search_equals_currency"));
        return element;
    }
    public static WebElement Debit_Party_Country(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyCountry"));
        return element;
    }
    public static WebElement Debit_Party_Id_Type(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyModeOper"));
        return element;
    }
}
