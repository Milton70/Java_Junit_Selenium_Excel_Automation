// junit
import Utilities.Log;
import org.junit.Test;

// Selenium
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

// File Utils
import java.io.File;
import java.net.URL;
import java.nio.file.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.text.SimpleDateFormat;

// Log4j
import org.apache.log4j.xml.DOMConfigurator;

// Homebrew classes
import Utilities.*;;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageObjects.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FirstTest {
    @Test
    public void runComponents() throws Exception {
        try {
            // Create driver for these tests
            WebDriver driver = null;

            // Set up logging
            DOMConfigurator.configure("log4j.xml");

            // Open Excel Driver
            ExcelUtils.openExcelFile(Constant.Path_TestData + Constant.File_TestData);

            // Get test cases and environment
            List<String> arrTestCases = ExcelUtils.getTestScheduleParams();

            // Loop round the test cases
            ListIterator<String> litr = arrTestCases.listIterator(1);
            while (litr.hasNext()) {
                String strTCTE = litr.next();
                String[] mysplit = strTCTE.split(":");

                // Get environment fields
                HashMap<String, String> hashEnvVars = (HashMap<String, String>) ExcelUtils.getEnvironmentParams(mysplit[1]);

                // Create a timestamped folder in the results folder with the test case name
                Date dNow = new Date();
                SimpleDateFormat mytime = new SimpleDateFormat("yMd_HHmmss");
                String format = mytime.format(dNow);
                String scrFolder = hashEnvVars.get("Screenshot") + "/" + mysplit[0] + "_" + format;
                new File(scrFolder).mkdir();

                // Now get components to execute along with params
                List<String> arrComponents = ExcelUtils.getTestCaseParams(mysplit[0]);
                ListIterator<String> itrTC = arrComponents.listIterator();
                while (itrTC.hasNext()) {
                    String strCompParam = itrTC.next();
                    String[] compSplit = strCompParam.split(">>");
                    String sComponent = compSplit[0];

                    Log.startTestCase(mysplit[0] + " [ " + sComponent + " ] ");

                    if (driver == null) {

     /*
                        System.setProperty("webdriver.gecko.driver","C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");
                        driver = new FirefoxDriver();


                        URL url = new URL("http://localhost:4444/wd/hub");
                        DesiredCapabilities capability = new DesiredCapabilities();
                        capability.setBrowserName("firefox");
                        capability.setPlatform(Platform.VISTA);
                        capability.setBrowserName("internet explorer");
                        capability.setPlatform(Platform.WINDOWS);
                        capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                        capability.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
                        capability.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
                        capability.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
                        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

                        capability.setJavascriptEnabled(true);
                        driver = new RemoteWebDriver(url,capability);

    */

                        InternetExplorerOptions options = new InternetExplorerOptions();
                        options.ignoreZoomSettings();
                        options.introduceFlakinessByIgnoringSecurityDomains();

                        System.setProperty("webdriver.ie.driver", Constant.IE_Driver_Path);
                        driver = new InternetExplorerDriver();

                    }

                    // Call the components
                    Components.main(driver, new String[]{sComponent}, compSplit[1], hashEnvVars, scrFolder);

                    Log.endTestCase(mysplit[0] + " [ " + sComponent + " ] ");
                }

                // Copy the log file to the results location
                String home = System.getProperty("user.dir") + "\\logfile.log";
                String new_home = scrFolder + "\\logfile.log";
                Path FROM = Paths.get(home);
                Path TO = Paths.get(new_home);
                // overwrite existing file, if exists
                CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(FROM, TO, options);
                Log.info("Log File copied.");

            }
            TearDown.KillBrowser(driver);
        } catch (Exception e) {
            Log.fatal(String.valueOf(e));
            TearDown.KillBrowser(null);
        }
    }
}
