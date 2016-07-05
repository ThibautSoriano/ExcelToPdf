package main.java.exceltopdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.entities.ExcelSheet;
 
		public class BarChartCreator {
			
			private int nbElements;
		 
		    public JFreeChart getChart() {
		        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		        ExcelReaderRankings excelReader = new ExcelReaderRankings();
		        ExcelSheet excelSheet = excelReader.readExcelSheet("zhengqinRankings.xls");
		        // TODO : maximum?
		        int firstValue = excelSheet.getCampaignRows().get(0).getImpressions();
		        if (excelSheet.getCampaignRows().size() > 1) {
		        	
		        }
		        for (int i = 0; i < 5; i++) {
		        	dataset.addValue(excelSheet.getCampaignRows().get(i).getImpressions(), excelSheet.getCampaignRows().get(i).getFirstColumnData(), "");
		        	nbElements++;
		        }
		         
		        JFreeChart chart = 
		            ChartFactory.createBarChart3D("",             
		                    "Ads",             
		                    "Impressions",             
		                    dataset,            
		                    PlotOrientation.HORIZONTAL,             
		                    true, true, false);


		        
		        return chart;
		    }
		    
		    public void create(OutputStream outputStream) throws DocumentException, IOException {
		        Document document = null;
		        PdfWriter writer = null;
		         
		        try {
		            document = new Document(PageSize.A4);
		            writer = PdfWriter.getInstance(document, outputStream);
		            writer.setPageEvent(new HeaderFooter());
		            
		            document.setMargins(85, 85, 85, 113);
		            
		            document.open();
		            
		            JFreeChart chart = getChart();
		            int width = 400;
		            int height = (nbElements * 75) + 50;
		            BufferedImage bufferedImage = chart.createBufferedImage(width, height);
		            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
		            document.add(image);
		            
		            document.add(new Paragraph("\n\n\n"));
		            boolean [] colsToPrint = {
		                    true,true,true,true,true,true,true,false
		            };
		            
		            ExcelReaderRankings excelReader = new ExcelReaderRankings();
			        ExcelSheet excelSheet = excelReader.readExcelSheet("zhengqinRankings.xls");
		            TabCreator tc = new TabCreator(excelSheet);
		            document.add(tc.createTabCampaign(colsToPrint,true));
		            
		            //release resources
		            document.close();
		            document = null;
		             
		            writer.close();
		            writer = null;
		        } catch(DocumentException de) {
		            throw de;
		        } catch (IOException ioe) {
		            throw ioe;
		        } finally {
		            //release resources
		            if(null != document) {
		                try { document.close(); }
		                catch(Exception ex) { }
		            }
		             
		            if(null != writer) {
		                try { writer.close(); }
		                catch(Exception ex) { }
		            }
		        }
		    }
		    
		    public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
		        (new BarChartCreator()).create(
		                new FileOutputStream(
		                        new File("zhengqin.pdf")));
		    }
}