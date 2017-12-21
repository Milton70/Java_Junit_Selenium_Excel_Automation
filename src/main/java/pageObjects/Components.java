package pageObjects;

import Utilities.Log;
import Utilities.Validation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;

import static Utilities.Validation.*;
import static org.junit.Assert.assertTrue;

public class Components {

    private static void select_from_dropdown(WebDriver driver, String the_element, String the_selection) {
        Select dropdown = null;
        switch (the_element) {
            case "Branch":
                dropdown = new Select(OR.Branch(driver));
                break;
            case "Batch_Booking":
                dropdown = new Select(OR.Batch_Booking(driver));
                break;
            case "Category_Purpose":
                dropdown = new Select(OR.Category_Purpose(driver));
                break;
            case "Currency":
                dropdown = new Select(OR.Currency(driver));
                break;
        }
        dropdown.selectByVisibleText(the_selection);
    }


    /* ------------------------------------------------- Components --------------------------------------------------*/
    private static void Login_BVA (WebDriver driver, HashMap<String, String> hashEnvVars, String results) {
        driver.get(hashEnvVars.get("URL"));
        assertTrue(driver.getTitle().equals("Certificate Error: Navigation Blocked"));
        driver.navigate().to("javascript:document.getElementById('overridelink').click()");

        Log.info("Started Application - got through certificate error");

        OR.txt_UserId(driver).sendKeys(hashEnvVars.get("UserName"));
        OR.txt_Password(driver).sendKeys(hashEnvVars.get("Password"));
        OR.btn_Login(driver).click();
        Log.info("Logged into BVA");
        Validation.TakeScreenShot(driver, "Login_BVA", results);
    }

    private static void Logout_BVA (WebDriver driver) {
        driver.navigate().to("javascript:document.getElementById('logoutlink').click()");
        Log.info("Clicked on the Logout button");
    }

    private static void Choose_From_Menu(WebDriver driver, String menuOptions, HashMap<String, String> hashEnvVars, String results) throws Exception {
        switch (menuOptions) {
            case "Manual Entry=>SEPA=>Manual Entry CCTI":
                String url = hashEnvVars.get("URL");
                driver.navigate().to(url + "processManualEntryCCTIPayment.do?sessionContext=clear&param_methodKey%3Asubmit.create=Link");
                if (!Validate_Message(driver,"div#head div h3.legend","Create a Credit Transfer Initiation")) {
                    Log.fatal("Manual Entry=>SEPA=>Manual Entry CCTI - menu option chosen but correct page not displayed. Please check.");
                    Validation.TakeScreenShot(driver, "Choose_From_Menu", results);
                    Logout_BVA(driver);
                } else {
                    Log.info("Manual Entry=>SEPA=>Manual Entry CCTI - menu option chosen & [ Create a Credit Transfer Initiation ] displayed.");
                    Validation.TakeScreenShot(driver, "Choose_From_Menu", results);
                }
        }
    }

    private static void Search_And_Load_Template(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results) {
        String[] eleSplit = parameters.split(",");
        for (String ele: eleSplit) {
            String[] paramSplit = ele.split(":");
            switch (paramSplit[0].trim()) {
                case "Branch": case "Batch_Booking": case "Category_Purpose": case "Currency":
                    select_from_dropdown(driver, paramSplit[0].trim(), paramSplit[1]);
                    break;
                case "Execution_Date":
                    OR.Execution_Date(driver).sendKeys(paramSplit[1]);
                    break;
                case "Waive_Charges":
                    if (paramSplit[1].equals("Yes")) {
                        if (!OR.Waive_Charges(driver).isSelected()) {
                            OR.Waive_Charges(driver).sendKeys(Keys.SPACE);
                        }
                    } else {
                        if (OR.Waive_Charges(driver).isSelected()) {
                            OR.Waive_Charges(driver).sendKeys(Keys.SPACE);
                        }
                    }
                    break;
                case "Customer_Id":
                    OR.Customer_Id(driver).sendKeys(paramSplit[1]);
                    break;
                case "Customer_Name":
                    OR.Customer_Name(driver).sendKeys(paramSplit[1]);
                    break;
                case "IBAN_Account_Number":
                    OR.IBAN_Account_Number(driver).sendKeys(paramSplit[1]);
                    break;
                case "Internal_Account_Number":
                    OR.Internal_Account_Number(driver).sendKeys(paramSplit[1]);
                    break;
                case "Currency_Default":
                    if (paramSplit[1].equals("Yes")) {
                        if (!OR.Currency_Default(driver).isSelected()) {
                            OR.Currency_Default(driver).sendKeys(Keys.SPACE);
                        }
                    } else {
                        if (OR.Currency_Default(driver).isSelected()) {
                            OR.Currency_Default(driver).sendKeys(Keys.SPACE);
                        }
                    }
                    break;
            }
        }
        // Click on Search
        OR.Search(driver).click();

        // Make sure a customer is found for the search entry
        if (driver.findElement(By.cssSelector("table.screencontent")).isDisplayed()){
            WebElement table_element = driver.findElement(By.cssSelector("table.screencontent"));
            List<WebElement> tr_collection = table_element.findElements(By.cssSelector("table.screencontent tbody tr"));

            int col_num;
            for(WebElement trElement : tr_collection)
            {
                List<WebElement> td_collection = trElement.findElements(By.cssSelector("td"));
                for(WebElement tdElement : td_collection)
                {
                   Log.info("Found Account data [ " + tdElement.getText() + " ] in the table.");
                }
            }
            Validation.TakeScreenShot(driver,"Search_And_Load_Template", results);
        } else {
            Log.fatal("Search_And_Load_Template - search criteria did not return an account. Please check.");
            Validation.TakeScreenShot(driver, "Search_And_Load_Template", results);
            Logout_BVA(driver);
        }
    }

