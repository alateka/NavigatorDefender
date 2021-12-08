/** 
Project: NavigatorDefender.
 *  
 * @author: ALATEKA 	
 * @source: LANG.java
 * @version: v0.0.10-alpha
 */

package nd.lang;

public class LANG {

	private String propertiesFileGenerated;
	private String NavigatorDefenderBackground;
	private String yourDownloadedFile;
	private String itHasNotViruses;
	private String detectedVirusesOn;


	/** Constructor
	 *
	 * @param propertiesFileGenerated
	 * @param NavigatorDefenderBackground
	 * @param yourDownloadedFile
	 * @param itHasNotViruses
	 * @param detectedVirusesOn
	 */
	public LANG(String propertiesFileGenerated, String NavigatorDefenderBackground, String yourDownloadedFile,
			String itHasNotViruses, String detectedVirusesOn) {
		this.propertiesFileGenerated = propertiesFileGenerated;
		this.NavigatorDefenderBackground = NavigatorDefenderBackground;
		this.yourDownloadedFile = yourDownloadedFile;
		this.itHasNotViruses = itHasNotViruses;
		this.detectedVirusesOn = detectedVirusesOn;
	}
	

	// -----------------( Getters & Setters )--------------------//

	public String getPropertiesFileGenerated() {
		return propertiesFileGenerated;
	}

	public void setPropertiesFileGenerated(String propertiesFileGenerated) {
		this.propertiesFileGenerated = propertiesFileGenerated;
	}

	public String getNavigatorDefenderBackground() {
		return NavigatorDefenderBackground;
	}

	public void setNavigatorDefenderBackground(String NavigatorDefenderBackground) {
		this.NavigatorDefenderBackground = NavigatorDefenderBackground;
	}

	public String getYourDownloadedFile() {
		return yourDownloadedFile;
	}

	public void setYourDownloadedFile(String yourDownloadedFile) {
		this.yourDownloadedFile = yourDownloadedFile;
	}

	public String getItHasNotViruses() {
		return itHasNotViruses;
	}

	public void setItHasNotViruses(String itHasNotViruses) {
		this.itHasNotViruses = itHasNotViruses;
	}

	public String getDetectedVirusesOn() {
		return detectedVirusesOn;
	}

	public void setDetectedVirusesOn(String detectedVirusesOn) {
		this.detectedVirusesOn = detectedVirusesOn;
	}
}
