package main.java.utils;

public enum Language {
    EN("en","US","English","ISO-8859-2", "MM/dd/yyyy"),HU("hu","HU","Magyar","UTF8", "yyyy. MM. dd."),FR("fr","FR","Français","ISO-8859-1", "dd/MM/yyyy");
    
    private String language, country,name, encoding, dateFormat;
    
    private Language(String language,String country,String name,String encoding,String dateFormat){
        this.language = language;
        this.country = country;
        this.name= name;
        this.encoding = encoding;
        this.dateFormat = dateFormat;
                
    }
    
    public String getDateFormat() {
        return dateFormat;
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
