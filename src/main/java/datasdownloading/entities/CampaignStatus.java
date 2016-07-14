package main.java.datasdownloading.entities;

public enum CampaignStatus {
	CURRENT("current"), FINISHED("finished");
	
	private String value;
	
	private CampaignStatus(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		
		return value;
	}
}
