package main.java.exceltopdf;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.text.BadElementException;
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

import main.java.datasdownloading.entities.Campaign;
import main.java.excelreader.entities.CampaignRow;
import main.java.exceltopdf.pdfsections.ContentPage;
import main.java.exceltopdf.pdfsections.InsertPage;
import main.java.exceltopdf.pdfsections.Section;
import main.java.exceltopdf.pdfsections.TitlePage;
import main.java.utils.Percentage;
import main.java.utils.Utils;

public class ExcelToPdf {
	
    public static final String TEMP_INSERT_PAGE = "tmp_insert_page.pdf";
    public static final String TEMP_TITLE_PAGE = "tmp_title_page.pdf";
    public static final String TEMP_CONTENT_PAGE = "tmp_content_page.pdf";
    public  List<String> FILES = new ArrayList<>();
	private static final int TITLE_PAGE = 0;
	private static final int INSERT_PAGE = 1;
	public static int CURRENT_PAGE_NUMBER = 0;

    private String campaignName = "";
    private String startDate = "";
    private String endDate = "";
    private List<ContentPage> content = new ArrayList<>();

    public void createPdf(List<String> src, String dest, List<Section> sections, boolean insertPageOn)
            throws IOException, DocumentException {
        for (int i = 2; i < sections.size(); i++) {
        	ContentPage contentPage = (ContentPage) sections.get(i);
        	if (contentPage.getExcelReader().getType().equals("Rankings")) {
        		contentPage.setExcelSheet(contentPage.getExcelReader().readExcelSheet(src.get((i-2))));
        		content.add(contentPage);
        		campaignName = contentPage.getExcelSheet().getCampaignName();
        		startDate = contentPage.getExcelSheet().getStartDate();
        		endDate = contentPage.getExcelSheet().getEndDate();
        	} else if (contentPage.getExcelReader().getType().equals("Technical")) {
        		contentPage.setExcelSheet(contentPage.getExcelReader().readExcelSheet(src.get((i-2))));
        		content.add(contentPage);
        		campaignName = contentPage.getExcelSheet().getCampaignName();
                        startDate = contentPage.getExcelSheet().getStartDate();
                        endDate = contentPage.getExcelSheet().getEndDate();
        	}
            else {
                System.err.println("xls not recognized");
            }
        }
        
        TitlePage titlePage = (TitlePage) sections.get(TITLE_PAGE);
        
        titlePage.setCampaignName(campaignName);
        titlePage.setStartDate(startDate);
        titlePage.setEndDate(endDate);
        createTitlePage(titlePage);
        
        if (insertPageOn) {
            InsertPage insertPage = (InsertPage) sections.get(INSERT_PAGE);
            
            createInsertPage(insertPage);
        }
                
        for (int i = 0; i < content.size(); i++) {
            createContentPage(content.get(i), false);
        }
        
        
        
        PdfConcat c = new PdfConcat();
        c.concat(FILES, dest);
    }

   public void createPdfDownload(Campaign campaign, String dest, List<Section> sections, boolean insertPageOn) throws DocumentException, IOException {
	   SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	   TitlePage titlePage = (TitlePage) sections.get(TITLE_PAGE);
       
       titlePage.setCampaignName(campaign.getCampaignHeader().getCampaignName());
       titlePage.setStartDate(f.format(campaign.getCampaignHeader().getStartDate()));
       titlePage.setEndDate(f.format(campaign.getCampaignHeader().getEndDate()));
       createTitlePage(titlePage);
       
       if (insertPageOn) {
           InsertPage insertPage = (InsertPage) sections.get(INSERT_PAGE);
           
           createInsertPage(insertPage);
       }
       
       
       ContentPage contentPage = (ContentPage) sections.get(2);
       contentPage.setCampaign(campaign);
       createContentPage(contentPage, true);
       
       
       PdfConcat c = new PdfConcat();
       c.concat(FILES, dest);
   }

