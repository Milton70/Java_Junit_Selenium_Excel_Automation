package pageObjects;

import Utilities.TearDown;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import Utilities.Log;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Components {

    private static WebElement element = null;
    /* ------------------------------------------------- Set up OR ---------------------------------------------------*/
    private static WebElement txt_UserId(WebDriver driver) {
        element = driver.findElement(By.id("j_username"));
        return element;
    }
    private static WebElement gcms_UnitCode(WebDriver driver){
        element = driver.findElement(By.name("unit_cd"));
        return element;
    }
    private static WebElement gcms_UserId(WebDriver driver){
        element = driver.findElement(By.name("user_cd"));
        return element;
    }
    private static WebElement gcms_Password(WebDriver driver){
        element = driver.findElement(By.name("im_password"));
        return element;
    }
    private static WebElement gcms_Login_Button(WebDriver driver){
        element = driver.findElement(By.name("btnLogin"));
        return element;
    }
    private static WebElement txt_Password(WebDriver driver) {
        element = driver.findElement(By.id("j_password"));
        return element;
    }
    private static WebElement btn_Login(WebDriver driver) {
        element = driver.findElement(By.id("login"));
        return element;
    }

    /* ------------------------------------------------- Components --------------------------------------------------*/
    private static void Logout_BVA (WebDriver driver) {
        driver.navigate().to("javascript:document.getElementById('logoutlink').click()");
        Log.info("Clicked on the Logout button");
    }

    private static void Login_BVA (WebDriver driver, HashMap<String, String> hashEnvVars) {
        driver.get(hashEnvVars.get("URL"));
        assertTrue(driver.getTitle().equals("Certificate Error: Navigation Blocked"));
        driver.navigate().to("javascript:document.getElementById('overridelink').click()");

        Log.info("Started Application - got through certificate error");

        txt_UserId(driver).sendKeys(hashEnvVars.get("UserName"));
        txt_Password(driver).sendKeys(hashEnvVars.get("Password"));
        btn_Login(driver).click();
        Log.info("Logged into BVA");
    }

    private static void TakeScreenShots(WebDriver driver, HashMap<String, String> hashEnvVars, String sComponent) {
        // Get current timestamp for screenshots
        Date dNow = new Date();
        SimpleDateFormat mytime = new SimpleDateFormat("yMd_HHmmss");
        String format = mytime.format(dNow);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(hashEnvVars.get("Screenshot") + "/" + sComponent + "_" + format + ".png"));
            Log.info("Screenshot taken");
        } catch (IOException e) {
            Log.fatal(String.valueOf(e));
        }
    }

    private static void Login_GCMS(WebDriver driver, HashMap<String, String> hashEnvVars) {
        driver.get(hashEnvVars.get("URL"));
        Alert first_alert = driver.switchTo().alert();
        first_alert.accept();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(driver.getTitle().equals("Certificate Error: Navigation Blocked"));
        driver.navigate().to("javascript:document.getElementById('overridelink').click()");
        Alert second_alert = driver.switchTo().alert();
        second_alert.accept();

        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnLogin")));

        Log.info("Started Application - got through certificate error");

        gcms_UnitCode(driver).sendKeys(hashEnvVars.get("UnitCode"));
        gcms_UserId(driver).sendKeys(hashEnvVars.get("UserName"));
        gcms_Password(driver).sendKeys(hashEnvVars.get("Password"));
        gcms_Login_Button(driver).click();

        // See if we get an error message
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        if (driver.findElement(By.cssSelector("div.information_message_red")).isDisplayed()) {
            Log.fatal("Couldn't Login...");
            Log.fatal("Shows error message [ " + driver.findElement(By.cssSelector("div.information_message_red p")).getText() + " ]");
            TearDown.KillBrowser(driver);
        } else {
            Log.info("Logged into GCMS");
        }
    }
    /* ------------------------------------------------ main driver --------------------------------------------------*/
    public static void main(WebDriver driver, String [] components, HashMap<String, String> envVars) throws Exception {
        for (String comp : components) {
            switch (comp) {
                case "Login_BVA":
                    Login_BVA(driver, envVars);
                    break;
                case "Logout_BVA":
                    Logout_BVA(driver);
                    break;
                case "TakeScreenShots":
                    TakeScreenShots(driver, envVars, comp);
                    break;
                case "Login_GCMS":
                    Login_GCMS(driver, envVars);
                    break;
            }
        }
    }

}

