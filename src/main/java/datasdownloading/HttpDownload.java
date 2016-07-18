package main.java.datasdownloading;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import main.java.datasdownloading.entities.Campaign;
import main.java.datasdownloading.entities.CampaignHeader;

public class HttpDownload {

    private String userName;

    private String password;

    private String sessionId;

    public XmlReader xmlReader;

    public HttpDownload(String userName, String password) {

        this.userName = userName;
        this.password = password;

        HttpMessage m = login(this.userName, this.password);

        if (!m.isOk()) {
            System.out.println(m.getErrorMessage());
        }
    }

    public HttpDownload() {
        this("zburi_owner", "ad12dac");
    }

    public static void main(String[] args) throws Exception {
        HttpDownload http = new HttpDownload();

        System.out.println(http.getCampaignHeaders());
    }

    private HttpMessage sendGet(String url) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);

            return new HttpMessage(true, "OK", content);
        } catch (UnknownHostException e) {
            return new HttpMessage(false, "No internet", "");
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

            sessionId = XmlReader.getSessionID(m.getContent());

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

    public String getXmlCampaignDatas(String campaignID) {
        String url = "http://gdeapi.gemius.com/GetBasicStats.php?ignoreEmptyParams=Y&sessionID="
                + sessionId
                + "&dimensionIDs=1%2C20&indicatorIDs=4%2C28%2C16%2C2%2C30%2C120%2C99&campaignIDs="
                + campaignID + "&timeDivision=General";

        HttpMessage m = sendGet(url);

        if (m.isOk())
            return m.getContent();
        return "";
    }

    public String getXmlPlacementList(String campaignID) {
        String url = "http://gdeapi.gemius.com/GetPlacementsList.php?ignoreEmptyParams=Y&sessionID="
                + sessionId + "&campaignID=" + campaignID + "&showPaths=Y";

        HttpMessage m = sendGet(url);

        if (m.isOk())
            return m.getContent();
        return "";
    }

    public Campaign getCampaignById(String campaignId) {
        String url = "http://gdeapi.gemius.com/GetCampaignsList.php?ignoreEmptyParams=Y&sessionID="
                + sessionId;

        String xmlCampaignData = getXmlCampaignDatas(campaignId);
        
        String xmlPlacementList = getXmlPlacementList(campaignId);

        HttpMessage m = sendGet(url);

        if (m.isOk() && !"".equals(xmlCampaignData) && !"".equals(xmlPlacementList)) {
            if (xmlReader == null)
                xmlReader = new XmlReader(m.getContent());
            return xmlReader.getCampaign(campaignId, xmlCampaignData,xmlPlacementList);

        }
        return null;
    }

}
