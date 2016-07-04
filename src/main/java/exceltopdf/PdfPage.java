package main.java.exceltopdf;

import com.itextpdf.text.Image;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfPage extends PdfPageEventHelper {

    public void onStartPage(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Left"), 30, 800, 0);
//        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 800, 0);
        try {
            Image img = Image.getInstance("C:\\Users\\user\\Documents\\Polytech\\SI4\\Hongrie\\ExcelToPdf\\src\\main\\resources\\fitlane.png");
            img.scaleToFit(100,100);  
            img.setAbsolutePosition(550, 800);
            img.setAlignment(Element.ALIGN_CENTER);
            writer.getDirectContent().addImage(img);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
    }

    public void onEndPage(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("http://www.xxxx-your_example.com/"), 110, 30, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("page " + document.getPageNumber()), 550, 30, 0);
    }

}