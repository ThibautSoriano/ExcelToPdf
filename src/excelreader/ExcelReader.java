package excelreader;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import excelreader.entities.Advertisement;
import excelreader.entities.ExcelSheet;

public class ExcelReader {
	private ExcelSheet excelSheet = new ExcelSheet();
	
	private HSSFSheet sheet;
	
	/**
	 * @return the ExcelSheet with fields filled in
	 */
	public ExcelSheet read(String fileName) {
	
	//String filename = "meurguez.xls";

    FileInputStream fis = null;
    try {
        fis = new FileInputStream(fileName);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);

        // reading the first sheet (there is only one)
        sheet = workbook.getSheetAt(0);
        
        readCampaignName();
        readStartDate();
        readEndDate();
        readAdvertisements();
        
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
	
	// reading methods
	
	public void readCampaignName() {
		
		HSSFRow row = sheet.getRow(DocumentStructure.CAMPAIGN_NAME_ROW);
		
		excelSheet.setCampaignName(row.getCell(DocumentStructure.CAMPAIGN_NAME_COL).getStringCellValue());
	}

	public void readStartDate() {
		HSSFRow row = sheet.getRow(DocumentStructure.DATES_ROW);
		
		String dates = row.getCell(DocumentStructure.DATES_COL).getStringCellValue();
		
		String[] s = dates.split(" ");
		
		excelSheet.setStartDate(s[0]);
	}
	
	public void readEndDate() {
		HSSFRow row = sheet.getRow(DocumentStructure.DATES_ROW);
		
		String dates = row.getCell(DocumentStructure.DATES_COL).getStringCellValue();
		
		String[] s = dates.split(" ");
		
		excelSheet.setEndDate(s[2]);
	}
	
	public void readAdvertisements() {
		int index = DocumentStructure.ADVERTISEMENTS_START_ROW;
		
		// we iterate and if we see a placement name = "All " then we break
		// and yes, there is a blank space after "All"
		while (true) {
			System.out.println("Line " + (index + 1) + " ...");
			HSSFRow row = sheet.getRow(index);
			String placementName = row.getCell(DocumentStructure.PLACEMENT_NAME).getStringCellValue();
			int impressions = 0;
			if (row.getCell(DocumentStructure.IMPRESSIONS).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				impressions = (int) row.getCell(DocumentStructure.IMPRESSIONS).getNumericCellValue();
			}
			int uniqueCookies = 0;
			if (row.getCell(DocumentStructure.UNIQUE_COOKIES).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				uniqueCookies = (int) row.getCell(DocumentStructure.UNIQUE_COOKIES).getNumericCellValue();
			}
			int frequency = 0;
			if (row.getCell(DocumentStructure.FREQUENCY).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				frequency = (int) row.getCell(DocumentStructure.FREQUENCY).getNumericCellValue();
			}
			int clicks = 0;
			if (row.getCell(DocumentStructure.CLICKS).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				clicks = (int) row.getCell(DocumentStructure.CLICKS).getNumericCellValue();
			}
			int clickingUsers = 0;
			if (row.getCell(DocumentStructure.CLICKING_USERS).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				clickingUsers = (int) row.getCell(DocumentStructure.CLICKING_USERS).getNumericCellValue();
			}
			float clickThroughRate = 0;
			if (row.getCell(DocumentStructure.CLICK_THROUGH_RATE).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				clickThroughRate = (float) row.getCell(DocumentStructure.CLICK_THROUGH_RATE).getNumericCellValue();
			}
			float uniqueCTR = 0;
			if (row.getCell(DocumentStructure.UNIQUE_CTR).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				uniqueCTR = (float) row.getCell(DocumentStructure.UNIQUE_CTR).getNumericCellValue();
			}
			
			Advertisement newAd = new Advertisement(placementName, impressions, uniqueCookies, frequency, clicks, clickingUsers, clickThroughRate, uniqueCTR);
			
			if (placementName.equals("All ")) {
				excelSheet.setAll(newAd);
				break;
			}
			else {				
				excelSheet.getAdvertisements().add(newAd);
				index++;
			}
		}
	}
	
	public static void main(String args[]) {
		//ExcelReader zhengqin = new ExcelReader();
		//ExcelSheet junior = zhengqin.read();
		//System.out.println(junior);
	}

}
