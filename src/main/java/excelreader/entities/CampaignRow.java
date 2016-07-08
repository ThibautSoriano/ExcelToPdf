package main.java.excelreader.entities;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import main.java.utils.Percentage;

public  class CampaignRow {
    
    public static final int MAX_COLUMNS = 8;
    
    protected String firstColumnData;

    protected int impressions;

    protected int uniqueCookies;

    protected float frequency;

    protected int clicks;

    protected int clickingUsers;

    /**
     * percentage : 0,01 for example
     */
    protected Percentage clickThroughRate;

    /**
     * percentage
     */
    protected Percentage uniqueCTR;

    
   
    public String getFirstColumnData() {
        return firstColumnData;
    }




    public void setFirstColumnData(String firstColumnData) {
        this.firstColumnData = firstColumnData;
    }




    public CampaignRow(String firstColumnData, int impressions, int uniqueCookies, float frequency, int clicks,
            int clickingUsers, Percentage clickThroughRate, Percentage uniqueCTR) {
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

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
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

    public Percentage getClickThroughRate() {
        return clickThroughRate;
    }

    public void setClickThroughRate(Percentage clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
    }

    public Percentage getUniqueCTR() {
        return uniqueCTR;
    }

    public void setUniqueCTR(Percentage uniqueCTR) {
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
    
    public List<String> toList() {
        List<String> l = new ArrayList<String>();
        l.add(splitFirstColumnData());
        l.add(getSpacesBetweenThousands(String.valueOf(impressions)));
        l.add(getSpacesBetweenThousands(String.valueOf(uniqueCookies)));
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        
        
        l.add(String.valueOf(df.format(frequency)));
        l.add(getSpacesBetweenThousands(String.valueOf(clicks)));
        l.add(getSpacesBetweenThousands(String.valueOf(clickingUsers)));
        l.add(clickThroughRate.toString());
        l.add(uniqueCTR.toString());
        return l;
    }
    
    public boolean isEmpty(){
        return  impressions == 0 && uniqueCookies == 0 && frequency == 0 && clicks == 0 && clickingUsers == 0
                && clickThroughRate.getValue() == 0 && uniqueCTR.getValue() == 0;
    }
    
    private String getSpacesBetweenThousands(String numberInString){
        
        
        StringBuilder sb = new StringBuilder(numberInString);
        for (int i = sb.length() - 3; i > 0; i -= 3)
            sb.insert(i, ' ');
       return sb.toString();
       
    }
    
    private String splitFirstColumnData() {
        return firstColumnData.replace("/","/\n");
    }
    
}
