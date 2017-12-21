package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Validation {

    private enum ElementStatus{
        VISIBLE,
        NOTVISIBLE,
        ENABLED,
        NOTENABLED,
        PRESENT,
        NOTPRESENT
    }

    private ElementStatus isElementVisible(WebDriver driver, By by,ElementStatus getStatus){
        try{
            if(getStatus.equals(ElementStatus.ENABLED)){
                if(driver.findElement(by).isEnabled())
                    return ElementStatus.ENABLED;
                return ElementStatus.NOTENABLED;
            }
            if(getStatus.equals(ElementStatus.VISIBLE)){
                if(driver.findElement(by).isDisplayed())
                    return ElementStatus.VISIBLE;
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        }catch(org.openqa.selenium.NoSuchElementException nse){
            return ElementStatus.NOTPRESENT;
        }
    }

    public static Boolean Validate_Message(WebDriver driver, String locator, String message) throws Exception {
        try {
            if (driver.findElement(By.cssSelector(locator)).isDisplayed()) {
                if (driver.findElement(By.cssSelector(locator)).getText().equals(message))
                    return true;
                else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void TakeScreenShot(WebDriver driver, String sComponent, String result_path) {
        // Get current timestamp for screenshots
        Date dNow = new Date();
        SimpleDateFormat mytime = new SimpleDateFormat("yMd_HHmmss");
        String format = mytime.format(dNow);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(result_path + "/" + format + "_" + sComponent + ".png"));
        } catch (IOException e) {
            Log.fatal(String.valueOf(e));
        }
    }
}
