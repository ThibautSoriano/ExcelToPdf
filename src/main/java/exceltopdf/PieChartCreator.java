
package main.java.exceltopdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import main.java.excelreader.ExcelReader;
import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.entities.ExcelSheet;
 
		/**
		 * Ugly demo of how to use jFreeChart
		 * with iText. The output will have
		 * the stair-case effect.
		 * 
		 * @author Jee Vang
		 *
		 */
		public class PieChartCreator {
		 
		    public JFreeChart getChart() {
		        DefaultPieDataset dataset = new DefaultPieDataset();
		        ExcelReaderRankings excelReaderRankings = new ExcelReaderRankings();
		        ExcelSheet excelSheet = excelReaderRankings.readExcelSheet("zhengqin.xls");
		        for (int i = 0; i < 4; i++) {
		        	dataset.setValue(excelSheet.getCampaignRows().get(i).getFirstColumnData(), excelSheet.getCampaignRows().get(i).getImpressions());
		        }
		         
		        //use the ChartFactory to create a pie chart
		        JFreeChart chart = 
		            ChartFactory.createPieChart3D(
		                    "Impressions per picture", dataset, true, true, false);


		        
		        return chart;
		    }
		     
		    /**
		     * Creates PDf file.
		     * @param outputStream {@link OutputStream}.
		     * @throws DocumentException
		     * @throws IOException
		     */
		    public void create(OutputStream outputStream) throws DocumentException, IOException {
		        Document document = null;
		        PdfWriter writer = null;
		         
		        try {
		            //instantiate document and writer
		            document = new Document();
		            writer = PdfWriter.getInstance(document, outputStream);
		             
		            //open document
		            document.open();
		             
		            //add image
		            int width = 400;
		            int height = 300;
		            JFreeChart chart = getChart();
		            BufferedImage bufferedImage = chart.createBufferedImage(width, height);
		            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
		            document.add(image);
		             
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
		     
		    /**
		     * Main method.
		     * @param args No args required.
		     * @throws FileNotFoundException
		     * @throws DocumentException
		     * @throws IOException
		     */
		    public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
		        (new PieChartCreator()).create(
		                new FileOutputStream(
		                        new File("fdpNIORJU.pdf")));
		    }
}