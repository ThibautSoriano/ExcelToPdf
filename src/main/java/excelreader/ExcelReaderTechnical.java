package main.java.excelreader;



import main.java.excelreader.entities.ExcelSheet;

public class ExcelReaderTechnical extends ExcelReader{
    
    
    public ExcelReaderTechnical() {
        excelSheet = new ExcelSheet();
        documentStructure = new DocumentStructureTechnical();
    }

    @Override
    public void fillExcelSheet() {
        
        excelSheet.setCampaignName("");
        
        readStartDate();
        readEndDate();
        super.readCampaignRows();
        
    }

}
