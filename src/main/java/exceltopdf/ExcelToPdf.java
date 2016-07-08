package main.java.exceltopdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import main.java.excelreader.ExcelReader;
import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.ExcelReaderTechnical;
import main.java.excelreader.entities.ExcelSheet;
import main.java.exceltopdf.pdfsections.ContentPage;
import main.java.exceltopdf.pdfsections.InsertPage;
import main.java.exceltopdf.pdfsections.Section;
import main.java.exceltopdf.pdfsections.TitlePage;
import main.java.utils.Utils;

public class ExcelToPdf {

    public static final String DEST = "meurguez.pdf";
    public static final String SRC = "./src/main/resources/Pannontej_Medve_nyar_July,Rankings,2016.06.27-2016.07.10_1467200027.xls";
    public static final String LOGO = "./src/main/resources/logo.png";
    public static final String TEMP_INSERT_PAGE = "tmp_insert_page.pdf";
    public static final String TEMP_TITLE_PAGE = "tmp_title_page.pdf";
    public static final String TEMP_CONTENT_PAGE = "tmp_content_page.pdf";
    public  List<String> FILES = new ArrayList<>();
	private static final int TITLE_PAGE = 0;
	private static final int INSERT_PAGE = 1;

    private ExcelSheet excelSheetRankings;
    private ExcelSheet excelSheetTechnical;
    

    public static void main(String args[]) throws IOException, DocumentException {
//        ExcelToPdf etdpf = new ExcelToPdf();
//        List<Section> sections = new ArrayList<>();
//        
//        TitlePage title = new TitlePage();
//        title.setStructure(new HeaderFooter(false, false, false, false, 1));
//        sections.add(title);
//        
//        etdpf.createPdf(
//                "./src/main/resources/Pannontej_Medve_nyar_July,Rankings,2016.06.27-2016.07.10_1467200027.xls",
//                DEST, sections);
        

        
    }

    public void createPdf(List<String> src, String dest, List<Section> sections)
            throws IOException, DocumentException {
        
        
        ExcelReader excelReader = null;
        
        for (int i = 0; i < src.size(); i++) {
        	File f = new File(src.get(i));
        	
        	if (f.getName().contains("Rankings")) {
                excelReader = new ExcelReaderRankings();
            }
            else if (f.getName().contains("Technical")) {
                excelReader = new ExcelReaderTechnical();
            }
            else {
                System.err.println("xls not recognized");
                return;
            }
        	
        	excelSheetRankings = excelReader.readExcelSheet(src.get(i));
        }
        
        
        
        TitlePage titlePage = (TitlePage) sections.get(TITLE_PAGE);
        
        titlePage.setCampaignName(excelSheetRankings.getCampaignName());
        titlePage.setStartDate(excelSheetRankings.getStartDate());
        titlePage.setEndDate(excelSheetRankings.getEndDate());
        createTitlePage(titlePage);
        
        
        InsertPage insertPage = (InsertPage) sections.get(INSERT_PAGE);
        
        createInsertPage(insertPage);
        
        for (int i = 2; i < sections.size(); i++) {
        	ContentPage contentPage = (ContentPage) sections.get(i);
        	contentPage.setExcelSheet(excelSheetRankings);
            createContentPage(contentPage);
        }
        
        
        PdfConcat.concat(FILES, dest);
    }

   

    private void createContentPage(ContentPage contentPage) throws DocumentException, IOException {
    	Document document = new Document();
    	document.setMargins(85, 85, 85, 113);
    	String fileName = Utils.getNewTmpFileName() + TEMP_CONTENT_PAGE;
		FileOutputStream outputStream = new FileOutputStream(fileName);
		FILES.add(fileName);
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		writer.setPageEvent(contentPage.getStructure());
		
		document.open();
    	boolean [] colsToPrint = {
    			true, contentPage.isImpressions(), contentPage.isUniqueCookies(), contentPage.isFrequency(),
                contentPage.isClicks(), contentPage.isClickingUsers(), contentPage.isClickThroughRate(),
                contentPage.isUniqueCTR()
        };
        
        TabCreator tc = new TabCreator(contentPage.getExcelSheet());
        document.add(tc.createTabCampaign(contentPage.getExcelSheet().getCampaignRows(),contentPage.getExcelSheet().getColumsLabels(),contentPage.getExcelSheet().getAll(),colsToPrint,true));
        
        document.close();
        
        writer.flush();
        writer.close();
        outputStream.flush();
        outputStream.close();
        
        writer = null;
        outputStream = null;
        System.gc();
		
	}

