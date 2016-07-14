package main.java.datasdownloading.entities;

import java.util.List;

import main.java.excelreader.entities.CampaignRow;

public class Campaign {
	
	private CampaignHeader campaignHeader;
	
	private List<CampaignRow> campaignContent;

	public Campaign(CampaignHeader campaignHeader, List<CampaignRow> campaignContent) {
		this.campaignHeader = campaignHeader;
		this.campaignContent = campaignContent;
	}
	

}
