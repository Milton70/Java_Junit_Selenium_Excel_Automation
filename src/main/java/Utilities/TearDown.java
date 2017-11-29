package Utilities;

import org.openqa.selenium.WebDriver;
import java.io.IOException;

public class TearDown {

    public static void KillBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
        } catch (IOException e) {
            Log.fatal(String.valueOf(e));
        }
        throw new AssertionError("Execution stopped because of errors - check the log.");
    }

}
