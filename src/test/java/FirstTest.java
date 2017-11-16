// junit
import Utilities.Log;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

// Selenium
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

// File Utils
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

// Log4j
import org.apache.log4j.xml.DOMConfigurator;

// Homebrew classes
import Utilities.Constant;
import Utilities.ExcelUtils;
import pageObjects.*;

public class FirstTest {
    @Test
    public void runComponents() throws Exception {
        try {
            // Set up logging
            DOMConfigurator.configure("log4j.xml");

            // Get current timestamp for screenshots
            Date dNow = new Date();
            SimpleDateFormat mytime = new SimpleDateFormat("yMd_Hmss");
            String format = mytime.format(dNow);

            Log.info("Open Excel Driver");
            ExcelUtils.openExcelFile(Constant.Path_TestData + Constant.File_TestData, "TestCase");

            String sTestCase = ExcelUtils.getCellData(1, 1);
            String sEnv = ExcelUtils.getCellData(1, 2);

            ExcelUtils.setExcelSheet("Env");

            String sURL = ExcelUtils.getCellData(1, 1);
            String sUserName = ExcelUtils.getCellData(1, 2);
            String sPassword = ExcelUtils.getCellData(1, 3);
            String sResultFolder = ExcelUtils.getCellData(1, 4);

            ExcelUtils.setExcelSheet("TestCase");

            String sComponent = ExcelUtils.getCellData(1, 2);

            Log.startTestCase(sComponent);

            System.setProperty("webdriver.ie.driver", "C:/IdeaProjects/local jars/IEDriverServer.exe");
            WebDriver driver = new InternetExplorerDriver();
            driver.get(sURL);
            assertTrue(driver.getTitle().equals("Certificate Error: Navigation Blocked"));
            driver.navigate().to("javascript:document.getElementById('overridelink').click()");

            Log.info("Started Application - got through certificate error");

            login_BVA.txt_UserId(driver).sendKeys(sUserName);
            login_BVA.txt_Password(driver).sendKeys(sPassword);
            login_BVA.btn_Login(driver).click();

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(sResultFolder + "/" + sComponent + "_" + format + ".png"));

            Log.info("Screenshot taken.");

            logout_BVA.btn_Logout(driver).click();
            driver.quit();

            Log.info("Logged out and quit browser.");

            try {
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Write out a pass
            ExcelUtils.setCellData("Pass", 1, 6);

            Log.endTestCase("Login BVA");

        } catch (Exception e) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
                Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
