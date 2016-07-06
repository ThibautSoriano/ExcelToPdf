package main.java.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Internationalization {
    
    public static JSONObject map;
    public static boolean init = false;
    
    static {
        loadLanguage(Language.EN);        
    }
    

    public static void main(String []args)
    {
        System.out.println(Internationalization.map.get("Browse"));
    }
    
    
    
    public static void loadLanguage(Language lang){
        JSONParser parser = new JSONParser();
        
        System.out.println('a');
        InputStreamReader z = null;
        try {
            z = new InputStreamReader(new FileInputStream("src/main/resources/languages/language"+lang.getExtension()+".json"),"ISO-8859-2");
            map = (JSONObject) parser.parse(z);
            if (init)
                JOptionPane.showMessageDialog(null, "Language changed","Language changed to"+lang.getExtension(), JOptionPane.INFORMATION_MESSAGE);
            init = true;
        
        } catch (UnsupportedEncodingException e) {
            
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            
            e.printStackTrace(); 
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (ParseException e) {
           
            e.printStackTrace();
        }
    }
    
    public static String getKey(String key) {
        if ( map.get(key) != null) 
            return (String) map.get(key);
        return "???";
    }
    
}
