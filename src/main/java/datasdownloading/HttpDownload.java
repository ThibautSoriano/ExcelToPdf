package main.java.datasdownloading;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpDownload {
    
    
    private String userName;
    
    private String password;
    
    private String sessionId;
    
    
    public HttpDownload(String userName,String password){
        this.userName = userName;
        this.password = password;
    
        HttpMessage m = login(this.userName, this.password);
        
        if (!m.isOk()) {
            System.out.println(m.getErrorMessage());
        }
    }
    

    public HttpDownload(){
        this("zburi_owner", "ad12dac");
    }
    
    
    public static void main(String[] args) throws Exception {
        HttpDownload http = new HttpDownload();
    }

    
    
    
    
    
    private HttpMessage sendGet(String url) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);
            
            System.out.println(content);
        //TODO fill session id
            return new HttpMessage(true, "OK",content);
        }
         catch (UnknownHostException e) {
             return new HttpMessage(false, "No internet","");
        } catch (ClientProtocolException e) {
            return new HttpMessage(false, e.getMessage(),"");
        } catch (IOException e) {
            return new HttpMessage(false, e.getMessage(),"");
        }
    }
    
    
    /**
     *  Tries to log in with the distant host gdeapi.gemius.com, if succes fills sessionId
     * @param loginName
     * @param password
     * @return a message containing infos about the success of the login 
     */
    private HttpMessage login(String loginName, String password) {
        
        String url = "http://gdeapi.gemius.com/OpenSession.php?ignoreEmptyParams=Y&login="
                + loginName + "&passwd=" + password;

        HttpMessage m = sendGet(url);

        if (m.isOk())
            ; 
        
        
        return m;
    }
    
    
    public List<Float>() getCampaignHeaders(){
        
        
        
        String url = "http://gdeapi.gemius.com/GetCampaignsList.php?ignoreEmptyParams=Y&sessionID="+sessionId;
        
        HttpMessage m = sendGet(url);
        
        if (!m.isOk())
            System.out.println(m.getErrorMessage());
        return null;
        
        
    }
    

}