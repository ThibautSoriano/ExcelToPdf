package main.java.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Utils {
	
	public static int TMP = 0;
	
	public static String changeExtension(String nameXls) {
		String namePdf = nameXls.substring(0, nameXls.length()-3);
		namePdf += "pdf";
		
		return namePdf;
	}

	public static boolean isXlsExension(String path) {
		if (path.length() < 5) {
			return false;
		}
		
		String extension = path.substring(path.length()-3, path.length());
		if (extension.equals("xls")) {
			return true;
		}

		return false;
	}

	public static int countTrueInTab(boolean [] tab){
	    int a = 0;
	    for (boolean b : tab) {
                if (b)
                    a++;
            }
	    return a;
	}
	
	public static int getNewTmpFileName() {
		TMP++;
		return TMP;
	}

	public static int parseInt(String textContent) {
		if (!"NULL".equals(textContent)) {
			return Integer.parseInt(textContent);
		}
		
		return 0;
	}

	public static float parseFloat(String textContent) {
		if (!"NULL".equals(textContent)) {
			return Float.parseFloat(textContent);
		}
		return 0;
	}
	
	
	public static String getPdfName(String campaignName) {
	    
	   String res = deAccent(campaignName);
	   res = res.replaceAll(" " , "_");
	   res = res.replaceAll("[^a-zA-Z0-9_\\-\\.]+", "");
        
	   return getNewFileBaseName(res,"pdf",0);
	    
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
	    while (true) {
	    String brazil = getPdfName("or.be öàç@&{'($¤**µ§<>go_ zéù%ccc-");
	    System.out.println(brazil);
	    
	    PrintWriter writer = new PrintWriter(brazil, "UTF-8");
	    writer.println("The first line");
	    
	    writer.close();
	    
	    }
	    
	}
	
	public static String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	public static String getNewFileBaseName(String fileName,String extension,int copy){
	    String name = fileName;
	    if (copy==0)
	        name+="."+extension;
	    else
	        name+="_"+copy+"."+extension;
	    
	    if (new File(name).exists())
	        return getNewFileBaseName(fileName, extension, copy+1);
	    
	    return name;
	        
	}
	
	
	
	public static String splitLongTextToFitPage(String txt) {
	    int maxLength = 40;
	        StringBuilder res = new StringBuilder();
	        int cpt = 0;
	        for (int i = 0; i< txt.length(); i++) {
	            if (cpt> maxLength && txt.charAt(i) == ' ') {
	                res.append("\n");
	                cpt = 0;
	                
	            }
	            else
	                res.append(txt.charAt(i));
	            cpt++;
	            
	            
	        }
	        return res.toString();
	        
	    }
	
}
