package main.java.exceltopdf;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

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
	public static int CURRENT_PAGE_NUMBER = 0;

    private ExcelSheet excelSheetRankings;
    private List<ContentPage> content = new ArrayList<>();

    public void createPdf(List<String> src, String dest, List<Section> sections)
            throws IOException, DocumentException {
        
        
        
        for (int i = 2; i < sections.size(); i++) {
        	ContentPage contentPage = (ContentPage) sections.get(i);
        	
        	if (contentPage.getExcelReader().getType().equals("Rankings")) {
        		excelSheetRankings = contentPage.getExcelReader().readExcelSheet(src.get((i-2)));
        		contentPage.setExcelSheet(excelSheetRankings);
        		content.add(contentPage);
        	}
        	if (contentPage.getExcelReader().getType().equals("Technical")) {
        		contentPage.setExcelSheet(contentPage.getExcelReader().readExcelSheet(src.get((i-2))));
        		content.add(contentPage);
        	}
            else {
                System.err.println("xls not recognized");
            }
        }
        
        TitlePage titlePage = (TitlePage) sections.get(TITLE_PAGE);
        
        titlePage.setCampaignName(excelSheetRankings.getCampaignName());
        titlePage.setStartDate(excelSheetRankings.getStartDate());
        titlePage.setEndDate(excelSheetRankings.getEndDate());
        createTitlePage(titlePage);
        
        
        InsertPage insertPage = (InsertPage) sections.get(INSERT_PAGE);
        
        createInsertPage(insertPage);
        
        for (int i = 0; i < content.size(); i++) {
            createContentPage(content.get(i));
        }
        
        
        PdfConcat.concat(FILES, dest);
    }

   

    private void createContentPage(ContentPage contentPage) throws DocumentException, IOException {
    	BarChartCreator barChartCreator = new BarChartCreator();
    	Document document = new Document();
    	document.setMargins(85, 85, 85, 113);
    	String fileName = Utils.getNewTmpFileName() + TEMP_CONTENT_PAGE;
		FileOutputStream outputStream = new FileOutputStream(fileName);
		FILES.add(fileName);
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		writer.setPageEvent(contentPage.getStructure());
		
		document.open();
	
		document.add(new Paragraph(contentPage.getExcelReader().getType() + " chart\n\n"));
		JFreeChart chart = barChartCreator.getChart(contentPage.getExcelSheet().getCampaignRows());
        int width = 600;
        int height = (5 * 80) + 50;
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);

        					
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
        image.scalePercent(70);
        image.setAlignment(Image.MIDDLE);
        document.add(image);
        document.add(new Paragraph("\n\n\n"));
        
        document.add(new Paragraph(contentPage.getExcelReader().getType() + " full datas table\n\n"));
    	boolean [] colsToPrint = {
    			true, contentPage.isImpressions(), contentPage.isUniqueCookies(), contentPage.isFrequency(),
                contentPage.isClicks(), contentPage.isClickingUsers(), contentPage.isClickThroughRate(),
                contentPage.isUniqueCTR()
        };
        
        TabCreator tc = new TabCreator(contentPage.getExcelSheet());
        document.add(tc.createTabCampaign(contentPage.getExcelSheet().getCampaignRows(),contentPage.getExcelSheet().getColumsLabels(),contentPage.getExcelSheet().getAll(),colsToPrint,true));
        
        
        document.close();
        CURRENT_PAGE_NUMBER += writer.getPageNumber();
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
		cb.moveText(xPositionCustom, PageSize.A4.getHeight() - 505);
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
		cb.moveText(xPosition, PageSize.A4.getHeight() - 335);
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
		cb.moveText(xPositionDates, PageSize.A4.getHeight() - 365);
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
		cb.moveText(xPositionCustom, PageSize.A4.getHeight() - 395);
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
