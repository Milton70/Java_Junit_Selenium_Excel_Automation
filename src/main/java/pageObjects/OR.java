package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OR {
    private static WebElement element = null;

    /* ------------------------------------------------- Set up OR ---------------------------------------------------*/
    public static WebElement txt_UserId(WebDriver driver) {
        element = driver.findElement(By.id("j_username"));
        return element;
    }
    public static WebElement txt_Password(WebDriver driver) {
        element = driver.findElement(By.id("j_password"));
        return element;
    }
    public static WebElement btn_Login(WebDriver driver) {
        element = driver.findElement(By.id("login"));
        return element;
    }
    public static WebElement Branch(WebDriver driver) {
        element = driver.findElement(By.id("instruction_bankKey"));
        return element;
    }
    public static WebElement Execution_Date(WebDriver driver) {
        element = driver.findElement(By.id("instruction_instructedDay"));
        return element;
    }
    public static WebElement Batch_Booking(WebDriver driver) {
        element = driver.findElement(By.id("instruction_batchBooking"));
        return element;
    }
    public static WebElement Waive_Charges(WebDriver driver) {
        element = driver.findElement(By.id("chargesWaived"));
        return element;
    }
    public static WebElement Category_Purpose(WebDriver driver) {
        element = driver.findElement(By.id("instruction_categoryPurpose"));
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
    public static WebElement Currency(WebDriver driver) {
        element = driver.findElement(By.id("search_equals_currency"));
        return element;
    }
    public static WebElement Internal_Account_Number(WebDriver driver) {
        element = driver.findElement(By.id("search_like_bankInternalAccountNumber"));
        return element;
    }
    public static WebElement Currency_Default(WebDriver driver) {
        element = driver.findElement(By.id("search_equals_currencyDefault"));
        return element;
    }
    public static WebElement Search(WebDriver driver){
        element = driver.findElement(By.id("defaultBttn"));
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
    public static WebElement Debit_Party_Country(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyCountry"));
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
    public static WebElement Debit_Party_EnrichBIC(WebDriver driver) {
        element = driver.findElement(By.id("instructionEnrichBIC"));
        return element;
    }
    public static WebElement Debit_Party_Id_Type(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyModeOper"));
        return element;
    }
    public static WebElement Debit_Party_Id(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyId"));
        return element;
    }
    public static WebElement Debit_Party_Id_Issuer(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyIdIssuer"));
        return element;
    }
    public static WebElement Debit_Party_Id_Scheme_Name_Code(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyIdSchemeNameCodeOrganisation"));
        return element;
    }
    public static WebElement Debit_Party_Id_Scheme_Name_Proprietary(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyIdType"));
        return element;
    }
    public static WebElement Debit_Party_Id_Scheme_Name_Code_Private(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyIdSchemeNameCodePrivate"));
        return element;
    }
    public static WebElement Debit_Party_Id_Birth_Date(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyIdBirthDate"));
        return element;
    }
    public static WebElement Debit_Party_Id_Birth_City(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyIdBirthCity"));
        return element;
    }
    public static WebElement Debit_Party_Id_Birth_Province(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyIdBirthProvince"));
        return element;
    }
    public static WebElement Debit_Party_Id_Birth_Country(WebDriver driver) {
        element = driver.findElement(By.id("instruction_originatingPartyIdBirthCountry"));
        return element;
    }
    public static WebElement Initiating_Party_Name(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyName"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Type(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyModeOper"));
        return element;
    }
    public static WebElement Initiating_Party_Id(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyId"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Issuer(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyIdIssuer"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Scheme_Name_Code(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyIdSchemeNameCodeOrganisation"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Scheme_Name_Proprietary(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyIdType"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Scheme_Name_Code_Private(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyIdSchemeNameCodePrivate"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Birth_Date(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyIdBirthDate"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Birth_City(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyIdBirthCity"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Birth_Province(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyIdBirthProvince"));
        return element;
    }
    public static WebElement Initiating_Party_Id_Birth_Country(WebDriver driver) {
        element = driver.findElement(By.id("instruction_initiatingPartyIdBirthCountry"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Name(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyName"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Addr_Line_1(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyAddressLine1"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Addr_Line_2(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyAddressLine2"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Country(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyCountry"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Type(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyModeOper"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyId"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Issuer(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyIdIssuer"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Scheme_Name_Code(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyIdSchemeNameCodeOrganisation"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Scheme_Name_Proprietary(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyIdType"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Scheme_Name_Code_Private(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyIdSchemeNameCodePrivate"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Birth_Date(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyIdBirthDate"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Birth_City(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyIdBirthCity"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Birth_Province(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyIdBirthProvince"));
        return element;
    }
    public static WebElement Ultimate_Debit_Party_Id_Birth_Country(WebDriver driver) {
        element = driver.findElement(By.id("instruction_ultimateOriginatingPartyIdBirthCountry"));
        return element;
    }
    public static WebElement Credit_Party_EnrichBIC(WebDriver driver) {
        element = driver.findElement(By.id("transactionEnrichBIC"));
        return element;
    }
}
