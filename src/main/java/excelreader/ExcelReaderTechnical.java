package main.java.excelreader;

import main.java.excelreader.entities.ExcelSheet;

public class ExcelReaderTechnical extends ExcelReader{
    
    public ExcelReaderTechnical() {
        excelSheet = new ExcelSheet();
        type = "Technical";
        documentStructure = new DocumentStructureTechnical();
    }

    @Override
    public void fillExcelSheet() {
        excelSheet.setCampaignName("");
        readStartDate();
        readEndDate();
        super.readCampaignRows();
        excelSheet.setColumsLabels(super.getColumsLabels());
    }
}
