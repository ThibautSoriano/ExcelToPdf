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

public class HeaderFooter extends PdfPageEventHelper {
	
	private String lineInHeader;
	
	private String logoInHeader;
	
	private String lineInFooter;
	
	private boolean header;
	
	private boolean separatorInHeader;
	
	private boolean footer;
	
	private boolean separatorInFooter;
	
	private boolean pagesCount;
	
	public HeaderFooter() {
		
	}

    public HeaderFooter(boolean header, boolean separatorInHeader, boolean footer, boolean separatorInFooter,
			boolean pagesCount) {
		
		this.header = header;
		this.separatorInHeader = separatorInHeader;
		this.footer = footer;
		this.separatorInFooter = separatorInFooter;
		this.pagesCount = pagesCount;
	}


	public void onStartPage(PdfWriter writer, Document document) {
		if (header) {
			Phrase topLeftCorner = new Phrase();
			
			if (lineInHeader.equals("")) {
				topLeftCorner.add("www.gemius.hu");
			}
			else {
				topLeftCorner.add(lineInHeader);
			}
			
	        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, topLeftCorner, 80, 800, 0);
	        try {
	        	Image img = null;
	        	if (logoInHeader.equals("")) {
	        		img = Image.getInstance("./src/main/resources/logo.png");
	        	}
	        	else {
	        		img = Image.getInstance(logoInHeader);
	        	}
	            img.scaleToFit(150, 150);  
	            img.setAbsolutePosition(420, 773);
	            img.setAlignment(Element.ALIGN_CENTER);
	            writer.getDirectContent().addImage(img);
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			}
	        
	        if (separatorInHeader) {
	        	// draws a line below the header
	        	PdfContentByte cb = writer.getDirectContent();

				cb.setLineWidth(2.0f);
				float x = 72f;
				float y = PageSize.A4.getHeight() - 72;
				cb.moveTo(x,         y);
				cb.lineTo(x + 72f*6, y);
				cb.stroke();
	        }
		}
    }

    public void onEndPage(PdfWriter writer, Document document) {
    	
    	if (footer) {
    		if (!lineInFooter.equals("")) {
        		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("your company name here"), 110, 30, 0);
    		}
    		
    		if (pagesCount) {
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.valueOf(document.getPageNumber())), 550, 30, 0);
    		}
            
            if (separatorInFooter) {
        		PdfContentByte cb = writer.getDirectContent();

        		cb.setLineWidth(2.0f);
        		float x = 72f;
        		float y = 72f;
        		cb.moveTo(x,         y);
        		cb.lineTo(x + 72f*6, y);
        		cb.stroke();
        	}
    	}
    }

	public void setLineInHeader(String lineInHeader) {
		this.lineInHeader = lineInHeader;
	}

	public void setLogoInHeader(String logoInHeader) {
		this.logoInHeader = logoInHeader;
	}

	public void setLineInFooter(String lineInFooter) {
		this.lineInFooter = lineInFooter;
	}
    
    

}