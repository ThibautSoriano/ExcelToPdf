package main.java.utils;

public enum Language {
    EN("en","US"),HU("hu","HU");
    
    private String language, country;
    
    private Language(String language,String country){
        this.language = language;
        this.country = country;
                
    }
    
    public String getExtension() {
        return "_"+language+"_"+country;
    }

}
