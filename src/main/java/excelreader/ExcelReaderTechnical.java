package main.java.excelreader;

import java.io.File;

import main.java.excelreader.entities.ExcelSheet;

public class ExcelReaderTechnical extends ExcelReader{
    
    public ExcelReaderTechnical() {
        excelSheet = new ExcelSheet();
        type = "Technical";
        documentStructure = new DocumentStructureTechnical();
    }

    @Override
    public void fillExcelSheet(String filePath) {
        
        excelSheet.setCampaignName(new File(filePath).getName().split("%")[0]);
        readStartDate();
        readEndDate();
        super.readCampaignRows();
        excelSheet.setColumsLabels(super.getColumsLabels());
    }
}
