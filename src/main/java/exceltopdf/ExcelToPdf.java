package main.java.exceltopdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import main.java.excelreader.ExcelReader;
import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.ExcelReaderTechnical;
import main.java.excelreader.entities.CampaignRow;
import main.java.excelreader.entities.ExcelSheet;

public class ExcelToPdf {

	public static final String DEST = "meurguez.pdf";
	public static final String LOGO = "./src/main/resources/logo.png";
	
	private ExcelSheet excelSheet;
	 
    public static void main(String args[]) throws IOException {
        //new ExcelToPdf().createPdf("./src/main/resources/Pannontej_Medve_nyar_July,Rankings,2016.06.27-2016.07.10_1467200027.xls", DEST, LOGO);
        new ExcelToPdf().createPdf("./src/main/resources/Pannontej_Medve_nyar_July%2cTechnical%2c2016.06.27-2016.07.10_1467289337.xls", DEST, LOGO);
        
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
        
        List<CampaignRow> campaignRows = excelSheet.getCampaignRows();
        
        
        for (CampaignRow campaignRow : campaignRows) {
            System.out.println(campaignRow.getFirstColumnData());
        }
        
        document.close();
    }
    
    
    public static PdfPTable createFirstTable() {
        // a table with three columns
        PdfPTable table = new PdfPTable(3);
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Cell with colspan 3"));
        cell.setColspan(3);
        table.addCell(cell);
        // now we add a cell with rowspan 2
        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
        cell.setRowspan(2);
        table.addCell(cell);
        // we add the four remaining cells with addCell()
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        return table;
    }
    
}
