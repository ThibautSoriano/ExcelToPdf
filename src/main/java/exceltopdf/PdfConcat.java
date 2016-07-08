package main.java.exceltopdf;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
	          PdfReader ReadInputPDF = null;
	          int number_of_pages;
	          for (int i = 0; i < files.length; i++) {
	                  ReadInputPDF = new PdfReader(files[i]);
	                  number_of_pages = ReadInputPDF.getNumberOfPages();
	                  for (int page = 0; page < number_of_pages; ) {
	                          copy.addPage(copy.getImportedPage(ReadInputPDF, ++page));
	                        }
	                  ReadInputPDF.close();
	                  
	                  
	          }
	          PDFCombineUsingJava.close();
	          copy.close();
	          
	          
	        }
	        catch (Exception i)
	        {
	            System.out.println(i);
	        }
		
		// delete tmp files
		for (int i = 0; i < filesToConcat.length; i++) {
			File f = new File(filesToConcat[i]);
			String fileName = f.getName();
			System.out.println("trying to delete " + f.getAbsolutePath());
//			if (!file.delete()) {
//			    System.err.println("Impossible to delete temporary file");
//			}
//		    File f = new File(fileName);

		    // Make sure the file or directory exists and isn't write protected
		    if (!f.exists())
		      throw new IllegalArgumentException(
		          "Delete: no such file or directory: " + fileName);

		    if (!f.canWrite())
		      throw new IllegalArgumentException("Delete: write protected: "
		          + fileName);

		    // If it is a directory, make sure it is empty
		    if (f.isDirectory()) {
		      String[] files = f.list();
		      if (files.length > 0)
		        throw new IllegalArgumentException(
		            "Delete: directory not empty: " + fileName);
		    }

		    // Attempt to delete it
		    boolean success = f.delete();

		    if (!success)
		      throw new IllegalArgumentException("Delete: deletion failed");
		  }
		
		
		
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(dest);
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
		
		
		}
		
	

}
