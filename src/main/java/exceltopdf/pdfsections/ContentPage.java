package main.java.exceltopdf.pdfsections;

import main.java.excelreader.entities.ExcelSheet;

public class ContentPage extends Section {	
	private ExcelSheet excelSheet;
	
	private boolean impressions;
	
	private boolean uniqueCookies;
	
	private boolean frequency;
	
	private boolean clicks;
	
	private boolean clickingUsers;
	
	private boolean clickThroughRate;
	
	private boolean uniqueCTR;

	public ContentPage(boolean impressions, boolean uniqueCookies, boolean frequency, boolean clicks,
			boolean clickingUsers, boolean clickThroughRate, boolean uniqueCTR) {
		super();
		this.impressions = impressions;
		this.uniqueCookies = uniqueCookies;
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
}