	public void createInsertPage(InsertPage insertPage) throws DocumentException, IOException {
		Document document = new Document();
		document.setMargins(85, 85, 85, 113);
		FileOutputStream outputStream = new FileOutputStream(TEMP_INSERT_PAGE);
		FILES.add(TEMP_INSERT_PAGE);
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		
		writer.setPageEvent(insertPage.getStructure());
		
		document.open();
		
		// custom text area
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		PdfContentByte cb = writer.getDirectContent();
		String customArea = insertPage.getCustomTextArea();
		Paragraph custom = new Paragraph(customArea);
		custom.setAlignment(Element.ALIGN_CENTER);
		
	    Chunk toMeasureCustom = new Chunk(customArea);
		
		float xPositionCustom = (PageSize.A4.getWidth() - toMeasureCustom.getWidthPoint()) / 2;
		cb.saveState();
		cb.beginText();
		cb.moveText(xPositionCustom, 260);
		cb.setFontAndSize(bf, 12);
		cb.showText(customArea);
		cb.endText();
		cb.restoreState();
		
		
		cb.closePathEoFillStroke();
                
                document.close();
                
                writer.flush();
                writer.close();
                outputStream.flush();
                outputStream.close();
                
                writer = null;
                outputStream = null;
                System.gc();
    }
    
    public void createTitlePage(TitlePage titlePage) throws DocumentException, IOException {
		Document document = new Document();
		document.setMargins(85, 85, 85, 113);
		FileOutputStream outputStream = new FileOutputStream(TEMP_TITLE_PAGE);
		FILES.add(TEMP_TITLE_PAGE);
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		
		writer.setPageEvent(titlePage.getStructure());
		document.open();
		
		// campaignName
		Paragraph campaignName = new Paragraph(titlePage.getCampaignName());
		campaignName.setAlignment(Element.ALIGN_CENTER);
		
		PdfContentByte cb = writer.getDirectContent();
	    BaseFont bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	    Chunk toMeasureSize = new Chunk(titlePage.getCampaignName());
	    Font fontTitle = new Font();
	    fontTitle.setStyle(Font.BOLD);
	    fontTitle.setSize(16);
		toMeasureSize.setFont(fontTitle);
		
		float xPosition = (PageSize.A4.getWidth() - toMeasureSize.getWidthPoint()) / 2;
		cb.saveState();
		cb.beginText();
		cb.moveText(xPosition, 340);
		cb.setFontAndSize(bfBold, 16);
		cb.showText(titlePage.getCampaignName());
		cb.endText();
		cb.restoreState();
		
		// dates
		String datesString = "From " + titlePage.getStartDate() + " to " + titlePage.getEndDate();
		Paragraph dates = new Paragraph(datesString);
		dates.setAlignment(Element.ALIGN_CENTER);
		
	    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	    Chunk toMeasureDates = new Chunk(datesString);
		
		float xPositionDates = (PageSize.A4.getWidth() - toMeasureDates.getWidthPoint()) / 2;
		cb.saveState();
		cb.beginText();
		cb.moveText(xPositionDates, 300);
		cb.setFontAndSize(bf, 12);
		cb.showText(datesString);
		cb.endText();
		cb.restoreState();
		
		// custom text area
		String customArea = titlePage.getBelowTitle();
		Paragraph custom = new Paragraph(customArea);
		custom.setAlignment(Element.ALIGN_CENTER);
		
	    Chunk toMeasureCustom = new Chunk(customArea);
		
		float xPositionCustom = (PageSize.A4.getWidth() - toMeasureCustom.getWidthPoint()) / 2;
		cb.saveState();
		cb.beginText();
		cb.moveText(xPositionCustom, 260);
		cb.setFontAndSize(bf, 12);
		cb.showText(customArea);
		cb.endText();
		cb.restoreState();
		
		cb.closePathEoFillStroke();
		
		document.close();
		
		writer.flush();
		writer.close();
		outputStream.flush();
		outputStream.close();
		
		writer = null;
		outputStream = null;
		System.gc();
    }
    
    
    
}
