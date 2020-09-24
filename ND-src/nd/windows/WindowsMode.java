package nd.windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

import nd.ND_MainClass;

public class WindowsMode {
	
// =============================( To Scan on Windows10 )============================================================== //

	public static void runWindowsDefender(String folderDownload, String downloadedFile) throws IOException, InterruptedException {

		Process windowsDefender = Runtime.getRuntime()
				.exec("\"C:\\Program Files\\Windows Defender\\MpCmdRun.exe\"  -Scan -ScanType 3 -File " + "\""
						+ folderDownload + "\\" + downloadedFile + "\"");
		
		windowsDefender.waitFor();
		
		BufferedReader windowsDefenderContent = new BufferedReader( new InputStreamReader(windowsDefender.getInputStream()));
		
		String infectedFiles;

		while ((infectedFiles = windowsDefenderContent.readLine()) != null) {
			
			if (infectedFiles.contains("threats")) {

				if (infectedFiles.equals("Scanning " + folderDownload + "\\" + downloadedFile + " found no threats.")) {
					JOptionPane.showMessageDialog(null, ND_MainClass.language.getYourDownloadedFile() + "'" + downloadedFile + "'" +
					ND_MainClass.language.getItHasNotViruses(), "BD", JOptionPane.INFORMATION_MESSAGE, ND_MainClass.iconND);
				} else {
					JOptionPane.showMessageDialog(null, ND_MainClass.language.getDetectedVirusesOn() + downloadedFile + "!", "BD", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		windowsDefenderContent.close();
	}
}
