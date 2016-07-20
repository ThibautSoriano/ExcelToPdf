package main.java.exceltopdf;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.log.SysoCounter;

import main.java.excelreader.entities.CampaignRow;

public class PieChartCreator {
 
    public JFreeChart getChart(List<CampaignRow> campaignRows, int colIndex, String title, boolean isRate, int numeratorIndex, int denominatorIndex) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        int nbElements = 0;
    
    	for (int i = 0; nbElements < 4; i++) {
    	    
        	if (campaignRows.get(i).isRelevant()) {
        	    System.out.println("je teste d'ajouter : " + campaignRows.get(i).getFirstColumnData());
        		dataset.setValue(campaignRows.get(i).getFirstColumnData(), campaignRows.get(i).toListFloat().get(colIndex));
        		nbElements++;
        	}
        	
        	float others = 0;
        	
        	if (isRate) {
        		others = getPercentage(campaignRows, i, numeratorIndex, denominatorIndex);
        	} else {
	        	for (int j = i; j < campaignRows.size(); j++) {
	        		others += campaignRows.get(i).toListFloat().get(colIndex);
	        	}
        	}
        	dataset.setValue("Others", others);
        	
        	
        }
//        else {
//        	// chart for the others
//        	for (int i = 0; i < campaignRows.size(); i++) {
//        		
//        		if (nbElements < 5 && campaignRows.get(i).isRelevant()) {
//        			nbElements++;
//        		} else if (campaignRows.get(i).isRelevant()) {
//	        		dataset.setValue(campaignRows.get(i).getFirstColumnData(), campaignRows.get(i).toListFloat().get(colIndex));
//	        	}
//	        }
//        }
        
        
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelGenerator(null);
        
        return chart;
    }

	private float getPercentage(List<CampaignRow> campaignRows, int startIndex, int numeratorIndex, int denominatorIndex) {
		float numerator = 0;
		float denominator = 0;
		float result = 0;
		
		for (int i = startIndex; i < campaignRows.size(); i++) {
			numerator += campaignRows.get(i).toListFloat().get(numeratorIndex);
			denominator += campaignRows.get(i).toListFloat().get(denominatorIndex);
		}
		
		if (denominator > 0) {
			result = numerator / denominator;
		}
		
		return result;
	}
}