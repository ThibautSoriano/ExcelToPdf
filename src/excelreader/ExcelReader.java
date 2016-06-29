package excelreader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import excelreader.entities.Advertisement;
import excelreader.entities.ExcelSheet;

public class ExcelReader {
	private ExcelSheet excelSheet = new ExcelSheet();
	
	private HSSFSheet sheet;
	
	/**
	 * @return the ExcelSheet with fields filled in
	 */
	public ExcelSheet read() {
	
	String filename = "meurguez.xls";

    FileInputStream fis = null;
    try {
        fis = new FileInputStream(filename);

        HSSFWorkbook workbook = new HSSFWorkbook(fis);

        sheet = workbook.getSheetAt(0);
        
        excelSheet.setCampaignName(readCampaignName());
        
        excelSheet.setStartDate(readStartDate());
        
        excelSheet.setEndDate(readEndDate());
        
//        excelSheet.setAdvertisements(readAdvertisements());
        
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

	// TODO
//	private List<Advertisement> readAdvertisements() {
//		String currentName = "";
//		HSSFRow currentRow;
//		
//		for (int i = 0; !currentName.equals(""); i++) {
//			currentRow = sheet.getRow(DocumentStructure.ADVERTISEMENTS_START_ROW);
//			
//		}
//		
//		HSSFRow row = sheet.getRow(DocumentStructure.DATES_ROW);
//		
//		String dates = row.getCell(DocumentStructure.DATES_COL).getStringCellValue();
//	}

	public String readStartDate() {
		HSSFRow row = sheet.getRow(DocumentStructure.DATES_ROW);
		
		String dates = row.getCell(DocumentStructure.DATES_COL).getStringCellValue();
		
		String[] s = dates.split(" ");
		
		return s[0];
	}
	
	public String readEndDate() {
		HSSFRow row = sheet.getRow(DocumentStructure.DATES_ROW);
		
		String dates = row.getCell(DocumentStructure.DATES_COL).getStringCellValue();
		
		String[] s = dates.split(" ");
		
		return s[2];
	}

	public String readCampaignName() {
		
		HSSFRow row = sheet.getRow(DocumentStructure.CAMPAIGN_NAME_ROW);
		
		return row.getCell(DocumentStructure.CAMPAIGN_NAME_COL).getStringCellValue();
	}
	
	
	public static void main(String args[]) {
		ExcelReader zhengqin = new ExcelReader();
		ExcelSheet junior = zhengqin.read();
		System.out.println("le nom de la campagne est : " + junior.getCampaignName());
		System.out.println("la date de début est : " + junior.getStartDate());
		System.out.println("la date de fin est : " + junior.getEndDate());
	}

}
