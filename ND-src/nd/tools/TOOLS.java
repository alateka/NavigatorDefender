package nd.tools;

public class TOOLS {

	public static String downloadsDir() {
		
		switch ( System.getProperty("user.country") ) {
		
		case "US": return "Downloads";
		
		case "EN": return "Downloads";
		
		case "ES": return "Descargas";
		
		default: return "Downloads";
		}
	}
}
