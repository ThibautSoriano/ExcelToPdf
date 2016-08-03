package main.java.datasdownloading.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.excelreader.entities.CampaignRowPeriod;

public class PeriodData {
	
	private List<CampaignRowPeriod> content = new ArrayList<>();
	
	private CampaignRowPeriod all;
	
	private List<String> columsLabels = new ArrayList<>();
	
	public PeriodData(List<CampaignRowPeriod> content, CampaignRowPeriod all) {
		columsLabels.add("Time period");
		columsLabels.add("Placement path");		
		columsLabels.add("Impressions");
		columsLabels.add("Unique cookies");
		columsLabels.add("Frequency");
		columsLabels.add("Clicks");
		columsLabels.add("Clicking users");
		columsLabels.add("Click Through Rate");
		columsLabels.add("Unique CTR");
		columsLabels.add("Reach");
		this.content = content;
		this.all = all;
		
	}

	public List<CampaignRowPeriod> getContent() {
		return content;
	}

	public void setContent(List<CampaignRowPeriod> content) {
		this.content = content;
	}

	public CampaignRowPeriod getAll() {
		return all;
	}

	public void setAll(CampaignRowPeriod all) {
		this.all = all;
	}

	public List<String> getColumsLabels() {
		return columsLabels;
	}

	public void setColumsLabels(List<String> columsLabels) {
		this.columsLabels = columsLabels;
	}

}
