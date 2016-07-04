package main.java.excelreader;



import org.apache.poi.hssf.usermodel.HSSFRow;


import main.java.excelreader.entities.ExcelSheet;


public class ExcelReaderRankings extends ExcelReader {
    

    
    @Override
    public void fillExcelSheet() {
        readCampaignName();
        super.readStartDate();
        super.readEndDate();
        super.readCampaignRows();
        excelSheet.setColumsLabels(super.getColumsLabels());

    }

    public ExcelReaderRankings() {
        excelSheet = new ExcelSheet();
       documentStructure = new DocumentStructureRankings();
    }
    
    public void readCampaignName() {

        HSSFRow row = sheet.getRow( ((DocumentStructureRankings) documentStructure).getCampaignNameRow());

        excelSheet.setCampaignName(
                row.getCell(((DocumentStructureRankings) documentStructure).getCampaignNameCol())
                        .getStringCellValue());
    }

   

    

    public static void main(String args[]) {
        // ExcelReader zhengqin = new ExcelReader();
        // ExcelSheet junior = zhengqin.read();
        // System.out.println(junior);
    }

    

}
