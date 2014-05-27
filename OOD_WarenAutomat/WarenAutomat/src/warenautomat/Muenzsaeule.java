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
	 * Pr�ft ob die gew�nschte Erh�hung oder Erniedrigung der Anzahl
	 * M�nzen in der S�ule erlaubt ist.
	 * 
	 * Setzt einen neuen M�nzbestand fest, welcher durch die Funktion
	 * "bestaetigeMuenzbestand" best�tigt werden muss.
	 * 
	 * @param pAnzMuenzen
	 * @return Gib aktueller Muenzbestand zur�ck oder die negative
	 * Anzahl �bersch�ssiger M�nzen. 
	 */
	public int pruefeMuenzbestand(int pAnzMuenzen){
		int retVal = 0;
		m_MuenzBestandZurBestaetigung = m_MuenzBestand + pAnzMuenzen;
		retVal = m_MuenzBestandZurBestaetigung;
		
		// S�ule voll 
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
	 * Best�tigt den berechnet M�nzbestand aus der Funktion 
	 * "pruefeMuenzbestand(int pAnzahlMuenzen)" und ersetzt den
	 * aktuellen M�nzbestand.
	 */
	public void bestaetigeMuenzbestand() {
		m_MuenzBestand = m_MuenzBestandZurBestaetigung;
	}
	
	/**
	 * Gibt den Totalbetrag der M�nzs�ule zur�ck
	 * Analyse: abgeleitetes Attribut
	 * 
	 * @return Totalbetrag in Rappen
	 */
	public int getTotalBetrag() {
		return (m_MuenzArt * m_MuenzBestand);
	}
	
	/**
	 * Gibt den �berfl�ssigen M�nzbestand der M�nzs�ule zur�ck
	 * 
	 * @return Anzahl �bersch�ssiger M�nzen
	 */
	public int getUeberfluessigerMuenzBestand() {
//		System.out.println("*** Muenzsaeule: getUeberfluessigerMuenzBestand: " + m_ueberfluessigerMuenzBestand);
		return m_UeberfluessigerMuenzBestand;
	}
	
	/**
	 * Gibt aktuellen M�nzbestand der S�ule zur�ck
	 * 
	 * @return Anzahl M�nzen der S�ule
	 */
	public int getMuenzBestand() {
		return m_MuenzBestand;
	}
	
	/** 
	 * Gibt die M�nzart der S�ule zur�ck
	 * 
	 * @return M�nzart in Rappen
	 */
	public int getMuenzArt() {
		return m_MuenzArt;
	}
}