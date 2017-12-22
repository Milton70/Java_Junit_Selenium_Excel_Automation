package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBox {
    private static WebElement element = null;

    public static WebElement txt_UserId(WebDriver driver) {
        element = driver.findElement(By.id("j_username"));
        return element;
    }
    public static WebElement txt_Password(WebDriver driver) {
        element = driver.findElement(By.id("j_password"));
        return element;
    }
    public static WebElement Execution_Date(WebDriver driver) {
        element = driver.findElement(By.id("instruction_instructedDay"));
        return element;
    }
    public static WebElement Customer_Id(WebDriver driver) {
        element = driver.findElement(By.id("search_equals_customerId"));
        return element;
    }
    public static WebElement Customer_Name(WebDriver driver) {
        element = driver.findElement(By.id("search_like_customerName"));
        return element;
    }
    public static WebElement IBAN_Account_Number(WebDriver driver) {
        element = driver.findElement(By.id("search_like_internationalAccountNumber"));
        return element;
    }
    public static WebElement Internal_Account_Number(WebDriver driver) {
        element = driver.findElement(By.id("search_like_bankInternalAccountNumber"));
        return element;
    }
    public static WebElement Debit_Party_Name(WebDriver driver){
        element = driver.findElement(By.id("instruction_originatingPartyName"));
        return element;
    }
    public static WebElement Debit_Party_Addr_Line_1(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyAddressLine1"));
        return element;
    }
    public static WebElement Debit_Party_Addr_Line_2(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyAddressLine2"));
        return element;
    }
    public static WebElement Debit_Party_IBAN(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyAccount"));
        return element;
    }
    public static WebElement Debit_Party_AgentId(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyAgentId"));
        return element;
    }
    public static WebElement End_to_End_Identification(WebDriver driver) {
        element = driver.findElement(By.id("transaction_endToEndId"));
        return element;
    }
    public static WebElement Instructed_Amount(WebDriver driver) {
        element = driver.findElement(By.id("transaction_instructedAmount"));
        return element;
    }
    public static WebElement Credit_Party_Name(WebDriver driver) {
        element = driver.findElement(By.id("transaction_creditPartyName"));
        return element;
    }
    public static WebElement Credit_Party_IBAN(WebDriver driver) {
        element = driver.findElement(By.id("transaction_creditPartyAccount"));
        return element;
    }
    public static WebElement Credit_Party_Account_Currency(WebDriver driver) {
        element = driver.findElement(By.cssSelector("span#addedCreditPartyCurrencyValue"));
        return element;
    }
    public static WebElement Benificiary_Bank_BIC(WebDriver driver) {
        element = driver.findElement(By.id("transaction_creditPartyAgentId"));
        return element;
    }
    public static WebElement Unstructured_Remittance_Information(WebDriver driver) {
        element = driver.findElement(By.id("transaction_remittanceUnstructured1"));
        return element;
    }
}
