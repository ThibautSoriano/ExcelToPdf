package main.java.datasdownloading;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import main.java.datasdownloading.entities.Campaign;
import main.java.datasdownloading.entities.CampaignHeader;

public class HttpDownload {
    
    public static final int BUDAPEST_ID = 44;

    private String userName; // gpapp

    private String password; // Zup38fer

    private String sessionId;

    private XmlReader xmlReader;

    private HttpClient client;

    public HttpDownload(String userName, String password) throws Exception {
        client = HttpClientBuilder.create().build();

//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(900).build();
//        client = HttpClientBuilder.create()
//                .setDefaultRequestConfig(requestConfig).build();

        this.userName = userName;
        this.password = password;

        HttpMessage m = login(this.userName, this.password);

        if (!m.isOk()) {

            throw new LoginException(m.getErrorMessage());
        }
    }

    public HttpDownload() throws Exception {
        this("zburi_owner", "ad12dac");
    }

   

    private HttpMessage sendGet(String url) {

        HttpGet request = null;
        try {
         request = new HttpGet(url);
        }
        catch (IllegalArgumentException e) {
            return new HttpMessage(false, "Only aphanumerical characters allowed", "");
        }
        
        try {
            
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);

            
            return new HttpMessage(true, "OK", content);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return new HttpMessage(false,
                    "Connection with the server failed.\nPlease check your internet connection",
                    "");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return new HttpMessage(false, e.getMessage(), "");
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpMessage(false, e.getMessage(), "");
        }
        
    }

    /**
     * Tries to log in with the distant host gdeapi.gemius.com, if success fills
     * sessionId
     * 
     * @param loginName
     * @param password
     * @return a message containing infos about the success of the login
     */
    private HttpMessage login(String loginName, String password) {

        String url = "https://gdeapi.gemius.com/OpenSession.php?ignoreEmptyParams=Y&login="
                + loginName + "&passwd=" + password;

        HttpMessage m = sendGet(url);

        if (m.isOk()) {

            HttpMessage m2 = XmlReader.getSessionID(m.getContent());

            if (m2.isOk())
                sessionId = m2.getContent();
            else
                return m2;

        }

        return m;
    }

    public List<CampaignHeader> getCampaignHeaders() {

        if (xmlReader == null) {
            String url = "https://gdeapi.gemius.com/GetCampaignsList.php?ignoreEmptyParams=Y&sessionID="
                    + sessionId;
            HttpMessage m = sendGet(url);
            
            System.out.println(sessionId);
            if (m.isOk())
                xmlReader = new XmlReader(m.getContent());
            else
                return new ArrayList<CampaignHeader>();
        }
           
       return xmlReader.getAllHeaders();


    }

    private HttpMessage getXmlCampaignDatas(String campaignID) {
        String url = "https://gdeapi.gemius.com/GetBasicStats.php?ignoreEmptyParams=Y&sessionID="
                + sessionId
                + "&dimensionIDs=1%2C20&indicatorIDs=4%2C28%2C16%2C2%2C30%2C120%2C99&campaignIDs="
                + campaignID + "&timeDivision=General";

        HttpMessage m = sendGet(url);

        if (m.isOk())
            return new HttpMessage(true, "", m.getContent());
        return m;
    }

    private HttpMessage getXmlPlacementList(String campaignID) {
        String url = "https://gdeapi.gemius.com/GetPlacementsList.php?ignoreEmptyParams=Y&sessionID="
                + sessionId + "&campaignID=" + campaignID + "&showPaths=Y";

        HttpMessage m = sendGet(url);

        if (m.isOk())
            return new HttpMessage(true, "", m.getContent());
        return m;
    }

    public Campaign getCampaignRankingsById(String campaignId) {

        if (xmlReader == null) {
            String url = "https://gdeapi.gemius.com/GetCampaignsList.php?ignoreEmptyParams=Y&sessionID="
                    + sessionId;

            HttpMessage campaignList = sendGet(url);
            if (campaignList.isOk())
                xmlReader = new XmlReader(campaignList.getContent());
            else
                return null;
        }      
        HttpMessage campaignData = getXmlCampaignDatas(campaignId);
        HttpMessage placementList = getXmlPlacementList(campaignId);
            
        if (campaignData.isOk() && placementList.isOk()) {

            return xmlReader.getCampaign(campaignId, campaignData.getContent(),
                    placementList.getContent());

        }
        return null;
    }

    public Campaign getCampaignTechnicalById(String campaignId) {

        if (xmlReader == null) {
            String url = "https://gdeapi.gemius.com/GetTechStats.php?ignoreEmptyParams=Y&sessionID="
                    + sessionId;

            HttpMessage campaignList = sendGet(url);
            if (campaignList.isOk())
                xmlReader = new XmlReader(campaignList.getContent());
            else
                return null;
        }

       
        HttpMessage campaignData = sendGet(
                "https://gdeapi.gemius.com/GetTechStats.php?"
                + "ignoreEmptyParams=Y&"
                + "sessionID="+sessionId
                + "&dimensionIDs=1&indicatorIDs=4%2C28%2C16%2C2%2C30%2C120%2C99&techDimension=Region&"
                + "campaignIDs="+campaignId);
        
        HttpMessage all = sendGet( "https://gdeapi.gemius.com/GetTechStats.php?ignoreEmptyParams=Y&"
                + "sessionID="+sessionId
                + "&dimensionIDs=1&indicatorIDs=4%2C28%2C16%2C2%2C30%2C120%2C99&"
                + "campaignIDs="+campaignId
                + "&techDimension=Country");
        
        
        
        HttpMessage mapIdToCounty = sendGet("https://gdeapi.gemius.com/GetRegionsList.php?ignoreEmptyParams=Y&"
                + "sessionID="+sessionId
                + "&countryID="+BUDAPEST_ID);
        

        if (campaignData.isOk() && campaignData.isOk() && mapIdToCounty.isOk()) {
            return xmlReader.getCampaignTechnical(campaignId, campaignData.getContent(),
                    all.getContent(),mapIdToCounty.getContent(),BUDAPEST_ID);

        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        HttpDownload a= new HttpDownload();
        Campaign c = a.getCampaignTechnicalById("557150106");
        
//        System.out.println(/c.getCampaignContent());
        System.out.println(c.getAll());
    }

    

    public boolean isSameLogin(HttpDownload other) {
        if (other ==null)
            return false;
        return other.client.equals(client) && other.password.equals(password);
    }

    public boolean isSameLogin(String login, String password) {
        
        return  this.userName.equals(login) && this.password.equals(password);
    }
}
