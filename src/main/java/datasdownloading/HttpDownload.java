package main.java.datasdownloading;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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

    private String userName;

    private String password; 

    private String sessionId;

    private XmlReader xmlReader;

    private HttpClient client;

    public HttpDownload(String userName, String password) throws Exception {
        client = HttpClientBuilder.create().build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(900).build();
        client = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig).build();

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

    public static void main(String[] args) throws Exception {
        HttpDownload http = new HttpDownload();

        System.out.println(http.getCampaignHeaders());
    }

    private HttpMessage sendGet(String url) {

        HttpGet request = new HttpGet(url);

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);

            return new HttpMessage(true, "OK", content);
        } catch (UnknownHostException e) {
            return new HttpMessage(false,
                    "Connection with the server failed.\nPlease check your internet connection",
                    "");
        } catch (ClientProtocolException e) {
            return new HttpMessage(false, e.getMessage(), "");
        } catch (IOException e) {
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

        String url = "http://gdeapi.gemius.com/OpenSession.php?ignoreEmptyParams=Y&login="
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

        String url = "http://gdeapi.gemius.com/GetCampaignsList.php?ignoreEmptyParams=Y&sessionID="
                + sessionId;

        HttpMessage m = sendGet(url);

        if (m.isOk()) {
            if (xmlReader == null)
                xmlReader = new XmlReader(m.getContent());
            return xmlReader.getAllHeaders();
        } else
            return new ArrayList<CampaignHeader>();

    }

    private HttpMessage getXmlCampaignDatas(String campaignID) {
        String url = "http://gdeapi.gemius.com/GetBasicStats.php?ignoreEmptyParams=Y&sessionID="
                + sessionId
                + "&dimensionIDs=1%2C20&indicatorIDs=4%2C28%2C16%2C2%2C30%2C120%2C99&campaignIDs="
                + campaignID + "&timeDivision=General";

        HttpMessage m = sendGet(url);

        if (m.isOk())
            return new HttpMessage(true, "", m.getContent());
        return m;
    }

    private HttpMessage getXmlPlacementList(String campaignID) {
        String url = "http://gdeapi.gemius.com/GetPlacementsList.php?ignoreEmptyParams=Y&sessionID="
                + sessionId + "&campaignID=" + campaignID + "&showPaths=Y";

        HttpMessage m = sendGet(url);

        if (m.isOk())
            return new HttpMessage(true, "", m.getContent());
        return m;
    }

    public Campaign getCampaignRankingsById(String campaignId) {

        if (xmlReader == null) {
            String url = "http://gdeapi.gemius.com/GetCampaignsList.php?ignoreEmptyParams=Y&sessionID="
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
            String url = "http://gdeapi.gemius.com/GetTechStats.php?ignoreEmptyParams=Y&sessionID="
                    + sessionId;

            HttpMessage campaignList = sendGet(url);
            if (campaignList.isOk())
                xmlReader = new XmlReader(campaignList.getContent());
            else
                return null;
        }

       
        HttpMessage campaignData = sendGet(
                "http://gdeapi.gemius.com/GetTechStats.php?"
                + "ignoreEmptyParams=Y&"
                + "sessionID=g359f9568a0c81bc"
                + "&dimensionIDs=1&indicatorIDs=4%2C28%2C16%2C2%2C30%2C120%2C99&techDimension=Region&"
                + "campaignIDs=557150106");
        
        HttpMessage all = sendGet( "http://gdeapi.gemius.com/GetTechStats.php?ignoreEmptyParams=Y&"
                + "sessionID=g5e878ecc5dbb34d"
                + "&dimensionIDs=1&indicatorIDs=4%2C28%2C16%2C2%2C30%2C120%2C99&"
                + "campaignIDs=557150106"
                + "&techDimension=Country");
        
        
        
        HttpMessage mapIdToCounty = sendGet("http://gdeapi.gemius.com/GetRegionsList.php?ignoreEmptyParams=Y&"
                + "sessionID="+sessionId
                + "&countryID=44");
        

        if (campaignData.isOk() && campaignData.isOk() && mapIdToCounty.isOk()) {
            return xmlReader.getCampaignTechnical(campaignId, campaignData.getContent(),
                    all.getContent(),mapIdToCounty.getContent(),BUDAPEST_ID);

        }
        return null;
    }

}
