/** 
Project: NavigatorDefender.
 *  
 * @author: ALATEKA 	
 * @source: UnixMode.java
 * @version: v0.0.10-alpha
 */
package nd.unix;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
////////////////////////////////
import nd.ND_MainClass;
import nd.tools.TOOLS;

public class UnixMode {
	

// ================( To work on UNIX/UNIX-like )===================//

	private static Process clamav;

	/**
	 * Runs ClamAV for check viruses.
	 * @param void
	 */
	public static void runClamAV(String folderDownload, String downloadedFile) {

		try {
			clamav = Runtime.getRuntime().exec("clamscan " + 
					 folderDownload + "/" + downloadedFile);
			
		} catch (IOException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
		
		try {
			clamav.waitFor();
			
		} catch (InterruptedException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
		BufferedReader clamContent = new BufferedReader(
				new InputStreamReader(clamav.getInputStream()));

		String infectedFiles;

		try {
			while ((infectedFiles = clamContent.readLine()) != null) {

				if (infectedFiles.contains("Infected files:")) {

					if (infectedFiles.equals("Infected files: 0")) {
						JOptionPane.showMessageDialog(null, ND_MainClass.language.getYourDownloadedFile() + 
								"'" + downloadedFile + "'" +
								ND_MainClass.language.getItHasNotViruses(), "ND", 
								JOptionPane.INFORMATION_MESSAGE, ND_MainClass.iconND);
						
					} else {
						JOptionPane.showMessageDialog(null, ND_MainClass.language.getDetectedVirusesOn() + downloadedFile + "!", 
													  "ND", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} catch (HeadlessException e) {
			TOOLS.outException(e, "");
			System.exit(1);
			
		} catch (IOException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
		try {
			clamContent.close();
			
		} catch (IOException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
	}
}
