package main.java.datasdownloading.entities;

import com.itextpdf.text.pdf.PdfReader;

public class TocElement {

	private String chapter;
	
	private PdfReader docToConcat;

	public TocElement(String chapter, PdfReader docToConcat) {
		this.chapter = chapter;
		this.docToConcat = docToConcat;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public PdfReader getDocToConcat() {
		return docToConcat;
	}

	public void setDocToConcat(PdfReader docToConcat) {
		this.docToConcat = docToConcat;
	}
}
