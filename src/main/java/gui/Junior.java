package main.java.gui;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.UIManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.java.utils.Language;

public class Junior {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("fils de pute");
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream dias = classLoader.getClass().getResourceAsStream("/languages/language_hu_HU.json");
        
        InputStreamReader z = new InputStreamReader(dias,Language.EN.getEncoding());
        JSONParser parser = new JSONParser();
        JSONObject map = (JSONObject) parser.parse(z);
        
        
        System.out.println(map.get("Validate"));
    
       
        try {
            // set for file chooser look
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        
        
                    YUKAIWENPUTEJUNIORPUTEMERGUEZPUTECLOPPUTECAZALAPUTE window = new YUKAIWENPUTEJUNIORPUTEMERGUEZPUTECLOPPUTECAZALAPUTE();
                    window.frame.setVisible(true);
        
    }
    

}
