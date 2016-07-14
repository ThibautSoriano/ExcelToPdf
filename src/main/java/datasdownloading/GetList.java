package main.java.datasdownloading;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class GetList {
	
	public static void main(String[] args) throws Exception {

		GetList http = new GetList();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet("zburi_owner", "ad12dac");
		
	}
	
	// HTTP GET request
		private void sendGet(String loginName, String password) throws Exception {

			String url = "http://gdeapi.gemius.com/OpenSession.php?ignoreEmptyParams=Y&login="+ loginName + "&passwd=" + password;
			
//			URL obj = new URL(url);
//			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
//			con.setRequestMethod("GET");

			//add request header
//			con.setRequestProperty("User-Agent", "Mozilla/5.0");
//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' request to URL : " + url);
//			System.out.println("Response Code : " + responseCode);
			
			 HttpClient client = HttpClientBuilder.create().build();
		        HttpGet request = new HttpGet(url);

		        try {
		            HttpResponse response = client.execute(request);
		            HttpEntity entity = response.getEntity();

		            //
		            // Read the contents of an entity and return it as a String.
		            //
		            String content = EntityUtils.toString(entity);
		            System.out.println(content);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

//			BufferedReader in = new BufferedReader(
//			        new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			System.out.println(response.toString());

		}

}
