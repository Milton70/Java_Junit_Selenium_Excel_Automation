package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static org.apache.poi.ss.usermodel.Row.*;

public class ExcelUtils {
    private static XSSFSheet oWS;
    private static XSSFWorkbook oWB;
    private static XSSFCell oCell;
    private static XSSFRow oRow;

    public static void openExcelFile(String Path, String SheetName) throws Exception {
        try {
            // Open Excel
            FileInputStream ExcelFile;
            ExcelFile = new FileInputStream(Path);
            oWB = new XSSFWorkbook(ExcelFile);
            oWS = oWB.getSheet(SheetName);
        } catch (Exception e) {
            throw(e);
        }
    }

    public static void setExcelSheet(String SheetName) throws Exception {
        try {
            oWS = oWB.getSheet(SheetName);
        } catch (Exception e) {
            throw(e);
        }
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            oCell = oWS.getRow(RowNum).getCell(ColNum);
            return oCell.getStringCellValue();
        } catch (Exception e) {
            return "";
        }
    }

    public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
        try {
            oRow = oWS.getRow(RowNum);
            oCell = oRow.getCell(ColNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (oCell == null) {
                oCell = oRow.createCell(ColNum);
                oCell.setCellValue(Result);
            } else {
                oCell.setCellValue(Result);
            }

            FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
            oWB.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw (e);
        }
    }
}
