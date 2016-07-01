package main.java.excelreader.entities;

import java.util.ArrayList;
import java.util.List;

public class ExcelSheet {
	
	private String campaignName;
	
	private String startDate;
	
	private String endDate;
	
	private List<Advertisement> advertisements = new ArrayList<>();
	
	private Advertisement all = new Advertisement();

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

	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	public Advertisement getAll() {
		return all;
	}

	public void setAll(Advertisement all) {
		this.all = all;
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res += "Campaign name : " + campaignName + "\n";
		res += "Start date : " + startDate + "\n";
		res += "End date : " + endDate + "\n";
		res += "Advertisements : " + advertisements + "\n";
		res += "All ads sump up : " + all + "\n";
		
		return res;
	}

}
