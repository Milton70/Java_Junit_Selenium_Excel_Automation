package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import Utilities.Log;

public class login_BVA {

    private static WebElement element = null;

    public static WebElement txt_UserId(WebDriver driver) {
        element = driver.findElement(By.id("j_username"));
        Log.info("Found Username");
        return element;
    }

    public static WebElement txt_Password(WebDriver driver) {
        element = driver.findElement(By.id("j_password"));
        Log.info("Found Password");
        return element;
    }

    public static WebElement btn_Login(WebDriver driver) {
        element = driver.findElement(By.id("login"));
        Log.info("Found login button");
        return element;
    }
}

