package main.java.excelreader.entities;

public  class CampaignRow {
    
    protected String firstColumnData;

   

    
    protected int impressions;

    protected int uniqueCookies;

    protected int frequency;

    protected int clicks;

    protected int clickingUsers;

    /**
     * percentage : 0,01 for example
     */
    protected float clickThroughRate;

    /**
     * percentage
     */
    protected float uniqueCTR;

    
   
    public String getFirstColumnData() {
        return firstColumnData;
    }




    public void setFirstColumnData(String firstColumnData) {
        this.firstColumnData = firstColumnData;
    }




    public CampaignRow(String firstColumnData, int impressions, int uniqueCookies, int frequency, int clicks,
            int clickingUsers, float clickThroughRate, float uniqueCTR) {
    this.firstColumnData = firstColumnData;
    this.impressions = impressions;
    this.uniqueCookies = uniqueCookies;
    this.frequency = frequency;
    this.clicks = clicks;
    this.clickingUsers = clickingUsers;
    this.clickThroughRate = clickThroughRate;
    this.uniqueCTR = uniqueCTR;
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

    
    public String getLabelFirstColumn() {
        return firstColumnData;
    }

    public void setLabelFirstColumn(String labelFirstColumn) {
        this.firstColumnData = labelFirstColumn;
    }
    
    @Override
    public String toString() {
            String res = "";
            
            res += "\n";
            res += firstColumnData + "\t";
            res += impressions + "\t";
            res += uniqueCookies + "\t";
            res += frequency + "\t";
            res += clicks + "\t";
            res += clickingUsers + "\t";
            res += clickThroughRate + "\t";
            res += uniqueCTR;
            
            return res;
    }
}
