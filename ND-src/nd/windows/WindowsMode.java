/** 
Project: NavigatorDefender.
 *  
 * @author: ALATEKA 	
 * @source: WindowsMode.java
 * @version: v0.0.10-alpha
 */
package nd.windows;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
////////////////////////////////
import nd.ND_MainClass;
import nd.tools.TOOLS;

public class WindowsMode {
	
// ======================( To work on Windows10 )======================//

	private static Process windowsDefender;
	
	/**
	 * Runs Windows Defender for check viruses.
	 * @param void
	 */
	public static void runWindowsDefender(String folderDownload, String downloadedFile)
	{
		
		try {
			windowsDefender = Runtime.getRuntime()
					.exec("\"C:\\Program Files\\Windows Defender\\MpCmdRun.exe\"  -Scan -ScanType 3 -File " +
			        "\"" + folderDownload + "\\" + downloadedFile + "\"");
			
		} catch (IOException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
		
		try {
			windowsDefender.waitFor();
		} catch (InterruptedException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
		
		BufferedReader windowsDefenderContent = new BufferedReader(
				new InputStreamReader(windowsDefender.getInputStream()));
		
		String infectedFiles;

		try {
			while ((infectedFiles = windowsDefenderContent.readLine()) != null) {
				
				if (infectedFiles.contains("threats")) {

					if (infectedFiles.equals("Scanning " + folderDownload + "\\" + downloadedFile + " found no threats.")) {
						JOptionPane.showMessageDialog(null, ND_MainClass.language.getYourDownloadedFile() + 
						"'" + downloadedFile + "'" + ND_MainClass.language.getItHasNotViruses(), 
						"ND", JOptionPane.INFORMATION_MESSAGE, ND_MainClass.iconND);
						
					} else {
						JOptionPane.showMessageDialog(null, ND_MainClass.language.getDetectedVirusesOn() +
								downloadedFile + "!", "ND", JOptionPane.ERROR_MESSAGE);
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
			windowsDefenderContent.close();
			
		} catch (IOException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
	}
}
