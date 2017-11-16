package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class logout_BVA {

    private static WebElement element = null;

    public static WebElement btn_Logout(WebDriver driver) {
        element = driver.findElement(By.id("logoutlink"));
        return element;
    }

}
