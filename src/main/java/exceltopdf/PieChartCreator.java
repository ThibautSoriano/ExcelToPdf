package main.java.exceltopdf;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import main.java.excelreader.entities.CampaignRow;

public class PieChartCreator {
 
    public JFreeChart getChart(List<CampaignRow> campaignRows, int colIndex, String title, boolean top5) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        int nbElements = 0;
        
        if (top5) {
        	for (int i = 0; nbElements < 5; i++) {
	        	if (campaignRows.get(i).isRelevant()) {
	        		dataset.setValue(campaignRows.get(i).getFirstColumnData(), campaignRows.get(i).toListFloat().get(colIndex));
	        		nbElements++;
	        	}
	        	float others = 0;
	        	for (int j = i; j < campaignRows.size(); j++) {
	        		others += campaignRows.get(i).toListFloat().get(colIndex);
	        	}
	        	dataset.setValue("Others", others);
	        	
	        }
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
}