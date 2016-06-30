public class Utils {
	
	public static String changeExtension(String nameXls) {
		String namePdf = nameXls.substring(0, nameXls.length()-3);
		namePdf += "pdf";
		
		return namePdf;
	}

}
