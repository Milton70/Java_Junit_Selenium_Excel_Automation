package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button {
    private static WebElement element = null;

    public static WebElement btn_Login(WebDriver driver) {
        element = driver.findElement(By.id("login"));
        return element;
    }
    public static WebElement Search(WebDriver driver){
        element = driver.findElement(By.name("param_actionPathExt:searchAccount_methodKey:submit.startSearchAccount"));
        return element;
    }
    public static WebElement Debit_Party_EnrichBIC(WebDriver driver) {
        element = driver.findElement(By.id("instructionEnrichBIC"));
        return element;
    }
    public static WebElement Credit_Party_EnrichBIC(WebDriver driver) {
        element = driver.findElement(By.id("transactionEnrichBIC"));
        return element;
    }
    public static WebElement Add_Transaction(WebDriver driver) {
        element = driver.findElement(By.name("param_actionPathExt:addTransaction_methodKey:submit.addTransaction"));
        return element;
    }
    public static WebElement Save(WebDriver driver) {
        element = driver.findElement(By.name("param_actionPathExt:save_methodKey:submit.save"));
        return element;
    }
}
