package utils;

import java.util.ArrayList;
import java.util.List;

public enum FileType {

    EXCEL("Excel files","xls"),LOGO("Images","png");
    
    private String description;
    private String [] acceptedExtensions = null;
    
    public String [] getAcceptedExtensions() {
        return acceptedExtensions;
    }

    private FileType(String description,String... strings ) {
        this.description = description + " (";
        
        acceptedExtensions =strings;
        for (int i = 0; i< strings.length-1;i++) {
            this.description += "*."+strings[i] +"," ;
        }
        this.description += "*."+strings[strings.length-1] +")" ;
       
    }

    public String getDescription() {
        return description;
    }

    
    
}
