package nd.unix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

import nd.ND_MainClass;

public class UnixMode {
	

// =============================( To Scan on UNIX/UNIX-like )====================================================== //

	public static void runClamAV(String folderDownload, String downloadedFile) throws IOException, InterruptedException {

		Process clamav = Runtime.getRuntime().exec("clamscan " + folderDownload + "/" + downloadedFile);
		clamav.waitFor();
		BufferedReader clamContent = new BufferedReader(new InputStreamReader(clamav.getInputStream()));

		String infectedFiles;

		while ((infectedFiles = clamContent.readLine()) != null) {

			if (infectedFiles.contains("Infected files:")) {

				if (infectedFiles.equals("Infected files: 0")) {
					JOptionPane.showMessageDialog(null, ND_MainClass.language.getYourDownloadedFile() + "'" + downloadedFile + "'"
					+ ND_MainClass.language.getItHasNotViruses(), "ND", JOptionPane.INFORMATION_MESSAGE, ND_MainClass.iconND);
				} else {
					JOptionPane.showMessageDialog(null, ND_MainClass.language.getDetectedVirusesOn() + downloadedFile + "!", "ND", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		clamContent.close();
	}
}