    private static void Choose_Customer_IBAN(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results) {
        Boolean blnFound = false;
        String[] eleSplit = parameters.split(":");
        WebElement table_element = driver.findElement(By.cssSelector("table.screencontent"));
        List<WebElement> tr_collection = table_element.findElements(By.cssSelector("table.screencontent tbody tr"));

        int col_num;
        for(WebElement trElement : tr_collection)
        {
            List<WebElement> td_collection = trElement.findElements(By.cssSelector("td"));
            for(WebElement tdElement : td_collection)
            {
                if(tdElement.getText().equals(eleSplit[1])) {
                    Log.info("Found & Chose Customer IBAN [ " + eleSplit[1] + " ].");
                    tdElement.click();
                    blnFound = true;
                    break;
                }
            }
            if (blnFound) {
                Validation.TakeScreenShot(driver,"Choose_Customer_IBAN", results);
                break;
            }
        }
    }

    private static void Validate_And_Enrich_Debit_Party(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results) {
        String[] eleSplit = parameters.split(",");
        for (String ele: eleSplit) {
            String[] paramSplit = ele.split(":");
            switch (paramSplit[0].trim()) {
                case "Name":
                    if (OR.Debit_Party_Name(driver).getAttribute("value").equals(paramSplit[1])) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1] + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1] + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + OR.Debit_Party_Name(driver).getAttribute("value") + " ]. Please check.");
                    }
                    break;
                case "Address_Line_1":
                    if (OR.Debit_Party_Addr_Line_1(driver).getAttribute("value").equals(paramSplit[1])) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1] + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1] + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + OR.Debit_Party_Addr_Line_1(driver).getAttribute("value") + " ]. Please check.");
                    }
                    break;
                case "Address_Line_2":
                    if (OR.Debit_Party_Addr_Line_2(driver).getAttribute("value").equals(paramSplit[1])) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1] + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1] + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + OR.Debit_Party_Addr_Line_2(driver).getAttribute("value") + " ]. Please check.");
                    }
                    break;
                case "Country":
                    String selectedOption = new Select(OR.Debit_Party_Country(driver)).getFirstSelectedOption().getText();
                    if (selectedOption.equals(paramSplit[1])) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1] + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1] + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + selectedOption + " ]. Please check.");
                    }
                    break;
                case "IBAN":
                    if (OR.Debit_Party_IBAN(driver).getAttribute("value").equals(paramSplit[1])) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1] + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1] + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + OR.Debit_Party_IBAN(driver).getAttribute("value") + " ]. Please check.");
                    }
                    break;
                case "Agent_Id":
                    OR.Debit_Party_AgentId(driver).sendKeys(paramSplit[1]);
                    OR.Debit_Party_EnrichBIC(driver).click();
                    break;
            }
        }
        Validation.TakeScreenShot(driver,"Validate_And_Enrich_Debit_Party", results);
    }

    /* ------------------------------------------------ main driver --------------------------------------------------*/
    public static void main(WebDriver driver, String [] component, String parameters, HashMap<String, String> envVars, String results_path) throws Exception {
        for (String comp : component) {
            switch (comp) {
                case "Login_BVA":
                    Login_BVA(driver, envVars, results_path);
                    break;
                case "Logout_BVA":
                    Logout_BVA(driver);
                    break;
                case "Choose_From_Menu":
                    Choose_From_Menu(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
                case "Search_And_Load_Template":
                    Search_And_Load_Template(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
                case "Choose_Customer_IBAN":
                    Choose_Customer_IBAN(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
                case "Validate_And_Enrich_Debit_Party":
                    Validate_And_Enrich_Debit_Party(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
            }
        }
    }

}

