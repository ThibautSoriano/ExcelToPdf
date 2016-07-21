package main.java.exceltopdf.pdfsections;

import java.util.HashMap;
import java.util.Map;

public class SummaryPage extends Section {
	
	private Map<String, String> summary = new HashMap<>();

	public Map<String, String> getSummary() {
		return summary;
	}

	public void setSummary(Map<String, String> summary) {
		this.summary = summary;
	}

}
