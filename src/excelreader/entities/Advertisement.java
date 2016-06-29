package excelreader.entities;

public class Advertisement {

	private String placementName;
	
	private int impressions;
	
	private int uniqueCookies;
	
	private int frequency;
	
	private int clicks;
	
	private int clickingUsers;
	
	/**
	 * percentage
	 */
	private float clickThroughRate;
	
	/**
	 * percentage
	 */
	private float uniqueCTR;

	public String getPlacementName() {
		return placementName;
	}

	public void setPlacementName(String placementName) {
		this.placementName = placementName;
	}

	public int getImpressions() {
		return impressions;
	}

	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}

	public int getUniqueCookies() {
		return uniqueCookies;
	}

	public void setUniqueCookies(int uniqueCookies) {
		this.uniqueCookies = uniqueCookies;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getClickingUsers() {
		return clickingUsers;
	}

	public void setClickingUsers(int clickingUsers) {
		this.clickingUsers = clickingUsers;
	}

	public float getClickThroughRate() {
		return clickThroughRate;
	}

	public void setClickThroughRate(float clickThroughRate) {
		this.clickThroughRate = clickThroughRate;
	}

	public float getUniqueCTR() {
		return uniqueCTR;
	}

	public void setUniqueCTR(float uniqueCTR) {
		this.uniqueCTR = uniqueCTR;
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res += placementName + "\t";
		res += impressions + "\t";
		res += uniqueCookies + "\t";
		res += frequency + "\t";
		res += clicks + "\t";
		res += clickingUsers + "\t";
		res += clickThroughRate + "\t";
		res += uniqueCTR + "\t";
		
		return res;
	}
}
