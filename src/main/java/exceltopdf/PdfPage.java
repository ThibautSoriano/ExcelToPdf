package main.java.exceltopdf;

import java.io.IOException;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfPage extends PdfPageEventHelper {

    public void onStartPage(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("www.gemius.hu"), 80, 800, 0);
        try {
            Image img = Image.getInstance("C:\\Users\\user\\Documents\\Polytech\\SI4\\Hongrie\\ExcelToPdf\\src\\main\\resources\\logo.png");
            img.scaleToFit(150, 150);  
            img.setAbsolutePosition(420, 773);
            img.setAlignment(Element.ALIGN_CENTER);
            writer.getDirectContent().addImage(img);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
        
        PdfContentByte cb = writer.getDirectContent();

		cb.setLineWidth(2.0f); // Make a bit thicker than 1.0 default
		//cb.setGrayStroke(1.0f); // 1 = black, 0 = white
		float x = 72f;
		float y = PageSize.A4.getHeight() - 72;
		cb.moveTo(x,         y);
		cb.lineTo(x + 72f*6, y);
		cb.stroke();
        
//        try {
//        	Chunk linebreak = new Chunk(new LineSeparator());
//        	linebreak.setLineHeight(2.0f);
//			document.add(linebreak);
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

    public void onEndPage(PdfWriter writer, Document document) {
    	PdfContentByte cb = writer.getDirectContent();

		cb.setLineWidth(2.0f); // Make a bit thicker than 1.0 default
		//cb.setGrayStroke(1.0f); // 1 = black, 0 = white
		float x = 72f;
		float y = 72f;
		cb.moveTo(x,         y);
		cb.lineTo(x + 72f*6, y);
		cb.stroke();
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("your company name here"), 110, 30, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.valueOf(document.getPageNumber())), 550, 30, 0);
    }
    
    

}