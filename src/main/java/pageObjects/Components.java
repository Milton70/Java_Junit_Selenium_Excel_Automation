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
                dropdown = new Select(DropDown.Branch(driver));
                break;
            case "Batch_Booking":
                dropdown = new Select(DropDown.Batch_Booking(driver));
                break;
            case "Category_Purpose":
                dropdown = new Select(DropDown.Category_Purpose(driver));
                break;
            case "Currency":
                dropdown = new Select(DropDown.Currency(driver));
                break;
            case "Account_Currency":
                dropdown = new Select(TextBox.Credit_Party_Account_Currency(driver));
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

        TextBox.txt_UserId(driver).sendKeys(hashEnvVars.get("UserName"));
        TextBox.txt_Password(driver).sendKeys(hashEnvVars.get("Password"));
        Button.btn_Login(driver).click();
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
/*
            case "Payment Management=>Instructions":
                driver.navigate().to(url + "");
                if (!Validate_Message(driver,"div#head div h3.legend","Create a Credit Transfer Initiation")) {
                    Log.fatal("Manual Entry=>SEPA=>Manual Entry CCTI - menu option chosen but correct page not displayed. Please check.");
                    Validation.TakeScreenShot(driver, "Choose_From_Menu", results);
                    Logout_BVA(driver);
                } else {
                    Log.info("Manual Entry=>SEPA=>Manual Entry CCTI - menu option chosen & [ Create a Credit Transfer Initiation ] displayed.");
                    Validation.TakeScreenShot(driver, "Choose_From_Menu", results);
                }
*/
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
                    TextBox.Execution_Date(driver).sendKeys(paramSplit[1]);
                    break;
                case "Waive_Charges":
                    if (paramSplit[1].equals("Yes")) {
                        if (!CheckBox.Waive_Charges(driver).isSelected()) {
                            CheckBox.Waive_Charges(driver).sendKeys(Keys.SPACE);
                        }
                    } else {
                        if (CheckBox.Waive_Charges(driver).isSelected()) {
                            CheckBox.Waive_Charges(driver).sendKeys(Keys.SPACE);
                        }
                    }
                    break;
                case "Customer_Id":
                    TextBox.Customer_Id(driver).sendKeys(paramSplit[1]);
                    break;
                case "Customer_Name":
                    TextBox.Customer_Name(driver).sendKeys(paramSplit[1]);
                    break;
                case "IBAN_Account_Number":
                    TextBox.IBAN_Account_Number(driver).sendKeys(paramSplit[1]);
                    break;
                case "Internal_Account_Number":
                    TextBox.Internal_Account_Number(driver).sendKeys(paramSplit[1]);
                    break;
                case "Currency_Default":
                    if (paramSplit[1].equals("Yes")) {
                        if (!CheckBox.Currency_Default(driver).isSelected()) {
                            CheckBox.Currency_Default(driver).sendKeys(Keys.SPACE);
                        }
                    } else {
                        if (CheckBox.Currency_Default(driver).isSelected()) {
                            CheckBox.Currency_Default(driver).sendKeys(Keys.SPACE);
                        }
                    }
                    break;
            }
        }
        // Click on Search
        Button.Search(driver).click();

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
                    if (TextBox.Debit_Party_Name(driver).getAttribute("value").equals(paramSplit[1].replace("|",","))) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1].replace("|",",") + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1].replace("|",",") + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + TextBox.Debit_Party_Name(driver).getAttribute("value") + " ]. Please check.");
                    }
                    break;
                case "Address_Line_1":
                    if (TextBox.Debit_Party_Addr_Line_1(driver).getAttribute("value").equals(paramSplit[1].replace("|",","))) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1].replace("|",",") + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1].replace("|",",") + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + TextBox.Debit_Party_Addr_Line_1(driver).getAttribute("value") + " ]. Please check.");
                    }
                    break;
                case "Address_Line_2":
                    if (TextBox.Debit_Party_Addr_Line_2(driver).getAttribute("value").equals(paramSplit[1].replace("|",","))) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1].replace("|",",") + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1].replace("|",",") + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + TextBox.Debit_Party_Addr_Line_2(driver).getAttribute("value") + " ]. Please check.");
                    }
                    break;
                case "Country":
                    String selectedOption = new Select(DropDown.Debit_Party_Country(driver)).getFirstSelectedOption().getText();
                    if (selectedOption.equals(paramSplit[1].replace("|",","))) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1].replace("|",",") + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1].replace("|",",") + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + selectedOption + " ]. Please check.");
                    }
                    break;
                case "IBAN":
                    if (TextBox.Debit_Party_IBAN(driver).getAttribute("value").equals(paramSplit[1].replace("|",","))) {
                        Log.info("Passed - [ " + paramSplit[0] + " ] equals expected value of [ " + paramSplit[1].replace("|",",") + " ].");
                    } else {
                        Log.error("Failed - expected value [ " + paramSplit[1].replace("|",",") + " ] for field [ " + paramSplit[0] + " ]. Instead found [ " + TextBox.Debit_Party_IBAN(driver).getAttribute("value") + " ]. Please check.");
                    }
                    break;
                case "Agent_Id":
                    TextBox.Debit_Party_AgentId(driver).sendKeys(paramSplit[1].replace("|",","));
                    Button.Debit_Party_EnrichBIC(driver).click();
                    break;
            }
        }
        Validation.TakeScreenShot(driver,"Validate_And_Enrich_Debit_Party", results);
    }

    public static void Enter_Transaction_Information(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results) {
        String[] eleSplit = parameters.split(",");
        for (String ele: eleSplit) {
            String[] paramSplit = ele.split(":");
            switch (paramSplit[0].trim()) {
                case "End_To_End_Identification":
                    TextBox.End_to_End_Identification(driver).clear();
                    TextBox.End_to_End_Identification(driver).sendKeys(paramSplit[1].replace("|",","));
                    break;
                case "Instructed_Amount":
                    TextBox.Instructed_Amount(driver).sendKeys(paramSplit[1].replace("|",","));
                    break;
            }
        }
        Log.info("Transaction data entered.");
        Validation.TakeScreenShot(driver,"Enter_Transaction_Information", results);
    }

    public static void Enter_Credit_Party(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results) {
        String[] eleSplit = parameters.split(",");
        for (String ele : eleSplit) {
            String[] paramSplit = ele.split(":");
            switch (paramSplit[0].trim()) {
                case "Name":
                    TextBox.Credit_Party_Name(driver).sendKeys(paramSplit[1].replace("|",","));
                    break;
                case "IBAN":
                    TextBox.Credit_Party_IBAN(driver).sendKeys(paramSplit[1].replace("|",","));
                    break;
                case "Account_Currency":
                    //select_from_dropdown(driver, "Account_Currency", paramSplit[1]);
                    break;
            }
        }
        Validation.TakeScreenShot(driver,"Enter_Credit_Party", results);
    }

    public static void Enter_Credit_Party_Agent_And_Enrich_BIC(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results) {
        String[] eleSplit = parameters.split(",");
        for (String ele : eleSplit) {
            String[] paramSplit = ele.split(":");
            switch (paramSplit[0].trim()) {
                case "Beneficiary_Bank_BIC":
                    TextBox.Benificiary_Bank_BIC(driver).sendKeys(paramSplit[1].replace("|",","));
                    break;
            }
        }
        Button.Credit_Party_EnrichBIC(driver).click();
        Validation.TakeScreenShot(driver,"Enter_Credit_Party_Agent_And_Enrich_BIC", results);
    }

    public static void Enter_Remittance_Information(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results) {
        String[] eleSplit = parameters.split(",");
        for (String ele : eleSplit) {
            String[] paramSplit = ele.split(":");
            switch (paramSplit[0].trim()) {
                case "Unstructured_Remittance_Information":
                    TextBox.Unstructured_Remittance_Information(driver).sendKeys(paramSplit[1].replace("|", ","));
                    break;
            }
        }
        Validation.TakeScreenShot(driver,"Enter_Remittance_Information", results);
    }

    public static void Add_And_Verify_Transaction_Summary(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results) {

        // Click the Add Transaction button
        Button.Add_Transaction(driver).click();

        // Connect to the table, get rows and cols
        List<WebElement> the_tables = driver.findElements(By.cssSelector("table.screencontent"));
        for (WebElement the_table: the_tables) {
            if (the_table.findElements(By.cssSelector("th")).size() == 8) {
                int Row_Count = the_table.findElements(By.cssSelector("tr")).size();
                int Col_Count = the_table.findElements(By.cssSelector("th")).size();
                String headers[] = new String[Col_Count];
                List<WebElement> header_row = the_table.findElements(By.cssSelector("tr.clmHeading"));
                int col_num = 0;
                for(WebElement trElement : header_row) {
                    List<WebElement> th_collection = trElement.findElements(By.cssSelector("th"));
                    for (WebElement thElement : th_collection) {
                        headers[col_num] = thElement.getText().replaceAll(" ", "_");
                        col_num = col_num + 1;
                    }
                }
                String values[] = new String[Col_Count];
                List<WebElement> value_row = the_table.findElements(By.cssSelector("tr"));
                col_num = 0;
                for(WebElement trElement : value_row) {
                    List<WebElement> td_collection = trElement.findElements(By.cssSelector("td"));
                    if(td_collection.size() > 1) {
                        for (WebElement tdElement : td_collection) {
                            values[col_num] = tdElement.getText().replaceAll("\n", "");
                            col_num = col_num + 1;
                        }
                    }
                }
                // Put headers and values into a hash
                HashMap<String, String> table_values = new HashMap<String, String>();
                for( int i = 0; i < headers.length - 1; i++) {
                    String header_val = headers[i];
                    String value_val = values[i];
                    table_values.put(header_val, value_val);
                }

                // Validate the table returned
                String[] eleSplit = parameters.split(",");
                for (String ele : eleSplit) {
                    String[] paramSplit = ele.split(":");
                    switch (paramSplit[0].trim()) {
                        case "End_to_End_Identification":
                            if (table_values.get("End-to-End_Identification").equals(paramSplit[1])){
                                Log.info("Success - Found [ " + paramSplit[1] + " ] for the [ End-to-End Identification ] column.");
                            } else {
                                Log.error("Failure - Could not find [ " + paramSplit[1] + " ] for the [ End-to-End Identification ] column. Instead found [ " + table_values.get("End-to-End_Identification") + " ]. Please check");
                            }
                            break;
                        default:
                            if (table_values.get(paramSplit[0].trim()).equals(paramSplit[1])){
                                Log.info("Success - Found [ " + paramSplit[1] + " ] for the [ " + paramSplit[0] + " ] column.");
                            } else {
                                Log.error("Failure - Could not find [ " + paramSplit[1] + " ] for the [ " + paramSplit[0] + " ] column. Instead found [ " + table_values.get(paramSplit[0].trim()) + " ]. Please check");
                            }
                            break;
                    }
                }
            }
        }
        Validation.TakeScreenShot(driver,"Add_And_Verify_Transaction_Summary", results);
    }

    public static void Save_Transaction_And_Capture_Instruction(WebDriver driver, String parameters, HashMap<String, String> hashEnvVars, String results){
        // Click on the 'Save' button
        Button.Save(driver).click();

        // Check we are taken to the Bank Dashboard and that the Instruction is created
        if (!driver.findElement(By.cssSelector("div#maincontent h2#skipnav")).getText().contains("Bank Dashboard")){
            if (driver.findElement(By.cssSelector("div.err")).isDisplayed()) {
                String err_msg = driver.findElement(By.cssSelector("div.err ul li")).getText();
                Log.fatal("Failure - The Transaction was saved but an Instruction was not created. Error message [ " + err_msg + " ] displayed. Please check, aborting...");
            } else {
                Log.fatal("Failure - The Transaction was saved but an Instruction was not created. Please check, aborting...");
            }
            Validation.TakeScreenShot(driver, "Save_Transaction_And_Capture_Instruction", results);
            Logout_BVA(driver);
        }
        String the_instruction_msg = OR.Instruction_Created_Message(driver).getText();
        if(the_instruction_msg.contains("The change is subject to 4-eye verification.")){
            Log.info("Success - The Transaction has been saved and the Instruction [ " + the_instruction_msg.substring(13,25) + " ] created.");
            // Add the instruction to the hash map
            hashEnvVars.put("Instruction_Id", the_instruction_msg.substring(13,25));
        } else {
            Log.fatal("Failure - The Transaction was saved but an Instruction was not created. Please check, aborting...");
            Validation.TakeScreenShot(driver, "Save_Transaction_And_Capture_Instruction", results);
            Logout_BVA(driver);
        }
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
                case "Enter_Transaction_Information":
                    Enter_Transaction_Information(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
                case "Enter_Credit_Party":
                    Enter_Credit_Party(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
                case "Enter_Credit_Party_Agent_And_Enrich_BIC":
                    Enter_Credit_Party_Agent_And_Enrich_BIC(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
                case "Enter_Remittance_Information":
                    Enter_Remittance_Information(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
                case "Add_And_Verify_Transaction_Summary":
                    Add_And_Verify_Transaction_Summary(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
                case "Save_Transaction_And_Capture_Instruction":
                    Save_Transaction_And_Capture_Instruction(driver, parameters.substring(1, parameters.length()-1), envVars, results_path);
                    break;
            }
        }
    }

}

