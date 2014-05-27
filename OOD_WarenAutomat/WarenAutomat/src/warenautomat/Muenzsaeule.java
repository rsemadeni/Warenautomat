package warenautomat;


/**
 * @author RSemadeni
 * @version 1.0
 * @created 21-Mai-2014 18:34:03
 */
public class Muenzsaeule {

	private static int MAX_ANZAHL = 100;
	private int m_MuenzArt;
	private int m_MuenzBestand;
	private int m_MuenzBestandZurBestaetigung;
	private int m_UeberfluessigerMuenzBestand;

	public Muenzsaeule(int pMuenzArt){
		m_MuenzArt = pMuenzArt;
		m_MuenzBestand = 0;	
		m_MuenzBestandZurBestaetigung = 0;
		m_UeberfluessigerMuenzBestand = 0;
	}
	
	/**
	 * Prüft ob die gewünschte Erhöhung oder Erniedrigung der Anzahl
	 * Münzen in der Säule erlaubt ist.
	 * 
	 * Setzt einen neuen Münzbestand fest, welcher durch die Funktion
	 * "bestaetigeMuenzbestand" bestätigt werden muss.
	 * 
	 * @param pAnzMuenzen
	 * @return Gib aktueller Muenzbestand zurück oder die negative
	 * Anzahl überschüssiger Münzen. 
	 */
	public int pruefeMuenzbestand(int pAnzMuenzen){
		int retVal = 0;
		m_MuenzBestandZurBestaetigung = m_MuenzBestand + pAnzMuenzen;
		retVal = m_MuenzBestandZurBestaetigung;
		
		// Säule voll 
		if (m_MuenzBestandZurBestaetigung > MAX_ANZAHL) {
			int zuvieleMuenzen = m_MuenzBestandZurBestaetigung - MAX_ANZAHL ;
			m_MuenzBestandZurBestaetigung = MAX_ANZAHL;
			retVal = -zuvieleMuenzen;
			m_UeberfluessigerMuenzBestand = zuvieleMuenzen;
		} else if (m_MuenzBestandZurBestaetigung < 0) {
			retVal = 0;
			m_MuenzBestandZurBestaetigung = 0;
		}
		return retVal;
	}
	
	/**
	 * Bestätigt den berechnet Münzbestand aus der Funktion 
	 * "pruefeMuenzbestand(int pAnzahlMuenzen)" und ersetzt den
	 * aktuellen Münzbestand.
	 */
	public void bestaetigeMuenzbestand() {
		m_MuenzBestand = m_MuenzBestandZurBestaetigung;
	}
	
	/**
	 * Gibt den Totalbetrag der Münzsäule zurück
	 * Analyse: abgeleitetes Attribut
	 * 
	 * @return Totalbetrag in Rappen
	 */
	public int getTotalBetrag() {
		return (m_MuenzArt * m_MuenzBestand);
	}
	
	/**
	 * Gibt den überflüssigen Münzbestand der Münzsäule zurück
	 * 
	 * @return Anzahl überschüssiger Münzen
	 */
	public int getUeberfluessigerMuenzBestand() {
//		System.out.println("*** Muenzsaeule: getUeberfluessigerMuenzBestand: " + m_ueberfluessigerMuenzBestand);
		return m_UeberfluessigerMuenzBestand;
	}
	
	/**
	 * Gibt aktuellen Münzbestand der Säule zurück
	 * 
	 * @return Anzahl Münzen der Säule
	 */
	public int getMuenzBestand() {
		return m_MuenzBestand;
	}
	
	/** 
	 * Gibt die Münzart der Säule zurück
	 * 
	 * @return Münzart in Rappen
	 */
	public int getMuenzArt() {
		return m_MuenzArt;
	}
}