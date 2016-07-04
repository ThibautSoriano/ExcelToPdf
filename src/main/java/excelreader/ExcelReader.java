package main.java.excelreader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import main.java.excelreader.entities.CampaignRow;
import main.java.excelreader.entities.ExcelSheet;


public abstract class ExcelReader {

    protected ExcelSheet excelSheet;

    protected HSSFSheet sheet;
    
    protected DocumentStructure documentStructure;
    
    /**
     * Fill the excelSheet attribute with data from the excel file
     */
    public abstract void fillExcelSheet();
    
    /**
     * @return the ExcelSheet with fields filled in
     */
    public ExcelSheet readExcelSheet(String filePath) {

        // String filename = "meurguez.xls";

        
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);

            // reading the first sheet (there is only one)
            sheet = workbook.getSheetAt(0);

            fillExcelSheet(); 

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excelSheet;
    }
    
    
    public void readStartDate() {
        HSSFRow row = sheet.getRow(documentStructure.getDatesRow());

        String dates = row.getCell(documentStructure.getDatesCol())
                .getStringCellValue();

        String[] s = dates.split(" ");
        
        excelSheet.setStartDate(s[0]);
    }

    public void readEndDate() {
        HSSFRow row = sheet.getRow(documentStructure.getDatesRow());

        String dates = row.getCell(documentStructure.getDatesCol())
                .getStringCellValue();

        String[] s = dates.split(" ");

        excelSheet.setEndDate(s[2]);
    }
    
    
    public List<String> getColumsLabels() {
        List<String> labels = new ArrayList<String>();
        
        HSSFRow row = sheet.getRow(documentStructure.getColumsLabelsRow());
        
//        while (true) {
//            row.get
//            
//            if (row ==  null)
//            {
//                excelSheet.setAll(cr);
//                break;
//            }
//        }
        return null;
    }
    
    public void readCampaignRows() {
        int index = documentStructure.getCampaignRowStartRow();

        // we iterate and if we see a placement name = "All " then we break
        // and yes, there is a blank space after "All"
        while (true) {
            System.out.println("Line " + (index + 1) + " ...");
            HSSFRow row = sheet.getRow(index);
            String firstColumnData = row.getCell(documentStructure.getFirstColumnLabelCol())
                    .getStringCellValue();
            int impressions = 0;
            if (row.getCell(documentStructure.getImpressionsCol())
                    .getCellType() == Cell.CELL_TYPE_NUMERIC) {
                impressions = (int) row.getCell(documentStructure.getImpressionsCol())
                        .getNumericCellValue();
            }
            int uniqueCookies = 0;
            if (row.getCell(documentStructure.getUniquesCookiesCol())
                    .getCellType() == Cell.CELL_TYPE_NUMERIC) {
                uniqueCookies = (int) row
                        .getCell(documentStructure.getUniquesCookiesCol())
                        .getNumericCellValue();
            }
            int frequency = 0;
            if (row.getCell(documentStructure.getFrequencyCol())
                    .getCellType() == Cell.CELL_TYPE_NUMERIC) {
                frequency = (int) row.getCell(documentStructure.getFrequencyCol())
                        .getNumericCellValue();
            }
            int clicks = 0;
            if (row.getCell(documentStructure.getClicksCol())
                    .getCellType() == Cell.CELL_TYPE_NUMERIC) {
                clicks = (int) row.getCell(documentStructure.getClicksCol())
                        .getNumericCellValue();
            }
            int clickingUsers = 0;
            if (row.getCell(documentStructure.getClickingUsersCol())
                    .getCellType() == Cell.CELL_TYPE_NUMERIC) {
                clickingUsers = (int) row
                        .getCell(documentStructure.getClickingUsersCol())
                        .getNumericCellValue();
            }
            float clickThroughRate = 0;
            if (row.getCell(documentStructure.getClickThroughRateCol())
                    .getCellType() == Cell.CELL_TYPE_NUMERIC) {
                clickThroughRate = (float) row
                        .getCell(documentStructure.getClickThroughRateCol())
                        .getNumericCellValue();
            }
            float uniqueCTR = 0;
            if (row.getCell(documentStructure.getUniqueCtrCol())
                    .getCellType() == Cell.CELL_TYPE_NUMERIC) {
                uniqueCTR = (float) row.getCell(documentStructure.getUniqueCtrCol())
                        .getNumericCellValue();
            }

            CampaignRow cr = new CampaignRow(firstColumnData, impressions,
                    uniqueCookies, frequency, clicks, clickingUsers,
                    clickThroughRate, uniqueCTR);

            
            HSSFRow nextRow = sheet.getRow(index+1);
            
            if (nextRow ==  null)
            {
                excelSheet.setAll(cr);
                break;
            }
                    
            else {
                excelSheet.getCampaignRows().add(cr);
                index++;
            }
        }
    }
    
}
