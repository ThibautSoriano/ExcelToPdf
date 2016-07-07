package main.java.utils;

public enum Language {
    EN("en","US","English","ISO-8859-2"),HU("hu","HU","Magyar","UTF8"),FR("fr","FR","Français","ISO-8859-1");
    
    private String language, country,name, encoding;
    
    private Language(String language,String country,String name,String encoding){
        this.language = language;
        this.country = country;
        this.name= name;
        this.encoding = encoding;
                
    }
    
    public String getName() {
        return name;
    }
    
    public String getExtension() {
        return "_"+language+"_"+country;
    }

    public String getEncoding() {
        return encoding;
    }

    
}
