package main.java.exceltopdf;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

public class PdfConcat {
	
	public static void concat(String[] filesToConcat, String dest) {
		
		try {
			String[] files = filesToConcat;
	          Document PDFCombineUsingJava = new Document();
	          PdfCopy copy = new PdfCopy(PDFCombineUsingJava, new FileOutputStream(dest));
	          PDFCombineUsingJava.open();
	          PdfReader ReadInputPDF;
	          int number_of_pages;
	          for (int i = 0; i < files.length; i++) {
	                  ReadInputPDF = new PdfReader(files[i]);
	                  number_of_pages = ReadInputPDF.getNumberOfPages();
	                  for (int page = 0; page < number_of_pages; ) {
	                          copy.addPage(copy.getImportedPage(ReadInputPDF, ++page));
	                        }
	          }
	          PDFCombineUsingJava.close();
	        }
	        catch (Exception i)
	        {
	            System.out.println(i);
	        }
	}

}
