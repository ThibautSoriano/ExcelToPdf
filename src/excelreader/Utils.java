package excelreader;

public class Utils {
	
	public static float stringToFloat(String s) {
		String split[] = s.split("%");
		
		return Float.parseFloat(split[0]);
	}

}
