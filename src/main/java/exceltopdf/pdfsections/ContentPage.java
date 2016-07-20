package main.java.exceltopdf.pdfsections;

import main.java.datasdownloading.entities.Campaign;
import main.java.excelreader.ExcelReader;
import main.java.excelreader.entities.ExcelSheet;

public class ContentPage extends Section {	
	private ExcelSheet excelSheet;
	
	private Campaign campaign;
	
	private ExcelReader excelReader = new ExcelReader() {
            
            @Override
            public void fillExcelSheet(String filePath) {
                // TODO Auto-generated method stub
                
            }
        };
	
	private boolean isTechnicalCampaign;
	
	private boolean impressions;
	
	private boolean reach;
	
	private boolean uniqueCookies;
	
	private boolean frequency;
	
	private boolean clicks;
	
	private boolean clickingUsers;
	
	private boolean clickThroughRate;
	
	private boolean uniqueCTR;

	public ContentPage(boolean impressions, boolean frequency, boolean clicks,
			boolean clickingUsers, boolean clickThroughRate, boolean uniqueCTR) {
		this.impressions = impressions;
		this.frequency = frequency;
		this.clicks = clicks;
		this.clickingUsers = clickingUsers;
		this.clickThroughRate = clickThroughRate;
		this.uniqueCTR = uniqueCTR;
	}

	public ExcelSheet getExcelSheet() {
		return excelSheet;
	}

	public void setExcelSheet(ExcelSheet excelSheet) {
		this.excelSheet = excelSheet;
	}

	public boolean isImpressions() {
		return impressions;
	}

	public void setImpressions(boolean impressions) {
		this.impressions = impressions;
	}

	public boolean isUniqueCookies() {
		return uniqueCookies;
	}

	public void setUniqueCookies(boolean uniqueCookies) {
		this.uniqueCookies = uniqueCookies;
	}

	public boolean isFrequency() {
		return frequency;
	}

	public void setFrequency(boolean frequency) {
		this.frequency = frequency;
	}

	public boolean isClicks() {
		return clicks;
	}

	public void setClicks(boolean clicks) {
		this.clicks = clicks;
	}

	public boolean isClickingUsers() {
		return clickingUsers;
	}

	public void setClickingUsers(boolean clickingUsers) {
		this.clickingUsers = clickingUsers;
	}

	public boolean isClickThroughRate() {
		return clickThroughRate;
	}

	public void setClickThroughRate(boolean clickThroughRate) {
		this.clickThroughRate = clickThroughRate;
	}

	public boolean isUniqueCTR() {
		return uniqueCTR;
	}

	public void setUniqueCTR(boolean uniqueCTR) {
		this.uniqueCTR = uniqueCTR;
	}

	public ExcelReader getExcelReader() {
		return excelReader;
	}

	public void setExcelReader(ExcelReader excelReader) {
		this.excelReader = excelReader;
	}

	public boolean isReach() {
		return reach;
	}

	public void setReach(boolean reach) {
		this.reach = reach;
	}

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

	public boolean isTechnicalCampaign() {
		return isTechnicalCampaign;
	}

	public void setTechnicalCampaign(boolean isTechnicalCampaign) {
		this.isTechnicalCampaign = isTechnicalCampaign;
	}
}
