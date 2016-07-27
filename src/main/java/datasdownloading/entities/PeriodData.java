package main.java.datasdownloading.entities;

import java.util.ArrayList;
import java.util.List;

import main.java.excelreader.entities.CampaignRowPeriod;

public class PeriodData {
	
	private List<CampaignRowPeriod> content = new ArrayList<>();
	
	private CampaignRowPeriod all;

	public PeriodData() {
	}

	public PeriodData(List<CampaignRowPeriod> content, CampaignRowPeriod all) {
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

}
