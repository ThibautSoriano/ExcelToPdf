package main.java.exceltopdf.pdfsections;

public class TitlePage extends Section {
	
	private String campaignName ="";
	
	private String startDate = "";
	
	private String endDate= "";
	
	private String belowTitle = "online kamp�ny elemz�se";
	
	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBelowTitle() {
		return belowTitle;
	}

	public void setBelowTitle(String belowTitle) {
		this.belowTitle = belowTitle;
	}
}
