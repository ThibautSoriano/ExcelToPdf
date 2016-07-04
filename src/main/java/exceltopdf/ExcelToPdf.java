package main.java.exceltopdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
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

    private ExcelSheet excelSheet;

    public static void main(String args[])
            throws IOException, DocumentException {
         new
         ExcelToPdf().createPdf("./src/main/resources/Pannontej_Medve_nyar_July,Rankings,2016.06.27-2016.07.10_1467200027.xls",
         DEST, LOGO);
//        new ExcelToPdf().createPdf(
//                "./src/main/resources/Pannontej_Medve_nyar_July%2cTechnical%2c2016.06.27-2016.07.10_1467289337.xls",
//                DEST, LOGO);

    }

    public void createPdf(String src, String dest, String image)
            throws IOException, DocumentException {

        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        
        
       
        
        
        document.open();

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

        document.add(new Paragraph(excelSheet.getCampaignName()));
        document.add(new Paragraph(
                excelSheet.getStartDate() + " to " + excelSheet.getEndDate()));
        Image logo = Image.getInstance(String.format(LOGO, (Object) null));

        document.add(logo);
        // List<CampaignRow> campaignRows = excelSheet.getCampaignRows();
        //
        // document.add(new Paragraph().add(createFirstTable()));

        boolean [] colsToPrint = {
                true,true,true,true,true,true,true,true
        };
        
        TabCreator tc = new TabCreator(excelSheet);
        document.add(tc.createTabCampaign(colsToPrint,true));

    
        
        
        document.close();
    }

   

    
    
    
    
}
