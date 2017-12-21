package pageObjects;

import Utilities.Log;
import Utilities.TearDown;
import Utilities.Validation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Components_GCMS {

    private static WebElement element = null;

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

    private static void Logout_GCMS(WebDriver driver) throws Exception {
        driver.findElement(By.cssSelector("li#logout a")).click();
        //driver.navigate().to("javascript:document.getElementById('logout').click()");
        Alert first_alert = driver.switchTo().alert();
        first_alert.accept();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (Validation.Validate_Message(driver,"div.information_message_black", "")){
            driver.navigate().to("javascript:document.getElementById('logout').click()");
        } else {
            Log.warn("Logged off but not presented withe the [Logged Out] message.");
        }
    }

    private static void Login_GCMS(WebDriver driver, HashMap<String, String> hashEnvVars) throws Exception {
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

        // See if we have an error message or we have logged in correctly
        if (Validation.Validate_Message(driver,"div.information_message_red", "")) {
            Log.fatal("Couldn't Login...");
            Log.fatal("Shows error message [ " + driver.findElement(By.cssSelector("div.information_message_red p")).getText() + " ]");
            TearDown.KillBrowser(driver);
        }

        if (Validation.Validate_Message(driver,"div.information_message_blue", "")) {
            if (driver.findElement(By.cssSelector("div.information_message_blue p")).getText().equals("The user is already logged in. Do you wish to perform a duplicate login?")){
                Log.info("User was already logged in, login as duplicate carried out.");
                driver.findElement(By.id("login_result2")).click();
            } else {
                Log.fatal("Couldn't Login...");
                Log.fatal("Shows error message [ " + driver.findElement(By.cssSelector("div.information_message_blue p")).getText() + " ]");
                TearDown.KillBrowser(driver);
            }
        }

        if (Validation.Validate_Message(driver,"div.service_name_wrapper div.left p.service_name", "")) {
            Log.info("Logged into GCMS");
        } else {
            Log.fatal("Don't appear to be logged in correctly, please check.");
            TearDown.KillBrowser(driver);
        }

    }

    public static void menuClick(WebElement a,WebElement b, WebDriver driver)
    {
        try
        {
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            ((JavascriptExecutor) driver).executeScript(mouseOverScript,a);
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript(mouseOverScript,b);
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",b);


        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    /* --------------------------------------- create remittance instruction -----------------------------------------*/
    private static void Create_Remittance_Instruction(WebDriver driver, HashMap<String, String> hashEnvVars) throws InterruptedException {

        List<WebElement> list = driver.findElements(By.cssSelector("div.menu_head a span"));
        for (WebElement ele : list) {
            if (ele.getAttribute("innerHTML").contains("Instruction")) {

                // Get sibling ul's next to Instruction span
                WebElement sibling = ele.findElement(By.xpath("./../.."));
                List<WebElement> ul_list = sibling.findElements(By.cssSelector("ul"));
                for (WebElement ul_ele: ul_list) {
                    // Get sub li and a values for this list
                    List<WebElement> li_a_list = ul_ele.findElements(By.cssSelector("li a"));
                    for (WebElement li_a_ele : li_a_list) {
                        Log.info(li_a_ele.getText());
                    }
                }
            }

        }

    }

    /* ------------------------------------------------ main driver --------------------------------------------------*/
    public static void main_gcms(WebDriver driver, String [] components, HashMap<String, String> envVars) throws Exception {
        for (String comp : components) {
            switch (comp) {
                case "Login_GCMS":
                    Login_GCMS(driver, envVars);
                    break;
                case "Create_Remittance_Instruction":
                    Create_Remittance_Instruction(driver, envVars);
                    break;
                case "Logout_GCMS":
                    Logout_GCMS(driver);
                    break;
            }
        }
    }

}
