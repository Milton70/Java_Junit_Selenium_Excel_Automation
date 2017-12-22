package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OR {
    private static WebElement element = null;

    /* ------------------------------------------------- Misc Fields -------------------------------------------------*/
    public static WebElement Instruction_Created_Message(WebDriver driver) {
        element = driver.findElement(By.id("instruction_message"));
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

    public static WebElement Credit_Party_Reference_Type_Code(WebDriver driver) {
        element = driver.findElement(By.id("transaction_remittance1CreditPartyReferenceTypeCode"));
        return element;
    }
    public static WebElement Credit_Party_Reference_Type_Issuer(WebDriver driver) {
        element = driver.findElement(By.id("transaction_remittance1CreditPartyReferenceTypeIssuer"));
        return element;
    }
    public static WebElement Credit_Party_Reference(WebDriver driver) {
        element = driver.findElement(By.id("transaction_remittance1CreditPartyReference"));
        return element;
    }

}
