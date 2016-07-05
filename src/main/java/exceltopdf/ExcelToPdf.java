package main.java.exceltopdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;

import main.java.excelreader.ExcelReader;
import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.ExcelReaderTechnical;
import main.java.excelreader.entities.CampaignRow;
import main.java.excelreader.entities.ExcelSheet;
import main.java.utils.Utils;

public class ExcelToPdf {

    public static final String DEST = "meurguez.pdf";
    public static final String LOGO = "./src/main/resources/logo.png";
    public static final String TEMP_INSERT_PAGE = "tmp_insert_page.pdf";

    private ExcelSheet excelSheet;
    
    

    public static void main(String args[])
            throws IOException, DocumentException {
//         new
//         ExcelToPdf().createPdf("./src/main/resources/Pannontej_Medve_nyar_July,Rankings,2016.06.27-2016.07.10_1467200027.xls",
//         DEST, LOGO);
        ExcelToPdf  etdpf =new ExcelToPdf();
        etdpf.createPdf(
                "./src/main/resources/Pannontej_Medve_nyar_July,Rankings,2016.06.27-2016.07.10_1467200027.xls",
                DEST, LOGO);

    }

    public void createPdf(String src, String dest, String image)
            throws IOException, DocumentException {
        
        
        ExcelReader excelReader = null;

        if (src.contains("Rankings"))
            excelReader = new ExcelReaderRankings();
        else if (src.contains("Technical"))
            excelReader = new ExcelReaderTechnical();
        else {
            System.err.println("xls not recognized");
            return;
        }
        excelSheet = excelReader.readExcelSheet(src);
        
        

        Document document = new Document();
        
        FileOutputStream outputStream = new FileOutputStream(dest);

        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        
        document.open();
        

        document.add(new Paragraph(excelSheet.getCampaignName()));
        document.add(new Paragraph(
                excelSheet.getStartDate() + " to " + excelSheet.getEndDate()));
        // List<CampaignRow> campaignRows = excelSheet.getCampaignRows();
        //
        // document.add(new Paragraph().add(createFirstTable()));

        boolean [] colsToPrint = {
                true,true,true,true,true,true,true,false
        };
        
        TabCreator tc = new TabCreator(excelSheet);
        //document.add(tc.createTabCampaign(colsToPrint,true));

//        HeaderFooter insertPageSettings = new HeaderFooter();
//        createInsertPage();
//        
        
        document.close();
    }

   

    public void createInsertPage(HeaderFooter structure, String content) throws DocumentException, FileNotFoundException{
    	Document document = new Document();      
        FileOutputStream outputStream = new FileOutputStream(TEMP_INSERT_PAGE);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        
        writer.setPageEvent(structure);
        
        document.open();
        
       try {
    	   
    	   PdfContentByte cb = writer.getDirectContent();
    	   cb.saveState();
    	   cb.beginText();
    	   cb.moveText(700, 30);
    	   cb.showText("Zheng");
    	   cb.endText();
    	   cb.restoreState();
        document.add(new Paragraph("Készítette" + content));
    } catch (DocumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        
        
    }
    
    
    
}
