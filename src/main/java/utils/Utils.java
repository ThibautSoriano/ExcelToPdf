package main.java.utils;
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
}
