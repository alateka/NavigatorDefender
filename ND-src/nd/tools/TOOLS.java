/** 
Project: NavigatorDefender.
 *  
 * @author: ALATEKA 	
 * @source: TOOLS.java
 * @version: v0.0.10-alpha
 */
package nd.tools;

import javax.swing.JOptionPane;

import nd.ND_MainClass;

public class TOOLS {

	/**
	 * Checks system's locate and return your downloads directory.
	 * @param void
	 * @return - Downloads folder name
	 */
	public static String downloadsDir() {
		
		switch ( System.getProperty("user.country") ) {
		
		case "US": return "Downloads";
		
		case "EN": return "Downloads";
		
		case "ES": return "Descargas";
		
		default: return "Downloads";
		}
	}
	
	/**
	 * Shows a graphic message about exception error.
	 * @param Exception e - Current exception
	 * @param String message - Message to show
	 */
	public static void outException( Exception e, String message ) {
		JOptionPane.showMessageDialog(null, message+"\nERROR ==> "+e.getMessage(), "NavigatorDefender", JOptionPane.ERROR_MESSAGE, ND_MainClass.iconND);
	}
}
