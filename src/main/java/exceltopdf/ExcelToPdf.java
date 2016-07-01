package main.java.exceltopdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import main.java.excelreader.ExcelReader;
import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.ExcelReaderTechnical;
import main.java.excelreader.entities.ExcelSheet;

public class ExcelToPdf {

	public static final String DEST = "meurguez.pdf";
	public static final String LOGO = "logo.png";
	
	private ExcelSheet excelSheet;
	 
    public static void main(String args[]) throws IOException {
        new ExcelToPdf().createPdf("meurguez.xls", DEST, LOGO);
    }
 
    public void createPdf(String src, String dest, String image) throws IOException {
        FileOutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
 
        ExcelReader excelReader = null;
        
        if (src.contains("Rankings")) 
            excelReader = new ExcelReaderRankings();
        else if (src.contains("Technical")) 
            excelReader =  new ExcelReaderTechnical();
        else 
        {
            System.err.println("xls not recognized");
            return;
        }
        excelSheet = excelReader.readExcelSheet(src);
        
        document.add(new Paragraph(excelSheet.getCampaignName()));
        document.add(new Paragraph(excelSheet.getStartDate() + " to " + excelSheet.getEndDate()));
        Image logo = new Image(ImageDataFactory.create(image));
        Paragraph p = new Paragraph("Company logo\n\n").add(logo);
        document.add(p);
        
        document.close();
    }
}
