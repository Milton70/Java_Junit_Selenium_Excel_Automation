// junit
import Utilities.Log;
import org.junit.Test;


// Selenium
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

// File Utils
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

// Log4j
import org.apache.log4j.xml.DOMConfigurator;

// Homebrew classes
import Utilities.*;;
import pageObjects.*;

public class FirstTest {
    @Test
    public void runComponents() throws Exception {
        try {
            // Create driver for these tests
            WebDriver driver = null;

            // Set up logging
            DOMConfigurator.configure("log4j.xml");

            // Start logging
            Log.info("Open Excel Driver");

            // Open Excel Driver
            ExcelUtils.openExcelFile(Constant.Path_TestData + Constant.File_TestData, "TestSchedule");

            // Get test cases and environment
            List<String> arrTestCases = ExcelUtils.getTestScheduleParams();

            // Loop round the test cases
            ListIterator<String> litr = arrTestCases.listIterator(1);
            while (litr.hasNext()) {
                String strTCTE = litr.next();
                String[] mysplit = strTCTE.split(":");

                // Get environment fields
                HashMap<String, String> hashEnvVars = (HashMap<String, String>) ExcelUtils.getEnvironmentParams(mysplit[1]);

                // Now get components to execute along with params
                List<String> arrComponents = ExcelUtils.getTestCaseParams(mysplit[0]);
                ListIterator<String> itrTC = arrComponents.listIterator();
                while (itrTC.hasNext()) {
                    String strCompParam = itrTC.next();
                    String[] compSplit = strCompParam.split(":");
                    String sComponent = compSplit[0];

                    Log.startTestCase(sComponent);

                    if (driver == null) {
                        InternetExplorerOptions options = new InternetExplorerOptions();
                        options.ignoreZoomSettings();
                        options.introduceFlakinessByIgnoringSecurityDomains();
                        //options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);

                        System.setProperty("webdriver.ie.driver", Constant.IE_Driver_Path);
                        driver = new InternetExplorerDriver(options);
                    }

                    // Call the components
                    Components.main(driver, new String[]{sComponent}, hashEnvVars);

                    Log.endTestCase(sComponent);
                }

                // Write out a pass
                ExcelUtils.setExcelSheet("TestSchedule");
                ExcelUtils.setCellData("Pass", 1, 2);

            }
            TearDown.KillBrowser(driver);
        } catch (Exception e) {
            Log.fatal(String.valueOf(e));
            TearDown.KillBrowser(null);
        }
    }
}
