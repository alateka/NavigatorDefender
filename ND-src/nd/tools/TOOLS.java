package nd.tools;

public class TOOLS {

	// This method checks system's locate and return your downloads directory.
	public static String downloadsDir() {
		
		switch ( System.getProperty("user.country") ) {
		
		case "US": return "Downloads";
		
		case "EN": return "Downloads";
		
		case "ES": return "Descargas";
		
		default: return "Downloads";
		}
	}
}
