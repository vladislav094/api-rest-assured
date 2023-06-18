package api.genderize.data_provider;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private String XMLFilePath;
    private XSSFSheet sheet;
    private XSSFWorkbook book;
    private String sheetName;

    // Legacy. Not using now
    ExcelReader(String XMLFilePath) throws IOException {
        this.XMLFilePath = XMLFilePath;
        File file = new File(XMLFilePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet("latinMaleNames");
        } catch (IOException e) {
            throw new IOException("Не поддерживаемый формат.");
        }
    }

    ExcelReader(String XMLFilePath, String sheetName) throws IOException {
        this.XMLFilePath = XMLFilePath;
        this.sheetName = sheetName;
        File file = new File(XMLFilePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet(sheetName);
        } catch (IOException e) {
            throw new IOException("Не поддерживаемый формат.");
        }
    }

    public String cellToString(XSSFCell cell) throws Exception {
        Object result = null;
        CellType type = cell.getCellType();
        switch (type){
            case NUMERIC:
                result = cell.getNumericCellValue();
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BLANK:
                result = "";
                break;
            default: throw new Exception("Ошибка чтения");
        }
        return result.toString();
    }

    private int XMLCountColumn(){
        return sheet.getRow(0).getLastCellNum();
    }

    private int XMLCountRow(){
        return sheet.getLastRowNum()+1;
    }

    public String[][] getSheetDataForTDD() throws Exception {
        File file = new File(XMLFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        int numberOfColumn = XMLCountColumn();
        int columnOfRow = XMLCountRow();
        String[][] data = new String[columnOfRow-1][numberOfColumn];
        for (int i = 1; i < columnOfRow; i++){
            for (int j = 0; j <numberOfColumn; j++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i-1][j] = value;
                if (value == null){
                    System.out.println("Пустые");
                }
            }
        }
        return data;
    }

    public String[][] getCustomSheetDataForTDD() throws Exception {
        File file = new File(XMLFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        int numberOfColumn = XMLCountColumn();
        int columnOfRow = XMLCountRow();
        String[][] data = new String[columnOfRow-1][numberOfColumn];
        for (int i = 1; i < columnOfRow; i++){
            for (int j = 0; j <numberOfColumn; j++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i-1][j] = value;
                if (value == null){
                    System.out.println("Пустые");
                }
            }
        }
        return data;
    }
}
