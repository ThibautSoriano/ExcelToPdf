package main.java.excelreader.entities;

import java.util.Date;

import main.java.utils.Percentage;

public class CampaignRowPeriod extends CampaignRow {
	
	private Date startPeriod;
	
	public CampaignRowPeriod() {
		super();
	}

	public CampaignRowPeriod(String firstColumnData, int impressions, float frequency, int clicks, int clickingUsers,
			Percentage clickThroughRate, Percentage uniqueCTR, Date periodStart) {
		super(firstColumnData, impressions, frequency, clicks, clickingUsers, clickThroughRate, uniqueCTR);
		this.startPeriod = periodStart;
	}


	public Date getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}

}