    private void createContentPage(ContentPage contentPage, boolean download) throws DocumentException, IOException {
    	BarChartCreator barChartCreator = new BarChartCreator();
    	PieChartCreator pieChartCreator = new PieChartCreator();
    	Document document = new Document();
    	document.setMargins(85, 85, 85, 113);
    	String fileName = Utils.getNewTmpFileName() + TEMP_CONTENT_PAGE;
		FileOutputStream outputStream = new FileOutputStream(fileName);
		FILES.add(fileName);
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		writer.setPageEvent(contentPage.getStructure());
		List<CampaignRow> rows = null;
		document.open();
		if (download) {
		    rows = contentPage.getCampaign().getCampaignContent();
		    document.add(new Paragraph("Charts\n\n"));
		}
		else {
		    rows = contentPage.getExcelSheet().getCampaignRows();
		    document.add(new Paragraph(contentPage.getExcelReader().getType() + " charts\n\n"));
		}		
		
		if (download || contentPage.getExcelReader().getType().equals("Rankings")) {
			if (contentPage.isImpressions()) {
			    JFreeChart impressionsChart = barChartCreator.getChart(rows, CampaignRow.IMPRESSIONS_INDEX, "Impressions", "Ads");
			    if (impressionsChart != null)
				document.add(getImageBar(impressionsChart, writer));
			}
			if (contentPage.isUniqueCookies()) {
			    JFreeChart uniqueCookiesChart = barChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.UNIQUE_COOKIES_INDEX), CampaignRow.UNIQUE_COOKIES_INDEX, "Unique cookies", "Ads");
			    if (uniqueCookiesChart != null)
				document.add(getImageBar(uniqueCookiesChart, writer));
			}
			if (contentPage.isReach()) {
			    JFreeChart reachChart = barChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.REACH_INDEX), CampaignRow.REACH_INDEX, "Reach", "Ads");
			    if (reachChart != null)
				document.add(getImageBar(reachChart, writer));
			}
			if (contentPage.isFrequency()) {
			    JFreeChart frequencyChart = barChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.FREQUENCY_INDEX), CampaignRow.FREQUENCY_INDEX, "Frequency", "Ads");
			    if (frequencyChart != null)
				document.add(getImageBar(frequencyChart, writer));
			}
			if (contentPage.isClicks()) {
			    JFreeChart clicksChart = barChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.CLICKS_INDEX), CampaignRow.CLICKS_INDEX, "Clicks", "Ads");
			    if (clicksChart != null)
				document.add(getImageBar(clicksChart, writer));
			}
			if (contentPage.isClickingUsers()) {
			    JFreeChart clickingUsersChart = barChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.CLICKING_USERS_INDEX), CampaignRow.CLICKING_USERS_INDEX, "Clicking users", "Ads");
			    if (clickingUsersChart != null)
				document.add(getImageBar(clickingUsersChart, writer));
			}
			if (contentPage.isClickThroughRate()) {
			    JFreeChart clickThroughRateChart = barChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.CLICK_THROUGH_RATE_INDEX), CampaignRow.CLICK_THROUGH_RATE_INDEX, "Click through rate", "Ads");
			    if (clickThroughRateChart != null)
				document.add(getImageBar(clickThroughRateChart, writer));
			}
			if (contentPage.isUniqueCTR()) {
			    JFreeChart uniqueCTRChart = barChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.UNIQUE_CTR_INDEX), CampaignRow.UNIQUE_CTR_INDEX, "Unique CTR", "Ads");
			    if (uniqueCTRChart != null)
				document.add(getImageBar(uniqueCTRChart, writer));
			}
		}		
		
		if (!download && contentPage.getExcelReader().getType().equals("Technical")) {
			if (contentPage.isImpressions()) {
				document.add(getImagePie(pieChartCreator.getChart(rows, CampaignRow.IMPRESSIONS_INDEX, "Impressions per county", false, 0, 0), writer));
			}
			if (contentPage.isUniqueCookies()) {
				document.add(getImagePie(pieChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.UNIQUE_COOKIES_INDEX), CampaignRow.UNIQUE_COOKIES_INDEX, "Unique cookies per county", false, 0, 0), writer));
			}
			if (contentPage.isFrequency()) {
				document.add(getImagePie(pieChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.FREQUENCY_INDEX), CampaignRow.FREQUENCY_INDEX, "Frequency per county", true, CampaignRow.IMPRESSIONS_INDEX, CampaignRow.UNIQUE_COOKIES_INDEX), writer));
			}
			if (contentPage.isClicks()) {
				document.add(getImagePie(pieChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.CLICKS_INDEX), CampaignRow.CLICKS_INDEX, "Clicks per county", false, 0, 0), writer));
			}
			if (contentPage.isClickingUsers()) {
				document.add(getImagePie(pieChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.CLICKING_USERS_INDEX), CampaignRow.CLICKING_USERS_INDEX, "Clicking users per county", false, 0, 0), writer));
			}
			if (contentPage.isClickThroughRate()) {
				document.add(getImagePie(pieChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.CLICK_THROUGH_RATE_INDEX), CampaignRow.CLICK_THROUGH_RATE_INDEX, "Click through rate per county", true, CampaignRow.CLICKS_INDEX, CampaignRow.IMPRESSIONS_INDEX), writer));
			}
			if (contentPage.isUniqueCTR()) {
				document.add(getImagePie(pieChartCreator.getChart(CampaignRow.sortBy(rows, CampaignRow.UNIQUE_CTR_INDEX), CampaignRow.UNIQUE_CTR_INDEX, "Unique CTR per county", true, CampaignRow.CLICKING_USERS_INDEX, CampaignRow.UNIQUE_COOKIES_INDEX), writer));
			}
		}
		
		
		document.newPage();
		writer.setPageEmpty(false);
        
		
		boolean reachOrCookies = false;
		List<String> labels;
		
		if (download) {
		    document.add(new Paragraph("Full datas table\n\n"));
		    reachOrCookies = contentPage.isReach();
		    labels = contentPage.getCampaign().getColumsLabels();
		}
		else {
		    document.add(new Paragraph(contentPage.getExcelReader().getType() + " full datas table\n\n"));
		    reachOrCookies = contentPage.isUniqueCookies();
		    labels = contentPage.getExcelSheet().getColumsLabels();
		}
		
    	boolean [] colsToPrint = {
    			true, contentPage.isImpressions(), reachOrCookies, contentPage.isFrequency(),
                contentPage.isClicks(), contentPage.isClickingUsers(), contentPage.isClickThroughRate(),
                contentPage.isUniqueCTR()
        };
        
        TabCreator tc = new TabCreator(contentPage.getExcelSheet());
        
        document.add(tc.createTabCampaign(rows, labels, new CampaignRow("merguez", 0, 0, 0, 0, new Percentage(0), new Percentage(0)),colsToPrint,true));
        
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
		cb.moveText(xPositionCustom, 320);
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
		cb.moveText(xPosition, 490);
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
		cb.moveText(xPositionDates, 460);
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
		cb.moveText(xPositionCustom, 430);
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
    
    public Image getImagePie(JFreeChart chart, PdfWriter writer) throws BadElementException, IOException {
    	int width = 400;
        int height = 400;
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
		
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
        image.scalePercent(70);
        image.setAlignment(Image.MIDDLE);
        
        return image;
    }
    
    public Image getImageBar(JFreeChart chart, PdfWriter writer) throws BadElementException, IOException {
    	int width = 600;
        int height = 450;
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
		
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
        image.scalePercent(70);
        image.setAlignment(Image.MIDDLE);
        
        return image;
    }
    
}
