package main.java.datasdownloading.entities;

import java.util.List;

import main.java.excelreader.entities.CampaignRow;

public class Campaign {

	private CampaignHeader campaignHeader;
	
	private List<CampaignRow> campaignContent;
	
	private List<String> columsLabels;

	public Campaign(CampaignHeader campaignHeader, List<CampaignRow> campaignContent) {
		this.campaignHeader = campaignHeader;
		this.campaignContent = campaignContent;
	}
	
	public CampaignHeader getCampaignHeader() {
		return campaignHeader;
	}

	public void setCampaignHeader(CampaignHeader campaignHeader) {
		this.campaignHeader = campaignHeader;
	}

	public List<CampaignRow> getCampaignContent() {
		return campaignContent;
	}

	public void setCampaignContent(List<CampaignRow> campaignContent) {
		this.campaignContent = campaignContent;
	}

    public List<String> getColumsLabels() {
        return columsLabels;
    }

    public void setColumsLabels(List<String> columsLabels) {
        this.columsLabels = columsLabels;
    }
	

}
