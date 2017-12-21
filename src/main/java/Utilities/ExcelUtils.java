package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static org.apache.poi.ss.usermodel.Row.*;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelUtils {
    private static XSSFSheet oWS;
    private static XSSFWorkbook oWB;
    private static XSSFCell oCell;
    private static XSSFRow oRow;
    private static XSSFRow oHeaderRow;

    public static void openExcelFile(String Path) throws Exception {
        try {
            // Open Excel
            FileInputStream ExcelFile;
            ExcelFile = new FileInputStream(Path);
            oWB = new XSSFWorkbook(ExcelFile);
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
    public static HashMap<String,String> getEnvironmentParams(String chosenENV) throws Exception {
        // We're getting environment settings so switch to the Env sheet
        setExcelSheet("Env");
        // Now get the column and row number for the chosen environment
        int iRow = getRowNumForValue("ENV", chosenENV);
        return getDataByRowNum(iRow);
    }

    public static List<String> getTestScheduleParams() throws Exception {
        List<String> al = new ArrayList<String>();
        // We're getting test case settings so switch to the Test Schedule sheet
        setExcelSheet("TestSchedule");
        // Get the test case by it's header value
        int iCol = getColNumberFromHeaderValue("TestCase");
        // Loop round rows in sheet getting all test case values into array
        Iterator <Row> rowIterator = oWS.rowIterator();
        while (rowIterator.hasNext()) {
            oRow = (XSSFRow) rowIterator.next();
            al.add(String.valueOf(oRow.getCell(iCol)) + ':' + String.valueOf(oRow.getCell(iCol + 1)));
        }
        return al;
    }

    public static List<String> getTestCaseParams(String strTC) throws Exception {
        List<String> al = new ArrayList<String>();

        // We're getting test case settings so switch to the Test Schedule sheet
        setExcelSheet("TestCase");
        // Get the test case by it's header value
        int iCol = getColNumberFromHeaderValue("TestCase");
        // Loop round rows in sheet getting all test case values into array
        Iterator <Row> rowIterator = oWS.rowIterator();
        while (rowIterator.hasNext()) {
            oRow = (XSSFRow) rowIterator.next();
            // Get the values next to the matching test case
            if (String.valueOf(oRow.getCell(iCol)).equals(strTC)) {
                List<String> il = new ArrayList<String>();
                //int noCols = oRow.getPhysicalNumberOfCells();
                Iterator <Cell> cellIterator = oRow.cellIterator();
                while (cellIterator.hasNext()) {
                    oCell = (XSSFCell) cellIterator.next();
                    String thisCell = oCell.getStringCellValue();
                    il.add(thisCell);
                }
                // Remove the first two elements as they're already sent
                il.remove(0);
                il.remove(0);
                al.add(String.valueOf(oRow.getCell(iCol + 1)) + ">>" + il);
            }
        }
        return al;
    }

    private static int getColNumberFromHeaderValue(String headerVal) {
        int iCol = 0;
        oRow = oWS.getRow(0);
        Iterator <Cell> cellIterator = oRow.cellIterator();
        while (cellIterator.hasNext()) {
            oCell = (XSSFCell) cellIterator.next();
            String thisCell = oCell.getStringCellValue();
            if (thisCell.equals(headerVal)) {
                iCol = oCell.getColumnIndex();
                break;
            }
        }
        return iCol;
    }
    private static int getRowNumForValue(String headerVal, String searchVal) {
        int iCol = 0;
        int iRow = 0;
        oRow = oWS.getRow(0);
        Iterator <Cell> cellIterator = oRow.cellIterator();
        while (cellIterator.hasNext()) {
            oCell = (XSSFCell) cellIterator.next();
            String thisCell = oCell.getStringCellValue();
            if (thisCell.equals(headerVal)) {
                iCol = oCell.getColumnIndex();
                break;
            }
        }
        Iterator <Row> rowIterator = oWS.rowIterator();
        while (rowIterator.hasNext()) {
            oRow = (XSSFRow) rowIterator.next();
            oCell = oRow.getCell(iCol);
            if (oCell.getStringCellValue().equals(searchVal)) {
                iRow = oRow.getRowNum();
                break;
            }
        }
        return iRow;
    }

    private static HashMap<String,String> getDataByRowNum(int RowNum) {
        DataFormatter formatter = new DataFormatter();
        HashMap<String,String> a = new HashMap<String,String>();
        oHeaderRow = oWS.getRow(0);
        oRow = oWS.getRow(RowNum);
        Iterator <Cell> cellIterator = oRow.cellIterator();
        while (cellIterator.hasNext()) {
            oCell = (XSSFCell) cellIterator.next();
            String headerVal = String.valueOf(oHeaderRow.getCell(oCell.getColumnIndex()));
            a.put(headerVal, formatter.formatCellValue(oRow.getCell(oCell.getColumnIndex())));
        }
        return a;
    }
}
