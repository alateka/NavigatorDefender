/** 
Project: NavigatorDefender.
 *  
 * @author: ALATEKA 	
 * @source: ND_MainClass.java
 * @version: v0.0.10-alpha
 */
package nd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import nd.lang.LANG;
import nd.tools.TOOLS;
import nd.unix.UnixMode;
import nd.windows.WindowsMode;

public class ND_MainClass {

//=============================( Main Class )=================================================================//
	
	public static Properties propertiesFile;
	
    public static ImageIcon iconND = new ImageIcon("Icon-ND_Shield.png");
    
    private static WatchService FileDownloaded;
        
    private static WatchKey key;

	private static String osVersion = System.getProperty("os.name"); // What OS type can be?

	public static LANG language = new LANG("A properties file will be generated.",
			"NavigatorDefender is in background.\nIf you need to choose another directory, you will need to modify the properties file.",
			"The file ",
			" it has not viruses",
			"Detected Viruses on: ");
	

	//=============================( Main Method )==============================================================//

		public static void main(String[] args) {
			
	//---------------------------( Properties File )------------------------------------------------------------//
			propertiesFile = new Properties();
			
			try { // If the ND.properties file exist, then it is going to load its settings.
				
				propertiesFile.load(new FileReader("ND.properties"));
				
			} catch (FileNotFoundException e) { // If the ND.properties file not exist, then will create it.
				
				JOptionPane.showMessageDialog(null, language.getPropertiesFileGenerated(),"NavigatorDefender", JOptionPane.INFORMATION_MESSAGE);

				if (osVersion.contains("Linux")) { // If it's Linux, the ND.properties file will add Downloads folder for GNU/Linux users.
					propertiesFile.setProperty("Downloads_directory", System.getProperty("user.home") + "/" + TOOLS.downloadsDir());
					propertiesFile.setProperty("LANG", System.getProperty("user.language"));
					
				} else if (osVersion.contains("Windows")) { // If it's Windows, then the ND.properties file will add Downloads folder for Windows users.
					propertiesFile.setProperty("Downloads_directory", System.getProperty("user.home") + "\\" + TOOLS.downloadsDir());
					propertiesFile.setProperty("LANG", System.getProperty("user.language"));
				}
			} catch (IOException e) {
				TOOLS.outException(e, "");
				System.exit(1);
			}
			if ( propertiesFile.getProperty("LANG").equals("es")) {
				
				language.setPropertiesFileGenerated("Se generará un fichero de configuración");
				
				language.setNavigatorDefenderBackground(
						"NavigatorDefender esta en segundo plano."
						+ "\nSi necesitas elegir un directorio distinto, puede hacerlo modificando el fichero properties.");
				
				language.setYourDownloadedFile("El archivo ");
				
				language.setItHasNotViruses(" no tiene virus.");
				
				language.setDetectedVirusesOn("Se han detectado virus en: ");
			}
			
	        JOptionPane.showMessageDialog(null, language.getNavigatorDefenderBackground(), "NavigatorDefender", JOptionPane.INFORMATION_MESSAGE, iconND);
	     
	        try {
				propertiesFile.store(new FileOutputStream("ND.properties"), "\n Parámetros de configuración \n");
				
			} catch (FileNotFoundException e) {
				TOOLS.outException(e, "");
				System.exit(1);
				
			} catch (IOException e) {
				TOOLS.outException(e, "");
				System.exit(1);
			}
			// Because of OS type, This method will use UnixMode or WindowsMode.
	        FileDetector();
		}
	
	//------------------( File Detection -- WatchService )---------------------------------------------------------//

	/**
	* 
	* @param void
	*/
	public static void FileDetector() {

		// Object of type (WatchService) to watch registered objects for changes and events.
		try {
			FileDownloaded = FileSystems.getDefault().newWatchService();
			
		} catch (IOException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
		
		// (Path) object with the downloads directory.
		Path dowloadDir = Paths.get(ND_MainClass.propertiesFile.getProperty("Downloads_directory"));
		
		// (WatchKey) object to register modify events on download directory.
		
		try {
		key = dowloadDir.register(FileDownloaded, ENTRY_MODIFY);
			
		} catch (IOException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}

		byte eventsCount = 0;
		
		// This loop manages generated files from browser.
		try {
			while (FileDownloaded.take() != null) {
				
				for (WatchEvent<?> event : key.pollEvents()) {
					
					// Browsers will generate temporary files during a download, so it have not to be scan.
					if (event.context().toString().contains(".crdownload") 
							|| event.context().toString().contains(".part")
							|| event.context().toString().contains(".partial")
							|| event.context().toString().contains(".opdownload")
							|| event.context().toString().contains(".tmp")) {
						
					// If it is not a temporary file, it check what OS is.
					} else if ( osVersion.contains("Linux") ) {
						UnixMode.runClamAV(dowloadDir.toString(), event.context().toString());

					} else if ( osVersion.contains("Windows") ) {
						
						eventsCount++;
						
						if ( eventsCount == 2 ) {
							WindowsMode.runWindowsDefender(dowloadDir.toString(), event.context().toString());
							eventsCount = 0;
						}
					}
				}
				key.reset();
			}
		} catch (InterruptedException e) {
			TOOLS.outException(e, "");
			System.exit(1);
		}
	}
}
