package main.java.exceltopdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.entities.CampaignRow;
import main.java.excelreader.entities.ExcelSheet;
 
		public class BarChartCreator {
		 
		    public JFreeChart getChart(List<CampaignRow> campaignRows, int colIndex, String abscissa, String ordinate) {
		        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		        
		        for (int i = 0; i < 5; i++) {
		        	dataset.addValue(campaignRows.get(i).toListFloat().get(colIndex), campaignRows.get(i).getFirstColumnData(), "");
		        }
		         
		        JFreeChart chart = ChartFactory.createBarChart3D("", ordinate, abscissa, dataset, PlotOrientation.HORIZONTAL,             
		                    true, true, false);
		        
		        return chart;
		    }
		    
		    public void create(OutputStream outputStream) throws DocumentException, IOException {
		        Document document = null;
		        PdfWriter writer = null;
		         
		        try {
		            document = new Document(PageSize.A4);
		            writer = PdfWriter.getInstance(document, outputStream);
//		            HeaderFooter hf = new HeaderFooter(true, true, true, true, 1);
//		            hf.setLineInHeader("dias");
//		            hf.setLogoInHeader("C:/Users/user/Documents/Polytech/SI4/Hongrie/ExcelToPdf/src/main/resources/fleury-michon.png");
//		            writer.setPageEvent(hf);
		            ExcelReaderRankings excelReader = new ExcelReaderRankings();
			        ExcelSheet excelSheet = excelReader.readExcelSheet("zhengqinRankings.xls");
//		            document.setMargins(85, 85, 85, 113);
//		            document.open();
//		            JFreeChart chart = getChart(excelSheet.getCampaignRows());
//		            int width = 600;
//		            int height = (nbElements * 80) + 50;
//		            BufferedImage bufferedImage = chart.createBufferedImage(width, height);
//
//		            					
//		            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
//		            image.scalePercent(70);
//		            image.setAlignment(Image.MIDDLE);
//		            document.add(image);
		            
		            document.add(new Paragraph("\n\n\n"));
		            boolean [] colsToPrint = {
		                    true,true,true,true,true,true,true,false
		            };
		            
		            
		            TabCreator tc = new TabCreator(excelSheet);
		            document.add(tc.createTabCampaign(excelSheet.getCampaignRows(),excelSheet.getColumsLabels(),excelSheet.getAll(),colsToPrint,true));
		            
		            //release resources
		            document.close();
		            document = null;
		             
		            writer.close();
		            writer = null;
		        } catch(DocumentException de) {
		            throw de;
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