package main.java.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class MeurguezSI5 {

	public static void main(String[] args) throws DocumentException, IOException {
		Document document = new Document();
		FileOutputStream outputStream = new FileOutputStream("BorbalAnita.pdf");
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		
		
		document.open();
		
		document.add(new Paragraph("GuezMer"));
                
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
